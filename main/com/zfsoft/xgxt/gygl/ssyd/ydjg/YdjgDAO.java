/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:15:17 
 */  
package com.zfsoft.xgxt.gygl.ssyd.ydjg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import common.Globals;

/**
 * @ģ������: ��Ԣ����
 * @�๦������:�����춯���ά��
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-29
 * @�汾�� V1.0
 */
public class YdjgDAO extends SuperDAOImpl<YdjgForm> {

	/*
	      ����:
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("ssydid");
		super.setTableName("xg_gygl_new_ssyd_ssydjg");
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YdjgForm model)
			throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @����:ȡ�������춯�����Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-9 ����10:41:59
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYdjg(YdjgForm model) throws Exception {

		StringBuilder sql = new StringBuilder("");
		sql.append("  select t1.ssydid, ");
		sql.append("         t1.xh, ");
		sql.append("         t1.ydqldmc ||'_'||t1.ydqqsh||'_'||t1.ydqcwh tsqs, "); //��������
		sql.append("         case ");
		sql.append("           when t1.ydhldmc is not null and t1.ydhqsh is not null and t1.ydhcwh is not null ");
		sql.append("                then  t1.ydhldmc ||'_'||t1.ydhqsh||'_'||t1.ydhcwh  ");		
		sql.append("           else '' ");
		sql.append("         end as rzqs, "); //��ס����(û�е������������գ�
		sql.append("         t1.czsj, "); //��¼ʱ��
		sql.append("         t1.xn, "); //�����춯��������	
		sql.append("         t1.xq, ");
		sql.append("         (select xqmc from XQDZB where xqdm=t1.xq ) xqmc, ");//ѧ������
		sql.append("         t1.ssydlx, ");
		sql.append("         case ");
		sql.append("         	when t1.ssydlx = '00' then '����' ");
		sql.append("         	when t1.ssydlx = '01' then '�������' ");
		sql.append("         	when t1.ssydlx = '03' then '��ס' ");
		sql.append("         end ssydlxmc, "); //�����춯��������		
		sql.append("         t1.tstzyy, ");		
		sql.append("         decode(t1.ssydlx,'03',t4.rzyymc,'01',t5.tzyymc,t3.tsyymc) tsyymc, ");
		sql.append("         t1.tstzsj, ");
		sql.append("         t1.bz, ");
		sql.append("         t1.sfcwcsh, "); 		
		sql.append("         t1.sjly, ");
		sql.append("         t1.ydqlddm, "); 
		sql.append("         t1.ydqldmc, ");
		sql.append("         t1.ydqqsh, ");
		sql.append("         t1.ydqcwh, ");
		sql.append("         t1.ydhlddm, ");
		sql.append("         t1.ydhldmc, ");
		sql.append("         t1.ydhqsh, ");
		sql.append("         t1.ydhcwh, ");
		sql.append("         t1.fjxx, ");
		sql.append("         decode(t1.jflx, '01', '����', '02', '�˻�', '') jflx, ");
		sql.append("         t1.zsfje ");
		sql.append("  from XG_GYGL_NEW_SSYD_SSYDJG t1 ");
		sql.append("  left join XG_GYGL_NEW_TSYYDMB t3  ");
		sql.append("  			on t1.tstzyy = t3.tsyydm  ");
		sql.append("  left join xg_gygl_new_rzyydmb t4  ");
		sql.append("  			on t1.tstzyy = t4.rzyydm  ");
		sql.append("  left join XG_GYGL_NEW_TZYYDMB t5  ");
		sql.append("  			on t1.tstzyy = t5.tzyydm  ");
		sql.append("  where t1.ssydid = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{model.getSsydid()});
	}
	
	/**
	 * ȡ�������춯����б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YdjgForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		/*StringBuilder sql = new StringBuilder(" select * from (");
		sql.append("  select t1.ssydid, ");
		sql.append("         t1.xh, ");
		sql.append("         t2.xm, ");
		sql.append("         t2.xydm, ");
		sql.append("         t2.xymc, ");
		sql.append("         t2.zydm, ");
		sql.append("         t2.zymc, ");
		sql.append("         t2.bjdm, ");
		sql.append("         t2.bjmc, ");
		sql.append("         t2.nj, ");	
		sql.append("         t2.xb, ");
		sql.append("         t1.ydqldmc ||'_'||t1.ydqqsh||'_'||t1.ydqcwh tsqs, "); //��������
		sql.append("         case ");
		sql.append("           when t1.ydhldmc is not null and t1.ydhqsh is not null and t1.ydhcwh is not null ");
		sql.append("                then  t1.ydhldmc ||'_'||t1.ydhqsh||'_'||t1.ydhcwh  ");		
		sql.append("           else '' ");
		sql.append("         end as rzqs, "); //��ס����(û�е������������գ�
		sql.append("         t1.czsj, "); //��¼ʱ��
		sql.append("         t1.xn, "); //�����춯��������	
		sql.append("         t1.xq, ");
		sql.append("         (select xqmc from XQDZB where xqdm=t1.xq ) xqmc, ");//ѧ������
		sql.append("         t1.ssydlx, ");
		sql.append("         case ");
		sql.append("         	when t1.ssydlx = '00' then '����' ");
		sql.append("         	when t1.ssydlx = '01' then '�������' ");
		sql.append("         end ssydlxmc, "); //�����춯��������		
		sql.append("         t1.tstzyy, ");		
		sql.append("         t1.tstzsj, ");
		sql.append("         t1.bz, ");
		sql.append("         t1.sfcwcsh, "); 		
		sql.append("         t1.sjly, ");
		sql.append("         t1.ydqlddm, "); 
		sql.append("         t1.ydqldmc, ");
		sql.append("         t1.ydqqsh, ");
		sql.append("         t1.ydqcwh, ");
		sql.append("         t1.ydhlddm, ");
		sql.append("         t1.ydhldmc, ");
		sql.append("         t1.ydhqsh, ");
		sql.append("         t1.ydhcwh     ");
		sql.append("  from XG_GYGL_NEW_SSYD_SSYDJG t1 ");
		sql.append("  left join view_xsxxb t2  ");
		sql.append("  			on t1.xh = t2.xh  ");		
		sql.append("  )a where 1=1 ");*/
		StringBuilder sql = new StringBuilder();
		sql.append("select * from VIEW_NEW_DC_GYGL_YGJG a where 1=1 ");
		
		
		String searchTjByGyfdy = " and (ydqlddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"') "
				+ "or ydhlddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+user.getUserName()+"'))";	
		/* ���û�Ϊ��Ԣ����Աʱ��xg_gygl_new_gyfdyb �� ���ڸ��û�����
		 * ����������ΪѧԺ���߸���Ա��Ȩ�޿��ƣ�ֻ����¥�����ݷ�Χ
		 */
		if("yes".equalsIgnoreCase(user.getGyglyQx())&&(Base.xxdm.equals(Globals.XXDM_ZJJJZYJSXY))){//�û�Ϊ��Ԣ����Ա
			sql.append(searchTjByGyfdy);
		}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
			sql.append(searchTjByUser);
		}
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @����:ѧ�������һ�������춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����10:07:58
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsydInfo(YdjgForm myForm) {

		//���ɲ�ѯ
		StringBuilder sql = new StringBuilder(" select a.*,rownum no from (");
		sql.append("  select t1.ssydid, ");
		sql.append("         t1.xh, ");
		sql.append("         t2.xm, ");
		sql.append("         t2.xydm, ");
		sql.append("         t2.xymc, ");
		sql.append("         t2.zydm, ");
		sql.append("         t2.zymc, ");
		sql.append("         t2.bjdm, ");
		sql.append("         t2.bjmc, ");
		sql.append("         t2.nj, ");	
		sql.append("         t2.xb, ");
		sql.append("         t1.ydqldmc ||'_'||t1.ydqqsh||'_'||t1.ydqcwh tsqs, "); //��������
		sql.append("         case ");
		sql.append("           when t1.ydhldmc is not null and t1.ydhqsh is not null and t1.ydhcwh is not null ");
		sql.append("                then  t1.ydhldmc ||'_'||t1.ydhqsh||'_'||t1.ydhcwh  ");		
		sql.append("           else '' ");
		sql.append("         end as rzqs, "); //��ס����(û�е������������գ�
		sql.append("         t1.czsj, "); //��¼ʱ��
		sql.append("         t1.xn, "); //�����춯��������	
		sql.append("         t1.xq, ");
		sql.append("         (select xqmc from XQDZB where xqdm=t1.xq ) xqmc, ");//ѧ������
		sql.append("         t1.ssydlx, ");
		sql.append("         case ");
		sql.append("         	when t1.ssydlx = '00' then '����' ");
		sql.append("         	when t1.ssydlx = '01' then '�������' ");
		sql.append("         	when t1.ssydlx = '03' then '��ס' ");
		sql.append("         end ssydlxmc, "); //�����춯��������		
		sql.append("         t1.tstzyy, ");		
		sql.append("         decode(t1.ssydlx,'03',t4.rzyymc,'01',t5.tzyymc,t3.tsyymc) tsyymc, ");		
		sql.append("         t1.tstzsj, ");
		sql.append("         t1.bz, ");
		sql.append("         t1.sfcwcsh, "); 		
		sql.append("         t1.sjly, ");
		sql.append("         t1.ydqlddm, "); 
		sql.append("         t1.ydqldmc, ");
		sql.append("         t1.ydqqsh, ");
		sql.append("         t1.ydqcwh, ");
		sql.append("         t1.ydhlddm, ");
		sql.append("         t1.ydhldmc, ");
		sql.append("         t1.ydhqsh, ");
		sql.append("         t1.ydhcwh, ");

		sql.append("         row_number()over(order by t1.czsj desc) rk,  ");
		sql.append("         t1.fjxx, ");
		sql.append("         decode(t1.jflx, '01', '����', '02', '�˻�', '') jflx, ");
		sql.append("         t1.zsfje ");
		sql.append("  from XG_GYGL_NEW_SSYD_SSYDJG t1 ");
		sql.append("  left join XG_GYGL_NEW_TSYYDMB t3  ");
		sql.append("  			on t1.tstzyy = t3.tsyydm  ");
		sql.append("  left join xg_gygl_new_rzyydmb t4  ");
		sql.append("  			on t1.tstzyy = t4.rzyydm  ");
		sql.append("  left join XG_GYGL_NEW_TZYYDMB t5  ");
		sql.append("  			on t1.tstzyy = t5.tzyydm  ");
		sql.append("  left join view_xsxxb t2  ");
		sql.append("  			on t1.xh = t2.xh  ");	
		sql.append("  where t1.xh = ? ");
		sql.append("  )a where 1=1 ");
		sql.append("  and rk = 1 ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getXh()});
	}

	/**
	 * @����:ѧ������ĸ��������춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����10:07:58
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsYdList(YdjgForm myForm) throws Exception {

		//���ɲ�ѯ
		StringBuilder sql = new StringBuilder(" select a.*,rownum no from (");
		sql.append("  select t1.ssydid, ");
		sql.append("         t1.xh, ");
		sql.append("         t2.xm, ");
		sql.append("         t2.xydm, ");
		sql.append("         t2.xymc, ");
		sql.append("         t2.zydm, ");
		sql.append("         t2.zymc, ");
		sql.append("         t2.bjdm, ");
		sql.append("         t2.bjmc, ");
		sql.append("         t2.nj, ");	
		sql.append("         t2.xb, ");
		sql.append("         t1.ydqldmc ||'_'||t1.ydqqsh||'_'||t1.ydqcwh tsqs, "); //��������
		sql.append("         case ");
		sql.append("           when t1.ydhldmc is not null and t1.ydhqsh is not null and t1.ydhcwh is not null ");
		sql.append("                then  t1.ydhldmc ||'_'||t1.ydhqsh||'_'||t1.ydhcwh  ");		
		sql.append("           else '' ");
		sql.append("         end as rzqs, "); //��ס����(û�е������������գ�
		sql.append("         t1.czsj, "); //��¼ʱ��
		sql.append("         t1.xn, "); //�����춯��������	
		sql.append("         t1.xq, ");
		sql.append("         (select xqmc from XQDZB where xqdm=t1.xq ) xqmc, ");//ѧ������
		sql.append("         t1.ssydlx, ");
		sql.append("         case ");
		sql.append("         	when t1.ssydlx = '00' then '����' ");
		sql.append("         	when t1.ssydlx = '01' then '�������' ");
		sql.append("         	when t1.ssydlx = '03' then '��ס' ");
		sql.append("         end ssydlxmc, "); //�����춯��������		
		sql.append("         t1.tstzyy, ");			
		sql.append("         decode(t1.ssydlx,'03',t4.rzyymc,'01',t5.tzyymc,t3.tsyymc) tsyymc, ");		
		sql.append("         t1.tstzsj, ");
		sql.append("         t1.bz, ");
		sql.append("         t1.sfcwcsh, "); 		
		sql.append("         t1.sjly, ");
		sql.append("         t1.ydqlddm, "); 
		sql.append("         t1.ydqldmc, ");
		sql.append("         t1.ydqqsh, ");
		sql.append("         t1.ydqcwh, ");
		sql.append("         t1.ydhlddm, ");
		sql.append("         t1.ydhldmc, ");
		sql.append("         t1.ydhqsh, ");
		sql.append("         t1.ydhcwh, ");
		sql.append("         row_number()over(order by t1.czsj desc) rk,  ");
		sql.append("         t1.fjxx, ");
		sql.append("         decode(t1.jflx, '01', '����', '02', '�˻�', '') jflx, ");
		sql.append("         t1.zsfje ");
		sql.append("  from XG_GYGL_NEW_SSYD_SSYDJG t1 ");
		sql.append("  left join XG_GYGL_NEW_TSYYDMB t3  ");
		sql.append("  			on t1.tstzyy = t3.tsyydm  ");
		sql.append("  left join xg_gygl_new_rzyydmb t4  ");
		sql.append("  			on t1.tstzyy = t4.rzyydm  ");
		sql.append("  left join XG_GYGL_NEW_TZYYDMB t5  ");
		sql.append("  			on t1.tstzyy = t5.tzyydm  ");
		sql.append("  left join view_xsxxb t2  ");
		sql.append("  			on t1.xh = t2.xh  ");	
		sql.append("  where t1.xh = ? ");
		sql.append("  )a where 1=1 ");
		sql.append("  and rk > 1 ");
		List<HashMap<String, String>> data = dao.getListNotOut(sql.toString(), new String[]{myForm.getXh()});
		return data;
		
	}

	/** 
	 * @����:��ѧ��ѧ��ȡ����λ�춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����05:14:15
	 * @param myForm
	 * @param currXnxqFlgY
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getQsYdList(YdjgForm myForm,
			String currXnxqFlg) throws Exception{

		//���ɲ�ѯ
		StringBuilder sql = new StringBuilder(" ");
		sql.append(" select t1.xn, ");
		sql.append("        t1.xq, ");
		sql.append("        (select xqmc from XQDZB where xqdm = t1.xq) xqmc, ");
		sql.append("        t1.xh, ");
		sql.append("        t2.xm, ");
		sql.append(" 		t2.xb, ");
		sql.append(" 		t2.nj, ");
		sql.append(" 		t2.xymc, ");
		sql.append(" 		t2.xydm, ");
		sql.append(" 		t2.zydm, ");
		sql.append(" 		t2.zymc, ");
		sql.append(" 		t2.bjdm, ");
		sql.append(" 		t2.bjmc, ");
		sql.append(" 		t1.ydqqsrzsj, ");//��סʱ��
		sql.append(" 		t1.tstzsj ");//����ʱ��
		sql.append(" from xg_gygl_new_ssyd_ssydjg t1 ");
		sql.append(" left join view_xsxxb t2 ");
		sql.append(" 		on t1.xh = t2.xh ");
		sql.append(" where t1.ydqlddm=? ");
		sql.append(" and t1.ydqqsh=? ");
		sql.append(" and t1.ydqcwh=? ");
		
		//��ǰѧ��ѧ��
		if(YdjgService.CURR_XNXQ_FLG_Y.equals(currXnxqFlg)){
			sql.append("   and t1.xn=? ");
			sql.append("   and t1.xq=? ");
			
			// �ǵ�ǰѧ��ѧ��
		}else if(YdjgService.CURR_XNXQ_FLG_N.equals(currXnxqFlg)){
			sql.append("   and ( t1.xn <>? ");
			//sql.append("   and t1.xq <>? ) ");
			//�Ų�·[982] 2013-11-11 update
			sql.append("   or t1.xq <>? ) ");
		}

		List<HashMap<String, String>> data = dao.getListNotOut(sql.toString(),
				new String[] { myForm.getYdqlddm(), myForm.getYdqqsh(),
						myForm.getYdqcwh(), myForm.getXn(), myForm.getXq() });
		return data;
	}

	/** 
	 * @����:ѧ�������Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10 ����11:59:38
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsInfo(String xh) {

		// SQL���x
		StringBuilder sql = new StringBuilder();

		sql.append(" select xh,xm,xb,xydm,xymc,zydm,zymc,bjdm,bjmc,nj from view_xsxxb ");
		sql.append(" where xh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @����:ɾ���������Ӧ�������춯�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param ssydsqid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int runDeleteYdjg(String ssydsqid) throws Exception {

		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{ssydsqid};
		sql.append("delete from ");
		sql.append(" XG_GYGL_NEW_SSYD_SSYDJG ");
		sql.append(" where ");
		sql.append(" SSYDSQID = ? ");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 
	 * @����:��������IDȡ���춯���
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param ssydsqid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYdjg(String ssydsqid) throws Exception {

		StringBuilder sql = new StringBuilder();
		String[] values = new String[]{ssydsqid};
		sql.append("select *  from ");
		sql.append(" XG_GYGL_NEW_SSYD_SSYDJG ");
		sql.append(" where ");
		sql.append(" SSYDSQID = ? ");
		logger.debug(sql);
		logger.debug(Arrays.toString(values));
		
		return dao.getListNotOut(sql.toString(), values);
	}
	
	
	/**
	 * 
	 * @����:���������춯ID��ȡ�춯��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-3 ����05:09:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ssydid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYdxx(String ssydid){
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.xh,a.xb,a.tstzyymc,a.xymc,a.bjmc,a.xm,a.tstzsj, ");
		sb.append(" (select b.yqmc from (select b.lddm,c.yqmc from xg_gygl_new_ldxxb b left join ZXBZ_SSYQDM c on b.yqdm=c.yqdm) b where a.ydqlddm=b.lddm ) ydqyqmc, ");
		sb.append(" a.ydqldmc,a.YDQQSH,a.YDQCWH, ");
		sb.append(" (select b.yqmc from (select b.lddm,c.yqmc from xg_gygl_new_ldxxb b left join ZXBZ_SSYQDM c on b.yqdm=c.yqdm) b where a.ydhlddm=b.lddm ) ydhyqmc, ");
		sb.append(" a.YDHLDMC,a.YDHQSH,a.YDHCWH,a.jflxmc,a.zsfje,a.ydqcws,a.ydhcws,a.ydqsfbz,a.ydqqsrzsj,a.BZ,a.TSQS,a.RZQS from VIEW_NEW_DC_GYGL_YGJG a  where a.SSYDID = ? ");
		
		return dao.getMapNotOut(sb.toString(), new String[]{ssydid});
		
	}

}
