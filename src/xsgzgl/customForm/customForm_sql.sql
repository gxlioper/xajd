/**评奖评优——数据库语句**/

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4106', '自定义表单', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410101', '自定义表单', 'customForm.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410105', '自定義表單', 'customForm.do', '1', '');

commit;

-------------------权限-----------------------------
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4101', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410101', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410105', '1');

commit;

-------------------表-----------------------------
--自定义表单表--
create table xg_custom_form(
form_id      varchar2(40) default sys_guid() not null,
form_name    varchar2(40),
ssmk         varchar2(20),
souce_table  varchar2(100),
primary key(form_id)
);
comment on table xg_custom_form is '自定义表单表';
comment on column xg_custom_form.form_id is '表单ID';
comment on column xg_custom_form.form_name is '表单名称';
comment on column xg_custom_form.ssmk is '所属模块';
comment on column xg_custom_form.souce_table is '数据表';

create table xg_custom_table(
table_id    varchar2(40) default sys_guid() not null,
form_id     varchar2(40),
title       varchar2(20),
row_num     varchar2(10),
xssx        varchar2(5),
create_time VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(table_id)
);
comment on table xg_custom_table is '自定义TABLE表';
comment on column xg_custom_table.table_id is 'ID';
comment on column xg_custom_table.form_id is '功能模块ID';
comment on column xg_custom_table.title is '标题';
comment on column xg_custom_table.row_num is '行数';
comment on column xg_custom_table.xssx is '显示顺序';

create table xg_custom_content
(
  content_id    varchar2(40) default sys_guid() not null,
  table_id      varchar2(40),
  row_num    varchar2(5),
  column_num  varchar2(10),
  ZD            VARCHAR2(20),
  ZDM           VARCHAR2(40),
  SSB           VARCHAR2(20),
  ZDLX          VARCHAR2(10),
  CHECKED       VARCHAR2(100),
  SOURCE_TABLE  VARCHAR2(50),
  OPTION_DM     VARCHAR2(50),
  OPTION_MC     VARCHAR2(50),
  INPUT_WIDTH   VARCHAR2(5),
  INPUT_POSTFIX VARCHAR2(10),
  TEXTAREA_ROWS VARCHAR2(5),
  primary key (content_id)
);

alter table XG_CUSTOM_CONTENT add rowspan VARCHAR2(5);
alter table XG_CUSTOM_CONTENT add colspan VARCHAR2(5);
comment on table xg_custom_content is '自定义表单_字段表';
comment on column XG_CUSTOM_CONTENT.rowspan is '行合并';
comment on column XG_CUSTOM_CONTENT.colspan is '列合并';
comment on column xg_custom_content.ZD is '字段';
comment on column xg_custom_content.ZDM is '字段名';
comment on column xg_custom_content.SSB is '所屬表';
comment on column xg_custom_content.ZDLX is '字段類型';
comment on column xg_custom_content.INPUT_WIDTH is '宽度';
comment on column xg_custom_content.textarea_rows is '文本域行数';
comment on column xg_custom_content.INPUT_POSTFIX is '后缀';
comment on column xg_custom_content.source_table is '来源表';
comment on column xg_custom_content.OPTION_DM is '下拉列表代码';
comment on column xg_custom_content.OPTION_MC is '下拉列表名称';
comment on column xg_custom_content.checked is '验证';
comment on column xg_custom_table_content.row_num is '行';
comment on column xg_custom_table_content.column_num is '列';

create table XG_CUSTOM_ZDB
(
  ZD            VARCHAR2(20) not null,
  ZDM           VARCHAR2(40),
  SSB           VARCHAR2(20) not null,
  ZDLX          VARCHAR2(10),
  CHECKED       VARCHAR2(100),
  SOURCE_TABLE  VARCHAR2(50),
  OPTION_DM     VARCHAR2(50),
  OPTION_MC     VARCHAR2(50),
  INPUT_WIDTH   VARCHAR2(5),
  INPUT_POSTFIX VARCHAR2(10),
  TEXTAREA_ROWS VARCHAR2(5),
  xssx          VARCHAR2(5),
  primary key (zd,ssb)
);

