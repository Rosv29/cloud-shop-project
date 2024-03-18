# 개인프로젝트
## cloud-shop-project

# 상세한 설명은 PDF파일에 작성하였습니다.

# 관리자 계정으로 로그인 시 관리자 페이지에서 상품 등록/수정/삭제, 카테고리 등록/수정/삭제 확인가능합니다
### ADMIN ID : ADMIN
### PASSWORD : 1234

# Java, Spring Boot, MariaDB, Spring Data JPA, AJAX, Jquery, ThymeLeaf ,AWS S3

## 회원가입 시 정규식 테스트 -> AJAX를 통해 비동기로 처리(아이디 중복, 비밀번호 확인),  이메일 인증 기능 (REDIS 사용) , 스프링 시큐리티를 통해 권한 설정(Enum 형식으로 Role 적용) ,CSRF 토큰 적용

## 관리자 페이지 구성
### 스프링 시큐리티를 통해 관리자 권한을 따로 부여하여 admin 일때만 접속이가능하게 처리하였습니다.
### 상품카테고리를 DB로 구성 -> 관리자 페이지에서 등록/수정/삭제 가능하게 처리
### 상품테이블 DB로 구성 -> 상품이미지를 별도의 테이블로 구성하여 관리자페이지에서 업로드, 수정, 삭제 처리 가능 (AWS S3 버킷 사용을 하였지만 버킷을 관리하지 않아도 되게 처리까지 하였음)


## 사용자 페이지
### 제품구매 시 수량 및 가격 반영되서 결제가능하게 처리

# 팀프로젝트 설명
## http://github.com/balancekim/green-six

## https://github.com/Rosv29/greenSix 소스코드수정링크
