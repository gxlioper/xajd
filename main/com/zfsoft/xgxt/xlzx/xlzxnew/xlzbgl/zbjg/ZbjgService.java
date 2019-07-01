package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

public class ZbjgService extends SuperServiceImpl<ZbjgForm,ZbjgDao> {
	/**
	 * 
	 * @描述: 获取周报问题人员信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:26:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZbWtryInfo(String zbjgid){
		return dao.getZbWtryInfo(zbjgid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午05:13:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsb
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveZbsb(ZbjgForm zbsb) throws Exception{
		boolean rs = true;
		String[] xhArray = zbsb.getXhArray();
		String[] zbwtmsArray = zbsb.getZbwtmsArray();
		zbsb.setSbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		if(StringUtils.isNotNull(zbsb.getSbjgid())){
			rs = dao.delWtb(zbsb.getSbjgid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdateNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			if(!this.checkIsNotExist(zbsb.getBjdm(), zbsb.getSbzbid())){
				throw new SystemException(MessageKey.XG_XLZX_SB_REPEAT);
			}
			zbsb.setSbjgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			zbsb.setSjly("0");
			rs = dao.runInsertNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < zbwtmsArray.length; i++) {
				paraList.add(new String[]{"zb",zbsb.getSbjgid(),xhArray[i],zbwtmsArray[i]});
			}
			rs = dao.saveDataIntoWtb(paraList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	
	/**
	 * 
	 * @描述: 验证是否已填写周报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-1 上午11:33:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param sbzbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String bjdm,String sbzbid){
		return dao.checkIsNotExist(bjdm, sbzbid);
	}
	
	/**
	 * 
	 * @描述: 结果维护查看班级信息
	 * @日期：2017-9-2 上午10:36:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getTeaBjxx(String bjdm){
		return dao.getTeaBjxx(bjdm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除上报结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-2 下午03:07:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delSbjg(String[] sbjgids) throws Exception{
		return dao.delSbjg(sbjgids);
	}
	
	/**
	 *
	 * @描述: 查询出辅导员所有带班信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-3 下午02:47:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjList(String zgh){
		return dao.getBjList(zgh);
	}
	
	/**
	 * 
	 * @描述: 是否辅导员
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-3 下午04:41:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fdy
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsFdy(String zgh){
		return dao.checkIsFdy(zgh);
	}
	
	/**
	 * 
	 * @描述: 获取周次List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 上午08:47:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param sblx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcList(String xn, String xq) {
		return dao.getZcList(xn, xq);
	}
	
	/**
	 * 
	 * @描述: 获取周报日期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 上午08:50:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param zbid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQzrq(String zbid) {
		return dao.getQzrq(zbid);
	}
}
