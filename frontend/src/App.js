import Signup from './accounts/Signup'
import Signin from './accounts/Signin'
import { Route, Routes } from 'react-router-dom';
import Main from './view/Main'
import VideoRoom from './components/VideoRoomComponent'
import UserProfile from './accounts/UserProfile'
import CreateStudyroom from './view/CreateStudyroom'
import BeforeEnterRoom from './view/BeforeEnterRoom'
import Community from './view/Community'
import { useEffect, useState } from 'react';
import axios from 'axios';


function App() {
  const [userInfo, setUserInfo] = useState({})
  const [studyrooms, setStudyroom] = useState([])

  useEffect(() => {
    axios.get("http://i6c204.p.ssafy.io:8081/api/member", {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    })
      .then(res => {
        setUserInfo(res.data.member)
        console.log(userInfo)
      })
      .catch(err => {
        console.log(err)
      })
  }, [])

  return (
    <Routes>
      <Route path="/" element={<Main studyrooms={studyrooms} setStudyroom={setStudyroom} />} />
      <Route path="/login" element={<Signin setUserInfo={setUserInfo} setStudyroom={setStudyroom} />} />
      <Route path="/join" element={<Signup />} />
      <Route path="/studyroom" element={<VideoRoom />} />
      <Route path="/profile" element={<UserProfile userInfo={userInfo} setUserInfo={setUserInfo} />} />
      <Route path="/createroom" element={<CreateStudyroom userInfo={userInfo} />} />
      <Route path="/beforestudy" element={<BeforeEnterRoom />} />
      <Route path="/community" element={<Community />} />
    </Routes>
  )
}

export default App;
