
package com.app.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.binding.LoginForm;
import com.app.binding.SignUpForm;
import com.app.binding.UnlockAccForm;
import com.app.entity.UserDetailsEntity;
import com.app.repo.UserDtlsRepo;
import com.app.util.EmailUtils;
import com.app.util.PwdUtils;

import net.bytebuddy.asm.Advice.Return;

@Service
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsRepo userRepo;

	@Autowired
	private EmailUtils myEmail;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean signUp(SignUpForm form) {

		UserDetailsEntity user = userRepo.findByEmail(form.getEmail());
		if (user != null) {
			return false;
		}

		// 0)TODO: copy data from bindingobj to entity obj
		UserDetailsEntity entity = new UserDetailsEntity();
		BeanUtils.copyProperties(form, entity);

		// 1)TODO: generate randome password and set to object
		String tempPwd = PwdUtils.generatePassword();
		entity.setPwd(tempPwd);

		// 2)TODO: set accc status to locked
		entity.setAccStatus("LOCKED");

		// 3)TODO: insert record into database
		userRepo.save(entity);

		// 4)TODO: send email to unlock the account
		String to = form.getEmail();
		String subject = "unlock your insurance account ";
		StringBuffer body = new StringBuffer();

		// use below temorary pwd to unlock account // temp pwd: yyoiyoiuo
		body.append("temporary password: " + tempPwd);
		body.append("<br>");
		body.append("<h2>use below link to unlock your account</h2>");
		body.append("<br>");
		// < href="unlock?emai="" +
		body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">click here to unlock your account");
		myEmail.sendEmail(to, subject, body.toString());
		return true;
	}

	@Override
	public boolean unlockAcc(UnlockAccForm form) {

		UserDetailsEntity entity = userRepo.findByEmail(form.getEmail());
		if (entity.getPwd().equals(form.getTempPwd())) {

			entity.setPwd(form.getNewPwd());
			entity.setAccStatus("UnLocked");
			userRepo.save(entity);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String login(LoginForm form) {
		UserDetailsEntity entity = userRepo.findByEmailAndPwd(form.getEmail(), form.getPassword());
		if (entity == null) {
			return "Invalid Cresentials";
		}
		if (entity.getAccStatus().equals("LOCKED")) {
			return "Your Account is locked";

		}
		session.setAttribute("userId", entity.getUserId());
		//create session and store user data in session
		return "Succesfully Login";

	}

	@Override
	public boolean forgotPwd(String email) {
		// Check record presence in db with given email
		UserDetailsEntity entity = userRepo.findByEmail(email);

		// if record not availble send error msg
		// if record not availble retuen flase
		if (entity == null) {
			return false;
		}

		// if record available send pwd to email and send succ msg
		// if record available send pwd to email and return true
		String subject = "Recover Password";
		String body = "Your Pwd :: " + entity.getPwd();
		myEmail.sendEmail(email, subject, body);
		return true;
	}

}
