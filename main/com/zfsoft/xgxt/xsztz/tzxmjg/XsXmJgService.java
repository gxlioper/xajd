/**
 * @部门:学工产品事业部
 * @日期：2015-7-17 上午09:55:44 
 */  
package com.zfsoft.xgxt.xsztz.tzxmjg;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-17 上午09:55:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsXmJgService extends SuperServiceImpl<XsXmJgForm, XsXmJgDao> {
	XsXmJgDao dao = new XsXmJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述:获取申请审核开关
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-10 下午04:29:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
//	public String[] getSqShKg() throws Exception{
//		return dao.getSqShKg();
//	}
	
	/**
	 * 
	 * @描述:重复判断
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-10 下午04:37:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExistForSave(XsXmJgForm model) {
		return dao.checkExistForSave(model);
	}
	
	
	public List<HashMap<String, String>> getXmSelectList(String xh) throws Exception {
		return dao.getXmlist(xh);
	}
	
	
	
	//活动信息
	public HashMap<String, String> getHdMap(String xmdm,String xn,String xq){
		return dao.getHdMap(xmdm,xn,xq);
	}
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @描述:奖项颁发保存
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-20 下午01:49:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJxbf(XsXmJgForm myForm) throws Exception{
		boolean result = true;
		String[] sjlys = myForm.getSjlys().split(",");
		String[] xhs = myForm.getXhs().split(",");
		String[] ylzd1s= myForm.getYlzd1s().split(",");
		String[] ylzd2s= myForm.getYlzd2s().split(",");
		List<String[]> sqxsList = new ArrayList<String[]>();
		List<String[]> sqxslcList = new ArrayList<String[]>();//流程数据
		String[] sqxs= null;
		for (int i = 0; i < xhs.length; i++) {
			sqxs = new String[6];
			sqxs[0]="no".equals(ylzd1s[i])?"":ylzd1s[i];
			sqxs[1]=ylzd2s[i];
			sqxs[2]=myForm.getXmdm();
			sqxs[3]=myForm.getXn();
			sqxs[4]=myForm.getXq();
			sqxs[5]=xhs[i];
			
			if("1".equals(sjlys[i])){
				sqxslcList.add(sqxs);
			}else{
				sqxsList.add(sqxs);
			}
			
		}
		//流程数据更新奖项
		result=dao.updateJxxx(sqxslcList);
		//新增学生插入结果库
		dao.delXssqjg(myForm);
		result=dao.insertJxxx(sqxsList);
		
		return result;
	}
	
	public List<HashMap<String, String>> grxfcxList(XsXmJgForm myForm,User user) throws Exception{
		return dao.grxfcxList(myForm,user);
	}
	/**
	 * @描述: 获取项目级别、总学分、是否合格、统计判断
	 * @作者： 孟威[工号：1186]
	 * @日期：2015-11-24 下午05:23:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSztzhdxfList(String xh) {
		return dao.getSztzhdxf(xh);
	}
	/**
	 * @描述: 空行List
	 * @作者： 孟威[工号：1186]
	 * @日期：2015-11-24 下午05:24:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param size
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	public File getXscjWord(String xh,HttpServletRequest request)
	throws Exception {
		XsxxService xsxxService = new XsxxService();
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> dyxqMap = new HashMap<String, String>();
		HashMap<String, String> dexqMap = new HashMap<String, String>();
		String xnxq = null;
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		if(null==xsxxMap.get("rxrq")||"".equals(xsxxMap.get("rxrq"))){
			return null ;
		}
		int rxnd = Integer.parseInt(xsxxMap.get("rxrq").substring(0, 4));
		int k=1;
		for (int i = rxnd; i < rxnd + 4; i++) {
			dyxqMap = new HashMap<String, String>();
			dexqMap = new HashMap<String, String>();
			xnxq = String.valueOf(i) + "-" + String.valueOf(i + 1);
			dyxqMap.put("xnxq", xnxq+"01");
			aList.add(dyxqMap);
			dexqMap.put("xnxq", xnxq+"02");
			aList.add(dexqMap);
			data.put("xnxq"+k, xnxq);
			k++;
		}
		data.putAll(xsxxMap);
		//学生照片
		InputStream is = xsxxService.getPhotoStream(xsxxMap.get("xh"));
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (StringUtil.isNull(photo)){
			//取默认照片 
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		if(photo == null){
			photo = "";
		}
		data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
		data.put("photo", photo);
		List<HashMap<String,String>> tzcjList = dao.getTzcjList(aList,xsxxMap.get("xh"));
		Double zcj1=0.0;
		Double zcj2=0.0;
		Double zcj3=0.0;
		Double zcj4=0.0;
		Double zcj5=0.0;
		Double zcj6=0.0;
		Double zcj7=0.0;
		Double zcj8=0.0;
		for (int i = 0; i < tzcjList.size(); i++) {
			zcj1+=Double.parseDouble(tzcjList.get(i).get("xmfs0"));
			zcj2+=Double.parseDouble(tzcjList.get(i).get("xmfs1"));
			zcj3+=Double.parseDouble(tzcjList.get(i).get("xmfs2"));
			zcj4+=Double.parseDouble(tzcjList.get(i).get("xmfs3"));
			zcj5+=Double.parseDouble(tzcjList.get(i).get("xmfs4"));
			zcj6+=Double.parseDouble(tzcjList.get(i).get("xmfs5"));
			zcj7+=Double.parseDouble(tzcjList.get(i).get("xmfs6"));
			zcj8+=Double.parseDouble(tzcjList.get(i).get("xmfs7"));
		}
		data.put("zcj1", zcj1);
		data.put("zcj2", zcj2);
		data.put("zcj3", zcj3);
		data.put("zcj4", zcj4);
		data.put("zcj5", zcj5);
		data.put("zcj6", zcj6);
		data.put("zcj7", zcj7);
		data.put("zcj8", zcj8);
		data.put("tzcjList", tzcjList);
		File file  = FreeMarkerUtil.getWordFile(data, "classpath://templates//sztz", "sztz_cjd.xml", xsxxMap.get("xh")
				+ "-" + xsxxMap.get("xm"));
		return file;
		
		}
	
	/**
	 * 
	 * @描述: 获取个人总学分查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-19 下午04:00:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getgrZxf(String xh){
		return dao.getgrZxf(xh);
	}
}