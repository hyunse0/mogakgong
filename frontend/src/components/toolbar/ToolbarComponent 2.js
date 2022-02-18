import React, { Component } from 'react';
import './ToolbarComponent.css';

import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';

import Mic from '@mui/icons-material/Mic';
import MicOff from '@mui/icons-material/MicOff';
import Videocam from '@mui/icons-material/Videocam';
import VideocamOff from '@mui/icons-material/VideocamOff';
import Fullscreen from '@mui/icons-material/FitScreen';
import FullscreenExit from '@mui/icons-material/FullscreenExit';
import SwitchVideoIcon from '@mui/icons-material/SwitchVideo';
import PictureInPicture from '@mui/icons-material/PictureInPicture';
import ScreenShare from '@mui/icons-material/ScreenShare';
import StopScreenShare from '@mui/icons-material/StopScreenShare';
import Tooltip from '@mui/material/Tooltip';
import PowerSettingsNew from '@mui/icons-material/PowerSettingsNew';
import QuestionAnswer from '@mui/icons-material/QuestionAnswer';
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';

import IconButton from '@mui/material/IconButton';
import { Button, Paper, Typography } from '@mui/material';

export default class ToolbarComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fullscreen: false,
            curTime: 0,
            curTimeStr: '00:00:00',
            isStarted: false,
        };
        this.camStatusChanged = this.camStatusChanged.bind(this);
        this.micStatusChanged = this.micStatusChanged.bind(this);
        this.screenShare = this.screenShare.bind(this);
        this.stopScreenShare = this.stopScreenShare.bind(this);
        this.toggleFullscreen = this.toggleFullscreen.bind(this);
        this.switchCamera = this.switchCamera.bind(this);
        this.leaveSession = this.leaveSession.bind(this);
        this.toggleChat = this.toggleChat.bind(this);


        this.toggleWatch = this.toggleWatch.bind(this);
        this.recordTime = this.recordTime.bind(this);
    }

    toggleWatch() {
        if (!this.state.isStarted) {
            this.setState({
                isStarted: true
            }, () => { this.startTick(); });
        } else {
            this.setState({
                isStarted: false
            }, () => { this.endTick(); });
        }
    }

    recordTime() {
        this.setState({ records: [], curTime: 0, curTimeStr: '00:00:00' })
    }

    startTick() {
        this.tick = setInterval(() => {
            this.setState({ curTime: this.state.curTime + 10, }, () => {
                this.setState({
                    curTimeStr: new Date(this.state.curTime).toISOString().slice(11, 19)
                })
            })
        }, 1)
    }

    endTick() {
        clearInterval(this.tick)
    }


    micStatusChanged() {
        console.log(this.props)
        this.props.micStatusChanged();
    }

    camStatusChanged() {
        this.props.camStatusChanged();
    }

    screenShare() {
        this.props.screenShare();
    }

    stopScreenShare() {
        this.props.stopScreenShare();
    }

    toggleFullscreen() {
        this.setState({ fullscreen: !this.state.fullscreen });
        this.props.toggleFullscreen();
    }

    switchCamera() {
        this.props.switchCamera();
    }

    leaveSession() {
        this.props.leaveSession();
    }

    toggleChat() {
        this.props.toggleChat();
    }

    render() {
        const mySessionId = this.props.sessionId;
        const localUser = this.props.user;
        return (
            <AppBar className="toolbar" id="header">
                <Toolbar className="toolbar">
                    <div id="navSessionInfo">
                        {this.props.sessionId &&
                            <div id="titleContent">
                                <span id="session-title">{mySessionId}</span>
                            </div>}
                    </div>

                    <div className="buttonsContent">

                        {/* 마이크 온오프 */}
                        <IconButton color="inherit" className="navButton" id="navMicButton" onClick={this.micStatusChanged}>
                            {localUser !== undefined && localUser.isAudioActive() ? <Mic /> : <MicOff color="secondary" />}
                        </IconButton>

                        {/* 비디오 캠 온오프 */}
                        <IconButton color="inherit" className="navButton" id="navCamButton" onClick={this.camStatusChanged}>
                            {localUser !== undefined && localUser.isVideoActive() ? (
                                <Videocam />
                            ) : (
                                <VideocamOff color="secondary" />
                            )}
                        </IconButton>

                        {/* 화면공유 */}
                        <IconButton color="inherit" className="navButton" onClick={this.screenShare}>
                            {localUser !== undefined && localUser.isScreenShareActive() ? <PictureInPicture /> : <ScreenShare />}
                        </IconButton>

                        {localUser !== undefined &&
                            localUser.isScreenShareActive() && (
                                <IconButton onClick={this.stopScreenShare} id="navScreenButton">
                                    <StopScreenShare color="secondary" />
                                </IconButton>
                            )}

                        {/* 비디오 전환 */}
                        <IconButton color="inherit" className="navButton" onClick={this.switchCamera}>
                            <SwitchVideoIcon />
                        </IconButton>

                        {/* 전체화면 */}
                        <IconButton color="inherit" className="navButton" onClick={this.toggleFullscreen}>
                            {localUser !== undefined && this.state.fullscreen ? <FullscreenExit /> : <Fullscreen />}
                        </IconButton>

                        {/* 연결 종료 */}
                        <IconButton color="error" className="navButton" onClick={this.leaveSession} id="navLeaveButton">
                            <PowerSettingsNew />
                        </IconButton>

                        {/* 스탑워치 */}
                        <Paper elevation={3} id="stopWatch">
                            <Typography variant='h5'>
                                {this.state.curTimeStr}
                            </Typography>
                            <Button variant="outlined" onClick={this.toggleWatch} sx={{ margin: 1 }}>
                                {this.state.isStarted ? "공부 잠시 멈추기" : "공부 시작하기"}
                            </Button>
                            <Button variant="outlined" color="error" onClick={this.recordTime} sx={{ margin: 1 }}>공부 시간 기록</Button>
                        </Paper>

                        {/* 스터디플래너 */}
                        <IconButton color="primary" id="navCalenderButton">
                            <Tooltip title="스터디 플래너">
                                <CalendarTodayIcon />
                            </Tooltip>
                        </IconButton>

                        {/* 채팅 */}
                        <IconButton color="primary" onClick={this.toggleChat} id="navChatButton">
                            {this.props.showNotification && <div id="point" className="" />}
                            <Tooltip title="채팅">
                                <QuestionAnswer />
                            </Tooltip>
                        </IconButton>

                    </div>
                </Toolbar>
            </AppBar>
        );
    }
}
