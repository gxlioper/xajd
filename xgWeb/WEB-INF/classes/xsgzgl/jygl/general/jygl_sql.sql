-------------------��-----------------------------
create table xg_jygl_sxjyb(
xh   varchar2(20),
sxqk   varchar2(1000),
jydw   varchar2(1000),
primary key(xh)
);
comment on table xg_jygl_sxjyb is 'ѧ��_��ҵ����_ʵϰ��ҵ��';
comment on column xg_jygl_sxjyb.xh is 'ѧ��';
comment on column xg_jygl_sxjyb.sxqk is 'ʵϰ���';
comment on column xg_jygl_sxjyb.jydw is '��ҵ���';

-------------------��ͼ-----------------------------
create or replace view xg_view_jygl_sxjy as
select a.xh pk,
       a.xh,
       b.xm,
       b.nj,
       b.xydm,
       b.xymc,
       b.zydm,
       b.zymc,
       b.bjdm,
       b.bjmc,
       a.sxqk,
       a.jydw
  from xg_jygl_sxjyb a
  left join view_xsbfxx b
    on a.xh = b.xh;
comment on column xg_view_jygl_sxjy.XH is 'ѧ��';
comment on column xg_view_jygl_sxjy.XM is '����';
comment on column xg_view_jygl_sxjy.NJ is '�꼶';
comment on column xg_view_jygl_sxjy.XYDM is 'ѧԺ����';
comment on column xg_view_jygl_sxjy.XYMC is 'ѧԺ����';
comment on column xg_view_jygl_sxjy.ZYDM is 'רҵ����';
comment on column xg_view_jygl_sxjy.ZYMC is 'רҵ����';
comment on column xg_view_jygl_sxjy.BJDM is '�༶����';
comment on column xg_view_jygl_sxjy.BJMC is '�༶����';
comment on column xg_view_jygl_sxjy.sxqk is 'ʵϰ���';
comment on column xg_view_jygl_sxjy.jydw is '��ҵ��λ';

-------------------ʵϰ��ҵ----------------------------
delete from xg_search_szb where path='jygl_general_sxjy.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xm','����','mhcx','xm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xymc','Ժϵ����','mhcx','xymc','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','zymc','רҵ����','mhcx','zymc','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','bjmc','�༶����','mhcx','bjmc','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','nj','�꼶','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xy','ѧԺ','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','zy','רҵ','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','bj','�༶','djcx','bjdm','','','4');

commit;
