/**�������š������ݿ����**/

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N40', '���ݹ�ҵ԰��', '', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4001', 'ϵͳ����', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N400101', '��������', 'szyc_pointSetting.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4004', '�ҵĹ���', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N400401', '�ҵĹ���', 'pjpy_szgyyq_mypj.do', '1', '');

insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N4005', 'ͳ�Ʒ���', '', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N400501', '�ɼ�����', 'pjpy_szgyyq_cjhz.do', '1', '');

commit;

-------------------Ȩ��-----------------------------
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N40', '1');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4001', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N400101', '1');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4004', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N400401', '1');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N4005', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N400501', '1');

commit;

--���ӱ��ֶ�(����)--
alter table szyq_dshdjzb add(sqf VARCHAR2(5));
comment on column szyq_dshdjzb.sqf is '�����';

alter table szyq_dshdjzb add(bzrshf VARCHAR2(5));
comment on column szyq_dshdjzb.bzrshf is '��������˷�';

alter table szyq_dshdjzb add(bzrsh VARCHAR2(20) default 'δ���');
comment on column szyq_dshdjzb.bzrsh is '���������';

alter table szyq_dshdjzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_dshdjzb.bzrshsj is '���������ʱ��';

alter table szyq_dshdjzb add(bzrshr VARCHAR2(20));
comment on column szyq_dshdjzb.bzrshr is '�����������';

alter table szyq_dshdjzb add(xyshf VARCHAR2(5));
comment on column szyq_dshdjzb.xyshf is 'ѧԺ��˷�';

alter table szyq_dshdjzb add(xysh VARCHAR2(20) default 'δ���');
comment on column szyq_dshdjzb.xysh is 'ѧԺ���';

alter table szyq_dshdjzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_dshdjzb.xyshsj is 'ѧԺ���ʱ��';

alter table szyq_dshdjzb add(xyshr VARCHAR2(20));
comment on column szyq_dshdjzb.xyshr is 'ѧԺ�����';

alter table szyq_dshdjzb add(xxshf VARCHAR2(5));
comment on column szyq_dshdjzb.xxshf is 'ѧУ��˷�';

alter table szyq_dshdjzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_dshdjzb.xxshsj is 'ѧУ���ʱ��';

alter table szyq_dshdjzb add(xxshr VARCHAR2(20));
comment on column szyq_dshdjzb.xxshr is 'ѧУ�����';

--���ӱ��ֶ�(���Ա��)--
alter table szyq_yybdjzb add(sqf VARCHAR2(5));
comment on column szyq_yybdjzb.sqf is '�����';

alter table szyq_yybdjzb add(bzrshf VARCHAR2(5));
comment on column szyq_yybdjzb.bzrshf is '��������˷�';

alter table szyq_yybdjzb add(bzrsh VARCHAR2(20) default 'δ���');
comment on column szyq_yybdjzb.bzrsh is '���������';

alter table szyq_yybdjzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_yybdjzb.bzrshsj is '���������ʱ��';

alter table szyq_yybdjzb add(bzrshr VARCHAR2(20));
comment on column szyq_yybdjzb.bzrshr is '�����������';

alter table szyq_yybdjzb add(xyshf VARCHAR2(5));
comment on column szyq_yybdjzb.xyshf is 'ѧԺ��˷�';

alter table szyq_yybdjzb add(xysh VARCHAR2(20) default 'δ���');
comment on column szyq_yybdjzb.xysh is 'ѧԺ���';

alter table szyq_yybdjzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_yybdjzb.xyshsj is 'ѧԺ���ʱ��';

alter table szyq_yybdjzb add(xyshr VARCHAR2(20));
comment on column szyq_yybdjzb.xyshr is 'ѧԺ�����';

alter table szyq_yybdjzb add(xxshf VARCHAR2(5));
comment on column szyq_yybdjzb.xxshf is 'ѧУ��˷�';

alter table szyq_yybdjzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_yybdjzb.xxshsj is 'ѧУ���ʱ��';

alter table szyq_yybdjzb add(xxshr VARCHAR2(20));
comment on column szyq_yybdjzb.xxshr is 'ѧУ�����';

