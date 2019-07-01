package xsgzgl.gygl.gyjldmgl;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xsgzgl.jcsj.comm.JcsjService;

public class GyjldmglService extends JcsjService{
	
	private GyjldmglDao dao=new GyjldmglDao();
	
	/**
	 * 获得表头
	 * @param type
	 * @return
	 */
	public String[] getTopTr(String type){
		String[] str = null;
		if(type.equals("jldl")){
			//重庆工商大学个性化
			if("11799".equals(Base.xxdm)){
				str = new String[]{"奖惩大类代码","奖惩大类名称"}; 
			}else{				
				str = new String[]{"纪律大类代码","纪律大类名称"};
			}
		}else if(type.equals("jllb")){
			//重庆工商大学个性化
			if("11799".equals(Base.xxdm)){
				str = new String[]{"奖惩类别代码","奖惩类别名称","奖惩类别扣分","奖惩大类名称"};
			}else{				
				str = new String[]{"纪律类别代码","纪律类别名称","纪律类别扣分","纪律大类名称"};
			}
		}else if (type.equals("cflb")){
			str = new String[]{"处分类别代码","处分类别名称"};
		}
		return str;
	}
	
	/**
	 * 获得纪律大类信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJldlList(GyjldmglForm model) throws Exception{
		return dao.getJldlList(model);
	}
	
	/**
	 * 保存纪律大类信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveJldlInfo(GyjldmglForm model,String type) throws Exception{
		return dao.saveJldlInfo(model, type);
	}
	
	/**
	 * 删除纪律大类信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteJldlInfo(GyjldmglForm model) throws Exception{
		String b=dao.deleteJldlInfo(model);
		return "exists".equalsIgnoreCase(b )? "类别代码已经使用，不能删除！" : ("yes".equalsIgnoreCase(b) ? "删除成功！":"删除失败！");
	}
	/**
	 * 获得纪律类别信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJllbList(GyjldmglForm model) throws Exception{
		return dao.getJllbList(model);
	}
	/**
	 * 保存纪律类别信息
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveJllbInfo(GyjldmglForm model,String type) throws Exception{
		return dao.saveJllbInfo(model, type);
	}
	
	/**
	 * 删除纪律类别信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String deleteJllbInfo(GyjldmglForm model) throws Exception{
		String b=dao.deleteJllbInfo(model);
		return "exists".equalsIgnoreCase(b )? "类别代码已经使用，不能删除！" : ("yes".equalsIgnoreCase(b) ? "删除成功！":"删除失败！");
	}
	/**
	 * 获得纪律类别List<HashMap<String,String>>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getJllbListMap(GyjldmglForm model) throws Exception{
		return dao.getJllbListMap(model);
	}
	
	/**
	 * 保存处分类别名称
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String saveCflbInfo(GyjldmglForm model, String type)	throws Exception { 
		return dao.saveCflbInfo(model, type);
	}
	
	/**
	 * 获得处分类别信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCflbList(GyjldmglForm model) throws Exception{
		return dao.getCflbList(model);
	}
}
