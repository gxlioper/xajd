package xsgzgl.szdw.general.tjcx.bmqk;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.szdw.general.SzdwGeneralDAO;
import xsgzgl.szdw.general.SzdwGeneralForm;

import com.zfsoft.utils.StringUtil;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͳ�Ʋ�ѯ_�������_ͨ��_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class BmqkDAO extends SzdwGeneralDAO {

	// ==================ִ�в�ѯ���� begin =============================

	/**
	 * ��ò�����������
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getBmqkList(SzdwGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================��������===================================
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================�������� end================================

		// ====================SQLƴװ================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select a.xydm,a.xymc,a.bjdms,a.fdydbs,a.mfdydbs,a.bzrdbs,a.mbzrdbs from(");
		tableSql.append("select xydm,xymc,count(bjdm)bjdms,count(fdydb)fdydbs ,(count(bjdm)-count(fdydb)) mfdydbs,count(bzrdb)bzrdbs ,(count(bjdm)-count(bzrdb)) mbzrdbs from( ");
		tableSql.append("select a.*, b.bjdm bzrdb ");
		tableSql.append("from (select a.nj, a.xydm, a.xymc, a.bjdm, a.bjmc, b.bjdm fdydb ");
		tableSql.append("from view_njxyzybj a ");
		tableSql.append("left join (select distinct bjdm from fdybjb) b on a.bjdm = b.bjdm) a ");
		tableSql.append("left join (select distinct bjdm from bzrbbb) b on a.bjdm = b.bjdm  ");
		tableSql.append(query);
		tableSql.append(") group by xydm,xymc ");
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "xydm", "xymc","bjdms", "fdydbs", "mfdydbs", "bzrdbs",
				"mbzrdbs" };

		DAO dao = DAO.getInstance();

		//ArrayList<String[]> list =(ArrayList<String[]>) dao.rsToListNotOut(tableSql.toString(), inputV);
		return dao.getListNotOut(tableSql.toString(), inputV);
	}

	// ==================ִ�в�ѯ���� end =============================
	
	
	
	/**
	 * 
	 * ��ѯ����ͳ������ľ�����Ϣ
	 * 
	 */
	public List<HashMap<String, String>> getBmtjInfo(SzdwGeneralForm model, String[] njArr)throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append("select a.*, rownum r from (select b.xydm,b.xymc, b.zydm, b.zymc,b.bjdm,b.bjmc,b.nj,fdy,bzr ");
		sql.append("from (select xydm,xymc,zydm,zymc,bjdm,bjmc,nj,WM_CONCAT(fdy) fdy ");
		sql.append("from (select t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc,t1.nj,");
		sql.append("case when t2.zgh is null then '' else '['||t4.xm||':'||t2.zgh||']' end fdy ");
		sql.append("from view_njxyzybj t1 left join fdybjb t2 on t1.bjdm = t2.bjdm left join yhb t4 on t2.zgh = t4.yhm) ");
		sql.append("group by xydm, xymc, zydm, zymc, bjdm, bjmc, nj) b ");
		sql.append("left join (select xydm,xymc,zydm,zymc,bjdm,bjmc,nj,WM_CONCAT(bzr) bzr ");
		sql.append("from (select t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc,t1.nj,");
		sql.append("case when t3.zgh is null then '' else '['||t5.xm||':'||t3.zgh||']' end bzr ");
		sql.append("from view_njxyzybj t1 left join bzrbbb t3 on t1.bjdm = t3.bjdm left join yhb t5 on t3.zgh = t5.yhm) ");
		sql.append("group by xydm, xymc, zydm, zymc, bjdm, bjmc, nj) c ");
		sql.append("on b.xydm = c.xydm and b.zydm = c.zydm and b.bjdm = c.bjdm and b.nj = c.nj where 1=1 ");
		sql.append(searchTj);
		for(int i=0;i<inputV.length;i++){
			input.add(inputV[i]);
		}
		
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" and b.xydm=? ");
			input.add(model.getXydm());
		}
		
		if (njArr != null && njArr.length > 0){
			sql.append(" and (");
			
			for (int i = 0 ; i < njArr.length ; i++){
				sql.append("b.nj=?");
				input.add(njArr[i]);
				
				if (i != njArr.length - 1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		if ("yes".equalsIgnoreCase(model.getBzrdb())){
			sql.append(" and bzr is not null ");
		} else if ("no".equalsIgnoreCase(model.getBzrdb())){
			sql.append(" and bzr is null ");
		}
		
		if ("yes".equalsIgnoreCase(model.getFdydb())){
			sql.append(" and fdy is not null ");
		} else if ("no".equalsIgnoreCase(model.getFdydb())){
			sql.append(" and fdy is null ");
		}
		
		sql.append(") a where 1=1 ");
		
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(), sql.toString(), input.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * ��ѯ���ศ��Աͳ������ľ�����Ϣ
	 * 
	 */
	public List<HashMap<String, String>> getFdyInfo(SzdwGeneralForm model, String[] njArr)throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append("select a.*, rownum r from (select xydm,xymc,zydm,zymc,bjdm,bjmc,nj,fdy,");
		sql.append("case when sfjryx = 'true' and bmjb = 'У��' then '����ѧУ�û�' ");
		sql.append("when sfjryx = 'true' and bmjb = 'Ժ��' then '����Ժϵ�û�' else '�༶�û�' end yhsf ");
		sql.append("from (select t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc,t1.nj,t3.sfjryx,");
		sql.append("decode(t5.bmlb,'5','Ժ��','У��') bmjb,");
		sql.append("case when t2.zgh is null then '' else '['||t4.xm||':'||t2.zgh||']' end fdy ");
		sql.append("from view_njxyzybj t1 left join fdybjb t2 on t1.bjdm = t2.bjdm left join yhb t4 on t2.zgh = t4.yhm ");
		sql.append("left join fdyxxb t3 on t2.zgh = t3.zgh left join zxbz_xxbmdm t5 on t3.bmdm=t5.bmdm) ");
		sql.append("where 1=1 ");
		sql.append(searchTj);
		for(int i=0;i<inputV.length;i++){
			input.add(inputV[i]);
		}
		
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" and xydm=? ");
			input.add(model.getXydm());
		}
		
		if (njArr != null && njArr.length > 0){
			sql.append(" and (");
			
			for (int i = 0 ; i < njArr.length ; i++){
				sql.append("b.nj=?");
				input.add(njArr[i]);
				
				if (i != njArr.length - 1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		sql.append(" and fdy is not null) a where 1=1  ");
		
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(), sql.toString(), input.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * ��ѯ���������ͳ������ľ�����Ϣ
	 * 
	 */
	public List<HashMap<String, String>> getBzrInfo(SzdwGeneralForm model, String[] njArr)throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		sql.append("select a.*, rownum r from (select xydm,xymc,zydm,zymc,bjdm,bjmc,nj,bzr,");
		sql.append("case when sfjryx = 'true' and bmjb = 'У��' then '����ѧУ�û�' ");
		sql.append("when sfjryx = 'true' and bmjb = 'Ժ��' then '����Ժϵ�û�' else '�༶�û�' end yhsf ");
		sql.append("from (select t1.xydm,t1.xymc,t1.zydm,t1.zymc,t1.bjdm,t1.bjmc,t1.nj,t2.sfjryx,");
		sql.append("decode(t4.bmlb,'5','Ժ��','У��') bmjb,");
		sql.append("case when t3.zgh is null then '' else '['||t5.xm||':'||t3.zgh||']' end bzr ");
		sql.append("from view_njxyzybj t1 left join bzrbbb t3 on t1.bjdm = t3.bjdm left join yhb t5 on t3.zgh = t5.yhm ");
		sql.append("left join fdyxxb t2 on t3.zgh = t2.zgh left join zxbz_xxbmdm t4 on t2.bmdm=t4.bmdm) ");
		sql.append("where 1=1 ");
		sql.append(searchTj);
		for(int i=0;i<inputV.length;i++){
			input.add(inputV[i]);
		}
		
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" and xydm=? ");
			input.add(model.getXydm());
		}
		
		if (njArr != null && njArr.length > 0){
			sql.append(" and (");
			
			for (int i = 0 ; i < njArr.length ; i++){
				sql.append("b.nj=?");
				input.add(njArr[i]);
				
				if (i != njArr.length - 1){
					sql.append(" or ");
				}
			}
			sql.append(")");
		}
		
		sql.append(" and bzr is not null) a where 1=1 ");	
		
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(), sql.toString(), input.toArray(new String[]{}));
	}
	
}
