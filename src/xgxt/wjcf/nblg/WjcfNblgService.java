package xgxt.wjcf.nblg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class WjcfNblgService {

	WjcfNblgDAO dao = WjcfNblgDAO.getInstance();
	
	public static WjcfNblgService service = new WjcfNblgService();
	
	public static WjcfNblgService getInstance() {
		return service;
	}
	
	public HashMap<String, String> printInfo(String xh) throws Exception {
		return dao.printInfo(xh);
	}
	
	public HashMap<String, String> printwjInfo(String pkValue) throws Exception {
		return dao.printwjInfo(pkValue);
	}
	
	public List<HashMap<String, String>> titie() throws Exception {
		return dao.titie();
	}
	
	public HashMap<String, String> printWjcf(String pk) {
		return dao.printWjcf(pk);
	}
	
	public List<String[]> result(WjcfNblgModel model) throws Exception {
		return dao.result(model);
	}
	
	public List<HashMap<String, String>> getJdlist() throws Exception {
		return dao.getJdlist();
	}
	
	public HashMap<String, String> getJdInfo(String pkValue) throws Exception {
		return dao.getJdInfo(pkValue);
	}
	
	public String getBz(String jd) throws Exception {
		if (!StringUtils.isNull(jd)) {
			if ("解除查看期".equalsIgnoreCase(jd) || "解除处分".equalsIgnoreCase(jd)) {
				return "解除";
			} else {
				return "更改";
			}
		} else {
			return "更改";
		}
	}
	
	public HashMap<String, String> printwjInfo2(String pkValue) throws Exception {
		return dao.printwjInfo2(pkValue);
	}
	
	public boolean updateJd(WjcfNblgModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		return dao.updateJd(model, pkValue, request);
	}
	
	public List getFileList(String pkValue){
		return dao.getFileList(pkValue);
	}
	
	public HashMap<String, String> printwjInfo1(String pkValue) throws Exception {
		return dao.printwjInfo1(pkValue);
	}
	
	public HashMap<String, String> printJcjkq(String pk) {
		return dao.printJcjkq(pk);
	}
	
	public HashMap<String, String> lxckPrint(String pkValue) {
		return dao.lxckPrint(pkValue);
	}
	
	public String jgInfo(List<String> jgList) throws Exception {
		return dao.getJgList(jgList);
	}
	
	public HashMap<String,String>getCxcfInfo(String pkValue){
		return dao.getCxcfInfo(pkValue);
	}
}
