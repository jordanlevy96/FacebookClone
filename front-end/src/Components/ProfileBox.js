import React from 'react'

export default class ProfileBox extends React.Component {
    constructor(props) {
        super();
        this.state = {
            imgsrc: "../resources/pics/user" + props.id + "pic.png"
        }
    }

    render() {
        return (
            <div id="profile-box">
                <img id="profile-pic"
                     src={require("../resources/pics/user1pic.png")}
                     alt="img not found"/>
                <p>{this.props.name}</p>
            </div>
        );
    }
}