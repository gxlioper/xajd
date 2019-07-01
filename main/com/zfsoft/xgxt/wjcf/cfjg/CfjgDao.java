/**
 * @部门:学工产品事业部
 * @日期：2014-8-5 上午11:40:55 
 */
package com.zfsoft.xgxt.wjcf.cfjg;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-8-5 上午11:40:55
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class CfjgDao extends SuperDAOImpl<CfjgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CfjgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 处分查询
	 */
	@Override
	public List<HashMap<String, String>> getPageList(CfjgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r , a.* from xg_view_wjcf_wjcfb a ");
		sql.append("where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	

	/**
	 * 新增保存
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean saveCfsj(CfjgForm form) throws Exception {
		boolean flag = false;
		String sql = "insert into xg_wjcf_wjcfb(filepath,filepath2,filepath3,filepath4,xh,xn,xq,cflbmc,cfyymc,cflbdm,cfyydm,wjsj,sbb,sbsj," +
				"wjssjg,bz,cfwh,cfsj,cfdqsj,fjmc,cfyj,cflsh,nd) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String[] input = new String[] { form.getFilepath(), form.getFilepath2(),form.getFilepath3(),form.getFilepath4(),form.getXh(), form.getXn(), form.getXq(), form.getCflbmc(),
				form.getCfyymc(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(),
				form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),form.getCfdqsj(),
				form.getFjmc(),form.getCfyj(),form.getCflsh(),form.getNd()};
		if("12389".equals(Base.xxdm)){
			sql = "insert into xg_wjcf_wjcfb(filepath,xh,xn,xq,cflbmc,cfyymc,cflbdm,cfyydm,wjsj,sbb,sbsj," +
					"wjssjg,bz,cfwh,cfsj,cfdqsj,fjmc,cfyj,cflsh,nd,sdlx) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			input = new String[] { form.getFilepath(), form.getXh(), form.getXn(), form.getXq(), form.getCflbmc(),
					form.getCfyymc(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(),
					form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),form.getCfdqsj(),
					form.getFjmc(),form.getCfyj(),form.getCflsh(),form.getNd(),form.getSdlx() };
		}
		DAO dao = DAO.getInstance();
		flag = dao.runUpdate(sql,input);

		return flag;
	}
	/**
	 * 获取流水号
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLsh(CfjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select cflsh from xg_wjcf_wjcfb")
		.append(" where xn=? and xq=? and cflsh is not null order by cflsh desc");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXn(),model.getXq()});
	}
	
	public String getLsh2(CfjgForm model){
		StringBuffer sql= new StringBuffer();
		sql.append(" select (case when length(cflsh)<2 then '0'|| cflsh else cflsh end)  cflsh from (");
		sql.append("select to_char((to_number(count(1))+1)) cflsh from xg_wjcf_wjcfb")
		.append(" where xn=? and xq=?  ");
		if(null!=model.getCfid()){
			sql.append(" and cfid!='"+model.getCfid()+"'");
		}
		sql.append(")");
		return dao.getOneRs(sql.toString(), new String[]{model.getXn(),model.getXq()},"cflsh");
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
	 *             String 返回类型
	 */
	public String cflbmc(String cflbdm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = " select cflbmc from xg_wjcf_cflbdmb where cflbdm=?";
		return dao.getOneRs(sql, new String[] { cflbdm }, "cflbmc");
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
	 *             String 返回类型
	 */
	public String cfyymc(String cfyydm) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = " select cfyymc from xg_wjcf_cfyydmb where cfyydm=?";
		return dao.getOneRs(sql, new String[] { cfyydm }, "cfyymc");
	}

	/**
	 * 查看处分信息
	 * 
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfsjwhCk(String cfid) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.filepath,a.filepath2,a.filepath3,a.filepath4,a.ssfilepath,a.CFID,a.nd,A.XH,a.XN,a.XQ,a.cfyj,a.cflsh,");
		if("12389".equals(Base.xxdm)){
			sql.append("a.sdlx,");
		}
		sql.append("(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,cflbdm,cflbmc,");
		sql.append(" a.ocflbmc, a.CFYYMC,a.CFSJ,a.cfdqsj,a.cfwh,a.wjsj,a.sbb,a.SBSJ,a.WJSSJG,a.bz,a.sfsc,a.sssj,a.sswh,");
		sql.append(" (case when a.ssjg='wcycf' then '维持原处分' when a.ssjg= 'ggcf' then '更改处分' ");
		sql.append(" when a.ssjg='cxcf' then '撤销处分' else a.ssjg end) ssjg,");
		sql.append(" a.cfggw,a.ssyj,a.jcwh,a.jcsj,a.jcyj,a.fjmc,a.fj,a.zzwh,a.zzsj,a.zzyj,a.xm,a.xb,a.nj,a.xydm,a.zydm,");
		sql.append(" a.bjdm,a.xymc,a.zymc,a.bjmc,a.zzmmmc,a.mzmc,a.sfzh,a.ssfjmc,a.ssfj,a.sqly,a.jgmc,v.csrq,");
		sql.append(" v.ssbh, (select a.sqly from xg_wjcf_wjcfjcsqb a where a.cfid = ?) jcly,");
		sql.append(" (select c.qxmc from dmk_qx c  where c.qxdm = substr(v.jg, 0, 2) || '0000') ||");
		sql.append(" (select d.qxmc from dmk_qx d where d.qxdm = substr(v.jg, 0, 4) || '00' and v.jg <> substr(v.jg, 0, 2) || '0000') ||");
		sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = v.jg and v.jg <> substr(v.jg, 0, 2) || '0000' and v.jg <> substr(v.jg, 0, 4) || '00') jgmc,");
		sql.append(" (select count(*) sqjg from xg_wjcf_wjcfjcsqb where sqjg in ('shz') and cfid=?) jclc,");
		sql.append(" (select count(*) ssjg from xg_wjcf_wjcfssb where ssjg in ('shz') and cfid=?) sslc, ");
		sql.append(" (select xm from fdyxxb t where t.zgh=a.sbb) sbbxm ");
		sql.append("from xg_view_wjcf_wjcfb a left join view_xsbfxx v on a.xh = v.XH where cfid = ?");
		HashMap<String, String> result = dao.getMapNotOut(sql.toString(), new String[] { cfid, cfid, cfid, cfid });
		return result;
	}

	/**
	 * 删除处分信息
	 * 
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
	 * 
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfSbSsJcSc(String[] cfid) throws Exception {
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String[] str = new String[8];
		if (null != cfid && cfid.length > 0) {
			str[0] = "delete from xg_wjcf_wjcfsbb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[0] += ",";
				}
				str[0] += "'" + cfid[i] + "'";
			}
			str[0] += " ) ";
			str[1] = "delete from xg_wjcf_wjcfjcshb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[1] += ",";
				}
				str[1] += "'" + cfid[i] + "'";
			}
			str[1] += " ) ";
			str[2] = "delete from xg_wjcf_wjcfjcsqb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[2] += ",";
				}
				str[2] += "'" + cfid[i] + "'";
			}
			str[2] += " ) ";
			str[3] = "delete from xg_wjcf_wjcfjcshb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[3] += ",";
				}
				str[3] += "'" + cfid[i] + "'";
			}
			str[3] += " ) ";
			str[4] = "delete from xg_wjcf_wjcfssb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[4] += ",";
				}
				str[4] += "'" + cfid[i] + "'";

			}
			str[4] += " ) ";
			str[5] = "delete from xg_wjcf_wjcfssshb where cfid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[5] += ",";
				}
				str[5] += "'" + cfid[i] + "'";
			}
			str[5] += " ) ";

			str[6] = "delete from xg_xtwh_wdgz where gnmklx='处分申诉' and ywid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[6] += ",";
				}
				str[6] += "'" + cfid[i] + "'";
			}
			str[6] += " ) ";

			str[7] = "delete from xg_xtwh_wdgz where gnmklx='处分解除' and ywid in ( ";
			for (int i = 0; i < cfid.length; i++) {
				if (i != 0) {
					str[7] += ",";
				}
				str[7] += "'" + cfid[i] + "'";
			}
			str[7] += " ) ";

		}

		flag = dao.saveArrDate(str);
		return flag;
	}

	/**
	 * 修改处分信息
	 * 
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhXg(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();

		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc().substring(form.getFjmc().lastIndexOf("\\") + 1, form.getFjmc().length()) : "";
		if("12389".equals(Base.xxdm)){
			if (StringUtils.isNotNull(fjmc)) {
				return dao.updateBlob("update xg_wjcf_wjcfb set cfdqsj = ?, fj = ?,xn = ?,xq = ?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,fjmc = ?,cfyj =?,nd=?,sdlx=? where cfid=?",
						new String[] {form.getCfdqsj(), form.getXn(), form.getXq(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(),
								form.getCfsj(), fjmc,form.getCfyj(),form.getNd(),form.getSdlx(), form.getCfid() }, form.getFj().getInputStream().available(), form.getFj().getInputStream());
			} else {
				return dao.runUpdate("update xg_wjcf_wjcfb set  cfdqsj = ?, filepath=?,xn = ?,xq = ?,cflbdm=?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,cfyj = ?,nd=?,sdlx=? where cfid=?", new String[] {
						form.getCfdqsj(), form.getFilepath(), form.getXn(), form.getXq(), form.getCflbdm(),form.getCflbmc(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),
						form.getCfyj(),form.getNd(),form.getSdlx(),form.getCfid() });
			}
		}else{
			if (StringUtils.isNotNull(fjmc)) {
				return dao.updateBlob("update xg_wjcf_wjcfb set cfdqsj = ?, fj = ?,xn = ?,xq = ?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,fjmc = ?,cfyj =?,nd=? where cfid=?",
						new String[] {form.getCfdqsj(), form.getXn(), form.getXq(), form.getCflbdm(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(),
								form.getCfsj(), fjmc,form.getCfyj(),form.getNd(), form.getCfid() }, form.getFj().getInputStream().available(), form.getFj().getInputStream());
			} else {
				return dao.runUpdate("update xg_wjcf_wjcfb set  cfdqsj = ?, filepath=?, filepath2=?, filepath3=?, filepath4=?,xn = ?,xq = ?,cflbdm=?,cflbmc = ?,cfyymc = ?,wjsj = ?," + "sbb = ?,sbsj = ?,wjssjg = ?,bz = ?,cfwh = ?,cfsj = ?,cfyj = ?,nd=? where cfid=?", new String[] {
						form.getCfdqsj(), form.getFilepath(), form.getFilepath2(), form.getFilepath3(), form.getFilepath4(), form.getXn(), form.getXq(), form.getCflbdm(),form.getCflbmc(), form.getCfyydm(), form.getWjsj(), form.getSbb(), form.getSbsj(), form.getWjssjg(), form.getBz(), form.getCfwh(), form.getCfsj(),
						form.getCfyj(),form.getNd(),form.getCfid() });
			}
		}

	}

	/**
	 * 保存处分申诉信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfssjgBc(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate("update xg_wjcf_wjcfb set sssj=?,sswh=?,ssjg=?,cfggw=?,ssyj=? where cfid = ?", new String[] { form.getSssj(), form.getSswh(), form.getSsjg(), form.getCflbmc(),
				form.getSsyj(), form.getCfid() });
	}

	/**
	 * 保存处分解除信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcjgBc(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate("update xg_wjcf_wjcfb set jcsj=?,jcwh=?,jcyj=? where cfid = ?", new String[] { form.getJcsj(), form.getJcwh(), form.getJcyj(), form.getCfid() });
	}

	/**
	 * 保存处分终止信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfzzjgBc(CfjgForm form) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdate("update xg_wjcf_wjcfb set zzsj=?,zzwh=?,zzyj=? where cfid = ?", new String[] { form.getZzsj(), form.getZzwh(), form.getZzyj(), form.getCfid() });
	}

	/**
	 * 查询附件信息
	 * 
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
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public HashMap<String, String> getZwAndZzmm(String xh) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select c.ldmc,c.qsh,b.zwmc,case when a.zzmm='01'or a.zzmm='02'or a.zzmm='03'then '是'else '否' end as sfdty from view_xsxxb a ");
		sql.append("left join VIEW_NEW_DC_SZDW_XSDWWH b on a.xh=b.xh ");
		sql.append("left join xg_view_gygl_new_xszsgl c on a.xh = c.xh ");
		sql.append("where a.xh=?");
		return dao.getMap(sql.toString(), new String[] { xh }, new String[] { "zwmc", "sfdty", "ldmc", "qsh" });
	}

	/**
	 * 通过学号查询违纪处分列表
	 * 
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuWjcfList(String xh) {
		DAO dao = DAO.getInstance();
		return dao.rsToVator("select xn,(select xqmc from xqdzb b where " + "a.xq=b.xqdm) xq,(case when cfggw is not null then cfggw else cflbmc end) cflbmc,"
				+ "cfyymc,cfsj,cfwh,sssj,sswh,ssjg,jcsj,jcwh," + "decode(zzwh,null,decode(jcwh, null,decode(ssjg, 'wsh', '未审核', 'shz', '审核中', 'cxcf', '处分已解除', '处分成立'), '处分已解除') ,'处分已终止')as jg "
				+ "from xg_wjcf_wjcfb a where xh=? order by xn desc,xq", new String[] { xh }, new String[] { "xn", "xq", "cflbmc", "cfyymc", "cfsj", "cfwh", "jg" });
	}

	/**
	 * 数据维护自定义导出
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCfjgExportList(CfjgForm model, User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		// User user = model.getUser();
		String[] colList = new String[] { "cfid", "xn", "xq", "xh", "xm", "cflbmc", "cfyymc", "fwsj", "fwwh", "fwjg", "zzwh", "zzsj", "zzyj" };
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

		sql.append("select rownum r , a.* ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm ");
		}
		sql.append(" from xg_view_wjcf_wjcfb a");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on a.bjdm = bzr.bjdm");
		}
		sql.append(query + qxSql);
		List<HashMap<String, String>> list = (List<HashMap<String, String>>) CommonQueryDAO.commonQueryforExportList(sql.toString(), "", inputV, colList, model);
		return list;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根

	}
	
	/**
	 * 通过学号查询当前学期违纪情况
	 * 
	 * @param xh
	 * @return
	 */
	public String getWjxxByXhXnXq(String xh,String xn,String xq) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,wm_concat(cflbmc) cfmc from xg_view_wjcf_wjcfb where ");
		sql.append("xh=? and xn=? and xq=? group by xh");
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "cfmc");
		
	}

	/**
	 * @描述:走审核流程的处分结果修改
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-16 上午11:25:31
	 * @param myForm
	 */
	public boolean cfsjshlcXg(CfjgForm form) throws  Exception {
		DAO dao = DAO.getInstance();
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc().substring(form.getFjmc().lastIndexOf("\\") + 1, form.getFjmc().length()) : "";
		if (StringUtils.isNotNull(fjmc)) {
			return dao.updateBlob("update xg_wjcf_wjcfb set cfdqsj = ?, fj = ?,cfwh = ?,cfsj = ?,fjmc = ? where cfid=?",
					new String[] {form.getCfdqsj(), form.getCfwh(),form.getCfsj(),form.getCfid() }, form.getFj().getInputStream().available(), form.getFj().getInputStream());
		} else {
			return dao.runUpdate("update xg_wjcf_wjcfb set cfdqsj = ?, filepath=?,cfwh = ?,cfsj = ? where cfid=?", new String[] {
					form.getCfdqsj(),form.getFilepath(), form.getCfwh(), form.getCfsj(),
					form.getCfid() });
		}
	}

	/** 
	 * @描述:青岛酒店管理职业技术学院生成默认处分文号：年份+4位顺序号，获取当前规则最大顺序号+1后的值
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月27日 下午2:48:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param regexp
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getDefaultCfwhFor13011(String regexp) {
		String sql = "SELECT nvl(max(to_number(substr(cfwh,-4))),-1)+1 cfwhseed FROM ((SELECT cfwh from xg_wjcf_wjcfb) "+
					 "UNION (SELECT kzzd5 cfwh FROM xg_wjcf_wjcfsbb)) WHERE  regexp_like(cfwh,?)";
		return dao.getOneRs(sql, new String[]{regexp}, "cfwhseed");
	}

	/** 
	 * @描述:根据处分id数组，查询处分结果信息（列表）
	 * （乌海职业技术学院：处分决定书下载）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月15日 上午10:55:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgList(String[] cfids) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT x.xh,x.xm,x.xb,x.bjmc,w.cfid,w.cflbdm,w.cflbmc,w.cfwh,w.wjssjg,w.cfyymc,");
		sql.append("substr(w.cfyymc,instr(w.cfyymc,'第'),instr(w.cfyymc,'条')) djt FROM xg_wjcf_wjcfb w ");
		sql.append("LEFT JOIN view_xsbfxx x ON w.xh = x.xh ");
		sql.append("WHERE w.cfid in (");
		
		for(int i=0;i<cfids.length;i++){
			sql.append("?");
			if(i!=cfids.length-1){
				sql.append(",");
			}
		}
		
		sql.append(")");
		return dao.getListNotOut(sql.toString(), cfids);
	}

	/** 
	 * @描述:根据处分id数组，查询处分结果信息，包含班主任、政治面貌等信息（列表）
	 * （乌海职业技术学院：处分审批表下载）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月16日 下午2:38:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgMoreList(String[] cfids) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT x.xh,x.xm,x.xb,x.bjmc,x.bjdm,x.mzmc,x.zzmmmc,x.xymc,w.cflbmc,w.wjsj,w.cfid,w.bz,w.cfyj,");
		sql.append("(SELECT replace(wm_concat(xm),';','、') bzr FROM fdyxxb WHERE zgh in (SELECT zgh FROM bzrbbb WHERE bjdm = x.bjdm)) bzr ");
		sql.append("FROM xg_wjcf_wjcfb w LEFT JOIN view_xsbfxx x ON w.xh = x.xh ");
		sql.append("WHERE w.cfid in (");
		
		for(int i=0;i<cfids.length;i++){
			sql.append("?");
			if(i!=cfids.length-1){
				sql.append(",");
			}
		}
		
		sql.append(")");
		return dao.getListNotOut(sql.toString(), cfids);
	}
	
	/**
	 * @描述: 根据cfid取记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-2 上午11:53:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCfxxByCfid(String cfid){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_wjcf_wjcfb where cfid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{cfid});
	}
	
	/**
	 * 
	 * @描述: 验证是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 下午07:42:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(CfjgForm form){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_wjcf_wjcfb where xn = ? and xq = ? and xh = ? and wjsj = ? and cflbdm = ?");
		paraList.add(form.getXn());
		paraList.add(form.getXq());
		paraList.add(form.getXh());
		paraList.add(form.getWjsj());
		paraList.add(form.getCflbdm());
		if(StringUtils.isNotNull(form.getCfid())){
			sql.append(" and cfid != ?");
			paraList.add(form.getCfid());
		}
		String rs = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(rs) ? true :false;
	}
}
