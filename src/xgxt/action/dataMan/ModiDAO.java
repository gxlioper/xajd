package xgxt.action.dataMan;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class ModiDAO {
	
	/**
	 * ���������Ż�ȡ��λ�б�
	 * @param ssbh
	 * @return
	 */
	public List<HashMap<String,String>>getCwList(String ssbh){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select cwh dm,cwh mc from cwxxb a where 1=1 ");
		sql.append(" and  not exists(select 1 from xszsxxb b where a.ssbh=b.ssbh and a.cwh=b.cwh) ");
		if(!Base.isNull(ssbh)){
			sql.append(" and ssbh=? ");
		}
		//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
		String sb = "";
		if("12898".equals(Base.xxdm)){
			sb = "cwh";
		}else{
			sb = "to_number(cwh)";
		}
		sql.append(" order by "+sb+" asc");
		return dao.getList(sql.toString(), new String[]{ssbh}, new String[]{"dm","mc"});
	}
	
	/**
	 * ���������Ż�ȡ��λ�б�
	 * @param ssbh
	 * @return
	 */
	public List<HashMap<String,String>>getQsList(String sslx){
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select ssbh dm,ssbh mc from ssxxb a where 1=1 ");
		if("yfpss".equalsIgnoreCase(sslx)){
			sql.append(" and exists(select 1 from ssfpb b where a.ssbh=b.ssbh) ");
		}else if("wfpss".equalsIgnoreCase(sslx)){
			sql.append(" and not exists(select 1 from ssfpb b where a.ssbh=b.ssbh) ");
		}
		
		sql.append(" order by ssbh asc");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc"});
	}
}
