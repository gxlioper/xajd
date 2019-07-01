/**公寓管理_第三版——数据库语句**/

--增加功能模块--
delete from gnmkdmb where gnmkdm like 'N38%';
commit;
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N38','公寓管理(new)','','1','009','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N3801','房源管理','','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380101','楼栋信息管理','gyglnew_ldgl_ldgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380102','寝室信息管理','gyglnew_qsgl_qsgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380103','床位信息管理','gyglnew_cwgl_cwgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N3802','住宿管理','','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380201','床位分配管理','gyglnew_cwfpgl_cwfp.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380202','床位入住管理','gyglnew_cwrzgl_cwrz.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380203','退宿信息管理','gyglnew_tsgl_plts.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N3803','统计查询','','1','','是','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) 
  values('N380301','公寓住宿统计','gyglnew_xxtj_xxtj.do','1','','是','','');
commit;
	
--增加用户权限--
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

--创建楼栋信息表
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

--创建寝室信息表--
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

--创建床位信息表--
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
  SFBL VARCHAR2(2) default '否'
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
comment on column XG_GYGL_NEW_CWXXB.CWSX
  is '床位属性';
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_GYGL_NEW_CWXXB
  add constraint PK_CWXXB primary key (LDDM, QSH, CWH);

--创建退宿信息表--
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
  
--创建退宿原因代码表
-- Create table
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



/**********************高级查询配置*************************/
delete from xg_search_szb where path like 'gyglnew_%';
--楼栋管理模块--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','lddm','楼栋代码','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldmc','楼栋名称','mhcx','','gygl_third','2');
--寝室管理模块--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','寝室性别','djcx','qsxb','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室号','mhcx','','gygl_third','2');
--床位管理模块--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','床位是否保留','djcx','sfbl','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','姓名','mhcx','','gygl_third','4');
--床位分配管理模块--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xb','性别限定','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp.do','xymc','学院名称','mhcx','','gygl_third','1');
--床位入住管理模块--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xb','性别限定','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz.do','xymc','学院名称','mhcx','','gygl_third','1');
--退宿管理模块--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xb','性别限定','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tsyy','退宿原因','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tssj','退宿时间','sjcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xh','学号','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xm','姓名','mhcx','','gygl_third','2');
--退宿管理模块--批量退宿--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_plts.do','xm','姓名','mhcx','','gygl_third','4');
commit;

--视图xg_jcsj_bmdmb变更为view_njxyzybj create by cef
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
--添加退书原因代码表初始化语句 create by cef
delete from xg_gygl_new_tsyydmb;
commit;
insert into xg_gygl_new_tsyydmb values('1','毕业离校');
insert into xg_gygl_new_tsyydmb values('2','住宿异动');
insert into xg_gygl_new_tsyydmb values('3','参军');
insert into xg_gygl_new_tsyydmb values('4','走读');
insert into xg_gygl_new_tsyydmb values('5','其他');
commit;

--修改床位信息视图
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
case when a.xydm is null and a.nj is null then '否' else '是' end sffp,
case when a.xh is null then '否' else '是' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
commit;
------以上内容为版本V5.0.4---------------------
------以上内容为版本V5.0.4--------------------

--取消入住开关控制表--
--20111024--zhanghui--
-- Create table
create table xg_gygl_new_qxrzkgkzb
(
  xydm varchar2(20),
  nj   varchar2(4),
  sfqy varchar2(10) default '否',
  kssj varchar2(50),
  jssj varchar2(50)
)
;
-- Add comments to the table 
comment on table xg_gygl_new_qxrzkgkzb
  is '取消入住开关控制表';
-- Add comments to the columns 
comment on column xg_gygl_new_qxrzkgkzb.xydm
  is '学院代码';
comment on column xg_gygl_new_qxrzkgkzb.nj
  is '年级';
comment on column xg_gygl_new_qxrzkgkzb.sfqy
  is '是否启用';
comment on column xg_gygl_new_qxrzkgkzb.kssj
  is '开始时间';
comment on column xg_gygl_new_qxrzkgkzb.jssj
  is '结束时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table xg_gygl_new_qxrzkgkzb
  add constraint pk_qxrzkgkz primary key (XYDM, NJ);
  
--增加功能模块--
--20111024--zhanghui--
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3800','参数设置','','1','','是','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380001','操作时间设置','gyglnew_cssz_sjsz.do','1','','是','','');

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3800','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380001','1');

--修改模块名称，将“退宿信息管理”修改为“住宿信息管理”
update gnmkdmb set gnmkmc='住宿信息管理' where gnmkdm ='N380203';
commit;

--配合高级查询修改高级查询配置表数据--
--20111024--zhanghui--
update xg_search_szb set tj='ld',zd='lddm' where path like 'gyglnew_%' and (tj ='lddm' or tj='ld');
commit;

--公寓管理【第三版】基本设置表--
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
  
--新增高级查询条件--
--20111024--zhanghui--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','寝室性别','djcx','qsxb','gygl_third','4');
commit;

--2011-10-25 gaobb 增加班级名称
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select bjmc from view_njxyzybj x where x.bjdm=a.bjdm) cwbjmc,
case when a.xydm is null and a.nj is null then '否' else '是' end sffp,
case when a.xh is null then '否' else '是' end sfrz
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
  is '床位信息导入临时表';
  
-- Add/modify columns 
alter table XG_GYGL_NEW_IMPCWXXB add mark char(1);
alter table XG_GYGL_NEW_IMPCWXXB add bz varchar2(50);
alter table XG_GYGL_NEW_IMPCWXXB modify MARK default '1';
commit;

--增加高级查询条件，操作时间设置--
--20111026--zhanghui--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','sf','是否启用','djcx','sfqy','gygl_third','3');
commit;


--


------------------20111026--中医药已升级已升级
----------------------------浙江工业职业已升级

-------------------------------------------------
--------------2011-10-27--zhanghui---------------
-------------------------------------------------
--增加高级查询条件--
--床位信息管理--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','床位是否分配','djcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','床位是否入住','djcx','','gygl_third','8');
--楼栋信息管理--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldxb','楼栋性别','djcx','','gygl_third','2');
commit;

--楼栋导出字段设置，暂时去掉校区代码，园区代码--
delete from dcb where zdssb = 'xg_gygl_new_ldxxb';
commit;
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('lddm','xg_gygl_new_ldxxb','public','楼栋代码');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('ldmc','xg_gygl_new_ldxxb','public','楼栋名称');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('ldxb','xg_gygl_new_ldxxb','public','楼栋性别');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('ldcs','xg_gygl_new_ldxxb','public','楼栋层数');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('qsch','xg_gygl_new_ldxxb','public','起始层号');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('sfhlc','xg_gygl_new_ldxxb','public','是否含0层');
insert into dcb (zdmc,zdssb,xxdm,zdsm) values('bz','xg_gygl_new_ldxxb','public','备注');
commit;

------------------20111027--浙江商业职业已升级
-------------------------------------

