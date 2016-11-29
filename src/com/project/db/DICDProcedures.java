package com.project.db;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mimiciii.d_icd_procedures")
public class DICDProcedures implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name = "row_id")  
	Integer row_id;


	@Column(name = "short_title")
	private String short_title;

	@Column(name = "long_title")
	private String long_title;


	@Id
	@Column(name = "icd9_code")
	private String icd9_code;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="dicdprocedure")
	private List<ProcedureICD> procedureicds;

	public Integer getRow_id() {
		return row_id;
	}

	public String getIcd9_code() {
		return icd9_code;
	}

	public void setIcd9_code(String icd9_code) {
		this.icd9_code = icd9_code;
	}

	public void setRow_id(Integer row_id) {
		this.row_id = row_id;
	}

	public String getShort_title() {
		return short_title;
	}

	public void setShort_title(String short_title) {
		this.short_title = short_title;
	}

	public String getLong_title() {
		return long_title;
	}

	public void setLong_title(String long_title) {
		this.long_title = long_title;
	}	 



	public List<ProcedureICD> getProcedureicds() {
		return procedureicds;
	}

	public void setProcedureicds(List<ProcedureICD> procedureicds) {
		this.procedureicds = procedureicds;
	}

	public DICDProcedures() {

	}
}

