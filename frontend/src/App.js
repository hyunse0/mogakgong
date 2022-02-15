import Signup from './accounts/Signup'
import Signin from './accounts/Signin'
import { Route, Routes } from 'react-router-dom';
import Main from './view/Main'
import VideoRoom from './components/VideoRoomComponent'
import UserProfile from './accounts/UserProfile'
import CreateStudyroom from './view/CreateStudyroom'
import BeforeEnterRoom from './view/BeforeEnterRoom'
import Community from './view/Community'
import { useState } from 'react';

// const { faker } = require('@faker-js/faker');
// faker.locale = "ko"
// const studyrooms = [...Array(10)].map((_, idx) => ({
//   title: faker.lorem.word(),
//   category: faker.lorem.word(),
//   description: faker.lorem.lines(),
//   hashtag: faker.lorem.word(),
//   start_date: faker.datatype.datetime(),
//   end_date: faker.datatype.datetime(),
//   limit: Math.random(0, 1) * 10,
//   img: faker.image.image(),
// }))


function App() {
  const [userInfo, setUserInfo] = useState(null)
  const [studyrooms, setStudyroom] = useState([])

  return (
    <Routes>
      <Route path="/" element={<Main studyrooms={studyrooms} setStudyroom={setStudyroom} />} />
      <Route path="/login" element={<Signin setUserInfo={setUserInfo} setStudyroom={setStudyroom} />} />
      <Route path="/join" element={<Signup />} />
      <Route path="/studyroom" element={<VideoRoom />} />
      <Route path="/profile" element={<UserProfile userInfo={userInfo} />} />
      <Route path="/createroom" element={<CreateStudyroom />} />
      <Route path="/beforestudy" element={<BeforeEnterRoom />} />
      <Route path="/community" element={<Community />} />
    </Routes>
  )
}

export default App;
