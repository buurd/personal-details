import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Registration = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState(null);

    const handleUsernameChange = event => setUsername(event.target.value);

    const handleSubmit = async event => {
        event.preventDefault();

        if (username === null) {
            alert("Please fill the username field");
            return;
        }

        try {
            const response = await axios.post('/accounts', { username });

            if (response.status === 201) {
                window.location = '/';
            } else {
                // handle registration error
            }
        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <div>
            <h1>Registration Page</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" data-testid="username-input" onChange={handleUsernameChange} />
                <button type="submit" data-testid="submit-button">Register</button>
            </form>
        </div>
    );
};

export default Registration;