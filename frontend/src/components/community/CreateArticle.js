import { useState } from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DatePicker from '@mui/lab/DatePicker';
import Input from '@mui/material/Input';
import axios from 'axios';

const theme = createTheme();

export default function SignUp() {
    const [title, setTitle] = useState('')
    const [content, setContent] = useState('')
    const [img, setImg] = useState(null)

    const setImgHandler = async (e) => {
        setImg(URL.createObjectURL(e.target.files[0]));
    }

    const handleSubmit = () => {

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
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    label="Titlel"
                                    value={title}
                                    onChange={(e) => setTitle(e.target.value)}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Box sx={{ display: 'flex', alignContent: 'center', justifyContent: 'center' }}>
                                    <img src={img ? img : null} height={254} />
                                </Box>
                            </Grid>
                            <Grid item xs={12}>
                                <Input
                                    type="file"
                                    onChange={setImgHandler}
                                    accept="image/*"
                                    fullWidth
                                />
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}