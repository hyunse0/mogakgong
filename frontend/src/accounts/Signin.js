import { useState } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';

const theme = createTheme();

export default function SignIn() {

    const emailReg = new RegExp(/[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9.]+/)

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const [emailCheck, setEmailCheck] = useState(true)

    const setEmailHandler = (e) => {
        setEmail(e.target.value)
        emailReg.test(email) ? setEmailCheck(true) : setEmailCheck(false)
    }

    const setPasswordHandler = (e) => {
        setPassword(e.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        // console.log(event.currentTarget)
        // const data = new FormData(event.currentTarget);
        // eslint-disable-next-line no-console
        const userInput = {
            email: email,
            password: password
        }
        axios.post("/login", userInput)
            .then(((res) => {
                console.log(res)
            }))
            .catch((err) => {
                console.log(err)
            })
    };

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        mt: 3,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Sign in
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    label="Email"
                                    value={email}
                                    onChange={setEmailHandler}
                                    error={!emailCheck}
                                    helperText={!emailCheck ? "올바른 이메일을 입력하세요" : null}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    label="Password"
                                    value={password}
                                    onChange={setPasswordHandler}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                >
                                    로그인하기
                                </Button>
                            </Grid>
                            <Grid item xs>
                                <Link href="#" variant="body2">
                                    비밀번호를 잊으셨나요?
                                </Link>
                            </Grid>
                            <Grid item>
                                <Link href="/join" variant="body2">
                                    {"모각공 회원이 아니신가요? 회원가입 하기"}
                                </Link>
                            </Grid>
                        </Grid>

                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}