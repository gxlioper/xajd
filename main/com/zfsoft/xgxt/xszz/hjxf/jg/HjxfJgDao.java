/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����11:52:03 
 */  
package com.zfsoft.xgxt.xszz.hjxf.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xszz.hjxf.unit.UnitForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-3-15 ����11:52:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjxfJgDao extends SuperDAOImpl<HjxfJgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	private static final String  RDZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	@Override
	public List<HashMap<String, String>> getPageList(HjxfJgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjxfJgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t.xn || t2.xqmc xnxq");
		sql.append(" from XG_XSZZ_NEW_HJXFJGB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh " +
				" left join xqdzb t2 on t.xq = t2.xqdm " +
				") t where 1= 1  ");
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
		// TODO �Զ����ɷ������
		super.setClass(HjxfJgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_XSZZ_NEW_HJXFJGB");
	}
	

	/**
	 * 
	 * @����:��ȡ������ƶ���ȼ�(���Ϊ����ʾ��ͥ���ò�����)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-3-16 ����02:22:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKnsdj(HjxfJgForm model){
		StringBuffer sql = new  StringBuffer();
		sql.append(" select t1.dcmc djmc ");
		sql.append(" from XG_XSZZ_NEW_HJXFJGB z");
		sql.append("   left join xg_xszz_new_knsjgb t ");
		sql.append(" on z.xh = t.xh and z.xn = t.xn");
		sql.append(" left join ");
		sql.append(" xg_xszz_new_kndcdmb t1");
		sql.append(" on t.rddc = t1.dcdm where z.jgid = ? and (t.xq = 'on' or t.xq = ?)");
	    String result = dao.getOneRs(sql.toString(), new String[]{model.getJgid(),model.getXq()}, "djmc");
		if(StringUtils.isNotNull(result)){
			return result;
		}else{
			return "��ͥ���ò�����";
		}
	}
	
	//ɾ����������Ѵ��ڵ�����
	//��������������ԭ��������е�����ʱ��ɾ��ԭ�������ݣ��ڽ��в���
	public boolean delDkjg(String xh,String xn,String xq)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from XG_XSZZ_NEW_HJXFJGB where xh = ? and xn = ? ");
		return dao.runUpdate(sql.toString(),new String[]{xh,xn});
	}
	
	//��ѯ
	public List<HashMap<String,String>> getJtknXshjList(String xn ,User user ){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xm,");
		sql.append(" t1.bjmc,");
		sql.append(" case t3.dcmc ");
		sql.append(" when '�ر�����' then  'A'");
		sql.append(" when 'һ������' then  'B'");
		sql.append(" when 'ͻ���¼�����' then 'C'");
		sql.append(" else   '' end dcmc,");
		sql.append(" case t.dkqk ");
		sql.append(" when '��Դ����ѧ����' then '��Դ��'");
		sql.append(" when '��У������ѧ����' then '��У'");
		sql.append(" when '��' then '��'");
		sql.append(" else '��' end dkqk,");
		sql.append(" t.hjje,");
		sql.append(" t.hjje+t.wnqfje ljqf,");
		sql.append(" t.wnqfje,");
		sql.append(" t.jqjzsj,");
		sql.append(" t.sqyy");
		sql.append(" from XG_XSZZ_NEW_HJXFJGB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh ");
		sql.append(" left join xg_xszz_new_knsjgb t2");
		sql.append(" on t.xh = t2.xh and  (t2.xq = 'on' or t2.xq is null or t.xq = t2.xq) and t.xn = t2.xn");
		sql.append(" left join xg_xszz_new_kndcdmb t3");
		sql.append(" on t2.rddc = t3.dcdm");
		sql.append(" where t.xn = ?");
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(searchTjByUser);
		return dao.getListNotOut(sql.toString(), new String[]{xn});
	}
	
	//���ݻ���
	public HashMap<String, String> getSumHz(String xn,User user){
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(t.hjje) hjjezh,sum(t.wnqfje+t.hjje) ljqfje  from XG_XSZZ_NEW_HJXFJGB t where xn = ?  ");
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		sql.append(searchTjByUser);
		return dao.getMapNotOut(sql.toString(), new String[]{xn});
	}
	
	//������ȡ�����ֹʱ��
	public HashMap<String, String> getJqNdYf(String jqjzsj){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(add_months(trunc(to_date(?,'yyyymmdd')),1),'yyyy') nd,to_char(add_months(trunc(to_date(?,'yyyymmdd')),1),'mm') yf from dual");
		return dao.getMapNotOut(sql.toString(), new String[]{jqjzsj,jqjzsj});
	}
	
	public boolean isNotExists(HjxfJgForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//���������ͽ������ظ���֤,qb(ȫ��)
	
			sql.append(" select count(1) flag from ");
			sql.append(" XG_XSZZ_NEW_HJXFJGB");
			sql.append(" where xn = ?");
			sql.append(" and xh = ?");
			sql.append(" and jgid <> ? ");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getJgid());
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
}
