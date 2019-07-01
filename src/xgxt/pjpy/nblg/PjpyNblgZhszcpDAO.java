package xgxt.pjpy.nblg;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyNblgZhszcpDAO {

	DAO dao = DAO.getInstance();
	
	public static PjpyNblgZhszcpDAO mydao = new PjpyNblgZhszcpDAO();

	public static PjpyNblgZhszcpDAO getInstance() {
		return mydao;
	}
	
	ArrayList<String> values = null;
	
	/**
	 * 查询表头
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> title(String type) throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "xn",
				"bjmc", "cj", "bjpm" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "学年", "班级",
				"德育评议成绩", "班级排名" };
		if ("1".equalsIgnoreCase(type)) {
			cnList = new String[] { "pk", "行号", "学号", "姓名", "学年", "班级",
					"行为记实成绩", "班级排名" };
		} else if ("2".equalsIgnoreCase(type)) {
			cnList = new String[] { "pk", "行号", "学号", "姓名", "学年", "班级",
					"生活园区记实成绩", "班级排名" };
		}
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql(PjpyNblgModel model) throws Exception {
		values = new ArrayList<String>();
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
			whereSql.append(" and xm = ?");
			values.add(DealString.toGBK(model.getXm()));
		}
		return whereSql;
	}
	
	/**
	 * 查询结果
	 * 
	 * @param type
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> result(String type, PjpyNblgModel model)
			throws Exception {
		String sql = "select xh||xn pk, rownum,xh,xm,xn,bjmc,bjdm,dycj,(dense_rank() over (partition by bjdm order by to_number(nvl(dycj,0)) desc nulls last)) bjpm from view_pjpy_ddpycjb where 1=1";
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "xn",
				"bjmc", "dycj", "bjpm"};
		StringBuffer whereSql = getWhereSql(model);
		if ("1".equalsIgnoreCase(type)) {
			sql = "select xh||xn pk, rownum,xh,xm,xn,bjmc,bjdm,xwcj,(dense_rank() over (partition by bjdm order by to_number(nvl(xwcj,0)) desc nulls last)) bjpm from view_pjpy_xwjscjb where 1=1";
			opList = new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
					"xwcj", "bjpm" };
		} else if ("2".equalsIgnoreCase(type)) {
			sql = "select xh||xn pk, rownum,xh,xm,xn,bjmc,bjdm,shcj,(dense_rank() over (partition by bjdm order by to_number(nvl(shcj,0)) desc nulls last)) bjpm from view_pjpy_shyqjsb where 1=1";
			opList = new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
					"shcj", "bjpm"};
		}
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public boolean ddpycjSave(PjpyNblgModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.insert("pjpy_ddpycjb", new String[] { "xh",
				"xn", "dycj", "bz" }, new String[] { model.getXh(),
				model.getXn(), model.getDycj(), DealString.toGBK(model.getBz()) }, request);
	}
	
	public boolean xwjscjSave(PjpyNblgModel model, HttpServletRequest request)
	throws Exception {
		return StandardOperation.insert("pjpy_xwjscjb", new String[] { "xh",
		"xn", "xwcj", "bz" }, new String[] { model.getXh(),
		model.getXn(), model.getDycj(), DealString.toGBK(model.getBz()) }, request);
	}
	
	public boolean shyqjsbSave(PjpyNblgModel model, HttpServletRequest request)
	throws Exception {
		return StandardOperation.insert("pjpy_shyqjsb", new String[] { "xh",
		"xn", "shcj", "bz" }, new String[] { model.getXh(),
		model.getXn(), model.getDycj(), DealString.toGBK(model.getBz()) }, request);
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
						new String[] { xh });
	}
	
	public HashMap<String, String> view(String pkValue, String params) throws Exception {
		return dao.getMapNotOut("select * from view_"+params+" where xh||xn=?", new String[]{pkValue});
	}
	
	public HashMap<String, String> ddpyView(String pkValue, String params) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,bz,dycj from view_"+params+" where xh||xn=?", new String[]{pkValue});
	}
	
	public HashMap<String, String> xwcjView(String pkValue, String params) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,bz,xwcj dycj from view_"+params+" where xh||xn=?", new String[]{pkValue});
	}
	
	public HashMap<String, String> shyqView(String pkValue, String params) throws Exception {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,bz,shcj dycj from view_"+params+" where xh||xn=?", new String[]{pkValue});
	}
	
	public boolean ddpyUpdate(String pkValue, PjpyNblgModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_ddpycjb", new String[] { "xn",
				"dycj", "bz" }, new String[] { model.getXn(), model.getDycj(),
				DealString.toGBK(model.getBz()) }, "xh||xn", pkValue, request);
	}
	
	public boolean xwcjUpdate(String pkValue, PjpyNblgModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_xwjscjb", new String[] { "xn",
				"xwcj", "bz" }, new String[] { model.getXn(), model.getDycj(),
				DealString.toGBK(model.getBz()) }, "xh||xn", pkValue, request);
	}
	
	public boolean shyqUpdate(String pkValue, PjpyNblgModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_shyqjsb", new String[] { "xn",
				"shcj", "bz" }, new String[] { model.getXn(), model.getDycj(),
				DealString.toGBK(model.getBz()) }, "xh||xn", pkValue, request);
	}
	
	public String delete(String[] keys, String realTable,
			HttpServletRequest request) throws Exception {
		String del = "";// 删除未成功记录数
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// 得到主键
			boolean bFlag = StandardOperation.delete(realTable, "xh||xn",
					whichpk, request);
			if (!bFlag) {// 删除不成功
				del += (i + 1);
				del += ",";
			}
		}
		return StringUtils.isNull(del) ? "" : del
				.substring(0, del.length() - 1);
	}
	
	/**
	 * 综合测评总分自动计算
	 * @param xn
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpAutoCount(String xn, String xydm) throws Exception {
		String sql = "{call NBLG_ZHSZCPCOUNT(?,?)}";
		return dao.runProcuder(sql, new String[] {xn, xydm});
	}
	
	/**
	 * 综合测评查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> zhcpTitle() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "xn",
				"bjmc", "dycj", "xwcj", "shcj", "kcjqf", "zhszcpzf", "bjpm" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "学年", "班级",
				"道德评议", "行为记实", "生活区记实", "课程加权分", "综合测评总分", "综合班级排名" };
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 综合测评查询结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zhcpResult(PjpyNblgModel model) throws Exception {
		String sql = "select xh||xn pk, rownum,xh,xm,xn,bjmc,ddpycj,xwjscj,shqjscj,zhszcpzf,kcjqf,(dense_rank() over (partition by bjdm order by to_number(nvl(zhszcpzf,0)) desc nulls last)) bjpm from view_nblg_zhszcp where 1=1";
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "xn",
				"bjmc", "ddpycj", "xwjscj", "shqjscj", "kcjqf", "zhszcpzf", "bjpm" };
		StringBuffer whereSql = getWhereSql(model);
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 综合素质测评导出数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zhcpExp(PjpyNblgModel model) throws Exception {
		String zydm = dao.getOneRs("select distinct zydm from view_xsjbxx where bjdm=?", new String[]{model.getBjdm()}, "zydm");
		StringBuffer sql = new StringBuffer("select rownum,xh,xm,zhszcpzf,bjpm,dycj,dypm,kcjqf,kcpm," +
				"ddpycj,xwjscj,shqjscj from (select xh,xm,zhszcpzf," +
				"(dense_rank() over (partition by bjdm order by to_number(nvl(zhszcpzf,0)) desc nulls last)) bjpm" +
				",(to_number(nvl(ddpycj,0)) + to_number(nvl(xwjscj,0)) + to_number(nvl(shqjscj,0))) dycj," +
				"(dense_rank() over (partition by bjdm order by (to_number(nvl(ddpycj,0)) + to_number(nvl(xwjscj,0)) " +
				"+ to_number(nvl(shqjscj,0))) desc nulls last)) dypm,kcjqf," +
				"(select kcpm from (select xh,(dense_rank() over(partition by zydm order by " +
				"to_number(nvl(kcjqf, 0)) desc nulls last)) kcpm from view_nblg_zhszcp where " +
				"xn = '"+model.getXn()+"' and zydm='"+zydm+"') b where a.xh=b.xh) kcpm,ddpycj,xwjscj," +
				"shqjscj from view_nblg_zhszcp a where 1=1");
		if (!StringUtils.isNull(model.getXn())) {
			sql.append(" and xn='");
			sql.append(model.getXn());
			sql.append("'");
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			sql.append(" and bjdm='");
			sql.append(model.getBjdm());
			sql.append("')");
		}
		String[] opList = new String[]{"rownum", "xh", "xm", "zhszcpzf", "bjpm", "dycj", "dypm", "kcjqf", "kcpm", "ddpycj", "xwjscj", "shqjscj"};
		return dao.rsToVator(sql.toString(), new String[]{}, opList);
	}
	
	public HashMap<String, String> zhcpFzExp(String bjdm) throws Exception {
		return dao
				.getMapNotOut(
						"select xymc,zymc,bjmc,to_char(sysdate,'yyyymmdd') dt from view_xsjbxx where bjdm=? and rownum=1",
						new String[] { bjdm });
	}
}
