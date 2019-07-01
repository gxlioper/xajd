package com.zfsoft.xgxt.dtjs.dxbmgl.pxjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @功能描述：党校培训结果service
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:49:44 
 */
public class DxpxjgService extends SuperServiceImpl<DxpxjgForm, DxpxjgDao> {
	/** 
	 * @功能描述：保存培训结果
	 * @author：杨珩 【1346】
	 * @date：2017-11-10 下午05:44:48 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean save(DxpxjgForm model) throws Exception {
		return dao.save(model);
	}
	/** 
	 * @功能描述：获取学生培训信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-11 上午09:57:55 
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXspx(String jgid) throws Exception {
		return dao.getXspx(jgid);
	}
	/** 
	 * @功能描述：根据学号获取培训列表
	 * @author：杨珩 【1346】
	 * @date：2017-11-11 上午11:52:11 
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListOfPx(DxpxjgForm t) throws Exception {
		return dao.getPageListOfPx(t);
	}
	/** 
	 * @功能描述：根据学号和培训id获取培训信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-11 下午03:20:29 
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXspxByxhpxid(String xh,String pxid) throws Exception {
		return dao.getXspxByxhpxid(xh,pxid);
	}
	/** 
	 * @功能描述：保存评分
	 * @author：杨珩 【1346】
	 * @date：2017-11-11 下午04:47:49 
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updatePf(DxpxjgForm t) throws Exception {
		return dao.updatePf(t);
	}
	/** 
	 * @功能描述：批量删除
	 * @author：杨珩 【1346】
	 * @date：2017-11-11 下午04:59:01 
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws 
	 */
	public int del(String[] ids) throws Exception{
		StringBuffer sql=new StringBuffer();
		if(ids.length>0){//防止为空报错
			sql.append("delete from XG_DTJS_DXBMJGB where jgid in (");
			for (int i = 0; i < ids.length; i++) {
				if (i==ids.length-1) {
					sql.append("?)");
				}else{
					sql.append("?,");
				}
			}
		}
		return dao.del(sql.toString(), ids);
	}
}
