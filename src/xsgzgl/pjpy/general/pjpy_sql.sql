insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N35', '评奖评优', '', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N3501', '基本设置', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350101', '评奖综合设置', 'pjpy_zhsz.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N3502', '综合素质测评', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350201', '综测分维护', 'zhcp_sjcl_sjdr.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350202', '综测分查询', 'zhcp_zczf_zccx.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N3503', '评奖流程', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350301', '我的评奖', 'pjpy_mypj.do', '1', '');

commit;

----------------------The Last----------------------------------

-------------------功能模块-----------------------------
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N3511', '基本设置(伪)', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351101', '基本设置', 'pjpy_general_index.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351102', '我的评奖', 'pjpy_general_wdpj.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351103', '代码维护', 'xtwhOther.do?method=xtDmwhNew&ssmk=pjpy', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351104', '评奖人员设置', 'general_pjpy.do?method=pjszPjry', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351105', '班级大类设置', 'general_pjpy.do?method=pjszBjdl', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351106', '综合分维护', 'general_pjpy.do?method=zhcpZcxx', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351107', '综合分结果', 'general_pjpy.do?method=zhcpResult', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N351108', '项目申请', 'general_pjpy.do?method=wdpjXssq', '1', '');

commit;
-------------------权限-----------------------------
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N3511', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351101', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351102', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351103', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351104', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351105', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351106', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351107', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N351108', '1');

commit;

-------------------最终权限-----------------------------
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N44', '评奖评优', '', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4401', '管理员用户', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440101', '代码维护', 'xtwhOther.do?method=xtDmwhNew\&ssmk=pjpy', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440102', '基本设置', 'pjpy_general_index.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4402', '教师用户', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440201', '班级大类设置', 'general_pjpy.do?method=pjszBjdl', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440202', '综合分维护', 'general_pjpy.do?method=zhcpZcxx', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440203', '综合分结果', 'general_pjpy.do?method=zhcpResult', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440204', '我的评奖', 'pjpy_general_wdpj.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4403', '学生用户', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440301', '项目申请', 'general_pjpy.do?method=wdpjXssq', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N440302', '我的评奖', 'pjpy_general_wdpj.do', '1', '');

commit;

insert into yhqxb (yhm, gnmkdm, dxq) select 'zf01',gnmkdm,'1' from gnmkdmb where gnmkdm like 'N44%';
commit;

-------------------代码维护--------------------------------
delete from dmwhglb where ssmk = 'pjpy';
insert into dmwhglb(ssmk,whdmb,whdmbmc,whzd,xxdm,pkkey) values('pjpy','xg_pjpy_xmxzb','评奖项目性质','XZDM,XZMC','all','XZDM');
insert into dmwhglb(ssmk,whdmb,whdmbmc,whzd,xxdm,pkkey) values('pjpy','xg_pjpy_bjdldmb','班级大类','BJDLDM,BJDLMC','all','BJDLDM');

commit;

-------------------综测项目初始化--------------------------------
delete from xg_pjpy_zcxmb where xn='2010-2011' and xq='no' and nd='no';
insert into xg_pjpy_zcxmb(xn,xq,nd,xmdm,xmmc,xmjb,sjdm,mrxm,jjf,ddwh)values('2010-2011','no','no','zd1','综测总分','1','','yes','+','no');
insert into xg_pjpy_zcxmb(xn,xq,nd,xmdm,xmmc,xmjb,sjdm,mrxm,jjf,ddwh) values('2010-2011','no','no','zd2','德育分','2','zd1','no','+','yes');
insert into xg_pjpy_zcxmb(xn,xq,nd,xmdm,xmmc,xmjb,sjdm,mrxm,jjf,ddwh) values('2010-2011','no','no','zd3','智育分','2','zd1','yes','+','yes');
insert into xg_pjpy_zcxmb(xn,xq,nd,xmdm,xmmc,xmjb,sjdm,mrxm,jjf,ddwh) values('2010-2011','no','no','zd4','体育分','2','zd1','no','+','yes');

commit;

-------------------表-----------------------------
create table xg_pjpy_pjlcb(
lcid   varchar2(40) default sys_guid() not null,
lcdm   varchar2(20),
lcmc   varchar2(50),
lcdj   varchar2(10),
sftj   varchar2(10) default 'no',
method varchar2(50),
primary key(lcid)
);
comment on table xg_pjpy_pjlcb is '学工_评奖评优_评奖流程表';
comment on column xg_pjpy_pjlcb.lcid is '流程ID';
comment on column xg_pjpy_pjlcb.lcdm is '流程代码';
comment on column xg_pjpy_pjlcb.lcmc is '流程名称';
comment on column xg_pjpy_pjlcb.lcdj is '流程等级';
comment on column xg_pjpy_pjlcb.method is '方法';
comment on column xg_pjpy_pjlcb.sftj is '是否提交';

create table xg_pjpy_pjlcdjb(
lcdj  varchar2(10),
sftj  varchar2(10) default 'no',
primary key(lcdj)
);
comment on table xg_pjpy_pjlcdjb is '学工_评奖评优_评奖流程等级表';
comment on column xg_pjpy_pjlcdjb.lcdj is '流程等级';
comment on column xg_pjpy_pjlcdjb.sftj is '是否提交';

