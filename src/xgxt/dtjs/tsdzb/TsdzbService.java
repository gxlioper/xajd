/**
 * @部门:学工产品事业部
 * @日期：2017-10-30 下午05:30:15 
 */  
package xgxt.dtjs.tsdzb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 特色(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2017-10-30 下午05:30:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsdzbService extends SuperServiceImpl<TsdzbForm, TsdzbDao>{
	private TsdzbDao dao = new TsdzbDao();
	
	private static String WSH = "0";
	
	private static String TG = "1";
	
	private static String WTG = "2";
	
	/** 
	 * @描述:增加党支部(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-30 下午07:15:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean addTsdzb(TsdzbForm form) throws Exception{
		String unid = UniqID.getInstance().getUniqIDHash();
		form.setDzbid(unid);
		form.setShzt(WSH);
		boolean result = runInsert(form);
		if(null != form.getBjdms()){
			result = dao.plInsert(form.getBjdms(), form.getDzbid());
		}
		return result;
	}
	
	/** 
	 * @描述:更新党支部(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午03:22:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateTsdzb(TsdzbForm form) throws Exception{
		boolean result = dao.runUpdate(form);
		if(result && !"sh".equalsIgnoreCase(form.getType())){
			deleteGlByDzbId(form);
		}
		if(!"sh".equalsIgnoreCase(form.getType())){
			if(null != form.getBjdms() && form.getBjdms().length > 1){				
				dao.plInsert(form.getBjdms(), form.getDzbid());
			}
			if(null != form.getBjdmms() && form.getBjdmms().length > 1){
				if(null != form.getBjdms() && form.getBjdms().length > 0){					
					ArrayList<String> bjdms = new ArrayList<String>();
					for(String str : form.getBjdms()){
						bjdms.add(str);
					}
					
					ArrayList<String> bjdmms = new ArrayList<String>();
					for(String str : form.getBjdmms()){
						bjdmms.add(str);
					}
					
					for(Iterator<String> it = bjdmms.iterator();it.hasNext();){
						String s = it.next();
						for(String str : bjdms){
							if(str.equals(s)){
								it.remove();
							}
						}
					}
					if(bjdmms.size() > 0){					
						dao.plInsert(bjdmms.toArray(new String[]{}), form.getDzbid());
					}
				}else{
					dao.plInsert(form.getBjdmms(), form.getDzbid());
				}
			}
		}
		return result;		
	}
	
	/**
	 * @throws Exception  
	 * @描述:(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-30 下午07:18:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteGlByDzbId(TsdzbForm form) throws Exception{
		return dao.deleteByDzbId(form.getDzbid());
	}
	
	/** 
	 * @描述:根据用户身份获取班级列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 上午09:56:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getBjList(User user,String dzbid){
		return dao.getBjList(user,dzbid);
	}
	
	/**
	 * @throws SQLException  
	 * @描述:根据党支部id获取班级列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午03:59:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<String> 返回类型 
	 * @throws 
	 */
	public List<String> getBjListBydzbId(String dzbId) throws SQLException{
		return dao.getBjListByDzbId(dzbId);
	}
	
	/** 
	 * @描述:根据ids删除关联(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-10-31 下午07:37:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @throws Exception
	 * void 返回类型 
	 * @throws 
	 */
	public void delGlByIds(String[] ids) throws Exception{
		dao.delGlByIds(ids);
	}
	
	/**
	 * @description	： 获取特殊团支部信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 上午09:06:24
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public TsdzbForm getTsdzbxx(TsdzbForm model) throws Exception{
		TsdzbForm form = getModel(model);
		String bjxx = dao.getTsdzbXX(model.getDzbid());
		form.setBjxx(bjxx);
		return form;
		
	}
	
	/**
	 * @description	： 获取团支部审核列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 上午10:53:47
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getPageListForSh(TsdzbForm t, User user) throws Exception{
		return dao.getPageListForSh(t, user);
	}
	
	/**
	 * @description	： 批量撤销
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-1 下午03:30:16
	 * @return
	 * @throws SQLException 
	 */
	public boolean plCx(String[] dzbids) throws SQLException{
		return dao.cx(dzbids);
	}
	
	/**
	 * @description	： 特色党支部导出
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-2 上午10:19:49
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllListForTsdzsh(TsdzbForm t, User user) throws Exception {
		return dao.getAllListForTsdzbSh(t, user);
	}
	
	/**
	 * @description	： 是否存在党支部
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-3 下午05:21:24
	 * @return
	 */
	public boolean isExist(String dzbmc,String dzbId){
		return dao.countTsdzbMc(dzbmc, dzbId);
	}
	
	/**
	 * @description	：获取学院列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:35:01
	 * @return
	 */
	public List<HashMap<String,String>> getXyList(String userStatus,String userName){
		return dao.getXyList(userStatus,userName);
	}
	
	/**
	 * @description	： 获取专业列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:50:20
	 * @param xydm
	 * @return
	 */
	public List<HashMap<String,String>> getZyList(String xydm,String userStatus,String userName){
		return dao.getZyList(xydm,userStatus,userName);
	}
	
	/**
	 * @description	： 获取班级列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:51:33
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return
	 */
	public List<HashMap<String,String>> getbjList(String dzbid,String xydm,String zydm,String nj,String userStatus,String userName){
		return dao.getbjList(dzbid,xydm,zydm,nj,userStatus,userName);
	}
	
	/**
	 * @description	： 获取年级列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-30 上午10:52:27
	 * @return
	 */
	public List<HashMap<String,String>> getNjList(){
		return dao.getNjList();
	}
}
