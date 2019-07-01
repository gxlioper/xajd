/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-10 ����05:56:26 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.rzkh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-6-10 ����05:56:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RzkhDao extends SuperDAOImpl<rzkhjgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(rzkhjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(rzkhjgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.*,t1.xm,t1.xymc,t1.xydm,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.nj,to_char(to_date(t2.rzsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') rzsjname,t3.xqmc xqmc," +
				" t4.zwmc zwmcname   ");
		sql.append("from XG_SZDW_XSGBRZKHJG t2 , VIEW_XSJBXX t1,xqdzb t3,xg_szdw_xsgb_zwb t4 where t2.xh = t1.xh and t3.xqdm = t2.xq and t4.zwid = t2.zwmc)");
		sql.append(" t where 1= 1  ");
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
		super.setClass(rzkhjgForm.class);
		super.setKey("khjgid");
		super.setTableName("XG_SZDW_XSGBRZKHJG");
	}
	
	//��ȡְ��ѧ���ɲ�ְλ��Ϣlist,�������ӻ��޸Ŀ��˽��ʱ��ְ������������
	public List<HashMap<String, String>> getZwxxList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_szdw_xsgb_zwb ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	   
	//�жϸ�ѧ���ɲ��ڽ�������Ƿ��м�¼������в����䱣����Ϣ��������ʾ
	public boolean checkExistForSave(rzkhjgForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("select t.khjgid from XG_SZDW_XSGBRZKHJG t where t.xh = ? and t.xn = ? and t.xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXq()}, "khjgid");
		if (num != null && ! num.equals("") ){
			flag = true;
		}
		return flag;
	}
	
	//��ȡ�鿴ʱ����Ҫ��ְλ���ƣ�����ʱְλ���Ʊ������ְλ���룩
	public HashMap<String, String> getZwmc(String zwdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select zwmc from xg_szdw_xsgb_zwb where zwid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{zwdm});
	}
	
	//��ȡ�������˱�������ֶ���Ϣ
	public HashMap<String, String> getKhjgxxMap(rzkhjgForm model, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.xm,t1.xb,t1.zzmmmc zzmm,t1.xymc || t1.bjmc ddqd," +"t1.bjmc,"+
				"to_char(to_date(t1.csrq,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') csrq,");
		sql.append("(select t3.zwmc  from  xg_szdw_xsgb_zwb t3 where t3.zwid = t2.zwmc) zwmc,");
		sql.append(" to_char(to_date(t2.rzsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') rzsj,  ");
		sql.append(" t2.grsz,t2.zgdwyj khdj,t2.qdyj,t2.szdwyj,t2.ddyj,t2.xsgzcyj from   ");
		sql.append(" View_Xsbfxx t1,XG_SZDW_XSGBRZKHJG t2 where t1.xh = t2.xh  ");
		sql.append(" and t2.khjgid = ?  ");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getKhjgid()});
	}
	
	//��ȡ���˻��ܱ������ֶ���Ϣ
	public List<HashMap<String, String>> getKhjgxxList(rzkhjgForm model ) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.zwmc,to_char(to_date(t2.rzsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') rzsj,t2.grzp,t2.zgdwyj, t2.xsgzcyj,t2.bz,t2.xn ||' '||t2.xq || ':'  xnxq  ");
		sql.append("from xg_szdw_xsgb_zwb t1 ,XG_SZDW_XSGBRZKHJG t2 ");
		sql.append(" where t1.zwid=t2.zwmc and t2.xh = ? ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXh()});
	}
	
	//��ȡѧ������
	public HashMap<String, String> getxqdz(String xqdm){
		StringBuilder sql = new StringBuilder();
		sql.append("   select xqmc from xqdzb where xqdm = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xqdm});
	}
}
