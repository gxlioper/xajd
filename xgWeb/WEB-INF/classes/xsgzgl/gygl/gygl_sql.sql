/**��Ԣ����_�����桪�����ݿ����**/

--���ӹ���ģ��--
delete from gnmkdmb where gnmkdm like 'N38%';
commit;
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N38','��Ԣ����(new)','','1','009','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N3801','��Դ����','','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380101','¥����Ϣ����','gyglnew_ldgl_ldgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380102','������Ϣ����','gyglnew_qsgl_qsgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380103','��λ��Ϣ����','gyglnew_cwgl_cwgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N3802','ס�޹���','','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380201','��λ�������','gyglnew_cwfpgl_cwfp.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380202','��λ��ס����','gyglnew_cwrzgl_cwrz.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380203','������Ϣ����','gyglnew_tsgl_plts.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N3803','ͳ�Ʋ�ѯ','','1','','��','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380301','��Ԣס��ͳ��','gyglnew_xxtj_xxtj.do','1','','��','','');
commit;
	
--�����û�Ȩ��--
delete from yhqxb where yhm||gnmkdm like 'zf01N38%';
commit;
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N38','1');
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
commit;

--����¥����Ϣ��
-- Create table
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

--����������Ϣ��--
-- Create table
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
  NJ VARCHAR2(4),
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

