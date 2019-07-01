/**�ճ�����_��ٹ��������ݿ����**/
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4103', '��ٹ���', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410301', '������̶���', 'rcsw_qjgl_cssz_qjlc.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410302', '�ҵ����', 'rcsw_qjgl_mygz_stu.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410303', '�ҵĹ���', 'rcsw_qjgl_mygz_tea.do', '1', '');

commit;

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4103', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410301', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410302', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410303', '1');

commit;

--������(�������ά����)--
create table xg_rcsw_qjgl_qjlxb(
id    varchar2(40) default sys_guid() not null,
lxmc  varchar2(40),
lcid  varchar2(40),
mints varchar2(10),
maxts varchar2(10),
xgr   varchar2(40),
xgsj  varchar2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
primary key(id)
);

comment on table xg_rcsw_qjgl_qjlxb is '��������_�ճ�����_�������ά����';
comment on column xg_rcsw_qjgl_qjlxb.id is '��ĿID';
comment on column xg_rcsw_qjgl_qjlxb.lxmc is '��������';
comment on column xg_rcsw_qjgl_qjlxb.lcid  is '����ID';
comment on column xg_rcsw_qjgl_qjlxb.mints  is '��С����';
comment on column xg_rcsw_qjgl_qjlxb.maxts  is '�������';
comment on column xg_rcsw_qjgl_qjlxb.xgr is '�޸���';
comment on column xg_rcsw_qjgl_qjlxb.xgsj is '�޸�ʱ��';

--������(��������)--
create table xg_rcsw_qjgl_qjsqb(
id    varchar2(40) default sys_guid() not null,
qjid  varchar2(40),
xn    varchar2(20),
xq    varchar2(10),
xh    varchar2(20),
sqsj  varchar2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
sqts  varchar2(10),
kssj  varchar2(20),
jssj  varchar2(20),
lxdh  varchar2(20),
jtdh  varchar2(20),
jtdz  varchar2(100),
sqly  varchar2(1000),
bz    varchar2(1000),
sqjg  varchar2(100),
kzzd1 varchar2(500),
kzzd2 varchar2(500),
kzzd3 varchar2(500),
kzzd4 varchar2(500),
kzzd5 varchar2(500),
primary key(id)
);

comment on table xg_rcsw_qjgl_qjsqb is '��������_�ճ�����_��������';
comment on column xg_rcsw_qjgl_qjsqb.id is '��ĿID';
comment on column xg_rcsw_qjgl_qjsqb.qjid  is '���ID';
comment on column xg_rcsw_qjgl_qjsqb.xn  is 'ѧ��';
comment on column xg_rcsw_qjgl_qjsqb.xq  is 'ѧ��';
comment on column xg_rcsw_qjgl_qjsqb.xh  is 'ѧ��';
comment on column xg_rcsw_qjgl_qjsqb.sqsj  is '����ʱ��';
comment on column xg_rcsw_qjgl_qjsqb.sqts is '��������';
comment on column xg_rcsw_qjgl_qjsqb.kssj is '��ʼʱ��';
comment on column xg_rcsw_qjgl_qjsqb.jssj is '����ʱ��';
comment on column xg_rcsw_qjgl_qjsqb.lxdh is '��ϵ�绰';
comment on column xg_rcsw_qjgl_qjsqb.jtdh is '��ͥ�绰';
comment on column xg_rcsw_qjgl_qjsqb.jtdz is '��ͥ��ַ';
comment on column xg_rcsw_qjgl_qjsqb.sqly is '��������';
comment on column xg_rcsw_qjgl_qjsqb.bz is '��ע';
comment on column xg_rcsw_qjgl_qjsqb.sqjg is '������';
comment on column xg_rcsw_qjgl_qjsqb.kzzd1 is '��չ�ֶ�1';
comment on column xg_rcsw_qjgl_qjsqb.kzzd2 is '��չ�ֶ�2';
comment on column xg_rcsw_qjgl_qjsqb.kzzd3 is '��չ�ֶ�3';
comment on column xg_rcsw_qjgl_qjsqb.kzzd4 is '��չ�ֶ�4';
comment on column xg_rcsw_qjgl_qjsqb.kzzd5 is '��չ�ֶ�5';

--������(�����˱�)--
create table xg_rcsw_qjgl_qjshb(
sqid    varchar2(40),
gwid    varchar2(40),
shr     varchar2(40),
shzt    varchar2(20) default 'δ���',
shsj    varchar2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
shyj    varchar2(1000),
primary key(sqid,gwid)
);

comment on table xg_rcsw_qjgl_qjshb is '��������_�ճ�����_��������';
comment on column xg_rcsw_qjgl_qjshb.sqid is '����ID';
comment on column xg_rcsw_qjgl_qjshb.gwid  is '��λID';
comment on column xg_rcsw_qjgl_qjshb.shr  is '�����';
comment on column xg_rcsw_qjgl_qjshb.shzt  is '���״̬';
comment on column xg_rcsw_qjgl_qjshb.shsj is '���ʱ��';
comment on column xg_rcsw_qjgl_qjshb.shyj is '������';

-----------------�߼���ѯ------------------------------
-----------------���------------------------------
delete from xg_search_szb where path='rcsw_qjgl_mysh.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','nj','�꼶','djcx','nj','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','xy','ѧԺ','djcx','xydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','zy','רҵ','djcx','zydm','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','bj','�༶','djcx','bjdm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','shzt','�༶','djcx','','','','7');

commit;


