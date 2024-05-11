# knufest-BE
2024 경북대학교 안내웹 백엔드 서버입니다.

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
