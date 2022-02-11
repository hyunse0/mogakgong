import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Chip from '@mui/material/Chip';
import { Input, InputLabel, Stack } from '@mui/material';
import { useEffect, useState } from 'react';
import { Box } from '@mui/system';
import Slider from '@mui/material/Slider';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import DatePicker from '@mui/lab/DatePicker';
import AdapterDateFns from '@mui/lab/AdapterDateFns';

const DEFAULT_IMG = "https://images.freeimages.com/images/small-previews/eaf/studying-ahead-1421056.jpg"

export default function StudyroomInput({ onButtonClick }) {
    const [title, setTitle] = useState('')
    const [password, setPassword] = useState('')
    const [description, setDescription] = useState('')
    const [hashtag, setHashtag] = useState([])
    const [start, setStart] = useState(null)
    const [end, setEnd] = useState(null)
    const [goalTime, setGoalTime] = useState(0)
    const [imgFile, setImgFile] = useState(null)
    const [maxPeople, setMaxPeople] = useState(0)

    const [tag, setTag] = useState('')

    useEffect(() => {
        onButtonClick({
            title,
            password,
            description,
            hashtag,
            start,
            end,
            goalTime,
            imgFile,
            maxPeople
        })
    }, [title, password, description, hashtag, start, end, goalTime, imgFile, maxPeople])

    const setImgHandler = async (e) => {
        setImgFile(URL.createObjectURL(e.target.files[0]));
    }

    const handleDelete = (toDelete) => {
        // console.info('You clicked the delete icon.');
        setHashtag(hashtag.filter(tag => tag !== toDelete));
    };

    const onCreate = e => {
        setHashtag(enters => [...enters, tag]);
        // console.log(hashtag)
        setTag('');
    };

    const handleSliderChange = (event, newValue) => {
        setMaxPeople(newValue);
    };

    const handleInputChange = (event) => {
        setMaxPeople(event.target.value === '' ? '' : Number(event.target.value));
    };

    const handleBlur = () => {
        if (maxPeople < 0) {
            setMaxPeople(0);
        } else if (maxPeople > 10) {
            setMaxPeople(10);
        }
    };

    return (
        <>
            <Typography variant="h6" gutterBottom>
                스터디룸 정보의 정보를 입력하세요
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} sm={6}>
                    <TextField
                        required
                        id="title"
                        name="title"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        label="스터디 이름을 입력하세요"
                        fullWidth
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        required
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        label="비밀번호를 입력하세요"
                        fullWidth
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        required
                        id="description"
                        name="description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        label="스터디에 대해 설명해주세요!"
                        fullWidth
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12}>
                    {hashtag ?
                        <Stack direction="row" spacing={1}>
                            {hashtag.map((tag, index) => (
                                <Chip
                                    key={index}
                                    label={tag}
                                    onDelete={() => handleDelete(tag)}
                                />
                            ))}
                        </Stack> : null}

                    <TextField
                        id="hashtag"
                        name="hashtag"
                        label="해쉬태그를 입력하고 Enter를 누르세요!"
                        fullWidth
                        variant="standard"
                        value={tag}
                        onChange={(e) => {
                            setTag(e.target.value)
                            console.log(e.target.value)
                        }}
                        onKeyPress={(e) => {
                            if (e.key === 'Enter') {
                                e.preventDefault()
                                onCreate()
                            }
                        }}
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <LocalizationProvider
                        dateAdapter={AdapterDateFns}
                    >
                        <DatePicker
                            fullWidth
                            label="시작날짜"
                            value={start}
                            onChange={(newValue) => {
                                setStart(newValue.toISOString().slice(0, 10))
                            }}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                </Grid>
                <Grid item xs={12} sm={6}>
                    <LocalizationProvider
                        dateAdapter={AdapterDateFns}
                    >
                        <DatePicker
                            fullWidth
                            label="종료날짜"
                            value={end}
                            onChange={(newValue) => {
                                setEnd(newValue.toISOString().slice(0, 10))
                            }}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        id="goalTime"
                        name="goalTime"
                        value={goalTime}
                        onChange={(e) => setGoalTime(e.target.value)}
                        label="목표시간"
                        fullWidth
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <Grid container spacing={2} alignItems="center">
                        <Grid item xs>
                            <InputLabel shrink>인원 수를 정하세요</InputLabel>
                            <Slider value={maxPeople} onChange={handleSliderChange} defaultValue={0} aria-label="Default" valueLabelDisplay="auto" max={10} marks />
                        </Grid>
                        <Grid item>
                            <Input
                                value={maxPeople}
                                size="small"
                                onChange={handleInputChange}
                                onBlur={handleBlur}
                                inputProps={{
                                    step: 1,
                                    min: 0,
                                    max: 10,
                                    type: 'number',
                                    'aria-labelledby': 'input-slider',
                                }}
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item xs={12}>
                    <Box sx={{ display: 'flex', alignContent: 'center', justifyContent: 'center' }}>
                        <img
                            src=
                            {imgFile ? imgFile : DEFAULT_IMG} height={254} />
                    </Box>
                    {!imgFile ? <div>*해당 이미지는 기본 이미지입니다.</div> : null}
                </Grid>
                <Grid item xs={12}>
                    <Input
                        type="file"
                        onChange={setImgHandler}
                        accept="image/*"
                        fullWidth
                    />
                </Grid>
            </Grid>
        </>
    );
}