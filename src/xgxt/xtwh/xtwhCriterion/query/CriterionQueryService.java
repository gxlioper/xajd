package xgxt.xtwh.xtwhCriterion.query;

import java.util.List;

import xgxt.utils.MakeQuery;
import xgxt.xtwh.xtwhCriterion.CriterionService;
import xgxt.xtwh.xtwhCriterion.QxwhForm;
import xgxt.xtwh.xtwhCriterion.jsgl.JsglDAO;

/**
 * @author luning 
 * @describe 通用查询用service，其他权限维护所有模块全部继承该类
 */
public class CriterionQueryService extends CriterionService{
	private CriterionQueryDAO dao = new CriterionQueryDAO();
	
/**
 * @author luning
 * @describe 获取角色操作范围列表
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public List getJsczfwList(){
		//具体业务放在角色管理DAO中实现
		JsglDAO jsglDAO = new JsglDAO();
		
		return jsglDAO.getJsczfwList();
		
	}
	
/**
 * @author luning
 * @describe 获取角色类型列表
 * @param mapping
 * @param form
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public List getJslxList(){
		//具体业务放在角色管理DAO中实现
		JsglDAO jsglDAO = new JsglDAO();
		
		return jsglDAO.getJslxList();
		
	}
	
	/**
	 * 查询角色用户List
	 * @param model
	 * @return
	 */
	public List<String[]> getJsyhList(QxwhForm model){
		return dao.getJsyhList(model);
	}
	
	
	/**
	 * 查询角色权限List
	 * @param model
	 * @return
	 */
	public List<String[]> getJsqxList(QxwhForm model){
		return dao.getJsqxList(model);
	}
	
	/**
	 * 查询用户权限List
	 * @param model
	 * @return
	 */
	public List<String[]> getYhqxList(QxwhForm model){
		return dao.getYhqxList(model);
	}
}
