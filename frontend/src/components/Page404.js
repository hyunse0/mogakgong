import { Box, Container, CssBaseline, Typography } from '@mui/material';

const NotFound = () => (
    <>
        <CssBaseline />
        <Box
            component="main"
            sx={{
                alignItems: 'center',
                display: 'flex',
                flexGrow: 1,
                minHeight: '100%'
            }}
        >
            <Container maxWidth="md">
                <Box
                    sx={{
                        alignItems: 'center',
                        display: 'flex',
                        flexDirection: 'column'
                    }}
                >
                    <Typography
                        align="center"
                        color="textPrimary"
                        variant="h1"
                    >
                        현재<br />
                        개발중인<br />
                        화면입니다
                    </Typography>
                </Box>
            </Container>
        </Box>
    </>
);

export default NotFound;