/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����10:16:08 
 */  
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq.KnsrdzbsqnrForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������϶�����ģ��
 * @�๦������: TODO(�������϶����) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-27 ����10:16:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdshDao extends SuperDAOImpl<KnsrdshForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("sqid");
		super.setTableName("xg_xszz_knsrd_zbsqb");

	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KnsrdshForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KnsrdshForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String shlx = t.getShzt();
		sql.append(" select a.* from (select t1.* ");
		// ============= ָ�����ݼ������������ begin============
		sql.append(" ,t6.zbnr,t7.jtqk ");
		// ============= ָ�����ݼ������������ end============
		sql.append(" from ( ");
		sql.append("select a.* from (select t2.xm, t2.xb, t2.bjmc, t2.lxdh, t2.xydm, t2.zydm, t2.xymc,");
		sql.append("t2.zymc, t2.bjdm, t2.nj, t3.xqmc,t5.spyh, t6.mc || '[' || decode(a.shzt, '0', 'δ���', '1', 'ͨ��', '2',");
		sql.append("'��ͨ��', '3', '�˻�', '4',  '������', '5', '�����') || ']' shztmc, t6.gwz,a.* from(select t1.sqid,");
		sql.append("t1.xh,t1.xn,t1.sqsj,t1.zmcl,t1.zbid,t1.xq,t4.zd2 rddc,t1.splc,t1.column_11,t4.shzt,t4.guid shid,");
		sql.append("t4.gwid,t4.shr,t4.shyj,lead(t4.zd3, 1, null) over(partition by t4.ywid order by t4.ywid, t4.shsj desc) as sjdcmc ,");
		sql.append("lead(t4.zd2, 1, null) over(partition by t4.ywid order by t4.ywid, t4.shsj desc) as sjdc,");
		sql.append("row_number() over(partition by t1.sqid order by t4.shsj desc) rn from xg_xszz_knsrd_zbsqb t1 left join xg_xtwh_shztb t4 ");
		sql.append(" on t1.sqid = t4.ywid ");
		if(!shlx.equals("dsh")){
			sql.append(" where t4.shzt not in('0','4')" );
		}
	    sql.append(" )a left join view_xsxxb t2 on a.xh = t2.xh left join xqdzb t3 on a.xq = t3.xqdm ");
		sql.append("left join xg_xtwh_spgwyh t5 on a.gwid = t5.spgw left join xg_xtwh_spgw t6 on a.gwid = t6.id where t5.spyh='"+user.getUserName()+"'" );
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");        
		if(shlx.equals("dsh")){
			sql.append(" and shzt in('0','4')" );
		}
		sql.append(" ) t1 ");
		// ============= ָ�����ݼ������������ begin============
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select sxid,sqid,WM_CONCAT(nrmc) over (partition by sqid order by to_number(xssx)) zbnr, ");
		sql.append(" row_number() over (partition by sqid order by to_number(xssx) desc) rn ");
		sql.append(" from xg_xszz_knsrd_zbnrb a left join xg_xszz_knsrd_zbsqnrb b on a.nrid=b.nrid ");
		sql.append(" ) t5 where t5.rn=1 ");
		sql.append(" ) t6 on (t1.sqid=t6.sqid) ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select sqid,WM_CONCAT(jtqk) over (partition by sqid order by to_number(xssx)) jtqk, ");
		sql.append(" row_number() over (partition by sqid order by to_number(xssx) desc) rn ");
		sql.append(" from ( select a.sqid,a.jtqk,b.xssx ");
		sql.append(" from xg_xszz_knsrd_zbsqsxb a ");
		sql.append(" left join (select distinct a.sxid,b.sqid,c.xssx ");
		sql.append(" from xg_xszz_knsrd_zbnrb a ");
		sql.append(" left join xg_xszz_knsrd_zbsqnrb b on a.nrid=b.nrid ");
		sql.append(" left join xg_xszz_knsrd_zbsxb c on a.sxid=c.sxid ");
		sql.append(" ) b on a.sxid=b.sxid and a.sqid=b.sqid ");
		sql.append(" where b.sqid is not null ");
		sql.append(" ) a ");
		sql.append(" ) a where rn=1 ");
		sql.append(" ) t7 on (t1.sqid=t7.sqid) ");
		// ============= ָ�����ݼ������������ end============
		sql.append(" ) a where 1=1");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:TODO(�õ��������϶�ָ�����Լ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����09:21:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(KnsrdshForm model)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.sxid,a.zbid,a.sxmc,a.qzbl,a.xssx,b.jtqk from xg_xszz_knsrd_zbsxb a left join xg_xszz_knsrd_zbsqsxb b");
		sql.append(" on a.sxid = b.sxid  where a.zbid = ? and b.sqid = ? order by xssx " );
		return dao.getListNotOut(sql.toString(), new String[] { model.getZbid(),model.getSqid()});
	}
	
	/**
	 * 
	 * @����:TODO(�õ��������϶�ָ������ids)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����09:21:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getKnsrdzbsqnrids(KnsrdshForm model)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(nrid),';',',') as nrids  from xg_xszz_knsrd_zbsqnrb where sqid =? group by sqid ");
		return dao.getOneRs(sql.toString(), new String[] {model.getSqid()}, "nrids");
	}
	
	
	/**
	 * 
	 * @����:TODO(�����������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����09:19:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelKnsrdzbsq(String sqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_xszz_knsrd_zbsqb ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	

	/**
	 * 
	 * @����:TODO(ɾ��ָ�����������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-19 ����03:19:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean  deleteKnsrdzbsqnrb(String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		boolean flag = false;
		sql.append(" delete from xg_xszz_knsrd_zbsqnrb  where nrid in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		flag = dao.runUpdate(sql.toString(), params.toArray(new String[]{}) );
		return flag ;
		
	}
	
	/**
	 * 
	 * @����:TODO(��������ָ������-ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-20 ����09:20:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean addKnsrdzbsqnr(KnsrdzbsqnrForm form){
		boolean flag = false;
		String sql = "insert into xg_xszz_knsrd_zbsqnrb(id,sqid,nrid,sqfz,shfz) values(?,?,?,?,?)";
		try {
			flag = dao.runUpdate(sql, new String[]{form.getId(),form.getSqid(),form.getNrid(),form.getSqfz(),form.getShfz()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public List<HashMap<String, String>> getKnsrdzbnrsqList(String sxid,String sqid)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.nrid,sxid,nrmc,fzlx,fz,xssx,b.shfz from xg_xszz_knsrd_zbnrb a  ");
		sql.append(" left join ( select * from xg_xszz_knsrd_zbsqnrb b where b.sqid = ? ) b on a.nrid = b.nrid where a.sxid = ? order by a.xssx ");
		return dao.getListNotOut(sql.toString(), new String[] {sqid,sxid});
	}
	
}
