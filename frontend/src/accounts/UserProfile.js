import { Box, Container, CssBaseline, Grid, Typography } from '@mui/material';
import { useState } from 'react';
import { Profile } from './profile/Profile';
import { ProfileDetail } from './profile/ProfileDetail';

const { faker } = require('@faker-js/faker');
faker.locale = "ko"
const profile = {
    email: faker.internet.email(),
    ninckname: faker.internet.userName(),
    category: faker.lorem.word(),
    goal: faker.lorem.lines(),
    birth: faker.datatype.datetime(),
    img: faker.image.image(),
}

export default function Account() {
    const [userInfo, setUserInfo] = useState(profile)

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
                            <ProfileDetail profile={userInfo} />
                        </Grid>
                    </Grid>
                </Container>
            </Box>
        </>
    )
};