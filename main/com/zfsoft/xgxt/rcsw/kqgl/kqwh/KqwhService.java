/**
 * @部门:学工产品事业部
 * @日期：2016-10-26 下午05:30:12 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqwh;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.kqgl.cssz.KqCsszDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2016-10-26 下午05:30:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqwhService extends SuperServiceImpl<KqwhForm, KqwhDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 获取kqwhform
	 */
	public KqwhForm getModel(String id) throws Exception{
		return dao.getModel(id);
	}
	
	/**
	 * 
	 * @描述:获取当前学年、学期、月份最大周次
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 上午11:20:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDqZc(){
		return dao.getDqZc();
	}

	/** 
	 * @描述:获取考情信息
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午02:16:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKqinfo(String id) {
		return dao.getKqinfo(id);
	}

	/** 
	 * @描述:保存考情信息
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午02:21:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveKqinfo(KqwhForm model, List<KqwhForm> list) throws Exception {
		boolean flag = runUpdate(model);
		if(flag){
			
			//更新审核状态
			flag = saveTj(model);
			
			if(flag){
				flag = dao.delKqInfo(model.getId());
			}
			
			if(flag){
				List<String[]> params = new ArrayList<String[]>();
				for(int i=0;i<list.size();i++){
					String[] info = new String[6];
					info[0] = UniqID.getInstance().getUniqIDHash();
					info[1] = model.getId();
					info[2] = list.get(i).getXh();
					info[3] = list.get(i).getBjcs();
					info[4] = list.get(i).getSjcs();
					info[5] = list.get(i).getKkjs();
					params.add(info);
				}
				flag = dao.saveKqInfo(params);
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:更新审核状态
	 * @作者：cq [工号：785]
	 * @日期：2016-10-28 上午09:47:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveTj(KqwhForm model)throws Exception{
		
		boolean flag = false;
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		KqCsszDao kqCsszDao = new KqCsszDao();
		String splc = kqCsszDao.getModel().getSplc();
		model.setSplc(splc);
		Date date = new Date();
        DateFormat df2 = DateFormat.getDateTimeInstance();
        model.setJlsj(df2.format(date));
		
		//更新考勤结果表
		flag = runUpdate(model);
		
		// 保存审核信息
		if (!"save".equals(model.getType())&&flag) {
			flag = shlc.runSubmit(model.getId(), splc, model.getBjdm(), "rcsw_zjsy_kqsh.do", "rcsw_zjsy_kqwh.do");
		}
		
		return flag;
	}

	/** 
	 * @描述:查询有多少条记录可以提交
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午07:38:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> isCanSubmit(String values, KqwhForm kqwhForm, User user) throws Exception {
		return dao.isCanSubmit(values, kqwhForm, user);
	}

	
	/** 
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 下午08:39:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param kqwhForm
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public int[] submit(String values, KqwhForm model, User user) throws Exception {
		
		//查询需要提交的学生名单
		List<HashMap<String, String>> isCanSubmit = isCanSubmit(values,model,user);
		
		int okNum = 0;
		int notOkNum = 0;
		for (int i = 0; i < isCanSubmit.size(); i++) {
			KqwhForm kqwhForm = new KqwhForm();
			kqwhForm.setId(isCanSubmit.get(i).get("id"));
			kqwhForm.setXn(isCanSubmit.get(i).get("xn"));
			kqwhForm.setXq(isCanSubmit.get(i).get("xq"));
			kqwhForm.setYf(isCanSubmit.get(i).get("yf"));
			kqwhForm.setZc(isCanSubmit.get(i).get("zc"));
			kqwhForm.setBjdm(isCanSubmit.get(i).get("bjdm"));
			kqwhForm.setCqrs(isCanSubmit.get(i).get("cqrs"));
			kqwhForm.setBz(isCanSubmit.get(i).get("bz"));
			kqwhForm.setJlr(user.getUserName());
			kqwhForm.setType("submit");
			
			if(saveTj(kqwhForm)){
				okNum++;
			}else{
				notOkNum++;
			}
		}
		int[] result = {okNum,notOkNum};
		return result;
	}
	
	
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：cq [工号：785]
	 * @日期：2016-10-28 上午10:40:03
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

}