create table xg_pjpy_pjrykb(
xh     varchar2(20),
bjdm   varchar2(20),
bjmc   varchar2(100),
sfcp   varchar2(10),
cpz    varchar2(40),
primary key(xh)
);
comment on table xg_pjpy_pjrykb is '学工_评奖评优_评奖人员库表';
comment on column xg_pjpy_pjrykb.xh is '学号';
comment on column xg_pjpy_pjrykb.bjdm is '班级代码';
comment on column xg_pjpy_pjrykb.bjmc is '班级名称';
comment on column xg_pjpy_pjrykb.sfcp is '是否参评';
comment on column xg_pjpy_pjrykb.cpz is '参评组';

create table xg_pjpy_cpzb(
cpzdm  varchar2(40) default sys_guid() not null,
cpzmc  varchar2(100),
bjdm   varchar2(20),
primary key(cpzdm)
);
comment on table xg_pjpy_cpzb is '学工_评奖评优_参评组表';
comment on column xg_pjpy_cpzb.cpzdm is '参评组代码';
comment on column xg_pjpy_cpzb.cpzmc is '参评组名称';
comment on column xg_pjpy_cpzb.bjdm is '班级代码';

create table xg_pjpy_zckzzdb(
xn            varchar2(20),
xq            varchar2(20),
nd            varchar2(20),
xmdm          varchar2(10),
kzzd          VARCHAR2(10),
xsmc          VARCHAR2(50),
zdlx          VARCHAR2(10),
checked  	  VARCHAR2(100),
source_table  varchar2(50),
select_dm     varchar2(50),
select_mc     varchar2(50),
xssx          varchar2(10),
sfxs          varchar2(10) default '0',
primary key(xn,xq,nd,xmdm,kzzd)
);
comment on table xg_pjpy_zckzzdb is '学工_评奖评优_综测扩展字段表';
comment on column xg_pjpy_zckzzdb.xn is '学年';
comment on column xg_pjpy_zckzzdb.xq is '学期';
comment on column xg_pjpy_zckzzdb.nd is '年度';
comment on column xg_pjpy_zckzzdb.xmdm is '项目代码';
comment on column xg_pjpy_zckzzdb.kzzd is '扩展字段';
comment on column xg_pjpy_zckzzdb.xsmc is '显示名称';
comment on column xg_pjpy_zckzzdb.zdlx is '字段类型';
comment on column xg_pjpy_zckzzdb.checked is '验证';
comment on column xg_pjpy_zckzzdb.source_table is '数据源';
comment on column xg_pjpy_zckzzdb.select_dm is '代码';
comment on column xg_pjpy_zckzzdb.select_mc is '名称';
comment on column xg_pjpy_zckzzdb.xssx is '显示顺序';
comment on column xg_pjpy_zckzzdb.sfxs is '是否显示';

create table xg_pjpy_pjlsxxb(
xn            varchar2(10) default 'no',
xq            varchar2(5) default 'no',
xmlx          varchar2(10) default '01',
xmmc          varchar2(100),
xh            varchar2(20),
xmje          VARCHAR2(10),
hdsj          VARCHAR2(20),
bz            VARCHAR2(1000),
primary key(xn,xq,xmlx,xmmc,xh)
);
comment on table xg_pjpy_pjlsxxb is '学工_评奖评优_评奖结果历史表';
comment on column xg_pjpy_pjlsxxb.xn is '评奖学年';
comment on column xg_pjpy_pjlsxxb.xq is '评奖学期(若无学期请维护"no")';
comment on column xg_pjpy_pjlsxxb.xmlx is '项目类型(01:奖学金,02:荣誉称号)';
comment on column xg_pjpy_pjlsxxb.xmmc is '项目名称';
comment on column xg_pjpy_pjlsxxb.xh is '学号';
comment on column xg_pjpy_pjlsxxb.xmje is '项目金额';
comment on column xg_pjpy_pjlsxxb.hdsj is '获得时间(格式：yyyymmdd)';
comment on column xg_pjpy_pjlsxxb.bz is '备注';


create table xg_pjpy_bjdldmb(
bjdldm  varchar2(40) default sys_guid() not null,
bjdlmc  varchar2(100),
primary key(bjdldm)
);
comment on table xg_pjpy_bjdldmb is '学工_评奖评优_班级大类代码表';
comment on column xg_pjpy_bjdldmb.bjdldm is '班级大类代码';
comment on column xg_pjpy_bjdldmb.bjdlmc is '班级大类名称';

create table xg_pjpy_bjdlb(
bjdldm  varchar2(40) default sys_guid() not null,
bjdlmc  varchar2(100),
bjdm   varchar2(20),
primary key(bjdldm)
);
comment on table xg_pjpy_bjdlb is '学工_评奖评优_班级大类表';
comment on column xg_pjpy_bjdlb.bjdldm is '班级大类代码';
comment on column xg_pjpy_bjdlb.bjdlmc is '班级大类名称';
comment on column xg_pjpy_bjdlb.bjdm is '班级代码';

