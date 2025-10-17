package com.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.binding.DashboardResponseForm;
import com.app.binding.EnqSearchCriteriaForm;
import com.app.binding.EnquiryForm;
import com.app.entity.CustomertEnquiriesEntity;
import com.app.service.EnquiryMgmtService;

@Controller
public class EnquiryContoller {

	@Autowired
	private HttpSession session;
	private EnquiryMgmtService enqService;

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";

	}

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {

		Integer userId = (Integer) session.getAttribute("userId");
		DashboardResponseForm dashboardData = enqService.getDashBoardData(userId);

		model.addAttribute(" ", dashboardData);
		//
		System.out.println("Dashboard method called......");
		return "dashboard";

	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {

		// get courses for drop down
		List<String> courses = enqService.getcourseNames();

		// get enquire status for drop down
		 List<CustomertEnquiriesEntity> enqStatus = enqService.getEnqStatus();

		// get binding class object
		EnqSearchCriteriaForm formObj = new EnqSearchCriteriaForm();

		// set model data into object
		model.addAttribute("courseNames", courses);
		model.addAttribute("enqStatusNames", enqStatus);
		model.addAttribute("formObj", formObj);

		return "add-enquiry";

	}

	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model) {

		System.out.println(formObj);

		boolean status = enqService.saveEnquiry(formObj);
		if (status) {

			model.addAttribute("succMsg", "Enquiry Added");
		} else {
			model.addAttribute("errMsg", "Problem Occured");
		}

		return "add-enquiry";

	}

	@GetMapping("/enquires")
	public String viewEnquiryPage(EnqSearchCriteriaForm criteriaForm, Model model) {
		initForm(model);
		model.addAttribute("searchForm", new EnqSearchCriteriaForm());
		List<CustomertEnquiriesEntity> enquiriesEntities = enqService.getEnqStatus();
		model.addAttribute("enquiries", enquiriesEntities);
		return "view-enquiries";

	}

	private void initForm(Model model) {

	}

}
