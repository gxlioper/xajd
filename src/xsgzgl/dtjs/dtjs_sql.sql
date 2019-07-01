------------------------------------------
--2011-12-23--zhanghui
------------------------------------------
--增加功能模块代码
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N0224','党团信息管理','','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N022401','学生党团信息管理','dtjs_dtxxgl_dtxxgl.do','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N022402','学生党团信息查询','dtjs_dtxxgl_dtxxcx.do','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N0225','其他信息','','1','');
insert into gnmkdmb (gnmkdm,gnmkmc,dyym,dxq,sfqy) values('N022501','团员缴费管理','dtjs_general_tyjf.do','1','');
commit;

insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N0224','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N022401','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N022402','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N0225','1');
insert into yhqxb (yhm,gnmkdm,dxq) values('zf01','N022501','1');
commit;

--增加党团建设基本设置表
-- Table XG_DTJS_JBSZB
-- Create table
create table XG_DTJS_JBSZB
(
  JDDM     VARCHAR2(30) not null,
  JDMC     VARCHAR2(100),
  SFSZJSSJ VARCHAR2(2) default '否',
  SFYRSHL  VARCHAR2(2) default '否',
  SFSZTJ   VARCHAR2(2) default '否',
  DYZD     VARCHAR2(30),
  DYZ      VARCHAR2(30),
  BZ       VARCHAR2(500),
  JDSX     VARCHAR2(30)
);
-- Add comments to the table 
comment on table XG_DTJS_JBSZB
  is '党团建设基本设置表';
-- Add comments to the columns 
comment on column XG_DTJS_JBSZB.JDDM
  is '阶段代码';
comment on column XG_DTJS_JBSZB.JDMC
  is '阶段名称';
comment on column XG_DTJS_JBSZB.SFSZJSSJ
  is '是否自动转入下一阶段';
comment on column XG_DTJS_JBSZB.SFYRSHL
  is '是否引入审核流';
comment on column XG_DTJS_JBSZB.SFSZTJ
  is '是否设置条件';
comment on column XG_DTJS_JBSZB.DYZD
  is '对应字段';
comment on column XG_DTJS_JBSZB.DYZ
  is '对应值';
comment on column XG_DTJS_JBSZB.BZ
  is '备注';
comment on column XG_DTJS_JBSZB.JDSX
  is '阶段顺序';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_DTJS_JBSZB
  add constraint PK_XG_DTJS_JBSZB_JDDM primary key (JDDM);

--增加学生党团信息记录表
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
  is '学生党团信息记录表';
-- Add comments to the columns 
comment on column XG_DTJS_XSDTXXJLB.XH
  is '学号';
comment on column XG_DTJS_XSDTXXJLB.JDDM
  is '阶段代码';
comment on column XG_DTJS_XSDTXXJLB.KSSJ
  is '开始时间';
comment on column XG_DTJS_XSDTXXJLB.JSSJ
  is '结束时间';
comment on column XG_DTJS_XSDTXXJLB.DQJDBJ
  is '当前阶段标记';
-- Create/Recreate primary, unique and foreign key constraints 
alter table XG_DTJS_XSDTXXJLB
  add constraint PK_XG_DTJS_XSDTXXJLB_XH primary key (XH, JDDM);

------------------------------------------
--2011-12-26--zhanghui
------------------------------------------
--增加高级查询条件--
delete from xg_search_szb where path = 'dtjs_dtxxgl_dtxxgl.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','jddm','阶段代码','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','xy','学院','djcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','zy','专业','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','bj','班级','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','zm','政治面貌','djcx','zzmm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','xh','学号','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxgl.do','xm','姓名','mhcx','','','2');
commit;

delete from xg_search_szb where path = 'dtjs_dtxxgl_dtxxcx.do';
commit;
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','jddm','阶段代码','djcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','xy','学院','djcx','','','2');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','zy','专业','djcx','','','3');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','bj','班级','djcx','','','4');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','zm','政治面貌','djcx','zzmm','','5');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','xh','学号','mhcx','','','1');
insert into xg_search_szb (path, tj, mc, lx, zd, ssmk, xssx) values ('dtjs_dtxxgl_dtxxcx.do','xm','姓名','mhcx','','','2');
commit;

--2011-12-26--gbb--
--党团信息导入临时表
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
  is '学生党团信息记录表导入临时表';
-- Add comments to the columns 
comment on column xg_dtjs_impxsdtxxjlb.xh
  is '学号';
comment on column xg_dtjs_impxsdtxxjlb.jddm
  is '阶段代码';
comment on column xg_dtjs_impxsdtxxjlb.kssj
  is '开始时间';
comment on column xg_dtjs_impxsdtxxjlb.jssj
  is '结束时间';
comment on column xg_dtjs_impxsdtxxjlb.dqjdbj
  is '当前阶段标记';
comment on column xg_dtjs_impxsdtxxjlb.mark
  is '标记';
comment on column xg_dtjs_impxsdtxxjlb.bz
  is '备注';
  
create table xg_dtjs_tyjfb(
xn     varchar2(20),
xh     varchar2(20),
yjtf   varchar2(10),
sjtf   varchar2(10),
primary key(xn,xh)
);
comment on table xg_dtjs_tyjfb is '学工_党团建设_团员缴费表';
comment on column xg_dtjs_tyjfb.xn is '学年';
comment on column xg_dtjs_tyjfb.xh is '学号';
comment on column xg_dtjs_tyjfb.yjtf is '应缴团费';
comment on column xg_dtjs_tyjfb.sjtf is '实缴团费';