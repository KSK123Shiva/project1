package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.binding.DashboardResponseForm;
import com.app.binding.EnqSearchCriteriaForm;
import com.app.binding.EnquiryForm;
import com.app.entity.CourseEntity;
import com.app.entity.CustomertEnquiriesEntity;
import com.app.entity.EnqStatusEntity;
import com.app.entity.UserDetailsEntity;
import com.app.repo.CourseEntityRepo;
import com.app.repo.CustomerEnqRepo;
import com.app.repo.EnqStatusRepo;
import com.app.repo.UserDtlsRepo;

@Service
public class EnquiryMgmtServiceImpl implements EnquiryMgmtService {

	@Autowired
	private HttpSession session;

	@Autowired
	public CustomerEnqRepo customerEnqRepo;

	@Autowired
	private CourseEntityRepo courseRepo;

	@Autowired
	private UserDtlsRepo userRepo;

	@Autowired
	private EnqStatusRepo enqStatusRepo;

	@Override
	public DashboardResponseForm getDashBoardData(Integer userId) {

		DashboardResponseForm response = new DashboardResponseForm();

		Optional<UserDetailsEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {
			UserDetailsEntity userEntity = findById.get();
			List<CustomertEnquiriesEntity> custEnquries = userEntity.getCustEnquries();
			Integer totalCount = custEnquries.size();
			Integer enrolledCount = custEnquries.stream().filter(e -> e.getEnqStatus().equals("ENROLLED"))
					.collect(Collectors.toList()).size();

			Integer lostCount = custEnquries.stream().filter(e -> e.getEnqStatus().equals("LOST"))
					.collect(Collectors.toList()).size();

			response.setTotalEnqCount(totalCount);
			response.setEnrolledEnqCount(enrolledCount);
			response.setLostEnqCount(lostCount);
		}
		return response;
	}

	@Override
	public List<String> getcourseNames() {

		List<CourseEntity> findAll = courseRepo.findAll();

		List<String> names = new ArrayList<>();

		for (CourseEntity entity : findAll) {

			names.add(entity.getCourseName());

		}
		return names;
	}

	@Override
	public List<CustomertEnquiriesEntity> getEnqStatus() {

		List<EnqStatusEntity> findAll = enqStatusRepo.findAll();

		List<String> statusList = new ArrayList<>();

		for (EnqStatusEntity entity : findAll) {

			statusList.add(entity.getEnqStatus());

		}
		return null;
	}

	@Override
	public boolean saveEnquiry(EnquiryForm enqForm) {

		CustomertEnquiriesEntity engEntity = new CustomertEnquiriesEntity();
		BeanUtils.copyProperties(enqForm, engEntity);
		Integer userId = (Integer) session.getAttribute("userId");
		UserDetailsEntity userEntity = userRepo.findById(userId).get();
		engEntity.setUser(userEntity);

		customerEnqRepo.save(engEntity);
		return true;
	}

	@Override
	public List<CustomertEnquiriesEntity> getEnquiries() {

		Integer userId = (Integer) session.getAttribute("userId");
		Optional<UserDetailsEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {
			UserDetailsEntity userDetailsEntity = findById.get();
			List<CustomertEnquiriesEntity> custEnquries = userDetailsEntity.getCustEnquries();
			return custEnquries;
		}
		return null;
	}

	@Override
	public List<CustomertEnquiriesEntity> getFilterEnquiries(EnqSearchCriteriaForm criteria, Integer userId) {
		//Integer userId = (Integer) session.getAttribute("userId");
		
		Optional<UserDetailsEntity> findById = userRepo.findById(userId);
		if (findById.isPresent()) {
			UserDetailsEntity userDetailsEntity = findById.get();
			List<CustomertEnquiriesEntity> custEnquries = userDetailsEntity.getCustEnquries();
			return custEnquries;
		}
		
		return null;
	}

}
