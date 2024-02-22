import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './indexStyles.css';
import { Link, useParams } from 'react-router-dom';

const PersonView = () => {
    const [person, setPerson] = useState(null);
    const { id } = useParams();

    useEffect(() => {
        axios.get(`/persons/${id}`)
            .then(response => setPerson(response.data))
            .catch(error => console.error('Error:', error));
    }, [id]);  // run the effect whenever the id changes

    return person && (
        <div>
            <h1>{person.name} {person.surname} - Age: {person.age}</h1>
            <h2>Emails</h2>
            {person.emails && person.emails.map((email, index) => (
                <div key={index}>
                    <p>{email.email} ({email.type.toLowerCase()})</p>
                </div>
            ))}
            <h2>Important Dates</h2>
            {person.importantDates && person.importantDates.map((date, index) => (
                <div key={index}>
                    <p>Type: {date.type}~ Date: {date.date} ~ Format: {date.format}</p>
                </div>
            ))}
            <h2>Social Media Handles</h2>
            {person.socialMediaHandles && person.socialMediaHandles.map((handle, index) => (
                <div key={index}>
                    <p>Platform: {handle.platform} ~ Handle: {handle.handle}</p>
                </div>
            ))}

            <Link to={`/personForm/${id}`}>Edit this person</Link>
        </div>
    );
};

export default PersonView;