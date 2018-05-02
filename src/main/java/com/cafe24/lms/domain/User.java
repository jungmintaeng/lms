package com.cafe24.lms.domain;

import com.cafe24.security.Auth;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotEmpty
	@Length(min = 1, max = 50)
	private String name;

	@Column(nullable = false, unique = true)
	@Email
	@Length(min = 8, max = 100)
	@NotEmpty
	private String email;

	@Column(nullable = false)
	private String salt;

	@Column(nullable = false)
	@NotEmpty
	@Length(min = 8, max = 14)
	private String password;

	private Character gender;

	@Enumerated(EnumType.STRING)
	private Auth.Role role;

	@Transient
	@NotEmpty
	private String agreeProv;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Auth.Role getRole() {
		return role;
	}

	public void setRole(Auth.Role role) {
		this.role = role;
	}

	public String getAgreeProv() {
		return agreeProv;
	}

	public void setAgreeProv(String agreeProv) {
		this.agreeProv = agreeProv;
	}

	@Override
	public String toString() {
		return "User{" +
				"no=" + no +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", salt='" + salt + '\'' +
				", password='" + password + '\'' +
				", gender=" + gender +
				", role=" + role +
				'}';
	}
}
