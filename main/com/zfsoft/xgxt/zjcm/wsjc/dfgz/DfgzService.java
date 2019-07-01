/**
 * @部门:学工产品事业部
 * @日期：2016-3-2 上午09:00:15 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.dfgz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.zjcm.wsjc.pfz.PfzDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2016-3-2 上午09:00:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DfgzService extends SuperServiceImpl<DfgzForm, DfgzDao> {
	
	private PfzDao pfzDao = new PfzDao();
	
	/**
	 * @throws SQLException  
	 * @描述:根据系统时间取月份
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-7 上午11:01:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<String> 返回类型 
	 * @throws 
	 */
	public List<String> getYueFenByXn(String xn) throws SQLException{
		return dao.getYueFenByXn();
	}
	
	public String getXpmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	public List<HashMap<String, String>> getPfzList(){
		return dao.getPfzList();
	}

	/**
	 * @throws Exception  
	 * @描述:查询所有记录，返回json格式
	 * @作者：cq [工号：785]
	 * @日期：2016-3-11 上午09:10:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccny
	 * @return
	 * DfgzForm 返回类型 
	 * @throws 
	 */
	public DfgzForm getAll(DfgzForm model) throws Exception {
		
		List<HashMap<String, String>> pfzszList = null;
		
		List<HashMap<String, String>> pfzList = dao.getPfzList();
		//根据学年、学期、抽查年月查询打分设置
		DfgzForm myForm = dao.getDfszInfo(model);
		DfgzForm viewForm = new DfgzForm();
		if(null!=myForm&&!StringUtils.isBlank(myForm.getDfszid())){
			BeanUtils.copyProperties(viewForm, myForm);
			pfzszList = pfzszList(myForm.getDfszid());
		}
		
		viewForm.setPfzList(pfzList);
		viewForm.setPfzszList(pfzszList);
		
		return viewForm;
	}
	
	
	public List<HashMap<String, String>> pfzszList(String dfszid){
		return dao.pfzszList(dfszid);
	}
	

	/**
	 * @throws Exception  
	 * @描述:查询是否已存在
	 * @作者：cq [工号：785]
	 * @日期：2016-3-11 下午03:36:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(DfgzForm myForm) throws Exception {
		
		myForm = dao.getDfszInfo(myForm);
		
		if(null==myForm){
			return false;
		}
		return true;
	}

	/**
	 * @throws Exception  
	 * @描述:保存打分规则
	 * @作者：cq [工号：785]
	 * @日期：2016-3-12 上午11:47:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param pfzList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveDfgzSz(DfgzForm myForm, List<DfgzForm> pfzList) throws Exception {
		
		//删除之前的评分组设置
		 delPfzSz(new String[]{myForm.getDfszid()});
		//删除之前的寝室
		 delPfzQs(new String[]{myForm.getDfszid()});
		//生成抽查数据
		 boolean bl = true;
		 
		 for (int i = 0; i < pfzList.size(); i++) {
			 if(!"0".equals(pfzList.get(i).getCcbl())){
				 bl = dao.randomQs(myForm,pfzList.get(i));
				 if(!bl){
					 break;
				 }
			 }
		}
		 
		 if(bl){
			 //保存评分组设置
			 savePfzSz(myForm,pfzList);
		 }
		 
		return bl;
	}
	
	
	//删除之前的评分组设置
	public boolean delPfzSz(String[] dfszid) throws Exception{
		
		boolean flg = true;
		
		for (int i = 0; i < dfszid.length; i++) {
			flg = dao.delPfzSz(dfszid[i]);
			if(!flg){
				break;
			}
		}
		return flg;
	}
	
	//删除之前的寝室
	public boolean delPfzQs(String[] dfszid) throws Exception{
		
		boolean flg = true;
		
		for (int i = 0; i < dfszid.length; i++) {
			flg = dao.delPfzQs(dfszid[i]);
			if(!flg){
				break;
			}
		}
		return flg;
	}
	
	
	/**
	 * @throws SQLException  
	 * @描述:保存评分组设置
	 * @作者：cq [工号：785]
	 * @日期：2016-3-12 下午02:31:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param pfzList
	 * void 返回类型 
	 * @throws 
	 */
	public boolean savePfzSz(DfgzForm myForm, List<DfgzForm> pfzList) throws SQLException {
		
		List<String[]> pfzxxList = new ArrayList<String[]>();
		String[] pfdx = null;
		for (DfgzForm model : pfzList) {
			pfdx = new String[4];
			pfdx[0] = myForm.getDfszid();
			pfdx[1] = model.getPfzid();
			pfdx[2] = model.getCcbl();
			pfdx[3] = model.getBhbyb();
			pfzxxList.add(pfdx);
		}
		return dao.pfzSzPlbc(pfzxxList);
		
	}

	/** 
	 * @描述:是否有提交的卫生分
	 * @作者：cq [工号：785]
	 * @日期：2016-3-12 下午02:44:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dfszid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean wsfTj(String dfszid) {
		return dao.getWsfTj(dfszid);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2016-3-14 上午08:59:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean editGz(DfgzForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xmid = UniqID.getInstance().getUniqIDHash();
			model.setDfszid(xmid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}

	/** 
	 * @描述:判断是否有抽查数据
	 * @作者：cq [工号：785]
	 * @日期：2016-3-14 下午06:17:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean getExistCcsj(DfgzForm myForm) {
		return dao.getExistCcsj(myForm.getDfszid());
	}
	
	
	/**
	 * 
	 * @描述:抽查寝室查询
	 * @作者：cq [工号：785]
	 * @日期：2016-3-15 下午02:39:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCcqsList(DfgzForm t, User user) throws Exception{
		return dao.getCcqsList(t, user);
	
	}

}
