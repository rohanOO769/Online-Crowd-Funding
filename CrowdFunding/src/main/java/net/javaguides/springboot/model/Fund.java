package net.javaguides.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funds")
public class Fund {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Project Name")
	private String name;
	
	@Column(name = "Product Code")
	private String code;
	
	// @Column(name = "email")
	// private String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

