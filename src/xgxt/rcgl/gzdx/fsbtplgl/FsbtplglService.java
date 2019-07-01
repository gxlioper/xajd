package xgxt.rcgl.gzdx.fsbtplgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.form.User;
/**
 * 贵州大学副食补贴批量管理
 * @author yeyipin
 */
public class FsbtplglService extends CommService{
	private FsbtplglDAO fsbtplglDao = new FsbtplglDAO();
	
	/**
	 * 获取副食补贴代码维护
	 * @return
	 */
	public List<HashMap<String, String>> getFsbtList(){
		return fsbtplglDao.getFsbtList();
	}
	/**
	 * 获取月份List
	 * @return
	 */
	public List<HashMap<String, String>> getYfList(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		for(int i=1;i<13; i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", i+"");
			map.put("mc", i + "月份");
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * 获取topMap
	 * @param mk
	 * @return
	 */
	public Map<String, Object> getTopMap(String mk){
		String[] outputs = null;
		String[] topTr = null;
		
		if("cx".equalsIgnoreCase(mk)){
			outputs = new String[]{"pkValue","xh","xm","bjmc","btny","btmc","btje","jsr","ffsj"};
			topTr = new String[]{"主键","学号","姓名","班级名称","补贴年月","补贴名称","补贴金额","经手人","发放时间"};
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("outputs", outputs);
		map.put("topTr", topTr);
		
		return map;
	}
	
	/**
	 * 获取查询信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCxList(FsbtplglForm model, HttpServletRequest request,User user, String[] outPutList) throws Exception {
		String condition = " where 1=1 ";
		
		if(!"".equalsIgnoreCase(model.getXydm())&&model.getXydm()!=null){
			condition +="and xydm='"+model.getXydm()+"' ";
		}
		if(!"".equalsIgnoreCase(model.getZydm())&&model.getZydm()!=null){
			condition +="and zydm='"+model.getZydm()+"' ";
		}
		if(!"".equalsIgnoreCase(model.getBjdm())&&model.getBjdm()!=null){
			condition +="and bjdm='"+model.getBjdm()+"' ";
		}
		if(!"".equalsIgnoreCase(model.getXueh())&&model.getXueh()!=null){
			condition +="and xh like '%"+model.getXueh()+"%' ";
		}
		if(!"".equalsIgnoreCase(model.getXm())&&model.getXm()!=null){
			condition +="and xm like '%"+model.getXm()+"%' ";
		}
		condition += user.getQueryCondition();
		model.setCondition(condition);
		//用于前台查询
		request.setAttribute("searchTjstr", condition);
		return fsbtplglDao.getCxList(model,request, user, outPutList);
	}
	
	/**
	 * 保存发放补贴
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveFsbt(FsbtplglForm model,HttpServletRequest request) throws Exception{
		User user = getUser(request);
		String realName = (String) request.getSession().getAttribute("userNameReal");
		user.setRealName(realName);
		String searchTjstr =request.getParameter("searchTjstr");
		String xh = request.getParameter("xh");
		String xhstr = request.getParameter("xhstr");
		String[] pk_xh = null;
		if(xh !=null && !"".equalsIgnoreCase(xh)){//单个学生
			pk_xh = new String[]{xh};
		}else if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){//多个学生
			pk_xh = xhstr.split("!!splitOne!!");
		}else {//数据集
			pk_xh = fsbtplglDao.getXhs(searchTjstr);
		}
		model.setPk_xh(pk_xh);
		return fsbtplglDao.saveFsbt(model, user);
	}
	
	/**
	 * 批量删除
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
		return fsbtplglDao.batchDel(pkValues);
	}
	
	/**
	 * 获取副食补贴信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getFsbtInfo(String pkValue){
		return fsbtplglDao.getFsbtInfo(pkValue);
	}
	/**
	 * 获得默认数据
	 * @param string
	 * @return
	 */
	public String getMrxx(String key) {
		if("nd".equalsIgnoreCase(key)){
			return Base.currNd;
		}else if("yf".equalsIgnoreCase(key)){
			return fsbtplglDao.getMrYf();
		}else if("btdm".equalsIgnoreCase(key)){
			return fsbtplglDao.getMrbtdm();
		}
		return null;
	}
	
	/**
	 * 获得学生信息
	 * @param xh
	 * @param request
	 * @return
	 */
	public Map<String, String> getXsxx(String xh, HttpServletRequest request) {
		return fsbtplglDao.getXsxx(xh,request);
	}
	/**
	 * 获得补贴信息
	 * @param btdm
	 * @return
	 */
	public Map<String,String> getBtxx(String btdm) {
		return fsbtplglDao.getBtxx(btdm);
	}
}
