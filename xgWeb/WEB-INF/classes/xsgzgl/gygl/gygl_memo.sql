/*
公寓管理模块说明：
====================================================
功能模块说明
====================================================
【情况一】
管理层次为两层，学校将床位分配至学院，学院安排学生入住
----------------------------------------------------
参数设置
	操纵时间设置【学校管理员】
房源管理
	楼栋管理【学校管理员】
	寝室管理【学校管理员】
	床位管理【学校管理员】
住宿管理
	床位分配管理【学校管理员】
	床位入住管理【学院管理员】
	住宿信息管理【公寓管理员】
统计查询
	公寓住宿统计【全部用户】
	学生住宿查询【全部用户】
	学生住宿统计【全部用户】
	退宿信息查询【全部用户】
====================================================
----------------------------------------------------
以下初始化语句仅适合新安装第三版公寓或者公寓数据尚未进入系统的学校
已有公寓数据的学校不要盲目执行以下语句
特别是建表语句，会先删除表再重建，容易造成数据丢失
----------------------------------------------------
*/
/*************************************************************************************************************/
/**********************************************数据库建表  sql *************************************************/
/*************************************************************************************************************/

----------------------------------------------------------------------------------------
-- 楼栋信息表---------------------------------------------------------------------------
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
  is '楼栋信息表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_LDXXB.LDDM
  is '楼栋代码';
comment on column XG_GYGL_NEW_LDXXB.LDMC
  is '楼栋名称';
comment on column XG_GYGL_NEW_LDXXB.LDXB
  is '楼栋性别';
comment on column XG_GYGL_NEW_LDXXB.LDCS
  is '楼栋层数';
comment on column XG_GYGL_NEW_LDXXB.QSCH
  is '起始层号';
comment on column XG_GYGL_NEW_LDXXB.SFHLC
  is '是否含0层';
comment on column XG_GYGL_NEW_LDXXB.XQDM
  is '校区代码';
comment on column XG_GYGL_NEW_LDXXB.YQDM
  is '园区代码';
comment on column XG_GYGL_NEW_LDXXB.BZ
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_LDXXB
  add constraint PK_LDXXB primary key (LDDM);

----------------------------------------------------------------------------------------
-- 寝室信息表---------------------------------------------------------------------------
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
  is '寝室信息表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_QSXXB.LDDM
  is '楼栋代码';
comment on column XG_GYGL_NEW_QSXXB.QSH
  is '寝室号';
comment on column XG_GYGL_NEW_QSXXB.CH
  is '层号';
comment on column XG_GYGL_NEW_QSXXB.QSXB
  is '寝室性别';
comment on column XG_GYGL_NEW_QSXXB.CWS
  is '床位数';
comment on column XG_GYGL_NEW_QSXXB.SFBZ
  is '收费标准';
comment on column XG_GYGL_NEW_QSXXB.QSDH
  is '寝室电话';
comment on column XG_GYGL_NEW_QSXXB.XYDM
  is '所属学院代码';
comment on column XG_GYGL_NEW_QSXXB.NJ
  is '所属年级';
comment on column XG_GYGL_NEW_QSXXB.BZ
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_QSXXB
  add constraint PK_QSXXB primary key (LDDM, QSH);

----------------------------------------------------------------------------------------
-- 床位信息表 ---------------------------------------------------------------------------
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
  SFBL  VARCHAR2(2) default '否',
  RZSJ  VARCHAR2(50),
  RZCZR VARCHAR2(100)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_CWXXB
  is '床位信息表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_CWXXB.LDDM
  is '楼栋代码';
comment on column XG_GYGL_NEW_CWXXB.QSH
  is '寝室号';
comment on column XG_GYGL_NEW_CWXXB.CWH
  is '床位号';
comment on column XG_GYGL_NEW_CWXXB.XYDM
  is '所属学院代码';
comment on column XG_GYGL_NEW_CWXXB.NJ
  is '所属年级';
comment on column XG_GYGL_NEW_CWXXB.BJDM
  is '所属班级代码';
comment on column XG_GYGL_NEW_CWXXB.XH
  is '现住宿学生学号';
comment on column XG_GYGL_NEW_CWXXB.BZ
  is '备注';
comment on column XG_GYGL_NEW_CWXXB.SFBL
  is '是否保留';
comment on column XG_GYGL_NEW_CWXXB.RZSJ
  is '入住时间';
comment on column XG_GYGL_NEW_CWXXB.RZCZR
  is '入住操作人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_CWXXB
  add constraint PK_CWXXB primary key (LDDM, QSH, CWH);

