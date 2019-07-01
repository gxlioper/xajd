/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述: 学生信息修改
 * @作者： ligl
 * @时间： 2013-11-23 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class XxxgDao extends SuperDAOImpl<XsxxglModel> {

	/**
	 * 普通查询方法
	 */
	public List<HashMap<String, String>> getPageList(XsxxglModel model)
			throws Exception {
		return null;
	}

	/**
	 * 高级查询方法
	 */
	public List<HashMap<String, String>> getPageList(XsxxglModel model,
			User user) throws Exception {
		return null;
	}

	/**
	 * 高级查询方法,审核记录
	 */
	public List<HashMap<String, String>> getWclPageList(XsxxglModel model,
			User user) throws Exception {
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String shgwzByUser = SearchService.getShgwzByUser(user, "a",
				"xydm", "bjdm");

		// query += searchTjByUser;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select b.*,c.*,a.xgsj,e.mc,e.gwz,row_number() over (partition by b.ywid order by b.shsj desc) as rn,  ");
		sb.append(" (e.mc || '[' || decode(b.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.shzt) || ']') shztmc ");
		if("12309".equals(Base.xxdm)){
			sb.append(" ,f.fdy");
		}
		sb.append(" from xg_xsxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx c,xg_xtwh_spgwyh d,xg_xtwh_spgw e ");
		if("12309".equals(Base.xxdm)){
			sb.append(",(select wm_concat(t1.xm) fdy,t.bjdm from fdybjb t left join yhb t1");
			sb.append(" on t.zgh = t1.yhm group by t.bjdm)f");
		}
		sb.append(" where a.xh=c.xh and a.sqid=b.ywid and b.gwid=d.spgw   and e.id=b.gwid ");
		if("12309".equals(Base.xxdm)){
			sb.append(" and c.bjdm = f.bjdm");
		}
		String shzt = model.getShzt();

		if (shzt != null && shzt.equals("tg")) {
			sb.append(" and b.shzt='1' ");
		} else if (shzt != null && !shzt.equals("dsh")) {
			sb.append(" and (b.shzt!='0' and b.shzt!='4')");
		} else {
			sb.append(" and (b.shzt='0' or b.shzt='4')");
		}

		sb.append(" and d.spyh='");
		sb.append(user.getUserName());
		sb.append("' ) a where rn=1 ");
		sb.append(searchTjByUser);
		sb.append(searchTj);
		sb.append(shgwzByUser);
		return getPageList(model, sb.toString(), inputV);
	}

	/**
	 * 高级查询方法,审核结果
	 */
	public List<HashMap<String, String>> getXgjgPageList(XsxxglModel model,
			User user) throws Exception {
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select  c.*,a.sqid,a.xgsj,a.shjg,");
		sb.append("decode(a.shjg,'0','未审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中')  shjgmc,");
		sb.append("b.guid spclid,b.lcid,b.ywid,row_number() over(partition by b.ywid order by b.shsj desc) as rn ");
		sb.append(" from xg_xsxx_xxxgsqb a,xg_xtwh_shztb b,view_xsjbxx  c ");
		sb.append(" where a.xh=c.xh  and a.sqid=b.ywid");
		sb.append(") a where  rn=1 ");
		sb.append(searchTjByUser);
		sb.append(searchTj);
		return getPageList(model, sb.toString(), inputV);
	}

	/**
	 * 
	 * @描述:增加修改申请记录
	 * @作者：ligl
	 * @日期：2013-12-6 上午10:32:13
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean insertXgsq(XgsqModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xsxx_xxxgsqb(sqid,xh,xgsj,shjg) ");
		sql.append(" values(?,?,?,?) ");
		String[] inputValue = { model.getSqid(), model.getXh(),
				model.getXgsj(), model.getShjg() };
		return dao.runUpdate(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:删除原有未进审批流的申请记录
	 * @作者：ligl
	 * @日期：2013-12-17 上午09:57:32
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean deleteXgsq(XgsqModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xsxx_xxxgsqb ");
		sql.append(" where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("' ");
		String[] inputValue = { model.getXh() };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}
	
	public boolean deleteShlc(XgsqModel model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xtwh_shztb ");
		sql.append(" where ywid in(select sqid from xg_xsxx_xxxgsqb where xh=? and shjg='");
		sql.append(Constants.SH_DSH);
		sql.append("') ");
		String[] inputValue = { model.getXh() };
		dao.runDelete(sql.toString(), inputValue);
		return true;
	}


	/**
	 * 
	 * @描述:修改申请状态
	 * @作者：ligl
	 * @日期：2013-12-9 上午09:42:04
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateXgsqZt(XgsqModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_xsxx_xxxgsqb ");
		sb.append(" set shjg=? ");
		sb.append(" where sqid=? ");
		String[] inputValue = { model.getShjg(), model.getSqid() };
		return dao.runUpdate(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:修改申请状态
	 * @作者：ligl
	 * @日期：2013-12-9 上午09:42:04
	 * @修改记录:
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateXgsqZtNotCommit(XgsqModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("update xg_xsxx_xxxgsqb ");
		sb.append(" set shjg=? ");
		sb.append(" where sqid=? ");
		String[] inputValue = { model.getShjg(), model.getSqid() };
		return dao.runUpdateNotCommit(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @描述:通过申请id，得到修改字段及修改后值
	 * @作者：ligl
	 * @日期：2013-12-9 下午01:45:09
	 * @修改记录:
	 * @param sqid
	 * @return
	 * @throws
	 */
	public List<HashMap<String, String>> getXgzdList(String sqid)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select sqid,zd,zdz,xgqz from xg_xsxx_xgzdb ");
		sb.append(" where sqid=? ");
		return dao.getListNotOut(sb.toString(), new String[] { sqid });
	}

	/**
	 * 
	 * @描述:根据学号，获取最新的一条审核中的记录
	 * @作者：ligl
	 * @日期：2013-12-16 下午03:57:35
	 * @修改记录:
	 * @param xh
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataByXh(String xh, String shjg)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select * from xg_xsxx_xxxgsqb  ");
		sb.append("  where  xh=? and shjg=? ");
		sb.append("  order by xgsj desc  ");
		sb.append(")  where rownum=1 ");
		String[] input = { xh, shjg };
		return dao.getMapNotOut(sb.toString(), input);
	}
	/**
	 * 
	 * @描述:获取申请记录
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-17 上午11:39:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param shjg
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getShxxByXh(String xh)
		throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (select a.*,b.shsj,b.shyj from (select * from xg_xsxx_xxxgsqb");
		sb.append(" where xh=? ) a");
		sb.append(" join xg_xtwh_shztb b on a.sqid=b.ywid and b.shsj is not null order by xgsj desc,shsj desc)  ");
		sb.append(" where rownum =1");
		return dao.getMapNotOut(sb.toString(), new String[]{xh});
}
	public HashMap<String, String> getShztByXh(String xh)
	throws Exception {
	StringBuilder sb = new StringBuilder();
	sb.append("select * from (select a.*,b.shsj,b.shyj,b.shzt from (select * from xg_xsxx_xxxgsqb");
	sb.append(" where xh=? and shjg = '5') a");
	sb.append(" join xg_xtwh_shztb b on a.sqid=b.ywid and b.shsj is not null order by xgsj desc,shsj desc)  ");
	sb.append(" where rownum =1");
	return dao.getMapNotOut(sb.toString(), new String[]{xh});
}
	public HashMap<String, String> getDshByXh(String xh) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select * from xg_xsxx_xxxgsqb  ");
		sb.append("  where  xh=? and (shjg='0' or shjg='3') ");
		sb.append("  order by xgsj desc  ");
		sb.append(")  where rownum=1 ");
		String[] input = { xh };
		return dao.getMapNotOut(sb.toString(), input);
	}

	/**
	 * 
	 * @描述:保存修改字段及值
	 * @作者：ligl
	 * @日期：2013-12-9 下午02:03:35
	 * @修改记录:
	 * @param list
	 * @param sqid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean insertXgzd(List<XgzdModel> list, String sqid)
			throws Exception {
		boolean result = false;
		List<String[]> paramList = new ArrayList<String[]>();
		StringBuffer sb = new StringBuffer();
		sb.append("insert into xg_xsxx_xgzdb(sqid,zd,zdz,xgqz) ");
		sb.append(" values(?,?,?,?)");
		for (XgzdModel xgzdModel : list) {
			String[] param = { sqid, xgzdModel.getZd(), xgzdModel.getZdz(),
					xgzdModel.getXgqz() };
			paramList.add(param);
		}
		result = dao.runBatchBoolean(sb.toString(), paramList);
		return result;
	}
	
	/**
	 * 
	 * @描述:通过申请id 获取学号
	 * @作者：ligl
	 * @日期：2013-12-24 上午09:59:20
	 * @修改记录: 
	 * @param sqid
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXhBySqid(String sqid) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select xh ");
		sb.append(" from  xg_xsxx_xxxgsqb ");
		sb.append(" where sqid=?");
		String[] inputValue = {sqid};
		String[] outputValue = {"xh"};
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String xh = null;
		if(oneRs != null && oneRs.length > 0){
			xh = oneRs[0];
		}
		return xh;
	}

	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("");
	}
	
	//当审核状态为退回时，保存草稿或者提交时需要删除xgzdb中的数据
	public boolean delXgzdb(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xsxx_xgzdb where sqid = ?");
		return dao.runUpdate(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @描述:北京中医药个人获奖附件上传查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-26 下午02:55:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrfjscCx(XsxxglModel model,
			User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(" select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.nj,");
		sql.append(" t1.zymc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.bjdm,");
		sql.append(" t.bjdw bjdwmc");
		sql.append(" from xg_xsxx_new_grhjqk t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.xh");
		//sql.append(" left join zxbz_xxbmdm t2");
		//sql.append(" on t.bjdw = t2.bmdm ");
		sql.append(" order by t.hjsj desc,t1.bjmc,t1.xh ");
		sql.append(" ) t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述: 获取学生获奖信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-26 下午04:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXshjXx(String hjid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t.bjdw bjdwmc from xg_xsxx_new_grhjqk t ");
		//sql.append(" left join zxbz_xxbmdm t2");
		//sql.append(" on t.bjdw = t2.bmdm ");
	    sql.append(" where t.hjid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{hjid});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述：北京中医药上传附件更新组gid
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-26 下午05:58:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hjid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateGrhjxxGid(String hjid,String gid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xsxx_new_grhjqk set gid = ? where hjid = ?");
		return dao.runUpdate(sql.toString(),  new String[]{gid,hjid});
	}
}
