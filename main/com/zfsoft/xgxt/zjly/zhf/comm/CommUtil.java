/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-20 ����03:00:56 
 */  
package com.zfsoft.xgxt.zjly.zhf.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-20 ����03:00:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CommUtil extends SuperDAOImpl {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List getPageList(Object t, User user) throws Exception {
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
	 * @����:����list
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����03:02:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param userdept
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
    public List<HashMap<String, String>> getDxList(String userdept,String username){
		StringBuilder sql = new StringBuilder();
		sql.append("  select xmmkdm,xmmkmc from xg_zjly_zhszf_mkxmb   ");
		ArrayList<String > paralist = new ArrayList<String>();
		//todo��Ȩ����
    	if(!username.equals("zf01")){
    		sql.append(" where xmmkdm in");
    		sql.append(" (");
    		sql.append(" select xmmkdm from xg_zjly_zhszf_jfxmb where jfxmdm in ");
    		sql.append(" (select jfxmdm ");
    		sql.append(" from xg_zjly_zhszf_xmsqb");
    		sql.append(" where bmdm = ?)");
    		paralist.add(userdept);
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" )");
    	}
		sql.append(" order by xmmkdm");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
    }
    
    /**
     * 
     * @����:С��list
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2016-6-20 ����03:03:03
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param userdept
     * @return
     * List<HashMap<String,String>> �������� 
     * @throws
     */
    public List<HashMap<String, String>> getXxList(String userdept,String username){
    	StringBuilder sql = new StringBuilder();
    	ArrayList<String > paralist = new ArrayList<String>();
    	sql.append("  select jfxmdm,jfxmmc,xmmkdm,khyd,fs from xg_zjly_zhszf_jfxmb ");
    	//todo��Ȩ����
    	if(!username.equals("zf01")){
    		sql.append(" where jfxmdm in");
    		sql.append(" (");
    		sql.append(" select jfxmdm ");
    		sql.append(" from xg_zjly_zhszf_xmsqb");
    		sql.append(" where bmdm = ?");
    		paralist.add(userdept);
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" ");
    		sql.append(" )");
    	}
		sql.append(" order by xmmkdm ,to_number(jfxmdm) ");
		
		return dao.getListNotOut(sql.toString(), paralist.toArray(new String[]{}));
	}
}
