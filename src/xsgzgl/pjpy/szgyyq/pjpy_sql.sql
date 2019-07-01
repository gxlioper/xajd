/**评奖评优——数据库语句**/

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N40', '苏州工业园区', '', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4001', '系统设置', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N400101', '分数设置', 'szyc_pointSetting.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4004', '我的工作', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N400401', '我的工作', 'pjpy_szgyyq_mypj.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4005', '统计分析', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N400501', '成绩汇总', 'pjpy_szgyyq_cjhz.do', '1', '');

commit;

-------------------权限-----------------------------
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N40', '1');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4001', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N400101', '1');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4004', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N400401', '1');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4005', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N400501', '1');

commit;

--增加表字段(读书活动)--
alter table szyq_dshdjzb add(sqf VARCHAR2(5));
comment on column szyq_dshdjzb.sqf is '申请分';

alter table szyq_dshdjzb add(bzrshf VARCHAR2(5));
comment on column szyq_dshdjzb.bzrshf is '班主任审核分';

alter table szyq_dshdjzb add(bzrsh VARCHAR2(20) default '未审核');
comment on column szyq_dshdjzb.bzrsh is '班主任审核';

alter table szyq_dshdjzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_dshdjzb.bzrshsj is '班主任审核时间';

alter table szyq_dshdjzb add(bzrshr VARCHAR2(20));
comment on column szyq_dshdjzb.bzrshr is '班主任审核人';

alter table szyq_dshdjzb add(xyshf VARCHAR2(5));
comment on column szyq_dshdjzb.xyshf is '学院审核分';

alter table szyq_dshdjzb add(xysh VARCHAR2(20) default '未审核');
comment on column szyq_dshdjzb.xysh is '学院审核';

alter table szyq_dshdjzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_dshdjzb.xyshsj is '学院审核时间';

alter table szyq_dshdjzb add(xyshr VARCHAR2(20));
comment on column szyq_dshdjzb.xyshr is '学院审核人';

alter table szyq_dshdjzb add(xxshf VARCHAR2(5));
comment on column szyq_dshdjzb.xxshf is '学校审核分';

alter table szyq_dshdjzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_dshdjzb.xxshsj is '学校审核时间';

alter table szyq_dshdjzb add(xxshr VARCHAR2(20));
comment on column szyq_dshdjzb.xxshr is '学校审核人';

--增加表字段(语言表达)--
alter table szyq_yybdjzb add(sqf VARCHAR2(5));
comment on column szyq_yybdjzb.sqf is '申请分';

alter table szyq_yybdjzb add(bzrshf VARCHAR2(5));
comment on column szyq_yybdjzb.bzrshf is '班主任审核分';

alter table szyq_yybdjzb add(bzrsh VARCHAR2(20) default '未审核');
comment on column szyq_yybdjzb.bzrsh is '班主任审核';

alter table szyq_yybdjzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_yybdjzb.bzrshsj is '班主任审核时间';

alter table szyq_yybdjzb add(bzrshr VARCHAR2(20));
comment on column szyq_yybdjzb.bzrshr is '班主任审核人';

alter table szyq_yybdjzb add(xyshf VARCHAR2(5));
comment on column szyq_yybdjzb.xyshf is '学院审核分';

alter table szyq_yybdjzb add(xysh VARCHAR2(20) default '未审核');
comment on column szyq_yybdjzb.xysh is '学院审核';

alter table szyq_yybdjzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_yybdjzb.xyshsj is '学院审核时间';

alter table szyq_yybdjzb add(xyshr VARCHAR2(20));
comment on column szyq_yybdjzb.xyshr is '学院审核人';

alter table szyq_yybdjzb add(xxshf VARCHAR2(5));
comment on column szyq_yybdjzb.xxshf is '学校审核分';

alter table szyq_yybdjzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_yybdjzb.xxshsj is '学校审核时间';

alter table szyq_yybdjzb add(xxshr VARCHAR2(20));
comment on column szyq_yybdjzb.xxshr is '学校审核人';

