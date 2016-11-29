package com.project.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mimiciii.admissions")
public class Admissions implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "row_id")
	private Integer row_id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="subject_id")
	private Patients patient;

	@Id
	@Column(name = "hadm_id")
	private Integer hadm_id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "admittime")
	private Date admittime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dischtime")
	private Date dischtime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deathtime", nullable=true)
	private Date deathtime;

	@Column(name = "admission_type")
	private String admission_type;

	@Column(name = "admission_location")
	private String admission_location;

	@Column(name = "discharge_location")
	private String discharge_location;

	@Column(name = "insurance")
	private String insurance;

	@Column(name = "language",nullable =true)
	private String language;

	@Column(name = "religion",nullable =true)
	private String religion;

	@Column(name = "marital_status",nullable =true)
	private String marital_status;

	@Column(name = "ethnicity")
	private String ethnicity;

	@Column(name = "diagnosis",nullable =true)
	private String diagnosis;
	@OneToMany(fetch = FetchType.LAZY,mappedBy="admission")
	private List<ProcedureICD> procedure_icds;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="admission")
	private List<DiagnosesICD> diagnoses_icds;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="admission")
	private List<ChartEvents> chartEvents;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="admission")
	private List<LabEvents> labEvents;

	public List<ChartEvents> getChartEvents() {
		return chartEvents;
	}

	public void setChartEvents(List<ChartEvents> chartEvents) {
		this.chartEvents = chartEvents;
	}

	public List<LabEvents> getLabEvents() {
		return labEvents;
	}

	public void setLabEvents(List<LabEvents> labEvents) {
		this.labEvents = labEvents;
	}

	public List<DiagnosesICD> getDiagnoses_icds() {
		return diagnoses_icds;
	}

	public void setDiagnoses_icds(List<DiagnosesICD> diagnoses_icds) {
		this.diagnoses_icds = diagnoses_icds;
	}

	public List<ProcedureICD> getProcedure_icds() {
		return procedure_icds;
	}

	public void setProcedure_icds(List<ProcedureICD> procedure_icds) {
		this.procedure_icds = procedure_icds;
	}

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


	public Integer getHadm_id() {
		return hadm_id;
	}


	public void setHadm_id(Integer hadm_id) {
		this.hadm_id = hadm_id;
	}


	public Date getAdmittime() {
		return admittime;
	}


	public void setAdmittime(Date admittime) {
		this.admittime = admittime;
	}


	public Date getDischtime() {
		return dischtime;
	}


	public void setDischtime(Date dischtime) {
		this.dischtime = dischtime;
	}


	public Date getDeathtime() {
		return deathtime;
	}


	public void setDeathtime(Date deathtime) {
		this.deathtime = deathtime;
	}


	public String getAdmission_type() {
		return admission_type;
	}


	public void setAdmission_type(String admission_type) {
		this.admission_type = admission_type;
	}


	public String getAdmission_location() {
		return admission_location;
	}


	public void setAdmission_location(String admission_location) {
		this.admission_location = admission_location;
	}


	public String getDischarge_location() {
		return discharge_location;
	}


	public void setDischarge_location(String discharge_location) {
		this.discharge_location = discharge_location;
	}


	public String getInsurance() {
		return insurance;
	}


	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getReligion() {
		return religion;
	}


	public void setReligion(String religion) {
		this.religion = religion;
	}


	public String getMarital_status() {
		return marital_status;
	}


	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}


	public String getEthnicity() {
		return ethnicity;
	}


	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}


	public String getDiagnosis() {
		return diagnosis;
	}


	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	Admissions(){

	}
}

