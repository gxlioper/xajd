package xsgzgl.gygl.gyccgl.dmwh;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class GyccDmwhService extends SuperServiceImpl<GyccDmwhForm, GyccDmwhDao> {
	/**
	 * 
	 * @描述: 损坏程度获取model
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 上午11:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm 返回类型 
	 * @throws
	 */
	public GyccDmwhForm getBscdForm(String key) throws Exception{
		return dao.getBscdForm(key);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存物品
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午04:26:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveWpForm(GyccDmwhForm form) throws Exception{
		boolean flag = true;
		/**
		 *增加、修改操作类型判断
		 */
		if("add".equals(form.getType())){
			if(!dao.checkIsNotExistWpdm(form.getDm(), null, form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_DM_EXISTS);//代码重复
			}
			if(!dao.checkIsNotExistWpdm(null, form.getMc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//名称重复
			}
			flag = dao.runInsert(form);
		}else{
			if(!dao.checkIsNotExistWpdm(form.getDm(), form.getMc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//名称重复
			}
			flag = dao.runUpdate(form);
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述: 保存损害程度
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:13:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public boolean saveShcdForm(GyccDmwhForm form) throws Exception{
		boolean flag = true;
		/**
		 *增加、修改操作类型判断
		 */
		if("add".equals(form.getType())){
			if(!dao.checkIsNotExistShcd(form.getShcddm(), null, form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_DM_EXISTS);//代码重复
			}
			if(!dao.checkIsNotExistShcd(null, form.getShcdmc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//名称重复
			}
		
		}else{
			if(!dao.checkIsNotExistShcd(form.getShcddm(), form.getShcdmc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//名称重复
			}
			
		}
		flag = dao.saveShcd(form, form.getType());
		return flag;
	}
	
	/**
	 * 
	 * @描述:删除损害程度
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-6 下午05:50:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shcddm
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int DelShcd(String[] shcddm) throws Exception{
		return dao.DelShcd(shcddm);
	}
	
	/**
	 * 
	 * @描述: 物品代码是否已被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 上午09:59:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public boolean isWpdmNotUserd(String[] dms){
		return dao.isWpdmNotUserd(dms);
	}
	
	/**
	 * 
	 * @描述: 损害程度是否已被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 上午09:59:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String[] 返回类型 
	 * @throws
	 */
	public boolean isShcdNotUserd(String[] dms){
		return dao.isShcdNotUserd(dms);
	}
	
}