-----------------------�ճ����� ��ٹ��� �������--------------------------------
create or replace view xg_view_rcsw_qjgl_qjsq
as
select 
a.id,
a.qjid,
a.xn,
a.xq,
a.xh,
a.sqsj,
a.sqts,
a.kssj,
a.jssj,
a.lxdh,
a.jtdh,
a.jtdz,
a.sqly,
a.bz,
a.sqjg sqjgxx,
(case when sqjg ='δ���' then sqjg when sqjg='��׼���' then sqjg else '�����' end)sqjg,
a.qjid qjlx,
a.kzzd1,
a.kzzd2,
a.kzzd3,
a.kzzd4,
a.kzzd5,
b.lxmc,
b.mints,
b.maxts,
c.nj,
c.xydm,
c.xymc,
c.zydm,
c.zymc,
c.bjdm,
c.bjmc,
c.xm,
c.xb
from xg_rcsw_qjgl_qjsqb a left join xg_rcsw_qjgl_qjlxb b
on a.qjid=b.id left join view_xsjbxx c 
on a.xh=c.xh
/
comment on column XG_VIEW_RCSW_QJGL_QJSQ.ZYDM is 'רҵ����';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.ZYMC is 'רҵ';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.BJDM is '�༶����';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.BJMC is '�༶';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XM is '����';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XB is '�Ա�';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.ID is 'id';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.QJID is '������ʹ���';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XN is 'ѧ��';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XQ is 'ѧ��';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XH is 'ѧ��';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQSJ is '����ʱ��';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQTS is '��������';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KSSJ is '��ٿ�ʼʱ��';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.JSSJ is '��ٽ���ʱ��';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.LXDH is '��ϵ�绰';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.JTDH is '��ͥ�绰';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.JTDZ is '��ͥ��ַ';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQLY is '��������';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.BZ is '��ע';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQJGXX is '������';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQJG is '������';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.QJLX is '�������';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD1 is '��չ�ֶ�1';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD2 is '��չ�ֶ�2';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD3 is '��չ�ֶ�3';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD4 is '��չ�ֶ�4';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD5 is '��չ�ֶ�5';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.LXMC is '��ϵ����';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.MINTS is '��С����';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.MAXTS is '�������';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.NJ is '�꼶';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XYDM is 'ѧԺ����';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XYMC is 'ѧԺ';

-----------------------�����ѯҳ�� �߼���ѯ------------------------------
delete from xg_search_szb where path='rcsw_qjgl_jgcx.do';
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','xh','ѧ��','mhcx','xh','1');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','xm','����','mhcx','xm','2');

insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','nj','�꼶','djcx','nj','3');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','xy','ѧԺ','djcx','xydm','4');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','zy','רҵ','djcx','zydm','5');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','bj','�༶','djcx','bjdm','6');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','qjlx','�������','djcx','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','sqjg','������','djcx','','8');
commit;



------------------------------����������-----------------------------
delete from dcb where zdssb='xg_view_rcsw_qjgl_qjsq' and xxdm='public';
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xn','xg_view_rcsw_qjgl_qjsq','public','ѧ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xq','xg_view_rcsw_qjgl_qjsq','public','ѧ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xh','xg_view_rcsw_qjgl_qjsq','public','ѧ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xm','xg_view_rcsw_qjgl_qjsq','public','����');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xb','xg_view_rcsw_qjgl_qjsq','public','�Ա�');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('nj','xg_view_rcsw_qjgl_qjsq','public','�꼶');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xymc','xg_view_rcsw_qjgl_qjsq','public','ѧԺ');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('zymc','xg_view_rcsw_qjgl_qjsq','public','רҵ');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('bjmc','xg_view_rcsw_qjgl_qjsq','public','�༶');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('sqsj','xg_view_rcsw_qjgl_qjsq','public','����ʱ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('sqts','xg_view_rcsw_qjgl_qjsq','public','��������');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('kssj','xg_view_rcsw_qjgl_qjsq','public','��ٿ�ʼʱ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('jssj','xg_view_rcsw_qjgl_qjsq','public','��ٽ���ʱ��');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('lxmc','xg_view_rcsw_qjgl_qjsq','public','�������');
commit;

--������(����ֶ����ñ�)--
create table xg_rcsw_qjgl_qjsqzdb(
zd     varchar2(40),
zdm    varchar2(40),
lx     varchar2(40),
zdz    varchar2(20),
primary key(zd)
);

comment on table xg_rcsw_qjgl_qjsqzdb is '��������_�ճ�����_��������ֶα�';
comment on column xg_rcsw_qjgl_qjsqzdb.zd is '�ֶ�';
comment on column xg_rcsw_qjgl_qjsqzdb.zdm  is '�ֶ�����';
comment on column xg_rcsw_qjgl_qjsqzdb.lx  is '����';
comment on column xg_rcsw_qjgl_qjsqzdb.zdz  is '�ֶ�ֵ';

---------------------�ճ����� ��ٹ��� ������-------------------------------
create or replace view xg_view_rcsw_qjgl_qjshb 
as
select a.*,c.id from xg_rcsw_qjgl_qjshb a left join xg_rcsw_qjgl_qjsqb b on a.sqid=b.id
left join xg_rcsw_qjgl_qjlxb c on b.qjid=c.id
/
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SQID is '�������';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.GWID is '��λ����';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHR is '�����';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHZT is '���״̬';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHSJ is '���ʱ��';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHYJ is '������';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.ID is '����';


--�������� �ճ���������--
create or replace procedure pro_xg_xtwh_splc_rcswsz
is
begin
   delete from xg_ty_shlcszb where gnmc in(select id from xg_rcsw_qjgl_qjlxb);
  insert into xg_ty_shlcszb (gnmc,lcid,tableName,xn,xq,nd,dzgwid,dzxn,dzxmid)
   select id,lcid,'xg_view_rcsw_qjgl_qjshb','','','','gwid','','id' from
   xg_rcsw_qjgl_qjlxb group by lcid,id;
   commit;
end pro_xg_xtwh_splc_rcswsz;
/