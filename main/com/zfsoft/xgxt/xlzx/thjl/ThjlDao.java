/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-10 ����11:12:28 
 */  
package com.zfsoft.xgxt.xlzx.thjl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ѧ��ά��ģ��(������һ�仰��������������) 
 * @���ߣ� wanghj [���ţ�1004]
 * @ʱ�䣺 2013-9-10 ����11:10:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ThjlDao extends SuperDAOImpl<ThjlForm> {

	
	public List<HashMap<String, String>> getPageList(ThjlForm model, User user)
			throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		/*sql.append(" select t.*,rownum r from ( select a.id,a.xh,a.zgh,a.thsj,a.thnr,a.bz,a.cjsj,");
		sql.append(" b.knlxdm,b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,");
		sql.append(" c.bmdm jsbmdm, ");
		sql.append(" (select bmmc from ZXBZ_XXBMDM where bmdm = c.bmdm) jsbmmc  ");
		sql.append(" from xg_xlzx_thjlb a left join VIEW_XLZX_TSXSXX b on a.xh = b.xh left join VIEW_FDYBBQK c on a.zgh = c.zgh");
		sql.append(" where a.sjzt='1' ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by thsj desc ");*/
				
		sql.append(" select t.*  ");

		if("10704".equals(Base.xxdm)){
			sql.append(" ,c.dbfdy ");
		}

		sql.append("  from VIEW_NEW_DC_XLZX_THJL t ");

		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t.BJDM = c.BJDM ");
		}

		sql.append(" where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @����:��������ID��ѯ̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public HashMap<String,String> getThjlListById(String id) throws Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r from ( select a.id,a.xh,a.zgh,a.thsj,a.thnr,a.bz,a.cjsj,");
		if("11527".equals(Base.xxdm)){
			sql.append("a.fjid,");
		}
		sql.append(" b.knlxdm,b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,(select bmmc from ZXBZ_XXBMDM where bmdm=c.bmdm) jsbmmc");
		sql.append(" from xg_xlzx_thjlb a left join VIEW_XLZX_TSXSXX b on a.xh = b.xh left join VIEW_FDYBBQK c on a.zgh = c.zgh");
		sql.append(" where a.sjzt='1') t where 1=1 and t.id=?");
		return	dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯ̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getThjlListByXh(String xh) throws Exception {

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r from ( select a.id,a.xh,a.zgh,a.thsj,a.thnr,a.bz,a.cjsj,");
		if("11527".equals(Base.xxdm)){
			sql.append("a.fjid,");
		}
		sql.append(" b.knlxdm,b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,(select bmmc from ZXBZ_XXBMDM where bmdm=c.bmdm) jsbmmc");
		sql.append(" from xg_xlzx_thjlb a left join VIEW_XLZX_TSXSXX b on a.xh = b.xh left join VIEW_FDYBBQK c on a.zgh = c.zgh");
		sql.append(" ) t where 1=1 and t.xh=? order by thsj desc ");
		return	dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	/**
	 * ������̸����¼��Ϣ��
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveThjlInfo(ThjlForm model)
			throws Exception {
	
		
		String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getZgh(),model.getThsj(),model.getThnr(),model.getBz(),model.getFjid()};
		
		boolean flag = false;
		String sql = " insert into xg_xlzx_thjlb (id,xh,zgh,thsj,thnr,bz,cjsj,sjzt,fjid) values(?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),1,?)";
		flag = dao.insert(sql, input);
		return flag;
	}
	
	
	/**
	 * 
	 * @����:ɾ��̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delThjlById(String[] id) throws Exception {
		if (id == null || id.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xlzx_thjlb");
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
	 * 
	 * @����:ɾ��̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delThjlByZgh(String[] zgh) throws Exception {
		if (zgh == null || zgh.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xlzx_thjlb");
		sql.append(" where  ");
		
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		return dao.runDelete(sql.toString(), zgh);
	}
	
	
	/**
	 * 
	 * @����:����ID��ѯ̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public HashMap<String,String> getThjlInfoById(String id) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_xlzx_thjlb");
		sql.append(" where  sjzt='1' and id = ?");

		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}

	
	/**
	 * 
	 * @����:����ID�޸�̸����¼��Ϣ
	 * @���ߣ�1004
	 * @���ڣ�2013-9-11 ����10:39:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	
	public boolean updateThjlInfo(ThjlForm model) throws Exception{
		
		HashMap<String,String>  thjlList = this.getThjlInfoById(model.getId());
		if(model.getId()==null || model.getId().equals("") || thjlList==null || thjlList.size()==0){
			return false;	
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xlzx_thjlb set ");
		
		if(!StringUtil.isNull(model.getThsj()) && !model.getThsj().equals(thjlList.get("thsj"))){
			sql.append(" thsj = '" + model.getThsj()+"',");
		}
		if(!StringUtil.isNull( model.getThnr()) && !model.getThnr().equals(thjlList.get("thnr"))){
			sql.append(" thnr = '" + model.getThnr()+"',");
		}
		if(!StringUtil.isNull(model.getSjzt()) && !model.getSjzt().equals(thjlList.get("sjzt"))){
			sql.append(" sjzt = '" + model.getSjzt()+"',");
		}
		if(!StringUtil.isNull(model.getFjid()) && !model.getFjid().equals(thjlList.get("fjid"))){
			sql.append(" fjid = '" + model.getFjid()+"',");
		}
		//ʲôҲû�޸ģ���return
		if(sql.lastIndexOf(",")==-1){
			return false;
		}
		//ȥ�����һ��","
		String sqls = sql.substring(0, sql.lastIndexOf(","))+" where id = ?";
		
		boolean flag = dao.runUpdate(sqls, new String []{model.getId()});
		return flag;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ThjlForm t)
			throws Exception {
		return null;
	}


	/** 
	 * @����:ͨ��ְ����ȡ�ý�ʦ��Ϣ
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-24 ����10:50:41
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
	 * @����:ȡ�ý�ʦ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-10-24 ����11:50:32
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJsInfoList(ThjlForm myForm) {

		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*, ");
		sql.append("        (select bmmc from ZXBZ_XXBMDM where bmdm=t.bmdm) bmmc  ");
		sql.append(" from VIEW_FDYBBQK t ");
		
		try {
			return getPageList(myForm, sql.toString(), new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
