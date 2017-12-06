import React, { Component } from 'react';
// import logo from './logo.svg';
import './App.css';
import ProfileComp from './Pages/ProfilePage.js';
import LoginComp from './Pages/LoginPage.js';

var classVar = null;

class App extends Component {

  constructor() {
    super()
    classVar = this;
    this.state = {
      active: 'LOGIN'
    }
  }

  updateActive() {
      var active = classVar.state.active;
      var newActive = active === 'LOGIN' ? 'PROFILE' : 'LOGIN';
      classVar.setState({
          active: newActive
      });
  }

  render() {
      return (
          <div>
              {this.state.active === 'LOGIN' ? (
                  <LoginComp changeHandler={this.updateActive} />
              ) : this.state.active === 'PROFILE' ? (
                  <ProfileComp />
              ) : null}
          </div>
      );

   }
}

export default App;