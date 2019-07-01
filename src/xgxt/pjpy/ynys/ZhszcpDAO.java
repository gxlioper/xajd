
package xgxt.pjpy.ynys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class ZhszcpDAO extends PjpyYnysDAO {

	public List<String> values = new ArrayList<String>();//±£´æ²éÑ¯Ìõ¼þÖµ
	
	public List<String[]> getZhszcpResult(ZhszcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn pk, rownum,xh,xm,xn,bjmc,sxzzddszf,kxwhszf,sxlxszf," +
				"sjlxcxf,zhszcpzf,xxsh from view_ynys_zhszcp where 1=1 ";
		String[] opList = new String[] { "pk", "rownum","xh", "xm", "xn", "bjmc",
				"sxzzddszf", "kxwhszf", "sxlxszf", "sjlxcxf", "zhszcpzf","xxsh" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public StringBuffer getWhereSql(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
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
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		return whereSql;
	}
	
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.delete("ynys_zhszcpb", "xh||xn",
					whichpk, request);
			if (!bFlag) {//É¾³ýÊ§°Üºó¼ÇÂ¼É¾³ýÊ§°ÜµÄÐÐºÅ
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	public boolean saveZhszcp(ZhszcpModel model, HttpServletRequest request) throws Exception {
		StandardOperation.delete("ynys_zhszcpb", "xh||xn", model.getXh() + model.getXn(), request);
		return StandardOperation.insert("ynys_zhszcpb",
				new String[] { "xh", "xn", "sxzzddszf", "kxwhszf", "sxlxszf",
						"sjlxcxf", "zhszcpzf" }, new String[] { model.getXh(),
						model.getXn(), model.getSxzzddszf(),
						model.getKxwhszf(), model.getSxlxszf(),
						model.getSjlxcxf(), model.getZhszcpzf() }, request);
	}
	
	public HashMap<String, String> viewZhszcp(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao.getMapNotOut("select xh,xn,xm,xb,nj,xymc,zymc,bjmc,sxzzddszf,kxwhszf,sxlxszf," +
				"sjlxcxf,zhszcpzf from view_ynys_zhszcp where xh||xn = ?", new String[]{pkValue});
	}
	
	public boolean updateZhszcp(String pkValue, ZhszcpModel model,
			HttpServletRequest request) throws Exception {
		if (StringUtils.isEqual(pkValue, model.getXh() + model.getXn())) {
			return StandardOperation.update("ynys_zhszcpb", new String[] {
					"sxzzddszf", "kxwhszf", "sxlxszf", "sjlxcxf", "zhszcpzf" },
					new String[] { model.getSxzzddszf(), model.getKxwhszf(),
							model.getSxlxszf(), model.getSjlxcxf(),
							model.getZhszcpzf() }, "xh||xn", pkValue, request);
		} else {
			StandardOperation.delete("ynys_zhszcpb", "xh||xn", pkValue, request);
			StandardOperation.delete("ynys_zhszcpb", "xh||xn", model.getXh() + model.getXn(), request);
			return StandardOperation.insert("ynys_zhszcpb",
					new String[] { "xh", "xn", "sxzzddszf", "kxwhszf", "sxlxszf",
							"sjlxcxf", "zhszcpzf" }, new String[] { model.getXh(),
							model.getXn(), model.getSxzzddszf(),
							model.getKxwhszf(), model.getSxlxszf(),
							model.getSjlxcxf(), model.getZhszcpzf() }, request);
		}
		
	} 
	
	public String zhszcpShres(String[] keys, String sJg,
			HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.update("ynys_zhszcpb",
					new String[] { "xxsh" }, new String[] { sJg },
					"xh||xn", whichpk, request);
			if (!bFlag) {//ÉóºËÊ§°Üºó¼ÇÂ¼ÉóºËÊ§°ÜµÄÐÐºÅ
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
}
