package xgxt.wjcf.shgc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

public class KswjCfbxService {

	DAO mydao = DAO.getInstance();
	KswjCfbxDAO dao = null;
	
	public List<String[]> cfbxResult(KswjModel model) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.cfbxResult(model);
	}
	
	public List<HashMap<String, String>> cfbxTitle() throws Exception {
		dao = new KswjCfbxDAO();
		return dao.cfbxTitle();
	}
	
	public HashMap<String, String> stuDetails(String xh) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.stuDetails(xh);
	}
	
	public boolean kswjcfbxSave(KswjModel model, HttpServletRequest request) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.kswjcfbxSave(model, request);
	}
	
	public HashMap<String, String> cfbxInfo(String pkValue) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.cfbxInfo(pkValue);
	}
	
	public boolean cfbxModiSave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.cfbxModiSave(pkValue, model, request);
	}
	
	public String cfbcDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.cfbcDel(keys, request);
	}
	
	/**
	 * 处分表现表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> bgdTitle() throws Exception {
		dao = new KswjCfbxDAO();
		return dao.bgdTitle();
	}
	
	public List<String[]> bgdResult(KswjModel model) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.bgdResult(model);
	}
	
	public HashMap<String, String> stuCfxx(String xh) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.stuCfxx(xh);
	}
	
	public HashMap<String, String> stuKswjxx(String pkValue) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.stuKswjxx(pkValue);
	}
	
	public boolean bgdSave(KswjModel model, HttpServletRequest request) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.bgdSave(model, request);
	}
	
	public String bgdDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.bgdDel(keys, request);
	}
	
	public HashMap<String, String> bgdinfo(String pkValue) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.bgdinfo(pkValue);
	}
	
	public boolean bgdmodisave(String pkValue, KswjModel model, HttpServletRequest request) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.bgdmodisave(pkValue, model, request);
	}
	
	public HashMap<String, String> kswjprint(String pkVal) throws Exception {
		dao = new KswjCfbxDAO();
		HashMap<String, String> rs = dao.kswjprint(pkVal);
		String sfzh = rs.get("sfzh");
		String rxrq = rs.get("rxrq");
		rxrq = !StringUtils.isNull(rxrq) ? rxrq.replaceAll("-", "") : "";
		sfzh = !StringUtils.isNull(sfzh) ? sfzh : "";
		String sJxYear = "";
		String sJxMon = "";
		String sCsYear = "";
		String sCsMon = "";
		String sCsDate = "";
		if (!StringUtils.isNull(rxrq)) {
			sJxYear = rxrq.substring(0, 4);
			sJxMon = rxrq.substring(4, 6);
		}
		if (!StringUtils.isNull(sfzh)) {
			sCsYear = sfzh.substring(6, 10);
			sCsMon = sfzh.substring(10, 12);
			sCsDate = sfzh.substring(12, 14);
		}
		rs.put("sjxy", sJxYear);
		rs.put("sjxmon", sJxMon);
		rs.put("scsy", sCsYear);
		rs.put("scsm", sCsMon);
		rs.put("scsd", sCsDate);
		return rs;
	}
	
	/**
	 * 考试违纪处分表现报表
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> kswjcfbxPrint(String pkValue) throws Exception {
		dao = new KswjCfbxDAO();
		return dao.kswjcfbxPrint(pkValue);
	}
	
	public HashMap<String, String> kswjbgdprint(String pkValue) throws Exception {
		dao = new KswjCfbxDAO();
		HashMap<String, String> rs = dao.kswjbgdprint(pkValue);
		String sfzh = rs.get("sfzh");
		String sCsYear = "";
		String sCsMon = "";
		if (!StringUtils.isNull(sfzh)) {
			sCsYear = sfzh.substring(6, 10);
			sCsMon = sfzh.substring(10, 12);
		}
		rs.put("csrq", sCsYear + sCsMon);
		return rs;
	}
	
	public HashMap<String, String> kswjjybzprint(String pkValue) throws Exception {
		dao = new KswjCfbxDAO();
		HashMap<String, String> rs = dao.kswjjybzprint(pkValue);
		String csrq = rs.get("csrq");
		String sfzh = rs.get("sfzh");
		if (StringUtils.isNull(csrq)) {
			csrq = !StringUtils.isNull(sfzh) && sfzh.length() > 15 ? sfzh.substring(6, 14) : "";
		}
		String nn = "";
		if (!StringUtils.isNull(csrq)) {
			String sql = "select floatToInt((months_between(to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),to_date('"
					+ csrq + "','yyyy-mm-dd')))/12) nn from dual";
			nn = mydao.getOneRs(sql, new String[] {},"nn" );// 获取年龄
			
		}// end if
		rs.put("nl", nn);
		return rs;
	}
}
