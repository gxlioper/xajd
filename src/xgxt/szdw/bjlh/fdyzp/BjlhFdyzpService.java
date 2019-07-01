package xgxt.szdw.bjlh.fdyzp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;


public class BjlhFdyzpService{
	BjlhFdyzpDAO myDao = new BjlhFdyzpDAO();
	
	/**
	 * 获取topTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		if("fdyzp".equals(mk)){
			topTr=new String[]{"学年","名称","日期","是否启用"};
		}else if("fdyzpcx".equals(mk)){
			topTr=new String[]{"考核学年","职工号","姓名","所在部门","是否提交","提交时间"};
		}
		
		return topTr;
	}
		
	public List<HashMap<String,String>> getAddXnList(){
		return myDao.getAddXnList();
	}
	public List<String[]> getTableList(BjlhFdyzpForm myForm,HttpServletRequest request) throws Exception{
		return myDao.getTableList(myForm,request);
	}
	public List<String[]> getQueryTableList(BjlhFdyzpForm myForm,
			HttpServletRequest request) throws Exception {
		return myDao.getQueryTableList(myForm, request);
	}
	public boolean deleteAllById(String zpbid) throws Exception {
		return myDao.deleteAllById(zpbid);
	}
	
	public boolean changeSfqy(String zpbid,String sfqy) throws Exception {
		return myDao.changeSfqy(zpbid,sfqy);	
	}
	
	public List<HashMap<String, String>> getXmListByZpbId(String zpbid) throws Exception{
		return myDao.getXmListByZpdId(zpbid);
	}
	public HashMap<String,String> getFdyzpbById(String pkValue) throws Exception{
		return myDao.getFdyzpbById(pkValue);
	}
	public boolean saveFdyzpbAndFdyzpxmb(BjlhFdyzpForm myForm) throws Exception {
		return myDao.saveFdyzpbAndFdyzpxmb(myForm);
	}
	public HashMap<String, String> getMrsz() {
		return myDao.getMrsz();
	}
	public List<HashMap<String,String>> getFdyzpxxb(String zpbid,String yhm) {
		return myDao.getFdyzpxxb(zpbid,yhm);
	}
	public boolean saveFdyzpxxb(BjlhFdyzpForm myForm,String sftj) throws Exception {
		return myDao.saveFdyzpxxb(myForm,sftj);		
	}
	public boolean copyFdyzpById(BjlhFdyzpForm myForm,String zpbid) throws Exception {
		return myDao.copyFdyzpById(myForm,zpbid);
	}
	public HashMap<String,String> getFdyxx(String yhm,String zpbid){
		return myDao.getFdyxx(yhm,zpbid);
	}
	public String getDate(){
		return myDao.getNowTime("YYYYMMDD");
	}
}
