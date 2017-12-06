package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable {
	private String username;
	private String password;
	private Wall wall;
	private int numPosts;
	private int id;
	private ArrayList<String> friends;
	private String name;
	
	public User() {}
	
	public User(String username, String password, String name, int id, int numPosts) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.id = id;
		this.numPosts = numPosts;
		this.wall = new Wall(this);
		this.friends = new ArrayList<>();
	}
	
	public int getNumPosts() {
		return this.numPosts;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password; //lol
	}
	
	public String getName() {
		return this.name;
	}
	
	public Wall getWall() {
		return this.wall;
	}
	
	public void addFriend(String name) {
		this.friends.add(name);
	}
	
	public List<String> getFriends() {
		return this.friends;
	}
	
	public String getFriendsAsString() {
		if (friends == null) {
			return "null";
		}
		return this.friends.toString();
	}
	
	public void makePost(Post post) {
		this.wall.addPost(post);
	}
	
	public void makePost(String content) {
		makePost(new Post(content));
	}
	
	public int getID() {
		return this.id;
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass() != User.class) {
			return -1;
		}
		
		User other = (User) o;
		if (this.username.equals(other.getUsername()) && this.password.equals(other.getPassword())) {
			return 0;
		}
		
		return 1;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	//rewrite entire user file lol
	public void incrementPosts() {
		numPosts++;
		String filename = "src/resources/users/user" + Integer.toString(id) + ".txt";
		File file = new File(filename);
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			String oldContent = "";
			String line = br.readLine();
			int lineCounter = 1;
			while (line != null) {
				if (lineCounter == 5) { //that's where the post count is
					oldContent += Integer.toString(numPosts) + "\n";
				}
				else {
					oldContent += line + "\n";
				}
				lineCounter++;
				line = br.readLine();
			}
			br.close();
			
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(oldContent);
			bw.close();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
