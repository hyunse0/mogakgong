import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import { Box } from '@mui/material';

const DEFAULT_IMG = "https://images.freeimages.com/images/small-previews/eaf/studying-ahead-1421056.jpg"

export default function Review({ props }) {
    console.log(props)
    return (
        <>
            <Typography variant="h6" gutterBottom>
                생성 할 스터디룸 정보
            </Typography>
            <List disablePadding>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="스터디룸 이름" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.title}
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="비밀번호" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.password}
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="설명" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.description}
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="해시태그" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.hashtag}
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="시작일" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.start}
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="종료일" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.end}
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="목표시간" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.goalTime}
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="최대인원" />
                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        {props.maxPeople}명
                    </Typography>
                </ListItem>
                <ListItem sx={{ py: 1, px: 0 }}>
                    <ListItemText primary="이미지" />
                    <Box sx={{ display: 'flex', alignContent: 'center', justifyContent: 'center' }}>
                        <img src={props.imgFile ? props.imgFile : DEFAULT_IMG} height={254} />
                    </Box>
                </ListItem>
            </List>
        </>
    );
}