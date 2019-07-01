/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-25 ����11:28:57 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-ѧ��ԤԼ��ѯ
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-25 ����11:28:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyysqDao extends SuperDAOImpl<XsyysqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLZX_YYSQB_WZDX");
		setKey("sqid");
		setClass(XsyysqForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsyysqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsyysqForm t, User user)
			throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select sqid || zxid pkid,sqid,zxid,xh,xm,xb,yyzt,cjsj,zzaprq,zxs,zxsxm,zxzt,xspjzt from(")
		   .append("select t.sqid,t2.zxid,t.xh,t1.xm,t1.xb,t.yyzt,t.cjsj,t2.zzaprq,t2.zxs,t3.xm zxsxm,t2.zxzt,")
		   .append("(case when t2.xszxpj is null then 'δ����' else '������' end) xspjzt ")
		   .append("from XG_XLZX_YYSQB_WZDX t ")
		   .append("left join VIEW_XSBFXX t1 ")
		   .append("on t.xh = t1.xh ")
		   .append("left join XG_XLZX_XLZXB_WZDX t2 ")
		   .append("on t.sqid = t2.sqid ")
		   .append("left join VIEW_FDYXX t3 ")
		   .append("on t2.zxs = t3.zgh ")
		   .append("union ")
		   .append("select a.sqid,a.zxid,a.xh,a1.xm,a1.xb,a2.yyzt,a2.cjsj,a.zzaprq,a.zxs,a3.xm zxsxm,a.zxzt,")
		   .append("(case when a.xszxpj is null then 'δ����' else '������' end) xspjzt ")
		   .append("from XG_XLZX_XLZXB_WZDX a ")
		   .append("left join VIEW_XSBFXX a1 ")
		   .append("on a.xh = a1.xh ")
		   .append("left join XG_XLZX_YYSQB_WZDX a2 ")
		   .append("on a.sqid = a2.sqid ")
		   .append("left join VIEW_FDYXX a3 ")
		   .append("on a.zxs = a3.zgh ) t ")
		   .append("where 1 = 1 ")
		   .append(searchTjByUser);
		
		return getPageList(t, sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:��ѯ��ѯԤԼ˵��
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����10:14:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param 
	 * @return
	 * boolean �������� 
	 */
	public String getZxyysm() throws Exception{
		String sql="select t.zxyysm from xljk_zxyysm t ";
		String zxyysm = dao.getOneRs(sql.toString(), new String[]{}, "zxyysm");
		return zxyysm;
	}
	
	/**
	 * 
	 * @����:�������������������
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����03:17:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc from XG_XLJK_XLWJYJ_XLWTLX t ");
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @����:�����ѡ��������������
	 * @���ߣ���־��
	 * @���ڣ�2014-4-29 ����02:17:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxdmstr   ����'23','123','213'
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllListByLxdm(String lxdmstr){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.lxdm,t.lxmc from XG_XLJK_XLWJYJ_XLWTLX t where t.lxdm in (")
		.append(lxdmstr)
		.append(")");
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @����: ȡ��ѧ��ԤԼ��ѯ����
	 * @���ߣ���־��
	 * @���ڣ�2014-4-29 ����11:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid 
	 * @param qxyyyy ȡ��ԤԼԭ��
	 * @param yyzt ԤԼ״̬
	 * @return
	 * boolean �������� 
	 */
	public boolean cancelXsyysq(String sqid, String qxyyyy, String yyzt) throws Exception{
		String sql = "update XG_XLZX_YYSQB_WZDX set qxyyyy=?,yyzt=? where sqid=?";
		boolean result = dao.runUpdate(sql, new String[] { qxyyyy, yyzt, sqid });
		return result;
	}
	
	/**
	 * 
	 * @����: ѧ����ѯ����(��������id)
	 * @���ߣ���־��
	 * @���ڣ�2014-5-7 ����11:46:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid 
	 * @param zxxgmydpf ��ѯЧ�����������
	 * @param xszxpj ѧ����ѯ����
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxpj(String sqid, String zxxgmydpf, String xszxpj) throws Exception{
		String sql = "update XG_XLZX_XLZXB_WZDX set zxxgmydpf=?, xszxpj=? where sqid=?";
		boolean result = dao.runUpdate(sql, new String[] { zxxgmydpf, xszxpj, sqid });
		return result;
	}
	
	/**
	 * 
	 * @����: ѧ����ѯ����(������ѯid)
	 * @���ߣ���־��
	 * @���ڣ�2014-5-9 ����15:00:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zxid 
	 * @param zxxgmydpf ��ѯЧ�����������
	 * @param xszxpj ѧ����ѯ����
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxpjByZxid(String zxid, String zxxgmydpf, String xszxpj) throws Exception{
		String sql = "update XG_XLZX_XLZXB_WZDX set zxxgmydpf=?, xszxpj=? where zxid=?";
		boolean result = dao.runUpdate(sql, new String[] { zxxgmydpf, xszxpj, zxid });
		return result;
	}

}
