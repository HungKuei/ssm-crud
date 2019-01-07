package com.hungkuei.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugkuei.crud.model.Student;
import com.hugkuei.crud.model.StudentExample;
import com.hugkuei.crud.model.StudentExample.Criteria;
import com.hungkuei.crud.dao.StudentMapper;

/**
 * @描述 Service服务层
 * @标题 StudentController.java
 * @Package com.hungkuei.crud.service
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午14:32:11
 * @Copyright: 2018 by hungkuei All rights reserved.
 */
@Service
public class StudentService {
	
	@Autowired
	StudentMapper studentMapper;

	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentMapper.selectByExampleWithIns(null);
	}
	
	public void saveStu(Student student) {
		studentMapper.insertSelective(student);
	}
	
	/**
	 * 校验当前用户名是否可用
	 * @param stuName
	 * @return
	 */
	public boolean checkUser(String stuName) {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStuNameEqualTo(stuName);
		long count = studentMapper.countByExample(example);
		return count == 0;
	}
	
	/**
	 * 按照学生Id查询学生
	 * @param id
	 * @return
	 */
	public Student getStus(Integer id) {
		Student student = studentMapper.selectByPrimaryKey(id);
		return student;
	}
	
	/**
	 * 根据学生id有选择的更新学生信息
	 * @param student
	 */
	public void updateStu(Student student) {
		studentMapper.updateByPrimaryKeySelective(student);
	}

	/**
	 * 删除学生
	 * @param id
	 */
	public void deleteStu(Integer id) {
		// TODO Auto-generated method stub
		studentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 批量删除学生
	 * @param del_ids
	 */
	public void deleteStu(List<Integer> del_ids) {
		// TODO Auto-generated method stub
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andStuIdIn(del_ids);
		studentMapper.deleteByExample(example);
		
	}
	

}
