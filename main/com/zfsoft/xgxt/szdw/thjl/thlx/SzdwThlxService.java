/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ� 2014-10-8 ����11:40:22
 */  
package com.zfsoft.xgxt.szdw.thjl.thlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼������-̸����¼ά��-̸������
 * @�๦������: 
 * @���ߣ� ��ˮ��[����:1150]
 * @ʱ�䣺 2014-10-8 ����11:40:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzdwThlxService extends SuperServiceImpl<SzdwThlxForm, SzdwThlxDao>{

	public SzdwThlxService() {
		super.setDao(new SzdwThlxDao());
	}
	
	/** 
	 * @����: ̸�����ʹ����Ƿ����
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 */
	public boolean thlxIsExist(SzdwThlxForm model) {
		return dao.thlxIsExist(model);
	}

	/**
	 * @����:��ȡȫ��̸������
	 * @���ߣ���ˮ��[����:1150]
	 * @���ڣ�2014-10-8 ����11:40:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllThlxList() throws Exception{
		return dao.getAllList(new SzdwThlxForm());
	}

	public List<HashMap<String,String>> getKhwtPageList(SzdwThlxForm model) throws Exception {
		return dao.getKhwtPageList(model);

	}

    public List<HashMap<String,String>> getWtmsPageList(SzdwThlxForm model) throws Exception {
		return dao.getWtmsPageList(model);
    }

	public List<HashMap<String,String>> getTgbzPageList(SzdwThlxForm model) throws Exception {
		return dao.getTgbzPageList(model);
	}

	public boolean addThlx(SzdwThlxForm model)   {
		boolean rs = false;
		try {
			rs = dao.runInsert(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean addKhwt(SzdwThlxForm model)   {
		boolean rs = false;
		try {
			rs = dao.addKhwt(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean addWtms(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs = dao.addWtms(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean addTgbz(SzdwThlxForm model)  {
		boolean rs = false;
		try {
			rs =  dao.addTgbz(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public HashMap<String,String> getKhwtListByThlx(String ssthlx) {
		return dao.getKhwtListByThlx(ssthlx);
	}

	/**
	 * @����:��ȡ�����������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 17:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getKhwtInfo(SzdwThlxForm model) {
		return dao.getKhwtInfo(model);
	}
	/**
	 * @����:��ȡ����������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 17:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getWtmsInfo(SzdwThlxForm model) {
		return dao.getWtmsInfo(model);
	}
	/**
	 * @����:��ȡ�ṩ������Ϣ
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/11 17:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: java.util.HashMap<java.lang.String,java.lang.String>
	 */
	public HashMap<String,String> getTgbzInfo(SzdwThlxForm model) {
		return dao.getTgbzInfo(model);
	}

	/**
	 * @����:�޸����������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/12 10:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean updateKhwt(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs =  dao.updateKhwt(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * @����:�޸���������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/12 10:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean updateWtms(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs =  dao.updateWtms(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * @����:�޸��ṩ����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/12 10:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model]
	 * @return: boolean
	 */
	public boolean updateTgbz(SzdwThlxForm model) {
		boolean rs = false;
		try {
			rs =  dao.updateTgbz(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * @�������鿴�����Ƿ����
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/12 10:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: [model, tableName]
	 * @return: boolean
	 */
	public boolean isExit(SzdwThlxForm model, String tableName) throws SQLException {
		int count = dao.getCount(model,tableName);
		return count == 0;
	}

	public int delete(String[] lxdms, String tableName) throws Exception {
		return dao.delete(lxdms,tableName);
	}

	public List<HashMap<String,String>> getWtmsListByKhwt(String sskhwt) {
		return dao.getWtmsListByKhwt(sskhwt);
	}

	public List<HashMap<String,String>> getAllTgbz() {
		return dao.getAllTgbz();
	}

	/**
	 * @����:��ȡ���а������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/13 11:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String,String>> getAllBzjg() {
		return dao.getAllBzjg();
	}

	/**
	 * @����:���ݴ��������ȡ�����������
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2018/7/13 11:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public String[] getBzjgmcByDms(String[] dms) throws SQLException {
		return dao.getBzjgmcByDms(dms);
	}
}
