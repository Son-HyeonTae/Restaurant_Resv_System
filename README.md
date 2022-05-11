# swRepository 소공프로젝트
-개발환경: springBoot, jdk11, h2 DataBase, lombok, jpa

src->main->java->hello->se

domain package는 EntityClass와 ClassForm이 있는 package

repository package는 data들을 DB에 저장

기본적으로 JPA의 ORM기술을 사용
DB Table과 java class를 매핑, query는 JPQL을 사용


**5월 6일**
모든 페이지를 controller과 연결, test수행 완료
예약date를 입력하고 "예약하기"를 누르면 DB에 data가 들어가게 함.

**5월 8일**
회원가입하면 DB에 다 들어가고, 로그인도 가능

(problem)
0. 로그인 버튼 옆에 있는 아이콘들의 HTML코드가 어디있는지?
1. 로그인에 실패하고 null을 반환한 error페이지
2. 로그인을 성공한 페이지 
3. 로그인 한 사용자의 예약 목록출력, 수정/추가/삭제 
4. 로그아웃기능 
5. 로그인을 하고 세션 제어(상태를 어떻게 유지할 것인가?)
6. 예약을 할 때 로그인 사용자와 일반 사용자의 분리
7. 인원수와 예약일시에 따른 검증 (중복예약, if Reservation covers > 테이블의 covers)

**5월 11일**
관리자 예약 리스트 페이지
유저 예약 리스트 클릭시 예약 수정 페이지 제작 
