import { Box, Container, CssBaseline, Grid, Typography } from '@mui/material';
import { Profile } from '../components/profile/Profile';
import { ProfileDetail } from '../components/profile/ProfileDetail';


export default function UserProfile({ userInfo, setUserInfo }) {
    console.log("user:", userInfo)

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
                            <Profile userInfo={userInfo} />
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

