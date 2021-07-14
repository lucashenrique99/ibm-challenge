const BASE_URL = "http://localhost:8080/tasks";

const findAll = (callback) => {
    fetch(BASE_URL, { method: 'GET' })
        .then(resp => resp.json())
        .then(callback);
}

const insert = (payload, callback) => {
    fetch(BASE_URL, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(resp => resp.json())
        .then(callback);
}

const update = (id, payload, callback) => {
    fetch(`${BASE_URL}/${id}`, {
        method: 'PATCH',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    })
        .then(resp => resp.json())
        .then(callback);
}

const findById = (id, callback) => {
    fetch(`${BASE_URL}/${id}`, { method: 'GET' })
        .then(resp => resp.json())
        .then(callback);
}

const deleteById = (id, callback) => {
    fetch(`${BASE_URL}/${id}`, { method: 'DELETE' })
        .then(callback);
}

export const TaskService = { findAll, insert, update, findById, deleteById };