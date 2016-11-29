package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.project.dao.Demographics;

public class ExtremeOfPatientDAO {

	public ArrayList<ArrayList<String>> getExtremeOfPatient(String order, int patient_id) throws SQLException{
		StringBuilder query = new StringBuilder();
		
		System.out.println("patient_id"+patient_id);
		query.append("Select tb.label,  tb.valuenum, tb.valueom   from (");
		query.append("select  ROW_NUMBER()OVER (PARTITION BY row.label order by row.valuenum " + order + ") AS r, row.valueom, row.valuenum, row.label from ");
		query.append("(select   distinct c.valueom, c.valuenum, d.label from admissions a, chartEvents c,(select itemid, label from d_items  group by label, itemid)d where ");
		query.append("c.valuenum <> 0 and c.hadm_id = a.hadm_id and d.itemid = c.itemid ");
		query.append("and a.subject_id in (" + patient_id + ")");
		query.append(" order by c.valuenum ) row )  tb where tb.r<=10 order by tb.label, tb.valuenum "+order);
		
		System.out.println(query.toString());
		PreparedStatement getExtremesStmt = Demographics.conn.prepareStatement(query.toString());
		
		ResultSet rs = getExtremesStmt.executeQuery();
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String>  header = new ArrayList<String>();
		header.add("Description");
		header.add("Value");
		header.add("Units");
		result.add(header);
		while(rs.next()) {
			ArrayList<String>  res = new ArrayList<String>();
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			result.add(res);
		}
		return result;
	}
	
	public void getPatientDetails(String order, int patient_id) throws SQLException{
		PreparedStatement getHadmIdStmt = Demographics.conn.
				prepareStatement("Select distinct a.hadm_id from admissions a, patients p where p.subject_id=?"
						+ " and p.subject_id=a.subject_id");
		getHadmIdStmt.setInt(1, patient_id);
		ResultSet hadmId = getHadmIdStmt.executeQuery();
		
		PreparedStatement getDiagnosesStmt =  Demographics.conn.
				prepareStatement("select di.long_title from diagnoses_icd d, d_icd_diagnoses di, admissions a "
						+ "where a.hadm_id=? and a.hadm_id=d.hadm_id and d.icd9_code=di.icd9_code");
		
		PreparedStatement getVitalsStmt = Demographics.conn.
				prepareStatement("Select tb.label, tb.valuenum, tb.valueom "  
						+" from (select  ROW_NUMBER()OVER (PARTITION BY row.label order by row.valuenum " + order
						+") AS r, row.valueom, row.valuenum, row.label from"
						+" (select   distinct c.valueom, c.valuenum, d.label from admissions a, chartEvents c,"
						+" (select itemid, label from d_items  group by label, itemid)d where a.hadm_id=? and c.valuenum <> 0"
						+" and c.hadm_id = a.hadm_id and d.itemid = c.itemid  order by c.valuenum ) row )"
						+"tb where tb.r<=10 order by tb.label, tb.valuenum " + order);
		
		PreparedStatement getProceduresStmt =  Demographics.conn.
				prepareStatement("select di.long_title from procedures_icd d, d_icd_procedures di, admissions a "
						+ "where a.hadm_id=? and a.hadm_id=d.hadm_id and d.icd9_code=di.icd9_code");
		
		HashMap<Integer, ArrayList<String>> diag = new HashMap<Integer, ArrayList<String>>();
		HashMap<Integer, ArrayList<String>> proc = new HashMap<Integer, ArrayList<String>>();
		HashMap<Integer, ArrayList<ArrayList<String>>> vitalSign = new HashMap<Integer, ArrayList<ArrayList<String>>>();
		
		while(hadmId.next()) {
			int id = hadmId.getInt(1);
			getDiagnosesStmt.setInt(1, id);
			getVitalsStmt.setInt(1, id);
			getProceduresStmt.setInt(1, id);
			ResultSet diagnoses = getDiagnosesStmt.executeQuery();
			ArrayList<String> d = new ArrayList<String>();
			while(diagnoses.next()) {
				d.add(diagnoses.getString(1));	
			}
			diag.put(id, d);
			ResultSet procedures = getProceduresStmt.executeQuery();
			ArrayList<String> v = new ArrayList<String>();
			while(procedures.next()) {
				v.add(procedures.getString(1));	
			}
			proc.put(id, v);
			ResultSet vitals = getVitalsStmt.executeQuery();
			ArrayList<ArrayList<String>> vt = new ArrayList<ArrayList<String>>();
			while(vitals.next()) {
				ArrayList<String> vs = new ArrayList<String>();
				vs.add(vitals.getString(1));
				vs.add(vitals.getString(2));
				vs.add(vitals.getString(3));
				vt.add(vs);
			}
			vitalSign.put(id, vt);
		}
		PDFGenerator.generatePatientRecords(diag, proc, vitalSign);
	}
	
}
