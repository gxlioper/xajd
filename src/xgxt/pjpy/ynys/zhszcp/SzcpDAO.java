
package xgxt.pjpy.ynys.zhszcp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.ynys.PjpyYnysDAO;
import xgxt.utils.String.StringUtils;
/**
 * 素质测评DAO
 * @describe
 * @author 李涛 2008-11-3
 */
public class SzcpDAO extends PjpyYnysDAO {

	ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * 保存思想素质分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveSxzzf(SzcpModel model) throws Exception {
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getFs().length+1];
		StringBuffer sql = null;
		for (int i = 0; i < model.getFs().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into sxzzddszb (sid,xh,xn,fs,lr,zt,dfxm) values(autoid.nextval,'");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getFs()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getLr()[i]));
			sql.append("','");
			sql.append(model.getJcxm()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getDfxm()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getFs()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		String updateSql = "update sxzzddszb set shzt = '未审核' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		sqlList[model.getFs().length] = updateSql;
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	
	/**
	 * 思想素质分查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> szxxddfQryRes(SzcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
//		String sql = "select a.sid pk,rownum,a.xh,a.xm,a.xn,a.bjmc,a.dfxm,(case when a.zt='0' then '加分' else '扣分' end) zt,a.fs,b.zf from view_sxzzddszb a ";
		String sql = "select a.xh||a.xn pk,a.xh, a.xm, a.xn, a.bjmc,a.shzt,(60 + sum(a.fs)) zf from (select a.xh pk,"+
               "a.xh,a.xm,a.xn,a.bjmc,a.shzt,(case when zt = '1' then '-' || fs else fs end) fs "+
               "from view_sxzzddszb a where xn='"+model.getXn()+"'"+whereSql.toString()+") a group by xh, xm, xn, bjmc,shzt HAVING 1=1 ";
		String[] opList = new String[] { "pk","xh", "xm", "xn",
				"bjmc", "zf","shzt" };
		return dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 查询条件及值
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(SzcpModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		values = new ArrayList<String>();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and a.xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and a.xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%"); 
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and a.xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getShzt())) {
			whereSql.append(" and a.shzt = ?");
			values.add(DealString.toGBK(model.getShzt())); 
		}
		return whereSql;
	}
	
	/**
	 * 思相素质分批量删除
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String sxzzddszfDel(String[] keys, HttpServletRequest request)
			throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.delete("sxzzddszb",  "xh||xn",
					whichpk, request);
			if (!bFlag) {// 删除失败后记录删除失败的行号
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * 思相素质分修改显示
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewSxzzf(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,zt,fs,lr,dfxm from view_sxzzddszb " +
						"where sid = ?",
						new String[] { pkValue });
	}
	
	/**
	 * 思相素质分修改保存结果
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean sxzzfModisave(String pkValue, SzcpModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("sxzzddszb", new String[] { "xn", "fs",
				"lr", "zt" ,"dfxm"}, new String[] { model.getXn(), model.getSfs(),
				DealString.toGBK(model.getSlr()), model.getSzt(),DealString.toGBK(model.getSdfxm()) }, "sid", pkValue, request);
	}
	
	/**
	 * 思想素质分查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> sjlxcxfQryRes(SzcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select a.xh||a.xn pk,a.xh, a.xm, a.xn,a.bjmc,a.shzt,(60 + sum(a.fs)) zf from (select a.xh pk,"+
        "a.xh,a.xm,a.xn,a.bjmc,a.shzt,(case when zt = '1' then '-' || fs else fs end) fs "+
        "from view_sjlxcxszb a where xn='"+model.getXn()+"'"+whereSql.toString()+") a group by xh, xm, xn, bjmc,shzt HAVING 1=1 ";
		String[] opList = new String[] { "pk", "xh", "xm", "xn",
				"bjmc","zf","shzt" };
		return dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 保存思想素质分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveSjlxf(SzcpModel model) throws Exception {
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getFs().length+1];
		StringBuffer sql = null;
		for (int i = 0; i < model.getFs().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into sjlxcxszb (jid,xh,xn,fs,lr,zt,dfxm) values(autoid.nextval,'");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getFs()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getLr()[i]));
			sql.append("','");
			sql.append(model.getJcxm()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getDfxm()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getFs()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		String updateSql = "update sjlxcxszb set shzt = '未审核' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		sqlList[model.getFs().length] = updateSql;
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	
	/**
	 * 思相素质分修改显示
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewSjlxf(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,zt,fs,lr,dfxm from view_sjlxcxszb " +
						"where jid = ?",
						new String[] { pkValue });
	}
	
	/**
	 * 思相素质分修改保存结果
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean sjlxfModisave(String pkValue, SzcpModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("sjlxcxszb", new String[] { "xn", "fs",
				"lr", "zt","dfxm" }, new String[] { model.getXn(), model.getSfs(),
				DealString.toGBK(model.getSlr()), model.getSzt(),DealString.toGBK(model.getSdfxm()) }, "jid", pkValue, request);
	}
	
	/**
	 * 思相素质分批量删除
	 * 
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String sjlxcxszbDel(String[] keys, HttpServletRequest request)
			throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.delete("sjlxcxszb",  "xh||xn",
					whichpk, request);
			if (!bFlag) {// 删除失败后记录删除失败的行号
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}

	/**
	 * 科学文化素质分保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveKxwhf(SzcpModel model) throws Exception {
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getCj().length+1];
		StringBuffer sql = null;
		for (int i = 0; i < model.getCj().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into kxwhszfb (kid,xh,xn,kcmc,cj,kclx,df,sfbxk) values(autoid.nextval,'");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(DealString.toGBK(model.getKcmc()[i]));
			sql.append("','");
			sql.append(model.getCj()[i]);
			sql.append("','");
			sql.append(model.getKclx()[i]);
			sql.append("','");
			sql.append(model.getDf()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getSfbxk()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getCj()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		String updateSql = "update kxwhszfb set shzt = '未审核' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		sqlList[model.getCj().length] = updateSql;
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	
	/**
	 * 科学文化分查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getKxwhfList(SzcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
//		String sql = "select a.kid pk,rownum,a.xh,a.xn,a.xm,a.bjmc,a.kcmc,a.cj,a.kclx,a.df,(case when a.sfbxk='0' then '是' else '否' end) sfbxk from view_kxwhszfb a where 1=1 ";
		String sql = "select distinct a.xh||a.xn pk,a.xh,a.xn,a.xm,a.bjmc,a.shzt,(select round(avg(cj),2) from kxwhszfb where xh = a.xh and kclx='0' and a.xn = xn) zykpjf,(select round(avg(cj),2) from kxwhszfb where xh = a.xh and kclx='1' and a.xn = xn) whkpjf,(select round(avg(cj),2) from kxwhszfb where xh = a.xh and a.xn = xn) kcpjf from view_kxwhszfb a where 1=1 ";
		String[] opList = new String[] { "pk","xh", "xm", "xn", "bjmc",
				"zykpjf", "whkpjf","kcpjf","shzt"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 科学文化分修改页面
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> kxwhfModi(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao.getMapNotOut("select xh,kcmc,xn,cj,kclx,df,sfbxk,(select b.xm from view_xsjbxx" +
				" b where a.xh=b.xh) xm,(select b.xb from view_xsjbxx b where a.xh=b.xh) xb,(select b.nj from view_xsjbxx b where a.xh=b.xh) nj,(select b.xymc from view_xsjbxx b where a.xh=b.xh) xymc,(select b.zymc from view_xsjbxx b where a.xh=b.xh) zymc,(select b.bjmc from view_xsjbxx b where a.xh=b.xh) bjmc from kxwhszfb a where kid=?", new String[]{pkValue});
	}
	
	/**
	 * 科学文化分修改保存
	 * 
	 * @param pkValue
	 * @param request
	 * @param xn
	 * @param kcmc
	 * @param cj
	 * @param kclx
	 * @return
	 * @throws Exception
	 */
	public boolean kxwhfModiSave(String pkValue, HttpServletRequest request,
			String xn, String kcmc, String cj, String kclx, String df, String sfbxk) throws Exception {
		dao = DAO.getInstance();
		return dao.runUpdate("update kxwhszfb set xn=?,kcmc=?,cj=?,kclx=?,df=?,sfbxk=? where kid=?", new String[]{xn,DealString.toGBK(kcmc),cj,kclx,df,DealString.toGBK(sfbxk),pkValue});
	}
	
	/**
	 * 文化分删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String kxwhfDel(String[] keys, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.delete("kxwhszfb",  "xh||xn",
					whichpk, request);
			if (!bFlag) {// 删除失败后记录删除失败的行号
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * 身心分查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> sxllfQry(SzcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select a.xh||a.xn pk,a.xh, a.xm, a.xn, a.bjmc,a.shzt,(60 + sum(a.fs)) zf from (select a.xh pk,"+
        "a.xh,a.xm,a.xn,a.bjmc,a.shzt,(case when zt = '1' then '-' || fs else fs end) fs "+
        "from view_sxlxszb a where xn='"+model.getXn()+"'"+whereSql.toString()+") a group by xh, xm, xn, bjmc,shzt HAVING 1=1 ";
		String[] opList = new String[] { "pk", "xh", "xm", "xn",
				"bjmc","zf","shzt" };
		return dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 保存身心能力分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveSxllf(SzcpModel model) throws Exception {
		String sFlag = "";
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getFs().length+1];
		StringBuffer sql = null;
		for (int i = 0; i < model.getFs().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into sxlxszb (sxid,xh,xn,fs,lr,zt,dfxm) values(autoid.nextval,'");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getFs()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getLr()[i]));
			sql.append("','");
			sql.append(model.getJcxm()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getDfxm()[i]));
			sql.append("')");
			if (StringUtils.isNull(model.getFs()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		String updateSql = "update sxlxszb set shzt = '未审核' where xh = '"+model.getXh()+"' and xn = '"+model.getXn()+"'";
		sqlList[model.getFs().length] = updateSql;
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return sFlag.substring(0, sFlag.length() - 1);
		} else {
			return "";
		}
	}
	
	public HashMap<String, String> sxllfView(String pkValue) throws Exception {
		dao = DAO.getInstance();
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,fs,lr,dfxm,(case zt when '0' then '加分' else '扣分' end) zt from view_sxlxszb where sxid=?", new String[]{pkValue});
	}
	
	public boolean sxllfModisave(String pkValue, String xn, String fs, String lr, String zt, String dfxm) throws Exception {
		dao = DAO.getInstance();
		return dao.runUpdate("update sxlxszb set xn=?,fs=?,lr=?,zt=?,dfxm=? where sxid=?", new String[]{xn,fs,lr,zt,dfxm,pkValue});
	}
	
	public String sxllfDel(String[] keys, HttpServletRequest request) throws Exception {
		String shRes = "";
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];
			boolean bFlag = StandardOperation.delete("sxlxszb", "xh||xn",
					whichpk, request);
			if (!bFlag) {// 删除失败后记录删除失败的行号
				shRes += (i + 1);
				shRes += ",";
			}
		}
		return StringUtils.isNull(shRes) ? "" : shRes.substring(0, shRes
				.length() - 1);
	}
	
	/**
	 * 专业课平均分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> kxwhzypfj(SzcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum pk,zf,rownum,xh,xm,xn,bjmc,zykpjf from (select distinct a.xh,a.xm,a.xn,a.bjmc,trunc(avg(a.cj) over (partition by xh),2) zykpjf,sum(a.cj) over (partition by xh) zf from view_kxwhszfb a where a.kclx='专业课' ";
		String[] opList =new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
		"zf", "zykpjf" };
		return dao.rsToVator(sql + whereSql.toString() + ")", values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 文化课平均分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> kxwhwhpfj(SzcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum pk,rownum,xh,xm,xn,bjmc,whkpjf,zf from (select distinct xh,xm,xn,bjmc,trunc(avg(cj) over (partition by xh),2) whkpjf,sum(a.cj) over (partition by xh) zf from view_kxwhszfb a where kclx='文化课' ";
		String[] opList =new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
		"zf","whkpjf" };
		return dao.rsToVator(sql + whereSql.toString() + ")", values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 课程平均分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> kxwhkcpfj(SzcpModel model) throws Exception {
		dao = DAO.getInstance();
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select rownum pk,rownum,xh,zf,xm,xn,bjmc,kcpjf from (select distinct xh,xm,xn,bjmc,trunc(avg(cj) over (partition by xh),2) kcpjf,sum(a.cj) over (partition by xh) zf from view_kxwhszfb a where 1=1 ";
		String[] opList =new String[] { "pk", "rownum", "xh", "xm", "xn", "bjmc",
		"zf", "kcpjf" };
		return dao.rsToVator(sql + whereSql.toString() + ")", values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}

	public HashMap<String, String> getSqxsxx(String pkValue) {
		// TODO 自动生成方法存根
		dao = DAO.getInstance();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt,(select sjhm from xsfzxxb " +
						"b where b.xh=a.xh) sjhm,(select wysp from xsfzxxb b " +
						"where b.xh=a.xh) wysp from (select distinct xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt from view_sxzzddszb where xh||xn =? ) a";
		return dao.getMapNotOut(sql,new String[] { pkValue });
	}
	
	public ArrayList<HashMap<String, String>> getSqxsxxs(String pkValue) throws Exception {
		dao = DAO.getInstance();
		String sql = "select sid xid,dfxm,zt jcxm,fs,lr from sxzzddszb where xh||xn=?";
		String[] opList =new String[] { "xid","dfxm","jcxm","fs","lr" };
		return dao.getArrayList(sql, new String[]{pkValue}, opList);
	}

	public HashMap<String, String> getKxwhszf(String pkValue) {
		//TODO 自动生成方法存根
		dao = DAO.getInstance();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt,(select sjhm from xsfzxxb " +
						"b where b.xh=a.xh) sjhm,(select wysp from xsfzxxb b " +
						"where b.xh=a.xh) wysp from (select distinct xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt from view_kxwhszfb where xh||xn =? ) a";
		return dao.getMapNotOut(sql,new String[] { pkValue });
	}

	public ArrayList<HashMap<String, String>> getKxwhszfs(String pkValue) {
		dao = DAO.getInstance();
		String sql = "select kid xid,kcmc,cj,kclx,sfbxk,df from kxwhszfb where xh||xn=?";
		String[] opList =new String[] { "xid","kcmc","cj","kclx","sfbxk","df" };
		return dao.getArrayList(sql, new String[]{pkValue}, opList);
	}

	public HashMap<String, String> getSjlxcxf(String pkValue) {
		//		 TODO 自动生成方法存根
		dao = DAO.getInstance();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt,(select sjhm from xsfzxxb " +
						"b where b.xh=a.xh) sjhm,(select wysp from xsfzxxb b " +
						"where b.xh=a.xh) wysp from (select distinct xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt from view_sjlxcxszb where xh||xn =? ) a";
		return dao.getMapNotOut(sql,new String[] { pkValue });
	}

	public ArrayList<HashMap<String, String>> getSjlxcxfs(String pkValue) {
		dao = DAO.getInstance();
		String sql = "select jid xid,dfxm,zt jcxm,fs,lr from sjlxcxszb where xh||xn=?";
		String[] opList =new String[] { "xid","dfxm","jcxm","fs","lr" };
		return dao.getArrayList(sql, new String[]{pkValue}, opList);
	}

	public HashMap<String, String> getSxllf(String pkValue) {
		dao = DAO.getInstance();
		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt,(select sjhm from xsfzxxb " +
						"b where b.xh=a.xh) sjhm,(select wysp from xsfzxxb b " +
						"where b.xh=a.xh) wysp from (select distinct xh,xm,xb,nj,xymc,zymc,bjmc,xn,shzt from view_sxlxszb where xh||xn =? ) a";
		return dao.getMapNotOut(sql,new String[] { pkValue });
	}

	public ArrayList<HashMap<String, String>> getSxllfs(String pkValue) {
		dao = DAO.getInstance();
		String sql = "select sxid xid,dfxm,zt jcxm,fs,lr from sxlxszb where xh||xn=?";
		String[] opList =new String[] { "xid","dfxm","jcxm","fs","lr" };
		return dao.getArrayList(sql, new String[]{pkValue}, opList);
	}
	
	/**
	 * 修改思想素质分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updataSxzzf(SzcpModel model) throws Exception {
		String sFlag = "";
		dao = DAO.getInstance();
		String delIds=model.getDelIds();
		String[] delId = delIds.split("-");
		String[] sqlList = new String[model.getFs().length+delId.length];
		StringBuffer sql = null;
		String shztQuery = "";
		if(model.getShzt()!=null){
			shztQuery ="', shzt = '"+DealString.toGBK(model.getShzt());
		}
		for (int i = 0; i < model.getFs().length; i++) {
			sql = new StringBuffer();
			sql.append("update sxzzddszb set fs='");
			sql.append(model.getFs()[i]);
			sql.append("',lr='");
			sql.append(DealString.toGBK(model.getLr()[i]));
			sql.append("',zt='");
			sql.append(model.getJcxm()[i]);
			sql.append("',dfxm='");
			sql.append(DealString.toGBK(model.getDfxm()[i]));
			sql.append(shztQuery);
			sql.append("' where sid = ");
			sql.append(model.getXid()[i]);
			sql.append("");
			if (StringUtils.isNull(model.getFs()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		for (int i = 0; i < delId.length; i++) {
			if(!delId[i].equalsIgnoreCase("")){
			String delsql = "delete from sxzzddszb where sid="+delId[i];
			sqlList[model.getFs().length+i] = delsql;
			}
		}
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 修改身心能力素质分
	 * @param model
	 * @return
	 * @throws SQLException 
	 * @throws SQLException 
	 * @throws Exception
	 */
	public boolean updataSxllfsz(SzcpModel model) throws SQLException {
		String sFlag = "";
		dao = DAO.getInstance();
		String delIds=model.getDelIds();
		String[] delId = delIds.split("-");
		String[] sqlList = new String[model.getFs().length+delId.length];
		StringBuffer sql = null;
		String shztQuery = "";
		if(model.getShzt()!=null){
			shztQuery ="', shzt = '"+DealString.toGBK(model.getShzt());
		}
		for (int i = 0; i < model.getFs().length; i++) {
			sql = new StringBuffer();
			sql.append("update sxlxszb set fs='");
			sql.append(model.getFs()[i]);
			sql.append("',lr='");
			sql.append(DealString.toGBK(model.getLr()[i]));
			sql.append("',zt='");
			sql.append(model.getJcxm()[i]);
			sql.append("',dfxm='");
			sql.append(DealString.toGBK(model.getDfxm()[i]));
			sql.append(shztQuery);
			sql.append("' where sxid = ");
			sql.append(model.getXid()[i]);
			sql.append("");
			if (StringUtils.isNull(model.getFs()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		for (int i = 0; i < delId.length; i++) {
			if(!delId[i].equalsIgnoreCase("")){
			String delsql = "delete from sxlxszb where sxid="+delId[i];
			sqlList[model.getFs().length+i] = delsql;
			}
		}
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 实践分修改保存结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean updataSjlx(SzcpModel model) throws SQLException {
		String sFlag = "";
		dao = DAO.getInstance();
		String delIds=model.getDelIds();
		String[] delId = delIds.split("-");
		String[] sqlList = new String[model.getFs().length+delId.length];
		StringBuffer sql = null;
		String shztQuery = "";
		if(model.getShzt()!=null){
			shztQuery ="', shzt = '"+DealString.toGBK(model.getShzt());
		}
		for (int i = 0; i < model.getFs().length; i++) {
			sql = new StringBuffer();
			sql.append("update sjlxcxszb set fs='");
			sql.append(model.getFs()[i]);
			sql.append("',lr='");
			sql.append(DealString.toGBK(model.getLr()[i]));
			sql.append("',zt='");
			sql.append(model.getJcxm()[i]);
			sql.append("',dfxm='");
			sql.append(DealString.toGBK(model.getDfxm()[i]));
			sql.append(shztQuery);
			sql.append("' where jid = ");
			sql.append(model.getXid()[i]);
			sql.append("");
			if (StringUtils.isNull(model.getFs()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		for (int i = 0; i < delId.length; i++) {
			if(!delId[i].equalsIgnoreCase("")){
			String delsql = "delete from sjlxcxszb where jid="+delId[i];
			sqlList[model.getFs().length+i] = delsql;
			}
		}
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 科学文化素质分修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	
	public boolean updataKxwhszf(SzcpModel model) throws SQLException {
		String sFlag = "";
		dao = DAO.getInstance();
		String delIds=model.getDelIds();
		String[] delId = delIds.split("-");
		String[] sqlList = new String[model.getKclx().length+delId.length];
		StringBuffer sql = null;
		String shztQuery = "";
		if(model.getShzt()!=null){
			shztQuery ="', shzt = '"+DealString.toGBK(model.getShzt());
		}
		for (int i = 0; i < model.getKclx().length; i++) {
			sql = new StringBuffer();
			sql.append("update kxwhszfb set kcmc='");
			sql.append(DealString.toGBK(model.getKcmc()[i]));
			sql.append("',cj='");
			sql.append(model.getCj()[i]);
			sql.append("',kclx='");
			sql.append(model.getKclx()[i]);
			sql.append("',df='");
			sql.append(model.getDf()[i]);
			sql.append("',sfbxk='");
			sql.append(DealString.toGBK(model.getSfbxk()[i]));
			sql.append(shztQuery);
			sql.append("' where kid = ");
			sql.append(model.getXid()[i]);
			sql.append("");
			if (StringUtils.isNull(model.getKclx()[i])) {//为空则不记录
				sql = new StringBuffer("");				
			}
			sqlList[i] = sql.toString();
		}
		for (int i = 0; i < delId.length; i++) {
			if(!delId[i].equalsIgnoreCase("")){
			String delsql = "delete from kxwhszfb where kid="+delId[i];
			sqlList[model.getKclx().length+i] = delsql;
			}
		}
		int[] iFlag = dao.runBatch(sqlList);//批量插入
		for (int i=0;i<iFlag.length;i++) {
			if(iFlag[i] <= -1){//记录操作失败的行号
				sFlag = (iFlag[i] + 1) + ",";
			}
		}
		if (!StringUtils.isNull(sFlag)) {
			return false;
		} else {
			return true;
		}
	}

		
}
