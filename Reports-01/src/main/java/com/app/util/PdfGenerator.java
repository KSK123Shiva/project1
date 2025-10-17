package com.app.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.app.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {
	
	public void pdfGenerate(HttpServletResponse response,List<CitizenPlan> plans,File f) throws Exception {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(20);

		// Paragraph p = new Paragraph("Citizen plans info)
		// document.add(p);
		Paragraph paragraph = new Paragraph("Citizen plans info", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(6);
		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");

		//List<CitizenPlan> plans = citizenPlanRepo.findAll();

		for (CitizenPlan plan : plans) {

			table.addCell(String.valueOf(plan.getCitizenName()));
			table.addCell(String.valueOf(plan.getCitizenName()));
			table.addCell(String.valueOf(plan.getPlanName()));
			table.addCell(String.valueOf(plan.getPlanStatus()));
			table.addCell(String.valueOf(plan.getPlanStartDate()) + "");
			table.addCell(String.valueOf(plan.getPlanEndDate()) + "");

		}
		document.add(table);
		document.close();
		
	}

}
