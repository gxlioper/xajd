package xgxt.szdw.bjlh.cssz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.utils.ModelToStrings;

public class BjlhCsszDAO {
	DAO dao = DAO.getInstance();
	public boolean bjlhCsszSave(BjlhCsszForm myForm, HttpServletRequest request) throws Exception{
		String tableName = "xg_szdw_bjlh_csszb";
		boolean updata = false;
		String sql = "delete from xg_szdw_bjlh_csszb";
		updata = dao.runUpdate(sql, new String[]{});
		String[] fieldList = new String[] { "xn","khsfqd","khkssj","khjssj","khlrjzsj","jskhpfbl","xskhpfbl","xspfyxbl"};
		String[] inputList = new String[]{myForm.getXn(),myForm.getKhsfqd(),myForm.getKhkssj(),myForm.getKhjssj(),myForm.getKhlrjzsj(),myForm.getJskhpfbl(),myForm.getXskhpfbl(),myForm.getXspfyxbl()};
		updata = dao.runInsert(tableName, fieldList, inputList);
		return updata;
	}
	public HashMap<String, String> getCssz(){
		String sql = "select * from xg_szdw_bjlh_csszb";
		String[] colList = new String[] { "xn","khsfqd","khkssj","khjssj","khlrjzsj","jskhpfbl","xskhpfbl","xspfyxbl"};
		return(HashMap<String,String>)(dao.getMap(sql, new String[]{}, colList));
	}
	
	public HashMap<String,String> getSzdwCssz(){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map=new HashMap<String, String>();
		String sql="select csmc,csz from xg_szdw_szkh_csszb";
		List<String[]> list=dao.rsToVator(sql, new String[]{}, new String[]{"csmc","csz"});
		if(list!=null&&list.size()>0){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String[] strs = (String[]) iterator.next();
				map.put(strs[0], strs[1]);
			}
		}
		
		return map;
	}

}
