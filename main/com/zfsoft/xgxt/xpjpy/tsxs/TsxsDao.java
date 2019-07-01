/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-2 ����10:13:29 
 */  
package com.zfsoft.xgxt.xpjpy.tsxs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���������Ź���ģ��
 * @�๦������: ����ѧ��ά��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-2 ����10:13:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsDao extends SuperDAOImpl<TsxsModel> {

	/**
	 * ͳ���б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(TsxsModel model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select  a.*,b.xqmc,c.lxmc from (");
		sql.append("select xn,xq,lxdm,count(*) zrs from xg_pjpy_new_tsxsb group by xn,xq,lxdm  ");
		sql.append(") a ");
		sql.append(" left join xqdzb b on a.xq=b.xqdm");
		sql.append(" left join xg_pjpy_new_tslxdmb c on a.lxdm = c.lxdm");
		sql.append(" where 1=1 ");
		if (!StringUtil.isNull(model.getXn())) {
			params.add(model.getXn());
			sql.append(" and a.xn=? ");
		}
		if (!StringUtil.isNull(model.getXq())) {
			params.add(model.getXq());
			sql.append(" and a.xq=? ");
		}

		return getPageList(model, sql.toString(), params
				.toArray(new String[] {}));
	}

	
	/**
	 * ����ѧ����ϸ�б�
	 */
	@Override
	public List<HashMap<String, String>> getPageList(TsxsModel model, User user)
			throws Exception {
	
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.id,a.xn,a.xq,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc, ");
		if("10279".equalsIgnoreCase(Base.xxdm)){
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('��ѧӢ��һ��','��ѧӢ������') and a.xh = b.xh and b.xn='");
			sql.append(model.getXn());
			sql.append( "') kc1, ");
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('��ѧӢ�����','��ѧӢ���ļ�') and a.xh = b.xh and b.xn='");
			sql.append(model.getXn());
			sql.append( "') kc2, ");
			sql.append(" (select cj from cjb b where a.xh = b.xh and upper(b.kcmc) = 'CET-4' and b.xn='");
			sql.append(model.getXn());
			sql.append("') cet4,");
		}
		sql.append(" a.xh,b.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.lxdm,c.lxmc from xg_pjpy_new_tsxsb a ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh left join xg_pjpy_new_tslxdmb c on a.lxdm = c.lxdm) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" and xn='");
		sql.append(model.getXn());
		sql.append("'");
		sql.append(" and xq='");
		sql.append(model.getXq());
		sql.append("'");
		sql.append(" and lxdm='");
		sql.append(model.getLxdm());
		sql.append("'");
	
	return getPageList(model, sql.toString(), inputV);
	}

	
	/**
	 * ����ѧ��δ����б�
	 */
	public List<HashMap<String, String>> getTsxsDtjPageList(TsxsModel model, User user)
			throws Exception {
		
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String xn = model.getXn();
		String xq = model.getXq();
		String lxdm = model.getLxdm();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,");
		if("10279".equalsIgnoreCase(Base.xxdm)){
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('��ѧӢ��һ��','��ѧӢ������') and a.xh = b.xh and b.xn='");
			sql.append(xn);
			sql.append( "') kc1, ");
			sql.append(" (select max(cj) from cjb b where b.kcmc in ('��ѧӢ�����','��ѧӢ���ļ�') and a.xh = b.xh and b.xn='");
			sql.append(xn);
			sql.append( "') kc2, ");
			sql.append(" (select max(cj) from xsdjksb b where a.xh = b.xh and b.djksmc in ('CET4','CET-4') ");
			sql.append(" ) cet4,");
		}
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm,a.nj,a.xz from view_xsbfxx a ");
		if("10279".equalsIgnoreCase(Base.xxdm)){
			sql.append(" where not exists (select 1 from xg_pjpy_new_tsxsb b where a.xh = b.xh and xn ='"+xn+"' and lxdm = '"+lxdm+"')) a ");
		}else{
			sql.append(" where not exists (select 1 from xg_pjpy_new_tsxsb b where a.xh = b.xh and xn ='"+xn+"' and xq = '"+xq+"' and lxdm = '"+lxdm+"')) a ");
		}		
		sql.append(" where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	
	/** 
	 * @����: ��ȡ����ѧ�����ʹ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-2 ����04:12:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTslx(String lxsx) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select * ");
		sb.append(" from xg_pjpy_new_tslxdmb ");
		sb.append(" where 1=1");
		if(lxsx != null && !lxsx.trim().equals("")){
			sb.append(" and lxsx='");
			sb.append(lxsx);
			sb.append("'");
		}			
		sb.append(" order by lxdm ");
		return dao.getListNotOut(sb.toString(), null);
	}
	

	/** 
	 * @����: ��ȡ����ѧ�����ʹ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-2 ����04:12:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTslx() {
		return getTslx(null);
	}

	
	/**
	 * 
	 * @����:��ѯ��������������ѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-15 ����04:59:46
	 * @�޸ļ�¼: 
	 * @param tsxsxhList
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> sctsxs(List<String> tsxsxhList,TsxsModel model) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select xh,xm,xb,nj,xymc,bjmc from view_xsbfxx where xh in(");
		for (int i = 0; i < tsxsxhList.size(); i++) {
			if(i > 0){
				sql.append(",");
			}
			sql.append("'");
			sql.append(tsxsxhList.get(i));
			sql.append("'");
		}		
		sql.append(")");
		sql.append(" and xh not in (select xh from xg_pjpy_new_tsxsb where xn='");
		sql.append(model.getXn());
		sql.append("'");
		sql.append(" and xq='");
		sql.append(model.getXq());
		sql.append("'");
		sql.append(" and lxdm='");
		sql.append(model.getLxdm());
		sql.append("')");
		
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @����:����������Ա
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-15 ����04:54:28
	 * @�޸ļ�¼: 
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTsxs(List<String[]> params) throws SQLException {		
		StringBuilder sql = new StringBuilder();		
		sql.append(" insert into xg_pjpy_new_tsxsb(xh,xn,xq,lxdm,lrr,lrsj) ");
		sql.append(" values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))");
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @����:����ѧ��ѧ�����ͣ�����ɾ������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-16 ����10:51:45
	 * @�޸ļ�¼: 
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean tsxsScForLx(List<String[]> params) throws SQLException {		
		StringBuilder sql = new StringBuilder();		
		sql.append(" delete from  xg_pjpy_new_tsxsb ");
		sql.append(" where xn=? and xq=? and lxdm=? ");
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}

	/** 
	 * @����:ɾ��������Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-5 ����07:04:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delTsxs(String values) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" delete from xg_pjpy_new_tsxsb where id in ( ");
		String[] ids = values.split(",");
		for(int i = 0; i<ids.length; i++){
			
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(" )");
		int deleteInt = 0;
		try {
			deleteInt = dao.runDelete(sql.toString(), ids);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
		if(deleteInt==0){
			return false;
		}else{

			return true;
		}
	}
	
	/**
	 * 
	 * @����:��ѯ������ѧ��
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-16 ����03:44:59
	 * @�޸ļ�¼: 
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXnList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct xn dm,xn mc  from xg_pjpy_new_tsxsb order by xn desc");
		return dao.getListNotOut(sql.toString(), null);
	}
	
	/**
	 * 
	 * @����:�õ���������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-17 ����11:25:59
	 * @�޸ļ�¼: 
	 * @param lxdm
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getNameById(String lxdm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select lxmc ");
		sb.append(" from  xg_pjpy_new_tslxdmb ");
		sb.append(" where lxdm=?");
		String[] inputValue = { lxdm };
		String[] outputValue = { "lxmc" };
		String[] oneRs = dao.getOneRs(sb.toString(), inputValue, outputValue);
		String mc = "";
		if (oneRs != null && oneRs.length > 0) {
			mc = oneRs[0];
		}
		return mc;
	}

	@Override
	protected void setTableInfo() {
		super.setClass(TsxsModel.class);
		super.setTableName("xg_pjpy_new_tsxsb");
		super.setKey("");
		
	}
	
	/** 
	 * @����:������������ѧ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-26 ����09:51:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param xn
	 * @param xq
	 * @param lxdm
	 * @param userName
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plbcTsxs(String ids,String xn,String xq,String lxdm,String userName) throws SQLException{
		String[] idss = ids.split(",");
		String[] strArr = new String[idss.length];
		for (int i = 0;i<idss.length;i++){
			String sql = "insert into xg_pjpy_new_tsxsb(xh,xn,xq,lxdm,lrr) values ('"+idss[i]+"','"+xn+"','"+xq+"','"+lxdm+"','"+userName+"')";
			strArr[i] = sql;
		}
		int [] num = dao.runBatch(strArr);
		return num.length>0;
	}
}
