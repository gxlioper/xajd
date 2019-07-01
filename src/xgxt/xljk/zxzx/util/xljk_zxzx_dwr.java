package xgxt.xljk.zxzx.util;

import xgxt.DAO.*;

import xgxt.utils.*;
import xgxt.xljk.lrh_Util.util.*;

public class xljk_zxzx_dwr {

	DAO mydao = DAO.getInstance();

	New_Random_ID newId = new New_Random_ID();

	lrh_commen_util commen_util = new lrh_commen_util();

	public String xljk_dmwh_chcekdmh(String dmh) {
		boolean flag = this.ckeck_dmh(dmh);
		return flag==true ? "dmh_ok" : "dmh_exits";
	}

	public boolean ckeck_dmh(String dmh) {
		boolean flag = false;
		String dmzh = commen_util.xljk_dmwhb_dmzh(dmh);
		if ("".equals(dmzh)) {
			flag = true;
		}
		return flag;
	}

}
