/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:48:42 
 */  
package com.zfsoft.xgxt.xsxx.djjd.dmwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: ����ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:48:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JddjDmwhService extends SuperServiceImpl<JddjDmwhModel, JddjDmwhDao> {

	public enum Type{
		XM("1"),
		JB("2");
		
		private String key;
		
		Type(String key){
			this.key = key;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param keyҪ���õ� key
		 */
		public void setKey(String key) {
			this.key = key;
		}
	}
	
	public List<HashMap<String,String>> getListByType(Type type) throws Exception{
		
		JddjDmwhModel model = new JddjDmwhModel();
		model.setLx(type.getKey());
		
		return super.getAllList(model);
	}
}
