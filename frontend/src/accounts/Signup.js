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
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DatePicker from '@mui/lab/DatePicker';

const theme = createTheme();

export default function SignUp() {
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        // eslint-disable-next-line no-console
        // console.log({
        //     email: data.get('email'),
        //     password: data.get('password'),
        // });
        console.log(data)
    };

    // email & password valid check regular expression
    // @와 .이 들어가야함
    const emailReg = new RegExp(/[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9.]+/)
    // 문자, 숫자, 특수기호가 하나 이상 들어가야함
    const passwordReg = new RegExp(/(?=.*\d{1,50})(?=.*[~`!@#$%^&*()-+=]{1,50})(?=.*[a-zA-Z]{1,50}).{8,50}$/)


    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [passwordConfirm, setPasswordConfirm] = useState('')
    const [nickname, setNickname] = useState('')
    const [birth, setBirth] = useState(null);

    const [emailCheck, setEmailCheck] = useState(true)
    const [passwordCheck, setpasswordCheck] = useState(true)
    const [passwordConfirmCheck, setpasswordConfirmCheck] = useState(true)


    const setEmailHandler = (e) => {
        setEmail(e.target.value)
        emailReg.test(email) ? setEmailCheck(true) : setEmailCheck(false)
    }

    const setNicknameHandler = (e) => {
        setNickname(e.target.value)
    }

    const setPasswordHandler = (e) => {
        setPassword(e.target.value)
        passwordReg.test(password) ? setpasswordCheck(true) : setpasswordCheck(false)
    }

    const setPasswordConfirmHandler = (e) => {
        setPasswordConfirm(e.target.value)
        password === passwordConfirm ? setpasswordConfirmCheck(false) : setpasswordConfirmCheck(true)
    }

    const signUp = () => {
        console.log('signup!')
        const userInfo = {
            email: email,
            password: password,
            nickname: nickname,
        }
    }

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
                        Sign up form
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
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
                                    error={!passwordCheck}
                                    helperText={!passwordCheck ? "문자와 숫자, 특수기호가 각각 1개 이상 포함되어야합니다." : null}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    label="Password Confirm"
                                    value={passwordConfirm}
                                    onChange={setPasswordConfirmHandler}
                                    error={!passwordConfirmCheck}
                                    helperText={!passwordConfirmCheck ? "입력한 비밀번호가 일치하지 않습니다!" : null}
                                />
                            </Grid>

                            <Grid item xs={12} sm={6}>
                                <TextField

                                    fullWidth
                                    label="Nickname"
                                    placeholder={email.slice(0, email.indexOf('@'))}
                                    value={nickname}
                                    onChange={setNicknameHandler}
                                />
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <LocalizationProvider
                                    dateAdapter={AdapterDateFns}
                                >
                                    <DatePicker
                                        fullWidth
                                        label="Birth"
                                        value={birth}
                                        onChange={(newValue) => {
                                            setBirth(newValue);
                                        }}
                                        renderInput={(params) => <TextField {...params} />}
                                    />
                                </LocalizationProvider>
                            </Grid>
                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            가입하기
                        </Button>
                        <Grid container justifyContent="flex-end">
                            <Grid item>
                                <Link href="/login" variant="body2">
                                    이미 계정이 있으신가요? 로그인하기
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}