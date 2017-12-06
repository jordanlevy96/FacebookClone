import React from 'react';

export default class Wall extends React.Component {
    render() {
        return(
            <div id="wall">
                <h3>Your Posts</h3>
                <ul>
                    {getPosts(this.props.wall)}
                </ul>
            </div>
        );
    }
}

function getPosts(wall) {
    var arr = [];

    console.log("Wall!");
    console.log(wall)

    for (var i in wall) {
        console.log(wall[i]);
        arr.push(
            <li>
                <p>{wall[i][0].content}</p>
                <p>{wall[i][1].date}</p>
            </li>
        );
    }

    return arr;
}