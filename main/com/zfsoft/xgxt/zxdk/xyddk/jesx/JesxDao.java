/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-7 ����03:14:08 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.jesx;

import java.net.URLDecoder;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-7 ����03:14:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JesxDao extends SuperDAOImpl<JesxForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JesxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JesxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String paras = t.getXlccmc();//URLDecoder.decode(URLDecoder.decode(t.getXlccmc(),"UTF-8"),"UTF-8");
		StringBuffer sql = new StringBuffer();
		List<String> inputV = new ArrayList<String>();
		sql.append(" select * from (");
		sql.append(" select t.xlccdm, t1.pyccmc xlccmc, t.jesx");
		sql.append(" from XG_ZXDK_DKSXB t");
		sql.append(" left join xg_xsxx_pyccdmb t1");
		sql.append(" on t.xlccdm = t1.pyccdm");
		sql.append(" ) t ");
		if(xgxt.utils.String.StringUtils.isNotNull(paras)){
			paras = URLDecoder.decode(URLDecoder.decode(t.getXlccmc(),"UTF-8"),"UTF-8");
			sql.append("where t.xlccmc like ?");
			inputV.add("%"+paras+"%");
		}
		return getPageList(t, sql.toString(),inputV.toArray(new String[]{}) );
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(JesxForm.class);
		this.setKey("xlccdm");
		this.setTableName("XG_ZXDK_DKSXB");
		
	}
	
	/**
	 * 
	 * @����:��ȡ������¼����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-7 ����05:43:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJesxMap(String xlccdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.xlccdm, t1.pyccmc xlccmc, t.jesx");
		sql.append(" from XG_ZXDK_DKSXB t");
		sql.append(" left join xg_xsxx_pyccdmb t1");
		sql.append(" on t.xlccdm = t1.pyccdm");
		sql.append(" where t.xlccdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xlccdm});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-7 ����05:51:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRs(String[] xlccdms,String[] jesxs) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from XG_ZXDK_DKSXB where xlccdm in (");
		for (int i = 0; i < xlccdms.length; i++) {
			String xlccdm = xlccdms[i];
			sql.append("'"+xlccdm+"'");
			if(i != xlccdms.length-1){
				sql.append(",");
			}
			
			
		}
		sql.append(" )");
		paraList.add(sql.toString());
		for (int i = 0; i < xlccdms.length; i++) {
			StringBuilder sql1 = new StringBuilder();
			sql1.append(" insert into XG_ZXDK_DKSXB(xlccdm,jesx) values('"+xlccdms[i]+"','"+jesxs[i]+"')");
			paraList.add(sql1.toString());
		}
		
		int[] res =  dao.runBatch(paraList.toArray(new String[]{}));
		boolean flag = true;
		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 
	 * @����: �������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-8 ����09:51:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xlccdms
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJesxList(String[] xlccdms){
		StringBuffer sql = new StringBuffer();
		List<String> parameter = new ArrayList<String>();
		sql.append(" select t.xlccdm, t1.pyccmc xlccmc, t.jesx");
		sql.append(" from XG_ZXDK_DKSXB t");
		sql.append(" left join xg_xsxx_pyccdmb t1");
		sql.append(" on t.xlccdm = t1.pyccdm");
		sql.append(" where t.xlccdm in (");
		for (int i = 0; i < xlccdms.length; i++) {
			sql.append("?");
			parameter.add(xlccdms[i]);
			if(i != xlccdms.length-1){
			  sql.append(",");
			}
		}
		sql.append(" )");
		return dao.getListNotOut(sql.toString(),parameter.toArray(new String[]{}));
	}
	

}
