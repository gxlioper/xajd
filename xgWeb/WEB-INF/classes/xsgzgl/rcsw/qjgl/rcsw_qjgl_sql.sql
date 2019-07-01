/**日常事务_请假管理——数据库语句**/
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4103', '请假管理', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410301', '审核流程定义', 'rcsw_qjgl_cssz_qjlc.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410302', '我的请假', 'rcsw_qjgl_mygz_stu.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410303', '我的工作', 'rcsw_qjgl_mygz_tea.do', '1', '');

commit;

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4103', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410301', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410302', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410303', '1');

commit;

--新增表(请假类型维护表)--
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

comment on table xg_rcsw_qjgl_qjlxb is '评奖评优_日常事务_请假类型维护表';
comment on column xg_rcsw_qjgl_qjlxb.id is '项目ID';
comment on column xg_rcsw_qjgl_qjlxb.lxmc is '类型名称';
comment on column xg_rcsw_qjgl_qjlxb.lcid  is '流程ID';
comment on column xg_rcsw_qjgl_qjlxb.mints  is '最小天数';
comment on column xg_rcsw_qjgl_qjlxb.maxts  is '最大天数';
comment on column xg_rcsw_qjgl_qjlxb.xgr is '修改人';
comment on column xg_rcsw_qjgl_qjlxb.xgsj is '修改时间';

--新增表(请假申请表)--
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

comment on table xg_rcsw_qjgl_qjsqb is '评奖评优_日常事务_请假申请表';
comment on column xg_rcsw_qjgl_qjsqb.id is '项目ID';
comment on column xg_rcsw_qjgl_qjsqb.qjid  is '请假ID';
comment on column xg_rcsw_qjgl_qjsqb.xn  is '学年';
comment on column xg_rcsw_qjgl_qjsqb.xq  is '学期';
comment on column xg_rcsw_qjgl_qjsqb.xh  is '学号';
comment on column xg_rcsw_qjgl_qjsqb.sqsj  is '申请时间';
comment on column xg_rcsw_qjgl_qjsqb.sqts is '申请天数';
comment on column xg_rcsw_qjgl_qjsqb.kssj is '开始时间';
comment on column xg_rcsw_qjgl_qjsqb.jssj is '结束时间';
comment on column xg_rcsw_qjgl_qjsqb.lxdh is '联系电话';
comment on column xg_rcsw_qjgl_qjsqb.jtdh is '家庭电话';
comment on column xg_rcsw_qjgl_qjsqb.jtdz is '家庭地址';
comment on column xg_rcsw_qjgl_qjsqb.sqly is '申请理由';
comment on column xg_rcsw_qjgl_qjsqb.bz is '备注';
comment on column xg_rcsw_qjgl_qjsqb.sqjg is '申请结果';
comment on column xg_rcsw_qjgl_qjsqb.kzzd1 is '扩展字段1';
comment on column xg_rcsw_qjgl_qjsqb.kzzd2 is '扩展字段2';
comment on column xg_rcsw_qjgl_qjsqb.kzzd3 is '扩展字段3';
comment on column xg_rcsw_qjgl_qjsqb.kzzd4 is '扩展字段4';
comment on column xg_rcsw_qjgl_qjsqb.kzzd5 is '扩展字段5';

--新增表(请假审核表)--
create table xg_rcsw_qjgl_qjshb(
sqid    varchar2(40),
gwid    varchar2(40),
shr     varchar2(40),
shzt    varchar2(20) default '未审核',
shsj    varchar2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
shyj    varchar2(1000),
primary key(sqid,gwid)
);

comment on table xg_rcsw_qjgl_qjshb is '评奖评优_日常事务_请假申请表';
comment on column xg_rcsw_qjgl_qjshb.sqid is '申请ID';
comment on column xg_rcsw_qjgl_qjshb.gwid  is '岗位ID';
comment on column xg_rcsw_qjgl_qjshb.shr  is '审核人';
comment on column xg_rcsw_qjgl_qjshb.shzt  is '审核状态';
comment on column xg_rcsw_qjgl_qjshb.shsj is '审核时间';
comment on column xg_rcsw_qjgl_qjshb.shyj is '审核意见';

-----------------高级查询------------------------------
-----------------审核------------------------------
delete from xg_search_szb where path='rcsw_qjgl_mysh.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','nj','年级','djcx','nj','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','xy','学院','djcx','xydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','zy','专业','djcx','zydm','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','bj','班级','djcx','bjdm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('rcsw_qjgl_mysh.do','shzt','班级','djcx','','','','7');

commit;


