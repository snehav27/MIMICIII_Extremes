package com.project.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mimiciii.patients")
public class Patients implements Serializable {
	private static final long serialVersionUID = 1L;	

	@Column(name = "row_id")
	private Integer row_id;

	@Id
	@Column(name = "subject_id")
	private Integer subject_id;

	@Column(name = "gender")
	private char gender;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dob")
	private Date dob;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dod",nullable = true)
	private Date dod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dod_hosp",nullable = true)
	private Date dod_hosp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dod_ssn",nullable = true)
	private Date dod_ssn;


	@Column(name = "hospital_expire_flag")
	private char hospital_expire_flag;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="patient")
	private List<Admissions> admissions;

	public Integer getRow_id() {
		return row_id;
	}

	public void setRow_id(Integer row_id) {
		this.row_id = row_id;
	}

	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDod() {
		return dod;
	}

	public void setDod(Date dod) {
		this.dod = dod;
	}

	public Date getDod_hosp() {
		return dod_hosp;
	}

	public void setDod_hosp(Date dod_hosp) {
		this.dod_hosp = dod_hosp;
	}

	public Date getDod_ssn() {
		return dod_ssn;
	}

	public void setDod_ssn(Date dod_ssn) {
		this.dod_ssn = dod_ssn;
	}

	public char getHospital_expire_flag() {
		return hospital_expire_flag;
	}

	public void setHospital_expire_flag(char hospital_expire_flag) {
		this.hospital_expire_flag = hospital_expire_flag;
	}

	public List<Admissions> getAdmissions() {
		return admissions;
	}

	public void setAdmissions(List<Admissions> admissions) {
		this.admissions = admissions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	Patients(){

	}

}