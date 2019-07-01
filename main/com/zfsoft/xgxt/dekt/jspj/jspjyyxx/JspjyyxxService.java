/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjyyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class JspjyyxxService extends SuperServiceImpl<JspjyyxxForm, JspjyyxxDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	public boolean isExist(JspjyyxxForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}
	public boolean saveJspj(JspjyyxxForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(),splc,model.getXh(),"xg_dekt_jspjglsh.do","xg_dekt_jspjglsq.do");
		}
		return result;
	}
	public boolean updateJspj(JspjyyxxForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
			// ��ȡ��������
			model.setSplc(dao.getShlcID());
		}
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_dekt_jspjglsh.do","xg_dekt_jspjglsq.do");
			return result;
		}else{
			return insertResult;
		}
		
}
	public String[] deleteJspj(String[] ids) throws Exception {
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getJspjxx(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?jspjDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
}
	private int jspjDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	public boolean submitJspj(JspjyyxxForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			model.setSplc(dao.getShlcID());
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.updateJspj(model);
		boolean result = false;
		if(flag){
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"xg_dekt_jspjglsh.do","xg_dekt_jspjglsq.do");
		}
		return result;
	}
	public boolean cancelJspj(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid,lcid);
	}
	public boolean updateCxJspj(JspjyyxxForm model) throws Exception {
		return dao.updateJspj(model);
	}
	public HashMap<String, String> getJspjInfo(JspjyyxxForm model) {
		return dao.getJspjInfo(model);
	}
	public Map<String,String> getFdyInfo(String zgh){
		
		return dao.getFdyInfo(zgh);
	}
	public String getfdyxm(String pjjszgh) {
		return dao.getfdyxm(pjjszgh);
	}
	public boolean xssqyy(String zgh, String xh , String zzshen) throws Exception{
		return dao.xssqyy(zgh, xh, zzshen);
	}
	public boolean deladd(String zgh, String xh ) throws Exception{
		return dao.deladd(zgh, xh);
	}
	public HashMap<String, String> getJspjyyxx(JspjyyxxForm model) {
		return dao.getJspjyyxx(model);
	}
	public boolean szdwSzSave(String jssfty, String jshfxx,String sqid) throws Exception {
		return dao.szdwSzSave(jssfty, jshfxx, sqid);
	}
	
	
	
	
	
	
	
	
}
