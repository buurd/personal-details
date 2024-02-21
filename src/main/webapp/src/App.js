import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import IndexView from './IndexView';
import PersonList from './PersonList';
import PersonForm from './PersonForm';
import PersonView from './PersonView'; // remember to import the PersonView component

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/personList" element={<PersonList />} />
                <Route path="/personForm" element={<PersonForm />} />
                <Route path="/personView/:id" element={<PersonView />} />
                <Route path="/" element={<IndexView />}/>
            </Routes>
        </Router>
    );
}

export default App;