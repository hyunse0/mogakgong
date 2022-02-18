# 모각공

### Server

- nginx

  1) 설치

  ```shell
  # apt repository 에 설치하고자 하는 nginx 버전 추가 
  # ubuntu 버전 (18.04: bionic, 16.04: xenial)
  $ sudo touch /etc/apt/sources.list.d/nginx.list
  $ echo "deb http://nginx.org/packages/ubuntu/ bionic nginx" | sudo tee -a /etc/apt/sources.list.d/nginx.list
  $ echo "deb-src http://nginx.org/packages/ubuntu/ bionic nginx"| sudo tee -a /etc/apt/sources.list.d/nginx.list
  
  # 인증 키 등록
  $ wget http://nginx.org/keys/nginx_signing.key
  $ sudo apt-key add nginx_signing.key
  
  # 저장소 업데이트
  $ sudo apt-get update
  
  # nginx 설치
  $ sudo apt-get install nginx
  ```

  2. 설정

  ```
  upstream backend {
          server localhost:8081;
  }
  server {
          listen [::]:443 ipv6only=on;
          listen 443 ssl;
  
          server_name i6c204.p.ssafy.io;
  
          # ssl 인증서
          ssl_certificate /etc/letsencrypt/live/i6c204.p.ssafy.io/fullchain.pem; # managed by Certbot
          ssl_certificate_key /etc/letsencrypt/live/i6c204.p.ssafy.io/privkey.pem; # managed by Certbot
          include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
          ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
  
          # Front
          location / {
                  root /home/ubuntu/app/S06P12C204/frontend/build;
                  try_files $uri $uri/ /index.html;
          }
  
          # Back
          location /api {
                  proxy_pass http://backend/api;
                  proxy_redirect off;
                  charset utf-8;
  
                  proxy_set_header X-Real-IP $remote_addr;
                  proxy_set_header X-Forwarded-For
                  $proxy_add_x_forwarded_for;
                  proxy_set_header X-Forwarded-Proto $scheme;
                  proxy_set_header X-Forwarded-Host $host;
                  proxy_set_header X-Forwarded-Port $server_port;
                          proxy_set_header X-NginX-Proxy true;
                          proxy_http_version 1.1;
                          proxy_set_header Connection "";
                          proxy_set_header    Upgrade $http_upgrade;
                          proxy_set_header Connection "upgrade";
          }
  }
  
  # redirect
  server {
          if ($host = i6c204.p.ssafy.io) {
                  return 301 https://$host$request_uri;
          } # managed by Certbot
  
          listen 80;
          listen [::]:80;
          server_name i6c204.p.ssafy.io;
                  return 404; # managed by Certbot
  }
  
  ```

- mysql 설치

  ```shell
  # 우분투 서버 업데이트
  $ sudo apt-get update
  
  # mysql-server 설치
  $ sudo apt-get install mysql-server
  
  # 방화벽 설정
  $ sudo ufw allow mysql
  
  # mysql 실행
  $ sudo systemctl start mysql
  ```

- jdk 11 설치

  ```shell
  # apt 업데이트
  $ sudo apt-get update && sudo apt-get upgrade
  
  # openjdk-11-jdk 설치
  $ sudo apt-get install openjdk-11-jdk
  
  # 설치 확인
  $ java -version
  ```

- git 설치

  ```shell
  # 패키지 리스트 업데이트
  $ sudo apt-get install git
  
  # 깃 설치
  $ sudo apt install git
  
  # 설치 후 버전 확인
  $ git --version
  ```

- openvidu

  1) docker 설치

     ```shell
     $ sudo apt-get update
     
     $ sudo apt-get install \
     	apt-transport-https \
     	ca-certificates \
     	curl \
     	gnupg \
     	lsb-release
     	
     $ sudo -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o
     /usr/share/keyrings/docker-archive-keyring.gpg
     
     $ echo \
     	"deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg]
     	https://download.docker.com/linux/ubuntu \
     	$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
     	
     $ sudo apt-get update
     
     $ sudo apt-get install docker-ce docker-ce-cli containerd.io
     
     $ sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
     
     $ sudo chmod +x /usr/local/bin/docker-compose
     ```

  2) 방화벽 설정

     ```shell
     $ ufw allow ssh
     $ ufw allow 80/tcp
     $ ufw allow 443/tcp
     $ ufw allow 3478/tcp
     $ ufw allow 3478/udp
     $ ufw allow 40000:57000/tcp
     $ ufw allow 40000:57000/udp
     $ ufw allow 57001:65535/tcp
     $ ufw allow 57001:65535/udp
     $ ufw enable
     ```

  3) 설치

     ```shell
     # 루트 권한 필요
     $ sudo su
     
     # 설치 경로 이동
     $ cd /opt
     
     # 설치
     $ curl https://s3-eu-west-1.amazonaws.com/aws.openvidu.io/install_openvidu_latest.sh | bash
     ```

  4) 환경변수 지정

     ```shell
     DOMAIN_OR_PUBLIC_IP=i6c204.p.ssafy.io
     OPENVIDU_SECRET=MY_SECRET
     CERTIFICATE_TYPE=letsencrypt
     LETSENCRYPT_EMAIL=ikhwan4142@gmail.com
     
     HTTP_PORT=8080
     HTTPS_PORT=8443
     ```

  5) 시작

     ```shell
     # sudo su 실행 후 루트 권한에서 실행하거나, sudo를 붙여서 실행해야함
     $ ./openvidu start
     ```



### Build & Deploy

- Backend 빌드 및 배포

  - app/S06P12C204 로 이동

  - ```shell
    # 쉘 스크립트 실행
    $ ./deploy.sh
    ```

  - deploy.sh

    ```shell
    #!/bin/bash
    
    // 변수 설정
    REPOSITORY=/home/ubuntu/app/S06P12C204
    PROJECT_NAME=BE
    
    cd $REPOSITORY/$PROJECT_NAME/
    
    echo "> Git Pull"
    
    // gitlab의 master pull
    git pull origin master
    
    echo "> 프로젝트 Build 시작"
    
    // 빌드
    ./gradlew build
    
    echo "> S06P12C204 디렉토리로 이동"
    
    cd $REPOSITORY
    
    echo "> Build 파일 복사"
    // 빌드 파일(jar) 복사
    cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/
    
    echo "> 현재 구동중인 애플리케이션 pid 확인"
    
    CURRENT_PID=$(pgrep -f mogakgong-0.0.1-SNAPSHOT.jar)
    
    echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"
    
    if [ -z "$CURRENT_PID" ]; then
    	echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
    else
    	echo "> kill -15 $CURRENT_PID"
    	kill -15 $CURRENT_PID
    	sleep 5
    fi
    
    echo "> 새 애플리케이션 배포"
    
    JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)
    
    echo "> JAR Name: $JAR_NAME"
    
    // 실행
    nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &
    ```

- Frontend 빌드 및 배포

  - app/S06P12C204/frontend 로 이동

    ```shell
    $ yarn install
    $ yarn build
    $ yarn start
    ```

