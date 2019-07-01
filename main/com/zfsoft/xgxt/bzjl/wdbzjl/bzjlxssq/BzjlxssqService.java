/**
 * @部门:学工产品事业部
 * @日期：2013-12-9 上午11:26:03 
 */  
package com.zfsoft.xgxt.bzjl.wdbzjl.bzjlxssq;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.bzjl.wdbzjl.bzjlsqsh.BzjlsqshModel;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:  
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-9 上午11:26:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BzjlxssqService extends SuperServiceImpl<BzjlxssqModel, BzjlxssqDao> {

	private BzjlxssqDao dao = new BzjlxssqDao();

	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public BzjlxssqService() {
		super.setDao(dao);
	}
	
	
	public List<HashMap<String , Object>> getSqXmList(BzjlxssqModel model) throws Exception{
		
		String xh = model.getXh();
		
		String queryType = model.getQueryType();
		
		List<HashMap<String , String>> retData = dao.getPageList(model);
		
		List<HashMap<String,Object>> finalRet = new ArrayList<HashMap<String,Object>>();
		
		if(StringUtils.isEqual("wsq", queryType)){
			TjszService tjszServcie = new TjszService();
			//验证条件
			for (HashMap<String,String> pjxm : retData){
				//返回结果
				HashMap<String,Object> resultMap = new HashMap<String,Object>();			
				resultMap.putAll(pjxm);
				List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(pjxm.get("xmdm"),xh);
				// 校验条件，返回不符合条件的项目名称。
				CheckCondition check = new CheckStudentCondition();
				//校验结果
				List<HashMap<String, String>> results = check.checkCondition(xh, conditions);
				
				String checks = "true";
				if(results != null){
					for (HashMap<String, String> hashMap : results) {
						String val = hashMap.get("result");
						if(StringUtils.isEqual(val, "false")){
							checks = "false";
							break;
						}
					}
				}
				
				resultMap.put("checkable", checks);
				
				resultMap.put("conditionCheckResult", results);
				
				finalRet.add(resultMap);
			}
		}else if(StringUtils.isEqual("ysq", queryType)){
			
			for (HashMap<String,String> pjxm : retData){
				//返回结果
				HashMap<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.putAll(pjxm);
				finalRet.add(resultMap);
			}
		}
		return finalRet;
	}
	
/**
 * 	
 * @描述:TODO(这里用一句话描述这个方法的作用)
 * @作者：张小彬[工号:1036]
 * @日期：2013-12-10 下午05:12:01
 * @修改记录: 修改者名字-修改日期-修改内容
 * @param xmdm
 * @param t
 * @return
 * @throws Exception
 * boolean 返回类型 
 * @throws
 */
	public boolean saveJxsq(String[] xmdm ,BzjlxssqModel t) throws Exception{
		
		
		if (xmdm == null || xmdm.length==0){
			return false;
		}
		
		for (String pjxm : xmdm){
			
			BzjlxssqModel model = new BzjlxssqModel();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setYlzd5(t.getYlzd5()); // 附件id
			model.setXmdm(pjxm);
			model.setTzhxmdm(pjxm);
			saveJxsb(model, t.getXh());
		}
		return true;
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午05:16:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJxsb(BzjlxssqModel model, String userName) throws Exception{
		
		String sqid = UniqID.getInstance().getUniqIDHash();
		
		//项目信息
		XmwhDao xmwhDao = new XmwhDao();
		XmwhModel xmwhModel = xmwhDao.getModel(model.getXmdm());
		String splc = xmwhModel.getShlc();
		
		//参数设置信息
		CsszDao csszDao = new CsszDao();
		CsszModel csszModel = csszDao.getModel();
		
		model.setSqr(userName);
		model.setXn(csszModel.getXn());
		model.setXq(csszModel.getXq());
		model.setSqid(sqid);
		model.setSplc(splc);
		//保存申请信息
		boolean result = dao.runInsert(model);
		
		if (result) {
			ShlcDao shlcDao = new ShlcDao();
			//查询第一级岗位ID
			String firstGwid = shlcDao.getFirstGwid(splc);
			//插入第一级待审记录
			dao.insertDbjd(sqid, firstGwid);
			
			// 待办工作
			Job job = Job.getJobInstance(sqid,
					model.getXh(), firstGwid, "pj_jxsh.do",
					"pj_pjxmsq.do", "评奖评优", xmwhModel.getXmmc());
			MyJobsManager manager = new MyJobsImpl();
			manager.pushJob(job);
		}
		return result;
	}


	/*
	      描述: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public BzjlxssqModel getModel(BzjlxssqModel t) throws Exception {
		// TODO 自动生成方法存根
		return super.getModel(t);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午07:14:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSpjlList(String sqid){
		return dao.getSpjlList(sqid);
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午07:15:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDsgw(String sqid){
		
		if (StringUtil.isNull(sqid)){
			return null;
		}
		
		return dao.getDsgw(sqid);
	}


	/** 
	 * @描述:当前系统时间
	 * @作者：cq [工号：785]
	 * @日期：2014-3-10 下午03:13:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dateFormat
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public String getCurrTime(String dateFormat){
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}


	/** 
	 * @描述:判断学生是否存在参评组中
	 * @作者：cq [工号：785]
	 * @日期：2014-5-16 下午05:16:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csszModel
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExistCpz(CsszModel csszModel, String xh) {
		
		String num = dao.isExistCpz(csszModel, xh);
		
		return Integer.valueOf(num)>0;
	}
	
	
	/**
	 * 
	 * @描述:得到某学生已申请项目的详细信息 (copy from mobile)
	 * @作者：ligl
	 * @日期：2014-3-26 下午04:28:45
	 * @修改记录: 
	 * @param xh
	 * @param xmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getYsqxmDetail(String xh,String xmdm)throws Exception {
		return dao.getYsqxmDetail(xh, xmdm);
	}
	
	
	/**
	 * 
	 * @描述:查询已申请的所有项目(已审核) copy from mobile
	 * @作者：ligl
	 * @日期：2014-3-26 下午03:33:46
	 * @修改记录: 
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAllYsh(BzjlsqshModel model, User user)
			throws Exception {
		
		return dao.getYsqxmAllYsh(model,user);
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
	public List<HashMap<String, String>> getYsqxmAll(BzjlsqshModel model, User user)
			throws Exception {
		
		return dao.getYsqxmAll(model,user);
	}
	
	
	/**
	 * 
	 * @描述: 所有审核级别审核意见汇总
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-16 上午08:36:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmdm) {
		return dao.getAllShyjList(xh, xn, xq, xmdm);
	}
	
	/**
	 * @描述：获取 学年、第1学期、第2学期 绩点
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public HashMap<String,String> getJd_10277(String xh,String xn){
		return dao.getJd_10277(xh, xn);
	}
	
	/**
	 * 
	 * @描述：获取最高和最低文化课成绩
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-29 下午05:03:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public  HashMap<String,String> getMaxOrMinWfkCj(String xh,String xn ,String xq){
		return dao.getMaxOrMinWfkCj(xh, xn, xq);
	}
	
	/**
	 * 
	 * @描述: 返回何时毕业届数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-28 下午02:32:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHsbyjs(String xh){
		return dao.getHsbyjs(xh);
	}
}
