package com.hungkuei.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hugkuei.crud.model.Institute;
import com.hugkuei.crud.model.Message;
import com.hungkuei.crud.service.InstituteService;

@Controller
public class InstituteController {
	
	@Autowired
	private InstituteService instituteService;
	
	@RequestMapping(value="ins")
	@ResponseBody
	public Message getIns() {
		List<Institute> list = instituteService.getInsList();
		return Message.succeed().add("Ins", list);
	}

}
