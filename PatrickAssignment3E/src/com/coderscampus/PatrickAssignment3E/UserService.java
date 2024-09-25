package com.coderscampus.PatrickAssignment3E;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class UserService {
	private User[] users;

	public UserService(String fileName) {
		loadUser(fileName);
	}

	private void loadUser(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			int linecount = 0;
			while (br.readLine() != null) {
				linecount++;
			}

			br.close();
			users = new User[linecount];

			BufferedReader br2 = new BufferedReader(new FileReader(fileName));
			String line;
			int index = 0;
			while ((line = br2.readLine()) != null) {
				String[] userData = line.split(",");

				if (userData.length == 3) {
					String username = userData[0];
					String password = userData[1];
					String name = userData[2];
					users[index] = new User(username, password, name);
					index++;
				} else {
					System.out.println("Invalid line Skipped: " + line);
				}

			}

			br2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public User validateLogin(String username, String password) {
		for (User user : users) {
			if (user != null) {
				if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
					return user;

				}
			} else {
				System.out.println("User is Null at an index in users array");
			}
		}
		return null;
	}

}
