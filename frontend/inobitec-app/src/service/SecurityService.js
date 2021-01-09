import http from "../http-common";

class SecurityService {

    loginUser(login, password) {
        let body = {
            login,
            password
        };

        return http.post('/v1/security/login', body);
    }

    registerUser(login, password) {
        let body = {
            login,
            password
        };

        return http.post('/v1/security/register', body);
    }
}

export default new SecurityService();