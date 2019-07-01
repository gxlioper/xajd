package xgxt.pjpy.zjcm;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class ZhszcpDAO extends CommonQueryDAO{

	DAO dao = DAO.getInstance();
	
	public static ZhszcpDAO mydao = new ZhszcpDAO();
	private ArrayList<String> values = new ArrayList<String>();
	
	public static ZhszcpDAO getInstance() {
		return mydao;
	}
	
	/**
	 * 查询表头
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTitleName(String szlx)
			throws Exception {
		String[] enList = new String[] { "pk", "rownum", "xh", "xn", "xq",
				"xm", "bjmc", "cj" };
		String[] cnList = new String[] { "pk", "行号", "学号", "学年", "学期",
				"姓名", "班级名称", "德育基本素质分" };
		if ("0".equalsIgnoreCase(szlx)) {
			cnList = new String[] { "pk", "行号", "学号", "学年", "学期", "姓名",
					"班级名称", "德育基本素质分" };
		} else if ("1".equalsIgnoreCase(szlx)) {
			cnList = new String[] { "pk", "行号", "学号", "学年", "学期", "姓名",
					"班级名称", "智育成绩分" };
		} else if ("2".equalsIgnoreCase(szlx)) {
			cnList = new String[] { "pk", "行号", "学号", "学年", "学期", "姓名",
					"班级名称", "体育成绩分" };
		} else if ("3".equalsIgnoreCase(szlx)) {
			cnList = new String[] { "pk", "行号", "学号", "学年", "学期", "姓名",
					"班级名称", "实践与创新能力分" };
		} else if ("4".equalsIgnoreCase(szlx)) {
			enList = new String[] { "pk", "rownum", "xh", "xn", "xq",
					"xm", "bjmc", "cj", "bjpm"};
			cnList = new String[] { "pk", "行号", "学号", "学年", "学期", "姓名",
					"班级名称", "综合素质分", "班级排名"};
		}
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql(ZhszcpModel model) throws Exception {
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
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
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
	 * @param model
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getResult(ZhszcpModel model, String tableName,
			PjpyZjcmActionForm dataSearchForm, String isFdy, String userName) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||xq pk,rownum r,xh,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,bjmc,cj from "
				+ tableName + " a where 1=1 ";
		String[] opList = new String[] { "pk", "r", "xh", "xn", "xq", "xm",
				"bjmc", "cj" };
		if ("view_zjcm_zhszcpb".equalsIgnoreCase(tableName)) {
			sql = "select xh||xn||xq pk,rownum r,xh,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,bjmc,cj,bjpm from "
				+ tableName + " a where 1=1 ";
			opList = new String[] { "pk", "r", "xh", "xn", "xq", "xm",
					"bjmc", "cj", "bjpm"};
		}
		StringBuffer qSql = new StringBuffer("select * from (");
		qSql.append(sql);
		qSql.append(whereSql.toString());
		if ("true".equalsIgnoreCase(isFdy)) {
			qSql.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"') ");
		}
		qSql.append(" ) where r<=");
		qSql.append((dataSearchForm.getPages().getStart() + dataSearchForm
				.getPages().getPageSize()));
		qSql.append(" and r> ");
		qSql.append(dataSearchForm.getPages().getStart());
		return dao.rsToVator(qSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 查询结果记录数
	 * @param model
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public int getResultNum(ZhszcpModel model, String tableName, String isFdy, String userName)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh,xn,xq pk,rownum,xh,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,xm,bjmc,cj from "
				+ tableName + " a where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xh", "xn", "xq",
				"xm", "bjmc", "cj" };
		
		if ("true".equalsIgnoreCase(isFdy)) {
			whereSql.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"') ");
		}
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList).size();
	}
	
	/**
	 * 素质分保存
	 * @param model
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean save(ZhszcpModel model, String tableName,
			HttpServletRequest request) throws Exception {
		return StandardOperation.insert(tableName, new String[] { "xh", "xn",
				"xq", "cj" }, new String[] { model.getXh(), model.getXn(),
				model.getXq(), model.getCj() }, request);
	}
	
	/**
	 * 素质分修改
	 * @param model
	 * @param tableName
	 * @param request
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean update(ZhszcpModel model, String tableName,
			HttpServletRequest request, String pkValue) throws Exception {
		return StandardOperation.update(tableName, new String[] { "xn", "xq",
				"cj" }, new String[] { model.getXn(), model.getXq(),
				model.getCj() }, "xh||xn||xq", pkValue, request);
	}
	
	/**
	 * 删除
	 * @param keys
	 * @param tableName
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delete(String[] keys, String tableName,
			HttpServletRequest request) throws Exception {
		String rs = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete(tableName, "xh||xn||xq",
					keys[i], request);
			if (!bFlag) {
				rs += (i + 1);
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}
	
	/**
	 * 修改显示
	 * 
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> view(String pkValue, String tableName)
			throws Exception {
		StringBuffer sql = new StringBuffer(
				"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,cj from ");
		sql.append(tableName);
		sql.append(" where xh||xn||xq = ?");
		return dao.getMapNotOut(sql.toString(), new String[] { pkValue });
	}
	
	/**
	 * 综合素质分的自动计算
	 * @param xn
	 * @param xq
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean zhszAutoCount(String xn , String xq, String xydm) throws Exception {
		return dao.runProcuder("{call ZJCM_ZHSZCP_AUTOCOUNT(?, ?, ?)}", new String[]{xn, xq, xydm});
	}
	
	/**
	 * 综合素质测评比例
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpBlxx() throws Exception {
		return dao.getMapNotOut("select id,dybl,zybl,tybl,sjcxbl,cxbl from zjcm_zhszcpblszb", new String[]{});
	}
	
	/**
	 * 保存综合测评比例
	 * 
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhcpbl(String pkValue, ZhszcpModel model,
			HttpServletRequest request) throws Exception {
		if (StringUtils.isNull(pkValue)) {// 主键不存在插入
			return StandardOperation.insert("zjcm_zhszcpblszb", new String[] {
					"dybl", "zybl", "tybl", "sjcxbl", "cxbl" }, new String[] {
					model.getDybl(), model.getZybl(), model.getTybl(),
					model.getSjcxbl(), model.getCxbl() }, request);
		} else {// 更新
			return StandardOperation.update("zjcm_zhszcpblszb", new String[] {
					"dybl", "zybl", "tybl", "sjcxbl", "cxbl" }, new String[] {
					model.getDybl(), model.getZybl(), model.getTybl(),
					model.getSjcxbl(), model.getCxbl() }, "id", pkValue, request);
		}
	}
	
	/**
	 * 检查比例数据是否有设置
	 * @return
	 * @throws Exception
	 */
	public boolean chgZhszcpBlExists() throws Exception {
		String blExists = dao.getOneRs("select id from zjcm_zhszcpblszb",
				new String[] {}, "id");
		if (StringUtils.isNull(blExists)) {//不存在返回FALSE
			return false;
		} else {//存在返回TRUE
			return true;
		}
	}
	
	/**
	 * 获取班主任所带班级列表
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdybjList(String userName)
			throws Exception {
		String mySql = "select a.bjdm, bjmc from view_fdybbj b,view_njxyzybj a where a.bjdm=b.bjdm  and  b.zgh=?";
		return dao.getList(mySql, new String[] { userName }, new String[] {
				"bjdm", "bjmc" });
	}
	
	public HashMap<String, String> getJxjsqxnxq() throws Exception {
		return dao.getMapNotOut("select jxjsqxn,jxjsqxq,jxjsqnd from xtszb", new String[]{});
	}
	
	public HashMap<String, String> getXy(String xh, String pkValue) throws Exception {
		if (StringUtils.isNull(xh) && StringUtils.isNull(pkValue)) {
			return null;
		}
		if (StringUtils.isNull(xh)) {
			return dao.getMapNotOut("select a.xh,b.xm,b.xb,b.nj,b.xymc,b.zymc," +
					"b.bjmc,b.zzmmmc,b.rxrq,b.csrq,b.lxdh,b.xz,b.bysj,b.sfzh,b.jtdz," +
					"b.mzmc from xsjxjb a left join view_xsxxb b on a.xh=b.xh where a.xn||a.nd||a.xh||a.jxjdm=?", new String[]{pkValue});
		} else {
			return dao
					.getMapNotOut(
							"select xh,xm,xb,nj,xymc,zymc,bjmc,zzmmmc,rxrq,csrq,lxdh,xz,bysj,sfzh,jtdz,mzmc from view_xsxxb where xh=?",
							new String[] { xh });
		}
	}
}
