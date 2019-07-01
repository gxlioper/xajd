/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:07:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class DtxxshDao extends SuperDAOImpl<DtxxshForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DtxxshForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append("select * from (select t1.*,b.jdmc,t2.xm,t2.xb,t2.nj,t2.xymc,t2.xydm,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,tt.sydm,tt.symc,t2.zybj,t2.zybjmc,d.gwid,d.shr,d.shyj, f.mc || '[' || decode(d.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����') || ']' shztmc");
		sql.append(" , d.guid shid ");
		sql.append(" ,row_number() over(partition by t1.dtxxsqid order by d.shsj desc) rn ");
		sql.append(" from XG_DTJS_DTXXSQ t1");
		//ѧ����Ϣ
		sql.append(" left join view_xsxxb t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join XG_XTWH_SYBJGLB tx on t2.bjdm = tx.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB tt on tx.sydm = tt.sydm ");
		//�׶���Ϣ
		sql.append(" left join XG_DTJS_JBSZB b on t1.jddm=b.jddm");
		//���״̬��Ϣ
		sql.append(" left join xg_xtwh_shztb d");
		sql.append(" on t1.dtxxsqid = d.ywid");
		//��˸�λ����Ϣ
		sql.append(" left join xg_xtwh_spgwyh e");
		sql.append(" on d.gwid = e.spgw");
		sql.append(" left join xg_xtwh_spgw f");
		sql.append(" on d.gwid = f.id");
		sql.append(" where e.spyh = '"+user.getUserName()+"' and t1.shzt<>9 and d.shzt<>9 ");

		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (d.shzt<>0 and d.shzt<>4 )  ");
		}else{
			sql.append(" and ( d.shzt=0 or d.shzt=4 )  ");
		}	
		sql.append(" ) a where rn = 1 )a ");
		sql.append(" where 1 = 1");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ö�Ӧ���״̬
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-12 ����10:06:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String �������� 
	 */
	public String getShid(String ywid,String gwid){
		StringBuffer sql=new StringBuffer();
		sql.append("select guid from xg_xtwh_shztb sh where sh.ywid=? and sh.gwid=?");
		return dao.getOneRs(sql.toString(), new String[]{ywid,gwid}, "guid");
	}
	/**
	 * 
	 * @����:�޸�������Ϣ״̬��ԭ���ķ�����֧�ּ̳й�ϵ����ʹ��runUpdate(T t) ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-24 ����07:22:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @param zt
	 * @return boolean
	 * @throws Exception
	 * int �������� 
	 */
	public boolean updateSqxx(String id,String zt) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("update XG_DTJS_DTXXSQ set shzt=? where dtxxsqid=?");
		return dao.update(sql.toString(), new String[]{zt,id})>0?true:false;
	}
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DtxxshForm t)
			throws Exception {
		return null;
	}
	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("dtxxsqid");
		this.setTableName("XG_DTJS_DTXXSQ");
		this.setClass(DtxxshForm.class);
	}
	/**
	 * 
	 * @����:ɾ����Ӧ������Ϣ����Ľ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����09:43:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid ����id
	 * @return
	 * @throws Exception
	 * int �������� 
	 */
	public int deleteDtxxjgForDtxxsqId(String sqid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete XG_DTJS_XSDTXXJLB dtxxjg where dtxxjg.sqid=?");
		return dao.runDelete(sql.toString(),new String[]{sqid});
	}
}
