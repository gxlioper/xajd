/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����02:35:20 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-2-18 ����02:35:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxdkSqDao extends SuperDAOImpl<XnwxdkSqModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkSqModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxdkSqModel t, User user)
			throws Exception {
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
		sql.append(" t1.zymc,t1.sfzh,t1.xz,t1.sjhm,t1.xmsj sjdh,t1.dzyx,");
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
		sql.append(" t.shzt) shztmc,");
		sql.append(" t2.xqmc");
		sql.append(" from xg_zdgxh_wxjk_sqb t");
		sql.append(" left join view_xsbfxx t1");
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
		super.setClass(XnwxdkSqModel.class);
		super.setKey("sqid");
		super.setTableName("xg_zdgxh_wxjk_sqb");
	}
	
	/**
	 * 
	 *�������ӽ��
	 */
	public boolean saveZsjg(XyzsSqForm model, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
    	sql.append("insert into xg_gygl_xyzsgl_sqb(sqbh,xh,sqsjstart,sqsjend,sqsj,shzt,splc,xxdz,lxdh,parentslxdy,xl,zwjzyy,bz,xn) values(xyzsgl_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	boolean flag = dao.runUpdate2(sql.toString(),new String[]{model.getXh(),model.getSqsjstart(),model.getSqsjend(),model.getSqsj(),model.getShzt(),model.getSplc(),model.getXxdz(),model.getLxdh(),model.getParentslxdy(),model.getXl(),model.getZwjzyy(),model.getBz(),model.getXn()},"xg_gygl_xyzsgl_sqb",user);
		return flag;
	}
	
	/*
	 * ����������ʱ���ȡѧ��������Ϣ�Լ�ס��������Ϣ
	 */
	public HashMap<String, String> getXyzsxxMap(XyzsSqForm model, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,t3.xlmc,");
		sql.append(" substr(t2.sqsjstart, 0, 4) || '��' || substr(t2.sqsjstart, 5, 2) || '��' ||substr(t2.sqsjstart, 7, 2) || '��' ||'��'||substr(t2.sqsjend, 0, 4) || '��' || substr(t2.sqsjend, 5, 2) || '��' ||substr(t2.sqsjend, 7, 2) || '��' as sqsj ");
		sql.append("from xg_gygl_xyzsgl t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh join xldmb t3 on t2.xl = t3.xldm)");
		sql.append(" t where sqbh = ?  ");
		return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
	}
	
	/**
	 *��ȡѧ�������ѧ������map,��������ʹ��
     *
	 */
	public List<HashMap<String, String>> getXl(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select xldm,xlmc from xldmb");
		return dao.getList(sql.toString(),new String[] {},new String[] { "xldm", "xlmc" });
	}
	
	/**
	 * �鿴ʱ��ʾѧ������
	 */
	public HashMap<String, String> getXlCk(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select t1.xldm,t1.xlmc from  xg_gygl_xyzsgl_sqb t");
    	sql.append(" join xldmb t1 on t.xl = t1.xldm  where t.sqbh = ?");
    	return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
	}
	
	/**
	 *�鿴ʱ��ʾ����У��ס�޵�ԭ��
	 */
	public HashMap<String, String> getXyZsyy(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select t1.dm,t1.mc from  xg_gygl_xyzsgl_sqb t");
    	sql.append(" join xg_gygl_xyzsgl_zwjzyydmb t1 on t.zwjzyy = t1.dm  where t.sqbh = ?");
    	return dao.getMapNotOut(sql.toString(),new String[]{model.getSqbh()});
	}
	
	//��ȡ������
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_zdgxh_wxjk_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	//��ȡ������
	public String getJesx() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select jesx from xg_zdgxh_wxjk_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "jesx");
	}
	
	//��ȡspid
	public String getSqbh(XnwxdkSqModel model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sqid from xg_zdgxh_wxjk_sqb where xh = ? and  xn = ? and xq = ?");
		return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXq()}, "sqid");
	}
	
	//��ȡ���״̬����
	public String getShztMc(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select decode(t2.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t2.shzt) shztmc from xg_gygl_xyzsgl_sqb t2 where sqbh = ? ");
		return dao.getOneRs(sql.toString(), new String[] {model.getSqbh()}, "shztmc");
	}

}
