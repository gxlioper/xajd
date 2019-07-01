insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350504', '综测统计(等级考试)', 'pjpy_tjcx_zcbjmd_djks.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350505', '综测统计(无等级考试)', 'pjpy_tjcx_zcbjmd_nodjks.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350506', '获奖名单汇总', 'pjpy_tjcx_hjmdhz.do', '1', '');
insert into gnmkdmb (GNMKDM, GNMKMC, DYYM, DXQ, XSSX) values ('N350507', '获奖金额汇总', 'pjpy_tjcx_hjjehz.do', '1', '');

insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350504', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350505', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350506', '1');
insert into yhqxb (yhm, gnmkdm, dxq) values ('zf01', 'N350507', '1');

commit;

-----------------高级查询-------------------------
-----------------综测班级名单【有等级考试】-------------------------
delete from xg_search_szb where path='pjpy_tjcx_zcbjmd_djks.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xn','学年','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','nj','年级','djcx','nj','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','xy','学院','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','zy','专业','djcx','zydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_djks.do','bj','班级','djcx','bjdm','','','5');

-----------------综测班级名单【无等级考试】-------------------------
delete from xg_search_szb where path='pjpy_tjcx_zcbjmd_nodjks.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xn','学年','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','nj','年级','djcx','nj','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','xy','学院','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','zy','专业','djcx','zydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_zcbjmd_nodjks.do','bj','班级','djcx','bjdm','','','5');

-----------------获奖名单汇总-------------------------
delete from xg_search_szb where path='pjpy_tjcx_hjmdhz.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xn','学年','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xy','学院','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjmdhz.do','xmlx','项目类型','djcx','','','','4');

-----------------获奖金额汇总-------------------------
delete from xg_search_szb where path='pjpy_tjcx_hjjehz.do';

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xh','学号','mhcx','xh','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xm','姓名','mhcx','xm','','','2');

insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xn','学年','djcx','','','','1');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','nj','年级','djcx','nj','','','2');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','xy','学院','djcx','xydm','','','3');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','zy','专业','djcx','zydm','','','4');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','bj','班级','djcx','bjdm','','','5');
insert into xg_search_szb(path,tj,mc,lx,zd,ssmk,xszd,xssx)
values('pjpy_tjcx_hjjehz.do','pjlsxm','项目名称','djcx','xmmc','','','6');

commit;