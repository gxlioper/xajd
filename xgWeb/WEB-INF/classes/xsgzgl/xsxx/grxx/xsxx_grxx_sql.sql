/**学生信息_个人信息——数据库语句**/
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4102', '个人信息修改', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410201', '参数设置', 'xsxx_cssz_grxx.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410202', '修改申请', 'xsxx_grxx_sq.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410203', '修改审核', 'xsxx_grxx_sh.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N410204', '修改结果', 'xsxx_grxx_jg.do', '1', '');

commit;

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4102', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410201', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410202', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410203', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N410204', '1');

commit;

insert into xg_xtwh_splcmkdzb (mkdm,mkmc) values ('xsxx', '学生信息');

commit;

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N110312', '学籍异动管理', 'xsxx_xjyd.do', '1', '');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N110312', '1');

commit;

create table xg_xsxx_grxx_szb(
  sfsh    VARCHAR2(10),
  lcid    VARCHAR2(40),
  sqkssj  VARCHAR2(20),
  sqjssj  VARCHAR2(20),
  shkssj  VARCHAR2(20),
  shjssj  VARCHAR2(20),
  over    VARCHAR2(10)
);
comment on table xg_xsxx_grxx_szb is '学生信息_参数设置_个人信息设置表';
comment on column xg_xsxx_grxx_szb.sfsh is '是否审核';
comment on column xg_xsxx_grxx_szb.lcid is '流程ID';
comment on column xg_xsxx_grxx_szb.sqkssj is '申请开始时间';
comment on column xg_xsxx_grxx_szb.sqjssj is '申请结束时间';
comment on column xg_xsxx_grxx_szb.shkssj is '审核开始时间';
comment on column xg_xsxx_grxx_szb.shjssj is '审核结束时间';
comment on column xg_xsxx_grxx_szb.over is '设置结束';

create table xg_xsxx_grxx_zdszb(
  zd    		VARCHAR2(20),
  zdm   		VARCHAR2(40),
  sslx   		VARCHAR2(20),
  zdlx  		VARCHAR2(10),
  checked  		VARCHAR2(100),
  source_table  varchar2(50),
  select_dm     varchar2(50),
  select_mc     varchar2(50),
  xsqx          varchar2(10),
  lsqx          varchar2(10),
  xssx          varchar2(5),
  bm            varchar2(5),
  primary key (zd)
);
comment on table xg_xsxx_grxx_zdszb is '学生信息_个人信息_字段设置表';
comment on column xg_xsxx_grxx_zdszb.zd is '字段';
comment on column xg_xsxx_grxx_zdszb.sslx is '所属类型';
comment on column xg_xsxx_grxx_zdszb.zdm is '字段名';
comment on column xg_xsxx_grxx_zdszb.checked is '验证';
comment on column xg_xsxx_grxx_zdszb.zdlx is '字段类型';
comment on column xg_xsxx_grxx_zdszb.source_table is '数据源表';
comment on column xg_xsxx_grxx_zdszb.select_dm is '数据源代码';
comment on column xg_xsxx_grxx_zdszb.select_mc is '数据源名称';
comment on column xg_xsxx_grxx_zdszb.xsqx is '学生权限';
comment on column xg_xsxx_grxx_zdszb.lsqx is '老师权限';
comment on column xg_xsxx_grxx_zdszb.xssx is '显示顺序';
comment on column xg_xsxx_grxx_zdszb.bm is '别名';

create table xg_xsxx_grxx_xgsqb
(
  id    varchar2(40) default sys_guid() not null,
  xh    varchar2(20),
  sqsj  varchar2(20) default to_char(sysdate,'yyyymmdd'),
  sqr   varchar2(50),
  sqjg  varchar2(50),
  primary key (id)
);
comment on table xg_xsxx_grxx_xgsqb is '学生信息_个人信息_修改申请表';
comment on column xg_xsxx_grxx_xgsqb.id is 'ID';
comment on column xg_xsxx_grxx_xgsqb.xh is '学号';
comment on column xg_xsxx_grxx_xgsqb.sqsj is '申请时间';
comment on column xg_xsxx_grxx_xgsqb.sqr is '申请人';
comment on column xg_xsxx_grxx_xgsqb.sqjg is '申请结果';

create table xg_xsxx_grxx_xgshb(
  id    varchar2(40) default sys_guid() not null,
  sqid  varchar2(40),
  gwid  varchar2(40),
  lcid  varchar2(40),
  shr   varchar2(20),
  shzt  varchar2(10) default '未审核',
  shsj  varchar2(20) default to_char(sysdate,'yyyymmdd'),
  shyj  varchar2(1000),
  primary key (id)
);
comment on table xg_xsxx_grxx_xgshb is '学生信息_个人信息_修改审核表';
comment on column xg_xsxx_grxx_xgshb.id is 'ID';
comment on column xg_xsxx_grxx_xgshb.sqid is '申请ID';
comment on column xg_xsxx_grxx_xgshb.gwid is '岗位ID';
comment on column xg_xsxx_grxx_xgshb.lcid is '流程ID';
comment on column xg_xsxx_grxx_xgshb.shr is '审核人';
comment on column xg_xsxx_grxx_xgshb.shzt is '审核状态';
comment on column xg_xsxx_grxx_xgshb.shsj is '审核时间';
comment on column xg_xsxx_grxx_xgshb.shyj is '审核意见';

