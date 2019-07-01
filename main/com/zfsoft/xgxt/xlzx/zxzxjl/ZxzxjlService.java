/**
 * @部门:学工产品事业部
 * @日期：2016-12-22 上午11:01:18 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-12-22 上午11:01:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxjlService extends SuperServiceImpl<ZxzxjlModel, ZxzxjlDao>{
	private ZxzxjlDao dao = new ZxzxjlDao();
	private ZxzxjlxxDao jlxxDao = new ZxzxjlxxDao();
	
	/** 
	 * @描述:获取是否选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:14:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getSfList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "是");
		map_one.put("mc", "是");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "否");
		map_two.put("mc", "否");
		list.add(map_two);
		return list;
	}
	
	/** 
	 * @描述:获取家庭所在地选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:17:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJtszdList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "大中城市");
		map_one.put("mc", "大中城市");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "小城镇");
		map_two.put("mc", "小城镇");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "农村");
		map_three.put("mc", "农村");
		list.add(map_three);
		return list;
	}
	
	/** 
	 * @描述:获取经济状态选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:19:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJjzkList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "富裕");
		map_one.put("mc", "富裕");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "一般");
		map_two.put("mc", "一般");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "贫困");
		map_three.put("mc", "贫困");
		list.add(map_three);
		return list;
	}
	
	/** 
	 * @描述:获取文化程度选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:23:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getWhcdList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "研究生以上");
		map_one.put("mc", "研究生以上");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "大学、大专");
		map_two.put("mc", "大学、大专");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "高中、中专、中技");
		map_three.put("mc", "高中、中专、中技");
		list.add(map_three);
		HashMap<String,String> map_four = new HashMap<String, String>();
		map_four.put("dm", "初中");
		map_four.put("mc", "初中");
		list.add(map_four);
		HashMap<String,String> map_five = new HashMap<String, String>();
		map_five.put("dm", "小学");
		map_five.put("mc", "小学");
		list.add(map_five);
		HashMap<String,String> map_six = new HashMap<String, String>();
		map_six.put("dm", "其他");
		map_six.put("mc", "其他");
		list.add(map_six);
		return list;
	}
	
	/** 
	 * @描述:获取家庭状况选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:25:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getHyzkList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "良好");
		map_one.put("mc", "良好");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "失和");
		map_two.put("mc", "失和");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "丧亲");
		map_three.put("mc", "丧亲");
		list.add(map_three);
		HashMap<String,String> map_four = new HashMap<String, String>();
		map_four.put("dm", "离异");
		map_four.put("mc", "离异");
		list.add(map_four);
		return list;
	}
	
	/** 
	 * @描述:获取有无选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:27:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getYwList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "有");
		map_one.put("mc", "有");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "无");
		map_two.put("mc", "无");
		list.add(map_two);
		return list;
	}
	
	/** 
	 * @描述:获取喜欢程度选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:28:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXhcdList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "非常喜欢");
		map_one.put("mc", "非常喜欢");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "一般");
		map_two.put("mc", "一般");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "不喜欢");
		map_three.put("mc", "不喜欢");
		list.add(map_three);
		return list;
	}
	
	/** 
	 * @描述:获取咨询问题选项(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午02:31:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxwtList(){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map_one = new HashMap<String, String>();
		map_one.put("dm", "适应");
		map_one.put("mc", "适应");
		list.add(map_one);
		HashMap<String,String> map_two = new HashMap<String, String>();
		map_two.put("dm", "学习");
		map_two.put("mc", "学习");
		list.add(map_two);
		HashMap<String,String> map_three = new HashMap<String, String>();
		map_three.put("dm", "人际关系");
		map_three.put("mc", "人际关系");
		list.add(map_three);
		HashMap<String,String> map_four = new HashMap<String, String>();
		map_four.put("dm", "恋爱");
		map_four.put("mc", "恋爱");
		list.add(map_four);
		HashMap<String,String> map_five = new HashMap<String, String>();
		map_five.put("dm", "性");
		map_five.put("mc", "性");
		list.add(map_five);
		HashMap<String,String> map_six = new HashMap<String, String>();
		map_six.put("dm", "职业生涯规划");
		map_six.put("mc", "职业生涯规划");
		list.add(map_six);
		HashMap<String,String> map_seven = new HashMap<String, String>();
		map_seven.put("dm", "家庭");
		map_seven.put("mc", "家庭");
		list.add(map_seven);
		HashMap<String,String> map_eight = new HashMap<String, String>();
		map_eight.put("dm", "网络依赖");
		map_eight.put("mc", "网络依赖");
		list.add(map_eight);
		HashMap<String,String> map_nine = new HashMap<String, String>();
		map_nine.put("dm", "学校");
		map_nine.put("mc", "学校");
		list.add(map_nine);
		HashMap<String,String> map_ten = new HashMap<String, String>();
		map_ten.put("dm", "危机咨询");
		map_ten.put("mc", "危机咨询");
		list.add(map_ten);
		return list;
	}
	
	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午03:24:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean addZxjbxx(ZxzxjlModel form) throws Exception{
		String[] wts = form.getYzxwts();
		if(null != wts){			
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<wts.length;i++){
				sb.append(wts[i]);
				if(i != wts.length-1){
					sb.append(",");
				}
			}
			String yzxwt = sb.toString();
			form.setYzxwt(yzxwt);
		}
		return runInsert(form);
	}
	
	/** 
	 * @描述:更新基本信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午03:32:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateZxjbxx(ZxzxjlModel form) throws Exception{
		String[] wts = form.getYzxwts();
		if(null != wts){			
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<wts.length;i++){
				sb.append(wts[i]);
				if(i != wts.length-1){
					sb.append(",");
				}
			}
			String yzxwt = sb.toString();
			form.setYzxwt(yzxwt);
		}else{
			form.setYzxwt("");
		}
		return runUpdate(form);
	}
	
	/** 
	 * @描述:删除基本信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午03:55:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public int deleteZxjbxx(String[] xhs) throws Exception{
		dao.deleteZxzxjl(xhs);//先删除在线咨询记录
		return runDelete(xhs);//删除主表记录
	}
	
	/** 
	 * @描述:验证基本信息是否存在(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-22 下午04:14:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExists(ZxzxjlModel form){
		String num = dao.getRecord(form);
		return Integer.valueOf(num)>0;
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @描述:维护在线咨询记录(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 上午11:10:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean whZxzxJl(String[] zjs,String[] xgs,String[] delIds,String xh,User user) throws Exception{
		List<ZxzxjlxxModel> insertList = new ArrayList<ZxzxjlxxModel>();
		List<ZxzxjlxxModel> updateList = new ArrayList<ZxzxjlxxModel>();
		if(null != zjs && zjs.length>0){//如果有增加数据			
			for(int i = 0;i<zjs.length;i++){
				ZxzxjlxxModel model = new ZxzxjlxxModel();
				model.setXh(xh);
				String[] zj = zjs[i].split(",");
				for(int j = 0;j<zj.length;j++){
					if(j==0){						
						model.setBh(zj[j]);
					}else if(j==1){
						model.setZxsxm(zj[j]);
					}else if(j==2){
						model.setZxsj(zj[j]);
					}else if(j==3){
						model.setZxdd(zj[j]);
					}else if(j==4){
						model.setZxpg(zj[j]);
					}else if(j==5){
						model.setZxgc(zj[j]);
					}else if(j==6){
						model.setZxfk(zj[j]);
					}else{
						if(zj[j].equalsIgnoreCase("blank")){
							model.setZxth("");
						}else{							
							model.setZxth(zj[j]);
						}
					}
				}
				insertList.add(model);
			}
		}
		if(null != xgs && xgs.length>0){//如果有修改数据
			for(int i = 0;i<xgs.length;i++){
				ZxzxjlxxModel model = new ZxzxjlxxModel();
				String[] zj = xgs[i].split(",");
				for(int j = 0;j<zj.length;j++){
					if(j==0){						
						model.setId(zj[j]);
					}else if(j==1){
						model.setBh(zj[j]);
					}else if(j==2){
						model.setZxsxm(zj[j]);
					}else if(j==3){
						model.setZxsj(zj[j]);
					}else if(j==4){
						model.setZxdd(zj[j]);
					}else if(j==5){
						model.setZxpg(zj[j]);
					}else if(j==6){
						model.setZxgc(zj[j]);
					}else if(j==7){
						model.setZxfk(zj[j]);
					}else{
						if(zj[j].equalsIgnoreCase("blank")){
							model.setZxth("");
						}else{							
							model.setZxth(zj[j]);
						}
					}
				}
				updateList.add(model);
			}
		}
		boolean result = true;
		boolean result1 = true;
		if((null != zjs && zjs.length>0) || null != xgs && xgs.length>0){
			result = jlxxDao.plInsertOrUpdate(insertList, updateList, user);
		}
		if(null != delIds && delIds.length>0){//如果有删除数据
			result1 = jlxxDao.plsc(delIds);
		}
		return result&&result1;
	}
	
	/** 
	 * @描述:获取咨询记录列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 下午02:05:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxjlList(String xh,User user,boolean sfck) throws Exception{
		List<HashMap<String, String>> list = jlxxDao.getZxjlList(xh,user);
		if(sfck){		
			if(null != list && list.size()>0){
				List<HashMap<String, String>> mapList = new ArrayList<HashMap<String,String>>();
				for(HashMap<String, String> map:list){
					HashMap<String, String> mapp = StringUtils.formatMap(map);
					mapList.add(mapp);
				}
				return mapList;
			}else{
				return list;
			}
		}else{
			return list;
		}
		
	}	
	
	/**
	 * 
	 * @描述: 获取导出登记表所需要的数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-4-5 上午10:49:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getExportData(String xh,User user){
		return dao.getXsxlzxxx(xh);
	}
	
	/** 
	 * @描述:获取咨询记录idList(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-27 下午02:00:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxIdList(String xh){
		return dao.getZxIdList(xh);
	}
}
