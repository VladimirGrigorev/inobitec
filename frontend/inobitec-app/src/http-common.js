import axios from "axios";

export default axios.create({
    baseURL: `http://192.168.0.13:8090/backend/api`, // for Tomcat
    // baseURL: "http://localhost:8080/api", // for IntelliJ IDEA
    headers: {
        "Content-type": "application/json"
    }
});