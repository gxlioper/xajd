/**�������š������ݿ����**/

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4106', '�Զ����', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410101', '�Զ����', 'customForm.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410105', '�Զ��x���', 'customForm.do', '1', '');

commit;

-------------------Ȩ��-----------------------------
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4101', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410101', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410105', '1');

commit;

-------------------��-----------------------------
--�Զ������--
create table xg_custom_form(
form_id      varchar2(40) default sys_guid() not null,
form_name    varchar2(40),
ssmk         varchar2(20),
souce_table  varchar2(100),
primary key(form_id)
);
comment on table xg_custom_form is '�Զ������';
comment on column xg_custom_form.form_id is '��ID';
comment on column xg_custom_form.form_name is '������';
comment on column xg_custom_form.ssmk is '����ģ��';
comment on column xg_custom_form.souce_table is '���ݱ�';

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
comment on column xg_custom_content.checked is '��֤';
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
comment on column XG_CUSTOM_ZDB.xssx is '��ʾ˳��';

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
comment on table xg_xsxx_xsxxb is 'ѧ��_ѧ����Ϣ_ѧ����Ϣ��';
comment on column xg_xsxx_xsxxb.XH is'ѧ��';
comment on column xg_xsxx_xsxxb.XM is'����';
comment on column xg_xsxx_xsxxb.XBDM is'�Ա�';
comment on column xg_xsxx_xsxxb.MZDM is'����';
comment on column xg_xsxx_xsxxb.ZZMMDM is'������ò';
comment on column xg_xsxx_xsxxb.SFZH is'���֤��';
comment on column xg_xsxx_xsxxb.CSRQ is'��������';
comment on column xg_xsxx_xsxxb.SYSZD is'��Դ���ڵ�';
comment on column xg_xsxx_xsxxb.HKSZD is'�������ڵ�';
comment on column xg_xsxx_xsxxb.JG is'����';
comment on column xg_xsxx_xsxxb.BJDM is'�༶����';
comment on column xg_xsxx_xsxxb.XLCC is'ѧ�����';
comment on column xg_xsxx_xsxxb.XJZT is'ѧ��״̬';
comment on column xg_xsxx_xsxxb.SFZXS is'�Ƿ���У��';
comment on column xg_xsxx_xsxxb.XSZT is'תΪ��ʷ��״̬ (1Ϊ��У����0Ϊ��ʷ��)';
comment on column xg_xsxx_xsxxb.SJHM is'�ֻ�����';
comment on column xg_xsxx_xsxxb.GDDH is'�̶��绰';
comment on column xg_xsxx_xsxxb.EMAIL is'��������';
comment on column xg_xsxx_xsxxb.QQHM is'QQ����';
comment on column xg_xsxx_xsxxb.JTDZ is'��ͥ��ַ';
comment on column xg_xsxx_xsxxb.JTYB is'��ͥ�ʱ�';
comment on column xg_xsxx_xsxxb.JTDH is'��ͥ�绰';
comment on column xg_xsxx_xsxxb.KZZD1 is'��չ�ֶ�1';
comment on column xg_xsxx_xsxxb.KZZD2 is'��չ�ֶ�2';
comment on column xg_xsxx_xsxxb.KZZD3 is'��չ�ֶ�3';
comment on column xg_xsxx_xsxxb.KZZD4 is'��չ�ֶ�4';
comment on column xg_xsxx_xsxxb.KZZD5 is'��չ�ֶ�5';
comment on column xg_xsxx_xsxxb.KZZD6 is'��չ�ֶ�6';
comment on column xg_xsxx_xsxxb.KZZD7 is'��չ�ֶ�7';
comment on column xg_xsxx_xsxxb.KZZD8 is'��չ�ֶ�8';
comment on column xg_xsxx_xsxxb.KZZD9 is'��չ�ֶ�9';
comment on column xg_xsxx_xsxxb.KZZD10 is'��չ�ֶ�10';
comment on column xg_xsxx_xsxxb.KZZD11 is'��չ�ֶ�11';
comment on column xg_xsxx_xsxxb.KZZD12 is'��չ�ֶ�12';
comment on column xg_xsxx_xsxxb.KZZD13 is'��չ�ֶ�13';
comment on column xg_xsxx_xsxxb.KZZD14 is'��չ�ֶ�14';
comment on column xg_xsxx_xsxxb.KZZD15 is'��չ�ֶ�15';
comment on column xg_xsxx_xsxxb.KZZD16 is'��չ�ֶ�16';
comment on column xg_xsxx_xsxxb.KZZD17 is'��չ�ֶ�17';
comment on column xg_xsxx_xsxxb.KZZD18 is'��չ�ֶ�18';
comment on column xg_xsxx_xsxxb.KZZD19 is'��չ�ֶ�19';
comment on column xg_xsxx_xsxxb.KZZD20 is'��չ�ֶ�20';





