comment on table XG_CUSTOM_ZDB is '自定义表单_字段表';
comment on column XG_CUSTOM_ZDB.ZD is '字段';
comment on column XG_CUSTOM_ZDB.ZDM is '字段名';
comment on column XG_CUSTOM_ZDB.SSB is '所屬表';
comment on column XG_CUSTOM_ZDB.ZDLX is '字段類型';
comment on column XG_CUSTOM_ZDB.INPUT_WIDTH is '宽度';
comment on column XG_CUSTOM_ZDB.textarea_rows is '文本域行数';
comment on column XG_CUSTOM_ZDB.INPUT_POSTFIX is '后缀';
comment on column XG_CUSTOM_ZDB.source_table is '来源表';
comment on column XG_CUSTOM_ZDB.OPTION_DM is '下拉列表代码';
comment on column XG_CUSTOM_ZDB.OPTION_MC is '下拉列表名称';
comment on column XG_CUSTOM_ZDB.checked is '验证';
comment on column XG_CUSTOM_ZDB.xssx is '显示顺序';

create table xg_xsxx_xsxxb
(
  XH VARCHAR2(20),
  XM VARCHAR2(20),
  XBDM VARCHAR2(20),
  MZDM VARCHAR2(20),
  ZZMMDM VARCHAR2(20),
  SFZH VARCHAR2(20),
  CSRQ VARCHAR2(20),
  SYSZD VARCHAR2(20),
  HKSZD VARCHAR2(20),
  JG VARCHAR2(20),
  BJDM VARCHAR2(20),
  XLCC VARCHAR2(20),
  XJZT VARCHAR2(20),
  SFZXS VARCHAR2(20),
  XSZT VARCHAR2(20),
  SJHM VARCHAR2(20),
  GDDH VARCHAR2(20),
  EMAIL VARCHAR2(20),
  QQHM VARCHAR2(20),
  JTDZ VARCHAR2(100),
  JTYB VARCHAR2(20),
  JTDH VARCHAR2(20),
  KZZD1 VARCHAR2(200),
  KZZD2 VARCHAR2(200),
  KZZD3 VARCHAR2(200),
  KZZD4 VARCHAR2(200),
  KZZD5 VARCHAR2(200),
  KZZD6 VARCHAR2(200),
  KZZD7 VARCHAR2(200),
  KZZD8 VARCHAR2(200),
  KZZD9 VARCHAR2(200),
  KZZD10 VARCHAR2(200),
  KZZD11 VARCHAR2(200),
  KZZD12 VARCHAR2(200),
  KZZD13 VARCHAR2(200),
  KZZD14 VARCHAR2(200),
  KZZD15 VARCHAR2(200),
  KZZD16 VARCHAR2(200),
  KZZD17 VARCHAR2(200),
  KZZD18 VARCHAR2(200),
  KZZD19 VARCHAR2(200),
  KZZD20 VARCHAR2(200),
  primary key (xh)
);
comment on table xg_xsxx_xsxxb is '学工_学生信息_学生信息表';
comment on column xg_xsxx_xsxxb.XH is'学号';
comment on column xg_xsxx_xsxxb.XM is'姓名';
comment on column xg_xsxx_xsxxb.XBDM is'性别';
comment on column xg_xsxx_xsxxb.MZDM is'民族';
comment on column xg_xsxx_xsxxb.ZZMMDM is'政治面貌';
comment on column xg_xsxx_xsxxb.SFZH is'身份证号';
comment on column xg_xsxx_xsxxb.CSRQ is'出生日期';
comment on column xg_xsxx_xsxxb.SYSZD is'生源所在地';
comment on column xg_xsxx_xsxxb.HKSZD is'户口所在地';
comment on column xg_xsxx_xsxxb.JG is'籍贯';
comment on column xg_xsxx_xsxxb.BJDM is'班级代码';
comment on column xg_xsxx_xsxxb.XLCC is'学历层次';
comment on column xg_xsxx_xsxxb.XJZT is'学籍状态';
comment on column xg_xsxx_xsxxb.SFZXS is'是否在校生';
comment on column xg_xsxx_xsxxb.XSZT is'转为历史生状态 (1为在校生，0为历史生)';
comment on column xg_xsxx_xsxxb.SJHM is'手机号码';
comment on column xg_xsxx_xsxxb.GDDH is'固定电话';
comment on column xg_xsxx_xsxxb.EMAIL is'电子邮箱';
comment on column xg_xsxx_xsxxb.QQHM is'QQ号码';
comment on column xg_xsxx_xsxxb.JTDZ is'家庭地址';
comment on column xg_xsxx_xsxxb.JTYB is'家庭邮编';
comment on column xg_xsxx_xsxxb.JTDH is'家庭电话';
comment on column xg_xsxx_xsxxb.KZZD1 is'扩展字段1';
comment on column xg_xsxx_xsxxb.KZZD2 is'扩展字段2';
comment on column xg_xsxx_xsxxb.KZZD3 is'扩展字段3';
comment on column xg_xsxx_xsxxb.KZZD4 is'扩展字段4';
comment on column xg_xsxx_xsxxb.KZZD5 is'扩展字段5';
comment on column xg_xsxx_xsxxb.KZZD6 is'扩展字段6';
comment on column xg_xsxx_xsxxb.KZZD7 is'扩展字段7';
comment on column xg_xsxx_xsxxb.KZZD8 is'扩展字段8';
comment on column xg_xsxx_xsxxb.KZZD9 is'扩展字段9';
comment on column xg_xsxx_xsxxb.KZZD10 is'扩展字段10';
comment on column xg_xsxx_xsxxb.KZZD11 is'扩展字段11';
comment on column xg_xsxx_xsxxb.KZZD12 is'扩展字段12';
comment on column xg_xsxx_xsxxb.KZZD13 is'扩展字段13';
comment on column xg_xsxx_xsxxb.KZZD14 is'扩展字段14';
comment on column xg_xsxx_xsxxb.KZZD15 is'扩展字段15';
comment on column xg_xsxx_xsxxb.KZZD16 is'扩展字段16';
comment on column xg_xsxx_xsxxb.KZZD17 is'扩展字段17';
comment on column xg_xsxx_xsxxb.KZZD18 is'扩展字段18';
comment on column xg_xsxx_xsxxb.KZZD19 is'扩展字段19';
comment on column xg_xsxx_xsxxb.KZZD20 is'扩展字段20';





































