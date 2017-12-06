import React from 'react'

export default class FriendsList extends React.Component {
    render() {
        return (
            <div id="friends-list">
                <h3>Friends</h3>
                <ul>
                    {getList(this.props.friends)}
                </ul>
            </div>
        );
    }
}

function getList(friends) {
    var arr = [];

    for (var friend in friends) {
        arr.push(<li>{friends[friend]}</li>);
    }

    return arr;
}