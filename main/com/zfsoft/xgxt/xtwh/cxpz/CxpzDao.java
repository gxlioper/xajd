/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-27 ����02:22:07 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �Զ����ѯ�� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-5-27 ����02:22:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpzDao extends SuperDAOImpl<CxpzForm> {


	@Override
	protected void setTableInfo() {

	}


	@Override
	public List<HashMap<String, String>> getPageList(CxpzForm t)
			throws Exception {
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CxpzForm t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @����: ��ȡ��ѯ�����б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-27 ����03:27:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCxgnList(String gnmc){
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select gnbz,gnmc from xg_xtwh_zdycx_cxgnb ");
		
		if (!StringUtils.isNull(gnmc)){
			sql.append("where gnmc like '%||?||%' ");
			params.add(gnmc);
		}
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @����: �����ܱ�־��ѯ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-27 ����03:30:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnbz
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getColList(String gnbz){
		
		String sql = "select * from xg_xtwh_zdycx_zdpzb where gnbz=? and (kfxg is null or kfxg='Y') order by to_number(nvl(xssx,'0'))";
		
		return dao.getListNotOut(sql, new String[]{gnbz});
	}
	
	
	
	/**
	 * 
	 * @����: ������������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-29 ����05:10:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateColInfo(CxpzForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xtwh_zdycx_zdpzb set ");
		sql.append(model.getKey());
		sql.append("=? where guid=? ");
		
		return dao.runUpdate2(sql.toString(), new String[]{model.getValue(),model.getGuid()});
	}
	
	
	
	/**
	 * 
	 * @����: ��ȡ��ѯ������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-31 ����01:50:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnbz
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCxpz(String gnbz){
		
		String sql = "select caption,pagerid as pager,url,sortname,sortorder from xg_xtwh_zdycx_cxpzb where gnbz = ?";
		
		return dao.getMapNotOut(sql, new String[]{gnbz});
	}
	
	
	
	/**
	 * 
	 * @����: ������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-31 ����02:02:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnbz
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllColList(String gnbz){
		
		String sql = "select label,name,pxzd,nvl(width,'1') width,iskey,ishidden,formatter from xg_xtwh_zdycx_zdpzb where gnbz=? order by to_number(nvl(xssx,'0')) ";
		
		return dao.getListNotOut(sql, new String[]{gnbz});
	}
}
