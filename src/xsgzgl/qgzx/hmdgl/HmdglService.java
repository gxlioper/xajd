/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package xsgzgl.qgzx.hmdgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;

public class HmdglService extends SuperServiceImpl<HmdglForm, HmdglDao>{


	public HashMap<String, String> getSingleHmdInfo(HmdglForm t) {
		return dao.getSingleHmdInfo(t);
	}


	public List<HashMap<String,String>> getYrdwList(HmdglForm t,User user) throws Exception {
		return dao.getYrdwList(t,user);
	}

	public List<HashMap<String,String>> getXsHmdList(HmdglForm t,User user) throws Exception {
		return dao.getXsHmdList(t,user);
	}

	public boolean addxshmdSave(HmdglForm t) throws Exception {
		return dao.addxshmdSave(t);
	}

	public HashMap<String,String> getYrdwxxByUser(User user){
		return dao.getYrdwxxByUser(user);
	}

	public boolean delXsHmd(String[] ids) throws Exception {
		return dao.delXsHmd(ids);
	}
}
