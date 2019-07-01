/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-1 ����09:57:25 
 */  
package com.zfsoft.xgxt.xlzx.zxzxthjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-7-1 ����09:57:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxthjlDao extends SuperDAOImpl<ZxzxthjlForm>{
	/*
	����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xljkzx_bjzyy_zxzxthjlb");
		super.setKey("id");
		super.setClass(ZxzxthjlForm.class);
	}
	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxthjlForm t)
			throws Exception {
		return null;
	}
	/**
	 * ��ѯ����
	 */
	public List<HashMap<String, String>> getPageList(ZxzxthjlForm t, User user)
		throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.id,a.xh,c.xm,c.xb,c.nj,c.xymc,c.zymc,c.bjmc,c.sjhm,a.ytr,d.xm zgxm,d.xb zgxb,(select bmmc from ZXBZ_XXBMDM where bmdm = d.bmdm) zgbmmc,a.thsj,a.jbqkms, ");
		sql.append(" decode(a.cbpgdm, '1', 'û�����⣬������ѯ', '2', 'һ����������', '3', '�����ϰ��;��񼲲�',a.cbpgdm)cbpgdm,a.ybwtlb,a.ybwtsfzx, ");
		sql.append(" a.zajb,a.cbpgjg, ");
		sql.append(" decode(a.zajbsmzx, '1', '��', '0', '��','2', '', a.zajbsmzx) zajbsmzx,decode(a.sfzj, '1', '��', '0', '��','2', '', a.sfzj) sfzj,a.bz,c.zydm,c.bjdm,c.xydm ");
		sql.append(" from XG_XLJKZX_BJZYY_ZXZXTHJLB a ");
		sql.append(" left join view_xsbfxx c on a.xh = c.xh ");
		sql.append(" left join view_fdybbqk d on a.ytr = d.zgh ");
		sql.append(" )a where 1 = 1 and ytr = '"+user.getUserName()+"'");
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * �����ж������Ƿ��ظ�
	 */
	public String addCheck(ZxzxthjlForm form){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String id =  form.getId();
		sql.append(" select count(1) num from xg_xljkzx_bjzyy_zxzxthjlb ");
		sql.append(" where xh = ? and ytr = ?  and thsj = ? ");
		params.add(form.getXh());
		params.add(form.getYtr());
		params.add(form.getThsj());
		if(!StringUtils.isEmpty(id)){
			sql.append(" and id <> ? ");
			params.add(id);
		}	
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
	/**
	 * @����: ɾ����������������ѯ̸����¼��Ϣ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-4 ����10:41:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delZxzxthjlId(String[] id) throws Exception {
		if (id == null || id.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xljkzx_bjzyy_zxzxthjlb");
		sql.append(" where  ");
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("id=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), id);
	}
	/**
	 * @����: ��ȡ��ʦ����Ϣ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-5 ����07:41:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getInfoByZgh(String zgh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*, ");
		sql.append("        (select bmmc from ZXBZ_XXBMDM where bmdm=t.bmdm) bmmc  ");
		sql.append(" from VIEW_FDYBBQK t ");
		sql.append(" where zgh = ? ");		
		return dao.getMapNotOut(sql.toString(), new String[]{ zgh });
	}
	
	/**
	 * @����: ������ѯ
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-6 ����02:14:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getThjlxx(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.id,a.xh,c.xm,c.xb,c.nj,c.xymc,c.zymc,c.bjmc,c.sjhm,a.ytr,d.xm zgxm,d.xb zgxb,(select bmmc from ZXBZ_XXBMDM where bmdm = d.bmdm) zgbmmc,a.thsj,a.jbqkms, ");
		sql.append(" decode(a.cbpgdm, '1', 'û�����⣬������ѯ', '2', 'һ����������', '3', '�����ϰ��;��񼲲�')cbpgdmmc,a.cbpgdm,a.ybwtlb,a.ybwtsfzx, ");
		sql.append(" a.zajb,a.cbpgjg, ");
		sql.append(" decode(a.zajbsmzx, '1', '��', '0', '��','2', '', a.zajbsmzx) zajbsmzx,decode(a.sfzj, '1', '��', '0', '��','2', '', a.sfzj) sfzj,a.bz ");
		sql.append(" from XG_XLJKZX_BJZYY_ZXZXTHJLB a ");
		sql.append(" left join view_xsbfxx c on a.xh = c.xh ");
		sql.append(" left join view_fdybbqk d on a.ytr = d.zgh ");
		sql.append(" where a.id = ? ");		
		return dao.getMapNotOut(sql.toString(), new String[]{ id });
	}
	/**
	 * @����: ��ȡһ���������������������
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-5 ����09:48:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYblxwtlbList() {
		String sql = " select dm,mc from xg_xljkzx_bjzyy_xlwtdmb order by dm ";
		return dao.getList(sql, new String[] {}, new String[] { "dm","mc" });
	}
	/**
	 * @����: ̸����¼�����ڱ���
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-13 ����05:51:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getThjl(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,b.xm,b.xymc,b.zymc,b.sjhm,a.thsj,a.jbqkms,a.ybwtlb,a.cbpgjg,a.bz,a.zajb, ");
		sql.append(" decode(a.cbpgdm, '1', 'û�����⣬������ѯ', '2', 'һ����������', '3', '�����ϰ��;��񼲲�',a.cbpgdm)cbpgdm, ");
		sql.append(" decode(a.zajbsmzx, '1', '��', '0', '��','2', '', a.zajbsmzx) zajbsmzx, ");
		sql.append(" decode(a.sfzj, '1', '��', '0', '��','2', '', a.sfzj) sfzj ");
		sql.append(" from xg_xljkzx_bjzyy_zxzxthjlb a  left join view_xsbfxx b on a.xh = b.xh ");
		sql.append(" where a.id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	/**
	 * @����: ȡһ���������
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-14 ����05:21:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getYbwtlb(String id) {
		String sql = null;
		sql = "select ybwtlb from xg_xljkzx_bjzyy_zxzxthjlb where id=? ";
		return dao.getOneRs(sql, new String[]{id}, "ybwtlb");
	}
	/**
	 * @����: ȡ�����ϰ��;��񼲲�
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-14 ����05:23:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZajb(String id) {
		String sql = null;
		sql = "select zajb from xg_xljkzx_bjzyy_zxzxthjlb where id=? ";
		return dao.getOneRs(sql, new String[]{id}, "zajb");
	}
}
