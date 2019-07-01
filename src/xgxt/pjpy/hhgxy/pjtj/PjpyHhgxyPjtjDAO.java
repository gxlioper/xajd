package xgxt.pjpy.hhgxy.pjtj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyHhgxyPjtjDAO {
	
	/**
	 * ���ݰ༶��ѧ�ꡢѧ�ڻ�ȡ�༶��Ŀ
	 * @return
	 */
	public List<HashMap<String,String>>getKmmc(PjpyModel model){
		DAO dao=DAO.getInstance();
		String bjdm=model.getBjdm();
		String xn=model.getXn();
		String xq=model.getXq();
		String sql="select distinct(kcsbm),kcmc from view_cjb where bjdm=? and xn=? and xq=? ";
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{bjdm,xn,xq}, new String[]{"kcsbm","kcmc"});
		return list;
	}
	
	/**
	 * ���ݰ༶��ѧ�ꡢѧ�ڻ�ȡѧ���ɼ�
	 * @return
	 */
	public List<HashMap<String,String>>getXscj(PjpyModel model){
		DAO dao=DAO.getInstance();
		String bjdm=model.getBjdm();
		String xn=model.getXn();
		String xq=model.getXq();
		String sql="select xh,kcsbm,cj from view_cjb where bjdm=? and xn=? and xq=? ";
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{bjdm,xn,xq}, new String[]{"xh","kcsbm","cj"});
		return list;
	}
	
	/**
	 * ��ȡ�ۺϲ�����Ϣ
	 * @return
	 */
	public List<HashMap<String,String>>getZccj(PjpyModel model){
		DAO dao=DAO.getInstance();
		String bjdm=model.getBjdm();
		String xn=model.getXn();
		String xq=model.getXq();
		String sql=" select distinct(b.xh),b.xm,a.dcj,a.zcj,a.tcj,a.zpf,a.bz from view_cjb b left join view_zhszcp a  on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where b.bjdm=? and b.xn=? and b.xq=? ";
		String[]colist={"xh","xm","dcj","tcj","zcj","zpf","bz"};
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{bjdm,xn,xq},colist);
		return list;
		
	}
	
	/**
	 * ��ȡѧ������
	 */
	public HashMap<String,String>getXqmc(PjpyModel model){
		DAO dao=DAO.getInstance();
		String xq=model.getXq();
		String sql="select xqmc from xqdzb where xqdm=? ";
		HashMap<String,String>hashMap=dao.getMap(sql, new String[]{xq},new String[]{"xqmc"});
		return hashMap;
	}
	
}
