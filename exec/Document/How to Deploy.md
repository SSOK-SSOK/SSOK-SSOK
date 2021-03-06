### 1. mobaXterm

> 아래 번호 순서대로 클릭하고 `4` 번에서 `.pem` 키를 등록합니다.

![image-20210323134814508](../images/deploy1.png)

✔ `login as :` 가 뜨면 `ubuntu`를 입력한다.

<br/>

### 2. 기본 세팅 (추가 예정)

```bash
# 설치되어있는 패키지들을 최신버전으로 업그레이드 해줍니다.
$ sudo apt-get update

# 소스코드 빌드 시 필요한 기본적인 패키지를 다운로드합니다.
$ sudo apt-get install build-essential

# vue
# NodeJS를 설치합니다.
$ curl -sL https://deb.nodesource.com/setup_14.x | sudo -E bash -
$ sudo apt-get install nodejs
# npm을 설치합니다.
$ sudo apt-get install npm

# Django
# 파이썬을 설치합니다. (for django)
$ sudo apt-get install python3

# pip를 설치하고 업그레이드합니다.
$ sudo apt-get install python3-pip
$ sudo pip3 install --upgrade pip 

# Java
# Java 8 설치
$ sudo apt-get install openjdk-8-jdk

# 타임존 변경
$ sudo timedatectl set-timezone 'Asia/Seoul'

# 설치 가능한 MySQL 버전 확인
$ sudo apt-cache search mysql-server

# MySQL
# MySQL 설치
$ sudo apt-get install mysql-server

# MySQL 설치 확인
$ dpkg -l | grep mysql-server

# MySQL 구동
$ sudo systemctl start mysql.service

# MySQL 구동 확인
$ ps -ef | grep mysql

# MySQL 외부접속 허용 설정
$ cd /etc/mysql/mysql.conf.d
$ vi mysqld.cnf
bind-address 값을 0.0.0.0 으로 수정

# MySQL 접속
$ sudo mysql -u root -p

# 모든 IP로 접속가능한 계정 생성
$ create user '아이디'@'%' identified by '비밀번호';

# Workbench Connection 설정
Connection Name: 원하는 이름
Connection Method: Stanard TCP/IP over SSH
SSH Hostname: j4a201.p.ssafy.io
SSH Username: ubuntu
SSH Key File: .pem 파일 등록
MySQL Hostname: 0.0.0.0
MySQL Server Port: 3306
Username: 모든 IP로 접속가능한 계정 ex) '아이디'
Test Connection 클릭

# Workbench DB 생성

# 특정 DB, 모든 Table, 모든 IP 접속 허용
# ssokauth DB, 모든 Table, 모든 IP 접속 권한부여
$ GRANT ALL PRIVILEGES ON ssokauth.* TO 아이디@'%';
```

<br/>

### 3. Nginx

> Nginx는 클라이언트와 서버 중간에서 대신 통신을 해주는 서버입니다. (리버스 프록시)

```bash
# nginx를 설치합니다.
$ sudo apt-get install nginx

# 현재 사용가능한 서비스별 ufw 설정값을 확인합니다.
$ sudo ufw app list
  Nginx Full # 포트 80(암호화되지 않은 웹 트래픽)과 포트 443(TLS/SSL 암호화 트래픽) 모두 연다.
  Nginx HTTP # 포트 80 (암호화되지 않은 웹 트래픽)을 연다
  Nginx HTTPS # 이 프로필은 포트 443 (TLS/SSL 암호화 트래픽)을 연다.
  OpenSSH 
  
# HTTP를 열어줍니다.
$ sudo ufw allow 'Nginx HTTP'

# sites-available 파일을 수정합니다.
$ sudo vi /etc/nginx/sites-available/default
```

```bash
# /etc/nginx/sites-available/default
server {
        listen 80 default_server;
        listen [::]:80 default_server;

		# front 빌드 후 생긴 dist 파일의 경로를 입력합니다. 
        root /home/ubuntu/Movie_community_Front/dist;
     
        index index.html;

        server_name _;

        location / {
            try_files $uri $uri/ /index.html;
        }
        
        ### backend reverse proxy 설정 추가 ###
        location /api {
            proxy_pass http://localhost:8080;
            proxy_http_version 1.1;
            proxy_set_header Connection "";
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
            proxy_set_header X-Forwarded-Host $host;
            proxy_set_header X-Forwarded-Port $server_port;
        }
```

