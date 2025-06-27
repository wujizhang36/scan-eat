import axios from 'axios'

export function getTenantList(params) {
    return axios.get('/api/admin/tenants', { params })
}

export function createTenant(data) {
    return axios.post('/api/admin/tenants', data)
}

export function updateTenant(data) {
    return axios.put(`/api/admin/tenants/${data.id}`, data)
}

export function updateTenantStatus(id, status) {
    return axios.patch(`/api/admin/tenants/${id}/status`, { status })
}
