package xgxt.xljk.zxzx.util;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;
import java.util.*;
import xgxt.xljk.zxzx.form.*;

public class xljk_dmwh_util {
	DAO mydao = DAO.getInstance();

	lrh_commen_util com_uti = new lrh_commen_util();

	HttpServletRequest request;

	public void xljk_dmwh_util1(HttpServletRequest request) {
		this.request = request;
	}

	public xljk_zxszy_Form get_dmwhb_sjdwh(xljk_zxszy_Form myform) {
		String xn_id = myform.getZY_XN_ID();
		String tableName = "xljk_dmwhb";
		String usersql = "select a.xn_id,a.dmh,a.dmmc,a.ss from ";
		String[] otherKeys = { "xn_id" };
		String[] otherKeyValues = { xn_id };
		List<HashMap<String, String>> li = com_uti.Find_By_OtherKey(tableName,
				usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < li.size(); i++) {
			map = li.get(0);
			String sjd = map.get("DMMC");
			String[] temp1 = sjd.split("-");
			for (int j = 0; j < temp1.length; j++) {
				String[] temp2 = temp1[j].split(":");
				if (j == 0) {
					myform.setHour1(temp2[0]);
					myform.setMinutes1(temp2[1]);
				}else if (j == 1) {
					myform.setHour2(temp2[0]);
					myform.setMinutes2(temp2[1]);
				}
			}
			myform.setSjd_dm(map.get("DMH"));
		}
		return myform;
	}

	public xljk_zxszy_Form get_dmwhb_ddwh(xljk_zxszy_Form myform) {
		String xn_id = myform.getZY_XN_ID();
		String tableName = "xljk_dmwhb";
		String usersql = "select a.xn_id,a.dmh,a.dmmc,a.ss from ";
		String[] otherKeys = { "xn_id" };
		String[] otherKeyValues = { xn_id };
		List<HashMap<String, String>> li = com_uti.Find_By_OtherKey(tableName,
				usersql, otherKeys, otherKeyValues);
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < li.size(); i++) {
			map = li.get(0);
			myform.setDd2(map.get("DMMC"));
			myform.setDd_dm(map.get("DMH"));
		}
		return myform;
	}

	public String modi_dmwhb_sjdwh(xljk_zxszy_Form myform) throws Exception {
		String xn_id = myform.getZY_XN_ID();
		String sjd = myform.getHour1() + ":" + myform.getMinutes1() + "-"
				+ myform.getHour2() + ":" + myform.getMinutes2();
		String tableName = "xljk_dmwhb";
		String[] columns = { "DMMC" };
		String[] values = { sjd };
		String primaryKey = "xn_id";
		String pkValue = xn_id;
		boolean flag = StandardOperation.update(tableName, columns, values,
				primaryKey, pkValue, this.request);
		return flag == true ? "update_success" : "update_fail";
	}

	public String del_dmwhb_sjdwh(xljk_zxszy_Form myform) throws Exception {
		String xn_id = myform.getZY_XN_ID();
		String tableName = "xljk_dmwhb";
		String primaryKey = "xn_id";
		String value = xn_id;
		boolean flag = StandardOperation.delete(tableName, primaryKey, value,
				this.request);
		return flag == true ? "del_success" : "del_fail";
	}

	public String modi_dmwhb_ddwh(xljk_zxszy_Form myform) throws Exception {
		String xn_id = myform.getZY_XN_ID2();
		String dd = myform.getDd2();
		String tableName = "xljk_dmwhb";
		String[] columns = { "DMMC" };
		String[] values = { dd };
		String primaryKey = "xn_id";
		String pkValue = xn_id;
		boolean flag = StandardOperation.update(tableName, columns, values,
				primaryKey, pkValue, this.request);
		return flag == true ? "update_success" : "update_fail";
	}

	public String del_dmwhb_ddwh(xljk_zxszy_Form myform) throws Exception {
		String xn_id = myform.getZY_XN_ID();
		String tableName = "xljk_dmwhb";
		String primaryKey = "xn_id";
		String value = xn_id;
		boolean flag = StandardOperation.delete(tableName, primaryKey, value,
				this.request);
		return flag == true ? "del_success" : "del_fail";
	}

}
