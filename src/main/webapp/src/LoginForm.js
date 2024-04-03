import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState(null);
    const [errorMessage, setErrorMessage] = useState(null);
    const [buttonDisabled, setButtonDisabled] = useState(false);

    const handleUsernameChange = event => setUsername(event.target.value);

    useEffect(() => {
        const validateToken = async () => {
            const token = localStorage.getItem('token');
            if (token) {
                try {
                    const response = await axios.post('/accounts/validateToken', { token });
                    console.log(response.status);
                    if (response.status === 200) {
                        setErrorMessage('You are already logged in.');
                        setButtonDisabled(true);
                        setTimeout(() => navigate('/'), 5000);
                    }
                } catch (error) {
                    localStorage.removeItem('token'); // remove invalid token
                }
            }
        };

        validateToken();
    }, [navigate]);

    const handleSubmit = async event => {
        event.preventDefault();

        if (errorMessage) {
            return;
        }

        if (username === null) {
            alert("Please fill the username field");
            return;
        }

        try {
            const response = await axios.post('/accounts/login', { username });

            if (response.status === 200) {
                localStorage.setItem('token', response.data);
                window.location = '/';
            } else {
                // handle login error
            }
        } catch (error) {
            console.error("Error:", error);
            setErrorMessage((error.response && error.response.data && error.response.data.message) || "Error logging in");
        }
    };
    return (
        <div>
            <h1>Login Page</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username:</label>
                <input type="text" id="username" name="username" data-testid="username-input" onChange={handleUsernameChange} />
                <button type="submit" data-testid="submit-button" disabled={buttonDisabled}>Login</button>
            </form>
            {errorMessage && <p data-testid="error-message">{errorMessage}</p>}
        </div>
    );
};

export default LoginForm;