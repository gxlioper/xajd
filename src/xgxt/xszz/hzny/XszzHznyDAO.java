package xgxt.xszz.hzny;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-6-12 ����02:25:39</p>
 */
public class XszzHznyDAO {
	
	
	DAO dao=DAO.getInstance();
	
	/**
	 * ��ȡѧԺ��ɫͨ��ͳ����Ϣ
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
	 * ��ȡѧԺ��ɫͨ��ͳ����Ϣ
	 */
	public List<HashMap<String,String >>getSydlstdData(){
		
		StringBuilder sql = new StringBuilder();

		sql.append(" select case when c.syds is null then '����' else sydsmc end syds,count(1)tgrs");
		sql.append(" from xszz_comm_zzwsb a, xszz_zzxmb b,view_xsbfxx c ");
		sql.append(" where a.xmdm = '8003'");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and (b.shjb='�������' or");
		sql.append(" b.shjb='һ�����' and a.shzt1='ͨ��' or");
		sql.append(" b.shjb='�������' and a.shzt2='ͨ��' or");
		sql.append(" b.shjb='�������' and a.shzt3='ͨ��' )");
		sql.append(" and a.xh=c.xh group by syds ");
		
		return dao.getList(sql.toString(), new String[]{}, 
				new String[]{"syds","tgrs"});
	}
}
