package com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy;

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
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
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
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.jgcx.JgcxForm;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyDao;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy.KnsjcszbjpyForm;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

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
public class KnsrdbjpyService extends SuperServiceImpl<KnsrdbjpyForm, KnsrdbjpyDao>
		implements Constants {
	
	private static final String SQSH = "2";// 申请审核
	
	private ShlcInterface shlc = new CommShlcImpl();

	private KnsrdbjpyDao dao = new KnsrdbjpyDao();
	
	private KnsdcDao dcDao = new KnsdcDao();
	
	private KnsjgDao jgDao = new KnsjgDao();

	public KnsrdbjpyService() {
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
	 * @param knsdcbjpy	困难生档次
	 * @param rddc rddc
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File printForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdbjpyForm model,
			List<HashMap<String, String>> knsdcbjpy, String rddc,HttpServletRequest request) throws Exception {

		// 加载学生家庭基本信息
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xsxx.get("xh"));
		
		//海洋大学家庭基本信息个性化查询
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xsxx.get("xh"));
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsxx == null){
			xsxx = new HashMap<String, String>();
		}
		if(knsdcbjpy == null){
			knsdcbjpy = new ArrayList<HashMap<String,String>>();
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
		data.put("kndc", knsdcbjpy);
		data.put("rddc", rddc);
		data.put("jtqk", jtqkmodel);// 家庭情况
		data.put("knsqInfo", getKnsqInfo(model.getGuid()));
		data.put("jtqkInfo", jtqkInfo);//上海海洋大学个性化查询
		
		//按学号加载成员列表 
		if(Base.xxdm.equals("10264")) {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyListSh(xsxx.get("xh"));
			data.put("cyList", cyList);
			//家庭成员列表空行
			int blankSize = (3 - cyList.size()) < 0 ? 0 : (3 - cyList.size());
			data.put("blankList", jtqkService.getBlankList(blankSize));
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
		}
		
		// ============为困难生档次编号（A,B...），并把选中的档次保存到knsdczmbhstr begin============
		String knsdcbjpyzmbhstr = "";
		String rddcmc = ""; // 困难档次名称
		for (int i = 0; i < knsdcbjpy.size(); i++) {
			HashMap<String, String> knsdcbjpyMap = knsdcbjpy.get(i);
			String knsdcbjpyzmbh = String.valueOf((char)(65+i));
			knsdcbjpyMap.put("knsdczmbh", knsdcbjpyzmbh);
			if(knsdcbjpyMap.get("dcdm").equals(rddc)){
				knsdcbjpyzmbhstr = knsdcbjpyzmbh;
				rddcmc = knsdcbjpyMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdcbjpyzmbhstr);
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
		PjjgService pjjgService = new PjjgService();
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
			ZzxmjgService zzxmjg = new ZzxmjgService();
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			String csny = xsxx.get("csrq") == null ? "" : xsxx.get("csrq").replace("-", "").substring(0, 6);
			data.put("csny", csny);
			KnsrdbjpyForm myForm = (KnsrdbjpyForm) model;
			data.put("pyxzdb", myForm.getBjpyxzdbxms());
			data.put("pyhsj", DateTranCnDate.fomartDateToCn(myForm.getBjpyjgpyhsj(),FomartDateType.day));
//			data.put("fdyxm", getNewKnsdcbjpy(model).get("shr"));
//			data.put("fdyspsj", DateTranCnDate.fomartDateToCn(getNewKnsdcbjpy(model).get("shsj"),FomartDateType.day));
			// ========== 审核信息 begin ============
			ShlcDao shlcDao = new ShlcDao();
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(model.getGuid(), "asc");
			if(shyjList.size() > 0){
				HashMap<String, String> shyj = shyjList.get(0);
				data.put("fdyxm", shyj.get("xm"));
				data.put("fdyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 1){
				HashMap<String, String> shyj = shyjList.get(1);
				data.put("xyxm", shyj.get("xm"));
				data.put("xyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 2){
				HashMap<String, String> shyj = shyjList.get(2);
				data.put("xxxm", shyj.get("xm"));
				data.put("xxspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			// ========== 审核信息 end ============
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "否");
		    }else{
		        data.put("zxdk", "是");
		    }
			data.put("rddc", model.getPddc() == null ? "" : model.getPddc());
			data.put("bjpyjgrdly", HtmlUtil.xmlZy(model.getBjpyjgrdly() == null ? "" : model.getBjpyjgrdly()));
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
		data.putAll(spxxInfo);
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
	 * @param knsdcbjpy	困难生档次
	 * @param rddc rddc
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File printjgForWord(HashMap<String, String> xsxx,
			List<HashMap<String,String>> infoList,KnsrdbjpyForm model,
			List<HashMap<String, String>> knsdcbjpy, String rddc,HttpServletRequest request) throws Exception {

		// 加载学生家庭基本信息
		JtqkdcService jtqkService = new JtqkdcService();
		JtqkdcForm jtqkmodel = jtqkService.getModel(xsxx.get("xh"));
		
		//海洋大学家庭基本信息个性化查询
		HashMap<String, String> jtqkInfo = jtqkService.getJtqkInfo(xsxx.get("xh"));	
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(xsxx == null){
			xsxx = new HashMap<String, String>();
		}
		if(knsdcbjpy == null){
			knsdcbjpy = new ArrayList<HashMap<String,String>>();
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
		data.put("kndc", knsdcbjpy);
		data.put("rddc", rddc);
		data.put("jtqk", jtqkmodel);// 家庭情况
		data.put("knsqInfo", getKnsqjgInfo(model.getGuid()));
		data.put("jtqkInfo", jtqkInfo);//上海海洋大学个性化查询
		
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
			
		}else {
			List<HashMap<String,String>> cyList = jtqkService.getJtcyList(xsxx.get("xh"));
			data.put("cyList", cyList);
		}
		
		// ============为困难生档次编号（A,B...），并把选中的档次保存到knsdczmbhstr begin============
		String knsdcbjpyzmbhstr = "";
		String rddcmc = ""; // 困难档次名称
		for (int i = 0; i < knsdcbjpy.size(); i++) {
			HashMap<String, String> knsdcbjpyMap = knsdcbjpy.get(i);
			String knsdcbjpyzmbh = String.valueOf((char)(65+i));
			knsdcbjpyMap.put("knsdczmbh", knsdcbjpyzmbh);
			if(knsdcbjpyMap.get("dcdm").equals(rddc)){
				knsdcbjpyzmbhstr = knsdcbjpyzmbh;
				rddcmc = knsdcbjpyMap.get("dcmc");
			}
		}
		data.put("knsdczmbhstr", knsdcbjpyzmbhstr);
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
		PjjgService pjjgService = new PjjgService();
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
			data.put("sqnyr", DateTranCnDate.fomartDateToCn(getKnsqjgInfo(model.getGuid()).get("sqsj"),FomartDateType.day));
			String csny = xsxx.get("csrq") == null ? "" : xsxx.get("csrq").replace("-", "").substring(0, 6);
			data.put("csny", csny);
			KnsrdbjpyForm myForm = getModel(model);
			data.put("pyxzdb", myForm.getBjpyxzdbxms());
			data.put("pyhsj", DateTranCnDate.fomartDateToCn(myForm.getBjpyjgpyhsj(),FomartDateType.day));
//			data.put("fdyxm", getNewKnsdcbjpy(model).get("shr"));
//			data.put("fdyspsj", DateTranCnDate.fomartDateToCn(getNewKnsdcbjpy(model).get("shsj"),FomartDateType.day));
			// ========== 审核信息 begin ============
			ShlcDao shlcDao = new ShlcDao();
			List<HashMap<String, String>> shyjList = shlcDao.getShyjList(model.getGuid(), "asc");
			if(shyjList.size() > 0){
				HashMap<String, String> shyj = shyjList.get(0);
				data.put("fdyxm", shyj.get("xm"));
				data.put("fdyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 1){
				HashMap<String, String> shyj = shyjList.get(1);
				data.put("xyxm", shyj.get("xm"));
				data.put("xyspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			if(shyjList.size() > 2){
				HashMap<String, String> shyj = shyjList.get(2);
				data.put("xxxm", shyj.get("xm"));
				data.put("xxspsj", DateTranCnDate.fomartDateToCn(shyj.get("shsj"),FomartDateType.day));
			}
			// ========== 审核信息 end ============
			ZzxmjgService zzxmjg = new ZzxmjgService(); 
			if(zzxmjg.getSfzxDk(xsxx.get("xh")).get("cs").equals("0")){
		        data.put("zxdk", "否");
		    }else{
		        data.put("zxdk", "是");
		    }
			data.put("rddc", myForm.getPddc() == null ? "" : myForm.getPddc());
			data.put("bjpyjgrdly", HtmlUtil.xmlZy(myForm.getBjpyjgrdly() == null ? "" : myForm.getBjpyjgrdly()));
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
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdb_"+Base.xxdm+".xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
			
		}catch (Exception e) {
			
			wordFile = FreeMarkerUtil.getWordFile(data, Constants.TEP_DIR + "xszz", "knsrdsq.xml", FreeMarkerUtil.getFileName(data.get("xh") +"["+data.get("xm")+"]"));
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
	public boolean saveKnssq(KnsrdbjpyForm model) throws Exception {
		// 困难生设置相关信息加载
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = jcszDao.getModel();
		
		model.setXn(jcszModel.getXn());
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			model.setXq("on");
		}
		else{
			model.setXq(jcszModel.getXq());
		}
		// 先判断当前学年学期该学生有木有申请过困难生
		KnsrdbjpyForm knsrdbjpyModel = dao.getModel(model, new String[] {
				model.getXh(), model.getXn(), model.getXq() });

		
		
		
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
			model.setShzt(YW_BJPYZ);
		}else{
			model.setShzt(YW_WTJ);
		}
		
		
		if (null == knsrdbjpyModel) {
			return dao.runInsert((KnsrdbjpyForm) StringUtils.formatBean(model));
		} else {
			return dao.runUpdate((KnsrdbjpyForm) StringUtils.formatBean(model));
		}
	}
	
	public boolean updateModel(KnsrdbjpyForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid,xh,"xszz_knsrdbjpy_sh.do","xszz_knsrdbjpy_sq.do");
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
	public List<HashMap<String, String>> getSplcInfo(KnsrdbjpyForm model) {

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
	public List<HashMap<String, String>> getShjlList(KnsrdbjpyForm t, User user)
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
	public boolean saveKnssh(KnsrdbjpyForm t, User user) throws Exception {

		//HashMap<String, String> beforeSpxx = dao.getBeforeSpxx(t);

//		// 当前审核岗位在第一级并且在做退回操作。（这样的逻辑不被允许！）
//		if (StringUtil.isNull(beforeSpxx.get("sjgw")) && TH.equals(t.getShzt())) {
//			return false;
//		}
		//人数控制
		KnsjcszbjpyDao jcszDao=new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszForm= jcszDao.getModel();
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
		model.setTzlj("xszz_knsrdbjpy_sh.do");
		model.setTzljsq("xszz_knsrdbjpy_sq.do");
	   
		
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
			if("10335".equals(Base.xxdm)){
			model.setZd4("无偿资助金额");
			// 設定业务字段2[业务ID]
			model.setZd6(t.getYlzd3());
			}
		}
		
		boolean result = false;
		try {
			String zhzt = shlc.runAuditing(model);
			//更新申请表中的审核状态、认定档次变更
			KnsrdbjpyForm kdModel = new KnsrdbjpyForm();
			kdModel.setGuid(t.getGuid());
			if(zhzt.equals(Constants.SH_TG)){
				kdModel.setRddc(t.getRddc());
			}
			kdModel.setShzt(zhzt);
			kdModel.setYlzd3(t.getYlzd3());
			result = dao.runUpdate(kdModel);
			// 最后一级审核通过时
			if(result && zhzt.equals(Constants.SH_TG)){
				// 插入正式表
				KnsjgDao knsjgbjpyDao = new KnsjgDao();
				KnsjgForm knsjgbjpyModel = new KnsjgForm();
				KnsrdbjpyForm copyModel = getModel(t);
				copyModel.setRddc(t.getRddc());
				copyModel.setYlzd3(t.getYlzd3());
				BeanUtils.copyProperties(knsjgbjpyModel, copyModel);

				// 先按学号、学年、学期判断是否存在
				Map<String, String> map = knsjgbjpyDao.getXsknsjg(knsjgbjpyModel
						.getXh(), knsjgbjpyModel.getXn(), knsjgbjpyModel.getXq());
				if (!map.isEmpty()) {
					knsjgbjpyDao.delKnsjg(knsjgbjpyModel.getXn(), knsjgbjpyModel.getXq(), knsjgbjpyModel.getXh());
				}
				
				knsjgbjpyModel.setSjly(SQSH);
				knsjgbjpyModel.setLylcywid(t.getGuid());
				knsjgbjpyDao.runInsert(knsjgbjpyModel);
				
			}
			// 退回 申请人时
			if(result && zhzt.equals(Constants.SH_TH) && "-1".equals(t.getThgw())){
				KnsrdbjpyForm kdModelOld = new KnsrdbjpyForm();
				kdModelOld.setGuid(t.getGuid());
				KnsrdbjpyForm kdModelNew = dao.getModel(kdModelOld);
				JgcxDao jgcxDao = new JgcxDao();
				// 更新班级评议表
				boolean rs = jgcxDao.cxBjpyxzpy(kdModelNew.getXn(), kdModelNew.getXq(), kdModelNew.getXh());
				if(rs){
					// 更新结果查询表
					JgcxForm jgcxForm = new JgcxForm();
					jgcxForm.setSqid(kdModelNew.getGuid());
					jgcxForm.setTjzt("0");
					jgcxForm.setTjsj(" ");
					jgcxForm.setShzt(" ");
					jgcxDao.runUpdate(jgcxForm);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
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
	private void checkRskz(KnsrdbjpyForm t) throws Exception{
		KnsjcszbjpyDao jcszDao=new KnsjcszbjpyDao();
		KnsjcszbjpyForm model=jcszDao.getModel();
		String rskzfw = model.getRskzfw();
		String rskzjb = model.getRskzjb();
		String rskzkg = model.getRssfkz();
		KnsrdbjpyForm knsrdbjpyForm=dao.getModel(t);
		if (t.getXtgwid().equals(rskzjb) && OPEN.equals(rskzkg)){
			//获取人数设置信息
			HashMap<String, String> rsszMap=jcszDao.getRsszOne(rskzfw,t.getXh(),knsrdbjpyForm);
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
				throw new SystemException(MessageKey.RSKZ_FAIL,tgrs);
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
		KnsrdbjpyForm model = new KnsrdbjpyForm();
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
	public String savePlsh(KnsrdbjpyForm t, User user) throws Exception {

		String[] ids = t.getId();
		String[] gwid = t.getGwid();
		String[] xhs = t.getXhs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			KnsrdbjpyForm model = new KnsrdbjpyForm();
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
	 *             KnsrdbjpyForm 返回类型
	 * @throws
	 */
	public KnsrdbjpyForm getModelByXnXq(KnsrdbjpyForm model) throws Exception {

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
	public boolean delKnssqFromKnsjgbjpy(String[] values) throws Exception {

		if (values == null || values.length == 0) {
			return false;
		}

		int num = dao.delKnssqFromKnsjgbjpy(values);

		if (num > 0) {
			dao.delKnsshFromKnsjgbjpy(values);
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
	 * 
	 */
	public Map<String, Object> getShqkInfo(User user) {
		// 困难生设置相关信息加载
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = new KnsjcszbjpyForm();
		try {
			jcszModel = jcszDao.getModel();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String xn = jcszModel.getXn();
		String xq = jcszModel.getXq();

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
	public List<HashMap<String, String>> getStudentsList(KnsrdbjpyForm model,
			User user) throws Exception {
		// 困难生设置相关信息加载
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = jcszDao.getModel();
		String xn = jcszModel.getXn();
		String xq = jcszModel.getXq();

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
	public List<HashMap<String, String>> getShtgrs(String rskzfw, String xn,
			String xq) {
		if (StringUtils.isNull(rskzfw)){
			return null;
		}
		return dao.getTgrs(rskzfw,xn,xq);
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
	public boolean getExists(KnsrdbjpyForm model, String user) throws Exception{
		// 困难生设置相关信息加载
		KnsjcszbjpyDao jcszDao = new KnsjcszbjpyDao();
		KnsjcszbjpyForm jcszModel = jcszDao.getModel();
		String xn = jcszModel.getXn();
		String xq = jcszModel.getXq();
		KnsrdbjpyForm knsrdbjpyModel = new KnsrdbjpyForm();
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			knsrdbjpyModel = dao.getModelOfXn(model, new String[] {
					user, xn});
		}else{
		knsrdbjpyModel = dao.getModel(model, new String[] {
				user, xn, xq });
		}
		
		if(null == knsrdbjpyModel){
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
	public HashMap<String, String> getNewKnsdcbjpy(KnsrdbjpyForm model) {

		if (StringUtil.isNull(model.getGuid())) {
			logger.error("审核ID不能为空！");
			throw new NullPointerException();
		}

		return dao.getNewKnsdcbjpy(model);
	}
	
	
	//审核导出不分页调用方法
	public List<HashMap<String,String>> getAllListSh(KnsrdbjpyForm t, User user) throws Exception {
		
		
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getShjlList(t, user);
	}
}