--������λ��Ϣ��--
-- Create table
create table XG_GYGL_NEW_CWXXB
(
  LDDM VARCHAR2(20) not null,
  QSH  VARCHAR2(20) not null,
  CWH  VARCHAR2(2) not null,
  CWSX VARCHAR2(20),
  XYDM VARCHAR2(10),
  NJ   VARCHAR2(4),
  BJDM VARCHAR2(20),
  XH   VARCHAR2(20),
  BZ   VARCHAR2(1000),
  SFBL VARCHAR2(2) default '��'
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
comment on column XG_GYGL_NEW_CWXXB.CWSX
  is '��λ����';
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_CWXXB
  add constraint PK_CWXXB primary key (LDDM, QSH, CWH);

--����������Ϣ��--
-- Create table
create table XG_GYGL_NEW_TSXXB
(
  XH    VARCHAR2(20) not null,
  LDMC  VARCHAR2(100),
  QSH   VARCHAR2(10),
  CWH   VARCHAR2(10),
  TSYY  VARCHAR2(50),
  TSSJ  VARCHAR2(50),
  BZ    VARCHAR2(1000),
  TSCZR VARCHAR2(50)
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
  
--��������ԭ������
-- Create table
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



/**********************�߼���ѯ����*************************/
delete from xg_search_szb where path like 'gyglnew_%';
--¥������ģ��--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','lddm','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldmc','¥������','mhcx','','gygl_third','2');
--���ҹ���ģ��--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','�����Ա�','djcx','qsxb','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
--��λ����ģ��--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','��λ�Ƿ���','djcx','sfbl','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','����','mhcx','','gygl_third','4');
--��λ�������ģ��--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xb','�Ա��޶�','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xymc','ѧԺ����','mhcx','','gygl_third','1');
--��λ��ס����ģ��--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xb','�Ա��޶�','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xymc','ѧԺ����','mhcx','','gygl_third','1');
--���޹���ģ��--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xb','�Ա��޶�','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tsyy','����ԭ��','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tssj','����ʱ��','sjcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xh','ѧ��','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xm','����','mhcx','','gygl_third','2');
--���޹���ģ��--��������--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','xm','����','mhcx','','gygl_third','4');
commit;

--��ͼxg_jcsj_bmdmb���Ϊview_njxyzybj create by cef
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
--�������ԭ�������ʼ����� create by cef
delete from xg_gygl_new_tsyydmb;
commit;
insert into xg_gygl_new_tsyydmb values('1','��ҵ��У');
insert into xg_gygl_new_tsyydmb values('2','ס���춯');
insert into xg_gygl_new_tsyydmb values('3','�ξ�');
insert into xg_gygl_new_tsyydmb values('4','�߶�');
insert into xg_gygl_new_tsyydmb values('5','����');
commit;

--�޸Ĵ�λ��Ϣ��ͼ
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
case when a.xydm is null and a.nj is null then '��' else '��' end sffp,
case when a.xh is null then '��' else '��' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
commit;
------��������Ϊ�汾V5.0.4---------------------
------��������Ϊ�汾V5.0.4--------------------

--ȡ����ס���ؿ��Ʊ�--
--20111024--zhanghui--
-- Create table
create table xg_gygl_new_qxrzkgkzb
(
  xydm varchar2(20),
  nj   varchar2(4),
  sfqy varchar2(10) default '��',
  kssj varchar2(50),
  jssj varchar2(50)
)
;
-- Add comments to the table 
comment on table xg_gygl_new_qxrzkgkzb
  is 'ȡ����ס���ؿ��Ʊ�';
-- Add comments to the columns 
comment on column xg_gygl_new_qxrzkgkzb.xydm
  is 'ѧԺ����';
comment on column xg_gygl_new_qxrzkgkzb.nj
  is '�꼶';
comment on column xg_gygl_new_qxrzkgkzb.sfqy
  is '�Ƿ�����';
comment on column xg_gygl_new_qxrzkgkzb.kssj
  is '��ʼʱ��';
comment on column xg_gygl_new_qxrzkgkzb.jssj
  is '����ʱ��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table xg_gygl_new_qxrzkgkzb
  add constraint pk_qxrzkgkz primary key (XYDM, NJ);
  
--���ӹ���ģ��--
--20111024--zhanghui--
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3800','��������','','1','','��','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380001','����ʱ������','gyglnew_cssz_sjsz.do','1','','��','','');

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3800','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380001','1');

--�޸�ģ�����ƣ�����������Ϣ�����޸�Ϊ��ס����Ϣ����
update gnmkdmb set gnmkmc='ס����Ϣ����' where gnmkdm ='N380203';
commit;

--��ϸ߼���ѯ�޸ĸ߼���ѯ���ñ�����--
--20111024--zhanghui--
update xg_search_szb set tj='ld',zd='lddm' where path like 'gyglnew_%' and (tj ='lddm' or tj='ld');
commit;

--��Ԣ���������桿�������ñ�--
--20111024--zhanghui--
-- Create table
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
  
--�����߼���ѯ����--
--20111024--zhanghui--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','�����Ա�','djcx','qsxb','gygl_third','4');
commit;

--2011-10-25 gaobb ���Ӱ༶����
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select bjmc from view_njxyzybj x where x.bjdm=a.bjdm) cwbjmc,
case when a.xydm is null and a.nj is null then '��' else '��' end sffp,
case when a.xh is null then '��' else '��' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/

-- Create table
create table XG_GYGL_NEW_IMPCWXXB
(
  LDDM VARCHAR2(20),
  QSH  VARCHAR2(20),
  CWH  VARCHAR2(2),
  XH   VARCHAR2(20)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_IMPCWXXB
  is '��λ��Ϣ������ʱ��';
  
-- Add/modify columns 
alter table XG_GYGL_NEW_IMPCWXXB add mark char(1);
alter table XG_GYGL_NEW_IMPCWXXB add bz varchar2(50);
alter table XG_GYGL_NEW_IMPCWXXB modify MARK default '1';
commit;

--���Ӹ߼���ѯ����������ʱ������--
--20111026--zhanghui--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','sf','�Ƿ�����','djcx','sfqy','gygl_third','3');
commit;


--


------------------20111026--��ҽҩ������������
----------------------------�㽭��ҵְҵ������

-------------------------------------------------
--------------2011-10-27--zhanghui---------------
-------------------------------------------------
--���Ӹ߼���ѯ����--
--��λ��Ϣ����--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','��λ�Ƿ����','djcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','��λ�Ƿ���ס','djcx','','gygl_third','8');
--¥����Ϣ����--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldxb','¥���Ա�','djcx','','gygl_third','2');
commit;

--¥�������ֶ����ã���ʱȥ��У�����룬԰������--
delete from dcb where zdssb = 'xg_gygl_new_ldxxb';
commit;
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('lddm','xg_gygl_new_ldxxb','public','¥������');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('ldmc','xg_gygl_new_ldxxb','public','¥������');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('ldxb','xg_gygl_new_ldxxb','public','¥���Ա�');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('ldcs','xg_gygl_new_ldxxb','public','¥������');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('qsch','xg_gygl_new_ldxxb','public','��ʼ���');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('sfhlc','xg_gygl_new_ldxxb','public','�Ƿ�0��');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('bz','xg_gygl_new_ldxxb','public','��ע');
commit;

------------------20111027--�㽭��ҵְҵ������
-------------------------------------

--���ѧ��ס�޺�ͳ�ƹ���
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc)
  values('N380302','ѧ��ס����Ϣ','gyglnew_xszsgl_xszsgl.do','1','','��','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc)
  values('N380303','ѧ��ס��ͳ��','gyglnew_xszstj_xszstj.do','1','','��','','');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380302','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380303','1');
commit;
--2011-10-28 gaobb ѧ��ס�޹�����ͼ
create or replace view xg_view_gygl_new_xszsgl as
select a.xh pk,a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,
b.lddm,b.ldmc,b.ch,b.qsh,b.cwh,(case when b.xh is not null then '��' else '��' end) sfzs 
from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh=b.xh;
/
comment on column xg_view_gygl_new_xszsgl.pk is '����';
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


-------------------------------------------------
--------------2011-10-28--zhanghui---------------
-------------------------------------------------
--���Ӹ߼���ѯ������ѧ��ס����Ϣ��ѯ--
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
commit;

--δס��ѧ����Ϣ��ͼ
create or replace view view_xg_gygl_new_wzsxsxx as
select a.* from  view_xsjbxx a 
where not exists (select 1 from (select a.xh,a.lddm,a.ldmc,a.ch,a.qsh,a.cwh,b.xm,b.xb,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.bjdm,b.nj
from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh where a.xh is not null) b where a.xh=b.xh);

comment on column view_xg_gygl_new_wzsxsxx.xh is 'ѧ��';
comment on column view_xg_gygl_new_wzsxsxx.xm is '����';
comment on column view_xg_gygl_new_wzsxsxx.xb is '�Ա�';
comment on column view_xg_gygl_new_wzsxsxx.xymc is 'ѧԺ����';
comment on column view_xg_gygl_new_wzsxsxx.zymc is 'רҵ����';
comment on column view_xg_gygl_new_wzsxsxx.bjmc is '�༶����';
comment on column view_xg_gygl_new_wzsxsxx.nj is '�꼶';

--ɾ����λ��Ϣ���еġ���λ���ԡ��ֶ�
alter table XG_GYGL_NEW_CWXXB drop column CWSX;


-------------------------------------------------
--------------2011-10-31--zhanghui---------------
-------------------------------------------------
--���Ӹ߼���ѯ��������ѧ��ס��ͳ�ơ����꼶��ѧԺ
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','xy','ѧԺ','djcx','xydm','gygl_third','2');

--���Ӹ߼���ѯ��������������Ϣ�������Ƿ񺬿մ�λsfzm
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sf','�Ƿ񺬿մ�λ','djcx','sfhkcw','gygl_third','5');
commit;

--2011-10-31 gaobb �����Ż����봲λ��ס����-----------
create index index_impcwxxb on XG_GYGL_NEW_IMPCWXXB (lddm, qsh, cwh);

------------------ v-----------------���ѧԺ�Ѿ�����2011-10-31
-------------------------------------���ѧԺ�Ѿ�����2011-10-31

--------------------���ݹ�ҵ԰��ְҵ����ѧԺ�Ѿ�����20111101 by cef
--------------------�ɶ������Ѿ�����20111101 by cef

--2011-11-01 sjf ��Ԣ����Ա��
create table xg_gygl_new_gyfdyb(
yhm varchar2(20),
lddm varchar2(20),
primary key(yhm,lddm)
);
/
comment on table xg_gygl_new_gyfdyb is '��Ԣ����Ա��';
comment on column xg_gygl_new_gyfdyb.yhm is '�û���';
comment on column xg_gygl_new_gyfdyb.lddm is '¥������';
commit;

-------------------------------------------------
--------------2011-11-01--zhanghui---------------
-------------------------------------------------
--������Ϣ�������ֶ�--
alter table xg_gygl_new_tsxxb add lddm varchar2(20);
alter table xg_gygl_new_tsxxb add nj varchar2(10);
alter table xg_gygl_new_tsxxb add xm varchar2(200);
alter table xg_gygl_new_tsxxb add xb varchar2(10);
alter table xg_gygl_new_tsxxb add xydm varchar2(20);
alter table xg_gygl_new_tsxxb add zydm varchar2(50);
alter table xg_gygl_new_tsxxb add bjdm varchar2(100);
alter table xg_gygl_new_tsxxb add xymc varchar2(200);
alter table xg_gygl_new_tsxxb add zymc varchar2(200);
alter table xg_gygl_new_tsxxb add bjmc varchar2(200);
alter table xg_gygl_new_tsxxb add rzsj varchar2(50);
alter table xg_gygl_new_tsxxb add rzczr varchar2(100);

--���ӹ���ģ��--������Ϣ��ѯ--
update gnmkdmb set dyym='gyglnew_zsxxgl_zsxxgl.do' where gnmkdm ='N380203';
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc)
  values('N380304','������Ϣ��ѯ','gyglnew_tsgl_tsgl.do','1','','��','','');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380304','1');
update gnmkdmb set gnmkmc='ͳ�Ʋ�ѯ' where gnmkdm ='N3803';
commit;

--�޸ı�ṹ sjf---------
alter table xg_gygl_new_tsxxb add czsj varchar2(50) default to_char(sysdate,'yyyy-MM-dd HH24:mi:ss');
alter table xg_gygl_new_tsxxb add constraint tsxx_primary_id primary key(xh,czsj);

comment on column xg_gygl_new_tsxxb.czsj
  is '����ʱ��';  

--2011-11-01 gaobb--
-- Add/modify columns 
alter table XG_GYGL_NEW_CWXXB add rzsj varchar2(50) default to_char(sysdate,'yyyy-mm-dd');
alter table XG_GYGL_NEW_CWXXB add rzczr varchar2(100);
-- Add comments to the columns 
comment on column XG_GYGL_NEW_CWXXB.rzsj
  is '��סʱ��';
comment on column XG_GYGL_NEW_CWXXB.rzczr
  is '��ס������';
--������סʱ�����ס�������ֶ�
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


-------------------------------------------------
--------------2011-11-02--zhanghui---------------
-------------------------------------------------
--���Ӹ߼���ѯ����--ס����Ϣ����--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','��λ�Ƿ���','djcx','sfbl','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sffp','��λ�Ƿ����','djcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','��λ�Ƿ���ס','djcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','����','mhcx','','gygl_third','4');
commit;

--2011-11-2
-- Add/modify columns 
alter table XG_GYGL_NEW_IMPCWXXB add rzsj varchar2(50);
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
comment on column XG_GYGL_NEW_IMPCWXXB.rzsj
  is '��סʱ��';
  


-------------------------------------------------
--------------2011-11-03--zhanghui---------------
-------------------------------------------------
--�߼���ѯ�����޸�
--���޹���ģ��--
delete from xg_search_szb where path like 'gyglnew_tsgl%';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','nj','�꼶','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xy','ѧԺ','djcx','xydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xb','�Ա�','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','rzsj','��סʱ��','sjcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tssj','����ʱ��','sjcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tsyy','����ԭ��','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xh','ѧ��','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xm','����','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','qsh','���Һ�','mhcx','','gygl_third','3');
commit;
--��λ��Ϣ����
delete from xg_search_szb where path like 'gyglnew_cwgl_cwgl.do';
commit;
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
commit;
--ס����Ϣ����
delete from xg_search_szb where path like 'gyglnew_zsxxgl_zsxxgl.do';
commit;
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
commit;

--������Ϣ��comment
comment on column xg_gygl_new_tsxxb.lddm
  is '¥������';
comment on column xg_gygl_new_tsxxb.nj
  is '�꼶';
comment on column xg_gygl_new_tsxxb.xm
  is '����';
comment on column xg_gygl_new_tsxxb.xb
  is '�Ա�';
comment on column xg_gygl_new_tsxxb.xydm
  is 'ѧԺ����';
comment on column xg_gygl_new_tsxxb.zydm
  is 'רҵ����';
comment on column xg_gygl_new_tsxxb.bjdm
  is '�༶����';
comment on column xg_gygl_new_tsxxb.xymc
  is 'ѧԺ����';  
comment on column xg_gygl_new_tsxxb.zymc
  is 'רҵ����';
comment on column xg_gygl_new_tsxxb.bjmc
  is '�༶����';
comment on column xg_gygl_new_tsxxb.rzsj
  is '��סʱ��';
comment on column xg_gygl_new_tsxxb.rzczr
  is '��ס������';  
comment on column xg_gygl_new_tsxxb.czsj
  is '����ʱ��';  

-----xsgzgl_tybb�Ѿ�ִ��by�¶���20111103

-------------------------------------------------
--------------2011-11-04--zhanghui---------------
-------------------------------------------------
--�߼���ѯ�����޸�
--����ʱ������
delete from xg_search_szb where path like 'gyglnew_cssz_sjsz.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','sf','�Ƿ�����','djcx','sfqy','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xymc','ѧԺ����','mhcx','','gygl_third','1');
commit;

