/*
��Ԣ����ģ��˵����
====================================================
����ģ��˵��
====================================================
�����һ��
������Ϊ���㣬ѧУ����λ������ѧԺ��ѧԺ����ѧ����ס
----------------------------------------------------
��������
	����ʱ�����á�ѧУ����Ա��
��Դ����
	¥������ѧУ����Ա��
	���ҹ���ѧУ����Ա��
	��λ����ѧУ����Ա��
ס�޹���
	��λ�������ѧУ����Ա��
	��λ��ס����ѧԺ����Ա��
	ס����Ϣ������Ԣ����Ա��
ͳ�Ʋ�ѯ
	��Ԣס��ͳ�ơ�ȫ���û���
	ѧ��ס�޲�ѯ��ȫ���û���
	ѧ��ס��ͳ�ơ�ȫ���û���
	������Ϣ��ѯ��ȫ���û���
====================================================
----------------------------------------------------
���³�ʼ�������ʺ��°�װ�����湫Ԣ���߹�Ԣ������δ����ϵͳ��ѧУ
���й�Ԣ���ݵ�ѧУ��ҪäĿִ���������
�ر��ǽ�����䣬����ɾ�������ؽ�������������ݶ�ʧ
----------------------------------------------------
*/
/*************************************************************************************************************/
/**********************************************���ݿ⽨��  sql *************************************************/
/*************************************************************************************************************/

