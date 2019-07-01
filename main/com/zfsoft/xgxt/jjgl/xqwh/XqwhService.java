/**
 * @部门:学工产品事业部
 * @日期：2014-8-27 下午04:22:03 
 */  
package com.zfsoft.xgxt.jjgl.xqwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.jjgl.common.JjxqztEnum;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqForm;
import com.zfsoft.xgxt.jjgl.jjxq.JjxqService;
import com.zfsoft.xgxt.jjgl.xsxqsq.XsxqsqForm;
import com.zfsoft.xgxt.jjgl.xsxqsq.XsxqsqService;
import org.apache.commons.lang.StringUtils;
import xgxt.utils.date.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-27 下午04:22:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XqwhService extends SuperServiceImpl<XqwhForm, XqwhDao> {

	/**
	 * 
	 * @描述:根据需求ID获取家教需求信息，包括当前的状态和家教人员信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 上午08:21:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getXqxxDetailsByXqid(String xqid) throws Exception{
		return dao.getXqxxDetailsByXqid(xqid);
	}
	
	/**
	 * 
	 * @描述:在岗数控制检测
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 下午03:46:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkZgs(String xh) throws Exception{
		return dao.checkZgs(xh);
	}
	
	/**
	 * 
	 * @描述:获取家教学生申请人列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 上午11:36:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getXqXsSqList(String xqid)throws Exception {
		return dao.getXqXsSqList(xqid);
	}
	
	
	/**
	 * 
	 * @描述:手工家教老师分配
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 下午03:26:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String>  assignManual(XqwhForm model) throws Exception{
		//执行结果
		HashMap<String , String> data = new HashMap<String, String>();
		//检查参数合法性
		if(model == null || StringUtils.isBlank(model.getXqid()) || StringUtils.isBlank(model.getXh())){
			data.put("isSuccess", "0");
			data.put("message", "参数错误，请重新提交！");
			return data;
		}
		//检查需求的状态合法性
		HashMap<String, String> modelMap = this.getXqxxDetailsByXqid(model.getXqid());
		
		if(!StringUtils.equals("1", modelMap.get("shzt"))){
			data.put("isSuccess", "0");
			data.put("message", "该家教需求审核不通过，不能分配家教老师！");
			return data;
		}else if(!StringUtils.equals(JjxqztEnum.WPC.getCode(), modelMap.get("jjzt"))){
			data.put("isSuccess", "0");
			data.put("message", "该家教需求状态不是‘未派出’状态，不能分配家教老师！");
			return data;
		}else if(xgxt.utils.String.StringUtils.isNotNull(modelMap.get("xssqid"))){
			data.put("isSuccess", "0");
			data.put("message", "该家教需求已被申请，不能分配家教老师！");
			return data;
		}

		//检查申请人的的在在岗数
		boolean checekRs = checkZgs(model.getXh());
		if(!checekRs){
			data.put("isSuccess", "0");
			data.put("message", "该学生在岗数已达到上限，请选择其他学生！");
			return data;
		}

		//1.设置需求状态
		JjxqForm jjxqModel = new JjxqForm();
		jjxqModel.setXqid(modelMap.get("xqid"));
		//设置为待交协议书
		jjxqModel.setJjzt(JjxqztEnum.DJXYS.getCode());
		//保存数据库
		boolean isSuccess = new JjxqService().runUpdate(jjxqModel);
		
		//需要先判断该学号是否已经申请该家教
		//如果已经申请了,则直接修改对应的申请记录
		//否则新建一条记录
		XsxqsqService xsxqsqService = new XsxqsqService();
		//新建一条学生家教申请记录，状态为有效和审核通过
		XsxqsqForm xsxqsqModel = new XsxqsqForm();
		xsxqsqModel.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
		xsxqsqModel.setXh(model.getXh());
		xsxqsqModel.setXqid(model.getXqid());
		String currTime = DateUtils.getCurrTime();
		xsxqsqModel.setSqsj(currTime);
		xsxqsqModel.setKssj(currTime);
		xsxqsqModel.setZt("1");
		xsxqsqModel.setShzt("1");
		isSuccess = xsxqsqService.runInsert(xsxqsqModel);

		if(isSuccess){
			data.put("isSuccess", "1");
			data.put("message", "操作成功!");
		}else{
			data.put("isSuccess", "0");
			data.put("message", "操作失败!");
		}
		
		return data;
	}
	
	/**
	 * 
	 * @描述:家教老师分配
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 下午03:26:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String>  assign(XqwhForm model) throws Exception{
		//执行结果
		HashMap<String , String> data = new HashMap<String, String>();
		//检查参数合法性
		if(model == null || StringUtils.isBlank(model.getXqid()) || StringUtils.isBlank(model.getXh())){
			data.put("isSuccess", "0");
			data.put("message", "参数错误，请重新提交！");
			return data;
		}
		
		//检查需求的状态合法性
		JjxqService jjxqService = new JjxqService();
		HashMap<String, String> modelMap = jjxqService.getModelMap(model.getXqid());
		
		if(!StringUtils.equals("1", modelMap.get("shzt"))){
			data.put("isSuccess", "0");
			data.put("message", "该家教需求审核不通过，不能分配家教老师！");
			return data;
		}else if(!StringUtils.equals(JjxqztEnum.WPC.getCode(), modelMap.get("jjzt"))){
			data.put("isSuccess", "0");
			data.put("message", "该家教需求状态不是‘未派出’状态，不能分配家教老师！");
			return data;
		//检查是否有老师正在教学中
		}else if(Integer.valueOf(modelMap.get("sfjjz")) > 0){
			data.put("isSuccess", "0");
			data.put("message", "该家教需求已处于教学中，不能分配家教老师！");
			return data;
		}
		
		//检查申请人的的在在岗数
		boolean checekRs = checkZgs(model.getXh());
		if(!checekRs){
			data.put("isSuccess", "0");
			data.put("message", "该学生在岗数已达到上限，请选择其他学生！");
			return data;
		}
		
		//1.设置需求状态
		JjxqForm jjxqModel = jjxqService.getModel(modelMap.get("xqid"));
		//设置为试教状态
		jjxqModel.setJjzt(JjxqztEnum.DJXYS.getCode());
		//保存数据库
		jjxqService.runUpdate(jjxqModel);
		
		//2.设置当前学号为通过状态，其他学号为不通过状态
		XsxqsqService xsxqsqService = new XsxqsqService();
		XsxqsqForm xsxqsqModel = xsxqsqService.getModelByXqidAndXh(model.getXqid(), model.getXh());
		xsxqsqModel.setShzt("1");
		xsxqsqModel.setKssj(DateUtils.getCurrTime());
		xsxqsqService.runUpdate(xsxqsqModel);
		
		
		//3.修改其他申请者的状态为不通过
		boolean isSuccess = dao.updateForAssign(model.getXqid(), model.getXh());
		
		
		if(isSuccess){
			data.put("isSuccess", "1");
			data.put("message", "操作成功!");
			
		}else{
			data.put("isSuccess", "0");
			data.put("message", "操作失败!");
		}
		return data;
	}
	
	
	/**
	 * 
	 * @描述:修改家教状态
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-28 下午03:26:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String>  changeJjzt(XqwhForm model) throws Exception{
		JjxqService jjxqService = new JjxqService();
		//执行结果
		HashMap<String , String> data = new HashMap<String, String>();
		//检查参数合法性
		if(model == null || StringUtils.isBlank(model.getXqid()) || StringUtils.isBlank(model.getJjzt())){
			data.put("isSuccess", "0");
			data.put("message", "参数错误，请重新提交！");
			return data;
		}
		
		String xqid = model.getXqid();
		String jjzt = model.getJjzt();
		
		//1.获取需求详细信息
		HashMap<String , String> xqDetails = getXqxxDetailsByXqid(xqid);
		
		//如果两个状态相等，则直接返回成功！
		if(StringUtils.equals(xqDetails.get("jjzt"),jjzt)){
			data.put("isSuccess", "1");
			data.put("message", "家教状态设置成功!");
			return data;
		}
		/*******************************************************/

		//关闭家教：
			//需要判断关闭的家教是否有被申请，申请是否通过
				//无人申请和申请已通过：只需要改家教状态
				//有人申请但未通过：需要改家教状态还要将申请审核为不通过

		//退家教和交协议书的时候学生的申请状态肯定是已通过的 既然申请状态是通过就不该再动了 我们只改变家教状态 学生可以在我的家教岗位中看到
		//退家教：一般是因为试讲失败
			//对应家教状态改为未派出，学生的申请直接退回到申请人
			//退家教后自己看到的家教市场是：对应家教已退回 当前可以申请
			//退家教后别人看到的家教市场是：对应家教未申请 当前可以申请
			//？？退家教后：家教岗位中是否看到  是否能再次申请

		//交协议书：
			//对应家教状态改为已交协议书
		
		//如果不同，分情况
		// 第一:如果需要修改状态到待试教或则无效，则需要将当前家教学生申请记录的 ZT 字段改为 0 即：无效状态！,同时也要将需求状态改为对应值
		// 第二：如果需要修改的状态为正常教学，或者结束，只需要将需求的状态改为对应值即可！
		//
		if(StringUtils.equals(jjzt, JjxqztEnum.WPC.getCode())){
			String xssqid = xqDetails.get("xssqid");
			if(StringUtils.isNotBlank(xssqid)){
				XsxqsqService xsxqsqService = new XsxqsqService();
				XsxqsqForm xsxqsqModel = xsxqsqService.getModel(xssqid);
				//设置无效
				xsxqsqModel.setZt("0");
				xsxqsqService.runUpdate(xsxqsqModel);
			}
			JjxqForm jjxqModel = jjxqService.getModel(xqid);
			jjxqModel.setJjzt(JjxqztEnum.WPC.getCode());
			jjxqService.runUpdate(jjxqModel);
			
		}else if(StringUtils.equals(jjzt, JjxqztEnum.YGB.getCode())){
			JjxqForm jjxqModel = jjxqService.getModel(xqid);
			jjxqModel.setJjzt(JjxqztEnum.YGB.getCode());
			jjxqService.runUpdate(jjxqModel);
			
			String xssqid = xqDetails.get("xssqid");
			if(StringUtils.isNotBlank(xssqid)){
				XsxqsqService xsxqsqService = new XsxqsqService();
				XsxqsqForm xsxqsqModel = xsxqsqService.getModel(xssqid);
				//设置无效
				xsxqsqModel.setZt("0");
				xsxqsqService.runUpdate(xsxqsqModel);
			}
		}else if(StringUtils.equals(jjzt, JjxqztEnum.DJXYS.getCode()) ||
				StringUtils.equals(jjzt, JjxqztEnum.YGB.getCode()) ||
				StringUtils.equals(jjzt, JjxqztEnum.YJXYS.getCode())){
			JjxqForm jjxqModel = jjxqService.getModel(xqid);
			jjxqModel.setJjzt(jjzt);
			jjxqService.runUpdate(jjxqModel);
		}
		/**************************************************************/
		///更新一下状态备注信息///
		JjxqForm jjxqModel = jjxqService.getModel(xqid);
		jjxqModel.setZtbz(model.getZtbz());
		jjxqService.runUpdate(jjxqModel);
		///更新一下状态备注信息///
		
		
		data.put("isSuccess", "1");
		data.put("message", "家教状态设置成功!");
		return data;
	}
	
	
	
	/**
	 * 
	 * @描述:获取用户投诉信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午05:09:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getTsxxList(String xqid) throws Exception{
		return dao.getTsxxList(xqid);
	}
	
	/**
	 * 
	 * @描述:获取评价信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午05:39:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getPjxxList(String xqid) throws Exception{
		return dao.getPjxxList(xqid);
	}
	
	
	/**
	 * 
	 * @描述:更新投诉信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-30 上午10:47:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateTsxx(XqwhForm model) throws Exception{
		return dao.updateTsxx(model) > 0;
	}

	/**
	 * 家教需求（增加）的保存
	 * @param xqwhForm
	 * @return
	 */
    public boolean xqwhSaveForAdd(XqwhForm xqwhForm) throws Exception {

    	xqwhForm.setXqid(dao.getXqidAsKey());
    	xqwhForm.setSqsj(DateUtils.getCurrTime());
    	xqwhForm.setShzt("1");
    	xqwhForm.setJjzt("0");
		return dao.xqwhSaveForAdd(xqwhForm);
    }

	/**
	 * 查询单个家教需求信息
	 * @param xqid
	 * @return
	 */
	public Map<String,String> getXqwhMap(String xqid) {

    	return dao.getXqwhMap(xqid);
	}

	/**
	 * 家教需求（修改）的保存
	 * @param xqwhForm
	 * @return
	 */
	public boolean xqwhSaveForEdit(XqwhForm xqwhForm) throws Exception {

		return dao.xqwhSaveForEdit(xqwhForm);
	}

	/**
	 * 家教需求删除
	 * @param ids
	 * @return
	 */
	public int xqwhDel(String[] ids) throws Exception {

		return dao.xqwhDel(ids);
	}

	/**
	 * 更新家教状态
	 * @param xqwhForm
	 * @return
	 */
	public boolean runUpdateJjzt(XqwhForm xqwhForm) throws Exception {

		return dao.runUpdateJjzt(xqwhForm);
	}

	/**
	 * 更新家教状态到最后一步审核通过之前的状态
	 * @param xqid
	 * @return
	 */
	public boolean updateJjztForCancel(String xqid) throws Exception {

		return dao.updateJjztForCancel(xqid);
	}

}
