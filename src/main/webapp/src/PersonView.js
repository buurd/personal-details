import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './indexStyles.css';
import { Link, useParams } from 'react-router-dom';
import HeaderView from './HeaderView';

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
            <HeaderView />
            <h1 data-testid="person-heading">{person.name} {person.surname}</h1>
            <h2 data-testid="emails-heading">Emails</h2>
            {person.emails && person.emails.map((email, index) => (
                <div key={index} data-testid={`email-container-${index}`}>
                    <p data-testid={`email-${index}`}>{email.email} ({email.type ? email.type.toLowerCase() : ''})</p>
                </div>
            ))}
            <h2 data-testid="importantDates-heading">Important Dates</h2>
            {person.importantDates && person.importantDates.map((date, index) => (
                <div key={index} data-testid={`date-container-${index}`}>
                    <p data-testid={`date-${index}`}>Type: {date.type || ''} ~ Date: {date.date || ''} ~ Format: {date.format || ''}</p>
                </div>
            ))}
            <h2 data-testid="socialMediaHandles-heading">Social Media Handles</h2>
            {person.socialMediaHandles && person.socialMediaHandles.map((handle, index) => (
                <div key={index} data-testid={`handle-container-${index}`}>
                    <p data-testid={`handle-${index}`}>Platform: {handle.platform} ~ Handle: {handle.handle}</p>
                </div>
            ))}

            <Link data-testid="edit-link" to={`/personForm/${id}`}>Edit this person</Link>
        </div>
    );
};

export default PersonView;