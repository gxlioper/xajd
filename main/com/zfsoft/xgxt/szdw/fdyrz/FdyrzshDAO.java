/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-4 ����05:06:22 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ�� 
 * @�๦������: TODO ����Ա��ְ���� ��ְ����
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-6-4 ����04:56:01 
 * @�汾�� V5.8.16
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyrzshDAO extends SuperDAOImpl<FdyrzsqForm>{

	/*
	 * ����: 
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	 * ����: ��ѯ��Ҫ��˵���ְ����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyrzsqForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" select * from ( ");
		sql.append("  select e.bmmc,a.zgh,e.xm,a.sqsj, a.sqdbgs ,a.sqid, b.guid shid,b.gwid,c.mc ||'['|| decode(b.shzt,0,'�����',1,'ͨ��',2,'��ͨ��',3,'�˻�',4,'������',5,'�����','����')||']' shzt,a.splc,b.shsj,t.zjz,t.bmdm   ");
		sql.append(" ,row_number()over(partition by a.sqid order by b.shsj desc) rn ");
		sql.append(" from xg_szdw_fdyrzsqb a ");
		sql.append("  join xg_xtwh_shztb b on a.sqid = b.ywid  ");
		sql.append(" join xg_xtwh_spgw c  on b.gwid = c.id ");
		sql.append(" join xg_xtwh_spgwyh d on c.id = d.spgw join view_fdyxx e on a.zgh = e.zgh left join fdyxxb t on a.zgh = t.zgh");
		sql.append(" where d.spyh = '"+user.getUserName()+"' and a.shzt<>9  and b.shzt <>9");
		String shlx = model.getShlx();

		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}
		sql.append("  )a where rn = 1 ");
		sql.append(searchTj);
		if (!"xx".equalsIgnoreCase(user.getUserType())&&!"admin".equalsIgnoreCase(user.getUserType())) {

			sql.append(" and bmdm='" + user.getUserDep() + "' ");
		}
		sql.append(" order by shsj desc");
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	 * ����: 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_szdw_fdyrzsqb");
		super.setKey("sqid");
	}
	
	/**
	 * @����:��ѯ�༶�б����������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-7 ����02:12:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjListBysqid(String sqid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.nj,b.xymc,b.zymc,b.bjmc,count(a.xh) bjrs,b.bjdm from xg_szdw_fdyrzsqbjb c, view_njxyzybj_all b,xsxxb a  where c.bjdm=a.bjdm and b.bjdm = a.bjdm  ");
		sql.append(" and c.sqbh = ? ");
		sql.append(" group by b.nj,b.xymc,b.zymc,b.bjmc,b.bjdm ");
		return dao.getList(sql.toString(), new String[]{sqid}, new String[]{"nj","xymc","zymc","bjmc","bjrs"});
	}
	/**
	 * 
	 * @����:TODO(����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-10 ����10:43:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int updateFdyrzsq(FdyrzsqForm model) throws Exception{
		String sql = " update xg_szdw_fdyrzsqb b set b.shzt=? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSqid()});
	}
	
}
