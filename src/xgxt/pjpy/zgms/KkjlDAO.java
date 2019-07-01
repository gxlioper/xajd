package xgxt.pjpy.zgms;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class KkjlDAO {

	DAO dao = DAO.getInstance();

	ArrayList<String> values = null;

	/**
	 * 查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> title() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "xn", "xq", "kksj" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "年级", "班级",
				"学年", "学期", "旷课时间" };
		return dao.arrayToList(enList, cnList);
	}

	/**
	 * 查询结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> result(ZgmsModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "xn", "xq", "kksj" };
		String sql = "select kid pk,rownum,xh,xm,nj,bjmc,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,kksj from view_pjpy_xskqb a where 1=1 ";
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}

	/**
	 * 查询条件值
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(ZgmsModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		return whereSql;
	}

	/**
	 * 保存
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean save(ZgmsModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.insert("pjpy_xskqb", new String[] { "xh",
				"xn", "xq", "kksj", "kkdd", "kkjl" }, new String[] {
				model.getXh(), model.getXn(), model.getXq(), model.getKksj(),
				DealString.toGBK(model.getKkdd()),
				DealString.toGBK(model.getKkjl()) }, request);
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean update(ZgmsModel model, HttpServletRequest request,
			String pkValue) throws Exception {
		return StandardOperation.update("pjpy_xskqb", new String[] { "xh",
				"xn", "xq", "kksj", "kkdd", "kkjl" }, new String[] {
				model.getXh(), model.getXn(), model.getXq(), model.getKksj(),
				DealString.toGBK(model.getKkdd()),
				DealString.toGBK(model.getKkjl()) }, "kid", pkValue, request);
	}

	/**
	 * 删除
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delete(String[] keys, HttpServletRequest request)
			throws Exception {
		String res = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = keys[i];
			boolean bFlag = StandardOperation.delete("pjpy_xskqb", "kid", pk,
					request);
			if (!bFlag) {
				res += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(res) ? "" : res
				.substring(0, res.length() - 1);
	}

	public HashMap<String, String> getStuinfo(String xh) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
						new String[] { xh });
	}

	/**
	 * 旷课详细信息
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKkjlinfo(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,kksj,kkdd,kkjl from view_pjpy_xskqb where kid=?",
						new String[] { pkValue });
	}

	public List<HashMap<String, String>> cjTitle() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "xn", "xq", "kcmc","zpcj2", "bkcj2", "cxcj2" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "年级", "班级",
				"学年", "学期","课程", "成绩", "补考成绩", "重修成绩" };
		return dao.arrayToList(enList, cnList);
	}

	/**
	 * 查询条件值
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(ZgmsModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and b.bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and a.xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and b.xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and a.xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and a.xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and b.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and b.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and b.nj = ?");
			values.add(model.getNj());
		}
		return whereSql;
	}

	/**
	 * 成绩查询记录数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cjResultNum(ZgmsModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opList = new String[] { "pk", "r", "xh", "xm", "nj",
				"bjmc", "xn", "xq", "zpcj2", "bkcj2", "cxcj2" };
		String sql = "select a.*,rownum r from (select a.XN||a.XQ||a.XH||a.KCMC||a.XF||a.KCLX pk,a.xh,b.xm,b.nj,b.bjmc,xn,xq,zpcj2,bkcj2,cxcj2,b.bjdm,b.zydm,b.xydm from cjb a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ";
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 成绩查询记录数
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cjResult(ZgmsModel model,
			PjpyZgmsActionForm dataSearchForm) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] opList = new String[] { "pk", "r", "xh", "xm", "nj",
				"bjmc", "xn", "xq", "kcmc","zpcj2", "bkcj2", "cxcj2" };
		String sql = "select * from (select a.*,rownum r from (select a.XN||a.XQ||a.XH||a.KCMC||a.XF||a.KCLX pk,a.xh,b.xm,b.nj,b.bjmc,xn,xq,zpcj2,bkcj2,cxcj2,a.kcmc,b.bjdm,b.zydm,b.xydm from cjb a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ";
		String sqls = sql + whereSql.toString() + ") where r<=" + (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) + " and r> "+dataSearchForm.getPages().getStart();
		return dao.rsToVator(sqls, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);//数据查询加分页
	}

	public HashMap<String, String> cjView(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,(select b.xm from view_xsjbxx b where a.xh=b.xh) xm,(select b.nj from view_xsjbxx b where a.xh=b.xh) nj,(select b.bjmc from view_xsjbxx b where a.xh=b.xh) bjmc,xn,xq,zpcj2,bkcj2,cxcj2,(select b.xymc from view_xsjbxx b where a.xh=b.xh) xymc,(select b.zymc from view_xsjbxx b where a.xh=b.xh) zymc,kcmc,kclx,bz from cjb a where XN||XQ||XH||KCMC||XF||KCLX=?",
						new String[] { DealString.toGBK(pkValue) });
	}

	public List<HashMap<String, String>> tydbTitle() throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "xn", "xq", "tydb" };
		String[] cnList = new String[] { "pk", "行号", "学号", "姓名", "年级", "班级",
				"学年", "学期", "体育达标" };
		return dao.arrayToList(enList, cnList);
	}

	public List<String[]> tydbResult(ZgmsModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select tid pk,rownum,xh,xm,nj,bjmc,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,tydb from view_pjpy_tydbqkb a where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh", "xm", "nj",
				"bjmc", "xn", "xq", "tydb" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}

	public List<HashMap<String, String>> tydbList() throws Exception {
		String[] tydbmc = dao.getArray("select tydbmc cn from tydbdmb",
				new String[] {}, "cn");
		return dao.arrayToList(tydbmc, tydbmc);
	}

	public HashMap<String, String> viewTydbinfo(String pkValue)
			throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,tydb,bz,xn,xq from view_pjpy_tydbqkb where tid=?",
						new String[] { pkValue });
	}

	/**
	 * 体育达标删除
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteTydb(String[] keys, HttpServletRequest request)
			throws Exception {
		String res = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = keys[i];
			boolean bFlag = StandardOperation.delete("pjpy_tydbqkb", "tid", pk,
					request);
			if (!bFlag) {
				res += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(res) ? "" : res
				.substring(0, res.length() - 1);
	}

	/**
	 * 体育达标修改
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateTydb(String pkValue, ZgmsModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_tydbqkb", new String[] { "xh",
				"xn", "xq", "tydb", "bz" }, new String[] { model.getXh(),
				model.getXn(), model.getXq(),
				DealString.toGBK(model.getTydb()),
				DealString.toGBK(model.getBz()) }, "tid", pkValue, request);
	}
	
	public boolean cjtb(String xn) throws Exception{
		String sql = "{call ZGMS_CJTB(?)}";
		return dao.runProcuder(sql, new String[] {xn});
	}
	
	public HashMap<String, String> ryPrint(String pkValue) {
		return dao
				.getMapNotOut(
						"select a.*,b.zzmmmc,b.mzmc,b.syd,b.jtdz,b.pycc,b.csrq,b.lxdh,(select b.drzw " +
						"from xsrychxxb b where a.xh=b.xh) drzw,(select b.zysj from xsrychxxb " +
						"b where a.xh=b.xh) zysj,(select b.brjl from xsrychxxb b where a.xh=b.xh)" +
						" brjl,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk from view_xsrychb" +
						" a left join view_xsxxb b on a.xh=b.xh where a.xh||a.xn||a.xq||a.rychdm=?",
						new String[] { pkValue });
	}
	
	public HashMap<String, String> rychPrint(String pkValue) {
		return dao
				.getMapNotOut(
						"select a.*,b.zzmmmc,b.mzmc,b.pycc,b.syd,b.jtdz,b.csrq,b.lxdh,(select b.drzw " +
						"from xsrychxxb b where a.xh=b.xh) drzw,(select b.zysj from xsrychxxb " +
						"b where a.xh=b.xh) zysj,(select b.brjl from xsrychxxb b where a.xh=b.xh)" +
						" brjl,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk from view_xsrychb" +
						" a left join view_xsxxb b on a.xh=b.xh where a.xn||a.nd||a.xh||a.rychdm=?",
						new String[] { pkValue });
	}
}
