import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import StudyroomInput from '../components/studyroom/StudyroomInput'
import Review from '../components/studyroom/Review';
import { useEffect, useState } from 'react';
import axios from 'axios';

const steps = ['정보입력', '확인'];
const theme = createTheme();

function getStepContent(step, info, func) {
    switch (step) {
        case 0:
            return <StudyroomInput onButtonClick={func} />
        case 1:
            return <Review props={info} />;

        default:
            throw new Error('Unknown step');
    }
}

export default function Checkout() {
    const [activeStep, setActiveStep] = useState(0);

    const handleNext = () => {
        setActiveStep(activeStep + 1);
    };

    const handleBack = () => {
        setActiveStep(activeStep - 1);
    };

    const [roomInfo, setRoomInfo] = useState(null)
    const handleRoomInfo = (info) => {
        setRoomInfo(info)
    }

    useEffect(() => {
    })

    const createRoom = (event) => {
        // axios.post()
    }

    return (
        <ThemeProvider theme={theme}>
            <CssBaseline />
            <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
                <Paper variant="outlined" sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}>
                    <Typography component="h1" variant="h4" align="center" >
                        스터디룸 만들기
                    </Typography>
                    <Stepper activeStep={activeStep} sx={{ justifyContent: 'end', pt: 3, pb: 5, width: '50 % ' }} alternativeLabel>
                        {steps.map((label) => (
                            <Step key={label} sx={{}}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
                    <>
                        {activeStep === steps.length ? (
                            <Box sx={{ display: 'grid', justifyContent: 'center' }}>
                                <Typography variant="h5" gutterBottom>
                                    스터디룸이 성공적으로 만들어졌습니다!
                                </Typography>
                                <Typography variant="subtitle1">
                                    설명이 들어갈 자리입니다.
                                </Typography>
                                <Button sx={{ mt: 3, ml: 1 }} onClick={createRoom}>
                                    홈으로 돌아가기
                                </Button>

                            </Box>
                        ) : (
                            <>
                                {getStepContent(activeStep, roomInfo, handleRoomInfo)}
                                <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                                    {activeStep !== 0 && (
                                        <Button onClick={handleBack} sx={{ mt: 3, ml: 1 }}>
                                            이전
                                        </Button>
                                    )}

                                    <Button
                                        variant="contained"
                                        onClick={handleNext}
                                        sx={{ mt: 3, ml: 1 }}
                                    >
                                        {activeStep === steps.length - 1 ? '스터디룸 생성하기' : '다음'}
                                    </Button>
                                </Box>
                            </>
                        )}
                    </>
                </Paper>
            </Container >
        </ThemeProvider >
    );
}