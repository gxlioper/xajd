/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����04:02:40 
 */  
package com.zfsoft.xgxt.xsxx.xsxxcj;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;


import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.demo.SimpleForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(ѧ����Ϣ�ɼ�--���ְҵ��ѧ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-7-30 ����04:02:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxcjDao extends SuperDAOImpl<XsxxcjForm> {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxcjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxcjForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select a.*,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.hkszdshen)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdshi)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdxian)||hkszdz hkszdmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jtdzshen)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzshi)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzxian)||jtdzz jtdzmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.sydshen)||(select qxmc from dmk_qx e where e.qxdm=a.sydshi)||(select qxmc from dmk_qx e where e.qxdm=a.sydxian) sydmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jgshen)||(select qxmc from dmk_qx e where e.qxdm=a.jgshi)||(select qxmc from dmk_qx e where e.qxdm=a.jgxian) jgmc,");
		sql.append("(select mzmc from mzdmb e where e.mzdm=a.ssmz) mzmc,");
		sql.append("b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from xsxx_xscjxxb a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	public XsxxcjForm getModel(XsxxcjForm t) throws Exception{
		XsxxcjForm model=new XsxxcjForm();
		StringBuilder sql = new StringBuilder("select * from (select a.*,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.hkszdshen)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdshi)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdxian)||hkszdz hkszdmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jtdzshen)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzshi)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzxian)||jtdzz jtdzmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.sydshen)||(select qxmc from dmk_qx e where e.qxdm=a.sydshi)||(select qxmc from dmk_qx e where e.qxdm=a.sydxian) sydmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jgshen)||(select qxmc from dmk_qx e where e.qxdm=a.jgshi)||(select qxmc from dmk_qx e where e.qxdm=a.jgxian) jgmc,");
		sql.append("(select mzmc from mzdmb e where e.mzdm=a.ssmz) mzmc,");
		sql.append("b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from xsxx_xscjxxb a left join view_xsjbxx b on a.xh=b.xh) where 1=1 ");
		sql.append(" and xh=?");
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{t.getXh()});
		BeanUtils.copyProperties(model, map);
		return model;
	};


	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("xsxx_xscjxxb");
		super.setKey("xh");
		super.setClass(XsxxcjForm.class);
		
	}



	/** 
	 * @����:TODO(��ȡѧ���б�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-1 ����05:55:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(XsxxcjForm model,
			User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.* from view_xsjbxx a where not exists (select 1 from xsxx_xscjxxb c where a.xh = c.xh  ) ");
		sql.append("order by a.nj, a.xydm, a.zydm, a.bjdm, a.xh) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}



	/**
	 * @param user 
	 * @param model  
	 * @����:TODO(��ѯѧ��������Ϣͳ�ơ�̨�ˡ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-2 ����10:36:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<String[]> getXsjbxxtzList(XsxxcjForm model, User user) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(xymc, '  �ϼ�') xymc,nvl(to_char(nj), '�ϼ�') nj, ");
		sql.append(" nvl(sum(decode(xb, '1', 1,'��',1)),0) nanrs, ");
		sql.append(" nvl(sum(decode(xb, '2', 1,'Ů',1)),0) nvrs, ");
		sql.append(" count(xh) zrs, ");
		sql.append(" nvl(sum(decode(sfzx || xb, '��У1', 1, '��У��', 1,'1',1,'��',1)),0) zxnanrs, ");
		sql.append(" nvl(sum(decode(sfzx || xb, '��У2', 1, '��УŮ', 1,'2',1,'Ů',1)),0) zxnvrs, ");
		sql.append(" nvl(sum(decode(sfzx, '��У', 1,'',1)),0) zxzrs, ");
		sql.append(" nvl(sum(decode(sfdgsx || xb, '��1', 1, '����', 1)),0) dgsxnanrs, ");
		sql.append(" nvl(sum(decode(sfdgsx || xb, '��2', 1, '��Ů', 1)),0) dgsxnvrs, ");
		sql.append(" nvl(sum(decode(sfdgsx, '��', 1)),0) dgsxzrs, ");
		sql.append(" nvl(sum(decode(sfzs || xb, '��1', 1, '����', 1)),0) zsnanrs, ");
		sql.append(" nvl(sum(decode(sfzs || xb, '��2', 1, '��Ů', 1)),0) zsnvrs, ");
		sql.append(" nvl(sum(decode(sfzs, '��', 1)),0) zszrs, ");
		sql.append(" nvl(sum(decode(sfsqrd || xb, '��1', 1, '����', 1)),0) sqrdnanrs, ");
		sql.append(" nvl(sum(decode(sfsqrd || xb, '��2', 1, '��Ů', 1)),0) sqrdnvrs, ");
		sql.append(" nvl(sum(decode(sfsqrd, '��', 1)),0) sqrdzrs, ");
		sql.append(" nvl(sum(decode(sfssmz || xb, '��1', 1, '����', 1)),0) ssmznanrs,");
		sql.append(" nvl(sum(decode(sfssmz || xb, '��2', 1, '��Ů', 1)),0) ssmznvrs, ");
		sql.append(" nvl(sum(decode(sfssmz, '��', 1)),0) ssmzzrs ");
		sql.append(" from (select * from (select a.xh,a.xb,c.xydm,nvl(c.xymc, 'δ֪'||'"+Base.YXPZXY_KEY+"') xymc, ");
		sql.append(" c.zydm,c.zymc,c.bjdm,c.bjmc,nvl(to_char(c.nj), 'δ֪�꼶') nj,a.sfzx, ");
		sql.append(" b.sfdgsx,b.sfzx sfzs,b.sfsqrd,b.hksfjrxx,b.sfssmz,b.sflspx,b.sfzjxy, ");
		sql.append(" b.sfjjkn,b.stsfcj,b.sfxxkn,b.sfxlkr,b.sfjtkr,b.sfyqtkr from xsxxb a ");
		sql.append(" left join xsxx_xscjxxb b on a.xh = b.xh left join view_njxyzybj_all c ");
		sql.append(" on a.bjdm = c.bjdm) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(") group by rollup(xymc, nj) order by xymc desc,nj");
		
		return dao.rsToVatorNotOut(sql.toString(), inputV);
	}



	/** 
	 * @����:TODO(��ѯѧ��������Ϣͳ�ơ�̨�ˡ�)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-2 ����02:05:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<String[]> �������� 
	 * @throws 
	 */
	public List<String[]> exportXsknxxtz(XsxxcjForm model, User user) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(xymc,'  �ϼ�')xymc, nvl(to_char(nj),'�ϼ�')nj, ");
		sql.append(" count(decode(sfjjkn || xb, '��1', 1,'����',1)) jjknnanrs,count(decode(sfjjkn || xb, '��2', 1,'��Ů',1)) jjknnvrs,count(decode(sfjjkn, '��', 1)) jjknzrs,");
		sql.append(" count(decode(sfxlkr || xb, '��1', 1,'����',1)) xlkrnanrs,count(decode(sfxlkr || xb, '��2', 1,'��Ů',1)) xlkrnvrs,count(decode(sfxlkr, '��', 1)) xlkrzrs,");
		sql.append(" count(decode(sfjjkn || xb, '��1', 1,'����',1)) jjknnanrs,count(decode(sfjjkn || xb, '��2', 1,'��Ů',1)) jjknnvrs,count(decode(sfjjkn, '��', 1)) jjknzrs,");
		sql.append(" count(decode(sfxxkn || xb, '��1', 1,'����',1)) xxknnanrs,count(decode(sfxxkn || xb, '��2', 1,'��Ů',1)) xxknnvrs,count(decode(sfxxkn, '��', 1)) xxknzrs,");
		sql.append(" count(decode(stsfcj || xb, '��1', 1,'����',1)) stcjnanrs,count(decode(stsfcj || xb, '��2', 1,'��Ů',1)) stcjnvrs,count(decode(stsfcj, '��', 1)) stcjzrs,");
		sql.append(" count(decode(sfyqtkr || xb, '��1', 1,'����',1)) qtkrnanrs,count(decode(sfyqtkr || xb, '��2', 1,'��Ů',1)) qtkrnvrs,count(decode(sfyqtkr, '��', 1)) qtkrzrs,");
		sql.append(" count(decode(sfzjxy || xb, '��1', 1,'����',1)) zjxynanrs,count(decode(sfzjxy || xb, '��2', 1,'��Ů',1)) zjxynvrs,count(decode(sfzjxy, '��', 1)) zjxyzrs");
		sql.append(" from (select* from(select a.xh, a.xb, c.xydm, nvl(c.xymc,'δ֪'||'"+Base.YXPZXY_KEY+"') xymc, c.zydm, c.zymc, c.bjdm, c.bjmc, nvl(to_char(c.nj),'δ֪�꼶') nj, a.sfzx, b.sfdgsx,b.sfzx sfzs,b.sfsqrd");
		sql.append(" ,b.hksfjrxx,b.sfssmz,b.sflspx,b.sfzjxy,b.sfjjkn,b.stsfcj,b.sfxxkn,b.sfxlkr,b.sfjtkr,b.sfyqtkr");
		sql.append(" from xsxxb a left join xsxx_xscjxxb b on a.xh = b.xh left join view_njxyzybj_all c on a.bjdm=c.bjdm ) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(") group by rollup(xymc, nj) order by xymc desc,nj");
		
		return dao.rsToVatorNotOut(sql.toString(), inputV);
	}

	

}
