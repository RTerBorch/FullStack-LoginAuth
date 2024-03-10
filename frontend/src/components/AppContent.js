import * as React from "react";
import WelcomeContent from "./WelcomeContent";
import AuthContent from "./AuthContent";
import LoginForm from "./LoginForm";
import Buttons from "./Buttons";
import { request } from "../axios_helper";

export default class AppContent extends React.Component {
  constructor(props) {
    super(props);
    this.state = { compoonentToShow: "welcome" };
  }

  login = () => {
    this.setState({ compoonentToShow: "login" });
  };

  logout = () => {
    this.setState({ compoonentToShow: "welcome" });
  };

  onLogin = (e, username, password) => {
    e.preventDefault();
    request("POST", "/login", { login: username, password: password }).then(
      (response) => {
        this.setState({ compoonentToShow: "messages" }).catch((error) => {
          this.setState({ compoonentToShow: "welcome" });
        });
      }
    );
  };

  onRegister = (e, firstName, lastName, username, password) => {
    e.preventDefault();
    request("POST", "/register", {
      firstname: firstName,
      lastName: lastName,
      login: username,
      password: password,
    }).then((response) => {
      this.setState({ compoonentToShow: "messages" }).catch((error) => {
        this.setState({ compoonentToShow: "welcome" });
      });
    });
  };

  render() {
    return (
      <div>
        <Buttons
          login={this.login}
          logout={this.logout}
          register={this.register}
        ></Buttons>

        {this.state.compoonentToShow === "welcome" && <WelcomeContent />}
        {this.state.compoonentToShow === "messages" && <AuthContent />}
        {this.state.compoonentToShow === "login" && (
          <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} />
        )}
      </div>
    );
  }
}
