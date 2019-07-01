------------------------------------------
--2011-12-23--zhanghui
------------------------------------------
--���ӹ���ģ�����
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N0224','������Ϣ����','','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N022401','ѧ��������Ϣ����','dtjs_dtxxgl_dtxxgl.do','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N022402','ѧ��������Ϣ��ѯ','dtjs_dtxxgl_dtxxcx.do','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N0225','������Ϣ','','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N022501','��Ա�ɷѹ���','dtjs_general_tyjf.do','1','');
commit;

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N0224','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N022401','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N022402','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N0225','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N022501','1');
commit;

--���ӵ��Ž���������ñ�
-- Table XG_DTJS_JBSZB
-- Create table
create table XG_DTJS_JBSZB
(
  JDDM     VARCHAR2(30) not null,
  JDMC     VARCHAR2(100),
  SFSZJSSJ VARCHAR2(2) default '��',
  SFYRSHL  VARCHAR2(2) default '��',
  SFSZTJ   VARCHAR2(2) default '��',
  DYZD     VARCHAR2(30),
  DYZ      VARCHAR2(30),
  BZ       VARCHAR2(500),
  JDSX     VARCHAR2(30)
);
-- Add comments to the table 
comment on table XG_DTJS_JBSZB
  is '���Ž���������ñ�';
-- Add comments to the columns 
comment on column XG_DTJS_JBSZB.JDDM
  is '�׶δ���';
comment on column XG_DTJS_JBSZB.JDMC
  is '�׶�����';
comment on column XG_DTJS_JBSZB.SFSZJSSJ
  is '�Ƿ��Զ�ת����һ�׶�';
comment on column XG_DTJS_JBSZB.SFYRSHL
  is '�Ƿ����������';
comment on column XG_DTJS_JBSZB.SFSZTJ
  is '�Ƿ���������';
comment on column XG_DTJS_JBSZB.DYZD
  is '��Ӧ�ֶ�';
comment on column XG_DTJS_JBSZB.DYZ
  is '��Ӧֵ';
comment on column XG_DTJS_JBSZB.BZ
  is '��ע';
comment on column XG_DTJS_JBSZB.JDSX
  is '�׶�˳��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_DTJS_JBSZB
  add constraint PK_XG_DTJS_JBSZB_JDDM primary key (JDDM);

--����ѧ��������Ϣ��¼��
-- Create table
create table XG_DTJS_XSDTXXJLB
(
  XH     VARCHAR2(30) not null,
  JDDM   VARCHAR2(30) not null,
  KSSJ   VARCHAR2(30),
  JSSJ   VARCHAR2(30),
  DQJDBJ VARCHAR2(2) default 0
);
-- Add comments to the table 
comment on table XG_DTJS_XSDTXXJLB
  is 'ѧ��������Ϣ��¼��';
-- Add comments to the columns 
comment on column XG_DTJS_XSDTXXJLB.XH
  is 'ѧ��';
comment on column XG_DTJS_XSDTXXJLB.JDDM
  is '�׶δ���';
comment on column XG_DTJS_XSDTXXJLB.KSSJ
  is '��ʼʱ��';
comment on column XG_DTJS_XSDTXXJLB.JSSJ
  is '����ʱ��';
comment on column XG_DTJS_XSDTXXJLB.DQJDBJ
  is '��ǰ�׶α��';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_DTJS_XSDTXXJLB
  add constraint PK_XG_DTJS_XSDTXXJLB_XH primary key (XH, JDDM);

------------------------------------------
--2011-12-26--zhanghui
------------------------------------------
--���Ӹ߼���ѯ����--
delete from xg_search_szb where path = 'dtjs_dtxxgl_dtxxgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','jddm','�׶δ���','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','xy','ѧԺ','djcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','zy','רҵ','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','bj','�༶','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','zm','������ò','djcx','zzmm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','xh','ѧ��','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','xm','����','mhcx','','','2');
commit;

delete from xg_search_szb where path = 'dtjs_dtxxgl_dtxxcx.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','jddm','�׶δ���','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','xy','ѧԺ','djcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','zy','רҵ','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','bj','�༶','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','zm','������ò','djcx','zzmm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','xh','ѧ��','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','xm','����','mhcx','','','2');
commit;

--2011-12-26--gbb--
--������Ϣ������ʱ��
-- Create table
create table xg_dtjs_impxsdtxxjlb
(
  xh     varchar2(30),
  jddm   varchar2(30),
  kssj   varchar2(30),
  jssj   varchar2(30),
  dqjdbj varchar2(2) default '0',
  mark   char(1) default '1',
  bz     varchar2(50)
)
;
-- Add comments to the table 
comment on table xg_dtjs_impxsdtxxjlb
  is 'ѧ��������Ϣ��¼������ʱ��';
-- Add comments to the columns 
comment on column xg_dtjs_impxsdtxxjlb.xh
  is 'ѧ��';
comment on column xg_dtjs_impxsdtxxjlb.jddm
  is '�׶δ���';
comment on column xg_dtjs_impxsdtxxjlb.kssj
  is '��ʼʱ��';
comment on column xg_dtjs_impxsdtxxjlb.jssj
  is '����ʱ��';
comment on column xg_dtjs_impxsdtxxjlb.dqjdbj
  is '��ǰ�׶α��';
comment on column xg_dtjs_impxsdtxxjlb.mark
  is '���';
comment on column xg_dtjs_impxsdtxxjlb.bz
  is '��ע';
  
create table xg_dtjs_tyjfb(
xn     varchar2(20),
xh     varchar2(20),
yjtf   varchar2(10),
sjtf   varchar2(10),
primary key(xn,xh)
);
comment on table xg_dtjs_tyjfb is 'ѧ��_���Ž���_��Ա�ɷѱ�';
comment on column xg_dtjs_tyjfb.xn is 'ѧ��';
comment on column xg_dtjs_tyjfb.xh is 'ѧ��';
comment on column xg_dtjs_tyjfb.yjtf is 'Ӧ���ŷ�';
comment on column xg_dtjs_tyjfb.sjtf is 'ʵ���ŷ�';