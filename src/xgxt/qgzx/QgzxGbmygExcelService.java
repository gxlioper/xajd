package xgxt.qgzx;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学-酬金发放-各部门用工表统计-service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author HongLin
 * @version 1.0
 */
/**
 *
 */
/**
 *
 */
public class QgzxGbmygExcelService extends CommService  {

	/**
	 * 统计导出数据excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printGbmyg(QgzxGbmygExcelActionForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String title = "各部门用工表统计";
		String ffsjks = myForm.getFfsjks();
		String ffsjjs = myForm.getFfsjjs();
		title +="（";
		if(!StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"至"+ffsjjs;
		}
		else if (!StringUtils.isNull(ffsjks) && StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"-"+"至今";
		}
		else if (StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+="截止到"+ffsjjs;
		}else{
			title+="全部";
		}
		title+="）";
		//创建一个execl表格
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();//单元格样式
		WritableSheet ws = wwb.createSheet(title+"("+GetTime.getSystemTime()+")", 0);//表格名称
		//获取部门 
		List<HashMap<String, String>> bmList= getBmmcList(myForm,request);
		
		try {			
			ws.setColumnView(1, 20);//设置单元格宽度(列，宽度)
			ws.setRowView(1, 50);//设置单元格高度(行，高度)
			ws.setColumnView(2, 30);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			//标题
			wcfTytle = ExcelMB.getWritableCellFormat(18,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(1, 2,6, 3);//合并单元格（开始y列，开始x，结束y，结束x），合并B3到G3的单元格，并且合并3、4行
			ws.addCell(new Label(1,2,title,wcfTytle));
			
			//表头
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.addCell(new Label(1,4,"部门",wcfTytle));//B5
			ws.addCell(new Label(2,4,"地点",wcfTytle));//C5
			ws.addCell(new Label(3,4,"负责老师",wcfTytle));//D5
			ws.addCell(new Label(4,4,"联系电话",wcfTytle));//E5
			ws.addCell(new Label(5,4,"固定（人）",wcfTytle));//F5
			ws.addCell(new Label(6,4,"费用",wcfTytle));//G5
			//第一列
			wcfTytle = ExcelMB.getWritableCellFormat(11,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			int zrs = 0;//总人数
			int zje = 0;//总金额
			if(bmList != null && bmList.size()>0){
				//循环部门
				for(int j=0; j<bmList.size();j++){
					HashMap<String,String> bmMap = bmList.get(j);
					String bmmc = bmMap.get("yrdwmc").toString();
					String bmdm = bmMap.get("yrdwdm").toString();
					ws.addCell(new Label(1,j+5,bmmc,wcfTytle));//B6
					ws.addCell(new Label(3,j+5,"",wcfTytle));//D6，第三列
					ws.addCell(new Label(4,j+5,"",wcfTytle));//E6，第四列
					//第二列
					//获取地点
					List<HashMap<String, String>> ddList= getDdmcList(myForm,request,bmdm);
					String dd="";//存放结果集
					int rs =0;//人数结果集
					int je =0;//金额结果集
					if(ddList != null && ddList.size()>0){
						//第三层循环：查询结果集中楼栋、学院对应的房间数
						dd="";
						for(int d=0; d<ddList.size();d++){
							HashMap<String,String> ddMap = ddList.get(d);
							String ddmc = ddMap.get("gwdm").toString();
							dd+=ddmc+"，";
							//第五列
							//获取固定（人）数，开始
							List<HashMap<String, String>> rsList= getRsslList(myForm,request,ddmc);
							rs+=rsList.size();
							zrs+=rsList.size();
							if(rsList != null && rsList.size()>0){
								for(int r=0; r<rsList.size();r++){
									HashMap<String,String> rsMap = rsList.get(r);
									String rsmc = rsMap.get("gwdm").toString();
									//最后一列,获取费用
									List<HashMap<String, String>> fyList= getFyjeList(myForm,request,rsmc,ffsjks,ffsjjs);
									if(fyList != null && fyList.size()>0){
										for(int f=0; f<fyList.size();f++){
											HashMap<String,String> fyMap = fyList.get(f);
											String cjje = fyMap.get("cjje").toString();
											je+=Integer.parseInt(cjje);
											zje+=Integer.parseInt(cjje);
										}
									}else{
										je+=0;
									}
								}
							}else{
								rs+=0;
								je+=0;
							}
							//获取人数 结束
						}
						ws.addCell(new Label(2,j+5,dd,wcfTytle));//C6，地点
						ws.addCell(new Label(5,j+5,rs+"",wcfTytle));//f6，人数
						ws.addCell(new Label(6,j+5,je+"",wcfTytle));//g6，发放 金额
					}else{
						dd="";
						rs=0;
						ws.addCell(new Label(2,j+5,dd,wcfTytle));//C6，地点
						ws.addCell(new Label(5,j+5,rs+"",wcfTytle));//f6，人数
						ws.addCell(new Label(6,j+5,je+"",wcfTytle));//g6，发放金额
						continue;
					}
				}
				wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
				int num = bmList.size()+5;
				ws.addCell(new Label(1,num,"总计",wcfTytle));
				ws.addCell(new Label(2,num,"",wcfTytle));
				ws.addCell(new Label(3,num,"",wcfTytle));
				ws.addCell(new Label(4,num,"",wcfTytle));
				ws.addCell(new Label(5,num,zrs+"",wcfTytle));
				ws.addCell(new Label(6,num,zje+"",wcfTytle));
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	QgzxGbmygExcelDAO dao = new QgzxGbmygExcelDAO();
	/**
	 * 获取部门列表
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBmmcList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request) throws Exception {
		return dao.getBmmcList(myForm,request);
	}
	
	/**
	 * 获取每个部门的地址
	 * @param myForm
	 * @param request
	 * @param sqdw
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDdmcList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String sqdw) throws Exception {
		return dao.getDdmcList(myForm,request,sqdw);
	}
	/**
	 * 获取固定人数
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRsslList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String gwdm) throws Exception {
		return dao.getRsslList(myForm,request,gwdm);
	}
	/**
	 * 获取发放金额
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFyjeList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String gwdm,String ffsjks,String ffsjjs) throws Exception {
		return dao.getFyjeList(myForm,request,gwdm,ffsjks,ffsjjs);
	}
	/**
	 * 
	 * 工资发放表
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void printGzff(QgzxGbmygExcelActionForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String title = "浙江建设职业技术学院勤工助学工资发放表";
		String ffsjks = myForm.getFfsjks();
		String ffsjjs = myForm.getFfsjjs();
		title +="（";
		if(!StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"至"+ffsjjs;
		}
		else if (!StringUtils.isNull(ffsjks) && StringUtils.isNull(ffsjjs)){
			title+=ffsjks+"-"+"至今";
		}
		else if (StringUtils.isNull(ffsjks) && !StringUtils.isNull(ffsjjs)){
			title+="截止到"+ffsjjs;
		}else{
			title+="全部";
		}
		title+="）";
		//创建一个execl表格
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();//单元格样式
		WritableSheet ws = wwb.createSheet("工资发放表"+"("+GetTime.getSystemTime()+")", 0);//表格名称
		//获取部门 
		List<HashMap<String, String>> bmList= getBmmcList(myForm,request);
		
		try {			
//			ws.setColumnView(1, 20);//设置单元格宽度(列，宽度)
			ws.setRowView(0, 700);//设置单元格高度(行，高度)
			ws.setColumnView(0, 10);
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 30);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(7, 10);
			ws.setColumnView(8, 10);
			ws.setColumnView(9, 10);
			ws.setColumnView(10, 20);
			//标题
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 0,10, 0);//合并单元格（开始y列，开始x，结束y，结束x）,第一行合并11个单元格
			ws.addCell(new Label(0,0,title,wcfTytle));
			
			//表头
			wcfTytle = ExcelMB.getWritableCellFormat(12,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.addCell(new Label(0,1,"序号",wcfTytle));
			ws.addCell(new Label(1,1,"部门序号",wcfTytle));
			ws.addCell(new Label(2,1,"部门",wcfTytle));
			ws.addCell(new Label(3,1,"岗位名称",wcfTytle));
			ws.addCell(new Label(4,1,"班级",wcfTytle));
			ws.addCell(new Label(5,1,"姓名",wcfTytle));
			ws.addCell(new Label(6,1,"学号",wcfTytle));
			ws.addCell(new Label(7,1,"小时",wcfTytle));
			ws.addCell(new Label(8,1,"金额",wcfTytle));
			ws.addCell(new Label(9,1,"岗位",wcfTytle));
			ws.addCell(new Label(10,1,"备注",wcfTytle));
			//第一列
			wcfTytle = ExcelMB.getWritableCellFormat(11,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			int num=0;//单元格行坐标
			int xlh =1;//序列号
			int hj =0;//合计
			if(bmList != null && bmList.size()>0){
				//循环部门
				for(int j=0; j<bmList.size();j++){
					HashMap<String,String> bmMap = bmList.get(j);
					String bmmc = bmMap.get("yrdwmc").toString();
					String bmdm = bmMap.get("yrdwdm").toString();
					//ws.addCell(new Label(2,j+2,bmmc,wcfTytle));//部门
					//遍历每个部门的工资发放信息
					List<HashMap<String, String>> gzffList= getgzffList(myForm,request,bmmc,ffsjks,ffsjjs);
					if(gzffList != null && gzffList.size()>0){
						for(int g=0; g<gzffList.size();g++){
							HashMap<String,String> gzffMap = gzffList.get(g);
							String deptnum = gzffMap.get("deptnum").toString();//部门序号
							String yrdwmc = gzffMap.get("yrdwmc").toString();//部门名称
							String gwdm = gzffMap.get("gwdm").toString();//岗位名称
							String bjmc = gzffMap.get("bjmc").toString();//班级名称
							String xm = gzffMap.get("xm").toString();//姓名
							String xh = gzffMap.get("xh").toString();//学号
							String gzsj = gzffMap.get("gzsj").toString();//小时
							String cjje = gzffMap.get("cjje").toString();//金额
							String gwxzmc = gzffMap.get("gwxzmc").toString();//岗位
							String bz;//备注
							if(gzffMap.get("bz") !=null && gzffMap.get("bz").length()>0){
								bz = gzffMap.get("bz").toString();
							}else{
								bz = "";
							}
							//单元格数据
							
							hj=hj+Integer.parseInt(cjje);
							ws.addCell(new Label(0,num+2,xlh+"",wcfTytle));//序号
							ws.addCell(new Label(1,num+2,deptnum,wcfTytle));//部门序号
							ws.addCell(new Label(2,num+2,yrdwmc,wcfTytle));//部门
							ws.addCell(new Label(3,num+2,gwdm,wcfTytle));//岗位名称
							ws.addCell(new Label(4,num+2,bjmc,wcfTytle));//班级名称
							ws.addCell(new Label(5,num+2,xm,wcfTytle));//姓名
							ws.addCell(new Label(6,num+2,xh,wcfTytle));//学号
							ws.addCell(new Label(7,num+2,gzsj,wcfTytle));//小时
							ws.addCell(new Label(8,num+2,cjje,wcfTytle));//金额
							ws.addCell(new Label(9,num+2,gwxzmc,wcfTytle));//岗位
							ws.addCell(new Label(10,num+2,bz,wcfTytle));//备注
							num=num+1;//单元格行坐标
							xlh=xlh+1;//序列号
						}
					}
					
				}
			}
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			//合计
			ws.addCell(new Label(6,num+2,"合计",wcfTytle));
			ws.addCell(new Label(8,num+2,hj+"",wcfTytle));
			ws.addCell(new Label(0,num+2,"",wcfTytle));
			ws.addCell(new Label(1,num+2,"",wcfTytle));
			ws.addCell(new Label(2,num+2,"",wcfTytle));
			ws.addCell(new Label(3,num+2,"",wcfTytle));
			ws.addCell(new Label(4,num+2,"",wcfTytle));
			ws.addCell(new Label(5,num+2,"",wcfTytle));
			ws.addCell(new Label(7,num+2,"",wcfTytle));
			ws.addCell(new Label(9,num+2,"",wcfTytle));
			ws.addCell(new Label(10,num+2,"",wcfTytle));
			//大写
			MoneyFormat mf = new MoneyFormat();//大写转换工具类
			String nr;
			if(hj==0){
				nr = "大写人民币:"+"零元整"+"                                                        ￥"+hj;
			}else{
				String dxje = mf.format(hj+"");
				nr = "大写人民币:"+dxje
							+"                                                        ￥"+hj;
			}
			ws.mergeCells(0, num+2+1,10, num+2+1);//合并单元格
			ws.addCell(new Label(0,num+2+1,nr,wcfTytle));
			ws.setRowView(num+2+1, 800);//大写行高
			//签字
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.LEFT, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, num+2+2,10, num+2+2);//合并单元格
			ws.addCell(new Label(0,num+2+2,"院领导签字：                                                        学生处领导签字：                                                        办事中心领导签字：                                                        制表：                            ",wcfTytle));
			ws.setRowView(num+2+2, 1600);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 工资发放表数据
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @param ffsjks
	 * @param ffsjjs
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getgzffList(QgzxGbmygExcelActionForm myForm,HttpServletRequest request,String gwdm,String ffsjks,String ffsjjs) throws Exception {
		return dao.getgzffList(myForm,request,gwdm,ffsjks,ffsjjs);
	}
	
	
	// ----------------获取临时岗位酬金发放信息 begin---------------------
	/**
	 * 导出数据excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printLsgwcjInfo(QgzxGbmygExcelActionForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		QgzxGbmygExcelDAO dao=new QgzxGbmygExcelDAO();
		
//		 -------------统计学生酬金发放信息BY临时岗位（当前学年、学期信息）--------------
		List<String[]> lsgwInfo=dao.getXscjInfoByLsgw(myForm);
		
		// -------------获取酬金发放总人数、金额、工时（当前学年、学期）------------------
		HashMap<String,String>zrsJeGsInfo=dao.getZrsJeGs(myForm);
		//获取学期名称
		String xqmc=dao.getXqmc(Base.currXq);
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();
		WritableSheet ws = wwb.createSheet(Base.currXn+"第"+Base.currXq+"学期勤工助学临时岗位统计表", 0);
		
		
		try {			
			
			//标题
			wcfTytle = ExcelMB.getWritableCellFormat(16,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 0,6, 0);
			ws.addCell(new Label(0,0,Base.currXn+"第"+xqmc+"学期勤工助学临时岗位统计表",wcfTytle));
			
			wcfTytle = ExcelMB.getWritableCellFormat(11,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.addCell(new Label(0,1,"部门",wcfTytle));
			ws.addCell(new Label(1,1,"岗位名称",wcfTytle));
			ws.addCell(new Label(2,1,"班级",wcfTytle));
			ws.addCell(new Label(3,1,"姓名",wcfTytle));
			ws.addCell(new Label(4,1,"学号",wcfTytle));
			ws.addCell(new Label(5,1,"工作总时间",wcfTytle));
			ws.addCell(new Label(6,1,"酬金",wcfTytle));
			
			int[] hbwz={0,1};
			
			// --------------------需要合并行数据 begin--------------------------
			this.hbdyg(lsgwInfo,hbwz, 3, ws);
			// --------------------需要合并行数据 end--------------------------
			
			for(int i = 0;i<lsgwInfo.size();i++){
				
				String[]lsgwArr=lsgwInfo.get(i);
				
				for(int j=2;j<lsgwArr.length;j++){
					
					ws.addCell(new Label(j-2,2+i,lsgwArr[j],wcfTytle));
					
				}
			}
			
			ws.mergeCells(0, 2+lsgwInfo.size(),4, 2+lsgwInfo.size());
			ws.addCell(new Label(0,2+lsgwInfo.size(),"人数总计共"+zrsJeGsInfo.get("zrs")+"人",wcfTytle));
			
			ws.addCell(new Label(5,2+lsgwInfo.size(),"时间总计："+zrsJeGsInfo.get("zgs"),wcfTytle));
			
			ws.addCell(new Label(6,2+lsgwInfo.size(),"酬金总计："+zrsJeGsInfo.get("zje"),wcfTytle));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
//	 ----------------获取临时岗位酬金发放信息 end---------------------
}
