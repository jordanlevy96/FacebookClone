package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Post;
import model.User;
import model.UserManager;
import model.Wall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProfileController {
    
    @RequestMapping(value = "/getBasicData")
	public String getBasicData() {
    		JSONArray array = new JSONArray();
    		JSONObject data = new JSONObject();
    		UserManager um = UserManager.getInstance();
    		User current = um.getCurrentUser();
    		System.out.println("Current user: " + current.getName());
    		try {
				array.put(new JSONObject("{name : " + current.getName() + "}"));
				array.put(new JSONObject("{id : " + Integer.toString(current.getID()) + "}"));
				array.put(new JSONObject("{friends : " + current.getFriendsAsString() + "}"));
			}
    		catch (JSONException e) {
				e.printStackTrace();
			}
    		
    		System.out.println(array.toString());
    		return array.toString();
	}
    
    @RequestMapping(value = "/getWall")
    public String getWall() {
		UserManager um = UserManager.getInstance();
		User current = um.getCurrentUser();
    		return current.getWall().getJSON().toString();
    }
    
    @RequestMapping(value = "/getFeed")
    public String getFeed() {
    		return "";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="addPost")
    public void addPost(@RequestBody Post post) {
    		UserManager um = UserManager.getInstance();
    		User current = um.getCurrentUser();
    		Wall wall = current.getWall();
    		wall.addPost(post);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/notlogin")
    public void login(@RequestBody User user) { 
        return;
    }
}