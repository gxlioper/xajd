/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-20 ����04:41:26 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxmc;

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
 * @�๦������: ����ά��-��������
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-20 ����04:41:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxmcDao extends SuperDAOImpl<DmwhJxmcForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxmcForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select t.* from (select a.*,b.jxlbmc,(case a.jsfs when '1' then '����' when '2' then '����' else a.jsfs end) jsfsmc,c.jxdjmc from xg_hjxxgl_jxmc a left join xg_hjxxgl_jxlb b on a.jxlbdm = b.jxlbdm left join xg_hjxxgl_jxdj c on a.jxdjdm = c.jxdjdm) t where 1 = 1 ");
		
		if (!StringUtil.isNull(t.getJxmcmc())){
			params.add(t.getJxmcmc());
			sql.append(" and jxmcmc like '%'||?||'%'");
		}
			
		sql.append(" order by to_number(jxmcdm) ");
			
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxmcForm t, User user)
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
		super.setTableName("xg_hjxxgl_jxmc");
		super.setKey("jxmcdm");
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
		String jxmcdm=dao.getOneRs("select max(jxmcdm) jxmcdm from xg_hjxxgl_jxmc ",new String[]{},"jxmcdm");
		//������������ݣ�Ĭ��Ϊ00
		if(StringUtils.isNull(jxmcdm)){jxmcdm="00";}
		String newId=String.valueOf(Integer.parseInt(jxmcdm)+1);
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
	public String checkExistForSave(DmwhJxmcForm model) {		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxmc where jxmcmc = ? and jxdjdm = ? and jsfs = ? and jxlbdm = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxmcmc().trim(), model.getJxdjdm(), model.getJsfs(), model.getJxlbdm()}, "num");
		
		return num;	
	}
	
	/**
	 * 
	 * @����: �޸��ظ���֤
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����03:01:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForUpdate(DmwhJxmcForm model) {		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxmc where jxmcmc = ? and jxdjdm = ? and jsfs = ? and jxlbdm = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxmcmc().trim(), model.getJxdjdm(), model.getJsfs(), model.getJxlbdm()}, "num");
		
		return num;	
	}
	
	/**
	 * 
	 * @����: ����ȼ�LIST
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-21 ����10:14:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jxlbdm
	 * @param jsfs
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxdjList(String jxlbdm, String jsfs) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.jxdjdm,t.jxdjmc from xg_hjxxgl_jxdj t ");
		
		sql.append(" where t.jxlbdm = '"+jxlbdm+"' and t.jsfs = '"+jsfs+"'"); 
		
		sql.append(" order by t.jxdjdm ");
		
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"jxdjdm","jxdjmc"});
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
		StringBuilder sql = new StringBuilder(" select distinct b.jxmcmc from xg_hjxxgl_sqb a,xg_hjxxgl_jxmc b where a.jxmcdm = to_char(b.jxmcdm) and a.jxmcdm in (" +value+") ");
		String[] jxmcmc = dao.getRs(sql.toString(), new String[]{}, "jxmcmc");
		
		return jxmcmc;
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
		StringBuilder sql = new StringBuilder(" select distinct b.jxmcmc from xg_hjxxgl_jgb a,xg_hjxxgl_jxmc b where a.jxmcdm = to_char(b.jxmcdm) and a.jxmcdm in (" +value+") ");
		String[] jxmcmc = dao.getRs(sql.toString(), new String[]{}, "jxmcmc");
		
		return jxmcmc;
	}
	
}
