/**
 * @部门:学工产品事业部
 * @日期：2013-10-22 上午11:48:50 
 */  
package com.zfsoft.xgxt.wjcf.cfsb;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.wjcf.general.WjcfGeneralForm;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhForm;
import com.zfsoft.xgxt.wjcf.cflbdmwh.CflbdmwhService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪处分管理模块
 * @类功能描述: (处分上报管理) 
 * @作者：陈敏杰[工号:913]
 * @时间： 2013-10-22 上午11:48:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfsbglService extends SuperServiceImpl<CfsbglForm, CfsbglDao> {
	
	private CfsbglDao dao=new CfsbglDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String BACK = "back";
	
	public CfsbglService(){
		this.setDao(dao);
	}

	/** 
	 * @描述:(保存处分上报)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午04:04:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String save(CfsbglForm model) throws Exception{
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setCfid(guid);
		
		String splcid=getSplcid(model);
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//设置初始审核状态值
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
			if(model.getType().equals("submit")){
				model.setSbjg(Constants.YW_SHZ);//审核中
			}else{
				model.setSbjg(Constants.YW_WTJ);//未提交
			}
		}
		boolean result = super.runInsert(model);
		if(result){
			if (result && SUBMIT.equalsIgnoreCase(model.getType())) {
				result = shlc.runSubmit(guid, splcid,model.getXh(),"wjcf_cfsh.do?method=cxCfshList","wjcf_cfsbgl.do?method=cxCfsbList");
			}
		}
		
		return String.valueOf(result);
	}

	
	/** 
	 * @描述:(获取审批流程id)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-22 下午04:18:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	private String getSplcid(CfsbglForm model) {
		
		return dao.getSplcid(model);
	}

	/**
	 * 
	 * @描述:(处分上报修改保存)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public String updateSave(CfsbglForm model) throws Exception{
		String cfid=model.getCfid();
		
		CfsbglForm modelGet = getModel(cfid);

		String splcid = "";
		
		// 退回记录取审核流程
		if(Constants.YW_YTH.equals(modelGet.getSbjg())){
			model.setCflbdm(modelGet.getCflbdm());
			splcid = modelGet.getSplcid();
		}else{
			splcid = getSplcid(model);
		}
		
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		
		//设置初始审核状态值
		if(SUBMIT.equalsIgnoreCase(model.getType())){
			model.setSbjg(Constants.SHZ); 
		}
		boolean result = dao.runUpdate(model);
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(cfid, splcid,model.getXh(),"wjcf_cfsh.do?method=cxCfshList","wjcf_cfsbgl.do?method=cxCfsbList");
		
		}
		return String.valueOf(result);
	}
	
	
	/**
	 * 
	 * @描述:(处分上报删除)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@Override
	public int runDelete(String[] values) throws Exception {
		String[] ids=getCancelIds(values);
		if(ids.length==0)
			return 0;
		int result=dao.runDelete(ids);
		for (String id : ids) {
			if(shlc.deleteShjl(id)){
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("违纪处分审核");
				dbcz.remove(new String[]{id});
			}
		}
		return result;
	}
	
	/**
	 * @throws Exception  
	 * @描述:(获取可以删除的id)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 下午07:01:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String[] 返回类型 
	 * @throws 
	 */
	private String[] getCancelIds(String[] values) throws Exception {
		
		return dao.getCancelIds(values);
	}
	
	/**
	 * 
	 * @描述:TODO(提交功能)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午03:07:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitCfsb(CfsbglForm model) throws Exception{
		
		model.setSbjg(Constants.YW_SHZ);
		boolean resultCfsb = dao.updateCfsbsbjg(model);
		boolean result = false;
		if(resultCfsb ){
			//保存审核流程
			result = shlc.runSubmit(model.getCfid(), model.getSplcid(),model.getXh(),"wjcf_cfsh.do?method=cxCfshList","wjcf_cfsbgl.do?method=cxCfsbList");
		}
		//if(!BACK.equalsIgnoreCase(model.getReturnflag())){
			return result;
		/*}else{
			return resultCfsb;
		}*/
		
	}
	/**
	 * 
	 * @描述:TODO(取消记录)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午03:07:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	/**
	 * 
	 * @描述:TODO(更新记录)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午03:07:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateCfsbModel(CfsbglForm model) throws Exception {
			boolean resultCfsb = dao.updateCfsbsbjg(model);
			return resultCfsb;
	}

	/** 
	 * @描述:验证处分在结果当中是否存在 （验证条件：学号、处分类别、处分时间
	 * @作者：cq [工号：785]
	 * @日期：2014-5-14 下午04:31:51
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkExistCfjg(CfsbglForm myForm) {
		
		String num = dao.checkExistCfjg(myForm);
		return Integer.valueOf(num) > 0;
	}
	public boolean checkExistCfwh(CfsbglForm myForm) {
		
		String num = dao.checkExistCfwh(myForm);
		return Integer.valueOf(num) > 0;
	}
	
	/** 
	 * @描述:验证处分在上报和结果库当中是否存在 （验证条件：学号、处分类别、处分时间
	 * @作者：cq [工号：785]
	 * @日期：2014-5-14 下午04:31:51
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkExistCfsbjg(CfsbglForm myForm) {
		String num = dao.checkExistCfsbjg(myForm);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * 获取处分信息（打印表专用）
	 */
	public HashMap<String,String> getDjbModel(String cfid) throws Exception{
		return dao.getDjbModel(cfid);
	}
	
	/**
	 * 根据ID获取处分信息
	 */
	public List<HashMap<String, String>> getCfjdXxByIds(String[] ids) {
		return dao.getCfjdXxByIds(ids);
	}
	
	/**
	 * 根据处分类别合并处分结果记录，即处分类别相同加入同一List，
	       各List以类别为key存入Map
	 */
	public Map<String, List<HashMap<String, String>>> getCfjdListMap(List<HashMap<String,String>> cfjdList) {
		
		Map<String, List<HashMap<String, String>>> cfjdListMap = new HashMap<String, List<HashMap<String, String>>>();
		
		for(HashMap<String,String> cfjg:cfjdList){
			String key = cfjg.get("cflbmc")+cfjg.get("cfyymc");
			List<HashMap<String,String>> list = cfjdListMap.get(key);
			if(list==null){
				list = new ArrayList<HashMap<String, String>>();
				cfjdListMap.put(key, list);
			}
			list.add(cfjg);
		}
		return cfjdListMap;
	}
	
	/**
	 * @描述: 根据合并记录后的map，生成word文件数组：处分决定书
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-8-17 下午04:44:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfjgListMap
	 * @return
	 * File[] 返回类型 
	 * @throws
	 */
	public File[] getCfjdsFiles(Map<String, List<HashMap<String, String>>> cfjgListMap) {
		
		List<File> fileList = new ArrayList<File>();
		
		Collection<List<HashMap<String, String>>> collection = cfjgListMap.values();
		for(List<HashMap<String, String>> list:collection){
			HashMap<String,Object> data = new HashMap<String,Object>();
			/*学号列表*/
			List<String> xhList = new LinkedList<String>();
			/*姓名列表*/
			List<String> nameList = new LinkedList<String>();
			/*文本转义*/
			for(int i=0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				map.put("cfwh", HtmlUtil.xmlZy(map.get("cfwh")));
				map.put("cflbmc", HtmlUtil.xmlZy(map.get("cflbmc")));
				map.put("cfyymc", HtmlUtil.xmlZy(map.get("cfyymc")));
				
				if(StringUtils.isNotNull(map.get("xm"))){
					nameList.add(map.get("xm"));
				}
				if(StringUtils.isNotNull(map.get("xh"))){
					xhList.add(map.get("xh"));
				}
				String fh = "；";
				String jh = "。";
				if(i != list.size() - 1){
					map.put("fh",fh);
				}else{
					map.put("fh",jh);
				}
			}
			//共几人阿拉伯数字
			int count = list.size();
			data.put("count", count);
			//共几人大写
			String zhNumber = StringUtils.formatChNum(String.valueOf(count));
			zhNumber = "二".equals(zhNumber)?"两":zhNumber;
			data.put("zhNumber", zhNumber);
			//处分类别名称
			String cflbmc = list.get(0).get("cflbmc");
			data.put("cflbmc", cflbmc);
			//处分原因名称
			String cfyymc = list.get(0).get("cfyymc");
			data.put("cfyymc", cfyymc);
			//处分文号
			String cfwh = list.get(0).get("cfwh");
			if(cfwh.matches("^[0-9]{8}$")){
				data.put("cfwh", "〔"+cfwh.substring(0,4)+"〕"+cfwh.substring(4,8));
			}else{
				data.put("cfwh",cfwh);
			}
			//处分时间【发文时间】
			String cfsj = DateTranCnDate.fomartDateToCn(list.get(0).get("cfsj"),FomartDateType.day);
			data.put("cfsj", cfsj);
			/*学号*/
			String xhs = org.apache.commons.lang.StringUtils.join(xhList, '、');
			//姓名
			String names = org.apache.commons.lang.StringUtils.join(nameList, '、');
			data.put("names", names);
			//系统当前时间
			String dyrq = DateTranCnDate.fomartDateToCn(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),FomartDateType.day);
			data.put("dyrq", dyrq);
			
			data.put("cfjgList", list);
			String mbmc = "cfjds_" + Base.xxdm + ".xml";
			String fileName = "[" + cflbmc + "]" + "-" + "【" + names + "】";
			fileName += "处分决定书";
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			fileList.add(wordFile);
		}
		return fileList.toArray(new File[]{});
	}

    public HashMap<String,String> getjcid(String cfid) {
		return dao.getjcid(cfid);
    }
}
