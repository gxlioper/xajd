package xgxt.pjpy.nbcs.pxpj;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.nbcs.PjpyNbcsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class PxpjNbcsDAO extends PjpyNbcsDAO {

	/**
	 * 保存问卷分配
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWjfp(PjpyTyForm model) throws Exception {
		
		boolean flag = false;
		
		DAO dao = DAO.getInstance();
		// 问卷ID
		String id = model.getId();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 分配班级
		String[] fpxh = model.getFpxh();
			
		StringBuilder query = new StringBuilder();
		
		if (fpxh != null && fpxh.length > 0) {// 选择学生
			for (int i = 0; i < fpxh.length; i++) {
				if (i == 0) {
					query.append("where b.xh = '" + fpxh[i] + "' ");
				} else {
					query.append("or b.xh = '" + fpxh[i] + "' ");
				}
			}
		} else {// 未选择班级
			query.append("where 1=1 ");
			query.append(Base.isNull(nj) ? "" : "and b.nj = '" + nj + "' ");
			query.append(Base.isNull(xydm) ? "" : "and b.xydm = '" + xydm + "' ");
			query.append(Base.isNull(zydm) ? "" : "and b.zydm = '" + zydm + "' ");
			query.append(Base.isNull(zydm) ? "" : "and b.bjdm = '" + bjdm + "' ");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from pxpj_wjfpb a where a.id = ?");
		
		flag = dao.runUpdate(sql.toString(), new String[] {id});
		
		if (flag) {
			
			sql = new StringBuilder();
			sql.append("insert into pxpj_wjfpb ");
			sql.append("select ? id, xh from view_xsjbxx b ");
			sql.append(query);
			
			flag = dao.runUpdate(sql.toString(), new String[] { id });
		}
		
		return flag;
	}
	
	/**
	 * 获得问卷回答情况
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> setWjHdInfo(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// 学号职工号
		String xhzgh = model.getXhzgh();
		// 回答者类型
		String lx = model.getLx();
		// 被评价人
		String bpjr = model.getBpjr();
		// 问卷编号
		String wjbh = model.getWjbh();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.fpbh, a.dabh, ");
		sql.append("(select b.stlx from wjdc_stxxb b where a.fpbh = b.stbh) stlx ");
		sql.append("from pxpj_hdb a ");
		sql.append(" where a.xhzgh = ? ");
		sql.append(" and a.lx = ? ");
		sql.append(" and a.wjbh = ? ");
		sql.append(" and a.bpjr = ? ");
		sql.append(" order by stlx,fpbh ");

		String[] inputValue = new String[] { xhzgh, lx, wjbh, bpjr };
		String[] outputValue = new String[] { "fpbh", "dabh" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 判断是否答卷人
	 * 
	 * @author luojw
	 */
	public Boolean isDjr(PjpyTyForm model) {
		
		DAO dao = DAO.getInstance();
		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();
		
		//SQL
		String sql = "select count(1) num from pxpj_djrb where xn = ? and djr = ?";
		
		String num = dao.getOneRs(sql, new String[]{xn,xh}, "num");
		
		boolean flag = (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) ? true
				: false;
		
		return flag;
	
	}
	
	/**
	 * 获得统计班级信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getTjbjList(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"id" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String[] colList = new String[] { "xydm", "xymc", "zydm", "zymc",
				"bjdm", "bjmc", "nj", "xn", "id", "wjmc", "bjrs","pk" };

		List<HashMap<String, String>> tjbjList = CommonQueryDAO
				.commonQueryforList("view_pxpj_bjqk", query, myQuery
						.getInputList(), colList, "");
		
		return tjbjList;
	}
	
	/**
	 * 获得统计学生信息列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getTjxsList(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		//问卷编号
		model.setWjbh(model.getId());
		
		String[] queryList = new String[] {"wjbh","bpjr"};

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		String[] colList = new String[] { "wjbh", "bpjr", "pj", "bjdm" };

		List<HashMap<String, String>> tjbjList = CommonQueryDAO
				.commonQueryforList("view_pxpj_tjjg", query, myQuery
						.getInputList(), colList, "");

		return tjbjList;
	}
	
	/**
	 * 获得学生评价列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// 班级代码
		String bjdm = model.getBjdm();
		// 问卷编号
		String wjbh = model.getWjbh();

		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh, a.xm, b.pj, b.pjf from ");
		sql.append("(select a.xh, a.xm from view_xsjbxx a where a.bjdm = ?) a ");
		sql.append("left join (select b.bpjr, b.pj, b.pjf from view_pxpj_tjjg b ");
		sql.append("where wjbh = ?) b on a.xh = b.bpjr order by a.xh");

		String[] inputValue = new String[] { bjdm, wjbh };
		String[] outputValue = new String[] { "xh", "xm", "pj", "pjf" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	
	/**
	 * 获得学生处分列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXscfList(PjpyTyForm model) {

		DAO dao = DAO.getInstance();

		// 学年
		String xn = model.getXn();

		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("select c.xn,c.xh,c.cflbmc from view_wjcf c where  c.xxsh = '通过' ");
		sql.append("and c.cfsj is not null and c.cfwh is not null and xn = ?");

		String[] inputValue = new String[] { xn };
		String[] outputValue = new String[] { "xh", "cflbmc" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
}
