import React from "react";
import './List.css';

function List(props) {

    return (
        <div className="list-container">
            {props.tasks.map((task, index) => (
                <div key={task.id} className="task-container">
                    <div>
                        {task.description}
                    </div>
                    <button className="primary-button edit-button" onClick={() => props.onEdit(task)}>
                        Editar
                    </button>
                    <button className="primary-button delete-button" onClick={() => props.onRemove(task)}>
                        Excluir
                    </button>
                </div>
            ))}
        </div>
    );
}

export default List;