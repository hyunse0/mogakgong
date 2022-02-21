import React, { Component } from 'react';
import './StreamComponent.css';
import OvVideoComponent from './OvVideo';

import MicOff from '@mui/icons-material/MicOff';
import VideocamOff from '@mui/icons-material/VideocamOff';
import VolumeUp from '@mui/icons-material/VolumeUp';
import VolumeOff from '@mui/icons-material/VolumeOff';
import IconButton from '@mui/material/IconButton';

export default class StreamComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            nickname: this.props.user.getNickname(),
            showForm: false,
            mutedSound: false,
            isFormValid: true
        };
        this.handleChange = this.handleChange.bind(this);
        this.handlePressKey = this.handlePressKey.bind(this);
        this.toggleNicknameForm = this.toggleNicknameForm.bind(this);
        this.toggleSound = this.toggleSound.bind(this);
    }

    handleChange(event) {
        this.setState({ nickname: event.target.value });
        event.preventDefault();
    }

    toggleNicknameForm() {
        if (this.props.user.isLocal()) {
            this.setState({ showForm: !this.state.showForm });
        }
    }

    toggleSound() {
        this.setState({ mutedSound: !this.state.mutedSound });
    }

    handlePressKey(event) {
        if (event.key === 'Enter') {
            console.log(this.state.nickname);
            if (this.state.nickname.length >= 3 && this.state.nickname.length <= 20) {
                this.props.handleNickname(this.state.nickname);
                this.toggleNicknameForm();
                this.setState({ isFormValid: true });
            } else {
                this.setState({ isFormValid: false });
            }
        }
    }

    render() {
        return (
            <div className="OT_widget-container">

                {/* Nickname 부분 */}
                <div className="pointer nickname">
                    <span id="nickname">{this.props.user.getNickname()}</span>
                </div>

                {this.props.user !== undefined && this.props.user.getStreamManager() !== undefined ? (
                    <div className="streamComponent">
                        <OvVideoComponent user={this.props.user} mutedSound={this.state.mutedSound} />
                        <div id="statusIcons">
                            {!this.props.user.isVideoActive() ? (
                                <div id="camIcon">
                                    <VideocamOff id="statusCam" />
                                </div>
                            ) : null}

                            {!this.props.user.isAudioActive() ? (
                                <div id="micIcon">
                                    <MicOff id="statusMic" />
                                </div>
                            ) : null}
                        </div>
                        <div>
                            {!this.props.user.isLocal() && (
                                <IconButton id="volumeButton" onClick={this.toggleSound}>
                                    {this.state.mutedSound ? <VolumeOff color="secondary" /> : <VolumeUp />}
                                </IconButton>
                            )}
                        </div>
                    </div>
                ) : null}
            </div>
        );
    }
}
