import { api } from "./axiosService";

export const hhService = {
    // CRUD methods
    getHh: (hhId) => api.get(`household/${hhId}`),
    updateHh: (hhId, hhData) => api.put(`household/${hhId}`, hhData),
    deleteHh: (hhId) => api.delete(`household/${hhId}`)
}