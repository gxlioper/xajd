/**
 * @部门:学工产品事业部
 * @日期：2015-7-13 上午11:40:07 
 */
package com.zfsoft.xgxt.khgl.khdxgl.pfzgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
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

public class PfzglService extends SuperServiceImpl<PfzglForm, PfzglDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SCZT = "1";//1:删除
	private PfzglDao dao = new PfzglDao();
	/**
	 * 
	 * @描述:判断评分组是否已存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午03:15:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isHave(PfzglForm model) {
		return dao.isHave(model);
	}
	/**
	 * 
	 * @描述:是否被使用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-19 下午04:35:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isUsed(String values) {
		boolean flag = false;
		if (values != null) {
			String[] pfzArr = values.split(",");
			for (int i = 0; i < pfzArr.length; i++) {
				flag = dao.isUsed(pfzArr[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除评分组
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午03:15:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
//	public boolean delPfz(String[] ids) throws Exception {
//		List<String[]> PfzList = new ArrayList<String[]>();
//		String[] Pfz = null;
//		for (int i = 0; i < ids.length; i++) {
//			Pfz = new String[2];
//			Pfz[0]=SCZT;
//			Pfz[1]=ids[i];
//			PfzList.add(Pfz);
//		}
//		return dao.delPfz(PfzList);
//	}

	/**
	 * 
	 * @描述:评分组保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-10 下午03:34:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean editPfz(PfzglForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String Pfzid = UniqID.getInstance().getUniqIDHash();
			model.setPfzid(Pfzid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
		
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:获取评分组
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-13 下午04:40:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String,String> getPfzgl(PfzglForm t) throws Exception {
		return dao.getPfzgl(t);
	}
	
	public List<HashMap<String, String>> getKhdxList(PfzglForm model, User user)
	throws Exception{
		if("1".equals(model.getKhlx())){
			return dao.getStuList(model,user);
		}else{
			return dao.getTeaList(model,user);
		}
		
	}
	/**
	 * 
	 * @描述:查询评分成员列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-12 上午11:38:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfcyList(PfzglForm model, User user)
	throws Exception{
		return dao.getPfcyList(model, user);
	}
	
	/**
	 * @描述：评分成员班级
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月29日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPfcybjList(PfzglForm model)
	throws Exception{
		return dao.getPfcybjList(model);
	}
	
	public List<HashMap<String, String>> getViewPfcyList(PfzglForm model, User user)
	throws Exception{
		return dao.getViewPfcyList(model, user);
	}
	
	public List<HashMap<String, String>> getPfzList(String khdxid)
	throws Exception{
		return dao.getPfzList(khdxid);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:评分组分配保存
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
	public boolean savePfcy(PfzglForm model,String value,String khdxr,User user) throws Exception{
		String[] pfcys=value.split(",");
		String[] khdxrs=khdxr==""?null:khdxr.split(",");
		boolean result = true;
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> khdxList = getKhdxList(model,user);
		//考核对象全选
		if("1".equals(model.getSfqx())&&null==khdxrs){
			khdxrs= new String[khdxList.size()];
			for (int i = 0; i < khdxList.size(); i++) {
				khdxrs[i]=null==khdxList.get(i).get("xh")?khdxList.get(i).get("zgh"):khdxList.get(i).get("xh");
			}
		}
		List<String[]> pfzcyList = new ArrayList<String[]>();
		String[] Pfz = null;
		List<String[]> pfzdelList = new ArrayList<String[]>();
		String[] Pfzdel = null;
		for (int i = 0; i < khdxrs.length; i++) {
			for (int j = 0; j < pfcys.length; j++) {
				Pfz = new String[3];
				Pfz[0]=model.getPfzid();
				Pfz[2]=pfcys[j];
				Pfz[1]=khdxrs[i];
				pfzcyList.add(Pfz);
			}
			Pfzdel=new String[3];
			Pfzdel[0]=model.getPfzid();
			Pfzdel[1]=khdxrs[i];
			Pfzdel[2]=model.getPfzid();
			pfzdelList.add(Pfzdel);
		}
		if("1".equals(model.getPflx())){ //参与考核行为的对象为学生
			//先删除在插入
			if("1".equals(model.getSfqx())){
				result=dao.pfxsPlDel(pfzdelList);//考核对象多选，先清空原有评分人
			}
			else{
				result=dao.pfxsDel(pfzcyList);
			}
			//参与评分
			if("save".equals(model.getType())){
				result= dao.pfxsInsert(pfzcyList);
			}
		}else{ //参与考核行为的对象为教师
			if("1".equals(model.getSfqx())){
				result=dao.pfjsPlDel(pfzdelList);
			}
			else{
				result=dao.pfjsDel(pfzcyList);
			}
			if("save".equals(model.getType())){
				result=dao.pfjsInsert(pfzcyList);
			}
		}
		return result;
	}
	
	/**
	 * @描述：保存评分成员班级
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月31日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param values
	 * @param khdx
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean savePfcybj(PfzglForm model,String values,String khdx,User user) throws Exception{
		String pfzid=model.getPfzid();
		String[] classids=values.split(",");
		//拼接classid 
		StringBuilder classid=new StringBuilder();
		classid.append("'");
		for(int i=0;i<classids.length-1;i++){
			classid.append(classids[i]);
			classid.append("','");
		}
		classid.append(classids[classids.length-1]);
		classid.append("'");
		boolean result = true;
		result=dao.pfxsbjDel(pfzid,khdx,classid.toString());
		//参与评分
		if("save".equals(model.getType())){
			result= dao.pfxsbjInsert(pfzid,khdx,classid.toString());
		}
		return result;
	}
	
	
	/**
	 * 
	 * @描述:评分组取消分配
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
	public boolean savePfzQxPf(PfzglForm model,String value) throws Exception{
		String[] values=value.split(",");
		List<String[]> PfzList = new ArrayList<String[]>();
		String[] Pfz = null;
		for (int i = 0; i < values.length; i++) {
			Pfz = new String[2];
			Pfz[0]=model.getPfzid();
			Pfz[1]=values[i];
			PfzList.add(Pfz);
		}
		if("1".equals(model.getPflx())){
			return dao.pfxsDel(PfzList);//考核学生取消分配
		}else{
			return dao.pfjsDel(PfzList);//考核教师取消分配
		}
	}
	/** 
	 * @描述：
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月31日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws Exception 
	 */
	public boolean pfcydrSave(PfzglForm model, User user) throws Exception {
		boolean isSuccess = true;
		String xh = model.getXh();
		StringBuilder value=new StringBuilder();
		if (!StringUtil.isNull(xh)) {
			String[] xhs = xh.replaceAll("\r", " ").replaceAll("\n"," ").split(" ");
			for(int i=0;i<xhs.length-1;i++){
				if(StringUtils.isNull(xhs[i])){
					continue;
				}
				value.append(xhs[i].trim());
				value.append(",");
			}
			value.append(xhs[xhs.length-1].trim());
			isSuccess=savePfcy(model, value.toString(), model.getZgh(), user);

		}
		return isSuccess;
	}

	
	
	



}
