import { Box, Container, CssBaseline, Grid, Typography } from '@mui/material';
import axios from 'axios';
import { useEffect } from 'react';
import { Profile } from './profile/Profile';
import { ProfileDetail } from './profile/ProfileDetail';


export default function Account({ userInfo, setUserInfo }) {
    console.log(userInfo)

    useEffect(() => {
        axios.get("http://i6c204.p.ssafy.io:8081/api/member", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
            .then(res => {
                console.log(res.data.member)
                setUserInfo(res.data.member)
            })
            .catch(err => {
                console.log(err)
            })
    }, [])

    return (
        <>
            <CssBaseline />
            <Box
                component="main"
                sx={{
                    flexGrow: 1,
                    py: 8
                }}
            >
                <Container maxWidth="lg">
                    <Typography
                        sx={{ mb: 3 }}
                        variant="h4"
                    >
                        프로필
                    </Typography>
                    <Grid
                        container
                        spacing={3}
                    >
                        <Grid item lg={4} md={6} xs={12}>
                            <Profile profile={userInfo} />
                        </Grid>
                        <Grid item lg={8} md={6} xs={12}>
                            <ProfileDetail userInfo={userInfo} setUserInfo={setUserInfo} />
                        </Grid>
                    </Grid>
                </Container>
            </Box>
        </>
    )
};