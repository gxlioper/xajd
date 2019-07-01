/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-6 ����11:05:50 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxlb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ����ά��-������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-6 ����11:05:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxlbDao extends SuperDAOImpl<DmwhJxlbForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxlbForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select * from xg_hjxxgl_jxlb where 1=1 ");
		
		if (!StringUtil.isNull(t.getJxlbmc())){
			params.add(t.getJxlbmc());
			sql.append(" and jxlbmc like '%'||?||'%'");
		}
			
		sql.append(" order by to_number(jxlbdm) ");
			
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxlbForm t, User user)
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
		super.setTableName("xg_hjxxgl_jxlb");
		super.setKey("jxlbdm");
	}
	
	/**
	 * 
	 * @����: id����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����01:50:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getNextId(){
		String jxlbdm=dao.getOneRs("select max(jxlbdm) jxlbdm from xg_hjxxgl_jxlb ",new String[]{},"jxlbdm");
		//������������ݣ�Ĭ��Ϊ00
		if(StringUtils.isNull(jxlbdm)){jxlbdm="00";}
		String newId=String.valueOf(Integer.parseInt(jxlbdm)+1);
		if(newId.length()==1){
			newId="0"+newId;
		}
		return newId;
	}
	
	/**
	 * 
	 * @����: �����ظ���֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����03:01:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(DmwhJxlbForm model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxlb where jxlbmc = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxlbmc().trim()}, "num");
		
		return num;		
	}
	
	/**
	 * 
	 * @����: �޸��ظ���֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����03:06:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(DmwhJxlbForm model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxlb where jxlbmc= ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getJxlbmc().trim()}, "num");
		
		return num;
	}
	
	/**
	 * 
	 * @����: �����ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����05:26:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkSq(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_sqb a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
	}
	
	/**
	 * 
	 * @����: ����ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����05:27:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkJg(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_jgb a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
	}
	
	/**
	 * 
	 * @����:����ȼ��ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-19 ����09:05:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkJxdj(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_jxdj a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
	}
	
	/**
	 * 
	 * @����:���������ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-8-19 ����09:05:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] checkJxmc(String value) throws Exception{
		StringBuilder sql = new StringBuilder(" select distinct b.jxlbmc from xg_hjxxgl_jxmc a,xg_hjxxgl_jxlb b where a.jxlbdm = to_char(b.jxlbdm) and a.jxlbdm in (" +value+") ");
		String[] jxlbmc = dao.getRs(sql.toString(), new String[]{}, "jxlbmc");
		
		return jxlbmc;
	}
	
}