-----------------------日常事务 请假管理 请假申请--------------------------------
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
(case when sqjg ='未审核' then sqjg when sqjg='批准请假' then sqjg else '审核中' end)sqjg,
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
comment on column XG_VIEW_RCSW_QJGL_QJSQ.ZYDM is '专业代码';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.ZYMC is '专业';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.BJDM is '班级代码';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.BJMC is '班级';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XM is '姓名';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XB is '性别';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.ID is 'id';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.QJID is '请假类型代码';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XN is '学年';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XQ is '学期';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XH is '学号';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQSJ is '申请时间';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQTS is '申请天数';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KSSJ is '请假开始时间';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.JSSJ is '请假结束时间';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.LXDH is '联系电话';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.JTDH is '家庭电话';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.JTDZ is '家庭地址';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQLY is '申请理由';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.BZ is '备注';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQJGXX is '申请结果';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.SQJG is '申请结果';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.QJLX is '请假类型';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD1 is '扩展字段1';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD2 is '扩展字段2';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD3 is '扩展字段3';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD4 is '扩展字段4';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.KZZD5 is '扩展字段5';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.LXMC is '联系名称';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.MINTS is '最小天数';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.MAXTS is '最大天数';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.NJ is '年级';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XYDM is '学院代码';
comment on column XG_VIEW_RCSW_QJGL_QJSQ.XYMC is '学院';

-----------------------结果查询页面 高级查询------------------------------
delete from xg_search_szb where path='rcsw_qjgl_jgcx.do';
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','xh','学号','mhcx','xh','1');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','xm','姓名','mhcx','xm','2');

insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','nj','年级','djcx','nj','3');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','xy','学院','djcx','xydm','4');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','zy','专业','djcx','zydm','5');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','bj','班级','djcx','bjdm','6');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','qjlx','请假类型','djcx','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('rcsw_qjgl_jgcx.do','sqjg','申请结果','djcx','','8');
commit;



------------------------------导出表设置-----------------------------
delete from dcb where zdssb='xg_view_rcsw_qjgl_qjsq' and xxdm='public';
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xn','xg_view_rcsw_qjgl_qjsq','public','学年');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xq','xg_view_rcsw_qjgl_qjsq','public','学期');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xh','xg_view_rcsw_qjgl_qjsq','public','学号');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xm','xg_view_rcsw_qjgl_qjsq','public','姓名');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xb','xg_view_rcsw_qjgl_qjsq','public','性别');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('nj','xg_view_rcsw_qjgl_qjsq','public','年级');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('xymc','xg_view_rcsw_qjgl_qjsq','public','学院');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('zymc','xg_view_rcsw_qjgl_qjsq','public','专业');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('bjmc','xg_view_rcsw_qjgl_qjsq','public','班级');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('sqsj','xg_view_rcsw_qjgl_qjsq','public','申请时间');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('sqts','xg_view_rcsw_qjgl_qjsq','public','申请天数');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('kssj','xg_view_rcsw_qjgl_qjsq','public','请假开始时间');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('jssj','xg_view_rcsw_qjgl_qjsq','public','请假结束时间');
insert into dcb(zdmc,zdssb,xxdm,zdsm)values('lxmc','xg_view_rcsw_qjgl_qjsq','public','请假类型');
commit;

--新增表(请假字段设置表)--
create table xg_rcsw_qjgl_qjsqzdb(
zd     varchar2(40),
zdm    varchar2(40),
lx     varchar2(40),
zdz    varchar2(20),
primary key(zd)
);

comment on table xg_rcsw_qjgl_qjsqzdb is '评奖评优_日常事务_请假申请字段表';
comment on column xg_rcsw_qjgl_qjsqzdb.zd is '字段';
comment on column xg_rcsw_qjgl_qjsqzdb.zdm  is '字段名称';
comment on column xg_rcsw_qjgl_qjsqzdb.lx  is '类型';
comment on column xg_rcsw_qjgl_qjsqzdb.zdz  is '字段值';

---------------------日常事务 请假管理 请假审核-------------------------------
create or replace view xg_view_rcsw_qjgl_qjshb 
as
select a.*,c.id from xg_rcsw_qjgl_qjshb a left join xg_rcsw_qjgl_qjsqb b on a.sqid=b.id
left join xg_rcsw_qjgl_qjlxb c on b.qjid=c.id
/
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SQID is '申请代码';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.GWID is '岗位代码';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHR is '审核人';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHZT is '审核状态';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHSJ is '审核时间';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.SHYJ is '审核意见';
comment on column XG_VIEW_RCSW_QJGL_QJSHB.ID is '代码';


--审批流程 日常事务设置--
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