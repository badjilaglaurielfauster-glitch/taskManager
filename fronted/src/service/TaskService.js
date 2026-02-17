import axios from "axios";

const API_URL = "http://localhost:8080/tasks";


export default {

    //Get road
    getTasksByUserId(userId) {
        return axios.get(`${API_URL}?userId=${userId}`);
    },


    //Post road
    createTask(userId, taskTitle) {
        return axios.post(`${API_URL}?userId=${userId}`, {
            title: taskTitle,
        });

    },

    //Put road
    toggleTask(taskId){
        return axios.put(`${API_URL}/${taskId}/toggle`);
    },


    //delete road
    deleteTask(taskId){
        return axios.delete(`${API_URL}/${taskId}/reset`);
    }
}