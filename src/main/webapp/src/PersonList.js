import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import './listStyles.css';
import HeaderView from './HeaderView';

class PersonList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            persons: []
        };
    }

    componentDidMount() {
        const token = window.localStorage.getItem('token');
        const fetchOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token   // Pass the token here
            }
        };

        fetch('/persons', fetchOptions)
            .then((response) => {
                console.log("Response: ", response);
                return response.json();
            })
            .then((data) => {
                if (Array.isArray(data)) {
                    this.setState({ persons: data });
                } else {
                    console.log('Error: Expected an array but received', data);
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    render() {
        return (
            <div className="container">
                <HeaderView />
                <h1>Person List</h1>
                <NavLink to="/personForm/new">
                    <button className="button" type="button">Add new Person</button> {/* Added class */}
                </NavLink>
                <div className="person-list">
                    {this.state.persons.map((person) => (
                        <div key={person.id} className="person-item">
                            <NavLink to={`/personView/${person.id}`}>
                                <h2 className="person-name">{person.name} {person.surname}</h2>
                            </NavLink>
                        </div>
                    ))}
                </div>
            </div>
        );
    }
}

export default PersonList;