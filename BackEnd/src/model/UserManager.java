package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
	private ArrayList<User> users;
	private static UserManager userManager;
	private static User lastUser;
	private static User loggedInUser;
	
	private UserManager() {
		this.users = new ArrayList<>();
	}
	
	public static UserManager getInstance() {
		if (userManager == null) {
			userManager = new UserManager();
		}
		
		try {
			userManager.readUserFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return userManager;
	}
	
	public List<User> getUserList() {
		return this.users;
	}
	
	public User getCurrentUser() {
		return loggedInUser;
	}
	
	public void readUserFiles() throws IOException {
		File f = new File("src/resources/users");
		if (f.isDirectory() && f.exists()) {
			if (f.list().length > 0) {
				File[] files = f.listFiles();
				for (File file : files) {
					if (file.getName().contains(".txt")) {
						BufferedReader br = new BufferedReader(new FileReader(file));
						String username = br.readLine();
						String password = br.readLine();
						String name = br.readLine();
						int id = Integer.parseInt(br.readLine());
						int numPosts = Integer.parseInt(br.readLine());
						User user = new User(username, password, name, id, numPosts);
						int numFriends = Integer.parseInt(br.readLine());
						for (int i = 0; i < numFriends; i++) {
							user.addFriend(br.readLine());
						}

						users.add(user);
						br.close();
					}
				}
			}
		}
	}
	
	public void createUser(User user) {
		users.add(user);
		writeUserToFile(user);
	}
	
	public void writeUserToFile(User user) {
		String id = Integer.toString(users.indexOf(user));
		String filename = "src/resources/users/user" + id + ".txt";
		String content = user.getUsername() + "\n" + user.getPassword() + "\n"
						 + user.getName() + "\n" + id + "\n" + user.getNumPosts()
						 + "\n" + Integer.toString(user.getFriends().size());
		File f = new File(filename);
		try {
			boolean created = f.createNewFile();
			if (created) {
				FileOutputStream fos = new FileOutputStream(f);
				byte[] bytesArray = content.getBytes();
				fos.write(bytesArray);
				fos.close();
			}
		} catch (IOException e) {
			// file not created
			e.printStackTrace();
		}	
	}
	
	public void startValidation(User user) {
		lastUser = user;
		System.out.println(lastUser.getUsername() + " set for validation");
	}
	
	public boolean validateUser() {
		User request = lastUser;
		System.out.println("validating " + request.getUsername());
		
		for (User user : users) {
			if (user.compareTo(request) == 0) {
				loggedInUser = user;
				System.out.println("logged in set to " + loggedInUser.getName());
				return true;
			}
		}
		
		return false;
	}
}
