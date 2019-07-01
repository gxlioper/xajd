package xgxt.szdw.ntzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.utils.CommonQueryDAO;

public class NtzyFdypxService extends BasicExtendService{
	/**
	 * 根据主键获得培训信息
	 * @param pkValue
	 * @return
	 */
	
	public static List<HashMap<String, String>> pxfw = new ArrayList<HashMap<String,String>>();
	
	public static List<HashMap<String, String>> pxlx = new ArrayList<HashMap<String,String>>();
	
	static{
		String[] colList = new String[]{"xmdm", "xmmc"};
		pxfw = CommonQueryDAO.commonQueryforList("szdw_ntzy_pxfwb", "", new String[]{}, colList, "");
		pxlx = CommonQueryDAO.commonQueryforList("szdw_ntzy_pxlxb", "", new String[]{}, colList, "");
	}
	
	public Map<String, String> getPxInfo(String pkValue){
		String pk = "zgh||pxfw||pxlx||kssj||jssj";
		String tableName = "view_szdw_ntzy_fdypx";
		String[] colList = new String[]{"zgh","xm","bmmc","zw","zwmc","xb","xl","zzmm","csrq","mzmc",
					"sfmc","pxfw","pxlx","pxxm","kssj","jssj","jtnr","bz","xxsh","fdyzmc"};
		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
}
