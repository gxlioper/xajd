package com.zfsoft.xgxt.xszz.knsrd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ResourceUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.szdw.xsgbgl.gbdw.DwwhService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcForm;
import com.zfsoft.xgxt.xszz.jtqkdc.JtqkdcService;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcDao;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcForm;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import common.Globals;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 2013版学生资助--困难生认定
 * @作者： Penghui.Qu
 * @时间： 2013-4-22 上午08:51:11
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdService extends SuperServiceImpl<KnsrdForm, KnsrdDao>
		implements Constants {
	
	private static final String SQSH = "1";// 申请审核
	
	private ShlcInterface shlc = new CommShlcImpl();

	private KnsrdDao dao = new KnsrdDao();
	
	private KnsdcDao dcDao = new KnsdcDao();
	
	private KnsjgDao jgDao = new KnsjgDao();

	public KnsrdService() {
		super.setDao(dao);
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @描述:以word方式打印
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-18 下午04:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsxx 学生信息
	 * @param jtqkmodel  家庭信息
	 * @param xxmc  学校名称
	 * @param knsdc	困难生档次
	 * @param rddc rddc
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File printForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdForm model,
			List<HashMap<String, String>> knsdc, String rddc,HttpServletRequest request) throws Exception {
		/*家庭情况调查*/
		JtqkdcService jtqkdcService = new JtqkdcService();
		String xh=xsxx.get("xh");
		// 加载学生家庭基本信息
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xh);
		
		
		//海洋大学家庭基本信息个性化查询
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xh);
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(knsdc == null){
			knsdc = new ArrayList<HashMap<String,String>>();
		}
		if(jtqkmodel == null){
			jtqkmodel = new JtqkdcForm();
		}
		if(rddc == null){
			rddc = "";
		}

		data.putAll(xsxx);
		data.put("xxmc", Base.xxmc);
		data.put("sqrdly", HtmlUtil.xmlZy(model.getSqly()));
		data.put("kndc", knsdc);
		data.put("rddc", rddc);
		//中国美术学院个性化
		if(Base.xxdm.equals("10355")){
			data.put("Jtrjnsrs", model.getJtrjnsr());
		}
		data.put("jtqk", jtqkmodel);// 家庭情况
		HashMap<String, String> knsqMap=getKnsqInfo(model.getGuid());
		String sqsj = knsqMap.get("sqsj");
		if(!StringUtil.isNull(sqsj)){
			knsqMap.put("sqsj",sqsj.substring(0,10));
		}
		data.put("knsqInfo", knsqMap);
		data.put("jtqkInfo", jtqkInfo);//上海海洋大学个性化查询
		
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh,4);
		List<HashMap<String, String>> pjjgList3line = pjjgService.getHjqkByXhMap(xh,3);
		data.put("pjjgList4line", pjjgList4line);
		data.put("pjjgList3line", pjjgList3line);
		List<HashMap<String, String>> cyList4line = jtqkService.getJtcyList(xh,4);
		data.put("cyList4line", cyList4line);
		List<HashMap<String, String>> cyList3line = jtqkService.getJtcyList(xh,3);
		data.put("cyList3line", cyList3line);
		ZzxmjgService zzxmjgService =new ZzxmjgService();
		List<HashMap<String, String>> zzjgList4line = zzxmjgService.getZzjgList(xh,4);
		data.put("zzjgList4line", zzjgList4line);
		data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// 出生年月
		data.put("rxny", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));//入学年月
		//按学号加载成员列表 
		if(Base.xxdm.equals("10264")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyListSh(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (3 - cyList.size()) < 0 ? 0 : (3 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else if(Base.xxdm.equals("90211")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));
			data.put("rxrq", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));
			
		}else if(Base.xxdm.equals("10277")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (4 - cyList.size()) < 0 ? 0 : (4 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			//总计年收入
			if(StringUtil.isNull(jtqkmodel.getYlzd15())){
				Integer znsr = 0;
				for(int i=0;i<cyList.size();i++){
					znsr = znsr + Integer.parseInt(cyList.get(i).get("cynsr"));
				}
				jtqkmodel.setYlzd15(znsr+"");
			}
		}
		
		// ============为困难生档次编号（A,B...），并把选中的档次保存到knsdczmbhstr begin============
		String knsdczmbhstr = "";
		String rddcmc = ""; // 困难档次名称
		for (int i = 0; i < knsdc.size(); i++) {
			HashMap<String, String> knsdcMap = knsdc.get(i);
			String knsdczmbh = String.valueOf((char)(65+i));
			knsdcMap.put("knsdczmbh", knsdczmbh);
			if(knsdcMap.get("dcdm").equals(rddc)){
				knsdczmbhstr = knsdczmbh;
				rddcmc = knsdcMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdczmbhstr);
		data.put("rddcmc", rddcmc);
		// ============为困难生档次编号（A,B...），并把选中的档次保存到knsdczmbhstr end============
		// ============勤工俭学 岗位录用情况 begin============
		XsxxglService xsxxglService = new XsxxglService();
		List<HashMap<String, String>> stuQgzxXsgwxxList = xsxxglService.getStuQgzxXsgwxxList(xsxx.get("xh"));
		HashMap<String, String> stuQgzxXsgwxxMap = new HashMap<String, String>();
		if(stuQgzxXsgwxxList.size() > 0){
			stuQgzxXsgwxxMap = stuQgzxXsgwxxList.get(0);
		}
		data.put("stuQgzxXsgwxxMap", stuQgzxXsgwxxMap);
		// ============勤工俭学 岗位录用情况 end============
		// ============评奖评优  begin============
		// 已获奖学金
		StringBuffer jxjStr = new StringBuffer();
		List<HashMap<String, String>> pjjg =  pjjgService.getPjpyInfoMapForDjb(xsxx.get("xh"));
		for (int i = 0; i < pjjg.size(); i++) {
			HashMap<String, String> pj = pjjg.get(i);
			if(StringUtils.isNotNull(pj.get("xmmc")) && pj.get("xmmc").contains("奖学金")){
				jxjStr.append(pj.get("xn"))
				.append(pj.get("xqmc")).append(" ")
				.append(pj.get("xmmc")).append(" ");
			}
		}
		data.put("jxjStr", jxjStr.toString());
		// ============评奖评优  end============
		// 寝室
		WdgwsqService wdgwsqService = new WdgwsqService();
		HashMap<String, String> qsxx= wdgwsqService.getQsxx(xsxx.get("xh"));
		String qsbh=null;
		if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
			qsbh="";
		}else{
			qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
		}
		data.put("qsbh", qsbh);
		data.put("qsxx", qsxx);
		// 担任职务
		DwwhService dwwhService = new DwwhService();
		data.put("zwmc", dwwhService.getZwForXh(xsxx.get("xh")));
		
		//获得家庭成员关系当中的父亲信息，用于打印
		HashMap<String, String> fqInfo = jtqkService.getfqInfo(xsxx.get("xh"));
		//获得家庭成员管理当中的母亲信息，用于打印
		HashMap<String, String> mqInfo = jtqkService.getmqInfo(xsxx.get("xh"));
		
		if(fqInfo == null){
			fqInfo = new HashMap<String, String>();
		}
		if(mqInfo == null){
			mqInfo = new HashMap<String, String>();
		}
		
		data.put("fqInfo", fqInfo);
		data.put("mqInfo", mqInfo);
		
		//陕西师大
		if("10718".equals(Base.xxdm)) {
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// 出生日期
			ZzxmjgService zzxmjg = new ZzxmjgService(); 
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "否");
		    }else{
		        data.put("zxdk", "是");
		    }
		}
		
		if("10277".equals(Base.xxdm)){
			data.put("knyylist", getKnnyList(knsqMap.get("ylzd5")));
		}
		
		//学生照片
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xsxx.get("xh"));
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//取默认照片
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		
		data.put("photo", photo);
		
		int i=1;
		if(null!=infoList){
		for(HashMap<String,String> sh:infoList){
			data.put("fdysh"+i, sh.get("shzt") == null?"":sh.get("shzt"));
		}}
		List<HashMap<String,String>> shInfoList = ShlcUtil.getShlcInfo(model.getGuid());
		String shsjTemp = "";
		String[] shsjTempArray;
		for(int gg = 0; gg<shInfoList.size();gg++){
			shsjTemp = shInfoList.get(gg).get("shsj");
			if(shsjTemp!=null){
				shsjTemp = shsjTemp.substring(0, shsjTemp.indexOf(" "));
				shsjTempArray = shsjTemp.split("-");
				shInfoList.get(gg).put("shsjnian", shsjTempArray[0]);  //审核时间年
				shInfoList.get(gg).put("shsjyue", shsjTempArray[1]);  //审核时间月
				shInfoList.get(gg).put("shsjri", shsjTempArray[2]);  //审核时间日
			}
		}
		data.put("shInfoList", shInfoList);
		//广西师范申请表增加审核信息
		HashMap<String, String> spxxInfo =getSpxxInfo(model.getGuid());
		if(spxxInfo.isEmpty()){
			spxxInfo=new HashMap<String,String>();
			spxxInfo.put("ejtjdcdm", "");
			spxxInfo.put("sjtjdcdm", "");
		}
		if(StringUtils.isNull(spxxInfo.get("ejtjdcdm"))){
			spxxInfo.put("ejtjdcdm", "");
		}
		if(StringUtils.isNull(spxxInfo.get("sjtjdcdm"))){
			spxxInfo.put("sjtjdcdm", "");
		}
		
		/*江西陶瓷工艺美术职业技术学院因学校的家庭情况调查没有家庭年终收入、家庭人均年收入字段需要单独处理*/
		if(Globals.XXDM_JXTCGYMSZYJSXY.equals(Base.xxdm)){
			HashMap<String, String> jtsrqk = jtqkdcService.getJtsrqkByXh(model.getXh());
			data.put("jtsrqk", jtsrqk);
		}
		
		data.putAll(spxxInfo);
		return getWord(data);
	}
	
	
	/**
	 * @描述: 移动端获取打印File
	 * @作者：陈春雷[工号：1620]
	 * @日期：2018-7-11下午04:00
	 * @throws Exception 
	 * 
	 */
	public File printWordForYdxg(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdForm model,
			List<HashMap<String, String>> knsdc, String rddc,HttpServletRequest request) throws Exception {
		String xh=xsxx.get("xh");
		Map<String, Object> data = new HashMap<String, Object>();
		data.putAll(xsxx);
		if(model.getGuid() != null){
			HashMap<String, String> knsqMap=getKnsqInfo(model.getGuid());
			data.put("knsqInfo", knsqMap);
		} else{
			HashMap<String, String> knsqMap=  new HashMap<String, String>();;
			knsqMap.put("dcmc", model.getDcmcYdxg());
			data.put("knsqInfo", knsqMap);
		}
		// 加载学生家庭基本信息
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xsxx.get("xh"));
		List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
		data.put("cyList", cyList);
		//家庭成员列表空行
		int blankSize = (4 - cyList.size()) < 0 ? 0 : (4 - cyList.size());
		data.put("blankList", jtqkService.getBlankList(blankSize));
		data.put("Jtrjnsrs", model.getJtrjnsr());
		return getWord(data);
	}
	
	
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @描述: 结果库中以word方式打印
	 * @作者：HongLin[工号：707]
	 * @日期：2014-2-18 下午04:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsxx 学生信息
	 * @param jtqkmodel  家庭信息
	 * @param xxmc  学校名称
	 * @param knsdc	困难生档次
	 * @param rddc rddc
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File printjgForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdForm model,
			List<HashMap<String, String>> knsdc, String rddc,HttpServletRequest request) throws Exception {
		/*家庭情况调查*/
		JtqkdcService jtqkdcService = new JtqkdcService();
		String xh=xsxx.get("xh");
		// 加载学生家庭基本信息
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xh);
		//海洋大学家庭基本信息个性化查询
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xh);	
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(knsdc == null){
			knsdc = new ArrayList<HashMap<String,String>>();
		}
		if(jtqkmodel == null){
			jtqkmodel = new JtqkdcForm();
		}
		if(rddc == null){
			rddc = "";
		}
		
		data.putAll(xsxx);
		data.put("xxmc", Base.xxmc);
		data.put("sqrdly", HtmlUtil.xmlZy(model.getSqly()));
		data.put("kndc", knsdc);
		//中国美术学院个性化
		if(Base.xxdm.equals("10355")){
			data.put("Jtrjnsrs", model.getJtrjnsr());
		}
		data.put("jtqk", jtqkmodel);// 家庭情况
		HashMap<String, String> knsqjgMap=getKnsqjgInfo(model.getGuid());
		data.put("knsqInfo", knsqjgMap);
		data.put("jtqkInfo", jtqkInfo);//上海海洋大学个性化查询
		PjjgService pjjgService = new PjjgService();
		List<HashMap<String, String>> pjjgList4line = pjjgService.getHjqkByXhMap(xh,4);
		List<HashMap<String, String>> pjjgList3line = pjjgService.getHjqkByXhMap(xh,3);
		data.put("pjjgList4line", pjjgList4line);
		data.put("pjjgList3line", pjjgList3line);
		List<HashMap<String, String>> cyList4line = jtqkService.getJtcyList(xh,4);
		data.put("cyList4line", cyList4line);
		List<HashMap<String, String>> cyList3line = jtqkService.getJtcyList(xh,3);
		data.put("cyList3line", cyList3line);
		ZzxmjgService zzxmjgService =new ZzxmjgService();
		List<HashMap<String, String>> zzjgList4line = zzxmjgService.getZzjgList(xh,4);
		data.put("zzjgList4line", zzjgList4line);
		data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// 出生日期
		data.put("rxny", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));//入学年月
		//广西师范申请表增加审核信息
		HashMap<String, String> spxxInfo =getSpxxInfo(model.getGuid());
		if(spxxInfo.isEmpty()){
			spxxInfo=new HashMap<String,String>();
			spxxInfo.put("ejtjdcdm", "");
			spxxInfo.put("sjtjdcdm", "");
		}
		data.putAll(spxxInfo);
		data.put("rddc", spxxInfo.get("yjtjdc")==null?rddc:spxxInfo.get("yjtjdc"));
		//按学号加载成员列表 
		if(Base.xxdm.equals("10264")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyListSh(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (3 - cyList.size()) < 0 ? 0 : (3 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else if(Base.xxdm.equals("90211")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));
			data.put("rxrq", DateTranCnDate.fomartDateToCn(xsxx.get("rxrq"),FomartDateType.month));
			
		}else if(Base.xxdm.equals("10277")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (5 - cyList.size()) < 0 ? 0 : (5 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (4 - cyList.size()) < 0 ? 0 : (4 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
		}
		
		// ============为困难生档次编号（A,B...），并把选中的档次保存到knsdczmbhstr begin============
		String knsdczmbhstr = "";
		String rddcmc = ""; // 困难档次名称
		for (int i = 0; i < knsdc.size(); i++) {
			HashMap<String, String> knsdcMap = knsdc.get(i);
			String knsdczmbh = String.valueOf((char)(65+i));
			knsdcMap.put("knsdczmbh", knsdczmbh);
			if(knsdcMap.get("dcdm").equals(rddc)){
				knsdczmbhstr = knsdczmbh;
				rddcmc = knsdcMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdczmbhstr);
		data.put("rddcmc", rddcmc);
		// ============为困难生档次编号（A,B...），并把选中的档次保存到knsdczmbhstr end============
		// ============勤工俭学 岗位录用情况 begin============
		XsxxglService xsxxglService = new XsxxglService();
		List<HashMap<String, String>> stuQgzxXsgwxxList = xsxxglService.getStuQgzxXsgwxxList(xsxx.get("xh"));
		HashMap<String, String> stuQgzxXsgwxxMap = new HashMap<String, String>();
		if(stuQgzxXsgwxxList.size() > 0){
			stuQgzxXsgwxxMap = stuQgzxXsgwxxList.get(0);
		}
		data.put("stuQgzxXsgwxxMap", stuQgzxXsgwxxMap);
		// ============勤工俭学 岗位录用情况 end============
		// ============奖学金  begin============
		// 已获奖学金
		StringBuffer jxjStr = new StringBuffer();
		List<HashMap<String, String>> pjjg =  pjjgService.getHjqkList(xsxx.get("xh"));
		for (int i = 0; i < pjjg.size(); i++) {
			HashMap<String, String> pj = pjjg.get(i);
			//if(StringUtils.isNotNull(pj.get("xmmc")) && pj.get("xmmc").contains("奖学金")){ 
			if(StringUtils.isNotNull(pj.get("xmmc"))){
				jxjStr.append(pj.get("xn"))
				.append(pj.get("xqmc")).append(" ")
				.append(pj.get("xmmc")).append(" ");
			}
		}
		data.put("jxjStr", jxjStr.toString());
		// ============奖学金  end============
		// 寝室
		WdgwsqService wdgwsqService = new WdgwsqService();
		HashMap<String, String> qsxx= wdgwsqService.getQsxx(xsxx.get("xh"));
		String qsbh=null;
		if(null==qsxx.get("ldmc")||null==qsxx.get("qsh")){
			qsbh="";
		}else{
			qsbh=qsxx.get("ldmc")+"-"+qsxx.get("qsh");
		}
		data.put("qsbh", qsbh);
		data.put("qsxx", qsxx);
		// 担任职务
		DwwhService dwwhService = new DwwhService();
		data.put("zwmc", dwwhService.getZwForXh(xsxx.get("xh")));
				
		//获得家庭成员关系当中的父亲信息，用于打印
		HashMap<String, String> fqInfo = jtqkService.getfqInfo(xsxx.get("xh"));
		//获得家庭成员管理当中的母亲信息，用于打印
		HashMap<String, String> mqInfo = jtqkService.getmqInfo(xsxx.get("xh"));
		
		if(fqInfo == null){
			fqInfo = new HashMap<String, String>();
		}
		if(mqInfo == null){
			mqInfo = new HashMap<String, String>();
		}
		
		data.put("fqInfo", fqInfo);
		data.put("mqInfo", mqInfo);
		
		
		//陕西师大
		if("10718".equals(Base.xxdm)) {
			ZzxmjgService zzxmjg = new ZzxmjgService(); 
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqjgInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			data.put("csny", DateTranCnDate.fomartDateToCn(xsxx.get("csrq"),FomartDateType.month));// 出生日期 
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "否");
		    }else{
		        data.put("zxdk", "是");
		    }
		}
		//上海体育学院个性化
		if("10277".equals(Base.xxdm)){
			data.put("knyylist", getKnnyList(knsqjgMap.get("ylzd5")));
		}
		
		/*江西陶瓷工艺美术职业技术学院因学校的家庭情况调查没有家庭年终收入、家庭人均年收入字段需要单独处理*/
		if(Globals.XXDM_JXTCGYMSZYJSXY.equals(Base.xxdm)){
			HashMap<String, String> jtsrqk = jtqkdcService.getJtsrqkByXh(xh);
			data.put("jtsrqk", jtsrqk);
		}
		
		//学生照片
		XsxxService xsxxService = new XsxxService();
		InputStream is = xsxxService.getPhotoStream(xsxx.get("xh"));
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		
		if (StringUtil.isNull(photo)){
			//取默认照片
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		
		if(photo == null){
			photo = "";
		}
		
		data.put("photo", photo);
		
		int i=1;
		if(null!=infoList){
		for(HashMap<String,String> sh:infoList){
			data.put("fdysh"+i, sh.get("shzt") == null?"":sh.get("shzt"));
		}}
		return getWord(data);
	}
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 * @描述:获取文档
	 * @作者：张昌路
	 * @日期：2013-6-18 下午04:13:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @return File 返回类型
	 * @throws
	 */
	private File getWord(Map<String, Object> data) throws FileNotFoundException {
//		File wordFile = FreeMarkerUtil.getWordFile(data,
//				"classpath://templates//xszz", "knsrdsq.xml");
		String templatePath = Constants.TEP_DIR+"xszz/knsrdb_"+Base.xxdm+".xml";
		
		File wordFile = null;
		
		try{
			ResourceUtils.getFile(templatePath);
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(""+data.get("bjmc")+""+data.get("xh")+"["+data.get("xm")+"]"));
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			}
		}catch (Exception e) {
			if("12867".equals(Base.xxdm)){
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdsq.xml", FreeMarkerUtil.getFileName(""+data.get("bjmc")+""+data.get("xh")+"["+data.get("xm")+"]"));
			}else{
				wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			}
		}

		return wordFile;
	
	}

	/**
	 * 
	 * @描述:保存困难生认定申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 上午08:50:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean saveKnssq(KnsrdForm model) throws Exception {
		// 困难生设置相关信息加载
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		model.setXn(jcszModel.getRdxn());
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			model.setXq("on");
		}
		else{
			model.setXq(jcszModel.getRdxq());
		}
		// 先判断当前学年学期该学生有木有申请过困难生
		KnsrdForm knsrdModel = dao.getModel(model, new String[] {
				model.getXh(), model.getXn(), model.getXq() });
		
		//获取申请性质
		if(Globals.XXDM_ZJDX.equals(Base.xxdm)){//浙大
		HashMap<String,String> sqxzMap = dao.getSqxz(model.getYlzd1(),model.getXh(),model.getXn(),model.getXq());
		if(StringUtils.isNull(model.getYlzd1())&&StringUtils.isNotNull(sqxzMap.get("xh"))){
			model.setYlzd9("重新认定");
		}else{
			model.setYlzd9(sqxzMap.get("sqxz"));
		}
		}
		
		if (!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())&&"submit".equalsIgnoreCase(model.getType())&&jcszModel != null && !StringUtil.isNull(jcszModel.getSplc())) {
			// 设置审批流程到申请记录上
			model.setShlc(jcszModel.getSplc());
		}
		
		
		//新增和修改都用这个方法,若审核流程为空，这里重新分配
		if(StringUtil.isNull(model.getShlc())){
			
			model.setShlc(jcszModel.getSplc());
		}
		
		
		// 设置申请时间为当前系统时间,默认审核状态为“未审核”
		model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		
		if(model.getType().equals("submit")){
			model.setShzt(YW_SHZ);
		}else{
			model.setShzt(YW_WTJ);
		}
		
		
		if (null == knsrdModel) {

			boolean isSuccess = dao.runInsert((KnsrdForm) StringUtils.formatBean(model));

			if (isSuccess) {
				if(model.getType().equals("submit")){
					// 获取申请信息
					knsrdModel = dao.getModel(model, new String[] { model.getXh(),model.getXn(), model.getXq() });
	
					if (null != jcszModel
							&& !StringUtil.isNull(jcszModel.getSplc())) {
						// 批量插入审核状态
						//dao.insertShzt(knsrdModel, jcszModel.getSplc());
						isSuccess = shlc.runSubmit(knsrdModel.getGuid(), jcszModel.getSplc(),knsrdModel.getXh(),"xszz_knsrd_sh.do","xszz_knsrd_sq.do");
					}
				}
			}
			return isSuccess;
		} else {
			boolean isSuccess = dao.runUpdate((KnsrdForm) StringUtils.formatBean(model));
			if (isSuccess) {
				if(model.getType().equals("submit")){
					// 获取申请信息
					knsrdModel = dao.getModel(model, new String[] { model.getXh(),model.getXn(), model.getXq() });
	
					if (null != jcszModel
							&& !StringUtil.isNull(jcszModel.getSplc())) {
						// 批量插入审核状态
						//dao.insertShzt(knsrdModel, jcszModel.getSplc());
						isSuccess = shlc.runSubmit(knsrdModel.getGuid(), jcszModel.getSplc(),knsrdModel.getXh(),"xszz_knsrd_sh.do","xszz_knsrd_sq.do");
					}
				}
			}
			return isSuccess;
		}
	}
	
	public boolean updateModel(KnsrdForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid,xh,"xszz_knsrd_sh.do","xszz_knsrd_sq.do");
	}

	// 删除操作
	public int runDelete(String[] values) throws Exception {

		// 删除审核记录
		dao.delShzt(values);

		// 删除申请记录
		int delNum = dao.delKnssq(values);

		// 删除相关待办
		String[] ids = dao.getExistsId(values);

		// 去除重复
		List<String> valuesList = new ArrayList<String>(Arrays.asList(values));
		valuesList.removeAll(Arrays.asList(ids));

		String[] yscId = valuesList.toArray(new String[] {});

		if (yscId != null && yscId.length > 0) {
			MyJobsManager manager = new MyJobsImpl();
			manager.removeJob(yscId, "学生资助");
		}

		return delNum;
	}

	/**
	 * 
	 * @描述:获取审批流程相关信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午01:52:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSplcInfo(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}

		return dao.getSplcInfo(model);
	}

	/**
	 * 
	 * @描述:困难生认定--审核查询
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午04:19:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getShjlList(KnsrdForm t, User user)
			throws Exception {

		return dao.getShjlList(t, user);
	}

	/**
	 * 
	 * @描述:审核操作
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 下午04:11:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	@TransactionControl
	public boolean saveKnssh(KnsrdForm t, User user) throws Exception {

		//HashMap<String, String> beforeSpxx = dao.getBeforeSpxx(t);

//		// 当前审核岗位在第一级并且在做退回操作。（这样的逻辑不被允许！）
//		if (StringUtil.isNull(beforeSpxx.get("sjgw")) && TH.equals(t.getShzt())) {
//			return false;
//		}
		//人数控制
		JcszDao jcszDao=new JcszDao();
		JcszForm jcszForm= jcszDao.getModel();
		if (TG.equals(t.getShjg())&&OPEN.equals(jcszForm.getRssfkz())){
			checkRskz(t);
		}
		ShxxModel model = new ShxxModel();
		model.setYwid(t.getGuid());
		model.setShlc(jcszForm.getSplc());
		model.setShr(user.getUserName());
		model.setShyj(t.getShyj());
		model.setShzt(t.getShjg());
		model.setThgw(t.getThgw());
		model.setGwid(t.getXtgwid());
		model.setSqrid(t.getXh());
		model.setTzlj("xszz_knsrd_sh.do");
		model.setTzljsq("xszz_knsrd_sq.do");
		
		if(t.getShjg().equals("1")){
			// 設定业务字段1[业务名称]
			model.setZd1("推荐档次");
			// 設定业务字段2[业务ID]
			model.setZd2(t.getRddc());
			// 設定业务字段3
			KnsdcForm dcForm = new KnsdcForm();
			dcForm.setDcdm(t.getRddc());
			dcForm = dcDao.getModel(dcForm);							
			model.setZd3(dcForm.getDcmc());
			/*浙江经济职业技术学院，审核页面增加民主评议结果字段*/
			if("12866".equals(Base.xxdm)){
				model.setZd4("民主评议结果");
				model.setZd5(t.getYlzd4());
				model.setZd6(t.getYlzd4());
			}
			if("10335".equals(Base.xxdm)){
			model.setZd4("无偿资助金额");
			// 設定业务字段2[业务ID]
			KnsrdForm ywModel = dao.getModel(t);
			HashMap<String,String> sqxzMap = dao.getSqxz(t.getRddc(),ywModel.getXh(),ywModel.getXn(),ywModel.getXq());
			model.setZd9(sqxzMap.get("sqxz"));
			model.setZd6(t.getYlzd3());
			model.setZd10(t.getYlzd10());
			}
			//江西航空个性化
			if("13871".equals(Base.xxdm)){
				model.setZd6(t.getKnpx());
				model.setZd4("困难排序");
			}
			
		}
		
		boolean result = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			//更新申请表中的审核状态、认定档次变更
			KnsrdForm kdModel = new KnsrdForm();
			kdModel.setGuid(t.getGuid());
			if(zhzt.equals(Constants.SH_TG)){
				kdModel.setRddc(t.getRddc());
			}
			kdModel.setShzt(zhzt);
			kdModel.setYlzd3(t.getYlzd3());
			kdModel.setShrddc(t.getRddc());
			result = dao.runUpdateNotCommit(kdModel);
			// 最后一级审核通过时
			if(result && zhzt.equals(Constants.SH_TG)){
				// 插入正式表
				KnsjgDao knsjgDao = new KnsjgDao();
				KnsjgForm knsjgModel = new KnsjgForm();
				KnsrdForm copyModel = getModel(t);
				copyModel.setRddc(t.getRddc());
				copyModel.setYlzd3(t.getYlzd3());
				//江西航空个性化
				if("13871".equals(Base.xxdm)){
					copyModel.setKnpx(t.getKnpx());
				}
				BeanUtils.copyProperties(knsjgModel, copyModel);
				// 先按学号、学年、学期判断是否存在
				Map<String, String> map = knsjgDao.getXsknsjg(knsjgModel
						.getXh(), knsjgModel.getXn(), knsjgModel.getXq());
				if (!map.isEmpty()) {
					knsjgDao.delKnsjg(knsjgModel.getXn(), knsjgModel.getXq(), knsjgModel.getXh());
				}
				
				knsjgModel.setSjly(SQSH);
				knsjgModel.setLylcywid(t.getGuid());
				
				/*浙江经济职业技术学院，【民主评议结果插入结果表ylzd4】*/
				if("12866".equals(Base.xxdm)){
					knsjgModel.setYlzd4(t.getYlzd4());
				}
				//杭州师范大学个性化
				if("10346".equals(Base.xxdm)){
					knsjgModel.setYlzd4(copyModel.getYlzd9());
				}
				knsjgDao.runInsertNotCommit(knsjgModel);
			}
		
		return result;
	}

	/** 
	 * @描述:人数控制
	 * @作者：cmj[工号：913]
	 * @日期：2013-12-10 下午04:20:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * void 返回类型 
	 * @throws 
	 */
	private void checkRskz(KnsrdForm t) throws Exception{
		JcszDao jcszDao=new JcszDao();
		JcszForm model=jcszDao.getModel();
		String rskzfw = model.getRskzfw();
		String rskzjb = model.getRskzjb();
		String rskzkg = model.getRssfkz();
		String rskzsplc=model.getSplc();
		KnsrdForm knsrdForm=dao.getModel(t);
		if (Integer.parseInt(dao.getSplcXh(t.getXtgwid(),rskzsplc))>=Integer.parseInt(dao.getSplcXh(rskzjb,rskzsplc))){
			//获取人数设置信息
			HashMap<String, String> rsszMap=jcszDao.getRsszOne(rskzfw,t.getXh(),knsrdForm,t.getRddc());
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
			} else if (RSKZFW_ZY.equals(rskzfw)){
				tgrs = dao.getTgrsByZy(t, rsszMap);
			} else {
				tgrs = dao.getTgrsByBj(t, rsszMap);
			}
			
			if (!(Integer.valueOf(tgrs) < Integer.valueOf(xzrs))){
				throw new SystemException(MessageKey.RSKZ_FAIL,xzrs);
			}
		}
	}
	/**
	 * 
	 * @描述:撤消审核操作
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 上午10:13:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return boolean 返回类型
	 * @throws Exception
	 */
	public boolean cancelKnssh(String ywid) throws Exception{
		//更新业务状态
		KnsrdForm model = new KnsrdForm();
		model.setGuid(ywid);
		model.setRddc("");
		model.setShzt(Constants.YW_SHZ);
		boolean result = this.updateModel(model);
		//回滚结果表数据
		if(result){
			jgDao.delJgb(ywid);
		}
		return result;
	}

	/**
	 * 
	 * @描述:批量审核保存---返回审核失败的学号。
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 下午02:22:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<String> 返回类型
	 * @throws
	 */
	@TransactionControl
	public String savePlsh(KnsrdForm t, User user) throws Exception {

		String[] ids = t.getId();
		String[] gwid = t.getGwid();
		String[] xhs = t.getXhs();
		
		JcszForm jcszForm= new JcszDao().getModel();
		//先判断选择的学生总人数是否超过选择学生申请项目的人数控制
		if(TG.equals(t.getShzt())&&OPEN.equals(jcszForm.getRssfkz())){
			String sqids = "";
			for(int i=0;i<ids.length;i++){
				sqids += "'"+ids[i]+"',";
			}
			if(sqids.length()>0){
				sqids=sqids.substring(0, sqids.length()-1);
			}
			
			List<HashMap<String,String>> results = dao.getXzrs(sqids);
			for(int i=0;i<results.size();i++){
				HashMap<String,String> rmap = (HashMap<String,String>) results.get(i);
				if(new Integer(rmap.get("dqjb")).intValue()>=new Integer(rmap.get("kzjb")).intValue()){
					HashMap<String,String> tmap = dao.getKzrsTgrs(rmap.get("dqjb"),rmap.get("kzbm"),rmap.get("rskzfw"),t.getRddc());
					if(tmap!=null&&tmap.size()>0){
						if(new Integer(rmap.get("xzrs")).intValue()>new Integer(tmap.get("zzme")).intValue()-new Integer(tmap.get("ytggs")).intValue()){
							return MessageUtil.getText(MessageKey.SYS_AUD_PERS_OUT);
						}
					}
				}
			}
		}
		
		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			KnsrdForm model = new KnsrdForm();
			model.setGuid(ids[i]);
			model.setXtgwid(gwid[i]);								
			model.setRddc(t.getRddc());
			model.setYlzd3(t.getYlzd3());
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setXh(xhs[i]);

			boolean isSuccess = saveKnssh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}

	/**
	 * 
	 * @描述: 是否允许修改审核流程
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 下午04:06:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean allowUpdateSetting() {

		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @描述:按学号、学年、学期查询学生申请的困难生信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 下午04:59:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 *             KnsrdForm 返回类型
	 * @throws
	 */
	public KnsrdForm getModelByXnXq(KnsrdForm model) throws Exception {

		return dao.getModel(model, new String[] { model.getXh(), model.getXn(),
				model.getXq() });
	}

	/**
	 * 
	 * @描述:从困难生结果级联删除困难生申请对应信息
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午01:49:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean delKnssqFromKnsjg(String[] values) throws Exception {

		if (values == null || values.length == 0) {
			return false;
		}

		int num = dao.delKnssqFromKnsjg(values);

		if (num > 0) {
			dao.delKnsshFromKnsjg(values);
		}

		return num > 0;
	}

	/**
	 * 
	 * @描述: 审核情况统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 上午09:58:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return Map<String,Object> 返回类型
	 * @throws Exception 
	 * 
	 */
	public Map<String, Object> getShqkInfo(User user) throws Exception {
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();

		String xn = jcszModel.getRdxn();
		String xq = jcszModel.getRdxq();

		// 申请总人数
		int zrs = Integer.valueOf(dao.getSqrs(user, xn, xq));
		// 各级审核情况
		List<HashMap<String, String>> shqkInfoList = dao.getShqkTjxx(user, xn,
				xq);
		// 申请人数、通过人数、通过率、各级审核统计情况
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("zrs", zrs);

		List<HashMap<String, String>> shqkList = new ArrayList<HashMap<String, String>>();

		if (zrs == 0) {
			resultMap.put("zztgrs", "0");
			resultMap.put("zztgl", "0%");
		}

		for (int i = 0, n = shqkInfoList.size(); i < n; i++) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.putAll(shqkInfoList.get(i));

			double tgrs = Double.valueOf(shqkInfoList.get(i).get("tg"));
			double bgtrs = Double.valueOf(shqkInfoList.get(i).get("btg"));
			double dshrs = Double.valueOf(shqkInfoList.get(i).get("dsh"));
			DecimalFormat formater = new DecimalFormat("#.##%");

			if (tgrs + bgtrs + dshrs == 0) {
				map.put("tgl", "0%");
				map.put("btgl", "0%");
				map.put("dshl", "0%");
			} else {
				double tgl = tgrs / (tgrs + bgtrs + dshrs);
				double btgl = bgtrs / (tgrs + bgtrs + dshrs);
				double dshl = dshrs / (tgrs + bgtrs + dshrs);

				map.put("tgl", formater.format(tgl));
				map.put("btgl", formater.format(btgl));
				map.put("dshl", formater.format(dshl));
			}

			shqkList.add(map);

			if (i == n - 1) {
				// 最终通过率
				double zztgl = tgrs / zrs;
				resultMap.put("zztgrs", shqkInfoList.get(i).get("tg"));
				resultMap.put("zztgl", formater.format(zztgl));
			}

		}

		resultMap.put("shqkList", shqkList);
		return resultMap;
	}

	/**
	 * 
	 * @描述: 从审核统计查询具体学生
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-3 下午04:25:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentsList(KnsrdForm model,
			User user) throws Exception {
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();

		String xn = jcszModel.getRdxn();
		String xq = jcszModel.getRdxq();

		model.setXn(xn);
		model.setXq(xq);

		return dao.getStudentsFromShtj(model, user);
	}
	/** 
	 * @描述:根据人数分配方式，统计对应单位的人数
	 * @作者：陈敏杰
	 * @日期：2013-12-10 下午02:20:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rskzfw
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getShtgrs(JcszForm model) {
		if (StringUtils.isNull(model.getRskzfw())){
			return null;
		}
		return dao.getTgrs(model);
	}
	
	/**
	 * 
	 * @描述:根据GUID查询困难生申请信息
	 * @作者：cq [工号：785]
	 * @日期：2014-1-27 下午04:00:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getKnsqInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getKnsqInfo(guid);
	}
	
	/**
	 * 
	 * @描述:根据GUID查询困难生申请结果信息
	 * @作者 HongLin[工号：707]
	 * @日期：2014-2-18 下午04:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getKnsqjgInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getKnsqjgInfo(guid);
	}
	
	public HashMap<String, String> getSpxxInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getSpxxInfo(guid);
	}
	
	/**
	 * 
	 * @描述:判断当前用户当前周期是否有申请记录
	 * @作者：cq
	 * @日期：2014-2-13 下午03:20:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getExists(KnsrdForm model, String user) throws Exception{
		
		JcszService jcszService = new JcszService();
		JcszForm jcszModel = jcszService.getModel();
		
		String xq = jcszModel.getRdxq();
		KnsrdForm knsrdModel = new KnsrdForm();
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			knsrdModel = dao.getModelOfXn(model, new String[] {
					user, jcszModel.getRdxn()});
		}else{
		knsrdModel = dao.getModel(model, new String[] {
				user, jcszModel.getRdxn(), xq });
		}
		
		if(null == knsrdModel){
			return false;
		}else {
			return true;
		}
		
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2014-3-18 下午03:22:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * void 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getNewKnsdc(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("审核ID不能为空！");
			throw new NullPointerException();
		}

		return dao.getNewKnsdc(model);
	}
	
	/**
	 * 
	 * @描述:审核获取最新zd5值
	 * @作者：tgj[工号：1075]
	 * @日期：2017-8-14 下午02:53:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getNewBjpjjg(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("审核ID不能为空！");
			throw new NullPointerException();
		}

		return dao.getNewBjpjjg(model);
	}
	
	public HashMap<String, String> getNewKnyy(KnsrdForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("审核ID不能为空！");
			throw new NullPointerException();
		}

		return dao.getNewKnyy(model);
	}
	
	
	
	//审核导出不分页调用方法
	public List<HashMap<String,String>> getAllListSh(KnsrdForm t, User user) throws Exception {
		
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getShjlList(t, user);
	}
	
	/**
	 * 
	 * @描述: 申请理由名称结果集
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-6-21 下午03:33:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSqlymcList() {
		
		return dao.getSqlymcList();
	}
	
	/**
	 * @描述:浙江大学个性化_困难生第一级审核通过后特困生、困难生的比例计算
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2016-10-19 下午10:56:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyKnsfp(KnsrdForm model) throws Exception{
		return dao.getXyKnsfp(model);
	}
	
	/**
	 * @描述：困难原因代码->所有困难名称，用</br>分隔，用于传出到view
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param knyydm
	 * @return
	 * @throws Exception
	 * String 返回类型
	 */
	public String getKnyymc(String knyydm) throws Exception{
		if(StringUtils.isNull(knyydm)){
			return "";
		}
		List<HashMap<String, String>> list=new KnsdcService().getKnyyList();
		HashMap<String,String> knyyMap=new HashMap<String,String>();
		for(HashMap<String,String> temp:list){
			knyyMap.put(temp.get("yydm"),temp.get("yymc"));
		}
		String[] arr=knyydm.split(",");
		StringBuilder knyymc=new StringBuilder();
		for(int i=0;i<arr.length-1;i++){
			knyymc.append(knyyMap.get(arr[i])).append("</br>");
		}
		knyymc.append(knyyMap.get(arr[arr.length-1]));
		return knyymc.toString();
	}
	
	/**
	 * @描述：困难原因代码->所有困难原因mapList,代码对应项checked="1"，用于传出到update
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param knyydm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getKnnyList(String knyydm) throws Exception{
		List<HashMap<String, String>> knyyList=new KnsdcService().getKnyyList();  //困难原因list
		if(StringUtils.isNull(knyydm)){
			return knyyList;
		}
		for(HashMap<String,String> tempMap:knyyList){
			for(String yydm:knyydm.split(",")){
				if(yydm.equals(tempMap.get("yydm"))){
					tempMap.put("checked", "1"); //本条困难原因勾选
				}
			}
		}
		return knyyList;
	}
	
	public KnsrdForm getModel(KnsrdForm t) throws Exception {
		KnsrdForm form=dao.getModel(t);
		//上海体育学院个性化
		if("10277".equals(Base.xxdm)){
			String knyy=new KnsrdService().getKnyymc(form.getYlzd5());
			form.setYymc(knyy);
		}
		return form;
	}
	
	/**
	 * 
	 * @描述: 获取学院名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-12-18 上午10:20:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	
	
	/**
	 * @description	：获取家庭困难类型list
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-13 下午04:24:35
	 * @return
	 */
	public List<HashMap<String, String>> getJtknlxList(){
		return dao.getJtknlxList();
	}
	
	/**
	 * @description	： 高档消费品list
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-12-13 下午04:22:13
	 * @return
	 */
	public List<HashMap<String,String>> getGdxfpLxList(){
		return dao.getGdxfpLxList();
	}

	/**
	 * @描述:获取申请理由列表
	 * @作者：lgx [工号：1553]
	 * @日期：2018/8/31 14:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
	public List<HashMap<String, String>> getSqlyList() {

		return dao.getSqlyList();
	}

	public String[] getSqlyListByDms(String[] ids) throws SQLException {

		return dao.getSqlyListByDms(ids);
	}
}
