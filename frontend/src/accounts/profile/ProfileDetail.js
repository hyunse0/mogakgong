import { useState } from 'react';
import {
    Box,
    Button,
    Card,
    CardContent,
    CardHeader,
    Divider,
    Grid,
    TextField,
    Chip,
    Select,
    InputLabel,
    OutlinedInput,
    MenuItem,
    useTheme
} from '@mui/material';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import AdapterDateFns from '@mui/lab/AdapterDateFns';
import DatePicker from '@mui/lab/DatePicker';

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
    PaperProps: {
        style: {
            maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
            width: 250,
        },
    },
};

const names = [
    'Oliver Hansen',
    'Van Henry',
    'April Tucker',
    'Ralph Hubbard',
    'Omar Alexander',
    'Carlos Abbott',
    'Miriam Wagner',
    'Bradley Wilkerson',
    'Virginia Andrews',
    'Kelly Snyder',
];

function getStyles(name, myCategory, theme) {
    return {
        fontWeight:
            myCategory.indexOf(name) === -1
                ? theme.typography.fontWeightRegular
                : theme.typography.fontWeightMedium,
    };
}

export const ProfileDetail = (props) => {
    const [userInfo, setUserInfo] = useState(props.profile)

    const [myCategory, setMyCategory] = useState([]);
    const theme = useTheme();

    // const handleCategory = (event) => {
    //     console.log(event)
    //     setUserInfo({
    //         ...userInfo,
    //         [event.target.name]: event.target.value
    //     });
    // };

    const handleCategory = (event) => {
        const {
            target: { value },
        } = event;
        setMyCategory(
            typeof value === 'string' ? value.split(',') : value,
        );
    };

    return (
        <form
            autoComplete="off"
            noValidate
            {...props}
        >
            <Card>
                <CardHeader
                    subheader="이곳에서 회원 정보를 수정합니다"
                    title="회원정보 수정"
                />
                <Divider />
                <CardContent>
                    <Grid container spacing={3}>
                        <Grid item md={6} xs={12} >
                            <TextField
                                fullWidth
                                label="닉네임"
                                name="nickname"
                                onChange={(e) => {
                                    setUserInfo((prev) => {
                                        return {
                                            ...prev,
                                            ninckname: e.target.value
                                        }
                                    })
                                    console.log(userInfo)
                                }
                                }
                                value={userInfo.ninckname}
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item md={6} xs={12}>
                            <LocalizationProvider
                                dateAdapter={AdapterDateFns}
                            >
                                <DatePicker
                                    fullWidth
                                    label="생년월일"
                                    value={userInfo.birth}
                                    onChange={e => {
                                        setUserInfo((prev) => {
                                            return {
                                                ...prev,
                                                birth: e.toISOString().slice(0, 10)
                                            }
                                        })
                                    }}
                                    renderInput={(params) => <TextField fullWidth {...params} />}
                                />
                            </LocalizationProvider>
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                label="회원 목표"
                                name="목표"
                                onChange={e => {
                                    setUserInfo(prev => {
                                        return {
                                            ...prev,
                                            goal: e.target.value
                                        }
                                    })
                                }}
                                value={userInfo.goal}
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Select
                                label="카테고리 설정"
                                fullWidth
                                multiple
                                value={myCategory}
                                onChange={handleCategory}
                                input={<OutlinedInput id="select-multiple-chip" label="Chip" />}
                                renderValue={(selected) => (
                                    <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                                        {selected.map((value) => (
                                            <Chip key={value} label={value} />
                                        ))}
                                    </Box>
                                )}
                                MenuProps={MenuProps}
                            >
                                {names.map((name) => (
                                    <MenuItem
                                        key={name}
                                        value={name}
                                        style={getStyles(name, myCategory, theme)}
                                    >
                                        {name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </Grid>
                    </Grid>
                </CardContent>
                <Divider />
                <Box
                    sx={{
                        display: 'flex',
                        justifyContent: 'flex-end',
                        p: 2
                    }}
                >
                    <Button
                        color="primary"
                        variant="contained"
                    >
                        저장하기
                    </Button>
                </Box>
            </Card>
        </form>
    );
};