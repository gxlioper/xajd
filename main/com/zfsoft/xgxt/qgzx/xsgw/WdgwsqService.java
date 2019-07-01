/**
 * @部门:学工产品事业部
 * @日期：2013-6-17 上午09:16:17 
 */
package com.zfsoft.xgxt.qgzx.xsgw;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import net.sf.json.JSONObject;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import xsgzgl.qgzx.cssz.QgzxCsszService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 学生岗位-我的岗位申请
 * @作者： zhangjw
 * @时间： 2013-6-17 上午09:09:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WdgwsqService extends SuperServiceImpl<WdgwsqForm, WdgwsqDAO> {

	private WdgwsqDAO dao = new WdgwsqDAO();

	private ShlcInterface shlc = new CommShlcImpl();

	public WdgwsqService() {
		super.setDao(dao);
	}

	@Override
	public WdgwsqForm getModel(WdgwsqForm t) throws Exception {

		return dao.getModel(t);
	}

	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	public boolean submitRecord(String pkValue, String lcid, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(pkValue, lcid, xh,
				"qgzx_xsgwsh.do?method=xsgwshCx",
				"qgzx_wdgwsq.do?method=wdgwsqCx");
		return result;
	}

	/**
	 * @描述:获取岗位列表
	 * @作者：zhangjw
	 * @日期：2013-6-27 下午03:29:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getGwPageList(WdgwsqForm model,
			User user) throws Exception {
		return dao.getGwPageList(model, user);
	}

	
	//==================================================//
	/**
	 * 获取数据
	 */
	public List<HashMap<String, String>> getGwsqPageListStu(WdgwsqForm model,User user) throws Exception {
		return dao.getGwsqPageListStu(model, user);
	}
	/**
	 * 
	 * @描述:TODO查询勤工助学学生表
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-29 下午03:55:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQgzxStuList(WdgwsqForm model, User user)
	throws Exception {
		return dao.getQgzxStuList(model, user);
	}
	
	/**
	 * 
	 * @描述:已申请项目
	 * @作者：张小彬[工号：1036]
	 */
	public List<HashMap<String, String>> getGwsqPageListStuYsq(WdgwsqForm model,
			User user) throws Exception {
		return dao.getGwsqPageListStuYsq(model, user);
	}
	//==================================================//
	public List<HashMap<String,String>> getGwList(WdgwsqForm t,User user) throws Exception {
		return dao.getGwList(t,user);
	}
	public List<HashMap<String,String>> getWdgwsqjlList(WdgwsqForm t,User user) throws Exception {
		return dao.getWdgwsqjlList(t,user);
	}
	public HashMap<String,String> getGwxxByGwdm(String gwdm) throws Exception {
		return dao.getGwxxByGwdm(gwdm);
	}
	public HashMap<String,String> getGwxxByXhAndGwdm(String gwdm,String xh) throws Exception {
		return dao.getGwxxByXhAndGwdm(gwdm,xh);
	}
	public List<HashMap<String,String>> getGzmxList(String gwdm,String xh){
		return dao.getGzmxList(gwdm,xh);
	}
	public List<HashMap<String,String>> getWdGwList(WdgwsqForm t,User user) throws Exception {
		return dao.getWdGwList(t,user);
	}
	/**
	 * @描述:TODO提交申请 保存申请单 并提交审批流
	 * @作者：zhangjw
	 * @日期：2013-6-17 下午06:25:22
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean saveSq(WdgwsqForm model) throws Exception {
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		
		//判断学号和岗位ID是否存在，如果存在就不继续进行。备注：勤工存在退岗问题，这里咱不考虑，后续尽快重构
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		
		// 获取审批流程
//		String splc = dao.getShlcID();
		String dwlb = dao.getYrdwlb(model.getGwdm());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//校内单位
			model.setSplc(splcMap.get("xsgwsqsplc"));
		} else {//校外单位
			model.setSplc(splcMap.get("xwxsgwsqsplc"));
		}

		boolean insertResult = super.runInsert(model);
		return insertResult;
	}
	//判断是否是校内岗位
	public boolean isXndw(String gwdm){
		String dwlb = dao.getYrdwlb(gwdm);
		return "01".equals(dwlb);//01校内，02校外
	}
	public boolean update(WdgwsqForm model) throws Exception {
		
		//判断学号和岗位ID是否存在，如果存在就不继续进行。备注：勤工存在退岗问题，这里咱不考虑，后续尽快重构
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		// 获取审批流程
		String dwlb = dao.getYrdwlb(model.getGwdm());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//校内单位
			model.setSplc(splcMap.get("xsgwsqsplc"));
		} else {//校外单位
			model.setSplc(splcMap.get("xwxsgwsqsplc"));
		}
		//ShlcDao shlcDao = new ShlcDao();
		//model.setShgw(shlcDao.getFirstGwid(splc));
		model.setShgw("");
		boolean insertResult = super.runUpdate(model);
		if (insertResult && Constants.YW_SHZ.equalsIgnoreCase(model.getShzt())) {
			insertResult = shlc.runSubmit(model.getSqbh(), model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xsgwshCx",
					"qgzx_wdgwsq.do?method=wdgwsqCx");
		}
		return insertResult;
	}

	public String saveSq(WdgwsqForm model, String type) throws Exception {
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		model.setSqsj(sdf.format(now));
		model.setShzt(Constants.YW_WTJ);

		String dwlb = dao.getYrdwlb(model.getGwdm());
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		if("01".equals(dwlb)){//校内单位
			model.setSplc(splcMap.get("xsgwsqsplc"));
		} else {//校外单位
			model.setSplc(splcMap.get("xwxsgwsqsplc"));
		}
		// 有审批流的情况设定初始值
		if (model.getSplc() != null && !"".equals(model.getSplc())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		}
		boolean insertResult=false;
		String guid = UniqID.getInstance().getUniqIDHash();
		if(!StringUtil.isNull(model.getSqbh())){
			guid=model.getSqbh();
			insertResult = super.runUpdate(model);
		}else{
			model.setSqbh(guid);
			insertResult = super.runInsert(model);
		}

		if (insertResult) {
			insertResult = shlc.runSubmit(guid, model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xsgwshCx",
					"qgzx_wdgwsq.do?method=wdgwsqCx");

			//start定时任务
			//学生岗位申请成功后需在1小时内进行面试，
			//面试完老师进行岗位审核通过后，该学生正式上岗，
			//如超过1小时则该岗位自动被释放
			/*if(!TASK_MAP.containsKey(model.getSqbh())){
				Future f = schedule.startTask(new XsgwsqTimeOutTask(model));
				TASK_MAP.put(model.getSqbh(),f);
			}*/
		}
		return String.valueOf(insertResult);
	}

	public boolean saveLzSq(WdgwsqForm model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_SHZ);
		HashMap<String,String> splcMap = new QgzxCsszService().getSplc();
		model.setSplc(splcMap.get("xslzsplc"));
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		boolean flag = dao.insertLzxx(model);
		if(flag){
			flag = shlc.runSubmit(guid, model.getSplc(), model.getXh(),
					"qgzx_xsgwsh.do?method=xslzshCx",
					"qgzx_wdgwsq.do?method=wdqggwCx");
		}
		return flag;
	}
	public boolean updateModel(WdgwsqForm model) throws Exception {
		return super.runUpdate(model);
	}

	/**
	 * @描述:TODO基本参数研制
	 * @作者：zhangjw
	 * @日期：2013-6-19 下午04:07:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return JSONObject 返回类型
	 */
	public JSONObject yzcssz(WdgwsqForm model) throws Exception {
		Map<String, String> resultmap = new HashMap<String, String>();
		HashMap<String, String> map = dao.getCsszb();
		
		//岗位信息
		HashMap<String, String> gwMap = dao.getGwInfo(model.getGwdm());
		String message;
		try {
			String sfkfxsgwsq = map.get("sfkfxsgwsq");

			String xsgwsqsplc = map.get("xsgwsqsplc");
			String xsxsgws = map.get("xsxsgws");
			String xsgws = map.get("xsgws");
			String gwxqrs = StringUtils.isNull(gwMap.get("xqrs")) ? "0" : gwMap.get("xqrs");//岗位招聘人数

			// 获取学生已申请个数
			int sqCount = dao.getSqCount2(model.getXh(),gwMap.get("xn"));
			//获取该岗位学生申请数
			int xsCount = dao.getXsCount(model.getGwdm(),gwMap.get("xn"));
			// 获取学生已在岗岗位数量
			int xszgsl = dao.getXszgsl(model.getXh());
			// 学生在岗的岗位是否有正式岗（岗位性质只有0 和 1）一个学生只能有一个正式岗（1），多个临时岗（0）
			//list的size最大只能是2
			List<HashMap<String,String>> list = dao.getZgGwxxz(model.getXh());

			//学生是否被企业拉黑
			boolean isHmdXs = dao.isHmdXs(model.getXh(),model.getGwdm());

			if (sfkfxsgwsq == null || !"on".equals(sfkfxsgwsq)) {// 申请已经开放
				message = "学生岗位申请没有开放";
			} else if (isHmdXs) {
				message = "您已被改单位拉黑，不能申请该单位的岗位！";
			}  else if (sqCount >= Integer.parseInt(xsxsgws)
					&& !"0".equals(xsxsgws)) {// 学生最大申请岗位数,0无限制申请数
				message = "一个学生最多申请" + xsxsgws + "个岗位！";
			} else if (xszgsl >= Integer.parseInt(xsgws)
					&& !"0".equals(xsgws)) {// 学生最大获得岗位数，0无限制获得数
				message = "一个学生最多可获得" + xsgws + "个岗位,而该学生已拥有" + xsgws + "个岗位！";
			} else if (xsgwsqsplc == null || "".equals(xsgwsqsplc)) {
				message = "还没有定义审批流程不能申请";
			} else if(!"0".equals(gwxqrs) && xsCount >= Integer.parseInt(gwxqrs)){
				message = "改岗位申请已满，请申请其他岗位";
			} else if(!CollectionUtils.isEmpty(list)
					&& "1".equals(list.get(0).get("gwxzdm"))
					&& "1".equals(gwMap.get("gwxzdm"))){
				message = "一个学生只能有一个正式岗位，请申请其他临时岗位";
			} else {
				message = "true";
			}
		} catch (Exception e) {
			message = "学生岗位申请未开通";
		}
		resultmap.put("message", message);
		return JSONObject.fromObject(resultmap);
	}

	/**
	 * @描述:验证申请的岗位是否已经达到上限
	 * @作者：zhangjw
	 * @日期：2013-7-1 上午11:00:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @return
	 * @throws SQLException
	 *             String 返回类型
	 */
	public String yzgwxx(String gwdm, String xh) throws Exception {
		String message = "";
		HashMap<String, String> gwxx = dao.getGwxx(gwdm);
		String xqrs = gwxx.get("xqrs");// 需求总人数
//		String knsrs = gwxx.get("knsrs");// 需求困难生数
		String gwrs = gwxx.get("gwrs");// 在岗总人数
		String zgknss = gwxx.get("zgknss");// 在岗困难生 数
//		int xqfkns = Integer.parseInt(xqrs) - Integer.parseInt(knsrs);
		int zgfkns = Integer.parseInt(gwrs) - Integer.parseInt(zgknss);

		int iskns = dao.isKns(gwdm, xh);
/*		if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs)) {
			message = "岗位人员已满";
		} else */
		/*if (xqfkns - zgfkns <= 0 && iskns == 0){
			message = "该岗位的非困难生人数已达到上限，请申请其他岗位。"; 
		}else */if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs) && iskns != 0) {
			message = "岗位人数已达到上限，请申请其他岗位。";
		} else {
			message = "true";
		}
		return message;
	}

	/**
	 * 
	 * @描述: 验证控制级别
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-4 下午01:54:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @param yzgw
	 * @param user
	 * @return
	 * @throws SQLException
	 *             String 返回类型
	 */
	public String yzjb(String xh,String splc,String gwdm, String yzgw, String shgw,boolean isTj)
			throws SQLException {
		String message = "true";
		// 当前用户非验证岗位 则不进行验证。 如果是提交使用则进行验证
		if (isCheck(splc, yzgw, shgw)||isTj) {
			HashMap<String, String> gwxx = dao.getGwxx(gwdm);
			String sfsgwsqsxz = gwxx.get("sfsgwsqsxz");
			String xqrs = gwxx.get("xqrs");// 需求总人数
			Integer gwshtgrsI=dao.getGwShtgRs(splc,shgw,gwdm);//岗位审核通过人数
			if(gwshtgrsI>=Integer.parseInt(xqrs)&&!"0".equals(xqrs)){
				message = "已超过当前审核岗位需求人数！";
			}else{
				HashMap<String, String> map = getCsszb();
				String xsgws = map.get("xsgws");//学生可获得岗位数
				Integer xshqGws=dao.getTgRs(splc,xh,shgw);//学生获取岗位数
				if ("1".equals(sfsgwsqsxz) && xshqGws >= Integer.parseInt(xsgws)&&!"0".equals(xsgws)) {
					message = "此学生已经有" + xshqGws + "个岗位，超过学生最大岗位数";
				}
			}
		}
		return message;
	}
	/**
	 * 
	 * @描述:是否需要验证
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-12 下午05:04:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splc
	 * @param yzgw
	 * @param shgw
	 * @return boolean 返回类型
	 */
	public boolean isCheck(String splc, String yzgw, String shgw) {
		if (StringUtils.isNull(splc) || StringUtils.isNull(yzgw)
				|| StringUtils.isNull(shgw)) {
			return false;
		}
		//新审核流程 审核岗位更改为 -1 时 为退回给申请人
		if("-1".equals(shgw)){
			return false;
		}
		String yzxh = dao.getGwSpXh(splc, yzgw);

		String shxh = dao.getGwSpXh(splc, shgw);
		if (Integer.parseInt(yzxh) <= Integer.parseInt(shxh)) {
			return true;
		}
		return false;
	}

	/**
	 * @描述:TODO获取审批流程ID
	 * @作者：zhangjw
	 * @日期：2013-6-19 下午04:08:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 */
	public String getShlcID() {
		return dao.getShlcID();
	}
	
	public String getYjShlcID() {
		return dao.getYjShlcID();
	}


	/**
	 * @描述:TODO获取学生在岗数量
	 * @作者：zhangjw
	 * @日期：2013-6-27 上午10:28:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException int 返回类型
	 */
	public int getXszggwsl(WdgwsqForm model) throws SQLException {
		return dao.getXszggwsl(model.getGwdm(), model.getXh());
	}

	/**
	 * @描述:TODO获取学生申请数量
	 * @作者：zhangjw
	 * @日期：2013-6-27 上午10:27:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @param xh
	 * @return
	 * @throws SQLException int 返回类型
	 */
	public int getXssqsl(WdgwsqForm model) throws SQLException {
		return dao.getXssqsl(model.getGwdm(), model.getXh());
	}

	/**
	 * @描述:获取查询设置表
	 * @作者：zhangjw
	 * @日期：2013-6-27 上午11:07:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 *             HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getCsszb(){
		return dao.getCsszb();
	}

	/**
	 * @描述:获取学生在岗总数
	 * @作者：zhangjw
	 * @日期：2013-6-27 上午11:13:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws SQLException int 返回类型
	 */
	public int getXszgsl(WdgwsqForm model) throws SQLException {
		return dao.getXszgsl(model.getXh());
	}

	/**
	 * @描述:根据岗位代码获取岗位需求人数、困难生数，在岗总人数，在岗困难生数
	 * @作者：zhangjw
	 * @日期：2013-7-1 上午10:43:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwdm
	 * @return
	 * @throws SQLException
	 *             HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getGwxx(String gwdm) throws SQLException {
		return dao.getGwxx(gwdm);
	}

	/**
	 * @描述: 学生岗位申请删除
	 * @作者：HongLin
	 * @日期：2014-1-17 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return int
	 * @throws SQLException int 返回类型
	 */
	public int runDelete(String[] values) throws Exception {
		return dao.runDelete(values);
	}
	/**
	 * Word方式打印
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：夏夏[工号：1104
	 * @日期：2014-6-20 上午09:57:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsxx
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws
	 */
	
	public File printForWord(Map<String, Object> data) throws Exception {
//		Map<String, Object> data = new HashMap<String, Object>();
//		if(xsxx == null){
//			xsxx = new HashMap<String, Object>();
//		}
//		//寝室信息
//		data.putAll(xsxx);
		return getWord(data);
	}
	private File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"qgzx/gwbmb_"+Base.xxdm+".xml";
		
		File wordFile = null;
		try{
			ResourceUtils.getFile(templatePath);
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "qgzx", "gwbmb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "qgzx", "gwbmb.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
		}

		return wordFile;
	
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:判断学生是否是勤工助学学生
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-29 下午05:19:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isQgzxStu(String xh) throws Exception{
		return dao.isQgzxStu(xh).size()>0;
	}
	public HashMap<String, String> getQsxx(String xh) throws Exception{
		return dao.getQsxx(xh);
	}
	/**
	 * 
	 * @描述:获取贫困级别
	 * @作者：cq [工号：785]
	 * @日期：2014-9-25 下午02:24:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPkxjb(String xh)throws Exception{
		String pkxx = dao.getPkxjb(xh);
		
		if(null==pkxx||"".equals(pkxx)){
			return "否";
		}else{
			return "是-"+pkxx;
		}
			
	}

	/**
	 * 假期留校-获取住宿信息【专用】
	 */
	public String getQsxxJqlx(String xh) throws Exception{
		HashMap<String, String> qsxx = dao.getQsxx(xh);
		String xqmc = StringUtil.isNull(qsxx.get("xqmc")) ? "" : (qsxx.get("xqmc") + " ");
		String yqmc = StringUtil.isNull(qsxx.get("yqmc")) ? "" : (qsxx.get("yqmc") + " ");
		String qsbh=null;
		if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")||null==qsxx.get("cwh")){
			qsbh="";
		}else{
			qsbh=qsxx.get("ldmc")+" "+qsxx.get("qsh")+"室 "+qsxx.get("cwh")+"床";
		}
		String qsxxStr = xqmc + yqmc + qsbh;
		return qsxxStr;
	}
	
	
	/**
	 * 
	 * @描述:保存勤工明细
	 * @作者：cq [工号：785]
	 * @日期：2015-3-23 下午03:48:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param mxrq
	 * @param mxxmList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQgmx(WdgwsqForm model,String[] mxrq,List<String[]> mxxmList) throws Exception{
		
		List<String[]> params = new ArrayList<String[]>();
		
		for (int i = 0 ; i < mxrq.length ; i++){
			String[] param = new String[]{model.getXh(),mxrq[i],StringUtils.joinArr(mxxmList.get(i)),i+""};
			params.add(param);
		}
		
		dao.delQgmx(model.getXh());
		return dao.saveQgmx(params);
	}

	/** 
	 * @描述:勤工明细
	 * @作者：cq [工号：785]
	 * @日期：2015-3-23 下午04:39:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getQgmxList(String id){
		
		List<HashMap<String,String>> qgmxList = dao.getQgmxList(id);
		
		return qgmxList;
	}
	/** 
	 * 勤工明细(打印表专用)
	 */
	public HashMap<String,String> getQgmxByQgrqQgmx(String[] qgrqArr, String[] qgmxArr, String xh){
		return dao.getQgmxByQgrqQgmx(qgrqArr, qgmxArr, xh);
	}
	
	
	/******copy from mobile*********/
	public List<HashMap<String, String>> getGwWsqPageList(WdgwsqForm model,
			User user) throws Exception {
		return dao.getGwWsqPageList(model, user);
	}
	
	/**
	 * 
	 * @描述:浙江中医药大学报名表打印学生基本信息及岗位信息查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-19 上午10:00:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBmbxx(String sqbh){
		return dao.getBmbxx(sqbh);
	}
	
	/**
	 * 
	 * @描述:浙江中医药勤工报名表打印-空闲明细
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-19 上午11:47:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKxsjMx(String xh){
		return dao.getKxsjMx(xh);
	}
	
	/** 
	 * @描述:获取关联学院描述(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-18 下午01:56:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXyms(WdgwsqForm form){
		return dao.getXyms(form);
	}
	
	/** 
	 * @描述:退岗是否在一年内(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-6-20 上午11:20:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isTgInOneYear(String xh){
		return dao.isTgInOneYear(xh);
	}
	//用人单位类别
	public String getYrdwlb(String gwdm){
		return dao.getYrdwlb(gwdm);
	}
	//获取某学生的勤工历史记录
	public List<HashMap<String,String>> getQglsjl(String xh){
		return dao.getQglsjl(xh);
	}
}
