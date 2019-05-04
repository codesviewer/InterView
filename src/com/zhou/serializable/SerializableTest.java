package com.zhou.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SerializableTest {

	/*
	 * 将User对象序列化到本地磁盘
	 */
	private static void writeUser(User user) throws FileNotFoundException,IOException {
		// 创建对象输出流
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				new File("c://java/user.txt")));
		// 输出对象到本地
		oos.writeObject(user);
		// 关闭资源
		oos.close();

	}
	
	/*
	 * 对象的反序列化
	 */
	private static User readUser() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("c://java/user.txt")));

		User readObject = (User) ois.readObject();

		return readObject;
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		User user = new User();
		user.setAge(23);
		user.setName("zhangsan");

		// writeUser(user);

		User user2 = readUser();

		System.out.println(user2.toString());

	}
}
