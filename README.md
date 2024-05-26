# knufest-BE
2024 경북대학교 대동제 "하푸루나"의 웹페이지 백엔드 서버입니다.


# 참여 인원
<table>
  <tbody>
    <td align="center"><a href=""><img src="https://github.com/LikeLion-KNU/knufest-frontend/assets/80188977/46fc1d4f-f939-4d18-a2d5-594500b6468b"width="100px;" alt=""/><br /><sub><b>이동건</b> </b></sub></a><br /></td>
    <td align="center"><a href=""><img src="https://github.com/LikeLion-KNU/knufest-frontend/assets/80188977/3f994eef-0f42-4a77-bde6-6df6a1aaa49e" width="100px;" alt=""/><br /><sub><b>전지웅</b></sub></a><br /></td>
    <td align="center"><a href=""><img src="https://github.com/LikeLion-KNU/knufest-frontend/assets/80188977/71baadd5-713f-4665-9be1-ae3af385896f" width="100px;" alt=""/><br /><sub><b>최정식</b></sub></a><br /></td
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
workflow.yml
```
name: Java CI with Gradle

# 동작 조건 설정 : main 브랜치에 push 혹은 pull request가 발생할 경우 동작한다.
on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]
    types: closed

    
permissions:
  contents: read

jobs:
  # Spring Boot 애플리케이션을 빌드하여 도커허브에 푸시하는 과정
  build-docker-image:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    # 1. Java 17 세팅
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        
    - name: Run chmod to make gradlew executable
      run: chmod +x ./gradlew

    # 2. Spring Boot 애플리케이션 빌드
    - name: Build with Gradle
      uses: gradle/gradle-build-action@67421db6bd0bf253fb4bd25b31ebb98943c375e1
      with:
        arguments: clean bootJar

    # 3. Docker 이미지 빌드
    - name: docker image build
      run: docker build -t ${{ secrets.DOCKERHUB_USERNAME }}/knufest .

    # 4. DockerHub 로그인
    - name: docker login
      uses: docker/login-action@v2
      with:
        username: ${{ secrets.DOCKERHUB_USERNAME }}
        password: ${{ secrets.DOCKERHUB_PASSWORD }}

    # 5. Docker Hub 이미지 푸시
    - name: docker Hub push
      run: docker push ${{ secrets.DOCKERHUB_USERNAME }}/knufest

  # 위 과정에서 푸시한 이미지를 ec2에서 풀받아서 실행시키는 과정 
  run-docker-image-on-ec2:
    # build-docker-image (위)과정이 완료되어야 실행됩니다.
    needs: build-docker-image
    runs-on: self-hosted

    steps:
      # 1. 최신 이미지를 풀받습니다
      - name: docker pull
        run: sudo docker pull ${{ secrets.DOCKERHUB_USERNAME }}/knufest
      
      # 2. 기존의 컨테이너를 중지시킵니다
      - name: docker stop container
        run: sudo docker stop knufest 2>/dev/null || true

      # 3. 최신 이미지를 컨테이너화하여 실행시킵니다
      - name: docker run new container
        run: sudo docker run --name knufest --rm -d -p 8080:8080 --net in-net ${{ secrets.DOCKERHUB_USERNAME }}/knufest sleep infinity

      # 4. 미사용 이미지를 정리합니다
      - name: delete old docker image
        run: sudo docker system prune -f
```
1. build-docker-image : main branch 에 push or pull request 가 발생할 경우 main branch 의 코드를 바탕으로 docker image 를 docker hub에 push 한다.
2. run-docker-image-on-ec2 : push 된 docker image를 pull 하여 ec2 인스턴스에서 재실행
