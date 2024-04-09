import React from 'react';
import { Link } from 'react-router-dom';
import './indexStyles.css';
import HeaderView from './HeaderView';

function IndexView() {
    return (
        <div className="container">
            <HeaderView />
            <h1>Welcome to our Application</h1>
            <Link to="/personList" className="button">View Persons</Link>
        </div>
    );
}

export default IndexView;