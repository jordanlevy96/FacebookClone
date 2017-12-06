import React from 'react';

export default class Feed extends React.Component {
    render() {
        return (
            <div id="feed">
                <h3>News Feed</h3>
                <ul>
                    {getPosts(this.props.feed)}
                </ul>
            </div>
        );
    }
}

function getPosts(feed) {
    var arr = [];

    for (var i in feed) {
        console.log(feed[i]);
        arr.push(
            <li>
                <p>{feed[i][0].content}</p>
                <p>{feed[i][1].date}</p>
                <p>{feed[i][2].author}</p>
            </li>
        );
    }

    return arr;
}