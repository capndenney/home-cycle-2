import { api } from "./axiosService";

export const feedbackService = {
    // CRUD methods
    sendFeedback: (data) => api.post(`feedback`, data)
}