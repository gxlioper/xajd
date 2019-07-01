/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-28 ����11:53:54 
 */  
package com.zfsoft.xgxt.rcsw.zyfw.zyfwhz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�߷���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cp[���ţ�1352]
 * @ʱ�䣺 2016-12-28 ����11:53:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwDao extends SuperDAOImpl<ZyfwForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwForm t, User user)
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
		
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cp[���ţ�1352]
	 * @���ڣ�2016-12-28 ����02:46:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsxx(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xm,a.xb,a.xymc,a.zymc,a.bjmc ");
		sql.append(" from view_xsxxb a ");
		sql.append(" where a.xh=? ");
		return DAO.getInstance().getMap(sql.toString(), new String[]{xh}, new String[]{"xm","xb","xymc","zymc","bjmc"});
	}

	public List<HashMap<String, String>> getZyfwList(String xh, ZyfwForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select* from (select a.f_xm xm,a.f_xh xh,a.f_xb xb,a.f_yx xymc,a.f_bj bjmc,to_char(a.f_rq,'yyyy-MM-dd') rq,to_char(a.f_zyzfwnr) zyfwnr ,to_char(a.f_gzdd) gzdd,a.f_gzsc gzsc,a.f_xzhdzgbmmc bmmc,a.f_xzshrid bmshr "); 
		sql.append(" from zfsoft_bpmx.w_xszyfwqkdjb a left join ");
		sql.append(" zfsoft_bpmx.bpm_pro_run_his b on a.id=b.businesskey where b.status='2' and a.f_xh =?) t where 1=1 ");
		if (StringUtils.isNotNull(model.getQsrq())) {
			sql.append(" and rq>='"+model.getQsrq()+"'");
		}
		if (StringUtils.isNotNull(model.getJsrq())) {
			sql.append(" and rq<='"+model.getJsrq()+"'");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}

	/**
	 * @param model  
	 * @����:TODO�ܹ���ʱ��
	 * @���ߣ�cp[���ţ�1352]
	 * @���ڣ�2016-12-28 ����05:44:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> ��������
	 * @throws 
	 */
	public HashMap<String, String> getZsc(String xh, ZyfwForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select mod(sum(fz),60) fz,case when sum(fz)>60 then sum(xs)+floor(sum(fz)/60)" +
				" else sum(xs)  end xs from(select to_number(substr(a.f_gzsc,0,instr(a.f_gzsc,'С')-1 )) xs ," +
				" to_number(substr(a.f_gzsc,instr(a.f_gzsc,'ʱ')+1,instr(a.f_gzsc,'��')-5)) fz from zfsoft_bpmx.w_xszyfwqkdjb " +
				" a left join zfsoft_bpmx.bpm_pro_run_his b on a.id=b.businesskey where b.status ='2'  and  a.f_xh=? ");
		if (StringUtils.isNotNull(model.getQsrq())) {
			sql.append(" and to_char(a.f_rq,'yyyy-MM-dd')>='"+model.getQsrq()+"'");
		}
		if (StringUtils.isNotNull(model.getJsrq())) {
			sql.append(" and to_char(a.f_rq,'yyyy-MM-dd')<='"+model.getJsrq()+"'");
		}
		sql.append(") ");
		return DAO.getInstance().getMap(sql.toString(), new String[]{xh}, new String[]{"fz","xs"});
	}

}
