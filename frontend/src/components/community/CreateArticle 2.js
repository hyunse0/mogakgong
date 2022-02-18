import { useEffect, useState } from 'react';
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
import TextareaAutosize from '@mui/material/TextareaAutosize';
import axios from 'axios';
import { Typography } from '@mui/material';
import { grey } from '@mui/material/colors';

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

    // useEffect(() => {
    //     console.log(content)
    // }, [content])

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Typography variant='h6' sx={{ mt: 3 }}>
                    스터디원 모집하기
                </Typography>
                <Box
                    sx={{
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
                                    label="Title"
                                    value={title}
                                    onChange={(e) => setTitle(e.target.value)}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextareaAutosize
                                    minRows={20}
                                    placeholder="설명을 작성해주세요"
                                    style={{ width: '100%' }}
                                    value={content}
                                    onChange={e => setContent(e.target.value)}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Box sx={{ display: 'flex', alignContent: 'center', justifyContent: 'center' }}>
                                    {img ? <img src={img} height={254} /> : null}
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
                            <Grid item xs={12} sm={6}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    sx={{ mt: 3, mb: 2 }}
                                // color={grey[50]}
                                >
                                    취소
                                </Button>
                            </Grid>
                            <Grid item xs={12} sm={6}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    sx={{ mt: 3, mb: 2 }}
                                >
                                    작성하기
                                </Button>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider >
    );
}