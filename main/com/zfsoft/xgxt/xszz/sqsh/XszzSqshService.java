/**
 * @部门:学工产品事业部
 * @日期：2013-4-25 下午02:27:01 
 */  
package com.zfsoft.xgxt.xszz.sqsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xszz.xmwh.rssz.XmwhRsszDao;
import com.zfsoft.xgxt.xszz.xmwh.tjsz.XmwhTjszService;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhDao;
import com.zfsoft.xgxt.xszz.xmwh.xmwh.XmwhForm;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.xszzbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import java.text.DecimalFormat;
import java.util.*;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版--项目申请审核 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-25 下午02:27:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XszzSqshService extends SuperServiceImpl<XszzSqshForm, XszzSqshDao> implements Constants{
	// 是否班级评议.1：是，0：否
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xszz.sfbjpy"));
	private static final String SQSH = "1";//申请审核
	
	private ShlcInterface shlc = new CommShlcImpl();
	private XszzSqshDao dao = new XszzSqshDao();
	private XmwhDao xmDao=new XmwhDao();
	private ZzxmjgDao zzjgDao = new ZzxmjgDao();
	
	public XszzSqshService(){
		super.setDao(dao);
	}
	
	public List<HashMap<String,String>> getXmsqInfoList(String xh){
		
		return dao.getXmsqInfoList(xh);
	}
	
	public List<HashMap<String , Object>> getXmsqInfoList(XszzSqshForm model) throws Exception{
		if(model == null || model.getXh() == null){
			throw new RuntimeException("学号不能为空");
		}
		
		XmwhTjszService tjszService = new XmwhTjszService();
		
		List<HashMap<String , String>> xmsqInfoList = dao.getXmsqInfoList(model, model.getSqType()); //项目列表
		
		List<HashMap<String,Object>> finalRet = new ArrayList<HashMap<String,Object>>(); //需返回列表
		
		if(StringUtils.isEqual("wsq", model.getSqType())){
			String xh = model.getXh();
			for (HashMap<String, String> xmxx : xmsqInfoList) {
				if(StringUtils.isNull(xmxx.get("shzt")) || 
						StringUtils.isEqual(Constants.YW_YTH, xmxx.get("shzt")) || 
						StringUtils.isEqual(Constants.YW_WTJ, xmxx.get("shzt"))){
					//返回结果
					HashMap<String,Object> resultMap = new HashMap<String,Object>();			
					resultMap.putAll(xmxx);
					String xmdm = (String)resultMap.get("xmdm");
					List<HashMap<String, String>> conditions = tjszService.getTjszGl(xmdm , xh);
					// 校验条件，返回不符合条件的项目名称。
					CheckCondition check = new CheckStudentCondition();
					List<HashMap<String, String>> faileds = check.checkCondition(xh, conditions);
					
					String checks = "true";
					if(faileds != null){
						for (HashMap<String, String> hashMap : faileds) {
							String val = hashMap.get("result");
							if(StringUtils.isEqual(val, "false")){
								checks = "false";
								break;
							}
						}
					}
					resultMap.put("checkable", checks);
					resultMap.put("conditionCheckResult", faileds);
					finalRet.add(resultMap);
				}
			}
		}else if(StringUtils.isEqual("ysq", model.getSqType())){
			for (HashMap<String,String> xmxx : xmsqInfoList){
				if(!(StringUtils.isNull(xmxx.get("shzt")) || StringUtils.isEqual(Constants.YW_YTH, xmxx.get("shzt")) || StringUtils.isEqual(Constants.YW_WTJ, xmxx.get("shzt")))){
					//返回结果
					HashMap<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.putAll(xmxx);
					finalRet.add(resultMap);
				}
			}
		}
		
		return finalRet;
	}
	
	public List<HashMap<String , String>> getAllYsqXmList(XszzSqshForm model) throws Exception{
		List<HashMap<String , String>> xmsqInfoList = dao.getAllXmsqInfoList(model); //项目列表
		
		List<HashMap<String,String>> finalRet = new ArrayList<HashMap<String,String>>(); //需返回列表
		for (HashMap<String,String> xmxx : xmsqInfoList){
			if(StringUtils.isEqual("1", xmxx.get("sfsq"))){
				//返回结果
				HashMap<String,String> resultMap = new HashMap<String,String>();
				resultMap.putAll(xmxx);
				finalRet.add(resultMap);
			}
		}
		return finalRet;
		
	}
	
	public boolean bcxgXmsq(XszzSqshForm model)throws Exception{
		if(model.getType().equals("submit")){
			if(SFBJPY_Y){
				model.setShzt(Constants.YW_BJPYZ);
			}else{
				model.setShzt(Constants.YW_SHZ);
			}
		}else{
			model.setShzt(YW_WTJ);
		}
		
		boolean isSuccess = dao.runUpdate(model);
		if (isSuccess){
			if(!SFBJPY_Y && model.getType().equals("submit")){
				XszzSqshForm sqModel = dao.getModel(model);
				//待办工作
				isSuccess = shlc.runSubmit(sqModel.getGuid(), sqModel.getShlc(), sqModel.getXh(), "xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
			}
		}
		return isSuccess;
	}
	
	/**
	 * @param ylzd1 
	 * 
	 * @描述:保存项目申请 
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午09:58:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param xmdmArray
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXmsq(XszzSqshForm model, String[] xmdmArray, String[] ylzd1) throws Exception{
		
		if (xmdmArray == null || xmdmArray.length == 0){
			logger.error("未选择项目！");
			throw new NullPointerException();
		}
		
		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		if(model.getType().equals("submit")){
			if(SFBJPY_Y){
				model.setShzt(YW_BJPYZ);
			}else{
				model.setShzt(YW_SHZ);
			}
		}else{
			model.setShzt(YW_WTJ);
		}
		
		for (int i = 0 , n = xmdmArray.length ; i < n ; i++){
			model.setXmdm(xmdmArray[i]);
			model.setDqxmdm(xmdmArray[i]);
			//加载项目信息
			XmwhDao xmwhDao = new XmwhDao();
			XmwhForm xmwhModel = new XmwhForm();
			xmwhModel.setXmdm(xmdmArray[i]);
			XmwhForm xmInfo = xmwhDao.getModel(xmwhModel);
			
			if (null != xmInfo && !StringUtil.isNull(xmInfo.getSplc())){
				String shlc = xmInfo.getSplc();
				model.setShlc(shlc);
				model.setXn(Base.currXn);
				model.setXq(Base.currXq);
				model.setPdxn(xmInfo.getPdxn());
				model.setPdxq(xmInfo.getPdxq());
				//学生可申请金额资助项目，设置学生申请金额
				if(!"".equalsIgnoreCase(ylzd1[i])){
					model.setYlzd1(ylzd1[i]);
				}
			}
			//保存申请
			boolean isSuccess = dao.runInsert(model);
			
			if (isSuccess){
				if(!SFBJPY_Y && model.getType().equals("submit")){
					XszzSqshForm sqModel = dao.getModelByXhSqsj(model);
					//插入审核状态
//					dao.insertShzt(sqModel, model.getShlc());
					
					//待办工作
					isSuccess = shlc.runSubmit(sqModel.getGuid(), model.getShlc(), sqModel.getXh(), "xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
	//				XtwhShlcDAO splcDao = new XtwhShlcDAO();
	//				String lcid = model.getShlc();		
	//				String[] spgw = splcDao.getSpgwByLcid(lcid);
	//
	//				if (null != spgw && spgw.length > 0){
	//					Job job = Job.getJobInstance(sqModel.getGuid(), model.getXh(), spgw[0], 
	//					"xszz_sqsh_xmsh.do", 
	//					"xszz_sqsh_xmsq.do","学生资助", xmInfo.getXmmc());
	//					MyJobsManager manager = new MyJobsImpl();
	//					manager.pushJob(job);
	//				}
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * 
	 * @描述:审核查询
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:31:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getShjlList(XszzSqshForm t, User user) throws Exception{
		
		return dao.getShjlList(t, user);
	}
	
	
	
	/**
	 * 
	 * @描述:保存项目审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午11:06:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXmsh(XszzSqshForm t,User user) throws Exception{
		
		
		//判断流程序号
		HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(t.getGuid(), user.getUserName(), t.getShlc(), "sh");
		//审核前一步骤的项目代码
		String tzhxmdm = "";
		String rskzxh = dao.getRskzXh(t.getShxmdm());

		//人数控制
		if (TG.equals(t.getShjg()) && !StringUtil.isNull(shxx.get("xh")) && new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
			tzhxmdm=t.getShxmdm();
			checkRskz(t, "sh");
		}
	
		XmwhForm xmForm = new XmwhForm();
		xmForm.setXmdm(t.getShxmdm());
		xmForm = xmDao.getModel(xmForm);
		
		//金额控制
		if (TG.equals(t.getShjg()) && !StringUtil.isNull(xmForm.getJe()) 
				&& Double.valueOf(xmForm.getJe()) > 0 && !StringUtil.isNull(shxx.get("xh"))
				&& new Integer(shxx.get("xh")).intValue()>=new Integer(rskzxh).intValue()){
			//查询学生所在院系、该项目组已经使用的金额
			//学生已获得总金额
			Double ytgJe = Double.valueOf(dao.getYtgJe(t.getXh(), t.getShxmdm(), t.getXtgwid()));
			String xyjes = dao.getXmzJe(t.getXh(), t.getShxmdm());
			if(!"".equals(xyjes)){

				//学院设定金额
				Double xyJe = Double.valueOf(dao.getXmzJe(t.getXh(), t.getShxmdm()));
				Double shje;
				//1:项目金额可变
				if("1".equalsIgnoreCase(xmForm.getJesfxssq())){
					shje = Double.valueOf(t.getShxmje());
				} else {
					shje = Double.valueOf(xmForm.getJe());
				}
				if ((ytgJe+shje) > xyJe){
					throw new SystemException(MessageKey.XSZZ_JEKZ_FAIL, xyJe);
				}
			}
		}

		ShxxModel model = new ShxxModel();
		model.setYwid(t.getGuid());
		model.setShlc(t.getShlc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getXtgwid());
		model.setSqrid(t.getXh());
		model.setTzlj("xszz_sqsh_xmsh.do");
		model.setTzljsq("xszz_sqsh_xmsq_stu.do");

		if(t.getShjg().equals("1")){
			// 設定业务字段1[业务名称]
			model.setZd1("调整资助项目");
			// 設定业务字段2[业务ID]
			model.setZd2(t.getShxmdm());
			// 設定业务字段3
			model.setZd3(xmForm.getXmmc());
		}
		//如果项目金额可以，将老师填写的金额插入到审核表的业务字段中
		if("1".equalsIgnoreCase(xmForm.getJesfxssq())){
			model.setZd4("调整金额");
			model.setZd5(t.getShxmje());
			model.setZd6(t.getShxmje());
		}
		boolean result = false;
		
			//审核通过-先判断是否在结果库有该学生的该学年学期的该奖项
			if(TG.equals(t.getShjg())){
				ZzxmjgForm zzjgForm = new ZzxmjgForm();
				XszzSqshForm xmzzSqshForm=new XszzSqshForm();
				xmzzSqshForm=dao.getModel(t.getGuid());
				zzjgForm.setXh(xmzzSqshForm.getXh());
				zzjgForm.setXn(xmzzSqshForm.getXn());
				zzjgForm.setXq(xmzzSqshForm.getXq());
				zzjgForm.setPdxn(xmzzSqshForm.getPdxn());
				zzjgForm.setPdxq(xmzzSqshForm.getPdxq());
				zzjgForm.setSqsj(xmzzSqshForm.getSqsj());
				zzjgForm.setXmmc(xmForm.getXmmc());//设置项目名称
				
				String num = zzjgDao.checkExistForSave(zzjgForm);
				if(Integer.valueOf(num) > 0){
					return false;
				}
			}
						
			String shzt = shlc.runAuditing(model);
			//更新申请表中的审核状态
			XszzSqshForm xmzzSqshForm=new XszzSqshForm();
			xmzzSqshForm.setGuid(t.getGuid());
			xmzzSqshForm.setShzt(shzt);
			xmzzSqshForm.setTzhxmdm(tzhxmdm);
			xmzzSqshForm.setDqxmdm(t.getShxmdm());
			result=dao.runUpdate(xmzzSqshForm);
			// 最后一级审核通过时
			if(result && shzt.equals(Constants.SH_TG)){
				//插入正式表
				
				ZzxmjgForm zzjgForm = new ZzxmjgForm();
				xmzzSqshForm=dao.getModel(t.getGuid());
				
				zzjgForm.setXh(xmzzSqshForm.getXh());
				zzjgForm.setXn(xmzzSqshForm.getXn());
				zzjgForm.setXq(xmzzSqshForm.getXq());
				zzjgForm.setSqsj(xmzzSqshForm.getSqsj());
				zzjgForm.setSqly(xmzzSqshForm.getSqly());
				zzjgForm.setPdxn(xmzzSqshForm.getPdxn());
				zzjgForm.setPdxq(xmzzSqshForm.getPdxq());
				zzjgForm.setLylcywid(xmzzSqshForm.getGuid());
				zzjgForm.setXmmc(xmForm.getXmmc());//设置项目名称
				zzjgForm.setLbdm(xmForm.getLbdm());//设置项目类别
				XsxxService xsxxService = new XsxxService();
				HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xmzzSqshForm.getXh());
				//华中师范大学个性化证书编号
				if("10511".equals(Base.xxdm)){
					ZzxmjgService zzxmjgService = new ZzxmjgService();
					zzjgForm.setNj(xsjbxx.get("nj"));
					zzjgForm.setXmdm(zzxmjgService.getXmdm(zzjgForm));//取项目代码
					zzjgForm.setXydm(xsjbxx.get("xydm"));
					zzjgForm.setZsbm(zzxmjgService.getZsbm(zzjgForm));
				}
				zzjgForm.setSjly(SQSH);//设置数据来源
				
				//结果库金额值设置
				if("1".equalsIgnoreCase(xmForm.getJesfxssq())){
					//项目金额可变:从前台取
					zzjgForm.setJe(t.getShxmje());
				}else{
					//项目金额固定：从项目表取
					zzjgForm.setJe(xmForm.getJe());
				}
				zzjgForm.setYlzd5(t.getYlzd5()); // 附件id
				result=zzjgDao.runInsert(zzjgForm);
			}
			
			
			// 退回 申请人时
			if(result && SFBJPY_Y && shzt.equals(Constants.SH_TH) && "-1".equals(t.getThgw())){
				XszzSqshForm xszzSqshModelOld = new XszzSqshForm();
				xszzSqshModelOld.setGuid(t.getGuid());
				XszzSqshForm xszzSqshModelNew = dao.getModel(xszzSqshModelOld);
				JgcxDao jgcxDao = new JgcxDao();
				// 更新班级评议表
				boolean rs = jgcxDao.cxBjpyxzpy(xszzSqshModelNew.getXn(), xszzSqshModelNew.getXq(), xszzSqshModelNew.getXmdm(), xszzSqshModelNew.getXh());
				if(rs){
					// 更新结果查询表
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(xszzSqshModelNew.getGuid());
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		
		
//		HashMap<String,String> beforeSpxx = dao.getBeforeSpxx(t);
//		
//		//当前审核岗位在第一级并且在做退回操作。（这样的逻辑不被允许！）
//		if (StringUtil.isNull(beforeSpxx.get("sjgw")) && TH.equals(t.getShzt())){
//			return false;
//		}
		
		//如果审核项目代码为空，则默认为上级确认的审核项目
//		if (StringUtil.isNull(t.getShxmdm())){
//			t.setShxmdm(beforeSpxx.get("shxm"));
//		}
		
//		int num = dao.runAuditing(t, user);
//		
//		if (num > 0){
//			String shzt = t.getShzt();
//			String lashShzt = SHZ;;//申请表中的状态
//			
//			Job job = null;
//			
//			if (TG.equals(shzt)){
//				HashMap<String,String> nextSpxx = dao.getNextSpxx(t);
////				//通过 并且 当前审批岗位为最高级做两件事：
////				// ① 数据插入正式表;
////				// ② 申请表中审核状态、困难生档次更改为最终状态
//				if (StringUtil.isNull(nextSpxx.get("xjxh"))){
//					lashShzt  = TG;
////					//插入正式表
//					ZzxmjgDao zzjgDao = new ZzxmjgDao();
//					ZzxmjgForm zzjgForm = new ZzxmjgForm();
//					
//					XszzSqshForm sqshModel = dao.getModel(t);
//					
//					BeanUtils.copyProperties(zzjgForm, sqshModel);
//					
//					zzjgForm.setSjly(SQSH);
//					//最终获得资助项目的名称、金额确定
//					XmwhDao xmwhDao = new XmwhDao();
//					XmwhForm paramModel = new XmwhForm();
//					paramModel.setXmdm(t.getShxmdm());
//					XmwhForm xmwhModel = xmwhDao.getModel(paramModel);
//					zzjgForm.setXmmc(xmwhModel.getXmmc());
//					zzjgForm.setJe(xmwhModel.getJe());
//					zzjgForm.setLbdm(xmwhModel.getLbdm());
//					
//					String jgNum = zzjgDao.checkExistForSave(zzjgForm);
//					if (Integer.valueOf(jgNum) == 0){
//						zzjgDao.runInsert(zzjgForm);
//					}
//				}
//				
//				//要把下一级状态改为"未审核"
//				if (!StringUtil.isNull(nextSpxx.get("xjgw"))){
//					dao.setShzt(t.getGuid(), nextSpxx.get("xjgw"), WSH);
//					
//				}
//				job = Job.getJobInstance(t.getGuid(),nextSpxx.get("xjgw"),
//						"xszz_sqsh_xmsh.do","学生资助");
//			} else if (BTG.equals(shzt)){
////				//不通过，申请表中最终审核状态改为“不通过”
//				lashShzt  = BTG;
//				job = Job.getJobInstance(t.getGuid(),"学生资助");
//			} else if (TH.equals(shzt)){
////				//退回，①申请表中最终状态不变（前提条件：第一级不存在退回操作），即无操作;
////				//②上一级审核状态改为“需重审”
//				dao.setShzt(t.getGuid(), beforeSpxx.get("sjgw"), XCS);
//				job = Job.getJobInstance(t.getGuid(),beforeSpxx.get("sjgw"),
//						"xszz_sqsh_xmsh.do","学生资助");
//			}
////			
//			MyJobsManager manager = new MyJobsImpl();
//			manager.updateJob(job);
//			
////			//申请表中的审核状态、认定档次变更
//			XszzSqshForm model = new XszzSqshForm();
//			model.setGuid(t.getGuid());
//			model.setShzt(lashShzt);
//			dao.runUpdate(model);
//		}
		
		return result;
	}
	
	
	//检测人数控制
	private void checkRskz(XszzSqshForm t, String type) throws Exception{
		
		XmwhDao xmwhDao = new XmwhDao();
		Map<String,String> xmwhMap = xmwhDao.getDataById(t.getShxmdm());
		//人数控制范围/级别/开关
		String rskzfw = xmwhMap.get("rskzfw");
			XmwhRsszDao rsszDao = new XmwhRsszDao();
			//人数控制相关Map
			Map<String,String> rsszMap = rsszDao.getRsszOne(t.getShxmdm(), t.getXh(), rskzfw);
			
			String xzrs = rsszMap.get("zzrs");
			//未设置就不控制
			if (StringUtil.isNull(xzrs)){
				return ;
			}
			
			String tgrs = null;
			
			if (RSKZFW_NJXY.equals(rskzfw)){
				tgrs = dao.getTgrsByNjxy(t, rsszMap);
			} else if (RSKZFW_NJZY.equals(rskzfw)){
				tgrs = dao.getTgrsByNjzy(t, rsszMap);
			} else if (RSKZFW_XY.equals(rskzfw)){
				tgrs = dao.getTgrsByXy(t, rsszMap);
			} else if(RSKZFW_SY.equals(rskzfw)){//按书院统计通过人数
				tgrs = dao.getTgrsBySy(t, rsszMap);
			} else{
				tgrs = dao.getTgrsByBj(t, rsszMap);
			}
			
			if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))){
				if("sh".equals(type)){
					throw new SystemException(MessageKey.RSKZ_FAIL,xzrs);
				}else if("cx".equals(type)){
					throw new SystemException(MessageKey.RSKZ_FAIL_CANCEL,xzrs);
				}

			}
		}

	
	
	
	/**
	 * 
	 * @描述: 查询通过人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-8 下午02:22:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rskzfw
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getShtgrs(String rskzfw,String xmdm,String xn,String xq){
		
		if (StringUtils.isNull(rskzfw)){
			return null;
		}
		if (RSKZFW_NJXY.equals(rskzfw)){
			return dao.getTgrsByNjxy(xmdm, xn, xq);
		} else if (RSKZFW_NJZY.equals(rskzfw)){
			return dao.getTgrsByNjzy(xmdm, xn, xq);
		} else if (RSKZFW_XY.equals(rskzfw)){
			return dao.getTgrsByXy(xmdm, xn, xq);
		} else if(RSKZFW_BJ.equals(rskzfw)){
			return dao.getTgrsByBj(xmdm, xn, xq);
		} else if(RSKZFW_SY.equals(rskzfw)){
			return dao.getTgrsBySy(xmdm, xn, xq);
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @描述:最后一级撤消审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午11:09:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelXmsh(XszzSqshForm t) throws Exception{
		
		XszzSqshForm xszzSqshForm=new XszzSqshForm();
		xszzSqshForm.setGuid(t.getGuid());
		xszzSqshForm.setTzhxmdm("");
		xszzSqshForm.setShzt(YW_SHZ);
		//修改审核表状态
		boolean result=dao.runUpdate(xszzSqshForm);
		if(result){
			//业务回滚
			zzjgDao.delByYwid(t.getGuid());
		}
		return result;
		
//		//该审批记录目前的审核状态
//		String currShzt = dao.getCurrunShzt(t);
//		
//		if (TG.equals(currShzt)){
//			HashMap<String,String> map = dao.getNextSpxx(t);
//			//最后一级，并且当前的状态已经是通过！
//			if (StringUtil.isNull(map.get("xjgw"))){
//				//该申请记录的审批流程已结束
//				throw new SystemException(MessageKey.SYS_CANCEL_END);
//			}
//		}
//		
//		int num = dao.cancelAuditing(t);
//		
//		if (num > 0){
//			HashMap<String,String> beforeShxx = dao.getBeforeSpxx(t);
//			//如果本级为第一级，将申请表中的状态置为“未审核”
//			if (StringUtil.isNull(beforeShxx.get("sjgw"))){
//				XszzSqshForm model = new XszzSqshForm();
//				model.setGuid(t.getGuid());
//				model.setShzt(WSH);
//				dao.runUpdate(model);
//			}
//			
//			//撤消退回操作，将前一级的状态再置为通过
//			if (TH.equals(currShzt) ){
//				dao.setShzt(t.getGuid(), beforeShxx.get("sjgw"), TG);
//			} 
//			
//			//待办
//			Job job = Job.getJobInstance(t.getGuid(),t.getXtgwid(),
//					"xszz_sqsh_xmsh.do","学生资助");
//			MyJobsManager manager = new MyJobsImpl();
//			manager.updateJob(job);
//		}
//		
//		return num > 0;
	}
	
	
	
	/**
	 * 
	 * @描述:获取申请记录对应的各级审批信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午04:18:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	@Deprecated
	public List<HashMap<String,String>> getSplcInfo(XszzSqshForm model){
		
		if (StringUtil.isNull(model.getGuid())){
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		
		return dao.getSplcInfo(model);
	}

	
	
	//删除 
	public int runDelete(String[] values) throws Exception {
		
		//删除审核记录
		//dao.delShzt(values);
		
		//删除申请记录
		int delNum = dao.delXmsq(values);
		
		//删除相关待办
		String[] ids = dao.getExistsId(values); 
		
		//去除重复
		List<String> valuesList = new ArrayList<String>(Arrays.asList(values));
		valuesList.removeAll(Arrays.asList(ids));
		
		String[] yscId = valuesList.toArray(new String[]{});
		
		if (yscId != null && yscId.length > 0){
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(yscId, "学生资助");
		}
		
		return delNum;
	}
	

	
	/**
	 * @描述:从资助结果删除从申请信息
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午01:55:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delXmsqFromZzjg(String[] values) throws Exception{
		
		if (values == null || values.length ==0){
			return false;
		}
		
		
		int num = dao.delXmsqFromZzjg(values);
		
//		if (num > 0){
//			dao.delXmshFromZzjg(values);
//		}
		
		return num > 0;
	}
	

	
	/**
	 * 
	 * @描述:批量审核
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午04:54:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String savePlsh(XszzSqshForm t,User user) throws Exception{
		
		String[] ids = t.getId();
		String[] gwid = t.getGwid();
		String[] xhs = t.getXhs();
		
		//先判断选择的学生总人数是否超过选择学生申请项目的人数控制
		if(t.getShzt().equals("1")){
			String sqids = "";
			for(int i=0;i<ids.length;i++){
				sqids += "'"+ids[i]+"',";
			}
			if(sqids.length()>0){
				sqids=sqids.substring(0, sqids.length()-1);
			}
			
			List<HashMap<String,String>> results = dao.getXzdrs(sqids);
			StringBuilder sqshXmdmBuilder = new StringBuilder(); // 获取最新项目代码（包括调整后）
			for(int i=0;i<results.size();i++){
				HashMap<String,String> rmap = (HashMap<String,String>) results.get(i);
				if(new Integer(rmap.get("dqjb")).intValue()>=new Integer(rmap.get("kzjb")).intValue()){
					HashMap<String,String> tmap = dao.getKzrsTgrsByXmdm(rmap.get("xmdm"),rmap.get("dqjb"),rmap.get("cpbm"),rmap.get("rskzfw"));
					if(tmap!=null&&tmap.size()>0){
						if(new Integer(rmap.get("xzrs")).intValue()>new Integer(tmap.get("zzme")).intValue()-new Integer(tmap.get("ytggs")).intValue()){
							return MessageUtil.getText(MessageKey.SYS_AUD_PERS_OUT);
						}
					}
				}
				String sqshXmdm = rmap.get("xmdm");
				if(sqshXmdmBuilder.indexOf(sqshXmdm) == -1){
					sqshXmdmBuilder.append("'").append(sqshXmdm).append("',");
				}
			}
			String sqshXmdms = "";
			if(sqshXmdmBuilder.length()>0){
				sqshXmdms = sqshXmdmBuilder.substring(0, sqshXmdmBuilder.length()-1);
			}
			// 判断学生申请总金额是否超过隶属学院的总金额
			 boolean jeFlag = dao.checkPlshJe(sqids, sqshXmdms);
			 if(!jeFlag){
				 return MessageUtil.getText(MessageKey.XSZZ_JEKZ_TEXT_FAIL);
			 }
		}
		List<String> failXhs = new ArrayList<String>();
		
		for (int i = 0 , n = ids.length ; i < n ; i++){
			XszzSqshForm model = new XszzSqshForm();
			model.setGuid(ids[i]);
			
			
			HashMap<String, String> tmpMap = shlc.getShxxByCondition(ids[i],gwid[i]);
			XszzSqshForm x = dao.getModel(model);
			if(tmpMap.get("xjzd2")==null||tmpMap.get("xjzd2").equals("")){
				model.setShxmdm(x.getDqxmdm());
			}else{
				model.setShxmdm(tmpMap.get("xjzd2"));
			}
			//浙大个性化判断，批量审核加上调整金额字段
			if(t.getShxmje()==null||t.getShxmje().equals("")){
			if(tmpMap.get("xjzd5")==null||tmpMap.get("xjzd5").equals("")){
				XmwhDao xmdao = new XmwhDao();
				if(x.getYlzd1()==null||x.getYlzd1().equals("")){
					XmwhForm xm = new XmwhForm();
					xm.setXmdm(x.getXmdm());
					xm = xmdao.getModel(xm);
					model.setShxmje(xm.getJe());
				}else{
					model.setShxmje(x.getYlzd1());
				}
			}else{
				model.setShxmje(tmpMap.get("xjzd5"));
				}
			}else{
				model.setShxmje(t.getShxmje());
			}
			
			model.setXtgwid(gwid[i]);
			model.setShlc(tmpMap.get("lcid"));
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);
			model.setYlzd5(x.getYlzd5()); // 附件id
			
			boolean isSuccess = saveXmsh(model, user);
			
			if (!isSuccess){
				failXhs.add(xhs[i]);
			}
		}
		
		JSONArray json = JSONArray.fromObject(failXhs); 
		
		if (failXhs.isEmpty()){
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (TH.equals(t.getShzt())){
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json.toString());
		}
	}
	

	
	/**
	 * 
	 * @描述:判断是否存在审核流程中的数据
	 * @作者：ligl
	 * @日期：2013-5-10 上午09:24:40
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistShlcData(String xmdm) throws Exception{
		int count = dao.getRsszCount(xmdm);
		return count > 0;
	}

	
	
	/**
	 * 
	 * @描述: 审核情况统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 上午09:58:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,Object>> 返回类型 
	 */
	public Map<String,Object> getShqkInfo(User user,String xmdm){
		
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		//申请总人数
		int zrs = Integer.valueOf(dao.getSqrs(user, xn, xq,xmdm));
		//各级审核情况
		List<HashMap<String,String>> shqkInfoList = dao.getShqkTjxx(user, xn, xq,xmdm);
		//申请人数、通过人数、通过率、各级审核统计情况
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("zrs", zrs);
		
		List<HashMap<String,String>> shqkList = new ArrayList<HashMap<String,String>>();
		
		if (zrs == 0){
			resultMap.put("zztgrs","0");
			resultMap.put("zztgl", "0%");
		}
		
		for (int i = 0 , n = shqkInfoList.size() ; i < n ; i++){
			
			HashMap<String,String> map = new HashMap<String, String>();
			map.putAll(shqkInfoList.get(i));
			
//			double tgrs = Double.valueOf(shqkInfoList.get(i).get("tg"));
//			double bgtrs = Double.valueOf(shqkInfoList.get(i).get("btg"));
//			double dshrs = Double.valueOf(shqkInfoList.get(i).get("dsh"));
			DecimalFormat formater = new DecimalFormat("#.##%");
			
//			if (tgrs+bgtrs+dshrs == 0){
//				map.put("tgl",  "0%");
//				map.put("btgl", "0%");
//				map.put("dshl", "0%");
//			} else {
//				double tgl = tgrs/(tgrs+bgtrs+dshrs);
//				double btgl =bgtrs/(tgrs+bgtrs+dshrs);
//				double dshl = dshrs/(tgrs+bgtrs+dshrs);
//				
//				map.put("tgl", formater.format(tgl));
//				map.put("btgl", formater.format(btgl));
//				map.put("dshl", formater.format(dshl));
//			}
			
			double dcls = Double.valueOf(shqkInfoList.get(i).get("dcl"));
			double ycls = Double.valueOf(shqkInfoList.get(i).get("ycl"));
			
			if (dcls+ycls == 0){
			map.put("dcll",  "0%");
			map.put("ycll", "0%");
		} else {
			double dcll = dcls/(dcls+ycls);
			double ycll = ycls/(dcls+ycls);
			
			map.put("dcll", formater.format(dcll));
			map.put("ycll", formater.format(ycll));
		}			
			
			
			shqkList.add(map);
			
//			if (i == n-1){
//				//最终通过率
//				double zztgl = tgrs/zrs;
//				resultMap.put("zztgrs",shqkInfoList.get(i).get("tg"));
//				resultMap.put("zztgl", formater.format(zztgl));
//			}
		}
		
		resultMap.put("shqkList", shqkList);
		return resultMap;
	}
	
	
	
	/**
	 *  
	 * @描述: 审核统计学生列表
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-8 下午02:29:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStudentsList(XszzSqshForm model,User user) throws Exception{
		
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		model.setXn(xn);
		model.setXq(xq);
		
		return dao.getStudentsFromShtj(model, user); 
	}

	/** 
	 * @描述:提交
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-4 下午05:21:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param lcid
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid,xh,"xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
	}
	/**
	 * 
	 * @描述:撤销
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-4 下午05:53:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	public boolean updateModel(XszzSqshForm model) throws Exception {
		return super.runUpdate(model);
	}

	/**
	 * @描述:审核撤销
	 * @作者：cmj
	 * @日期：2013-12-16 下午02:49:46
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
	public String cxshnew(String ywid, XszzSqshForm model, User user) throws Exception{
		ShlcInterface service = new CommShlcImpl();
		
		//判断流程序号
		HashMap<String,String> shxx =ShlcDao.getDqjbByCondition(ywid, user.getUserName(), model.getShlc(), "cx");
		
		//审核前一步骤的项目代码
		String tzhxmdm = shxx.get("sjxmdm");
		String rskzxh = dao.getRskzXh(tzhxmdm);
		String shzt = Constants.YW_SHZ;
		String dqxmdm =null;
		if(null==shxx.get("sjxmdm")||"".equals(shxx.get("sjxmdm"))){
			 dqxmdm = dao.getXmdm(ywid);
		}else{
		     dqxmdm = shxx.get("sjxmdm"); 
		}
		
		//如果当前记录是审核不通过，并且审核级别大于等于控制级别
		int shxxXh = new Integer(shxx.get("xh"));
		if(Constants.SH_BTG.equals(shxx.get("shzt")) && shxxXh > 1){
			String spgw = new ShlcDao().getBeforeGwid(shxx.get("splc"), shxx.get("gwid"));
			model.setXtgwid(spgw);
			if(StringUtil.isNull(rskzxh)){
				model.setShxmdm(dqxmdm);
				checkRskz(model, "cx");
			}else{ 
				if(shxxXh>=new Integer(rskzxh).intValue()){
					model.setShxmdm(tzhxmdm);
					checkRskz(model, "cx");
				}
			}
		}
		
//		if(new Integer(shxx.get("xh")).intValue()<=new Integer(rskzxh).intValue()){
//			tzhxmdm = "";
//		}
	
		String cancelFlag= service.runCancelNew(user.getUserName(), model.getShid(), model.getShlc());
		//回滚申请表当中的调整后项目
		dao.updateSqjl(ywid, tzhxmdm, shzt,dqxmdm);
		return cancelFlag;

	}
	
	/**
	 * 
	 * @描述:返回在资助结果中某个项目的申请成功记录数
	 * @作者：王志刚
	 * @日期：2014-5-4 下午03:16:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc 项目名称
	 * @param xh 学号
	 * @return String 返回类型
	 * @throws
	 */
	public String getTheSameZzxmNumber(String xmmc, String xh) {
		return dao.getTheSameZzxmNumber(xmmc,xh);
	}
	
	/**
	 * 
	 * @描述:大学期间同时享受其他奖(助)学金
	 * @作者：王志刚
	 * @日期：2014-5-4 下午03:57:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc 项目名称
	 * @param xh 学号
	 * @return 
	 * @throws
	 */
	public List<HashMap<String,String>> getYwqtjxList(String xmmc, String xh){
		return dao.getYwqtjxList(xmmc, xh);
	}
	
	/**
	 * 
	 * @描述:返回班级人数
	 * @作者：王志刚
	 * @日期：2014-5-29 下午06:57:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh 学号
	 * @return int 返回类型
	 * @throws
	 */
	public String countBjRs(String xh) {
		return dao.countBjRs(xh);
	}
	
	//审核导出不分页调用方法
	public List<HashMap<String,String>> getAllListSh(XszzSqshForm t, User user) throws Exception {
		
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getShjlList(t, user);
	}
	
	
	
	/**
	 * 
	 * @描述:查询已申请的所有项目
	 * @作者：ligl
	 * @日期：2014-3-26 下午03:33:46
	 * @修改记录: 
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAll(XszzSqshForm model,User user)
			throws Exception {
		
		return dao.getYsqxmAll(model,user);
	}	
	
	/**
	 * 
	 * @描述:查询已申请的所有项目(已审核)
	 * @作者：ligl
	 * @日期：2014-3-26 下午03:33:46
	 * @修改记录: 
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAllYsh(XszzSqshForm model,User user)
			throws Exception {
		
		return dao.getYsqxmAllYsh(model,user);
	}

	/** 
	 * @描述:查询未申请项目
	 * @作者：cq [工号：785]
	 * @日期：2015-10-8 上午10:10:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWsqList(String xh) {
		return dao.getWsqList(xh);
	}

	/** 
	 * @描述:根据guid 查询jgsqzq
	 * @作者：cq [工号：785]
	 * @日期：2015-10-9 下午05:28:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getJgsqzq(String values) {
		return dao.getJgsqzq(values);
	}
	
	
	/**
	 * 
	 * @描述:返回不可提交的项目
	 * @作者：cq [工号：785]
	 * @日期：2015-10-10 上午10:57:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getFalseXmdm(String values){
		return dao.getFalseXmdm(values);
	}

	/**
	 * 
	 * @描述:获取项目类别
	 * @作者：Huang Chenji
	 * @日期：2015-10-27 下午14:40:33
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		return dao.getXmlb();
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-12-8 下午12:25:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getGyxx(String xh) {
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getGyxx(xh);
		} catch (Exception e) {
			logger.error("select to_char(to_date(a.csrq, 'yyyy-mm-dd')) from view_xg_gygl_new_cwxx a where xh ='"+xh+"'");
			throw new NullPointerException();
		}
	}
	
	/**
	 * @描述: 老师审核通过的最终金额
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-11-25 上午11:36:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String zzxmshtgJe(String xh, String xn, String xq) {
		return dao.zzxmshtgJe(xh,xn,xq);
	}

	/**
	 *  异常数据处理.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-17 17:53
	 * @param
	 * @return java.util.Map<java.lang.String,java.lang.String>
	 * @throw Exception
	 */
	public Map<String,String> exceptionDataResolve() throws Exception {

		boolean success =true;
		String message = "处理成功！";
		Map resultMap = new HashMap<String,Object>();
		List<HashMap<String,String>> exceptionDataList = dao.getExceptionDataList();
		if(exceptionDataList.size() != 0){
			for(HashMap<String,String> edMap:exceptionDataList){
				success = shlc.runSubmit(edMap.get("guid"), edMap.get("shlc"), edMap.get("xh"), "xszz_sqsh_xmsh.do", "xszz_sqsh_xmsq_stu.do");
				if(!success){
					message = "处理失败！";
				}
			}
		}else{
			message = "无异常数据！";
		}
		resultMap.put("success",success);
		resultMap.put("message",message);
		return resultMap;
	}
	/**
	 * 
	 * @描述:获取学生助学贷款信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:51:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @deprecated
	 * @throws
	 */
    public  String getXsDkxx(String xn,String xh){
    	return dao.getXsDkxx(xn,xh);
    }

	/**
	 * @描述:河北建材国家励志奖学金成绩为优秀的课程数
	 * @作者：lgx [工号：1553]
	 * @日期：2018/9/4 17:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.HashMap
	 */
	public String getYxksByXh_12389(String xh,String xn,String kcxz) {
		return dao.getYxksByXh_12389(xh,xn,kcxz);
	}
	/**
	 * @描述:河北建材国家励志奖学金成绩为良的课程数
	 * @作者：lgx [工号：1553]
	 * @日期：2018/9/4 17:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.HashMap
	 */
	public String getLkcsByXh_12389(String xh,String xn,String kcxz) {
		return dao.getLkcsByXh_12389(xh,xn,kcxz);
	}

	public String getCjpmXy(String xn, String xh, String zydm) {
		return dao.getCjpmXy(xn,xh,zydm);
	}

	public String getZyzrs(String zydm) {
		return dao.getZyzrs(zydm);
	}

    public List<HashMap<String,String>> getSqExportList(XszzSqshForm myForm, User user) throws Exception {
		return dao.getSqExportList(myForm,user);
    }

	public List<HashMap<String,String>> getShExportList(XszzSqshForm myForm, User user) throws Exception {
		return dao.getShExportList(myForm,user);
	}
}

