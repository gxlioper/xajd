/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-27 ����06:54:47 
 */  
package com.zfsoft.xgxt.znxgl.znxgl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import oracle.sql.CLOB;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�  yxy[����:1206]
 * @ʱ�䣺 2015-8-27 ����06:54:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZnxglDao extends SuperDAOImpl<ZnxglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZnxglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZnxglForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
	//	String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.xjbh,");
		sql.append(" t.jsrbh,");
		sql.append(" t.fprbh,");
		sql.append(" t.jsrydbj,");
		sql.append(" t.jsrscbj,");
		sql.append(" t1.fsrbh,");
		sql.append(" t1.fssj,");
		sql.append(" t1.ztlb,");
		sql.append(" t1.xjzt,");
		sql.append(" t1.fsrydbj,");
		sql.append(" t1.fsrscbj,");
		sql.append(" t2.xm fsrxm");
		sql.append(" from XG_ZNX_NEW_SXB t");
		sql.append(" left join XG_ZNX_NEW_FXB t1");
		sql.append(" on t.xjbh = t1.xjbh");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.fsrbh = t2.xh");
		sql.append(" where t.jsrbh = 'ϵͳ����Ա'  and t.jsrscbj = '0'");
		sql.append(" )t where 1= 1  ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(ZnxglForm.class);
		super.setKey("xjbh");
		super.setTableName("XG_ZNX_NEW_SXB");
	}
	
	//����Ա�ż��鿴
	public HashMap<String, String> glyXjckMap(ZnxglForm t){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xjbh,");
		sql.append(" t.jsrbh,");
		sql.append(" t.fprbh,");
		sql.append(" t.jsrydbj,");
		sql.append(" t.jsrscbj,");
		sql.append(" t1.fsrbh,");
		sql.append(" t1.fssj,");
		sql.append(" t1.ztlb,");
		sql.append(" t1.xjzt,");
		sql.append(" t1.fsrydbj,");
		sql.append(" t1.fsrscbj,");
		sql.append(" t2.xm fsrxm");
		sql.append(" from XG_ZNX_NEW_SXB t");
		sql.append(" left join XG_ZNX_NEW_FXB t1");
		sql.append(" on t.xjbh = t1.xjbh");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.fsrbh = t2.xh");
		sql.append(" where t.jsrbh = ? ");
		sql.append(" and t.xjbh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getJsrbh(),t.getXjbh()});
	}
	
	//��ȡclob�ֶΣ��ż��������ݣ�
	public String getFsnr_Clob(String xjbh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select fsnr from XG_ZNX_NEW_FXB t where t.xjbh = ?");
		CLOB clob  = dao.getOneClob(sql.toString(), new String[]{xjbh}, "fsnr");
		return clob.getSubString(1L, (int) clob.length());
	}
	
	//�ż�����ʱɾ��������ԭ�еĽ��ܼ�¼
	public boolean delFprJsjl(ZnxglForm t) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_ZNX_NEW_SXB where jsrbh = ? and xjbh = ? ");
		int result = dao.runDelete(sql.toString(), new String[]{t.getJsrbh(),t.getXjbh()});
		return result>0 ? true : false;
	}
	
	//վ��������
	public String getZnxTx(String username){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) wdyjs from xg_znx_new_sxb t where t.jsrbh = ?  and t.jsrydbj = '0' and t.jsrscbj = '0'");
		return dao.getOneRs(sql.toString(), new String[]{username}, "wdyjs");
	}
	
	public boolean runUpate_sxbjl(ZnxglForm sx) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrydbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
		
	}
    
    public boolean runUpate_sxbscjl(ZnxglForm sx)throws Exception{
    	StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrscbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
	}
    /**
     * 
     * @����:�ظ�����ʱԭ�յ����ż�����jsrbhΪ��ǰ��������ʵ��½�˺�
     * @���ߣ�yxy[���ţ�1206]
     * @���ڣ�2015-10-16 ����02:36:19
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param sx
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean runUpdate_znxsjx(ZnxglForm sx) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append("update XG_ZNX_NEW_SXB set jsrbh=? where xjbh= ? and jsrbh = 'ϵͳ����Ա'");
    	return dao.runUpdate(sql.toString(), new String[]{sx.getJsrbh(),sx.getXjbh()});
    }
    
    /**
     * 
     * @����:����
     * @���ߣ�����Դ[���ţ�1206]
     * @���ڣ�2015-12-7 ����03:19:04
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param sx
     * @return
     * @throws Exception
     * boolean �������� 
     * @throws
     */
    public boolean save(ZnxglForm sx) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into XG_ZNX_NEW_SXB (xjbh,jsrbh,fprbh) values(?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh(),sx.getFprbh()});
    }
}
