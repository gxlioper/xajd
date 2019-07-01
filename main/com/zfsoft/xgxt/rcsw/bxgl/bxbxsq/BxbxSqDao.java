/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-26 ����09:35:19 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-3-26 ����09:35:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BxbxSqDao extends SuperDAOImpl<BxbxSqForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BxbxSqForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(BxbxSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* ");
		sql.append(" from (select t1.*,");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm,");
		}
		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc");
		sql.append(" from view_rcsw_bxbxsq t1");
		//��������ְҵ����ѧԺ
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on t1.bjdm = bzr.bjdm");
		}
		sql.append(" ) t where 1 = 1 ");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ���ձ�����ϸ��Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-27 ����03:17:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBxsqxx(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from view_rcsw_bxbxsq where sqid=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(BxbxSqForm.class);
		super.setKey("sqid");
		super.setTableName("XG_RCSW_BXGL_BXBXSQB");
	}
	
	public boolean checkMzje(String bxje,String csfysj,String xh,String id){
		StringBuilder sql = new StringBuilder();
		csfysj=csfysj.substring(0, 4);
		List<String> paraList = new ArrayList<String>();
		sql.append("select case when (select mzbxsx from XG_RCSW_BXGL_CSSZB t where rownum=1)<zje then '0' else '1' end ck  from ");
		sql.append("(select nvl(a.je1,0)+nvl(b.je2,0)+to_number(?) zje from ");
		paraList.add(bxje);
		sql.append("(select sum(bxje)je1 from XG_RCSW_BXGL_BXBXSQB t1 left join xg_xtwh_shztb t2 on t1.sqid = t2.ywid where t2.shzt='5' and bxxz='���ﱨ��' and substr(t1.csfysj,0,4)=? and t1.xh=? ");
		paraList.add(csfysj);
		paraList.add(xh);
		if(StringUtils.isNotNull(id)){
			sql.append(" and sqid != ? ");
			paraList.add(id);
		}
		sql.append(") a, ");
		
	
		sql.append("(select sum(bxje)je2 from XG_RCSW_BXGL_XSBXBXB where  bxxz='���ﱨ��' and substr(csfysj,0,4)=? and xh=?  ");
		paraList.add(csfysj);
		paraList.add(xh);
		if(StringUtils.isNotNull(id)){
			sql.append(" and bxid != ? ");
			paraList.add(id);
		}
		sql.append(") b )");
		
		return dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "ck").equals("1");
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܶ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-21 ����07:02:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZe(String xh,String csfysj){
		StringBuilder sql = new StringBuilder();
		csfysj = csfysj.substring(0, 4);
		sql.append(" SELECT nvl(SUM(BXJE),0) ZE FROM (");
		sql.append(" select SQID, bxje from XG_RCSW_BXGL_BXBXSQB where xh = ? and shzt != '2' and bxxz = '���ﱨ��' and substr(csfysj,0,4)= ?");
		sql.append(" union ");
		sql.append(" select BXID, bxje from XG_RCSW_BXGL_XSBXBXB where xh = ? and bxxz = '���ﱨ��' and substr(csfysj,0,4)= ?");
		sql.append(" )");
		return dao.getOneRs(sql.toString(), new String[]{xh,csfysj,xh,csfysj}, "ze");
	}
	
}


