package com.ibm.countrycodes.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="country")
@Entity
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="iso3code")
	private String iso3code;
	@Column(name="name")
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIso3code() {
		return iso3code;
	}
	public void setIso3code(String iso3code) {
		this.iso3code = iso3code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Country [id=" + id + ", iso3code=" + iso3code + ", name=" + name + "]";
	}

	
}
