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

		// 插入数据
		// instituteMapper.insertSelective(new Institute(1, "计算机科学与工程学院"));
		// instituteMapper.insertSelective(new Institute(2, "电器信息工程学院"));

		studentMapper.insertSelective(new Student(null, "郭洪奎", "男", "hungkuei@163.com", 1));

	}

	@Test
	public void delectTest() {
		// 删除数据
		// instituteMapper.deleteByPrimaryKey(2);
		studentMapper.deleteByPrimaryKey(1);
	}

	// 批量生成学生
	@Test
	public void sqlSesionTest() {

		StudentMapper student = sqlSession.getMapper(StudentMapper.class);

		for (int i = 0; i < 1000; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5) + i;
			studentMapper.insertSelective(new Student(null, uid, "男", uid + "@163.com", 1));
		}
		System.out.println("批量完成！");
	}

	// 分页查询所有学生信息
	@Test
	public void getStudent() {
		PageHelper.startPage(5, 5);
		List<Student> list = studentMapper.selectByExampleWithIns(null);
		PageInfo page = new PageInfo(list, 5);
		System.out.println("当前页码：" + page.getPageNum());
		System.out.println("总页码：" + page.getPages());
		System.out.println("总记录数：" + page.getTotal());
		System.out.print("页面中连续显示的页码：");
		int[] num = page.getNavigatepageNums();
		for (int i : num) {
			System.out.print(" " + i);
		}
		System.out.println();
		// 获取学生数据
		List<Student> stus = page.getList();
		for (Student student : stus) {
			System.out.println("学生ID：" + student.getStuId() + "，学生姓名：" + student.getStuName() + ",性别：" + student.getGender());
		}
	}

}