--添加学生住宿和统计功能
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc)
  values('N380302','学生住宿信息','gyglnew_xszsgl_xszsgl.do','1','','是','','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc)
  values('N380303','学生住宿统计','gyglnew_xszstj_xszstj.do','1','','是','','');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380302','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380303','1');
commit;
--2011-10-28 gaobb 学生住宿管理视图
create or replace view xg_view_gygl_new_xszsgl as
select a.xh pk,a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,
b.lddm,b.ldmc,b.ch,b.qsh,b.cwh,(case when b.xh is not null then '是' else '否' end) sfzs 
from view_xsjbxx a left join view_xg_gygl_new_cwxx b on a.xh=b.xh;
/
comment on column xg_view_gygl_new_xszsgl.pk is '主键';
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


-------------------------------------------------
--------------2011-10-28--zhanghui---------------
-------------------------------------------------
--增加高级查询条件，学生住宿信息查询--
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
commit;

--未住宿学生信息视图
create or replace view view_xg_gygl_new_wzsxsxx as
select a.* from  view_xsjbxx a 
where not exists (select 1 from (select a.xh,a.lddm,a.ldmc,a.ch,a.qsh,a.cwh,b.xm,b.xb,b.xydm,b.xymc,b.zydm,b.zymc,b.bjmc,b.bjdm,b.nj
from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh where a.xh is not null) b where a.xh=b.xh);

comment on column view_xg_gygl_new_wzsxsxx.xh is '学号';
comment on column view_xg_gygl_new_wzsxsxx.xm is '姓名';
comment on column view_xg_gygl_new_wzsxsxx.xb is '性别';
comment on column view_xg_gygl_new_wzsxsxx.xymc is '学院名称';
comment on column view_xg_gygl_new_wzsxsxx.zymc is '专业名称';
comment on column view_xg_gygl_new_wzsxsxx.bjmc is '班级名称';
comment on column view_xg_gygl_new_wzsxsxx.nj is '年级';

--删除床位信息表中的“床位属性”字段
alter table XG_GYGL_NEW_CWXXB drop column CWSX;


-------------------------------------------------
--------------2011-10-31--zhanghui---------------
-------------------------------------------------
--增加高级查询条件——学生住宿统计——年级、学院
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','xy','学院','djcx','xydm','gygl_third','2');

--增加高级查询条件——寝室信息管理——是否含空床位sfzm
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sf','是否含空床位','djcx','sfhkcw','gygl_third','5');
commit;

--2011-10-31 gaobb 用于优化导入床位入住性能-----------
create index index_impcwxxb on XG_GYGL_NEW_IMPCWXXB (lddm, qsh, cwh);

------------------ v-----------------红河学院已经升级2011-10-31
-------------------------------------红河学院已经升级2011-10-31

--------------------苏州工业园区职业技术学院已经升级20111101 by cef
--------------------成都体育已经升级20111101 by cef

--2011-11-01 sjf 公寓辅导员表
create table xg_gygl_new_gyfdyb(
yhm varchar2(20),
lddm varchar2(20),
primary key(yhm,lddm)
);
/
comment on table xg_gygl_new_gyfdyb is '公寓辅导员表';
comment on column xg_gygl_new_gyfdyb.yhm is '用户名';
comment on column xg_gygl_new_gyfdyb.lddm is '楼栋代码';
commit;

-------------------------------------------------
--------------2011-11-01--zhanghui---------------
-------------------------------------------------
--退宿信息表增加字段--
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

--增加功能模块--退宿信息查询--
update gnmkdmb set dyym='gyglnew_zsxxgl_zsxxgl.do' where gnmkdm ='N380203';
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc)
  values('N380304','退宿信息查询','gyglnew_tsgl_tsgl.do','1','','是','','');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380304','1');
update gnmkdmb set gnmkmc='统计查询' where gnmkdm ='N3803';
commit;

--修改表结构 sjf---------
alter table xg_gygl_new_tsxxb add czsj varchar2(50) default to_char(sysdate,'yyyy-MM-dd HH24:mi:ss');
alter table xg_gygl_new_tsxxb add constraint tsxx_primary_id primary key(xh,czsj);

comment on column xg_gygl_new_tsxxb.czsj
  is '操作时间';  

--2011-11-01 gaobb--
-- Add/modify columns 
alter table XG_GYGL_NEW_CWXXB add rzsj varchar2(50) default to_char(sysdate,'yyyy-mm-dd');
alter table XG_GYGL_NEW_CWXXB add rzczr varchar2(100);
-- Add comments to the columns 
comment on column XG_GYGL_NEW_CWXXB.rzsj
  is '入住时间';
comment on column XG_GYGL_NEW_CWXXB.rzczr
  is '入住操作人';
--增加入住时间和入住操作人字段
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


-------------------------------------------------
--------------2011-11-02--zhanghui---------------
-------------------------------------------------
--增加高级查询条件--住宿信息管理--
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','床位是否保留','djcx','sfbl','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sffp','床位是否分配','djcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','床位是否入住','djcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','姓名','mhcx','','gygl_third','4');
commit;

--2011-11-2
-- Add/modify columns 
alter table XG_GYGL_NEW_IMPCWXXB add rzsj varchar2(50);
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
comment on column XG_GYGL_NEW_IMPCWXXB.rzsj
  is '入住时间';
  


-------------------------------------------------
--------------2011-11-03--zhanghui---------------
-------------------------------------------------
--高级查询条件修改
--退宿管理模块--
delete from xg_search_szb where path like 'gyglnew_tsgl%';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','nj','年级','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xy','学院','djcx','xydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xb','性别','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','rzsj','入住时间','sjcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tssj','退宿时间','sjcx','','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','tsyy','退宿原因','djcx','','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xh','学号','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','xm','姓名','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_tsgl_tsgl.do','qsh','寝室号','mhcx','','gygl_third','3');
commit;
--床位信息管理
delete from xg_search_szb where path like 'gyglnew_cwgl_cwgl.do';
commit;
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
commit;
--住宿信息管理
delete from xg_search_szb where path like 'gyglnew_zsxxgl_zsxxgl.do';
commit;
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
commit;

--退宿信息表comment
comment on column xg_gygl_new_tsxxb.lddm
  is '楼栋代码';
comment on column xg_gygl_new_tsxxb.nj
  is '年级';
comment on column xg_gygl_new_tsxxb.xm
  is '姓名';
comment on column xg_gygl_new_tsxxb.xb
  is '性别';
comment on column xg_gygl_new_tsxxb.xydm
  is '学院代码';
comment on column xg_gygl_new_tsxxb.zydm
  is '专业代码';
comment on column xg_gygl_new_tsxxb.bjdm
  is '班级代码';
comment on column xg_gygl_new_tsxxb.xymc
  is '学院名称';  
comment on column xg_gygl_new_tsxxb.zymc
  is '专业名称';
comment on column xg_gygl_new_tsxxb.bjmc
  is '班级名称';
comment on column xg_gygl_new_tsxxb.rzsj
  is '入住时间';
comment on column xg_gygl_new_tsxxb.rzczr
  is '入住操作人';  
comment on column xg_gygl_new_tsxxb.czsj
  is '操作时间';  

-----xsgzgl_tybb已经执行by陈恩夫20111103

-------------------------------------------------
--------------2011-11-04--zhanghui---------------
-------------------------------------------------
--高级查询条件修改
--操作时间设置
delete from xg_search_szb where path like 'gyglnew_cssz_sjsz.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','sf','是否启用','djcx','sfqy','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cssz_sjsz.do','xymc','学院名称','mhcx','','gygl_third','1');
commit;

