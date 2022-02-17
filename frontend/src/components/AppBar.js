import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import { Button, Typography, Link } from '@mui/material';
import { createTheme } from '@mui/material/styles';
import { useEffect } from 'react';
// import { useState } from 'react';

export default function CustomAppBar({ userInfo, setUserInfo }) {
    const logout = (e) => {
        // e.preventDefault();
        localStorage.removeItem('token')
        setUserInfo()
    }

    useEffect(() => {
        setUserInfo()
    }, [])

    return (
        <AppBar
            position="static"
            elevation={0}
            style={{ backgroundColor: '#0c151c' }}
        >
            <Toolbar sx={{ flexWrap: 'wrap' }}>
                <Typography variant="h4" color="inherit" noWrap sx={{ flexGrow: 1 }} >
                    <Link href="/" color="inherit" underline="none">
                        모각공
                    </Link>
                </Typography>
                <nav>
                    <Link
                        variant="button"
                        color="inherit"
                        href="community"
                        sx={{ my: 1, mx: 1.5 }}
                    >
                        커뮤니티
                    </Link>
                    <Link
                        variant="button"
                        color="inherit"
                        href="/studyroom"
                        sx={{ my: 1, mx: 1.5 }}
                    >
                        스터디룸
                    </Link>
                    <Link
                        variant="button"
                        color="inherit"
                        href="/profile"
                        sx={{ my: 1, mx: 1.5 }}
                    >
                        프로필
                    </Link>
                </nav>
                {userInfo ?
                    <Button onClick={logout} variant="contained" color="primary" href="/login" sx={{ my: 1, mx: 1.5 }}>
                        로그아웃
                    </Button>
                    : <Button variant="contained" color="primary" href="/login" sx={{ my: 1, mx: 1.5 }}>
                        로그인
                    </Button>
                }

            </Toolbar>
        </AppBar>
    )
}