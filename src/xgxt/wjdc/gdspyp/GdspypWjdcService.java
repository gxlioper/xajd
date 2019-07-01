package xgxt.wjdc.gdspyp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;

public class GdspypWjdcService {
	private GdspypWjdcDAO wjdcDao = new GdspypWjdcDAO();
	
	/**
	 * 获取表头
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		if("jgfx".equalsIgnoreCase(mk)){
			topTr = new String[]{"学号", "姓名",Base.YXPZXY_KEY, "专业", "班级", "总分"};
		}
		
		return topTr;
	}
	
	/**
	 * 结果分析查询
	 * @param model
	 * @return
	 */
	public List<String[]> jgfxQuery(GdspypWjdcForm model){
		return wjdcDao.jgfxQuery(model);
	}
	
	public boolean savePynr(GdspypWjdcForm model){
		boolean flag = false;
		flag = wjdcDao.delPynr(model);
		
		if(flag){
			flag = wjdcDao.savePynr(model);
		}
		return flag;
	}
	
	public Map<String, String> getJgfxOne(String pk){
		return wjdcDao.getJgfxOne(pk);
	}
	
	public boolean delJgfx(String[] pks){
		List<String[]> params = new ArrayList<String[]>();
		
		for(String pk : pks){
			params.add(new String[]{pk});
		}
		
		return wjdcDao.delJgfx(params);
	}
	
	/**
	 * 获取评议代码维护
	 * @return
	 */
	public List<HashMap<String, String>> getPy(){
		return wjdcDao.getPy();
	}
	
		/**
	 * 获取马氏量表题目表
	 * @return
	 */
	public List<HashMap<String,String>> getMslbtmb(){
		return wjdcDao.getMslbtmb();
	}
	
	public String saveWjhd(HttpServletRequest request) throws Exception{
		String userName=request.getSession().getAttribute("userName").toString();
		String zfh=null;//最符合
		String zbf=null;//最不符
		ArrayList<String> sql=new ArrayList<String>();
		for(int i=1;i<100;i++){
			zfh=request.getParameter("zfh_"+i);
			zbf=request.getParameter("zbf_"+i);
			if(!Base.isNull(zfh)&&!Base.isNull(zbf)){
				sql.add("insert into xg_wjdc_gdspyp_mslbxsdab values('"+userName+"','"+i+"','"+zfh+"','"+zbf+"')");
			}
		}
		if(sql.size()==0){
			return "没有数据可保存！";
		}
		boolean b=wjdcDao.saveWjhd(sql.toArray(new String[]{}));
		if(b){
			return "保存成功！";
		}else{
			return "保存失败！";
		}
	}
	
	/**
	 * 获取问卷回答答案
	 * @param userName
	 * @return
	 */
	public List<HashMap<String,String>> getWjhdda(String userName){
		return wjdcDao.getWjhdda(userName);
	}
	
	/**
	 * 获取学生回答具体信息
	 * @param userName
	 * @return
	 */
	public List<Map<String, String>> getHdInfoList(String userName){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		//问卷试题
		List<HashMap<String, String>> wjstList = getMslbtmb();
		//问卷答案
		List<HashMap<String, String>> wjdaList = getWjhdda(userName);
		
		for(Map<String, String> wjstMap : wjstList){
			String stId = wjstMap.get("id");
			
			for(Map<String, String> wjdaMap : wjdaList){
				if(stId.equalsIgnoreCase(wjdaMap.get("id"))){
					wjstMap.put("zfh", wjdaMap.get("zfh"));
					wjstMap.put("zbf", wjdaMap.get("zbf"));
					wjstMap.put("fs", wjdaMap.get("fs"));
					
					break;
				}
			}
			
			list.add(wjstMap);
		}
		
		return list;
	}
}
