import { api } from "./axiosService";

export const userService = {
    // Authentication Methods
    login: async (credentials) => {
        const response = await api.post('login', credentials);
        return response.data;
    },
    logout: async () => {
        return await api.post('logout')
    },

    // Matching Registration Service from back end
    register: async (userDto) => api.post('register', userDto),

    // Password update
    updatePassword: async(passwordDTO) => api.put(`users/update-password`, passwordDTO),

    // CRUD methods
    getMe: () => api.get('users/me'),
    create: async (userDto) => api.post('users/new', userDto),
    getUser: (userId) => api.get(`users/${userId}`),
    updateUser: (userId, userData) => api.patch(`users/${userId}`, userData),
    deleteUser: (userId) => api.delete(`users/${userId}/delete`)
}