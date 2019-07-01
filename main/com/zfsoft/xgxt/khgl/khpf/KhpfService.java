/**
 * @部门:学工产品事业部
 * @日期：2015-8-12 下午02:42:07 
 */  
package com.zfsoft.xgxt.khgl.khpf;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.OtmMapping;
import common.Globals;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import xgxt.action.Base;
import xgxt.base.DBEncrypt;
import xgxt.base.Encrypt;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.io.File;
import java.util.*;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核管理
 * @类功能描述: Service
 * @作者：cq [工号:785]
 * @时间： 2015-8-12 下午02:42:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KhpfService extends SuperServiceImpl<KhpfForm, KhpfDao> {
	
	public static final String SFNZ_N = "1";	//是否内置_否
	public static final String SFNZ_Y = "2";	//是否内置_是
	public static final String SFTJ_Y = "1";	//是否提交_是
	public static final String SFTJ_N = "2";	//是否提交_否
	public static final String YHLX_XS = "1";	//用户类型_学生
	public static final String YHLX_JS = "2";	//用户类型_教师
	public static final String YHLX_BR = "3";	//用户类型_本人
	public static final String PFZT_WP = "0";	//评分状态_未评
	public static final String PFZT_YP = "1";	//评分状态_已评
	public static final String KHGL_PFZT = "1";	//已评
	
	
	public static final String BR_BR = "br";		//自己看下面注释
	public static final String XS_JS_BZR = "bzr";
	public static final String XS_JS_FDY = "fdy";
	public static final String XS_JS_BZRFDY = "bzrfdy";
	public static final String XS_JS_BYX = "byx";
	public static final String XS_JS_XX = "xx";
	public static final String XS_XS_BB = "bb";
	public static final String JS_XS_BZR = "bzr";
	public static final String JS_XS_FDY = "fdy";
	public static final String JS_XS_BZRFDY = "bzrfdy";
	public static final String JS_XS_BYX = "byx";
	public static final String JS_XS_XX = "xx";
	public static final String JS_JS_BBM = "bbm";
	public static final String JS_JS_QX = "qx";
	
	
	/**
	 * 
	评分对象	数据范围			数据范围代码		备注
	-------------------------------------------------------------------------
						本人   评   本人			
	本人		本人				br				只能自己评自己
	
	-------------------------------------------------------------------------			
						学生   评   教师			
	学生		班主任			bzr				只能对自己班班主任评
			辅导员			fdy				只能对自己班辅导员评
			班主任+辅导员	bzrfdy			只能对自己班班主任+辅导员评
			本院系			byx				只能对自己院系老师评（只是判断学院部门）
			全校				xx				能对所有被评分对象评
			
	-------------------------------------------------------------------------			
						学生   评   学生			
	学生		本班				bb				对自己班学生评
	
	-------------------------------------------------------------------------			
					教师   评   学生			
	教师		班主任			bzr				班主任所带班级学生
			辅导员			fdy				辅导员所带班级学生
			班主任+辅导员	bzrfdy			班主任+辅导员所带班级学生
			本院系			byx				本院系内所有学生
			全校				xx 				能对所有被评分对象评
			
	-------------------------------------------------------------------------			
						教师   评   教师			
	教师		本部门			bbm				和自己同一部门教师
			全校				qx				辅导员当中所有教师  
	 **/

	
	/**
	 * @throws Exception  
	 * @描述:考核评分查询
	 * @作者：cq [工号：785]
	 * @日期：2015-8-12 下午04:30:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhpflList(KhpfForm model, User user) throws Exception {
		
		//学生和教师不一样的查询方法
		if("stu".equalsIgnoreCase(user.getUserType())){
			return dao.getXsPageList(model, user);
		}
		return dao.getJsPageList(model, user);
	}


	/**
	 * @throws Exception  
	 * @描述:根据登陆人查询可评人
	 * @作者：cq [工号：785]
	 * @日期：2015-8-13 下午04:36:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> quePf(KhpfForm model, User user) throws Exception {
		
		String userType = user.getUserType();
		List<HashMap<String, String>> quePf = null;
		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			return dao.jsPf(model, user);
		}else{
		if("stu".equals(userType) && YHLX_JS.equals(model.getKhlx())){
			//学生评教师
			quePf = dao.stuEvaTea(model, user);			
		}else if ("stu".equals(userType) && YHLX_XS.equals(model.getKhlx())){
			//学生评学生
			quePf = dao.stuEvaStu(model, user);	
		}else if (YHLX_XS.equals(model.getKhlx())){
			//教师评学生
			quePf = dao.teaEvaStu(model, user);	
		}else if (YHLX_JS.equals(model.getKhlx())){
			//教师评教师
			quePf = dao.teaEvaTea(model, user);	
		}
		}
		
		return quePf;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:根据项目id和考核表id查询项目信息
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 上午09:43:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @param khbid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public KhpfForm getXmKhb(String xmid, String khbid) throws Exception{
		return dao.getXmKhb(xmid, khbid);
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存考核评分
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 下午03:28:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveKhpf(KhpfForm form, User user) throws Exception{
		return dao.saveKhpf(form, user);		
	}
	
	
	/**
	 * 
	 * @描述:根据考核表ID查询考核项目
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 下午04:07:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmInfo(String xmid){
		return dao.getXmInfo(xmid);
	}
	
	public HashMap<String, String> getPfxxInfo(String xmid,User user){
		return dao.getPfxxInfo(xmid,user);
	}

	public HashMap<String, String> getPfxxInfo_HNCS(String xmid,String xh){
		return dao.getPfxxInfo_HNCS(xmid, xh);
	}
	
	
	/**
	 * 
	 * @描述:查询人员信息
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 下午04:10:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getRyInfo(String ry){
		
		HashMap<String, String> ryInfo = dao.getJsInfo(ry);
		
		if(null == ryInfo||ryInfo.size()==0){
			ryInfo = dao.getXsInfo(ry);
		}
		
		return ryInfo;
	}


	/**
	 * @throws Exception  
	 * @描述:保存考核分数
	 * @作者：cq [工号：785]
	 * @日期：2015-8-15 上午11:41:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveKhfs(KhpfForm model, User user) throws Exception {
		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			return dao.saveKhfs_HNCS(model, user);
		}else{
			dao.saveKhfs(model, user);
			 return dao.saveKhzt(model, user);
		}
		 
	}
	
	public boolean saveBz(KhpfForm model, User user) throws Exception {
		return dao.saveBz(model, user);
	}
	
	/** 
	 * @描述:保存考核意见建议(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-2 下午04:15:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveKhYjjy(KhpfForm model, User user) throws Exception {
		return dao.saveKhYjjy(model, user);
	}
	
	/** 
	 * @描述:获取评价建议意见(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-2 下午05:07:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String getKhYjjy(KhpfForm model, User user) throws Exception {
		return dao.getKhYjjy(model, user);
	}


	/**
	 * @throws Exception  
	 * @描述: 查询考核分数列表
	 * @作者：cq [工号：785]
	 * @日期：2015-8-15 下午01:43:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfList(KhpfForm model, User user) throws Exception {
		if(Globals.XXDM_HNCSXY.equals(Base.xxdm)){
			return dao.getKhPfList(model, user);
		}else{
		// 保存考核评分状态表
		dao.saveKhzt(model, user);
		
		return dao.getPfList(model,user);
		}
	}
	/**
	 * 
	 * @描述:考核评分（湖南城市学院个性化）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-8-9 下午04:39:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKhPfList(KhpfForm model, User user) throws Exception {
		return dao.getKhPfList(model,user);
	}
	
	public List<HashMap<String, String>> getPfTjList(KhpfForm model, User user) throws Exception {
		return dao.getPfTjList(model,user);
	}
	
	public HashMap<String,String> getZpxx(KhpfForm model) throws Exception {
		return dao.getZpxx(model);
	}
	
	public HashMap<String,String> getZpxxList(String xh) throws Exception {
		return dao.getZpxxList(xh);
	}
	
	
	/**
	 * 
	 * @描述:验证是否可提交
	 * @作者：cq [工号：785]
	 * @日期：2015-8-17 上午11:39:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsCanSubmit(KhpfForm model, User user){
		return dao.checkIsCanSubmit(model, user);
	}


	/**
	 * @throws Exception  
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2015-8-17 上午11:51:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submitBjzcf(KhpfForm model, User user) throws Exception {		
//		boolean submitBjzcf=checkIsCanSubmit(model,user);
		model.setShzt(KHGL_PFZT);
		//提交状态修改前再次批量保存数据（根据效率优化或删除）
		boolean submitBjzcf=dao.batchSaveKhfs(model,user);
		if(submitBjzcf){
			submitBjzcf=dao.submitBjzcf(model, user);
		}
		if(Base.xxdm.equals("12309") || Base.xxdm.equals("33333")){//武昌首义个性化，浙江商业技师学院，更新意见建议表
			submitBjzcf=dao.submitYjjy(model, user);
		}
		return submitBjzcf;
	}
	
	public boolean submitKhpf(KhpfForm model, User user) throws Exception {
		boolean result = false;
		//提交前再次保存一下分数数据
		model.setShzt(KHGL_PFZT);
//		return dao.submitKhpf(model, user);
		result = dao.batchSubmitKhpf(model);
		//数据和提交状态改变后，根据情况将数据推向下一级
		if(result){
			result = dao.afterTheCurrently(model,user);
		}
		return result;
	}
	/**
	 * 
	 * @描述:审核
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-1-11 下午05:05:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean sh(KhpfForm model, User user) throws Exception {		
		String[] khdxrs = model.getKhdxr().split(",");
		boolean flag=true;
		
		for (int i = 0; i < khdxrs.length; i++) {
			KhpfForm pfshForm = new KhpfForm();
			pfshForm.setXmid(model.getXmid());
			pfshForm.setKhbid(model.getKhbid());
			pfshForm.setXmszid(model.getXmszid());
			pfshForm.setKhdxr(khdxrs[i]);
			pfshForm.setPfr(user.getUserName());
			pfshForm.setShzt(KHGL_PFZT);
			pfshForm.setPflx(model.getPflx());
			flag=dao.beforeTheCurrently(pfshForm, user);
			flag=dao.submitKhpf(pfshForm, user);
			flag=dao.afterTheCurrently(pfshForm,user);
		}
		return flag;
	}

	/**
	 *  湖南城市撤消.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-19 10:14
	 * @param model
	 * @param user
	 * @return boolean
	 * @throw
	 */
	public boolean cx(KhpfForm model, User user) throws Exception {
		String[] khdxrs = model.getKhdxr().split(",");
		boolean flag=true;

		for (int i = 0; i < khdxrs.length; i++) {
			KhpfForm pfshForm = new KhpfForm();
			pfshForm.setXmid(model.getXmid());
			pfshForm.setKhbid(model.getKhbid());
			pfshForm.setXmszid(model.getXmszid());
			pfshForm.setKhdxr(khdxrs[i]);
			pfshForm.setPfr(user.getUserName());
			pfshForm.setShzt(PFZT_WP);
			pfshForm.setPflx(model.getPflx());
			flag=dao.submitKhpf(pfshForm, user);
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @描述:根据项目id删除考核评分
	 * @作者：cq [工号：785]
	 * @日期：2015-8-20 下午02:37:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKhpfForXmid(String[] xmid) throws Exception{
		
		boolean bl = false;
		
		if(dao.delKhpfForXmid(xmid)){
			bl = dao.delWxPfmx();
		}
		return bl;
	}
	
	public boolean isJs(KhpfForm model){
		return dao.isJs(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 考核取消提交[浙大宁宁波理工学院个性化需求]
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-7 下午03:02:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param khpfForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelTjRecord(KhpfForm khpfForm) throws Exception{
		return dao.cancelTjRecord(khpfForm);
	}

	/**
	 *  根据用户信息，获取班主任所代的班级的班组评分密码信息列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 11:51
	 * @param user
	 * @return List<HashMap<String,String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getBzpfmmList(User user) {

		List<HashMap<String,String>> bzpfmmList = dao.getBzpfmmList(user);
		if(bzpfmmList != null){
			DBEncrypt encrypt = new DBEncrypt();
			//解密
			for(HashMap<String,String> map:bzpfmmList){
				String password = map.get("password");
				if(StringUtils.isNotNull(password)){
					map.put("password",encrypt.dCode(password.getBytes()));
				}
			}
		}

		return bzpfmmList;
	}

	/**
	 *  班组评分密码保存.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 16:50
	 * @param usernameArr
	 * @param passwordArr
	 * @return boolean
	 * @throw
	 */
	public boolean scbzpfmmSave(String[] usernameArr, String[] passwordArr) throws Exception {

		boolean rs = true;
		if(usernameArr != null){
			DBEncrypt dbEncrypt = new DBEncrypt();
			Encrypt encrypt = new Encrypt();
			for (int i=0;i<usernameArr.length;i++){
				String username = usernameArr[i];
				String password = passwordArr[i];

				if(StringUtils.isNull(password)){
					continue;
				}
				rs = dao.scbzpfmmSave(username,dbEncrypt.eCode(password),encrypt.encrypt(password));
			}
		}

		return rs;
	}

	/**
	 *  获取班级测评成绩总列表（考核记录，含各一级指标总分）.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 09:52
	 * @param model
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
    public List<HashMap<String,String>> getBjzccjzbList(KhpfForm model, User user) throws Exception {

		return dao.getBjzccjzbList(model, user);
    }

	/**
	 *  生成班级测评成绩总表Excel文件数组.
	 *  <p>湖南城市学院</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 10:36
	 * @param resultList
	 * @return java.io.File[]
	 * @throw Exception
	 */
	public File[] getBjzccjzbFiles(List<HashMap<String, String>> resultList) throws Exception {

		List<File> fileList = new ArrayList<File>();
		Map<String,List<Map<String,String>>> classMap = new OtmMapping().setResultMap("bjdm", resultList).getResultMap();

		Collection<List<Map<String,String>>> classCollection = classMap.values(); //相关学生列表集合，以班级为单位
		Iterator<List<Map<String,String>>> iterator = classCollection.iterator();

		while(iterator.hasNext()){
			//设置相关格式
			WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);//设置标题字体
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//设置表头字体
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//设置正文字体

			WritableCellFormat title_cf = new WritableCellFormat(tileFont);//设置标题单元格字体
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//设置正文表头字体
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体
			WritableCellFormat body_bz_cf = new WritableCellFormat(bodyFont);//设置正文单元格字体

			title_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置标题单元格对齐
			title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置标题单元格边框
//		title_cf.setBackground(Colour.YELLOW);	//设置标题背景色

			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//设置表头水平对齐
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

			body_cf.setAlignment(jxl.format.Alignment.CENTRE);//设置正文单元格对齐
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//设置正文单元格边框
			body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
			body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);

			List<Map<String,String>> list = iterator.next();	//班级对应的相关学生列表
			String xymc = list.get(0).get("xymc");	//学院名称
			String zymc = list.get(0).get("zymc");	//专业名称
			String bjdm = list.get(0).get("bjdm");	//班级代码
			String bjmc = list.get(0).get("bjmc");	//班级名称
			String xn = list.get(0).get("xn");		//学年
			String title = "湖南城市学院学生德智体美综合素质测评汇总表";	//标题
			String title_tip = "学院名称："+(xymc==null?"":xymc)+"    "
					              + "专业名称： "+(zymc==null?"":zymc)+"    "
					 			  + "班级名称： "+(bjmc==null?"":bjmc)+"    "
								  + "学年： "+(xn==null?"":xn);

			//画Excel
			String fileName = bjmc+"（"+bjdm+"）"+ ".xls";
			File file = new File(System.getProperty("java.io.tmpdir"),fileName);

			if(!file.exists()){
				file.createNewFile();
			}

			//创建工作簿
			WritableWorkbook wwb = Workbook.createWorkbook(file);

			//创建工作表
			WritableSheet sheet = wwb.createSheet(bjmc, 0);

			//设置各列列宽
			sheet.setColumnView(0, 14);
			sheet.setColumnView(1, 14);
			sheet.setColumnView(2, 14);
			sheet.setColumnView(3, 18);
			sheet.setColumnView(4, 18);
			sheet.setColumnView(5, 14);
			sheet.setColumnView(6, 14);
			sheet.setColumnView(7, 14);
			sheet.setColumnView(8, 14);
			sheet.setColumnView(9, 14);

			//合并单元格
			sheet.mergeCells(0, 0, 9, 0);	//湖南城市学院学生德智体美综合素质测评汇总表
			sheet.mergeCells(0, 1, 9, 1);	//学院 专业 班级 学年

			//创建标题及表头
			Label t_0_0 = new Label(0, 0,title,title_cf);
			Label h_0_1 = new Label(0,1,title_tip,body_cf);

			Label h_0_2 = new Label(0,2,"学籍号",head_cf);
			Label h_1_2 = new Label(1,2,"姓名",head_cf);
			Label h_2_2 = new Label(2,2,"性别",head_cf);
			Label h_3_2 = new Label(3,2,"思想品德素质",head_cf);
			Label h_4_2 = new Label(4,2,"专业文化素质",head_cf);
			Label h_5_2 = new Label(5,2,"身心素质",head_cf);
			Label h_6_2 = new Label(6,2,"能力水平",head_cf);
			Label h_7_2 = new Label(7,2,"扣分",head_cf);
			Label h_8_2 = new Label(8,2,"总分",head_cf);
			Label h_9_2 = new Label(9,2,"班级排名",head_cf);

			sheet.addCell(t_0_0);
			sheet.addCell(h_0_1);

			sheet.addCell(h_0_2);
			sheet.addCell(h_1_2);
			sheet.addCell(h_2_2);
			sheet.addCell(h_3_2);
			sheet.addCell(h_4_2);
			sheet.addCell(h_5_2);
			sheet.addCell(h_6_2);
			sheet.addCell(h_7_2);
			sheet.addCell(h_8_2);
			sheet.addCell(h_9_2);

			//遍历创建单元格
			if(list.size()>0){
				for(int j=0;j<list.size();j++){
					Map<String,String> map = list.get(j);
					Label xjh = new Label(0, j+3, map.get("xjh"), body_cf);	//学籍号
					Label xm = new Label(1, j+3, map.get("xm"), body_cf);		//姓名
					Label xb = new Label(2, j+3, map.get("xb"), body_cf);		//性别
					Label sxpdszf = new Label(3, j+3, map.get("sxpdszf"), body_cf);		//思想品德素质
					Label zywhszf = new Label(4, j+3, map.get("zywhszf"), body_cf);		//专业文化素质
					Label sxszf = new Label(5, j+3, map.get("sxszf"), body_cf);		//身心素质
					Label nlspf = new Label(6, j+3, map.get("nlspf"), body_cf);		//能力水平
					Label kf = new Label(7, j+3, map.get("kf"), body_cf);	//扣分
					Label zf = new Label(8, j+3, map.get("zf"), body_cf);		//总分
					Label pm = new Label(9, j+3, map.get("pm"), body_cf);		//班级排名

					sheet.addCell(xjh);
					sheet.addCell(xm);
					sheet.addCell(xb);
					sheet.addCell(sxpdszf);
					sheet.addCell(zywhszf);
					sheet.addCell(sxszf);
					sheet.addCell(nlspf);
					sheet.addCell(kf);
					sheet.addCell(zf);
					sheet.addCell(pm);
				}
			}
			wwb.write();
			wwb.close();
			fileList.add(file);
		}
		return fileList.toArray(new File[]{});
	}
}