--2011-11-04 gaobb 去除默认时间 
alter table XG_GYGL_NEW_CWXXB modify RZSJ default null;

--2011-10-04 学生住宿管理视图修改
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
-----xsgzgl_tybb已经执行by陈恩夫20111107
-----xsgzgl_setup已经执行by陈恩夫20111107
------以上内容为版本V5.0.5---------------------
------以上内容为版本V5.0.5--------------------
--------------2011-11-09--zhanghui---------------
-------------------------------------------------
--高级查询--床位分配（学院—班级）
delete from xg_search_szb where path like 'gyglnew_cwfpgl_cwfp_xy.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','zy','专业','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','bj','班级','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwfpgl_cwfp_xy.do','xb','性别限定','djcx','','gygl_third','5');
commit;
--高级查询--床位入住（学院-班级）
delete from xg_search_szb where path like 'gyglnew_cwrzgl_cwrz_xy.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','nj','年级','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xy','学院','djcx','xydm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','zy','专业','djcx','zydm','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','bj','班级','djcx','bjdm','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwrzgl_cwrz_xy.do','xb','性别限定','djcx','','gygl_third','5');
commit;
--高级查询--床位信息管理
delete from xg_search_szb where path like 'gyglnew_cwgl_cwgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','zy','专业','djcx','zydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','bj','班级','djcx','bjdm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xb','床位性别','djcx','qsxb','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','是否保留床位','djcx','sfbl','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','是否分配学院','djcx','sffpxy','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','是否入住','djcx','','gygl_third','11');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','姓名','mhcx','','gygl_third','4');
commit;
--高级查询--住宿信息管理
delete from xg_search_szb where path like 'gyglnew_zsxxgl_zsxxgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','zy','专业','djcx','zydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','bj','班级','djcx','bjdm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xb','床位性别','djcx','qsxb','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','是否保留床位','djcx','sfbl','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','是否入住','djcx','','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','姓名','mhcx','','gygl_third','4');
commit;
--床位信息视图，增加是否分配学院，是否分配班级--
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from view_njxyzybj x where x.bjdm=a.bjdm) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '否' else '是' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '否' else '是' end sffpbj,
case when a.xh is null then '否' else '是' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '是' else '否' end) sfzs,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=b.lddm) ldmc,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch 
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh;
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

--2011-11-14 gaobb 增加班级代码和名称字段
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.bjdm,a.bjmc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '是' else '否' end) sfzs,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=b.lddm) ldmc,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh;
/

comment on column xg_view_gygl_new_xszsgl.xh is '学号';
comment on column xg_view_gygl_new_xszsgl.xm is '姓名';
comment on column xg_view_gygl_new_xszsgl.xb is '性别';
comment on column xg_view_gygl_new_xszsgl.nj is '年级';
comment on column xg_view_gygl_new_xszsgl.xydm is '学院代码';
comment on column xg_view_gygl_new_xszsgl.xymc is '学院名称';
comment on column xg_view_gygl_new_xszsgl.lddm is '楼栋代码';
comment on column xg_view_gygl_new_xszsgl.qsh is '寝室号';
comment on column xg_view_gygl_new_xszsgl.cwh is '床位号';
comment on column xg_view_gygl_new_xszsgl.sfzs is '是否住宿';
comment on column xg_view_gygl_new_xszsgl.ldmc is '楼栋名称';
comment on column xg_view_gygl_new_xszsgl.ch is '层号';
comment on column xg_view_gygl_new_xszsgl.bjdm is '班级代码';
comment on column xg_view_gygl_new_xszsgl.bjmc is '班级名称';

-------------------------------------------------
--------------2011-11-15--zhanghui---------------
-------------------------------------------------
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz) 
  values('N3804','公寓报修','','1','','是','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz) 
  values('N380401','我的报修申请','gyglnew_gybxgl_gybxgl_stu.do','1','','是','');
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz) 
  values('N380402','报修申请管理','gyglnew_gybxgl_gybxgl.do','1','','是','');

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3804','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380401','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380402','1');
commit;

-- 床位信息视图，增加“寝室电话”
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,b.qsdh,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from view_njxyzybj x where x.bjdm=a.bjdm) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '否' else '是' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '否' else '是' end sffpbj,
case when a.xh is null then '否' else '是' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
-- 增加公寓报修表
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
  CLZT      VARCHAR2(20) default '未处理',
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
  is '楼栋代码';
comment on column XG_GYGL_NEW_GYBXB.QSH
  is '寝室号';
comment on column XG_GYGL_NEW_GYBXB.CWH
  is '床位号';
comment on column XG_GYGL_NEW_GYBXB.XH
  is '学号';
comment on column XG_GYGL_NEW_GYBXB.BXNR
  is '报修内容';
comment on column XG_GYGL_NEW_GYBXB.BXSJ
  is '报修时间';
comment on column XG_GYGL_NEW_GYBXB.JJCD
  is '紧急程度';
comment on column XG_GYGL_NEW_GYBXB.QWWXSJ_KS
  is '期望维修时间';
comment on column XG_GYGL_NEW_GYBXB.QWWXSJ_JS
  is '期望维修时间';
comment on column XG_GYGL_NEW_GYBXB.LXDH
  is '联系电话';
comment on column XG_GYGL_NEW_GYBXB.CLZT
  is '处理状态';
comment on column XG_GYGL_NEW_GYBXB.BCLYY
  is '不处理原因';
comment on column XG_GYGL_NEW_GYBXB.WXRY
  is '维修人员';
comment on column XG_GYGL_NEW_GYBXB.WXSJ
  is '维修时间';
comment on column XG_GYGL_NEW_GYBXB.WXFY
  is '维修费用';
comment on column XG_GYGL_NEW_GYBXB.WXNR
  is '维修内容';
comment on column XG_GYGL_NEW_GYBXB.MYCD
  is '满意程度';
comment on column XG_GYGL_NEW_GYBXB.PJ
  is '评价';

--高级查询条件增加--
--我的报修申请--
delete from xg_search_szb where path like 'gyglnew_gybxgl_gybxgl_stu.do';
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl_stu.do','clzt','处理状态','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl_stu.do','jjcd','紧急程度','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl_stu.do','bxsj','报修时间','sjcx','','gygl_third','1');
commit;
delete from xg_search_szb where path like 'gyglnew_gybxgl_gybxgl.do';
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','clzt','处理状态','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','jjcd','紧急程度','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','wxsj','维修时间','sjcx','','gygl_third','1');
commit;
--20111123 广东建设已升级

--2011-11-23 gaobb 学生住宿统计高级查询条件
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','nj','年级','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszstj_xszstj.do','xymc','学院','mhcx','','gygl_third','2');
commit;
--20111123 苏州工业园职业学院已升级

------以上内容为版本V5.0.6---------------------
------以上内容为版本V5.0.6--------------------
-----xsgzgl_tybb已经执行by陈恩夫20111125
-----xsgzgl_setup已经执行by陈恩夫20111125

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
  is '寝室信息导入更新表';
-- Add comments to the columns 
comment on column xg_gygl_new_impqsxxb.lddm
  is '楼栋代码';
