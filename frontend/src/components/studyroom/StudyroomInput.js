import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Chip from '@mui/material/Chip';
import { Input, Stack } from '@mui/material';
import { useState } from 'react';
import { Box } from '@mui/system';

export default function StudyroomInput() {
    const [hashtag, setHashtag] = useState([])
    const [tag, setTag] = useState('')
    const [imgFile, setImgFile] = useState(null)

    const setImgHandler = async (e) => {
        setImgFile(URL.createObjectURL(e.target.files[0]));
    }

    const handleClick = () => {
        console.info('You clicked the Chip.');
    };

    const handleDelete = (toDelete) => {
        console.info('You clicked the delete icon.');
        setHashtag(hashtag.filter(tag => tag !== toDelete));
    };

    const onCreate = e => {
        setHashtag(enters => [...enters, tag]);
        console.log(hashtag)
        setTag('');
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
                                    onClick={handleClick}
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
                    <TextField
                        required
                        id="city"
                        name="city"
                        label="City"
                        fullWidth
                        autoComplete="shipping address-level2"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        id="state"
                        name="state"
                        label="State/Province/Region"
                        fullWidth
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        required
                        id="zip"
                        name="zip"
                        label="Zip / Postal code"
                        fullWidth
                        autoComplete="shipping postal-code"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        required
                        id="country"
                        name="country"
                        label="Country"
                        fullWidth
                        autoComplete="shipping country"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12}>
                    <Box sx={{ display: 'flex', alignContent: 'center', justifyContent: 'center' }}>
                        <img
                            src=
                            {imgFile ? imgFile
                                : "https://images.freeimages.com/images/small-previews/eaf/studying-ahead-1421056.jpg"} height={254} />
                    </Box>
                    <div>
                        *해당 이미지는 기본 이미지입니다.
                    </div>
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