----------------------------------------------------------------------------------------
-- ¥����Ϣ��---------------------------------------------------------------------------
-- Create table 
drop table XG_GYGL_NEW_LDXXB
create table XG_GYGL_NEW_LDXXB
(
  LDDM  VARCHAR2(20) not null,
  LDMC  VARCHAR2(200) not null,
  LDXB  VARCHAR2(10),
  LDCS  VARCHAR2(2),
  QSCH  VARCHAR2(10),
  SFHLC VARCHAR2(2),
  XQDM  VARCHAR2(10),
  YQDM  VARCHAR2(10),
  BZ    VARCHAR2(2000)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_LDXXB
  is '¥����Ϣ��';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_LDXXB.LDDM
  is '¥������';
comment on column XG_GYGL_NEW_LDXXB.LDMC
  is '¥������';
comment on column XG_GYGL_NEW_LDXXB.LDXB
  is '¥���Ա�';
comment on column XG_GYGL_NEW_LDXXB.LDCS
  is '¥������';
comment on column XG_GYGL_NEW_LDXXB.QSCH
  is '��ʼ���';
comment on column XG_GYGL_NEW_LDXXB.SFHLC
  is '�Ƿ�0��';
comment on column XG_GYGL_NEW_LDXXB.XQDM
  is 'У������';
comment on column XG_GYGL_NEW_LDXXB.YQDM
  is '԰������';
comment on column XG_GYGL_NEW_LDXXB.BZ
  is '��ע';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_LDXXB
  add constraint PK_LDXXB primary key (LDDM);

----------------------------------------------------------------------------------------
-- ������Ϣ��---------------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_QSXXB;
create table XG_GYGL_NEW_QSXXB
(
  LDDM VARCHAR2(20) not null,
  QSH  VARCHAR2(20) not null,
  CH   VARCHAR2(2),
  QSXB VARCHAR2(2),
  CWS  VARCHAR2(2),
  SFBZ VARCHAR2(10),
  QSDH VARCHAR2(20),
  XYDM VARCHAR2(10),
  NJ   VARCHAR2(4),
  BZ   VARCHAR2(2000)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_QSXXB
  is '������Ϣ��';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_QSXXB.LDDM
  is '¥������';
comment on column XG_GYGL_NEW_QSXXB.QSH
  is '���Һ�';
comment on column XG_GYGL_NEW_QSXXB.CH
  is '���';
comment on column XG_GYGL_NEW_QSXXB.QSXB
  is '�����Ա�';
comment on column XG_GYGL_NEW_QSXXB.CWS
  is '��λ��';
comment on column XG_GYGL_NEW_QSXXB.SFBZ
  is '�շѱ�׼';
comment on column XG_GYGL_NEW_QSXXB.QSDH
  is '���ҵ绰';
comment on column XG_GYGL_NEW_QSXXB.XYDM
  is '����ѧԺ����';
comment on column XG_GYGL_NEW_QSXXB.NJ
  is '�����꼶';
comment on column XG_GYGL_NEW_QSXXB.BZ
  is '��ע';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_QSXXB
  add constraint PK_QSXXB primary key (LDDM, QSH);

----------------------------------------------------------------------------------------
-- ��λ��Ϣ�� ---------------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_CWXXB;
create table XG_GYGL_NEW_CWXXB
(
  LDDM  VARCHAR2(20) not null,
  QSH   VARCHAR2(20) not null,
  CWH   VARCHAR2(2) not null,
  XYDM  VARCHAR2(10),
  NJ    VARCHAR2(4),
  BJDM  VARCHAR2(20),
  XH    VARCHAR2(20),
  BZ    VARCHAR2(1000),
  SFBL  VARCHAR2(2) default '��',
  RZSJ  VARCHAR2(50),
  RZCZR VARCHAR2(100)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_CWXXB
  is '��λ��Ϣ��';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_CWXXB.LDDM
  is '¥������';
comment on column XG_GYGL_NEW_CWXXB.QSH
  is '���Һ�';
comment on column XG_GYGL_NEW_CWXXB.CWH
  is '��λ��';
comment on column XG_GYGL_NEW_CWXXB.XYDM
  is '����ѧԺ����';
comment on column XG_GYGL_NEW_CWXXB.NJ
  is '�����꼶';
comment on column XG_GYGL_NEW_CWXXB.BJDM
  is '�����༶����';
comment on column XG_GYGL_NEW_CWXXB.XH
  is '��ס��ѧ��ѧ��';
comment on column XG_GYGL_NEW_CWXXB.BZ
  is '��ע';
comment on column XG_GYGL_NEW_CWXXB.SFBL
  is '�Ƿ���';
comment on column XG_GYGL_NEW_CWXXB.RZSJ
  is '��סʱ��';
comment on column XG_GYGL_NEW_CWXXB.RZCZR
  is '��ס������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_CWXXB
  add constraint PK_CWXXB primary key (LDDM, QSH, CWH);

----------------------------------------------------------------------------------------
-- ������Ϣ�� ---------------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_TSXXB;
create table XG_GYGL_NEW_TSXXB
(
  XH    VARCHAR2(20) not null,
  LDMC  VARCHAR2(100),
  QSH   VARCHAR2(10),
  CWH   VARCHAR2(10),
  TSYY  VARCHAR2(50),
  TSSJ  VARCHAR2(50),
  BZ    VARCHAR2(1000),
  TSCZR VARCHAR2(50),
  LDDM  VARCHAR2(20),
  NJ    VARCHAR2(10),
  XYDM  VARCHAR2(20),
  ZYDM  VARCHAR2(50),
  BJDM  VARCHAR2(100),
  RZSJ  VARCHAR2(50),
  RZCZR VARCHAR2(100),
  XYMC  VARCHAR2(200),
  ZYMC  VARCHAR2(200),
  BJMC  VARCHAR2(200),
  XM    VARCHAR2(200),
  XB    VARCHAR2(10),
  CZSJ  VARCHAR2(50) default to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') not null
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_TSXXB
  is '��Ԣ������Ϣ��';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_TSXXB.XH
  is 'ѧ��';
comment on column XG_GYGL_NEW_TSXXB.LDMC
  is '¥������';
comment on column XG_GYGL_NEW_TSXXB.QSH
  is '���Һ�';
comment on column XG_GYGL_NEW_TSXXB.CWH
  is '��λ��';
comment on column XG_GYGL_NEW_TSXXB.TSYY
  is '����ԭ��';
comment on column XG_GYGL_NEW_TSXXB.TSSJ
  is '����ʱ��';
comment on column XG_GYGL_NEW_TSXXB.BZ
  is '��ע';
comment on column XG_GYGL_NEW_TSXXB.TSCZR
  is '���޲�����';
comment on column XG_GYGL_NEW_TSXXB.LDDM
  is '¥������';
comment on column XG_GYGL_NEW_TSXXB.NJ
  is '�꼶';
comment on column XG_GYGL_NEW_TSXXB.XYDM
  is 'ѧԺ����';
comment on column XG_GYGL_NEW_TSXXB.ZYDM
  is 'רҵ����';
comment on column XG_GYGL_NEW_TSXXB.BJDM
  is '�༶����';
comment on column XG_GYGL_NEW_TSXXB.RZSJ
  is '��סʱ��';
comment on column XG_GYGL_NEW_TSXXB.RZCZR
  is '��ס������';
comment on column XG_GYGL_NEW_TSXXB.XYMC
  is 'ѧԺ����';
comment on column XG_GYGL_NEW_TSXXB.ZYMC
  is 'רҵ����';
comment on column XG_GYGL_NEW_TSXXB.BJMC
  is '�༶����';
comment on column XG_GYGL_NEW_TSXXB.XM
  is '����';
comment on column XG_GYGL_NEW_TSXXB.XB
  is '�Ա�';
comment on column XG_GYGL_NEW_TSXXB.CZSJ
  is '����ʱ��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_TSXXB
  add constraint TSXX_PRIMARY_ID primary key (XH, CZSJ);
  
----------------------------------------------------------------------------------------
-- ����ԭ������ ----------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_TSYYDMB;
create table XG_GYGL_NEW_TSYYDMB
(
  TSYYDM VARCHAR2(10),
  TSYYMC VARCHAR2(100)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_TSYYDMB
  is '����ԭ������(��Ԣ������)';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_TSYYDMB.TSYYDM
  is '����ԭ�����';
comment on column XG_GYGL_NEW_TSYYDMB.TSYYMC
  is '����ԭ������';

--����ԭ�����ݳ�ʼ��
delete from xg_gygl_new_tsyydmb;
commit;
insert into xg_gygl_new_tsyydmb values('1','��ҵ��У');
insert into xg_gygl_new_tsyydmb values('2','ס���춯');
insert into xg_gygl_new_tsyydmb values('3','�ξ�');
insert into xg_gygl_new_tsyydmb values('4','�߶�');
insert into xg_gygl_new_tsyydmb values('5','����');
commit;
----------------------------------------------------------------------------------------
-- ȡ����ס���ؿ��Ʊ� -------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_QXRZKGKZB;
create table XG_GYGL_NEW_QXRZKGKZB
(
  XYDM VARCHAR2(20) not null,
  NJ   VARCHAR2(4) not null,
  SFQY VARCHAR2(10) default '��',
  KSSJ VARCHAR2(50),
  JSSJ VARCHAR2(50)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_QXRZKGKZB
  is 'ȡ����ס���ؿ��Ʊ�';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_QXRZKGKZB.XYDM
  is 'ѧԺ����';
comment on column XG_GYGL_NEW_QXRZKGKZB.NJ
  is '�꼶';
comment on column XG_GYGL_NEW_QXRZKGKZB.SFQY
  is '�Ƿ�����';
comment on column XG_GYGL_NEW_QXRZKGKZB.KSSJ
  is '��ʼʱ��';
comment on column XG_GYGL_NEW_QXRZKGKZB.JSSJ
  is '����ʱ��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_QXRZKGKZB
  add constraint PK_QXRZKGKZ primary key (XYDM, NJ);
  
----------------------------------------------------------------------------------------
-- ��λ��Ϣ������ʱ�� -------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_IMPCWXXB;
create table XG_GYGL_NEW_IMPCWXXB
(
  LDDM VARCHAR2(20),
  QSH  VARCHAR2(20),
  CWH  VARCHAR2(2),
  XH   VARCHAR2(20),
  MARK CHAR(1) default '1',
  BZ   VARCHAR2(50),
  RZSJ VARCHAR2(50)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_IMPCWXXB
  is '��λ��Ϣ������ʱ��';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_IMPCWXXB.LDDM
  is '¥������';
comment on column XG_GYGL_NEW_IMPCWXXB.QSH
  is '���Һ�';
comment on column XG_GYGL_NEW_IMPCWXXB.CWH
  is '��λ��';
comment on column XG_GYGL_NEW_IMPCWXXB.XH
  is 'ѧ��';
comment on column XG_GYGL_NEW_IMPCWXXB.MARK
  is '��� 1���������� 0�����ݲ�����';
comment on column XG_GYGL_NEW_IMPCWXXB.BZ
  is '��ע';
comment on column XG_GYGL_NEW_IMPCWXXB.RZSJ
  is '��סʱ��';

----------------------------------------------------------------------------------------
-- �������ñ� --------------------------------------------------------------------------- 
-- Create table
drop table XG_GYGL_NEW_JBSZB;
create table XG_GYGL_NEW_JBSZB
(
  CSDM VARCHAR2(20),
  CSMC VARCHAR2(100),
  CSZ  VARCHAR2(20),
  BZ   VARCHAR2(500)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_JBSZB
  is '��Ԣ���������桿�������ñ�';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_JBSZB.CSDM
  is '��������';
comment on column XG_GYGL_NEW_JBSZB.CSMC
  is '��������';
comment on column XG_GYGL_NEW_JBSZB.CSZ
  is '����ֵ';
comment on column XG_GYGL_NEW_JBSZB.BZ
  is '��ע';

----------------------------------------------------------------------------------------
-- ��Ԣ����Ա�� --------------------------------------------------------------------------- 
-- Create table
drop table XG_GYGL_NEW_GYFDYB;
create table XG_GYGL_NEW_GYFDYB
(
  YHM  VARCHAR2(20) not null,
  LDDM VARCHAR2(20) not null
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_GYFDYB
  is '��Ԣ����Ա��';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_GYFDYB.YHM
  is '�û���';
comment on column XG_GYGL_NEW_GYFDYB.LDDM
  is '¥������';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_GYFDYB
  add primary key (YHM, LDDM);

----------------------------------------------------------------------------------------
-- ��λ��Ϣ��ͼ ---------------------------------------------------------------------------
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
case when a.xydm is null and a.nj is null then '��' else '��' end sffp,
case when a.xh is null then '��' else '��' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
comment on column xg_view_gygl_new_xszsgl.xh is 'ѧ��';
comment on column xg_view_gygl_new_xszsgl.xm is '����';
comment on column xg_view_gygl_new_xszsgl.xb is '�Ա�';
comment on column xg_view_gygl_new_xszsgl.nj is '�꼶';
comment on column xg_view_gygl_new_xszsgl.xydm is 'ѧԺ����';
comment on column xg_view_gygl_new_xszsgl.xymc is 'ѧԺ����';
comment on column xg_view_gygl_new_xszsgl.lddm is '¥������';
comment on column xg_view_gygl_new_xszsgl.ldmc is '¥������';
comment on column xg_view_gygl_new_xszsgl.ch is '���';
comment on column xg_view_gygl_new_xszsgl.qsh is '���Һ�';
comment on column xg_view_gygl_new_xszsgl.cwh is '��λ��';
comment on column xg_view_gygl_new_xszsgl.sfzs is '�Ƿ�ס��';

----------------------------------------------------------------------------------------
--δס��ѧ����Ϣ��ͼ---------------------------------------------------------------------
create or replace view view_xg_gygl_new_wzsxsxx as
select a.* from  view_xsjbxx a 
where not exists (select 1 from (select a.xh,a.lddm,a.ldmc,a.ch,a.qsh,a.cwh,b.xm,b.xb,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.bjdm,b.nj
from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh where a.xh is not null) b where a.xh=b.xh);
/
comment on column view_xg_gygl_new_wzsxsxx.xh is 'ѧ��';
comment on column view_xg_gygl_new_wzsxsxx.xm is '����';
comment on column view_xg_gygl_new_wzsxsxx.xb is '�Ա�';
comment on column view_xg_gygl_new_wzsxsxx.xymc is 'ѧԺ����';
comment on column view_xg_gygl_new_wzsxsxx.zymc is 'רҵ����';
comment on column view_xg_gygl_new_wzsxsxx.bjmc is '�༶����';
comment on column view_xg_gygl_new_wzsxsxx.nj is '�꼶';

----------------------------------------------------------------------------------------
--ѧ��ס�޹�����ͼ---------------------------------------------------------------------
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,
b.lddm,b.ldmc,b.ch,b.qsh,b.cwh,(case when b.xh is not null then '��' else '��' end) sfzs 
from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh=b.xh;
/
comment on column xg_view_gygl_new_xszsgl.xh is 'ѧ��';
comment on column xg_view_gygl_new_xszsgl.xm is '����';
comment on column xg_view_gygl_new_xszsgl.xb is '�Ա�';
comment on column xg_view_gygl_new_xszsgl.nj is '�꼶';
comment on column xg_view_gygl_new_xszsgl.xydm is 'ѧԺ����';
comment on column xg_view_gygl_new_xszsgl.xymc is 'ѧԺ����';
comment on column xg_view_gygl_new_xszsgl.lddm is '¥������';
comment on column xg_view_gygl_new_xszsgl.ldmc is '¥������';
comment on column xg_view_gygl_new_xszsgl.ch is '���';
comment on column xg_view_gygl_new_xszsgl.qsh is '���Һ�';
comment on column xg_view_gygl_new_xszsgl.cwh is '��λ��';
comment on column xg_view_gygl_new_xszsgl.sfzs is '�Ƿ�ס��';

/*************************************************************************************************************/
/***********************************************��ձ�����  sql*************************************************/
/*************************************************************************************************************/
delete from xg_gygl_new_ldxxb;
delete from xg_gygl_new_qsxxb;
delete from xg_gygl_new_cwxxb;
delete from xg_gygl_new_tsxxb;
delete from xg_gygl_new_qxrzkgkzb;
delete from xg_gygl_new_impcwxxb;
delete from xg_gygl_new_gyfdyb;

-- ִ���������󣬹�Ԣ��Ȩ�޷��������¸�Ȩ
/*************************************************************************************************************/
/********************************************����ģ���������sql***********************************************/
/*************************************************************************************************************/
--��ʼ������ģ�����--
delete from gnmkdmb where gnmkdm like 'N38%';
commit;
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N38','��Ԣ����','','1','009','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3800','��������','','1','','��','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380001','����ʱ������','gyglnew_cssz_sjsz.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3801','��Դ����','','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380101','¥����Ϣ����','gyglnew_ldgl_ldgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380102','������Ϣ����','gyglnew_qsgl_qsgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380103','��λ��Ϣ����','gyglnew_cwgl_cwgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3802','ס�޹���','','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380201','��λ�������','gyglnew_cwfpgl_cwfp.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380202','��λ��ס����','gyglnew_cwrzgl_cwrz.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380203','ס����Ϣ����','gyglnew_zsxxgl_zsxxgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3803','ͳ�Ʋ�ѯ','','1','','��','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380301','��Ԣס�����ͳ��','gyglnew_xxtj_xxtj.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380302','ѧ��ס�����ͳ��','gyglnew_xszstj_xszstj.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380303','ѧ��ס����Ϣ��ѯ','gyglnew_xszsgl_xszsgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380304','ѧ��������Ϣ��ѯ','gyglnew_tsgl_tsgl.do','1','','��','','');
commit;
--��ʼ����������Աzf01Ȩ��--
delete from yhqxb where yhm||gnmkdm like 'zf01N38%';
commit;
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N38','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3800','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380001','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3801','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380101','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380102','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380103','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3802','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380201','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380202','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380203','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3803','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380301','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380302','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380303','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380304','1');
commit;
/*************************************************************************************************************/
/********************************************�߼���ѯ�������sql***********************************************/
/*************************************************************************************************************/
--��ʼ���߼���ѯ���--
delete from xg_search_szb where path like 'gyglnew_%';
commit;
--����ʱ������
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','sf','�Ƿ�����','djcx','sfqy','gygl_third','3');
--¥����Ϣ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldxb','¥���Ա�','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','lddm','¥������','mhcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldmc','¥������','mhcx','','gygl_third','2');
--������Ϣ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','�����Ա�','djcx','qsxb','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sf','�Ƿ񺬿մ�λ','djcx','sfhkcw','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
--��λ��Ϣ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xb','��λ�Ա�','djcx','qsxb','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','��λ�Ƿ���','djcx','sfbl','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','��λ�Ƿ����','djcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','��λ�Ƿ���ס','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','����','mhcx','','gygl_third','4');
--��λ�������
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xb','�Ա��޶�','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xymc','ѧԺ����','mhcx','','gygl_third','1');
--��λ��ס����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xb','�Ա��޶�','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xymc','ѧԺ����','mhcx','','gygl_third','1');
--ס����Ϣ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xb','��λ�Ա�','djcx','qsxb','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','��λ�Ƿ���','djcx','sfbl','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sffp','��λ�Ƿ����','djcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','��λ�Ƿ���ס','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','����','mhcx','','gygl_third','4');
--������Ϣ��ѯ
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xb','�Ա�','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','ld','¥��','djcx','lddm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','rzsj','��סʱ��','sjcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tssj','����ʱ��','sjcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tsyy','����ԭ��','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xh','ѧ��','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xm','����','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','qsh','���Һ�','mhcx','','gygl_third','3');
--ѧ��ס����Ϣ��ѯ
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','sf','�Ƿ�ס��','djcx','sfzs','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xm','����','mhcx','','gygl_third','4');
--ѧ��ס�����ͳ��
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
--�߼���ѯ--��λ���䣨ѧԺ���༶��
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','zy','רҵ','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','bj','�༶','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xb','�Ա��޶�','djcx','','gygl_third','5');
--�߼���ѯ--��λ��ס��ѧԺ-�༶��
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','zy','רҵ','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','bj','�༶','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xb','�Ա��޶�','djcx','','gygl_third','5');
commit;

