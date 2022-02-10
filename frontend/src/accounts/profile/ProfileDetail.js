import { useState } from 'react';
import {
    Box,
    Button,
    Card,
    CardContent,
    CardHeader,
    Divider,
    Grid,
    TextField
} from '@mui/material';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import DatePicker from '@mui/lab/DatePicker';

export const ProfileDetail = (props) => {
    const [userInfo, setUserInfo] = useState(props.profile)

    const handleChange = (event) => {
        console.log(event)
        setUserInfo({
            ...userInfo,
            [event.target.name]: event.target.value
        });
    };

    return (
        <form
            autoComplete="off"
            noValidate
            {...props}
        >
            <Card>
                <CardHeader
                    subheader="이곳에서 회원 정보를 수정합니다"
                    title="회원정보 수정"
                />
                <Divider />
                <CardContent>
                    <Grid
                        container
                        spacing={3}
                    >
                        <Grid
                            item
                            md={6}
                            xs={12}
                        >
                            <TextField
                                fullWidth
                                label={userInfo.ninckname}
                                name="nickname"
                                onChange={handleChange}
                                value={userInfo.ninckname}
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item md={6} xs={12}>
                            <LocalizationProvider
                                dateAdapter={AdapterDateFns}
                            >
                                <DatePicker
                                    fullWidth
                                    label="Birth"
                                    value={userInfo.birth}
                                    onChange={handleChange}
                                    renderInput={(params) => <TextField fullWidth {...params} />}
                                />
                            </LocalizationProvider>
                        </Grid>
                        <Grid item md={6} xs={12}>
                            <TextField
                                fullWidth
                                label="Email"
                                name="email"
                                onChange={handleChange}
                                disabled
                                value={userInfo.email}
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item md={6} xs={12}>
                            <TextField
                                fullWidth
                                label="Phone Number"
                                name="phone"
                                onChange={handleChange}
                                type="number"
                                value={userInfo.phone}
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item md={6} xs={12}>
                            <TextField
                                fullWidth
                                label="Country"
                                name="country"
                                onChange={handleChange}
                                required
                                value={userInfo.country}
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item md={6} xs={12}>
                            <TextField
                                fullWidth
                                label="Select State"
                                name="state"
                                onChange={handleChange}
                                required
                                select
                                SelectProps={{ native: true }}
                                value={userInfo.state}
                                variant="outlined"
                            >

                            </TextField>
                        </Grid>
                    </Grid>
                </CardContent>
                <Divider />
                <Box
                    sx={{
                        display: 'flex',
                        justifyContent: 'flex-end',
                        p: 2
                    }}
                >
                    <Button
                        color="primary"
                        variant="contained"
                    >
                        저장하기
                    </Button>
                </Box>
            </Card>
        </form>
    );
};