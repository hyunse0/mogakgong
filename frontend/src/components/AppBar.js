import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
// import { useState } from 'react';
import { Button, Typography, Link } from '@mui/material';


export default function CustomAppBar() {
    return (
        <AppBar
            position="static"
            color="default"
            elevation={0}
            sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}
        >
            <Toolbar sx={{ flexWrap: 'wrap' }}>
                <Typography variant="h4" color="inherit" noWrap sx={{ flexGrow: 1 }}>
                    모각공
                </Typography>
                <nav>
                    <Link
                        to="#"
                        variant="button"
                        color="text.primary"
                        href="#"
                        sx={{ my: 1, mx: 1.5 }}
                    >
                        커뮤니티
                    </Link>
                    <Link
                        variant="button"
                        color="text.primary"
                        href="/studyroom"
                        sx={{ my: 1, mx: 1.5 }}
                    >
                        스터디룸
                    </Link>
                    <Link
                        variant="button"
                        color="text.primary"
                        href="/profile"
                        sx={{ my: 1, mx: 1.5 }}
                    >
                        프로필
                    </Link>
                </nav>
                <Button href="/login" variant="outlined" sx={{ my: 1, mx: 1.5 }}>
                    로그인
                </Button>
            </Toolbar>
        </AppBar>
    )
}