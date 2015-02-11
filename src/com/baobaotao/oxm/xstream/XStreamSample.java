package com.baobaotao.oxm.xstream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamSample {

	private static XStream xstream;

	static {
		// new DomDriver()，这种方式不需要加xpp3包
		xstream = new XStream(new DomDriver());
	}

	/**
	 * 初始化转换对象
	 * 
	 * @return
	 */
	public static User getUser() {
		LoginLog log1 = new LoginLog();
		LoginLog log2 = new LoginLog();
		log1.setIp("192.168.1.91");
		log1.setLoginDate(new Date());
		log2.setIp("192.168.1.92");
		log2.setLoginDate(new Date());
		User user = new User();
		user.setUserId(1);
		user.setUserName("xstream");
		user.addLoginLog(log1);
		user.addLoginLog(log2);
		return user;
	}

	/**
	 * Java对象转化为XML
	 * 
	 * @throws Exception
	 */
	public static void objectToXml() throws Exception {
		User user = getUser();
		FileOutputStream outputStream = new FileOutputStream(
				"out/XStreamSample.xml");
		xstream.toXML(user, outputStream);
	}

	public static User xmlToObject() throws Exception {
		FileInputStream inputStream = new FileInputStream(
				"out/XStreamSample.xml");
		User u = (User) xstream.fromXML(inputStream);
		for (LoginLog log : u.getLogs()) {
			if (log != null) {
				System.out.println("访问IP: " + log.getIp());
				System.out.println("访问时间: " + log.getLoginDate());
			}
		}
		return u;
	}

	public static void main(String[] args) throws Exception {
		objectToXml();
		xmlToObject();
	}

}
