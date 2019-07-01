/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-13 ����11:39:31 
 */
package com.zfsoft.xgxt.khgl.khbgl.khnrgl;


import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������ݹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-13 ����11:39:31
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KhnrglDao extends SuperDAOImpl<KhnrglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(KhnrglForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����10:30:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhnrList(String khbid)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,case when t1.fzlx ='0' then t1.fzzdf||'��'||t1.fzzgf else t1.fzzdf end fzqj,");
		sql.append(" case when t1.pflx ='1' then '�ӷ�' else '����' end pflxmc from xg_khgl_tk_zbx t1");
		sql.append(" where khbid=? order by to_number(xssx)");
		return dao.getListNotOut(sql.toString(), new String[]{khbid});
	}

	public List<HashMap<String, String>> getTeaList(KhnrglForm model, User user)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql.append("select rownum r,a.* from (select b.zgh yhm,b.xm,b.bmdm,c.bmmc from fdyxxb b ");
		sql.append(" left join zxbz_xxbmdm c on b.bmdm= c.bmdm");
		sql.append(")a where 1=1");
		sql.append(searchTj);
		sql.append("order by bmdm desc");
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����04:38:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getKhnr(KhnrglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,decode(t1.fzlx,'0','����','�̶�') fzlxmc,case when t1.fzlx ='0' then t1.fzzdf||'��'||t1.fzzgf else t1.fzzdf end fzqj,");
		sql.append(" case when t1.pflx ='1' then '�ӷ�' else '����' end pflxmc from xg_khgl_tk_zbx t1");
		sql.append(" where zbmxid=?");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getZbmxid() });
	}


	/**
	 * 
	 * ɾ����������
	 */
	public boolean delKhnrgl(String Zbmxid) throws Exception {
		String sql = "delete from xg_khgl_tk_zbx where Zbmxid=?";
		return dao.runUpdate(sql, new String[] { Zbmxid });
	}
	
	/**
	 * 
	 * @����:���������б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-13 ����03:27:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhnrList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select *from xg_khgl_tk_zbx");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * 
	 * @����:��ȡ��ʾ˳��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-18 ����10:35:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getXssx(String khbid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_number(count(1))+1 xssx from xg_khgl_tk_zbx t1 where khbid=?");
		return dao.getOneRs(sql.toString(), new String[]{khbid}, "xssx");
	}
	/**
	 * 
	 * @����:�жϿ��������Ƿ��Ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-9-1 ����11:12:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHave(KhnrglForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) num from xg_khgl_tk_zbx where khnr=? and khbid=? ");
		if(null!=model.getKhbid()){
			sql.append(" and zbmxid<>'"+model.getZbmxid()+"' ");	
		}
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getKhnr(),model.getKhbid()}, "num");
		return Integer.parseInt(num)>0;
	}
	@Override
	protected void setTableInfo() {
		super.setClass(KhnrglForm.class);
		super.setKey("zbmxid");
		super.setTableName("xg_khgl_tk_zbx");
	}
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KhnrglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
}
