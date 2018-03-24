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

	

}
