import React, { useCallback, useEffect, useState } from "react";
import './Form.css';

function Form(props) {
    const [text, setText] = useState(props.current.description);

    const updateText = useCallback(() => {
        console.log(props)
        if (props.current.description !== text) {
            setText(props.current.description);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [props.current])

    useEffect(() => updateText(), [updateText])

    return (
        <div className="form-container">
            <div>
                <input
                    type="text"
                    value={text}
                    onChange={event => setText(event.target.value)}
                />

                <button className="primary-button" onClick={() => props.onSave({ id: props.current.id, description: text })}>
                    Salvar
                </button>
            </div>
        </div >
    );
}

export default Form;