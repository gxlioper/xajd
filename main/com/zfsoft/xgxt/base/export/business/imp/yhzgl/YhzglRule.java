package com.zfsoft.xgxt.base.export.business.imp.yhzgl;

import java.util.List;
import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/** 
 * 组用户导入个性化
 */
public class YhzglRule extends BusinessExtend {
	
	private final static String _YWKZ_JEYZ_KEY = "YWKZ_YHZGL";

	public YhzglRule(String drmkdm) {
		super(drmkdm, _YWKZ_JEYZ_KEY);
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		return this._YZTG;
	}
	
}
