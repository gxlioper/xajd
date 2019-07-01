package xsgzgl.szdw.general.tjcx.szry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.zfsoft.utils.StringUtil;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralDAO;
import xsgzgl.szdw.general.SzdwGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_ͳ�Ʋ�ѯ_˼����Ա_ͨ��_DAO��
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

public class SzryDAO extends SzdwGeneralDAO {

	// ==================ִ�в�ѯ���� begin =============================

	/**
	 * ���˼����Ա�����
	 * 
	 * @date 2013-01-10
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSzryList(SzdwGeneralForm myForm,
			SzryModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

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
		StringBuilder sql = new StringBuilder();

		sql.append("select a.* from(");
		sql.append(" select a.bmdm bmdm, decode(bmmc,null,'����',bmmc)bmmc,");
		sql.append(" zrs,nans,nvs,nvl(fdys,0)fdys, nvl(bzrs,0) bzrs,(case when (nvl(fdys, 0)='0' and nvl(bzrs, 0)='0') then '2' else '1' end) bbzt, wjyhs, jrs from (select bmdm, bmmc, count(*) zrs, sum(nan) nans, sum(nv) nvs,(count(*) - count(zczg)) wjyhs, sum(jryx) jrs");
		sql.append(" from (select a.*, b.yhm zczg from (select a.*, b.bmmc," );
		sql.append(" (case when a.xb = '1' then  1  when a.xb = '��' then  1  when a.xb is null then  1  else   0 end) nan,");
		sql.append(" (case when a.xb = '2' then   1 when a.xb = 'Ů' then  1  else  0 end) nv,");
		sql.append(" (case when sfjryx = 'true' then  1 else  0 end) jryx from  fdyxxb a,");
        sql.append(" zxbz_xxbmdm b where a.bmdm = b.bmdm) a");
        sql.append(" left join yhb b on a.zgh = b.yhm) group by bmdm, bmmc) a left join (select bmdm, sum(fdy) fdys, sum(bzr) bzrs ");
		sql.append(" from (select b.bmdm, a.fdy, a.bzr");
		sql.append(" from (select distinct zgh, 1 fdy, 0 bzr from fdybjb union all select distinct zgh, 0 fdy, 1 bzr from bzrbbb) a left join fdyxxb b on a.zgh = b.zgh)");
		sql.append(" group by bmdm) b on a.bmdm = b.bmdm )a ");
		sql.append(query);

		// ====================SQLƴװ end================================

		String[] colList = new String[] { "bmdm", "bmmc", "zrs", "nans", "nvs","fdys", "bzrs", "wjyhs", "jrs" };

		DAO dao = DAO.getInstance();

		return dao.getListNotOut(sql.toString(), inputV);
		
//		List<String[]> list =(ArrayList<String[]>) dao.rsToListNotOut(sql.toString(), inputV);
//		return list;
	}

	// ==================ִ�в�ѯ���� end =============================
	
	
	
	/**
	 * 
	 * ˼����Աͳ��--��ϸ����
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	public List<HashMap<String, String>> getSzryInfo(SzdwGeneralForm myForm) throws Exception{
		
		List<String> input = new ArrayList<String>();
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from (");
		sql.append(" select distinct t.zgh,t.xm, decode(t.xb, '��', '1', 'Ů', '2', '', '1', t.xb) xb,t.sfjryx,t.bmdm,b.bmmc,t.lxdh,");
		sql.append(" (case when t.bmdm in (select  distinct a.bmdm from fdyxxb a, zxbz_xxbmdm b  where a.bmdm=b.bmdm and  (zgh in (select  zgh   from fdybjb) or zgh in ( select  zgh from bzrbbb ))) then '1' else '2' end) bbzt ");
		sql.append(" from fdyxxb t, zxbz_xxbmdm b where t.bmdm = b.bmdm) a where 1=1 ");
		sql.append(searchTj);
		for(int i=0;i<inputV.length;i++){
			input.add(inputV[i]);
		}
		
		if (!StringUtil.isNull(myForm.getBmdm())){
			sql.append(" and bmdm=? ");
			input.add(myForm.getBmdm());
		}
		
		if (!StringUtil.isNull(myForm.getXb())){
			sql.append(" and xb=? ");
			input.add(myForm.getXb());
		}
		
		if (!StringUtil.isNull(myForm.getFdydb())){
			sql.append(" and exists (select 1 from fdybjb t4 where a.zgh=t4.zgh) ");
		}
		
		if (!StringUtil.isNull(myForm.getBzrdb())){
			sql.append(" and exists (select 1 from bzrbbb t5 where a.zgh=t5.zgh) ");
		}
		
		if ("true".equalsIgnoreCase(myForm.getJryx())){
			sql.append(" and sfjryx='true' ");
		}
		
		return commonPageQueryForMap(myForm.getPages(), sql.toString(), input.toArray(new String[]{}));
	}
	
	
}
