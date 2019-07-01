package xgxt.qgzx.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.qgzx.QgzxTyDAO;
import xgxt.qgzx.QgzxTyForm;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.utils.Pages;

public class QgzxGzdxDAO extends QgzxTyDAO {
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * @param model
	 * @return StringBuilder
	 * */
	public StringBuilder getWhereSql(QgzxTyForm model){
		QgzxDao qgzxDao = new  QgzxDao();
		String xh = DealString.toGBK(model.getXh());
		String xm = DealString.toGBK(model.getXm());
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String userName = model.getUserName();
		String yrdwdm = model.getYrdwdm();
		
		StringBuilder sb = new StringBuilder("where 1=1 ");
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(zydm !=null && !zydm.equalsIgnoreCase("")){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(bjdm !=null && !bjdm.equalsIgnoreCase("")){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(xh !=null && !xh.equalsIgnoreCase("")){
			sb.append( " and xh=?");
			value.add(xh);
		}
		if(xm !=null && !xm.equalsIgnoreCase("")){
			sb.append( " and xm=?");
			value.add(xm);
		}
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(nd !=null && !nd.equalsIgnoreCase("")){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(xq !=null && !xq.equalsIgnoreCase("")){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(qgzxDao.isYrdwUser(userName)){
			sb.append(" and exists(select 1 from yrdwdmb b where a.yrdwdm=b.yrdwdm and b.dlm=?)");
			value.add(userName);
		}
		if(yrdwdm !=null && !yrdwdm.equalsIgnoreCase("")){
			sb.append( " and yrdwdm=?");
			value.add(yrdwdm);
		}
		return sb;
	}

	/**
	 * 获得岗位信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getLsgwList(QgzxTyForm model) {

		DAO dao = DAO.getInstance();

		// 学年
		String xn = model.getXn();
		// 学期
		// String xq = model.getXq();
		// 年度
		String nd = model.getNd();
		// 登陆用户
		String userName = model.getUserName();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.gwsbsj sj,a.gwdm dm from view_gwxx a ");
		sql.append(" where a.xn = ? ");
		sql.append(" and a.nd = ? ");
		sql.append(" and a.gwxzmc = '临时岗位' ");
		sql.append(" and a.shjg = '通过' ");
		sql.append(Base.isNull(userName) ? "": " and exists (select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=?)");

		String[] inputValue = Base.isNull(userName) ? new String[] { xn, nd }
				: new String[] { xn, nd, userName };
		String[] outputValue = new String[] { "sj", "dm" };

		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 获得岗位信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getLsgwList(String userName) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select gwsbsj||gwdm dm,a.gwdm mc from view_gwxx a ");
		sql.append(" where a.gwxzmc = '临时岗位' ");
		sql.append(" and a.shjg = '通过' ");
		sql.append(Base.isNull(userName) ? "": " and exists (select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm=?)");

		String[] inputValue = Base.isNull(userName) ? new String[] {}
				: new String[] { userName };
		String[] outputValue = new String[] { "dm", "mc" };

		return dao.getList(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * 查询用工部门学生列表
	 * @param model
	 * @param output
	 * @return List<HashMap<String, String>> 
	 * */
	public List<String[]> selectYgbmxsList(QgzxTyForm model, 
			                               String[] output){
		DAO dao = DAO.getInstance();
		//分页信息
		Pages pages = model.getPages();
		int start = pages.getStart();
		int pageSize = pages.getPageSize();
		
		//组合查询语句
		StringBuilder whereSql = getWhereSql(model);//查询条件
		//此处查询的数据应该默认为学校审核通过的数据
		whereSql.append(" and xxyj='通过' ");
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select rownum r,xh||xn||nd||xq pkValue,a.* from(");
		sb.append("select distinct xh,xm,xb,xn,nd,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xqmc,decode((select 1 from qgzx_pjpy_jjfzb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq),1,'已申报','未申报')是否已申报 from view_xsgwxx a ");
		sb.append(whereSql);
		sb.append(")a");
		sb.append(") where r>");
		sb.append(start);
		sb.append(" and r<=");
		sb.append(start + pageSize);
		System.out.println(sb.toString());
		//查询总页数
		StringBuilder coutSql = new StringBuilder("select count(*) num from (");
		coutSql.append("select distinct xh,xm,xb,xn,nd,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xqmc from view_xsgwxx a ");
		coutSql.append(whereSql);
		coutSql.append(")");
		
		String count = dao.getOneRs(coutSql.toString(),
					 value != null ? value.toArray(new String[0]) : new String[]{} , 
					 "num");
		pages.setMaxRecord(Integer.parseInt(count));//设置总记录数
		return dao.rsToVator(sb.toString(), value != null ? value.toArray(new String[0]) : new String[]{}, output);
	}
	
	/**
	 * 查询用工部门学生列表(用人单位)
	 * @param model
	 * @param output
	 * @return List<HashMap<String, String>> 
	 * */
	public List<String[]> selectYgbmxs(QgzxTyForm model, 
			                               String[] output){
		DAO dao = DAO.getInstance();
		//分页信息
		Pages pages = model.getPages();
		int start = pages.getStart();
		int pageSize = pages.getPageSize();
		
		//组合查询语句
		StringBuilder whereSql = getWhereSql(model);//查询条件
		//此处查询的数据应该默认为学校审核通过的数据
		whereSql.append(" and xxyj='通过' ");
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (");
		sb.append("select rownum r,xh||xn||nd||xq pkValue,a.* from(");
		sb.append("select distinct xh,xm,xb,xn,nd,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xqmc,yrdwdm,yrdwmc,decode((select 1 from qgzx_pjpy_jjfzb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq),1,'已申报','未申报')是否已申报 from view_xsgwxx a ");
		sb.append(whereSql);
		sb.append(")a");
		sb.append(") where r>");
		sb.append(start);
		sb.append(" and r<=");
		sb.append(start + pageSize);
		System.out.println(sb.toString());
		//查询总页数
		StringBuilder coutSql = new StringBuilder("select count(*) num from (");
		coutSql.append("select distinct xh,xm,xb,xn,nd,xq,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xqmc,yrdwdm,yrdwmc from view_xsgwxx a ");
		coutSql.append(whereSql);
		coutSql.append(")");
		
		String count = dao.getOneRs(coutSql.toString(),
					 value != null ? value.toArray(new String[0]) : new String[]{} , 
					 "num");
		pages.setMaxRecord(Integer.parseInt(count));//设置总记录数
		return dao.rsToVator(sb.toString(), value != null ? value.toArray(new String[0]) : new String[]{}, output);
	}
	
	/**
	 * 保存勤工助学积极份子申报信息
	 * @param model
	 * @return boolean
	 * */
	public boolean saveQgzxjjfzsb(QgzxTyForm model){
		boolean result = false;//操作结果
		DAO dao = DAO.getInstance();
		String[] pkValue = model.getCheckVal();//主键
		String[] sqlArr = new String[pkValue.length*2];
		String tableName = "qgzx_pjpy_jjfzb";
		
		int step = 0;
		for(int i=0; i<pkValue.length; i++){
			sqlArr[step + i] = "delete from " + tableName + " where xh||xn||nd||xq='" + pkValue[i] + "'";
			sqlArr[step + i +1] = "insert into " + tableName + "(xh,xn,nd,xq,sbr)(select distinct xh,xn,nd,xq,'" + model.getUserName() + "' from view_xsgwxx where xh||xn||nd||xq='" + pkValue[i] + "')";
			step++;
		}
		try{
			int[] res = dao.runBatch(sqlArr);
			result = dao.checkBatch(res);
		}catch(Exception e){
			result = false;
		}
		
		return result;
	}
}
