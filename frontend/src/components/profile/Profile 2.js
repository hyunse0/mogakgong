import {
    Avatar,
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    Divider,
    Typography
} from '@mui/material';
import axios from 'axios';
import { useEffect } from 'react';

export const Profile = ({ userInfo, setUserInfo }) => {
    console.log(userInfo)

    return (
        <Card>
            <CardContent>
                <Box
                    sx={{
                        alignItems: 'center',
                        display: 'flex',
                        flexDirection: 'column'
                    }}
                >
                    <Avatar
                        src={userInfo ? userInfo.img : ''}
                        sx={{
                            height: 64,
                            mb: 2,
                            width: 64
                        }}
                    />
                    <Typography
                        color="textPrimary"
                        gutterBottom
                        variant="h5"
                    >
                        {userInfo ? userInfo.email : ''}
                    </Typography>
                    <Typography
                        color="textSecondary"
                        variant="body2"
                    >
                        {userInfo ? userInfo.nickname : ''}
                    </Typography>
                </Box>
            </CardContent>
            <Divider />
            <CardActions>
                <Button
                    color="primary"
                    fullWidth
                    variant="text"
                >
                    사진 업로드하기
                </Button>
            </CardActions>
        </Card>
    )
};