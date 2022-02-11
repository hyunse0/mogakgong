import { Container, CssBaseline, Grid, ThemeProvider, Typography, Card, CardContent } from "@mui/material";
import { styled } from '@mui/material/styles';


export default function StudyroomDetail(props) {
    console.log(props.info)
    return (
        <ThemeProvider>
            <Container component="main" maxWidth="lg">
                <CssBaseline />


            </Container>
        </ThemeProvider>
    )
}