package com.app.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.binding.EnquiryForm;
import com.app.entity.CourseEntity;
import com.app.entity.EnqStatusEntity;
import com.app.repo.CourseEntityRepo;
import com.app.repo.EnqStatusRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CourseEntityRepo courseRepo;

	@Autowired
	private EnqStatusRepo statusRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		CourseEntity cl1 = new CourseEntity();
		cl1.setCourseName("LIC");

		CourseEntity cl2 = new CourseEntity();
		cl2.setCourseName("TATA");

		CourseEntity cl3 = new CourseEntity();
		cl3.setCourseName("HDFC");

		courseRepo.saveAll(Arrays.asList(cl1, cl2, cl3));

		courseRepo.deleteAll();

		EnqStatusEntity ef1 = new EnqStatusEntity();
		ef1.setEnqStatus("New");

		EnqStatusEntity ef2 = new EnqStatusEntity();
		ef2.setEnqStatus("Enrolled");

		EnqStatusEntity ef3 = new EnqStatusEntity();
		ef3.setEnqStatus("Lost");

		statusRepo.saveAll(Arrays.asList(ef1));

	}

}
