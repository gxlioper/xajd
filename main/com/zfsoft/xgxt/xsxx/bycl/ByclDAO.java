/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-25 ����4:15:17 
 */  
package com.zfsoft.xgxt.xsxx.bycl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydDao;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;

/**
 * @ģ������: ѧ����Ϣ
 * @�๦������:��ҵ����
 * @���ߣ� qilm
 * @ʱ�䣺 2013-9-29
 * @�汾�� V1.0
 */
public class ByclDAO extends SuperDAOImpl<ByclForm> {

	/*
	      ����:
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("xh");
		super.setTableName("xg_xsxx_bycl_byztb");
		super.setClass(ByclForm.class);
	}

	/*
	      ����:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByclForm model)
			throws Exception {
		return null;
	}
	
	/**
	 * ȡ����ҵ�����б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ByclForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();


		sql.append(" select * from ( ");
		sql.append(" select a.*,(case when b.pjnum is not null or c.wjnum is not null then '��' else '��' end)sfyjcxx from VIEW_XSBFXX a left join (select xh,count(1)pjnum from xg_pjpy_new_pjjgb group by xh )b");
		sql.append(" on a.xh=b.xh left join (select xh,count(1)wjnum from xg_wjcf_wjcfb group by xh )c on a.xh=c.xh where 1=1 ");
		
		// �Ƿ��ѱ�ҵ�жϣ�1���ǣ�
		if("1".equals(model.getSfyby())){
			sql.append(" and a.sfyby ='��' ");
		}else{
			sql.append(" and (a.sfyby is null or a.sfyby !='��' ) ");
		}		
		sql.append(" )a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/** 
	 * @����: ��ѯȡ����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����09:03:10
	 * @param myForm
	 * @param user
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int getCounts(ByclForm model, User user) throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");	
		StringBuilder sql = new StringBuilder();

		sql.append(" select count(xh) counts from VIEW_XSBFXX ");
		sql.append(" where 1=1 ");
		
		// �Ƿ��ѱ�ҵ�жϣ�1���ǣ�
		if("1".equals(model.getSfyby())){
			sql.append(" and sfyby ='��' ");
		}else{
			sql.append("  and (sfyby is null or sfyby !='��' )  ");
		}		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		List<HashMap<String,String>> resultList = null;
		resultList = dao.getListNotOut(sql.toString(), inputV);
		if(resultList!=null){
			return Integer.parseInt(resultList.get(0).get("counts"));
		}else{
			return 0;
		}
	}
	
	/**
	 * 
	 * @����: ���±�ҵ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����10:30:31
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runUpdate(ByclForm model, User user) throws Exception{

		StringBuilder sql = new StringBuilder();
		
		// ȡ�á���ҵ����ѧ��������
		XjydDao xjydlb = new XjydDao();
		XjydForm xjydForm = new XjydForm();
		xjydForm.setXjlbmc("��ҵ");
		xjydForm = xjydlb.getModel(xjydForm);
		
		sql.append(" update xsxxb ");
		sql.append(" set sfyby='��' ,byny = '" + model.getByny() +"' ");
		sql.append(" , sfzx='����У' ");
		sql.append(" , xjztm='��ѧ��' ");
		sql.append(" , xjlb='��ҵ' ");
		if(xjydForm !=null ){
			sql.append(" , xjlbdm ='"+ xjydForm.getXjlbdm() + "' ");
		}else{
			sql.append(" , xjlbdm ='' ");
		}

		// ɾ��ѧ����Ϣ-��ҵ����-��ҵ״̬��[��¼����ԭѧ��������Ϣ]
		StringBuilder sqlDelByztb = new StringBuilder();
		sqlDelByztb.append(" delete from XG_XSXX_BYCL_BYZTB ");
		
		// ����ѧ����Ϣ-��ҵ����-��ҵ״̬��[��¼����ԭѧ��������Ϣ]
		StringBuilder sqlInsertByztb = new StringBuilder();
		sqlInsertByztb.append(" insert into XG_XSXX_BYCL_BYZTB ");
		sqlInsertByztb.append("   (xh, sfby, byny, byzt, Yxjlbdm, Yxjlb, yxjztm, ysfzx) ");
		sqlInsertByztb.append("   select xh, '1' sfby, '" + model.getByny() +"' byny, '0' byzt, xjlbdm, xjlb, xjztm, sfzx ");
		sqlInsertByztb.append("     from xsxxb ");
		
		List<String> sqlArr=new ArrayList<String>();
		
		if(StringUtils.isNotNull(model.getValues())){
			StringBuilder sqlxh = new StringBuilder();
			sqlxh.append(" where xh in (");

			String[] xhs = model.getValues().split(",");
			for (int i = 0; i < xhs.length; i++) {
				if (i != 0) {
					sqlxh.append(",");
				}
				sqlxh.append("'"+xhs[i]+"'");
			}
			
			sqlxh.append(") "); 
			sql.append(sqlxh);

			sqlDelByztb.append(sqlxh);
			sqlInsertByztb.append(sqlxh);			
			
			sqlArr.add(sqlDelByztb.toString());
			sqlArr.add(sqlInsertByztb.toString());
			sqlArr.add(sql.toString());
			return dao.saveArrDate(sqlArr.toArray(new String[]{}));
		}else{

			StringBuilder sqlxh = new StringBuilder();
			sqlxh.append(" where xh in (");
			sqlxh.append(" select xh ");
			sqlxh.append(" from VIEW_XSBFXX  where 1=1 and (sfyby is null or sfyby !='��' )  ");
			
			//���ɸ߼���ѯ�������������ֵ 
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "", "xydm", "bjdm");
			sqlxh.append(searchTjByUser);
			sqlxh.append(" ");
			sqlxh.append(searchTj);			
			sqlxh.append(") "); 
			sql.append(sqlxh.toString());
			sqlDelByztb.append(sqlxh);
			sqlInsertByztb.append(sqlxh);
			boolean flg = true;
			
			flg = dao.runUpdate(sqlDelByztb.toString(), inputV);
			
			if (flg){
				flg = dao.runUpdate(sqlInsertByztb.toString(), inputV);
			}
			
			if(flg){
				flg = dao.runUpdate(sql.toString(), inputV);
			}
			
			return flg;
			
		}
		
	}

	/** 
	 * @����: ȡ����ҵ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����01:36:35
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runDelete(ByclForm model, User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();

		sql.append(" update xsxxb a ");
		sql.append(" set (sfyby,byny,xjlbdm,xjlb,xjztm,sfzx) = ( ");		
		sql.append(" select '��' sfyby,'' byny,Yxjlbdm, Yxjlb, yxjztm, ysfzx ");
		sql.append(" from XG_XSXX_BYCL_BYZTB b  ");
		sql.append(" where b.sfby = '1'  ");
		sql.append(" and a.xh = b.xh ) ");
		

		// ɾ��ѧ����Ϣ-��ҵ����-��ҵ״̬��[��¼����ԭѧ��������Ϣ]
		StringBuilder sqlDelByztb = new StringBuilder();
		sqlDelByztb.append(" delete from XG_XSXX_BYCL_BYZTB a ");

		List<String> sqlArr=new ArrayList<String>();
		
		if(StringUtils.isNotNull(model.getValues())){
			StringBuilder sqlxh = new StringBuilder();
			sqlxh.append(" where a.xh in  (");

			String[] xhs = model.getValues().split(",");
			for (int i = 0; i < xhs.length; i++) {
				if (i != 0) {
					sqlxh.append(",");
				}
				sqlxh.append("'"+xhs[i]+"'");
			}
			
			sqlxh.append(") "); 
			
			sql.append(sqlxh.toString());
			sqlDelByztb.append(sqlxh.toString());
			sqlArr.add(sql.toString());
			// ɾ��ѧ����ҵ��¼
			sqlArr.add(sqlDelByztb.toString());
			
			return dao.saveArrDate(sqlArr.toArray(new String[]{}));
		}else{

			StringBuilder sql2 = new StringBuilder();

			sql2.append(" update xsxxb a ");
			sql2.append(" set (sfyby,byny,xjlbdm,xjlb,xjztm,sfzx) = ( ");		
			sql2.append(" select '��' sfyby,'' byny,Yxjlbdm, Yxjlb, yxjztm, ysfzx ");
			sql2.append(" from XG_XSXX_BYCL_BYZTB b  ");
			
			sql2.append(" left join VIEW_XSBFXX c ");
			sql2.append(" on c.xh = b.xh ");
			sql2.append(" where 1 = 1  ");
			
			//���ɸ߼���ѯ�������������ֵ 
			String searchTj = SearchService.getSearchTj(model.getSearchModel(), "c");
			String[] inputV = SearchService.getTjInput(model.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "c", "xydm", "bjdm");
			sql2.append(searchTjByUser);
			sql2.append(" ");
			sql2.append(searchTj);
			
			sql2.append(" and b.sfby = '1'  ");
			sql2.append(" and a.xh = b.xh ) ");
			sql2.append("  where exists (select 1 ");
			sql2.append(" from XG_XSXX_BYCL_BYZTB b  ");
			
			sql2.append(" left join VIEW_XSBFXX c ");
			sql2.append(" on c.xh = b.xh ");
			sql2.append(" where 1 = 1  ");
			sql2.append(searchTjByUser);
			sql2.append(" ");
			sql2.append(searchTj);			
			sql2.append(" and b.sfby = '1'  ");
			sql2.append(" and a.xh = b.xh ) ");
			String[] inputVNew = new String[inputV.length*2];
			
			for(int i=0;i< inputVNew.length;i++){
				if(i < inputV.length){

					inputVNew[i] = inputV[i];
				}else{
					inputVNew[i] = inputV[i - inputV.length];
				}
			}
			// ����
			if(dao.runUpdate(sql2.toString(), inputVNew)){
				
				sqlDelByztb.append(" where a.xh in  (");
				sqlDelByztb.append(" select xh ");
				sqlDelByztb.append(" from view_xsbfxx c where 1=1   ");
				sqlDelByztb.append(searchTjByUser);
				sqlDelByztb.append(" ");
				sqlDelByztb.append(searchTj);	
				sqlDelByztb.append(") ");
				return dao.runUpdate(sqlDelByztb.toString(), inputV);
				
			}else{
				return false;
			}
			
		}
	};
	
	/**
	 * @����: ѧ���Ƿ��ظ�
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-10-12 ����11:43:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> checkIsFhJfhbDr(String xh){
		List<String> paraList = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select xh,count(*)zs from xsxxb where xh  = ? group by xh");
		paraList.add(xh);
		String zs = "0";
		HashMap<String,String> dataMap = dao.getMapNotOut(sql.toString(), paraList.toArray(new String[]{}));
		if(!dataMap.isEmpty()){
			zs = dataMap.get("zs");
		}
		boolean rs = "1".equals(zs) ? true :false;
		dataMap.put("rs", rs+"");
		dataMap.put("zs", zs);
		return dataMap;
	}
	
	/**
	 * @����: ��֤�Ƿ����ѧ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����11:44:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String sfczXsxxb(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*)sfczXsxxb from xsxxb where xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "sfczXsxxb");
	}
	
	/**
	 * @����: ��֤�Ƿ��ҵ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����11:45:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xhfby(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*)xhfby from xsxxb where xh = ? and sfzx = '����У' ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "xhfby");
	}
	
	/**
	 * @����: ���Ի����뱣��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-11 ����09:24:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param byclForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runUpdateForDr(List<String[]> list,ByclForm byclForm) throws Exception{
		/********��ɾ����塢�ٸ���*********/
		StringBuilder sql = new StringBuilder();
		
