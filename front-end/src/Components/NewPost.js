import React from 'react'

function NewPost(props) {
    return (
        <div id="new-post">
            <input id="postText" type="textarea" placeholder="What's on your mind?"/>
            <button id="post" onClick={sendPost}>Post</button>
        </div>
    );
}

function sendPost() {
    var postText = document.getElementById("postText").value;

    fetch('http://localhost:3002/addPost', {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            content: postText
          })
        }).then(function(response) {
            console.log(response);
        });
}

export default NewPost