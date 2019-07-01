package xsgzgl.gygl.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zfsoft.xgxt.gygl.gywp.GywpxxDAO;

import common.GlobalsVariable;

public class GyglNewInter {
	
	public List<HashMap<String, Object>> getStuGyAllList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "公寓信息");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuGyxxList(xh));
		
		rs.add(map);
		List<String[]> list=getQswpList(xh);
		//list为0表明没有维护xg_gygl_wfxy_qswpxxb，不是潍坊学院
		if(list.size()!=0){
			map = new HashMap<String, Object>();
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "寝室物品信息");
			String[] title = new String[]{"物品名称","所属大类","物品类别","数量","是否在合格期","是否完好"};
			list.add(0, title);
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}
		return rs;
	}
	
	/** 
	 * @描述:(获取学生寝室物品List)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 下午05:04:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<String[]> 返回类型 
	 * @throws 
	 */
	private List<String[]> getQswpList(String xh) {
		GywpxxDAO dao=new GywpxxDAO();
		List<String[]> rs=dao.getQswpList(xh);
		return rs;
	}

	public List<String[]> getStuGyxxList(String xh) {
		GyglNewInit init = new GyglNewInit();		
		GyglNewDAO dao = new GyglNewDAO();		
		
		String[] title = new String[]{};
		String[] out = new String[]{};

		if(!"0".equals(GyglNewInit.XQBJ)&&!"0".equals(GyglNewInit.YQBJ)){//校区、园区
			title = new String[]{"校区","园区","楼栋","层号","寝室号", "床位号", "寝室电话"};
			out = new String[]{"xqmc","yqmc","ldmc","chmc","qsh","cwh", "qsdh"};
		}else if(!"0".equals(GyglNewInit.XQBJ)){//校区
			title = new String[]{"校区","楼栋","层号","寝室号", "床位号", "寝室电话"};
			out = new String[]{"xqmc","ldmc","chmc","qsh","cwh", "qsdh"};
		}else if(!"0".equals(GyglNewInit.YQBJ)){//园区
			title = new String[]{"园区","楼栋","层号","寝室号", "床位号", "寝室电话"};	
			out = new String[]{"yqmc","ldmc","chmc","qsh","cwh", "qsdh"};
		}else{
			title = new String[]{"楼栋","层号","寝室号", "床位号", "寝室电话"};	
			out = new String[]{"ldmc","chmc","qsh","cwh", "qsdh"};
		}
		
		List<String[]> rs = dao.getStuGyxxList(xh,out);//学生成绩列表
		rs.add(0, title);
		return rs;
	}
}
