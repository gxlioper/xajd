/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-13 ����09:04:42 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������ģ��
 * @�๦������: TODO����ѧ�Ѳ�������
 * @���ߣ�HongLin 
 * @ʱ�䣺 2013-5-13 ����08:55:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwxfbcglDao extends SuperDAOImpl<RwxfbcglForm>{

	@Override
	public List<HashMap<String, String>> getPageList(RwxfbcglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/**
	 * ʹ�ø߼���ѯ
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> getPageList(RwxfbcglForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select *");
		sql.append(" from view_xg_rwgl_rwxfbcxxb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rwgl_rwxfbcxxb");
		super.setKey("guid");
		super.setClass(RwxfbcglForm.class);
		
	}
	
	/**
	 * 
	 * @����:���Ӳ���Ψһ���жϣ�ѧ�ţ�ѧ�꣩
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:52:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String checkExistForSave(RwxfbcglForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rwgl_rwxfbcxxb where xh=? and xn=?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn()}, "num");
		return num;
		
	}
	
	/**
	 * 
	 * @����:�޸Ĳ���Ψһ���жϣ�ѧ�ţ�ѧ�꣩
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:52:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String checkExistForUpdate(RwxfbcglForm model) {
		StringBuilder sql = new StringBuilder("select count(1) num from xg_rwgl_rwxfbcxxb where xh=? and xn=? and guid<>?");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getGuid()}, "num");
		return num;
		
	}

	/** 
	 * @����:TODO ��õ���ѧ������ѧ�Ѳ�����Ϣ
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-14 ����02:09:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getOneRwxfbcList(String  xh) {
		StringBuilder sql = new StringBuilder("select xn,xfbcsj,xfbcje,bz,rwxn,rwsj,rwd,rwdwdmc,rwdwd,sg,tz,zysl,yysl from view_xg_rwgl_rwxfbcxxb where xh=? ");
				
		return dao.getMapNotOut(sql.toString(),new String[]{xh});
	}
	
	/** 
	 * @����: ��������
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-16 ����05:02:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updatePlbc(RwxfbcglForm model) throws Exception {
		String[] ids = model.getGuid().split(",");
		String[] xhs = model.getXh().split(",");
		StringBuilder guid = new StringBuilder();//���ڴ洢ֵ��Ϊ�յ�guid
		StringBuilder guidsql = new StringBuilder();
		StringBuilder xhsql = new StringBuilder();
		
		if(null!=xhs && xhs.length>0){//���˵�guidΪ�յģ���ƴ��ѧ��
			xhsql.append(" (");
			for (int i = 0 , n = xhs.length ; i < n ; i++){
				if(ids[i]!=null && !"null".equalsIgnoreCase(ids[i]) && !"".equalsIgnoreCase(ids[i])){
					guid.append(ids[i] + ",");
				}
				if(i==(xhs.length-1)){
					xhsql.append(" xh='"+xhs[i]+"' ");
				}else{
					xhsql.append(" xh='"+xhs[i]+"' or ");
				}
			}
			xhsql.append(" ) ");
		}
		String[] guids=guid.toString()!=""?guid.toString().split(","):new String[]{};
		if(null!=guids && guids.length>0){
			guidsql.append(" and (");
			for (int i = 0 , n = guids.length ; i < n ; i++){//ƴ��guid
				if(i==(guids.length-1)){
					guidsql.append(" guid='"+guids[i]+"' ");
				}else{
					guidsql.append(" guid='"+guids[i]+"' or ");
				}
			}
			guidsql.append(" )");
		}
		
		String sql = "update xg_rwgl_rwxfbcxxb set xn='"+model.getXn()+"',xfbcsj='"+model.getXfbcsj()+"',xfbcje='"+model.getXfbcje()+"',bz='"+model.getBz()+"' where 1=1 "+guidsql.toString();
		return dao.runUpdate(sql, new String []{});
	}
	
	/** 
	 * @����: ��������
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-16 ����05:02:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean insertPlbc(RwxfbcglForm model) throws Exception {
		String[] ids = model.getGuid().split(",");
		String[] xhs = model.getXh().split(",");
		StringBuilder guid = new StringBuilder();//���ڴ洢ֵ��Ϊ�յ�guid
		StringBuilder guidsql = new StringBuilder();
		StringBuilder xhsql = new StringBuilder();
		
		if(null!=xhs && xhs.length>0){//���˵�guidΪ�յģ���ƴ��ѧ��
			xhsql.append(" (");
			for (int i = 0 , n = xhs.length ; i < n ; i++){
				if(ids[i]!=null && !"null".equalsIgnoreCase(ids[i]) && !"".equalsIgnoreCase(ids[i])){
					guid .append(ids[i] + ",");
				}
				if(i==(xhs.length-1)){
					xhsql.append(" xh='"+xhs[i]+"' ");
				}else{
					xhsql.append(" xh='"+xhs[i]+"' or ");
				}
			}
			xhsql.append(" ) ");
		}
		String[] guids=guid.toString()!=""?guid.toString().split(","):new String[]{};
		if(null!=guids && guids.length>0){
			guidsql.append(" (");
			for (int i = 0 , n = guids.length ; i < n ; i++){//ƴ��guid
				if(i==(guids.length-1)){
					guidsql.append(" guid='"+guids[i]+"' ");
				}else{
					guidsql.append(" guid='"+guids[i]+"' or ");
				}
			}
			guidsql.append(" )");
		}
		
		xhsql = new StringBuilder();
		xhsql.append(xhsql.toString()!=null && !"".equals(xhsql.toString())?xhsql.toString()+" and ":"");
		String sql = "insert into xg_rwgl_rwxfbcxxb (xh,xn,xfbcsj,xfbcje,bz) select xh,'"+model.getXn()+"','"+model.getXfbcsj()+"','"+model.getXfbcje()+"','"+model.getBz()+"' from xsxxb a where "+xhsql.toString()+" not exists (select 1 from xg_rwgl_rwxfbcxxb b where a.xh=b.xh)";
		return dao.runUpdate(sql, new String []{});
	}
}
