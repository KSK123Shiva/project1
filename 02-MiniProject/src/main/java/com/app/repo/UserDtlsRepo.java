package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.UserDetailsEntity;

@Repository
public interface UserDtlsRepo extends JpaRepository<UserDetailsEntity, Integer>{
	
	public UserDetailsEntity findByEmail(String email);
	
	public UserDetailsEntity findByEmailAndPwd(String email,String pwd);

}