--���ӱ��ֶ�(IVT��̳)--
alter table szyq_ivtltb add(sqf VARCHAR2(5));
comment on column szyq_ivtltb.sqf is '�����';

alter table szyq_ivtltb add(bzrshf VARCHAR2(5));
comment on column szyq_ivtltb.bzrshf is '��������˷�';

alter table szyq_ivtltb add(bzrsh VARCHAR2(20) default 'δ���');
comment on column szyq_ivtltb.bzrsh is '���������';

alter table szyq_ivtltb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_ivtltb.bzrshsj is '���������ʱ��';

alter table szyq_ivtltb add(bzrshr VARCHAR2(20));
comment on column szyq_ivtltb.bzrshr is '�����������';

alter table szyq_ivtltb add(xyshf VARCHAR2(5));
comment on column szyq_ivtltb.xyshf is 'ѧԺ��˷�';

alter table szyq_ivtltb add(xysh VARCHAR2(20) default 'δ���');
comment on column szyq_ivtltb.xysh is 'ѧԺ���';

alter table szyq_ivtltb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_ivtltb.xyshsj is 'ѧԺ���ʱ��';

alter table szyq_ivtltb add(xyshr VARCHAR2(20));
comment on column szyq_ivtltb.xyshr is 'ѧԺ�����';

alter table szyq_ivtltb add(xxshf VARCHAR2(5));
comment on column szyq_ivtltb.xxshf is 'ѧУ��˷�';

alter table szyq_ivtltb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_ivtltb.xxshsj is 'ѧУ���ʱ��';

alter table szyq_ivtltb add(xxshr VARCHAR2(20));
comment on column szyq_ivtltb.xxshr is 'ѧУ�����';

--���ӱ��ֶ�(����)--
alter table szyq_xthddjb add(sqf VARCHAR2(5));
comment on column szyq_xthddjb.sqf is '�����';

alter table szyq_xthddjb add(bzrshf VARCHAR2(5));
comment on column szyq_xthddjb.bzrshf is '��������˷�';

alter table szyq_xthddjb add(bzrsh VARCHAR2(20) default 'δ���');
comment on column szyq_xthddjb.bzrsh is '���������';

alter table szyq_xthddjb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_xthddjb.bzrshsj is '���������ʱ��';

alter table szyq_xthddjb add(bzrshr VARCHAR2(20));
comment on column szyq_xthddjb.bzrshr is '�����������';

alter table szyq_xthddjb add(xyshf VARCHAR2(5));
comment on column szyq_xthddjb.xyshf is 'ѧԺ��˷�';

alter table szyq_xthddjb add(xysh VARCHAR2(20) default 'δ���');
comment on column szyq_xthddjb.xysh is 'ѧԺ���';

alter table szyq_xthddjb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_xthddjb.xyshsj is 'ѧԺ���ʱ��';

alter table szyq_xthddjb add(xyshr VARCHAR2(20));
comment on column szyq_xthddjb.xyshr is 'ѧԺ�����';

alter table szyq_xthddjb add(xxshf VARCHAR2(5));
comment on column szyq_xthddjb.xxshf is 'ѧУ��˷�';

alter table szyq_xthddjb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyq_xthddjb.xxshsj is 'ѧУ���ʱ��';

alter table szyq_xthddjb add(xxshr VARCHAR2(20));
comment on column szyq_xthddjb.xxshr is 'ѧУ�����';

--���ӱ��ֶ�(��֯����)--
alter table szyc_zznlfzb add(sqf VARCHAR2(5));
comment on column szyc_zznlfzb.sqf is '�����';

alter table szyc_zznlfzb add(bzrshf VARCHAR2(5));
comment on column szyc_zznlfzb.bzrshf is '��������˷�';

alter table szyc_zznlfzb add(bzrsh VARCHAR2(20) default 'δ���');
comment on column szyc_zznlfzb.bzrsh is '���������';

alter table szyc_zznlfzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_zznlfzb.bzrshsj is '���������ʱ��';

alter table szyc_zznlfzb add(bzrshr VARCHAR2(20));
comment on column szyc_zznlfzb.bzrshr is '�����������';

