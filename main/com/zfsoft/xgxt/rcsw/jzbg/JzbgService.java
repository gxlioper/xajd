/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.jzbg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JzbgService extends SuperServiceImpl<JzbgForm, JzbgDao>{

	private JzbgDao dao = new JzbgDao();
	
	public JzbgService() {
		super.setDao(dao);
	}
	
	public boolean isExist(JzbgForm model) {
		return dao.isExist(model);
	}

	public List<HashMap<String, String>> getPjxxPageList(JzbgForm model,
			User user) throws Exception {
		return dao.getPjxxPageList(model,user);
	}

	public boolean insertPjxx(JzbgForm t, User u) throws Exception {
		return dao.insertPjxx(t,u);
	}

	public boolean updatePjxx(JzbgForm t) throws Exception {
		return dao.updatePjxx(t);
	}

	public boolean JzbgDelete(String jzid) throws Exception {
		return dao.JzbgDelete(jzid);
	}

	public boolean isExistPjxx(String jzid) {
		return dao.isExistPjxx(jzid);
	}

	public HashMap<String, String> getJzbgXX(JzbgForm myForm) {
		return dao.getJzbgXX(myForm);
	}
public HashMap<String, String>  getModelXX(JzbgForm t)throws Exception{
		return dao.getModelXX(t);
	}
}
