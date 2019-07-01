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
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ���_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class JxglJxjgService extends JxglCommService {

	JxglJxjgDAO dao = new JxglJxjgDAO();
	
	/**
	 * ��ȡ��ѵ�����б�
	 * @param model
	 * @param topTr
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getJxmdList(JxglJxjgForm model,List<HashMap<String,String>>topTr) throws Exception{
		
		return dao.getJxmdList(model,topTr,"no");
	}
	
	/**
	 * ��ȡ��ͷ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>>getTopTr(JxglJxjgForm model) throws Exception{
		
		DAO Cdao=DAO.getInstance();
		List<HashMap<String,String>>jxjzList=dao.getJxjz();
		String[]en={"xh","xm","nj","xymc","zymc","bjmc"};
		String[]cn={"ѧ��","����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶"};
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
	 * ���ؾ�ѵ�����б�
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
	 * ��ȡѧ����ѵ������Ϣ
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
	 * ִ�о�ѵ������ʼ���洢����
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
		this.expToExcel("��ѵ����", topTr, list, os);

	}
}