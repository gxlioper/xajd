/**

 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-24 ����11:01:37 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.zxsgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ-��ѯʦ����
 * @�๦������: 
 * @���ߣ�  ��־��[����:1060]
 * @ʱ�䣺 2014-4-24 ����11:01:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxsxxDao extends SuperDAOImpl<ZxsxxForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_XLZX_ZXSXXB");
		setKey("zgh");
		setClass(ZxsxxForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxsxxForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.zgh,t.zxslv,t.zxszg,t.kjdrs,t.status,t.createsj,t.createbh,t.createmc,t.zxsjj,t.datazt,t.address,t1.xm,t1.xb,t1.lxdh,trunc((to_char(sysdate, 'yyyymmdd') - (to_char(to_date(t1.csrq,'yyyy-mm-dd'), 'yyyymmdd')))/10000) age,t1.bmmc ")
		   .append("from XG_XLZX_ZXSXXB t ")
		   .append("left join VIEW_FDYXX t1 ")
		   .append("on t.zgh = t1.zgh ")
		   .append("where 1=1 ")
		   .append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxsxxForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/** 
	 * @����:����ְ���Ų�ѯ��ʦ��Ϣ
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����02:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 */
	public HashMap<String,String> getFdyInfo(String zgh){
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select b.zgh,b.xm,b.xb,b.csrq, trunc((to_char(sysdate, 'yyyymmdd') - (to_char(to_date(b.csrq,'yyyy-mm-dd'), 'yyyymmdd')))/10000) age, b.bmdm, b.bmmc,b.lxdh" );
		sql.append(" from view_fdyxx b" ); 
		sql.append(" where b.zgh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zgh});
	}
	
	/** 
	 * @����:(����ְ���Ų�ѯ��ѯʦ��Ϣ�Ƿ����)
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����03:35:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * boolean �������� 
	 */
	public boolean zxsxxIsExist(String zgh) {
		String sql="select count(t.zgh) num from XG_XLZX_ZXSXXB t where t.zgh=?";
		String num = dao.getOneRs(sql.toString(), new String[]{zgh}, "num");
		return !num.equals("0");
	}
	
	/**
	 * 
	 * @����:���������ѯʦ   
	 *       ���� ������ľ�� [��][�̷߽�չ�о�����][20020964] [����10��][��ԤԼ5��]
	 * @���ߣ���־��
	 * @���ڣ�2014-4-30 ����04:36:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.zgh,t.lxdh,t.address,t1.xm,t1.xb,t1.bmmc,t.kjdrs,t.zxsjj,");
	    sql.append("(case when t.kjdrs is null then '����������' else '��������'||t.kjdrs||'��' end) kjdrsms,");
	    sql.append("(select count(g.zxid) from XG_XLZX_XLZXB_WZDX g where g.zxs = t.zgh and g.zzaprq = to_char(sysdate, 'yyyy-mm-dd')) yaprs ");
		sql.append(" ,'' yyrq " );
	    
	    // sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a where to_number(a.kjdrs) > to_number(a.yaprs) or a.kjdrs is null ");
		sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) ");
			
		  List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @����:���������ѯʦ  (����ԤԼʱ��) 
	 *       ���� ������ľ�� [��][�̷߽�չ�о�����][20020964] [����10��][��ԤԼ5��]
	 * @���ߣ���־��
	 * @���ڣ�2014-4-30 ����04:36:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 */
	public List<HashMap<String, String>> getZxsxxAllByYysjList(String yysj){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t.zgh,t.lxdh,t.address,t1.xm,t1.xb,t1.bmmc,t.kjdrs,");
		sql.append("(case when t.kjdrs is null then '����������' else '��������'||t.kjdrs||'��' end) kjdrsms,");
		sql.append(" (select count(zxid) ");
		sql.append("  from ((select zxid, zzaprq,zxs from XG_XLZX_XLZXB_WZDX where sqid is null) ");
		sql.append("         union (select a.zxid, a.zzaprq,a.zxs ");
		sql.append("                   from XG_XLZX_XLZXB_WZDX a ");
		sql.append("                  inner join XG_XLZX_YYSQB_WZDX b ");
		sql.append("                    on b.sqid = a.sqid	 ");
		sql.append("                 where b.yyzt = '2')) a ");
		sql.append("   where a.zzaprq = '" + yysj + "' and a.zxs = t.zgh ");
		sql.append("     ) yaprs ");
		sql.append(" ,'" +yysj + "' yyrq " );
		//sql.append("(select count(g.zxid) from XG_XLZX_XLZXB_WZDX g where g.zxs = t.zgh and g.zzaprq = '"+yysj+"') yaprs ");
		//sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a where to_number(a.kjdrs) > to_number(a.yaprs) or a.kjdrs is null ");
		sql.append("from XG_XLZX_ZXSXXB t left join view_fdyxx t1 on t.zgh = t1.zgh where t.status='1' ) a ");		
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), new String[]{});
		return list;
	}
	
	/**
	 * 
	 * @����:������ѯʦ�ڸ�״̬
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����09:14:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh ְ����
	 * @param status �ڸ�״̬
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxsxxStatus(String zgh, String status) throws Exception{
		String sql = "update XG_XLZX_ZXSXXB set STATUS=? where ZGH in ("+zgh+")";
		boolean result = dao.runUpdate(sql, new String[] { status });
		return result;
	}
	
	/**
	 * 
	 * @����:������ѯԤԼ˵��
	 * @���ߣ���־��
	 * @���ڣ�2014-4-25 ����09:43:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zxyysm ��ѯԤԼ˵��
	 * @return
	 * boolean �������� 
	 */
	public boolean setZxyysm(String zxyysm) throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("MERGE INTO xljk_zxyysm a ")
		   .append("USING (select count(1) count from xljk_zxyysm) b ")
		   .append("ON (b.count>0) ")
		   .append("when matched then ")
		   .append("update set zxyysm = ? ")
		   .append("when not matched then ")
		   .append("insert values( ? ) ");
		boolean result = dao.runUpdate(sql.toString(), new String[] { zxyysm,zxyysm });
		return result;
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

}
