import Signup from './accounts/Signup'
import Signin from './accounts/Signin'
import { Route, Routes } from 'react-router-dom';
import Main from './view/Main'
import Studyroom from './view/studyroom/Studyroom'



function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/login" element={<Signin />} />
      <Route path="/join" element={<Signup />} />
      <Route path="/studyroom" element={<Studyroom />} />
    </Routes>
  )
}

export default App;