--2011-11-04 gaobb ȥ��Ĭ��ʱ�� 
alter table XG_GYGL_NEW_CWXXB modify RZSJ default null;

--2011-10-04 ѧ��ס�޹�����ͼ�޸�
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
-----xsgzgl_tybb�Ѿ�ִ��by�¶���20111107
-----xsgzgl_setup�Ѿ�ִ��by�¶���20111107
------��������Ϊ�汾V5.0.5---------------------
------��������Ϊ�汾V5.0.5--------------------
--------------2011-11-09--zhanghui---------------
-------------------------------------------------
--�߼���ѯ--��λ���䣨ѧԺ���༶��
delete from xg_search_szb where path like 'gyglnew_cwfpgl_cwfp_xy.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','zy','רҵ','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','bj','�༶','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xb','�Ա��޶�','djcx','','gygl_third','5');
commit;
--�߼���ѯ--��λ��ס��ѧԺ-�༶��
delete from xg_search_szb where path like 'gyglnew_cwrzgl_cwrz_xy.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','nj','�꼶','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xy','ѧԺ','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','zy','רҵ','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','bj','�༶','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xb','�Ա��޶�','djcx','','gygl_third','5');
commit;
--�߼���ѯ--��λ��Ϣ����
delete from xg_search_szb where path like 'gyglnew_cwgl_cwgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','zy','רҵ','djcx','zydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','bj','�༶','djcx','bjdm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xb','��λ�Ա�','djcx','qsxb','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','�Ƿ�����λ','djcx','sfbl','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','�Ƿ����ѧԺ','djcx','sffpxy','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','�Ƿ���ס','djcx','','gygl_third','11');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','����','mhcx','','gygl_third','4');
commit;
--�߼���ѯ--ס����Ϣ����
delete from xg_search_szb where path like 'gyglnew_zsxxgl_zsxxgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','zy','רҵ','djcx','zydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','bj','�༶','djcx','bjdm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xb','��λ�Ա�','djcx','qsxb','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','�Ƿ�����λ','djcx','sfbl','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','�Ƿ���ס','djcx','','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','����','mhcx','','gygl_third','4');
commit;
--��λ��Ϣ��ͼ�������Ƿ����ѧԺ���Ƿ����༶--
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from view_njxyzybj x where x.bjdm=a.bjdm) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '��' else '��' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '��' else '��' end sffpbj,
case when a.xh is null then '��' else '��' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '��' else '��' end) sfzs,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=b.lddm) ldmc,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch 
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh;
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

--2011-11-14 gaobb ���Ӱ༶����������ֶ�
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '��' else '��' end) sfzs,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=b.lddm) ldmc,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh;
/

comment on column xg_view_gygl_new_xszsgl.xh is 'ѧ��';
comment on column xg_view_gygl_new_xszsgl.xm is '����';
comment on column xg_view_gygl_new_xszsgl.xb is '�Ա�';
comment on column xg_view_gygl_new_xszsgl.nj is '�꼶';
comment on column xg_view_gygl_new_xszsgl.xydm is 'ѧԺ����';
comment on column xg_view_gygl_new_xszsgl.xymc is 'ѧԺ����';
comment on column xg_view_gygl_new_xszsgl.lddm is '¥������';
comment on column xg_view_gygl_new_xszsgl.qsh is '���Һ�';
comment on column xg_view_gygl_new_xszsgl.cwh is '��λ��';
comment on column xg_view_gygl_new_xszsgl.sfzs is '�Ƿ�ס��';
comment on column xg_view_gygl_new_xszsgl.ldmc is '¥������';
comment on column xg_view_gygl_new_xszsgl.ch is '���';
comment on column xg_view_gygl_new_xszsgl.bjdm is '�༶����';
comment on column xg_view_gygl_new_xszsgl.bjmc is '�༶����';

