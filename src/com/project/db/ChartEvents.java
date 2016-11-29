package com.project.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mimiciii.chartevents")
public class ChartEvents implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "row_id")
	private Integer row_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="subject_id")
	private Patients patient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hadm_id")
	private Admissions admissions;

	@Column(name = "itemId")
	private Integer itemId ;

	@Column(name = "value")
	private String value;

	@Column(name = "valuenum")
	private Double valuenum;

	@Column(name = "valueom")
	private String valueom;

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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

	public Integer getWarning() {
		return warning;
	}

	public void setWarning(Integer warning) {
		this.warning = warning;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getStopped() {
		return stopped;
	}

	public void setStopped(String stopped) {
		this.stopped = stopped;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "warning")
	private Integer warning ;

	@Column(name = "error")
	private Integer error ;

	@Column(name = "resultStatus")
	private String resultStatus;

	@Column(name = "stopped")
	private String stopped ;

	ChartEvents(){

	}
}