comment on column xg_gygl_new_impqsxxb.qsh
  is '寝室号';
comment on column xg_gygl_new_impqsxxb.drzd
  is '导入字段';
comment on column XG_GYGL_NEW_IMPQSXXB.mark
  is '标记';

-- sjf
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_mrjlgl_mrjlgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_mrjlgl_mrjlgl.do','zbry','值班人员','djcx','zbrydm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_mrjlgl_mrjlgl.do','kssj','时间','sjcx','sj','gygl_third','7');
commit;

insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_mrjlgl_mrjlgl.do','zbrymc','值班人员','mhcx','1');
insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_mrjlgl_mrjlgl.do','ldmc','楼栋','mhcx','1');
insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_mrjlgl_mrjlgl.do','sj','时间','mhcx','1');
commit;

--sjf 公寓报修
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gybxgl_gybxgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb(path,tj,mc,lx,xssx) values('gyglnew_gybxgl_gybxgl.do','ldmc','楼栋','mhcx','1');
commit;

--------------------------------------------------
-----xsgzgl_tybb已经执行by陈芬芬20111202
-----xsgzgl_setup未执行

--20111202--zhanghui--
--增加高级查询条件——寝室信息管理
delete from xg_search_szb where path  ='gyglnew_qsgl_qsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','寝室性别','djcx','qsxb','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','rzqk','寝室入住情况','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sffp','寝室分配情况','djcx','','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室号','mhcx','','gygl_third','2');
commit;

--寝室信息视图
create or replace view view_xg_gygl_new_qsxx as
select a.*,ptcws-yzcws wzcws,case when xydm is not null then '是' else '否' end sffp,
case when yzcws=0 then '全空' when yzcws=ptcws then '满员' else '缺额' end rzqk from (
select a.*,b.ldmc,b.ldxb,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select count(*) from xg_gygl_new_cwxxb x where a.lddm = x.lddm and a.qsh = x.qsh) qscws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='是' and a.lddm = x.lddm and a.qsh = x.qsh) blcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='否' and a.lddm = x.lddm and a.qsh = x.qsh) ptcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='否' and a.lddm = x.lddm and a.qsh = x.qsh and xh is not null) yzcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='否' and a.lddm = x.lddm and a.qsh = x.qsh and xydm is not null) yfpcws
from xg_gygl_new_qsxxb a left join xg_gygl_new_ldxxb b on a.lddm = b.lddm order by a.lddm,to_number(a.ch),a.qsh) a;

--20111205--zhanghui--
--修改学生住宿信息视图
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '是' else '否' end) sfzs,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=b.lddm) ldmc,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh;
/
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XH is '学号';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XM is '姓名';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XB is '性别';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.NJ is '年级';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYDM is '学院代码';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYMC is '学院名称';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJDM is '班级代码';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJMC is '班级名称';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDDM is '楼栋代码';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.QSH is '寝室号';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CWH is '床位号';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.SFZS is '是否住宿';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDMC is '楼栋名称';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CH is '层号';

--学生住宿信息查询--增加专业、班级查询条件
delete from xg_search_szb where path like 'gyglnew_xszsgl_xszsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ld','楼栋','djcx','lddm','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ch','层号','djcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','寝室','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','nj','年级','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xy','学院','djcx','xydm','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','zy','专业','djcx','zydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','bj','班级','djcx','bjdm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','sf','是否住宿','djcx','sfzs','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ldmc','楼栋名称','mhcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','寝室号','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xh','学号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xm','姓名','mhcx','','gygl_third','4');
commit;
-----xsgzgl_tybb已经执行by陈芬芬20111207
-----苏州工业园区职业技术学院 已经升级 by cff
-----成都纺织高等专科学校  已升级 by cff
-----浙江建设职业技术学院 已升级 by cff

--2011-11-9 gaobb 增加公寓管理人员表
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
  is '公寓管理-公寓管理人员表';
-- Add comments to the columns 
comment on column xg_gygl_new_gyglryb.xh
  is '学号';
comment on column xg_gygl_new_gyglryb.lddm
  is '楼栋代码';
comment on column xg_gygl_new_gyglryb.ch
  is '层号';
comment on column xg_gygl_new_gyglryb.qsh
  is '寝室号';
comment on column xg_gygl_new_gyglryb.rzzt
  is '任职状态';
comment on column xg_gygl_new_gyglryb.rzksrq
  is '任职开始日期';
comment on column xg_gygl_new_gyglryb.rzjsrq
  is '任职结束日期';
comment on column xg_gygl_new_gyglryb.lxdh
  is '联系电话';
comment on column xg_gygl_new_gyglryb.bz
  is '备注';

