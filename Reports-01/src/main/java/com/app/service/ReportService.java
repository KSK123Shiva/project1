package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.app.entity.CitizenPlan;
import com.app.request.SearchRequest;

public interface ReportService {

	public List<String> getPlanPlanNames();

	public List<String> getPlanStatuses();

	public List<CitizenPlan> search(SearchRequest request);
	//public boolean exportData();

	public boolean exportData(HttpServletResponse response) throws Exception;

	//public boolean exportPdf();
	public boolean exportPdf(HttpServletResponse response) throws Exception;

}
