/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-4 ����10:35:25 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.JsonUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-4 ����10:35:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XgzdService extends SuperServiceImpl<XgzdForm, XgzdDao> {
	/**
	 * 
	 * @����: ��ͥ��������ֶ�������Ϣ��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-4 ����01:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnmk
	 * @param xxdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtqkdcZdxx(String gnmk,String xxdm){
		return dao.getJtqkdcZdxx(gnmk, xxdm);
	}
	
	/**
	 * 
	 * @����:��ȡjson��ʽ ���ֶ���Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-4 ����02:30:08
	 * @�޸ļ�¼: xuwen-2017/3/1-���Ӹ�����Ϣ���
	 * @return
	 * JSONObject �������� 
	 * @throws
	 */
	public JSONObject getJsonData(){
		String xxdm = Base.xxdm;
		//��ͥ����޸��ֶ�
		List<HashMap<String, String>> jtqkdclist = this.getJtqkdcZdxx("jtqkdc", xxdm);
		//���������ѧУ���Ի����ã���ȡpublic
		if(jtqkdclist == null || jtqkdclist.size() == 0){
			jtqkdclist = this.getJtqkdcZdxx("jtqkdc", "public");
		}
		//��ͥ��Ա�޸��ֶ�
		List<HashMap<String, String>> jtcylist = this.getJtqkdcZdxx("jtcy", xxdm);
		//���������ѧУ���Ի����ã���ȡpublic
		if(jtcylist == null || jtcylist.size() == 0){
			jtcylist = this.getJtqkdcZdxx("jtcy", "public");
		}
		
		//������Ϣ�޸��ֶ�
		List<HashMap<String, String>> fjxxList = this.getJtqkdcZdxx("fjxx", xxdm);
		//���������ѧУ���Ի����ã���ȡpublic
		if(fjxxList == null || fjxxList.size() == 0){
			fjxxList = this.getJtqkdcZdxx("fjxx", "public");
		}
				
		//������ת����json��ʽ
		JSONArray json_jtqkdclist = JsonUtil.ListToJSON(jtqkdclist);
		JSONArray json_jtcylist = JsonUtil.ListToJSON(jtcylist);
		JSONArray json_fjxxList = JsonUtil.ListToJSON(fjxxList);
		HashMap<String, JSONArray> datamap = new HashMap<String, JSONArray>();
		datamap.put("jtqkdclist", json_jtqkdclist);
		datamap.put("jtcylist", json_jtcylist);
		datamap.put("fjxxList", json_fjxxList);
		JSONObject JsonObj = JSONObject.fromObject(datamap);
		return JsonObj;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����ֶα���Ǳ�����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-5 ����01:46:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveData(XgzdForm model) throws Exception{
		
		return dao.saveData(model);
	}
}
