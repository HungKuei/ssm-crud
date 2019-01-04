package com.hungkuei.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugkuei.crud.model.Student;
import com.hugkuei.crud.model.StudentExample;
import com.hugkuei.crud.model.StudentExample.Criteria;
import com.hungkuei.crud.dao.StudentMapper;

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
	 * У�鵱ǰ�û����Ƿ����
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
	 * ����ѧ��Id��ѯѧ��
	 * @param id
	 * @return
	 */
	public Student getStus(Integer id) {
		Student student = studentMapper.selectByPrimaryKey(id);
		return student;
	}
	

}
