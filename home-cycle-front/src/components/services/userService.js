import { api } from "./axiosService";

export const userService = {
    // TODO: Add endpoint for current logged in user
    create: async (userDto) => api.post('users/new', userDto),
    getUser: (userId) => api.get(`users/${userId}`),
    updateUser: (userId, userData) => api.put(`users/${userId}`, userData),
    deleteUser: (userId) => api.delete(`users/${userId}/delete`)
}