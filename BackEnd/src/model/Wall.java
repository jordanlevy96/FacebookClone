package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Wall {
	private User owner;
	private ArrayList<Post> posts;
	private int numPosts;
	
	public Wall(User owner) {
		this.owner = owner;
		this.posts = new ArrayList<>();
		this.numPosts = owner.getNumPosts();
		readWallFromFile();
	}
	
	public List<Post> getPosts() {
		return this.posts;
	}
	
	//rewrite with old posts, then add new on end
	public void addPost(Post post) {		
		String filename = "src/resources/walls/user" + Integer.toString(owner.getID()) + "wall.txt";
		File file = new File(filename);
		BufferedWriter bw = null;
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			String oldContent = "";
			
			String line = br.readLine();
			while (line != null) {
				oldContent += line + "\n";
				line = br.readLine();
			}
						
			br.close();
			
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(oldContent);
			
			bw.write(post.getContent() + "\n");
			String date;
			if (post.getDate() == null) {
				date = new Date().toString();
			}
			else {
				date = post.getDate().toString();
			}
			bw.write(date + "\n");

			owner.incrementPosts();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		//update parent
		owner.setWall(this);
		
	}

	public JSONArray getJSON() {
		JSONArray array = new JSONArray();
		for (Post post : posts) {
			array.put(post.toJSON());
		}
		return array;
	}
	
	public void readWallFromFile() {
		String filename = "src/resources/walls/user" + Integer.toString(owner.getID()) + "wall.txt";
		File file = new File(filename);
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			int counter = 0;
			while (counter < numPosts) {
				posts.add(new Post(br.readLine(), br.readLine()));
				counter++;
			}
			
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//update parent
		owner.setWall(this);
	}
}
