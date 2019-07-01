--�Զ������--
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
  is '�Զ������';
-- Add comments to the columns 
comment on column XG_CUSTOM_FORM.FORM_ID
  is '��ID';
comment on column XG_CUSTOM_FORM.FORM_NAME
  is '������';
comment on column XG_CUSTOM_FORM.SSMK
  is '����ģ��';
comment on column XG_CUSTOM_FORM.SOURCE_TABLE
  is '���ݱ�';
comment on column XG_CUSTOM_FORM.SOURCE_TABLE_PK
  is '���ݱ�������';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_ONE
  is '������1';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_ONE_ZD
  is '������1�ֶ�';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_ONE_RELATE
  is '������1����';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_TWO
  is '������2';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_TWO_ZD
  is '������2�ֶ�';
comment on column XG_CUSTOM_FORM.ASSISTANT_TABLE_TWO_RELATE
  is '������2����';
comment on column XG_CUSTOM_FORM.DETAIL_VIEW
  is '��ϸ��ͼ';
comment on column XG_CUSTOM_FORM.SEARCH_VIEW
  is '��ѯ��ͼ';
comment on column XG_CUSTOM_FORM.CREATE_TIME
  is '����ʱ��';

--�Զ������ԃ��--
create table xg_custom_search(
form_id     varchar2(40),
zd          varchar2(20),
zdm         varchar2(20),
xssx        varchar2(5),
primary key(form_id,zd)
);
comment on table xg_custom_search is '�Զ����ԃ��';
comment on column xg_custom_search.form_id is '���ID';
comment on column xg_custom_search.zd is '�ֶ�';
comment on column xg_custom_search.zdm is '�ֶ���';
comment on column xg_custom_search.xssx is '�@ʾ���';

--�Զ����TABLE��--
create table xg_custom_table(
table_id    varchar2(40) default sys_guid() not null,
form_id     varchar2(40),
title       varchar2(20),
row_num     varchar2(10),
xssx        varchar2(5),
create_time VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(table_id)
);
comment on table xg_custom_table is '�Զ���TABLE��';
comment on column xg_custom_table.table_id is 'ID';
comment on column xg_custom_table.form_id is '����ģ��ID';
comment on column xg_custom_table.title is '����';
comment on column xg_custom_table.row_num is '����';
comment on column xg_custom_table.xssx is '��ʾ˳��';

--�Զ����Content��--
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

comment on table xg_custom_content is '�Զ����_�ֶα�';
comment on column XG_CUSTOM_CONTENT.rowspan is '�кϲ�';
comment on column XG_CUSTOM_CONTENT.colspan is '�кϲ�';
comment on column xg_custom_content.ZD is '�ֶ�';
comment on column xg_custom_content.ZDM is '�ֶ���';
comment on column xg_custom_content.SSB is '���ٱ�';
comment on column xg_custom_content.ZDLX is '�ֶ����';
comment on column xg_custom_content.INPUT_WIDTH is '���';
comment on column xg_custom_content.textarea_rows is '�ı�������';
comment on column xg_custom_content.INPUT_POSTFIX is '��׺';
comment on column xg_custom_content.source_table is '��Դ��';
comment on column xg_custom_content.OPTION_DM is '�����б����';
comment on column xg_custom_content.OPTION_MC is '�����б�����';
comment on column xg_custom_content.onlyone is 'Ψһ';
comment on column xg_custom_content.checked is '��֤';
comment on column xg_custom_content.isnull is '����Ϊ��';
comment on column xg_custom_content.edit is '�ɷ��޸�';
comment on column xg_custom_table_content.row_num is '��';
comment on column xg_custom_table_content.column_num is '��';

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

comment on table XG_CUSTOM_ZDB is '�Զ����_�ֶα�';
comment on column XG_CUSTOM_ZDB.ZD is '�ֶ�';
comment on column XG_CUSTOM_ZDB.ZDM is '�ֶ���';
comment on column XG_CUSTOM_ZDB.SSB is '���ٱ�';
comment on column XG_CUSTOM_ZDB.ZDLX is '�ֶ����';
comment on column XG_CUSTOM_ZDB.INPUT_WIDTH is '���';
comment on column XG_CUSTOM_ZDB.textarea_rows is '�ı�������';
comment on column XG_CUSTOM_ZDB.INPUT_POSTFIX is '��׺';
comment on column XG_CUSTOM_ZDB.source_table is '��Դ��';
comment on column XG_CUSTOM_ZDB.OPTION_DM is '�����б����';
comment on column XG_CUSTOM_ZDB.OPTION_MC is '�����б�����';
comment on column XG_CUSTOM_ZDB.checked is '��֤';
comment on column XG_CUSTOM_ZDB.isnull is '����Ϊ��';
comment on column XG_CUSTOM_ZDB.onlyone is '�Ƿ�Ψһ';
comment on column XG_CUSTOM_ZDB.edit is '�ɷ��޸�';
comment on column XG_CUSTOM_ZDB.xssx is '��ʾ˳��';