package com.zfsoft.xgxt.comm.bdpz.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ���Ű� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-28 ����08:56:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BdpzDao  {

	
	/**
	 * ���ݹ���ģ���ʶ��ȡ������Ϣ����
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String, String>> getJbxxpz(String gnmk){
		
		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> jbxxpz = null;
		
		String sql = "select zddm,zdmc from xg_xtwh_jbxxpzb where gnmk = ? and xxdm = ? order by to_number(nvl(xssx,0))";
		
		jbxxpz = dao.getListNotOut(sql, new String[]{gnmk,Base.xxdm});
		
		if( null==jbxxpz || jbxxpz.size()==0){
			jbxxpz = dao.getListNotOut(sql, new String[]{gnmk,"public"});
		}
		
		return jbxxpz;
		
	} 
	
	
}
