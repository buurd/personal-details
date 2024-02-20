import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import IndexView from './IndexView';
import PersonList from './PersonList';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/personList" element={<PersonList />} />
                <Route path="/" element={<IndexView />}/>
            </Routes>
        </Router>
    );
}

export default App;