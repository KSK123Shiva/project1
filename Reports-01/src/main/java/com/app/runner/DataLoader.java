package com.app.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.app.entity.CitizenPlan;
import com.app.repo.CitizenPlanRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepo citizenPlanRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		citizenPlanRepo.deleteAll();
		
		// cash Plan data
		CitizenPlan cp1 = new CitizenPlan();
		cp1.setCitizenName("John");
		cp1.setGender("Male");
		cp1.setPlanName("Cash");
		cp1.setPlanStatus("Approved");
		cp1.setPlanStartDate(LocalDate.now());
		cp1.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp1.setBeniftAmount(5000.00);

		CitizenPlan cp2 = new CitizenPlan();
		cp2.setCitizenName("Smith");
		cp2.setGender("Male");
		cp2.setPlanName("Cash");
		cp2.setPlanStatus("Denied");
		cp2.setDenialReason("Rental incode");

		CitizenPlan cp3 = new CitizenPlan();
		cp3.setCitizenName("Cathy");
		cp3.setGender("Female");
		cp3.setPlanName("Cash");
		cp3.setPlanStatus("Terminated");
		cp3.setPlanStartDate(LocalDate.now());
		cp3.setPlanEndDate(LocalDate.now().minusMonths(4));
		cp3.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp3.setBeniftAmount(5000.00);
		cp3.setTerminatedDate(LocalDate.now());
		cp3.setTeminationReson("Employed");

		// Food Plan data
		CitizenPlan cp4 = new CitizenPlan();
		cp4.setCitizenName("David");
		cp4.setGender("Male");
		cp4.setPlanName("Food");
		cp4.setPlanStatus("Approved");
		cp4.setPlanStartDate(LocalDate.now());
		cp4.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp4.setBeniftAmount(4000.00);

		CitizenPlan cp5 = new CitizenPlan();
		cp5.setCitizenName("Robert");
		cp5.setGender("Male");
		cp5.setPlanName("Food");
		cp5.setPlanStatus("Denied");
		cp5.setDenialReason("property incode");

		CitizenPlan cp6 = new CitizenPlan();
		cp6.setCitizenName("Orlen");
		cp6.setGender("Female");
		cp6.setPlanName("Food");
		cp6.setPlanStatus("Terminated");
		cp6.setPlanStartDate(LocalDate.now());
		cp6.setPlanEndDate(LocalDate.now().minusMonths(4));
		cp6.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp6.setBeniftAmount(5000.00);
		cp6.setTerminatedDate(LocalDate.now());
		cp6.setTeminationReson("Employed");

		// medical Plan data
		CitizenPlan cp7 = new CitizenPlan();
		cp7.setCitizenName("Charles");
		cp7.setGender("Male");
		cp7.setPlanName("Medical");
		cp7.setPlanStatus("Approved");
		cp7.setPlanStartDate(LocalDate.now());
		cp7.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp7.setBeniftAmount(5000.00);

		CitizenPlan cp8 = new CitizenPlan();
		cp8.setCitizenName("Butler");
		cp8.setGender("Male");
		cp8.setPlanName("Medical");
		cp8.setPlanStatus("Denied");
		cp8.setDenialReason("propert incode");

		CitizenPlan cp9 = new CitizenPlan();
		cp9.setCitizenName("Neel");
		cp9.setGender("Female");
		cp9.setPlanName("Medical");
		cp9.setPlanStatus("Terminated");
		cp9.setPlanStartDate(LocalDate.now());
		cp9.setPlanEndDate(LocalDate.now().minusMonths(4));
		cp9.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp9.setBeniftAmount(5000.00);
		cp9.setTerminatedDate(LocalDate.now());
		cp9.setTeminationReson("Govt Job");

		// Employment Plan data
		CitizenPlan cp10 = new CitizenPlan();
		cp10.setCitizenName("Steave");
		cp10.setGender("Male");
		cp10.setPlanName("Employment");
		cp10.setPlanStatus("Approved");
		cp10.setPlanStartDate(LocalDate.now());
		cp10.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp10.setBeniftAmount(5000.00);

		CitizenPlan cp11 = new CitizenPlan();
		cp11.setCitizenName("Moris");
		cp11.setGender("Male");
		cp11.setPlanName("Employment");
		cp11.setPlanStatus("Denied");
		cp11.setDenialReason("propert incode");

		CitizenPlan cp12 = new CitizenPlan();
		cp12.setCitizenName("Bibs");
		cp12.setGender("Female");
		cp12.setPlanName("Employment");
		cp12.setPlanStatus("Terminated");
		cp12.setPlanStartDate(LocalDate.now());
		cp12.setPlanEndDate(LocalDate.now().minusMonths(4));
		cp12.setPlanEndDate(LocalDate.now().plusMonths(6));
		cp12.setBeniftAmount(5000.00);
		cp12.setTerminatedDate(LocalDate.now());
		cp12.setTeminationReson("Govt Job");
		
		List<CitizenPlan> list = Arrays.asList(cp1,cp2,cp3,cp4,cp5,cp6,cp7,cp8,cp9,cp10,cp11,cp12);
		citizenPlanRepo.saveAll(list);
		
		  

	}

}
