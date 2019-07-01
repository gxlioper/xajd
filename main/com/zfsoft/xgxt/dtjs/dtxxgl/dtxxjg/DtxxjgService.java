/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxsq.DtxxsqForm;
import com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz.ShlcpzDao;
import com.zfsoft.xgxt.rcsw.kqgl.zjsy.ZjsyKqForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 请假管理模块
 * @类功能描述: 请假申请service
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class DtxxjgService extends SuperServiceImpl<DtxxjgForm, DtxxjgDao> {

	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID = "-1";
	/**
	 * 结果库数据
	 */
	public static String _SJLY_JGK = "0";
	/**
	 * 申请数据
	 */
	public static String _SJLY_SQ = "1";
	DtxxjgDao dao = new DtxxjgDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public DtxxjgService() {
		this.setDao(dao);
	}

	/**
	 * 
	 * @描述:获得个人阶段信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 上午10:16:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getGrJdxx(String xh) throws Exception {
		List<HashMap<String, String>> newlist = new ArrayList<HashMap<String, String>>();
		ShlcpzDao sd = new ShlcpzDao();
		List<HashMap<String, String>> list = sd.getGrJdxx(xh);
		// newlist.addAll(list.subList(0, 5));
		// Collections.reverse(newlist);
		// newlist.addAll(list.subList(5,list.size()));
		// Collections.reverse(list);
		return list;
	}

	/**
	 * 
	 * @描述:获取个人阶段的党团结果信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 下午03:29:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param jddm
	 * @return HashMap<String,String> 返回类型
	 */

	public HashMap<String, String> getGrDtjgxx(String xh, String jddm) {
		return dao.getGrDtjgxx(xh, jddm);
	}

	/**
	 * 
	 * @描述:保存申请数据到结果库
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-29 上午10:29:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean saveForDtxxsq(DtxxsqForm model) throws Exception {
		DtxxjgForm df = new DtxxjgForm();
		df.setSqid(model.getDtxxsqid());
		df.setSjly(_SJLY_SQ);
		df.setJddm(model.getJddm());
		df.setGrxj(model.getGrxj());
		df.setKssj(model.getSqsj());
		df.setXh(model.getXh());
		df.setZd3(model.getZd3());
		//if("11527".equalsIgnoreCase(Base.xxdm)) {
		df.setZd5(model.getZd5()); // 附件
		//}
		df.setJlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		df.setKssj(GetTime.getTimeByFormat("yyyy-mm-dd"));
		HashMap<String, String> xsdtxx =dao.getGrDtjgxx(model.getXh(), model.getJddm());
		if(!xsdtxx.isEmpty()){
			df.setDtxxjgid(df.getXh()+df.getJddm());
			return runUpdate(df);
		}
		else{
			return runInsert(df);
		}
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:保存
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public String save(DtxxjgForm model) throws Exception {
		String jddmStr = model.getJddmstr();
		String kssjStr = model.getKssjstr();
		String grxjStr = model.getGrxjstr();
		String zd5Str = model.getZd5str();
		String dtxxjgidStr = model.getDtxxjgidstr();
		String zd1Str = model.getZd1str();
		String zd2Str = model.getZd2str();
		String zd3Str = model.getZd3str();
		String zd8Str = model.getZd8str();
		String zd9Str = model.getZd9str();
		String zd10Str = model.getZd10str();
		DtxxsqService sqService = new DtxxsqService();
		XsxxService xsxxService = new XsxxService();
		HashMap<String, String> xsjbxx = xsxxService
				.getXsjbxxMoreForDtgl(model.getXh());
		int age = sqService.getAge(xsjbxx.get("csrq"));
		if (StringUtils.isNotNull(jddmStr) && StringUtils.isNotNull(kssjStr)
				&& StringUtils.isNotNull(grxjStr)&&StringUtils.isNotNull(zd1Str)
				&&StringUtils.isNotNull(zd2Str)) {
			String[] jddm = jddmStr.split(",");
			String[] kssj = kssjStr.split(",");
			String[] grxj = grxjStr.split(",");
			String[] zd5 = zd5Str.split(",");
			String[] dtxxjgid = dtxxjgidStr.split(",");
			String[] zd1 = zd1Str.split(",");
			String[] zd2 = zd2Str.split(",");
			String[] zd3 = zd3Str.split(",");
			String[] zd8 = zd8Str.split(",");
			String[] zd9 = zd9Str.split(",");
			String[] zd10 = zd10Str.split(",");
			// 循环插入
			for (int i = 0; i < jddm.length; i++) {
				model.setJddm(formatStr(jddm[i]));
				model.setKssj(formatStr(kssj[i]));
				model.setGrxj(formatStr(grxj[i]));
				model.setZd5(formatStr(zd5[i]));
				model.setDtxxjgid(formatStr(dtxxjgid[i]));
				model.setSjly(_SJLY_JGK);
				model.setJlsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
				model.setZd1(formatStr(zd1[i]));
				model.setZd2(formatStr(zd2[i]));
				model.setZd3(formatStr(zd3[i]));
				model.setZd8(formatStr(zd8[i]));
				model.setZd9(formatStr(zd9[i]));
				model.setZd10(formatStr(zd10[i]));
				String jdmc = sqService.getJdmc(model.getJddm());
				if(StringUtils.equals(jdmc,"入党积极分子") && age<18){
					return "保存失败，入党积极分子申请必须年满18岁！";
				}
				if(StringUtils.equals(jdmc,"预备党员") && age<19){
					return "保存失败，预备党员申请必须年满19岁！";
				}
				if (!saveOrUpdate(model)) {
					return "保存失败！";
				}
			}
		}
		return "保存成功！";
	}

	public boolean saveOrUpdate(DtxxjgForm model) throws Exception {
		if (StringUtils.isNull(model.getJddm())) {
			// 如果阶段代码为空则直接跳过，不进行增加或修改操作
			return true;
			// 如果没有开始时间也没有结束时间
		} else if (StringUtils.isNull(model.getKssj())
				&& StringUtils.isNull(model.getGrxj())) {
			// 如果没有主id值则直接返回，有则进行删除
			if (StringUtils.isNotNull(model.getDtxxjgid())) {
				return runDelete(new String[] { model.getDtxxjgid() }) > 0 ;
			}
			return true;
		}
		// 存在住id执行修改
		if (StringUtils.isNotNull(model.getDtxxjgid())) {
			return runUpdate(model);
		} else {
			return runInsert(model);
		}
	}

	/**
	 * 
	 * @描述:格式化字符串（针对前台传递，-1认为空数据）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-28 下午07:37:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return boolean 返回类型
	 * @throws
	 */
	private String formatStr(String str) {
		if (!StringUtils.isNotNull(str)) {
			return null;
		} else if (str.equals("-1")) {
			return null;
		}
		return str;
	}

	@Override
	public DtxxjgForm getModel(DtxxjgForm t) throws Exception {
		t = dao.getModel(t);
		return t;
	}

	@Override
	public DtxxjgForm getModel(String keyValue) throws Exception {
		DtxxjgForm t = new DtxxjgForm();
		t.setDtxxjgid(keyValue);
		return getModel(t);
	}

	/**
	 * 
	 * @描述:获取结果管理阶段信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-29 上午10:10:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dtxxjgid
	 * @return
	 * @throws Exception
	 *             String[] 返回类型
	 */
	public String[] getJgForJdxx(String dtxxjgid) throws Exception {
		List<String> dtxxjgidForJdxx = new ArrayList<String>();
		DtxxjgForm df = getModel(dtxxjgid);
		// 获取关联阶段结果信息
		List<HashMap<String, String>> list = dao.getDtxxjgJdxx(df.getXh());
		for (HashMap<String, String> hm : list) {
			dtxxjgidForJdxx.add(hm.get("xh")+hm.get("jddm"));
		}
		return dtxxjgidForJdxx.toArray(new String[] {});
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:31:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] 返回类型 String数组 0为成功删除条数为不能删除的
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		String[] nowIds;// 当前要删除的ids

		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// 可删除的id集合

		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if (null == ids || ids.length <= 0) {
			return null;
		}
		int unDelXsts = 0;// 不能删除学生条数
		for (String id : ids) {
			// 获取此结果信息学生所有相关阶段结果信息
			nowIds = getJgForJdxx(id);
			for (String str : nowIds) {
				if (!isCanDel(str)) {// 如果有任何一个阶段信息不能删除 则不允许删除
					HashMap<String, String> hm = getAllProperty(str);
					noDel.append(hm.get("xh"));
					noDel.append("&nbsp;");
					noDel.append(hm.get("xm"));
					noDel.append(",</br>");
					isHaveNoId = true;
					unDelXsts++;
					break;
				} else {
					delId.add(str);
				}
			}
		}

		int sjsc = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		int i = ids.length - unDelXsts;
		String str = noDel.toString();
		// 去除最后多余逗号
		str = isHaveNoId ? str : _BCZSCID;
		return new String[] { String.valueOf(i), str };
	}

	/**
	 * 
	 * @描述:获取其他相关信息（getModel的扩展）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-30 上午11:18:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getAllProperty(String id) throws Exception {
		HashMap<String, String> property = null;
		DtxxjgForm hm = new DtxxjgForm();
		hm.setDtxxjgid(id);
		List<HashMap<String, String>> list = getAllList(hm);
		if (null != list && list.size() == 1) {// 按主键检索 只有一条数据 才为正常数据
			property = list.get(0);
		}
		return property;
	}

	/**
	 * 
	 * @描述:是否可以删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-10-29 上午09:53:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkvalue
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean isCanDel(String pkvalue) throws Exception {
		DtxxjgForm df = getModel(pkvalue);
		// 存在对应申请信息，为学生申请过来的数据 不允删除
		if (df != null && StringUtils.isNotNull(df.getSqid())) {
			return false;
		}
		return true;
	}

	/**
	 * 查询学生个人信息
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}
	
	public File getExportFile(DtxxjgForm model,User user) throws Exception{
		//生成Excle文件
		File tempxls = new File(System.getProperty("java.io.tmpdir")+"/"+System.currentTimeMillis()+".xls");
		WritableWorkbook book = Workbook.createWorkbook(tempxls);
		WritableSheet sheet = book.createSheet("学生发展对象", 0);
		
		//设置标题样式
		WritableCellFormat title = new WritableCellFormat(); // 构造单元格格式
		title = ExcelMethods.getWcf(WritableFont.ARIAL,20, true, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.NONE);
		
		//设置表头样式
		WritableCellFormat top = new WritableCellFormat(); // 构造单元格格式
		top = ExcelMethods.getWcf(WritableFont.ARIAL,12, false, Alignment.LEFT, VerticalAlignment.CENTRE, false, Border.NONE);
		
		//设置内容样式
		WritableCellFormat content = new WritableCellFormat(); // 构造单元格格式
		content = ExcelMethods.getWcf(WritableFont.ARIAL,10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
        
		sheet.mergeCells(0, 0, 15, 0);
		sheet.setColumnView(9, 29);
		addCell(sheet,0,0,title,"拟发展的中共预备党员名册");
		
		//拼接中部的信息
		StringBuilder topString = new StringBuilder();
		topString.append("    总支名称（盖章）:                                                                               填表人：");
		topString.append(user.getRealName());
		topString.append("                                                              ");
		topString.append("填表时间:");
		topString.append( DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
		sheet.mergeCells(0, 1, 15, 1);
		addCell(sheet,0,1,top,topString.toString());
		addCell(sheet,0,2,content,"序号");
		addCell(sheet,1,2,content,"姓名");
		addCell(sheet,2,2,content,"性别");
		addCell(sheet,3,2,content,"民族");
		addCell(sheet,4,2,content,"身份证号");
		addCell(sheet,5,2,content,"籍贯");
		addCell(sheet,6,2,content,"所在班级(或部门)");
		addCell(sheet,7,2,content,"现任职务");
		addCell(sheet,8,2,content,"每学期成绩排名/班级总人数");
		addCell(sheet,9,2,content,"获奖情况");
		addCell(sheet,10,2,content,"申请入党时间");
		addCell(sheet,11,2,content,"列为积极分子时间");
		addCell(sheet,12,2,content,"何时何地参加党校培训");
		addCell(sheet,13,2,content,"联系人");
		addCell(sheet,14,2,content,"是否有不及格课程");
		addCell(sheet,15,2,content,"志愿书编号");
		int xh = 1;
		int rindex = 3;
		int cindex = 0;
		List<HashMap<String,String>> mzlist = getExportList(model,user);
		for(int i=0;i<mzlist.size();i++){
			addCell(sheet,cindex++,rindex,content,String.valueOf(xh++));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xm"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xb"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("mzmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("sfzh"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("jgmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("bjmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zwmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xqpm"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("xmmc"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("rdsqsj"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("rdjjfssj"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zd8"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zd9"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("isbjg"));
			addCell(sheet,cindex++,rindex,content,mzlist.get(i).get("zd10"));
			rindex++;
			cindex=0;
		}
		book.write();
		book.close();
		return tempxls;
	}
	
	public void addCell(WritableSheet sheet, int c, int r, WritableCellFormat cellFormat, String str) throws RowsExceededException, WriteException{
		Label label = new Label(c,r,str);
		label.setCellFormat(cellFormat);
		sheet.addCell(label);
	}
	
	/**
	 * 
	 * @描述;党团信息与其他信息的集合
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-6-25 上午10:07:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getExportList(DtxxjgForm model,User user) throws Exception{
		List<HashMap<String,String>> mzlist = dao.getMcExportData(model, user);
		List<HashMap<String,String>> infolist = dao.getMcInfoData(model, user);
		for(int i =0;i<mzlist.size();i++){
			for(int j =0;j<infolist.size();j++){
				if(mzlist.get(i).get("xh").equals(infolist.get(j).get("xh"))){
					mzlist.get(i).put("xmmc", infolist.get(j).get("xmmc"));
					mzlist.get(i).put("isbjg", infolist.get(j).get("isbjg"));
					mzlist.get(i).put("zwmc", infolist.get(j).get("zwmc"));
					mzlist.get(i).put("xqpm", infolist.get(j).get("xqpm"));
				}
			}
		}
		
		return mzlist;
	}
	
	
	/**
	 * 
	 * @描述: 重庆三峡医高专-预审发展对象
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-23 上午10:47:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxYsfzList(String xymc) throws Exception{
		return dao.getDtxxYsfzList(xymc);
	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专-预备党员
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-24 下午01:52:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xymc
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxYbdyList(String xymc) throws Exception{
		return dao.getDtxxYbdyList(xymc);
	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专-预备党员转正（正式党员）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-24 下午03:37:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xymc
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxZsdyList(String xymc) throws Exception{
		return dao.getDtxxZsdyList(xymc);		
	}
	
	/**
	 * @描述：江西航空个性化导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年4月6日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getExportList_13871(DtxxjgForm t, User user)throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getExportList_13871(t, user);
	}

	/**
	* @description: TODO 西安交大-积极分子导出
 	* @param model
	* @param user
	* @return java.io.File
	* @author Wang ChenHui
	* @date 2019/5/24 13:03
	*/
	public List<HashMap<String,String>> getJjfzList(DtxxjgForm model, User user) throws Exception {
		return dao.getDtxxJjfzList(model,user);
	}

	/**
	* @description: TODO 预备党员接收登记
	* @param model
	* @param user
	* @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	* @author Wang ChenHui
	* @date 2019/5/24 15:09
	*/
	public List<HashMap<String,String>> getFzdxList(DtxxjgForm model, User user) throws Exception{
		return dao.getDtxxFzdxList(model,user);
	}

	/**
	* @description: TODO 预备党员接收登记
	* @param model
	* @param user
	* @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	* @author Wang ChenHui
	* @date 2019/5/24 15:09
	*/
	public List<HashMap<String,String>> getYbdyList(DtxxjgForm model, User user) throws Exception{
		return dao.getYbdyList(model,user);
	}

	/**
	* @description: TODO 预备党员转正请示列表
	* @param model
	* @param user
	* @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	* @author Wang ChenHui
	* @date 2019/5/24 15:02
	*/
	public List<HashMap<String,String>> getZsdyList(DtxxjgForm model, User user) throws Exception{
		return dao.getZsdyList(model,user);
	}
}
