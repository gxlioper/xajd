/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:59:15 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.dazcjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ����ת��-���
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:54:21 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazcjgDao extends SuperDAOImpl<DazcjgForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcjgForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(DazcjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,zymc,a.bjdm,a.bjmc,");
		sql.append("b.guid,b.zcfs,b.yjdz,b.yzbm,b.sjr,b.sjrdh,b.dwmc,b.dwdz,b.kdfs,b.kddh,b.yjsj,");
		sql.append("b.yjzt,b.zddacn,b.yqtdrq,b.sjtdrq,b.sjtdr,b.dazcxx,b.sjly,b.ywid,b.sjlrr,b.sjlrsj,");
		sql.append("decode(b.zcfs, '1', '�ʼ�', '2', '�Դ�', b.zcfs) zcfsmc,");
		sql.append("decode(b.yjzt, '1', '���ʼ�', '2', 'δ�ʼ�', b.yjzt) yjztmc,");
		sql.append("case b.dazcxx when '1' then '�ѵǼ�' when '2' then '��ת��' else 'δ�Ǽ�' end dazcxxmc ");
		sql.append("from view_xsjbxx a left join xg_xsxx_dagl_dazcjgb b on a.xh = b.xh");
		sql.append(")t where 1 = 1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(DazcjgForm.class);
		this.setKey("guid");
		this.setTableName("xg_xsxx_dagl_dazcjgb");
	}
	
	/**
	 * @����: ��֤Ψһ�ԣ�ѧ��(xh)
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����04:06:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(DazcjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xsxx_dagl_dazcjgb where xh = ?  ");
		paraList.add(model.getXh());
		if(StringUtils.isNotNull(model.getGuid())){
			sql.append(" and guid <> ? ");
			paraList.add(model.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*��������ʱ����false��������ʱ����true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @����: ͨ��guid�鿴ѧ������ת�������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-10 ����05:24:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,zymc,a.bjdm,a.bjmc,");
		sql.append("b.guid,b.zcfs,b.yjdz,b.yzbm,b.sjr,b.sjrdh,b.dwmc,b.dwdz,b.kdfs,b.kddh,b.yjsj,");
		sql.append("b.yjzt,b.zddacn,b.yqtdrq,b.sjtdrq,b.sjtdr,b.dazcxx,b.sjly,b.ywid,b.sjlrr,b.sjlrsj,");
		sql.append("decode(b.zcfs, '1', '�ʼ�', '2', '�Դ�', b.zcfs) zcfsmc,");
		sql.append("decode(b.yjzt, '1', '���ʼ�', '2', 'δ�ʼ�', b.yjzt) yjztmc,");
		sql.append("case b.dazcxx when '1' then '�ѵǼ�' when '2' then '��ת��' else 'δ�Ǽ�' end dazcxxmc ");
		sql.append("from view_xsbfxx a left join xg_xsxx_dagl_dazcjgb b on a.xh = b.xh");
		sql.append(") where xh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * @����: ����ѧ�Ų鿴������Ƿ�������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����09:17:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getDazcjgRs(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)cnt from xg_xsxx_dagl_dazcjgb where xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "cnt");
	}
	
	/**
	 * @����: ͨ������idɾ�����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-14 ����05:37:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delShjgById(String sqid)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("delete from xg_xsxx_dagl_dazcjgb where ywid = ? ");
		return dao.runUpdate(sql.toString(),new String[]{sqid});
	}

}
