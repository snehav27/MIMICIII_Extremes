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
@Table(name = "mimiciii.diagnoses_icd")
public class DiagnosesICD implements Serializable {
	private static final long serialVersionUID = 1L;  

	@Id
	@Column(name = "row_id")
	private Integer row_id;

	@Column(name = "subject_id")
	private Integer subject_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="hadm_id")
	private Admissions admission;

	@Column(name = "proc_seq_num")
	private Integer proc_seq_num;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="icd9_code")	
	private DICDDiagnoses dicddiagnoses;

	public DICDDiagnoses getDicddiagnoses() {
		return dicddiagnoses;
	}

	public void setDicddiagnoses(DICDDiagnoses dicddiagnoses) {
		this.dicddiagnoses = dicddiagnoses;
	}

	public Integer getRow_id() {
		return row_id;
	}

	public void setRow_id(Integer row_id) {
		this.row_id = row_id;
	}

	public Admissions getAdmission() {
		return admission;
	}

	public void setAdmission(Admissions admission) {
		this.admission = admission;
	}

	public Integer getProc_seq_num() {
		return proc_seq_num;
	}

	public void setProc_seq_num(Integer proc_seq_num) {
		this.proc_seq_num = proc_seq_num;
	}


	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}

	public DiagnosesICD() {

	}	
}

