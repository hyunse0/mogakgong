import { useEffect, useState } from 'react';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import SpeedDial from '@mui/material/SpeedDial';
import { Card, SpeedDialAction, SpeedDialIcon, Typography, Link } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
import IconButton from '@mui/material/IconButton';
import InfoIcon from '@mui/icons-material/Info';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import ProgressBar from './ProgressBar';
import api from '../components/api'

const DEFAULT_IMG = [
    "https://images.freeimages.com/images/small-previews/eaf/studying-ahead-1421056.jpg",
    "https://www.how-to-study.com/images/study-skills-assessments.jpg",
    "https://ugc.futurelearn.com/uploads/images/4d/c9/header_4dc9321b-f608-4196-9fb7-02f6c0029a5f.jpg"

]
const theme = createTheme();

export default function Main({ studyrooms, setStudyroom, userInfo, setUserInfo, rcmStudyrooms, setRcmStudyroom, totalStudyrooms, setTotalStudyroom }) {

    const fetchStudyroom = async () => {
        try {
            const res = await api.get("/studyroom?page=0&size=20")
            console.log(res.info.content)
            setTotalStudyroom(res.info.content)
        } catch (err) {
            console.log(err)
        }
    }

    // 새로고침시 상태를 다시 불러오기 위함 
    useEffect(() => {
        api.get("/member", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
            .then(res => {
                // setUserInfo(res.data.member)
                api.get("/main/" + `${res.data.member
                    .id}`, {
                    headers: {
                        Authorization: localStorage.getItem('token')
                    },
                })
                    .then(res => {
                        console.log(res)
                        setStudyroom(res.data.historyStudyRoom.content)
                        setRcmStudyroom(res.data.recommendStudyRoom.content)
                    })
                    .catch((err) => {
                        console.log(err)
                    })
            })
            .catch(err => {
                console.log(err)
            })
        fetchStudyroom();
    }, [])

    // 선택한 room 정보 localStorage에 저장
    const saveRoomInfo = (item) => {
        console.log(item)
        localStorage.setItem('roomInfo', JSON.stringify(item))
    }

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="lg">
                <CssBaseline />
                <Grid container spacing={2}>
                    {/* 내 목표 */}
                    {/* <Grid item xs={12} sm={6}>
                        <Typography m={2} variant='h6'>내 목표</Typography>
                        <Grid
                            container
                            justifyContent="center"
                            alignItems="center">
                            <ProgressBar props={65} m={3}></ProgressBar>
                        </Grid>
                    </Grid> */}
                    {/* 내 랭킹 */}
                    {/* <Grid item xs={12} sm={6}>
                        <Typography mt={2} variant='h6'>내 랭킹</Typography>
                    </Grid> */}
                    <Grid item xs={12}>
                        {/* 내 스터디 */}
                        <Typography mt={3} variant='h6' >내 스터디</Typography>
                        <ImageList cols={4} >
                            {userInfo ? studyrooms.map((item) => (
                                <Card key={item.id}>
                                    <ImageListItem sx={{ maxHeight: 164 }}>
                                        <img
                                            src={item.img ? item.img : DEFAULT_IMG[item.id % 3]}
                                            alt={DEFAULT_IMG[item.id % 3]}
                                            loading="lazy"

                                        />
                                        <ImageListItemBar
                                            title={item.title}
                                            subtitle={item.hashtag}
                                            actionIcon={
                                                <IconButton
                                                    sx={{ color: 'rgba(255, 255, 255, 0.54)' }}
                                                    onClick={() => {
                                                        saveRoomInfo(item)
                                                    }}
                                                >
                                                    <Link
                                                        color="inherit"
                                                        href="/beforestudy"
                                                    >
                                                        <InfoIcon />
                                                    </Link>
                                                </IconButton>
                                            }
                                        />
                                    </ImageListItem>
                                </Card>
                            )) : <div> 로그인이 필요한 기능입니다 </div>}
                            {studyrooms.length > 0 ? null : <div> 최근 스터디룸이 없습니다. </div>}
                        </ImageList>

                    </Grid>

                    <Grid item xs={12} >
                        {/* 추천 스터디 */}
                        <Typography mt={2} variant='h6'>추천 스터디</Typography>
                        <ImageList cols={4} >
                            {rcmStudyrooms.length > 0 ? rcmStudyrooms.map((item) => (
                                <Card key={item.id}>
                                    <ImageListItem sx={{ maxHeight: 164 }} >
                                        <img
                                            src={item.img ? item.img : DEFAULT_IMG[item.id % 3]}
                                            alt="null"
                                            loading="lazy"
                                        />
                                        <ImageListItemBar
                                            title={item.title}
                                            subtitle={item.hashtag}
                                            actionIcon={
                                                <IconButton
                                                    sx={{ color: 'rgba(255, 255, 255, 0.54)' }}
                                                    onClick={() => {
                                                        saveRoomInfo(item)
                                                    }}
                                                >
                                                    <Link
                                                        color="inherit"
                                                        href="/beforestudy"
                                                    >
                                                        <InfoIcon />
                                                    </Link>
                                                </IconButton>
                                            }
                                        />
                                    </ImageListItem>
                                </Card>
                            )) : <div>추천 스터디룸이 없습니다.</div>}
                        </ImageList>
                    </Grid>

                    <Grid item xs={12} >
                        {/* 전체 스터디 */}
                        <Typography mt={2} variant='h6'>전체 스터디</Typography>
                        <ImageList cols={4} >
                            {totalStudyrooms.length > 0 ? totalStudyrooms.map((item) => (
                                <Card key={item.id}>
                                    <ImageListItem sx={{ maxHeight: 164 }}>
                                        <img
                                            src={item.img ? item.img : DEFAULT_IMG[item.id % 3]}
                                            alt="null"
                                            loading="lazy"
                                        />
                                        <ImageListItemBar
                                            title={item.title}
                                            subtitle={item.hashtag}
                                            actionIcon={
                                                <IconButton
                                                    sx={{ color: 'rgba(255, 255, 255, 0.54)' }}
                                                    onClick={() => {
                                                        saveRoomInfo(item)
                                                    }}
                                                >
                                                    <Link
                                                        color="inherit"
                                                        href="/beforestudy"
                                                    >
                                                        <InfoIcon />
                                                    </Link>
                                                </IconButton>
                                            }
                                        />
                                    </ImageListItem>
                                </Card>
                            )) : <div>현재 스터디룸이 없습니다.</div>}
                        </ImageList>
                    </Grid>
                </Grid>
                {/* float menu */}
                <SpeedDial
                    ariaLabel=""
                    sx={{ position: 'fixed', bottom: 32, right: 32, color: 'rgba(255, 255, 255, 0.54)' }}
                    icon={<SpeedDialIcon />}
                >
                    <SpeedDialAction
                        icon={<SearchIcon />}
                        tooltipTitle={"스터디 검색하기"}
                    />
                    <SpeedDialAction
                        icon={
                            <Link href='/createroom'>
                                <AddIcon />
                            </Link>}
                        tooltipTitle={"새로운 스터디 만들기"}
                    />
                </SpeedDial>
            </Container >
        </ThemeProvider >
    );
}
