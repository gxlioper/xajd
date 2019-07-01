package xgxt.wjcf.nblg;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class WjcfNblgDAO {

	DAO dao = DAO.getInstance();

	public static WjcfNblgDAO mydao = new WjcfNblgDAO();

	public static WjcfNblgDAO getInstance() {
		return mydao;
	}

	private ArrayList<String> values = null;

	public HashMap<String, String> printInfo(String xh) throws Exception {
		return dao.getMapNotOut(
				"select xh,xm,bjmc,xb,jg,csrq from view_xsxxb where xh=?",
				new String[] { xh });
	}

	public HashMap<String, String> printwjInfo(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select a.xh,a.xm,a.bjmc,a.xymc,a.zymc,a.nj,a.xb,a.jg,a.csrq,b.bz,b.xyclyj," +
						"b.cflbmc from view_wjcf b left join view_xsxxb a on a.xh=b.xh where b.xh||b.xn||b.sbsj=?",
						new String[] { pkValue});
	}

	public HashMap<String, String> printWjcf(String pk) {
		return dao
				.getMapNotOut(
						"select a.*,(select b.csrq from xsxxb b where a.xh=b.xh) csrq,(select b.jg from xsxxb b where a.xh=b.xh) jg from view_wjcf a where a.xh||a.cflb||a.cfyy||a.xn||a.nd=?",
						new String[] { pk });
	}

	public HashMap<String, String> printwjInfo1(String pkValue)
			throws Exception {
		return dao
				.getMapNotOut(
						"select a.xh,a.cfsj,a.cflbmc,b.xm,b.bjmc,b.xb,b.jg,b.csrq,a.yq bz from view_wjcf_xsssxx a left join view_xsxxb b on a.xh=b.xh where a.xh||a.sssj=? ",
						new String[] { pkValue });
	}

	public HashMap<String, String> printwjInfo2(String pkValue)
			throws Exception {
		return dao
				.getMapNotOut(
						"select a.xh,a.cfsj,a.cflbmc,b.xm,b.bjmc,b.xb,b.jg,b.csrq,a.yq bz from view_wjcf_xsssxx a left join view_xsxxb b on a.xh=b.xh where a.xh||a.cfwh||a.cfsj||a.cflbmc||a.cfyymc=? ",
						new String[] { DealString.toGBK(pkValue) });
	}

	public HashMap<String, String> printJcjkq(String pk) {
		return dao
				.getMapNotOut(
						"select a.*,(select b.csrq from xsxxb b where a.xh=b.xh) csrq,(select b.jg from xsxxb b where a.xh=b.xh) jg from view_wjcf_cxcf a where xh||cfwh||cfsj||cxsqsj=?",
						new String[] { pk });
	}

	public List<HashMap<String, String>> titie() throws Exception {
		String[] enList = new String[] { "pk", "color", "xh", "xm", "xymc",
				"zymc", "bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "jd" };
		String[] cnList = new String[] { "pk", "color", "学号", "姓名", Base.YXPZXY_KEY, "专业",
				"班级", "处分文号", "处分类别", "处分原因", "申诉时间", "委员会决定" };
		return dao.arrayToList(enList, cnList);
	}

	public StringBuffer getWhereSql(WjcfNblgModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm = ?");
			values.add(DealString.toGBK(model.getXm()));
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		return whereSql;
	}

	public List<String[]> result(WjcfNblgModel model) throws Exception {
		String sql = "select xh||sssj pk,(case jd when '未决定' then '#CCCCCC' "
				+ "else '' end) color,xh,xm,xymc,zymc,bjmc,cfwh,cflbmc,cfyymc,sssj,"
				+ "jd from view_wjcf_xsssxx where 1=1";
		String[] opList = new String[] { "pk", "color", "xh", "xm", "xymc",
				"zymc", "bjmc", "cfwh", "cflbmc", "cfyymc", "sssj", "jd" };
		StringBuffer whereSql = getWhereSql(model);
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}

	public List<HashMap<String, String>> getJdlist() throws Exception {
		String[] cnList = new String[] { "未决定", "解除查看期", "解除处分", "更改处分",
				"维持原处分" };
		return dao.arrayToList(cnList, cnList);
	}

	public HashMap<String, String> getJdInfo(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,cfwh,cfsj,xn,nd,xq,"
								+ "cflbmc,cfyymc,lxdh,dz,yb,sssj,jd,jdsj,jdwh,yq,jdly,ggcflbdm "
								+ "from view_wjcf_xsssxx where xh||sssj=?",
						new String[] { pkValue });
	}

	public boolean updateJd(WjcfNblgModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		boolean bFlag = StandardOperation.update("wjcf_xsssb", new String[] {
				"jd", "jdsj", "jdwh", "jdly", "ggcflbdm" }, new String[] {
				DealString.toGBK(model.getJd()), model.getJdsj(),
				DealString.toGBK(model.getJdwh()),
				DealString.toGBK(model.getJdly()), model.getGgcflbdm() },
				"xh||sssj", pkValue, request);
		if (bFlag) {// 更改决定后修改WJCFB里面的申诉结果信息
			dao
					.runUpdate(
							"update wjcfb set cxwh=?,cxsj=?,ssjg=?,ggcflbdm=?  where xh||cfsj||cfwh=(select xh||cfsj||cfwh from wjcf_xsssb where xh||sssj=?)",
							new String[] { DealString.toGBK(model.getJdwh()),
									model.getJdsj(),
									DealString.toGBK(model.getJd()),
									model.getGgcflbdm(), pkValue });
		}
		return bFlag;
	}

	public List getFileList(String pkValue) {
		DAO dao = DAO.getInstance();
		pkValue = "%" + pkValue + "%";
		String sql = "select length(xh||cfwh||cfsj)len, cfwh,cflbmc,cfyymc,sssj,wjsclj from wjcf_xsssb where xh||cfwh||cfsj like ? and wjsclj is not null";
		List rs = dao.getList(sql, new String[] { pkValue }, new String[] {
				"len", "cfwh", "cflbmc", "cfyymc", "sssj", "wjsclj" });
		return rs;
	}

	public HashMap<String, String> lxckPrint(String pkValue) {

		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xsbx,cfsj,cfwh,"
								+ "cflbmc,jg,zzmmmc,JTWJSY,bjpyyj,fdyjdyj,xyyj,xxyj,xndshyj from view_wjcf_zjlg_lxck"
								+ " where xh||cfxn||cfnd||cfsbsj=?",
						new String[] { pkValue });
	}

	public String getJgList(List<String> jgList) {
		DAO dao = DAO.getInstance();
		StringBuffer jg = new StringBuffer();
		for (String string : jgList) {
			String sql = "select qxdm,qxmc from dmk_qx where qxdm=?";
			String rs = dao.getOneRs(sql, new String[] { string }, "qxmc");
			jg.append(rs.toString());
		}
		return jg.toString();
	}
	
	public HashMap<String, String> getCxcfInfo(String pkValue){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*,xm,xb,zzmmmc,csrq,mzmc,xymc,zymc,nj,bjmc from (select a.*,");
		sql.append(" to_char(to_date(cxsqsj, 'yyyymmdd'), 'yyyy') || '年' ||  ");
		sql.append(" to_char(to_date(cxsqsj, 'yyyymmdd'), 'mm') ||'月' ||  ");
		sql.append(" to_char(to_date(cxsqsj, 'yyyymmdd'), 'dd') || '日' cxsqtime ");
		sql.append(" from wjcf_cxcfb a where xh||cfwh||cfsj= ? ) a left join ");
		sql.append(" view_xsxxb b on a.xh=b.xh ");
		return dao.getMap(sql.toString(), new String[] { pkValue },
				new String[] {"xh","xm","xb","cflbmc","cfsj","cfyymc","zzmmmc","csrq","mzmc","bz","xyyj","xxyj","nj","xymc","zymc","bjmc","cxsqtime"});
	}
}
