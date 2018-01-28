package com.shinn.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;
import com.shinn.web.view.Views;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class User implements Serializable {

    Integer id;
	@JsonView(Views.Public.class)
	String username;
	String password;
	@JsonView(Views.Public.class)
	String email;
	@JsonView(Views.Public.class)
	String phone;
	String address;
	String status;

	public User() {
	}

	public User(String username, String password, String email, String phone, String address) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
	public String toString() {
		return "User [id = "+ id + " username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + "]";
	}

}
