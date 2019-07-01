--功能模块--
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4102', '浙江建设', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410201', '走读申请', 'gygl_zjjs_zdsq.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410202', '走读审核', 'gygl_zjjs_zdsh.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410203', '走读结果', 'gygl_zjjs_zdjg.do', '1', '');

commit;

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4102', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410201', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410202', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410203', '1');

commit;

--新增表(走读生申请表)--
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
bjsh	 varchar2(20) default '未审核',
bjshr	 varchar2(20),
bjshyj	 varchar2(1000),
bjshsj	 varchar2(20),
xysh	 varchar2(20) default '未审核',
xyshr	 varchar2(20),
xyshyj	 varchar2(1000),
xyshsj	 varchar2(20),
primary key(id)
);

comment on table xg_gygl_zjjs_zdssqb is '走读生申请表';
comment on column xg_gygl_zjjs_zdssqb.id is 'ID';
comment on column xg_gygl_zjjs_zdssqb.xh is '学号';
comment on column xg_gygl_zjjs_zdssqb.lxdh is '联系电话';
comment on column xg_gygl_zjjs_zdssqb.zsdd is '住宿地址';
comment on column xg_gygl_zjjs_zdssqb.jtdz is '家庭地址';
comment on column xg_gygl_zjjs_zdssqb.jtdh is '家庭电话';
comment on column xg_gygl_zjjs_zdssqb.zdkssj is '走读开始时间';
comment on column xg_gygl_zjjs_zdssqb.zdjssj is '走读结束时间';
comment on column xg_gygl_zjjs_zdssqb.sqly is '申请理由';
comment on column xg_gygl_zjjs_zdssqb.sqsj is '申请时间';
comment on column xg_gygl_zjjs_zdssqb.bz is '备注';
comment on column xg_gygl_zjjs_zdssqb.bjsh is '辅导员审核';
comment on column xg_gygl_zjjs_zdssqb.bjshr is '辅导员审核人';
comment on column xg_gygl_zjjs_zdssqb.bjshyj is '辅导员审核意见';
comment on column xg_gygl_zjjs_zdssqb.bjshsj is '辅导员审核时间';
comment on column xg_gygl_zjjs_zdssqb.xysh is '学院审核';
comment on column xg_gygl_zjjs_zdssqb.xyshr is '学院审核人';
comment on column xg_gygl_zjjs_zdssqb.xyshyj is '学院审核意见';
comment on column xg_gygl_zjjs_zdssqb.xyshsj is '学院审核时间';

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
comment on column xg_view_gygl_zjjs_zdssqb.pk is '主键';
comment on column xg_view_gygl_zjjs_zdssqb.id is 'ID';
comment on column xg_view_gygl_zjjs_zdssqb.xh is '学号';
comment on column xg_view_gygl_zjjs_zdssqb.xm is '姓名';
comment on column xg_view_gygl_zjjs_zdssqb.xb is '性别';
comment on column xg_view_gygl_zjjs_zdssqb.nj is '年级';
comment on column xg_view_gygl_zjjs_zdssqb.xydm is '学院代码';
comment on column xg_view_gygl_zjjs_zdssqb.xymc is '学院名称';
comment on column xg_view_gygl_zjjs_zdssqb.zydm is '专业名称';
comment on column xg_view_gygl_zjjs_zdssqb.zymc is '专业名称';
comment on column xg_view_gygl_zjjs_zdssqb.bjdm is '班级名称';
comment on column xg_view_gygl_zjjs_zdssqb.bjmc is '班级名称';
comment on column xg_view_gygl_zjjs_zdssqb.lxdh is '联系电话';
comment on column xg_view_gygl_zjjs_zdssqb.zsdd is '住宿地址';
comment on column xg_view_gygl_zjjs_zdssqb.jtdz is '家庭地址';
comment on column xg_view_gygl_zjjs_zdssqb.jtdh is '家庭电话';
comment on column xg_view_gygl_zjjs_zdssqb.zdkssj is '走读开始时间';
comment on column xg_view_gygl_zjjs_zdssqb.zdjssj is '走读结束时间';
comment on column xg_view_gygl_zjjs_zdssqb.sqly is '申请理由';
comment on column xg_view_gygl_zjjs_zdssqb.sqsj is '申请时间';
comment on column xg_view_gygl_zjjs_zdssqb.bz is '备注';
comment on column xg_view_gygl_zjjs_zdssqb.bjsh is '辅导员审核';
comment on column xg_view_gygl_zjjs_zdssqb.bjshr is '辅导员审核人';
comment on column xg_view_gygl_zjjs_zdssqb.bjshyj is '辅导员审核意见';
comment on column xg_view_gygl_zjjs_zdssqb.bjshsj is '辅导员审核时间';
comment on column xg_view_gygl_zjjs_zdssqb.xysh is '学院审核';
comment on column xg_view_gygl_zjjs_zdssqb.xyshr is '学院审核人';
comment on column xg_view_gygl_zjjs_zdssqb.xyshyj is '学院审核意见';
comment on column xg_view_gygl_zjjs_zdssqb.xyshsj is '学院审核时间';

delete from xg_search_szb where path='gygl_zjjs_zdsh.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','nj','年级','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','xy','学院','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','zy','专业','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','bj','班级','djcx','bjdm','','','8');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdsh.do','shzt','审核状态','djcx','','','','9');

commit;

delete from xg_search_szb where path='gygl_zjjs_zdjg.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','nj','年级','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','xy','学院','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','zy','专业','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','bj','班级','djcx','bjdm','','','8');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('gygl_zjjs_zdjg.do','shzt','最终审核','djcx','xysh','','','9');

commit;