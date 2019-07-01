package com.zfsoft.xgxt.jskp.sbsh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszDao;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 纪实考评
 * @类功能描述: 申报审核 
 * @作者： xiaxia[工号:1104]
 * @时间： 2017-7-7 下午02:07:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class JskpXmsbshDao extends SuperDAOImpl<JskpXmsbshForm>{


	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbshForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(JskpXmsbshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		String sfsh = new CsszDao().getSfsh();
		sql.append("select t.* from (");
		sql.append("select t7.xm fzrxm,t8.bmmc,t1.sqid,t1.xh,t1.xmid,t1.hjsj,t1.splcid splc,t1.sbsj, ");
		sql.append("t3.zdf,t3.zxf,t3.zdls,t3.xmlb,t3.zdbm,t3.xmmc ");
		/**不通过立项审核的一套*/
		if("0".equals(sfsh)){
			sql.append(",'自立项' xmdlmc,'zlx' xmdl,");
			sql.append("t4.shzt,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, ");
			sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
			sql.append("decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t6.gwz, ");
			sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
			sql.append("from xg_jskp_xmsbb t1 left join view_xsjbxx t2 on t1.xh = t2.xh  ");
			sql.append(" left join xg_jskp_xmsqb t3 on t1.xmid = t3.sqid ");
			sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
			sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
			sql.append(" left join (select yhm yhm, xm from yhb union all select xh yhm, xm from xsxxb) t7 on t3.fzr = t7.yhm ");
			sql.append(" left join zxbz_xxbmdm t8 on t3.zdbm=t8.bmdm where t5.spyh ='" + user.getUserName() + "' ");
			String shlx = t.getShzt();
			if (!shlx.equals("dsh")) {
				sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
			} else {
				sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
			}
			sql.append(" and t3.sjly = 'NO'");
			sql.append(" ) t where 1=1 ");
			sql.append(" and  rn = 1 ");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}else{
			sql.append(",decode(t3.xmdl, 'gdx', '固定项', '自立项') xmdlmc,t3.xmdl,");
			sql.append("t4.shzt,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, ");
			sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
			sql.append("decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t6.gwz, ");
			sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
			sql.append("from xg_jskp_xmsbb t1 left join view_xsjbxx t2 on t1.xh = t2.xh  ");
			sql.append(" left join xg_jskp_xmjgb t3 on t1.xmid = t3.xmid ");
			sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
			sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
			sql.append(" left join yhb t7 on t3.fzr = t7.yhm ");
			sql.append(" left join zxbz_xxbmdm t8 on t3.zdbm=t8.bmdm where t5.spyh ='" + user.getUserName() + "' ");
			String shlx = t.getShzt();
			if (!shlx.equals("dsh")) {
				sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
			} else {
				sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
			}
			sql.append(" and t3.sjly <> 'NO'");
			sql.append(" ) t where 1=1 ");
			sql.append(" and  rn = 1 ");
			sql.append(searchTj);
		}
		return getPageList(t, sql.toString(), inputV);
		
	}
	/**
	 * 
	 * @描述:加载审核信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 下午02:28:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSbshInfo(JskpXmsbshForm t) {
		StringBuilder sql = new StringBuilder();
		String sfsh = new CsszDao().getSfsh();
		/**自立项目不审核，参数设置为否时*/
		if("0".equals(sfsh)){
			sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, t3.xmmc,t4.xmlbmc");
			sql.append(",'自立项' xmdlmc,");
			sql.append(" t3.zdf,t3.zxf,t5.bmmc zddwmc,t6.xm fzrxm,t3.fzrlxfs,decode(t1.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
			sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', t1.shzt) shztmc,");
			sql.append(" case when length(t3.sjlrr) = '10' then '学生自主申请' when length(t3.sjlrr) <> '10' then  '此数据为'||t9.bmmc||','||t9.xm||'('||'职工号：'||t9.yhm||')'||'录入' else t3.sjlrr end sjlyfs");
			sql.append(" from xg_jskp_xmsbb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
			sql.append(" left join xg_jskp_xmsqb t3 on t1.xmid = t3.sqid ");
			sql.append(" left join xg_jskp_xmlbb t4 on t3.xmlb=t4.xmlbdm left join zxbz_xxbmdm t5 on t3.zdbm=t5.bmdm");
			sql.append(" left join (select yhm yhm, xm from yhb union all select xh yhm, xm from xsxxb)t6 on t3.fzr = t6.yhm ");
			sql.append(" left join (select t7.yhm,t7.xm,t7.szbm,t8.bmmc from yhb t7 left join zxbz_xxbmdm t8 on t7.szbm = t8.bmdm) t9 on t3.sjlrr = t9.yhm ");
			sql.append(" where t1.sqid = ? ");
		}else{
			sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj, t3.xmmc,t4.xmlbmc");
			sql.append(",decode(t3.xmdl,'gdx','固定项','自立项') xmdlmc,");
			sql.append(" t3.zdf,t3.zxf,t5.bmmc zddwmc,t6.xm fzrxm,t3.fzrlxfs,decode(t1.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
			sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
			sql.append(" t1.shzt) shztmc from xg_jskp_xmsbb t1 ");
			sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
			sql.append(" left join xg_jskp_xmjgb t3 on t1.xmid = t3.xmid ");
			sql.append(" left join xg_jskp_xmlbb t4 on t3.xmlb=t4.xmlbdm left join zxbz_xxbmdm t5 on t3.zdbm=t5.bmdm");
			sql.append(" left join yhb t6 on t3.fzr = t6.yhm ");
			sql.append(" where t1.sqid = ? ");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
	}
	/**
	 * 
	 * @描述:更新申请记录
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-20 下午06:57:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param tzhxmdm
	 * @param shzt
	 * @param dqxmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSqjl(String ywid, String shzt,String yxgs) throws Exception{
		String sql = "update xg_jskp_xmsbb set shzt=? ,yxgs=? where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,yxgs,ywid});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(JskpXmsbshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_jskp_xmsbb");
		
	}
	
	/**
	 * 
	 * @描述:根据ID查询当下有多少审批岗位
	 * @作者：cq [工号：785]
	 * @日期：2017-8-21 下午04:26:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getShxhForId(String id){
		
		StringBuffer sql = new StringBuffer();
		String[] ids = id.split(",");
		List<String> params = new ArrayList<String>();
		
		sql.append("select xh,count(1)count from ( ");
		sql.append("select decode(t3.xh,'1','first','','emp','second')xh from xg_jskp_xmsbb t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.sqid = t2.ywid and shr is null ");
		sql.append("left join xg_xtwh_spbz t3 on t2.gwid = t3.spgw and t2.lcid = t3.splc where t1.sqid in ( ");
		for (int i = 0; i < ids.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append(" ? ");
			params.add(ids[i]);
			
		}
		sql.append(")) group by xh");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:根据id获取前一次分数
	 * @作者：cq [工号：785]
	 * @日期：2017-8-22 下午04:14:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBeforeMark(String[] ids){
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from ( ");
		sql.append("select sqid,t3.xh,t2.shr,t2.shsj,lag(t2.zd3,1) over(partition by sqid order by sqid,shsj) sjfs ");
		sql.append("from xg_jskp_xmsbb t1 ");
		sql.append("left join xg_xtwh_shztb t2 on t1.sqid = t2.ywid ");
		sql.append("left join xg_xtwh_spbz t3 on t2.gwid = t3.spgw and t2.lcid = t3.splc ");
		sql.append("where sqid in (");
		
		for (int i = 0; i < ids.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append(" ? ");
			params.add(ids[i]);
			
		}
		
		sql.append(") ) where shr is null and xh >1 ");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	/**
	 * @描述: 根据申请id取审核状态表中的最新一级 的分数（zd3）
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2018-1-17 上午11:03:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getLevelXxBySqid(JskpXmsbshForm t)throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from xg_xtwh_shztb where shsj is not null order by shsj desc) ");
		sql.append("where ywid = ? and rownum = 1 ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
}
