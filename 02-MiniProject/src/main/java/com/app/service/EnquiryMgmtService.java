package com.app.service;

import java.util.List;

import com.app.binding.EnquiryForm;
import com.app.binding.DashboardResponseForm;
import com.app.binding.EnqSearchCriteriaForm;
import com.app.entity.CourseEntity;
import com.app.entity.CustomertEnquiriesEntity;
import com.app.entity.UserDetailsEntity;

public interface EnquiryMgmtService {

	public DashboardResponseForm getDashBoardData(Integer userId);

	public List<String> getcourseNames();

	public List<CustomertEnquiriesEntity> getEnqStatus();

	public boolean saveEnquiry(EnquiryForm enqForm);

    public List<CustomertEnquiriesEntity> getEnquiries();
	
//	//public boolean upsert(AddStudentEnqForm enqForm);
    public List<CustomertEnquiriesEntity> getFilterEnquiries(EnqSearchCriteriaForm criteria,Integer userId);
    
//
//	public CustomertEnquiriesEntity getStudentById(Integer id);
//	
//	public boolean updateStudent(CustomertEnquiriesEntity studEntity );
//	
//	public UserDetailsEntity getUserById(Integer userId);
}
