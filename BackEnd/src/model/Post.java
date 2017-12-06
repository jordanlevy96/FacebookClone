package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post {
	private String content;
	private Date creationDate;
	
	public Post() {}
	
	public Post(String content) {
		this.content = content;
		this.creationDate = new Date();
	}
	
	public Post(String content, String date) {		
		this.content = content;
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		try {
			this.creationDate = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public JSONArray toJSON() {
		JSONArray arr = new JSONArray();
		JSONObject content = new JSONObject();
		JSONObject date = new JSONObject();
		try {
			content.put("content", this.content);
			if (this.creationDate == null) {
				date.put("date", new Date().toString());
			}
			else {
				date.put("date", this.creationDate.toString());
			}
			arr.put(content);
			arr.put(date);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public String toString() {
		return content + "\n" + creationDate.toString() + "\n";
	}

	public String getContent() {
		return this.content;
	}
	
	public Date getDate() {
		return this.creationDate;
	}
}
