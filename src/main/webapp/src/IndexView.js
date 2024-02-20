import React from 'react';
import { Link } from 'react-router-dom';
import './indexStyles.css'; // Include if you have specific css for indexView

function IndexView() {
    return (
        <div className="container">
            <h1>Welcome to our Application</h1>
            <Link to="/personList" className="button">View Persons</Link>
        </div>
    );
}

export default IndexView;