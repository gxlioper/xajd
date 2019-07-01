package com.zfsoft.xgxt.dtjs.dxbmgl.pxgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @功能描述：党校培训管理service
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午02:55:21 
 */
public class DxpxglService extends SuperServiceImpl<DxpxglForm, DxpxglDao> {
	/** 
	 * @功能描述：保存培训信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 上午09:10:29 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean save(DxpxglForm model) throws Exception {
		return dao.save(model);
	}
	/** 
	 * @功能描述：更新培训信息
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 上午09:13:00 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean update(DxpxglForm model) throws Exception {
		return dao.update(model);
	}
	/** 
	 * @功能描述：更新百分比
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 上午09:13:35 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updatebfb(DxpxglForm model) throws Exception {
		return dao.updatebfb(model);
	}
	/** 
	 * @功能描述：删除培训信息 排除已有学生选择的项
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 上午09:14:50 
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws 
	 */
	public int del(String[] ids) throws Exception{
		StringBuffer sql=new StringBuffer();
		if(ids.length>0){//防止为空报错
			sql.append("delete from xg_dtjs_dxpxb where id in (");
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