-------------------------------------------------
--------------2011-11-15--zhanghui---------------
-------------------------------------------------
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz) 
  values('N3804','��Ԣ����','','1','','��','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz) 
  values('N380401','�ҵı�������','gyglnew_gybxgl_gybxgl_stu.do','1','','��','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz) 
  values('N380402','�����������','gyglnew_gybxgl_gybxgl.do','1','','��','');

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3804','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380401','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380402','1');
commit;

-- ��λ��Ϣ��ͼ�����ӡ����ҵ绰��
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,b.qsdh,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from view_njxyzybj x where x.bjdm=a.bjdm) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '��' else '��' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '��' else '��' end sffpbj,
case when a.xh is null then '��' else '��' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
-- ���ӹ�Ԣ���ޱ�
-- Create table
create table XG_GYGL_NEW_GYBXB
(
  LDDM      VARCHAR2(20),
  QSH       VARCHAR2(20),
  CWH       VARCHAR2(20),
  XH        VARCHAR2(20),
  BXNR      VARCHAR2(1000),
  BXSJ      VARCHAR2(20) default to_char(sysdate,'yyyy-MM-dd HH24:mi:ss'),
  JJCD      VARCHAR2(20),
  QWWXSJ_KS VARCHAR2(20),
  QWWXSJ_JS VARCHAR2(20),
  LXDH      VARCHAR2(20),
  CLZT      VARCHAR2(20) default 'δ����',
  BCLYY     VARCHAR2(200),
  WXRY      VARCHAR2(50),
  WXSJ      VARCHAR2(20),
  WXFY      VARCHAR2(20),
  WXNR      VARCHAR2(1000),
  MYCD      VARCHAR2(20),
  PJ        VARCHAR2(500)
);
-- Add comments to the columns 
comment on column XG_GYGL_NEW_GYBXB.LDDM
  is '¥������';
comment on column XG_GYGL_NEW_GYBXB.QSH
  is '���Һ�';
comment on column XG_GYGL_NEW_GYBXB.CWH
  is '��λ��';
comment on column XG_GYGL_NEW_GYBXB.XH
  is 'ѧ��';
comment on column XG_GYGL_NEW_GYBXB.BXNR
  is '��������';
comment on column XG_GYGL_NEW_GYBXB.BXSJ
  is '����ʱ��';
comment on column XG_GYGL_NEW_GYBXB.JJCD
  is '�����̶�';
comment on column XG_GYGL_NEW_GYBXB.QWWXSJ_KS
  is '����ά��ʱ��';
comment on column XG_GYGL_NEW_GYBXB.QWWXSJ_JS
  is '����ά��ʱ��';
comment on column XG_GYGL_NEW_GYBXB.LXDH
  is '��ϵ�绰';
comment on column XG_GYGL_NEW_GYBXB.CLZT
  is '����״̬';
comment on column XG_GYGL_NEW_GYBXB.BCLYY
  is '������ԭ��';
comment on column XG_GYGL_NEW_GYBXB.WXRY
  is 'ά����Ա';
comment on column XG_GYGL_NEW_GYBXB.WXSJ
  is 'ά��ʱ��';
comment on column XG_GYGL_NEW_GYBXB.WXFY
  is 'ά�޷���';
comment on column XG_GYGL_NEW_GYBXB.WXNR
  is 'ά������';
comment on column XG_GYGL_NEW_GYBXB.MYCD
  is '����̶�';
comment on column XG_GYGL_NEW_GYBXB.PJ
  is '����';

--�߼���ѯ��������--
--�ҵı�������--
delete from xg_search_szb where path like 'gyglnew_gybxgl_gybxgl_stu.do';
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl_stu.do','clzt','����״̬','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl_stu.do','jjcd','�����̶�','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl_stu.do','bxsj','����ʱ��','sjcx','','gygl_third','1');
commit;
delete from xg_search_szb where path like 'gyglnew_gybxgl_gybxgl.do';
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','clzt','����״̬','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','jjcd','�����̶�','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','wxsj','ά��ʱ��','sjcx','','gygl_third','1');
commit;
--20111123 �㶫����������

--2011-11-23 gaobb ѧ��ס��ͳ�Ƹ߼���ѯ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','nj','�꼶','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','xymc','ѧԺ','mhcx','','gygl_third','2');
commit;
--20111123 ���ݹ�ҵ԰ְҵѧԺ������

------��������Ϊ�汾V5.0.6---------------------
------��������Ϊ�汾V5.0.6--------------------
-----xsgzgl_tybb�Ѿ�ִ��by�¶���20111125
-----xsgzgl_setup�Ѿ�ִ��by�¶���20111125

--2011-11-25 gaobb
-- Create table
create table xg_gygl_new_impqsxxb
(
  lddm varchar2(20),
  qsh  varchar2(20),
  drzd varchar2(20),
  mark char(1) default '1'
)
;
-- Add comments to the table 
comment on table xg_gygl_new_impqsxxb
  is '������Ϣ������±�';
-- Add comments to the columns 
comment on column xg_gygl_new_impqsxxb.lddm
  is '¥������';
comment on column xg_gygl_new_impqsxxb.qsh
  is '���Һ�';
comment on column xg_gygl_new_impqsxxb.drzd
  is '�����ֶ�';
comment on column XG_GYGL_NEW_IMPQSXXB.mark
  is '���';

-- sjf
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_mrjlgl_mrjlgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_mrjlgl_mrjlgl.do','zbry','ֵ����Ա','djcx','zbrydm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_mrjlgl_mrjlgl.do','kssj','ʱ��','sjcx','sj','gygl_third','7');
commit;

insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_mrjlgl_mrjlgl.do','zbrymc','ֵ����Ա','mhcx','1');
insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_mrjlgl_mrjlgl.do','ldmc','¥��','mhcx','1');
insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_mrjlgl_mrjlgl.do','sj','ʱ��','mhcx','1');
commit;

--sjf ��Ԣ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_gybxgl_gybxgl.do','ldmc','¥��','mhcx','1');
commit;

--------------------------------------------------
-----xsgzgl_tybb�Ѿ�ִ��by�·ҷ�20111202
-----xsgzgl_setupδִ��

--20111202--zhanghui--
--���Ӹ߼���ѯ��������������Ϣ����
delete from xg_search_szb where path  ='gyglnew_qsgl_qsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','�����Ա�','djcx','qsxb','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','rzqk','������ס���','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sffp','���ҷ������','djcx','','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
commit;

--������Ϣ��ͼ
create or replace view view_xg_gygl_new_qsxx as
select a.*,ptcws-yzcws wzcws,case when xydm is not null then '��' else '��' end sffp,
case when yzcws=0 then 'ȫ��' when yzcws=ptcws then '��Ա' else 'ȱ��' end rzqk from (
select a.*,b.ldmc,b.ldxb,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select count(*) from xg_gygl_new_cwxxb x where a.lddm = x.lddm and a.qsh = x.qsh) qscws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh) blcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh) ptcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh and xh is not null) yzcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh and xydm is not null) yfpcws
from xg_gygl_new_qsxxb a left join xg_gygl_new_ldxxb b on a.lddm = b.lddm order by a.lddm,to_number(a.ch),a.qsh) a;

--20111205--zhanghui--
--�޸�ѧ��ס����Ϣ��ͼ
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '��' else '��' end) sfzs,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=b.lddm) ldmc,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh;
/
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XH is 'ѧ��';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XM is '����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XB is '�Ա�';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.NJ is '�꼶';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYDM is 'ѧԺ����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYMC is 'ѧԺ����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJDM is '�༶����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJMC is '�༶����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDDM is '¥������';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.QSH is '���Һ�';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CWH is '��λ��';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.SFZS is '�Ƿ�ס��';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDMC is '¥������';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CH is '���';

--ѧ��ס����Ϣ��ѯ--����רҵ���༶��ѯ����
delete from xg_search_szb where path like 'gyglnew_xszsgl_xszsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ld','¥��','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ch','���','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','����','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','nj','�꼶','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xy','ѧԺ','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','zy','רҵ','djcx','zydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','bj','�༶','djcx','bjdm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','sf','�Ƿ�ס��','djcx','sfzs','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ldmc','¥������','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','���Һ�','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xh','ѧ��','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xm','����','mhcx','','gygl_third','4');
commit;
-----xsgzgl_tybb�Ѿ�ִ��by�·ҷ�20111207
-----���ݹ�ҵ԰��ְҵ����ѧԺ �Ѿ����� by cff
-----�ɶ���֯�ߵ�ר��ѧУ  ������ by cff
-----�㽭����ְҵ����ѧԺ ������ by cff

--2011-11-9 gaobb ���ӹ�Ԣ������Ա��
-- Create table
create table xg_gygl_new_gyglryb
(
  xh     varchar2(20) not null,
  lddm   varchar2(20) not null,
  ch     varchar2(2),
  qsh    varchar2(20),
  rzzt   varchar2(10),
  rzksrq varchar2(20),
  rzjsrq varchar2(20),
  lxdh   varchar2(30),
  bz     varchar2(200)
)
;
-- Add comments to the table 
comment on table xg_gygl_new_gyglryb
  is '��Ԣ����-��Ԣ������Ա��';
-- Add comments to the columns 
comment on column xg_gygl_new_gyglryb.xh
  is 'ѧ��';
comment on column xg_gygl_new_gyglryb.lddm
  is '¥������';
comment on column xg_gygl_new_gyglryb.ch
  is '���';
comment on column xg_gygl_new_gyglryb.qsh
  is '���Һ�';
comment on column xg_gygl_new_gyglryb.rzzt
  is '��ְ״̬';