create or replace view xg_view_gygl_new_gyglryb as 
select a.lddm,a.ch,a.qsh,a.xb,a.gllx,(case when c.xh is null then '否' else '是' end) sffp,
b.ldmc,(case when a.ch='#' then '#' when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
c.xh,c.lxdh,c.bz,d.xm,f.qsdh
from ( 
select lddm,'#' ch,'#' qsh,ldxb xb,'楼长' gllx from xg_gygl_new_ldxxb
union all
select lddm,ch,'#' qsh,qsxb,'层长' gllx from xg_gygl_new_qsxxb group by lddm,ch,qsxb
union all
select lddm,ch,qsh,qsxb,'寝室长' gllx from xg_gygl_new_qsxxb
) a left join xg_gygl_new_ldxxb b on a.lddm=b.lddm
left join xg_gygl_new_gyglryb c on a.lddm=c.lddm and a.ch=c.ch and a.qsh=c.qsh
left join view_xsjbxx d on c.xh=d.xh 
left join xg_gygl_new_cwxxb e on e.xh=c.xh
left join xg_gygl_new_qsxxb f on f.lddm=e.lddm and f.qsh=e.qsh
where nvl(c.rzzt,' ')<>'离任'
order by lddm,ch,qsh;
/
comment on column xg_view_gygl_new_gyglryb.lddm is '楼栋代码';
comment on column xg_view_gygl_new_gyglryb.ch is '层号';
comment on column xg_view_gygl_new_gyglryb.qsh is '寝室号';
comment on column xg_view_gygl_new_gyglryb.xb is '管理性别';
comment on column xg_view_gygl_new_gyglryb.sffp is '是否分配管理人员';
comment on column xg_view_gygl_new_gyglryb.ldmc is '楼栋名称';
comment on column xg_view_gygl_new_gyglryb.chmc is '层号名称';
comment on column xg_view_gygl_new_gyglryb.xh is '学号';
comment on column xg_view_gygl_new_gyglryb.gllx is '管理类型';
comment on column xg_view_gygl_new_gyglryb.lxdh is '管理人员电话';
comment on column xg_view_gygl_new_gyglryb.bz is '备注';
comment on column xg_view_gygl_new_gyglryb.xm is '管理人员姓名';
comment on column xg_view_gygl_new_gyglryb.qsdh is '管理人员寝室电话';

insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N3805','寝室长维护','','1','','是','','');  
insert into gnmkdmb(gnmkdm,gnmkmc,dyym,dxq,xssx,sfqy,gnbz,sfyc) values('N380501','寝室长维护','gyglnew_gyglry_gyglry.do','1','','是','','');

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3805','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380501','1');
commit;

--2011-12-13
create or replace view xg_view_gygl_new_gyglryb as 
select a.*,(case when a.xh is null then '否' else '是' end) sffp,
b.ldmc,(case when a.ch='#' then '#' when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
c.xm
 from (
select a.lddm,'#' ch,'#' qsh,ldxb xb,'楼长' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_ldxxb a
left join 
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c 
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch='#' and a.qsh='#' and a.rzzt='在任'
) b
on a.lddm=b.lddm
union all
select a.lddm,a.ch,'#' qsh,a.qsxb xb,'层长' gllx,b.xh,b.lxdh,b.bz,b.qsdh from 
(select lddm,ch,qsxb from xg_gygl_new_qsxxb group by lddm,ch,qsxb) a 
left join 
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch=c.ch and a.qsh='#' and a.rzzt='在任'
) b
on a.lddm=b.lddm and a.ch=b.ch
union all
select a.lddm,a.ch,a.qsh,a.qsxb xb,'寝室长' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_qsxxb a
left join 
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and a.qsh=b.qsh and b.lddm=c.lddm and b.qsh=c.qsh and a.rzzt='在任'
) b
on a.lddm=b.lddm and a.ch=b.ch and a.qsh=b.qsh ) a
left join xg_gygl_new_ldxxb b on a.lddm=b.lddm 
left join view_xsjbxx c on a.xh=c.xh 
order by a.lddm,a.ch,a.qsh;
/
comment on column xg_view_gygl_new_gyglryb.lddm is '楼栋代码';
comment on column xg_view_gygl_new_gyglryb.ch is '层号';
comment on column xg_view_gygl_new_gyglryb.qsh is '寝室号';
comment on column xg_view_gygl_new_gyglryb.xb is '管理性别';
comment on column xg_view_gygl_new_gyglryb.sffp is '是否分配管理人员';
comment on column xg_view_gygl_new_gyglryb.ldmc is '楼栋名称';
comment on column xg_view_gygl_new_gyglryb.chmc is '层号名称';
comment on column xg_view_gygl_new_gyglryb.xh is '学号';
comment on column xg_view_gygl_new_gyglryb.gllx is '管理类型';
comment on column xg_view_gygl_new_gyglryb.lxdh is '管理人员电话';
comment on column xg_view_gygl_new_gyglryb.bz is '备注';
comment on column xg_view_gygl_new_gyglryb.xm is '管理人员姓名';
comment on column xg_view_gygl_new_gyglryb.qsdh is '管理人员寝室电话';

insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ld','楼栋','djcx','lddm','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ch','层号','djcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','寝室','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldxb','性别','djcx','xb','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','gygllx','类型','djcx','gllx','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','sf','是否分配','djcx','sffp','','6');

insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldmc','楼栋名称','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','寝室号','mhcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xh','学号','mhcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xm','姓名','mhcx','','','4');
commit;
------以上内容为版本V5.0.7---------------------
------以上内容为版本V5.0.7--------------------
-----xsgzgl_setup已经执行by陈恩夫20111213

--20120301--zhuanghui--
--床位信息视图修改
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,b.qsdh,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from 
(select xh,zydm from bks_xsjbxx a where not exists (select 1 from xsxxb b where a.xh = b.xh) 
union all select a.xh,a.zydm from xsxxb a 
where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null))
 x where x.xh=a.xh ) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '否' else '是' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '否' else '是' end sffpbj,
case when a.xh is null then '否' else '是' end sfrz
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/

--20120307 gaobb
--校区和园区关联
--初始化公寓管理基本设置表
delete from xg_gygl_new_jbszb;
commit;
insert into xg_gygl_new_jbszb (csdm,csmc,csz,bz) values('xqbj','校区标记','1','0：无校区；1：存在校区；');
insert into xg_gygl_new_jbszb (csdm,csmc,csz,bz) values('cwfpdx','分配对象','xydm','xydm：分配床位由学校分配给学院，学院安排入住；bjdm：分配床位由学校分配给学院，学院分配给班级，班主任安排入住');
insert into xg_gygl_new_jbszb (csdm,csmc,csz,bz) values('yqbj','园区标记','1','0：无校区；1：存在园区；');
commit;

--床位信息视图
create or replace view view_xg_gygl_new_cwxx as
select a.lddm,a.qsh,a.cwh,a.sfbl,b.qsdh,a.xydm,a.nj,a.bjdm,a.xh,a.bz,a.rzsj,a.rzczr,
b.ch,(case when to_number(b.ch)>-1 then b.ch else 'B'||abs(b.ch) end) chmc,b.qsxb,b.nj qsnj,
b.xydm qsxydm,(select distinct xymc from view_njxyzybj x where x.xydm=b.xydm) qsxymc,
c.ldmc,(select distinct zydm from
(select xh,zydm from bks_xsjbxx a where not exists (select 1 from xsxxb b where a.xh = b.xh)
union all select a.xh,a.zydm from xsxxb a
where (sfyby = '否' or sfyby is null) and (sfzx = '在校' or sfzx is null))
 x where x.xh=a.xh ) zydm,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select distinct bjmc from view_njxyzybj x where x.bjdm=a.bjdm) bjmc,
