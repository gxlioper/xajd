package xsgzgl.wjcf.cfsjwh;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: ltt
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午03:45:48
 * </p>
 */
public class WjcfCfsjwhDao extends CommDAO {

	/**
	 * 处分查询
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCfjgList(WjcfCfsjwhActionForm model) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		User user = model.getUser();
		String[] colList = new String[] { "cfid", "xn", "xqmc", "xh", "xm","bjmc", "cflbmc", "cfyymc", "fwsj", "fwjg","sjly","jcwh" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r , a.* from xg_view_wjcf_wjcfb a");
		sql.append(query + qxSql);
		// ====================SQL拼装 end================================
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQuery(sql.toString(), "", inputV, colList, model);
		return list;
	}
	
	/**
	 * 新增保存
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean saveCfsj(WjcfCfsjwhActionForm form, InputStream instream) throws Exception{
		boolean flag = false;
		String sql = "insert into xg_wjcf_wjcfb(fj,xh,xn,xq,cflbmc,cfyymc,cflbdm,cfyydm,wjsj,sbb,sbsj,wjssjg,bz,cfwh,cfsj,fjmc) " +
				     "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		DAO dao = DAO.getInstance();
		
		flag = dao.updateBlob(sql, new String[]{form.getXh(), form.getXn(),form.getXq(),form.getCflbmc(),
				form.getCfyymc(),form.getCflbdm(),form.getCfyydm(),form.getWjsj(), form.getSbb(), form.getSbsj(),
				form.getWjssjg(), form.getBz(), form.getCfwh(),
				form.getCfsj(),form.getFjmc()}, instream.available(), instream);
		
		return flag;
	}
	/**
	 * 
	 * @描述: 获取处分类别名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-22 下午05:02:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cflbdm
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String cflbmc(String cflbdm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = " select cflbmc from xg_wjcf_cflbdmb where cflbdm=?";
		return dao.getOneRs(sql, new String[]{cflbdm}, "cflbmc");
	}
	/**
	 * 
	 * @描述: 获取对应处分原因名称
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-22 下午05:02:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfyydm
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String cfyymc(String cfyydm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = " select cfyymc from xg_wjcf_cfyydmb where cfyydm=?";
		return dao.getOneRs(sql, new String[]{cfyydm}, "cfyymc");
	}
	/**
	 * 查看处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfsjwhCk(String cfid) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select a.CFID,A.XH,a.XN,a.XQ,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(case when cfggw is not null then cfggw else cflbmc end) cflbmc,a.CFYYMC,a.CFSJ,a.cfwh,a.wjsj,a.sbb,a.SBSJ,a.WJSSJG,a.bz,a.sfsc,a.sssj,a.sswh," +
		" (case when a.ssjg='wcycf' then '维持原处分' when a.ssjg= 'ggcf' then '更改处分' when a.ssjg='cxcf' then '撤销处分' else a.ssjg end) ssjg," +
		" a.cfggw,a.ssyj,a.jcwh,a.jcsj,a.jcyj,a.fjmc,a.fj,a.zzwh,a.zzsj,a.zzyj,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,a.zzmmmc,a.mzmc,a.sfzh,a.ssfjmc,a.ssfj,a.sqly,a.jgmc,v.csrq,"+
        " v.ssbh, (select a.sqly from xg_wjcf_wjcfjcsqb a where a.cfid = ?) jcly," +
		" (select c.qxmc from dmk_qx c  where c.qxdm = substr(v.jg, 0, 2) || '0000') ||" +
		" (select d.qxmc from dmk_qx d where d.qxdm = substr(v.jg, 0, 4) || '00' and v.jg <> substr(v.jg, 0, 2) || '0000') ||" +
		" (select e.qxmc from dmk_qx e where e.qxdm = v.jg and v.jg <> substr(v.jg, 0, 2) || '0000' and v.jg <> substr(v.jg, 0, 4) || '00') jgmc," +
		" (select count(*) sqjg from xg_wjcf_wjcfjcsqb where sqjg in ('shz') and cfid=?) jclc," +
		" (select count(*) ssjg from xg_wjcf_wjcfssb where ssjg in ('shz') and cfid=?) sslc " +
		"from xg_view_wjcf_wjcfb a left join view_xsbfxx v on a.xh = v.XH where cfid = ?";
		HashMap<String, String> result = dao.getMapNotOut(sql, new String[]{cfid,cfid,cfid,cfid});
		return result;
	}
	
	/**
	 * 删除处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhSc(List<String[]> cfid) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.checkBatchResult(dao.runBatch("delete from xg_wjcf_wjcfb where cfid = ?", cfid));
	}
	
	/**
	 * 删除处分信息时连带删除处分上报，处分上报审核，申述，申述审核，解除，解除审核表数据
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfSbSsJcSc(String[] cfid) throws Exception{
		DAO dao = DAO.getInstance();
		boolean flag=false;
		String[] str=new String[8];
		if(null!=cfid&&cfid.length>0){
			str[0] ="delete from xg_wjcf_wjcfsbb where cfid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[0]+=",";
				}
				str[0]+="'"+cfid[i]+"'";
			}
			str[0]+=" ) ";
			str[1] ="delete from xg_wjcf_wjcfjcshb where cfid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[1]+=",";
				}
				str[1]+="'"+cfid[i]+"'";
			}
			str[1]+=" ) ";
			str[2] ="delete from xg_wjcf_wjcfjcsqb where cfid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[2]+=",";
				}
				str[2]+="'"+cfid[i]+"'";
			}
			str[2]+=" ) ";
			str[3] ="delete from xg_wjcf_wjcfjcshb where cfid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[3]+=",";
				}
				str[3]+="'"+cfid[i]+"'";
			}
			str[3]+=" ) ";
			str[4] ="delete from xg_wjcf_wjcfssb where cfid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[4]+=",";
				}
				str[4]+="'"+cfid[i]+"'";
				
			}
			str[4]+=" ) ";
			str[5] ="delete from xg_wjcf_wjcfssshb where cfid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[5]+=",";
				}
				str[5]+="'"+cfid[i]+"'";
			}
			str[5]+=" ) ";
			
			str[6] ="delete from xg_xtwh_wdgz where gnmklx='处分申诉' and ywid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[6]+=",";
				}
				str[6]+="'"+cfid[i]+"'";
			}
			str[6]+=" ) ";
			
			str[7] ="delete from xg_xtwh_wdgz where gnmklx='处分解除' and ywid in ( ";	
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[7]+=",";
				}
				str[7]+="'"+cfid[i]+"'";
			}
			str[7]+=" ) ";
			
			
		}
		
		flag=dao.saveArrDate(str);
		return flag;
	}
	
	/**
	 * 修改处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhXg(WjcfCfsjwhActionForm form) throws Exception {
		DAO dao = DAO.getInstance();
		
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc()
				.substring(form.getFjmc().lastIndexOf("\\")+1,
						form.getFjmc().length()) : "";
				
		if (StringUtils.isNotNull(fjmc)) {
			return dao.updateBlob("update xg_wjcf_wjcfb set fj = ?,xn = ?,xq = ?,cflbmc = ?,cfyymc = ?,wjsj = ?," +
					"sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,fjmc = ? where cfid=?", 
					new String[]{form.getXn(),form.getXq(),form.getCflbdm(),form.getCfyydm(),
									form.getWjsj(), form.getSbb(),
									form.getSbsj(), form.getWjssjg(),
									form.getBz(), form.getCfwh(),
									form.getCfsj(), fjmc,
									form.getCfid() }, form.getFj()
									.getInputStream().available(), form.getFj()
									.getInputStream());
		} else {
			return dao.runUpdate("update xg_wjcf_wjcfb set xn = ?,xq = ?,cflbmc = ?,cfyymc = ?,wjsj = ?," +
					"sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ? where cfid=?", 
					new String[]{form.getXn(),form.getXq(),form.getCflbdm(),
					form.getCfyydm(),form.getWjsj(), form.getSbb(), form.getSbsj(),
					form.getWjssjg(), form.getBz(), form.getCfwh(),
					form.getCfsj(),form.getCfid()});
		}
	} 
	
	/**
	 * 保存处分申诉信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfssjgBc(WjcfCfsjwhActionForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao
				.runUpdate(
						"update xg_wjcf_wjcfb set sssj=?,sswh=?,ssjg=?,cfggw=?,ssyj=? where cfid = ?",
						new String[] { form.getSssj(), form.getSswh(),
								form.getSsjg(), form.getCflbmc(),form.getSsyj(), form.getCfid() });
	}
	
	/**
	 * 保存处分解除信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcjgBc(WjcfCfsjwhActionForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao
				.runUpdate(
						"update xg_wjcf_wjcfb set jcsj=?,jcwh=?,jcyj=? where cfid = ?",
						new String[] { form.getJcsj(), form.getJcwh(),form.getJcyj(), form.getCfid() });
	}
	
	/**
	 * 保存处分终止信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfzzjgBc(WjcfCfsjwhActionForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao
				.runUpdate(
						"update xg_wjcf_wjcfb set zzsj=?,zzwh=?,zzyj=? where cfid = ?",
						new String[] { form.getZzsj(), form.getZzwh(),form.getZzyj(), form.getCfid() });
	}
	
	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public Blob fjCx(String sql, String[] inputList, String column) {
		DAO dao = DAO.getInstance();
		return dao.getOneLong(sql, inputList, column);
	}
	/**
	 * 
	 * @描述:查询是否党团员和职务
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-1 上午11:10:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZwAndZzmm(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.ldmc,c.qsh,b.zwmc,case when a.zzmm='01'or a.zzmm='02'or a.zzmm='03'then '是'else '否' end as sfdty from view_xsxxb a ");
		sql.append("left join VIEW_NEW_DC_SZDW_XSDWWH b on a.xh=b.xh ");
		sql.append("left join xg_view_gygl_new_xszsgl c on a.xh = c.xh ");
		sql.append("where a.xh=?");
		return dao.getMap(sql.toString(), new String[]{xh}, new String[]{"zwmc","sfdty","ldmc","qsh"});
	}
	/**
	 * 通过学号查询违纪处分列表
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuWjcfList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select xn,(select xqmc from xqdzb b where "
								+ "a.xq=b.xqdm) xq,(case when cfggw is not null then cfggw else cflbmc end) cflbmc," +
										"cfyymc,cfsj,cfwh,sssj,sswh,ssjg,jcsj,jcwh," +
										"decode(zzwh,null,decode(jcwh, null,decode(ssjg, 'wsh', '未审核', 'shz', '审核中', 'cxcf', '处分已解除', '处分成立'), '处分已解除') ,'处分已终止')as jg "
								+ "from xg_wjcf_wjcfb a where xh=? order by xn desc,xq",
						new String[] { xh }, new String[] { "xn", "xq", "cflbmc", "cfyymc", "cfsj",
								"cfwh", "jg" });
	}
	
	/**
	 * 数据维护自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCfjgExportList(WjcfCfsjwhActionForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		//User user = model.getUser();
		String[] colList = new String[] { "cfid", "xn", "xq", "xh", "xm", "cflbmc", "cfyymc", "fwsj", "fwwh", "fwjg" ,"zzwh","zzsj","zzyj"};
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		String qxSql = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r , a.* from xg_view_wjcf_wjcfb a");
		sql.append(query + qxSql);
		List<HashMap<String,String>> list = (List<HashMap<String,String>>) CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList, model);
		return list;
	}
	
	
	/**
	 * 
	 * @描述:TODO(查询处分决定书信息)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-14 下午01:46:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> cfjdsxx(String cfid) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select trunc(sysdate) dysj,a.xh,a.xm,a.xb,a.zzmmmc,a.csrq,a.xymc,a.zymc,a.bjmc,b.wjsj,b.wjssjg,b.cflbmc,b.cfsj,b.cfyj,a.sfzh,a.jgmc " );
		if("10080".equals(Base.xxdm)){
			sql.append(",a.mzmc");
		}
		sql.append(" from view_xsxxb a,xg_wjcf_wjcfb b where a.xh=b.xh and cfid=?");
		if("10080".equals(Base.xxdm)){
			return dao.getMap(sql.toString(), new String[]{cfid}, new String[]{"zymc","dysj","zzmmmc","xh","cflbmc","cfsj","csrq","xm","xb","xymc","bjmc","wjsj","wjssjg","cfyj","sfzh","jgmc","mzmc"});
		}else{
			return dao.getMap(sql.toString(), new String[]{cfid}, new String[]{"zymc","dysj","zzmmmc","xh","cflbmc","cfsj","csrq","xm","xb","xymc","bjmc","wjsj","wjssjg","cfyj","sfzh","jgmc"});
		}
	}

	
}
