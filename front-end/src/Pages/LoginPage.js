import React from 'react';

export default class LoginComp extends React.Component {
    render() {
        return (
            <div id="login-page">
                <h1>BookFace</h1>
                <h3>Sign In!</h3>
                <EnterDetails changeHandler={this.props.changeHandler}/>
                <a href="#">Create an Account</a>
            </div>
        );
    }
}

var classVar = null; //need for function calls within asynchronous responses

class EnterDetails extends React.Component {
    constructor() {
        super();
        classVar = this;
    }

    sendValidationRequest() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        console.log(username);
        console.log(password);

        fetch('http://localhost:3002/login', {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            username: username,
            password: password
          })
        }).then(function(response) {
            console.log(response);
            classVar.getValidationStatus();
        });
    }

    getValidationStatus() {
        fetch('http://localhost:3002/authenticate')
            .then((resp) => resp.text()) // Transform the data into a string
            .then(function(data) {
                classVar.checkValidationStatus(data);
            });
    }

    checkValidationStatus(status) {
        if (status === "invalid") {
            alert("Invalid Credentials!");
        }
        else if (status === "valid") {
            this.props.changeHandler();
        }
    }

    render() {
        return (
            <div>
                <input id="username" type="text"
                                     placeholder="Username"
                                     name="username"/>
                <br/>
                <input id="password" type="password"
                                     placeholder="Password"
                                     name="password"/>
                <br/>
                <input type="submit" onClick={this.sendValidationRequest}/>
            </div>
        );
    }
}













