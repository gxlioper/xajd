package xgxt.wjcf.zgkd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class WjcfZgkdDAO {

	private DAO dao = DAO.getInstance();
	
	public static WjcfZgkdDAO mydao = new WjcfZgkdDAO();
	
	public static WjcfZgkdDAO getInstance(){
		return mydao;
	}
	
	private List<String> values = null;
	
	public List<HashMap<String, String>> getGzjyTitle() {
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc","cfsj", "cflb", "sj"};
		String[] cnList = new String[]{"pk", "行号", "学年", "年度", "学号", "姓名", "班级", "处分时间", "处分类别", "谈话时间"};
		return dao.arrayToList(enList, cnList);
	}
	
	private StringBuffer getWhereSql(WjcfZgkdModel model) {
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
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(model.getNd());
		}
		if (!StringUtils.isNull(model.getCflb())) {
			whereSql.append(" and cflb = ?");
			values.add(model.getCflb());
		}
		return whereSql;
	}
	
	public List<String[]> getGzjyResult(WjcfZgkdModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||sj pk,rownum,xn,nd,xh,xm,bjmc,cfsj,cflbmc,sj from view_wjcf_zgkd_gzjy where 1=1";
		String[] opList = new String[] { "pk", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "cfsj", "cflbmc", "sj" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public boolean gzjyAdd(WjcfZgkdModel model, HttpServletRequest request)
			throws Exception {
		HashMap<String, String> cfMap = dao
				.getMapNotOut(
						"select xh,xn,nd,cflb,cfyy,cfsj,cfwh from wjcfb where xh||xn||nd||sbsj=?",
						new String[] { model.getPkValue() });
		return StandardOperation.insert("wjcf_zgkd_gzjyb", new String[] { "xh",
				"xn", "nd", "cflb", "cfyy", "cfsj", "cfwh", "sj", "dd", "thjg",
				"thxj" }, new String[] { model.getXh(), cfMap.get("xn"),
				cfMap.get("nd"), cfMap.get("cflb"), cfMap.get("cfyy"),
				cfMap.get("cfsj"), cfMap.get("cfwh"), model.getSj(),
				DealString.toGBK(model.getDd()),
				DealString.toGBK(model.getThjg()),
				DealString.toGBK(model.getThxj()) }, request);
	}
	
	public boolean gzjyUpdate(WjcfZgkdModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.update("wjcf_zgkd_gzjyb", new String[] { "sj",
				"dd", "thjg", "thxj" }, new String[] { model.getSj(),
				DealString.toGBK(model.getDd()),
				DealString.toGBK(model.getThjg()),
				DealString.toGBK(model.getThxj()) }, "xh||sj", model
				.getPkValue(), request);
	}
	
	public HashMap<String, String> gzjyView(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,cflbmc,cfyymc,cfsj,cfwh,xn,nd,sj,dd,thjg,thxj from view_wjcf_zgkd_gzjy where xh||sj=?",
						new String[] { pkValue });
	}
	
	public String gzjyDelete(String[] keys) throws Exception {
		String rs = "";
		int[] res = new int[keys.length];
		String[] sql = new String[keys.length];
		for (int i=0;i<keys.length;i++) {
			sql[i] = "delete from wjcf_zgkd_gzjyb where xh||sj='" + keys[i] + "'";
		}
		res = dao.runBatch(sql);
		for (int i=0;i<res.length;i++) {
			if (res[i]<0) {
				rs += (i+1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}
	
	public List<HashMap<String, String>> getStuwjcfInfo() {
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc","cfsj", "cfwh","cflb"};
		String[] cnList = new String[]{"pk", "行号", "学年", "年度", "学号", "姓名", "班级", "处分时间", "处分文号", "处分类别"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> getStuwjcfResult(WjcfZgkdModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||nd||sbsj pk,rownum,xn,nd,xh,xm,bjmc,cfsj,cfwh,cflbmc from view_wjcf where cfsj is not null and cfwh is not null ";
		String[] opList = new String[] { "pk", "rownum", "xn", "nd", "xh",
				"xm", "bjmc", "cfsj", "cfwh", "cflbmc" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public HashMap<String, String> getStuwjcfByPk(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,cflbmc,cfyymc,cfsj,cfwh,xn,nd from view_wjcf where xh||xn||nd||sbsj=?",
						new String[] { pkValue });
	}
}
