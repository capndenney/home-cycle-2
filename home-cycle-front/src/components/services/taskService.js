import { api } from "./axiosService";

export const taskService = {
    create: async (taskDto) => api.post('tasks/newtask', taskDto),
    getTasks: () => api.get('tasks'),
    getTask: (taskId) => api.get(`tasks/${taskId}`),
    updateTask: (taskId, taskDto) => api.put(`tasks/${taskId}`, taskDto),
    completeTask: (taskId, userId) => api.put(`tasks/${taskId}/complete`, userId), 
    deleteTask: (taskId) => api.delete(`tasks/${taskId}`)
}