package com.sei.web.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.sei.web.servlet.User;

public class ReaderDBFile {

	public User ReaderFileUse(String username, String password,String count) {

		FileReader reader;
		String[] linesarr;
		User user = new User();

		try {
			reader = new FileReader("/data/weiang_data/mousetrack/huawei/userID/dbfile.txt");
			BufferedReader bf = new BufferedReader(reader);// 涓�琛屼竴琛岃顕�

			try {
				
				String t1 = bf.readLine();
				//System.out.println(t1+"haha");
				while (t1 != null&&t1.length()!=0) {
					// System.out.println(t1);
					linesarr = t1.split("\\s+");
					//System.out.println(linesarr+"\n"+linesarr[linesarr.length - 3]);
					if (linesarr.length > 0) {
					
						if (linesarr[linesarr.length - 3].equals(username)) {
							// user.setId(1);
							if(linesarr[linesarr.length - 2]
										.equals(password)) {
							
									user.setCount(linesarr[linesarr.length - 1]);
									 
									linesarr[linesarr.length - 1]=count;
									System.out.println("usename="+username);
									System.out.println("userpassword="+password);
								
								user.setPassword(password);
							}
							else {
								user.setPassword(null);
							}
							user.setUsername(username);
							
							break;
						}
					}

					t1 = bf.readLine();
				}
				bf.close();
	

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

}
