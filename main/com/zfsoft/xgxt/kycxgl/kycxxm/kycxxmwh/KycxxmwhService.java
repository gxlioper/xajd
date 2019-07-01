package com.zfsoft.xgxt.kycxgl.kycxxm.kycxxmwh;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import xgxt.utils.GetTime;

/**
 * 科研创新项目类别
 */
public class KycxxmwhService extends SuperServiceImpl<KycxxmwhForm, KycxxmwhDao>{
	
	private KycxxmwhDao dao = new KycxxmwhDao();
	
	public KycxxmwhService() {
		super.setDao(dao);
	}
	/**
	 * 保存修改
	 */
	public boolean updateKycxxmwh(KycxxmwhForm model) throws Exception {
		return dao.updateKycxxmwh(model);
	}
	/**
	 * 保存修改申请开关
	 */
	public boolean updateKycxxmwhSqkg(KycxxmwhForm model) throws Exception {
		return dao.updateKycxxmwhSqkg(model);
	}
	/**
	 * 验证唯一性
	 */
	public boolean checkKycxxmwhSave(KycxxmwhForm model) throws Exception {
		return dao.checkKycxxmwhSave(model);
	}
	/**
	 * 删除验证
	 */
	public String checkKycxxmwhDel(String values) throws Exception {
    	String rs = "";
    	String[] rsArr = dao.checkKycxxmwhDel(values);
    	for(int i = 0; i < rsArr.length; i++){
    		rs += rsArr[i];
			if(i < rsArr.length - 1){
				rs += "、";
			}
		}
		return rs;
	}
	/**
	 * 查询可以申请的类别
	 */
	public List<HashMap<String,String>> getKycxxmwhOpenList() throws Exception {
		return dao.getKycxxmwhOpenList();
	}
	
}
