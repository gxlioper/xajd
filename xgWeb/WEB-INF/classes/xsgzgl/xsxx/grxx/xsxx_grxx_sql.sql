/**ѧ����Ϣ_������Ϣ�������ݿ����**/
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4102', '������Ϣ�޸�', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410201', '��������', 'xsxx_cssz_grxx.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410202', '�޸�����', 'xsxx_grxx_sq.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410203', '�޸����', 'xsxx_grxx_sh.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410204', '�޸Ľ��', 'xsxx_grxx_jg.do', '1', '');

commit;

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4102', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410201', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410202', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410203', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410204', '1');

commit;

insert into xg_xtwh_splcmkdzb (mkdm,mkmc) values ('xsxx', 'ѧ����Ϣ');

commit;

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N110312', 'ѧ���춯����', 'xsxx_xjyd.do', '1', '');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N110312', '1');

commit;

create table xg_xsxx_grxx_szb(
  sfsh    VARCHAR2(10),
  lcid    VARCHAR2(40),
  sqkssj  VARCHAR2(20),
  sqjssj  VARCHAR2(20),
  shkssj  VARCHAR2(20),
  shjssj  VARCHAR2(20),
  over    VARCHAR2(10)
);
comment on table xg_xsxx_grxx_szb is 'ѧ����Ϣ_��������_������Ϣ���ñ�';
comment on column xg_xsxx_grxx_szb.sfsh is '�Ƿ����';
comment on column xg_xsxx_grxx_szb.lcid is '����ID';
comment on column xg_xsxx_grxx_szb.sqkssj is '���뿪ʼʱ��';
comment on column xg_xsxx_grxx_szb.sqjssj is '�������ʱ��';
comment on column xg_xsxx_grxx_szb.shkssj is '��˿�ʼʱ��';
comment on column xg_xsxx_grxx_szb.shjssj is '��˽���ʱ��';
comment on column xg_xsxx_grxx_szb.over is '���ý���';

create table xg_xsxx_grxx_zdszb(
  zd    		VARCHAR2(20),
  zdm   		VARCHAR2(40),
  sslx   		VARCHAR2(20),
  zdlx  		VARCHAR2(10),
  checked  		VARCHAR2(100),
  source_table  varchar2(50),
  select_dm     varchar2(50),
  select_mc     varchar2(50),
  xsqx          varchar2(10),
  lsqx          varchar2(10),
  xssx          varchar2(5),
  bm            varchar2(5),
  primary key (zd)
);
comment on table xg_xsxx_grxx_zdszb is 'ѧ����Ϣ_������Ϣ_�ֶ����ñ�';
comment on column xg_xsxx_grxx_zdszb.zd is '�ֶ�';
comment on column xg_xsxx_grxx_zdszb.sslx is '��������';
comment on column xg_xsxx_grxx_zdszb.zdm is '�ֶ���';
comment on column xg_xsxx_grxx_zdszb.checked is '��֤';
comment on column xg_xsxx_grxx_zdszb.zdlx is '�ֶ�����';
comment on column xg_xsxx_grxx_zdszb.source_table is '����Դ��';
comment on column xg_xsxx_grxx_zdszb.select_dm is '����Դ����';
comment on column xg_xsxx_grxx_zdszb.select_mc is '����Դ����';
comment on column xg_xsxx_grxx_zdszb.xsqx is 'ѧ��Ȩ��';
comment on column xg_xsxx_grxx_zdszb.lsqx is '��ʦȨ��';
comment on column xg_xsxx_grxx_zdszb.xssx is '��ʾ˳��';
comment on column xg_xsxx_grxx_zdszb.bm is '����';

create table xg_xsxx_grxx_xgsqb
(
  id    varchar2(40) default sys_guid() not null,
  xh    varchar2(20),
  sqsj  varchar2(20) default to_char(sysdate,'yyyymmdd'),
  sqr   varchar2(50),
  sqjg  varchar2(50),
  primary key (id)
);
comment on table xg_xsxx_grxx_xgsqb is 'ѧ����Ϣ_������Ϣ_�޸������';
comment on column xg_xsxx_grxx_xgsqb.id is 'ID';
comment on column xg_xsxx_grxx_xgsqb.xh is 'ѧ��';
comment on column xg_xsxx_grxx_xgsqb.sqsj is '����ʱ��';
comment on column xg_xsxx_grxx_xgsqb.sqr is '������';
comment on column xg_xsxx_grxx_xgsqb.sqjg is '������';