----------------------------------------------------------------------------------------
-- 退宿信息表 ---------------------------------------------------------------------------
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
  is '公寓退宿信息表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_TSXXB.XH
  is '学号';
comment on column XG_GYGL_NEW_TSXXB.LDMC
  is '楼栋名称';
comment on column XG_GYGL_NEW_TSXXB.QSH
  is '寝室号';
comment on column XG_GYGL_NEW_TSXXB.CWH
  is '床位号';
comment on column XG_GYGL_NEW_TSXXB.TSYY
  is '退宿原因';
comment on column XG_GYGL_NEW_TSXXB.TSSJ
  is '退宿时间';
comment on column XG_GYGL_NEW_TSXXB.BZ
  is '备注';
comment on column XG_GYGL_NEW_TSXXB.TSCZR
  is '退宿操作人';
comment on column XG_GYGL_NEW_TSXXB.LDDM
  is '楼栋代码';
comment on column XG_GYGL_NEW_TSXXB.NJ
  is '年级';
comment on column XG_GYGL_NEW_TSXXB.XYDM
  is '学院代码';
comment on column XG_GYGL_NEW_TSXXB.ZYDM
  is '专业代码';
comment on column XG_GYGL_NEW_TSXXB.BJDM
  is '班级代码';
comment on column XG_GYGL_NEW_TSXXB.RZSJ
  is '入住时间';
comment on column XG_GYGL_NEW_TSXXB.RZCZR
  is '入住操作人';
comment on column XG_GYGL_NEW_TSXXB.XYMC
  is '学院名称';
comment on column XG_GYGL_NEW_TSXXB.ZYMC
  is '专业名称';
comment on column XG_GYGL_NEW_TSXXB.BJMC
  is '班级名称';
comment on column XG_GYGL_NEW_TSXXB.XM
  is '姓名';
comment on column XG_GYGL_NEW_TSXXB.XB
  is '性别';
comment on column XG_GYGL_NEW_TSXXB.CZSJ
  is '操作时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_TSXXB
  add constraint TSXX_PRIMARY_ID primary key (XH, CZSJ);
  
