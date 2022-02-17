
# member
INSERT INTO `mogakgong`.`member` (`id`, `email`, `password`, `nickname`, `birth`, `goal`, `is_exist`, `type`, `role`) VALUES ('1', 'ssafy@naver.com', '$2a$10$0e9B229ryjzQRtf1ZSjteO4QjjK/xLAzab8WUOWjG8m/q4mEEJUaC', '김싸피', '2000-02-02', '화이팅', '1', 'mogakgong', 'USER');
INSERT INTO `mogakgong`.`member` (`id`, `email`, `password`, `nickname`, `birth`, `goal`, `is_exist`, `type`, `role`) VALUES ('2', 'ssafy2@naver.com', '$2a$10$7XetJ8F13kIgT.W7gSm3ie1heqb3cI5z8.9T4JjQDrsaBFFnE58oO', '이싸피', '2000-02-02', '열공', '1', 'mogakgong', 'USER');

# category
INSERT INTO `mogakgong`.`category` (`id`, `name`) VALUES ('1', '고시');
INSERT INTO `mogakgong`.`category` (`id`, `name`) VALUES ('2', '공무원');
INSERT INTO `mogakgong`.`category` (`id`, `name`) VALUES ('3', '수능');
INSERT INTO `mogakgong`.`category` (`id`, `name`) VALUES ('4', '어학');
INSERT INTO `mogakgong`.`category` (`id`, `name`) VALUES ('5', '자격증');
INSERT INTO `mogakgong`.`category` (`id`, `name`) VALUES ('6', '자기개발');
INSERT INTO `mogakgong`.`category` (`id`, `name`) VALUES ('7', '취업');

# member_category
INSERT INTO `mogakgong`.`member_category` (`id`, `member_id`, `category_id`) VALUES ('1', '1', '1');
INSERT INTO `mogakgong`.`member_category` (`id`, `member_id`, `category_id`) VALUES ('2', '1', '2');
INSERT INTO `mogakgong`.`member_category` (`id`, `member_id`, `category_id`) VALUES ('3', '1', '7');
INSERT INTO `mogakgong`.`member_category` (`id`, `member_id`, `category_id`) VALUES ('4', '2', '4');
INSERT INTO `mogakgong`.`member_category` (`id`, `member_id`, `category_id`) VALUES ('5', '2', '5');
INSERT INTO `mogakgong`.`member_category` (`id`, `member_id`, `category_id`) VALUES ('6', '2', '6');

# study_room
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('1', '1', 'cs공부', 'cs공부하고 올해는 네카라쿠베 가요!', '8', '4', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('2', '1', '모각코', '모여서 각자 코딩하는 방입니다. 잡담금지 채팅질문 가능', '8', '3', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('3', '1', '토익', '영어 성적 같이 올릴 분들 오세요', '8', '5', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('4', '2', '자습할 사람 오세요', '각자 자기 할 공부합시다', '8', '4', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('5', '2', '싸피 대비 ct문제풀이방', '싸피 8기 대비 컴띵 준비해요~', '8', '2', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('6', '2', '2022 수능대비방', '수능 공부방입니다. 잡담금지 대화금지 캠ON필수', '8', '7', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('7', '1', '삼성전자 면접 스터디', '삼성전자 면접 스터디방입니다. 다같이 취뽀해요!', '8', '5', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('8', '1', '코시국방', '코시국에 같이 공부해요', '8', '3', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('9', '1', '정처기방', '정보처리기사 필기, 실기 같이 준비해요', '8', '3', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('10', '2', '알고리즘 풀이방', '네카라쿠배 코테 대비 알고리즘 준비합시다', '8', '4', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('11', '2', 'SQLD 스터디', '상반기 SQLD따고 취뽀해요! 잡담금지', '8', '4', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('12', '2', '조용한 방', '캠ONOFF자유(캠ON시 동적인 움직임 자제), 마이크OFF필수, 잡담금지 채팅금지', '8', '6', '1');
INSERT INTO `mogakgong`.`study_room` (`id`, `member_id`, `title`, `description`, `limit_people`, `goal_time`, `is_exist`) VALUES ('13', '1', 'NCS 준비방', 'NCS준비하시는 분들 함께해요. 꾸준히 열심히 참여하실 분들 환영합니다!', '8', '5', '1');

# study_room_hashtag
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('1', '1', 'cs');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('2', '1', 'it');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('3', '2', '코딩');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('4', '2', 'it');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('5', '2', '자습');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('6', '3', '영어');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('7', '3', '토익');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('8', '5', '싸피');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('9', '5', '삼성');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('10', '6', '수능');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('11', '6', 'sky');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('12', '6', '1등급');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('13', '7', '삼성전자');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('14', '7', '면접');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('15', '9', '정보처리기사');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('16', '9', '정처기');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('17', '10', '알고리즘');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('18', '10', '네카라쿠배');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('19', '11', 'sqld');
INSERT INTO `mogakgong`.`study_room_hashtag` (`id`, `study_room_id`, `name`) VALUES ('20', '12', '잡담No');

# study_room_category
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('1', '1', '7');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('2', '2', '6');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('3', '2', '7');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('4', '3', '4');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('5', '4', '6');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('6', '5', '6');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('7', '5', '7');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('8', '6', '3');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('9', '7', '7');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('10', '8', '6');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('11', '9', '5');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('12', '10', '7');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('13', '11', '5');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('14', '12', '6');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('15', '13', '2');
INSERT INTO `mogakgong`.`study_room_category` (`id`, `study_room_id`, `category_id`) VALUES ('16', '13', '7');

# study_room_member
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('1', '1', '1', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('2', '1', '2', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('3', '1', '3', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('4', '2', '4', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('5', '2', '5', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('6', '2', '6', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('7', '1', '7', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('8', '1', '8', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('9', '1', '9', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('10', '2', '10', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('11', '2', '11', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('12', '2', '12', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('13', '1', '13', '2', '1');
INSERT INTO `mogakgong`.`study_room_member` (`id`, `member_id`, `study_room_id`, `level`, `is_exist`) VALUES ('14', '1', '4', '0', '1');

