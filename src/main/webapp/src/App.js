import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import IndexView from './IndexView';
import PersonList from './PersonList';
import PersonForm from './PersonForm';
import PersonView from './PersonView';
import Registration from './Registration';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/personList" element={<PersonList />} />
                <Route path="/personForm/new" element={<PersonForm />} />
                <Route path="/personForm/:id" element={<PersonForm />} />
                <Route path="/personView/:id" element={<PersonView />} />
                <Route path='/registration' element={<Registration />} />
                <Route path="/" element={<IndexView />}/>
            </Routes>
        </Router>
    );
}

export default App;