create table xg_xsxx_grxx_zdxgb
(
  id    varchar2(40) default sys_guid() not null,
  sqid  varchar2(40),
  xh    varchar2(20),
  xgsj  varchar2(20) default to_char(sysdate,'yyyymmdd'),
  xgr   varchar2(20),
  xgzd  varchar2(4000),
  ysz   varchar2(4000),
  xgz   varchar2(4000),
  primary key (id)
);
comment on table xg_xsxx_grxx_zdxgb is '学生信息_个人信息_字段修改表';
comment on column xg_xsxx_grxx_zdxgb.id is 'ID';
comment on column xg_xsxx_grxx_zdxgb.xh is '学号';
comment on column xg_xsxx_grxx_zdxgb.sqid is '申请ID';
comment on column xg_xsxx_grxx_zdxgb.xgsj is '修改时间';
comment on column xg_xsxx_grxx_zdxgb.xgr is '修改人';
comment on column xg_xsxx_grxx_zdxgb.xgzd is '修改字段';
comment on column xg_xsxx_grxx_zdxgb.ysz is '原始值';
comment on column xg_xsxx_grxx_zdxgb.xgz is '修改值';

-- Create/Recreate indexes 
create index index_XG_XSXX_GRXX_XGSQB_xh on XG_XSXX_GRXX_XGSQB (xh);

-----------------高级查询（个人信息修改审核）--------------------------------------
delete from xg_search_szb where path='xsxx_grxx_sh.do';
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','xh','学号','mhcx','xh','1');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','xm','姓名','mhcx','xm','2');

insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','nj','年级','djcx','nj','3');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','xy','学院','djcx','xydm','4');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','zy','专业','djcx','zydm','5');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','bj','班级','djcx','bjdm','6');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_sh.do','shzt','审核状态','djcx','','7');
commit;

-----------------高级查询（个人信息修改结果）--------------------------------------
delete from xg_search_szb where path='xsxx_grxx_jg.do';
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','xh','学号','mhcx','xh','1');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','xm','姓名','mhcx','xm','2');

insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','nj','年级','djcx','nj','3');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','xy','学院','djcx','xydm','4');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','zy','专业','djcx','zydm','5');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','bj','班级','djcx','bjdm','6');
insert into xg_search_szb(path,tj,mc,lx,zd,xssx) values('xsxx_grxx_jg.do','sqjg','申请结果','djcx','','7');
commit;