alter table szyc_zznlfzb add(xyshf VARCHAR2(5));
comment on column szyc_zznlfzb.xyshf is 'ѧԺ��˷�';

alter table szyc_zznlfzb add(xysh VARCHAR2(20) default 'δ���');
comment on column szyc_zznlfzb.xysh is 'ѧԺ���';

alter table szyc_zznlfzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_zznlfzb.xyshsj is 'ѧԺ���ʱ��';

alter table szyc_zznlfzb add(xyshr VARCHAR2(20));
comment on column szyc_zznlfzb.xyshr is 'ѧԺ�����';

alter table szyc_zznlfzb add(xxshf VARCHAR2(5));
comment on column szyc_zznlfzb.xxshf is 'ѧУ��˷�';

alter table szyc_zznlfzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_zznlfzb.xxshsj is 'ѧУ���ʱ��';

alter table szyc_zznlfzb add(xxshr VARCHAR2(20));
comment on column szyc_zznlfzb.xxshr is 'ѧУ�����';

--���ӱ��ֶ�(���ʵ��)--
alter table szyc_shsjfzb add(sqf VARCHAR2(5));
comment on column szyc_shsjfzb.sqf is '�����';

alter table szyc_shsjfzb add(bzrshf VARCHAR2(5));
comment on column szyc_shsjfzb.bzrshf is '��������˷�';

alter table szyc_shsjfzb add(bzrsh VARCHAR2(20) default 'δ���');
comment on column szyc_shsjfzb.bzrsh is '���������';

