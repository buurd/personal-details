import React, {useState} from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import './formStyles.css';

const EMAIL_TYPES = ['Select a value', 'PERSONAL', 'WORK', 'ACADEMIC', 'OTHERS'];
const SOCIAL_MEDIA_PLATFORMS = ['Select a value', 'FACEBOOK', 'TWITTER', 'INSTAGRAM', 'LINKEDIN', 'SNAPCHAT', 'TIKTOK', 'YOUTUBE', 'PINTEREST', 'REDDIT'];
const DATE_TYPES = ['Select a value', 'BIRTHDAY', 'WEDDING_DAY'];
const DATE_FORMATS = ['Select a value', 'DAY', 'DAY_TIME', 'TIME'];

function PersonForm() {
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [emails, setEmails] = useState([{email: "", emailType: ""}]);
    const [importantDates, setImportantDates] = useState([{date: "", type: "", format: ""}]);
    const [socialMediaHandles, setSocialMediaHandles] = useState([{platform: "", handle: ""}]);

    const handleNameChange = event => setName(event.target.value);
    const handleSurnameChange = event => setSurname(event.target.value);
    const handleEmailChange = (index, event) => {
        const newEmails = [...emails];
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
    const handleSubmit = async event => {
        event.preventDefault();

        if (name === "" || surname === "" || !emails.every(email => email.email !== "" && email.emailType !== "Select a value") ||
            !importantDates.every(date => date.date !== "" && date.type !== "Select a value" && date.format !== "Select a value") ||
            !socialMediaHandles.every(handle => handle.platform !== "Select a value" && handle.handle !== "")) {
            alert("Please fill in all fields");
            return;
        }

        const person = {
            name: name,
            surname: surname,
            emails: emails.map(email => ({ ...email, type: email.emailType })),
            importantDates: importantDates,
            socialMediaHandles: socialMediaHandles
        };

        try {
            const response = await axios.post('/persons', person);
            console.log('Success:', response);
            navigate(`/personView/${response.data.id}`);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>Name</label>
            <input type="text" value={name} onChange={handleNameChange} />

            <label>Surname</label>
            <input type="text" value={surname} onChange={handleSurnameChange} />

            {emails.map((email, index) => (
                <div key={index}>
                    <label>Email</label>
                    <input type="email" name="email" value={email.email} onChange={event => handleEmailChange(index, event)} />

                    <label>Email type</label>
                    <select name="emailType" value={email.emailType} onChange={event => handleEmailChange(index, event)}>
                        {EMAIL_TYPES.map((emailType, index) => (
                            <option key={index} value={emailType}>{emailType}</option>
                        ))}
                    </select>
                </div>
            ))}

            {importantDates.map((importantDate, index) => (
                <div key={index}>
                    <label>Date</label>
                    <input type="date" name="date" value={importantDate.date} onChange={event => handleDateChange(index, event)} />

                    <label>Date type</label>
                    <select name="type" value={importantDate.type} onChange={event => handleDateChange(index, event)}>
                        {DATE_TYPES.map((dateType, index) => (
                            <option key={index} value={dateType}>{dateType}</option>
                        ))}
                    </select>

                    <label>Date format</label>
                    <select name="format" value={importantDate.format} onChange={event => handleDateChange(index, event)}>
                        {DATE_FORMATS.map((dateFormat, index) => (
                            <option key={index} value={dateFormat}>{dateFormat}</option>
                        ))}
                    </select>
                </div>
            ))}

            {socialMediaHandles.map((socialMediaHandle, index) => (
                <div key={index}>
                    <label>Social Media Platform</label>
                    <select name="platform" value={socialMediaHandle.platform} onChange={event => handleSocialMediaChange(index, event)}>
                        {SOCIAL_MEDIA_PLATFORMS.map((platform, index) => (
                            <option key={index} value={platform}>{platform}</option>
                        ))}
                    </select>

                    <label>Handle</label>
                    <input type="text" name="handle" value={socialMediaHandle.handle} onChange={event => handleSocialMediaChange(index, event)} />
                </div>
            ))}

            <button type="submit">Submit</button>
        </form>
    );
}

export default PersonForm;