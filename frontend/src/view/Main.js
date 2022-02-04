import { useState } from 'react';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import { styled } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
// import { Box } from '@mui/system';
// import Button from '@mui/material/Button';
import SpeedDial from '@mui/material/SpeedDial';
import { Backdrop, Card, SpeedDialAction, SpeedDialIcon, Typography } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import AddIcon from '@mui/icons-material/Add';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import ImageListItemBar from '@mui/material/ImageListItemBar';
// import ListSubheader from '@mui/material/ListSubheader';
import IconButton from '@mui/material/IconButton';
import InfoIcon from '@mui/icons-material/Info';
import { createTheme, ThemeProvider } from '@mui/material/styles';
// import AppBar from '../components/AppBar';

const theme = createTheme();

const itemData = [
    {
        img: 'https://images.unsplash.com/photo-1551963831-b3b1ca40c98e',
        title: 'Breakfast',
        author: '@bkristastucchio',
        featured: true,
    },
    {
        img: 'https://images.unsplash.com/photo-1551782450-a2132b4ba21d',
        title: 'Burger',
        author: '@rollelflex_graphy726',
    },
    {
        img: 'https://images.unsplash.com/photo-1522770179533-24471fcdba45',
        title: 'Camera',
        author: '@helloimnik',
    },

];

export default function Main() {
    const [open, setOpen] = useState(false);
    const handleClose = () => {
        setOpen(false);
    };
    const handleToggle = () => {
        setOpen(!open);
    };

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="lg">
                <CssBaseline />
                <Grid container spacing={1}>
                    <Grid item xs={12} sm={6}>
                        {/* 내 스터디 */}

                        <Typography mt={3}>내 스터디</Typography>
                        <ImageList sx={{ height: 300 }} >
                            {
                                itemData.map((item) => (

                                    <ImageListItem key={item.img} >

                                        <img
                                            src={`${item.img}?w=248&fit=crop&auto=format`}
                                            srcSet={`${item.img}?w=248&fit=crop&auto=format&dpr=2 2x`}
                                            alt={item.title}
                                            loading="lazy"
                                        />
                                        <ImageListItemBar
                                            title={item.title}
                                            subtitle={item.author}
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

                                ))
                            }
                        </ImageList>


                    </Grid>
                    <Grid item xs={12} sm={6}>
                        {/* 내 목표 */}
                        <Grid item xs={12}>
                            <Typography mt={2}>내 목표</Typography>
                        </Grid>
                        <Grid item xs={12}>
                            <Typography mt={2}>내 랭킹</Typography>
                        </Grid>
                    </Grid>
                    <Grid item xs={12}>
                        {/* 추천 스터디 */}
                        <Typography mt={2}>추천 스터디</Typography>
                        <ImageList cols={4} >
                            {itemData.map((item) => (
                                <Card>
                                    <Backdrop

                                        open={open}
                                        onClick={handleClose}>
                                        {item.title}
                                    </Backdrop>
                                    <ImageListItem key={item.img}>
                                        <img
                                            src={`${item.img}`}
                                            srcSet={`${item.img}`}
                                            alt={item.title}
                                            loading="lazy"
                                        />
                                        <ImageListItemBar
                                            title={item.title}
                                            subtitle={item.author}
                                            actionIcon={
                                                <IconButton
                                                    sx={{ color: 'rgba(255, 255, 255, 0.54)' }}
                                                    aria-label={`info about ${item.title}`}
                                                    onClick={handleToggle}
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
                        icon={<AddIcon />}
                        tooltipTitle={"새로운 스터디 만들기"}
                    />
                </SpeedDial>
            </Container >
        </ThemeProvider>
    );
}