/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.cssz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	： CsszService
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-16 上午10:00:15
 * @version 	V1.0 
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {	
	/**
	 * @description	：获取参数设置列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午11:01:22
	 * @return
	 */
	public List<HashMap<String,String>> getCsszList(){
		return dao.getCsszList();
	}
	
	/**
	 * @description	： 插入数据
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午11:06:15
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(CsszForm t) throws SQLException{
		String dsSplc = t.getDs();
		String jsSplc = t.getJs();
		String blSplc = t.getBl();
		List<String[]> paramList = new ArrayList<String[]>();
		if(StringUtils.isNotNull(dsSplc)){
			t.setDss(new String[]{"ds",dsSplc});
			paramList.add(t.getDss());
		}
		if(StringUtils.isNotNull(jsSplc)){
			t.setJss(new String[]{"js",jsSplc});
			paramList.add(t.getJss());
		}
		if(StringUtils.isNotNull(blSplc)){
			t.setBls(new String[]{"bl",blSplc});
			paramList.add(t.getBls());
		}
		t.setParamList(paramList);
		return dao.insert(t);
	}
	
	/**
	 * @description	： 更新数据
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午11:06:41
	 * @param t
	 * @return
	 * @throws Exception 
	 * @throws SQLException
	 */
	public boolean delete() throws Exception{
		return dao.delete();
	}
	
	/**
	 * @description	： 获取参数设置
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 下午02:43:30
	 * @return
	 */
	public CsszForm getCsszForm(){
		CsszForm csszForm = new CsszForm();
		List<HashMap<String, String>> csszList = getCsszList();
		if(null != csszList && csszList.size() > 0){			
			for(HashMap<String,String> map : csszList){
				String lx = map.get("lx");
				String splc = map.get("splc");
				if(lx.equals("ds")){
					csszForm.setDs(splc);
				}else if(lx.equals("js")){
					csszForm.setJs(splc);
				}else if(lx.equals("bl")){
					csszForm.setBl(splc);
				}
			}
		}
		return csszForm;
	}
}
