package com.hungkuei.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugkuei.crud.model.Institute;
import com.hungkuei.crud.dao.InstituteMapper;

@Service
public class InstituteService {
	
	@Autowired
	private InstituteMapper instituteMapper;
	
	public List<Institute> getInsList(){
		List<Institute> list = instituteMapper.selectByExample(null);
		return list;
	}

}
