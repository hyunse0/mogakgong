import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Toolbar from '@mui/material/Toolbar';
import Paper from '@mui/material/Paper';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import StudyroomInput from '../components/studyroom/StudyroomInput'
import Review from '../components/studyroom/Review';

const steps = ['정보입력', '확인'];

function getStepContent(step) {
    switch (step) {
        case 0:
            return <StudyroomInput />
        case 1:
            return <Review />
        default:
            throw new Error('Unknown step')
    }
}

const theme = createTheme();

export default function Checkout() {
    const [activeStep, setActiveStep] = React.useState(0);

    const handleNext = () => {
        setActiveStep(activeStep + 1);
    };

    const handleBack = () => {
        setActiveStep(activeStep - 1);
    };

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
                    <React.Fragment>
                        {activeStep === steps.length ? (
                            <React.Fragment>
                                <Typography variant="h5" gutterBottom>
                                    스터디룸이 성공적으로 만들어졌습니다!
                                </Typography>
                                <Typography variant="subtitle1">
                                    설명이 들어갈 자리입니다.
                                </Typography>
                                <Button sx={{ mt: 3, ml: 1 }}>
                                    홈으로 돌아가기
                                </Button>
                            </React.Fragment>
                        ) : (
                            <React.Fragment>
                                {getStepContent(activeStep)}
                                <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                                    {activeStep !== 0 && (
                                        <Button onClick={handleBack} sx={{ mt: 3, ml: 1 }}>
                                            Back
                                        </Button>
                                    )}

                                    <Button
                                        variant="contained"
                                        onClick={handleNext}
                                        sx={{ mt: 3, ml: 1 }}
                                    >
                                        {activeStep === steps.length - 1 ? 'Place order' : 'Next'}
                                    </Button>
                                </Box>
                            </React.Fragment>
                        )}
                    </React.Fragment>
                </Paper>
            </Container>
        </ThemeProvider >
    );
}