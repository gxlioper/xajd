package xgxt.wjcf.hhgxy;

import java.util.HashMap;

import xgxt.utils.String.StringUtils;

public class WjcfHhgxyService {

	WjcfHhgxyDAO dao = WjcfHhgxyDAO.getInstance();
	
	public static WjcfHhgxyService service = new WjcfHhgxyService();
	
	public static WjcfHhgxyService getInstance() {
		return service;
	}
	
	public HashMap<String, String> wjcfysbPrint(String xh, String cflb,
			String cfyy, String pkValue) throws Exception {
		return dao.wjcfysbPrint(xh, cflb, cfyy, pkValue);
	}
	
	/**
	 * 数组转字符串
	 * @param list
	 * @return
	 */
	public String listToString(String[] list) {
		if (list != null && list.length > 0) {
			String str= "";
			for (String s : list) {
				str = str + s + "-"; 
			}
			return StringUtils.isNull(str) ? "" : str.substring(0, str.length() - 1);
		} 
		return "";
	}
	/**
	 * 批量发文
	 * @param pkVal
	 * @param cfwh
	 * @param cfsj
	 * @return
	 * @throws Exception
	 */
	public boolean plfw(String pkVal, String cfwh, String cfsj) throws Exception{
		return dao.plfw(pkVal, cfwh, cfsj);
	}
}
