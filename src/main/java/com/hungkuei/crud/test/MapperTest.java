package com.hungkuei.crud.test;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hugkuei.crud.model.Student;
import com.hungkuei.crud.dao.InstituteMapper;
import com.hungkuei.crud.dao.StudentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private InstituteMapper instituteMapper;

	@Autowired
	private SqlSession sqlSession;

	@Test
	public void testMapper() {
		System.out.println(studentMapper);

		// ��������
		// instituteMapper.insertSelective(new Institute(1, "�������ѧ�빤��ѧԺ"));
		// instituteMapper.insertSelective(new Institute(2, "������Ϣ����ѧԺ"));

		studentMapper.insertSelective(new Student(null, "�����", "��", "hungkuei@163.com", 1));

	}

	@Test
	public void delectTest() {
		// ɾ������
		// instituteMapper.deleteByPrimaryKey(2);
		studentMapper.deleteByPrimaryKey(1);
	}

	// ��������ѧ��
	@Test
	public void sqlSesionTest() {

		StudentMapper student = sqlSession.getMapper(StudentMapper.class);

		for (int i = 0; i < 1000; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5) + i;
			studentMapper.insertSelective(new Student(null, uid, "��", uid + "@163.com", 1));
		}
		System.out.println("������ɣ�");
	}

	// ��ҳ��ѯ����ѧ����Ϣ
	@Test
	public void getStudent() {
		PageHelper.startPage(5, 5);
		List<Student> list = studentMapper.selectByExampleWithIns(null);
		PageInfo page = new PageInfo(list, 5);
		System.out.println("��ǰҳ�룺" + page.getPageNum());
		System.out.println("��ҳ�룺" + page.getPages());
		System.out.println("�ܼ�¼����" + page.getTotal());
		System.out.print("ҳ����������ʾ��ҳ�룺");
		int[] num = page.getNavigatepageNums();
		for (int i : num) {
			System.out.print(" " + i);
		}
		System.out.println();
		// ��ȡѧ������
		List<Student> stus = page.getList();
		for (Student student : stus) {
			System.out.println("ѧ��ID��" + student.getStuId() + "��ѧ��������" + student.getStuName() + ",�Ա�" + student.getGender());
		}
	}

}
