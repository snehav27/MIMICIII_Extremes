package com.project.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "mimiciii.ditems")
public class DItems implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "row_id")
	private Integer row_id;
	
	@Column(name = "itemId")
	private Integer itemId ;
	
	@Column(name = "label")
	private String label ;
	
	@Column(name = "abbreviation")
	private String abbreviation ;
	
	@Column(name = "dbsource")
	private String dbsource;
	
	@Column(name = "linksto")
	private String linksto;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "unitname")
	private String unitname;
	
	@Column(name = "param_type")
	private String param_type;
	
	@Column(name = "canceptid")
	private String canceptid;
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDbsource() {
		return dbsource;
	}

	public void setDbsource(String dbsource) {
		this.dbsource = dbsource;
	}

	public String getLinksto() {
		return linksto;
	}

	public void setLinksto(String linksto) {
		this.linksto = linksto;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getParam_type() {
		return param_type;
	}

	public void setParam_type(String param_type) {
		this.param_type = param_type;
	}

	public String getCanceptid() {
		return canceptid;
	}

	public void setCanceptid(String canceptid) {
		this.canceptid = canceptid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	DItems() {
		
	}
}
