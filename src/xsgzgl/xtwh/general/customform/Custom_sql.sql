--自定义表单表--
-- Create table
create table XG_CUSTOM_FORM
(
  FORM_ID                    VARCHAR2(100) default sys_guid() not null,
  FORM_NAME                  VARCHAR2(40),
  SSMK                       VARCHAR2(20),
  SOURCE_TABLE               VARCHAR2(100),
  SOURCE_TABLE_PK            VARCHAR2(100),
  ASSISTANT_TABLE_ONE        VARCHAR2(100),
  ASSISTANT_TABLE_ONE_ZD     VARCHAR2(100),
  ASSISTANT_TABLE_ONE_RELATE VARCHAR2(100),
  ASSISTANT_TABLE_TWO        VARCHAR2(100),
  ASSISTANT_TABLE_TWO_ZD     VARCHAR2(100),
  ASSISTANT_TABLE_TWO_RELATE VARCHAR2(100),
  DETAIL_VIEW                VARCHAR2(100),
  SEARCH_VIEW                VARCHAR2(100),
  CREATE_TIME                VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
  primary key(FORM_ID)
);
comment on table XG_CUSTOM_FORM
  is '自定义表单表';
-- Add comments to the columns 
comment on column XG_CUSTOM_FORM.FORM_ID
  is '表单ID';
comment on column XG_CUSTOM_FORM.FORM_NAME
  is '表单名称';
comment on column XG_CUSTOM_FORM.SSMK
  is '所属模块';
comment on column XG_CUSTOM_FORM.SOURCE_TABLE
  is '数据表';
comment on column XG_CUSTOM_FORM.SOURCE_TABLE_PK
  is '数据表【主键】';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_ONE
  is '辅助表1';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_ONE_ZD
  is '辅助表1字段';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_ONE_RELATE
  is '辅助表1关联';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_TWO
  is '辅助表2';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_TWO_ZD
  is '辅助表2字段';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_TWO_RELATE
  is '辅助表2关联';
comment on column XG_CUSTOM_FORM.DETAIL_VIEW
  is '详细视图';
comment on column XG_CUSTOM_FORM.SEARCH_VIEW
  is '查询视图';
comment on column XG_CUSTOM_FORM.CREATE_TIME
  is '创建时间';

--自定义表单查詢表--
create table xg_custom_search(
form_id     varchar2(40),
zd          varchar2(20),
zdm         varchar2(20),
xssx        varchar2(5),
primary key(form_id,zd)
);
comment on table xg_custom_search is '自定义查詢表';
comment on column xg_custom_search.form_id is '表單ID';
comment on column xg_custom_search.zd is '字段';
comment on column xg_custom_search.zdm is '字段名';
comment on column xg_custom_search.xssx is '顯示順序';

--自定义表单TABLE表--
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

--自定义表单Content表--
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
  onlyone        VARCHAR2(5),
  isnull        VARCHAR2(5),
  edit          VARCHAR2(5),
  rowspan   VARCHAR2(5),
  colspan   VARCHAR2(5),
  primary key (content_id)
);

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
comment on column xg_custom_content.onlyone is '唯一';
comment on column xg_custom_content.checked is '验证';
comment on column xg_custom_content.isnull is '允许为空';
comment on column xg_custom_content.edit is '可否修改';
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
  isnull        VARCHAR2(5) default 'yes',
  onlyone       VARCHAR2(5) default 'no',
  edit          VARCHAR2(5) default 'yes',
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
comment on column XG_CUSTOM_ZDB.isnull is '允许为空';
comment on column XG_CUSTOM_ZDB.onlyone is '是否唯一';
comment on column XG_CUSTOM_ZDB.edit is '可否修改';
comment on column XG_CUSTOM_ZDB.xssx is '显示顺序';