## 백엔드 서버 배포

1. sts에서 jar 파일 추출
- 프로젝트 파일 Run As>5 Maven Build
- 콘솔창에 jar 파일 경로 확인

2. mobaXterm에 jar 파일 업로드
- mobaXterm 접속
- jar 파일이 있는 경로로 이동해서 파일 mobaXterm으로 드래그 (자동업로드 됨)

3. jar 파일 업로드한 경로로 경로이동
```
cd s04p23a201/backend
```

4. jar파일 실행
```
java -jar [파일명].jar --server.servlet.context-path=/card-api --server.port=8081
```