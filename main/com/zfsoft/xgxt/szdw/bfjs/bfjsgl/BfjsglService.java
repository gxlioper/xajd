/**
 * @部门:学工产品事业部
 * @日期：2017-4-19 下午01:59:41 
 */  
package com.zfsoft.xgxt.szdw.bfjs.bfjsgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 班风建设service(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-4-19 下午01:59:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BfjsglService extends SuperServiceImpl<BfjsglForm, BfjsglDao>{
	
	BfjsglDao dao = new BfjsglDao();
	
	public boolean runInsertForZj(BfjsglForm t) throws Exception {
		String jcid = UniqID.getInstance().getUniqIDHash();
		t.setJcid(jcid);
		return runInsert(t);
	}
	
	/** 
	 * @描述:获取班级列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-21 上午11:33:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(BfjsglForm t,User user) throws Exception{
		return dao.getBjList(t, user);
	}
	
	/** 
	 * @描述:获取学生信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-21 下午03:06:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsxxList(BfjsglForm model, User user) throws Exception{
		return dao.getXsxxList(model, user);
	} 
	
	/**
	 * @throws SQLException  
	 * @描述:增加检查明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-19 下午02:16:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertJcmx(BfjsglForm t) throws SQLException {
		String[] qjs = t.getQjs();//获取请假的学生
		String[] qqs = t.getQqs();//获取缺勤的学生
		String[] cds = t.getCds();//获取迟到的学生
		String[] zts = t.getZts();//获取早退的学生
		List<String[]> list = new ArrayList<String[]>();
		if(null != qjs && qjs.length>0){
			for(int i = 0;i<qjs.length;i++){
				String[] qjstr = qjs[i].split("_");
				list.add(qjstr);
			}
		}
		if(null != qqs && qqs.length>0){
			for(int i = 0;i<qqs.length;i++){
				String[] qqstr = qqs[i].split("_");
				list.add(qqstr);
			}
		}
		if(null != cds && cds.length>0){
			for(int i = 0;i<cds.length;i++){
				String[] cdstr = cds[i].split("_");
				list.add(cdstr);
			}
		}
		if(null != zts && zts.length>0){
			for(int i = 0;i<zts.length;i++){
				String[] ztstr = zts[i].split("_");
				list.add(ztstr);
			}
		}
		if(list.size()>0){			
			return dao.insertJcmx(list, t);
		}else{
			return true;
		}
	}
	
	/** 
	 * @描述:判断班级是否有检查日程(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-24 上午08:38:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isBjExist(BfjsglForm form) {
		return dao.isBjExist(form);
	}
	
	/**
	 * @throws Exception  
	 * @描述:获取考勤人员信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-24 上午09:57:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public BfjsglForm getKqRyxxList(BfjsglForm form) throws Exception{
		HashMap<String,String> modelMap = dao.getModelMap(form);
		form.setBjdm(modelMap.get("bjdm"));
		form.setBjmc(modelMap.get("bjmc"));
		form.setJcrq(modelMap.get("jcrq"));
		List<HashMap<String,String>> list = dao.getKqRyxxList(form);
		if(null != list && list.size()>0){			
			List<HashMap<String,String>> zcqqList = new ArrayList<HashMap<String,String>>();//早操人员缺勤信息
			List<HashMap<String,String>> zdqqList = new ArrayList<HashMap<String,String>>();//早读人员缺勤信息
			List<HashMap<String,String>> skqqList = new ArrayList<HashMap<String,String>>();//上课人员缺勤信息
			List<HashMap<String,String>> wzxqqList = new ArrayList<HashMap<String,String>>();//晚自修缺勤信息
			List<HashMap<String,String>> zccqList = new ArrayList<HashMap<String,String>>();//早操人员出勤信息
			List<HashMap<String,String>> zdcqList = new ArrayList<HashMap<String,String>>();//早读人员出勤信息
			List<HashMap<String,String>> skcqList = new ArrayList<HashMap<String,String>>();//上课人员出勤信息
			List<HashMap<String,String>> wzxcqList = new ArrayList<HashMap<String,String>>();//晚自修出勤信息
			for(HashMap<String,String> map : list){
				if("zc".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						zccqList.add(map);
					}else{
						zcqqList.add(map);
					}
				}else if("zd".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						zdcqList.add(map);
					}else{
						zdqqList.add(map);
					}
				}else if("sk".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						skcqList.add(map);
					}else{
						skqqList.add(map);
					}
				}else if("wzx".equalsIgnoreCase(map.get("jclx"))){
					if(map.get("qqlxdm").equalsIgnoreCase("cq")){
						wzxcqList.add(map);
					}else{
						wzxqqList.add(map);
					}
				}
			}
			form.setZccqList(zccqList);
			form.setZcqqList(zcqqList);
			form.setZdcqList(zdcqList);
			form.setZdqqList(zdqqList);
			form.setSkcqList(skcqList);
			form.setSkqqList(skqqList);
			form.setWzxcqList(wzxcqList);
			form.setWzxqqList(wzxqqList);
		}
		return form;
	}
	
	/** 
	 * @描述:删除班风建设(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-24 下午04:21:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delBfjs(String[] ids) throws Exception{
		return dao.delBfjs(ids);
	}
	
	/** 
	 * @描述:设置要删除和要修改的学号(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-25 上午08:55:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * void 返回类型 
	 * @throws 
	 */
	public List<String[]> setDelXh(BfjsglForm form){
		List<String[]> list = new ArrayList<String[]>();
		String[] qjs = form.getQjs();//获取请假的学生
		String[] qqs = form.getQqs();//获取缺勤的学生
		String[] cds = form.getCds();//获取迟到的学生
		String[] zts = form.getZts();//获取早退的学生
		if(null != qjs && qjs.length>0){
			for(int i = 0;i<qjs.length;i++){
				String[] qjstr = qjs[i].split("_");
				String[] qjstrr = new String[3];
				qjstrr[0] = form.getJcid();
				qjstrr[1] = qjstr[1];
				qjstrr[2] = qjstr[0];
				list.add(qjstrr);
			}
		}
		if(null != qqs && qqs.length>0){
			for(int i = 0;i<qqs.length;i++){
				String[] qqstr = qqs[i].split("_");
				String[] qqstrr = new String[3];
				qqstrr[0] = form.getJcid();
				qqstrr[1] = qqstr[1];
				qqstrr[2] = qqstr[0];
				list.add(qqstrr);
			}
		}
		if(null != cds && cds.length>0){
			for(int i = 0;i<cds.length;i++){
				String[] cdstr = cds[i].split("_");
				String[] cdstrr = new String[3];
				cdstrr[0] = form.getJcid();
				cdstrr[1] = cdstr[1];
				cdstrr[2] = cdstr[0];
				list.add(cdstrr);
			}
		}
		if(null != zts && zts.length>0){
			for(int i = 0;i<zts.length;i++){
				String[] ztstr = zts[i].split("_");
				String[] ztstrr = new String[3];
				ztstrr[0] = form.getJcid();
				ztstrr[1] = ztstr[1];
				ztstrr[2] = ztstr[0];
				list.add(ztstrr);
			}
		}
		String[] dels = form.getDels();
		if(null != dels && dels.length>0){
			for(int i = 0;i<dels.length;i++){
				list.add(dels[i].split("_"));
			}
		}
		return list;
	}
	
	/**
	 * @throws SQLException  
	 * @描述:根据学号、jcID、检查类型删除考勤明细(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-25 上午09:38:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public void delXh(BfjsglForm form) throws SQLException{
		List<String[]> list = setDelXh(form);
		if(null != list && list.size()>0){
			dao.delXh(list);
		}
	}
}
