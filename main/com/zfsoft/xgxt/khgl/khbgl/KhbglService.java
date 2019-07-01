/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午11:40:07 
 */
package com.zfsoft.xgxt.khgl.khbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.khgl.khbgl.khnrgl.KhnrglDao;
import com.zfsoft.xgxt.khgl.khbgl.khnrgl.KhnrglForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-11 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhbglService extends SuperServiceImpl<KhbglForm, KhbglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final String SCZT = "1";//1:删除
	private static final String KHB_TY="2";//考核表默认停用
	private KhbglDao dao = new KhbglDao();
	private KhnrglDao khnrDao = new KhnrglDao();
	/**
	 * 
	 * @描述:判断考核表是否已存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:15:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHave(KhbglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @描述:判断考核表是否被使用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-18 上午11:36:26
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
			HashMap<String,String> khbMap = dao.isUsed(valArr[i]);
			if(null!=khbMap.get("num")&&!"".equals(khbMap.get("num"))){
				notDel.append("["+khbMap.get("khbmc")+"]");
			}
		}
		if(0!=notDel.length()){
			message= "<font color='red'>"+notDel+"</font>"+"已被使用，不允许删除！";
		}
		return message;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:考核表启用状态设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:15:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qySz(String[] ids,String qyzt) throws Exception {
		List<String[]> KhbList = new ArrayList<String[]>();
		String[] Khb = null;
		for (int i = 0; i < ids.length; i++) {
			Khb = new String[2];
			Khb[0]=qyzt;
			Khb[1]=ids[i];
			KhbList.add(Khb);
		}
		return dao.qySz(KhbList);
	}
	/**
	 * 
	 * @描述:删除考核表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-14 下午12:00:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKhb(String[] ids) throws Exception {
		 dao.runDelete(ids);
		//删除考核内容
		return dao.delKhnr(ids);
	}

	/**
	 * 
	 * @描述:考核表保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:34:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean editKhb(KhbglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String Khbid = UniqID.getInstance().getUniqIDHash();
			model.setKhbid(Khbid);
			model.setSfqy(KHB_TY);//考核表默认停用
			model.setCjsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			model.setXgsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runUpdate(model);
		}
		return result;
		
	}
	/**
	 * @throws Exception
	 * 
	 * @描述:获取考核表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKhb(String khbid) throws Exception {
		return dao.getKhb(khbid);
	}
	public List<HashMap<String, String>> getKhnrList(KhbglForm model)
			throws Exception {
	return dao.getKhnrList(model);
	}
	/**
	 * 
	 * @描述:考核表列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:25:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhbList(String khdxid) throws Exception {
		return dao.getKhbList(khdxid);
	}
	
	public List<HashMap<String, String>> getKhbList(KhbglForm model, User user)
	throws Exception{
		return null;
		
	}
	/**
	 * 
	 * @描述:考核表复制
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-14 上午10:08:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean khbfz(KhbglForm model) throws Exception {
		String fzid = UniqID.getInstance().getUniqIDHash();
		KhbglForm fzForm = new KhbglForm();
		KhnrglForm khnrForm = new KhnrglForm();
		khnrForm.setKhbid(model.getKhbid());
		KhbglForm khbForm = dao.getModel(model);//被复制考核表
		List<HashMap<String, String>> khnrList = khnrDao.getKhnrList(model.getKhbid());//被复制考核表考核内容
		BeanUtils.copyProperties(fzForm, StringUtils.formatData(khbForm));
		fzForm.setKhbid(fzid);
		fzForm.setKhbmc(model.getKhbmc());
		dao.runInsert(fzForm);
		List<String[]> fznrList= new ArrayList<String[]>();
		String[] fznrArr=null;
		for (HashMap<String, String> khnrMap : khnrList) {
			fznrArr=new String[9];
			fznrArr[0]=fzid;
			fznrArr[1]=khnrMap.get("yjzb");
			fznrArr[2]=khnrMap.get("ejzb");
			fznrArr[3]=khnrMap.get("khnr");
			fznrArr[4]=khnrMap.get("fzlx");
			fznrArr[5]=khnrMap.get("fzzgf");
			fznrArr[6]=khnrMap.get("fzzdf");
			fznrArr[7]=khnrMap.get("xssx");
			fznrArr[8]=khnrMap.get("pflx");
			fznrList.add(fznrArr);
		}
		return dao.khnrFz(fznrList);
	}
}