create table xg_pjpy_pjtsrqb(
tsrqdm      varchar2(40) default sys_guid() not null,
tsrqmc      varchar2(100),
condition   varchar2(4000),
xssx        varchar2(5),
primary key(tsrqdm)
);
comment on table xg_pjpy_pjtsrqb is '学工_评奖评优_特殊人群表';
comment on column xg_pjpy_pjtsrqb.tsrqdm is '特殊人群代码';
comment on column xg_pjpy_pjtsrqb.tsrqmc is '特殊人群名称';
comment on column xg_pjpy_pjtsrqb.condition is '条件';
comment on column xg_pjpy_pjtsrqb.xssx is '显示顺序';

alter table XG_PJPY_PJXMWHB add rskz VARCHAR2(50);
alter table XG_PJPY_PJXMWHB add jdkz VARCHAR2(50);
alter table XG_PJPY_PJXMWHB add xmsy VARCHAR2(50);
alter table XG_PJPY_PJXMWHB modify XMDM VARCHAR2(40) default sys_guid();
alter table XG_PJPY_PJXMWHB modify KZFW VARCHAR2(40);
alter table XG_PJPY_PJXMSQB modify XMDM VARCHAR2(40);
alter table XG_PJPY_PJXMSHB modify XMDM VARCHAR2(40);

-----------------表结构修改 begin---------------------------------
comment on column XG_PJPY_PJXMWHB.rskz is '人数控制';
comment on column XG_PJPY_PJXMWHB.jdkz is '兼得控制';
comment on column XG_PJPY_PJXMWHB.xmsy is '项目顺延';

alter table XG_PJPY_XTSZB add ZYPM VARCHAR2(10);
comment on column XG_PJPY_XTSZB.ZYPM is '智育排名';

alter table XG_PJPY_PJXMSQB add sqjg VARCHAR2(50);
alter table XG_PJPY_PJXMSQB add over VARCHAR2(5) default 'no';
comment on column XG_PJPY_PJXMSQB.sqjg is '申请结果';
comment on column XG_PJPY_PJXMSQB.over is '结束标志';

alter table XG_PJPY_TJSZB modify XMDM VARCHAR2(40);
alter table XG_PJPY_RSSZB modify XMDM VARCHAR2(40);
alter table XG_PJPY_JDSZB modify XMDM VARCHAR2(40);
alter table XG_PJPY_JDSZB modify FJDDM VARCHAR2(40);
alter table xg_pjpy_sjszb modify xmdm VARCHAR2(40);
alter table XG_PJPY_ZCXMB modify XN default 'no';
alter table XG_PJPY_ZCXMB modify XQ default 'no';
alter table XG_PJPY_ZCXMB modify ND default 'no';
alter table XG_PJPY_ZCBLDMB modify XN default 'no';
alter table XG_PJPY_ZCBLDMB modify XQ default 'no';
alter table XG_PJPY_ZCBLDMB modify ND default 'no';
alter table XG_PJPY_ZCBLB modify XN default 'no';
alter table XG_PJPY_ZCBLB modify XQ default 'no';
alter table XG_PJPY_ZCBLB modify ND default 'no';
alter table XG_PJPY_ZHCPB modify XN default 'no';
alter table XG_PJPY_ZHCPB modify XQ default 'no';
alter table XG_PJPY_ZHCPB modify ND default 'no';
alter table XG_PJPY_PJXMSQB modify PJXN default 'no';
alter table XG_PJPY_PJXMSQB modify PJXQ default 'no';
alter table XG_PJPY_PJXMSQB modify PJND default 'no';
alter table XG_PJPY_PJXMSHB modify PJXN default 'no';
alter table XG_PJPY_PJXMSHB modify PJXQ default 'no';
alter table XG_PJPY_PJXMSHB modify PJND default 'no';
alter table XG_PJPY_PJXMWHB modify SFQY default 'yes';

alter table XG_PJPY_PJXMWHB add TSRQ VARCHAR2(50);
comment on column XG_PJPY_PJXMWHB.TSRQ is '特殊人群';

alter table XG_PJPY_SJSZB add bz VARCHAR2(500);
comment on column XG_PJPY_SJSZB.bz is '备注';

alter table XG_PJPY_XTSZB modify PJXN default 'no';
alter table XG_PJPY_XTSZB modify PJXQ default 'no';
alter table XG_PJPY_XTSZB modify PJND default 'no';

alter table XG_PJPY_ZCXMB add jjf VARCHAR2(10) default '+';
comment on column XG_PJPY_ZCXMB.jjf is '加减分';
alter table XG_PJPY_ZCXMB add mrxm VARCHAR2(10) default 'yes';
comment on column XG_PJPY_ZCXMB.mrxm is '默认项目';
alter table XG_PJPY_ZCXMB add lrly VARCHAR2(10) default 'no';
comment on column XG_PJPY_ZCXMB.lrly is '录入理由';

alter table XG_PJPY_XTSZB add pjzq VARCHAR2(10);
alter table XG_PJPY_XTSZB add zcpm VARCHAR2(10);
alter table XG_PJPY_XTSZB add cpz VARCHAR2(10);

comment on table XG_PJPY_XTSZB is '学工_评奖评优_系统设置表';
comment on column XG_PJPY_XTSZB.pjzq is '评奖周期';
comment on column XG_PJPY_XTSZB.zcpm is '综测排名';
comment on column XG_PJPY_XTSZB.cpz is '参评组';

