import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import './formStyles.css';

const EMAIL_TYPES = ['Select a value', 'PERSONAL', 'WORK', 'ACADEMIC', 'OTHERS'];
const SOCIAL_MEDIA_PLATFORMS = ['Select a value', 'FACEBOOK', 'TWITTER', 'INSTAGRAM', 'LINKEDIN', 'SNAPCHAT', 'TIKTOK', 'YOUTUBE', 'PINTEREST', 'REDDIT'];
const DATE_TYPES = ['Select a value', 'BIRTHDAY', 'WEDDING_DAY'];
const DATE_FORMATS = ['Select a value', 'DAY', 'DAY_TIME', 'TIME'];

function PersonForm() {
    const navigate = useNavigate();
    const { id } = useParams();
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [emails, setEmails] = useState([{email: "", type: ""}]);
    const [importantDates, setImportantDates] = useState([]);
    const [socialMediaHandles, setSocialMediaHandles] = useState([]);

    useEffect(() => {
        if (id) {
            axios.get(`/persons/${id}`).then(response => {
                const person = response.data;

                setName(person.name || "");
                setSurname(person.surname || "");
                setEmails(person.emails.map(email => ({ ...email, type: email.type || "" })));
                setImportantDates(person.importantDates || []);
                setSocialMediaHandles(person.socialMediaHandles || []);
            });
        }
    }, [id]);

    const handleNameChange = event => setName(event.target.value);
    const handleSurnameChange = event => setSurname(event.target.value);
    const handleEmailChange = (index, event) => {
        const newEmails = [...emails];
        // Using correct property 'type' to save the state
        newEmails[index] = {...newEmails[index], [event.target.name]: event.target.value};
        setEmails(newEmails);
    };
    const handleDateChange = (index, event) => {
        const newDates = [...importantDates];
        newDates[index] = {...newDates[index], [event.target.name]: event.target.value};
        setImportantDates(newDates);
    };
    const handleSocialMediaChange = (index, event) => {
        const newHandles = [...socialMediaHandles];
        newHandles[index] = {...newHandles[index], [event.target.name]: event.target.value};
        setSocialMediaHandles(newHandles);
    };
    const handleAddEmail = () => setEmails([...emails, {email: "", type: ""}]);
    const handleRemoveEmail = index => setEmails(emails.filter((_, i) => i !== index));
    const handleAddDate = () => setImportantDates([...importantDates, {}]);
    const handleRemoveDate = index => setImportantDates(importantDates.filter((_, i) => i !== index));
    const handleAddSocialMedia = () => setSocialMediaHandles([...socialMediaHandles, {}]);
    const handleRemoveSocialMedia = index => setSocialMediaHandles(socialMediaHandles.filter((_, i) => i !== index));

    const handleSubmit = async event => {
        event.preventDefault();

        if (
            name === "" ||
            surname === "" ||
            !emails.every(
                email => email.email !== "" && email.type !== "Select a value"
            ) ||
            !importantDates.every(
                date => date.date !== "" && date.type !== "Select a value"
            ) ||
            !socialMediaHandles.every(
                handle => handle.handle !== "" && handle.platform !== "Select a value"
            )
        ) {
            alert("Please fill in all fields");
            return;
        }

        try {
            const response = await axios.post('/persons', {
                name,
                surname,
                emails,
                importantDates,
                socialMediaHandles,
            });
            navigate(`/personView/${response.data.id}`);
        } catch (error) {
            console.error("Error:", error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>Name:
                <input type="text" value={name} onChange={handleNameChange} />
            </label>
            <label>Surname:
                <input type="text" value={surname} onChange={handleSurnameChange} />
            </label>

            {emails.map((email, index) => (
                <div key={index}>
                    <label>Email:
                        <input type="email" name="email" value={email.email} onChange={(event) => handleEmailChange(index, event)} />
                    </label>
                    <label>Type:
                        <select name="type" value={email.type} onChange={(event) => handleEmailChange(index, event)}>
                            {EMAIL_TYPES.map(type => <option key={type} value={type}>{type}</option>)}
                        </select>
                    </label>
                    <button onClick={(event) => {
                        event.preventDefault();
                        handleRemoveEmail(index);
                    }}>Remove
                    </button>
                </div>
            ))}
            <button onClick={(event) => {
                event.preventDefault();
                handleAddEmail();
            }}>Add Email
            </button>

            {importantDates.map((date, index) => (
                <div key={index}>
                <label>Date:
                        <input type="date" name="date" value={date.date} onChange={(event) => handleDateChange(index, event)} />
                    </label>
                    <label>Type:
                        <select name="type" value={date.type} onChange={(event) => handleDateChange(index, event)}>
                            {DATE_TYPES.map(type => <option key={type} value={type}>{type}</option>)}
                        </select>
                    </label>
                    <label>Format:
                        <select name="format" value={date.format} onChange={(event) => handleDateChange(index, event)}>
                            {DATE_FORMATS.map(format => <option key={format} value={format}>{format}</option>)}
                        </select>
                    </label>
                    <button onClick={(event) => {
                        event.preventDefault();
                        handleRemoveDate(index);
                    }}>Remove
                    </button>
                </div>
            ))}
            <button onClick={(event) => {
                event.preventDefault();
                handleAddDate();
            }}>Add Date
            </button>

            {socialMediaHandles.map((handle, index) => (
                <div key={index}>
                    <label>Platform:
                        <select name="platform" value={handle.platform}
                                onChange={(event) => handleSocialMediaChange(index, event)}>
                            {SOCIAL_MEDIA_PLATFORMS.map(platform => <option key={platform}
                                                                            value={platform}>{platform}</option>)}
                        </select>
                    </label>
                    <label>Handle:
                        <input type="text" name="handle" value={handle.handle}
                               onChange={(event) => handleSocialMediaChange(index, event)}/>
                    </label>
                    <button onClick={(event) => {
                        event.preventDefault();
                        handleRemoveSocialMedia(index);
                    }}>Remove
                    </button>
                </div>
            ))}
            <button onClick={(event) => {
                event.preventDefault();
                handleAddSocialMedia();
            }}>Add Social Media
            </button>

            <button type="submit">Submit</button>
        </form>
    );
}

export default PersonForm;