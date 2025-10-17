package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.binding.LoginForm;
import com.app.binding.SignUpForm;
import com.app.binding.UnlockAccForm;
import com.app.service.UserService;

@Controller
public class UserContoller {

	@Autowired
	private UserService userService;

	// to handle signUp functionality
	@PostMapping("/signup")
	public String handleSignUpFun(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = userService.signUp(form);
		if (status) {
			model.addAttribute("succMsg", "Account created,Check your email");
		} else {
			model.addAttribute("errorMsg", "try With Different Email or Choose unique email");
		}
		return "signup";
	}

	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";

	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {

		UnlockAccForm unlockAccFormObj = new UnlockAccForm();
		unlockAccFormObj.setEmail(email);
		// sending bindingobject to UI
		model.addAttribute("unlock", unlockAccFormObj);
		return "unlock";

	}

	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockAccForm unlock, Model model) {

		// System.out.println(unlock);

		if (unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userService.unlockAcc(unlock);
			if (status) {
				model.addAttribute("succMsg", "your account is unlocked");
			} else {
				model.addAttribute("errMsg", "Given temporary pwd is incorrect,Check your email");
			}

		} else {
			model.addAttribute("errMsg", "new pwd and confirm pwd Should be same");
		}

		return "unlock";

	}

	@GetMapping("/login")
	public String loginPage(Model model) { 
		model.addAttribute("loginForm", new LoginForm());
		return "login";

	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm form, Model model) {
		System.out.println(form);
		String status = userService.login(form);

		if (status.contains("sucess")) {
			
			
			//redirect request to dashboard method
			return "redirect:/dashboard";
		}
		model.addAttribute("errMsg", status);
		return "login";

	}

	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotpwd";

	}

	@PostMapping("/forgotPwd")
	public String forgotPWd(@RequestParam("email") String email, Model model) {

		System.out.println(email);
		boolean status = userService.forgotPwd(email);

		if (status) {
			// send success msg
			model.addAttribute("succMsg", "Pwd sent to your email");
		} else {
			// send error msg
			model.addAttribute("errorMsg", "Invalid email");
		}

		return "forgotpwd";

	}

}
