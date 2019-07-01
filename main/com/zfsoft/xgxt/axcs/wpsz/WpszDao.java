/**
 * @部门:学工产品事业部
 * @日期：2014-12-2 下午06:13:18 
 */  
package com.zfsoft.xgxt.axcs.wpsz;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;


import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-2 下午06:13:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WpszDao extends SuperDAOImpl<WpszForm>{

	
	@Override
	public List<HashMap<String, String>> getPageList(WpszForm model) throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xmtp,(case when length(xmxxjs)>10 then substr(xmxxjs,0,10)||'...' else xmxxjs end)xmjs,c.mc lbmc from xg_xszz_axcsxmb a left join xg_xszz_wptpb b on a.xmdm=b.xmdm");
		sql.append(" left join xg_xszz_axcslbb c on a.xmlb = c.dm where 1=1");
		if (!StringUtil.isNull(model.getXn())) {
			params.add(model.getXn());
			sql.append(" and a.xn like '%'||?||'%'");
		}
		if (!StringUtil.isNull(model.getXmmc())) {
			params.add(model.getXmmc());
			sql.append(" and a.xmmc like '%'||?||'%'");
		}
		return getPageList(model, sql.toString(), params.toArray(new String[] {}));
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(WpszForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * 
	 * @描述:获取物品类别列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午03:56:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWplbList() throws Exception{
		String sql = "select * from xg_xszz_axcslbb";
		return dao.getListNotOut(sql, new String[]{});
	}
	/**
	 * 
	 * @描述:获取物品项目图片
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-3 下午04:47:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * InputStream 返回类型 
	 * @throws
	 */
	public InputStream getPhotoStream(String xmdm) {
		String sql = "select xmtp from xg_xszz_wptpb where xmdm = ?";
		try {
			return dao.getInputStream(sql, new String[] { xmdm });
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取可复制学年
	 */
	public List<HashMap<String, String>> getFzXnList(String xn) {
		String sql = "select distinct xn wpfzxn from xg_xszz_axcsxmb where xn<>?";
		return dao.getListNotOut(sql, new String[]{xn});
		
	}
	
	public List<HashMap<String, String>> getTjpzList() {
		String sql = "select * from XG_AXCS_TJPZB";
		return dao.getListNotOut(sql, new String[]{});
		
	}
	
	/**
	 * 获取可复制物品列表
	 */
	public List<HashMap<String, String>> getKfzWpList(String fzxn) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from xg_xszz_axcsxmb where xn = ?  ");
		return dao.getListNotOut(sb.toString(), new String[]{fzxn});
	}
	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveSqJl(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		String sql = "select count(*)  from xg_axjz_axcswpsqb  ";
		sql += " where xmdm in("+values+")";
		int result = dao.getOneRsint(sql);
		return result > 0;
	}
	/**
	 * 
	 * @描述:获取物品详细信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-8 下午04:41:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getWpxxByXmdm(String xmdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,nvl(sqsjzq,'无限制')sqsjfw from(");
		sql.append("select a.*,t2.mc xmlbmc,");
		sql.append(" (case when a.sqkssj is null then a.sqjssj when a.sqjssj is null then a.sqkssj else (a.sqkssj || '-' || a.sqjssj) end) sqsjzq");
		sql.append(" ,b.sqtj from xg_xszz_axcsxmb a");
		sql.append("  left join xg_xszz_axcslbb t2 on a.xmlb = t2.dm left join (select a.xmdm ,nvl(wm_concat(a.sqtj), '无限制条件')sqtj from (select a.xmdm,");
		sql.append("(case when a.tjmc is null then a.dcmc else  (a.dcmc || '(' || a.tjmc || ')') end) sqtj from(select");
		sql.append(" a.xmdm,a.dcdm,a.dcmc, wm_concat(a.tjmc) tjmc from(select t1.*,t3.dcdm,t3.tjz,t4.tjmc,t5.dcmc from xg_xszz_axcsxmb t1");
		sql.append(" left join XG_AXJZ_AXCSXMTJB t3 on t1.xmdm = t3.xmdm  left join XG_AXCS_TJPZB t4 on t3.tjz = t4.tjz");
		sql.append(" left join xg_xszz_new_kndcdmb t5 on t3.dcdm = t5.dcdm)a group by xmdm, dcdm, dcmc) a) a group by xmdm) b on a.xmdm=b.xmdm)a");
		sql.append(" where a.xmdm=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm});
		
	}
	/**
	 * 
	 * @描述:获取物品申请条件
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-10 下午06:45:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWpTjSz(String xmdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,(case when a.tjmc is null then a.dcmc else (a.dcmc || '(' || a.tjmc || ')') end) sqtj");
		sql.append(" from (select a.xmdm, a.dcdm, a.dcmc, wm_concat(a.tjz) tjz, wm_concat(a.tjmc) tjmc from (select t3.xmdm,");
		sql.append(" t3.dcdm, t3.tjz, t4.tjmc, t5.dcmc from  XG_AXJZ_AXCSXMTJB t3 left join XG_AXCS_TJPZB t4 on t3.tjz = t4.tjz");
		sql.append(" left join xg_xszz_new_kndcdmb t5 on t3.dcdm = t5.dcdm) a group by xmdm, dcdm, dcmc) a where a.xmdm=?");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	/**
	 * 
	 * @描述:获取物品条件List
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-11 下午01:02:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWpTjList(String xmdm){
		StringBuffer sql = new StringBuffer();
		sql.append("select *　from XG_AXJZ_AXCSXMTJB where xmdm=?");
		return dao.getListNotOut(sql.toString(), new String[]{xmdm});
	}
	/**
	 * 
	 * @描述:获取学生家庭情况
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-10 下午06:46:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getJtxx(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,b.rddc　from xg_xszz_new_jtqkdcb a left join");
		sql.append(" (select * from (select rownum rn, a.* from(select * from xg_xszz_new_knsjgb  order by sqsj desc) a where a.xh=?) where rn=1");
		sql.append(" )b on a.xh=b.xh where a.xh=?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xh});
		
	} 
	
	/**
	 * 
	 * @描述:保存物品复制
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-5 下午01:33:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmfzList
	 * @param currXn
	 * @param currXq
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveData(List<HashMap<String, String>> wpList,String currXn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		String xmdm=null;
		sql.append("insert into xg_xszz_axcsxmb ");
		sql.append("(xn,xmlb,xmmc,xmxxjs,sqkg,sqkssj,sqjssj,shkg,shkssj,shjssj,splc,jbsz,tjsz,xmdm)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		List<String[]> params = new ArrayList<String[]>();
		String[] param = null;
		for (HashMap<String, String> map : wpList) {
			xmdm=xgxt.utils.String.StringUtils.getGuid();
			param = new String[14];
			param[0] = currXn;
			param[1] = map.get("xmlb");
			param[2] = map.get("xmmc");
			param[3] = map.get("xmxxjs");
			param[4] = map.get("sqkg");
			param[5] = map.get("sqkssj");
			param[6] = map.get("sqjssj");
			param[7] = map.get("shkg");
			param[8] = map.get("shkssj");
			param[9] = map.get("shjssj");
			param[10] = map.get("splc");
			param[11] = map.get("jbsz");
			param[12] = map.get("tjsz");
			param[13] = xmdm;
			params.add(param);
		}
		try {
			dao.runBatch(sql.toString(), params);
			return true;		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;		
		}
	}
	@Override
	protected void setTableInfo() {
		super.setClass(WpszForm.class);
		super.setTableName("xg_xszz_axcsxmb");
		super.setKey("xmdm");
		
	}
	
	
	/**
	 * 
	 * @描述:获取物品名称list
	 * @作者：cq [工号：785]
	 * @日期：2014-12-10 下午02:36:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	
	public List<HashMap<String,String>> getWpmcList(String xn) throws Exception{
		String sql = "select a.*,(select mc from xg_xszz_axcslbb b where a.xmlb=b.dm) lbmc from xg_xszz_axcsxmb a where xn = ?";
		return dao.getListNotOut(sql, new String[]{xn});
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:更新物品条件设置状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-11 下午01:51:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateWpTjZt(String xmdm,String szzt) throws Exception{
		String sql="update xg_xszz_axcsxmb set tjsz=? where xmdm=?";
		return dao.runUpdate(sql, new String[]{szzt,xmdm});
		
	}

}
