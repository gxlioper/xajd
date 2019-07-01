/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:48:42 
 */  
package com.zfsoft.xgxt.xsxx.djjd.dmwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @类功能描述: 代码维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:48:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
		 * @param key要设置的 key
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
