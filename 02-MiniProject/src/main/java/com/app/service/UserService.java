package com.app.service;

import com.app.binding.LoginForm;
import com.app.binding.SignUpForm;
import com.app.binding.UnlockAccForm;

public interface UserService {

	public boolean signUp(SignUpForm form);

	public boolean unlockAcc(UnlockAccForm form);
	
	public String login(LoginForm form);
	
	public boolean forgotPwd(String email);
}
