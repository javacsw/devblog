//net/index.js
import axios from "axios";
import { ElMessage } from "element-plus";
function getAuthToken() {
    return localStorage.getItem('authToken') || '';
}
const defaultError = () => ElMessage.error('发生错误，请联系管理员。')
const defaultFailure = (message) => ElMessage.warning(message)

function post(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Authorization": getAuthToken()
        },
        withCredentials: true
    }).then(({data: responseData}) => {
        if (responseData.success)
            success(responseData.message,responseData.data)
        else
            failure(responseData.message)
    }).catch(error)
}
function jsonpost(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            "Content-Type": "application/json"
        },
        withCredentials: true
    }).then(({data: responseData}) => {
        if (responseData.success)
            success(responseData.message,responseData.data)
        else
            failure(responseData.message)
    }).catch(error)
}

function get(url, data = null, success, failure = defaultFailure, error = defaultError) {
    const config = {
        withCredentials: true,
        params: data,
        headers:{
            "Authorization": getAuthToken()
        }
    };

    axios.get(url, config)
        .then(({data: responseData}) => {
            if (responseData.success)
                success(responseData.message,responseData.data)
            else
                failure(responseData.message)
        })
        .catch(error)
}

export { get, post }