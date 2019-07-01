package xgxt.qgzx.comm.gwsqwh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学_学生岗位申请-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class QgzxXsgwsqDao {
	
	DAO dao = DAO.getInstance();
	ArrayList<String> whereList = new ArrayList<String>();
	
	/**
	 * 通过表名查询列表数据，适用于下拉列表数据
	 * @param tableName
	 * @param outZdArray
	 * @param wheresql
	 * @return
	 */
	public List<HashMap<String, String>> getLbList(String tableName, String[] outZdArray, String wheresql) {
		if (StringUtils.isNull(tableName)) {//tableName 为空则直接返回空
			return new ArrayList<HashMap<String, String>>(){};
		}
		
		wheresql = StringUtils.isNull(wheresql) ? "" : wheresql;
		String sql = "select * from " + tableName + wheresql;
		if (outZdArray != null && outZdArray.length > 0) {
			return dao.getList(sql, new String[]{}, outZdArray);
		} else {
			return dao.getListNotOut(sql, new String[]{});
		}
	}
	
	/**
	 * 查询岗位申请信息
	 * 
	 * @param qgzxForm
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwsqxx(
			QgzxXsgwsqForm qgzxForm) {
		
		return dao.getList(
				"select * from (select gwdm||gwsbsj pk,gwxzmc,yrdwmc,gwdm,sqsyrs,nvl(sqsyknss,'0') sqsyknss,sqjssj,gwxz,sqdw,gzksrq,gzjsrq," +
				"'ksq' cz from view_gwxx a where xn=? and nd=? and xueqi=? and sfyx='有效' and sqsyrs > 0)" + getWhereSql(qgzxForm),
				new String[] { qgzxForm.getXn(), qgzxForm.getNd(), qgzxForm.getXq() },
						new String[] {"pk",  "gwxzmc", "yrdwmc", "gwdm",
						"sqsyrs", "sqsyknss", "sqjssj", "cz"  });
	}
	
	/**
	 * 通过学号查询学生的岗位申请信息
	 * 申请岗位>0 并且 是有将的岗位 的才显示出来
	 * 
	 * @param qgzxForm
	 * @return
	 */
	public List<HashMap<String, String>> queryXsgwxxByStu(
			QgzxXsgwsqForm qgzxForm) {
		String sql = "select * from (select a.*,nvl(b.xyyj,'') xyyj,nvl(b.xxyj,'') xxyj,(case when b.xyyj is null and b.xxyj is null then '未申请' else '用人单位('||b.xyyj||')<br/>学校('||b.xxyj||')'" +
				" end) shzt,'ksq' cz from (select gwdm||gwsbsj pk,gwsbsj,gwxzmc,yrdwmc,gwdm,nvl(sqsyrs,'0') sqsyrs,nvl(sqsyknss,'0') sqsyknss,sqjssj,a.gwxz,a.gzksrq,a.gzjsrq,a.sqdw " +
				" from view_gwxx a where xn=? and nd=? and xueqi=? and sfyx='有效' and sqsyrs > 0) a left join (select * from xsgwxxb b where " +
				" xn=? and nd=? and xq=? and xh= ?) b on a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj)" + getWhereSql(qgzxForm);
		return dao.getList(sql, new String[] { qgzxForm.getXn(),
				qgzxForm.getNd(), qgzxForm.getXq(), qgzxForm.getXn(),
				qgzxForm.getNd(), qgzxForm.getXq(), qgzxForm.getXh() },
				new String[] { "pk", "gwxzmc", "yrdwmc", "gwdm", "sqsyrs",
						"sqsyknss", "xyyj", "xxyj", "sqjssj", "shzt", "cz" });
	}
	
	/**
	 * 组合拼装查询条件，因为涉及到时间值所以不能采用通用的查询方法
	 * @param qgzxForm
	 * @return
	 */
	public String getWhereSql(QgzxXsgwsqForm qgzxForm) {
		StringBuffer sql = new StringBuffer(" where 1=1 ");
		if (!StringUtils.isNull(qgzxForm.getGwxz())) {
			sql.append(" and gwxz = '");
			sql.append(qgzxForm.getGwxz());
			sql.append("'");
		}
		if (!StringUtils.isNull(qgzxForm.getSqdw())) {
			sql.append(" and sqdw = '");
			sql.append(qgzxForm.getSqdw());
			sql.append("'");
		}
		if (!StringUtils.isNull(qgzxForm.getGzksrq())) {
			sql.append(" and to_date(gzksrq,'yyyy-mm-dd hh24:mi') >= to_date('");
			sql.append(qgzxForm.getGzksrq());
			sql.append("','yyyy-mm-dd')");
		}
		if (!StringUtils.isNull(qgzxForm.getGzjsrq())) {
			sql.append(" and to_date(gzjsrq,'yyyy-mm-dd hh24:mi') <= to_date('");
			sql.append(qgzxForm.getGzjsrq());
			sql.append("','yyyy-mm-dd')");
		}
		return sql.toString();
	}
	
	/**
	 * 获取岗位详细信息
	 * @param pkValue 岗位代码+岗位上报时间
	 * @return
	 */
	public Map<String, String> viewGwxx(String pkValue){
		return dao.getMapNotOut("select a.gwdm||a.gwsbsj gwdmsbsj,a.* from view_gwxx a where gwdm||gwsbsj = ?", new String[]{pkValue});
	}
	
	/**
	 * 通过学号获取学生信息
	 * @param xh 学号
	 * @return
	 */
	public Map<String, String> viewXsxx(String xh){
		return dao.getMapNotOut("select * from view_xsjbxx a where xh = ?", new String[]{xh});
	}
	
	/**
	 * 获取学生岗位申请信息
	 * @param xh 学号
	 * @param pkValue 岗位代码+岗位上报时间
	 * @return
	 */
	public Map<String, String> viewSqxx(String xh,String pkValue){
		return dao.getMapNotOut("select * from xsgwxxb a where xh = ? and gwdm||gwsbsj=?", new String[]{xh,pkValue});
	}
	
	/**
	 * 根据学号获取该生是否已经开始审核，若为0则未开始审核，可以修改申请信息；否则不能修改申请信息
	 * @param xh 学号
	 * @return
	 */
	public String getShCount(String xh,String pkValue){
		return dao.getOneRs("select count(*) cont from view_xsgwxx  where xh=? and gwdm||gwsbsj=? and (nvl(xyyj,'未审核')<>'未审核' or nvl(xxyj,'未审核')<>'未审核')", new String[]{xh,pkValue}, "cont");
	}
	
	/**
	 * 学生岗位申请结果查询列表
	 * 
	 * @param qgzxForm
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> queryXsgwsqxxByStu(QgzxXsgwsqForm qgzxForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[]{"xh", "xn", "xq", "nd", "yrdwdm", "gwxz"}, new String[]{"gwdm"}, qgzxForm);
		} catch (IllegalArgumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return CommonQueryDAO.commonQuery("select xh||'!!split!!'||gwdm||gwsbsj pk,a.*,rownum r,(case when xyyj <> '未审核' or xxyj <> '未审核' then 'disabled'"
								+ " else '' end) dis from view_xsgwxx a"
								,makeQuery.getQueryString(), makeQuery
								.getInputList(), new String[] { "pk","dis", "r", "xn",
								"nd", "xqmc", "xh", "xm", "bjmc", "gwdm", "sfpks", "xyyj", "xxyj" }, qgzxForm);
	}
	
	/**
	 * 用人单位查询学生岗位申请结果信息
	 * 
	 * @param form
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> queryXsgwsqxxByYrdw(QgzxXsgwsqForm form)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[] { "xh", "xn", "xq", "nd",
					"yrdwdm", "gwxz", "xydm", "zydm", "bjdm", "nj" },
					new String[] { "xh", "xm", "gwdm" }, form);
		} catch (IllegalArgumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return CommonQueryDAO.commonQuery("select xh||'!!split!!'||gwdm||gwsbsj pk,a.*,rownum r,(case when xxyj <> '未审核' then 'disabled'"
								+ " else '' end) dis from view_xsgwxx a "
								,makeQuery.getQueryString(), makeQuery
								.getInputList(), new String[] {"pk", "dis", "r","xn",
									"nd", "xqmc", "xh", "xm", "bjmc", "gwdm", "sfpks", "xyyj", "xxyj" }, form);
	}
	
	/**
	 * 学校用户查询学生岗位申请结果信息
	 * 
	 * @param form
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> queryXsgwsqxxByxx(QgzxXsgwsqForm form)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[] { "xh", "xn", "xq", "nd",
					"yrdwdm", "gwxz", "xydm", "zydm", "bjdm", "nj" },
					new String[] { "xh", "xm", "gwdm" }, form);
		} catch (IllegalArgumentException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return CommonQueryDAO.commonQuery(
						"select xh||'!!split!!'||gwdm||gwsbsj pk,a.*,rownum r,'' dis from view_xsgwxx a ",
						makeQuery.getQueryString(), makeQuery.getInputList(),
						new String[] { "pk","dis",  "r", "xn", "nd", "xqmc", "xh",
								"xm", "bjmc", "gwdm", "sfpks", "xyyj",
								"xxyj" }, form);
	}
	
	/**
	 * 删除学生岗位申请信息
	 * @param form
	 * @return
	 * @throws SQLException
	 */
	public int[] deleteXsgwsqxx(QgzxXsgwsqForm form) throws SQLException {
		String[] pk = form.getPrimarykey_cbv();
		StringBuffer sql = new StringBuffer();
		for (String s : pk) {
			sql.append("delete from xsgwxxb where xh||'!!split!!'||gwdm||gwsbsj = '");
			sql.append(s);
			sql.append("'!@");
		}
		return dao.runBatch(sql.toString().split("!@"));
	}
	
	public HashMap<String, String> queryGwsqsjb() {
		return dao.getMapNotOut("select * from gwsqsjb where rownum <2", new String[]{});
	}
}