-----------------初始化字段设置--------------------------------------
delete from xg_xsxx_grxx_zdszb;
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xh','学号','基本信息','disabled','','','','','1','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xm','姓名','基本信息','text','16!!luojw!!no','','','','2','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xb','性别','基本信息','select','','','男!!luojw!!女','男!!luojw!!女','3','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('csrq','出生日期','基本信息','calendar','','','','','4','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('mz','民族','基本信息','select','','mzdmb','mzdm','mzmc','5','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('zzmm','政治面貌','基本信息','select','','zzmmdmb','zzmmdm','zzmmmc','6','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzh','身份证号','基本信息','text','18!!luojw!!sfzh','','','','7','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('pycc','培养层次','基本信息','select','','xg_xsxx_pyccdmb','pyccdm','pyccmc','8','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('hkszd','户口所在地','基本信息','ssx','','','','','9','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jg','籍贯','基本信息','ssx','','','','','10','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('syd','生源地','基本信息','ssx','','','','','11','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('bjdm','所在部门','学籍状态','szbm','','','','','12','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xjztm','学籍状态','学籍状态','select','','dm_zju_xjzt','zxdmmc','zxdmmc','13','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xz','学制','学籍状态','text','4!!luojw!!num','','','','14','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzc','是否注册','学籍状态','select','','','是!!luojw!!否','是!!luojw!!否','15','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzd','是否走读','学籍状态','select','','','是!!luojw!!否','是!!luojw!!否','16','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('rxrq','入学日期','学籍状态','calendar','','','','','17','a');
--insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('bysj','毕业时间','学籍状态','calendar','','','','','18','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('byny','毕业时间','学籍状态','calendar','','','','','18','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfbys','是否毕业生','学籍状态','select','','','是!!luojw!!否','是!!luojw!!否','19','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfyby','是否已毕业','学籍状态','select','','','是!!luojw!!否','是!!luojw!!否','20','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sfzx','是否在校','学籍状态','select','','','在校!!luojw!!不在校','在校!!luojw!!不在校','21','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('lxdh','联系电话','联系方式','text','20!!luojw!!num','','','','22','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sjhm','手机号码','联系方式','text','20!!luojw!!num','','','','23','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('qqhm','QQ号码','联系方式','text','20!!luojw!!num','','','','24','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('dzyx','电子邮箱','联系方式','text','30!!luojw!!dzyx','','','','25','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('yhdm','银行','其他信息','select','','dmk_yh','yhdm','yhmc','64','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('yhkh','银行卡号','其他信息','text','20!!luojw!!num','','','','65','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('fdyxm','辅导员姓名','其他信息','text','10!!luojw!!no','','','','66','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('kh','一卡通号','其他信息','text','20!!luojw!!num','','','','67','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('sg','身高','其他信息','text','10!!luojw!!money','','','','68','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('tz','体重','其他信息','text','10!!luojw!!money','','','','69','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('xmpy','姓名拼音','其他信息','text','16!!luojw!!no','','','','70','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('cym','曾用名','其他信息','text','16!!luojw!!no','','','','71','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('tc','特长','其他信息','text','32!!luojw!!no','','','','72','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('kslb','考生类别','其他信息','select','','xg_xsxx_kslbdmb','kslbdm','kslbmc','73','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('rxfs','入学方式','其他信息','select','','xg_xsxx_rxfsdmb','rxfsdm','rxfsmc','74','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('pyfs','培养方式','其他信息','select','','xg_xsxx_pyfsdmb','pyfsdm','pyfsmc','75','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jkzk','健康状况','其他信息','text','100!!luojw!!no','','','','76','a');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('lxdh1','家庭电话','家庭信息','text','20!!luojw!!num','','','','27','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtszd','家庭地址','家庭信息','text','25!!luojw!!no','','','','28','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('yb','邮政编码','家庭信息','text','10!!luojw!!num','','','','29','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jjzk','家庭经济状况','家庭信息','text','50!!luojw!!no','','','','30','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_xm','家庭成员1_姓名','家庭信息','text','25!!luojw!!no','','','','31','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_gx','家庭成员1_关系','家庭信息','text','10!!luojw!!no','','','','32','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_nl','家庭成员1_生日','家庭信息','calendar','','','','','33','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_sfzh','家庭成员1_身份证号','家庭信息','text','18!!luojw!!sfzh','','','','34','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_zy','家庭成员1_职业','家庭信息','text','10!!luojw!!no','','','','35','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_zw','家庭成员1_职务','家庭信息','text','10!!luojw!!no','','','','36','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_lxdh1','家庭成员1_工作电话','家庭信息','text','20!!luojw!!num','','','','37','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_lxdh2','家庭成员1_个人电话','家庭信息','text','20!!luojw!!num','','','','38','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_gzdz','家庭成员1_工作地址','家庭信息','text','25!!luojw!!no','','','','39','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_mz','家庭成员1_民族','家庭信息','select','','mzdmb','mzdm','mzmc','40','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy1_zzmm','家庭成员1_政治面貌','家庭信息','select','','zzmmdmb','zzmmdm','zzmmmc','41','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_xm','家庭成员2_姓名','家庭信息','text','25!!luojw!!no','','','','42','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_gx','家庭成员2_关系','家庭信息','text','10!!luojw!!no','','','','43','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_nl','家庭成员2_生日','家庭信息','calendar','','','','','44','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_sfzh','家庭成员2_身份证号','家庭信息','text','18!!luojw!!sfzh','','','','45','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_zy','家庭成员2_职业','家庭信息','text','10!!luojw!!no','','','','46','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_zw','家庭成员2_职务','家庭信息','text','10!!luojw!!no','','','','47','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_lxdh1','家庭成员2_工作电话','家庭信息','text','20!!luojw!!num','','','','48','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_lxdh2','家庭成员2_个人电话','家庭信息','text','20!!luojw!!num','','','','49','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_gzdz','家庭成员2_工作地址','家庭信息','text','25!!luojw!!no','','','','50','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_mz','家庭成员2_民族','家庭信息','select','','mzdmb','mzdm','mzmc','51','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy2_zzmm','家庭成员2_政治面貌','家庭信息','select','','zzmmdmb','zzmmdm','zzmmmc','52','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_xm','家庭成员3_姓名','家庭信息','text','25!!luojw!!no','','','','53','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_gx','家庭成员3_关系','家庭信息','text','10!!luojw!!no','','','','54','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_nl','家庭成员3_生日','家庭信息','calendar','','','','','55','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_sfzh','家庭成员3_身份证号','家庭信息','text','18!!luojw!!sfzh','','','','56','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_zy','家庭成员3_职业','家庭信息','text','10!!luojw!!no','','','','57','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_zw','家庭成员3_职务','家庭信息','text','10!!luojw!!no','','','','58','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_lxdh1','家庭成员3_工作电话','家庭信息','text','20!!luojw!!num','','','','59','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_lxdh2','家庭成员3_个人电话','家庭信息','text','20!!luojw!!num','','','','60','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_gzdz','家庭成员3_工作地址','家庭信息','text','25!!luojw!!no','','','','61','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_mz','家庭成员3_民族','家庭信息','select','','mzdmb','mzdm','mzmc','62','c');
insert into xg_xsxx_grxx_zdszb(zd,zdm,sslx,zdlx,checked,source_table,select_dm,select_mc,xssx,bm) values('jtcy3_zzmm','家庭成员3_政治面貌','家庭信息','select','','zzmmdmb','zzmmdm','zzmmmc','63','c');
commit;
