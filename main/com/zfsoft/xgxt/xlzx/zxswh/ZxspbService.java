/**
 * @部门:学工产品事业部
 * @日期：2013-8-19 下午04:44:04 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import java.util.HashMap;
import java.util.List;
import java.io.OutputStream;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理咨询-咨询师排班管理(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-8-19 下午04:35:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxspbService extends SuperServiceImpl<ZxspbForm, ZxspbDao> {
	
	private ZxspbDao dao = new ZxspbDao();
	private static final String DATE_FORMAT  = "yyyy-mm-dd hh24:mi:ss";
	public ZxspbService() {
		super.setDao(dao);
	}
	

	/**
	 * 根据职工号查询咨询师排班信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getZxspbInfoByZgh(String zgh){
		
		return dao.getZxspbInfoByZgh(zgh);
	}
	
	/**
	 * 根据日期查询排班情况
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoByDate(String date){
		
		return dao.getPbInfoByDate(date);
	}
	/**
	 * 条件查询咨询师排班信息（日历展示方式）
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getZxspbAsRl(String queryDate) throws Exception{
		
		return dao.getZxspbAsRl(queryDate);
	}
	
	/**
	 * 条件查询咨询师排班信息（日历展示方式）
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getZxspbAsRlForXs(String xh,String queryDate) throws Exception{
		
		return dao.getZxspbAsRlForXs(xh,queryDate);
	}
	
	/**
	 * 根据排班编号查询排班信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoById(String id){
		
		return dao.getPbInfoById(id);
	}
	/**
	 * 保存至咨询师排班表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveZxspbInfo(ZxspbForm model)
			throws Exception {
		return dao.saveZxspbInfo(model);
	}
	
	/**
	 * 批量排班
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveBatchZxspbInfo(String[] pbdate,String zgh,String bz)
	throws Exception{
		
		return dao.saveBatchZxspbInfo(pbdate, zgh, bz);
	}
	/**
	 * 删除咨询师排班信息(按主键）
	 */
	public int delZxspbById(String id) throws Exception {
		
		return dao.delZxspbById(id);
	}
	
	/**
	 * 删除咨询师的排班信息
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public int delZxspbByZgh(String[] zgh) throws Exception {
		
		return dao.delZxspbByZgh(zgh);
	}
	/**
	 * 修改咨询师的排班信息
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean updateZxspbInfo(ZxspbForm model) throws Exception{
		
		return dao.updateZxspbInfo(model);
	}
	/**
	 * 根据日期获取对应星期
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public 	HashMap<String, String> getWeekdayByDate(String date){
		
		return dao.getWeekdayByDate(date);
	}
	/**
	 * 获取起止日期内对应所有星期M的日期
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDateByWeekBetweenDate(String startDate,String endDate) throws Exception{
		
		return dao.getDateByWeekBetweenDate(startDate, endDate);
	}
	/**
	 * @描述: 湖南城市学院个性化导出EXCLE
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-6-29 下午01:53:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public void exportConfig_11527(ZxspbForm model,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);//创建一个工作薄，就是整个Excel文档
		WritableSheet ws = wwb.createSheet("Sheet", 0);//创建一个工作表
		ws.setRowView(0, 850);//第1行高度
		ws.setRowView(1, 600);//第2行高度
		//1到7列的宽度
		for (int s = 0; s < 7; s++){ 
			ws.setColumnView(s, 26);
	    }
		//第3行以后的高度
		for (int d = 2; d < 9; d++){ 
			ws.setRowView(d, 900);
	    }
		
		String dd = model.getDqny();//第一列说需要的导出的月份
		
		// 第1行
		WritableCellFormat r1Format = new WritableCellFormat();
		WritableFont r1Font = new WritableFont(WritableFont.createFont("宋体"),18);//字体为宋体，字号18号
		r1Format.setFont(r1Font);//好像是awt中的组件不能设置字体, 但可以更改颜色,字号或加粗斜体等
		r1Font.setBoldStyle(WritableFont.BOLD);//设置字体为（粗体）
		r1Format.setAlignment(Alignment.CENTRE);//数据居中
		ws.mergeCells(0, 0, 6, 0);//合并第一列第一行到第七列第一行
		ws.addCell(new Label(0, 0, dd , r1Format));//显示出第一行中的数据
		
		//调整 第2行的格式
		WritableCellFormat r2Format = new WritableCellFormat();
		WritableFont r2Font = new WritableFont(WritableFont.createFont("宋体"),12);
		r2Format.setFont(r2Font);
		r2Font.setBoldStyle(WritableFont.BOLD);
		r2Format.setAlignment(Alignment.CENTRE);
		
		//显示第二行的内容
		int rownum = 1;
		ws.addCell(new Label(0, rownum,"周日", r2Format));
		ws.addCell(new Label(1, rownum,"周一", r2Format));
		ws.addCell(new Label(2, rownum,"周二", r2Format));
		ws.addCell(new Label(3, rownum,"周三", r2Format));
		ws.addCell(new Label(4, rownum,"周四", r2Format));
		ws.addCell(new Label(5, rownum,"周五", r2Format));
		ws.addCell(new Label(6, rownum,"周六", r2Format));
		rownum++;
		
		// 从第三行开始往下面循环
		WritableCellFormat r3Format = new WritableCellFormat();
		WritableFont r3Font = new WritableFont(WritableFont.createFont("宋体"),11);
		r3Format.setWrap(true);//整行的数据自动换行！！！！
		r3Format.setFont(r3Font);
		r3Format.setAlignment(Alignment.CENTRE);
		r3Font.setBoldStyle(WritableFont.NO_BOLD);
		
		String dqny = model.getDqny().replace("年", "-").replace("月", "");//获取所选择导出的年、月
		String[] sz = dqny.split("-");
		String y =sz[0];
		String m = sz[1];
		int year =Integer.parseInt(y);
		int month =Integer.parseInt(m);
		int daynumber = DateUtils.getMDay(year, month);
		String strMonth = String.valueOf(month);
		if(month < 10){
		    strMonth = "0" + strMonth;
		}
	    int firstnumber = DateUtils.getDayOfWeek_num(year + strMonth + "01");
	    int lastnumber = DateUtils.getDayOfWeek_num(year + strMonth + daynumber);
	    int weeknumber = (daynumber - (7 - firstnumber) - (lastnumber + 1)) / 7; 
	    int day = 1; 

	    String dateMy = year + "-" + strMonth+ "-";
	    String date = "";
	    String zghInfo = "";
	    for (int i = firstnumber-1; i < 7; i++){
			if(day < 10){
				date  =  dateMy + "0" + day;
			} else {
				date  =  dateMy + day;
			}
			zghInfo = getzghInfo(date);
	    	ws.addCell(new Label(i, rownum ,String.valueOf(day)+zghInfo, r3Format));
	        day++; 
	    }
	    rownum++;
	    for (int i = 0; i < weeknumber; i++){ //其他星期 
	        for (int k = 0; k < 7; k++) { 
				if(day < 10){
					date  =  dateMy + "0" + day;
				} else {
					date  =  dateMy + day;
				}
				zghInfo = getzghInfo(date);
	        	ws.addCell(new Label(k, rownum ,String.valueOf(day)+zghInfo, r3Format));
		        day++;
	        } 
	        rownum++;
	    }
	    for (int i = 0; i < lastnumber; i++){
			if(day < 10){
				date  =  dateMy + "0" + day;
			} else {
				date  =  dateMy + day;
			}
			zghInfo = getzghInfo(date);
	    	ws.addCell(new Label(i, rownum ,String.valueOf(day)+zghInfo, r3Format));
	        day++; 
	    }
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}
	/**
	 * @描述: 提取教师信息
	 * @作者： 孟威[工号：1186]
	 * @日期：2016-6-29 下午01:52:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param date
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	private String getzghInfo(String date) throws Exception{
		List<HashMap<String,String>> zghInfoList= getZghInfoList(date);
		String zghInfo = "";
		HashMap<String,String> zghMap = null;
		for(int i =0;i<zghInfoList.size();i++){
			zghMap = zghInfoList.get(i);
			zghInfo +="\r\n";
			zghInfo += zghMap.get("xm").toString();
			zghInfo += "["+zghMap.get("xb").toString()+"]";
			zghInfo += "["+zghMap.get("bmmc").toString()+"]";
			zghInfo += "["+zghMap.get("zgh").toString()+"]";
		}
		return zghInfo;
	}
	/**
	 * 需要被getzghInfo调用
	 */
	private List<HashMap<String,String>> getZghInfoList(String date) throws Exception{
		ZxsglService zxsglSv = new ZxsglService();
		List<HashMap<String,String>> zghInfoList= new ArrayList<HashMap<String,String>>();
		HashMap<String, String> zxspbInfo = dao.getPbInfoByDate(date);
		if(zxspbInfo!=null && zxspbInfo.size()>0){
			String _zgh = zxspbInfo.get("zgh");
			if(!StringUtil.isNull(_zgh)){
				String[] zgh = _zgh.split(",");
				zghInfoList = zxsglSv.getZxsInfoByZgh(zgh);
			}
		}
		return zghInfoList;
	}
	
	/**
	 * 
	 * @描述:获取排班方式
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 上午10:37:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPbfs(){
		return dao.getPbfs();
	}
	
	/**
	 * 
	 * @描述: 获取按时间段排序老师基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午02:11:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxspbList(String pbdate){
		return dao.getZxspbList(pbdate);
	}
	
	/**
	 * 
	 * @描述: 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午02:33:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxspbListSjd(String pbdate){
		return dao.getZxspbListSjd(pbdate);
	}
	
	/**
	 * 
	 * @描述: 按时间段排班组建数据数组
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午03:05:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * JSONObject 返回类型 
	 * @throws
	 */
	public JSONArray createSjdPbInitList(String pbdate){
		List<HashMap<String, String>> pbList = this.getZxspbList(pbdate);
		List<HashMap<String, String>> pbListDetail = this.getZxspbListSjd(pbdate);
		List<HashMap<String, Object>> zhList = new ArrayList<HashMap<String,Object>>();
		for (int i = 0; i < pbList.size(); i++) {
			List<HashMap<String, String>> tempList = new ArrayList<HashMap<String,String>>();
			HashMap<String, String> tempCompareMap = pbList.get(i);
			if(pbListDetail != null && !pbListDetail.isEmpty()){
				for (int j = 0; j < pbListDetail.size(); j++) {
					HashMap<String, String> tempMap = pbListDetail.get(j);
					if(tempCompareMap.get("zgh").equals(tempMap.get("zgh"))){
						tempList.add(tempMap);
					}
				}
			}
			HashMap<String, Object> tempZhMap = new HashMap<String, Object>();
			tempZhMap.putAll(tempCompareMap);
			tempZhMap.put("sjdList", tempList);
			zhList.add(tempZhMap);
		}
		return  JSONArray.fromObject(zhList);
	}
	
	/**
	 * 
	 * @描述: 获取时间段List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午05:21:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSjdList(){
		return dao.getSjdList();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存排班信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-22 下午02:25:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean savePbxxb(ZxspbForm zxspbform) throws Exception{
		String id = zxspbform.getId();
		String createsj = GetTime.getTimeByFormat(DATE_FORMAT);
		String bz = zxspbform.getBz();
		String[] zghs = zxspbform.getZghs();
		String[] xqdms = zxspbform.getXqdm();
		String[] sjddm = zxspbform.getSjdm();
		String pbdate = zxspbform.getPbdate();
		String sfCopypb = zxspbform.getSfCopyPb();
		String startDate = (StringUtils.isNotNull(sfCopypb)&&"1".equals(sfCopypb))?zxspbform.getStartDate():pbdate;
		String endDate = (StringUtils.isNotNull(sfCopypb) &&"1".equals(sfCopypb))?zxspbform.getEndDate():pbdate;
		List<HashMap<String, String>> pbdateList = dao.getDateByWeekBetweenDate(startDate, endDate);
		boolean rs = true;
		boolean isAdd = StringUtils.isNull(id);
		//如果id为空，是增加操作，不执行删除操作
		if(!dao.checkIsNotExistsInPbb(pbdateList,id)){
			throw new SystemException(MessageKey.XG_XLZX_PB_ADD_REPEAT);
		}
		//组合参数
		List<String[]> pbDelList = new ArrayList<String[]>();
		List<String[]> sjdDelList = new ArrayList<String[]>();
		List<String[]> sjdSaveList = new ArrayList<String[]>();
		List<String[]> pbSaveList = new ArrayList<String[]>();
		for (int i = 0; i < pbdateList.size(); i++) {
			String temppbdate = pbdateList.get(i).get("tdate");
			String tempid = (temppbdate.equals(pbdate)&&StringUtils.isNotNull(id)) ? id : UniqID.getInstance().getUniqIDHash().toUpperCase();
			pbDelList.add(new String[]{temppbdate});
			pbSaveList.add(new String[]{tempid,temppbdate,null,createsj,bz});
			for (int j = 0; j < zghs.length; j++) {
				sjdDelList.add(new String[]{zghs[j],tempid});
				if(sjddm != null && sjddm.length > 0 && !"none".equals(sjddm[j])){
				   String temsjdStr = sjddm[j];
					if(StringUtils.isNotNull(temsjdStr)){
						String[] tempSjdArr = temsjdStr.split(";");
						for (int k = 0; k < tempSjdArr.length; k++) {
							//北京中医药个性化字段校区代码，非北京中医药该字段取空
							String tempXqdm = ("10026").equals(Base.xxdm)?xqdms[j]:null;
							if("10026".equals(Base.xxdm)){
								sjdSaveList.add(new String[]{tempid,zghs[j],tempXqdm,tempSjdArr[k]});
							}else{
								sjdSaveList.add(new String[]{tempid,zghs[j],tempXqdm,tempSjdArr[k]});
							}
							
						}
					}
				}
			}
		}
		//操作数据库
		rs = this.savePbxxIntoDb(pbDelList, sjdDelList, sjdSaveList, pbSaveList, isAdd);
		return rs;
		
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 操作排班信息数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-23 上午10:31:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean savePbxxIntoDb(List<String[]>pbDelList,List<String[]> sjdDelList,
			List<String[]> sjdSaveList,List<String[]> pbSaveList,boolean isAdd) throws Exception{
		//如果是增加操作，无需执行删除操作
		boolean rs = true;
		if(!isAdd){
			rs = dao.delPbxx(sjdDelList, pbDelList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		
		if(isAdd && (sjdSaveList == null || sjdSaveList.size() == 0)){
			throw new SystemException(MessageKey.XG_XLZX_ADD_PBDATE_CAN_NOTNULL);//时间段必须勾选
		}else if((sjdSaveList != null && sjdSaveList.size() > 0)){
			rs = dao.saveZxsPb(sjdSaveList, pbSaveList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			return rs;
		}
		return rs;
		
		
	}
	
	/**
	 * 
	 * @描述: 学生社区预约查询(按时间段)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-24 下午03:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param queryDate
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
    public List<HashMap<String, String>> getZxspbAsRlForXsSjd(String xh,String queryDate) throws Exception{
		return dao.getZxspbAsRlForXsSjd(xh, queryDate);
		
	}
	
    /**
     * 
     * @描述: 时间段排班
     * @作者：喻鑫源[工号：1206]
     * @日期：2017-3-27 上午10:07:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getZxsjbxxForSjdPb(String pbdate){
    	return dao.getZxsjbxxForSjdPb(pbdate);
    }
    
    /**
	 * 根据日期查询排班情况[时间段预约]
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoByDateForsjd(String date){
		return dao.getPbInfoByDateForsjd(date);
	}
	
	/**
	 * 
	 * @描述:获取校区
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-31 下午07:06:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String date,String zgh){
		return dao.getXqmc(date, zgh);
	}
}