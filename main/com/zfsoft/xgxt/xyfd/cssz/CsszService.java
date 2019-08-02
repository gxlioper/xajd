/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xyfd.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.utils.String.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
		String dsSplc = t.getPb();
		List<String[]> paramList = new ArrayList<String[]>();
		if(StringUtils.isNotNull(dsSplc)){
			t.setPbs(new String[]{"pb",dsSplc});
			paramList.add(t.getPbs());
		}
		t.setParamList(paramList);
		return dao.insert(t);
	}

	/**
	 * @description	： 更新数据
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午11:06:41
	 * @param
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
				if(lx.equals("pb")){
					csszForm.setPb(splc);
				}
			}
		}
		return csszForm;
	}
}
