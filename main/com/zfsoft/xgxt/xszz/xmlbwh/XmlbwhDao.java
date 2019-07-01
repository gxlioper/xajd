/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmlbwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀ���ά��
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmlbwhDao extends SuperDAOImpl<XmlbwhForm> {

	/**
	 * 
	 * @����:��ͨ��ѯ����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼: void ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmlbwhForm model)
			throws Exception {

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select lbdm,lbmc,lbsm");
		sql.append(" from xg_xszz_new_zzxmlbb where 1=1 ");

		if (!StringUtil.isNull(model.getLbmc())) {
			params.add(model.getLbmc());
			sql.append(" and lbmc like '%'||?||'%'");
		}

		return getPageList(model, sql.toString(), params
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @����:��ѯ����idֵ
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public int getMaxId() throws Exception {
		String sql = "select nvl(max(lbdm),0) bjdm from xg_xszz_new_zzxmlbb";
		return dao.getOneRsint(sql);
	}

	/**
	 * 
	 * @����:�ж��ظ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRepeat(XmlbwhForm model) throws Exception {
		boolean flag = false;
		String sql = "select count(*) count from xg_xszz_new_zzxmlbb t ";
		sql += " where t.lbmc=?";
		if(model.getLbdm() != null){
			sql += " and lbdm!='"+model.getLbdm()+"'";
		}
		String[] input = new String[1];
		String lbmc = model.getLbmc();
		if(lbmc!= null){
			lbmc = lbmc.trim();
		}
		input[0] = lbmc;
		String[] output = new String[1];
		output[0] = "count";
		String[] oneRs = dao.getOneRs(sql,input,output);/////�˷����쳣�ѱ���������������޷����� ��������������////������������////
		if(oneRs != null && oneRs.length > 0){
			if(!oneRs[0].equals("0")){
				flag = true;
			}
		}
		return flag;
	}
	
	

	/**
	 * 
	 * @����:�ɷ��޸�ɾ����֤
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(XmlbwhForm model) throws Exception {
		boolean flag = false;
		String sql = "select count(*)  from xg_xszz_new_zzxmdmb  ";
		sql += " where lbdm='"+model.getLbdm()+"'";
		int result = dao.getOneRsint(sql);/////�˷����쳣�ѱ�������������޷����� ��������������////
		if(result > 0){
			flag = true;
		}
		return flag;
	}


	/**
	 * 
	 * @����:�ɷ��޸�ɾ����֤
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-18 ����02:42:55
	 * @�޸ļ�¼:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		boolean flag = false;
		String sql = "select count(*)  from xg_xszz_new_zzxmdmb  ";
		sql += " where lbdm in("+values+")";
		int result = dao.getOneRsint(sql);/////�˷����쳣�ѱ�������������޷����� ��������������////
		if(result > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XmlbwhForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * 
	 * @����:��ȡ��Ŀ�����뼰����
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-19 ����02:19:00
	 * @�޸ļ�¼:
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select lbdm,lbmc ");
		sb.append(" from  xg_xszz_new_zzxmlbb ");
		return dao.getListNotOut(sb.toString(), null);
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmlbb");
		super.setKey("lbdm");
	}

}
