package xsgzgl.xtwh.general.qxgl.yhgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.xtwh.ksdh.KsdhService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.comm.BasicService;
import xsgzgl.xtwh.general.qxgl.GnmkModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户管理_service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */
public class YhglNewService extends BasicService{

	YhglNewDao dao = new YhglNewDao();
	
	/**
	 * 获得用户信息结果集表头
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public List<HashMap<String,String>> getYhxxTop(User user,String checkbox){
		DAO dao = DAO.getInstance();
		String[] en = new String[]{};
	    String[] cn = new String[]{};
		if ("12898".equals(Base.xxdm)) {
			 en = new String[] { "pk", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt", "sffdy", "sfbzr","sfbl"	};
			 cn = new String[] { "", "用户名", "姓名", "所属组", "所属部门", "是否分组", "启用状态","是否辅导员","是否班主任","是否思政可见" };
		}else {
			 en = new String[] { "pk", "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt", "sffdy", "sfbzr"	};
			 cn = new String[] { "", "用户名", "姓名", "所属组", "所属部门", "是否分组", "启用状态","是否辅导员","是否班主任" };
		}

		if("no".equalsIgnoreCase(checkbox)){
			en = new String[] { "yhm", "xm", "zmc", "bmmc", "sffz", "qyzt", "sffdy", "sfbzr" };
			cn = new String[] { "用户名", "姓名", "所属组", "所属部门", "是否分组", "启用状态","是否辅导员","是否班主任" };
		}
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * 获得用户信息结果集
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public ArrayList<String[]> getYhxxList(YhglNewForm myForm,User user) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		return dao.getYhxxList(myForm, user);
		
	}
	/**
	 * 获得用户信息结果集-根据查询结果批量
	 */
	public ArrayList<String[]> getYhxxAllList(YhglNewForm t,User user) 
	throws IllegalArgumentException, SecurityException, IllegalAccessException, 
	InvocationTargetException, NoSuchMethodException{
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getYhxxList(t, user);
		
	}
	
