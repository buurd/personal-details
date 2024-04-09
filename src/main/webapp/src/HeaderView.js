import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const HeaderView = () => {
    const navigate = useNavigate();

    const handleLogout = async () => {
        const token = localStorage.getItem('token');

        if (token) {
            try {
                const response = await axios.post('/accounts/logout', { token }); // calling the logout endpoint

                if (response.status === 200) {
                    localStorage.removeItem('token');
                    navigate('/login');
                }
            } catch (error) {
                console.log('An error occurred during logout: ', error);
            }
        }
    };

    return (
        <header>
            <h1>Welcome</h1>

            <button onClick={handleLogout} data-testid="logout-button">Logout</button>
        </header>
    );
};

export default HeaderView;