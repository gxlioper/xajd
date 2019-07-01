/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-8 ����10:08:51 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsq;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-2-8 ����10:08:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcsqDAO extends SuperDAOImpl<ZcsqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcsqForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcsqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select *");
		sql.append(" from (select t.*,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XB,");
		sql.append(" t1.XM,");
		sql.append(" t1.csrq,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,");
		sql.append(" t2.jc,");
		sql.append(" t3.dzbmc,");
		sql.append(" decode(t.shzt,");
		sql.append(" '0',");
		sql.append(" 'δ�ύ',");
		sql.append(" '1',");
		sql.append(" 'ͨ��',");
		sql.append(" '2',");
		sql.append(" '��ͨ��',");
		sql.append(" '3',");
		sql.append(" '���˻�',");
		sql.append(" '5',");
		sql.append(" '�����',");
		sql.append("  t.shzt) shztmc");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcsqb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join zzmmdmb t2");
		sql.append(" on t1.ZZMM = t2.zzmmdm");
		sql.append(" left join xg_dtjs_zzgxzc_dzbdmb t3");
		sql.append("  on t.szdzb = t3.dzbdm) t");
		sql.append("  where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		this.setClass(ZcsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_dtjs_zzgxzc_zzgxzcsqb");
	}
	
	/**
	 * 
	 * @����: ��֧������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-8 ����05:15:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDzbList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from XG_DTJS_ZZGXZC_DZBDMB t");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: �鿴ת������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����02:05:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> ckZcsq(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select t.*,t2.jc,t3.dzbmc");
		sql.append("  from xg_dtjs_zzgxzc_zzgxzcsqb t");
		sql.append("  left join view_xsxxb t1");
		sql.append("  on t.xh = t1.XH");
		sql.append("  left join zzmmdmb t2");
		sql.append("  on t1.ZZMM = t2.zzmmdm");
		sql.append("  left join xg_dtjs_zzgxzc_dzbdmb t3");
		sql.append("  on t.szdzb = t3.dzbdm");
		sql.append("  where t.xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����: �Ƿ񲻴�������ͽ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-9 ����03:21:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean IsNotExist(ZcsqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from ((select xh");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcsqb");
		sql.append(" union");
		sql.append(" select xh");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcjgb))"); 
		sql.append("  where xh = ?"); 
		String num = dao.getOneRs(sql.toString(), new String[]{model.getXh()}, "num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @����: ��֤���ʸ�ҳ���ѧ���Ƿ�Ϊ��Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-2-10 ����09:35:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param username
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean IsDy(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.jc from view_xsxxb t");
		sql.append(" left join zzmmdmb t1");
		sql.append(" on t.ZZMM = t1.zzmmdm");
		sql.append(" where t.xh = ?");
		String jc = dao.getOneRs(sql.toString(), new String[]{username},"jc");
		return "��ʽ��Ա".equals(jc) || "Ԥ����Ա".equals(jc) ? true : false;
	}
}
