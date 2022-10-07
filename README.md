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

(problem)
0. 로그인에 실패하고 NULL을 반환한 error페이지
1. 로그인을 성공한 페이지
2. 로그아웃 기능
3. 로그인을 하고 세션 제어
4. 예약을 할 때 로그인 사용자와 일반 사용자의 분리
5. 인원수와 예약일시에 따른 검증 (중복예약, If Reservation covers > 테이블의 covers)
