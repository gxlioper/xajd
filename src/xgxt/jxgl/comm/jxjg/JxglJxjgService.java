package xgxt.jxgl.comm.jxjg;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.jxgl.comm.JxglCommService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.ExcelMethods;



/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_军训结果_Service类
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

public class JxglJxjgService extends JxglCommService {

	JxglJxjgDAO dao = new JxglJxjgDAO();
	
	/**
	 * 获取军训名单列表
	 * @param model
	 * @param topTr
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getJxmdList(JxglJxjgForm model,List<HashMap<String,String>>topTr) throws Exception{
		
		return dao.getJxmdList(model,topTr,"no");
	}
	
	/**
	 * 获取表头
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>>getTopTr(JxglJxjgForm model) throws Exception{
		
		DAO Cdao=DAO.getInstance();
		List<HashMap<String,String>>jxjzList=dao.getJxjz();
		String[]en={"xh","xm","nj","xymc","zymc","bjmc"};
		String[]cn={"学号","姓名","年级",Base.YXPZXY_KEY,"专业","班级"};
		String[]jxjzenArr=new String[jxjzList.size()];
		String[]jxjzcnArr=new String[jxjzList.size()];
		for(int i=1;i<=jxjzList.size();i++){
			HashMap<String,String>jxjzMap=jxjzList.get(i-1);
			jxjzenArr[i-1]="mc"+i;
			jxjzcnArr[i-1]=jxjzMap.get("jzmc");
		}
		
		en=Cdao.unionArray(en, jxjzenArr);
		cn=Cdao.unionArray(cn, jxjzcnArr);
		
		return Cdao.arrayToList(en, cn);
	}
	
	/**
	 * 加载军训建制列表
	 */
	public void getJxjzList(HttpServletRequest request){
		List<HashMap<String,String>>jxjzList=dao.getXxbzList();
		List<HashMap<String,String>>jxjz=dao.getJxjz();
		
		List<HashMap<String,String>>firstList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>secoundList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>thirdList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>fourthList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>fifthList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<jxjzList.size();i++){
			HashMap<String,String>jxjzMap=jxjzList.get(i);
			for(int j=0;j<jxjz.size();j++){
				HashMap<String,String>jzMap=jxjz.get(j);
				if(jzMap.get("jzdm").equalsIgnoreCase(jxjzMap.get("bzdj"))){
					if("1".equalsIgnoreCase(jzMap.get("jzdm"))){
						firstList.add(jxjzMap);
					}else if("2".equalsIgnoreCase(jzMap.get("jzdm"))){
						secoundList.add(jxjzMap);
					}else if("3".equalsIgnoreCase(jzMap.get("jzdm"))){
						thirdList.add(jxjzMap);
					}else if("4".equalsIgnoreCase(jzMap.get("jzdm"))){
						fourthList.add(jxjzMap);
					}else if("5".equalsIgnoreCase(jzMap.get("jzdm"))){
						fifthList.add(jxjzMap);
					}
					
				}
			}
		}
		request.setAttribute("firstList", firstList);
		request.setAttribute("secoundList", secoundList);
		request.setAttribute("thirdList", thirdList);
		request.setAttribute("fourthList", fourthList);
		request.setAttribute("fifthList", fifthList);
	}
	
	/**
	 * 获取学生军训编制信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getXsJxbz(JxglJxjgForm model) throws Exception {
		
		HashMap<String,String>xsjxbzMap=dao.getXsJxbz(model);
		
		List<HashMap<String,String>>jxbzList=dao.getJxjz();
		
		for(int i=1;i<=jxbzList.size();i++){
			HashMap<String,String>jxbzMap=jxbzList.get(i-1);
			xsjxbzMap.put("jzjb"+i, jxbzMap.get("jzmc"));
		}
		return xsjxbzMap;
	}
	
	/**
	 * 执行军训名单初始化存储过程
	 * @param String sql
	 * @return boolean
	 */
	public boolean jxmdIni(User user){
		
		DAO dao=DAO.getInstance();

		boolean blog=true;
		try {
			blog= dao.runProcuder("{call pro_xg_jxgl_jxmdcsh()}",new String[]{});
		} catch (Exception e) {
			
			e.printStackTrace();
			blog=false;
			return blog;
		}
	
		return blog;
	}
	
	
	public void expExcel(JxglJxjgForm model,OutputStream os) throws Exception {
		List<HashMap<String,String>>topTr=getTopTr(model);
		ArrayList<String[]> list=(ArrayList)dao.getJxmdList(model, topTr, "yes");
		this.expToExcel("军训名单", topTr, list, os);

	}
}