	/**
	 * 验证用户名是否存在
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean checkYhm(String yhm){
		return dao.checkYhm(yhm);
	}
	
	/**
	 * 保存用户权限
	 * 
	 * @date 2013-01-08
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm){
		return dao.saveYhqx(yhm);
	}
	
	/**
	 * 删除用户
	 * 
	 * @date 2013-01-09
	 * @author zhanghui
	 */
	public Boolean deleteYhxx(YhglNewForm myForm){		
		return dao.deleteYhxx(myForm);		
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public HashMap<String,String> getYhxx(String yhm){
		return dao.getYhxx(yhm);		
	}
	
	/**
	 * 
	 * @描述: 单独获取三级菜单列表
	 * @作者：zhangxiaobin [工号：1036]
	 * @日期：2015-1-30 下午04:44:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yhm
	 * @param zdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkThrList(String yhm){
		// 三级菜单
		List<HashMap<String, String>> gnmkListThr = dao.getGnmkList(null , yhm, null, 3);
		
		return gnmkListThr;
	}
	
	/**
	 * 获取所有的功能模块
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public List<GnmkModel> getAllGnmkList(String userName, String yhm, String zdm){
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// 一级菜单
		List<HashMap<String, String>> gnmkListOne = dao.getGnmkList(userName, yhm, zdm, 1);
		// 二级菜单
		List<HashMap<String, String>> gnmkListTwo = dao.getGnmkList(userName, yhm, zdm, 2);
		// 三级菜单
		List<HashMap<String, String>> gnmkListThr = dao.getGnmkList(userName, yhm, zdm, 3);
		
		// 获取所有的功能
		for(HashMap<String,String> mapOne : gnmkListOne){
			GnmkModel gnmkModelOne = new GnmkModel();
			String dmOne = mapOne.get("dm");
			gnmkModelOne.setGnmkdm(dmOne);
			gnmkModelOne.setGnmkmc(mapOne.get("mc"));
			gnmkModelOne.setChecked(mapOne.get("checked"));
			List<GnmkModel> childListOne = setChildList(gnmkModelOne, gnmkListTwo);
			
			for(GnmkModel gnmkModelTwo : childListOne){
				// 三级菜单
				setChildList(gnmkModelTwo, gnmkListThr);
			}

			list.add(gnmkModelOne);
		}
		
		return list;
	}

	/**
	 * 获取所有的功能模块
	 *
	 * @date 2018-09-19
	 */
	public List<HashMap<String, String>> getAllDjGnmkList(String yhm){

		List<HashMap<String, String>> list = dao.getDjGnmkList(yhm);
		return list;
	}
	
	/**
	 * 设置子节点
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	private List<GnmkModel> setChildList(GnmkModel model, List<HashMap<String, String>> list){
		List<GnmkModel> childList = new ArrayList<GnmkModel>();
		
		// 获取该gnmk下的子功能模块
		for (HashMap<String, String> map : list){
			String dm = map.get("dm");
			if(dm.substring(0, dm.length()-2).equalsIgnoreCase(model.getGnmkdm())){
				GnmkModel gnmkModel = new GnmkModel();
				
				gnmkModel.setGnmkdm(dm);
				gnmkModel.setGnmkmc(map.get("mc"));
				gnmkModel.setDxq(map.get("dxq"));
				gnmkModel.setChecked(map.get("checked"));
	
				childList.add(gnmkModel);
			}
		}
		
		list.removeAll(childList);
		
		model.setChildList(childList);
		return childList;
	}
	
	/**
	 * 保存用户权限
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm,HttpServletRequest request) throws Exception{

		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		String[] sqls=new String[1+gnmkdm.length];
		
		sqls[0]="delete from yhqxb where yhm='"+yhm.replace("'", "")+"'";
		String dxq=null;
		for(int i=0;i<gnmkdm.length;i++){
			dxq=Base.chgNull(request.getParameter("dxq_"+gnmkdm[i]),"",0);
			sqls[1+i]="insert into yhqxb values('"+yhm+"','"+gnmkdm[i]+"','"+dxq+"')";
		}
		boolean flag =dao.saveArrDate(sqls);
		if(flag){
			try {
				KsdhService ksdh = new KsdhService();
				ksdh.del_Rubbish_data(yhm.replace("'", ""));
			} catch (Exception e) {
				System.out.println("快速导航表【XG_XTWH_KSDHB】删除出错！");
			}
		}
		return flag;
	}

	/**
	 * 保存用户权限 20180921
	 */
	public Boolean yhGnsqSave(String yhm, JSONArray yhgnqxArray) throws Exception{

		DAO me = DAO.getInstance();
		String delSql = "delete from yhqxb where yhm = ? ";
		boolean result = me.runUpdate(delSql,new String[]{yhm});

		if(result){
			if(yhgnqxArray.length() > 0){
				String insertSql = "insert into yhqxb values (?,?,?)";
				List<String []> paramList = new ArrayList<String[]>();
				for(int i=0;i<yhgnqxArray.length();i++){
					JSONObject jobj = yhgnqxArray.getJSONObject(i);
					String gnmkdm = jobj.getString("gnmkdm");
					String dxq = jobj.getString("dxq").equals("null") ? null : jobj.getString("dxq");
					paramList.add(new String []{yhm,gnmkdm,dxq});
				}
				result = me.runBatchBoolean(insertSql,paramList);
			}
		}
		if(result){
			try {
				KsdhService ksdh = new KsdhService();
				ksdh.del_Rubbish_data(yhm.replace("'", ""));
			} catch (Exception e) {
				System.out.println("快速导航表【XG_XTWH_KSDHB】删除出错！");
			}
		}
		return result;
	}
	
	/**
	 * 获取用户权限变更功能模块（增加的，减少的）
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public HashMap<String,List<String>> getChgGnmk(String yhm,HttpServletRequest request) throws SQLException{

		//修改后的用户权限
		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		//当前用户权限
		String[] current = dao.getYhqx(yhm); 

		List<String> oldgnmk = dao.arrayToList(current);	
		List<String> newgnmk = dao.arrayToList(gnmkdm);
		
		//去掉当前功能和修改后功能相同值，即不变的功能模块，剩下的为增加的模块和减少的模块
		if(current != null && current.length != 0){
			for(int i=0;i<oldgnmk.size();i++){
				for(int j=0;j<newgnmk.size();j++){
					if(oldgnmk.get(i).equalsIgnoreCase(newgnmk.get(j))){
						oldgnmk.set(i, "");
						newgnmk.set(j, "");
					}
				}
			}
		}
		
		List<String> del = new ArrayList<String>();
		List<String> add = new ArrayList<String>();
		
		for(int i=0;i<oldgnmk.size();i++){
			if(!"".equalsIgnoreCase(oldgnmk.get(i))){
				del.add(oldgnmk.get(i));
			}
		}
		for(int i=0;i<newgnmk.size();i++){
			if(!"".equalsIgnoreCase(newgnmk.get(i))){
				add.add(newgnmk.get(i));
			}
		}
		
		HashMap<String,List<String>> map = new HashMap<String, List<String>>();
		map.put("add", add);	//新增的功能模块代码
		map.put("del", del);	//删除的功能模块代码
				
		return map;
	}
	
	/**
	 * 保存用户权限
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public Boolean saveYhqx(String yhm,HttpServletRequest request,HashMap<String,List<String>> map){
		
		List<String> add = map.get("add");		//用户组新增功能
		List<String> del = map.get("del");		//用户组减少功能
		String[] gnmkdm=request.getParameterValues("gnmkdm");//用户读写权限保存 2013-3-19
		String[] sqls=new String[add.size()*2+del.size()+gnmkdm.length];
		
		String dxq=null;
		int i=0;
		for(i=0;i<del.size();i++){
			sqls[i] = "delete from yhqxb where yhm ='"+yhm.replace("'", "")+"' and gnmkdm ='"+del.get(i)+"'";
		}
		for(int j=0;j<add.size();j++){	
			sqls[i+j]="delete from yhqxb where yhm ='"+yhm.replace("'", "")+"' and gnmkdm ='"+add.get(j)+"'";
		}
		i=i+add.size();
		for(int j=0;j<add.size();j++){	
			dxq=Base.chgNull(request.getParameter("dxq_"+add.get(j)),"",0);
			sqls[i+j]="insert into yhqxb (yhm,gnmkdm,dxq) values ('"+yhm.replace("'", "")+"','"+add.get(j)+"','"+dxq+"') ";
		}
		//用户读写权限保存 2013-3-19 
		for (int j = 0; j < gnmkdm.length; j++) {
			dxq=Base.chgNull(request.getParameter("dxq_"+gnmkdm[j]),"",0);
			sqls[i+j]=" update yhqxb b set b.dxq ='"+dxq+"' where b.gnmkdm = '"+gnmkdm[j]+"'";
		}
		boolean flag = true;
		try {
			flag = dao.saveArrDate(sqls);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 初始化用户密码
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public Boolean cshYhmm(YhglNewForm myForm){		
		return dao.cshYhmm(myForm);		
	}
	
	/**
	 * 获取辅导员信息
	 * 
	 * @date 2013-01-14
	 * @author zhanghui
	 */
	public HashMap<String,String> getFdyxx(String yhm){
		return dao.getRsInfo("fdyxxb", "zgh", yhm, 
				new String[]{"zgh","xm","bmdm"});		
	}
	
	/**
	 * 批量修改用户信息
	 * 
	 * @date 2013-01-18
	 * @author zhanghui
	 */
	public Boolean plUpdateYhxx(YhglNewForm myForm){		
		return dao.plUpdateYhxx(myForm);		
	}
	/**
	 * 批量取消分配
	 */
	public Boolean plQxYhxx(YhglNewForm myForm){		
		return dao.plQxYhxx(myForm);		
	}
	/**
	 * 用户分组
	 */
	public Boolean yhfz(YhglNewForm myForm){		
		return dao.yhfz(myForm);		
	}
	/**
	 * 用户停用
	 */
	public Boolean yhty(YhglNewForm myForm){		
		return dao.yhty(myForm);		
	}
	/**
	 * 用户思政可见
	 * @throws Exception 
	 */
	public int[] szkj(String[] zghs, String sfbl) throws Exception {
		// TODO Auto-generated method stub
		return dao.szkj(zghs,sfbl);
	}
}
