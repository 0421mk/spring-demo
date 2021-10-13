DROP DATABASE IF EXISTS demo;
CREATE DATABASE demo;
USE demo;

#게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

#게시물 테스트 데이터
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 1',
`body` = '내용 1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 2',
`body` = '내용 2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 3',
`body` = '내용 3';

#회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(60) NOT NULL,
    `name` CHAR(20) NOT NULL,
    nickname CHAR(20) NOT NULL,
    cellphoneNo CHAR(20) NOT NULL,
    email CHAR(50) NOT NULL,
    `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한레벨 (3=일반,7=관리자)',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부 (0=탈퇴전, 1=탈퇴후)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

#회원 테스트 데이터
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`name` = '관리자',
nickname = '관리자',
cellphoneNo = '01011111111',
email = 'admin@gmail.com',
`authLevel` = 7;

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '사용자1',
nickname = '사용자1',
cellphoneNo = '01011111112',
email = 'user1@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '사용자2',
nickname = '사용자2',
cellphoneNo = '01011111113',
email = 'user2@gmail.com';

ALTER TABLE article ADD COLUMN memberId INT(10)UNSIGNED NOT NULL AFTER `updateDate`;

UPDATE article
SET memberId = 2
WHERE memberId = 0;

#게시판 테이블 생성
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(20) NOT NULL UNIQUE COMMENT 'notice(공지사항), free(자유게시판)',
    `name` CHAR(20) NOT NULL UNIQUE COMMENT '게시판 이름',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '게시판 삭제 여부 (0=삭제전, 1=삭제후)',
    delDate DATETIME COMMENT '삭제날짜'
);

#기본 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유';


#게시판 테이블에 boardId 칼럼 추가
ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER memberId;

UPDATE article
SET boardId =  1;

UPDATE article
SET boardId =  2
WHERE id IN (3);`article`

#게시물 개수 늘리기
INSERT INTO article
(
    regDate, updateDate, memberId, boardId, title, `body`
)
SELECT NOW(), NOW(), FLOOR(RAND() * 2) + 1, FLOOR(RAND() * 2) + 1,
CONCAT('제목_', RAND()), CONCAT('내용_', RAND()) FROM article;

#게시물 테이블 hitCount 칼럼을 추가
ALTER TABLE article
ADD COLUMN hitCount INT(10) UNSIGNED NOT NULL AFTER `body`;

#좋아요 테이블 생성
CREATE TABLE liketable (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relId INT(10) UNSIGNED NOT NULL COMMENT '관련데이터번호',
    `point` SMALLINT(2) NOT NULL COMMENT '좋아요=1, 싫어요=-1'
);

#리액션 포인트 테스트 데이터
INSERT INTO liketable
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relId = 2,
`point` = 1;
    
INSERT INTO liketable
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relId = 1,
`point` = -1;

#게시물 리스트 뿌려주는 DB
SELECT *,
IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra_likePoint,
IFNULL(SUM(IF(L.point < 0, L.point, 0)), 0) AS extra_disLikePoint
FROM(
SELECT A.*,
M.nickname AS extra_writerName
FROM
article AS A
LEFT JOIN MEMBER AS M
ON
A.memberId = M.id
WHERE boardId = 1) AS A
LEFT JOIN liketable AS L
ON A.id = L.relId
GROUP BY A.id
ORDER BY
A.id DESC

#댓글 테이블 생성
CREATE TABLE reply (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    articleId INT(10) UNSIGNED NOT NULL COMMENT '어떤 게시물의 댓글인지',
    replyType SMALLINT(2) NOT NULL COMMENT '(1:댓글, 2:대댓글)',
    `body` TEXT NOT NULL
);

#댓글 테스트 데이터 생성
`article`