alter table xg_pjpy_pjrykb add xm VARCHAR2(50);
comment on column xg_pjpy_pjrykb.xm is '姓名';

alter table XG_PJPY_PJXMSHB modify SHZT default 'wsh';
alter table XG_PJPY_PJTJKB modify TJMS default 'no';

alter table XG_PJPY_PJLCB add picname VARCHAR2(100);
comment on column XG_PJPY_PJLCB.picname is '图片名';

alter table XG_PJPY_ZCXMB modify ddwh VARCHAR2(10) default 'no';
comment on column XG_PJPY_ZCXMB.ddwh is '单独维护';

-- Add/modify columns 
alter table XG_PJPY_ZHCPB add DYFNJXYPM VARCHAR2(10);
alter table XG_PJPY_ZHCPB add DYFNJZYPM VARCHAR2(10);
alter table XG_PJPY_ZHCPB add DYFBJPM VARCHAR2(10);
-- Add comments to the columns 
comment on column XG_PJPY_ZHCPB.DYFNJXYPM
  is '德育分学院排名';
comment on column XG_PJPY_ZHCPB.DYFNJZYPM
  is '德育分专业排名';
comment on column XG_PJPY_ZHCPB.DYFBJPM
  is '德育分班级排名';

-- Add/modify columns 
alter table XG_PJPY_ZCLSB add yhm VARCHAR2(10) not null;

-----------------表结构修改 end---------------------------------

-------------评奖申请备份表------------------------
create table XG_PJPY_PJXMSQB_BACKUP
(
  XMDM  VARCHAR2(40),
  PJXN  VARCHAR2(10),
  PJXQ  VARCHAR2(5),
  PJND  VARCHAR2(10),
  XH    VARCHAR2(20),
  SQSJ  VARCHAR2(30),
  SQLY  VARCHAR2(1000),
  TJR   VARCHAR2(50),
  KZZD1 VARCHAR2(20),
  KZZD2 VARCHAR2(20),
  KZZD3 VARCHAR2(20),
  KZZD4 VARCHAR2(800),
  KZZD5 VARCHAR2(800),
  KZZD6 VARCHAR2(800),
  KZZD7 VARCHAR2(800),
  SQJG  VARCHAR2(50),
  OVER  VARCHAR2(5),
  BFSJ	VARCHAR2(20)
);

-------------评奖审核备份表------------------------
create table XG_PJPY_PJXMSHB_BACKUP
(
  XMDM   VARCHAR2(40),
  PJXN   VARCHAR2(10),
  PJXQ   VARCHAR2(5),
  PJND   VARCHAR2(10),
  XH     VARCHAR2(20),
  XTGWID VARCHAR2(32),
  SHZT   VARCHAR2(20),
  SHSJ   VARCHAR2(20),
  SHYJ   VARCHAR2(1000),
  SFTJ   VARCHAR2(10),
  SHR    VARCHAR2(20),
  BFSJ	VARCHAR2(20)
);

-----------------视图-------------------------
-----------------评奖历史信息-------------------------
create or replace view xg_view_pjpy_pjlsxx as
select a.xn || a.xq || a.xh||a.xmmc pk,
       a.xn,
       a.xq,
       (select c.xqmc from xqdzb c where a.xq = c.xqdm) xqmc,
       case
         when a.xq = 'no' then
          a.xn
         else
          a.xn || ' ' || (select c.xqmc from xqdzb c where a.xq = c.xqdm)
       end pjsj,
       a.xmlx,
       decode(a.xmlx, '01', '奖学金', '02', '荣誉称号', '其他') xmlxmc,
       a.xmmc,
       a.xh,
       b.xm,
       b.nj,
       b.xydm,
       b.xymc,
       b.zydm,
       b.zymc,
       b.bjdm,
       b.bjmc,
       a.xmje,
       a.hdsj,
       a.bz
  from xg_pjpy_pjlsxxb a
  left join view_xsbfxx b
    on a.xh = b.xh;
comment on column xg_view_pjpy_pjlsxx.pk is '';
comment on column xg_view_pjpy_pjlsxx.xn is '学年';
comment on column xg_view_pjpy_pjlsxx.xq is '学期代码';
comment on column xg_view_pjpy_pjlsxx.xqmc is '学期';
comment on column xg_view_pjpy_pjlsxx.xh is '学号';
comment on column xg_view_pjpy_pjlsxx.pjsj is '评奖时间';
comment on column xg_view_pjpy_pjlsxx.xm is '姓名';
comment on column xg_view_pjpy_pjlsxx.nj is '年级';
comment on column xg_view_pjpy_pjlsxx.xydm is '学院代码';
comment on column xg_view_pjpy_pjlsxx.xymc is '学院名称';
comment on column xg_view_pjpy_pjlsxx.zydm is '专业代码';
comment on column xg_view_pjpy_pjlsxx.zymc is '专业名称';
comment on column xg_view_pjpy_pjlsxx.bjdm is '班级代码';
comment on column xg_view_pjpy_pjlsxx.bjmc is '班级名称';
comment on column xg_view_pjpy_pjlsxx.xmlx is '类型代码';
comment on column xg_view_pjpy_pjlsxx.xmlxmc is '项目类型';
comment on column xg_view_pjpy_pjlsxx.xmmc is '项目名称';
comment on column xg_view_pjpy_pjlsxx.xmje is '项目金额';
comment on column xg_view_pjpy_pjlsxx.hdsj is '获得时间';
comment on column xg_view_pjpy_pjlsxx.bz is '备注';

