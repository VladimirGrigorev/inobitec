import http from "../http-common";

class CurrentUserService {

    setToken(token) {
        localStorage.setItem('token', token);
    }

    getToken() {
        return localStorage.getItem('token');
    }

    setCurrentUser(user) {
        console.log(JSON.stringify(user))
        localStorage.setItem('currentUser', JSON.stringify(user));
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('currentUser'));
    }

    getUpdatedCurrentUser() {
        this.setSession()
        return this.getCurrentUser();
    }

    isAuthenticated() {
        return !!this.getToken();
    }

    setSession() {
        return http.get('/v1/me', this.buildOpts())
            .then(user => {
                    this.setCurrentUser(user);
                },
                error => {
                    console.log(error);
                });
    }

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('currentUser');
    }

    buildOpts() {
        return {
            headers: ({
                'Authorization': `Bearer ${this.getToken()}`
            })
        };
    }
}

export default new CurrentUserService();