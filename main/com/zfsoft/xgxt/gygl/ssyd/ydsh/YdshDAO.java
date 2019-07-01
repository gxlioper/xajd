/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:15:17 
 */  
package com.zfsoft.xgxt.gygl.ssyd.ydsh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.Globals;

/**
 * @ģ������: ��Ԣ����
 * @�๦������:�����춯���
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-29
 * @�汾�� V1.0
 */
public class YdshDAO extends SuperDAOImpl<YdshForm> {

	@Override
	protected void setTableInfo() {
		super.setKey("ssydsqid");
		super.setTableName("xg_gygl_new_ssyd_ssydsq");
	}

	@Override
	public List<HashMap<String, String>> getPageList(YdshForm model)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(YdshForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append("  select * from ( ");
		sql.append("  select m.xymc, ");
		sql.append("         m.xydm,");
		sql.append("         m.zydm,");
		sql.append("         m.bjdm, ");		
		sql.append("         m.zymc, ");
		sql.append("         m.bjmc,m.zybj,m.zybjmc, ");
		sql.append("         m.xh, ");
		sql.append("         m.xm, ");
		sql.append("         m.nj, ");	
		sql.append("         m.xb, ");//�Ԅe		
		sql.append("         case when a.tzqldmc is not null and a.tzqqsh is not null and a.tzqcwh is not null then a.tzqldmc || '_' || a.tzqqsh || '_' || a.tzqcwh else '' end as ssxx, ");//������Ϣ
		sql.append("         a.tzhlddm,a.tzhqsh,a.tzhcwh,a.tzqlddm,a.tzqqsh,a.tzqcwh, ");
		sql.append("         a.sqsj, ");
		sql.append("         a.SSYDSQID, ");
		sql.append("         a.xn, ");
		sql.append("         a.xq, ");
		sql.append("         a.ssydlx, ");		
		sql.append("         case ");
		sql.append("         	when a.ssydlx = '00' then '����' ");
		sql.append("         	when a.ssydlx = '01' then '�������' ");
		sql.append("         	when a.ssydlx = '03' then '��ס' ");
		sql.append("         end ssydlxmc, "); //�����춯��������	
		sql.append("         a.TSTZYY, ");
		sql.append("         a.TSTZSJ, ");
		//sql.append("         a.TZRZCW, ");
		sql.append("         a.BZ, ");		
		sql.append("         a.SFCWCSH, ");
		sql.append("         a.splcid, ");
		// ���ͨ�ã�ȡ����λID,���ʱ��,״̬,���ID��
		sql.append("         b.gwid, "); //xg_xtwh_shztb ��λID gwid����λID������Ա/������/ѧԺ/ѧУ�ȣ�
		sql.append("         b.shsj, ");
		sql.append("         b.guid shid, "); //xg_xtwh_shztb ���id shid��xg_xtwh_shztb guid
		sql.append("         c.mc || '[' || decode(b.shzt, ");
		sql.append("                               0,'�����', ");
		sql.append("                               1,'ͨ��', ");
		sql.append("                               2,'��ͨ��', ");
		sql.append("                               3,'�˻�', ");
		sql.append("                               4,'������', ");
		sql.append("                               5,'�����', ");
		sql.append("                               '����') || ']' shzt,c.gwz ");
		sql.append("         ,row_number()over(partition by a.SSYDSQID order by b.shsj desc) rn ");
		sql.append("  from XG_GYGL_NEW_SSYD_SSYDSQ a ");
		sql.append("  left join view_xsjbxx m  ");
		sql.append("  			on a.xh = m.xh  ");
		sql.append("  left join VIEW_XG_GYGL_NEW_CWXX f  ");
		sql.append("  			on a.xh = f.xh  ");
		sql.append("  left join xg_xtwh_shztb b  ");
		sql.append("  			on a.SSYDSQID = b.ywid  ");
		sql.append("  left join xg_xtwh_spgw c  ");
		sql.append("  			on b.gwid = c.id  ");
		sql.append("  left join xg_xtwh_spgwyh d  ");
		sql.append("  			on c.id = d.spgw  ");
		sql.append("  left join  xg_xtwh_spbz t1  "); //��������
		sql.append("  			on t1.splc = a.splcid ");
		sql.append("  			and t1.spgw = d.spgw  ");
		sql.append("  left join XG_GYGL_NEW_SSYD_SSYDJG t2  ");
		sql.append("  			on t2.ssydsqid = a.ssydsqid ");	
		if(Globals.XXDM_ZJZYYDX.equals(Base.xxdm)){
			sql.append(" left join xg_gygl_new_ldxxb ld on a.tzhlddm=ld.lddm ");
			sql.append(" left join xg_gygl_new_ldxxb ldtzq on a.tzqlddm=ldtzq.lddm ");
		}
		sql.append("  where d.spyh = '"+user.getUserName()+"' and a.shzt<>9 and b.shzt<>9 ");
		//�㽭��ҽҩ��������Ի��������������������xg_zjzyy_xqdzb��
		if(Globals.XXDM_ZJZYYDX.equals(Base.xxdm)){
			sql.append(" and (t1.xh = '1' or t1.xh = '2' or ((t1.xh ='3' or t1.xh = '4') and (nvl(ld.xqdm,ldtzq.xqdm) in (select xq from xg_zjzyy_xqdzb where zgh = '" + user.getUserName() + "') or ldtzq.xqdm is null) )) ");
		}
		String shlx = model.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}		
		sql.append(" order by b.shsj desc )a ");
		sql.append(" where rn = 1 "); //ȡ������һ��
		sql.append(" )a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(model, sql.toString(), inputV);
	}

