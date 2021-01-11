import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8080/inobitec-project-0.0.1-SNAPSHOT/api", // for Tomcat
    // baseURL: "http://localhost:8080/api", // for IntelliJ IDEA
    headers: {
        "Content-type": "application/json"
    }
});