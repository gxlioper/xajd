/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-24 ����09:05:50 
 */
package com.zfsoft.xgxt.xszz.knsrdnew.knsrdsq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������϶�����ģ��
 * @�๦������: (�������϶�����) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-24 ����09:05:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdsqDao extends SuperDAOImpl<KnsrdsqForm> {

	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setKey("sqid");
		super.setTableName("xg_xszz_knsrd_zbsqb");
	}

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(KnsrdsqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

		/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(KnsrdsqForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.* from (select t1.sqid,t1.xh,t2.xm, t2.xb, t2.nj,");
		sql.append(" t2.xymc,t2.zymc,t2.bjmc,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.yhkh,");
		sql.append(" t2.sfzh,t1.xn,t3.xqmc,t1.sqsj,t4.dcmc,t1.zmcl,t2.xydm,");
		sql.append(" t2.zydm,t2.bjdm,t4.dcdm,t1.zbid,t1.xq,t1.rddc,t1.splc,t1.column_11,");
		sql.append(" t1.shzt,decode(t1.shzt, '0','δ�ύ', '1', 'ͨ��', '2', '��ͨ��',");
		sql.append(" '3', '�˻�', '4', '������','5', '�����',t1.shzt) shztmc");
		// ============= ָ�����ݼ������������ begin============
		sql.append(" ,t6.zbnr,t7.jtqk ");
		// ============= ָ�����ݼ������������ end============
		sql.append(" from xg_xszz_knsrd_zbsqb t1 left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join xg_xszz_new_kndcdmb t4 on t1.rddc=t4.dcdm ");
		// ============= ָ�����ݼ������������ begin============
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select sxid,sqid,WM_CONCAT(nrmc) over (partition by sqid order by to_number(xssx)) zbnr, ");
		sql.append(" row_number() over (partition by sqid order by to_number(xssx) desc) rn ");
//		sql.append(" from xg_xszz_knsrd_zbnrb a left join xg_xszz_knsrd_zbsqnrb b on a.nrid=b.nrid ");
		sql.append(" from xg_xszz_knsrd_zbsqnrb a left join xg_xszz_knsrd_zbnrb b on a.nrid=b.nrid ");
		sql.append(" ) t5 where t5.rn=1 ");
		sql.append(" ) t6 on t1.sqid=t6.sqid ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select sqid,WM_CONCAT(jtqk) over (partition by sqid order by to_number(xssx)) jtqk, ");
		sql.append(" row_number() over (partition by sqid order by to_number(xssx) desc) rn ");
		sql.append(" from ( select a.sqid,a.jtqk,b.xssx ");
		sql.append(" from xg_xszz_knsrd_zbsqsxb a ");
		sql.append(" left join xg_xszz_knsrd_zbsxb b on a.sxid=b.sxid ");
//		sql.append(" left join (select distinct a.sxid,b.sqid,c.xssx ");
//		sql.append(" from xg_xszz_knsrd_zbnrb a ");
//		sql.append(" left join xg_xszz_knsrd_zbsqnrb b on a.nrid=b.nrid ");
//		sql.append(" left join xg_xszz_knsrd_zbsxb c on a.sxid=c.sxid ");
//		sql.append(" ) b on a.sxid=b.sxid and a.sqid=b.sqid ");
//		sql.append(" where b.sqid is not null ");
		sql.append(" ) a ");
		sql.append(" ) a where rn=1 ");
		sql.append(" ) t7 on t1.sqid=t7.sqid ");
		// ============= ָ�����ݼ������������ end============
		sql.append(" ) a where 1=1");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:(��ȡ���õ�ָ��Id)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-24 ����11:43:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getKnsrdzbid() {
		StringBuilder sql = new StringBuilder(
				" select zbid from xg_xszz_knsrd_knzbb where qyzt = '1' ");
		String zbid = dao.getOneRs(sql.toString(), new String[] {}, "zbid");
		return zbid;

	}
	
	/**
	 * 
	 * @����:(����������ָ������-ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:01:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean addKnsrdzbsqsx(KnsrdzbsqsxForm form){
		boolean flag = false;
		String sql = "insert into xg_xszz_knsrd_zbsqsxb(id,sqid,sxid,jtqk) values(?,?,?,?)";
		try {
			flag = dao.runUpdate(sql, new String[]{form.getId(),form.getSqid(),form.getSxid(),form.getJtqk()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @����:(��������ָ������-ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:01:25
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
	
	/**
	 * 
	 * @����:(��ȡ������ָ������-ָ�����Լ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:01:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(KnsrdsqForm model)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.sxid,a.zbid,a.sxmc,a.qzbl,a.xssx,b.jtqk from xg_xszz_knsrd_zbsxb a left join xg_xszz_knsrd_zbsqsxb b");
		sql.append(" on a.sxid = b.sxid  where a.zbid = ? and b.sqid = ? order by xssx " );
		return dao.getListNotOut(sql.toString(), new String[] { model.getZbid(),model.getSqid()});
	}
	
	/**
	 * 
	 * @����:(��ȡ����ָ������-ָ�����ݱ�ids)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:02:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getKnsrdzbsqnrids(KnsrdsqForm model)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select replace(wm_concat(nrid),';',',') as nrids  from xg_xszz_knsrd_zbsqnrb where sqid =? group by sqid ");
		return dao.getOneRs(sql.toString(), new String[] {model.getSqid()}, "nrids");
	}
	
	
	
	/**
	 * 
	 * @����:(ɾ��������ָ������-ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:02:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteKnsrdzbsqsx(String sqid){
		boolean flag = false;
		try {
			flag = dao.runUpdate(" delete from xg_xszz_knsrd_zbsqsxb where sqid = ? ", new String[]{sqid} );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:(ɾ������ָ������-ָ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:03:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteKnsrdzbsqnr(String sqid){
		boolean flag = false;
		try {
			flag = dao.runUpdate(" delete from xg_xszz_knsrd_zbsqnrb where sqid = ? ", new String[]{sqid} );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	/**
	 * 
	 * @����:(ɾ���������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:04:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String sqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_xszz_knsrd_zbsqb where sqid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{sqid});
		String bbsqzt=map.get("shzt");
		//���δ�ύ�ſ����ύ
		return null==bbsqzt||bbsqzt.equals("0")||bbsqzt.equals("3")?true:false;
	}
	
	/**
	 * 
	 * @����:(ɾ���������϶�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:04:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKnsrdzbsq(String sqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_xszz_knsrd_zbsqb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.sqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{sqid});
	}
	
	
	/**
	 * 
	 * @����:(�����������϶�����״̬)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����08:30:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateKnsrdsq(String sqid,String shzt) throws Exception{
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
	 * @����:(����ѧ��,ѧ�꣬ѧ���жϸ�ѧ���������϶������Ƿ��Ѿ�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-27 ����09:16:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String checkExistForSave(KnsrdsqForm model) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_xszz_knsrd_zbsqb where xh=? and xn=? and xq=?");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),model.getXn(),model.getXq()}, "num");
		return num;

	}

}
