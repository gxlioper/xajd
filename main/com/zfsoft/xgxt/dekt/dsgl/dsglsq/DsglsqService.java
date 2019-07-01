/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsglsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class DsglsqService extends SuperServiceImpl<DsglsqForm, DsglsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	public boolean isExist(DsglsqForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public boolean saveDssq(DsglsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"xg_dekt_dsglsh.do","xg_dekt_dsglsq.do");
		}
		return result;
	}

	public boolean updateDssq(DsglsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
			// 获取审批流程
			model.setSplc(dao.getShlcID());
		}
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_dekt_dsglsh.do","xg_dekt_dsglsq.do");
			return result;
		}else{
			return insertResult;
		}
		
}

	public String[] deleteDssq(String[] ids) throws Exception {
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getDssqxx(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?dssqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
}

	private int dssqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}

	public boolean submitDssq(DsglsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			model.setSplc(dao.getShlcID());
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.updateDssq(model);
		boolean result = false;
		if(flag){
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_dekt_dsglsh.do","xg_dekt_dsglsq.do");
		}
		return result;
	}

	public boolean cancelDssq(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid,lcid);
	}

	public boolean updateCxDssq(DsglsqForm model) throws Exception {
		return dao.updateDssq(model);
}

	public HashMap<String, String> getDssqInfo(DsglsqForm model) {
		return dao.getDssqInfo(model);
}

    //取学生4个100信息完成图
	public HashMap<String,String> getViewWct(String xh){
		HashMap<String,String> hashMap = new HashMap<String, String>(4);
		hashMap.put("ds",dao.getdsCount(xh));
		hashMap.put("js",dao.getjsCount(xh));
		hashMap.put("jz",dao.getjzCount(xh));
		hashMap.put("hd",dao.gethdCount(xh));
		return hashMap;
	}
}
