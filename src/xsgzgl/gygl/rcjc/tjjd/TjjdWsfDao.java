/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-15 ����05:33:10 
 */  
package xsgzgl.gygl.rcjc.tjjd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
* @ģ������: �����������ָ��Ի�ͳ�ƹ���ģ��
 * @�๦������: ��¥����ѧԺͳ��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2017-8-15 ����05:33:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjjdWsfDao extends SuperDAOImpl<TjjdWsfForm> {
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TjjdWsfForm t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TjjdWsfForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
	}
	
	
	/**
	 * 
	 * @����:��¥��ͳ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-15 ����05:45:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdtjList(TjjdWsfForm t)
		throws Exception {
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		
		sql.append("select lddm,ldmc,count(case when to_number(fs) >=90 then 1 else null end) jsf, ");
		sql.append("count(case when to_number(fs) between 80 and 89 then 1 else null end) bsf, ");
		sql.append("count(case when to_number(fs) between 60 and 79 then 1 else null end) qsf, ");
		sql.append("count(case when to_number(fs) <60 then 1 else null end) lsf from ( ");
		sql.append("select a.lddm, a.qsh, a.jcrq, a.lrsj, a.fs,b.kssj, b.jssj, c.ldmc ");
		sql.append("from xg_gygl_new_wsjc_qsfsb a ");
		sql.append("left join xg_gygl_new_wsjc_jcrcb b on a.guid = b.guid ");
		sql.append("left join xg_gygl_new_ldxxb c on a.lddm = c.lddm ");
		sql.append("where (a.lddm,a.qsh) in (select lddm,qsh from xg_gygl_new_qsxxb) ");
		if(!StringUtils.isBlank(t.getKssj())&&!StringUtils.isBlank(t.getJssj())){
			sql.append("and (b.kssj between ? and ? or b.jssj between ? and ?) ");
			params.add(t.getKssj());
			params.add(t.getJssj());
			params.add(t.getKssj());
			params.add(t.getJssj());
		}
		sql.append(") group by lddm,ldmc order by lddm");
		
		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}
	
	
	/**
	 * 
	 * @����:��ѧԺͳ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-15 ����05:45:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXytjList(TjjdWsfForm t)
		throws Exception {
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select xydm,xymc,yjqs,yxqs,round(yxqs/yjqs*100,2)||'%' yxl,bhg,round(bhg/yjqs*100,2)||'%'bhgl from ( ");
		sql.append("select xydm,(select bmmc from zxbz_xxbmdm t1 where t.xydm=t1.bmdm) xymc, ");
		sql.append("count(case when fs is not null then 1 end) yjqs, ");
		sql.append("count(case when to_number(fs) >=80 then 1 else null end) yxqs, ");
		sql.append("count(case when to_number(fs) < 60 then 1 else null end) bhg from ( ");
		sql.append("select a.lddm, a.qsh, a.jcrq, a.lrsj, a.fs,b.kssj, b.jssj, c.xydm ");
		sql.append("from xg_gygl_new_wsjc_qsfsb a ");
		sql.append("left join xg_gygl_new_wsjc_jcrcb b on a.guid = b.guid ");
		sql.append("left join xg_gygl_new_qsxxb c on a.lddm = c.lddm and a.qsh=c.qsh ");
		sql.append("where xydm is not null and (a.lddm,a.qsh) in (select lddm,qsh from xg_gygl_new_qsxxb) ");
		if(!StringUtils.isBlank(t.getKssj())&&!StringUtils.isBlank(t.getJssj())){
			sql.append("and (b.kssj between ? and ? or b.jssj between ? and ?) ");
			params.add(t.getKssj());
			params.add(t.getJssj());
			params.add(t.getKssj());
			params.add(t.getJssj());
		}
		sql.append(") t group by xydm) order by xydm");
		
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}

}
