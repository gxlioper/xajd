package com.zfsoft.xgxt.gygl.ssyd.ydsh;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.gygl.ssyd.ydjg.YdjgForm;
import com.zfsoft.xgxt.gygl.ssyd.ydjg.YdjgService;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述:宿舍异动审核
 * @作者： qilm
 * @时间：2013-9-29
 * @版本： V1.0
 */
public class YdshService extends SuperServiceImpl<YdshForm, YdshDAO> {
	/**
	 * 数据来源：1[审批流]
	 */
	private static final String SJLY_SPL = "1";
	/**
	 * 是否床位初始化[0:初始化]
	 */
	private static final String  SFCWCSH_CSH = "0";
	
	/**
	 *  退宿
	 */
	public static String _YDLX_TS = "00";
	/**
	 *  宿舍调整
	 */
	public static String _YDLX_SSTZ = "01";
	/**
	 *  入住
	 */
	public static String _YDLX_SSRZ = "03";
	
	private YdshDAO dao = new YdshDAO();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public YdshService() {
		super.setDao(dao);
	}
	/**
	 * @描述:宿舍异动审核
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	@TransactionControl
	public boolean ydsh(YdshForm form,User user) throws Exception{
		
		// 审核操作Model初始化
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShjg());
		model.setThgw(form.getThgw());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		// 业务ID(多为申请ID)
		model.setYwid(form.getSsydsqid());
		model.setSqrid(form.getXh());
		model.setTzlj("ydshbase.do");
		model.setTzljsq("ydsqbase.do");
		
		// 設定业务字段1[业务名称]
		model.setZd1("原床位是否初始化");
		
		// 設定业务字段1[业务ID]
		model.setZd2(form.getSfcwcsh());		
		String sfcwcsh = "0".equals(form.getSfcwcsh())?"是":"否";
		
		// 設定业务字段1[业务ID对应名称]
		model.setZd3(sfcwcsh);	
		
		ShlcService shlcService = new ShlcService();
		HashMap<String, String> shlcXx = shlcService.getXx(form.getSplcid());
		
		String zsfxs = shlcXx.get("zsfxs");
		if("1".equals(zsfxs)){
			model.setZd4("住宿费");
			
			model.setZd5(form.getJflx());
			if("01".equals(form.getJflx())) {
				String jflx = "补缴";
				model.setZd6(jflx+" "+form.getZsfje());
			}else if("02".equals(form.getJflx())) {
				String jflx = "退还";
				model.setZd6(jflx+" "+form.getZsfje());
			}
		}
		// ============ 审核床位 begin ==========
		YdsqService ydsqService = new YdsqService();
		//调整后床位信息
		String ydhcwxx = "";
		//如果是宿舍调整调用调整保存
		if(form.getSsydlx().equals(_YDLX_SSTZ)){
			ydhcwxx = form.getCwxx();
		}else if(form.getSsydlx().equals(_YDLX_SSRZ)){//入住保存
			ydhcwxx = form.getRzcwxx();
		}
		if(ydhcwxx!=null && !"".equals(ydhcwxx) && !"null_null_null".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			HashMap<String, String> cwxxMap = ydsqService.getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			form.setYdhlddm(cwxxMap.get("lddm"));
			form.setYdhldmc(cwxxMap.get("ldmc"));
			form.setYdhqsh(cwxxMap.get("qsh"));
			form.setYdhcwh(cwxxMap.get("cwh"));
			String ydglxh = cwxxMap.get("xh");//异动关联学生
			ydglxh = (ydglxh!=null && !"".equals(ydglxh) && !"null".equals(ydglxh)) ? ydglxh : "";
			form.setYdglxh(ydglxh);
		}else{
			form.setYdhlddm("");
			form.setYdhldmc("");
			form.setYdhqsh("");
			form.setYdhcwh("");
			form.setYdglxh("");
		}
		model.setZd7(form.getYdhlddm());
		model.setZd8(form.getYdhldmc());
		model.setZd9(form.getYdhqsh());
		model.setZd10(form.getYdhcwh());
		// ============ 审核床位 end ==========
		boolean result = false;
		
			String zhzt = shlc.runAuditingNotCommit(model);
			YdshForm upForm = new YdshForm();
			upForm.setSsydsqid(form.getSsydsqid());
			upForm.setShzt(zhzt);
			result = dao.runUpdateNotCommit(upForm, form.getSsydsqid());

			if (result) {
//				String ssydlxmc = form.getSsydlx().equals(_YDLX_TS) ? "退宿" : "宿舍调整";
//				//设置代办信息
//				BaseDbcz dbcz=new BaseDbcz();
//				dbcz.setSqPath("ydsqbase.do");
//				dbcz.setShPath("ydshbase.do");
//				dbcz.setGnmkMc("宿舍异动审核");
//				dbcz.setXmmc(ssydlxmc);
//				dbcz.shPush(form.getSsydsqid(),form.getSplcid());
			}
			
			// 最后一级审核通过时
			if(result && zhzt.equals(Constants.SH_TG)){
				
				// 插入宿舍异动结果库
				result = insertYdjg(form);
				
				// 宿舍调整（床位信息表变更）
				result = tzQS(form);
			}
			
		
		return result;
	}
	/**
	 * @描述:調整床位信息
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws SQLException  
	 */
	private boolean tzQS(YdshForm form) throws Exception {

		boolean returnFlg = true;
		
		// 异动审核MAP
		HashMap<String, String>  ydshMap = new HashMap<String, String>();
		if(YdshService._YDLX_SSRZ.equals(form.getSsydlx())){
			ydshMap = dao.getYdshRzForm(form);
		}else{
			ydshMap = dao.getYdshForm(form);
		}
		
		// 学生
		String xh = ydshMap.get("xh");		
		
		// 异动前床位信息
		String ydqlddm = ydshMap.get("ydqlddm")==null ?"":ydshMap.get("ydqlddm");
		String ydqqsh = ydshMap.get("ydqqsh")==null ?"":ydshMap.get("ydqqsh");
		String ydqcwh = ydshMap.get("ydqcwh")==null ?"":ydshMap.get("ydqcwh");
		
		// 是否床位初始化
		String sfcwcsh = form.getSfcwcsh();
		HashMap<String, String> cwxxMap = new HashMap<String, String>();
		// 退宿/调整时间
		String tstzsj = ydshMap.get("tstzsj")==null ?"":ydshMap.get("tstzsj");
		
		// 宿舍异动类型：退宿
		if(ydshMap.get("ssydlx")!=null && _YDLX_TS.equals(ydshMap.get("ssydlx"))){
			
			cwxxMap.put("xh", "");
			cwxxMap.put("lddm", ydqlddm);
			cwxxMap.put("qsh", ydqqsh);
			cwxxMap.put("cwh", ydqcwh);
			cwxxMap.put("rzsj", "");//入住时间
			
			// 是否初始化
			if(SFCWCSH_CSH.equals(sfcwcsh)){

				cwxxMap.put("nj", "");
				cwxxMap.put("bjdm", "");
				cwxxMap.put("zydm", "");
				cwxxMap.put("xydm", "");
			}
			returnFlg = dao.updateCwxxb(cwxxMap);
			
			// 宿舍异动类型：宿舍调整
		}else if(ydshMap.get("ssydlx")!=null && _YDLX_SSTZ.equals(ydshMap.get("ssydlx"))){
			// 异动关联学生
			String ydglxh = ydshMap.get("ydglxh")==null ?"":ydshMap.get("ydglxh");
			
			// 异动后床位信息
			String ydhlddm = ydshMap.get("ydhlddm")==null ?"":ydshMap.get("ydhlddm");
			String ydhqsh = ydshMap.get("ydhqsh")==null ?"":ydshMap.get("ydhqsh");
			String ydhcwh = ydshMap.get("ydhcwh")==null ?"":ydshMap.get("ydhcwh");

			// 异动后的床位信息更新
			cwxxMap.put("lddm", ydhlddm);
			cwxxMap.put("qsh", ydhqsh);
			cwxxMap.put("cwh", ydhcwh);
			cwxxMap.put("xh", xh);
			cwxxMap.put("nj", ydshMap.get("ydqxsnj"));
			cwxxMap.put("bjdm", ydshMap.get("ydqxsbjdm"));
			cwxxMap.put("zydm", ydshMap.get("ydqxszydm"));
			cwxxMap.put("xydm", ydshMap.get("ydqxsxydm"));
			cwxxMap.put("rzsj", tstzsj);//入住时间
			
			returnFlg = dao.updateCwxxb(cwxxMap);
			
			// 有关联学生
			if(returnFlg && !"".equals(ydglxh)){
				
				cwxxMap = new HashMap<String, String>();
				// 异动前的床位信息更新
				cwxxMap.put("lddm", ydqlddm);
				cwxxMap.put("qsh", ydqqsh);
				cwxxMap.put("cwh", ydqcwh);
				cwxxMap.put("xh", ydglxh);
				cwxxMap.put("nj", ydshMap.get("ydhxsnj"));
				cwxxMap.put("bjdm", ydshMap.get("ydhxsbjdm"));
				cwxxMap.put("zydm", ydshMap.get("ydhxszydm"));
				cwxxMap.put("xydm", ydshMap.get("ydhxsxydm"));
				cwxxMap.put("rzsj", tstzsj);//入住时间
				returnFlg = dao.updateCwxxb(cwxxMap);
				
				//无关联学生
			}else if( returnFlg){
				
				cwxxMap =  new HashMap<String, String>() ;
				cwxxMap.put("xh", "");
				cwxxMap.put("lddm", ydqlddm);
				cwxxMap.put("qsh", ydqqsh);
				cwxxMap.put("cwh", ydqcwh);
				cwxxMap.put("rzsj", "");//入住时间
				
				// 是否初始化
				if(SFCWCSH_CSH.equals(sfcwcsh)){
	
					cwxxMap.put("nj", "");
					cwxxMap.put("bjdm", "");
					cwxxMap.put("zydm", "");
					cwxxMap.put("xydm", "");
				}
				returnFlg = dao.updateCwxxb(cwxxMap);
			}
		}else if(ydshMap.get("ssydlx")!=null && _YDLX_SSRZ.equals(ydshMap.get("ssydlx"))){
			// 异动后床位信息
			String ydhlddm = ydshMap.get("ydhlddm")==null ?"":ydshMap.get("ydhlddm");
			String ydhqsh = ydshMap.get("ydhqsh")==null ?"":ydshMap.get("ydhqsh");
			String ydhcwh = ydshMap.get("ydhcwh")==null ?"":ydshMap.get("ydhcwh");

			// 异动后的床位信息更新
			cwxxMap.put("lddm", ydhlddm);
			cwxxMap.put("qsh", ydhqsh);
			cwxxMap.put("cwh", ydhcwh);
			cwxxMap.put("xh", xh);
			cwxxMap.put("nj", ydshMap.get("ydqxsnj"));
			cwxxMap.put("bjdm", ydshMap.get("ydqxsbjdm"));
			cwxxMap.put("zydm", ydshMap.get("ydqxszydm"));
			cwxxMap.put("xydm", ydshMap.get("ydqxsxydm"));
			cwxxMap.put("rzsj", tstzsj);//入住时间
			cwxxMap.put("rzyydm", ydshMap.get("tstzyy"));
			
			dao.updateCwxxb(cwxxMap);
			
		}
		
		return returnFlg;
	}
	/**
	 * 
	 * @描述:插入宿舍异动结果库
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	private boolean insertYdjg(YdshForm form) throws Exception{
		
		boolean returnFlg = true;
		YdjgForm ydjgForm = new YdjgForm();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		YdjgService ydjgService = new YdjgService();
				
		// 异动审核MAP
		HashMap<String, String>  ydshMap = new HashMap<String, String>();
		if(YdshService._YDLX_SSRZ.equals(form.getSsydlx())){
			ydshMap = dao.getYdshRzForm(form);
		}else{
			ydshMap = dao.getYdshForm(form);
		}
		String guid = UniqID.getInstance().getUniqIDHash();
		ydjgForm.setSsydid(guid);
		ydjgForm.setXh(ydshMap.get("xh"));
		ydjgForm.setCzsj(date);
		ydjgForm.setXn(ydshMap.get("xn"));
		ydjgForm.setXq(ydshMap.get("xq"));
		ydjgForm.setSsydlx(ydshMap.get("ssydlx"));
		ydjgForm.setTstzsj(ydshMap.get("tstzsj"));
		ydjgForm.setTstzyy(ydshMap.get("tstzyy"));
		ydjgForm.setBz(ydshMap.get("bz"));
		ydjgForm.setTjsqrxm(ydshMap.get("tjsqrxm"));//提交申请人姓名
		//異動前床位信息
		ydjgForm.setYdqlddm(ydshMap.get("ydqlddm"));
		ydjgForm.setYdqldmc(ydshMap.get("ydqldmc"));
		ydjgForm.setYdqqsh(ydshMap.get("ydqqsh"));
		ydjgForm.setYdqcwh(ydshMap.get("ydqcwh"));
		ydjgForm.setYdqqsrzsj(ydshMap.get("rzsj"));
		//異動後床位信息		
		ydjgForm.setYdhlddm(ydshMap.get("ydhlddm"));
		ydjgForm.setYdhldmc(ydshMap.get("ydhldmc"));
		ydjgForm.setYdhqsh(ydshMap.get("ydhqsh"));
		ydjgForm.setYdhcwh(ydshMap.get("ydhcwh"));
		//异动后寝室原所属
		ydjgForm.setYdhnj(ydshMap.get("ydhnj"));
		ydjgForm.setYdhxydm(ydshMap.get("ydhxydm"));
		ydjgForm.setYdhzydm(ydshMap.get("ydhzydm"));
		ydjgForm.setYdhbjdm(ydshMap.get("ydhbjdm"));		
		
		ydjgForm.setFjxx(ydshMap.get("fjxx"));// 附件信息
		ydjgForm.setSfcwcsh(form.getSfcwcsh());//是否床位初始化
		ydjgForm.setJflx(form.getJflx());//缴费类型
		ydjgForm.setZsfje(form.getZsfje());//住宿费金额
		ydjgForm.setSjly(SJLY_SPL);					//设定数据来源
		ydjgForm.setSsydsqid(ydshMap.get("ssydsqid"));//申请ID
		ydjgForm.setYdqqsrzsj(ydshMap.get("ydqrzsj"));//异动前寝室入住时间
		
		if(ydshMap.get("ydglxh")!=null && !"".equals(ydshMap.get("ydglxh"))){
			YdjgForm ydjgFormGl = new YdjgForm();
			String guidGl = UniqID.getInstance().getUniqIDHash();
			ydjgFormGl.setSsydid(guidGl);
			ydjgFormGl.setXh(ydshMap.get("ydglxh"));
			ydjgFormGl.setCzsj(date);
			ydjgFormGl.setXn(ydshMap.get("xn"));
			ydjgFormGl.setXq(ydshMap.get("xq"));
			ydjgFormGl.setSsydlx(ydshMap.get("ssydlx"));
			ydjgFormGl.setTstzsj(ydshMap.get("tstzsj"));
			ydjgFormGl.setTstzyy(ydshMap.get("tstzyy"));
			ydjgFormGl.setBz(ydshMap.get("bz"));
			//異動前床位信息
			ydjgFormGl.setYdqlddm(ydshMap.get("ydhlddm"));
			ydjgFormGl.setYdqldmc(ydshMap.get("ydhldmc"));
			ydjgFormGl.setYdqqsh(ydshMap.get("ydhqsh"));
			ydjgFormGl.setYdqcwh(ydshMap.get("ydhcwh"));
			//異動後床位信息
			ydjgFormGl.setYdhlddm(ydshMap.get("ydqlddm"));
			ydjgFormGl.setYdhldmc(ydshMap.get("ydqldmc"));
			ydjgFormGl.setYdhqsh(ydshMap.get("ydqqsh"));
			ydjgFormGl.setYdhcwh(ydshMap.get("ydqcwh"));
			//异动后寝室原所属
			ydjgFormGl.setYdhnj(ydshMap.get("ydhnj"));
			ydjgFormGl.setYdhxydm(ydshMap.get("ydhxydm"));
			ydjgFormGl.setYdhzydm(ydshMap.get("ydhzydm"));
			ydjgFormGl.setYdhbjdm(ydshMap.get("ydhbjdm"));
			ydjgForm.setFjxx(ydshMap.get("fjxx"));
			ydjgFormGl.setSfcwcsh(form.getSfcwcsh());//是否床位初始化
			ydjgForm.setJflx(form.getJflx());//缴费类型
			ydjgForm.setZsfje(form.getZsfje());//住宿费金额
			ydjgFormGl.setSjly(SJLY_SPL);					//设定数据来源
			ydjgFormGl.setSsydsqid(ydshMap.get("ssydsqid"));//申请ID
			ydjgFormGl.setYdqqsrzsj(ydshMap.get("ydhrzsj"));//异动前寝室入住时间
			
			//插入关联的异动信息
			returnFlg = ydjgService.runInsert(ydjgFormGl);
		}
		
		if(returnFlg) returnFlg = ydjgService.runInsert(ydjgForm);
		return returnFlg;
	}
	/** 
	 * @描述:最后一级撤销审核
	 * @作者：qilm
	 * @日期：2013-10-10
	 * @param shlc 审核流程ID
	 * @param ssydsqid 申请ID
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(String shlc, String ssydsqid) throws Exception{
		
		YdjgService ydjgService = new YdjgService();
		
		// 更新申请表的审核状态
		YdshForm upForm = new YdshForm();
		upForm.setSsydsqid(ssydsqid);
		upForm.setShzt(Constants.YW_SHZ);
		boolean result = dao.runUpdate(upForm, ssydsqid);
		
		if(result){
			
			// 根据申请ID取得异动结果
			List<HashMap<String, String>> ydjgLst = ydjgService.getYdjg(ssydsqid);
			
			// 异动类型
			String ssydlx = "";
			for(HashMap<String, String> ydjg : ydjgLst){
				
				// 异动类型
				ssydlx = ydjg.get("ssydlx");

				HashMap<String, String> cwxxMap =  new HashMap<String, String>();
				HashMap<String, String> xsInfo = ydjgService.getXsInfo(ydjg.get("xh"));
				cwxxMap.put("xh", ydjg.get("xh"));	
				cwxxMap.put("nj", xsInfo.get("nj"));
				cwxxMap.put("bjdm", xsInfo.get("bjdm"));
				cwxxMap.put("zydm", xsInfo.get("zydm"));
				cwxxMap.put("xydm", xsInfo.get("xydm"));
				cwxxMap.put("rzsj", ydjg.get("ydqqsrzsj"));
				cwxxMap.put("lddm", ydjg.get("ydqlddm"));
				cwxxMap.put("qsh", ydjg.get("ydqqsh"));
				cwxxMap.put("cwh", ydjg.get("ydqcwh"));
				
				// 学生入住信息回滚
				dao.updateCwxxb(cwxxMap);

				// 宿舍调整空床位情况
				if(_YDLX_SSTZ.equalsIgnoreCase(ssydlx)&& ydjgLst!=null && ydjgLst.size()==1){
					cwxxMap =  new HashMap<String, String>();
					cwxxMap.put("xh", "");	
					cwxxMap.put("rzsj", "");
					cwxxMap.put("nj", ydjg.get("ydhnj"));
					cwxxMap.put("bjdm", ydjg.get("ydhbjdm"));
					cwxxMap.put("zydm", ydjg.get("ydhzydm"));
					cwxxMap.put("xydm", ydjg.get("ydhxydm"));					
					cwxxMap.put("lddm", ydjg.get("ydhlddm"));
					cwxxMap.put("qsh", ydjg.get("ydhqsh"));
					cwxxMap.put("cwh", ydjg.get("ydhcwh"));	
					
					// 空床位信息回滚
					dao.updateCwxxb(cwxxMap);			
				}else if(_YDLX_SSRZ.equalsIgnoreCase(ssydlx) && ydjgLst!=null && ydjgLst.size()==1){ // 入住
					cwxxMap =  new HashMap<String, String>();
					cwxxMap.put("xh", "");	
					cwxxMap.put("rzsj", "");
					cwxxMap.put("rzyydm", "");
					cwxxMap.put("nj", ydjg.get("ydhnj"));
					cwxxMap.put("bjdm", ydjg.get("ydhbjdm"));
					cwxxMap.put("zydm", ydjg.get("ydhzydm"));
					cwxxMap.put("xydm", ydjg.get("ydhxydm"));					
					cwxxMap.put("lddm", ydjg.get("ydhlddm"));
					cwxxMap.put("qsh", ydjg.get("ydhqsh"));
					cwxxMap.put("cwh", ydjg.get("ydhcwh"));	
					
					// 空床位信息回滚
					dao.updateCwxxb(cwxxMap);			
				}
			}
			// 异动结果库有数据的情况
			if(ydjgLst!=null && ydjgLst.size()>0){
				
				// 删除该申请对应的宿舍异动结果库
				int count = ydjgService.runDeleteYdjg(ssydsqid);
				
				
				// 删除成功
				if(count >0){
	
//					String ssydlxmc = ssydlx.equals(_YDLX_TS) ? "退宿" : "宿舍调整";
//					//设置代办信息
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setGnmkMc("宿舍异动审核");
//					dbcz.setXmmc(ssydlxmc);
//					dbcz.shPush(ssydsqid,shlc);
					
					return true;
				}else{
					return false;
				}
			}
		}
		return result;
		
	}
	/**
	 * @throws Exception  
	 * @描述:批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-28 下午05:09:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public String savePlsh(YdshForm t, User user) throws Exception {
		

		String[] ids = t.getId();
		String[] gwid = t.getGwids();
		String[] xhs = t.getXhs();
		String[] splcs = t.getSplcs();
		String[] ssydlxs = t.getSsydlxs();
		String[] sqshHideCwxxs = t.getSqshHideCwxxs();

		List<String> failXhs = new ArrayList<String>();

		for (int i = 0, n = ids.length; i < n; i++) {
			
			
			YdshForm model = new YdshForm();
			
			model.setSplcid(splcs[i]);
			model.setSsydsqid(ids[i]);
			model.setGwid(gwid[i]);
			model.setShyj(t.getShyj());
			model.setShjg(t.getShzt());
			model.setSfcwcsh(t.getSfcwcsh());
			model.setJflx(t.getJflx());
			model.setZsfje(t.getZsfje());
			model.setXh(xhs[i]);
			model.setSsydlx(ssydlxs[i]);
			// ========== 床位设置 begin ============
			if(ssydlxs[i].equals(_YDLX_SSTZ)){
				model.setCwxx(sqshHideCwxxs[i]);
			}else if(ssydlxs[i].equals(_YDLX_SSRZ)){//入住保存
				model.setRzcwxx(sqshHideCwxxs[i]);
			}
			// ========== 床位设置 end ============
			boolean isSuccess = ydsh(model, user);

			if (!isSuccess) {
				failXhs.add(xhs[i]);
			}
		}

		JSONArray json = JSONArray.fromObject(failXhs);

		if (failXhs.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_AUD_SUCCESS);
		} else if (Constants.SH_TH.equals(t.getShzt())) {
			return MessageUtil.getText(MessageKey.SYS_BACK_FAIL, json
					.toString());
		} else {
			return MessageUtil.getText(MessageKey.SYS_PLSH_FAIL, json
					.toString());
		}
	}
	
}