--新增表(自定义功能模块表)--
create table xg_custom_gnmkb(
id   	varchar2(40) default sys_guid() not null,
gnmkdm  varchar2(20),
gnmkmc  varchar2(50),
dyym 	varchar2(100),
shzt 	varchar2(10),
tablename 	varchar2(50),
create_time VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(id)
);
comment on table xg_custom_gnmkb is '自定义表单表';
comment on column xg_custom_gnmkb.id is 'ID';
comment on column xg_custom_gnmkb.gnmkdm is '功能模块代码';
comment on column xg_custom_gnmkb.gnmkmc is '功能模块名称';
comment on column xg_custom_gnmkb.dyym is '对应页面';
comment on column xg_custom_gnmkb.shzt is '审核状态';
comment on column xg_custom_gnmkb.tablename is '表名';
comment on column xg_custom_gnmkb.create_time is '创建时间';

--新增表(自定义表单表)--
create table xg_custom_table(
id   	 varchar2(40) default sys_guid() not null,
gnmk_id   varchar2(40),
title   varchar2(20),
row_all varchar2(10),
create_time VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(id)
);
comment on table xg_custom_table is '自定义表单表';
comment on column xg_custom_table.id is 'ID';
comment on column xg_custom_table.gnmk_id is '功能模块ID';
comment on column xg_custom_table.title is '标题';
comment on column xg_custom_table.row_all is '行数';
comment on column xg_custom_table.create_time is '创建时间';

