package xsgzgl.gygl.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;

/**
 * Title: 学工管理系统
 * Description: 公寓管理_第三版_通用-service类
 * Copyright: Copyright (c) 2011
 * Company: zfsoft
 * @author zhang
 * @version 3.0
 */

public class GyglNewService extends CommService {

	GyglNewDAO gyglNewDao = new GyglNewDAO();
	
	public List<HashMap<String, String>> getToplist(String[] colListCN ) throws Exception {
		String[] en = new String[colListCN.length];
		for(int i=0;i<en.length;i++){
			en[i]="tr_"+i;
		}
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.arrayToList(en,colListCN);
		return list;
	}
			
	/**
	 * 获取topTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		
		if("qswh".equalsIgnoreCase(mk)){
			topTr = new String[]{"楼栋名称","寝室号","寝室性别","所属年级","所属"+Base.YXPZXY_KEY,"床位数","保留床位","空闲床位数","入住情况"};
		}else if("cwwh".equalsIgnoreCase(mk)){
			if("xydm".equals(GyglNewInit.CWFPDX)){//床位分配对象为学院时，不显示所属班级名称
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","所属年级","所属"+Base.YXPZXY_KEY,"学号","姓名","是否保留"};
			}else if("zydm".equals(GyglNewInit.CWFPDX)){//床位分配对象为学院时，不显示所属班级名称
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","所属年级","所属"+Base.YXPZXY_KEY,"所属专业","学号","姓名","是否保留"};
			}else {
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","所属年级","所属"+Base.YXPZXY_KEY,"所属班级","学号","姓名","是否保留"};
			}
		}else if("zsgl".equalsIgnoreCase(mk)){		
			if(Globals.XXDM_zjgmzyjsxy.equalsIgnoreCase(Base.xxdm)){
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","学号","姓名","专业名称","班级","是否保留","联系方式"};
			}else if("13033".equalsIgnoreCase(Base.xxdm)) {
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","学号","姓名","专业名称","班级","是否保留","备注","违纪情况"};
			}else if("10868".equals(Base.xxdm)){
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","学号","姓名","专业名称","班级","是否保留","备注","挂星时间","星级","撤星时间"};
			} else if("12216".equals(Base.xxdm)) {
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","学号","姓名","学院名称","专业名称","班级","是否保留","备注"};
			}else {
				topTr = new String[]{"楼栋名称","寝室号","床位号","床位性别","学号","姓名","专业名称","班级","是否保留","备注"};
			}
		}else if("xxtj".equalsIgnoreCase(mk)){
			if("1".equals(GyglNewInit.XQBJ)){
				topTr = new String[]{"校区", "楼栋", "总数", "男生数", "女生数", "总数", "男生数", "女生数", "总数", "男生数", "女生数", "总数", "男生数", "女生数"};
			}else{
				topTr = new String[]{"楼栋", "总数", "男生数", "女生数", "总数", "男生数", "女生数", "总数", "男生数", "女生数", "总数", "男生数", "女生数"};
			}
		}else if("sjsz".equalsIgnoreCase(mk)){
			topTr = new String[]{"年级", Base.YXPZXY_KEY, "开始时间", "结束时间", "开启状态"};
		}else if("yh".equalsIgnoreCase(mk)){
			topTr = new String[]{"用户名", "姓名", "所在部门"};
		}else if("xs".equalsIgnoreCase(mk)){
			topTr = new String[]{"学号", "姓名", "性别", "年级", Base.YXPZXY_KEY, "专业", "班级"};
		}else if("gyglry".equals(mk)){
			if("10466".equals(Base.xxdm)){//河南农业大学
				topTr=new String[]{"楼栋","层号","寝室号","类型","性别","学号","姓名","联系电话","备注"};
			}else {
				topTr=new String[]{"楼栋","层号","寝室号","类型","性别","学号","姓名","联系电话","宿舍电话","备注"};
			}
		}else if("rzyy".equals(mk)){
			topTr = new String[]{"入住原因代码","入住原因名称"};
		}else if("dtxxtj".equals(mk))
			topTr = new String[]{"系部名称", "时间", "名字", "时间", "名字", "上会时间", "名字", "预审合格下文时间", "上会时间", "名字", "预备期", "上会时间", "名字"};
		return topTr;
	}
	
	/**
	 * 获取楼栋信息
	 * @return
	 */
	public List<HashMap<String, String>> getLdList(){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderBy", "order by lddm");
		
		return gyglNewDao.getRsList("xg_gygl_new_ldxxb", map, new String[]{"lddm", "ldmc"});
	}
	/**
	 * 
	 * @描述:查询楼栋信息（过滤用户权限）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-1-3 上午10:07:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdxxList(User user){
		
		return gyglNewDao.getLdxxList(user);
	}
	
	public HashMap<String,String> getStuInfo_gy(String xh){
		return gyglNewDao.getStuInfo_gy(xh);
	}
	
	/**
	 * 获取退宿原因list
	 * @return
	 */
	public List<HashMap<String, String>> getTsyyList(){
		return gyglNewDao.getTsyyList();
	}
	
	public List<HashMap<String, String>> getTzyyList(){
		return gyglNewDao.getTzyyList();
	}
	/**
	 * 获取公寓管理员的条件
	 * @param myForm
	 * @return
	 */
	public String getSearchTjByGyfdy(HttpServletRequest request){
		return gyglNewDao.getSearchTjByGyfdy(request);
	}
	/**
	 * 获取公寓管理员的条件（not in）
	 */
	public String getSearchTjByGyfdyNotIn(HttpServletRequest request){
		return gyglNewDao.getSearchTjByGyfdyNotIn(request);
	}
	
	/**
	 * 获取初始化类型list
	 * @return
	 */
	public List<HashMap<String, String>> getCshlxList(){
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		map.put("dm", "1");
		map.put("mc", "清空床位全部所属");
		list.add(map);
		String cwfpdx = GyglNewInit.CWFPDX;
		if("bjdm".equalsIgnoreCase(cwfpdx)){
			map = new HashMap<String, String>();
			map.put("dm", "0");
			map.put("mc", "清空床位班级所属");
			list.add(map);
		}
		
		return list;
	}
	
	public List<HashMap<String,String>> getXqList(){

		Map<String, String> map = new HashMap<String, String>();
		map.put("orderBy", "order by dm");
		
		return gyglNewDao.getRsList("dm_zju_xq", map, new String[]{"dm", "xqmc"});
	}
	
	/**2012-03-06 23
	 * 获取园区列表
	 * @return
	 */
	public List<HashMap<String,String>> getYqList(String xqdm){

		Map<String, String> map = new HashMap<String, String>();
		if("1".equals(GyglNewInit.XQBJ)){//校区标记
			map.put("xqdm", xqdm);
		}
		map.put("orderBy", "order by yqdm");
		
		
		return gyglNewDao.getRsList("zxbz_ssyqdm", map, new String[]{"yqdm", "yqmc"});
	}
	
	/**
	 * 获取入住原因list
	 * @return
	 */
	public List<HashMap<String, String>> getRzyyList(){
		return gyglNewDao.getRzyyList();
	}
}