comment on column xg_gygl_new_gyglryb.rzksrq
  is '��ְ��ʼ����';
comment on column xg_gygl_new_gyglryb.rzjsrq
  is '��ְ��������';
comment on column xg_gygl_new_gyglryb.lxdh
  is '��ϵ�绰';
comment on column xg_gygl_new_gyglryb.bz
  is '��ע';

create or replace view xg_view_gygl_new_gyglryb as 
select a.lddm,a.ch,a.qsh,a.xb,a.gllx,(case when c.xh is null then '��' else '��' end) sffp,
b.ldmc,(case when a.ch='#' then '#' when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
c.xh,c.lxdh,c.bz,d.xm,f.qsdh
from ( 
select lddm,'#' ch,'#' qsh,ldxb xb,'¥��' gllx from xg_gygl_new_ldxxb
union all
select lddm,ch,'#' qsh,qsxb,'�㳤' gllx from xg_gygl_new_qsxxb group by lddm,ch,qsxb
union all
select lddm,ch,qsh,qsxb,'���ҳ�' gllx from xg_gygl_new_qsxxb
) a left join xg_gygl_new_ldxxb b on a.lddm=b.lddm
left join xg_gygl_new_gyglryb c on a.lddm=c.lddm and a.ch=c.ch and a.qsh=c.qsh
left join view_xsjbxx d on c.xh=d.xh 
left join xg_gygl_new_cwxxb e on e.xh=c.xh
left join xg_gygl_new_qsxxb f on f.lddm=e.lddm and f.qsh=e.qsh
where nvl(c.rzzt,' ')<>'����'
order by lddm,ch,qsh;
/
comment on column xg_view_gygl_new_gyglryb.lddm is '¥������';
comment on column xg_view_gygl_new_gyglryb.ch is '���';
comment on column xg_view_gygl_new_gyglryb.qsh is '���Һ�';
comment on column xg_view_gygl_new_gyglryb.xb is '�����Ա�';
comment on column xg_view_gygl_new_gyglryb.sffp is '�Ƿ���������Ա';
comment on column xg_view_gygl_new_gyglryb.ldmc is '¥������';
comment on column xg_view_gygl_new_gyglryb.chmc is '�������';
comment on column xg_view_gygl_new_gyglryb.xh is 'ѧ��';
comment on column xg_view_gygl_new_gyglryb.gllx is '��������';
comment on column xg_view_gygl_new_gyglryb.lxdh is '������Ա�绰';
comment on column xg_view_gygl_new_gyglryb.bz is '��ע';
comment on column xg_view_gygl_new_gyglryb.xm is '������Ա����';
comment on column xg_view_gygl_new_gyglryb.qsdh is '������Ա���ҵ绰';

insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3805','���ҳ�ά��','','1','','��','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380501','���ҳ�ά��','gyglnew_gyglry_gyglry.do','1','','��','','');

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3805','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380501','1');
commit;

--2011-12-13
create or replace view xg_view_gygl_new_gyglryb as 
select a.*,(case when a.xh is null then '��' else '��' end) sffp,
b.ldmc,(case when a.ch='#' then '#' when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
c.xm
 from (
select a.lddm,'#' ch,'#' qsh,ldxb xb,'¥��' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_ldxxb a
left join 
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c 
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch='#' and a.qsh='#' and a.rzzt='����'
) b
on a.lddm=b.lddm
union all
select a.lddm,a.ch,'#' qsh,a.qsxb xb,'�㳤' gllx,b.xh,b.lxdh,b.bz,b.qsdh from 
(select lddm,ch,qsxb from xg_gygl_new_qsxxb group by lddm,ch,qsxb) a 
left join 
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch=c.ch and a.qsh='#' and a.rzzt='����'
) b
on a.lddm=b.lddm and a.ch=b.ch
union all
select a.lddm,a.ch,a.qsh,a.qsxb xb,'���ҳ�' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_qsxxb a
left join 
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and a.qsh=b.qsh and b.lddm=c.lddm and b.qsh=c.qsh and a.rzzt='����'
) b
on a.lddm=b.lddm and a.ch=b.ch and a.qsh=b.qsh ) a
left join xg_gygl_new_ldxxb b on a.lddm=b.lddm 
left join view_xsjbxx c on a.xh=c.xh 
order by a.lddm,a.ch,a.qsh;
/
comment on column xg_view_gygl_new_gyglryb.lddm is '¥������';
comment on column xg_view_gygl_new_gyglryb.ch is '���';
comment on column xg_view_gygl_new_gyglryb.qsh is '���Һ�';
comment on column xg_view_gygl_new_gyglryb.xb is '�����Ա�';
comment on column xg_view_gygl_new_gyglryb.sffp is '�Ƿ���������Ա';
comment on column xg_view_gygl_new_gyglryb.ldmc is '¥������';
comment on column xg_view_gygl_new_gyglryb.chmc is '�������';
comment on column xg_view_gygl_new_gyglryb.xh is 'ѧ��';
comment on column xg_view_gygl_new_gyglryb.gllx is '��������';
comment on column xg_view_gygl_new_gyglryb.lxdh is '������Ա�绰';
comment on column xg_view_gygl_new_gyglryb.bz is '��ע';
comment on column xg_view_gygl_new_gyglryb.xm is '������Ա����';
comment on column xg_view_gygl_new_gyglryb.qsdh is '������Ա���ҵ绰';

insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ld','¥��','djcx','lddm','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ch','���','djcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','����','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldxb','�Ա�','djcx','xb','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','gygllx','����','djcx','gllx','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','sf','�Ƿ����','djcx','sffp','','6');

insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldmc','¥������','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','���Һ�','mhcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xh','ѧ��','mhcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xm','����','mhcx','','','4');
commit;
------��������Ϊ�汾V5.0.7---------------------
------��������Ϊ�汾V5.0.7--------------------
-----xsgzgl_setup�Ѿ�ִ��by�¶���20111213

--20120301--zhuanghui--
--��λ��Ϣ��ͼ�޸�
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,b.qsdh,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from 
(select xh,zydm from bks_xsjbxx a where not exists (select 1 from xsxxb b where a.xh = b.xh) 
union all select a.xh,a.zydm from xsxxb a 
where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null))
 x where x.xh=a.xh ) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '��' else '��' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '��' else '��' end sffpbj,
case when a.xh is null then '��' else '��' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/

--20120307 gaobb
--У����԰������
--��ʼ����Ԣ����������ñ�
delete from xg_gygl_new_jbszb;
commit;
insert into xg_gygl_new_jbszb (csdm,csmc,csz,bz) values('xqbj','У�����','1','0����У����1������У����');
insert into xg_gygl_new_jbszb (csdm,csmc,csz,bz) values('cwfpdx','�������','xydm','xydm�����䴲λ��ѧУ�����ѧԺ��ѧԺ������ס��bjdm�����䴲λ��ѧУ�����ѧԺ��ѧԺ������༶�������ΰ�����ס');
insert into xg_gygl_new_jbszb (csdm,csmc,csz,bz) values('yqbj','԰�����','1','0����У����1������԰����');
commit;

--��λ��Ϣ��ͼ
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,b.qsdh,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from
(select xh,zydm from bks_xsjbxx a where not exists (select 1 from xsxxb b where a.xh = b.xh)
union all select a.xh,a.zydm from xsxxb a
where (sfyby = '��' or sfyby is null) and (sfzx = '��У' or sfzx is null))
 x where x.xh=a.xh ) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '��' else '��' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '��' else '��' end sffpbj,
