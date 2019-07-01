package xgxt.xszz.hzny;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-12 下午02:25:39</p>
 */
public class XszzHznyDAO {
	
	
	DAO dao=DAO.getInstance();
	
	/**
	 * 获取学院绿色通道统计信息
	 */
	public List<HashMap<String,String >>getXylstdData(){
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select c.xydm,c.xymc,count(1)blstdrs,sum(a.kzzd6)/10000 hjje,sum(a.kzzd2)hjzf");
		sql.append(" from xszz_comm_zzwsb a, xszz_zzxmb b,view_xsjbxx c ");
		sql.append(" where a.xmdm = '8003'");
		sql.append(" and a.xmdm = b.xmdm ");

		sql.append(" and a.xh=c.xh group by c.xydm,c.xymc");
		
		return dao.getList(sql.toString(), new String[]{}, 
				new String[]{"xymc","blstdrs","hjje","hjzf"});
	}
	
	/**
	 * 获取学院绿色通道统计信息
	 */
	public List<HashMap<String,String >>getSydlstdData(){
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select case when c.syds is null then '其它' else sydsmc end syds,count(1)tgrs");
		sql.append(" from xszz_comm_zzwsb a, xszz_zzxmb b,view_xsbfxx c ");
		sql.append(" where a.xmdm = '8003'");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and (b.shjb='无需审核' or");
		sql.append(" b.shjb='一级审核' and a.shzt1='通过' or");
		sql.append(" b.shjb='二级审核' and a.shzt2='通过' or");
		sql.append(" b.shjb='三级审核' and a.shzt3='通过' )");
		sql.append(" and a.xh=c.xh group by syds ");
		
		return dao.getList(sql.toString(), new String[]{}, 
				new String[]{"syds","tgrs"});
	}
}
