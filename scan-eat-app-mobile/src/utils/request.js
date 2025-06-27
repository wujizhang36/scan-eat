import axios from 'axios'

// const service = axios.create({
//     baseURL: import.meta.env.VITE_API_BASE_URL, // 使用环境变量
//     timeout: 10000,
// })

const service = axios.create({
    baseURL: '/api', // 将被代理到 http://localhost:8081
    timeout: 10000,
})

export default service
