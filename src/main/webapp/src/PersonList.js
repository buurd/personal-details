import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import './listStyles.css';  // <-- Add this import statement

class PersonList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            persons: []
        };
    }

    componentDidMount() {
        // Call your API here
        fetch('/api/person/findAll')
            .then((response) => response.json())
            .then((data) => this.setState({persons: data}));
    }

    render() {
        return (
            <div className="container">
                <h1>Person List</h1>
                <NavLink to="/personForm">
                    <button className="button" type="button">Add new Person</button> {/* Added class */}
                </NavLink>
                <div className="person-list">
                    {this.state.persons.map((person) => (
                        <div key={person.id} className="person-item">
                            <NavLink to={`/personView/${person.id}`}>
                                <h2>{person.name} {person.surname}</h2>
                            </NavLink>
                        </div>
                    ))}
                </div>
            </div>
        );
    }
}

export default PersonList;