--增加表字段(IVT论坛)--
alter table szyq_ivtltb add(sqf VARCHAR2(5));
comment on column szyq_ivtltb.sqf is '申请分';

alter table szyq_ivtltb add(bzrshf VARCHAR2(5));
comment on column szyq_ivtltb.bzrshf is '班主任审核分';

alter table szyq_ivtltb add(bzrsh VARCHAR2(20) default '未审核');
comment on column szyq_ivtltb.bzrsh is '班主任审核';

alter table szyq_ivtltb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_ivtltb.bzrshsj is '班主任审核时间';

alter table szyq_ivtltb add(bzrshr VARCHAR2(20));
comment on column szyq_ivtltb.bzrshr is '班主任审核人';

alter table szyq_ivtltb add(xyshf VARCHAR2(5));
comment on column szyq_ivtltb.xyshf is '学院审核分';

alter table szyq_ivtltb add(xysh VARCHAR2(20) default '未审核');
comment on column szyq_ivtltb.xysh is '学院审核';

alter table szyq_ivtltb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_ivtltb.xyshsj is '学院审核时间';

alter table szyq_ivtltb add(xyshr VARCHAR2(20));
comment on column szyq_ivtltb.xyshr is '学院审核人';

alter table szyq_ivtltb add(xxshf VARCHAR2(5));
comment on column szyq_ivtltb.xxshf is '学校审核分';

alter table szyq_ivtltb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_ivtltb.xxshsj is '学校审核时间';

alter table szyq_ivtltb add(xxshr VARCHAR2(20));
comment on column szyq_ivtltb.xxshr is '学校审核人';

--增加表字段(文体活动)--
alter table szyq_xthddjb add(sqf VARCHAR2(5));
comment on column szyq_xthddjb.sqf is '申请分';

alter table szyq_xthddjb add(bzrshf VARCHAR2(5));
comment on column szyq_xthddjb.bzrshf is '班主任审核分';

alter table szyq_xthddjb add(bzrsh VARCHAR2(20) default '未审核');
comment on column szyq_xthddjb.bzrsh is '班主任审核';

alter table szyq_xthddjb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_xthddjb.bzrshsj is '班主任审核时间';

alter table szyq_xthddjb add(bzrshr VARCHAR2(20));
comment on column szyq_xthddjb.bzrshr is '班主任审核人';

alter table szyq_xthddjb add(xyshf VARCHAR2(5));
comment on column szyq_xthddjb.xyshf is '学院审核分';

alter table szyq_xthddjb add(xysh VARCHAR2(20) default '未审核');
comment on column szyq_xthddjb.xysh is '学院审核';

alter table szyq_xthddjb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_xthddjb.xyshsj is '学院审核时间';

alter table szyq_xthddjb add(xyshr VARCHAR2(20));
comment on column szyq_xthddjb.xyshr is '学院审核人';

alter table szyq_xthddjb add(xxshf VARCHAR2(5));
comment on column szyq_xthddjb.xxshf is '学校审核分';

alter table szyq_xthddjb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_xthddjb.xxshsj is '学校审核时间';

alter table szyq_xthddjb add(xxshr VARCHAR2(20));
comment on column szyq_xthddjb.xxshr is '学校审核人';

--增加表字段(组织能力)--
alter table szyc_zznlfzb add(sqf VARCHAR2(5));
comment on column szyc_zznlfzb.sqf is '申请分';

alter table szyc_zznlfzb add(bzrshf VARCHAR2(5));
comment on column szyc_zznlfzb.bzrshf is '班主任审核分';

alter table szyc_zznlfzb add(bzrsh VARCHAR2(20) default '未审核');
comment on column szyc_zznlfzb.bzrsh is '班主任审核';

alter table szyc_zznlfzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_zznlfzb.bzrshsj is '班主任审核时间';

alter table szyc_zznlfzb add(bzrshr VARCHAR2(20));
comment on column szyc_zznlfzb.bzrshr is '班主任审核人';