----------------------------------------------------------------------------------------
-- 退宿原因代码表 ----------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_TSYYDMB;
create table XG_GYGL_NEW_TSYYDMB
(
  TSYYDM VARCHAR2(10),
  TSYYMC VARCHAR2(100)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_TSYYDMB
  is '退宿原因代码表(公寓第三版)';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_TSYYDMB.TSYYDM
  is '退宿原因代码';
comment on column XG_GYGL_NEW_TSYYDMB.TSYYMC
  is '退宿原因名称';

--退宿原因数据初始化
delete from xg_gygl_new_tsyydmb;
commit;
insert into xg_gygl_new_tsyydmb values('1','毕业离校');
insert into xg_gygl_new_tsyydmb values('2','住宿异动');
insert into xg_gygl_new_tsyydmb values('3','参军');
insert into xg_gygl_new_tsyydmb values('4','走读');
insert into xg_gygl_new_tsyydmb values('5','其他');
commit;
----------------------------------------------------------------------------------------
-- 取消入住开关控制表 -------------------------------------------------------------------
-- Create table
drop table XG_GYGL_NEW_QXRZKGKZB;
create table XG_GYGL_NEW_QXRZKGKZB
(
  XYDM VARCHAR2(20) not null,
  NJ   VARCHAR2(4) not null,
  SFQY VARCHAR2(10) default '否',
  KSSJ VARCHAR2(50),
  JSSJ VARCHAR2(50)
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_QXRZKGKZB
  is '取消入住开关控制表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_QXRZKGKZB.XYDM
  is '学院代码';
comment on column XG_GYGL_NEW_QXRZKGKZB.NJ
  is '年级';
comment on column XG_GYGL_NEW_QXRZKGKZB.SFQY
  is '是否启用';
comment on column XG_GYGL_NEW_QXRZKGKZB.KSSJ
  is '开始时间';
comment on column XG_GYGL_NEW_QXRZKGKZB.JSSJ
  is '结束时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_QXRZKGKZB
  add constraint PK_QXRZKGKZ primary key (XYDM, NJ);
  
----------------------------------------------------------------------------------------
-- 床位信息导入临时表 -------------------------------------------------------------------
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
  is '床位信息导入临时表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_IMPCWXXB.LDDM
  is '楼栋代码';
comment on column XG_GYGL_NEW_IMPCWXXB.QSH
  is '寝室号';
comment on column XG_GYGL_NEW_IMPCWXXB.CWH
  is '床位号';
comment on column XG_GYGL_NEW_IMPCWXXB.XH
  is '学号';
comment on column XG_GYGL_NEW_IMPCWXXB.MARK
  is '标记 1：数据正常 0：数据不正常';
comment on column XG_GYGL_NEW_IMPCWXXB.BZ
  is '备注';
comment on column XG_GYGL_NEW_IMPCWXXB.RZSJ
  is '入住时间';

----------------------------------------------------------------------------------------
-- 基础设置表 --------------------------------------------------------------------------- 
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
  is '公寓管理【第三版】基本设置表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_JBSZB.CSDM
  is '参数代码';
comment on column XG_GYGL_NEW_JBSZB.CSMC
  is '参数名称';
comment on column XG_GYGL_NEW_JBSZB.CSZ
  is '参数值';
comment on column XG_GYGL_NEW_JBSZB.BZ
  is '备注';

----------------------------------------------------------------------------------------
-- 公寓辅导员表 --------------------------------------------------------------------------- 
-- Create table
drop table XG_GYGL_NEW_GYFDYB;
create table XG_GYGL_NEW_GYFDYB
(
  YHM  VARCHAR2(20) not null,
  LDDM VARCHAR2(20) not null
);
-- Add comments to the table 
comment on table XG_GYGL_NEW_GYFDYB
  is '公寓辅导员表';
-- Add comments to the columns 
comment on column XG_GYGL_NEW_GYFDYB.YHM
  is '用户名';
comment on column XG_GYGL_NEW_GYFDYB.LDDM
  is '楼栋代码';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_GYFDYB
  add primary key (YHM, LDDM);

----------------------------------------------------------------------------------------
-- 床位信息视图 ---------------------------------------------------------------------------
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
case when a.xydm is null and a.nj is null then '否' else '是' end sffp,
case when a.xh is null then '否' else '是' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
comment on column xg_view_gygl_new_xszsgl.xh is '学号';
comment on column xg_view_gygl_new_xszsgl.xm is '姓名';
comment on column xg_view_gygl_new_xszsgl.xb is '性别';
comment on column xg_view_gygl_new_xszsgl.nj is '年级';
comment on column xg_view_gygl_new_xszsgl.xydm is '学院代码';
comment on column xg_view_gygl_new_xszsgl.xymc is '学院名称';
comment on column xg_view_gygl_new_xszsgl.lddm is '楼栋代码';
comment on column xg_view_gygl_new_xszsgl.ldmc is '楼栋名称';
comment on column xg_view_gygl_new_xszsgl.ch is '层号';
comment on column xg_view_gygl_new_xszsgl.qsh is '寝室号';
comment on column xg_view_gygl_new_xszsgl.cwh is '床位号';
comment on column xg_view_gygl_new_xszsgl.sfzs is '是否住宿';

----------------------------------------------------------------------------------------
--未住宿学生信息视图---------------------------------------------------------------------
create or replace view view_xg_gygl_new_wzsxsxx as
select a.* from  view_xsjbxx a 
where not exists (select 1 from (select a.xh,a.lddm,a.ldmc,a.ch,a.qsh,a.cwh,b.xm,b.xb,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.bjdm,b.nj
from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh where a.xh is not null) b where a.xh=b.xh);
/
comment on column view_xg_gygl_new_wzsxsxx.xh is '学号';
comment on column view_xg_gygl_new_wzsxsxx.xm is '姓名';
comment on column view_xg_gygl_new_wzsxsxx.xb is '性别';
comment on column view_xg_gygl_new_wzsxsxx.xymc is '学院名称';
comment on column view_xg_gygl_new_wzsxsxx.zymc is '专业名称';
comment on column view_xg_gygl_new_wzsxsxx.bjmc is '班级名称';
comment on column view_xg_gygl_new_wzsxsxx.nj is '年级';

----------------------------------------------------------------------------------------
--学生住宿管理视图---------------------------------------------------------------------
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,
b.lddm,b.ldmc,b.ch,b.qsh,b.cwh,(case when b.xh is not null then '是' else '否' end) sfzs 
from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh=b.xh;
/
comment on column xg_view_gygl_new_xszsgl.xh is '学号';
comment on column xg_view_gygl_new_xszsgl.xm is '姓名';
comment on column xg_view_gygl_new_xszsgl.xb is '性别';
comment on column xg_view_gygl_new_xszsgl.nj is '年级';
comment on column xg_view_gygl_new_xszsgl.xydm is '学院代码';
comment on column xg_view_gygl_new_xszsgl.xymc is '学院名称';
comment on column xg_view_gygl_new_xszsgl.lddm is '楼栋代码';
comment on column xg_view_gygl_new_xszsgl.ldmc is '楼栋名称';
comment on column xg_view_gygl_new_xszsgl.ch is '层号';
comment on column xg_view_gygl_new_xszsgl.qsh is '寝室号';
comment on column xg_view_gygl_new_xszsgl.cwh is '床位号';
comment on column xg_view_gygl_new_xszsgl.sfzs is '是否住宿';

/*************************************************************************************************************/
/***********************************************清空表数据  sql*************************************************/
/*************************************************************************************************************/
delete from xg_gygl_new_ldxxb;
delete from xg_gygl_new_qsxxb;
delete from xg_gygl_new_cwxxb;
delete from xg_gygl_new_tsxxb;
delete from xg_gygl_new_qxrzkgkzb;
delete from xg_gygl_new_impcwxxb;
delete from xg_gygl_new_gyfdyb;

-- 执行以下语句后，公寓的权限分配需重新赋权
/*************************************************************************************************************/
/********************************************功能模块代码增加sql***********************************************/
/*************************************************************************************************************/
--初始化功能模块代码--
delete from gnmkdmb where gnmkdm like 'N38%';
commit;
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N38','公寓管理','','1','009','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3800','参数设置','','1','','是','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380001','操作时间设置','gyglnew_cssz_sjsz.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3801','房源管理','','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380101','楼栋信息管理','gyglnew_ldgl_ldgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380102','寝室信息管理','gyglnew_qsgl_qsgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380103','床位信息管理','gyglnew_cwgl_cwgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3802','住宿管理','','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380201','床位分配管理','gyglnew_cwfpgl_cwfp.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380202','床位入住管理','gyglnew_cwrzgl_cwrz.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380203','住宿信息管理','gyglnew_zsxxgl_zsxxgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3803','统计查询','','1','','是','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380301','公寓住宿情况统计','gyglnew_xxtj_xxtj.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380302','学生住宿情况统计','gyglnew_xszstj_xszstj.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380303','学生住宿信息查询','gyglnew_xszsgl_xszsgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380304','学生退宿信息查询','gyglnew_tsgl_tsgl.do','1','','是','','');
commit;
--初始化超级管理员zf01权限--
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
/********************************************高级查询配置语句sql***********************************************/
/*************************************************************************************************************/
--初始化高级查询语句--
delete from xg_search_szb where path like 'gyglnew_%';
commit;
--操作时间设置
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','sf','是否启用','djcx','sfqy','gygl_third','3');
--楼栋信息管理
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldxb','楼栋性别','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','lddm','楼栋代码','mhcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldmc','楼栋名称','mhcx','','gygl_third','2');
--寝室信息管理
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','寝室性别','djcx','qsxb','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sf','是否含空床位','djcx','sfhkcw','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室号','mhcx','','gygl_third','2');
--床位信息管理
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xb','床位性别','djcx','qsxb','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','床位是否保留','djcx','sfbl','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','床位是否分配','djcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','床位是否入住','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','姓名','mhcx','','gygl_third','4');
--床位分配管理
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xb','性别限定','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xymc','学院名称','mhcx','','gygl_third','1');
--床位入住管理
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xb','性别限定','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xymc','学院名称','mhcx','','gygl_third','1');
--住宿信息管理
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xb','床位性别','djcx','qsxb','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','床位是否保留','djcx','sfbl','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sffp','床位是否分配','djcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','床位是否入住','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','姓名','mhcx','','gygl_third','4');
--退宿信息查询
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xb','性别','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','ld','楼栋','djcx','lddm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','rzsj','入住时间','sjcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tssj','退宿时间','sjcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tsyy','退宿原因','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xh','学号','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xm','姓名','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','qsh','寝室号','mhcx','','gygl_third','3');
--学生住宿信息查询
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','sf','是否住宿','djcx','sfzs','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xm','姓名','mhcx','','gygl_third','4');
--学生住宿情况统计
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','xy','学院','djcx','xydm','gygl_third','2');
--高级查询--床位分配（学院—班级）
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','zy','专业','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','bj','班级','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xb','性别限定','djcx','','gygl_third','5');
--高级查询--床位入住（学院-班级）
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','zy','专业','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','bj','班级','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xb','性别限定','djcx','','gygl_third','5');
commit;

