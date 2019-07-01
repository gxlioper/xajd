package xgxt.szdw.bjlh.cssz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class BjlhCsszService {
	BjlhCsszDAO myDAO= new BjlhCsszDAO();
	public boolean bjlhCsszSave(BjlhCsszForm form, HttpServletRequest request) throws Exception{
		return myDAO.bjlhCsszSave(form,request);
	}
	public HashMap<String, String> getCssz(){
		return myDAO.getCssz();
	}
	
	public HashMap<String, String> getSzdwCssz(){
		return myDAO.getSzdwCssz();
	}
}