alter table szyc_shsjfzb add(bzrshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_shsjfzb.bzrshsj is '���������ʱ��';

alter table szyc_shsjfzb add(bzrshr VARCHAR2(20));
comment on column szyc_shsjfzb.bzrshr is '�����������';

alter table szyc_shsjfzb add(xyshf VARCHAR2(5));
comment on column szyc_shsjfzb.xyshf is 'ѧԺ��˷�';

alter table szyc_shsjfzb add(xysh VARCHAR2(20) default 'δ���');
comment on column szyc_shsjfzb.xysh is 'ѧԺ���';

alter table szyc_shsjfzb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_shsjfzb.xyshsj is 'ѧԺ���ʱ��';

alter table szyc_shsjfzb add(xyshr VARCHAR2(20));
comment on column szyc_shsjfzb.xyshr is 'ѧԺ�����';

alter table szyc_shsjfzb add(xxshf VARCHAR2(5));
comment on column szyc_shsjfzb.xxshf is 'ѧУ��˷�';

alter table szyc_shsjfzb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_shsjfzb.xxshsj is 'ѧУ���ʱ��';

alter table szyc_shsjfzb add(xxshr VARCHAR2(20));
comment on column szyc_shsjfzb.xxshr is 'ѧУ�����';

--���ӱ��ֶ�(5s��)--
alter table szyc_5sb add(sqf VARCHAR2(5));
comment on column szyc_5sb.sqf is '�����';

alter table szyc_5sb add(jfrq VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd'));
comment on column szyc_5sb.jfrq is '����';

alter table szyc_5sb add(jfyy VARCHAR2(500));
comment on column szyc_5sb.jfyy is '�ӷ�ԭ��';

alter table szyc_5sb add(xyshf VARCHAR2(5));
comment on column szyc_5sb.xyshf is 'ѧԺ��˷�';

alter table szyc_5sb add(xysh VARCHAR2(20) default 'δ���');
comment on column szyc_5sb.xysh is 'ѧԺ���';

alter table szyc_5sb add(xyshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_5sb.xyshsj is 'ѧԺ���ʱ��';

alter table szyc_5sb add(xyshr VARCHAR2(20));
comment on column szyc_5sb.xyshr is 'ѧԺ�����';

alter table szyc_5sb add(xxshf VARCHAR2(5));
comment on column szyc_5sb.xxshf is 'ѧУ��˷�';

alter table szyc_5sb add(xxshsj VARCHAR2(20) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'));
comment on column szyc_5sb.xxshsj is 'ѧУ���ʱ��';

alter table szyc_5sb add(xxshr VARCHAR2(20));
comment on column szyc_5sb.xxshr is 'ѧУ�����';

--���ӱ��ֶ�(�ۺ����ʷ�)--
alter table szgy_zhszcphzlsb add(zhszf VARCHAR2(10));
comment on column szgy_zhszcphzlsb.zhszf is '�ۺ����ʷ�';

alter table szgy_zhszcphzlsb add(zhszfpm VARCHAR2(5));
comment on column szgy_zhszcphzlsb.zhszfpm is '�ۺ����ʷ�����';

--������(ѧ�����߱�)--
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

comment on table xg_pjpy_szgyyq_xsssb is '��������_���ݹ�ҵ԰��_ѧ�����߱�';
comment on column xg_pjpy_szgyyq_xsssb.xn is 'ѧ��';
comment on column xg_pjpy_szgyyq_xsssb.xq is 'ѧ��';
comment on column xg_pjpy_szgyyq_xsssb.xh is 'ѧ��';
comment on column xg_pjpy_szgyyq_xsssb.xmlx is '��Ŀ����';
comment on column xg_pjpy_szgyyq_xsssb.xmid is '��ĿID';
comment on column xg_pjpy_szgyyq_xsssb.ssnr is '��������';
comment on column xg_pjpy_szgyyq_xsssb.sssj is '����ʱ��';
comment on column xg_pjpy_szgyyq_xsssb.clr is '������';
comment on column xg_pjpy_szgyyq_xsssb.clyj is '�������';
comment on column xg_pjpy_szgyyq_xsssb.clsj is '����ʱ��';

--������(ѧ��Ͷ�߱�)--
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

comment on table xg_pjpy_szgyyq_xstsb is '��������_���ݹ�ҵ԰��_ѧ��Ͷ�߱�';
comment on column xg_pjpy_szgyyq_xstsb.xn is 'ѧ��';
comment on column xg_pjpy_szgyyq_xstsb.xq is 'ѧ��';
comment on column xg_pjpy_szgyyq_xstsb.xh is 'ѧ��';
comment on column xg_pjpy_szgyyq_xstsb.xmlx is '��Ŀ����';
comment on column xg_pjpy_szgyyq_xstsb.btsr is '��Ͷ����';
comment on column xg_pjpy_szgyyq_xstsb.tsnr is 'Ͷ������';
comment on column xg_pjpy_szgyyq_xstsb.tssj is 'Ͷ��ʱ��';
comment on column xg_pjpy_szgyyq_xstsb.clr is '������';
comment on column xg_pjpy_szgyyq_xstsb.clyj is '�������';
comment on column xg_pjpy_szgyyq_xstsb.clsj is '����ʱ��';

--2011-11-17 gaobb
create or replace view xg_view_szgy_zhszcphzlsb as
select a.*,b.xm,b.bjmc,b.nj,b.xydm,b.zydm,b.bjdm,(case when zhszf is null or zhszf='0' then '��' else '��' end) cjsfjs 
from szgy_zhszcphzlsb a,view_xsjbxx b where a.xh=b.xh;
/
comment on column xg_view_szgy_zhszcphzlsb.xh is 'ѧ��';
comment on column xg_view_szgy_zhszcphzlsb.xn is 'ѧ��';
comment on column xg_view_szgy_zhszcphzlsb.xq is 'ѧ��';
comment on column xg_view_szgy_zhszcphzlsb.wsmkf is '��Sģ���';
comment on column xg_view_szgy_zhszcphzlsb.yybdf is '���Ա���';
comment on column xg_view_szgy_zhszcphzlsb.dshdf is '������';
comment on column xg_view_szgy_zhszcphzlsb.ivtlt is 'ivt��̳��';
comment on column xg_view_szgy_zhszcphzlsb.wthd is '������';
comment on column xg_view_szgy_zhszcphzlsb.zznl is '��֯������';
comment on column xg_view_szgy_zhszcphzlsb.shsj is '���ʵ����';
comment on column xg_view_szgy_zhszcphzlsb.zhszf is '�ۺ����ʷ�';
comment on column xg_view_szgy_zhszcphzlsb.zhszfpm is '�ۺ����ʷ�����';
comment on column xg_view_szgy_zhszcphzlsb.xm is '����';
comment on column xg_view_szgy_zhszcphzlsb.bjmc is '�༶';
comment on column xg_view_szgy_zhszcphzlsb.cjsfjs is '�ɼ��Ƿ����';
comment on column xg_view_szgy_zhszcphzlsb.nj is '�꼶';
comment on column xg_view_szgy_zhszcphzlsb.xydm is 'ѧԺ����';
comment on column xg_view_szgy_zhszcphzlsb.zydm is 'רҵ����';
comment on column xg_view_szgy_zhszcphzlsb.bjdm is '�༶����';

--2011-11-17 gaobb �����Ż�ϵͳ����
create table szgy_zhszcphzlsb_zhszfpm_temp
(
 xh varchar2(20),
 fz varchar2(20)
);
/
-- Add comments to the table 
comment on table szgy_zhszcphzlsb_zhszfpm_temp
  is '���ݹ�ҵ�ۺ����ʲ��������趨��_�ۺ����ʷ�������������ʱ��';
-- Add comments to the columns 
comment on column szgy_zhszcphzlsb_zhszfpm_temp.xh is 'ѧ��';
comment on column szgy_zhszcphzlsb_zhszfpm_temp.fz is '��ֵ������';

-- Add/modify columns 
alter table SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP add xn varchar2(20);
alter table SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP add xq varchar2(20);
-- Add comments to the columns 
comment on column SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP.xn
  is 'ѧ��';
comment on column SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP.xq
  is 'ѧ��';

  
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','xn','ѧ��','djcx','','pjpy','','1');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','xq','ѧ��','djcx','','pjpy','','2');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','nj','�꼶','djcx','','pjpy','','3');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','xy','ѧԺ','djcx','xydm','pjpy','','4');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','zy','רҵ','djcx','zydm','pjpy','','5');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','bj','�༶','djcx','bjdm','pjpy','','6');
insert into xg_search_szb values('pjpy_szgyyq_zhcp.do','sf','�ɼ��Ƿ����','djcx','cjsfjs','pjpy','','7');
commit;

-------------------------�߼���ѯ------------------------------------------
-----------------------2011.11.21 qlj ���ݹ�ҵ԰�� �������-------------------------------
delete from xg_search_szb where path='pjpy_szgyyq_fssh.do';
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xn','ѧ��','djcx','xn','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xq','ѧ��','djcx','xq','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','nj','�꼶','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','xy','ѧԺ','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','zy','רҵ','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','bj','�༶','djcx','bjdm','','','8');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','shztlx','���״̬','djcx','shztlx','','','9');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','sf','�Ƿ�������','djcx','ss','','','10');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fssh.do','sftj','�Ƿ�Ͷ��','djcx','ts','','','11');

