-------------------表-----------------------------
create table xg_jygl_sxjyb(
xh   varchar2(20),
sxqk   varchar2(1000),
jydw   varchar2(1000),
primary key(xh)
);
comment on table xg_jygl_sxjyb is '学工_就业管理_实习就业表';
comment on column xg_jygl_sxjyb.xh is '学号';
comment on column xg_jygl_sxjyb.sxqk is '实习情况';
comment on column xg_jygl_sxjyb.jydw is '就业情况';

-------------------视图-----------------------------
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
comment on column xg_view_jygl_sxjy.XH is '学号';
comment on column xg_view_jygl_sxjy.XM is '姓名';
comment on column xg_view_jygl_sxjy.NJ is '年级';
comment on column xg_view_jygl_sxjy.XYDM is '学院代码';
comment on column xg_view_jygl_sxjy.XYMC is '学院名称';
comment on column xg_view_jygl_sxjy.ZYDM is '专业代码';
comment on column xg_view_jygl_sxjy.ZYMC is '专业名称';
comment on column xg_view_jygl_sxjy.BJDM is '班级代码';
comment on column xg_view_jygl_sxjy.BJMC is '班级名称';
comment on column xg_view_jygl_sxjy.sxqk is '实习情况';
comment on column xg_view_jygl_sxjy.jydw is '就业单位';

-------------------实习就业----------------------------
delete from xg_search_szb where path='jygl_general_sxjy.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xm','姓名','mhcx','xm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xymc','院系名称','mhcx','xymc','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','zymc','专业名称','mhcx','zymc','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','bjmc','班级名称','mhcx','bjmc','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('jygl_general_sxjy.do','bj','班级','djcx','bjdm','','','4');

commit;
