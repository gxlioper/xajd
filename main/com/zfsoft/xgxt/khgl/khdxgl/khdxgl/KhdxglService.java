/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午11:40:07 
 */
package com.zfsoft.xgxt.khgl.khdxgl.khdxgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-8-10 上午11:40:07
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class KhdxglService extends SuperServiceImpl<KhdxglForm, KhdxglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SCZT = "1";//1:删除
	private KhdxglDao dao = new KhdxglDao();
	/**
	 * 
	 * @描述:判断考核对象是否已存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午03:15:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHave(KhdxglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @描述:判断考核对象是否被使用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-19 下午03:27:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isUsed(String values) {
		boolean flag = false;
		if (values != null) {
			String[] khdxArr = values.split(",");
			for (int i = 0; i < khdxArr.length; i++) {
				flag = dao.isUsed(khdxArr[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	

	/**
	 * 
	 * @描述:考核对象保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午03:34:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean editKhdx(KhdxglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String khdxid = UniqID.getInstance().getUniqIDHash();
			model.setKhdxid(khdxid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:获取考核对象
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getKhdx(String khdxid) throws Exception {
		return dao.getKhdx(khdxid);
	}
	/**
	 * 
	 * @描述:考核对象列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 下午03:25:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKhdxList() throws Exception {
		return dao.getKhdxList();
	}
	
	public List<HashMap<String, String>> getKhdxList(KhdxglForm model, User user)
	throws Exception{
		if("1".equals(model.getKhlx())){
			return dao.getKhdxOfStuList(model,user);
		}else{
			return dao.getKhdxOfTeaList(model,user);
		}
		
	}
	
	public List<HashMap<String, String>> getKhdxFpList(KhdxglForm model, User user)
	throws Exception{
		if("1".equals(model.getKhlx())){
			return dao.getStuList(model,user);
		}else{
			return dao.getTeaList(model,user);
		}
		
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:考核对象分配保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午08:55:42
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
	public boolean saveKhdxFp(KhdxglForm model,String value) throws Exception{
		String[] values=value.split(",");
		List<String[]> khdxList = new ArrayList<String[]>();
		String[] khdx = null;
		for (int i = 0; i < values.length; i++) {
			khdx = new String[3];
			String dxid = UniqID.getInstance().getUniqIDHash();
			
			khdx[0]=dxid;
			khdx[1]=model.getKhdxid();
			khdx[2]=values[i];
			khdxList.add(khdx);
		}
		if("1".equals(model.getKhlx())){
			return dao.KhxsInsert(khdxList);//考核学生增加
		}else{
			return dao.KhjsInsert(khdxList);//考核教师增加
		}
	}
	/**
	 * 
	 * @描述:考核对象取消分配
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-11 上午09:23:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveKhdxQxFp(KhdxglForm model,String value) throws Exception{
		boolean result=true;
		String[] values=value.split(",");
		List<String[]> khdxList = new ArrayList<String[]>();
		String[] khdx = null;
		for (int i = 0; i < values.length; i++) {
			khdx = new String[2];
			khdx[0]=model.getKhdxid();
			khdx[1]=values[i];
			khdxList.add(khdx);
		}
		if("1".equals(model.getKhlx())){
			result=dao.KhxsDel(khdxList);//考核学生取消分配
			
		}else{
			result=dao.KhjsDel(khdxList);//考核教师取消分配
		}
		//删除考核学生对应的评分成员
		result=dao.pfcyJsDel(khdxList);
		result=dao.pfcyXsDel(khdxList);
		return result;
	}

	
	
	



}
