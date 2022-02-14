import { useState } from 'react';
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
import axios from 'axios';

const { faker } = require('@faker-js/faker');
// faker.locale = "ko"
const studyrooms = [...Array(10)].map((_, idx) => ({
    title: faker.lorem.word(),
    nickname: faker.name.firstName(),
    category: faker.lorem.word(),
    description: faker.lorem.lines(),
    hashtag: faker.lorem.word(),
    start_date: faker.datatype.datetime(),
    end_date: faker.datatype.datetime(),
    limit: Math.random(0, 1) * 10,
    img: faker.image.image(),
}))
const theme = createTheme();

export default function Main() {
    // const [studyrooms, setStudyroom] = useState({
    //     axios.get()
    // })

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
                    <Grid item xs={12} sm={6}>
                        <Typography m={2} variant='h6'>내 목표</Typography>
                        <Grid
                            container
                            justifyContent="center"
                            alignItems="center">
                            <ProgressBar props={65} m={3}></ProgressBar>
                        </Grid>
                    </Grid>
                    {/* 내 랭킹 */}
                    <Grid item xs={12} sm={6}>
                        <Typography mt={2} variant='h6'>내 랭킹</Typography>
                    </Grid>
                    <Grid item xs={12}>
                        {/* 내 스터디 */}
                        <Typography mt={3} variant='h6' >내 스터디</Typography>
                        <ImageList cols={4} >
                            {studyrooms.map((item) => (
                                <Card key={item.hashtag}>
                                    <ImageListItem >
                                        <img
                                            src={`${item.img}`}
                                            srcSet={`${item.img}`}
                                            alt={item.title}
                                            loading="lazy"
                                        />
                                        <ImageListItemBar
                                            title={item.title}
                                            subtitle={item.hashtag}
                                            actionIcon={
                                                <IconButton
                                                    sx={{ color: 'rgba(255, 255, 255, 0.54)' }}
                                                    aria-label={`info about ${item.title}`}

                                                >
                                                    <InfoIcon />
                                                </IconButton>
                                            }
                                        />
                                    </ImageListItem>
                                </Card>
                            ))}
                        </ImageList>

                    </Grid>

                    <Grid item xs={12} >
                        {/* 추천 스터디 */}
                        <Typography mt={2} variant='h6'>추천 스터디</Typography>
                        <ImageList cols={4} >
                            {studyrooms.map((item) => (
                                <Card key={item.hashtag}>
                                    <ImageListItem >
                                        <img
                                            src={`${item.img}`}
                                            srcSet={`${item.img}`}
                                            alt={item.title}
                                            loading="lazy"
                                        />
                                        <ImageListItemBar
                                            title={item.title}
                                            subtitle={item.hashtag}
                                            actionIcon={
                                                <IconButton
                                                    sx={{ color: 'rgba(255, 255, 255, 0.54)' }}
                                                    aria-label={`info about ${item.title}`}
                                                >
                                                    <Link
                                                        color="inherit"
                                                        href="/beforestudy"
                                                        onClick={() => {
                                                            saveRoomInfo(item)
                                                        }}
                                                    >
                                                        <InfoIcon />
                                                    </Link>
                                                </IconButton>
                                            }
                                        />
                                    </ImageListItem>
                                </Card>
                            ))}
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