--------------评奖人员库---------------------------
create or replace view xg_view_pjpy_pjryk as
select a.xh, a.xm,b.nj, b.xydm, b.xymc, b.zydm, b.zymc, b.bjdm, b.bjmc
  from xg_pjpy_pjrykb a
  left join view_njxyzybj_all b
    on a.bjdm = b.bjdm
 where a.sfcp = 'yes';
comment on column xg_view_pjpy_pjryk.xh is '姓名';
comment on column xg_view_pjpy_pjryk.xm is '姓名';
comment on column xg_view_pjpy_pjryk.nj is '年级';
comment on column xg_view_pjpy_pjryk.xydm is '学院代码';
comment on column xg_view_pjpy_pjryk.xymc is '学院名称';
comment on column xg_view_pjpy_pjryk.zydm is '专业代码';
comment on column xg_view_pjpy_pjryk.zymc is '专业名称';
comment on column xg_view_pjpy_pjryk.bjdm is '班级代码';
comment on column xg_view_pjpy_pjryk.bjmc is '班级名称';


-------------------初始化-----------------------------
delete from xg_pjpy_pjlcdjb;
delete from xg_pjpy_pjlcb;

insert into xg_pjpy_pjlcdjb (lcdj,sftj) values('1','no');
insert into xg_pjpy_pjlcdjb (lcdj,sftj) values('2','no');
insert into xg_pjpy_pjlcdjb (lcdj,sftj) values('3','no');
insert into xg_pjpy_pjlcdjb (lcdj,sftj) values('4','no');
insert into xg_pjpy_pjlcdjb (lcdj,sftj) values('5','no');
insert into xg_pjpy_pjlcdjb (lcdj,sftj) values('6','no');
commit;

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59D6EB0DE040007F01004B58', '101', '开始新评奖', '1', 'no', 'showNewPjpy()', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59D7EB0DE040007F01004B58', '102', '评奖人员库设置', '2', 'no', 'goPjszPjry()', 'pjlc_ic01');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59D8EB0DE040007F01004B58', '103', '参评小组设置', '', 'no', 'goPjszCpxz()', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59D9EB0DE040007F01004B58', '104', '综测项目维护', '3', 'no', 'goPjszZcxm()', 'pjlc_ic03');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59DAEB0DE040007F01004B58', '105', '评奖项目维护', '5', 'no', 'goPjszPjxm()', 'pjlc_ic04');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59DBEB0DE040007F01004B58', '106', '评奖条件设置', '', 'no', '', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59DCEB0DE040007F01004B58', '107', '评奖人数设置', '', 'no', '', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59DDEB0DE040007F01004B58', '108', '评奖顺延设置', '', 'no', '', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59DEEB0DE040007F01004B58', '109', '评奖兼得设置', '', 'no', '', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59DFEB0DE040007F01004B58', '110', '评奖时间设置', '', 'no', '', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59E0EB0DE040007F01004B58', '111', '教务成绩确认', '', 'no', '', '');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59E1EB0DE040007F01004B58', '112', '综合分维护', '4', 'no', 'goZhcpZcwh()', 'pjlc_ic11');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59E2EB0DE040007F01004B58', '113', '评奖项目申请', '5', 'no', 'goPjpyWdpj()', 'pjlc_ic12');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59E3EB0DE040007F01004B58', '114', '评奖审核项目', '5', 'no', 'goPjpyWdpj()', 'pjlc_ic13');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59E4EB0DE040007F01004B58', '115', '班级大类设置', '2', 'no', 'goPjszBjdl()', 'pjlc_ic14');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59E5EB0DE040007F01004B58', '116', '综合分结果', '4', 'no', 'goZhcpZcjg()', 'pjlc_ic15');

insert into xg_pjpy_pjlcb (LCID, LCDM, LCMC, LCDJ, SFTJ, METHOD, PICNAME)
values ('C52EF07A59E6EB0DE040007F01004B58', '999', '结束本次评奖', '6', 'no', 'showEndPjpy()', '');

commit;
-------------------高级查询-----------------------------

-------------------评奖人员设置----------------------------
delete from xg_search_szb where path='general_pjpy.do?method=pjszPjry';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','xm','姓名','mhcx','xm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','bj','班级','djcx','bjdm','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','sf','是否调换','djcx','sfdh','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszPjry','sfyby','是否参评','djcx','sfcp','','','6');

commit;

-------------------参评小组设置----------------------------
delete from xg_search_szb where path='pjpy_pjsz_cpxz.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_cpxz.do','bjmc','班级名称','mhcx','bjmc','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_cpxz.do','cpzmc','参评组名称','mhcx','cpzmc','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_cpxz.do','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_cpxz.do','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_cpxz.do','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_cpxz.do','bj','班级','djcx','bjdm','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_cpxz.do','sf','是否设置','djcx','sfsz','','','5');

