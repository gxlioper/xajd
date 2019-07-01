/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-24 ����11:22:55 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxxmsz;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��У��Ŀ����
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-24 ����11:22:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxxmszDao extends SuperDAOImpl<LxxmszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxxmszForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxxmszForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String paras = t.getXmmc();
		//URLDecoder.decode(URLDecoder.decode(t.getXlccmc(),"UTF-8"),"UTF-8");
		StringBuffer sql = new StringBuffer();
		List<String> inputV = new ArrayList<String>();
		sql.append(" select t.*,t.lxkssj || '��' || t.lxjssj qzsj from xg_cqsx_jqlx_xmsz t");
		if(xgxt.utils.String.StringUtils.isNotNull(paras)){
			paras = URLDecoder.decode(URLDecoder.decode(paras,"UTF-8"),"UTF-8");
		}
		if(xgxt.utils.String.StringUtils.isNotNull(paras)){
			sql.append(" where t.xmmc like ?");
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
		this.setClass(LxxmszForm.class);
		this.setKey("xmid");
		this.setTableName("xg_cqsx_jqlx_xmsz");
	}
	
	/**
	 * 
	 * @����: ��Ŀ�����Ƿ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:36:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String xmid,String xmmc){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) num from xg_cqsx_jqlx_xmsz");
		sql.append(" where xmmc = ? ");
		paraList.add(xmmc);
		if(StringUtils.isNotNull(xmid)){
			sql.append(" and xmid != ?");
			paraList.add(xmid);
		}
	    String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
	    return ("0").equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @����: �ж��Ƿ��ɾ�����ж����ݣ�xg_cqsx_jqlx_mdwh���Ƿ����õ���ɾ������Ŀ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-24 ����03:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean ifCanDel(String[] xmids){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("  select count(1) num from xg_cqsx_jqlx_mdwh where xmid in(");
		for (int i = 0; i < xmids.length; i++) {
			sql.append("?");
			paraList.add(xmids[i]);
			if(i != xmids.length-1){
				sql.append(",");
			}
		}
		sql.append("  )");
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return ("0").equals(num) ? true : false;
	}
}