--新增表(自定义表单内容表)--
create table xg_custom_table_content(
id  	 	 varchar2(40) default sys_guid() not null,
table_id varchar2(40),
row_num		 varchar2(5),
column_num	 varchar2(10),
content	 	 varchar2(50),
width		 varchar2(5),
postfix		 varchar2(10),
textarea_rows	 varchar2(5),
source_table	 varchar2(50),
select_dm		 varchar2(50),
select_mc		 varchar2(50),
checked      varchar2(100),
primary key(id,row_num,column_num)
);

comment on table xg_custom_table_content is '自定义表单表';
comment on column xg_custom_table_content.id is 'ID';
comment on column xg_custom_table_content.table_id is '表ID';
comment on column xg_custom_table_content.row_num is '行';
comment on column xg_custom_table_content.column_num is '列';
comment on column xg_custom_table_content.width is '宽度';
comment on column xg_custom_table_content.content is '内容';
comment on column xg_custom_table_content.textarea_rows is '文本域行数';
comment on column xg_custom_table_content.postfix is '后缀';
comment on column xg_custom_table_content.source_table is '来源表';
comment on column xg_custom_table_content.select_dm is '下拉列表代码';
comment on column xg_custom_table_content.select_mc is '下拉列表名称';
comment on column xg_custom_table_content.checked is '验证';

--新增表(自定义表单查询表)--
create table xg_custom_search_content(
id  	 	varchar2(40) default sys_guid() not null,
gnmk_id  	varchar2(40),
content_id 	varchar2(40),
xssx	 	varchar2(5),
primary key(id)
);

comment on table xg_custom_search_content is '自定义表单表';
comment on column xg_custom_search_content.id is 'ID';
comment on column xg_custom_search_content.gnmk_id is '功能模块ID';
comment on column xg_custom_search_content.content_id is '内容ID';
comment on column xg_custom_search_content.xssx is '显示顺序';

--新增表(自定义表单主键表)--
create table xg_custom_pk_content(
id  	 	varchar2(40) default sys_guid() not null,
gnmk_id varchar2(40),
pk_id 	varchar2(40),
xssx	 	varchar2(5),
primary key(id)
);

comment on table xg_custom_pk_content is '自定义表单表';
comment on column xg_custom_pk_content.id is 'ID';
comment on column xg_custom_pk_content.gnmk_id is '功能模块ID';
comment on column xg_custom_pk_content.pk_id is '主键ID';
comment on column xg_custom_pk_content.xssx is '显示顺序';

--新增表(大师信息表)--
create table xg_custom_master_xxb(
id  	varchar2(40) default sys_guid() not null,
gnmkdm  varchar2(40),
zd      varchar2(40),
zdz 	varchar2(4000),
pk 	  varchar2(4000),
create_time VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(id)
);

comment on table xg_custom_master_xxb is '自定义表单表';
comment on column xg_custom_master_xxb.id is 'ID';
comment on column xg_custom_master_xxb.gnmkdm is '功能模块代码';
comment on column xg_custom_master_xxb.zd is '字段';
comment on column xg_custom_master_xxb.zdz is '字段值';
comment on column xg_custom_master_xxb.pk is '主键';
comment on column xg_custom_master_xxb.create_time is '创建时间';

------------------------高级查询--------------------------------
delete from xg_search_szb where path='customGnmk.do?method=customGnmkManage';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','nj','年级','djcx','nj','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','xy','学院','djcx','xydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','zy','专业','djcx','zydm','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','bj','班级','djcx','bjdm','','','6');

commit;