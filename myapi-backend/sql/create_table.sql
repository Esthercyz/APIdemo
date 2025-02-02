# 数据库初始化

-- 创建库
create database if not exists cyz_apis_platform;

-- 切换库
use cyz_apis_platform;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    accessKey    varchar(512)                           not null comment 'accessKey',
    secretKey    varchar(512)                           not null comment 'secretKey',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 用户调用接口关系表
create table if not exists `user_interface_info`
(
    `id` bigint not null auto_increment comment '自增id' primary key,
    `userId` bigint default 00000 not null comment '调用用户 id',
    `interfaceInfoId` bigint default  00000 not null comment '接口 id',
    `totalNum` bigint default 100 not null comment '总调用次数',
    `leftNum` bigint default 100 not null comment '剩余调用次数',
    `interfaceStatus` tinyint default 0 not null comment '接口状态：0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '用户名',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';


-- 接口信息表
USE cyz_apis_platform;

-- 接口信息表
create table if not exists `interfaces_info`
(
    `id`             bigint                             not null auto_increment comment '主键' primary key,
    `name`           varchar(256)                       not null comment '名称',
    `description`    varchar(256)                       null comment '描述',
    `url`            varchar(512)                       not null comment '接口地址',
    `requestHeader`  text                               null comment '请求头',
    `responseHeader` text                               null comment '响应头',
    `status`         int      default 0                 not null comment '接口状态（0-关闭，1-开启）',
    `method`         varchar(256)                       not null comment '请求类型',
    `userId`         bigint                             not null comment '创建人',
    `createTime`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete`       tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息表';

-- 接口相关数据表-----
-- ----------------------------
-- Table structure for t_city
-- ------------ ----------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `city_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市名称',
                           `adcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市code',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           PRIMARY KEY (`id`, `city_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3526 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
-- ----------------------------
-- Table structure for t_poet
-- ----------------------------
CREATE TABLE `t_poet`  (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `author` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '作者',
                           `dynasty` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '朝代',
                           `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '题目',
                           `poetry` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '诗句',
                           `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_poet
-- ----------------------------
INSERT INTO `t_poet` VALUES (1, '李白', '唐', '将进酒', '君不见黄河之水天上来，奔流到海不复回。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (2, '李白', '唐', '将进酒', '君不见高堂明镜悲白发，朝如青丝暮成雪。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (3, '李白', '唐', '将进酒', '人生得意须尽欢，莫使金樽空对月。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (4, '李白', '唐', '将进酒', '天生我材必有用，千金散尽还复来。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (5, '李白', '唐', '将进酒', '烹羊宰牛且为乐，会须一饮三百杯。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (6, '李白', '唐', '将进酒', '岑夫子，丹丘生，将进酒，杯莫停。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (7, '李白', '唐', '将进酒', '与君歌一曲，请君为我倾耳听。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (8, '李白', '唐', '将进酒', '钟鼓馔玉不足贵，但愿长醉不愿醒。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (9, '李白', '唐', '将进酒', '古来圣贤皆寂寞，惟有饮者留其名。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (10, '李白', '唐', '将进酒', '陈王昔时宴平乐，斗酒十千恣欢谑。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (11, '李白', '唐', '将进酒', '主人何为言少钱，径须沽取对君酌。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (12, '李白', '唐', '将进酒', '五花马、千金裘，呼儿将出换美酒，与尔同销万古愁。', '2023-03-09 09:01:09');
INSERT INTO `t_poet` VALUES (13, '李白', '唐', '行路难·其一', '金樽清酒斗十千，玉盘珍羞直万钱。', '2023-03-09 09:02:54');
INSERT INTO `t_poet` VALUES (14, '李白', '唐', '行路难·其一', '停杯投箸不能食，拔剑四顾心茫然。', '2023-03-09 09:02:54');
INSERT INTO `t_poet` VALUES (15, '李白', '唐', '行路难·其一', '欲渡黄河冰塞川，将登太行雪满山。', '2023-03-09 09:02:54');
INSERT INTO `t_poet` VALUES (16, '李白', '唐', '行路难·其一', '闲来垂钓碧溪上，忽复乘舟梦日边。', '2023-03-09 09:02:54');
INSERT INTO `t_poet` VALUES (17, '李白', '唐', '行路难·其一', '行路难，行路难，多歧路，今安在？', '2023-03-09 09:02:54');
INSERT INTO `t_poet` VALUES (18, '李白', '唐', '行路难·其一', '长风破浪会有时，直挂云帆济沧海。', '2023-03-09 09:02:54');
INSERT INTO `t_poet` VALUES (19, '李白', '唐', '黄鹤楼送孟浩然之广陵', '故人西辞黄鹤楼，烟花三月下扬州。', '2023-03-09 09:03:38');
INSERT INTO `t_poet` VALUES (20, '李白', '唐', '黄鹤楼送孟浩然之广陵', '孤帆远影碧空尽，唯见长江天际流', '2023-03-09 09:03:38');
INSERT INTO `t_poet` VALUES (21, '李白', '唐', '宣州谢朓楼饯别校书叔云', '弃我去者，昨日之日不可留；', '2023-03-09 09:04:16');
INSERT INTO `t_poet` VALUES (22, '李白', '唐', '宣州谢朓楼饯别校书叔云', '乱我心者，今日之日多烦忧。', '2023-03-09 09:04:16');
INSERT INTO `t_poet` VALUES (23, '李白', '唐', '宣州谢朓楼饯别校书叔云', '长风万里送秋雁，对此可以酣高楼。', '2023-03-09 09:04:16');
INSERT INTO `t_poet` VALUES (24, '李白', '唐', '宣州谢朓楼饯别校书叔云', '蓬莱文章建安骨，中间小谢又清发。', '2023-03-09 09:04:16');
INSERT INTO `t_poet` VALUES (25, '李白', '唐', '宣州谢朓楼饯别校书叔云', '俱怀逸兴壮思飞，欲上青天揽明月。', '2023-03-09 09:04:16');
INSERT INTO `t_poet` VALUES (26, '李白', '唐', '宣州谢朓楼饯别校书叔云', '抽刀断水水更流，举杯消愁愁更愁。', '2023-03-09 09:04:16');
INSERT INTO `t_poet` VALUES (27, '李白', '唐', '宣州谢朓楼饯别校书叔云', '人生在世不称意，明朝散发弄扁舟。', '2023-03-09 09:04:16');
INSERT INTO `t_poet` VALUES (28, '李白', '唐', '梦游天姥吟留别', '海客谈瀛洲，烟涛微茫信难求；', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (29, '李白', '唐', '梦游天姥吟留别', '越人语天姥，云霞明灭或可睹。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (30, '李白', '唐', '梦游天姥吟留别', '天姥连天向天横，势拔五岳掩赤城。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (31, '李白', '唐', '梦游天姥吟留别', '天台四万八千丈，对此欲倒东南倾。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (32, '李白', '唐', '梦游天姥吟留别', '我欲因之梦吴越，一夜飞度镜湖月。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (33, '李白', '唐', '梦游天姥吟留别', '湖月照我影，送我至剡溪。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (34, '李白', '唐', '梦游天姥吟留别', '谢公宿处今尚在，渌水荡漾清猿啼。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (35, '李白', '唐', '梦游天姥吟留别', '脚著谢公屐，身登青云梯。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (36, '李白', '唐', '梦游天姥吟留别', '半壁见海日，空中闻天鸡。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (37, '李白', '唐', '梦游天姥吟留别', '千岩万转路不定，迷花倚石忽已暝。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (38, '李白', '唐', '梦游天姥吟留别', '熊咆龙吟殷岩泉，栗深林兮惊层巅。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (39, '李白', '唐', '梦游天姥吟留别', '云青青兮欲雨，水澹澹兮生烟。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (40, '李白', '唐', '梦游天姥吟留别', '列缺霹雳，丘峦崩摧。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (41, '李白', '唐', '梦游天姥吟留别', '洞天石扉，訇然中开。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (42, '李白', '唐', '梦游天姥吟留别', '青冥浩荡不见底，日月照耀金银台。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (43, '李白', '唐', '梦游天姥吟留别', '霓为衣兮风为马，云之君兮纷纷而来下。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (44, '李白', '唐', '梦游天姥吟留别', '虎鼓瑟兮鸾回车，仙之人兮列如麻。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (45, '李白', '唐', '梦游天姥吟留别', '忽魂悸以魄动，恍惊起而长嗟。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (46, '李白', '唐', '梦游天姥吟留别', '惟觉时之枕席，失向来之烟霞。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (47, '李白', '唐', '梦游天姥吟留别', '世间行乐亦如此，古来万事东流水。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (48, '李白', '唐', '梦游天姥吟留别', '别君去兮何时还？且放白鹿青崖间，须行即骑访名山。', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (49, '李白', '唐', '梦游天姥吟留别', '安能摧眉折腰事权贵，使我不得开心颜！', '2023-03-09 09:05:25');
INSERT INTO `t_poet` VALUES (50, '李白', '唐', '望天门山', '天门中断楚江开，碧水东流至此回。', '2023-03-09 09:06:29');
INSERT INTO `t_poet` VALUES (51, '李白', '唐', '望天门山', '两岸青山相对出，孤帆一片日边来。', '2023-03-09 09:06:29');
INSERT INTO `t_poet` VALUES (52, '李白', '唐', '侠客行', '赵客缦胡缨，吴钩霜雪明。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (53, '李白', '唐', '侠客行', '银鞍照白马，飒沓如流星。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (54, '李白', '唐', '侠客行', '十步杀一人，千里不留行。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (55, '李白', '唐', '侠客行', '事了拂衣去，深藏身与名。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (56, '李白', '唐', '侠客行', '闲过信陵饮，脱剑膝前横。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (57, '李白', '唐', '侠客行', '将炙啖朱亥，持觞劝侯嬴。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (58, '李白', '唐', '侠客行', '三杯吐然诺，五岳倒为轻。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (59, '李白', '唐', '侠客行', '眼花耳热后，意气素霓生。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (60, '李白', '唐', '侠客行', '救赵挥金槌，邯郸先震惊。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (61, '李白', '唐', '侠客行', '千秋二壮士，烜赫大梁城。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (62, '李白', '唐', '侠客行', '纵死侠骨香，不惭世上英。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (63, '李白', '唐', '侠客行', '谁能书阁下，白首太玄经。', '2023-03-09 09:07:22');
INSERT INTO `t_poet` VALUES (64, '李商隐', '唐', '锦瑟', '锦瑟无端五十弦，一弦一柱思华年。', '2023-03-09 09:12:19');
INSERT INTO `t_poet` VALUES (65, '李商隐', '唐', '锦瑟', '庄生晓梦迷蝴蝶，望帝春心托杜鹃。', '2023-03-09 09:12:19');
INSERT INTO `t_poet` VALUES (66, '李商隐', '唐', '锦瑟', '沧海月明珠有泪，蓝田日暖玉生烟。', '2023-03-09 09:12:19');
INSERT INTO `t_poet` VALUES (67, '李商隐', '唐', '锦瑟', '此情可待成追忆，只是当时已惘然。', '2023-03-09 09:12:19');
INSERT INTO `t_poet` VALUES (68, '卓文君', '汉', '白头吟', '皑如山上雪，皎若云间月。', '2023-03-09 09:13:19');
INSERT INTO `t_poet` VALUES (69, '卓文君', '汉', '白头吟', '闻君有两意，故来相决绝。', '2023-03-09 09:13:19');
INSERT INTO `t_poet` VALUES (70, '卓文君', '汉', '白头吟', '今日斗酒会，明旦沟水头。', '2023-03-09 09:13:19');
INSERT INTO `t_poet` VALUES (71, '卓文君', '汉', '白头吟', '躞蹀御沟上，沟水东西流。', '2023-03-09 09:13:19');
INSERT INTO `t_poet` VALUES (72, '卓文君', '汉', '白头吟', '凄凄复凄凄，嫁娶不须啼。', '2023-03-09 09:13:19');
INSERT INTO `t_poet` VALUES (73, '卓文君', '汉', '白头吟', '愿得一心人，白头不相离。', '2023-03-09 09:13:19');
INSERT INTO `t_poet` VALUES (74, '卓文君', '汉', '白头吟', '竹竿何袅袅，鱼尾何簁簁！', '2023-03-09 09:13:19');
INSERT INTO `t_poet` VALUES (75, '杜甫', '唐', '春夜喜雨', '好雨知时节，当春乃发生。', '2023-04-04 20:49:42');