import { Switch, Route, Link } from "react-router-dom";
import './App.css';
import React, { Component } from "react";
import NodeListComponent from "./component/NodeListComponent";
import RegisterComponent from "./component/RegisterComponent";
import LoginComponent from "./component/LoginComponent";
import CurrentUserService from "./service/CurrentUserService";
import NodeComponent from "./component/NodeComponent";
import AdminNodeListComponent from "./component/AdminNodeListComponent";

class App extends Component {

  constructor(props) {
    super(props)
    this.isAdmin = this.isAdmin.bind(this);
    this.isUser = this.isUser.bind(this);
    this.isAuthenticated = this.isAuthenticated.bind(this);
    this.logout = this.logout.bind(this);

    this.state = {
      isAuthenticated: false,
      isUser: false,
      isAdmin: false,
    }
  }

  isAdmin() {
    if (CurrentUserService.isAuthenticated() && CurrentUserService.getCurrentUser()) {
      console.log(CurrentUserService.getCurrentUser().data)
      this.setState({isAdmin: !!CurrentUserService.getCurrentUser().data.roles.find(role => role.name === "ROLE_ADMIN")});
    }
    else
      this.setState({isAdmin: false});
  }

  isUser() {
    if (CurrentUserService.isAuthenticated() && CurrentUserService.getCurrentUser())
      this.setState({isUser: !!CurrentUserService.getCurrentUser().data.roles.find(role => role.name === "ROLE_USER")});
    else
      this.setState({isUser: false});
  }

  isAuthenticated() {
    this.setState({isAuthenticated: CurrentUserService.isAuthenticated()});
  }

  logout() {
    CurrentUserService.logout();
    window.location.reload();
  }

  componentDidMount() {
    this.isAuthenticated();
    this.isUser();
    this.isAdmin();
    console.log(CurrentUserService.getCurrentUser());
  }

  render() {
    return (
        <div>
          <nav className="navbar navbar-expand navbar-dark bg-dark">
            <a href="/nodes" className="navbar-brand">
              Новости
            </a>
            <div className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link to={"/nodes"} className="nav-link">
                  Главная
                </Link>
              </li>
              {this.state.isAdmin && (
              <li className="nav-item">
                <Link to={"/admin/nodes"} className="nav-link">
                  Управление узлами
                </Link>
              </li>)}
              {!this.state.isAuthenticated && (
                  <li className="nav-item">
                    <Link to={"/login"} className="nav-link">
                      Войти
                    </Link>
                  </li>)}
              {!this.state.isAuthenticated && (
                  <li className="nav-item">
                    <Link to={"/register"} className="nav-link">
                      Зарегистрироваться
                    </Link>
                  </li>)}
              {this.state.isAuthenticated ? (
                  <li className="nav-item">
                    <Link onClick={this.logout} className="nav-link">
                      Выйти
                    </Link>
                  </li>) : null}
            </div>
          </nav>

          <div className="container mt-3">
            <Switch>
              <Route exact path={["/", "/nodes"]} component={NodeListComponent}/>
              <Route exact path="/admin/nodes" component={AdminNodeListComponent}/>
              <Route path="/nodes/:id" component={NodeComponent}/>
              <Route exact path="/register" component={RegisterComponent}/>
              <Route exact path="/login" component={LoginComponent}/>
            </Switch>
          </div>
        </div>
    );
  }
}

export default App;
