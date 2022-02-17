import { Container, CssBaseline, Grid, ThemeProvider, Typography, Card, CardContent, CardMedia, CardActions, Button, createTheme, Link, List, ListItem, ListItemText } from "@mui/material";
import { Box } from "@mui/system";

const theme = createTheme();
const DEFAULT_IMG = [
    "https://images.freeimages.com/images/small-previews/eaf/studying-ahead-1421056.jpg",
    "https://www.how-to-study.com/images/study-skills-assessments.jpg",
    "https://ugc.futurelearn.com/uploads/images/4d/c9/header_4dc9321b-f608-4196-9fb7-02f6c0029a5f.jpg"
]

export default function BeforeEnterRoom() {
    const roomInfo = JSON.parse(localStorage.getItem('roomInfo'))

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="md" sx={{ my: 5 }}>
                <CssBaseline />
                <Grid container spacing={2}>
                    <Grid item xs={12} md={4}>
                        <Card
                            sx={{ display: 'flex', flexDirection: 'column' }}
                        >
                            <CardMedia
                                component="img"
                                image={roomInfo.img ? roomInfo.img : DEFAULT_IMG[roomInfo.id % 3]}
                                sx={{ width: 'auto' }}
                            />
                        </Card>
                    </Grid>
                    {/* <Grid item xs={12} md={8}>
                        <Card
                            sx={{ display: 'flex', flexDirection: 'column' }}
                        >
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography gutterBottom variant="h5" component="h2">
                                    {roomInfo.title}
                                </Typography>
                                <Typography>
                                    {roomInfo.description ? roomInfo.description : "내용이 없습니다."}
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
                    </Grid> */}
                    <Grid item xs={12} md={8}>
                        <Card>
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography variant="h6" gutterBottom>
                                    스터디룸 입장하기
                                </Typography>
                                <List disablePadding>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="스터디룸 이름" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.title}
                                        </Typography>
                                    </ListItem>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="설명" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.description}
                                        </Typography>
                                    </ListItem>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="해시태그" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.studyRoomHashtags.map((item) => (
                                                <span key={item}> {item} </span>
                                            ))}
                                        </Typography>
                                    </ListItem>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="카테고리" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.studyRoomCategories.map((item) => (
                                                <span> {item} </span>
                                            ))}
                                        </Typography>
                                    </ListItem>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="시작일" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.startDate ? roomInfo.startDate.slice(0, 10) : ""}
                                        </Typography>
                                    </ListItem>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="종료일" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.endDate ? roomInfo.endDate.slice(0, 10) : ""}
                                        </Typography>
                                    </ListItem>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="목표시간" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.goalTime}
                                        </Typography>
                                    </ListItem>
                                    <ListItem sx={{ py: 1, px: 0 }}>
                                        <ListItemText primary="최대인원" />
                                        <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                                            {roomInfo.limitPeople}명
                                        </Typography>
                                    </ListItem>
                                </List>
                            </CardContent>
                            <CardActions sx={{
                                display: 'flex', justifyContent: 'end'
                            }} >
                                <Button color="error" size="large" variant="contained">뒤로가기</Button>
                                <Link color="inherit" href="/studyroom" underline="none" >
                                    <Button size="large" variant="contained" color="primary" sx={{ ml: 3 }} >입장하기</Button>
                                </Link>
                            </CardActions>
                        </Card>
                    </Grid>
                </Grid>
            </Container>
        </ThemeProvider >
    )
}