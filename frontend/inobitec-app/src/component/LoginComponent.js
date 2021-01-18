import React, { Component } from "react";
import SecurityService from '../service/SecurityService';
import CurrentUserService from "../service/CurrentUserService";
import { withRouter } from 'react-router-dom';

class LoginComponent extends Component {

    constructor(props) {
        super(props);
        this.onSubmit = this.onSubmit.bind(this);
        this.onChangeLogin = this.onChangeLogin.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);

        this.state = {
            isAuthenticated: false,
            login: "",
            password: "",
        }
    }

    componentDidMount() {
        this.setState({isAuthenticated: CurrentUserService.isAuthenticated()});
    }

    onChangeLogin(e) {
        this.setState({
            login: e.target.value
        });
    }

    onChangePassword(e) {
        this.setState({
            password: e.target.value
        });
    }

    onSubmit() {
        SecurityService.loginUser(this.state.login, this.state.password).then(res => {
            console.log(res.data.token);
            CurrentUserService.setToken(res.data.token);
            this.state.isAuthenticated = true;
        }).then(res => {
            CurrentUserService.setSession().then(res => {
                this.toStartPage();
            });
        });
    }

    toStartPage() {
        this.props.history.push("/");
        window.location.reload();
        console.log(CurrentUserService.getCurrentUser());
    }

    render() {
        return (
            <div>
                {!this.state.isAuthenticated ? (<div className="login-container">
                        <div className="form-group">
                            <label htmlFor="title">Логин</label>
                            <input
                                type="text"
                                className="form-control"
                                id="login"
                                required
                                value={this.state.login}
                                onChange={this.onChangeLogin}
                                name="login"
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="password">Пароль</label>
                            <input
                                type="text"
                                className="form-control"
                                id="password"
                                required
                                value={this.state.password}
                                onChange={this.onChangePassword}
                                name="password"
                            />
                        </div>

                        <button onClick={this.onSubmit} className="btn btn-success">
                            Войти
                        </button>
                    </div>
                ) : (
                    <div className="container">
                        <p>Вы успешно вошли!</p>
                    </div>
                )}
            </div>
        )
    }
}

export default withRouter(LoginComponent)