		/*ȡ�á���ҵ����ѧ��������*/
		XjydDao xjydlb = new XjydDao();
		XjydForm xjydForm = new XjydForm();
		xjydForm.setXjlbmc("��ҵ");
		xjydForm = xjydlb.getModel(xjydForm);
		
		sql.append("update xsxxb set ");
		sql.append("sfyby='��', byny = '"+byclForm.getByny()+"', sfzx='����У', xjztm='��ѧ��', xjlb='��ҵ'");
		
		if(xjydForm !=null ){
			sql.append(", xjlbdm ='"+ xjydForm.getXjlbdm() + "' ");
		}else{
			sql.append(", xjlbdm ='' ");
		}
		
		/* ɾ��ѧ����Ϣ-��ҵ����-��ҵ״̬��[��¼����ԭѧ��������Ϣ]*/
		StringBuilder sqlDelByztb = new StringBuilder();
		sqlDelByztb.append(" delete from XG_XSXX_BYCL_BYZTB ");
		
		/*����ѧ����Ϣ-��ҵ����-��ҵ״̬��[��¼����ԭѧ��������Ϣ]*/
		StringBuilder sqlInsertByztb = new StringBuilder();
		sqlInsertByztb.append(" insert into XG_XSXX_BYCL_BYZTB ");
		sqlInsertByztb.append("   (xh, sfby, byny, byzt, Yxjlbdm, Yxjlb, yxjztm, ysfzx) ");
		sqlInsertByztb.append("   select xh, '1' sfby, '"+byclForm.getByny()+"'byny, '0' byzt, xjlbdm, xjlb, xjztm, sfzx ");
		sqlInsertByztb.append("     from xsxxb ");
		
		List<String> sqlArr=new ArrayList<String>();
		
		if(list.size()>0){
			StringBuilder sqlxh = new StringBuilder();
			sqlxh.append(" where xh in (");
			for (int i = 0; i < list.size(); i++) {
				if (i != 0) {
					sqlxh.append(",");
				}
				sqlxh.append("'"+list.get(i)[0]+"'");
			}
			
			sqlxh.append(") "); 
			sql.append(sqlxh);

			sqlDelByztb.append(sqlxh);
			sqlInsertByztb.append(sqlxh);			
			
			sqlArr.add(sqlDelByztb.toString());
			sqlArr.add(sqlInsertByztb.toString());
			sqlArr.add(sql.toString());
			return dao.saveArrDate(sqlArr.toArray(new String[]{}));
		}
		return true;
	}
}
