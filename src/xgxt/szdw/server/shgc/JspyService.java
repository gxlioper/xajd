package xgxt.szdw.server.shgc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.szdw.dao.common.CommonDAO;
import xgxt.szdw.model.shgc.JspyModel;
import xgxt.szdw.server.common.CommonService;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;

public class JspyService extends CommonService{
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理思政队伍-上海工程-教师评优service类</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	
	CommonDAO dao = new CommonDAO();
	DAO basicDao = DAO.getInstance();
	public HashMap<String, String> getJspySqxx(String puber, String title, JspyModel myModel) throws SQLException {
	//    得到教师申请信息
		String tableName    = "view_sxjy_fdypyb";
		String zghChoose    = "";
		String xn           = Base.currXn;
		String xq           = Base.currXq;
		String nd           = Base.currNd;
		String zgh          = DealString.toGBK(myModel.getZgh());
		if(dao.getSffdy(puber)){
			zghChoose = puber; 
		}else{
			zghChoose = zgh;
		}
		String [] colList = new String []{"pk","zgh","xn","xq","nd","xyyj","xxyj","xxsh","lrrq","pyxm",
				"sdnj","sdbj","xsrs","lxdh","yddh","zwmc","xb","xm","bmdm","bmmc","zysj"}; 
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", zghChoose+xn+nd+xq+title);
		if(rs.get("zgh").equalsIgnoreCase("")){
			colList = new String []{"zgh","zwmc","bmmc","bmdm","lxdh","yddh","xb"};
			rs = dao.sxjyQueryOne3("view_fdyxx", colList, "zgh", zghChoose, rs, "");
		}
		rs = inputXnNjXq(rs);
		return rs;
	}
	
	public ArrayList<String[]> getJspyshList(JspyModel myModel, String userType) throws SQLException {
    //    得到申请评优教师列表
			String tableName    = "view_sxjy_fdypyb";
			String xn           = DealString.toGBK(myModel.getXn());
			String xq           = DealString.toGBK(myModel.getXq());
			String nd           = DealString.toGBK(myModel.getNd());
			String xydm         = DealString.toGBK(myModel.getXydm());
			String xm         = DealString.toGBK(myModel.getXm());
			String xxsh         = DealString.toGBK(myModel.getXxsh());
			String pyxm         = DealString.toGBK(myModel.getPyxm());
			StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition("","","","","",xm,"",nd,xq,xn));
			if(!("".equalsIgnoreCase(xydm.trim()))){
				query.append(" and bmdm='");
				query.append(xydm);
				query.append("' ");
			}
			if(!("".equalsIgnoreCase(xxsh.trim()))){
				query.append(" and xxsh='");
				query.append(xxsh);
				query.append("' ");
			}
			if(!("".equalsIgnoreCase(pyxm.trim()))){
				query.append(" and pyxm='");
				query.append(pyxm);
				query.append("' ");
			}
			
			String [] colList = new String []{"pk","zgh","xm","bmmc","lrrq","xxsh"}; 
			ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(), new String []{}, colList, "");
			return rs;
	}
	
	public List<HashMap<String, String>> getBmList(){
	//    得到申请评优教师列表
		List<HashMap<String, String>> bmList = basicDao.getBmList();
		return bmList;
	}

	public List getJspyTopTr() {
	//    得到教师评优查询审核的表头
		String tableName     = "view_sxjy_fdypyb";
		String [] colList    = new String []{"pk","zgh","xm","bmmc","lrrq","xxsh"}; 
		String[] colListCN     = basicDao.getColumnNameCN(colList, tableName);
		List topTr = basicDao.arrayToList(colList, colListCN);//表头 
		
		return topTr;
	}

	public boolean updataFdysq(JspyModel myModel, String title, String pk, HttpServletRequest request) 
		throws Exception {
		String tableName       = "sxjy_fdypyb";
		String pkComment       = "zgh||xn||nd||xq||pyxm";
		String [] colList      = new String []{"zgh","xn","xq","nd","xyyj","xxyj","xxsh","pyxm","sdnj","sdbj","xsrs","zysj"}; 
		String [] inputList    = ModelToStrings.modelToStrings(colList, myModel);
		inputList[7]           = title;
		boolean inserted = StandardOperation.delete(tableName, pkComment,pk, request);
		if(inserted){
			inserted = StandardOperation.insert(tableName, colList, inputList, request);
		}
		return inserted;
	}

	public HashMap<String, String> getJspySqxxForSh(String pk) {
		// 根据主键得到教师评优申请信息
		String tableName    = "view_sxjy_fdypyb";
		String [] colList = new String []{"pk","zgh","xn","xq","nd","xyyj","xxyj","xxsh","lrrq","pyxm","zysj",
				"sdnj","sdbj","xsrs","lxdh","yddh","zwmc","xb","xm","bmdm","bmmc","rzsj","csrq","zzmm"};
		
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk", pk);
		if(rs.get("rzsj")!=null&&rs.get("rzsj").equalsIgnoreCase("0")){
			rs.put("rzsj","1");
		}
		return rs;
	}

}
