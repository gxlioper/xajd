--����ģ��--
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4102', '�㽭����', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410201', '�߶�����', 'gygl_zjjs_zdsq.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410202', '�߶����', 'gygl_zjjs_zdsh.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410203', '�߶����', 'gygl_zjjs_zdjg.do', '1', '');

commit;

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4102', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410201', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410202', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410203', '1');

commit;

--������(�߶��������)--
create table xg_gygl_zjjs_zdssqb(
id  	 varchar2(40) default sys_guid() not null,
xh       varchar2(20),
lxdh	 varchar2(20),
zsdd     varchar2(100),
jtdz	 varchar2(100),
jtdh	 varchar2(20),
zdkssj	 varchar2(20),
zdjssj	 varchar2(20),
sqly	 varchar2(1000),
sqsj  	 varchar2(20) default to_char(sysdate,'yyyymmdd'),
bz		 varchar2(1000),
bjsh	 varchar2(20) default 'δ���',
bjshr	 varchar2(20),
bjshyj	 varchar2(1000),
bjshsj	 varchar2(20),
xysh	 varchar2(20) default 'δ���',
xyshr	 varchar2(20),
xyshyj	 varchar2(1000),
xyshsj	 varchar2(20),
primary key(id)
);

comment on table xg_gygl_zjjs_zdssqb is '�߶��������';
comment on column xg_gygl_zjjs_zdssqb.id is 'ID';
comment on column xg_gygl_zjjs_zdssqb.xh is 'ѧ��';
comment on column xg_gygl_zjjs_zdssqb.lxdh is '��ϵ�绰';
comment on column xg_gygl_zjjs_zdssqb.zsdd is 'ס�޵�ַ';
comment on column xg_gygl_zjjs_zdssqb.jtdz is '��ͥ��ַ';
comment on column xg_gygl_zjjs_zdssqb.jtdh is '��ͥ�绰';
comment on column xg_gygl_zjjs_zdssqb.zdkssj is '�߶���ʼʱ��';
comment on column xg_gygl_zjjs_zdssqb.zdjssj is '�߶�����ʱ��';
comment on column xg_gygl_zjjs_zdssqb.sqly is '��������';
comment on column xg_gygl_zjjs_zdssqb.sqsj is '����ʱ��';
comment on column xg_gygl_zjjs_zdssqb.bz is '��ע';
comment on column xg_gygl_zjjs_zdssqb.bjsh is '����Ա���';
comment on column xg_gygl_zjjs_zdssqb.bjshr is '����Ա�����';
comment on column xg_gygl_zjjs_zdssqb.bjshyj is '����Ա������';
comment on column xg_gygl_zjjs_zdssqb.bjshsj is '����Ա���ʱ��';
comment on column xg_gygl_zjjs_zdssqb.xysh is 'ѧԺ���';
comment on column xg_gygl_zjjs_zdssqb.xyshr is 'ѧԺ�����';
comment on column xg_gygl_zjjs_zdssqb.xyshyj is 'ѧԺ������';
comment on column xg_gygl_zjjs_zdssqb.xyshsj is 'ѧԺ���ʱ��';

create or replace view xg_view_gygl_zjjs_zdssqb as
select a.xh||a.sqsj pk,
       a.id,
       a.xh,
       b.xm,
       b.xb,
       b.nj,
       b.xydm,
       b.xymc,
       b.zydm,
       b.zymc,
       b.bjdm,
       b.bjmc,
       a.lxdh,
       a.zsdd,
       a.jtdz,
       a.jtdh,
       a.zdkssj,
       a.zdjssj,
       a.sqly,
       a.sqsj,
       a.bz,
       a.bjsh,
       a.bjshr,
       a.bjshyj,
       a.bjshsj,
       a.xysh,
       a.xyshr,
       a.xyshyj,
       a.xyshsj
  from xg_gygl_zjjs_zdssqb a, view_xsjbxx b
 where a.xh = b.xh;
comment on column xg_view_gygl_zjjs_zdssqb.pk is '����';
comment on column xg_view_gygl_zjjs_zdssqb.id is 'ID';
comment on column xg_view_gygl_zjjs_zdssqb.xh is 'ѧ��';
comment on column xg_view_gygl_zjjs_zdssqb.xm is '����';
comment on column xg_view_gygl_zjjs_zdssqb.xb is '�Ա�';
comment on column xg_view_gygl_zjjs_zdssqb.nj is '�꼶';
comment on column xg_view_gygl_zjjs_zdssqb.xydm is 'ѧԺ����';
comment on column xg_view_gygl_zjjs_zdssqb.xymc is 'ѧԺ����';
comment on column xg_view_gygl_zjjs_zdssqb.zydm is 'רҵ����';
comment on column xg_view_gygl_zjjs_zdssqb.zymc is 'רҵ����';
comment on column xg_view_gygl_zjjs_zdssqb.bjdm is '�༶����';
comment on column xg_view_gygl_zjjs_zdssqb.bjmc is '�༶����';
comment on column xg_view_gygl_zjjs_zdssqb.lxdh is '��ϵ�绰';
comment on column xg_view_gygl_zjjs_zdssqb.zsdd is 'ס�޵�ַ';
comment on column xg_view_gygl_zjjs_zdssqb.jtdz is '��ͥ��ַ';
comment on column xg_view_gygl_zjjs_zdssqb.jtdh is '��ͥ�绰';
comment on column xg_view_gygl_zjjs_zdssqb.zdkssj is '�߶���ʼʱ��';
comment on column xg_view_gygl_zjjs_zdssqb.zdjssj is '�߶�����ʱ��';
comment on column xg_view_gygl_zjjs_zdssqb.sqly is '��������';
comment on column xg_view_gygl_zjjs_zdssqb.sqsj is '����ʱ��';
comment on column xg_view_gygl_zjjs_zdssqb.bz is '��ע';
comment on column xg_view_gygl_zjjs_zdssqb.bjsh is '����Ա���';
comment on column xg_view_gygl_zjjs_zdssqb.bjshr is '����Ա�����';
comment on column xg_view_gygl_zjjs_zdssqb.bjshyj is '����Ա������';
comment on column xg_view_gygl_zjjs_zdssqb.bjshsj is '����Ա���ʱ��';
comment on column xg_view_gygl_zjjs_zdssqb.xysh is 'ѧԺ���';
comment on column xg_view_gygl_zjjs_zdssqb.xyshr is 'ѧԺ�����';
comment on column xg_view_gygl_zjjs_zdssqb.xyshyj is 'ѧԺ������';
comment on column xg_view_gygl_zjjs_zdssqb.xyshsj is 'ѧԺ���ʱ��';

delete from xg_search_szb where path='gygl_zjjs_zdsh.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','nj','�꼶','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','xy','ѧԺ','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','zy','רҵ','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','bj','�༶','djcx','bjdm','','','8');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','shzt','���״̬','djcx','','','','9');

commit;

delete from xg_search_szb where path='gygl_zjjs_zdjg.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','nj','�꼶','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','xy','ѧԺ','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','zy','רҵ','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','bj','�༶','djcx','bjdm','','','8');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','shzt','�������','djcx','xysh','','','9');

commit;