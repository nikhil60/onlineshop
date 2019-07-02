package com.retail.onlineshop.controller;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.retail.onlineshop.dto.GlobalParamDTO;
import com.retail.onlineshop.model.Customer;
import com.retail.onlineshop.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@PostMapping(path="/login/{userName}/{passWord}")
	public ResponseEntity<GlobalParamDTO> validateLogin(@PathVariable String userName,@PathVariable String passWord)
	{
		Customer customer = loginService.validateLogin(userName, passWord);
		GlobalParamDTO globalParamDTO = GlobalParamDTO.convertToDTO(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(globalParamDTO.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(globalParamDTO);
	}
}
