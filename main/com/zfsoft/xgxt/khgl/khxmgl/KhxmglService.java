/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午11:40:07 
 */
package com.zfsoft.xgxt.khgl.khxmgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;
import common.Globals;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-15 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhxmglService extends SuperServiceImpl<KhxmglForm, KhxmglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String SCZT = "1";//1:删除
	private KhpfService khpfService = new KhpfService();
	private KhxmglDao dao = new KhxmglDao();
	
	/**
	 * 
	 * @描述:考核项目保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午03:34:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean editKhxm(KhxmglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xmid="";
			if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
				xmid = Base.currXn;
			}else{
				xmid = UniqID.getInstance().getUniqIDHash();
			}
			model.setXmid(xmid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	public boolean isHave(KhxmglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @描述:删除对应评分对象设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-20 下午02:19:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKhxm(String[] ids) throws Exception {
		boolean result=true;
		 dao.runDelete(ids);
		//删除评分对象设置
		 result= dao.delPfdxSz(ids);
		 //删除对应未提交的评分
		 result=khpfService.delKhpfForXmid(ids);
		 return result;
		
		
	}
	public HashMap<String,String> getKhxmByXmmc(String xmmc) throws Exception {
		return dao.getKhxmByXmmc(xmmc);
	}
	/**
	 * 
	 * @描述:判断考核表是否被使用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-18 下午02:03:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String isUsed(String values) {
		String message=null;
		String[] valArr = values.split(",");
		StringBuffer notDel = new StringBuffer();
		for (int i = 0; i < valArr.length; i++) {
			HashMap<String,String> khxmMap = dao.isUsed(valArr[i]);
			if(null!=khxmMap.get("num")&&!"".equals(khxmMap.get("num"))){
				notDel.append("["+khxmMap.get("xmmc")+"]");
			}
		}
		if(0!=notDel.length()){
			message= "<font color='red'>"+notDel+"</font>"+"已有用户评分，不允许删除！";
		}
		return message;
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:获取考核项目
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKhxm(String xmid) throws Exception {
		return dao.getKhxm(xmid);
	}
	
	/**
	 * 
	 * @描述:考核项目列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午03:25:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhxmList() throws Exception {
		return dao.getKhxmList();
	}
	
	public List<HashMap<String,String>> getpfdxXxList(String id) throws Exception {
		return dao.getpfdxXxList(id);
	}
	
	public List<HashMap<String,String>> getPffwList(String pfzid,String khlx) throws Exception {
		return dao.getPffwList(pfzid,khlx);
	}
	/**
	 * 
	 * @描述:评分对象设置保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午05:49:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savePfdxSz(KhxmglForm model, List<KhxmglForm> pfdxxxList) throws Exception {
		//删除评分对象信息在插入
		 dao.delPfdxSz(model.getXmid());
		return PfdxSzPlbc(model, pfdxxxList);
	}
	/**
	 * 
	 * @描述:评分对象设置批量保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-15 下午05:52:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	private boolean PfdxSzPlbc(KhxmglForm model, List<KhxmglForm> pfdxxxList) throws SQLException {
		List<String[]> pfdxList = new ArrayList<String[]>();
		String[] pfdx = null;
		for (KhxmglForm pfdxForm : pfdxxxList) {
			pfdx = new String[7];
			pfdx[0] = model.getXmid();
			pfdx[1] = pfdxForm.getPfzid();
			pfdx[2] = pfdxForm.getSjfwdm();
			pfdx[3] = pfdxForm.getKhbid();
			pfdx[4] = pfdxForm.getSzqz();
			pfdx[5] = pfdxForm.getJsfs();
			pfdx[6] = pfdxForm.getJsfsbfb();
			pfdxList.add(pfdx);
		}
		return dao.PfdxSzPlbc(pfdxList);
	}
}
