insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350504', '�۲�ͳ��(�ȼ�����)', 'pjpy_tjcx_zcbjmd_djks.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350505', '�۲�ͳ��(�޵ȼ�����)', 'pjpy_tjcx_zcbjmd_nodjks.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350506', '����������', 'pjpy_tjcx_hjmdhz.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350507', '�񽱽�����', 'pjpy_tjcx_hjjehz.do', '1', '');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350504', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350505', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350506', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350507', '1');

commit;

-----------------�߼���ѯ-------------------------
-----------------�۲�༶�������еȼ����ԡ�-------------------------
delete from xg_search_szb where path='pjpy_tjcx_zcbjmd_djks.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xn','ѧ��','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','nj','�꼶','djcx','nj','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xy','ѧԺ','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','zy','רҵ','djcx','zydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','bj','�༶','djcx','bjdm','','','5');

-----------------�۲�༶�������޵ȼ����ԡ�-------------------------
delete from xg_search_szb where path='pjpy_tjcx_zcbjmd_nodjks.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xn','ѧ��','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','nj','�꼶','djcx','nj','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xy','ѧԺ','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','zy','רҵ','djcx','zydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','bj','�༶','djcx','bjdm','','','5');

-----------------����������-------------------------
delete from xg_search_szb where path='pjpy_tjcx_hjmdhz.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xn','ѧ��','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xy','ѧԺ','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xmlx','��Ŀ����','djcx','','','','4');

-----------------�񽱽�����-------------------------
delete from xg_search_szb where path='pjpy_tjcx_hjjehz.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xh','ѧ��','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xm','����','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xn','ѧ��','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','nj','�꼶','djcx','nj','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xy','ѧԺ','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','zy','רҵ','djcx','zydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','bj','�༶','djcx','bjdm','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','pjlsxm','��Ŀ����','djcx','xmmc','','','6');

commit;