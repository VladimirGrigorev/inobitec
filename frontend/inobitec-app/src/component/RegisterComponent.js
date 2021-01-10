import React, { Component } from "react";
import SecurityService from '../service/SecurityService';
import CurrentUserService from "../service/CurrentUserService";

class RegisterComponent extends Component {

    constructor(props) {
        super(props)
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
        SecurityService.registerUser(this.state.login, this.state.password).then(res => {
            this.props.history.push("/login");
        });
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
                            Зарегистрироваться
                        </button>
                    </div>
                ) : (
                    <div className="container">
                        <p>Вы уже вошли!</p>
                    </div>
                )}
            </div>
        )
    }
}

export default RegisterComponent