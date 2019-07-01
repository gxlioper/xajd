package xgxt.pjpy.comm.zhcp.pdbx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_品德表现_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpPdbxService extends PjpyCommService {
	
	ZhcpPdbxDAO dao = new ZhcpPdbxDAO();
	
	/**
	 * 获取评分人A所需要评价的所有学生
	 * @param model
	 * @param user
	 * @param colList
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getXspjPdbx(ZhcpPdbxForm model,User user) throws Exception{
		//获取显示字段的EN
		String[]colList=severTop(getTop(model,user),"en");
		
		return dao.getXspjPdbx(model, user, colList);
	}
	
	/**
	 * 获取表头字段
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTop(ZhcpPdbxForm model, User user) {
		
		DAO dao=DAO.getInstance();
		ZhcpPdbxDAO pdbxDAO=new ZhcpPdbxDAO();
		//获取配置表中配置的字段、字段名称
		List<HashMap<String,String>>colList=pdbxDAO.getTop(model, user);
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		
//		colArr.add("xn");
//		topArr.add("学年");
//		
//		colArr.add("xqmc");
//		topArr.add("学期");
//		
//		colArr.add("nd");
//		topArr.add("年度");
	
		colArr.add("pkValue");
		topArr.add("主键");
		
		colArr.add("disabled");
		topArr.add("禁用");
		
		colArr.add("bpfr");
		topArr.add("学号");
		
		colArr.add("bpfrxm");
		topArr.add("姓名");
		
		colArr.add("zf");
		topArr.add("总分");
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		return dao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * 分离top列表 根据获取类型获取（en,cn）
	 * 
	 * @param topList
	 * @param hqlx
	 * @return List<HashMap<String, String>>
	 */
	public String[] severTop(List<HashMap<String, String>> topList, String hqlx) {

		List<String> outList = new ArrayList<String>();
		for (int i = 0; i < topList.size(); i++) {
			HashMap<String, String> topMap = topList.get(i);
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}
	
	/**
	 * 修改品德表现分
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updatePdbxf(ZhcpPdbxForm model, User user) throws Exception {

		return dao.updatePdbxf(model,user,"xiugai");
	}
	
	/**
	 * 修改学生提交状态
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateXstjzt(ZhcpPdbxForm model, User user) throws Exception {
		dao.updatePdbxf(model,user,"tijiao");
		return dao.updateXstjzt(model, user);
	}
	
	/**
	 * 修改学生提交状态
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getLsckpf(ZhcpPdbxForm model, User user) throws Exception {
		
		return dao.getLsckpf(model, user);
	}
	
	public List<HashMap<String,String>>getLsckTop(ZhcpPdbxForm model, User user){
		
		DAO dao=DAO.getInstance();
		
		String[]en={"disabled","xh","xm","nj","xymc","zymc","bjmc","sfpf","sfqr","qrr"};
		String[]cn={"disabled","学号","姓名","年级",Base.YXPZXY_KEY+"名称","专业名称","班级名称","是否评分","是否确认","确认人"};
		return dao.arrayToList(en, cn);
		
	}
	
	
	/**
	 * 获取评分人A所需要评价的所有学生(老师查看)
	 * @param model
	 * @param user
	 * @param colList
	 * @return  List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getLcckPdbx(ZhcpPdbxForm model,User user) throws Exception{
		
		String[]colList=severTop(getTop(model,user),"en");
		
		return dao.getLcckPdbx(model, user, colList);
	}
	
	/**
	 * 单个评价学生评价结果确认
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateQrzt(ZhcpPdbxForm model,User user) throws Exception{
		
		return dao.updateQrzt(model, user);
	}
	
	/**
	 * 获取可否评分（ false：不可评，true：可以）
	 * @param model
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public boolean getDypfsz (ZhcpPdbxForm model, User user) throws Exception {
		
		String kfpf = dao.getDypfsz(model, user);
		
		if ("no".equalsIgnoreCase(kfpf)) {
			
			return false;
		
		} else {

			return true;
		}
	}
	
	/**
	 * 批量评价学生评价结果确认(取消确认)
	 * 根据前台传值
	 * @param model
	 * @param user
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateZt(ZhcpPdbxForm model,User user) throws Exception{
		
		return dao.updateZt(model, user);
	}
	
	/**
	 * 评价人信息
	 * @param model
	 * @param user
	 * @return
	 */
	public HashMap<String,String>getPjrxx(ZhcpPdbxForm model,User user){
		
		
		return dao.getPjrxx(model, user);
	} 
	
	/**
	 * 获取指定综测周期项目信息
	 * @param model
	 * @param user
	 * @return  HashMap<String,String>
	 * @throws Exception 
	 */
	public boolean pdfjs(ZhcpPdbxForm model,User user) throws Exception {
	
		return dao.pdfjs(model,user);
	}
	
	/**
	 * 获取辅导员所带班级评分情况
	 * @param model
	 * @param user
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>pdbxfTj(ZhcpPdbxForm model,User user){
		return dao.pdbxfTj(model, user);
	}
	
	/**
	 * 获取品德表现分设置信息
	 * @param model
	 * @param user
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getPdbxSz(ZhcpPdbxForm model,User user){
		
		
		return dao.getPdbxSz(model, user);
	}

}