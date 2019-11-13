package xsgzgl.xtwh.general.qxgl.yhzgl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.base.log.LogInfo;
import com.zfsoft.xgxt.xtwh.ksdh.KsdhService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.CommanAction;
import xgxt.comm.CommDAO;
import xgxt.comm.chart.operation.ChartService;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.xtwh.general.qxgl.GnmkModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_权限管理_用户组管理_service类
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
public class YhzglNewService extends BasicService{

	private Log logger = LogFactory.getLog(CommanAction.class);
	
	YhzglNewDao dao = new YhzglNewDao();
	
	/**
	 * 获得用户组信息结果集表头
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public List<HashMap<String,String>> getYhzxxTop(User user){
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "zdm", "zmc", "yhsxs"	};
		String[] cn = new String[] { "", "组名称", "用户数" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}
	
	/**
	 * 获得用户组信息结果集
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public ArrayList<String[]> getYhzxxList(YhzglNewForm myForm,User user) 
		throws IllegalArgumentException, SecurityException, IllegalAccessException, 
		InvocationTargetException, NoSuchMethodException{
		
		return dao.getYhzxxList(myForm, user);
		
	}
	
	/**
	 * 验证用户组名称是否存在
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean checkZmc(String zmc){
		return dao.checkZmc(zmc);
	}
	
	/**
	 * 增加用户组
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean addYhzxx(YhzglNewForm myForm){		
		return dao.addYhzxx(myForm);		
	}
	
	/**
	 * 修改用户组
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean updateYhzxx(YhzglNewForm myForm){		
		return dao.updateYhzxx(myForm);		
	}
	/**
	 * 复制用户组
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public Boolean copyYhzxx(YhzglNewForm myForm){		
		return dao.copyYhzxx(myForm);		
	}

	/**
	 * 判断选中的用户组是否含用户
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean checkYhz(String[] yhz){
		return dao.checkYhz(yhz);
	}
	
	/**
	 * 删除用户组
	 * 
	 * @date 2013-01-07
	 * @author zhanghui
	 */
	public Boolean deleteYhzxx(YhzglNewForm myForm){		
		return dao.deleteYhzxx(myForm);		
	}
	
	/**
	 * 获取所有的功能模块
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public List<GnmkModel> getAllGnmkList(String userName, String zdm){
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// 一级菜单
		List<HashMap<String, String>> gnmkListOne = dao.getGnmkList(zdm, 1);
		// 二级菜单
		List<HashMap<String, String>> gnmkListTwo = dao.getGnmkList(zdm, 2);
		// 三级菜单
		List<HashMap<String, String>> gnmkListThr = dao.getGnmkList(zdm, 3);
		
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
	public List<HashMap<String, String>> getAllDjGnmkList(String zdm){

		List<HashMap<String, String>> list = dao.getDjGnmkList(zdm);
		return list;
	}
	
	/**
	 * 设置子节点
	 * 
	 * @date 2013-01-10
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
	 * 获得用户组信息
	 * 
	 * @date 2013-01-10
	 * @author zhanghui
	 */
	public HashMap<String,String> getYhzxx(String zdm){
		return dao.getYhzxx(zdm);
	}
	