create table xg_xsxx_grxx_xgshb(
  id    varchar2(40) default sys_guid() not null,
  sqid  varchar2(40),
  gwid  varchar2(40),
  lcid  varchar2(40),
  shr   varchar2(20),
  shzt  varchar2(10) default 'δ���',
  shsj  varchar2(20) default to_char(sysdate,'yyyymmdd'),
  shyj  varchar2(1000),
  primary key (id)
);
comment on table xg_xsxx_grxx_xgshb is 'ѧ����Ϣ_������Ϣ_�޸���˱�';
comment on column xg_xsxx_grxx_xgshb.id is 'ID';
comment on column xg_xsxx_grxx_xgshb.sqid is '����ID';
comment on column xg_xsxx_grxx_xgshb.gwid is '��λID';
comment on column xg_xsxx_grxx_xgshb.lcid is '����ID';
comment on column xg_xsxx_grxx_xgshb.shr is '�����';
comment on column xg_xsxx_grxx_xgshb.shzt is '���״̬';
comment on column xg_xsxx_grxx_xgshb.shsj is '���ʱ��';
comment on column xg_xsxx_grxx_xgshb.shyj is '������';

create table xg_xsxx_grxx_zdxgb
(
  id    varchar2(40) default sys_guid() not null,
  sqid  varchar2(40),
  xh    varchar2(20),
  xgsj  varchar2(20) default to_char(sysdate,'yyyymmdd'),
  xgr   varchar2(20),
  xgzd  varchar2(4000),
  ysz   varchar2(4000),
  xgz   varchar2(4000),
  primary key (id)
);
comment on table xg_xsxx_grxx_zdxgb is 'ѧ����Ϣ_������Ϣ_�ֶ��޸ı�';
comment on column xg_xsxx_grxx_zdxgb.id is 'ID';
comment on column xg_xsxx_grxx_zdxgb.xh is 'ѧ��';
comment on column xg_xsxx_grxx_zdxgb.sqid is '����ID';
comment on column xg_xsxx_grxx_zdxgb.xgsj is '�޸�ʱ��';
comment on column xg_xsxx_grxx_zdxgb.xgr is '�޸���';
comment on column xg_xsxx_grxx_zdxgb.xgzd is '�޸��ֶ�';
comment on column xg_xsxx_grxx_zdxgb.ysz is 'ԭʼֵ';
comment on column xg_xsxx_grxx_zdxgb.xgz is '�޸�ֵ';

-- Create/Recreate indexes 
create index index_XG_XSXX_GRXX_XGSQB_xh on XG_XSXX_GRXX_XGSQB (xh);

-----------------�߼���ѯ��������Ϣ�޸���ˣ�--------------------------------------
delete from xg_search_szb where path='xsxx_grxx_sh.do';
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','xh','ѧ��','mhcx','xh','1');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','xm','����','mhcx','xm','2');

insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','nj','�꼶','djcx','nj','3');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','xy','ѧԺ','djcx','xydm','4');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','zy','רҵ','djcx','zydm','5');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','bj','�༶','djcx','bjdm','6');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','shzt','���״̬','djcx','','7');
commit;

-----------------�߼���ѯ��������Ϣ�޸Ľ����--------------------------------------
delete from xg_search_szb where path='xsxx_grxx_jg.do';
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','xh','ѧ��','mhcx','xh','1');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','xm','����','mhcx','xm','2');

insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','nj','�꼶','djcx','nj','3');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','xy','ѧԺ','djcx','xydm','4');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','zy','רҵ','djcx','zydm','5');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','bj','�༶','djcx','bjdm','6');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','sqjg','������','djcx','','7');
commit;

