# ✏ 모각공

SSAFY 광주 2반 공통 프로젝트 4팀

**팀장** *1대 팀장 김성현* → 2대 팀장 김일환

**팀원** 김일환 심은조 정우철 하현서



### 프로젝트 소개

코로나로 인한 비대면 수업의 증가로

1) 집중력 저하
2) 



### 팀원 소개



| 前 팀 장 | 팀 장  | 팀 원  | 팀 원  | 팀 원  |
| :------: | :----: | :----: | :----: | :----: |
|  김성현  | 김일환 | 심은조 | 정우철 | 하현서 |

#### 팀규칙



>------
>
>🤗**카메라 항상 켜기**🤗
>
>------
>
>😴**휴식시간 철저히😴** 자유 갈 때, 올 때 채팅 남기기
>
>------
>
>🐽**코어 타임 집중하기**🐽 10:00 - 12:00 오전 코어 타임 1:00 - 6:00 오후 코어 타임
>
>개별 작업을 하게 되면 시간을 정해서 상시 대기해서 언제든지 소통을 할 수 있도록!
>
>------
>
>🍒**일일 회고 남기기🍒**
>
>5시 30분 회의실에 모여서 다같이 회고하기 하루의 소감, 힘들었던 부분, 기술적인 부분 중에서 아무거나!
>
>------
>
>⚽**트러블 슈팅 남기기**⚽
>
>자세한 이슈 내용, 문제 원인, 시행착오 적어두기!
>
>------
>
>🥝**잠깨는 시간, 준비시간**🥝
>
>09:00- 09:05
>
>------
>
>🍍**잡담 시간**🍍 09:05 - 09:30
>
>- 한 주를 담당할 프로젝트 매니저(이하 PM)를 매주 선정
>- PM의 역할
>  - PM은 팀원들이 그라운드 룰을 잘 지키는지 확인
>  - 평일 아침 스크럼 및 회의 진행
>
>------
>
>🍎**스크럼 시간**🍎 09:30 - 10:00
>
>------
>
>🍏점심 시간🍏
>
>11:00 - 01:00
>
>- 사이에 알아서 1시간 갖기
>- 갈 때, 올 때 채팅 남기기
>
>------
>
>🍑**회고 시간**🍑
>
>05:30 - 6:00
>
>- 금요일
>  - 한 주를 마무리하고 칭찬 / 아쉬운 점 공유 시간 갖기!
>
>------
>
>### GIt
>
>#### 규칙
>
>- **기능 단위**로 Push → Merge Request 생성
>
>- 팀원 모두 다른 사람이 작성한 코드 확인 후 Comment 작성 ‼본인 코드 뿐만 아니라 다른 사람 코드도 이해하기 ‼
>
>- Merge Request Template
>
>  ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e1bae76c-a4d9-4211-a801-4e4931ea722c/Untitled.png)
>
>#### Flow (Branch)
>
>- **master** : 제품으로 출시될 수 있는 브랜치
>
>- **dev** : 다음 출시 버전을 개발하는 브랜치
>
>- **feature** : 기능을 개발하는 브랜치
>
>- ex) 로그인 기능 위임 시
>
>  feature/login 브랜치 생성
>
>  ex) 같은 기능을 작업할 때 (로그인) → 이름의 이니셜 약어 이용하여 브랜치 생성
>
>  feature/login/sh
>
>  feature/login/hs
>
>#### Commit Message Convention
>
>- https://overcome-the-limits.tistory.com/entry/협업-협업을-위한-기본적인-git-커밋컨벤션-설정하기
>
>- **태그: 제목** 형태 → ex) Feat: 로그인 기능
>
>  | 태그 이름 | 설명                                                     |
>  | --------- | -------------------------------------------------------- |
>  | Feat      | 새로운 기능 추가                                         |
>  | Fix       | 버그 수정                                                |
>  | Design    | CSS 등 사용자 UI 디자인 변경                             |
>  | Comment   | 필요한 주석 추가 및 변경                                 |
>  | Docs      | 문서를 수정한 경우                                       |
>  | Rename    | 파일 혹은 폴더 명을 수정하거나 옮기는 작업만 수행한 경우 |
>  | Remove    | 파일을 삭제하는 작업만 수행한 경우                       |
>  | Recovery  | 파일 혹은 폴더를 복구하는 작업을 수행하는 경우           |
>
>#### 자주 사용하는 명령어
>
>- git push origin 브랜치명 → 원격 저장소로 해당 브랜치 push
>
>  ex) git push origin login
>
>- git pull [원격저장소명] [원격저장소 브랜치명] → 원격 저장소에서 local로 pull
>
>  ex) git pull origin feature → 원격 저장소에 있는 feature 브랜치 pull
>
>- git checkout -b 브랜치명 → 현재 있는 브랜치로부터 새로운 브랜치 생성(-b) 후 이동(checkout)
>
>  ex) git checkout -b login → login 브랜치 생성 후 이동
>
>- 깃 저장소 이동하기
>
>  ``` bash
>  git clone --mirror {기존 레퍼지토리 주소}
>  cd {기존 레퍼지토리 명}.git
>  git remote set-url --push origin {신규 레퍼지토리 주소}
>  git push --mirror
>  ```
>
>  
>
>- etc) git clone 옵션중 --mirror / --bare 차이점
>
>  https://pinocc.tistory.com/138

### 프로젝트 명세

[기능명세서](https://docs.google.com/spreadsheets/d/18-CeEBBO8wSRqbJIzstc_J5Cyt1iNi4LRHm5akPiwvw/edit?usp=sharing)

[유즈케이스](https://app.diagrams.net/#G1sagDOmQBDiuSQeBHrKMscmzctqgiRLNo)

ERD
[와이어프레임](https://docs.google.com/presentation/d/1yVuQeDnOL--OQ7ABIADai3rTTZEwzWQRAwQoF91Pxrk/edit)

API 명세서

아키텍처



### 시연 영상



### 느낀점

