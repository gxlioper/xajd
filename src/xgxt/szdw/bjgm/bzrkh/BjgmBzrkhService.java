package xgxt.szdw.bjgm.bzrkh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewInit;

/**
 * 
* 
* 类名称：BjgmBzrkhService 
* 类描述：北京工贸班主任考核Service
* 创建人：yijd 
* 创建时间：2012-6-1 上午09:20:00 
* 修改备注：  
* @version 
*
 */
public class BjgmBzrkhService extends CommService{
	BjgmBzrkhDAO dao=new BjgmBzrkhDAO();
	
	/**
	 * 分页查询
	 * @param model
	 * @param request
	 * @return
	 */
	public List<HashMap<String, String>> queryBzrkhxx(BjgmBzrkhForm model,HttpServletRequest request){
		try {
			return dao.queryBzrkhxx(model,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询班级信息，根据职工号
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> queryBjxxByZgh(BjgmBzrkhForm model) throws Exception{
		return dao.queryBjxxByZgh(model);
	}
	
	/**
	 * 获取topTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		if("bjgm".equalsIgnoreCase(mk)){
			topTr = new String[]{"学年","学期","部门名称","职工号","姓名","出勤","材料","年级",
					"学生课","个人得分","班级得分","总分"};
		}
		
		return topTr;
	}
	
	
	/**
	 * 批量保持班主任考核信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public int insertBzrshs(BjgmBzrkhForm model) throws Exception{
		List<HashMap<String, String>> list=getBjgmBzrkh(model);
		if(list!=null && list.size()>0){
			return -1;
		}
		int length=0;
		if(model!=null){
			if(model.getBb()!=null){
				length=model.getBb().length;
			}else{
				return -1;
			}
		}
		int[] rows=dao.insertBzrshs(model);
		int succeed=length - rows.length;
		return succeed;
	}
	
	/**
	 * 查询班主任考核数据集，根据主健
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getBjgmBzrkh(BjgmBzrkhForm model){
		return dao.getBjgmBzrkh(model);
	}
	
	/**
	 * 查询班主任考核数据集，根据主健用于数据修改
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBjgmBzrkhOnUpdate(BjgmBzrkhForm model) throws Exception{
		return dao.getBjgmBzrkhOnUpdate(model);
	}
	
	/**
	 * 修改班主任考核信息
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateArrBjgmBzrkh(BjgmBzrkhForm model,User user) throws Exception{
		List<HashMap<String, String>> list=getBjgmBzrkh(model);
		BjgmBzrkhForm czModel=new BjgmBzrkhForm();
		BeanUtils.copyProperties(czModel, model);
		//清除当前老师多余的班级
		try {
			dao.deleteBjgmBzrkhByFutile(model);
			if(model!=null){
				//班级为空不能保存
				if(model.getBjdms()==null){
					return false;
				}
				if(list!=null){
					for (int i = 0; i < model.getBb().length; i++) {
						for (int j = 0; j < list.size(); j++) {
							if(model.getBjdms()[i].equals(list.get(j).get("bjdm"))){
								//班级代码相等修改数据
								setAtrr(model, i, czModel);
								dao.updateBjgmBzrkh(czModel, user);
								break;
							}
							//内循环最后一次
							if((j+1)==list.size()){
								//没有找到匹配班级，说明需要新增班级审核信息
								setAtrr(model, i, czModel);
								dao.insertBzrshs(czModel);
							}
						}
					}
				}else{
					//数据库中当前学期没有当前老师的审核
					dao.insertBzrshs(model);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 批量删除数据
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBjgmBzrkh(List<String[]> ids) throws Exception{
		return dao.deleteBjgmBzrkh(ids);
	}
	
	//工具类，重新设置model
	public void setAtrr(BjgmBzrkhForm model,int i,BjgmBzrkhForm czModel){
		String[] array=null;
		array=new String[]{model.getBb()[i]};
		czModel.setBb(array);
		array=new String[]{model.getBjdf()[i]};
		czModel.setBjdf(array);
		array=new String[]{model.getBjdms()[i]};
		czModel.setBjdms(array);
		array=new String[]{model.getJl()[i]};
		czModel.setJl(array);
		array=new String[]{model.getJli()[i]};
		czModel.setJli(array);
		array=new String[]{model.getKjc()[i]};
		czModel.setKjc(array);
		array=new String[]{model.getRs()[i]};
		czModel.setRs(array);
		array=new String[]{model.getSq()[i]};
		czModel.setSq(array);
		array=new String[]{model.getWsaq()[i]};
		czModel.setWsaq(array);
	}
}
