# knufest-BE
2024 경북대학교 대동제 "하푸루나"의 웹페이지 백엔드 서버입니다.


# 참여 인원
<table>
  <tbody>
    <tr>
      <td align="center"><a href=""><img src="https://private-user-images.githubusercontent.com/80188977/331893463-c0a16a58-1ede-40eb-b877-984b9335ee68.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTYxNDQxOTMsIm5iZiI6MTcxNjE0Mzg5MywicGF0aCI6Ii84MDE4ODk3Ny8zMzE4OTM0NjMtYzBhMTZhNTgtMWVkZS00MGViLWI4NzctOTg0YjkzMzVlZTY4LnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA1MTklMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNTE5VDE4MzgxM1omWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWMxNWFmZWQ1MGIxMGMzYTAwZTAzYzc3YjUxZDRjNjI3ZDAwNzUzYTMwYThmZTU0OGE3NWQ1MDNmZmZmNTZlMzkmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.wnWC-LXFYI03buRqadQP0LtWY6beAEpkHxEMSM2EJNk"width="100px;" alt=""/><br /><sub><b>이동건 : @himodu </b></sub></a><br /></td>
      <td align="center"><a href=""><img src="" width="100px;" alt=""/><br /><sub><b>최정식 : </b></sub></a><br /></td>
  </tbody>
</table>

1. 이동건: @himodu
2. 최정식: @siksik-Choi


# 개발기간 및 운영기간
개발기간: 2024.05.14 ~ NOW
운영기간: 2024.05.21 ~ 2024.05.23




# 사용 기술
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white"> <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white">


# Entity 구성
1. Booth: 축제 부스의 정보, 부스 번호와 분류를 구별한 부스 정보.
2. Comment: 부스 게시판의 댓글 정보, 부스별 댓글 데이터를 저장.
3. User: 웹페이지에 접속한 유저의 고유 Hash값 정보

# 주요 기능
1. 부스 정보를 조회 (모든 부스 정보 / 특정 부스 정보)
2. 부스별 좋아요 기능
3. 부스별 댓글을 생성, 삭제 기능.
4. 웹페이지에 접근한 순수 유저의 수를 세는 기능.


# 시스템 구조



# SpringBoot + gradle

build.gradle 파일은 아래와 같습니다. 
```
    plugins {
    	id 'java'
    	id 'org.springframework.boot' version '3.2.5'
    	id 'io.spring.dependency-management' version '1.1.4'
    }
    
    java {
    	sourceCompatibility = '17'
    }
    group = 'LlikelionKNU'
    version = '0.0.1-SNAPSHOT'
    
    configurations {
    	compileOnly {
    		extendsFrom annotationProcessor
    	}
    }
    
    repositories {
    	mavenCentral()
    }
    
    dependencies {
    	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    	implementation 'org.springframework.boot:spring-boot-starter-web'
    	implementation 'org.springframework.boot:spring-boot-starter-webflux'
    	compileOnly 'org.projectlombok:lombok'
    	runtimeOnly 'com.mysql:mysql-connector-j'
    	annotationProcessor 'org.projectlombok:lombok'
    	testImplementation 'org.springframework.boot:spring-boot-starter-test'
    	testImplementation 'io.projectreactor:reactor-test'
    	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0'
    }
```
- spring boot starter web : 웹 서버 배포를 위한 라이브러리
- spring boot starter data jpa : jpa 사용을 위한 라이브러리
- springdoc-openapi-starter-webmvc-ui : swagger 사용을 위한 라이브러리
- org projectlombok lombok : lombok 사용을 위한 라이브러리
- com mysql mysql-connector-j : mysql 드라이버


## making bootjar 
gradle 에서
Tasks &rarr; build &rarr; bootJar 실행  
실행 결과 build &rarr; libs 경로에 jar 파일 생성

## docker image build
도커 이미지 빌드  
```
docker build -t {docker username}/{image name} .
```
생성된 이미지를 pull to hub  

## docker container execute
도커 이미지 pull
```
docker pull {docker username}/{image name}
```
도커 컨테이너 실행 및 생성
```
docker run -it -d --name {컨테이너 명} -p 8080:8080 --net {docker network} -v {docker volume} {이미지 명} sleep infinity
```

- d : 백그라운드(daemon)로 컨테이너 실행
- p : in/out bound port 설정
- net : docker network 설정
- v : docker volume 설정
- sleep infinity : 무중단 설정 


# GITHUB ACTION
