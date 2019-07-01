/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:14:23 
 */  
package com.zfsoft.xgxt.szdw.jtff;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(����Ա��������--ɽ��Ϋ��) 
 * @���ߣ� CMJ [���ţ�913]
 * @ʱ�䣺 2013-8-5 ����11:14:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyjtffDAO extends SuperDAOImpl<FdyjtffForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyjtffForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdyjtffForm model, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		/*StringBuilder sql = new StringBuilder("select * from (select a.id,a.zgh,a.bzlx,decode(a.bzlx,'0','��λ����','1','��Ч���˲���')bzlxmc,a.kpdj,decode(a.kpdj,'0','����','1','����','2','�ϸ�')kpdjmc,a.xn,a.xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,a.bzje,b.xm,b.bmdm,(select bmmc from zxbz_xxbmdm e where b.bmdm=e.bmdm)bmmc,b.xb,(select xb from dmk_xb e where b.xb=e.xbdm) xbmc,b.lxdh");
		sql.append(" from WFXY_FDYJTXXB a left join fdyxxb b on a.zgh=b.zgh) where 1=1 ");*/
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from VIEW_NEW_DC_SZDW_JTFF where 1=1 ");
		
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("WFXY_FDYJTXXB");
		super.setKey("Id");
		super.setClass(FdyjtffForm.class);
		
	}

	/** 
	 * @����:TODO(�жϸ���Ա���������Ƿ���¼��)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����01:56:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(FdyjtffForm model) {
		// TODO �Զ����ɷ������
		StringBuilder sql = new StringBuilder();
		sql.append("select *��from WFXY_FDYJTXXB where zgh=? and xn=? and xq=?");
		List<HashMap<String, String>> list=dao.getArrayList(sql.toString(), new String[]{model.getZgh(),model.getXn(),model.getXq()}, new String[]{"zgh"});
		return list.size()!=0;
	}

	/** 
	 * @����:TODO(��ȡ����Ա��Ϣ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����02:39:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getFdyjbxx(String zgh) {
		// TODO �Զ����ɷ������
		String sql="select zgh,xm,(case xb when '1' then '��' when '��' then '��' when '2' then 'Ů' when  'Ů' then 'Ů' else '' end) xbmc,lxdh,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc from fdyxxb a where zgh=?";
		return dao.getMap(sql, new String[]{zgh}, new String[]{"zgh","xm","xbmc","bmmc","lxdh"});
	}

	/** 
	 * @����:TODO(��ȡ����ԱList)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-8-5 ����02:47:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyPageList(FdyjtffForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.*,(select zcmc from zcb f where a.zc=f.zcdm)zcmc,(case xb when '1' then '��' when '��' then '��' when '2' then 'Ů' when  'Ů' then 'Ů' else '' end) xbmc");
		sql.append(" from view_fdyxx a) a where 1 = 1");
		sql.append(searchTj);
		
		if("zxswh".equals(model.getFdyjtfflx())){ // ��������ѯ-��ѯʦ����-��ѯʦά��
			sql.append(" and (not exists (select 1 from xg_view_xlzx_zxsxx z where a.zgh=z.zgh and z.datazt='1' and z.status = '1')) ");
		}
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	/** 
	 * @����:��ȡ����Ա�����ҵ�ʦ������ˢ�¸�ҳ�棩
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-26 ����08:46:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyQsdsPageList(FdyjtffForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select lxdh,zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc,(case xb when '1' then '��' when '��' then '��' when '2' then 'Ů' when  'Ů' then 'Ů' else '' end) xbmc");
		sql.append(" from fdyxxb a where exists (select 1 from XG_GYGL_QSDS qsdsb where a.zgh=qsdsb.zgh)) a where 1 = 1");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	public FdyjtffForm getModel(FdyjtffForm model)throws Exception{
		String sql="select id,zgh,bzlx,decode(bzlx,'0','��λ����','1','��Ч���˲���')bzlxmc,kpdj,decode(kpdj,'0','����','1','����','2','�ϸ�')kpdjmc,xn,xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,bzje from wfxy_fdyjtxxb a where id=?";
		FdyjtffForm myForm=new FdyjtffForm();
		HashMap<String, String> map=dao.getMapNotOut(sql, new String[]{model.getId()});
		BeanUtils.copyProperties(myForm, map);
		return myForm;
	}

}
