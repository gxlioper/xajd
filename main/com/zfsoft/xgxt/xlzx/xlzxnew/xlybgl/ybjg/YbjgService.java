package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class YbjgService extends SuperServiceImpl<YbjgForm, YbjgDao> {
	/**
	 * 
	 * @描述: 获取学院名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-5 上午11:59:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取学生查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 下午01:45:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuCx(YbjgForm t, User user,String xhs) throws Exception{
		return dao.getStuCx(t, user, xhs);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-5 下午02:55:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList(String xymc) throws Exception{
		return dao.getXyList(xymc);
	}
	
	public boolean saveYbjg(YbjgForm ybjg) throws Exception{
		boolean rs = true;
		String[] xhArray = ybjg.getXhArray();
		String[] ybwtmsArray = ybjg.getYbwtmsArray();
		String[] ybgycsArray = ybjg.getYbgycsArray();
		String[] ybgyhjgArray = ybjg.getYbgyhjgArray();
		String[] wtfsrqArray = ybjg.getWtfsrqArray();
		ybjg.setTxrq(GetTime.getTimeByFormat("yyyy-mm-dd"));
		if(StringUtils.isNotNull(ybjg.getJgid())){
			rs = dao.delWtb(ybjg.getJgid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdate(ybjg);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			if(!dao.checkIsNotExist(ybjg.getXydm(),ybjg.getXn(),ybjg.getYf())){
				throw new SystemException(MessageKey.XG_XLZX_SB_REPEAT);
			}
			ybjg.setJgid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			ybjg.setSjly("0");
			rs = dao.runInsert(ybjg);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < ybwtmsArray.length; i++) {
				//西安科技大学个性化
				if("10704".equals(Base.xxdm)){
					paraList.add(new String[]{"yb",ybjg.getJgid(),xhArray[i],ybwtmsArray[i],null,ybgyhjgArray[i],wtfsrqArray[i]});
				}else{
					paraList.add(new String[]{"yb",ybjg.getJgid(),xhArray[i],ybwtmsArray[i],ybgycsArray[i],ybgyhjgArray[i],null});
				}
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
	 * @描述: 获取周报问题人员信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:26:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYbWtryInfo(String ybjgid){
		return dao.getYbWtryInfo(ybjgid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除月报结果
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

	public boolean update(YbjgForm myForm) throws Exception {
		boolean rs = true;
		rs = dao.update(myForm);
		if(rs){
			rs =  dao.delWtb(myForm.getJgid());
		}
		return rs;
	}

	public List<HashMap<String, String>> getYbhzList(YbjgForm model, User user) throws Exception{
		return dao.getYbhzList(model,user);
	}

	public List<HashMap<String, String>> getAllHzList(YbjgForm t, User user)  throws Exception{
		return dao.getAllHzList(t, user);
	}

}
