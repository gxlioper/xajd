package xsgzgl.xtwh.general.xssjtb;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_学生数据检测
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.date.DateUtils;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_学生数据检测
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author xucy
 * @version 1.0
 */
public class XssjtbDao extends DAO{
	
	DAO dao = DAO.getInstance();	
	/**
	 * 检测日志 查询
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getSjjcRzList(XssjtbForm model) 
	throws IllegalArgumentException, SecurityException, 
	IllegalAccessException, InvocationTargetException, 
	NoSuchMethodException{
		String[]colList = new String[]{"jcsj", "jcb","jcbxx","zs","cgs","sbs"};
		String query="";
		query="where 1=1 ";
		if(null!=model.getJckssj()&&!"".equals(model.getJckssj())){
			query += " and to_date( '"+model.getJckssj()+"','yyyy-mm-dd hh24:mi:ss') <= to_date(jcsj,'yyyy-mm-dd hh24:mi:ss')";
		}
		if(null!=model.getJcjssj()&&!"".equals(model.getJcjssj())){
			query += " and to_date ('"+model.getJcjssj()+"' ,'yyyy-mm-dd hh24:mi:ss')>= to_date(jcsj,'yyyy-mm-dd hh24:mi:ss')";
		}
		query+=" order by jcsj desc  ) a";
		String sql=" select rownum r,a.* from (select jcsj,jcb," +
				"case when jcb = 'xg_xtwh_bmdmb'then '部门代码同步结果：' " +
				"when  jcb = 'xg_xtwh_zydmb'   then '专业代码同步结果：'  " +
				"when  jcb = 'xg_xtwh_bjdmb'   then '班级代码同步结果：'  " +
				"when  jcb = 'xg_xtwh_xsxxb'   then '学生信息同步结果：' " +
				"end jcbxx,  (cgs+sbs) zs,cgs,sbs from xg_xtwh_sjtbrzb  ";
		return CommonQueryDAO.commonQuery(sql, query, new String[]{},colList, model);
	}
	
	
	/**
	 * 异常数据 查询
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getYcsjList(XssjtbForm model) 
	throws IllegalArgumentException, SecurityException, 
	IllegalAccessException, InvocationTargetException, 
	NoSuchMethodException{
		String query="";
		String[]colList = new String[]{};
		String sql="";
		if("xg_xtwh_bmdmb".equals(model.getJcb())){
			colList = new String[]{"ycb", "zj","bmmc","ycyy"};
			query="where 1=1 ";
			 sql="select rownum r,a.*,b.bmmc from " +
			 		"(select * from xg_xtwh_sjtbycb where ycb = '"+model.getJcb()+"' and jcsj = '"+model.getJcsj()+"') a " +
			 		"left join xg_xtwh_bmdmb b on a.zj = b.bmdm ";
		}
		if("xg_xtwh_zydmb".equals(model.getJcb())){
			colList = new String[]{"ycb", "zj","zymc","bmdm","bmmc","ycyy"};
			query="where 1=1 ";
			sql="select rownum r, a.*, b.zymc,b.bmdm,b.bmmc from (select * from xg_xtwh_sjtbycb where ycb = '"+model.getJcb()+"' and jcsj " +
					"= '"+model.getJcsj()+"') a" +
			 	" left join ( select b.zydm,b.zymc,b.bmdm,c.bmmc from  xg_xtwh_zydmb b left join xg_xtwh_bmdmb c on b.bmdm = c.bmdm) b on a.zj = b.zydm ";
		}
		if("xg_xtwh_bjdmb".equals(model.getJcb())){
			colList = new String[]{"ycb", "zj","bjmc","zydm","zymc","nj","ycyy"};
			query="where 1=1 ";
			sql="select rownum r, a.*, b.bjmc,b.zydm,b.zymc,b.nj from (select * from xg_xtwh_sjtbycb where ycb = '"+model.getJcb()+"' and jcsj" +
					" = '"+model.getJcsj()+"') a" +
			 	" left join ( select b.bjdm,b.bjmc,b.zydm,b.nj,c.zymc from xg_xtwh_bjdmb b left join xg_xtwh_zydmb c on b.zydm = c.zydm) b on a.zj = b.bjdm ";
		}
		if("xg_xtwh_xsxxb".equals(model.getJcb())){
			colList = new String[]{"ycb", "zj","xm","xb","ycyy"};
			query="where 1=1 ";
			sql="select rownum r, a.ycb,a.zj, substr(a.ycyy,0,length(a.ycyy)-1) ycyy,b.xm, case when b.xb='1' then '男' when b.xb='2' then '女' else b.xb end xb from (select * from xg_xtwh_sjtbycb where ycb = '"+model.getJcb()+"' and jcsj" +
					" = '"+model.getJcsj()+"') a left join xg_xtwh_xsxxb b  on a.zj = b.xh " ;
		}
		return CommonQueryDAO.commonQuery(sql, query, new String[]{},colList, model);
	}
	
	//数据同步初始化
	public boolean cshsjTb() throws Exception{
		boolean flag = true;
		String sql = "truncate table xg_xtwh_sjtbycb";
			flag =  dao.runUpdate(sql, new String[]{});
		if(flag){
			sql = " truncate table xg_xtwh_sjtbrzb";
			flag =  dao.runUpdate(sql, new String[]{});
		}
		return flag;
	}
	
	//学院数据同步
	public boolean xysjTb() throws Exception{
		boolean flag = true;
		String jcsj = DateUtils.getCurrTime();
		//插入异常数据进异常表
		String xyycsql = "insert into xg_xtwh_sjtbycb select 'xg_xtwh_bmdmb' ycb,a.bmdm zj,'部门类别不正确' ycyy,'"+jcsj+"' jcsj from xg_xtwh_bmdmb a where " +
				"a.bmlb not in('1','5') union select  'xg_xtwh_bmdmb' ycb,a.bmdm zj,'部门没有专业' ycyy ,'"+jcsj+"' jcsj from (select * from xg_xtwh_bmdmb a " +
						"where a.bmlb='5') a where not exists (select 1 from xg_xtwh_zydmb b where a.bmdm = b.bmdm ) or not exists (select 1 from bks_zydm b where a.bmdm = b.bmdm )";
		flag =  dao.runUpdate(xyycsql, new String[]{});
		
		//批量同步部门（新增）
		String insertxysql = "insert into zxbz_xxbmdm (bmdm,bmmc,bmlb) select a.bmdm, a.bmmc, a.bmlb from xg_xtwh_bmdmb a where a.bmlb " +
							"in ('1', '5') and exists (select 1 from (select * from xg_xtwh_bmdmb a where a.bmlb = '5') a where  " +
							"exists (select 1 from xg_xtwh_zydmb b where a.bmdm = b.bmdm)) and not exists( select 1 from zxbz_xxbmdm b where a.bmdm = b.bmdm) ";
		flag =  dao.runUpdate(insertxysql, new String[]{});
		
		//批量同步部门（更新）
		String updatexysql = "update zxbz_xxbmdm a set bmmc=(select bmmc from view_xysjtb_update b where a.bmdm=b.bmdm),"+
						"bmlb=(select bmlb from view_xysjtb_update d where a.bmdm=d.bmdm) where exists (select 1 from view_xysjtb_update e where a.bmdm = e.bmdm)";
		flag =  dao.runUpdate(updatexysql, new String[]{});
		
		//异常日志表
		String xytbrz =" insert into xg_xtwh_sjtbrzb select  '"+jcsj+"' jcsj,  'xg_xtwh_bmdmb' jcb, '' jcnr, b.zs - a.sbs cgs,  a.sbs " +
				" from (select count(*) sbs from xg_xtwh_sjtbycb where ycb='xg_xtwh_bmdmb' and jcsj = '"+jcsj+"' ) a ,(select count(*) zs from  xg_xtwh_bmdmb ) b";
		flag =  dao.runUpdate(xytbrz, new String[]{});
		return flag;
	}
	
	
	//专业数据同步
	public boolean zysjTb() throws Exception{
		boolean flag = true;
		String jcsj = DateUtils.getCurrTime();
		//插入异常数据进异常表
		String zyycsql = "insert into xg_xtwh_sjtbycb select 'xg_xtwh_zydmb' ycb,a.zydm zj,'专业所属部门不存在部门代码表中' ycyy,'"+jcsj+"' jcsj from " +
						"xg_xtwh_zydmb a where not exists(select 1 from zxbz_xxbmdm b where a.bmdm=b.bmdm) " +
						"union select 'xg_xtwh_zydmb' ycb, a.zydm zj, '专业所属部门类别不是学院（5）' ycyy ,'"+jcsj+"' jcsj from  xg_xtwh_zydmb a " +
						"where not exists ( select * from zxbz_xxbmdm b where exists (select 1 from xg_xtwh_zydmb c where b.bmdm = c.bmdm) and bmlb='5')";
		flag =  dao.runUpdate(zyycsql, new String[]{});
		
		//批量同步专业（新增）
		String insertzysql = "insert into bks_zydm(zydm, bmdm,xkmldm,zymc) select zydm, bmdm,xkmldm,zymc  " +
							" from xg_xtwh_zydmb a  where exists (select 1 from zxbz_xxbmdm b where a.bmdm = b.bmdm) and exists " +
							" (select * from zxbz_xxbmdm b where exists (select 1 from xg_xtwh_zydmb c where b.bmdm = c.bmdm) and bmlb = '5') " +
							" and not exists (select 1 from bks_zydm c where a.zydm = c.zydm)";
		flag =  dao.runUpdate(insertzysql, new String[]{});
		
		//批量同步专业（更新）
		String updatezysql = "update bks_zydm a set bmdm =(select bmdm from view_zysjtb_update b where a.zydm = b.zydm),"+
							 " zymc = (select zymc from view_zysjtb_update b where a.zydm = b.zydm) "+
							 " where exists (select 1 from view_zysjtb_update e where a.zydm = e.zydm)";
		flag =  dao.runUpdate(updatezysql, new String[]{});
		
		//异常日志表
		String zytbrz =" insert into xg_xtwh_sjtbrzb select  '"+jcsj+"' jcsj,  'xg_xtwh_zydmb' jcb, '' jcnr, b.zs - a.sbs cgs,  a.sbs " +
					   "from (select count(*) sbs from xg_xtwh_sjtbycb where ycb='xg_xtwh_zydmb' and jcsj = '"+jcsj+"' ) a ,(select count(*) zs from xg_xtwh_zydmb ) b";
		flag =  dao.runUpdate(zytbrz, new String[]{});
		return flag;
	}
	
	//班级数据同步
	public boolean bjsjTb() throws Exception{
		boolean flag = true;
		String jcsj = DateUtils.getCurrTime();
		//插入异常数据进异常表
		String bjycsql = "insert into xg_xtwh_sjtbycb select 'xg_xtwh_bjdmb' ycb,a.bjdm zj,'班级所属专业不存在专业代码表中' ycyy,'"+jcsj+"' jcsj from " +
						"xg_xtwh_bjdmb a where not exists(select 1 from bks_zydm b where a.zydm=b.zydm) " ;
		flag =  dao.runUpdate(bjycsql, new String[]{});
		
		//批量同步班级（新增）
		String insertbjsql = "insert into bks_bjdm(bjdm,zydm,bjmc,nj) select bjdm,zydm,bjmc,nj from xg_xtwh_bjdmb a  " +
							"where exists (select 1 from bks_zydm b where a.zydm = b.zydm) and not exists(select 1 from bks_bjdm c where a.bjdm = c.bjdm)";
		flag =  dao.runUpdate(insertbjsql, new String[]{});
		
		//批量同步班级（更新）
		String updatebjsql = "update bks_bjdm a set zydm =(select zydm from view_bjsjtb_update b where a.bjdm = b.bjdm),"+
						" bjmc = (select bjmc from view_bjsjtb_update b where a.bjdm = b.bjdm), "+
						" nj = (select nj from view_bjsjtb_update b where a.bjdm = b.bjdm) "+
						" where exists (select 1 from view_bjsjtb_update e where a.bjdm = e.bjdm)";
		flag =  dao.runUpdate(updatebjsql, new String[]{});
		
		//异常日志表
		String bjtbrz ="insert into xg_xtwh_sjtbrzb select '"+jcsj+"' jcsj,  'xg_xtwh_bjdmb' jcb, '' jcnr, b.zs - a.sbs cgs,  a.sbs " +
					" from (select count(*) sbs from xg_xtwh_sjtbycb where ycb='xg_xtwh_bjdmb' and jcsj = '"+jcsj+"' ) a ,(select count(*) zs from  xg_xtwh_bjdmb ) b";
		flag =  dao.runUpdate(bjtbrz, new String[]{});
		return flag;
	}
	
	//学生数据同步
	public boolean xssjTb() throws Exception{
		boolean flag = true;
		String jcsj = DateUtils.getCurrTime();
		//插入异常数据进异常表
		String xsycsql ="insert into xg_xtwh_sjtbycb select 'xg_xtwh_xsxxb' ycb,a.zj zj," +
						" max(a.xbycyy)||max(a.mzycyy)||max(a.zzmmycyy)||max(a.jgycyy)||max(a.sfzxycyy)||max(a.njxyzybjycyy) ycyy,'"+jcsj+"' jcsj from " +
						" (select 'xg_xtwh_xsxxb' ycb, a.xh zj,'性别不是(男、女)或(1、2)；' xbycyy,''mzycyy,''zzmmycyy,''jgycyy,''sfzxycyy ,''njxyzybjycyy "+
						" from xg_xtwh_xsxxb a where a.xb not in('1','2','男','女')"+
						" union select  'xg_xtwh_xsxxb' ycb, a.xh zj,''xbycyy,'名族代码不存在；' mzycyy,''zzmmycyy,''jgycyy,''sfzxycyy ,''njxyzybjycyy"+
						" from (select * from  xg_xtwh_xsxxb a where a.mzdm is not null) a where not exists (select 1 from mzdmb b where a.mzdm = b.mzdm)"+
						" union select  'xg_xtwh_xsxxb' ycb,a.xh zj,''xbycyy ,''mzycyy,'政治面貌代码不存在；' zzmmycyy,''jgycyy,''sfzxycyy ,''njxyzybjycyy "+
						" from (select * from  xg_xtwh_xsxxb a where a.zzmmdm is not null)  a where not exists (select 1 from zzmmdmb b where a.zzmmdm = b.zzmmdm)"+
						" union select  'xg_xtwh_xsxxb' ycb,a.xh zj,''xbycyy,''mzycyy,''zzmmycyy, '籍贯代码不存在；' jgycyy,''sfzxycyy ,''njxyzybjycyy"+
						" from (select * from  xg_xtwh_xsxxb a where a.jgdm is not null)  a where not exists (select 1 from dmk_qx b where a.jgdm = b.qxdm)"+
						" union select  'xg_xtwh_xsxxb' ycb, a.xh zj,'' xbycyy,''mzycyy,''zzmmycyy,''jgycyy,'是否在校状态不正确；' sfzxycyy ,'' njxyzybjycyy "+
						" from  xg_xtwh_xsxxb a where a.sfzx is not null and a.sfzx not in('在校','不在校')"+
						" union select 'xg_xtwh_xsxxb' ycb, a.xh zj,'' xbycyy,''mzycyy,''zzmmycyy,''jgycyy, '' sfzxycyy, " +
						" '班级不存在；' njxyzybjycyy"+
						" from xg_xtwh_xsxxb a where not exists(select 1 from bks_bjdm b where a.bjdm = b.bjdm )"+
						" ) a group by a.ycb,a.zj";
		flag =  dao.runUpdate(xsycsql, new String[]{});
		
		String pzdyzdsql="select dyzd from xg_xsxx_tbzdpzb where sftb = '是'";
		String pzzdsql="select zd from xg_xsxx_tbzdpzb where sftb = '是'";
		
		List<HashMap<String,String>> dyzdlist = dao.getList(pzdyzdsql, new String[]{}, new String[]{"dyzd"});
		
		List<HashMap<String,String>> zdlist = dao.getList(pzzdsql, new String[]{}, new String[]{"zd"});
		String dyzds = "";
		String zds = "";
		if(null!=dyzdlist&&dyzdlist.size()>0){
			for(int i=0;i<dyzdlist.size();i++){
				if(i!=dyzdlist.size()-1){
					dyzds+=dyzdlist.get(i).get("dyzd")+",";
					zds+=zdlist.get(i).get("zd")+",";
				}else{
					dyzds+=dyzdlist.get(i).get("dyzd");
					zds+=zdlist.get(i).get("zd");
				}
			}
		}else{
			dyzds = "xh,xm,xb,bjdm,sfzh,xz,mz,zzmm,csrq,jg,syd,hkszd,sfzx,sjhm,dzyx,qqhm,jtdz,jtyb";
			zds = "xh,xm,xb,bjdm,sfzh,xz,mzdm,zzmmdm,csrq,jgdm,syddm,hkszddm,sfzx,sjhm,dzyx,qqhm,jtdz,jtyb";
		}
		
		//批量同步学生信息（新增）
		String insertxssql = "insert into xsxxb("+dyzds+") select "+zds+" from xg_xtwh_xsxxb a  where a.xb  in('1','2','男','女')" +
							 " and (exists (select 1 from (select * from  xg_xtwh_xsxxb a where a.mzdm is not null and exists (select 1 from mzdmb b where a.mzdm = b.mzdm))) or " +
							 " exists(select * from  xg_xtwh_xsxxb a where a.mzdm is  null )) " +
							 " and (exists (select 1 from (select * from  xg_xtwh_xsxxb a where a.zzmmdm is not null and exists (select 1 from zzmmdmb b where a.zzmmdm = b.zzmmdm))) or" +
							 " exists(select * from  xg_xtwh_xsxxb a where a.zzmmdm is  null ))" +
							 " and (exists (select 1 from (select * from  xg_xtwh_xsxxb a where a.jgdm is not null and exists (select 1 from dmk_qx b where a.jgdm = b.qxdm))) or" +
							 " exists(select * from  xg_xtwh_xsxxb a where a.jgdm is  null ))" +
							 " and exists (select  1 from  xg_xtwh_xsxxb a where a.sfzx in('在校','不在校','') )  and exists ( select 1 from xg_xtwh_xsxxb a where exists" +
							 " (select 1 from bks_bjdm b where  a.bjdm = b.bjdm )) and not exists(select 1 from xsxxb b where a.xh = b.xh)";
		flag =  dao.runUpdate(insertxssql, new String[]{});
		
		String[] zd = zds.split(",");
		String[] dyzd = dyzds.split(",");
		String updateSql = "";
		String zdz="";
		for(int i=0;i<zd.length;i++){	
			zdz = "(select "+zd[i]+" from view_xssjtb_update b where a.xh = b.xh)";
			if(i!=zd.length-1){
				updateSql+=""+dyzd[i]+" ="+zdz+",";
			}else{
				updateSql+=""+dyzd[i]+" ="+zdz+"";
			}
		}
				
		//批量同步学生信息（更新）
		String updatexssql = "update xsxxb a set "+updateSql+" where exists (select 1 from (view_xssjtb_update) b where a.xh = b.xh)";
		flag =  dao.runUpdate(updatexssql, new String[]{});
		
		//异常日志表
		String xstbrz =" insert into xg_xtwh_sjtbrzb select '"+jcsj+"' jcsj,  'xg_xtwh_xsxxb' jcb, '' jcnr, b.zs - a.sbs cgs,  a.sbs " +
				" from  (select count(*) sbs from xg_xtwh_sjtbycb where ycb='xg_xtwh_xsxxb' and jcsj = '"+jcsj+"' ) a ,(select count(*) zs from  xg_xtwh_xsxxb ) b";
		flag =  dao.runUpdate(xstbrz, new String[]{});
		return flag;
	}
	
	
}