alter table szyc_zznlfzb add(xyshf VARCHAR2(5));
comment on column szyc_zznlfzb.xyshf is '学院审核分';

alter table szyc_zznlfzb add(xysh VARCHAR2(20) default '未审核');
comment on column szyc_zznlfzb.xysh is '学院审核';

alter table szyc_zznlfzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_zznlfzb.xyshsj is '学院审核时间';

alter table szyc_zznlfzb add(xyshr VARCHAR2(20));
comment on column szyc_zznlfzb.xyshr is '学院审核人';

alter table szyc_zznlfzb add(xxshf VARCHAR2(5));
comment on column szyc_zznlfzb.xxshf is '学校审核分';

alter table szyc_zznlfzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_zznlfzb.xxshsj is '学校审核时间';

alter table szyc_zznlfzb add(xxshr VARCHAR2(20));
comment on column szyc_zznlfzb.xxshr is '学校审核人';

--增加表字段(社会实践)--
alter table szyc_shsjfzb add(sqf VARCHAR2(5));
comment on column szyc_shsjfzb.sqf is '申请分';

alter table szyc_shsjfzb add(bzrshf VARCHAR2(5));
comment on column szyc_shsjfzb.bzrshf is '班主任审核分';

alter table szyc_shsjfzb add(bzrsh VARCHAR2(20) default '未审核');
comment on column szyc_shsjfzb.bzrsh is '班主任审核';

alter table szyc_shsjfzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_shsjfzb.bzrshsj is '班主任审核时间';

alter table szyc_shsjfzb add(bzrshr VARCHAR2(20));
comment on column szyc_shsjfzb.bzrshr is '班主任审核人';

alter table szyc_shsjfzb add(xyshf VARCHAR2(5));
comment on column szyc_shsjfzb.xyshf is '学院审核分';

alter table szyc_shsjfzb add(xysh VARCHAR2(20) default '未审核');
comment on column szyc_shsjfzb.xysh is '学院审核';

alter table szyc_shsjfzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_shsjfzb.xyshsj is '学院审核时间';

alter table szyc_shsjfzb add(xyshr VARCHAR2(20));
comment on column szyc_shsjfzb.xyshr is '学院审核人';

alter table szyc_shsjfzb add(xxshf VARCHAR2(5));
comment on column szyc_shsjfzb.xxshf is '学校审核分';

alter table szyc_shsjfzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_shsjfzb.xxshsj is '学校审核时间';

alter table szyc_shsjfzb add(xxshr VARCHAR2(20));
comment on column szyc_shsjfzb.xxshr is '学校审核人';

--增加表字段(5s分)--
alter table szyc_5sb add(sqf VARCHAR2(5));
comment on column szyc_5sb.sqf is '申请分';