case when a.xydm is null and a.nj is null then '否' else '是' end sffpxy,
case when a.xydm is null and a.nj is null and a.bjdm is null then '否' else '是' end sffpbj,
case when a.xh is null then '否' else '是' end sfrz,
c.xqdm,(select xqmc from dm_zju_xq x where x.dm = c.xqdm) xqmc,
c.yqdm,(select yqmc from zxbz_ssyqdm x where x.yqdm = c.yqdm) yqmc
from xg_gygl_new_cwxxb a
left join xg_gygl_new_qsxxb b on a.lddm=b.lddm and a.qsh=b.qsh
left join xg_gygl_new_ldxxb c on a.lddm=c.lddm;
/
--寝室信息视图
create or replace view view_xg_gygl_new_qsxx as
select a.*,ptcws-yzcws wzcws,case when xydm is not null then '是' else '否' end sffp,
case when yzcws=0 then '全空' when yzcws=ptcws then '满员' else '缺额' end rzqk from (
select a.*,b.ldmc,b.ldxb,(case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
(select distinct xymc from view_njxyzybj x where x.xydm=a.xydm) xymc,
(select count(*) from xg_gygl_new_cwxxb x where a.lddm = x.lddm and a.qsh = x.qsh) qscws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='是' and a.lddm = x.lddm and a.qsh = x.qsh) blcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='否' and a.lddm = x.lddm and a.qsh = x.qsh) ptcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='否' and a.lddm = x.lddm and a.qsh = x.qsh and xh is not null) yzcws,
(select count(*) from xg_gygl_new_cwxxb x where sfbl='否' and a.lddm = x.lddm and a.qsh = x.qsh and xydm is not null) yfpcws,
b.xqdm,(select xqmc from dm_zju_xq x where x.dm = b.xqdm) xqmc,
b.yqdm,(select yqmc from zxbz_ssyqdm x where x.yqdm = b.yqdm) yqmc
from xg_gygl_new_qsxxb a left join xg_gygl_new_ldxxb b on a.lddm = b.lddm order by a.lddm,to_number(a.ch),a.qsh) a;
/
--学生住宿信息查询视图
create or replace view xg_view_gygl_new_xszsgl as
select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,
b.lddm,b.qsh,b.cwh,(case when b.xh is not null then '是' else '否' end) sfzs,
c.ldmc,c.xqdm,c.yqdm,
(select ch from xg_gygl_new_qsxxb x where x.lddm=b.lddm and x.qsh=b.qsh) ch
from view_xsjbxx a left join xg_gygl_new_cwxxb b on a.xh=b.xh
left join xg_gygl_new_ldxxb c on b.lddm = c.lddm;
/
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XH is '学号';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XM is '姓名';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XB is '性别';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.NJ is '年级';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYDM is '学院代码';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XYMC is '学院名称';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJDM is '班级代码';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.BJMC is '班级名称';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDDM is '楼栋代码';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.QSH is '寝室号';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CWH is '床位号';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.SFZS is '是否住宿';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.LDMC is '楼栋名称';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.CH is '层号';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.XQDM is '校区代码';
comment on column XG_VIEW_GYGL_NEW_XSZSGL.YQDM is '园区代码';
--公寓管理人员视图
create or replace view xg_view_gygl_new_gyglryb as
select a.*,(case when a.xh is null then '否' else '是' end) sffp,
b.ldmc,b.xqdm,b.yqdm,(case when a.ch='#' then '#' when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) chmc,
c.xm
 from (
select a.lddm,'#' ch,'#' qsh,ldxb xb,'楼长' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_ldxxb a
left join
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch='#' and a.qsh='#' and a.rzzt='在任'
) b
on a.lddm=b.lddm
union all
select a.lddm,a.ch,'#' qsh,a.qsxb xb,'层长' gllx,b.xh,b.lxdh,b.bz,b.qsdh from
(select lddm,ch,qsxb from xg_gygl_new_qsxxb group by lddm,ch,qsxb) a
left join
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and b.lddm=c.lddm and b.qsh=c.qsh and a.ch=c.ch and a.qsh='#' and a.rzzt='在任'
) b
on a.lddm=b.lddm and a.ch=b.ch
union all
select a.lddm,a.ch,a.qsh,a.qsxb xb,'寝室长' gllx,b.xh,b.lxdh,b.bz,b.qsdh from xg_gygl_new_qsxxb a
left join
(
select a.*,c.qsdh from xg_gygl_new_gyglryb a,xg_gygl_new_cwxxb b,xg_gygl_new_qsxxb c
where a.xh=b.xh and a.lddm=b.lddm and a.qsh=b.qsh and b.lddm=c.lddm and b.qsh=c.qsh and a.rzzt='在任'
) b
on a.lddm=b.lddm and a.ch=b.ch and a.qsh=b.qsh ) a
left join xg_gygl_new_ldxxb b on a.lddm=b.lddm
left join view_xsjbxx c on a.xh=c.xh
order by a.lddm,a.ch,a.qsh;
/
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.LDDM is '楼栋代码';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.CH is '层号';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.QSH is '寝室号';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XB is '管理性别';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.GLLX is '管理类型';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XH is '学号';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.LXDH is '管理人员电话';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.BZ is '备注';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.QSDH is '管理人员寝室电话';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.SFFP is '是否分配管理人员';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.LDMC is '楼栋名称';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.CHMC is '层号名称';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XM is '管理人员姓名';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.XQDM is '校区代码';
comment on column XG_VIEW_GYGL_NEW_GYGLRYB.YQDM is '园区代码';


--增加楼栋信息高级查询条件“校区”
--高级查询--楼栋信息管理--
delete from xg_search_szb where path = 'gyglnew_ldgl_ldgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','xqdm','校区','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','yqdm','园区','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ld','楼栋','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','lddm','楼栋代码','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_ldgl_ldgl.do','ldmc','楼栋名称','mhcx','','gygl_third','3');
commit;
--高级查询-寝室信息管理
delete from xg_search_szb where path = 'gyglnew_qsgl_qsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xqdm','校区','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','yqdm','园区','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ld','楼栋','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ch','层号','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','xb','寝室性别','djcx','qsxb','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','rzqk','寝室入住情况','djcx','','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','sffp','寝室分配情况','djcx','','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','ldmc','楼栋名称','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_qsgl_qsgl.do','qsh','寝室号','mhcx','','gygl_third','3');
commit;
--高级查询--床位信息管理
delete from xg_search_szb where path = 'gyglnew_cwgl_cwgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xqdm','校区','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','yqdm','园区','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ld','楼栋','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ch','层号','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','nj','年级','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xy','学院','djcx','xydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','zy','专业','djcx','zydm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','bj','班级','djcx','bjdm','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xb','床位性别','djcx','qsxb','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sf','是否保留床位','djcx','sfbl','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sffp','是否分配学院','djcx','sffpxy','gygl_third','11');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','sfrz','是否入住','djcx','','gygl_third','12');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','ldmc','楼栋名称','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','qsh','寝室号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xh','学号','mhcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_cwgl_cwgl.do','xm','姓名','mhcx','','gygl_third','5');
commit;
--高级查询--住宿信息管理
delete from xg_search_szb where path = 'gyglnew_zsxxgl_zsxxgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xqdm','校区','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','yqdm','园区','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ld','楼栋','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ch','层号','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','nj','年级','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xy','学院','djcx','xydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','zy','专业','djcx','zydm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','bj','班级','djcx','bjdm','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xb','床位性别','djcx','qsxb','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sf','是否保留床位','djcx','sfbl','gygl_third','10');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','sfrz','是否入住','djcx','','gygl_third','11');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','ldmc','楼栋名称','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','qsh','寝室号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xh','学号','mhcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_zsxxgl_zsxxgl.do','xm','姓名','mhcx','','gygl_third','5');
commit;
--高级查询--学生住宿信息查询
delete from xg_search_szb where path = 'gyglnew_xszsgl_xszsgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xqdm','校区','djcx','','gygl_third','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','yqdm','园区','djcx','','gygl_third','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ld','楼栋','djcx','lddm','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ch','层号','djcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','寝室','djcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','nj','年级','djcx','','gygl_third','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xy','学院','djcx','xydm','gygl_third','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','zy','专业','djcx','zydm','gygl_third','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','bj','班级','djcx','bjdm','gygl_third','8');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','sf','是否住宿','djcx','sfzs','gygl_third','9');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','ldmc','楼栋名称','mhcx','','gygl_third','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','qsh','寝室号','mhcx','','gygl_third','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xh','学号','mhcx','','gygl_third','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_xszsgl_xszsgl.do','xm','姓名','mhcx','','gygl_third','5');
commit;
--高级查询--公寓管理人员
delete from xg_search_szb where path = 'gyglnew_gyglry_gyglry.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xqdm','校区','djcx','','','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','yqdm','园区','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ld','楼栋','djcx','lddm','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ch','层号','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','寝室','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldxb','性别','djcx','xb','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','gygllx','类型','djcx','gllx','','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','sf','是否分配','djcx','sffp','','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','ldmc','楼栋名称','mhcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','qsh','寝室号','mhcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xh','学号','mhcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gyglnew_gyglry_gyglry.do','xm','姓名','mhcx','','','5');
commit;

