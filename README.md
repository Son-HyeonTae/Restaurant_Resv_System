# swRepository 소공프로젝트
-개발환경: springBoot, jdk11, h2 DataBase, lombok, jpa

domain package는 EntityClass와 ClassForm이 있는 package

기본적으로 JPA의 ORM기술을 사용
DB Table과 java class를 매핑, query는 JPQL을 사용

**6/May/2022**
모든 페이지를 controller과 연결, test수행 완료
예약date를 입력하고 "예약하기"를 누르면 DB에 data가 들어가게 함.

**8/May/2022**
회원가입하면 DB에 다 들어가고, 로그인도 가능
