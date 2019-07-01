/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-12 ����11:10:54 
 */  
package com.zfsoft.extend.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendStoreData;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-12 ����11:10:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendStoreDataDAO extends SuperDAOImpl<ExtendStoreData> {

	/**
	 * 
	 * @����: ɾ������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-19 ����03:51:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dataid
	 * @param mid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteData(String dataid , String mid) throws Exception{
		String del = "delete from ZFXG_EXTEND_DATA where id = ? and mid = ?";
		dao.runDelete(del, new String[]{dataid,mid});
		return Boolean.TRUE;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������,��ɾ��֮ǰ������(�����),Ȼ�����������
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-16 ����03:45:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @param id
	 * @param mid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveData(List<ExtendStoreData> data , String id , String mid) throws Exception{
		String del = "delete from ZFXG_EXTEND_DATA where id = ? and mid = ?";
		String sql = "insert into ZFXG_EXTEND_DATA (id,mid,meid,gid,zjz,zd,zdz,rn) values (?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		//ɾ��������
		dao.runDelete(del, new String[]{id,mid});
		for (ExtendStoreData d : data) {
			String _id = d.getId();
			String _mid = d.getMid();
			String _meid = d.getMeid();
			String _gid = d.getGid();
			String _zjz = d.getZjz();
			String _zd = d.getZd();
			String _zdz = d.getZdz();
			String _rn = d.getRn();
			params.add(new String[]{_id,_mid,_meid,_gid,_zjz,_zd,_zdz,_rn});
		}
		dao.runBatch(sql, params);
		return Boolean.TRUE;
	}
	
	/**
	 * 
	 * @����: ����id��ȡ��ر�����
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2015-6-12 ����11:12:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<ExtendStoreData> �������� 
	 * @throws
	 */
	public List<ExtendStoreData> getExtendStoreDataList(String id, String mid) throws Exception{
		String sql = "select * from ZFXG_EXTEND_DATA where id = ? and mid = ? ";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{id, mid});
		List<ExtendStoreData> dataList = new ArrayList<ExtendStoreData>();
		for (HashMap<String, String> i : listNotOut) {
			String _id = i.get("id");
			String zjz = i.get("zjz");
			String _mid = i.get("mid");
			String meid = i.get("meid");
			String gid = i.get("gid");
			String zd = i.get("zd");
			String zdz = i.get("zdz");
			String rn = i.get("rn");
			ExtendStoreData data = new ExtendStoreData(_id, zjz, _mid, meid, gid, zd, zdz, rn);
			dataList.add(data);
		}
		return dataList;
	}
	 
	@Override
	public List<HashMap<String, String>> getPageList(ExtendStoreData t)
			throws Exception {
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(ExtendStoreData t,
			User user) throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setClass(ExtendStoreData.class);
		super.setKey("ID");
		super.setTableName("ZFXG_EXTEND_DATA");
	}

}
