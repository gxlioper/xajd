/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 下午5:58:11 
 */  
package xsgzgl.gyjc.jcsd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gyjc.jcrc.JcrcForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月22日 下午5:58:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CcrcService extends SuperServiceImpl<CcrcForm, CcrcDao> {

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月23日 下午2:28:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcrq
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkIfRqIntersection(String jcrq) {
		// TODO 自动生成方法存根
		return dao.checkIfRqIntersection(jcrq);
	}
	
	
	@TransactionControl
	public boolean  saveCcRc(CcrcForm t) throws Exception {
		return dao.runInsert(t);
	}
	
	public List<HashMap<String, String>> getList(CcrcForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return dao.getList(t,user);
	}
	
	
	public List<HashMap<String, String>> getRyfpList(CcrcForm t, User user) throws  Exception{
		return dao.getRyfpList(t, user);
	}
	
	@TransactionControl
	public boolean saveRyFp(CcrcForm t, String[] lddms, String[] qshs) throws Exception{
		/**
		 * 参数组合
		 */
		List<String[]> paraList = new ArrayList<String[]>();
		String zgh = t.getZgh();
		String ccid = t.getCcid();
		/**
		 * 参数组合
		 */
		for (int i = 0; i < lddms.length; i++) {
			paraList.add(new String[]{ccid,lddms[i],qshs[i],zgh});
		}
		boolean rs = dao.saveFpry(paraList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}


	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月24日 下午5:22:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> gettodayCcList(CcrcForm model, User user) throws Exception {
		// TODO 自动生成方法存根
		return dao.gettodayCcList(model,user);
	}


	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月25日 下午2:21:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param lddms
	 * @param qshs
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateRyFp(CcrcForm model) throws Exception {
		// TODO 自动生成方法存根
		return dao.updateRyfp(model);
	}


	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月25日 下午4:34:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getqsfpList(CcrcForm model, User user) throws Exception {
		// TODO 自动生成方法存根
		return dao.getqsfpList(model,user);
	}


	

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月25日 下午6:41:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param lddm
	 * @param qsh
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertQs(CcrcForm model, String[] lddm, String[] qsh) throws Exception {
		/**
		 * 参数组合
		 */
		List<String[]> paraList = new ArrayList<String[]>();
		String zgh = model.getZgh();
		String ccid = model.getCcid();
		/**
		 * 参数组合
		 */
		for (int i = 0; i < lddm.length; i++) {
			paraList.add(new String[]{ccid,lddm[i],qsh[i],zgh});
		}
		boolean rs = dao.saveFpry(paraList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}


	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月25日 下午7:14:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param userStatus
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public boolean cancelRyfp(CcrcForm model, String userStatus) throws Exception {
		// TODO 自动生成方法存根
		return dao.cancelFp(model,userStatus);
	}


	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月28日 上午9:49:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkIfExistTj(String rcid) {
		// TODO 自动生成方法存根
		return dao.checkIfExistTj(rcid);
	}


	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月28日 上午9:55:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @param b
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delCcrcTran(String rcid, boolean b) throws Exception {
		// TODO 自动生成方法存根
		return dao.delCcrc(new String[]{rcid});
	}


	

}