```bash
# nginx를 재시작해줍니다.
$ sudo service nginx restart
```

<br/>



-----------------

### 2. HTTP -> HTTPS 로 변경

> HTTP 배포에 이어서 진행합니다.

```bash
# 시작 경로
ubuntu@ip-172-26-11-72:~/s04p23a201/frontend$ ~~~
```

```bash
$ sudo service nginx status
$ cd /etc/nginx/sites-available
$ sudo apt update
$ sudo apt-get install  letsencrypt -y
$ sudo apt upgrade -y 
$ sudo apt install certbot python3-certbot-nginx
$ sudo service nginx stop
$ sudo certbot certonly --standalone -d j4a201.p.ssafy.io
```

- 마지막 코드를 실행하면 이메일을 입력하라는 메세지가 뜹니다. 인증을 하지는 않으니 아무 이메일이나 입력해도 상관없습니다.
- 이메일 입력 후 (A)gree -> (Y)es 선택
- `Congratulations!` 메세지가 뜨면 성공!

```bash
$ sudo certbot renew --dry-run
$ sudo vi default
```

- 위의 명령어를 입력하면 Nginx 설정코드를 볼 수 있습니다. 그 코드를 수정해야합니다.

```
# Virtual Host configuration for example.com
...

server {
        #listen 80 default_server;
        #listen [::]:80 default_server;

        # SSL configuration
        #
        listen 443 ssl;
        listen [::]:443 ssl;

        root home/ubuntu/s04p23a201/frontend/dist;

        # Add index.php to the list if you are using PHP
        index index.html index.htm;

        server_name j4a201.p.ssafy.io;

        location / {
                # First attempt to serve request as file, then
                # as directory, then fall back to displaying a 404.
                #proxy_pass http://$host$request_uri;
        }
        
        ### backend reverse proxy 설정 추가 ###
        location /api {
            proxy_pass http://localhost:8080;
            proxy_http_version 1.1;
            proxy_set_header Connection "";
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;
            proxy_set_header X-Forwarded-Host $host;
            proxy_set_header X-Forwarded-Port $server_port;
        }

        ssl_certificate /etc/letsencrypt/live/j4a201.p.ssafy.io/fullchain.pem; # managed by Certbot
        ssl_certificate_key /etc/letsencrypt/live/j4a201.p.ssafy.io/privkey.pem; # managed by Certbot
        ssl_protocols TLSv1.2 TLSv1.3;
        ssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:DHE-RSA-AES128-GCM-SHA256:DHE-RSA-AES256-GCM-SHA384;
        ssl_prefer_server_ciphers off;
        ssl_dhparam /path; # managed by Certbot

}
server {
        if ($host = "j4a201.p.ssafy.io"){
                return 301 https://$host$request_uri;
        }
        listen 80;
        listen [::]:80;

        server_name j4a201.p.ssafy.io;

        #root /var/www/example.com;
        #index index.html;

        #location / {
        #       try_files $uri $uri/ =404;
        #}
        return 404;
}
```

- 위의 코드를 맨 끝에 입력한 후 `:wq!` 로 vi 종료합니다.

```bash
$ cd /etc/letsencrypt
$ mkdir path
```

```bash
$ curl https://ssl-config.mozilla.org/ffdhe2048.txt > /path
```

- 위의 명령어를 입력했을 때 `Permission denied` 가 뜨면 새로운 터미널을 하나 더 생성합니다.

```bash
# 새로운 터미널

$ sudo su
$ curl https://ssl-config.mozilla.org/ffdhe2048.txt > /path

# 추천하는 방법은 아님. 자주 사용하면 안된다.
```

- 원래의 터미널로 돌아갑니다.

```bash
$ cd /etc/nginx/sites-available
$ sudo vi default
$ sudo service nginx start
```



#### 성공!

![image-20210323213700897](../images/image-20210323213700897.png)