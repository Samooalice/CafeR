CREATE TABLE reboard(
    rebno NUMBER(6)
        CONSTRAINT REBRD_NO_PK PRIMARY KEY,
    writer VARCHAR2(10 CHAR)
        CONSTRAINT REBRD_ID_FK REFERENCES member(id)
        CONSTRAINT REBRD_ID_NN NOT NULL,
    body VARCHAR2(200 CHAR)   
        CONSTRAINT REBRD_NODY_NN NOT NULL,
    wdate DATE DEFAULT sysdate
        CONSTRAINT REBRD_DATE_NN NOT NULL,
    goods NUMBER(6) DEFAULT 0
        CONSTRAINT REBRD_GOOD_NN NOT NULL,
    reupno VARCHAR2 (15 CHAR)
        CONSTRAINT REBRD_UPNO_NN NOT NULL,
    regroup NUMBER(6)
        CONSTRAINT REBRD_GROUP_NN NOT NULL,
    RELEVEL NUMBER(1) DEFAULT 1
        CONSTRAINT REBRD_LEVEL_NN NOT NULL
);

ALTER TABLE reboard MODIFY reupno VARCHAR2(21 CHAR);

ALTER TABLE reboard 
ADD
    reisshow CHAR(1) DEFAULT 'Y'
        CONSTRAINT REBRD_SHOW_CK CHECK(reisshow IN('Y','N'))
        CONSTRAINT REBRD_SHOW_NN NOT NULL
;

ALTER TABLE reboard
MODIFY rebno NUMBER(6) DEFAULT rebrdSeq.NEXTVAL;

CREATE SEQUENCE rebrdSeq
    START WITH 1001
    MAXVALUE 999999
    NOCACHE
    NOCYCLE
;    


-- 데이터 입력
SELECT
    SUBSTR('#1001#1012')
FROM
    dual
;

SELECT
    SUBSTR('#1001#1012', 2, INSTR('#1001#1012', '#', 2, 1) -2) Upno
FROM
    dual
;


selectKey

INSERT INTO
    reboard(
        rebno, writer, body,
        reupno, regroup, relevel
    )
VALUES(
    #{bno}, #{id}, #{body},
    #{upno}||#{bno},
<if test="regroup eq 0">
    #{bno}
</if>
<if test="regroup neq 0">
    #{regroup}
</if>
    #{level + 1}
);

-- 리스트 조회 질의명령
SELECT
    rno, bno, id, body, wdate, goods, upno, regroup, "level", savename
FROM
    (
        SELECT
            ROWNUM rno, bno, id, body, wdate, goods, upno, regroup, "level", savename
        FROM
            (
                SELECT
                    rebno bno, writer id, body, wdate, goods, reupno upno,
                    regroup, relevel "level", filename savename
                FROM
                    reboard, member, avatar
                WHERE
                    writer = id
                    AND avatar = ano
                    AND reisshow = 'Y'
                ORDER BY
                    regroup DESC, 
                    SUBSTR(upno || '#', 1, INSTR(upno || '#', '#', 2) - 1),
                    upno
            )
    )
WHERE
    rno BETWEEN 1 AND 3
;

SELECT
    SUBSTR(reupno || '#', 2, INSTR(reupno || '#', '#', 2) - 2) result
FROM    
    reboard 
;    

SELECT
    COUNT(*)
FROM
    reboard
WHERE
    reisshow = 'Y';
    
-- 글 삭제 질의명령    
UPDATE
    reboard
SET
    reisshow = "N"
WHERE
    -- reupno LIKE '#1004%'
    reupno LIKE '%' || #{bno} || '%'
;    

--selectKey

INSERT INTO
    reboard(rebno, writer, body, reupno, regroup, relevel)
SELECT
    1004, 'iu', '테스트1-1', reupno || '#' || 1004, regroup, relevel+1
FROM
    reboard
WHERE
    rebno = 1001
;

commit;

-- 좋아요 요청 처리
UPDATE
    reboard
SET
    goods = goods + 1
WHERE
    rebno = 1003
;