-----------------��ʼ���ֶ�����--------------------------------------
delete from xg_xsxx_grxx_zdszb;
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xh','ѧ��','������Ϣ','disabled','','','','','1','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xm','����','������Ϣ','text','16!!luojw!!no','','','','2','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xb','�Ա�','������Ϣ','select','','','��!!luojw!!Ů','��!!luojw!!Ů','3','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('csrq','��������','������Ϣ','calendar','','','','','4','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('mz','����','������Ϣ','select','','mzdmb','mzdm','mzmc','5','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('zzmm','������ò','������Ϣ','select','','zzmmdmb','zzmmdm','zzmmmc','6','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzh','���֤��','������Ϣ','text','18!!luojw!!sfzh','','','','7','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('pycc','�������','������Ϣ','select','','xg_xsxx_pyccdmb','pyccdm','pyccmc','8','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('hkszd','�������ڵ�','������Ϣ','ssx','','','','','9','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jg','����','������Ϣ','ssx','','','','','10','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('syd','��Դ��','������Ϣ','ssx','','','','','11','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('bjdm','���ڲ���','ѧ��״̬','szbm','','','','','12','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xjztm','ѧ��״̬','ѧ��״̬','select','','dm_zju_xjzt','zxdmmc','zxdmmc','13','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xz','ѧ��','ѧ��״̬','text','4!!luojw!!num','','','','14','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzc','�Ƿ�ע��','ѧ��״̬','select','','','��!!luojw!!��','��!!luojw!!��','15','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzd','�Ƿ��߶�','ѧ��״̬','select','','','��!!luojw!!��','��!!luojw!!��','16','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('rxrq','��ѧ����','ѧ��״̬','calendar','','','','','17','a');
--insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('bysj','��ҵʱ��','ѧ��״̬','calendar','','','','','18','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('byny','��ҵʱ��','ѧ��״̬','calendar','','','','','18','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfbys','�Ƿ��ҵ��','ѧ��״̬','select','','','��!!luojw!!��','��!!luojw!!��','19','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfyby','�Ƿ��ѱ�ҵ','ѧ��״̬','select','','','��!!luojw!!��','��!!luojw!!��','20','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzx','�Ƿ���У','ѧ��״̬','select','','','��У!!luojw!!����У','��У!!luojw!!����У','21','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('lxdh','��ϵ�绰','��ϵ��ʽ','text','20!!luojw!!num','','','','22','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sjhm','�ֻ�����','��ϵ��ʽ','text','20!!luojw!!num','','','','23','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('qqhm','QQ����','��ϵ��ʽ','text','20!!luojw!!num','','','','24','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('dzyx','��������','��ϵ��ʽ','text','30!!luojw!!dzyx','','','','25','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('yhdm','����','������Ϣ','select','','dmk_yh','yhdm','yhmc','64','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('yhkh','���п���','������Ϣ','text','20!!luojw!!num','','','','65','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('fdyxm','����Ա����','������Ϣ','text','10!!luojw!!no','','','','66','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('kh','һ��ͨ��','������Ϣ','text','20!!luojw!!num','','','','67','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sg','���','������Ϣ','text','10!!luojw!!money','','','','68','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('tz','����','������Ϣ','text','10!!luojw!!money','','','','69','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xmpy','����ƴ��','������Ϣ','text','16!!luojw!!no','','','','70','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('cym','������','������Ϣ','text','16!!luojw!!no','','','','71','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('tc','�س�','������Ϣ','text','32!!luojw!!no','','','','72','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('kslb','�������','������Ϣ','select','','xg_xsxx_kslbdmb','kslbdm','kslbmc','73','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('rxfs','��ѧ��ʽ','������Ϣ','select','','xg_xsxx_rxfsdmb','rxfsdm','rxfsmc','74','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('pyfs','������ʽ','������Ϣ','select','','xg_xsxx_pyfsdmb','pyfsdm','pyfsmc','75','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jkzk','����״��','������Ϣ','text','100!!luojw!!no','','','','76','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('lxdh1','��ͥ�绰','��ͥ��Ϣ','text','20!!luojw!!num','','','','27','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtszd','��ͥ��ַ','��ͥ��Ϣ','text','25!!luojw!!no','','','','28','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('yb','��������','��ͥ��Ϣ','text','10!!luojw!!num','','','','29','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jjzk','��ͥ����״��','��ͥ��Ϣ','text','50!!luojw!!no','','','','30','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_xm','��ͥ��Ա1_����','��ͥ��Ϣ','text','25!!luojw!!no','','','','31','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_gx','��ͥ��Ա1_��ϵ','��ͥ��Ϣ','text','10!!luojw!!no','','','','32','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_nl','��ͥ��Ա1_����','��ͥ��Ϣ','calendar','','','','','33','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_sfzh','��ͥ��Ա1_���֤��','��ͥ��Ϣ','text','18!!luojw!!sfzh','','','','34','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_zy','��ͥ��Ա1_ְҵ','��ͥ��Ϣ','text','10!!luojw!!no','','','','35','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_zw','��ͥ��Ա1_ְ��','��ͥ��Ϣ','text','10!!luojw!!no','','','','36','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_lxdh1','��ͥ��Ա1_�����绰','��ͥ��Ϣ','text','20!!luojw!!num','','','','37','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_lxdh2','��ͥ��Ա1_���˵绰','��ͥ��Ϣ','text','20!!luojw!!num','','','','38','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_gzdz','��ͥ��Ա1_������ַ','��ͥ��Ϣ','text','25!!luojw!!no','','','','39','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_mz','��ͥ��Ա1_����','��ͥ��Ϣ','select','','mzdmb','mzdm','mzmc','40','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_zzmm','��ͥ��Ա1_������ò','��ͥ��Ϣ','select','','zzmmdmb','zzmmdm','zzmmmc','41','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_xm','��ͥ��Ա2_����','��ͥ��Ϣ','text','25!!luojw!!no','','','','42','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_gx','��ͥ��Ա2_��ϵ','��ͥ��Ϣ','text','10!!luojw!!no','','','','43','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_nl','��ͥ��Ա2_����','��ͥ��Ϣ','calendar','','','','','44','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_sfzh','��ͥ��Ա2_���֤��','��ͥ��Ϣ','text','18!!luojw!!sfzh','','','','45','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_zy','��ͥ��Ա2_ְҵ','��ͥ��Ϣ','text','10!!luojw!!no','','','','46','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_zw','��ͥ��Ա2_ְ��','��ͥ��Ϣ','text','10!!luojw!!no','','','','47','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_lxdh1','��ͥ��Ա2_�����绰','��ͥ��Ϣ','text','20!!luojw!!num','','','','48','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_lxdh2','��ͥ��Ա2_���˵绰','��ͥ��Ϣ','text','20!!luojw!!num','','','','49','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_gzdz','��ͥ��Ա2_������ַ','��ͥ��Ϣ','text','25!!luojw!!no','','','','50','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_mz','��ͥ��Ա2_����','��ͥ��Ϣ','select','','mzdmb','mzdm','mzmc','51','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_zzmm','��ͥ��Ա2_������ò','��ͥ��Ϣ','select','','zzmmdmb','zzmmdm','zzmmmc','52','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_xm','��ͥ��Ա3_����','��ͥ��Ϣ','text','25!!luojw!!no','','','','53','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_gx','��ͥ��Ա3_��ϵ','��ͥ��Ϣ','text','10!!luojw!!no','','','','54','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_nl','��ͥ��Ա3_����','��ͥ��Ϣ','calendar','','','','','55','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_sfzh','��ͥ��Ա3_���֤��','��ͥ��Ϣ','text','18!!luojw!!sfzh','','','','56','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_zy','��ͥ��Ա3_ְҵ','��ͥ��Ϣ','text','10!!luojw!!no','','','','57','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_zw','��ͥ��Ա3_ְ��','��ͥ��Ϣ','text','10!!luojw!!no','','','','58','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_lxdh1','��ͥ��Ա3_�����绰','��ͥ��Ϣ','text','20!!luojw!!num','','','','59','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_lxdh2','��ͥ��Ա3_���˵绰','��ͥ��Ϣ','text','20!!luojw!!num','','','','60','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_gzdz','��ͥ��Ա3_������ַ','��ͥ��Ϣ','text','25!!luojw!!no','','','','61','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_mz','��ͥ��Ա3_����','��ͥ��Ϣ','select','','mzdmb','mzdm','mzmc','62','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_zzmm','��ͥ��Ա3_������ò','��ͥ��Ϣ','select','','zzmmdmb','zzmmdm','zzmmmc','63','c');
commit;