--创建公寓管理人员信息视图
create or replace view view_xg_gygl_new_gyglryb as
select xh,
case when ch = '#' and qsh = '#' then '楼长'
when ch !='#' and qsh ='#' then '层长'  
when ch !='#' and qsh !='#' then '寝室长' end zwmc,
case when ch = '#' and qsh = '#' then b.ldmc
when ch !='#' and qsh ='#' then b.ldmc || ','|| (case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) 
when ch !='#' and qsh !='#' then b.ldmc|| ','|| (case when to_number(a.ch)>-1 then a.ch else 'B'||abs(a.ch) end) ||','|| a.qsh end bz
from xg_gygl_new_gyglryb a left join xg_gygl_new_ldxxb b on a.lddm = b.lddm where rzzt = '在任';
/

---------------------------------------------
-------------------sql已提交-----------------
---------------------------------------------

--2012-03-19 gaobb 移植卫生检查到贵州大学
insert into gnmkdmb values('N3806','卫生检查','','1','','是','','');
insert into gnmkdmb values('N380601','参数设置','gzdx_gygl_wsjc_cssz.do','1','','是','','');
insert into gnmkdmb values('N380602','空白报表形成','gzdx_gygl_wsjc_kbbb.do','1','','是','','');
insert into gnmkdmb values('N380603','卫生分录入','gzdx_gygl_wsjc_fslr.do','1','','是','','');
insert into gnmkdmb values('N380604','卫生分查询','gzdx_gygl_wsjc_fscx.do','1','','是','','');
insert into gnmkdmb values('N380605','特殊寝室维护','gzdx_gygl_wsjc_tsqs.do','1','','是','','');
insert into gnmkdmb values('N380606','卫生分统计','gzdx_gygl_wsjc_fstj.do','1','','是','','');
insert into gnmkdmb values('N380607','学生卫生分录入','gzdx_gygl_wsjc_xsfslr.do','1','','是','','');
insert into gnmkdmb values('N380608','学生卫生分查看','gzdx_gygl_wsjc_xsfsck.do','1','','是','','');


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
--卫生分录入增加高级查询条件
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','xqdm','校区','djcx','','','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','yqdm','园区','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','ld','楼栋','djcx','lddm','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','ch','层号','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','qsh','寝室','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','xy','学院','djcx','xydm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','sf','是否打分','djcx','sfdf','','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','jcsj','检查时间','dgsjcx','','','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','ldmc','楼栋名称','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fslr.do','qsh','寝室号','mhcx','','','2');
--卫生分查询增加高级查询条件
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','xqdm','校区','djcx','','','0');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','yqdm','园区','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','ld','楼栋','djcx','lddm','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','ch','层号','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','qsh','寝室','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','xy','学院','djcx','xydm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','sf','是否打分','djcx','sfdf','','6');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','jcsj','检查时间','dgsjcx','','','7');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','ldmc','楼栋名称','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('gzdx_gygl_wsjc_fscx.do','qsh','寝室号','mhcx','','','2');

commit;

--2012-03-21 gaobb 增加文明寝室评比功能模块     贵州大学个性
insert into gnmkdmb values('N3807','文明寝室','','1','','是','','');
insert into gnmkdmb values('N380701','文明寝室个数维护','gzdx_gygl_wmqs_qsgswh.do','1','','是','','');
insert into gnmkdmb values('N380702','文明寝室申请','gzdx_gygl_wmqs_qssq.do','1','','是','','');
insert into gnmkdmb values('N380703','文明寝室审核','gzdx_gygl_wmqs_qssh.do','1','','是','','');
insert into gnmkdmb values('N380704','文明寝室管理','gzdx_gygl_wmqs_qsgl.do','1','','是','','');

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
  is '公寓管理-文明寝室个数表(贵州大学)';
-- Add comments to the columns 
comment on column xg_gygl_new_gzdx_wmqsgsb.nd
  is '年度';
comment on column xg_gygl_new_gzdx_wmqsgsb.xydm
  is '学院代码';
comment on column xg_gygl_new_gzdx_wmqsgsb.wmqsgs
  is '文明寝室个数';
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
  fdyshzt varchar2(10) default '未审核',
  fdyshr  varchar2(20),
  fdyshsj varchar2(10),
  fdyshbz varchar2(200),
  xxshzt  varchar2(10) default '未审核',
  xxshr   varchar2(20),
  xxshsj  varchar2(10),
  xxshbz  varchar2(200)
)
;
-- Add comments to the table 
comment on table xg_gygl_new_gzdx_wmqssqb
  is '公寓管理-文明寝室申请表(贵州大学)';
-- Add comments to the columns 
comment on column xg_gygl_new_gzdx_wmqssqb.nd
  is '年度';
comment on column xg_gygl_new_gzdx_wmqssqb.lddm
  is '楼栋代码';
comment on column xg_gygl_new_gzdx_wmqssqb.qsh
  is '寝室号';
comment on column xg_gygl_new_gzdx_wmqssqb.qsrs
  is '寝室人数';
comment on column xg_gygl_new_gzdx_wmqssqb.wspjf
  is '卫生平均分';
comment on column xg_gygl_new_gzdx_wmqssqb.sqsm
  is '申请说明';
comment on column xg_gygl_new_gzdx_wmqssqb.sqr
  is '申请人';
comment on column xg_gygl_new_gzdx_wmqssqb.sqsj
  is '申请时间';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshzt
  is '辅导员审核状态';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshr
  is '辅导员审核人';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshsj
  is '辅导员审核时间';
comment on column xg_gygl_new_gzdx_wmqssqb.fdyshbz
  is '辅导员审核备注';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshzt
  is '学校审核状态';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshr
  is '学校审核人';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshsj
  is '学校审核时间';
comment on column xg_gygl_new_gzdx_wmqssqb.xxshbz
  is '学校审核备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table xg_gygl_new_gzdx_wmqssqb
  add constraint gzdx_wmqsshb_pri primary key (ND, LDDM, QSH);

create or replace view view_xg_gygl_new_gzdx_wmqssq as 
select a.*,c.xydm,c.xymc,c.bjdm,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=a.lddm) ldmc
from xg_gygl_new_gzdx_wmqssqb a 
left join xg_gygl_new_gyglryb b on a.lddm=b.lddm and a.qsh=b.qsh and b.rzzt='在任'
left join view_xsjbxx c on b.xh=c.xh
/