alter table szyc_5sb add(jfrq VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd'));
comment on column szyc_5sb.jfrq is '日期';

alter table szyc_5sb add(jfyy VARCHAR2(500));
comment on column szyc_5sb.jfyy is '加分原因';

alter table szyc_5sb add(xyshf VARCHAR2(5));
comment on column szyc_5sb.xyshf is '学院审核分';

alter table szyc_5sb add(xysh VARCHAR2(20) default '未审核');
comment on column szyc_5sb.xysh is '学院审核';

alter table szyc_5sb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_5sb.xyshsj is '学院审核时间';

alter table szyc_5sb add(xyshr VARCHAR2(20));
comment on column szyc_5sb.xyshr is '学院审核人';

alter table szyc_5sb add(xxshf VARCHAR2(5));
comment on column szyc_5sb.xxshf is '学校审核分';

alter table szyc_5sb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_5sb.xxshsj is '学校审核时间';

alter table szyc_5sb add(xxshr VARCHAR2(20));
comment on column szyc_5sb.xxshr is '学校审核人';

--增加表字段(综合素质分)--
alter table szgy_zhszcphzlsb add(zhszf VARCHAR2(10));
comment on column szgy_zhszcphzlsb.zhszf is '综合素质分';

alter table szgy_zhszcphzlsb add(zhszfpm VARCHAR2(5));
comment on column szgy_zhszcphzlsb.zhszfpm is '综合素质分排名';

--新增表(学生申诉表)--
create table xg_pjpy_szgyyq_xsssb(
xn   varchar2(20),
xq   varchar2(10),
xh   varchar2(20),
xmlx varchar2(20),
xmid varchar2(40),
ssnr varchar2(1000),
sssj varchar2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
clr  varchar2(20),
clyj varchar2(1000),
clsj varchar2(20),
primary key(xn,xq,xh,xmid)
);

comment on table xg_pjpy_szgyyq_xsssb is '评奖评优_苏州工业园区_学生申诉表';
comment on column xg_pjpy_szgyyq_xsssb.xn is '学年';
comment on column xg_pjpy_szgyyq_xsssb.xq is '学期';
comment on column xg_pjpy_szgyyq_xsssb.xh is '学号';
comment on column xg_pjpy_szgyyq_xsssb.xmlx is '项目类型';
comment on column xg_pjpy_szgyyq_xsssb.xmid is '项目ID';
comment on column xg_pjpy_szgyyq_xsssb.ssnr is '申诉内容';
comment on column xg_pjpy_szgyyq_xsssb.sssj is '申诉时间';
comment on column xg_pjpy_szgyyq_xsssb.clr is '处理人';
comment on column xg_pjpy_szgyyq_xsssb.clyj is '处理意见';
comment on column xg_pjpy_szgyyq_xsssb.clsj is '处理时间';

--新增表(学生投诉表)--
create table xg_pjpy_szgyyq_xstsb(
xn   varchar2(20),
xq   varchar2(10),
xh   varchar2(20),
btsr varchar2(20),
xmlx varchar2(20),
tsnr varchar2(1000),
tssj varchar2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
clr  varchar2(20),
clyj varchar2(1000),
clsj varchar2(20),
primary key(xn,xq,xh,btsr,xmlx)
);

comment on table xg_pjpy_szgyyq_xstsb is '评奖评优_苏州工业园区_学生投诉表';
comment on column xg_pjpy_szgyyq_xstsb.xn is '学年';
comment on column xg_pjpy_szgyyq_xstsb.xq is '学期';
comment on column xg_pjpy_szgyyq_xstsb.xh is '学号';
comment on column xg_pjpy_szgyyq_xstsb.xmlx is '项目类型';
comment on column xg_pjpy_szgyyq_xstsb.btsr is '被投诉人';
comment on column xg_pjpy_szgyyq_xstsb.tsnr is '投诉内容';
comment on column xg_pjpy_szgyyq_xstsb.tssj is '投诉时间';
comment on column xg_pjpy_szgyyq_xstsb.clr is '处理人';
comment on column xg_pjpy_szgyyq_xstsb.clyj is '处理意见';
comment on column xg_pjpy_szgyyq_xstsb.clsj is '处理时间';

--2011-11-17 gaobb
create or replace view xg_view_szgy_zhszcphzlsb as
select a.*,b.xm,b.bjmc,b.nj,b.xydm,b.zydm,b.bjdm,(case when zhszf is null or zhszf='0' then '否' else '是' end) cjsfjs 
from szgy_zhszcphzlsb a,view_xsjbxx b where a.xh=b.xh;
/
comment on column xg_view_szgy_zhszcphzlsb.xh is '学号';
comment on column xg_view_szgy_zhszcphzlsb.xn is '学年';
comment on column xg_view_szgy_zhszcphzlsb.xq is '学期';
comment on column xg_view_szgy_zhszcphzlsb.wsmkf is '五S模块分';
comment on column xg_view_szgy_zhszcphzlsb.yybdf is '语言表达分';
comment on column xg_view_szgy_zhszcphzlsb.dshdf is '读书活动分';
comment on column xg_view_szgy_zhszcphzlsb.ivtlt is 'ivt论坛分';
comment on column xg_view_szgy_zhszcphzlsb.wthd is '文体活动分';
comment on column xg_view_szgy_zhszcphzlsb.zznl is '组织能力分';
comment on column xg_view_szgy_zhszcphzlsb.shsj is '社会实践分';
comment on column xg_view_szgy_zhszcphzlsb.zhszf is '综合素质分';
comment on column xg_view_szgy_zhszcphzlsb.zhszfpm is '综合素质分排名';
comment on column xg_view_szgy_zhszcphzlsb.xm is '姓名';
comment on column xg_view_szgy_zhszcphzlsb.bjmc is '班级';
comment on column xg_view_szgy_zhszcphzlsb.cjsfjs is '成绩是否计算';
comment on column xg_view_szgy_zhszcphzlsb.nj is '年级';
comment on column xg_view_szgy_zhszcphzlsb.xydm is '学院代码';
comment on column xg_view_szgy_zhszcphzlsb.zydm is '专业代码';
comment on column xg_view_szgy_zhszcphzlsb.bjdm is '班级代码';

--2011-11-17 gaobb 用于优化系统性能
create table szgy_zhszcphzlsb_zhszfpm_temp
(
 xh varchar2(20),
 fz varchar2(20)
);
/
-- Add comments to the table 
comment on table szgy_zhszcphzlsb_zhszfpm_temp
  is '苏州工业综合素质测评分数设定表_综合素质分配排名计算临时表';
-- Add comments to the columns 
comment on column szgy_zhszcphzlsb_zhszfpm_temp.xh is '学号';
comment on column szgy_zhszcphzlsb_zhszfpm_temp.fz is '分值或排名';

-- Add/modify columns 
alter table SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP add xn varchar2(20);
alter table SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP add xq varchar2(20);
-- Add comments to the columns 
comment on column SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP.xn
  is '学年';
comment on column SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP.xq
  is '学期';

  
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','xn','学年','djcx','','pjpy','','1');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','xq','学期','djcx','','pjpy','','2');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','nj','年级','djcx','','pjpy','','3');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','xy','学院','djcx','xydm','pjpy','','4');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','zy','专业','djcx','zydm','pjpy','','5');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','bj','班级','djcx','bjdm','pjpy','','6');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','sf','成绩是否计算','djcx','cjsfjs','pjpy','','7');
commit;

