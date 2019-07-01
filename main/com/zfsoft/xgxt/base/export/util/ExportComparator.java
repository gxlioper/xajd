package com.zfsoft.xgxt.base.export.util;

import java.util.Comparator;

import com.zfsoft.xgxt.base.export.model.ExportConfigModel;


/**
 * µ¼³ö×Ö¶ÎÅÅÐò
 * @author Penghui.Qu
 *
 */
public class ExportComparator implements Comparator<ExportConfigModel>{

	/**
	 * °´ÏÔÊ¾Ë³ÐòÅÅÐò
	 */
	public int compare(ExportConfigModel oldModel, ExportConfigModel newModel) {
		try{
			return Integer.valueOf(oldModel.getXssx()) > Integer.valueOf(newModel.getXssx()) ? 1 : -1;
		}catch (Exception e) {
			return 0;
		}
	}
	
}