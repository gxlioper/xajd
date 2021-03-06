/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.stcygl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglForm;
import com.zfsoft.xgxt.ttgl.stgl.stglsq.StglsqDao;

public class StcyglService extends SuperServiceImpl<StcyglForm, StcyglDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public HashMap<String, String> getStxxInfo(StcyglForm myForm) {
		// TODO Auto-generated method stub
		return dao.getStxxInfo(myForm);
	}

	public List<HashMap<String, String>>  getFzrxx(StcyglForm myForm) {
		// TODO Auto-generated method stub
		return dao.getFzrxx(myForm);
	}

	public List<HashMap<String, String>>  getTzsxx(StcyglForm myForm) {
		// TODO Auto-generated method stub
		return dao.getTzsxx(myForm);
	}

	public List<HashMap<String, String>> getStRyList(StcyglForm model, User user) throws Exception {
		// TODO Auto-generated method stub
		return dao.getStRyList(model,user);
	}

	public boolean saveRysh(String shzt, String[] guidArr) throws Exception {
		// TODO Auto-generated method stub
		return dao.saveRysh(shzt,guidArr);
	}

	public HashMap<String, String> getSqxxInfo(StcyglForm myForm) {
		// TODO Auto-generated method stub
		return dao.getSqxxInfo(myForm);
	}

	public boolean saveStzz(StcyglForm model) throws Exception {
		StglsqDao stglsqDao = new StglsqDao();
		String splc = stglsqDao.getShlcID();// 获取审批流程
		String zzywid = dao.getZzywid(model.getJgid());
		boolean updateResult =dao.updateSqbZzShzt(model); //更新申请表里zzshzt 为5 
		boolean result = false;
		if (updateResult) {
			//保存审核流程
			result = shlc.runSubmit(zzywid,splc,"","xg_ttgl_stglsh.do","xg_ttgl_stglsq.do");
		}
		return result;
	}

    /**
     * 删除成员
     * @param t
     * @throws Exception
     */
	public void delCy(StcyglForm t) throws Exception{
	    dao.delFromCy(t);
	    dao.delFromFzr(t);
	    dao.delFromTzs(t);
    }

    public boolean updateZw(StcyglForm t) throws Exception{
		dao.delFromTzs(t);
		dao.delFromFzr(t);
		String sjly = dao.getSjly(t);
		boolean result = true;
		if("1".equals(sjly)){//通过申请的学生组织
			if (t.getTnzw().indexOf("负责人")>=0){
				String[] input = new String[]{t.getXh(),t.getJgid(),"",t.getTnzw()};
				result = dao.updateFzr(input);
			}else if(t.getTnzw().equals("团支书")){
				String[] input = new String[]{t.getXh(),t.getJgid(),""};
				result = dao.updateTzs(input);
			}
		}else {//结果添加的学生组织
			if (t.getTnzw().indexOf("负责人")>=0){
				String[] input = new String[]{t.getXh(),"",t.getJgid(),t.getTnzw()};
				result = dao.updateFzr(input);
			}else if(t.getTnzw().equals("团支书")){
				String[] input = new String[]{t.getXh(),"",t.getJgid()};
				result = dao.updateTzs(input);
			}
		}
		if (result){
			result = dao.updateCyzw(t);
		}
		return result;
	}

	public HashMap<String,String> getCyxx(StcyglForm t) throws Exception{
		return dao.getCyxx(t);
	}
}
