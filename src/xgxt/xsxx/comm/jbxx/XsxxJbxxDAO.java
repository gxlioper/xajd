package xgxt.xsxx.comm.jbxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;

public class XsxxJbxxDAO {
	
	/**
	 * ��ȡ�����б���Ϣ
	 * @param XsxxJbxxForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>> getXlkxx(XsxxJbxxForm model){
		
		DAO dao=DAO.getInstance();
		//����
		String tableName=model.getTableName();
		//�ֶδ���
		String zddm=model.getZddm();
		//�ֶ�����
		String zdmc=model.getZdmc();
		
		String sql=" select distinct("+zddm+") dm, "+zdmc+" mc from "+tableName;
		
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
		
	}
	
	/**
	 * ��ȡ�����б��ֶ�
	 * @param XsxxJbxxForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXllbList(XsxxJbxxForm model) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select b.zd,b.lyb,b.mc,b.dm,lydmwh from ");
		sql.append(" (select * from xg_xsxx_zdszb where (lrxs='�����б�' or lrxs='��ѡ��') and sfqy='��') ");
		sql.append(" a ,xg_xsxx_zdlyb b where a.zd=b.zd and a.lyb=b.lyb ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"zd", "lyb", "dm", "mc","lydmwh"});
	}
	
	/**
	 * ��ȡѧ�������Ϣ
	 * @param model
	 * @return HashMap<String,String>
	 * @author qlj
	 */
	public HashMap<String,String>getXsxgxx(XsxxJbxxForm model){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		//����ʾ�ֶ�
		StringBuilder zd=new StringBuilder();
		List<HashMap<String,String>>xszdList=getXszdxx(model);
		//�ж�����ʾ���ֶ��Ƿ����0
		if(xszdList.size()==0){
			return null;
		}
		
		List<String>outPut=new ArrayList<String>();
		for(int i=0;i<xszdList.size();i++){
			HashMap<String,String>xszdMap=xszdList.get(i);
			if(i==0){
				zd.append("a."+xszdMap.get("zd"));
			}else{
				zd.append(",a."+xszdMap.get("zd"));
			}
			outPut.add(xszdMap.get("zd"));
			if("jg".equalsIgnoreCase(xszdMap.get("zd"))
					&& !Base.isNull(xszdMap.get("lyb"))){
				outPut.add("jgmc");
				zd.append(",a.jgmc ");
			}else if("hkszd".equalsIgnoreCase(xszdMap.get("zd"))
					&& !Base.isNull(xszdMap.get("lyb"))){
				outPut.add("hkszdmc");
				zd.append(",a.hkszdmc ");
			}else if("syd".equalsIgnoreCase(xszdMap.get("zd"))
					&& !Base.isNull(xszdMap.get("lyb"))){
				outPut.add("sydmc");
				zd.append(",a.sydmc ");
			}
		}
		sql.append(" select "+zd+" from view_xsxxb a  where a.xh=? ");

		return dao.getMap(sql.toString(), new String[]{model.getXh()}, outPut.toArray(new String[]{}));
	}
	
	public List<HashMap<String,String>>getXszdxx(XsxxJbxxForm model){
		DAO dao=DAO.getInstance();
		
		String sql=" select a.zd,b.lyb from xg_xsxx_xsqwzb a,xg_xsxx_zdszb b where a.zd=b.zd  ";
		
		return dao.getList(sql, new String[]{}, new String[]{"zd","lyb"});
	}
	
	/**
	 * ��ȡѧ����ϸҳ��Ϣ
	 * @author qlj
	 */
	public List<HashMap<String, String>> getXxyxx(XsxxJbszForm model) {
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		StringBuilder query = new StringBuilder();
		
		sql.append(" select xsqdm,zd,zdszh,zdszl,zdm,lrxz,wkxz,lrxs,xgwz,zszl, ");
		sql.append(" zszh,sfqy,sfzk,sfzd,lyb,lydmwh,case when lydmwh ='yes'  ");
		sql.append(" or lyb is null  then zd else  mc end mc from(  ");
		sql.append(" select a.xsqdm,a.zd,a.zdszh,a.zdszl,a.zdm, ");
		sql.append(" a.lrxz,a.wkxz,a.lrxs,a.xgwz,a.zszl,a.zszh,a.sfqy,a.sfzk,a.sfzd,b.mc, b.lyb,b.lydmwh ");
		sql.append(" from xg_view_xsxx_xsqzd a ");
		sql.append(" left join xg_xsxx_zdlyb b on a.zd = b.zd) where 1=1 ");
		
		if (!Base.isNull(model.getSearch_sfqy())) {
			query.append(" and  sfqy='" + model.getSearch_sfqy() + "' ");
		}

		query.append(" order by xsqdm,zdszh,zdszl ");

		ArrayList<String> inPutList = new ArrayList<String>();

		String[] colList = new String[] { "xsqdm", "zd", "zdm", "zdszh",
				"zdszl", "lrxz", "wkxz", "lrxs", "sfqy", "sfzd", "sfzk","mc" };
		

		return dao.getList(sql.toString(), new String[]{}, colList);
	}
}
