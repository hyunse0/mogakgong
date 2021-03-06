import { Container, CssBaseline, Grid, ThemeProvider, Typography, Card, CardContent, CardMedia, CardActions, Button, createTheme, Link } from "@mui/material";

const theme = createTheme();

export default function BeforeEnterRoom({ article }) {

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="md" sx={{ my: 5 }}>
                <CssBaseline />
                <Grid item xs={12}>
                    <Card
                        sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                    >
                        <CardMedia
                            component="img"
                            image={article.img}
                            alt="random"
                        />
                        <CardContent sx={{ flexGrow: 1 }}>
                            <Typography gutterBottom variant="h5" component="h2">
                                {article.title}
                            </Typography>
                            <Typography>
                                {article.description}
                            </Typography>
                        </CardContent>
                        <CardActions sx={{
                            display: 'flex', justifyContent: 'end'
                        }} >
                            <Button color="error" >뒤로가기</Button>
                            <Link color="inherit" href="/studyroom">
                                <Button >입장하기</Button>
                            </Link>
                        </CardActions>
                    </Card>
                </Grid>
            </Container>
        </ThemeProvider >
    )
}