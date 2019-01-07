package com.hugkuei.crud.model;

import javax.validation.constraints.Pattern;

public class Student {
    private Integer stuId;
    
    @Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})", message="用户名一定是2-5位中文或者6-16位的数字和英文组合！")
    private String stuName;

    private String gender;

    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message="您输入的邮箱格式不正确！")
    private String email;

    private Integer insId;
    
    public Student() {
		super();
	}

	public Student(Integer stuId, String stuName, String gender, String email, Integer insId) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.gender = gender;
		this.email = email;
		this.insId = insId;
	}

	//学生所在学院
    private Institute institute;

	public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getInsId() {
        return insId;
    }

    public void setInsId(Integer insId) {
        this.insId = insId;
    }
    
    public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", gender=" + gender + ", email=" + email
				+ ", insId=" + insId + ", institute=" + institute + "]";
	}
	
}