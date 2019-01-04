package com.hugkuei.crud.model;

import java.util.HashMap;
import java.util.Map;

public class Message {
	//״̬��      100-�ɹ�      200-ʧ��
	private int code;
	
	//��ʾ��Ϣ
	private String msg;
	
	//�û����������������
	private Map<String, Object> extend = new HashMap<String, Object>();
	
	public static Message succeed() {
		Message result = new Message();
		result.setCode(100);
		result.setMsg("����ɹ���");
		return result;
	}
	
	public static Message failed() {
		Message result = new Message();
		result.setCode(200);
		result.setMsg("����ʧ�ܣ�");
		return result;
	}
	
	public Message add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

}