-------------------------高级查询------------------------------------------
-----------------------2011.11.21 qlj 苏州工业园区 分数审核-------------------------------
delete from xg_search_szb where path='pjpy_szgyyq_fssh.do';
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xn','学年','djcx','xn','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xq','学期','djcx','xq','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','nj','年级','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xy','学院','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','zy','专业','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','bj','班级','djcx','bjdm','','','8');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','shztlx','审核状态','djcx','shztlx','','','9');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','sf','是否有申诉','djcx','ss','','','10');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','sftj','是否被投诉','djcx','ts','','','11');

commit;

-----------------------苏州工业园区 综合测评分-------------------------------
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_zhcp.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_zhcp.do','xm','姓名','mhcx','xm','','','2');
commit;

-----------------------苏州工业园区 5s-------------------------------
delete from xg_search_szb where path='pjpy_szgyyq_fives.do';
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xn','学年','djcx','xn','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xq','学期','djcx','xq','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','nj','年级','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xy','学院','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','zy','专业','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','bj','班级','djcx','bjdm','','','8');
commit;

-----------------------苏州工业园区 结果查询-------------------------------
delete from xg_search_szb where path='pjpy_szgyyq_teajgcx.do';
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xn','学年','djcx','xn','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xq','学期','djcx','xq','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','nj','年级','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xy','学院','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','zy','专业','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','bj','班级','djcx','bjdm','','','8');
commit;

-- 2011-11-23 gaobb 
 create index szgy_zhszcphzlsb_z_t_xh_index on SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP (xh);