alter table XG_GYGL_NEW_GZDX_WMQSSQB drop column QSRS;
create or replace view view_xg_gygl_new_gzdx_wmqssq as
select a.*,b.ch,d.xqdm,d.yqdm,c.xydm,c.xymc,c.bjdm,
(select ldmc from xg_gygl_new_ldxxb x where x.lddm=a.lddm) ldmc,
(select count(1) from xg_gygl_new_cwxxb x where x.lddm=a.lddm and x.qsh=a.qsh) qsrs
from xg_gygl_new_gzdx_wmqssqb a
left join xg_gygl_new_gyglryb b on a.lddm=b.lddm and a.qsh=b.qsh and b.rzzt='在任'
left join view_xg_gygl_new_qsxx d on a.lddm = d.lddm and a.qsh =d.qsh
left join view_xsjbxx c on b.xh=c.xh
/
comment on column view_xg_gygl_new_gzdx_wmqssq.nd is '年度';
comment on column view_xg_gygl_new_gzdx_wmqssq.lddm is '楼栋';
comment on column view_xg_gygl_new_gzdx_wmqssq.qsh is '寝室号';
comment on column view_xg_gygl_new_gzdx_wmqssq.wspjf is '卫生平均分';
comment on column view_xg_gygl_new_gzdx_wmqssq.sqsm is '申请说明';
comment on column view_xg_gygl_new_gzdx_wmqssq.sqr is '申请人';
comment on column view_xg_gygl_new_gzdx_wmqssq.sqsj is '申请时间';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshzt is '辅导员审核状态';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshr is '辅导员审核人';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshsj is '辅导员审核时间';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshzt is '学校审核状态';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshr is '学校审核人';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshsj is '学校审核时间';
comment on column view_xg_gygl_new_gzdx_wmqssq.fdyshbz is '辅导员审核备注';
comment on column view_xg_gygl_new_gzdx_wmqssq.xxshbz is '学校审核备注';
comment on column view_xg_gygl_new_gzdx_wmqssq.ch is '层号';
comment on column view_xg_gygl_new_gzdx_wmqssq.xqdm is '校区代码';
comment on column view_xg_gygl_new_gzdx_wmqssq.yqdm is '园区代码';
comment on column view_xg_gygl_new_gzdx_wmqssq.xydm is '学院代码';
comment on column view_xg_gygl_new_gzdx_wmqssq.xymc is '学院名称';
comment on column view_xg_gygl_new_gzdx_wmqssq.bjdm is '班级代码';
comment on column view_xg_gygl_new_gzdx_wmqssq.ldmc is '楼栋代码';
comment on column view_xg_gygl_new_gzdx_wmqssq.qsrs is '寝室人数';
-- Add/modify columns 
alter table GYGL_WSJC_WSFWHB add jcry varchar2(20);
-- Add comments to the columns 
comment on column GYGL_WSJC_WSFWHB.jcry
  is '检查人员';
  
------------------------------------------------------------
--20120514--zhanghui--公寓纪律------------------------------
------------------------------------------------------------  
--公寓记录类别大类表
create table xg_gygl_new_gyjllbdlb(
       gyjllbdldm varchar2(20),
       gyjllbdlmc varchar2(200)
);
alter table xg_gygl_new_gyjllbdlb add constraint PK_gyjllbdlb primary key (gyjllbdldm);
comment on table xg_gygl_new_gyjllbdlb is '公寓记录类别大类表';
comment on column xg_gygl_new_gyjllbdlb.gyjllbdldm is '公寓纪律类别大类代码';
comment on column xg_gygl_new_gyjllbdlb.gyjllbdlmc is '公寓纪律类别大类名称';

--公寓记录类别代码表
create table xg_gygl_new_gyjllbdmb(
       gyjllbdm varchar2(20),
       gyjllbmc varchar2(200),
       gyjllbdldm  varchar2(20)
);
alter table xg_gygl_new_gyjllbdmb add constraint PK_gyjllbdmb primary key (gyjllbdm);
comment on table xg_gygl_new_gyjllbdmb is '公寓记录类别代码表';
comment on column xg_gygl_new_gyjllbdmb.gyjllbdm is '公寓纪律类别代码';
comment on column xg_gygl_new_gyjllbdmb.gyjllbmc is '公寓纪律类别名称';
comment on column xg_gygl_new_gyjllbdmb.gyjllbdldm is '公寓纪律类别大类代码';

--公寓纪律表
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
comment on table XG_GYGL_NEW_GYJLB is '公寓纪律表';
comment on column XG_GYGL_NEW_GYJLB.XH is '学号';
comment on column XG_GYGL_NEW_GYJLB.LDDM is '楼栋代码';
comment on column XG_GYGL_NEW_GYJLB.LDMC is '楼栋名称';
comment on column XG_GYGL_NEW_GYJLB.QSH is '寝室号';
comment on column XG_GYGL_NEW_GYJLB.CWH is '床位号';
comment on column XG_GYGL_NEW_GYJLB.NJ is '年级';
comment on column XG_GYGL_NEW_GYJLB.XYDM is '学院代码';
comment on column XG_GYGL_NEW_GYJLB.ZYDM is '专业代码';
comment on column XG_GYGL_NEW_GYJLB.BJDM is '班级代码';
comment on column XG_GYGL_NEW_GYJLB.XYMC is '学院名称';
comment on column XG_GYGL_NEW_GYJLB.ZYMC is '专业名称';
comment on column XG_GYGL_NEW_GYJLB.BJMC is '班级名称';
comment on column XG_GYGL_NEW_GYJLB.GYJLLBDLDM is '公寓纪律类别大类代码';
comment on column XG_GYGL_NEW_GYJLB.GYJLLBDM is '公寓纪律类别代码';
comment on column XG_GYGL_NEW_GYJLB.WJSJ is '违纪时间';
comment on column XG_GYGL_NEW_GYJLB.BZ is '备注';
comment on column XG_GYGL_NEW_GYJLB.CZR is '操作人';
comment on column XG_GYGL_NEW_GYJLB.CZSJ is '操作时间';


--增加权限
--delete from gnmkdmb where gnmkdm like 'N3808%';
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N3808','公寓纪律','','1','是');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N380801','公寓纪律代码维护','gyglnew_gyjldmgl_gyjldmgl.do','1','是');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N380802','公寓纪律信息维护','gyglnew_gyjlgl_gyjlgl.do','1','是');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N380803','公寓纪律信息查询','gyglnew_gyjlgl_gyjlcx.do','1','是');

--delete from yhqxb where yhm ='zf01' and gnmkdm like 'N3808%';
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N3808','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380801','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380802','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N380803','1');





--寝室信息导出设置
delete from dcb where zdssb = 'view_xg_gygl_new_qsxx';
commit;
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('ldmc','view_xg_gygl_new_qsxx','public','楼栋名称');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('chmc','view_xg_gygl_new_qsxx','public','层号');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qsh','view_xg_gygl_new_qsxx','public','寝室号');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qsxb','view_xg_gygl_new_qsxx','public','寝室性别');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qsdh','view_xg_gygl_new_qsxx','public','寝室电话');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('nj','view_xg_gygl_new_qsxx','public','所属年级');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('xymc','view_xg_gygl_new_qsxx','public','所属学院名称');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('qscws','view_xg_gygl_new_qsxx','public','寝室床位数');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('blcws','view_xg_gygl_new_qsxx','public','保留床位数');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('ptcws','view_xg_gygl_new_qsxx','public','普通床位数');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('yfpcws','view_xg_gygl_new_qsxx','public','已分配床位数');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('yzcws','view_xg_gygl_new_qsxx','public','已住床位数');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('wzcws','view_xg_gygl_new_qsxx','public','未住床位数');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('sffp','view_xg_gygl_new_qsxx','public','是否分配');
insert into dcb(zdmc,zdssb,xxdm,zdsm) values('rzqk','view_xg_gygl_new_qsxx','public','入住情况');
commit;