/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-18 ����10:35:48 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-8-18 ����10:35:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExportDao extends SuperDAOImpl<ExportModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExportModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExportModel t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		
	}
	
	/**
	 * 
	 * @����: ȡ�Զ����xg_zdybd_flszb�е�С�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-18 ����11:33:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkMrList( ){
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.*,");
		sql.append(" a.flmc || '-' || c.flmc zhmc,");
		sql.append(" c.xsxx zhsx,");
		sql.append(" b.bhmk,");
		sql.append(" b.gnlx,");
		sql.append(" '1' zt,");
		sql.append(" b.yzsz");
		sql.append(" from (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = ?");
		sql.append(" and flflszid is null");
		sql.append(" and sfqy = '1' ");
		sql.append(" order by to_number(xsxx)) a");
		sql.append(" join xg_zdybd_gnszb b");
		sql.append(" on a.gndm = b.gndm");
		sql.append(" join (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = ?");
		sql.append(" and flflszid is not null");
		sql.append(" and sfqy = '1' and (flszid != 'xsxx_query_xsxx_zwjd' and flszid != 'xsxx_query_xsxx_byxx')");
		sql.append(" order by flflszid, to_number(xsxx)) c");
		sql.append(" on a.flszid = c.flflszid");
		//���λ���ʦ����ҵ����Ϣ���Ի���ʾ��ѧ����Ϣ����ʾ������
		sql.append(" ");
		sql.append(" order by to_number(a.xsxx),a.flszid , to_number(c.xsxx)");
		return dao.getListNotOut(sql.toString(), new String[]{"xsxx_query","xsxx_query"});
	}
	
	/**
	 * 
	 * @����: ȡ���������ñ��еĹ���xg_zdybd_flszb�е�С�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-18 ����02:25:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkInSzb(String zgh,String type){
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.*,");
		sql.append(" a.xssx zhsx,a.zt,a.dcjs,");
		sql.append(" (select c.flmc from xg_zdybd_flszb c where b.flflszid = c.flszid) || '-' ||");
		sql.append(" b.flmc zhmc");
		sql.append(" from xg_xsxx_xxhzdc a");
		sql.append(" join xg_zdybd_flszb b");
		sql.append(" on a.flszid = b.flszid");
		sql.append(" where zgh = ?");
		sql.append(" and dcjs = '"+type+"'");
		//���λ���ʦ����ҵ����Ϣ���Ի���ʾ��ѧ����Ϣ����ʾ������
		sql.append(" and (a.flszid != 'xsxx_query_xsxx_zwjd' and a.flszid != 'xsxx_query_xsxx_byxx')");
		sql.append(" order by to_number(a.xssx)");
		return dao.getListNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ�����ñ�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����11:15:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public void delGndm(String zgh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xsxx_xxhzdc where zgh = ?");
		 dao.runUpdate(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����:��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-23 ����11:33:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param selectCol
	 * @param unselectCol
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveBcszDc(String[] selectCol,String[] unselectCol,int sellen,int unsellen,String zgh) {
		ArrayList<String> sqllist = new ArrayList<String>();
		/*�ж�ѡ���и����Ƿ����0������0�����в���sqlƴ��*/
		if(sellen > 0){
			for (int i = 0; i < sellen; i++) {
				StringBuilder sql = new StringBuilder();
				String[] flszjids = selectCol[i].split("@");
				String  flszid = null;
				if(null != flszjids && flszjids.length ==2){
					flszid = flszjids[0];
				}
				if(StringUtils.isNull(flszid)){
					return false;
				}
				sql.append(" insert into xg_xsxx_xxhzdc(flszid,zgh,zt,xssx)values('"+flszid+"','"+zgh+"','1','"+i+"')");
				sqllist.add(sql.toString());
			}
		
		}
		/*�ж�δѡ���и����Ƿ����0������0�����в���sqlƴ��*/
		if(unsellen > 0){
			
			int alllen = unsellen + sellen;
			for (int i = sellen; i < alllen; i++) {
				StringBuilder sql = new StringBuilder();
				String[] flszjids = unselectCol[i-sellen].split("@");
				String  flszid = null;
				if(null != flszjids && flszjids.length ==2){
					flszid = flszjids[0];
				}
				if(StringUtils.isNull(flszid)){
					return false;
				}
				sql.append(" insert into xg_xsxx_xxhzdc(flszid,zgh,zt,xssx)values('"+flszid+"','"+zgh+"','0','"+i+"')");
				sqllist.add(sql.toString());
			}
			
		}
		try {
			int[] len = dao.runBatch(sqllist.toArray(new String[] {}));
			return len.length > 0 ? true :false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * 
	 * @����: ��ȡѡ�е����Զ�����������ñ��е�ģ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����09:00:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param flszidArr
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectMkinFlszb(String[] flszidArr){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zdybd_flszb where flszid in( ");
		for (int i = 0; i < flszidArr.length; i++) {
			sql.append(" ? ");
			if(i != flszidArr.length-1){
				sql.append(" ,");
			}
		}
		sql.append(" ) ");
		return dao.getListNotOut(sql.toString(), flszidArr);
	}
	
	/**
	 * 
	 * @����: ��ȡ�༶����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����02:43:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public  String getBjdm(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjdm from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "bjdm");
	}
	
	/**
	 * 
	 * @����: ������Ϣ����ϵ��ʽ��������Ϣȡֵ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-29 ����04:25:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param flszid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZdyxxList(String flszid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum rn");
		sql.append(" from (select t.*, to_number(replace(zbwz, ',', '')) xssx");
		sql.append(" from xg_zdybd_zddyb t");
		sql.append(" where t.flszid = ?");
		sql.append(" and (xxdm = ? or xxdm = ?) and (szz != 'zpHidDiv' or szz is null)");
		sql.append(" order by xssx asc)t");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), new String[]{flszid,"public",Base.xxdm});
	}
	

}
