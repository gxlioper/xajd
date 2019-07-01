/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-25 ����01:32:30 
 */  
package xsgzgl.gygl.xyzsgl.sq;

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
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-5-25 ����01:32:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyzsSqDao extends SuperDAOImpl<XyzsSqForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyzsSqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyzsSqForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.*,t1.xm,t1.xb, t1.zydm, t1.nj, t1.bjdm,t1.xydm,t1.xymc,t1.bjmc,t1.zymc,t1.sfzh,");
		sql.append("decode(t2.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����',t2.shzt) shztmc,t1.mz mzmc,t1.zzmmmc, ");
		sql.append(" substr(t2.sqsjstart, 0, 4) || '-' || substr(t2.sqsjstart, 5, 2) || '-' ||substr(t2.sqsjstart, 7, 2) ||'  ' ||'��' || ' ' ||substr(t2.sqsjend, 0, 4) || '-' || substr(t2.sqsjend, 5, 2) || '-' ||substr(t2.sqsjend, 7, 2)  as sqsj1 ");
		sql.append("from xg_gygl_xyzsgl_sqb t2 join VIEW_XSJBXX t1 on t2.xh = t1.xh)");
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
		super.setClass(XyzsSqForm.class);
		super.setKey("sqbh");
		super.setTableName("XG_GYGL_XYZSGL_SQB");//xg_gygl_xyzsgl_sqb
		
	}
	
	/**
	 * ��ȡ�����סԭ��
	 */
	public List<HashMap<String, String>> getZyjzxx(XyzsSqForm model) {
		StringBuffer sql = new StringBuffer();
    	sql.append("select dm,mc from xg_gygl_xyzsgl_zwjzyydmb");
//		sql.append(" where t1.xh=? and t2.yrdwdm=? and t1.zgzt='zg' order by t2.xn ");
//		return dao.getList(sql.toString(), new String[] { model.getXh(), model.getYrdw() }, new String[] { "gwdm", "gwmc" });
		return dao.getList(sql.toString(),new String[] {},new String[] { "dm", "mc" });
	}
	
	/**
	 * �жϸ�ѧ������Уס�޽���Ƿ����
	 */
	 
		public boolean checkExistForSave(XyzsSqForm model) {
			boolean flag = false;
			StringBuilder sql = new StringBuilder();
			sql.append("select sqbh from xg_gygl_xyzsgl_sqb t where t.xh = ? and t.xn = ? ");
			String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),Base.currXn}, "sqbh");
			if (num != null && ! num.equals("") ){
				flag = true;
			}
			return flag;
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
			sql.append(" select splc from XG_GYGL_XYZS_JCSZ ");
			return dao.getOneRs(sql.toString(), new String[] {}, "splc");
		}
		
		//��ȡspid
		public String getSqbh(XyzsSqForm model) {
			StringBuffer sql = new StringBuffer();
			sql.append(" select sqbh from xg_gygl_xyzsgl_sqb where xh = ? and  xn = ? ");
			return dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn()}, "sqbh");
		}
		
		//��ȡ���״̬����
		public String getShztMc(XyzsSqForm model) {
			StringBuffer sql = new StringBuffer();
			sql.append(" select decode(t2.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t2.shzt) shztmc from xg_gygl_xyzsgl_sqb t2 where sqbh = ? ");
			return dao.getOneRs(sql.toString(), new String[] {model.getSqbh()}, "shztmc");
		}
}
