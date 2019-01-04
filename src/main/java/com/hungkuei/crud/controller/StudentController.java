package com.hungkuei.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hugkuei.crud.model.Message;
import com.hugkuei.crud.model.Student;
import com.hungkuei.crud.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;

	/**
	 * 学生分页查询
	 * 
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("/list")
	public String getStudent(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber, Model model) {
		PageHelper.startPage(pageNumber, 5);
		List<Student> list = studentService.getAll();
		PageInfo page = new PageInfo(list, 5);
		model.addAttribute("pageInfo", page);
		return "list";
	}
	
	/**
	 * 查询学生数据
	 * @param pageNumber
	 * @return
	 */
	
	@RequestMapping("/students")
	@ResponseBody
	public Message getStudentWithJson(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber) {
		PageHelper.startPage(pageNumber, 5);
		List<Student> list = studentService.getAll();
		PageInfo page = new PageInfo(list, 5);
		return Message.succeed().add("pageInfo", page);
	}
	
	/**
	 * 新增学生
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/students", method = RequestMethod.POST)
	@ResponseBody
	public Message submitStu(@Valid Student student, BindingResult result) {
		if(result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
				System.out.println("错误字段名：" + fieldError.getField());
				System.out.println("错误信息：" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
			return Message.failed().add("fieldErrors", map);
		}else {
			studentService.saveStu(student);
			return Message.succeed();
		}
	}
	
	/**
	 * 校验用户名是否可用
	 * @param stuName
	 * @return
	 */
	@RequestMapping(value="checkuser")
	@ResponseBody
	public Message checkuser(@RequestParam(value="stuName") String stuName) {
		//判断用户名是否是合法的表达式
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!stuName.matches(regex)) {
			return Message.failed().add("va_msg", "用户名必须是6-16位的数字和英文组合或者2-5位中文！");
		}
		boolean isCheck = studentService.checkUser(stuName);
		if(isCheck) {
			return Message.succeed();
		}else {
			return Message.failed().add("va_msg", "用户名已存在，请重新输入！");
		}
	}
	/**
	 * 根据学生Id查询学生信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/stu/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Message getStu(@PathVariable("id")Integer id) {
		Student student = studentService.getStus(id);
		return Message.succeed().add("stu", student);
		
	}
}