--������(�Զ��幦��ģ���)--
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
comment on table xg_custom_gnmkb is '�Զ������';
comment on column xg_custom_gnmkb.id is 'ID';
comment on column xg_custom_gnmkb.gnmkdm is '����ģ�����';
comment on column xg_custom_gnmkb.gnmkmc is '����ģ������';
comment on column xg_custom_gnmkb.dyym is '��Ӧҳ��';
comment on column xg_custom_gnmkb.shzt is '���״̬';
comment on column xg_custom_gnmkb.tablename is '����';
comment on column xg_custom_gnmkb.create_time is '����ʱ��';

--������(�Զ������)--
create table xg_custom_table(
id   	 varchar2(40) default sys_guid() not null,
gnmk_id   varchar2(40),
title   varchar2(20),
row_all varchar2(10),
create_time VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(id)
);
comment on table xg_custom_table is '�Զ������';
comment on column xg_custom_table.id is 'ID';
comment on column xg_custom_table.gnmk_id is '����ģ��ID';
comment on column xg_custom_table.title is '����';
comment on column xg_custom_table.row_all is '����';
comment on column xg_custom_table.create_time is '����ʱ��';

--������(�Զ�������ݱ�)--
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

comment on table xg_custom_table_content is '�Զ������';
comment on column xg_custom_table_content.id is 'ID';
comment on column xg_custom_table_content.table_id is '��ID';
comment on column xg_custom_table_content.row_num is '��';
comment on column xg_custom_table_content.column_num is '��';
comment on column xg_custom_table_content.width is '���';
comment on column xg_custom_table_content.content is '����';
comment on column xg_custom_table_content.textarea_rows is '�ı�������';
comment on column xg_custom_table_content.postfix is '��׺';
comment on column xg_custom_table_content.source_table is '��Դ��';
comment on column xg_custom_table_content.select_dm is '�����б����';
comment on column xg_custom_table_content.select_mc is '�����б�����';
comment on column xg_custom_table_content.checked is '��֤';

--������(�Զ������ѯ��)--
create table xg_custom_search_content(
id  	 	varchar2(40) default sys_guid() not null,
gnmk_id  	varchar2(40),
content_id 	varchar2(40),
xssx	 	varchar2(5),
primary key(id)
);

comment on table xg_custom_search_content is '�Զ������';
comment on column xg_custom_search_content.id is 'ID';
comment on column xg_custom_search_content.gnmk_id is '����ģ��ID';
comment on column xg_custom_search_content.content_id is '����ID';
comment on column xg_custom_search_content.xssx is '��ʾ˳��';

--������(�Զ����������)--
create table xg_custom_pk_content(
id  	 	varchar2(40) default sys_guid() not null,
gnmk_id varchar2(40),
pk_id 	varchar2(40),
xssx	 	varchar2(5),
primary key(id)
);

comment on table xg_custom_pk_content is '�Զ������';
comment on column xg_custom_pk_content.id is 'ID';
comment on column xg_custom_pk_content.gnmk_id is '����ģ��ID';
comment on column xg_custom_pk_content.pk_id is '����ID';
comment on column xg_custom_pk_content.xssx is '��ʾ˳��';

--������(��ʦ��Ϣ��)--
create table xg_custom_master_xxb(
id  	varchar2(40) default sys_guid() not null,
gnmkdm  varchar2(40),
zd      varchar2(40),
zdz 	varchar2(4000),
pk 	  varchar2(4000),
create_time VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(id)
);

comment on table xg_custom_master_xxb is '�Զ������';
comment on column xg_custom_master_xxb.id is 'ID';
comment on column xg_custom_master_xxb.gnmkdm is '����ģ�����';
comment on column xg_custom_master_xxb.zd is '�ֶ�';
comment on column xg_custom_master_xxb.zdz is '�ֶ�ֵ';
comment on column xg_custom_master_xxb.pk is '����';
comment on column xg_custom_master_xxb.create_time is '����ʱ��';

------------------------�߼���ѯ--------------------------------
delete from xg_search_szb where path='customGnmk.do?method=customGnmkManage';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','nj','�꼶','djcx','nj','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','xy','ѧԺ','djcx','xydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','zy','רҵ','djcx','zydm','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('customGnmk.do?method=customGnmkManage','bj','�༶','djcx','bjdm','','','6');

commit;