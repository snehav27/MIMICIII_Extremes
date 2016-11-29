package com.project.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mimiciii.dlabitems")
public class DLabItems implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "row_id")
	private Integer row_id;
	
	@Column(name = "itemId")
	private Integer itemId ;
	
	@Column(name = "label")
	private String label ;
	
	@Column(name = "fluid")
	private String fluid;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "lionc_code")
	private String lionc_code;

	public Integer getRow_id() {
		return row_id;
	}

	public void setRow_id(Integer row_id) {
		this.row_id = row_id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFluid() {
		return fluid;
	}

	public void setFluid(String fluid) {
		this.fluid = fluid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLionc_code() {
		return lionc_code;
	}

	public void setLionc_code(String lionc_code) {
		this.lionc_code = lionc_code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	DLabItems() {
		
	}
}
