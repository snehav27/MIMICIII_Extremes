package com.project.dao;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;

public class PDFGenerator {
	public static void generate(ArrayList<ArrayList<String>> result) {
		JFileChooser dialog = new JFileChooser();

		int dialogResult = dialog.showSaveDialog(null);

		if (dialogResult==JFileChooser.APPROVE_OPTION){
			String filePath = dialog.getSelectedFile().getPath();

			try {
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(filePath));
				document.open();
				PdfPTable table = new PdfPTable(3);

				for(ArrayList<String> res: result){
					table.addCell(res.get(0));
					table.addCell(res.get(1));
					table.addCell(res.get(2));
				}
				document.add(table);
				document.close();
			}
			catch(DocumentException de) {
				de.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public static void generatePatientRecords(HashMap<Integer, ArrayList<String>> diagnoses, 
			HashMap<Integer, ArrayList<String>> procedures, HashMap<Integer, ArrayList<ArrayList<String>>> vitals){
		JFileChooser dialog = new JFileChooser();

		int dialogResult = dialog.showSaveDialog(null);

		if (dialogResult==JFileChooser.APPROVE_OPTION){

			String filePath = dialog.getSelectedFile().getPath();
			try {
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(filePath));
				document.open();
				for(Map.Entry<Integer, ArrayList<String>> entry: diagnoses.entrySet()) {
					int id = entry.getKey();
					document.add(new Paragraph("Admissions number: " + Integer.toString(id) + System.lineSeparator()));

					if(!diagnoses.isEmpty()) {
						document.add(new Paragraph("Diagnoses"));
						document.add(new Paragraph(System.lineSeparator()));
						PdfPTable table = new PdfPTable(1);
						for(String doc: entry.getValue()) {
							table.addCell(doc);
						}
						document.add(table);
					}
					else {
						document.add(new Paragraph("Diagnoses: No records available"));
						document.add(new Paragraph(System.lineSeparator()));
					}
					if(!procedures.get(id).isEmpty()) {
						document.add(new Paragraph("Procedures"));
						document.add(new Paragraph(System.lineSeparator()));
						PdfPTable table = new PdfPTable(1);
						for(String doc: procedures.get(id)) {
							table.addCell(doc);
						}
						document.add(table);
					}
					else {
						document.add(new Paragraph("Procedures: No records available"));
						document.add(new Paragraph(System.lineSeparator()));
					}
					if(!vitals.get(id).isEmpty()) {
						document.add(new Paragraph("Vitals" ));
						document.add(new Paragraph(System.lineSeparator()));
						PdfPTable table = new PdfPTable(3);
						table.addCell("Vital Type");
						table.addCell("Measurement");
						table.addCell("Unit");
						for(ArrayList<String> res: vitals.get(id)){
							table.addCell(res.get(0));
							table.addCell(res.get(1));
							table.addCell(res.get(2));
						}
						document.add(table);
					}
					else{
						document.add(new Paragraph("Vitals: No data available" ));
						document.add(new Paragraph(System.lineSeparator()));
					}
					document.add(new Paragraph(System.lineSeparator()));
				}
				document.close();
			}
			catch(DocumentException de) {
				de.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