case when a.xh is null then '��' else '��' end sfrz,
c.xqdm,(select xqmc from dm_zju_xq x where x.dm = c.xqdm) xqmc,
c.yqdm,(select yqmc from zxbz_ssyqdm x where x.yqdm = c.yqdm) yqmc
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
--������Ϣ��ͼ
create or replace view view_xg_gygl_new_qsxx as
select a.*,ptcws-yzcws wzcws,case when xydm is not null then '��' else '��' end sffp,
case when yzcws=0 then 'ȫ��' when yzcws=ptcws then '��Ա' else 'ȱ��' end rzqk from (
select a.*,b.ldmc,b.ldxb,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select count(*) from xg_gygl_new_cwxxb x where a.lddm = x.lddm and a.qsh = x.qsh) qscws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh) blcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh) ptcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh and xh is not null) yzcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='��' and a.lddm = x.lddm and a.qsh = x.qsh and xydm is not null) yfpcws,
b.xqdm,(select xqmc from dm_zju_xq x where x.dm = b.xqdm) xqmc,
b.yqdm,(select yqmc from zxbz_ssyqdm x where x.yqdm = b.yqdm) yqmc
from xg_gygl_new_qsxxb a left join xg_gygl_new_ldxxb b on a.lddm = b.lddm order by a.lddm,to_number(a.ch),a.qsh) a;
/
--ѧ��ס����Ϣ��ѯ��ͼ
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '��' else '��' end) sfzs,
c.ldmc,c.xqdm,c.yqdm,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh
left join xg_gygl_new_ldxxb c on b.lddm = c.lddm;
/
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XH is 'ѧ��';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XM is '����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XB is '�Ա�';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.NJ is '�꼶';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYDM is 'ѧԺ����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYMC is 'ѧԺ����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJDM is '�༶����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJMC is '�༶����';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDDM is '¥������';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.QSH is '���Һ�';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CWH is '��λ��';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.SFZS is '�Ƿ�ס��';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDMC is '¥������';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CH is '���';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XQDM is 'У������';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.YQDM is '԰������';
--��Ԣ������Ա��ͼ
create or replace view xg_view_gygl_new_gyglryb as
select a.*,(case when a.xh is null then '��' else '��' end) sffp,
b.ldmc,b.xqdm,b.yqdm,(case when a.ch='#' then '#' when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
c.xm
 from (
select a.lddm,'#' ch,'#' qsh,ldxb xb,'¥��' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_ldxxb a
left join
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch='#' and a.qsh='#' and a.rzzt='����'
) b
on a.lddm=b.lddm
union all
select a.lddm,a.ch,'#' qsh,a.qsxb xb,'�㳤' gllx,b.xh,b.lxdh,b.bz,b.qsdh from
(select lddm,ch,qsxb from xg_gygl_new_qsxxb group by lddm,ch,qsxb) a
left join
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch=c.ch and a.qsh='#' and a.rzzt='����'
) b
on a.lddm=b.lddm and a.ch=b.ch
union all
select a.lddm,a.ch,a.qsh,a.qsxb xb,'���ҳ�' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_qsxxb a
left join
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and a.qsh=b.qsh and b.lddm=c.lddm and b.qsh=c.qsh and a.rzzt='����'
) b
on a.lddm=b.lddm and a.ch=b.ch and a.qsh=b.qsh ) a
left join xg_gygl_new_ldxxb b on a.lddm=b.lddm
left join view_xsjbxx c on a.xh=c.xh
order by a.lddm,a.ch,a.qsh;
/
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.LDDM is '¥������';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.CH is '���';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.QSH is '���Һ�';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XB is '�����Ա�';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.GLLX is '��������';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XH is 'ѧ��';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.LXDH is '������Ա�绰';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.BZ is '��ע';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.QSDH is '������Ա���ҵ绰';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.SFFP is '�Ƿ���������Ա';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.LDMC is '¥������';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.CHMC is '�������';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XM is '������Ա����';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XQDM is 'У������';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.YQDM is '԰������';


--����¥����Ϣ�߼���ѯ������У����
--�߼���ѯ--¥����Ϣ����--
delete from xg_search_szb where path = 'gyglnew_ldgl_ldgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','xqdm','У��','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','yqdm','԰��','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ld','¥��','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','lddm','¥������','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldmc','¥������','mhcx','','gygl_third','3');
commit;
--�߼���ѯ-������Ϣ����
delete from xg_search_szb where path = 'gyglnew_qsgl_qsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xqdm','У��','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','yqdm','԰��','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','¥��','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','���','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','����','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','�����Ա�','djcx','qsxb','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','rzqk','������ס���','djcx','','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sffp','���ҷ������','djcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','¥������','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','���Һ�','mhcx','','gygl_third','3');
commit;
--�߼���ѯ--��λ��Ϣ����
delete from xg_search_szb where path = 'gyglnew_cwgl_cwgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xqdm','У��','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','yqdm','԰��','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','¥��','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','���','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','����','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','�꼶','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','ѧԺ','djcx','xydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','zy','רҵ','djcx','zydm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','bj','�༶','djcx','bjdm','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xb','��λ�Ա�','djcx','qsxb','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','�Ƿ�����λ','djcx','sfbl','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','�Ƿ����ѧԺ','djcx','sffpxy','gygl_third','11');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','�Ƿ���ס','djcx','','gygl_third','12');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','¥������','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','���Һ�','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','ѧ��','mhcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','����','mhcx','','gygl_third','5');
commit;
--�߼���ѯ--ס����Ϣ����
delete from xg_search_szb where path = 'gyglnew_zsxxgl_zsxxgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xqdm','У��','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','yqdm','԰��','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','¥��','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','���','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','����','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','�꼶','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','ѧԺ','djcx','xydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','zy','רҵ','djcx','zydm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','bj','�༶','djcx','bjdm','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xb','��λ�Ա�','djcx','qsxb','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','�Ƿ�����λ','djcx','sfbl','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','�Ƿ���ס','djcx','','gygl_third','11');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','¥������','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','���Һ�','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','ѧ��','mhcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','����','mhcx','','gygl_third','5');
commit;
--�߼���ѯ--ѧ��ס����Ϣ��ѯ
delete from xg_search_szb where path = 'gyglnew_xszsgl_xszsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xqdm','У��','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','yqdm','԰��','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ld','¥��','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ch','���','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','����','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','nj','�꼶','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xy','ѧԺ','djcx','xydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','zy','רҵ','djcx','zydm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','bj','�༶','djcx','bjdm','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','sf','�Ƿ�ס��','djcx','sfzs','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ldmc','¥������','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','���Һ�','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xh','ѧ��','mhcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xm','����','mhcx','','gygl_third','5');
commit;
--�߼���ѯ--��Ԣ������Ա
delete from xg_search_szb where path = 'gyglnew_gyglry_gyglry.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xqdm','У��','djcx','','','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','yqdm','԰��','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ld','¥��','djcx','lddm','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ch','���','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','����','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldxb','�Ա�','djcx','xb','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','gygllx','����','djcx','gllx','','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','sf','�Ƿ����','djcx','sffp','','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldmc','¥������','mhcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','���Һ�','mhcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xh','ѧ��','mhcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xm','����','mhcx','','','5');
commit;

--������Ԣ������Ա��Ϣ��ͼ
create or replace view view_xg_gygl_new_gyglryb as
select xh,
case when ch = '#' and qsh = '#' then '¥��'
when ch !='#' and qsh ='#' then '�㳤'  
when ch !='#' and qsh !='#' then '���ҳ�' end zwmc,
case when ch = '#' and qsh = '#' then b.ldmc
when ch !='#' and qsh ='#' then b.ldmc || ','|| (case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) 
when ch !='#' and qsh !='#' then b.ldmc|| ','|| (case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) ||','|| a.qsh end bz
from xg_gygl_new_gyglryb a left join xg_gygl_new_ldxxb b on a.lddm = b.lddm where rzzt = '����';
/

