/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpysq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
/**
 * @className	： RcpysqService
 * @description	： 人才培养申请Service
 * @author 		：CP（1352）
 * @date		： 2018-5-11 下午04:47:05
 * @version 	V1.0
 */
public class RcpysqService extends SuperServiceImpl<RcpysqForm, RcpysqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	public boolean isExist(RcpysqForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())||"submit".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public boolean saveRcpysq(RcpysqForm model) throws Exception {
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
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"xsxx_rcpy_rcpysh.do","xsxx_rcpy_rcpysq.do");
		}
		return result;
	}

	public boolean updateRcpysq(RcpysqForm model) throws Exception {
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
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xsxx_rcpy_rcpysh.do","xsxx_rcpy_rcpysq.do");
			return result;
		}else{
			return insertResult;
		}
		
}

	public String[] delRcpysq(String[] ids) throws Exception {
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String id:ids){
			if(dao.isCanDel(id)){
				delId.add(id);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getRcpyXhXm(id);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?rcpyDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
}

	private int rcpyDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}

	public boolean submitRcpysq(RcpysqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取审批流程
			model.setSplc(dao.getShlcID());
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.updateRcpysq(model);
		boolean result = false;
		if(flag){
			//保存审核流程
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xsxx_rcpy_rcpysh.do","xsxx_rcpy_rcpysq.do");
		}
		return result;
}

	public boolean cancelRcpysq(String sqid, String lcid) throws Exception {
		return shlc.firstStepCancle(sqid,lcid);
	}

	public boolean updateRcpysqForCx(RcpysqForm model) throws Exception {
		return dao.updateRcpysq(model);
	}

	public HashMap<String, String> getRcpysqInfo(RcpysqForm model) {
		return dao.getRcpysqInfo(model);
	}

	public List<HashMap<String, String>> getPylbList() {
		return dao.getPylbList();
	}

	public List<HashMap<String, String>> getKhzbList() {
		return dao.getKhzbList();
	}

	public List<HashMap<String, String>> getXztjList() {
		return dao.getXztjList();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