commit;

-------------------班级大类设置----------------------------
delete from xg_search_szb where path='general_pjpy.do?method=pjszBjdl';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszBjdl','bjmc','班级名称','mhcx','bjmc','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszBjdl','bjdlmc','班级大类名称','mhcx','bjdlmc','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszBjdl','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszBjdl','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszBjdl','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszBjdl','bj','班级','djcx','bjdm','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=pjszBjdl','sf','是否设置','djcx','sfsz','','','5');

commit;

-------------------综合测评维护----------------------------
delete from xg_search_szb where path='general_pjpy.do?method=zhcpZcxx';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=zhcpZcxx','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=zhcpZcxx','xm','姓名','mhcx','xm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=zhcpZcxx','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=zhcpZcxx','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=zhcpZcxx','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('general_pjpy.do?method=zhcpZcxx','bj','班级','djcx','bjdm','','','4');

commit;

-------------------人数设置设置----------------------------
delete from xg_search_szb where path='pjpy_xmsz_rssz.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','xymc','院系名称','mhcx','xymc','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','zymc','专业名称','mhcx','zymc','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','bjmc','班级名称','mhcx','bjmc','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','bj','班级','djcx','bjdm','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do','sf','是否设置','djcx','sfsz','','','5');

commit;

-------------------人数设置设置【年级】----------------------------
delete from xg_search_szb where path='pjpy_xmsz_rssz.do\&searchType=nj';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=nj','nj','年级','mhcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=nj','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=nj','sf','是否设置','djcx','sfsz','','','2');

commit;

-------------------人数设置设置【学院】----------------------------
delete from xg_search_szb where path='pjpy_xmsz_rssz.do\&searchType=xy';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=xy','xymc','院系名称','mhcx','xymc','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=xy','xy','学院','djcx','xydm','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=xy','sf','是否设置','djcx','sfsz','','','2');

commit;

-------------------人数设置设置【年级+学院】----------------------------
delete from xg_search_szb where path='pjpy_xmsz_rssz.do\&searchType=njxy';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njxy','nj','年级','mhcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njxy','xymc','院系名称','mhcx','xymc','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njxy','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njxy','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njxy','sf','是否设置','djcx','sfsz','','','3');

commit;

-------------------人数设置设置【年级+专业】----------------------------
delete from xg_search_szb where path='pjpy_xmsz_rssz.do\&searchType=njzy';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njzy','nj','年级','mhcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njzy','xymc','院系名称','mhcx','xymc','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njzy','zymc','专业名称','mhcx','zymc','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njzy','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njzy','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njzy','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=njzy','sf','是否设置','djcx','sfsz','','','4');

commit;

-------------------人数设置设置【班级】----------------------------
delete from xg_search_szb where path='pjpy_xmsz_rssz.do\&searchType=bj';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','nj','年级','mhcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','xymc','院系名称','mhcx','xymc','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','zymc','专业名称','mhcx','zymc','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','bjmc','班级名称','mhcx','bjmc','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','bj','班级','djcx','bjdm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_xmsz_rssz.do\&searchType=bj','sf','是否设置','djcx','sfsz','','','5');

commit;

-------------------评奖项目设置----------------------------
delete from xg_search_szb where path='pjpy_pjsz_pjxm.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_pjxm.do','xmdm','项目代码','mhcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_pjxm.do','xmmc','项目名称','mhcx','','','','1');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_pjxm.do','xmlx','项目类型','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_pjsz_pjxm.do','xmxz','项目性质','djcx','','','','2');

commit;

-------------------本次评奖----------------------------
delete from xg_search_szb where path='pjpy_general_bcpj.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','xm','姓名','mhcx','xm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','xymc','院系名称','mhcx','xymc','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','zymc','专业名称','mhcx','zymc','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','bjmc','班级名称','mhcx','bjmc','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_bcpj.do','bj','班级','djcx','bjdm','','','4');

commit;

-------------------历史评奖----------------------------
delete from xg_search_szb where path='pjpy_general_lspj.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','xm','姓名','mhcx','xm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','xymc','院系名称','mhcx','xymc','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','zymc','专业名称','mhcx','zymc','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','bjmc','班级名称','mhcx','bjmc','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','nj','年级','djcx','nj','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','xy','学院','djcx','xydm','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','zy','专业','djcx','zydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','bj','班级','djcx','bjdm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','pjzq','评奖时间','djcx','pjsj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_general_lspj.do','pjlsxm','评奖项目','djcx','xmmc','','','6');

commit;

---------------汉字转拼音-----------------------------
CREATE OR REPLACE FUNCTION F_PINYIN(P_NAME IN VARCHAR2) RETURN VARCHAR2 AS

  V_COMPARE VARCHAR2(100);

  V_RETURN VARCHAR2(4000);

  FUNCTION F_NLSSORT(P_WORD IN VARCHAR2) RETURN VARCHAR2 AS
  
  BEGIN
  
    RETURN NLSSORT(P_WORD, 'NLS_SORT=SCHINESE_PINYIN_M');
  
  END;

