package com.hungkuei.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugkuei.crud.model.Student;
import com.hugkuei.crud.model.StudentExample;
import com.hugkuei.crud.model.StudentExample.Criteria;
import com.hungkuei.crud.dao.StudentMapper;

/**
 * @���� Service�����
 * @���� StudentController.java
 * @Package com.hungkuei.crud.service
 * @�汾 v1.0
 * @���� HungKuei
 * @���� 2018��11��19�� ����14:32:11
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
	
	/**
	 * ����ѧ��id��ѡ��ĸ���ѧ����Ϣ
	 * @param student
	 */
	public void updateStu(Student student) {
		studentMapper.updateByPrimaryKeySelective(student);
	}

	/**
	 * ɾ��ѧ��
	 * @param id
	 */
	public void deleteStu(Integer id) {
		// TODO Auto-generated method stub
		studentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * ����ɾ��ѧ��
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