	/**保存用户组权限
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public Boolean saveYhzqx(String zdm,HttpServletRequest request) throws Exception{
		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		String[] sqls=new String[2+gnmkdm.length];
		//用户组权限插入临时表
		sqls[0]="insert into yhzqxb_lsb select * from yhzqxb b where b.zdm ='"+zdm+"'";
		//删除用户组权限表
		sqls[1]="delete from yhzqxb where zdm='"+zdm.replace("'", "")+"'";
		String dxq=null;
		for(int i=0;i<gnmkdm.length;i++){
			dxq=Base.chgNull(request.getParameter("dxq_"+gnmkdm[i]),"",0);
			sqls[2+i]="insert into yhzqxb values('"+zdm+"','"+gnmkdm[i]+"','"+dxq+"')";
		}
		
		boolean flag = dao.saveArrDate(sqls);
		return flag;
	}


	/**保存用户组权限
	 *
	 * @date 2018-09-19
	 */
	public Boolean yhzGnsqSave(String zdm,JSONArray yhzgnqxArray) throws Exception{

		DAO me = DAO.getInstance();
		boolean result = false;
		logger.info("【insert into yhzqxb_lsb select * from yhzqxb b where b.zdm = '"+zdm+"'】:【start】"+new Date().toString());
		String lsbSql = "insert into yhzqxb_lsb select * from yhzqxb b where b.zdm = ? ";
		result = me.runUpdate(lsbSql,new String[]{zdm});
		logger.info("【insert into yhzqxb_lsb select * from yhzqxb b where b.zdm = '"+zdm+"'】:【end】"+new Date().toString());

		logger.info("【delete from yhzqxb where zdm= '"+zdm+"'】:【start】"+new Date().toString());
		String delSql = "delete from yhzqxb where zdm= ? ";
		result = me.runUpdate(delSql,new String[]{zdm});
		logger.info("【delete from yhzqxb where zdm= '"+zdm+"'】:【end】"+new Date().toString());

		logger.info("【insert into yhzqxb values (?,?,?)】:【start】"+new Date().toString());
		if(result){
			if(yhzgnqxArray.length() > 0){
				String insertSql = "insert into yhzqxb values (?,?,?)";
				List<String []> paramList = new ArrayList<String[]>();
				for(int i=0;i<yhzgnqxArray.length();i++){
					JSONObject jobj = yhzgnqxArray.getJSONObject(i);
					String gnmkdm = jobj.getString("gnmkdm");
					String dxq = jobj.getString("dxq").equals("null") ? null : jobj.getString("dxq");
					paramList.add(new String []{zdm,gnmkdm,dxq});
//					result = me.runUpdate(insertSql,new String[]{zdm,gnmkdm,dxq});
//					if(!result){
//						logger.info("insert into yhzqxb values ('"+zdm+"','"+gnmkdm+"','"+dxq+"') 执行失败！");
//						break;
//					}
				}
				result = me.runBatchBoolean(insertSql,paramList);
			}
		}
		logger.info("【insert into yhzqxb values (?,?,?)】:【end】"+new Date().toString());

		if(result){
			saveYhqx(zdm);
		}

		return result;
	}
	
	/**
	 * 获取用户组权限变更功能模块（增加的，减少的）
	 * 
	 * @date 2013-01-11
	 * @author zhanghui
	 */
	public HashMap<String,List<String>> getChgGnmk(String zdm,HttpServletRequest request) throws SQLException{

		//修改后的用户组权限
		String[] gnmkdm=request.getParameterValues("gnmkdm");
		if(gnmkdm==null){
			gnmkdm=new String[0];
		}
		//当前用户组权限
		String[] current = dao.getYhzqx(zdm); 

		List<String> oldgnmk = dao.arrayToList(current);	
		List<String> newgnmk = dao.arrayToList(gnmkdm);
				
		//当前功能和修改后功能相同值，即不变的功能模块，剩下的为增加的模块和减少的模块
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
	 * 保存用户权限（修改组权限时，同时修改用户权限）
	 * 
	 * @date 2013-03-19
	 * @author zhangjw
	 */
	public Boolean saveYhqx(String zdm){
		CommDAO dao=new CommDAO();


		boolean flag = true;
		try {
			//先查询组下面所有用户
			String[] sqls=new String[3];
			//删除组下用户所有权限
			String sql = "delete from yhqxb a where (a.yhm,a.gnmkdm) in (select b.yhm,a.gnmkdm from yhzqxb_lsb a left join view_yhz_yhxxb b on a.zdm=b.zdm)";
			sqls[0]=sql;
			
			StringBuffer sb = new StringBuffer();
			sb.append("merge into yhqxb t1 ");
			sb.append("using (select b.yhm,a.gnmkdm,a.dxq from yhzqxb a left join view_yhz_yhxxb b on a.zdm=b.zdm where b.zdm ='"+zdm+"') t2 ");
			sb.append("on (t1.yhm=t2.yhm and t1.gnmkdm=t2.gnmkdm) ");
			sb.append("when matched then ");
			sb.append("  update set t1.dxq = t2.dxq ");
			sb.append("when not matched then ");
			sb.append("insert values(t2.yhm,t2.gnmkdm,t2.dxq) ");
			
			//删除用户组临时表记录
			sqls[1]=sb.toString();
			sqls[2]="delete from yhzqxb_lsb";
			logger.info("【批处理保存用户权限】:【start】"+new Date().toString());
			flag = dao.saveArrDate(sqls);
			logger.info("【批处理保存用户权限】:【end】"+new Date().toString());
			if(flag){
				try {
					KsdhService ksdh = new KsdhService();
					ksdh.del_Rubbish_data_yhz(zdm.replace("'", ""));
				} catch (Exception e) {
					logger.error("快速导航表【XG_XTWH_KSDHB】删除出错！");
				}
			}
		} catch (SQLException e1) {
			logger.error("组根据用户组代码查询用户组下面用户异常");
			e1.printStackTrace();
		} catch (Exception e) {
			logger.error("保存用户组权限时联动用户权限更新失败");
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public String getYhzmc(String keyValue) throws Exception{
		return dao.getYhzmc(keyValue)[0];
	}
}
