package xgxt.qgzx.hngydx.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDataInfo;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.hngydx.dao.HngydxXsqgzxDAO;
import xgxt.utils.ExcelMethods;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块湖南工业学生勤工助学管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-11</p>
 */
public class HngydxXsqgzxService {
	
	/**
	 * 获取范围限定信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getRangeInfo(){
		HashMap<String, String> map = new HashMap<String, String>();
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();
		//申请时间范围
		map.put("sqsjFlag", dao.isRangeOfSqsj()==true ? "0" : "1");
		//当前时间信息
		map = dao.getCurrXnNdXq(map);
		return map;
	}
	
	/**
	 * 获取学生信息
	 * @param xh
	 * @param getStuInfo
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> setStuInfo(String xh,String getStuInfo,HashMap<String, String> map){
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();
		map = dao.addStuInfo(xh,getStuInfo,map);
		return map;
	}
	
	/**
	 * 增加学生岗位信息
	 * @param form 
	 * @param request
	 * @return boolean
	 * */
	public boolean addXsgwInfo(CommanForm form,HttpServletRequest request){
		boolean flag = true;
		GetDataInfo data = new GetDataInfo();
		String[] input = {"xh","gwdm","gwsbsj","lxdh","gzjl","yhtc","xssq","bz","xn","nd"};
		String[] inputValue = null;
		
		String gwPk = DealString.toGBK(form.getGwdm());
		String gwdm = gwPk.split("-")[0];
		String gwsbsj = gwPk.split("-")[1];
		String xh = form.getXh();
		String lxdh = form.getLxdh();
		String gzjl = DealString.toGBK(form.getGzjl());
		String yhtc = DealString.toGBK(form.getYhtc());
		String sqly = DealString.toGBK(form.getSqly());
		String bz = DealString.toGBK(form.getBz());
		String xn = form.getXn();
		String nd = form.getNd();
		
		//保存申请基本信息
		try {
			if(data.IsDataExist(xh+"-"+gwPk).equalsIgnoreCase("1")){
				//已经有岗位存在
				flag = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj", xh+gwdm+gwsbsj, request);
			}
			//保存信息
			if(flag){
				inputValue = new String[]{xh,gwdm,gwsbsj,lxdh,gzjl,yhtc,sqly,bz,xn,nd};
				flag = StandardOperation.insert("xsgwxxb", input, inputValue, request);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		//保存空闲时间
		saveFreeTime sft = new saveFreeTime(request,xh,5,7);
		new Thread(sft).start();
		return flag;
	}	
	
	/**
	 * 按主键查询学生的申请信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStustationInfo(String pkValue){
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuinfo(pkValue);
		return map;
	}
	
	/**
	 * 修改学生申请岗位信息
	 * @param model
	 * @return boolean
	 * */
	public boolean modXsgwInfo(CommanForm model,HttpServletRequest request){
		boolean flag = true;
		String[] columns = {"lxdh","gzjl","yhtc","xssq","bz","xn","nd"};
		String[] values = null;
		String gwdm = DealString.toGBK(model.getGwdm());
		String xh = model.getXh();
		String lxdh = model.getLxdh();
		String gzjl = DealString.toGBK(model.getGzjl());
		String yhtc = DealString.toGBK(model.getYhtc());
		String xssq = DealString.toGBK(model.getSqly());
		String bz = DealString.toGBK(model.getBz());
		String xn = model.getXn();
		String nd = model.getNd();
		
		values = new String[]{lxdh,gzjl,yhtc,xssq,bz,xn,nd};
		
		try {
			//修改学生岗位申请信息
			flag = StandardOperation.update("xsgwxxb", columns, values, "xh||gwdm||'-'||gwsbsj", xh+gwdm, request);
			
			//重新保存空闲时间
			saveFreeTime sft = new saveFreeTime(request,xh,5,7);
			new Thread(sft).start();
			
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 查询学生申请岗位信息
	 * @param pkVal
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuAppInfo(String pkVal){
		HngydxXsqgzxDAO  dao = new HngydxXsqgzxDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuAppInfo(pkVal);
		map.put("sfpks", dao.isKns(map.get("xh"))== true ? "是" : "否");
		return map; 
	}
	
	public void printRoster(WritableWorkbook wwb){
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();		
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat wcfTytle = new WritableCellFormat();		
		String xn = Base.currXn;
		List list = dao.getRoster(xn);
		try {
			wcfTytle.setAlignment(Alignment.CENTRE);
			wcfTytle.setVerticalAlignment(VerticalAlignment.CENTRE);
			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.NONE);
			ws.addCell(new Label(0,0,xn+"年湖南工业大学勤工助学申请花名册",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			for(int i=2; i<list.size()+2; i++){
				for(int j=0;j<13; j++){
					  String[] value = (String[]) list.get(i-2);
					  ws.addCell(new Label(j,i,value[j],wcfTytle));
				 }
			 }
		} catch (RowsExceededException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 保存空闲时间
	 * */
	public static class saveFreeTime implements Runnable {
		HttpServletRequest request = null;
		String sXh = "";
		int intI = 0;
		int intJ = 0;
		
		public saveFreeTime(HttpServletRequest request,String xh,int sjLen, int xqLen ){
		this.request = request;
		sXh = xh;
		intI = sjLen;
		intJ = xqLen;
		}
		public void run() {
			String[][] kxf = new String[intI][intJ];
			boolean flag = false;
			DAO dao = DAO.getInstance();
			for(int i=0; i<intI; i++){
				for(int j=0; j<intJ; j++){
					//获取页面时间表格的每个复选框的值
					kxf[i][j] = request.getParameter(String.valueOf(i)+String.valueOf(j+1))==null?"0":"1";
					}
				}
			if(kxf!=null){
				try {
				//StandardOperation.delete("xsqgzxsjb", "xh", sXh, request);
					flag = dao.runUpdate("delete from xsqgzxsjb where xh=?", new String[]{sXh});
				for(int i=0; i<intI; i++){
					for(int j=0; j<intJ; j++){
//						StandardOperation.insert("xsqgzxsjb", new String[]{"xh","xq","sj","kxbz"}, new String[]{sXh,String.valueOf(i+1),String.valueOf(j+1),kxf[i][j]}, request);
						if(flag){
							dao.insert("insert into xsqgzxsjb(xh,xq,sj,kxbz) values(?,?,?,?)", new String[]{sXh,String.valueOf(i+1),String.valueOf(j+1),kxf[i][j]});
						}
						
					}
				}
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 修改岗位信息备注
	 * @param pkValue
	 * @param bz
	 * @param request
	 * @return boolean
	 * */
	public boolean saveXsgwxxRemark(String pkValue,String bz,HttpServletRequest request) throws Exception{
		boolean flag = false;
		pkValue = DealString.toGBK(pkValue);
		bz = DealString.toGBK(bz);
		flag = StandardOperation.update("xsgwxxb", new String[]{"bz"}, new String[]{bz}, "xh||gwdm||sqsj", pkValue, request);
		return flag;
	}
	
	/**
	 * 获取打印申请表页面数据
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPintInfo(CommanForm model){
		HngydxXsqgzxDAO dao = new HngydxXsqgzxDAO();
		return dao.getPintInfo(model);
	}
}
