package xsgzgl.gygl.gybxgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;
import xsgzgl.gygl.comm.GyglNewService;

public class GybxglService extends GyglNewService{
	
	GybxglDao dao = new GybxglDao();
	/**
	 * 获取退宿信息数据集
	 * @author zhanghui
	 */
	public ArrayList<String[]> getGybxglInfoList(GybxglForm myForm, String[] colList, HttpServletRequest request) 
		throws Exception{		
		return dao.getGybxglInfoList(myForm,colList,request);
	}
	
	public List<String[]> getGybxglSelfList(GybxglForm myForm, String[] colList, User user, String searchTjByGyfdy) 
	throws Exception{		
		return dao.getGybxglSelfList(myForm, colList, user, searchTjByGyfdy);
	}

	/**
	 * 查看学生信息
	 * @author zhanghui
	 */
	public HashMap<String,String> viewXsxx(String xh){
		return dao.viewXsxx(xh);
	}
	
	public HashMap<String,String> viewXsxx(String pk, String xh){
		return dao.viewXsxx(pk,xh);
	}
	public List<HashMap<String, String>> getBxlbList(){
		return dao.getBxlbList();
	}
	
	public boolean gybxglAdd(GybxglForm myForm) throws Exception{
		return dao.gybxglAdd(myForm);		
	}
	
	/**
	 * 公寓保修修改
	 * @author sjf
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean gybxglModi(GybxglForm myForm) throws Exception{
		return dao.gybxglModi(myForm);		
	}
	
	/**
	 * 公寓报修处理
	 * @author sjf
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean gybxglUpdate(GybxglForm myForm) throws Exception{
		return dao.gybxglUpdate(myForm);		
	}
	
	public boolean delGybx(GybxglForm model) throws Exception{
		List<String[]> params = new ArrayList<String[]>();
		String[] pks = model.getPrimarykey_checkVal();
		
		for(String pk : pks){
			params.add(new String[]{pk});
		}
		
		return dao.delGybx(params);
	}
	
	public boolean pjUpdate(GybxglForm model) throws SQLException{
		String[] pks = model.getPrimarykey_checkVal();
		String mycd = model.getMycd();
		String pj = model.getPj();
		
		List<String[]> list = new ArrayList<String[]>();
		
		for(String pk : pks){
			list.add(new String[]{mycd, pj, pk});
		}
		return dao.pjUpdate(list);
	}
	
	
	/**
	 * 公寓报修管理 自定义导出
	 * @param myForm
	 * @param colList
	 * @param request
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getGybxglInfoExportList(GybxglForm myForm, String[] colList, HttpServletRequest request) 
		throws Exception{		
		return dao.getGybxglInfoExportList(myForm,colList,request);
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-5 下午02:01:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bxlb
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBxlbzxList(String bxlb) 
	throws Exception{		
	return dao.getBxlbzxList(bxlb);
} 
	
	/**
	 * 
	 * @描述:获取分配部门list
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-15 上午10:30:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getfpbmList(){
		return  dao.getfpbmList();
	}
	
	/**
	 * 
	 * @描述:分配保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-15 下午01:54:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean gybxglfpUpdate(GybxglForm myForm) throws Exception{		
		return dao.gybxglfpUpdate(myForm);
	}
	
	/**
	 * 查看学生信息以及相关报修信息
	 * @param pk
	 * @return
	 */
	public HashMap<String,String> viewfpXsxx(String pk,String xh){
		return dao.viewfpXsxx(pk, xh);
	}

	/**
	 * @return  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-14 下午02:20:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public  List<HashMap<String, String>> getfpxqList() {
		return dao.getfpxqList();
	}	
}