---------------------------------------------
-------------------sql���ύ-----------------
---------------------------------------------

--2012-03-19 gaobb ��ֲ������鵽���ݴ�ѧ
insert into gnmkdmb values('N3806','�������','','1','','��','','');
insert into gnmkdmb values('N380601','��������','gzdx_gygl_wsjc_cssz.do','1','','��','','');
insert into gnmkdmb values('N380602','�հױ����γ�','gzdx_gygl_wsjc_kbbb.do','1','','��','','');
insert into gnmkdmb values('N380603','������¼��','gzdx_gygl_wsjc_fslr.do','1','','��','','');
insert into gnmkdmb values('N380604','�����ֲ�ѯ','gzdx_gygl_wsjc_fscx.do','1','','��','','');
insert into gnmkdmb values('N380605','��������ά��','gzdx_gygl_wsjc_tsqs.do','1','','��','','');
insert into gnmkdmb values('N380606','������ͳ��','gzdx_gygl_wsjc_fstj.do','1','','��','','');
insert into gnmkdmb values('N380607','ѧ��������¼��','gzdx_gygl_wsjc_xsfslr.do','1','','��','','');
insert into gnmkdmb values('N380608','ѧ�������ֲ鿴','gzdx_gygl_wsjc_xsfsck.do','1','','��','','');


insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3806','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380601','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380602','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380603','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380604','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380605','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380606','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380607','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380608','1');
commit;

--2012-3-22--zhanghui--
--������¼�����Ӹ߼���ѯ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','xqdm','У��','djcx','','','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','yqdm','԰��','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','ld','¥��','djcx','lddm','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','ch','���','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','qsh','����','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','xy','ѧԺ','djcx','xydm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','sf','�Ƿ���','djcx','sfdf','','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','jcsj','���ʱ��','dgsjcx','','','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','ldmc','¥������','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','qsh','���Һ�','mhcx','','','2');
--�����ֲ�ѯ���Ӹ߼���ѯ����
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','xqdm','У��','djcx','','','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','yqdm','԰��','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','ld','¥��','djcx','lddm','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','ch','���','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','qsh','����','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','xy','ѧԺ','djcx','xydm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','sf','�Ƿ���','djcx','sfdf','','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','jcsj','���ʱ��','dgsjcx','','','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','ldmc','¥������','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','qsh','���Һ�','mhcx','','','2');

commit;

--2012-03-21 gaobb ���������������ȹ���ģ��     ���ݴ�ѧ����
insert into gnmkdmb values('N3807','��������','','1','','��','','');
insert into gnmkdmb values('N380701','�������Ҹ���ά��','gzdx_gygl_wmqs_qsgswh.do','1','','��','','');
insert into gnmkdmb values('N380702','������������','gzdx_gygl_wmqs_qssq.do','1','','��','','');
insert into gnmkdmb values('N380703','�����������','gzdx_gygl_wmqs_qssh.do','1','','��','','');
insert into gnmkdmb values('N380704','�������ҹ���','gzdx_gygl_wmqs_qsgl.do','1','','��','','');

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3807','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380701','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380702','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380703','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380704','1');
commit;

--2012-03-22 gaobb
-- Create table
create table xg_gygl_new_gzdx_wmqsgsb
(
  nd     varchar2(4) not null,
  xydm   varchar2(10) not null,
  wmqsgs varchar2(8)
)
;
-- Add comments to the table 
comment on table xg_gygl_new_gzdx_wmqsgsb
  is '��Ԣ����-�������Ҹ�����(���ݴ�ѧ)';
-- Add comments to the columns 
comment on column xg_gygl_new_gzdx_wmqsgsb.nd
  is '���';
comment on column xg_gygl_new_gzdx_wmqsgsb.xydm
  is 'ѧԺ����';
comment on column xg_gygl_new_gzdx_wmqsgsb.wmqsgs
  is '�������Ҹ���';
-- Create/Recreate primary, unique and foreign key constraints 
alter table xg_gygl_new_gzdx_wmqsgsb
  add constraint wmqsgsb_pri primary key (ND, XYDM);

--2012-03-23 gaobb 
-- Create table
create table xg_gygl_new_gzdx_wmqssqb
(
  nd      varchar2(4) not null,
  lddm    varchar2(20) not null,
  qsh     varchar2(20) not null,
  qsrs    varchar2(2),
  wspjf   varchar2(8),
  sqsm    varchar2(1000),
  sqr     varchar2(20),
  sqsj    varchar2(10),
  fdyshzt varchar2(10) default 'δ���',
  fdyshr  varchar2(20),
  fdyshsj varchar2(10),
  fdyshbz varchar2(200),
  xxshzt  varchar2(10) default 'δ���',
  xxshr   varchar2(20),
  xxshsj  varchar2(10),
  xxshbz  varchar2(200)
)
;
-- Add comments to the table 
comment on table xg_gygl_new_gzdx_wmqssqb
  is '��Ԣ����-�������������(���ݴ�ѧ)';
-- Add comments to the columns 
comment on column xg_gygl_new_gzdx_wmqssqb.nd
  is '���';
comment on column xg_gygl_new_gzdx_wmqssqb.lddm
  is '¥������';
comment on column xg_gygl_new_gzdx_wmqssqb.qsh
  is '���Һ�';
comment on column xg_gygl_new_gzdx_wmqssqb.qsrs
  is '��������';
comment on column xg_gygl_new_gzdx_wmqssqb.wspjf
  is '����ƽ����';
comment on column xg_gygl_new_gzdx_wmqssqb.sqsm
  is '����˵��';
comment on column xg_gygl_new_gzdx_wmqssqb.sqr
  is '������';
comment on column xg_gygl_new_gzdx_wmqssqb.sqsj
  is '����ʱ��';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshzt
  is '����Ա���״̬';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshr
  is '����Ա�����';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshsj
  is '����Ա���ʱ��';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshbz
  is '����Ա��˱�ע';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshzt
  is 'ѧУ���״̬';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshr
  is 'ѧУ�����';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshsj
  is 'ѧУ���ʱ��';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshbz
  is 'ѧУ��˱�ע';
-- Create/Recreate primary, unique and foreign key constraints 
alter table xg_gygl_new_gzdx_wmqssqb
  add constraint gzdx_wmqsshb_pri primary key (ND, LDDM, QSH);

create or replace view view_xg_gygl_new_gzdx_wmqssq as 
select a.*,c.xydm,c.xymc,c.bjdm,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=a.lddm) ldmc
from xg_gygl_new_gzdx_wmqssqb a 
left join xg_gygl_new_gyglryb b on a.lddm=b.lddm and a.qsh=b.qsh and b.rzzt='����'
left join view_xsjbxx c on b.xh=c.xh
/

