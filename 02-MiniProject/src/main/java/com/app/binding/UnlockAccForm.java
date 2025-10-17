package com.app.binding;

import lombok.Data;

@Data
public class UnlockAccForm {
	
	private String email;
	private String tempPwd;
	private String newPwd;
	//private String confirmNewPwd;
	private String confirmPwd;

}
