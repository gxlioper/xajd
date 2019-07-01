package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxqkh;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh.RcxwxxwhService;

/**
 * 日常行为学期考核 
 */
public class RcxwxqkhService extends SuperServiceImpl<RcxwxqkhForm, RcxwxqkhDao> {
	private RcxwxqkhDao dao = new RcxwxqkhDao();

	public RcxwxqkhService() {
		super.setDao(dao);
	}

}