BEGIN

  FOR I IN 1 .. LENGTH(P_NAME) LOOP
  
    V_COMPARE := F_NLSSORT(SUBSTR(P_NAME, I, 1));
  
    IF V_COMPARE >= F_NLSSORT('吖') AND V_COMPARE <= F_NLSSORT('骜') THEN
    
      V_RETURN := V_RETURN || 'A';
    
    ELSIF V_COMPARE >= F_NLSSORT('八') AND V_COMPARE <= F_NLSSORT('簿') THEN
    
      V_RETURN := V_RETURN || 'B';
    
    ELSIF V_COMPARE >= F_NLSSORT('嚓') AND V_COMPARE <= F_NLSSORT('错') THEN
    
      V_RETURN := V_RETURN || 'C';
    
    ELSIF V_COMPARE >= F_NLSSORT('咑') AND V_COMPARE <= F_NLSSORT('鵽') THEN
    
      V_RETURN := V_RETURN || 'D';
    
    ELSIF V_COMPARE >= F_NLSSORT('妸') AND V_COMPARE <= F_NLSSORT('樲') THEN
    
      V_RETURN := V_RETURN || 'E';
    
    ELSIF V_COMPARE >= F_NLSSORT('发') AND V_COMPARE <= F_NLSSORT('猤') THEN
    
      V_RETURN := V_RETURN || 'F';
    
    ELSIF V_COMPARE >= F_NLSSORT('旮') AND V_COMPARE <= F_NLSSORT('腂') THEN
    
      V_RETURN := V_RETURN || 'G';
    
    ELSIF V_COMPARE >= F_NLSSORT('妎') AND V_COMPARE <= F_NLSSORT('夻') THEN
    
      V_RETURN := V_RETURN || 'H';
    
    ELSIF V_COMPARE >= F_NLSSORT('丌') AND V_COMPARE <= F_NLSSORT('攈') THEN
    
      V_RETURN := V_RETURN || 'J';
    
    ELSIF V_COMPARE >= F_NLSSORT('咔') AND V_COMPARE <= F_NLSSORT('穒') THEN
    
      V_RETURN := V_RETURN || 'K';
    
    ELSIF V_COMPARE >= F_NLSSORT('垃') AND V_COMPARE <= F_NLSSORT('擽') THEN
    
      V_RETURN := V_RETURN || 'L';
    
    ELSIF V_COMPARE >= F_NLSSORT('呒') AND V_COMPARE <= F_NLSSORT('椧') THEN
    
      V_RETURN := V_RETURN || 'M';
    
    ELSIF V_COMPARE >= F_NLSSORT('拏') AND V_COMPARE <= F_NLSSORT('疟') THEN
    
      V_RETURN := V_RETURN || 'N';
    
    ELSIF V_COMPARE >= F_NLSSORT('筽') AND V_COMPARE <= F_NLSSORT('沤') THEN
    
      V_RETURN := V_RETURN || 'O';
    
    ELSIF V_COMPARE >= F_NLSSORT('妑') AND V_COMPARE <= F_NLSSORT('曝') THEN
    
      V_RETURN := V_RETURN || 'P';
    
    ELSIF V_COMPARE >= F_NLSSORT('七') AND V_COMPARE <= F_NLSSORT('裠') THEN
    
      V_RETURN := V_RETURN || 'Q';
    
    ELSIF V_COMPARE >= F_NLSSORT('亽') AND V_COMPARE <= F_NLSSORT('鶸') THEN
    
      V_RETURN := V_RETURN || 'R';
    
    ELSIF V_COMPARE >= F_NLSSORT('仨') AND V_COMPARE <= F_NLSSORT('蜶') THEN
    
      V_RETURN := V_RETURN || 'S';
    
    ELSIF V_COMPARE >= F_NLSSORT('侤') AND V_COMPARE <= F_NLSSORT('箨') THEN
    
      V_RETURN := V_RETURN || 'T';
    
    ELSIF V_COMPARE >= F_NLSSORT('屲') AND V_COMPARE <= F_NLSSORT('鹜') THEN
    
      V_RETURN := V_RETURN || 'W';
    
    ELSIF V_COMPARE >= F_NLSSORT('夕') AND V_COMPARE <= F_NLSSORT('鑂') THEN
    
      V_RETURN := V_RETURN || 'X';
    
    ELSIF V_COMPARE >= F_NLSSORT('丫') AND V_COMPARE <= F_NLSSORT('韵') THEN
    
      V_RETURN := V_RETURN || 'Y';
    
    ELSIF V_COMPARE >= F_NLSSORT('帀') AND V_COMPARE <= F_NLSSORT('咗') THEN
    
      V_RETURN := V_RETURN || 'Z';
    
    END IF;
  
  END LOOP;

  RETURN V_RETURN;

END;







------------------20120830--------------------------------------
create table XG_PJPY_CPZB
(
  CPZDM VARCHAR2(40) default sys_guid() not null,
  CPZMC VARCHAR2(100),
  BJDM  VARCHAR2(20),
  primary key(CPZDM)
);
comment on table XG_PJPY_CPZB
  is '学工_评奖评优_参评组表';
-- Add comments to the columns 
comment on column XG_PJPY_CPZB.CPZDM
  is '参评组代码';
comment on column XG_PJPY_CPZB.CPZMC
  is '参评组名称';
comment on column XG_PJPY_CPZB.BJDM
  is '班级代码';
  
