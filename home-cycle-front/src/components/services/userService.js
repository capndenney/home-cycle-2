import { api } from "./axiosService";

export const userService = {
    // Authentication Methods
    login: async (credentials) => {
        const response = await api.post('login', credentials);
        return response.data;
    },

    // Matching Registration Service from back end
    register: async (userDto) => api.post('register', userDto),

    // CRUD methods
    getMe: () => api.get('users/me'),
    create: async (userDto) => api.post('users/new', userDto),
    getUser: (userId) => api.get(`users/${userId}`),
    updateUser: (userId, userData) => api.put(`users/${userId}`, userData),
    deleteUser: (userId) => api.delete(`users/${userId}/delete`)
}