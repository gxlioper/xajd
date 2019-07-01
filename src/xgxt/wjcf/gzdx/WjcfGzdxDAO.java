package xgxt.wjcf.gzdx;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class WjcfGzdxDAO {

	private DAO dao = DAO.getInstance();

	/**
	 * 查询违纪处分汇总信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryWjcfxx(WjcfGzdxModel model, String[] colList)
			throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy", "xq", "sfjw", "xxsh" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		model.setXxsh("通过");

		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select a.*,rownum r from (select a.*  from view_wjcf a ";
		if (colList == null) {
			colList = new String[] { "r", "xm", "xymc", "nj", "bjmc", "cflbmc",
					"cfyymc", "cfsj" };
		}
		return CommonQueryDAO.commonQueryNotFy(sql,
						queryObject.getQueryString()
								+ " and not exists (select 1 from wjcf_zjlg_lxckb b " +
										"where a.xh=b.xh and a.xn=b.cfxn and" +
										" a.nd=b.cfnd and a.cfsj=substr(b.cfsbsj,0,8) )) a order by r,cflb,xydm",
						queryObject.getInputList(), colList, model);
	}

	// 查询处分数据的起始学年
	public String queryWjcfMaxMinXn(WjcfGzdxModel model) throws Exception {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "cflb", "cfyy", "xq", "sfjw" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, queryLikeList, model);
		String sql = "select min(xn) minxn,max(xn) maxxn from view_wjcf ";
		String[] xn = dao.getOneRs(sql + queryObject.getQueryString(),
				queryObject.getInputList(), new String[] { "minxn", "maxxn" });
		if (xn != null && xn.length == 2) {
			String qxn = !StringUtils.isNull(xn[0]) && xn[0].length() == 9 ? (xn[0]
					.substring(0, 4) + "年")
					: "";
			String hxn = !StringUtils.isNull(xn[1]) && xn[1].length() == 9 ? (xn[1]
					.substring(5, 9) + "年")
					: "";
			return qxn.equalsIgnoreCase(hxn) ? qxn : (qxn + "~" + hxn);
		}
		return "";
	}

	/**
	 * 判断学生在指定学年是否有受过处分，如果学年参数是空的，那么返回学生历年来的所有的处分信息
	 * 
	 * @param xh
	 * @param xn
	 * @return true 为受过处分，false 为没受过处分
	 */
	public boolean checkStuWjcfxx(String xh, String xn) {
		String sql = "select count(*) num from wjcfb where xh=? and cfwh is not null and cfsj is not null";
		if (!StringUtils.isNull(xn)) {
			sql += " and xn='";
			sql += xn;
			sql += "'";
		}
		String num = dao.getOneRs(sql, new String[] { xh }, "num");
		if (!StringUtils.isNull(num) && !"0".equalsIgnoreCase(num)) {
			return true;
		}
		return false;
	}

	public HashMap<String, String> querySsbprint(String pkValue) {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,cflbmc,cfyymc,yb,dz,lxdh,cfwh,cfsj,xn,xq,nd,sssj,yq from view_wjcf_xsssxx where xh||cfwh||cfsj=? and rownum=1",
						new String[] { pkValue });
	}

	public HashMap<String, String> queryCfbprint(String pkValue) {
		return dao
				.getMapNotOut(
						"select xm,bz,qrsj,xyclyj,xxclyj,xb,fsjname,bjmc,cflbmc,cfyymc,cfsj,cfwh,xh,(select csrq from view_xsxxb b where a.xh=b.xh) csrq,(select jg from view_xsxxb b where a.xh=b.xh) jg from view_wjcf a where xh||xn||sbsj=?",
						new String[] { pkValue });
	}
}
