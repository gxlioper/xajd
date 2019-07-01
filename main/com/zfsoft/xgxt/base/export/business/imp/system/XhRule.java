package com.zfsoft.xgxt.base.export.business.imp.system;

import java.util.List;

import com.zfsoft.xgxt.base.export.business.BusinessExtend;
import com.zfsoft.xgxt.base.export.model.ImportModel;

/** 
 * 学生数据过滤
 */
public class XhRule extends BusinessExtend {

	public XhRule(String drmkdm) {
		super(drmkdm, "");
	}

	@Override
	protected String check(String param, List<ImportModel> data) {
		return this._YZTG;
	}
}
