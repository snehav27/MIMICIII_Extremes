package com.project.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mimiciii.labevents")
public class LabEvents implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "row_id")
	private Integer row_id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "chartTime")
	private Date chartTime;

	@Column(name = "value")
	private String value;

	@Column(name = "valuenum")
	private Double valuenum;

	@Column(name = "valueom")
	private String valueom;

	@Column(name = "flag")
	private String flag ;	

	@Column(name = "itemId")
	private Integer itemId ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="subject_id")
	private Patients patient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hadm_id")
	private Admissions admissions;

	public Integer getRow_id() {
		return row_id;
	}

	public void setRow_id(Integer row_id) {
		this.row_id = row_id;
	}

	public Patients getPatient() {
		return patient;
	}

	public void setPatient(Patients patient) {
		this.patient = patient;
	}

	public Admissions getAdmissions() {
		return admissions;
	}

	public void setAdmissions(Admissions admissions) {
		this.admissions = admissions;
	}

	public Date getChartTime() {
		return chartTime;
	}

	public void setChartTime(Date chartTime) {
		this.chartTime = chartTime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Double getValuenum() {
		return valuenum;
	}

	public void setValuenum(Double valuenum) {
		this.valuenum = valuenum;
	}

	public String getValueom() {
		return valueom;
	}

	public void setValueom(String valueom) {
		this.valueom = valueom;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	LabEvents() {

	}

}
