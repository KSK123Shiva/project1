package com.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.app.entity.CitizenPlan;
import com.app.repo.CitizenPlanRepo;
import com.app.request.SearchRequest;
import com.app.util.EmailUtils1;
import com.app.util.ExcelGenerator;
import com.app.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo citizenPlanRepo;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private EmailUtils1 emitUtils;

	@Override
	public List<String> getPlanPlanNames() {
		return citizenPlanRepo.getPlanName();
	}

	@Override
	public List<String> getPlanStatuses() {
		return citizenPlanRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();
		// BeanUtils.copyProperties(request, plan);
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);

			entity.setPlanStartDate(localDate);
		}

		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(endDate, formatter);

			entity.setPlanEndDate(localDate);
		}
		return citizenPlanRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportData(HttpServletResponse response) throws Exception {
		
		

		File f = new File("Plans.xls");
		List<CitizenPlan> plans = citizenPlanRepo.findAll();
		excelGenerator.generate(response, plans, f);
		String subject = "Test mail Subject";
		String body = "<h1> Test Mail Body,</h1>";
		String to = "shivajavatech124@gmail.com";
		// File f= new File("plans.xls");
		emitUtils.sendEmail(subject, body, to, f);
		// f.delete();
		return true;

	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans = citizenPlanRepo.findAll();
		File f = new File("plans.pdf");
		pdfGenerator.pdfGenerate(response, plans,f);
		String subject = "Test mail Subject";
		String body = "<h1> Test Mail Body,</h1>";
		String to = "shivajavatech124@gmail.com";
		// File f = new File("plans.pdf");
		emitUtils.sendEmail(subject, body, to, f);
		  f.delete();
		return true;

	}

}
