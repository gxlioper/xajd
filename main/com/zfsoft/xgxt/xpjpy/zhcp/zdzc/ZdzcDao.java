/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-29 ����11:35:33 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.InputVerifier;

import org.mortbay.html.Input;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2015-5-29 ����11:35:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzcDao extends SuperDAOImpl<ZdzcForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZdzcForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZdzcForm t, User user)
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
	 * @����:����۲����Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-29 ����03:15:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getzcxmList() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists ( ");
		sql.append("select 1 from xg_zhcp_zcxmb t2 where t1.xmdm=t2.fjdm) ");
		sql.append("and xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("order by jktb nulls last,fjdm,xmmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/** 
	 * @����:�����Ի��۲�ά��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-29 ����03:20:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdzcForm
	 * @param zcxmList
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZcwhList(ZdzcForm t,
			List<HashMap<String, String>> zcxmList, User user) throws Exception{
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("select id,xh,xm,tjzt, decode(tjzt,'1','���ύ','δ�ύ') tjztmc,bjdm,bjmc,zydm,zymc,xydm,xymc,nj ");
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",sum(fs").append(i).append(") fs").append(i);
		}
		
		sql.append(" from ( select t1.id,t1.xh,t1.xm,nvl(t1.tjzt,0) tjzt,t1.bjdm,t1.bjmc,t1.zydm,t1.zymc,t1.xydm,t1.xymc,t1.nj");
		
		for (int i = 0 ; i < zcxmList.size() ; i++){
			sql.append(",case when t2.xmdm='").append(zcxmList.get(i).get("xmdm"))
			   .append("' then fs else '' end fs").append(i);
		}
		sql.append(" from (select t1.id,t1.xn,t1.xq,t1.xh,t1.xm,t1.tjzt,t2.* from xg_pjpy_new_cpmdb t1 ");
		sql.append("left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm where t1.xn||t1.xq =(select xn || xq from xg_pjpy_new_csszb) and t2.bjdm is not null ");
		sql.append(searchTjByUser);
		sql.append(" ) t1 left join xg_zhcp_zcfsb t2 on t1.xh = t2.xh and t1.xn = t2.xn and t1.xq = t2.xq ");
		sql.append("where t1.xn || t1.xq in (select xn || xq from xg_pjpy_new_csszb where rownum = 1)) where 1=1  ");
		sql.append(searchTj);
		sql.append(" group by id, xh,xm, tjzt,bjdm,bjmc,zydm,zymc,xydm,xymc,nj order by decode(tjzt, '1','1','0'),nj,xydm,zydm,bjdm desc");
		return getPageList(t, sql.toString(), inputV);
	}

	
	/*
	 * ����: ȡ��������Ա������¼
	 */
	
	public boolean insertTzjl(String id, User user) throws Exception {
		
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" insert into xg_pjpy_new_cpmdtzjlb(xn,xq,xh,tzsj,tzr,tzbz) ");
		sql.append(" select xn,xq,xh,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') tzsj,'"+user.getUserName()+"'yhm,");
		sql.append("  '�� '||(select b.bjmc from view_njxyzybj_all b where a.bjdm=b.bjdm)|| ");
		sql.append(" ' ����Ϊ ������' tzbz from xg_pjpy_new_cpmdb a  where id in ( " );
		
		String[] ids = id.split(",");
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/*
	 * ����: ȡ������ѧ��
	 */
	
	public boolean updateCpmd(String id,User user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		List<String> params = new ArrayList<String>();
		
		sql.append(" update xg_pjpy_new_cpmdb ");
		sql.append("set bjdm = '' ");
		sql.append(" where id in ( ");
		String[] ids = id.split(",");
		
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		sql.append(" ) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * @throws Exception  
	 * @����:����ID���¶�Ӧ��ѧ���۲�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-6 ����10:19:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param user
	 * void �������� 
	 * @throws 
	 */
	public boolean updateXncpmd(String values, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("MERGE INTO xg_pjpy_new_cpmdb a ");
		sql.append("USING (select xn,'on' xq,xh,xm,bjdm from (select a.*,row_number()over(partition by xh order by xq desc) rn from xg_pjpy_new_cpmdb a ");
		sql.append("where xq <> 'on' and xn||xh in (select xn||xh from xg_pjpy_new_cpmdb where id in ( ");
		
		String[] ids = values.split(",");
		for(int i=0; i<ids.length; i++ ){
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
			params.add(ids[i]);
		}
		
		sql.append(") )) ");
		sql.append("where rn =1) b ");
		sql.append("ON (a.xh = b.xh and a.xq = b.xq and a.xn = b.xn) ");
		sql.append("WHEN MATCHED THEN ");
		sql.append("UPDATE ");
		sql.append("SET a.bjdm = b.bjdm ");
		sql.append("WHERE a.xq = b.xq and a.xh = b.xh and a.xn = b.xn ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("INSERT (xn,xq,xh,xm,bjdm) ");
		sql.append("VALUES (b.xn,b.xq, b.xh,b.xm,b.bjdm) ");
		
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
		
	}
	
	
	/**
	 * 
	 * @����:��ѯ�ɵ���ѧ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����02:46:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getStuById(ZdzcForm model, User user) throws Exception{
		
		//�û�Ȩ��
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select a.xn,a.xq,a.xh,a.xm,a.tjzt,a.tjr,a.tjsj,b.* from xg_pjpy_new_cpmdb a left join view_njxyzybj_all b on a.bjdm = b.bjdm) ");
		sql.append("where bjdm is not null and xn||xq in (select xn || xq from xg_pjpy_new_csszb) and nvl(tjzt,0) <>'"+ZdzcService.tjzt_tj+"' ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		System.out.println("----�����ѯѧ��-------"+sql.toString()+"----------------");
		String input = "";
		if(null!=inputV&&inputV.length!=0){
			for (int i = 0; i < inputV.length; i++) {
				input+=inputV[i]+",";
			}
		}
		System.out.println("------�����ֶ�---------"+input+"");
		return dao.getRs(sql.toString(),inputV, "xh");
	}
	
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����02:55:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean batchInsertZcfs(List<String[]> params) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("MERGE INTO xg_zhcp_zcfsb t1");
		sql.append(" USING (select ? xh, ? xn, ? xq, ? xmdm, ? fs , ? lrr , to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') lrsj from dual) t2");
		sql.append(" ON (t1.xh=t2.xh and t1.xmdm=t2.xmdm)");
		sql.append(" WHEN MATCHED THEN");
		sql.append("   UPDATE");
		sql.append("     SET xn=t2.xn,xq=t2.xq,fs=t2.fs,lrr=t2.lrr,");
		sql.append("     lrsj=to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')");
		sql.append("   WHERE xh=? and xmdm=?");
		sql.append("WHEN NOT MATCHED THEN");
		sql.append("  INSERT (xh, xn, xq, xmdm, fs, lrr, lrsj)");
		sql.append("  VALUES (t2.xh, t2.xn, t2.xq, t2.xmdm, t2.fs, t2.lrr, t2.lrsj)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @����:�ύ������Ա״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����04:24:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean tjCpxs(String values, String tjzt,ZdzcForm model, User user) throws Exception {
		
		//�û�Ȩ��
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_pjpy_new_cpmdb a set tjzt = '"+tjzt+"',tjr='"+user.getUserName()+"',tjsj=to_char(sysdate,'yyyy-MM-dd HH24:mi:ss') ");
		sql.append("where xn||xq = (select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sql.append("and exists (select 1 from (select * from (select nvl(a.tjzt,0) tjzt,a.id,a.xn,a.xq,a.xh,a.xm,b.* ");
		sql.append("from xg_pjpy_new_cpmdb a left join view_njxyzybj_all b on a.bjdm=b.bjdm where a.xn||a.xq = (select xn||xq from xg_pjpy_new_csszb where rownum=1) and b.bjdm is not null)where 1=1 ");
		sql.append("  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		if(!StringUtil.isNull(values)){
			String[] value = values.split(",");
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(") b where a.xh=b.xh)");
		
		return dao.runUpdate(sql.toString(), inputV);
		
	}

	/** 
	 * @����:����Ƿ���ύ�۲��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-10 ����05:20:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param zdzcForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isCanSubmit(String values, ZdzcForm zdzcForm, User user) throws Exception{
		
		//�û�Ȩ��
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b", "xydm", "bjdm");
		String searchTj = SearchService.getSearchTj(zdzcForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(zdzcForm.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		String[] value = values.split(",");
		
		sql.append("select count(1) count from (");
		sql.append("select a.id,a.tjzt,a.xh,b.* from xg_zhcp_zcxmb d left join xg_pjpy_new_cpmdb a on a.xn=d.xn  and a.xq=d.xq ");
		sql.append("left join view_njxyzybj_all b on a.bjdm=b.bjdm left join xg_zhcp_zcfsb c on a.xh=c.xh and a.xn=c.xn and a.xq=c.xq and d.xmdm=c.xmdm where c.fs is null");
		sql.append(searchTjByUser);
		sql.append(" and d.xn||d.xq = (select xn||xq from xg_pjpy_new_csszb where rownum=1) and d.xmdm not in (select fjdm from xg_zhcp_zcxmb) ) where 1=1 ");
		if(""!=values && values.length()!=0){
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		sql.append(searchTj);
		
		String num = dao.getOneRs(sql.toString(), inputV, "count");
		
		return Integer.parseInt(num)==0;
	}
	
	/**
	 * 
	 * @����:����¼���۲�ֵ��۲���Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-18 ����09:20:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_zhcp_zcxmb t1 where not exists");
		sql.append(" (select 1 from xg_zhcp_zcxmb t2 where t1.xmdm = t2.fjdm)");
		sql.append("and xn || xq = (select xn || xq from xg_pjpy_new_csszb where rownum = 1)");
		sql.append("order by jktb nulls last, fjdm, xmmc");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	/**
	 * @����:ͨ��idȡ��ѧ���б�
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-12-3 ����04:44:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String[] getXhArray(String values) throws SQLException {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xh from xg_pjpy_new_cpmdb where 1=1");

		if(!StringUtil.isNull(values)){
			String[] value = values.split(",");
			sql.append(" and id in ('");
			for (int i = 0; i < value.length; i++) {
				if(i!=0){
					sql.append("','");
				}
				sql.append(value[i]);
			}
			sql.append("')");
		}
		
		return dao.getArray(sql.toString(), new String[]{}, "xh");
	}

}
