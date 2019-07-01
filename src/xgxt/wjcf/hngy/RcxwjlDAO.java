
package xgxt.wjcf.hngy;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class RcxwjlDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = new ArrayList<String>();
	StringBuffer tmpSql = new StringBuffer();
	public List<HashMap<String, String>> title() throws Exception {
		String[] enList = new String[]{"pk", "rownum", "xn", "xq", "XH","xm", "nj", "bjmc", "wjsj"};
		String[] cnList = new String[]{"pk", "行号", "学年", "学期", "学号","姓名", "年级", "班级名称", "违纪时间"};
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 获取WHERE语句公用方法
	 * 
	 * @param shgcxxshqryModel
	 * @return
	 */
	public StringBuffer getWhereSql(RcxwjlModel model) {
		tmpSql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append("and xh = ?");
			tmpSql.append(" and xh = '"+model.getXh()+"'");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append("and xm like ?");
			tmpSql.append(" and xm = '"+model.getXm()+"'");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append("and nj = ?");
			tmpSql.append(" and nj = '"+model.getNj()+"'");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append("and xq = ?");
			tmpSql.append(" and xq = '"+model.getXq()+"'");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append("and bjdm = ?");
			values.add(model.getBjdm());
			tmpSql.append(" and bjdm = '"+model.getBjdm()+"'");
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append("and zydm = ?");
			values.add(model.getZydm());
			tmpSql.append(" and zydm = '"+model.getZydm()+"'");
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append("and xydm = ?");
			values.add(model.getXydm());
			tmpSql.append(" and xydm = '"+model.getXydm()+"'");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append("and xn = ?");
			values.add(model.getXn());
			tmpSql.append(" and xn = '"+model.getXn()+"'");
		}
		if (!StringUtils.isNull(model.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(model.getNd());
			tmpSql.append(" and nd = '"+model.getNd()+"'");
		}
		if (!StringUtils.isNull(model.getWjsj())) {
			whereSql.append(" and wjsj = ?");
			values.add(model.getWjsj());
		}
		return whereSql;
	}
	
	public List<String[]> result(RcxwjlModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||nd||xq||wjsj pk,rownum,xn,xh,(select b.xqmc from xqdzb b where b.xqdm=a.xq) xq,xm,nj,bjmc,wjsj from view_wjcf_rcxwjlb a where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xn", "xq", "xh","xm", "nj", "bjmc", "wjsj"};
		String cs = model.getCs();
		String tmp = "";
		if (!StringUtils.isNull(cs)) {
			tmp = " and exists (select 1 from (select xh from view_wjcf_rcxwjlb where 1=1 "+tmpSql.toString()+" group by xh having count(xh)="+cs+") b where a.xh=b.xh)";
		}
		return dao.rsToVator(sql + whereSql.toString() + tmp, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public HashMap<String, String> stuinfo(String xh) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?", new String[]{xh});
	}
	
	public boolean save(RcxwjlModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.insert("wjcf_rcxwjlb", new String[] { "xh",
				"xn", "xq", "nd", "wjsj", "wjqk", "bz" }, new String[] {
				model.getXh(), model.getXn(), model.getXq(), model.getNd(),
				model.getWjsj(), DealString.toGBK(model.getWjqk()),
				DealString.toGBK(model.getBz()) },
				request);
	}
	
	public HashMap<String, String> view(String pkValue) throws Exception {
		return dao.getMapNotOut("select xh,xn,nd,xq,wjsj,wjqk,bz,xm,xb,nj,xymc,zymc,bjmc from view_wjcf_rcxwjlb where xh||xn||nd||xq||wjsj = ?", new String[]{pkValue});
	}
	
	public boolean update(String pkValue, RcxwjlModel model, HttpServletRequest request) throws Exception {
		if (!StringUtils.isNull(pkValue)
				&& pkValue.equalsIgnoreCase(model.getXh() + model.getXn()
						+ model.getNd() + model.getXq() + model.getWjsj())) {
			return StandardOperation.update("wjcf_rcxwjlb", new String[] {
					"xn", "nd", "xq", "wjsj", "wjqk", "bz" }, new String[] {
					model.getXn(), model.getNd(), model.getXq(),
					model.getWjsj(), DealString.toGBK(model.getWjqk()), DealString.toGBK(model.getBz()) }, "xh||xn||nd||xq||wjsj",
					pkValue, request);
			} else {
				dao.runUpdate("delete from wjcf_rcxwjlb where xh||xn||nd||xq||wjsj = ?", new String[]{pkValue});
				return StandardOperation.insert("wjcf_rcxwjlb", new String[] { "xh",
						"xn", "xq", "nd", "wjsj", "wjqk", "bz" }, new String[] {
						model.getXh(), model.getXn(), model.getXq(), model.getNd(),
						model.getWjsj(), DealString.toGBK(model.getWjqk()),
						DealString.toGBK(model.getBz()) },
						request);
			}
		}
	
	public String delete(String[] keys, HttpServletRequest request) throws Exception {
		String sDel = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("wjcf_rcxwjlb", "xh||xn||nd||xq||wjsj", keys[i], request);
			if (!bFlag) {//记录删除失败的数据行
				sDel += (i+1);
				sDel += ",";
			}
		}
		return StringUtils.isNull(sDel) ? "" : sDel.substring(0, sDel.length() - 1);
	}
}
