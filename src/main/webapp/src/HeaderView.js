import React, { useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const HeaderView = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            const noAuthPaths = ['/registration', '/login'];
            if (!noAuthPaths.includes(location.pathname)) {
                const token = localStorage.getItem('token');
                if (token) {
                    try {
                        await axios.post('/accounts/validateToken', {token: token});
                    } catch (error) {
                        if (error.response && error.response.status === 401) {
                            console.log('The token is invalid.');
                            localStorage.removeItem('token');
                            navigate('/login');
                        } else {
                            console.log('An error has occurred: ', error);
                        }
                    }
                } else {
                    navigate('/login');
                }
            }
        }
        fetchData();
    }, [navigate]);

    const handleLogout = async () => {
        const token = localStorage.getItem('token');

        if (token) {
            try {
                const response = await axios.post('/accounts/logout', { token });

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