alter table XG_GYGL_NEW_GZDX_WMQSSQB drop column QSRS;
create or replace view view_xg_gygl_new_gzdx_wmqssq as
select a.*,b.ch,d.xqdm,d.yqdm,c.xydm,c.xymc,c.bjdm,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=a.lddm) ldmc,
(select count(1) from xg_gygl_new_cwxxb x where x.lddm=a.lddm and x.qsh=a.qsh) qsrs
from xg_gygl_new_gzdx_wmqssqb a
left join xg_gygl_new_gyglryb b on a.lddm=b.lddm and a.qsh=b.qsh and b.rzzt='����'
left join view_xg_gygl_new_qsxx d on a.lddm = d.lddm and a.qsh =d.qsh
left join view_xsjbxx c on b.xh=c.xh
/
comment on column view_xg_gygl_new_gzdx_wmqssq.nd is '���';
comment on column view_xg_gygl_new_gzdx_wmqssq.lddm is '¥��';
comment on column view_xg_gygl_new_gzdx_wmqssq.qsh is '���Һ�';
comment on column view_xg_gygl_new_gzdx_wmqssq.wspjf is '����ƽ����';
comment on column view_xg_gygl_new_gzdx_wmqssq.sqsm is '����˵��';
comment on column view_xg_gygl_new_gzdx_wmqssq.sqr is '������';
comment on column view_xg_gygl_new_gzdx_wmqssq.sqsj is '����ʱ��';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshzt is '����Ա���״̬';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshr is '����Ա�����';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshsj is '����Ա���ʱ��';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshzt is 'ѧУ���״̬';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshr is 'ѧУ�����';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshsj is 'ѧУ���ʱ��';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshbz is '����Ա��˱�ע';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshbz is 'ѧУ��˱�ע';
comment on column view_xg_gygl_new_gzdx_wmqssq.ch is '���';
comment on column view_xg_gygl_new_gzdx_wmqssq.xqdm is 'У������';
comment on column view_xg_gygl_new_gzdx_wmqssq.yqdm is '԰������';
comment on column view_xg_gygl_new_gzdx_wmqssq.xydm is 'ѧԺ����';
comment on column view_xg_gygl_new_gzdx_wmqssq.xymc is 'ѧԺ����';
comment on column view_xg_gygl_new_gzdx_wmqssq.bjdm is '�༶����';
comment on column view_xg_gygl_new_gzdx_wmqssq.ldmc is '¥������';
comment on column view_xg_gygl_new_gzdx_wmqssq.qsrs is '��������';
-- Add/modify columns 
alter table GYGL_WSJC_WSFWHB add jcry varchar2(20);
-- Add comments to the columns 
comment on column GYGL_WSJC_WSFWHB.jcry
  is '�����Ա';
  
------------------------------------------------------------
--20120514--zhanghui--��Ԣ����------------------------------
------------------------------------------------------------  
--��Ԣ��¼�������
create table xg_gygl_new_gyjllbdlb(
       gyjllbdldm varchar2(20),
       gyjllbdlmc varchar2(200)
);
alter table xg_gygl_new_gyjllbdlb add constraint PK_gyjllbdlb primary key (gyjllbdldm);
comment on table xg_gygl_new_gyjllbdlb is '��Ԣ��¼�������';
comment on column xg_gygl_new_gyjllbdlb.gyjllbdldm is '��Ԣ�������������';
comment on column xg_gygl_new_gyjllbdlb.gyjllbdlmc is '��Ԣ��������������';

--��Ԣ��¼�������
create table xg_gygl_new_gyjllbdmb(
       gyjllbdm varchar2(20),
       gyjllbmc varchar2(200),
       gyjllbdldm  varchar2(20)
);
alter table xg_gygl_new_gyjllbdmb add constraint PK_gyjllbdmb primary key (gyjllbdm);
comment on table xg_gygl_new_gyjllbdmb is '��Ԣ��¼�������';
comment on column xg_gygl_new_gyjllbdmb.gyjllbdm is '��Ԣ����������';
comment on column xg_gygl_new_gyjllbdmb.gyjllbmc is '��Ԣ�����������';
comment on column xg_gygl_new_gyjllbdmb.gyjllbdldm is '��Ԣ�������������';

--��Ԣ���ɱ�
create table XG_GYGL_NEW_GYJLB
(
  XH         VARCHAR2(20) not null,
  LDDM       VARCHAR2(20),
  LDMC       VARCHAR2(100),
  QSH        VARCHAR2(10),
  CWH        VARCHAR2(10),
  NJ         VARCHAR2(10),
  XYDM       VARCHAR2(20),
  ZYDM       VARCHAR2(50),
  BJDM       VARCHAR2(100),
  XYMC       VARCHAR2(200),
  ZYMC        VARCHAR2(200),
  BJMC        VARCHAR2(200),
  GYJLLBDLDM  VARCHAR2(20),
  GYJLLBDM    VARCHAR2(20),
  WJSJ        VARCHAR2(50),
  BZ          VARCHAR2(2000),
  CZR         VARCHAR2(50),
  CZSJ        VARCHAR2(50) default to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') not null
);
alter table XG_GYGL_NEW_GYJLB add constraint PK_GYJLB primary key (xh,wjsj);
comment on table XG_GYGL_NEW_GYJLB is '��Ԣ���ɱ�';
comment on column XG_GYGL_NEW_GYJLB.XH is 'ѧ��';
comment on column XG_GYGL_NEW_GYJLB.LDDM is '¥������';
comment on column XG_GYGL_NEW_GYJLB.LDMC is '¥������';
comment on column XG_GYGL_NEW_GYJLB.QSH is '���Һ�';
comment on column XG_GYGL_NEW_GYJLB.CWH is '��λ��';
comment on column XG_GYGL_NEW_GYJLB.NJ is '�꼶';
comment on column XG_GYGL_NEW_GYJLB.XYDM is 'ѧԺ����';
comment on column XG_GYGL_NEW_GYJLB.ZYDM is 'רҵ����';
comment on column XG_GYGL_NEW_GYJLB.BJDM is '�༶����';
comment on column XG_GYGL_NEW_GYJLB.XYMC is 'ѧԺ����';
comment on column XG_GYGL_NEW_GYJLB.ZYMC is 'רҵ����';
comment on column XG_GYGL_NEW_GYJLB.BJMC is '�༶����';
comment on column XG_GYGL_NEW_GYJLB.GYJLLBDLDM is '��Ԣ�������������';
comment on column XG_GYGL_NEW_GYJLB.GYJLLBDM is '��Ԣ����������';
comment on column XG_GYGL_NEW_GYJLB.WJSJ is 'Υ��ʱ��';
comment on column XG_GYGL_NEW_GYJLB.BZ is '��ע';
comment on column XG_GYGL_NEW_GYJLB.CZR is '������';
comment on column XG_GYGL_NEW_GYJLB.CZSJ is '����ʱ��';


--����Ȩ��
--delete from gnmkdmb where gnmkdm like 'N3808%';
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N3808','��Ԣ����','','1','��');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N380801','��Ԣ���ɴ���ά��','gyglnew_gyjldmgl_gyjldmgl.do','1','��');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N380802','��Ԣ������Ϣά��','gyglnew_gyjlgl_gyjlgl.do','1','��');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N380803','��Ԣ������Ϣ��ѯ','gyglnew_gyjlgl_gyjlcx.do','1','��');

--delete from yhqxb where yhm ='zf01' and gnmkdm like 'N3808%';
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3808','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380801','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380802','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380803','1');





--������Ϣ��������
delete from dcb where zdssb = 'view_xg_gygl_new_qsxx';
commit;
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('ldmc','view_xg_gygl_new_qsxx','public','¥������');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('chmc','view_xg_gygl_new_qsxx','public','���');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qsh','view_xg_gygl_new_qsxx','public','���Һ�');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qsxb','view_xg_gygl_new_qsxx','public','�����Ա�');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qsdh','view_xg_gygl_new_qsxx','public','���ҵ绰');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('nj','view_xg_gygl_new_qsxx','public','�����꼶');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('xymc','view_xg_gygl_new_qsxx','public','����ѧԺ����');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qscws','view_xg_gygl_new_qsxx','public','���Ҵ�λ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('blcws','view_xg_gygl_new_qsxx','public','������λ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('ptcws','view_xg_gygl_new_qsxx','public','��ͨ��λ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('yfpcws','view_xg_gygl_new_qsxx','public','�ѷ��䴲λ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('yzcws','view_xg_gygl_new_qsxx','public','��ס��λ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('wzcws','view_xg_gygl_new_qsxx','public','δס��λ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('sffp','view_xg_gygl_new_qsxx','public','�Ƿ����');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('rzqk','view_xg_gygl_new_qsxx','public','��ס���');
commit;