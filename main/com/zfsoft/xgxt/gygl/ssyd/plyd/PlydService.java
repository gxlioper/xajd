/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��12�� ����11:00:27 
 */
package com.zfsoft.xgxt.gygl.ssyd.plyd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ģ��
 * @�๦������: ���������춯 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��12�� ����11:00:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PlydService extends SuperServiceImpl<PlydModel, PlydDao> {

	private static final String Split_CHAR = ",";
	
	/**
	 * 
	 * @����: ����סѧ���б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��13�� ����10:25:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYrzPageList(PlydModel t, User user) throws Exception{
		
		return dao.getYrzPageList(t, user);
	}
	
	
	/**
	 * 
	 * @����: �������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��13�� ����10:26:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtzPageList(PlydModel t, User user) throws Exception{
		
		return dao.getDtzPageList(t, user);
	}
	
	
	/**
	 * 
	 * @����: ȷ�ϵ����б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��13�� ����10:26:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQrtzList(PlydModel t, User user) throws Exception{
		return dao.getQrtzList(t, user);
	}
	
	
	/**
	 * 
	 * @����: ��������Ϊ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��16�� ����10:46:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean szDtz(String ids) throws Exception{
		
		if (StringUtil.isNull(ids)){
			return false;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		
		return dao.szDtz(xhs);
	}
	
	
	/**
	 * 
	 * @����: ȡ��������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����01:44:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxDtz(String ids) throws Exception{
		
		if (StringUtil.isNull(ids)){
			return false;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		
		return dao.qxDtz(xhs);
	}
	
	
	/**
	 * 
	 * @����: ȡ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����01:44:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxtz(String ids) throws Exception{
		
		if (StringUtil.isNull(ids)){
			return false;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		
		return dao.qxtz(xhs);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ����ѧ����Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����02:27:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getTzxsList(String ids){
		
		if (StringUtil.isNull(ids)){
			return null;
		}
		
		String[] xhs = ids.split(Split_CHAR);
		return dao.getTzxsList(xhs);
	}
	
	
	/**
	 * 
	 * @����: �ɵ�����ס�Ĵ�λ��Ϣ�б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-17 ����02:50:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xb
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String,Object> getCwxxList(String xb){
		
		
		List<HashMap<String,String>> ldxxList = dao.getLdxxList(xb);
		List<HashMap<String,String>> lcxxList = dao.getLcxxList(xb);
		List<HashMap<String,String>> cwxxList = dao.getCwxxList(xb);
		 
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("ldxxList", ldxxList);
		map.put("lcxxList", lcxxList);
		
		List<HashMap<String,Object>> qscwList = new ArrayList<HashMap<String,Object>>();
		
		String tempLddm = null;//¥������
		String tempCh = null;//���
		String tempQsh = null;//���Һ�
		
		HashMap<String,Object> qscw = null;
		List<String> cwList = null;
		
		for (HashMap<String,String> cwxx : cwxxList){
			
			String lddm = cwxx.get("lddm");
			String ch = cwxx.get("ch");
			String qsh = cwxx.get("qsh");
			String cwh = cwxx.get("cwh");
			
			if (tempQsh == null || !((lddm.equals(tempLddm)) && ch.equals(tempCh) && qsh.equals(tempQsh))){
				
				if (cwList != null && !cwList.isEmpty()){
					qscw.put("cwList", cwList);
					qscwList.add(qscw);
				}
				
				tempLddm = lddm;
				tempCh = ch;
				tempQsh = qsh;
				qscw = new HashMap<String,Object>();
				cwList = new ArrayList<String>();
				
				qscw.put("lddm", lddm);
				qscw.put("ch", ch);
				qscw.put("qsh", qsh);
			} 
			
			cwList.add(cwh);
		}
		
		if (cwList != null && !cwList.isEmpty()){
			qscw.put("cwList", cwList);
			qscwList.add(qscw);
		}
		
		map.put("qscwList", qscwList);
		
		return map;
	}
	
	
	
	/**
	 * 
	 * @����: ����������ס��Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-19 ����03:25:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rzxx
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean saveRzxx(String rzxx) throws Exception{
		
		JSONArray jsonArr = JSONArray.fromString(rzxx);
		List<String[]> params = new ArrayList<String[]>();
		List<String> xhList = new ArrayList<String>();
		
		for (int i = 0 ; i < jsonArr.length() ; i++){
			JSONObject json = jsonArr.getJSONObject(i);
			
			String cwxx = json.getString("cwxx");
			String xh = json.getString("xh");
			
			xhList.add(xh);
			
			String[] cwInfo = cwxx.split("\\-");
			params.add(StringUtils.joinStrArr(cwInfo,new String[]{xh}));
		}
		
		dao.szDtz(xhList.toArray(new String[]{}));
		
		return dao.saveRzxx(params);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�����ύ������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-24 ����02:18:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int getCountByBktj(){
		
		int bktj = dao.getCountByBktj();
		int wrz = dao.getCountWrz();
		
		return bktj+wrz;
	}
	
	
	/**
	 *  
	 * 
	 * @����: ȷ�ϵ���
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-6-26 ����05:07:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean qrtz() throws Exception{
		
		//�����춯��¼
		boolean result = dao.saveYdjg();
		//��Ŀ�괲λԭ��ס��ѧ�����
		if (result){
			result = dao.updateYcwToBlank();
		}
		//���������µ���ʽס����Ϣ��
		if (result){
			result = dao.updateToXcw();
		}
		//�����ʱ������¼
		if (result){
			result = dao.clearTempData();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}