create or replace view xg_view_pjpy_pjryk as
select a.xh,
       a.xm,
       b.nj,
       b.xydm,
       b.xymc,
       b.zydm,
       b.zymc,
       b.bjdm,
       b.bjmc,
       a.cpz cpzdm,
       a.cpz cpzmc
  from xg_pjpy_pjrykb a
  left join view_njxyzybj_all b
    on a.bjdm = b.bjdm
 where a.sfcp = 'yes';
comment on column xg_view_pjpy_pjryk.xh is '姓名';
comment on column xg_view_pjpy_pjryk.xm is '姓名';
comment on column xg_view_pjpy_pjryk.nj is '年级';
comment on column xg_view_pjpy_pjryk.xydm is '学院代码';
comment on column xg_view_pjpy_pjryk.xymc is '学院名称';
comment on column xg_view_pjpy_pjryk.zydm is '专业代码';
comment on column xg_view_pjpy_pjryk.zymc is '专业名称';
comment on column xg_view_pjpy_pjryk.bjdm is '班级代码';
comment on column xg_view_pjpy_pjryk.bjmc is '班级名称';
comment on column xg_view_pjpy_pjryk.cpzdm is '参评组代码';
comment on column xg_view_pjpy_pjryk.cpzmc is '参评组名称';

--班级排名
create or replace view xg_view_pjpy_bjpmbl as
select a.xh,
       a.zyfbjpm,
       a.zcfbjpm,
       b.num,
       round(a.zyfbjpm * 100 / nvl(b.num, 1), 2) zyfbl,
       round(a.zcfbjpm * 100 / nvl(b.num, 1), 2) zcfbl
  from (select a.xh, a.zyfbjpm, a.zcfbjpm, b.bjdm
          from xg_pjpy_zhcpb a, xg_view_pjpy_pjryk b
         where a.xh = b.xh
           and a.xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)
           and a.xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)
           and a.nd = (select pjnd from xg_pjpy_xtszb where rownum = 1)) a
  left join (select bjdm, bjmc, count(1) num
               from xg_view_pjpy_pjryk
              group by bjdm, bjmc) b
    on a.bjdm = b.bjdm;

--年级专业排名 
create or replace view xg_view_pjpy_njzypmbl as
select a.xh,
       a.zyfnjzypm,
       a.zcfnjzypm,
       b.num,
       round(a.zyfnjzypm * 100 / nvl(b.num, 1), 2) zyfbl,
       round(a.zcfnjzypm * 100 / nvl(b.num, 1), 2) zcfbl
  from (select a.xh, a.zyfnjzypm, a.zcfnjzypm, b.nj||b.zydm bmdm
          from xg_pjpy_zhcpb a, xg_view_pjpy_pjryk b
         where a.xh = b.xh
           and a.xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)
           and a.xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)
           and a.nd = (select pjnd from xg_pjpy_xtszb where rownum = 1)) a
  left join (select nj||zydm bmdm, count(1) num
               from xg_view_pjpy_pjryk
              group by nj, zydm) b
    on a.bmdm = b.bmdm;
    
--参评组排名
create or replace view xg_view_pjpy_cpzpmbl as
select a.xh,
       a.zyfcpzpm,
       a.cpzpm,
       b.num,
       round(a.zyfcpzpm * 100 / nvl(b.num, 1), 2) zyfbl,
       round(a.cpzpm * 100 / nvl(b.num, 1), 2) zcfbl
  from (select a.xh, a.zyfcpzpm, a.cpzpm, b.cpzdm bmdm
          from xg_pjpy_zhcpb a, xg_view_pjpy_pjryk b
         where a.xh = b.xh
           and a.xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)
           and a.xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)
           and a.nd = (select pjnd from xg_pjpy_xtszb where rownum = 1)) a
  left join (select cpzdm bmdm, count(1) num
               from xg_view_pjpy_pjryk
              group by cpzdm) b
    on a.bmdm = b.bmdm;
    
create table xg_xtwh_drszb
(
  szid  VARCHAR2(40) default sys_guid() not null,
  drb VARCHAR2(100),
  primary key(szid)
);

create table xg_xtwh_drzdszb
(
  szid  VARCHAR2(40),
  zd    VARCHAR2(50),
  zdm   VARCHAR2(50),
  primary key(szid,zd)
);

create table xg_xtwh_drlsb
(
  zd0   VARCHAR2(50),
  zd1   VARCHAR2(50),
  zd2   VARCHAR2(50),
  zd3   VARCHAR2(50),
  zd4   VARCHAR2(50),
  zd5   VARCHAR2(50),
  zd6   VARCHAR2(50),
  zd7   VARCHAR2(50),
  zd8   VARCHAR2(50),
  zd9   VARCHAR2(50),
  zd10  VARCHAR2(50),
  czr   VARCHAR2(50),
  szid  VARCHAR2(50),
  lx    VARCHAR2(50) default 'error'
);

create table xg_xtwh_drckb
(
  ckid  VARCHAR2(40) default sys_guid() not null,
  szid  VARCHAR2(40) ,
  ckmc  VARCHAR2(50),
  ckb   VARCHAR2(50),
  xssx  VARCHAR2(5),
  primary key(ckid)
);