package com.zfsoft.xgxt.comm.export.util;

import java.util.Comparator;
import java.util.HashMap;


/**
 * µº≥ˆ◊÷∂Œ≈≈–Ú
 * @author Penghui.Qu
 *
 */
public class ExportComparator implements Comparator<HashMap<String,String>>{

	/**
	 * ∞¥œ‘ æÀ≥–Ú≈≈–Ú
	 */
	public int compare(HashMap<String,String> oldModel, HashMap<String,String>  newModel) {
		try{
			return Integer.valueOf(oldModel.get("xssx")) > Integer.valueOf(newModel.get("xssx")) ? 1 : -1;
		}catch (Exception e) {
			return 0;
		}
	}
	
}