/**
 * @部门:学工产品事业部
 * @日期：2014-8-5 上午11:41:11 
 */  
package com.zfsoft.xgxt.wjcf.cfjg;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.ctc.wstx.util.DataUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import common.GlobalsVariable;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-8-5 上午11:41:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjgService extends SuperServiceImpl<CfjgForm, CfjgDao>{
	
	@Override
	public List<HashMap<String, String>> getPageList(CfjgForm t, User user)
			throws Exception {
		return getCfjgList(super.getPageList(t, user));
	}
	public List<HashMap<String, String>> getCfjgList(
			List<HashMap<String, String>> list) {
		return list;
	}
	/**
	 * 新增保存
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean saveCfsb(CfjgForm form) throws Exception{
		
		form.setCflbmc(dao.cflbmc(form.getCflbdm()));
		form.setCfyymc(dao.cfyymc(form.getCfyydm()));
		return dao.saveCfsj(form);
	}
	/**
	 * 
	 * @描述:上海中侨处分通知书流水号获取
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-26 上午10:13:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getLsh(CfjgForm model) {
		StringBuffer lshstr=new StringBuffer();
		List<HashMap<String, String>> lshList = dao.getLsh(model);
		if (lshList.size() > 0) {
			int lsh=0;
			lsh = Integer.parseInt(Base.isNull(lshList.get(0).get("cflsh"))?"0":lshList.get(0).get("cflsh"))+1;
			if(lsh>9&&lsh<100){
				lshstr.append("0").append(lsh);
			}
			else if(lsh>99){
				lshstr.append(lsh);
			}
			else{
				lshstr.append("00").append(lsh);
			}
		} else {
			lshstr.append("001");
		}
		return lshstr.toString();
	}
	
	public String getLsh2(CfjgForm model) {
		return dao.getLsh2(model);
	}
	
	/**
	 * 查看处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfsjwhCk(String cfid) throws Exception{
		CfjgDao dao = new CfjgDao();
		return dao.cfsjwhCk(cfid);
	}
	/**
	 * 
	 * @描述:查询是否党团员和职务
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-1 上午11:09:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZwAndZzmm(String xh) throws Exception{
		CfjgDao dao = new CfjgDao();
		return dao.getZwAndZzmm(xh);
	}
	
	/**
	 * 删除处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhSc(String[] cfid) throws Exception {
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : cfid){
			if (StringUtils.isNull(str)) continue;
			params.add(new String[]{str});
		}
		CfjgDao dao = new CfjgDao();
		boolean f =dao.cfsjwhSc(params); 
		f = dao.cfSbSsJcSc(cfid);
		return f;
	}
	
	/**
	 * 修改处分信息
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhXg(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		form.setCflbmc(dao.cflbmc(form.getCflbdm()));
	//	form.setCfyymc(dao.cfyymc(form.getCfyydm()));
		return dao.cfsjwhXg(form);
	}
	
	/**
	 * 保存处分申诉信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfssjgBc(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfssjgBc(form);
	}
	
	/**
	 * 保存处分解除信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcjgBc(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfjcjgBc(form);
	}
	
	/**
	 * 保存处分终止信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfzzjgBc(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfzzjgBc(form);
	}

	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public InputStream fjCx(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	
	/**
	 * 通过学号查询学生处分列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuWjcfList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "违纪处分");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuWjcfAllList(xh));
		rs.add(map);
		return rs;
	}
	
	/**
	 * 通过学号查询违纪处分列表
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuWjcfAllList(String xh) {
		CfjgDao dao = new CfjgDao();
		String[] title = {"学年", "学期", "处分类别", "处分原因", "处分时间", "处分文号", "最终结果"};
		List<String[]> rs = new ArrayList<String[]>();
		rs.addAll(dao.getStuWjcfList(xh));
		rs.add(0, title);
		return rs;
	}
	/**
	 * 数据维护自定义导出
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getCfjgExportList(CfjgForm model,User user) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.getCfjgExportList(model,user);
	}
	/**
	 * 
	 * @描述:获取学年列表
	 * @作者：张昌路[工号：982]
	 * @日期：2015-5-4 下午03:19:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public  List<HashMap<String, String>> getXnndList(){
		DAO dao = DAO.getInstance();
		return dao.getXnndList();
		
	}
	
	public String getWjxxByXhXnXq(String xh,String xn,String xq) {
		return dao.getWjxxByXhXnXq(xh, xn, xq);
	}
	/** 
	 * @描述:走审核流程的处分结果修改
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-16 上午11:24:04
	 * @param myForm
	 */
	public boolean cfsjshlcXg(CfjgForm myForm) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfsjshlcXg(myForm);
	}
	/** 
	 * @描述:青岛酒店管理职业技术学院生成默认处分文号：年份+4位顺序号
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月27日 下午2:37:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfjgForm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getDefaultCfwhFor13011() {
		String year = DateUtils.getYear();
		//'^2017[0-9]{4}$'
		String regexp = "^"+year+"[0-9]{4}$";
		String cfwhseed = dao.getDefaultCfwhFor13011(regexp);
		
		String defaultCfwh = year+"000".substring(0,4-cfwhseed.length())+cfwhseed;
		return defaultCfwh;
	}
	
	/** 
	 * @描述:根据处分id数组，查询处分结果信息（列表）
	 * （乌海职业技术学院：处分决定书下载）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月15日 上午10:21:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgList(String [] cfids) {
		
		return dao.getCfjgList(cfids);
	}
	
	/** 
	 * @描述:根据处分id数组，查询处分结果信息，包含班主任、政治面貌等信息（列表）
	 * （乌海职业技术学院：处分审批表下载）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月16日 下午2:36:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgMoreList(String[] cfids) {
		
		return dao.getCfjgMoreList(cfids);
	}
	
	/** 
	 * @描述:根据处分类别和处分文号合并处分结果记录，即处分类别和处分文号相同加入同一List，
	 * 		  各List以处分文号和类别为key存入Map
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月15日 上午10:33:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Map<String,List<HashMap<String,String>>> 返回类型 
	 * @throws 
	 */
	public Map<String, List<HashMap<String, String>>> getCfjgListMap(List<HashMap<String,String>> cfjgList) {
		
		Map<String, List<HashMap<String, String>>> cfjgListMap = new HashMap<String, List<HashMap<String, String>>>();
		
		for(HashMap<String,String> cfjg:cfjgList){
			String key = cfjg.get("cflbdm")+cfjg.get("cfwh");
			List<HashMap<String,String>> list = cfjgListMap.get(key);
			if(list==null){
				list = new ArrayList<HashMap<String, String>>();
				cfjgListMap.put(key, list);
			}
			list.add(cfjg);
		}
		
		return cfjgListMap;
	}
	/** 
	 * @描述:根据合并记录后的map，生成word文件数组：处分决定书
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月15日 上午10:42:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfjgListMap
	 * @return
	 * File [] 返回类型 
	 * @throws 
	 */
	public File[] getCfjdsFiles(Map<String, List<HashMap<String, String>>> cfjgListMap) {
		
		List<File> fileList = new ArrayList<File>();
		
		Collection<List<HashMap<String, String>>> collection = cfjgListMap.values();
		for(List<HashMap<String, String>> list:collection){
			HashMap<String,Object> data = new HashMap<String,Object>();
			//姓名列表
			List<String> nameList = new LinkedList<String>();
			//分别违反第几条，一般是相同的
			Set<String> djtSet = new LinkedHashSet<String>();
			//文本转义
			for(int i=0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				map.put("cflbmc", HtmlUtil.xmlZy(map.get("cflbmc")));
				map.put("cfwh", HtmlUtil.xmlZy(map.get("cfwh")));
				map.put("wjssjg", HtmlUtil.xmlZy(map.get("wjssjg")));
				map.put("cfyymc", HtmlUtil.xmlZy(map.get("cfyymc")));
				
				if(StringUtils.isNotNull(map.get("xm"))){
					nameList.add(map.get("xm"));
				}
				if(StringUtils.isNotNull(map.get("djt"))){
					djtSet.add(map.get("djt"));
				}
			}
			//当前时间大写
			String zhDate = DateUtils.getZHDate();
			//共几人阿拉伯数字
			int count = list.size();
			//共几人大写
			String zhNumber = StringUtils.formatChNum(String.valueOf(count));
			zhNumber = "二".equals(zhNumber)?"两":zhNumber;
			//处分类别名称
			String cflbmc = list.get(0).get("cflbmc");
			//处分文号
			String cfwh = list.get(0).get("cfwh");
			//第几条
			//String djt = list.get(0).get("djt");
			String djt = org.apache.commons.lang.StringUtils.join(djtSet, '、');
			//姓名
			String names = org.apache.commons.lang.StringUtils.join(nameList, '、');
			
			data.put("names", names);
			data.put("cfjgList", list);
			data.put("zhDate", zhDate);
			data.put("count", count);
			data.put("zhNumber", zhNumber);
			data.put("cflbmc", cflbmc);
			data.put("cfwh", cfwh);
			data.put("djt",djt );
			
			String mbmc = "wjcftzs_" + Base.xxdm + ".xml";
			if(cflbmc.contains("留校察看")){
				mbmc = "HD_" + mbmc;	//红头文件
			}
			String fileName = "学生违纪处分决定书_"+System.currentTimeMillis();
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			fileList.add(wordFile);
			
		}
		
		return fileList.toArray(new File[]{});
	}
	
	/** 
	 * @描述:生成word文件数组：处分审批表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月16日 下午12:42:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfjgList
	 * @return
	 * File [] 返回类型 
	 * @throws 
	 */
	public File[] getCfspbFiles(List<HashMap<String, String>> cfjgList) {
		
		List<File> fileList = new ArrayList<File>();
		
		HashMap<String,Object> data = new HashMap<String,Object>();
		for(int i=0;i<cfjgList.size();i++){
			HashMap<String, String> map = cfjgList.get(i);
			map.put("cflbmc", HtmlUtil.xmlZy(map.get("cflbmc")));
			map.put("bz", HtmlUtil.xmlZy(map.get("bz")));
			map.put("cfyj", HtmlUtil.xmlZy(map.get("cfyj")));
			
			//当前时间
			String curDate = DateUtils.getLyr();
			//各级别审核信息
			List<HashMap<String,String>> list = new CommShlcImpl().getShyjListByYwid(map.get("cfid"));
			
			data.put("curDate", curDate);
			data.put("cfjg", map);
			data.put("yjsh", list.get(0));
			data.put("ejsh", list.get(1));
			data.put("sjsh", list.get(2));
			
			String mbmc = "wjcfsq_" + Base.xxdm + ".xml";
			String fileName = "学生违纪处分审批表_"+System.currentTimeMillis();
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			fileList.add(wordFile);
		}
		
		return fileList.toArray(new File[]{});
	}
	
	/**
	 * @描述: 根据cfid取记录
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-2 上午11:57:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCfxxByCfid(String cfid){
		return dao.getCfxxByCfid(cfid);
	}
	
	/**
	 * 
	 * @描述: 验证是否重复
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-26 下午07:42:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(CfjgForm form){
		return dao.checkIsNotRepeat(form);
	}
}
