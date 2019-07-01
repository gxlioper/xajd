/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-12 ����03:38:02 
 */  
package com.zfsoft.xgxt.comm.provicecitylocal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-12-12 ����03:38:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SsxDao extends SuperDAOImpl<SsxModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SsxModel t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SsxModel t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	
	/**
	 * 
	 * @����:��ȡʡ��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����03:50:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getProviceMap(){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> para = new ArrayList<String> ();
		sql.append(" select t.sydqdm provicedm, t.sydq provicename");
		sql.append(" from dmk_sydq t");
		sql.append(" order by t.sydqdm asc");
		return dao.getListNotOut(sql.toString(),para.toArray(new String[]{}) );
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����03:50:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCtiyMap(String provicedm){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> para = new ArrayList<String> ();
		sql.append(" select t.qxdm citydm,t.qxmc cityname");
		sql.append(" from dmk_qx t");
		sql.append(" where substr(t.qxdm,0,2) = ? and");
		para.add(provicedm);
		sql.append(" substr(t.qxdm, 3, 2) <> '00'");
		sql.append(" and substr(t.qxdm, 5, 2) = '00'");
		sql.append(" order by t.qxdm asc");
		return dao.getListNotOut(sql.toString(),para.toArray(new String[]{}) );
	}
	
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-12 ����03:50:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
    public List<HashMap<String, String>> getLocalMap(String provicedm,String ctiydm){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> para = new ArrayList<String> ();
		sql.append(" select t.qxdm localdm,t.qxmc localname");
		sql.append("  from dmk_qx t");
		sql.append("  where substr(t.qxdm,0,2) = ? and");
		para.add(provicedm);
		sql.append("  substr(t.qxdm, 3, 2) = ? ");
		para.add(ctiydm);
		sql.append("  and substr(t.qxdm, 5, 2) <> '00'");
		sql.append(" order by t.qxdm asc");
		return dao.getListNotOut(sql.toString(), para.toArray(new String[]{}));
	}
    
    /**
     * 
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-12-12 ����04:28:31
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @return
     * String �������� 
     * @throws
     */
    public String getSsxQcName(String dqdm){
    	String proflag = dqdm.substring(0, 2);
    	String cityflag = dqdm.substring(2, 4);
    	String localflag = dqdm.substring(4, 6);
    	String provicedm = proflag +"0000";
    	String ctiydm = null;
    	String localdm = null;
    	if(!cityflag.equalsIgnoreCase("00")){
    		ctiydm = proflag + cityflag +"00";
    	}
    	if(!localflag.equalsIgnoreCase("00")){
    		localdm = dqdm;
    	}
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String> para = new ArrayList<String> ();
    	sql.append(" select replace(wm_concat(t.qxmc), ';', '') qc");
    	sql.append("  from dmk_qx t");
    	sql.append("  where t.qxdm = ? ");
    	para.add(provicedm);
    	if(StringUtils.isNotNull(ctiydm)){
    		sql.append("  or t.qxdm = ? ");
    		para.add(ctiydm);
    	}
    	if(StringUtils.isNotNull(localdm)){
    		sql.append("  or t.qxdm = ? ");
    		para.add(localdm);
    	}
    	sql.append("  order by t.qxdm asc ");
    	return dao.getOneRs(sql.toString(), para.toArray(new String[]{}), "qc");
    }

}
