package com.project.boiler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoilerController {
	
	@Autowired
	BoilerMapper mapper;
	
	@GetMapping("/boilerstat")
	List<Member> getBoilerstat() {
		return mapper.selectBoilerstat();
	}

	@GetMapping("/updateon_off/{on_off}")
	void getUpdateon_off(@PathVariable("on_off") String on_off) {
		String id = "boiler";
		mapper.updateon_off(on_off, id);
	}
}
