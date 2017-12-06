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

    for (var i in wall) {
        arr.push(<li>{wall[i].content}</li>);
    }

    return arr;
}