/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-5-27 ����02:23:14 
 */  
package com.zfsoft.xgxt.xtwh.cxpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import net.sf.json.JSONObject;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �Զ����ѯ�� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-5-27 ����02:23:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpzService extends SuperServiceImpl<CxpzForm, CxpzDao> {

	private CxpzDao dao = new CxpzDao();
	
	public CxpzService(){
		super.setDao(dao);
	}
	
	
	
	/**
	 * 
	 * @����: ��ȡ�Զ����ѯ��ع����б�
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-27 ����03:48:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnmc
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCxgnList(String gnmc){
		return dao.getCxgnList(gnmc);
	}
	
	
	
	/**
	 * 
	 * @����: �����ܱ�־��ѯ�����������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-27 ����03:49:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnbz
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getColList(String gnbz){
		return dao.getColList(gnbz);
	}
	
	
	
	/**
	 * 
	 * @����: �޸���������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-29 ����05:12:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateColInfo(CxpzForm model) throws Exception{
		
		return dao.updateColInfo(model);
	}
	
	
	
	/**
	 * 
	 * @����: �Զ����ѯ����������Ϣ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-5-31 ����02:12:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnbz
	 * @return
	 * JSONObject �������� 
	 * @throws
	 */
	public JSONObject getCxbzForJson(String gnbz,String cxpz){
		
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,String> cxpzMap = dao.getCxpz(gnbz);
		map.putAll(cxpzMap);
		
		List<HashMap<String,String>> colList = dao.getAllColList(gnbz);
		List<Map<String,Object>> newColList = new ArrayList<Map<String,Object>>();
		
		for (Map<String,String> colMap : colList){
			Map<String,Object> newMap = new HashMap<String, Object>();
			newMap.put("label", colMap.get("label"));
			newMap.put("name", colMap.get("name"));
			newMap.put("index", colMap.get("pxzd"));
			newMap.put("width", colMap.get("width")+"%");
			newMap.put("key", "Y".equals(colMap.get("iskey")));
			newMap.put("hidden","Y".equals(colMap.get("ishidden")));
			
			if (!StringUtils.isNull(colMap.get("formatter")) && !"yes".equals(cxpz)){
				newMap.put("formatter", colMap.get("formatter"));
			}
			
			newColList.add(newMap);
		}
		
		map.put("colList",newColList);
		return JSONObject.fromMap(map);
	}
}