commit;

-----------------------���ݹ�ҵ԰�� �ۺϲ�����-------------------------------
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_zhcp.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_zhcp.do','xm','����','mhcx','xm','','','2');
commit;

-----------------------���ݹ�ҵ԰�� 5s-------------------------------
delete from xg_search_szb where path='pjpy_szgyyq_fives.do';
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xn','ѧ��','djcx','xn','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xq','ѧ��','djcx','xq','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','nj','�꼶','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','xy','ѧԺ','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','zy','רҵ','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_fives.do','bj','�༶','djcx','bjdm','','','8');
commit;

-----------------------���ݹ�ҵ԰�� �����ѯ-------------------------------
delete from xg_search_szb where path='pjpy_szgyyq_teajgcx.do';
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xn','ѧ��','djcx','xn','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xq','ѧ��','djcx','xq','','','4');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','nj','�꼶','djcx','nj','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','xy','ѧԺ','djcx','xydm','','','6');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','zy','רҵ','djcx','zydm','','','7');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_szgyyq_teajgcx.do','bj','�༶','djcx','bjdm','','','8');
commit;

-- 2011-11-23 gaobb 
 create index szgy_zhszcphzlsb_z_t_xh_index on SZGY_ZHSZCPHZLSB_ZHSZFPM_TEMP (xh);