	/**
	 * @throws SQLException  
	 * @����:ȡ�������춯������Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29 
	 * @param form
	 * @return
	 * YdshForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getYdshForm(YdshForm form) throws SQLException {
		
		// SQL���x
		StringBuilder sql = new StringBuilder();
		sql.append("  select t1.xh, ");
		sql.append("         t1.xn, ");
		sql.append("         t1.xq, ");
		sql.append("         t1.ssydsqid, ");		
		sql.append("         t1.ssydlx, ");		
		sql.append("         t1.tstzsj, ");
		sql.append("         t1.tstzyy,	");
		sql.append("         t1.bz, ");
		sql.append("         t1.tjsqrxm, ");//�ύ����������
		sql.append("         t1.sfcwcsh, ");
//		sql.append("         t1.tzhlddm ydhlddm, ");//�춯��¥������
//		sql.append("         t3.ldmc    ydhldmc, ");//�춯��¥������
//		sql.append("         t1.tzhqsh  ydhqsh, ");//�춯�����Һ�
//		sql.append("         t1.tzhcwh  ydhcwh, ");//�춯��λ��
		sql.append("         '"+form.getYdhlddm()+"' ydhlddm, ");//�춯��¥������
		sql.append("         '"+form.getYdhldmc()+"' ydhldmc, ");//�춯��¥������
		sql.append("         '"+form.getYdhqsh()+"'  ydhqsh, ");//�춯�����Һ�
		sql.append("         '"+form.getYdhcwh()+"'  ydhcwh, ");//�춯��λ��
//		sql.append("         t3.xh      ydglxh, ");//�춯����ѧ��
		sql.append("         '"+form.getYdglxh()+"'      ydglxh, ");//�춯����ѧ��
		sql.append("         t2.lddm    ydqlddm, ");//�춯ǰ¥������
		sql.append("         t2.ldmc    ydqldmc, ");//�춯ǰ¥������
		sql.append("         t2.qsh     ydqqsh, ");	//�춯ǰ���Һ�
		sql.append("         t2.cwh     ydqcwh, ");	//�춯ǰ��λ��
		sql.append("         t4.nj      ydqxsnj, ");	//�춯ǰ�꼶
		sql.append("         t4.bjdm    ydqxsbjdm, ");	//�춯ǰ�༶����
		sql.append("         t4.zydm    ydqxszydm, ");	//�춯ǰרҵ����
		sql.append("         t4.xydm    ydqxsxydm, ");	//�춯ǰѧԺ����
		sql.append("         t3.nj      ydhnj, ");		//�춯������ԭ�����꼶
		sql.append("         t3.bjdm    ydhbjdm, ");	//�춯������ԭ�����༶����
		sql.append("         t3.zydm    ydhzydm, ");	//�춯������ԭ����רҵ����
		sql.append("         t3.xydm    ydhxydm, ");	//�춯������ԭ����ѧԺ����			
		sql.append("         t5.nj      ydhxsnj, ");	//�춯���꼶
		sql.append("         t5.bjdm    ydhxsbjdm, ");	//�춯��༶����
		sql.append("         t5.zydm    ydhxszydm, ");	//�춯��רҵ����
		sql.append("         t5.xydm    ydhxsxydm, ");	//�춯��ѧԺ����	
		sql.append("         t2.rzsj    ydqrzsj, ");	//�춯ǰ��סʱ��	
		sql.append("         t3.rzsj    ydhrzsj, ");	//�춯����סʱ��	
		sql.append("         fjxx  			 ");	//������Ϣ
		sql.append("  FROM XG_GYGL_NEW_SSYD_SSYDSQ t1 ");
		sql.append("  INNER JOIN VIEW_XG_GYGL_NEW_CWXX T2 ");
		sql.append("         ON T1.xh = T2.xh ");
		sql.append("  LEFT JOIN VIEW_XG_GYGL_NEW_CWXX T3 ");
		sql.append("         ON t1.tzhlddm = T3.lddm ");
		sql.append("         and t1.tzhqsh = t3.qsh ");
		sql.append("         and t1.tzhcwh = t3.cwh ");
		sql.append("  LEFT JOIN VIEW_XSXXB T4 ");
		sql.append("         on t1.xh = t4.xh ");
		sql.append("  LEFT JOIN VIEW_XSXXB T5 ");
		sql.append("         on t3.xh = t5.xh ");
		sql.append("  where t1.ssydsqid = ? ");
		
		HashMap<String, String> befMap = new HashMap<String, String>();
		
		// ȡ�������춯������Ϣ
		befMap = dao.getMapNotOut(sql.toString(), new String[] { form
				.getSsydsqid() });
		return befMap;
	}
	
	/**
	 * @����:ȡ�������춯������Ϣ����ס��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-17 ����11:32:29
	 * @param form
	 * @return
	 * YdshForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getYdshRzForm(YdshForm form) throws SQLException {
		
		// SQL���x
		StringBuilder sql = new StringBuilder();
		sql.append("  select t1.xh, ");
		sql.append("         t1.xn, ");
		sql.append("         t1.xq, ");
		sql.append("         t1.ssydsqid, ");		
		sql.append("         t1.ssydlx, ");		
		sql.append("         t1.tstzsj, ");
		sql.append("         t1.tstzyy,	");
		sql.append("         t1.bz, ");
		sql.append("         t1.tjsqrxm, ");//�ύ����������
		sql.append("         t1.sfcwcsh, ");
//		sql.append("         t1.tzqlddm ydhlddm, ");//�춯��¥������
//		sql.append("         t3.ldmc    ydhldmc, ");//�춯��¥������
//		sql.append("         t1.tzqqsh  ydhqsh, ");//�춯�����Һ�
//		sql.append("         t1.tzqcwh  ydhcwh, ");//�춯��λ��
		sql.append("         '"+form.getYdhlddm()+"' ydhlddm, ");//�춯��¥������
		sql.append("         '"+form.getYdhldmc()+"' ydhldmc, ");//�춯��¥������
		sql.append("         '"+form.getYdhqsh()+"'  ydhqsh, ");//�춯�����Һ�
		sql.append("         '"+form.getYdhcwh()+"'  ydhcwh, ");//�춯��λ��
		sql.append("         ''      ydglxh, ");//�춯����ѧ��
		sql.append("         null    ydqlddm, ");//�춯ǰ¥������
		sql.append("         null    ydqldmc, ");//�춯ǰ¥������
		sql.append("         null     ydqqsh, ");	//�춯ǰ���Һ�
		sql.append("         null     ydqcwh, ");	//�춯ǰ��λ��
		sql.append("         t4.nj      ydqxsnj, ");	//�춯ǰ�꼶
		sql.append("         t4.bjdm    ydqxsbjdm, ");	//�춯ǰ�༶����
		sql.append("         t4.zydm    ydqxszydm, ");	//�춯ǰרҵ����
		sql.append("         t4.xydm    ydqxsxydm, ");	//�춯ǰѧԺ����
		sql.append("         t3.nj      ydhnj, ");		//�춯������ԭ�����꼶
		sql.append("         t3.bjdm    ydhbjdm, ");	//�춯������ԭ�����༶����
		sql.append("         t3.zydm    ydhzydm, ");	//�춯������ԭ����רҵ����
		sql.append("         t3.xydm    ydhxydm, ");	//�춯������ԭ����ѧԺ����			
		sql.append("         t5.nj      ydhxsnj, ");	//�춯���꼶
		sql.append("         t5.bjdm    ydhxsbjdm, ");	//�춯��༶����
		sql.append("         t5.zydm    ydhxszydm, ");	//�춯��רҵ����
		sql.append("         t5.xydm    ydhxsxydm, ");	//�춯��ѧԺ����	
		sql.append("         null    ydqrzsj, ");	//�춯ǰ��סʱ��	
		sql.append("         t3.rzsj    ydhrzsj, ");	//�춯����סʱ��	
		sql.append("         fjxx ");	//������Ϣ	
		sql.append("  FROM XG_GYGL_NEW_SSYD_SSYDSQ t1 ");
		sql.append("  LEFT JOIN VIEW_XG_GYGL_NEW_CWXX T3 ");
		sql.append("         ON t1.tzqlddm = T3.lddm ");
		sql.append("         and t1.tzqqsh = t3.qsh ");
		sql.append("         and t1.tzqcwh = t3.cwh ");
		sql.append("  LEFT JOIN VIEW_XSXXB T4 ");
		sql.append("         on t1.xh = t4.xh ");
		sql.append("  LEFT JOIN VIEW_XSXXB T5 ");
		sql.append("         on t3.xh = t5.xh ");
		sql.append("  where t1.ssydsqid = ? ");
		
		HashMap<String, String> befMap = new HashMap<String, String>();
		
		// ȡ�������춯������Ϣ
		befMap = dao.getMapNotOut(sql.toString(), new String[] { form
			.getSsydsqid() });
		return befMap;
	}

	/**
	 * 
	 * @����:���´�λ��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-29 
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean updateCwxxb(HashMap<String, String> cwxxMap) throws Exception{
		List<String> params = new ArrayList<String>();
		// SQL���x
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE XG_GYGL_NEW_CWXXB ");
		sql.append(" set ");
		sql.append(" xh = ? ");
		sql.append(" ,rzsj = ? ");
		params.add(cwxxMap.get("xh"));
		params.add(cwxxMap.get("rzsj"));
		
		if(cwxxMap.get("nj") != null 
				&& cwxxMap.get("bjdm") != null 
				&& cwxxMap.get("zydm") != null 
				&& cwxxMap.get("xydm") != null ){
			
			sql.append(" ,nj  = ? ");
			sql.append(" ,bjdm = ?");
			sql.append(" ,zydm = ?");
			sql.append(" ,xydm = ?");
			params.add(cwxxMap.get("nj"));
			params.add(cwxxMap.get("bjdm"));
			params.add(cwxxMap.get("zydm"));
			params.add(cwxxMap.get("xydm"));
		}
		if(cwxxMap.get("rzyydm") != null){
			sql.append(" ,rzyydm = ?");
			params.add(cwxxMap.get("rzyydm"));
		}

		sql.append(" where lddm=? ");
		sql.append("   and qsh=? ");
		sql.append("   and cwh=? ");
		
		params.add(cwxxMap.get("lddm"));
		params.add(cwxxMap.get("qsh"));
		params.add(cwxxMap.get("cwh"));
		
		return dao.update(sql.toString(), params.toArray(new String[]{}))>0 ? true:false;
	}

}
