import React from 'react';
import NewPost from '../Components/NewPost.js';
import ProfileBox from '../Components/ProfileBox.js';
import FriendsList from '../Components/FriendsList.js';
import Wall from '../Components/Wall.js';
import Feed from '../Components/Feed.js';

var classVar = null;
export default class ProfileComp extends React.Component {
    constructor() {
        getBasicData()
        getWall();
        getFeed();
        super();
        this.state = {};
        classVar = this;
    }

    render() {
        return (
            <div>
                <ProfileBox img={ "../resources/pics/user"
                                   + this.state.id + "pic.png" }
                            name={this.state.name} />
                <NewPost callback={newPostCallback}/>
                <FriendsList friends={this.state.friends} />
                <Wall wall={this.state.wall} />
                <Feed feed={this.state.feed} />
            </div>
        );
    }
}

function newPostCallback() {
    console.log("posted new post");
    getWall();
}

function getBasicData() {
    fetch('http://localhost:3002/getBasicData')
        .then((resp) => resp.json()) // Transform the data into JSON
        .then(function(data) {
            console.log(data);
            classVar.setState({
                name: data[0].name,
                id: data[1].id,
                friends: data[2].friends
            });
            console.log("state name set to " + data[0].name);
            console.log("friends = " + data[2].friends);
        });
}

function getWall() {
    fetch('http://localhost:3002/getWall')
        .then((resp) => resp.json()) // Transform the data into JSON
        .then(function(data) {
            console.log(data);
            classVar.setState({
                wall: data
            })
        });
}

function getFeed() {
    fetch('http://localhost:3002/getFeed')
        .then((resp) => resp.json()) // Transform the data into JSON
        .then(function(data) {
            console.log("feed response log");
            console.log(data);
            classVar.setState({
                feed: data
            })
        });
}

//<img src="props.picture" alt={props.altText}/>