/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-13 ����05:17:59 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxdj;

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
 * @�๦������: ����ά��-����ȼ� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-13 ����05:17:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxdjDao extends SuperDAOImpl<DmwhJxdjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxdjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder(" select t.* from (select a.*,b.jxlbmc,(case a.jsfs when '1' then '����' when '2' then '����' else a.jsfs end) jsfsmc from xg_hjxxgl_jxdj a left join xg_hjxxgl_jxlb b on a.jxlbdm = b.jxlbdm) t where 1 = 1 ");
		
		if (!StringUtil.isNull(t.getJxdjmc())){
			params.add(t.getJxdjmc());
			sql.append(" and jxdjmc like '%'||?||'%'");
		}
			
		sql.append(" order by to_number(jxdjdm) ");
			
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DmwhJxdjForm t, User user)
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
		super.setTableName("xg_hjxxgl_jxdj");
		super.setKey("jxdjdm");
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
		String jxdjdm=dao.getOneRs("select max(jxdjdm) jxdjdm from xg_hjxxgl_jxdj ",new String[]{},"jxdjdm");
		//������������ݣ�Ĭ��Ϊ00
		if(StringUtils.isNull(jxdjdm)){jxdjdm="00";}
		String newId=String.valueOf(Integer.parseInt(jxdjdm)+1);
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
	public String checkExistForSave(DmwhJxdjForm model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxdj where jxdjmc = ? and jxlbdm = ? and jsfs = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxdjmc().trim(), model.getJxlbdm(), model.getJsfs()}, "num");
		
		return num;		
	}
	
	/**
	 * 
	 * @����: �������list
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-20 ����11:26:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJxlbList() {
		String sql = "select jxlbdm,jxlbmc from xg_hjxxgl_jxlb order by jxlbdm";
		return dao.getList(sql, new String[] {}, new String[] { "jxlbdm", "jxlbmc" });	
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
	public String checkExistForUpdate(DmwhJxdjForm model) {
		
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_hjxxgl_jxdj where jxdjmc = ? and jxlbdm = ? and jsfs = ? ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getJxdjmc().trim(), model.getJxlbdm(), model.getJsfs()}, "num");
		
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
		StringBuilder sql = new StringBuilder(" select distinct b.jxdjmc from xg_hjxxgl_sqb a,xg_hjxxgl_jxdj b where a.jxdjdm = to_char(b.jxdjdm) and a.jxdjdm in (" +value+") ");
		String[] jxdjmc = dao.getRs(sql.toString(), new String[]{}, "jxdjmc");
		
		return jxdjmc;
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
		StringBuilder sql = new StringBuilder(" select distinct b.jxdjmc from xg_hjxxgl_jgb a,xg_hjxxgl_jxdj b where a.jxdjdm = to_char(b.jxdjdm) and a.jxdjdm in (" +value+") ");
		String[] jxdjmc = dao.getRs(sql.toString(), new String[]{}, "jxdjmc");
		
		return jxdjmc;
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
		StringBuilder sql = new StringBuilder(" select distinct b.jxdjmc from xg_hjxxgl_jxmc a,xg_hjxxgl_jxdj b where a.jxdjdm = to_char(b.jxdjdm) and a.jxdjdm in (" +value+") ");
		String[] jxdjmc = dao.getRs(sql.toString(), new String[]{}, "jxdjmc");
		
		return jxdjmc;
	}
}
