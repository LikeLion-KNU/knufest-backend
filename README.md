# knufest-BE
2024 경북대학교 대동제 "하푸루나"의 웹페이지 백엔드 서버입니다.

# 개발 기간
2024.05.14 ~ NOW

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
