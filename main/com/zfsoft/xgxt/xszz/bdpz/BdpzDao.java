package com.zfsoft.xgxt.xszz.bdpz;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������ģ����Ű� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-28 ����08:56:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BdpzDao extends SuperDAOImpl<BdpzForm> {

	@Override
	public List<HashMap<String, String>> getPageList(BdpzForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(BdpzForm t, User user)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
	}

	
	
	/**
	 * ���ݹ���ģ���ʶ��ȡ�ֶ�����
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String, String>> getBdpz(String gnmk){
		List<HashMap<String,String>> mkxxList = null;
		String sql = "select zddm,zdmc,kjlx,zdcd,zdgs,sfbt,sjy,kjys,substr(sjy,INSTR(sjy,'#', 1, 3)+1,length(sjy))xlcs from xg_xszz_new_zdcjpzb where gnmk = ? and xxdm = ? order by to_number(nvl(xssx,0))";
		mkxxList = dao.getListNotOut(sql, new String[]{gnmk, Base.xxdm});
		if(null == mkxxList || mkxxList.size() == 0){
			mkxxList = dao.getListNotOut(sql, new String[]{gnmk, "public"});
		}
		return mkxxList;
	} 
	
	
	/**
	 * ���ݹ���ģ���ʶ��ȡ������Ϣ����
	 * @param gnmk
	 * @return
	 */
	public List<HashMap<String, String>> getJbxxpz(String gnmk){
		
		String sql = "select zddm,zdmc from xg_xszz_new_jbxxxspzb where gnmk = ? order by to_number(nvl(xssx,0))";
		
		return dao.getListNotOut(sql, new String[]{gnmk});
	} 
	
	
	/**
	 * ��̬��ȡ����Դ�б�
	 * @param params
	 * @return
	 */
	public List<HashMap<String,String>> getSjyList(String[] params){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append(params[0]);
		sql.append(" as dm,");
		sql.append(params[1]);
		sql.append(" as mc ");
		sql.append(" from ");
		sql.append(params[2]);
		
		return dao.getListNotOut(sql.toString(),new String[]{});
	}
}
