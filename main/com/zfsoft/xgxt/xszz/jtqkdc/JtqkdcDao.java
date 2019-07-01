package com.zfsoft.xgxt.xszz.jtqkdc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版之家庭情况调查 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-18 下午06:25:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JtqkdcDao extends SuperDAOImpl<JtqkdcForm> {

	@Override
	public List<HashMap<String, String>> getPageList(JtqkdcForm t)
			throws Exception {
		return null;
	}

	
	public List<HashMap<String, String>> getPageList(JtqkdcForm model, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
//		sql.append("select * from (select t1.xh,t1.dcsj,t2.xm,t2.xb,t2.nj,t2.xydm, ");
//		sql.append("t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc ");
//		sql.append("from xg_xszz_new_jtqkdcb t1 ");
//		sql.append("left join view_xsjbxx t2 on t1.xh=t2.xh ) t1 where 1=1 ");
		
		sql.append("select t1.*,t2.sydm,t3.symc from (select * from xg_view_xszz_new_jtqkdc a ) t1 ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_XTWH_SYDMB t3 on t2.sydm = t3.sydm");
		sql.append(" where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		super.setKey("xh");
		super.setTableName("xg_xszz_new_jtqkdcb");
		super.setClass(JtqkdcForm.class);
	}

	
	/**
	 * 家庭成员列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyList(String xh){
		
		String sql = "select a.*,b.mc cygxmc, nvl(c.mc,a.cyjkzk)cyjkzkmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx=b.dm left join xg_xsxx_jkztb c on a.cyjkzk=c.dm where a.xh = ? order by cygx";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	
	
	/**
	 * 
	 * @描述:按学号删除家庭成员信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-18 下午06:24:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteJtcy(String[] xh) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_jtcyb where (");
		
		for (int i = 0 , n = xh.length ; i < n ; i++){
			sql.append("xh=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		
		return dao.runUpdate(sql.toString(), xh);
	}
	
	
	
	/**
	 * 
	 * @描述:批量保存家庭成员
	 * @作者：Penghui.Qu
	 * @日期：2013-4-19 上午08:39:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param keys
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJtcy(String[] keys ,List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		
		sql.append("insert into xg_xszz_new_jtcyb (");
		
		for (int i = 0,n = keys.length ; i < n ; i++){
			sql.append(keys[i]);
			
			temp.append("?");
			
			if (i < n -1){
				sql.append(",");
				temp.append(",");
			}
		}

		sql.append(") values (");
		sql.append(temp);
		sql.append(")");
		
		int[] result = dao.runBatch(sql.toString(), params);
		
		return dao.checkBatchResult(result);
	}


	/** 
	 * @描述:根据学号获得父亲信息
	 * @作者：cq [工号：785]
	 * @日期：2013-9-22 下午01:48:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * JtqkdcForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getfqInfo(String xh) {
		
			String sql = "select a.*, b.mc cygxmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx = b.dm where a.xh = ? and mc like '父亲' and  rownum =1";
		
		return dao.getMapNotOut(sql, new String[]{xh});
	}


	/** 
	 * @描述:根据学号获得母亲信息
	 * @作者：cq [工号：785]
	 * @日期：2013-9-22 下午01:48:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * JtqkdcForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getmqInfo(String xh) {
		
			String sql = "select a.*, b.mc cygxmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx = b.dm where a.xh = ? and mc like '母亲' and  rownum =1";
		
			return dao.getMapNotOut(sql, new String[]{xh});
	}


	/** 
	 * @描述:上海海洋大学个性化查询家庭信息
	 * @作者：cq [工号：785]
	 * @日期：2014-10-29 下午04:51:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getJtqkInfo(String xh) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh,dcsj,decode(sfgc,'1','是','0','否',sfgc) sfgc,decode(sfdq,'1','是','0','否',sfdq) sfdq,decode(lszn,'1','是','0','否',lszn) lszn, ");
		sql.append("decode(sfdb,'1','是','0','否',sfdb) sfdb,jthk,jtszdssxdm,decode(sfpkx,'1','是','0','否',sfpkx) sfpkx,pkxjb, ");
		sql.append("decode(sfqz,'1','是','0','否',sfqz) sfqz,decode(fmjk,'1','是','0','否',fmjk) fmjk,fmjz,jtdh,jtdz,jtrs,jtyb,jtrjsr,jtnzsr,jtrjysr, ");
		sql.append("jtsrly,mzbmtxdz,mzbmyzbm,mzbmlxdh,snjtsr,yhzzqk,jtszqk,tfsjqk,cjnmqk,jtsyqk,jtqzqk,jtqtqk,ylzd1, ");
		sql.append("decode(ylzd2,'1','是','0','否',ylzd2) ylzd2,decode(ylzd3,'1','是','0','否',ylzd3) ylzd3,ylzd4,ylzd5,ylzd6,ylzd7,ylzd8,ylzd9, ");
		sql.append("ylzd10 from xg_xszz_new_jtqkdcb where xh = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	
	/**
	 * 
	 * @描述: 最多取默认前3条家庭成员信息(上海海洋个性化)
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2014-11-7 上午10:12:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJtcyListSh(String xh){
		
		String sql = "select a.*,b.mc cygxmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx=b.dm where a.xh = ? order by guid ";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:填写调查表模块，浙江大学是否残疾与残疾证编号联动，页面加载完毕根据是否残疾值为是（1）的时候
	 * 取cjbh(ylzd4)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-10-30 下午04:05:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
    public String getCjbh_10335(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select ylzd4 cjbh from xg_xszz_new_jtqkdcb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "cjbh");
	}
    
    
    /**
     * 
     * @描述: 导出
     * @作者：沈晓波 [工号：1123]
     * @日期：2016-5-16 上午10:32:08
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param model
     * @param user
     * @return
     * @throws Exception
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getAllList(JtqkdcForm model,
			User user) throws Exception {
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String view = "xg_view_xszz_new_jtqkdc";
		StringBuilder sql = new StringBuilder();

		sql.append("select t1.*,t2.sydm,t3.symc from (select * from ");
		sql.append(view);
		sql.append(" a ) t1 ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t1.bjdm = t2.bjdm");
		sql.append(" left join XG_XTWH_SYDMB t3 on t2.sydm = t3.sydm");
		sql.append(" where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return dao.getListNotOut(sql.toString(), inputV);
	}
    
    
    /**
	 * 家庭成员列表(最多4条)
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getJtcyListFour(String xh){
		
		String sql = " select a.* from (select rownum n, a.*, b.mc cygxmc, nvl(c.mc, a.cyjkzk) cyjkzkmc from xg_xszz_new_jtcyb a left join xszz_jtcygxb b on a.cygx = b.dm left join xg_xsxx_jkztb c on a.cyjkzk = c.dm where a.xh = ? order by cygx) a where a.n <= 4 ";
		
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * @描述：通用sql，取前n条家庭成员列表
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年12月6日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param n
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getJtcyList(String xh,String n){
		String sql = "select t.* from (select a.*, b.mc cygxmc, nvl(c.mc, a.cyjkzk) cyjkzkmc from xg_xszz_new_jtcyb a "
				+ " left join xszz_jtcygxb b on a.cygx = b.dm left join xg_xsxx_jkztb c on a.cyjkzk = c.dm where a.xh = ? order by cygx)t where rownum<= ? ";
		return dao.getListNotOut(sql, new String[]{xh,n});
	}
	
	/**
	 * @描述: 根据核实人的用户名查询出姓名
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-8 上午11:27:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String yhxm (String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select xm yhxm from yhb where yhm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{yhm}, "yhxm");
	}
	
	/**
	 * @描述: 根据学号获取家庭收入情况
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-9-11 下午05:10:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getJtsrqkByXh (String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select jtnzsr,jtnzsr / case when rs is null or rs = 0 then 1 else rs end jtrjnsr ");
		sql.append("from (select sum(nvl(cynsr, 0)) jtnzsr, count(*) rs from xg_xszz_new_jtcyb where xh = ?)");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
}
