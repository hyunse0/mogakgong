import axios from "axios";

const api = axios.create({
    // 개발용
    // baseURL: "http://i6c204.p.ssafy.io:8081/api",
    // 배포용
    baseURL: "https://i6c204.p.ssafy.io",
    headers: {
        Authorization: localStorage.getItem('token')
    }
})

export default api;