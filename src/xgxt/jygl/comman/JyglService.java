package xgxt.jygl.comman;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.bdsz.BdszDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicService;
import com.zfsoft.database.model.TableModel;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 就业管理Service</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 屈朋辉</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-13</p>
 */
public class JyglService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	JyglDAO dao = new JyglDAO();
	
	
	/**
	 * 就业协议表学生信息
	 * @param xh
	 * @return
	 */
	protected HashMap<String,String> getJyxyStuInfo(String xh) {
		return dao.getJyxyStuInfo(xh);
	}
	
	
	/**
	 * 学生基本信息
	 * @param xh
	 * @return
	 */
	protected HashMap<String, String> getStuInfo(String xh) {
		return CommonQueryDAO.getStuInfo(xh);
	}
	
	
	/**
	 * 查询某学生所在班的辅导员
	 * @param xh
	 * @return
	 * @throws SQLException 
	 */
	protected String getFdy(String xh) throws SQLException {
		
		String[] temp = dao.getFdy(xh);
		StringBuffer sb = new StringBuffer();
		
		if (null != temp && temp.length>0) {
			
			for ( int i = 0 ; i < temp.length ; i ++ ) {
				sb.append(temp[i]);
				sb.append(",");
			}
			
			sb.deleteCharAt(sb.length()-1);
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 下拉列表
	 * @param request
	 * @param flg
	 */
	protected void setList(HttpServletRequest request,String flg) {
		
		DAO da = DAO.getInstance();
		
		
		if ("tjb".equalsIgnoreCase(flg)) {
			//补办原因
			List<HashMap<String, String>> yyList = dao.getSelectList("bbyy");
			request.setAttribute("yyList", yyList);
			
			//所需材料
			List<HashMap<String, String>> clList = dao.getSelectList("sxcl");
			request.setAttribute("clList", clList);
			
		}  else if ("jysb".equals(flg)) {
			//民族
			List<HashMap<String, String>> mzList = da.getWhList("mzdmb", "mzdm", "mzmc", "", "", "");
			request.setAttribute("mzList", mzList);
			
			//政治面貌
			List<HashMap<String, String>> zzmmList = da.getWhList("dmk_zzmm", "zzmmdm", "zzmm", "", "", "");
			request.setAttribute("zzmmList", zzmmList);
			
			//学校
			List<HashMap<String, String>> xxList = da.getWhList("dmk_xx", "xxdm", "xxmc", "", "", "");
			request.setAttribute("xxList", xxList);
			
			//专业对照
			List<HashMap<String, String>> gbzyList = da.getWhList("dmk_zydmdzb", "jygl_zydm", "jygl_zymc", "", "", "");
			request.setAttribute("gbzyList",gbzyList);
			
			//学历
			List<HashMap<String, String>> xlList = da.getWhList("dmk_xl", "xldm", "xl", "", "", "");
			request.setAttribute("xlList",xlList);
			
			//培养方式
			List<HashMap<String, String>> pyfsList = da.getWhList("dmk_bzpyfs", "pyfsdm", "pyfs", "", "", "");
			request.setAttribute("pyfsList",pyfsList);
			
			//生源地区（省）
			List<HashMap<String, String>> sydqList = da.getWhList("dmk_sydq", "sydqdm", "sydq", "", "", "");
			request.setAttribute("sydqList",sydqList);
			
			//招生类别
			List<HashMap<String, String>> zsList = da.getWhList("dmk_zslb", "dm","mc", "", "", "");
			request.setAttribute("zsList", zsList);
			
			
		} else if ("jyxy".equals(flg)) {
			//单位性质
			List<HashMap<String, String>> dwxzList = da.getWhList("dmk_dwxz", "dwxzdm", "dwxz", "", "", "");
			request.setAttribute("dwxzList",dwxzList);
			
			//行业
			List<HashMap<String, String>> hyList = da.getWhList("dmk_hyfl", "hyfldm", "hyfl", "", "", "");
			request.setAttribute("hyList",hyList);
			
			//下基层情况
			List<HashMap<String, String>> xjcList = da.getWhList("dmk_xjcqk", "dm", "mc", "", "", "");
			request.setAttribute("xjcList", xjcList);
			
			//生源地区
			List<HashMap<String, String>> lsList = da.getWhList("dmk_sydq", "sydqdm", "sydq", "", "", "");
			request.setAttribute("lsList", lsList);
			
			//就业标志
			List<HashMap<String, String>> jybzList = da.getWhList("dmk_jybz", "dm", "mc", "", "", "");
			request.setAttribute("jybzList", jybzList);
			
			//用人单位
			List<HashMap<String, String>> yrdwList = da.getWhList("dmk_yrdw", "dm", "mc", "", "", "");
			request.setAttribute("yrdwList", yrdwList);
			
			//隶属部门
			//List<HashMap<String, String>> lsbmList = da.getWhList("dmk_zgbm", "zgbmdm", "zgbm", "", "", "");
			List<HashMap<String, String>> lsbmList = dao.getLsbmOption();
			request.setAttribute("lsbmList", lsbmList);
			
			//推荐类型
			List<HashMap<String, String>> tjlxList = da.getWhList("dmk_tjlx", "dm", "mc", "", "", "");
			request.setAttribute("tjlxList", tjlxList);
			
			//单位所在地
			List<HashMap<String, String>> dwdzList = da.getWhList("dmk_dwdq", "dwdqdm", "dwdq", "", "", "");
			request.setAttribute("dwdzList", dwdzList);
			
			request.setAttribute("nfList", getNfList());
			
			//主管单位
			//List<HashMap<String, String>> zgdwList = da.getWhList("dmk_zgdw", "dm", "mc", "", "", "");
			List<HashMap<String, String>> zgdwList = dao.getZgdwOption();
			request.setAttribute("zgdwList", zgdwList);
		} else if ("zgdd".equals(flg)) {
			//学历
			List<HashMap<String, String>> xlList = da.getWhList("dmk_zgdd_xlb", "xldm", "xl", "", "", "");
			request.setAttribute("xlList",xlList);
			
			//培养方式
			List<HashMap<String, String>> pyfsList = da.getWhList("dmk_zgdd_pyfs", "pyfsdm", "pyfs", "", "", "");
			request.setAttribute("pyfsList",pyfsList);
			
			//外语语种
			List<HashMap<String, String>> yzList = da.getWhList("dmk_zgdd_zxwy", "yzdm", "yz", "", "", "");
			request.setAttribute("yzList",yzList);
			
			//生源地区（省）
			List<HashMap<String, String>> sydqList = da.getWhList("dmk_sydq", "sydqdm", "sydq", "", "", "");
			request.setAttribute("sydqList",sydqList);
			
			//民族
			List<HashMap<String, String>> mzList = da.getWhList("mzdmb", "mzdm", "mzmc", "", "", "");
			request.setAttribute("mzList", mzList);
			
			//政治面貌
			List<HashMap<String, String>> zzmmList = da.getWhList("dmk_zzmm", "zzmmdm", "zzmm", "", "", "");
			request.setAttribute("zzmmList", zzmmList);
			
			//学校
			List<HashMap<String, String>> xxList = da.getWhList("dmk_xx", "xxdm", "xxmc", "", "", "");
			request.setAttribute("xxList", xxList);
		} else if ("zgddJyxy".equals(flg)) {
			//毕业去向
			List<HashMap<String, String>> byqxList = da.getWhList("dmk_zgdd_byqx", "byqxdm", "byqx", "", "", "");
			request.setAttribute("byqxList", byqxList);
			
			//就业情况
			List<HashMap<String, String>> jyqkList = da.getWhList("dmk_zgdd_jybz", "dm", "mc", "", "", "");
			request.setAttribute("jyqkList", jyqkList);
			
			//用人单位性质
			List<HashMap<String, String>> dwxzList = da.getWhList("dmk_dwxz", "dwxzdm", "dwxz", "", "", "");
			request.setAttribute("dwxzList", dwxzList);
			
			//单位隶属部门
			List<HashMap<String, String>> lsbmList = da.getWhList("dmk_zgdd_zgbm", "zgbmdm", "zgbm", "", "", "");
			request.setAttribute("lsbmList", lsbmList);
			
			//单位所在地
			List<HashMap<String, String>> dwszdList = da.getWhList("dmk_dwdq", "dwdqdm", "dwdq", "", "", "");
			request.setAttribute("dwszdList", dwszdList);
			
			//用人单位
			List<HashMap<String, String>> yrdwList = da.getWhList("dmk_zgdd_yrdw", "dm", "mc", "", "", "");
			request.setAttribute("yrdwList", yrdwList);
			
			//行业
			List<HashMap<String, String>> hyList = da.getWhList("dmk_hyfl", "hyfldm", "hyfl", "", "", "");
			request.setAttribute("hyList",hyList);
		}  else if ("xysgl".equals(flg)) {
			
			//协议书补办类别
			List<HashMap<String, String>> bblbList = da.getWhList("dmk_xysbblb", "dm", "mc", "", "", "");
			request.setAttribute("bblbList",bblbList);
		} else if ("gp".equals(flg)) {
			
			//用人单位
			List<HashMap<String, String>> yrdwList = da.getWhList("dmk_yrdw", "dm", "mc", "", "", "");
			request.setAttribute("yrdwList", yrdwList);
			
			//单位所在地
			List<HashMap<String, String>> dwdzList = da.getWhList("dmk_dwdq", "dwdqdm", "dwdq", "", "", "");
			request.setAttribute("dwdzList", dwdzList);
		} else if ("fc".equals(flg)) {
			
			//风采类型
			List<HashMap<String, String>> fclxList = da.getWhList("dmk_fclx", "dm", "mc", "", "", "");
			request.setAttribute("fclxList", fclxList);
		} else if("yxbys".equals(flg)) {
			List<HashMap<String, String>> sqlbList = dao.getSelectList("yxbys");
			request.setAttribute("sqlbList", sqlbList);
		} else if("xb".equals(flg)){
			List<HashMap<String, String>> xbList = dao.getSelectList("xb");
			request.setAttribute("xbList", xbList);
		} else if ("jytj".equals(flg)) {
			//就业统计
			
			List<HashMap<String, String>> tjxmList = da.getWhList("jygl_jyxgtj", "tjdm", "tjmc", "", "xxdm", Base.xxdm);
			request.setAttribute("tjxmList", tjxmList);
		} else if ("jygzzph".equals(flg)) {
			//企业分类
			List<HashMap<String, String>> qyflList = da.getWhList("dmk_qyflb", "dm", "mc", "", "", "");
			request.setAttribute("qyflList", qyflList);
			//招聘会类型
			List<HashMap<String, String>> zphlxList = da.getWhList("dmk_zphlx", "dm", "mc", "", "", "");
			request.setAttribute("zphlxList", zphlxList);
		} else if ("jygzhy".equals(flg)) {
			//会议类型
			List<HashMap<String, String>> hylxList = da.getWhList("dmk_hylxb", "dm", "mc", "", "", "");
			request.setAttribute("hylxList", hylxList);
		} else if ("jyssz".equals(flg)) {
			//角色分类
			List<HashMap<String, String>> jsflList = da.getWhList("dmk_jsfldm", "dm", "mc", "", "", "");
			request.setAttribute("jsflList", jsflList);
			//推荐结果
			List<HashMap<String, String>> tjjgList = da.getWhList("dmk_tjjgdmb", "dm", "mc", "", "", "");
			request.setAttribute("tjjgList", tjjgList);
		} else if ("jyxys".equals(flg)) {
			//协议书领取情况
			List<HashMap<String, String>> lqqkList = dao.getSelectList("jyxys");
			request.setAttribute("lqqkList", lqqkList);
		} else if ("xysbl".equals(flg)) {
			//补办类别
			List<HashMap<String, String>> bblbList = da.getWhList("dmk_bblb", "dm", "mc", "", "", "");
			request.setAttribute("bblbList", bblbList);
		} 
		
		//是否
		List<HashMap<String, String>> isNot = dao.getSelectList("isNot");
		request.setAttribute("isNot", isNot);
		
		//	审核状态
		List<HashMap<String, String>> shztList = dao.getSelectList("shzt");
		request.setAttribute("shztList", shztList);
		
		request.setAttribute("now", getNow());
		
		request.setAttribute("nfList", getNfList());
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
	}
	
	
	/**
	 * 当前时间
	 * @return
	 */
	protected String getNow() {
		
		return dao.getNow();
	}
	
	
	/**
	 * 毕业生批量上报
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	protected boolean byssb(String[] xh,String sbr,String sbnd) throws SQLException {
		
		return dao.byssb(xh,sbr,sbnd);
	}
	
	
	/**
	 * 生源地分布情况统计
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void printPayReport(OutputStream os,String nj) throws SQLException{		
		
		List<HashMap<String,String>> sydshi = new StuInfoDAO().getShiList("330000").get("shiList");
		HashMap<String, String[]> rs = dao.getBysrs(nj);
		List<String[]> xyzy = dao.getXyZy(nj);
		List<String[] > bysrs = dao.getBysByZy(nj); 
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("生源地分布情况表", 0);
		try{
			ExcelMB excel = new ExcelMB();
			excel.printTitle(ws, Base.xxmc+nj+"届毕业生生源地分布情况表", 4+sydshi.size(), 800);// 标题
			
			 WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE,
						Border.ALL);// 单元格样式
			 
			 //跨int列 、第int列起、跨int 行、第int 行起
			 ws.mergeCells(0, 0,3+sydshi.size(), 0);
			 
			 ws.addCell(new Label(0,2,Base.YXPZXY_KEY+"名称",wcfTytle));
			 ws.addCell(new Label(1,2,"专业名称",wcfTytle));
			 ws.addCell(new Label(2,2,"毕业生数",wcfTytle));
			 ws.addCell(new Label(3,2,"浙江生源",wcfTytle));
			 
			 for ( int i = 1 ; i < sydshi.size() ; i ++ ) {
				 ws.addCell(new Label(3+i,2,sydshi.get(i).get("shimc").substring(0, 2),wcfTytle));
			 }
			 ws.addCell((new Label(3+sydshi.size(),2,"外省生源",wcfTytle)));
			 
			 ws.mergeCells(0,3,1,3);//int 列起，int 行起，跨int 列，int 行结束
			 ws.addCell(new Label(0,3,"全校合计",wcfTytle));
			 ws.addCell(new Label(2,3,rs.get("zrs")[0],wcfTytle));
			 ws.addCell(new Label(3,3,rs.get("zjsrs")[0],wcfTytle));
			 
			 String[] shibys = rs.get("bysrs");
			 for ( int i = 0 ; i < shibys.length ; i ++ ) {
				 ws.addCell(new Label(4+i,3,shibys[i],wcfTytle));
			 }
			 ws.addCell(new Label(4+shibys.length,3,rs.get("wsrs")[0],wcfTytle));
			 
			 for( int i = 0 ; i < xyzy.size() ; i ++) {
				 ws.addCell(new Label(0,4+i,xyzy.get(i)[0],wcfTytle));
				 ws.addCell(new Label(1,4+i,xyzy.get(i)[1],wcfTytle));
			 }
			 ExcelMB.mergeCellsData(xyzy, ws, 0, 4, wcfTytle);
			 
			 for ( int i = 0; i<bysrs.size() && i < xyzy.size(); i ++) {
				 ws.addCell(new Label(2,4+i,bysrs.get(i)[0],wcfTytle));
				 ws.addCell(new Label(3,4+i,bysrs.get(i)[1],wcfTytle));
				 int wsrs = Integer.valueOf(bysrs.get(i)[0])-Integer.valueOf(bysrs.get(i)[1]);
				 ws.addCell(new Label(3+sydshi.size(),4+i,wsrs+"",wcfTytle));
			 }
	
			 for ( int i = 0 ; i < xyzy.size() ; i ++) {
				 String zydm = xyzy.get(i)[2];
				 for ( int j = 1 ; j < sydshi.size() ; j ++ ) {
					 ws.addCell(new Label(3+j,4+i,dao.getBys(zydm, sydshi.get(j).get("shidm"), nj),wcfTytle));
				 }
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		 ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 按班级就业统计
	 * 
	 * @author honglin
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void printBjjytj(OutputStream os,String[] nj) throws SQLException{		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String rq=sdf.format(date);
		
		List<HashMap<String,String>> sydshi = new StuInfoDAO().getShiList("330000").get("shiList");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("班级就业统计表", 0);
		String njStr="";
		for (int i = 0 ; i < nj.length ; i++){
			njStr+=nj[i];
			if (i != nj.length-1){
				njStr=njStr+"、";
			}
		}
		String title =Base.xxmc+njStr+"届毕业生就业进展情况统计表（统计日期"+rq+"）";
		try{
			ExcelMB excel = new ExcelMB();
			//excel.printTitle(ws, Base.xxmc+nj+"届毕业生就业进展情况统计表（统计日期2012年3月12日）", 17,800 );// 标题
			
			/////////////表格标题开始/////////////
			 WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(12,
						true, Alignment.CENTRE, VerticalAlignment.CENTRE,
						Border.ALL);// 单元格样式
			 ws.mergeCells(0,0,16,0);//合并17列
			 ws.addCell(new Label(0,0,title,wcfTytle));
			 ws.setRowView(0, 700);
			 //跨int列 、第int列起、跨int 行、第int 行起
			 ws.mergeCells(0, 0,3+sydshi.size(), 0);
			 ws.mergeCells(0,1,0,2);//合并两行
			 ws.addCell(new Label(0,1,"序号",wcfTytle));
			 ws.mergeCells(1,1,1,2);//合并两行,Base.YXPZXY_KEY
			 ws.addCell(new Label(1,1,"系别",wcfTytle));
			 ws.mergeCells(2,1,2,2);//合并两行
			 ws.addCell(new Label(2,1,"班级",wcfTytle));
			 ws.mergeCells(3,1,3,2);//合并两行
			 ws.addCell(new Label(3,1,"学历",wcfTytle));
			 ws.mergeCells(4,1,6,1);//合并三个单元格
			 ws.addCell(new Label(4,1,"人数",wcfTytle));
			 ws.addCell(new Label(4,2,"总",wcfTytle));
			 ws.addCell(new Label(5,2,"男",wcfTytle));
			 ws.addCell(new Label(6,2,"女",wcfTytle));
			 ws.mergeCells(7,1,7,2);//合并两行
			 ws.addCell(new Label(7,1,"领取协议",wcfTytle));
			 ws.mergeCells(8,1,8,2);//合并两行
			 ws.addCell(new Label(8,1,"就业意向",wcfTytle));
			 ws.mergeCells(9,1,9,2);//合并两行
			 ws.addCell(new Label(9,1,"签约",wcfTytle));
			 ws.mergeCells(10,1,10,2);//合并两行
			 ws.addCell(new Label(10,1,"出国",wcfTytle));
			 ws.mergeCells(11,1,11,2);//合并两行
			 ws.addCell(new Label(11,1,"深造",wcfTytle));
			 ws.mergeCells(12,1,12,2);//合并两行
			 ws.addCell(new Label(12,1,"签约率",wcfTytle));
			 ws.mergeCells(13,1,13,2);//合并两行
			 ws.addCell(new Label(13,1,"就业意向率",wcfTytle));
			 ws.mergeCells(14,1,14,2);//合并两行
			 ws.addCell(new Label(14,1,"班主任",wcfTytle));
			 ws.mergeCells(15,1,15,2);//合并两行
			 ws.addCell(new Label(15,1,"特困",wcfTytle));
			 ws.mergeCells(16,1,16,2);//合并两行
			 ws.addCell(new Label(16,1,"外省",wcfTytle));
			 ws.setColumnView(0, 4);
			 ws.setColumnView(1, 4);
			 ws.setColumnView(2, 15);
			 ws.setColumnView(3, 4);
			 ws.setColumnView(4, 4);
			 ws.setColumnView(5, 4);
			 ws.setColumnView(6, 4);
			 ws.setColumnView(7, 6);
			 ws.setColumnView(8, 6);
			 ws.setColumnView(9, 4);
			 ws.setColumnView(10, 4);
			 ws.setColumnView(11, 4);
			 ws.setColumnView(12, 9);
			 ws.setColumnView(13, 9);
			 ws.setColumnView(14, 9);
			 ws.setColumnView(15, 4);
			 ws.setColumnView(16, 4);
			 /////////////表格标题结束/////////////
			 
			 /////////////表格内容开始/////////////
			 
			 WritableCellFormat wcfTytle2 = ExcelMB.getWritableCellFormat(11,
						false, Alignment.CENTRE, VerticalAlignment.CENTRE,
						Border.ALL);// 单元格样式
			 wcfTytle2.setWrap(true);
			 
			 //获取学校学院List
			 List<HashMap<String, String>> xxxy = dao.getXxXy(nj);
			 if(xxxy.size()>0){
				 String xymc ="";
				 String xydm ="";
				 int yrow =0;//每个学院班级个数
				 int yAllrow=0;//每个学院中所有班级个数累加
				 int bjrow=2;//班级行数，用于指定班级坐标
				 int xh=0;//序号，用于指定班级坐标
				 int zrow=0;//最后一行坐标 
				 
				 int xbjzrs =0;//学校总人数
				 int xboyzrs =0;//学校男生总人数
				 int xgirlzrs =0;//学校女生总人数
				 int xxyzrs =0;//学校领取协议和就业意向总人数
				 int xqyzrs =0;//学校签约总人数
				 int xcgzrs =0;//学校出国总人数
				 int xszzrs =0;//学校深造总人数
				 double xlylrs =0;//学校签约率人数
				 int xtkzrs =0;//学校特困总人数
				 int xwszrs =0;//学校外省总人数
				 int xbzrzrs=0;//学校班主任人数
				 double xqylzrs=0;
				 NumberFormat df = new DecimalFormat("#0.00");//百分比
				 int zygs = 0;//该学院中专业各数
				 
				 
				 for(int i =0;i<xxxy.size();i++){
					 HashMap<String, String> map = xxxy.get(i);
					 xymc=map.get("xymc");//学院名称
					 xydm=map.get("xydm");//学院代码
					 zygs=zygs+dao.getZygs(nj,xydm);
					 //获取学院中的班级List
					 List<HashMap<String, String>> bjgzList = dao.getBjByGz(nj,xydm);//高职
					 List<HashMap<String, String>> bjbk = dao.getBjByBk(nj,xydm);//本科
					 
					 List<HashMap<String, String>> bjgz = new ArrayList<HashMap<String, String>>();
					 //去除高职中也妇科本科的数据
					 if(bjgzList.size()>0){
						 for(int bj=0;bj<bjgzList.size();bj++){
							 HashMap<String, String> wclbjmap =bjgzList.get(bj);
							 String wclbjdm=wclbjmap.get("bjdm");//没有处理过的高职班级代码
							 boolean bz = false;
							 if(bjbk.size()>0){
								 for(int bkbj=0;bkbj<bjbk.size();bkbj++){
									 HashMap<String, String> wclbkbjmap =bjbk.get(bkbj);
									 String wclbkbjdm=wclbkbjmap.get("bjdm");
									 if(wclbjdm.equals(wclbkbjdm)){
										 bz=true;
										 break;
									 }
								 }
							 }
							 if(bz==false){
								 HashMap<String, String> bjjg = new HashMap<String, String>();
								 bjjg.put("bjdm", wclbjmap.get("bjdm"));
								 bjjg.put("bjmc", wclbjmap.get("bjmc"));
								 bjgz.add(bjjg);
							 }
						 }
					 }
					 String bjmc ="";//班级名称
					 String bjdm ="";//班级代码
					 ////////////////输出系别--开始/////////////////////
					 yrow = bjbk.size()+bjgz.size();
					 yAllrow=yrow+yAllrow;
					 if(yrow>0){
							 ws.mergeCells(1,yAllrow-yrow+3,1,yAllrow+3);//学院合并几列
							 ws.addCell(new Label(1,yAllrow-yrow+3,xymc,wcfTytle2));
							 
							 if(bjbk.size()>0){
								 ws.mergeCells(3,yAllrow-yrow+3,3,yAllrow-bjgz.size()+2);//本科合并几列
								 ws.addCell(new Label(3,yAllrow-yrow+3,"本科",wcfTytle2));
								 if(bjgz.size()>0){
									 ws.mergeCells(1,yAllrow-bjbk.size()+3,1,yAllrow+2);//高职合并几列
									 ws.addCell(new Label(3,yAllrow-yrow+3,"高职",wcfTytle2));
								 }
							 }else{
								 if(bjgz.size()>0){
									 ws.mergeCells(3,yAllrow-bjbk.size()+3,3,yAllrow+2);//高职合并几列
									 ws.addCell(new Label(3,yAllrow-yrow+3,"高职",wcfTytle2));
								 }
							 }
							 
					 }
					 
					 ////////////////输出系别--结束/////////////////////
					 ////////////////输出班级--开始/////////////////////
					 int bjzrs =0;//学院总人数
					 int boyzrs =0;//学院男生总人数
					 int girlzrs =0;//学院女生总人数
					 int xyzrs =0;//学院领取协议和就业意向总人数
					 int qyzrs =0;//学院签约总人数
					 int cgzrs =0;//学院出国总人数
					 int szzrs =0;//学院深造总人数
					 double lylrs =0;//学院签约率人数
					 int tkzrs =0;//学院特困总人数
					 int wszrs =0;//学院外省总人数
					 int bzrzrs=0;//学院班主任人数
					 if(bjbk.size()>0){
						 zrow = zrow+bjbk.size();
						 for(int b =0;b<bjbk.size();b++){
							 HashMap<String, String> bkmap = bjbk.get(b);
							 bjmc=bkmap.get("bjmc");//班级名称
							 bjdm=bkmap.get("bjdm");//班级代码
							 bjrow=bjrow+1;//班级名称坐标
							 xh=xh+1;//序号
							 ws.addCell(new Label(0,bjrow,xh+"",wcfTytle2));//序号
							 ws.addCell(new Label(2,bjrow,bjmc,wcfTytle2));//班级名称
							 HashMap<String, String> zrsmap = dao.getBjZrs(bjdm);//班级总人数
							 ws.addCell(new Label(4,bjrow,zrsmap.get("zrs"),wcfTytle2));//班级总人数
							 HashMap<String, String> brsmap = dao.getBjBrs(bjdm);//班级男生总人数
							 ws.addCell(new Label(5,bjrow,brsmap.get("brs"),wcfTytle2));//班级男生总人数
							 HashMap<String, String> grsmap = dao.getBjGrs(bjdm);//班级女生总人数
							 ws.addCell(new Label(6,bjrow,grsmap.get("grs"),wcfTytle2));//班级女生总人数
							 HashMap<String, String> lqxyrsmap = dao.getBjLqxyrs(bjdm);//领取协议人数
							 ws.addCell(new Label(7,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//领取协议人数
							 ws.addCell(new Label(8,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//就业意向人数
							 HashMap<String, String> qyrsmap = dao.getBjQyrs(bjdm);//签约人数
							 ws.addCell(new Label(9,bjrow,qyrsmap.get("rs"),wcfTytle2));//签约人数
							 HashMap<String, String> cgrsmap = dao.getBjCgrs(bjdm);//出国人数
							 ws.addCell(new Label(10,bjrow,cgrsmap.get("rs"),wcfTytle2));//出国人数
							 HashMap<String, String> szrsmap = dao.getBjSzrs(bjdm);//深造人数
							 ws.addCell(new Label(11,bjrow,szrsmap.get("rs"),wcfTytle2));//深造人数
							 
							 HashMap<String, String> qylmap = dao.getQyl(bjdm);//签约率人数
							 double q =Double.valueOf(qylmap.get("rs"));
							 double z =Double.valueOf(zrsmap.get("zrs"));
							 ws.addCell(new Label(12,bjrow,df.format(q/z*100)+"%",wcfTytle2));//签约率
							 double j =Double.valueOf(lqxyrsmap.get("rs"));
							 ws.addCell(new Label(13,bjrow,df.format(j/z*100)+"%",wcfTytle2));//就业意向率
							 HashMap<String, String> bzrmap = dao.getBjBzr(bjdm);//获取班主任姓名
							 ws.addCell(new Label(14,bjrow,bzrmap.get("xm"),wcfTytle2));//获取班主任姓名
//							 if(bzrmap.get("xm")!=null){
//								 if(bzrmap.get("xm").length()>0){
//									 bzrzrs+=1;
//								 }
//							 }
							 HashMap<String, String> tksrsmap = dao.getBjTksrs(bjdm);//获取班级特困生人数
							 ws.addCell(new Label(15,bjrow,tksrsmap.get("rs"),wcfTytle2));//获取班级特困生人数
							 HashMap<String, String> wsrsmap = dao.getBjWsrs(bjdm);//获取班级外省人数
							 ws.addCell(new Label(16,bjrow,wsrsmap.get("rs"),wcfTytle2));//获取班级外省人数
							 
							 bjzrs = bjzrs + Integer.parseInt(zrsmap.get("zrs"));
							 boyzrs = boyzrs +Integer.parseInt(brsmap.get("brs"));
							 girlzrs = girlzrs +Integer.parseInt(grsmap.get("grs"));
							 xyzrs = xyzrs +Integer.parseInt(lqxyrsmap.get("rs"));
							 qyzrs = qyzrs +Integer.parseInt(qyrsmap.get("rs"));
							 cgzrs = cgzrs +Integer.parseInt(cgrsmap.get("rs"));
							 szzrs = szzrs +Integer.parseInt(szrsmap.get("rs"));
							 lylrs = lylrs + q;
							 tkzrs = tkzrs +Integer.parseInt(tksrsmap.get("rs"));
							 wszrs = wszrs +Integer.parseInt(wsrsmap.get("rs"));
						 }
					 }
					 if(bjgz.size()>0){
						 zrow = zrow+bjgz.size();
						 for(int g =0;g<bjgz.size();g++){
							 HashMap<String, String> gzmap = bjgz.get(g);
							 bjmc=gzmap.get("bjmc");//班级名称
							 bjdm=gzmap.get("bjdm");//班级代码
							 bjrow=bjrow+1;//班级名称坐标
							 xh=xh+1;//序号
							 ws.addCell(new Label(0,bjrow,xh+"",wcfTytle2));//序号
							 ws.addCell(new Label(2,bjrow,bjmc,wcfTytle2));//班级名称
							 HashMap<String, String> zrsmap = dao.getBjZrs(bjdm);//班级总人数
							 ws.addCell(new Label(4,bjrow,zrsmap.get("zrs"),wcfTytle2));//班级总人数
							 HashMap<String, String> brsmap = dao.getBjBrs(bjdm);//班级男生总人数
							 ws.addCell(new Label(5,bjrow,brsmap.get("brs"),wcfTytle2));//班级男生总人数
							 HashMap<String, String> grsmap = dao.getBjGrs(bjdm);//班级女生总人数
							 ws.addCell(new Label(6,bjrow,grsmap.get("grs"),wcfTytle2));//班级女生总人数
							 HashMap<String, String> lqxyrsmap = dao.getBjLqxyrs(bjdm);//领取协议人数
							 ws.addCell(new Label(7,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//领取协议人数
							 ws.addCell(new Label(8,bjrow,lqxyrsmap.get("rs"),wcfTytle2));//就业意向人数
							 HashMap<String, String> qyrsmap = dao.getBjQyrs(bjdm);//签约人数
							 ws.addCell(new Label(9,bjrow,qyrsmap.get("rs"),wcfTytle2));//签约人数
							 HashMap<String, String> cgrsmap = dao.getBjCgrs(bjdm);//出国人数
							 ws.addCell(new Label(10,bjrow,cgrsmap.get("rs"),wcfTytle2));//出国人数
							 HashMap<String, String> szrsmap = dao.getBjSzrs(bjdm);//深造人数
							 ws.addCell(new Label(11,bjrow,szrsmap.get("rs"),wcfTytle2));//深造人数
							 
							 HashMap<String, String> qylmap = dao.getQyl(bjdm);//签约率人数
							 double q =Double.valueOf(qylmap.get("rs"));
							 double z =Double.valueOf(zrsmap.get("zrs"));
							 ws.addCell(new Label(12,bjrow,df.format(q/z*100)+"%",wcfTytle2));//签约率
							 double j =Double.valueOf(lqxyrsmap.get("rs"));
							 ws.addCell(new Label(13,bjrow,df.format(j/z*100)+"%",wcfTytle2));//就业意向率
							 HashMap<String, String> bzrmap = dao.getBjBzr(bjdm);//获取班主任姓名
							 ws.addCell(new Label(14,bjrow,bzrmap.get("xm"),wcfTytle2));//获取班主任姓名
//							 if(bzrmap.get("xm")!=null){
//								 if(bzrmap.get("xm").length()>0){
//									 bzrzrs+=1;
//								 }
//							 }
							 HashMap<String, String> tksrsmap = dao.getBjTksrs(bjdm);//获取班级特困生人数
							 ws.addCell(new Label(15,bjrow,tksrsmap.get("rs"),wcfTytle2));//获取班级特困生人数
							 HashMap<String, String> wsrsmap = dao.getBjWsrs(bjdm);//获取班级外省人数
							 ws.addCell(new Label(16,bjrow,wsrsmap.get("rs"),wcfTytle2));//获取班级外省人数
							 
							 bjzrs = bjzrs + Integer.parseInt(zrsmap.get("zrs"));
							 boyzrs = boyzrs +Integer.parseInt(brsmap.get("brs"));
							 girlzrs = girlzrs +Integer.parseInt(grsmap.get("grs"));
							 xyzrs = xyzrs +Integer.parseInt(lqxyrsmap.get("rs"));
							 qyzrs = qyzrs +Integer.parseInt(qyrsmap.get("rs"));
							 cgzrs = cgzrs +Integer.parseInt(cgrsmap.get("rs"));
							 szzrs = szzrs +Integer.parseInt(szrsmap.get("rs"));
							 lylrs = lylrs + q;
							 tkzrs = tkzrs +Integer.parseInt(tksrsmap.get("rs"));
							 wszrs = wszrs +Integer.parseInt(wsrsmap.get("rs"));
						 }
					 }
					 ////////////////输出班级--结束/////////////////////
					 WritableCellFormat wcfTytle3 = ExcelMB.getWritableCellFormat(11,
								false, Alignment.CENTRE, VerticalAlignment.CENTRE,
								Border.ALL);// 单元格样式
					 wcfTytle3.setWrap(true);
					 wcfTytle3.setBackground(Colour.YELLOW);
					 if(yAllrow>0){
						 bjrow=bjrow+1;
						 yAllrow=yAllrow+1;
						 double qylzrs=bjzrs;
						 xqylzrs=xqylzrs+qylzrs;
						 ws.addCell(new Label(0,bjrow,"",wcfTytle2));
						 ws.addCell(new Label(2,bjrow,"",wcfTytle2));
						 //ws.addCell(new Label(3,yAllrow+3,"d",wcfTytle3));
						 xbjzrs =xbjzrs+bjzrs;
						 ws.addCell(new Label(4,bjrow,bjzrs+"",wcfTytle3));
						 xboyzrs=xboyzrs+boyzrs;
						 ws.addCell(new Label(5,bjrow,boyzrs+"",wcfTytle3));
						 xgirlzrs=xgirlzrs+girlzrs;
						 ws.addCell(new Label(6,bjrow,girlzrs+"",wcfTytle3));
						 xxyzrs=xxyzrs+xyzrs;
						 ws.addCell(new Label(7,bjrow,xyzrs+"",wcfTytle3));
						 ws.addCell(new Label(8,bjrow,xyzrs+"",wcfTytle3));
						 xqyzrs=xqyzrs+qyzrs;
						 ws.addCell(new Label(9,bjrow,qyzrs+"",wcfTytle3));
						 xcgzrs = xcgzrs+cgzrs;
						 ws.addCell(new Label(10,bjrow,cgzrs+"",wcfTytle3));
						 xszzrs= xszzrs+szzrs;
						 ws.addCell(new Label(11,bjrow,szzrs+"",wcfTytle3));
						 xlylrs=xlylrs+lylrs;
						 ws.addCell(new Label(12,bjrow,df.format(lylrs/qylzrs*100)+"%",wcfTytle3));
						 ws.addCell(new Label(13,bjrow,df.format(xyzrs/qylzrs*100)+"%",wcfTytle3));
						 HashMap<String, String> bzrzrsmap = dao.getBzrzrs(xydm);
						 bzrzrs = Integer.parseInt(bzrzrsmap.get("rs"));
						 xbzrzrs =xbzrzrs+bzrzrs;
						 ws.addCell(new Label(14,bjrow,bzrzrs+"人",wcfTytle3));
						 xtkzrs=xtkzrs+tkzrs;
						 ws.addCell(new Label(15,bjrow,tkzrs+"",wcfTytle3));
						 xwszrs=xwszrs+wszrs;
						 ws.addCell(new Label(16,bjrow,wszrs+"",wcfTytle3));
						 zrow = zrow+1;
					 }
					 
				 }
				 WritableCellFormat wcfTytle4 = ExcelMB.getWritableCellFormat(11,
							false, Alignment.CENTRE, VerticalAlignment.CENTRE,
							Border.ALL);// 单元格样式
				 wcfTytle4.setWrap(true);
				 wcfTytle4.setBackground(Colour.GREEN);
				 ws.mergeCells(0,zrow+3,3,zrow+3);//合并四个单元格
				 ws.addCell(new Label(0,zrow+3,xxxy.size()+"个系"+zygs+"个专业"+xh+"个班级",wcfTytle4));
				 ws.addCell(new Label(4,zrow+3,xbjzrs+"",wcfTytle4));
				 ws.addCell(new Label(5,zrow+3,xboyzrs+"",wcfTytle4));
				 ws.addCell(new Label(6,zrow+3,xgirlzrs+"",wcfTytle4));
				 ws.addCell(new Label(7,zrow+3,xxyzrs+"",wcfTytle4));
				 ws.addCell(new Label(8,zrow+3,xxyzrs+"",wcfTytle4));
				 ws.addCell(new Label(9,zrow+3,xqyzrs+"",wcfTytle4));
				 ws.addCell(new Label(10,zrow+3,xcgzrs+"",wcfTytle4));
				 ws.addCell(new Label(11,zrow+3,xszzrs+"",wcfTytle4));
				 ws.addCell(new Label(12,zrow+3,df.format(xlylrs/xqylzrs*100)+"%",wcfTytle4));
				 ws.addCell(new Label(13,zrow+3,df.format(xxyzrs/xqylzrs*100)+"%",wcfTytle4));
				 ws.addCell(new Label(14,zrow+3,xbzrzrs+"人",wcfTytle4));
				 ws.addCell(new Label(15,zrow+3,xtkzrs+"",wcfTytle4));
				 ws.addCell(new Label(16,zrow+3,xwszrs+"",wcfTytle4));
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		 ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 按专业就业统计
	 * 
	 * @author honglin
	 * @date 2012-4-23
	 * 
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 * 
	 */
	public void printZyjytj(OutputStream os,String[] nj,String[] jyxy) throws SQLException{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String rq=sdf.format(date);
		
		String njStr="";
		for (int i = 0 ; i < nj.length ; i++){
			njStr+=nj[i];
			if (i != nj.length-1){
				njStr=njStr+"、";
			}
		}
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("按专业就业率统计", 0);
		String title =Base.xxmc+njStr+"届毕业生就业进展情况统计表";
		
		try {
			//需要使用的样式
			WritableCellFormat btys = ExcelMB.getWritableCellFormat(12,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 大标题单元格样式
			WritableCellFormat dygbtys2 = ExcelMB.getWritableCellFormat(12,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 2行标题单元格样式，无底色
			WritableCellFormat dygbtys3 = ExcelMB.getWritableCellFormat(12,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 3行标题单元格样式，无底色
			WritableCellFormat btdsys = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格标题样式，有底色，浅蓝色
			btdsys.setBackground(Colour.LIGHT_TURQUOISE2);
			WritableCellFormat dygdsys = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式，有底色，浅蓝色
			dygdsys.setBackground(Colour.LIGHT_TURQUOISE2);
			WritableCellFormat dygys = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式，无底色
			WritableCellFormat dygjcys = ExcelMB.getWritableCellFormat(10,
					true, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式，加粗,无底色
			WritableCellFormat dyghsys = ExcelMB.getWritableCellFormat(10,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式，有底色，灰色
			dyghsys.setBackground(Colour.GREY_25_PERCENT);
			//表格标题
			 //跨int列 、第int列起、跨int 行、第int 行起
			 ws.mergeCells(0, 0,42, 0);
			 ws.addCell(new Label(0,0,title,btys));
			 //表格日期
			 ws.mergeCells(0, 1,42, 1);
			 ws.addCell(new Label(0,1,"（统计日期"+rq+"）",dygbtys2));
			 ws.mergeCells(0, 2,42, 2);
			 ws.addCell(new Label(0,2,"制表单位：招生就业处",dygbtys3));
			 
			 
			 //表格标题
			 ws.mergeCells(0,3,0,5);//合并三行
			 ws.addCell(new Label(0,3,"专业名称",btdsys));
			 ws.setColumnView(0, 15);
			 ws.mergeCells(1,3,1,5);//合并三行
			 ws.addCell(new Label(1,3,"排序",btdsys));
			 ws.setColumnView(1, 4);
			 ws.mergeCells(2, 3,13, 3);//合并12列
			 ws.addCell(new Label(2,3,"生源人数",btdsys));
			 ws.mergeCells(2, 4,4, 4);//合并3行
			 ws.addCell(new Label(2,4,"合计",btdsys));
			 ws.addCell(new Label(2,5,"男",btdsys));
			 ws.setColumnView(2, 4);
			 ws.addCell(new Label(3,5,"女",btdsys));
			 ws.setColumnView(3, 4);
			 ws.addCell(new Label(4,5,"总",btdsys));
			 ws.setColumnView(4, 4);
			 ws.mergeCells(5, 4,7, 4);//合并3列
			 ws.addCell(new Label(5,4,"研",btdsys));
			 ws.addCell(new Label(5,5,"男",btdsys));
			 ws.setColumnView(5, 4);
			 ws.addCell(new Label(6,5,"女",btdsys));
			 ws.setColumnView(6, 4);
			 ws.addCell(new Label(7,5,"总",btdsys));
			 ws.setColumnView(7, 4);
			 ws.mergeCells(8, 4,10, 4);//合并3列
			 ws.addCell(new Label(8,4,"本科",btdsys));
			 ws.addCell(new Label(8,5,"男",btdsys));
			 ws.setColumnView(8, 4);
			 ws.addCell(new Label(9,5,"女",btdsys));
			 ws.setColumnView(9, 4);
			 ws.addCell(new Label(10,5,"总",btdsys));
			 ws.setColumnView(10, 4);
			 ws.mergeCells(11, 4,13, 4);//合并3列
			 ws.addCell(new Label(11,4,"高职",btdsys));
			 ws.addCell(new Label(11,5,"男",btdsys));
			 ws.setColumnView(11, 4);
			 ws.addCell(new Label(12,5,"女",btdsys));
			 ws.setColumnView(12, 4);
			 ws.addCell(new Label(13,5,"总",btdsys));
			 ws.setColumnView(13, 4);
			 ws.mergeCells(14, 3,21, 3);//合并8列
			 ws.addCell(new Label(14,3,"就业意向率",btdsys));
			 ws.mergeCells(14, 4,14, 5);//合并2行
			 ws.addCell(new Label(14,4,"研",btdsys));
			 ws.setColumnView(14, 4);
			 ws.mergeCells(15, 4,15, 5);//合并2行
			 ws.addCell(new Label(15,4,"％",btdsys));
			 ws.setColumnView(15, 8);
			 ws.mergeCells(16, 4,16, 5);//合并2行
			 ws.addCell(new Label(16,4,"本科",btdsys));
			 ws.setColumnView(16, 4);
			 ws.mergeCells(17, 4,17, 5);//合并2行
			 ws.addCell(new Label(17,4,"％",btdsys));
			 ws.setColumnView(17, 8);
			 ws.mergeCells(18, 4,18, 5);//合并2行
			 ws.addCell(new Label(18,4,"高职",btdsys));
			 ws.setColumnView(18, 4);
			 ws.mergeCells(19, 4,19, 5);//合并2行
			 ws.addCell(new Label(19,4,"％",btdsys));
			 ws.setColumnView(19, 8);
			 ws.mergeCells(20, 4,20, 5);//合并2行
			 ws.addCell(new Label(20,4,"合计",btdsys));
			 ws.setColumnView(20, 4);
			 ws.mergeCells(21, 4,21, 5);//合并2行
			 ws.addCell(new Label(21,4,"％",btdsys));
			 ws.setColumnView(21, 8);
			 ws.mergeCells(22, 3,41, 3);//合并20列
			 ws.addCell(new Label(22,3,"签约率",btdsys));
			 ws.mergeCells(22, 4,24, 4);//合并3列
			 ws.addCell(new Label(22,4,"研",btdsys));
			 ws.addCell(new Label(22,5,"男",btdsys));
			 ws.setColumnView(22, 4);
			 ws.addCell(new Label(23,5,"女",btdsys));
			 ws.setColumnView(23, 4);
			 ws.addCell(new Label(24,5,"总",btdsys));
			 ws.setColumnView(24, 4);
			 ws.mergeCells(25, 4,27, 4);//合并3列
			 ws.addCell(new Label(25,4,"％",btdsys));
			 ws.addCell(new Label(25,5,"男",btdsys));
			 ws.setColumnView(25, 8);
			 ws.addCell(new Label(26,5,"女",btdsys));
			 ws.setColumnView(26, 8);
			 ws.addCell(new Label(27,5,"总",btdsys));
			 ws.setColumnView(27, 8);
			 ws.mergeCells(28, 4,30, 4);//合并3列
			 ws.addCell(new Label(28,4,"本科",btdsys));
			 ws.addCell(new Label(28,5,"男",btdsys));
			 ws.setColumnView(28, 4);
			 ws.addCell(new Label(29,5,"女",btdsys));
			 ws.setColumnView(29, 4);
			 ws.addCell(new Label(30,5,"总",btdsys));
			 ws.setColumnView(30, 4);
			 ws.mergeCells(31, 4,33, 4);//合并3列
			 ws.addCell(new Label(31,4,"％",btdsys));
			 ws.addCell(new Label(31,5,"男",btdsys));
			 ws.setColumnView(31, 8);
			 ws.addCell(new Label(32,5,"女",btdsys));
			 ws.setColumnView(32, 8);
			 ws.addCell(new Label(33,5,"总",btdsys));
			 ws.setColumnView(33, 8);
			 ws.mergeCells(34, 4,36, 4);//合并3列
			 ws.addCell(new Label(34,4,"高职",btdsys));
			 ws.addCell(new Label(34,5,"男",btdsys));
			 ws.setColumnView(34, 4);
			 ws.addCell(new Label(35,5,"女",btdsys));
			 ws.setColumnView(35, 4);
			 ws.addCell(new Label(36,5,"总",btdsys));
			 ws.setColumnView(36, 4);
			 ws.mergeCells(37, 4,39, 4);//合并3列
			 ws.addCell(new Label(37,4,"％",btdsys));
			 ws.addCell(new Label(37,5,"男",btdsys));
			 ws.setColumnView(37, 8);
			 ws.addCell(new Label(38,5,"女",btdsys));
			 ws.setColumnView(38, 8);
			 ws.addCell(new Label(39,5,"总",btdsys));
			 ws.setColumnView(39, 8);
			 ws.mergeCells(40, 4,40, 5);//合并2行
			 ws.addCell(new Label(40,4,"合计",btdsys));
			 ws.setColumnView(40, 8);
			 ws.mergeCells(41, 4,41, 5);//合并2行
			 ws.addCell(new Label(41,4,"％",btdsys));
			 ws.setColumnView(41, 8);
			 ws.addCell(new Label(42,3,"去年同期",btdsys));
			 ws.setColumnView(42, 8);
			 ws.mergeCells(42, 4,42, 5);//合并2行
			 ws.addCell(new Label(42,4,"签约率",btdsys));
			 ws.setColumnView(42, 8);
			 
			 
			 //表格内容
			 
			 //总计开始
			 int syrs=0;//生源人数
			 int sybrs=0;//生源男生人数
			 int sygrs=0;//生源女生人数
			 int sybkrs=0;//生源本科人数
			 int sybkbrs=0;//生源本科男生人数
			 int sybkgrs=0;//生源本科女生人数
			 int sygzrs=0;//生源高职人数
			 int sygzbrs=0;//生源高职男生人数
			 int sygzgrs=0;//生源高职女生人数
			 int jylbkrs=0;//就业意向率本科人数
			 int jylgzrs=0;//就业意向率高职人数
			 int jylrs=0;//就业意向率人数
			 int qylbkrs=0;//签约率本科人数
			 int qylbkbrs=0;//签约率本科男生人数
			 int qylbkgrs=0;//签约率本科女生人数
			 int qylgzrs=0;//签约率高职人数
			 int qylgzbrs=0;//签约率高职男生人数
			 int qylgzgrs=0;//签约率高职女生人数
			 int qylgjrs=0;//签约率合计人数
			 //总计结束
			 
			 //获取学院中的班级列表
			 List<HashMap<String , String >> jyzyList=dao.getXyByZy(nj,jyxy);
			 NumberFormat df = new DecimalFormat("#0.00");//百分比
			 if(jyzyList.size()>0){
				 for (int i = 0 ; i< jyzyList.size(); i++ ) {
					 HashMap<String , String > jyzyMap = jyzyList.get(i);
					 String zymc = jyzyMap.get("zymc");//专业名称
					 String zydm = jyzyMap.get("zydm");//专业代码
					 //专业名称
					 ws.addCell(new Label(0,6+i,zymc,btdsys));
					 //序号
					 ws.addCell(new Label(1,6+i,i+1+"",dygdsys));
					 //生源人数-合计-男生人数
					 HashMap<String, String> sybzrsMap= dao.getZyBrs(nj,zydm);
					 ws.addCell(new Label(2,6+i,sybzrsMap.get("brs"),dygdsys));
					 sybrs=sybrs+Integer.parseInt(sybzrsMap.get("brs"));
					//生源人数-合计-女生人数
					 HashMap<String, String> sygzrsMap= dao.getZyGrs(nj,zydm);
					 ws.addCell(new Label(3,6+i,sygzrsMap.get("grs"),dygdsys));
					 sygrs=sygrs+Integer.parseInt(sygzrsMap.get("grs"));
					//生源人数-合计-总人数
					 HashMap<String, String> syzrsMap= dao.getZyZrs(nj,zydm);
					 ws.addCell(new Label(4,6+i,syzrsMap.get("zrs"),dygdsys));
					 syrs=syrs+Integer.parseInt(syzrsMap.get("zrs"));
					 //生源人数-研-男生总人数
					 ws.addCell(new Label(5,6+i,"",dyghsys));
					//生源人数-研-女生总人数
					 ws.addCell(new Label(6,6+i,"",dyghsys));
					//生源人数-研-总人数
					 ws.addCell(new Label(7,6+i,"0",dygdsys));
					//生源人数-本科-男生总人数
					 HashMap<String, String> sybkbzrsMap= dao.getZyBkBrs(nj,zydm);
					 ws.addCell(new Label(8,6+i,sybkbzrsMap.get("rs"),dygys));
					 sybkbrs=sybkbrs+Integer.parseInt(sybkbzrsMap.get("rs"));
					//生源人数-本科-女生总人数
					 HashMap<String, String> sybkgzrsMap= dao.getZyBkGrs(nj,zydm);
					 ws.addCell(new Label(9,6+i,sybkgzrsMap.get("rs"),dygys));
					 sybkgrs=sybkgrs+Integer.parseInt(sybkgzrsMap.get("rs"));
					//生源人数-本科-总人数
					 HashMap<String, String> sybkzrsMap= dao.getZyBkZrs(nj,zydm);
					 ws.addCell(new Label(10,6+i,sybkzrsMap.get("rs"),dygdsys));
					 sybkrs=sybkrs+Integer.parseInt(sybkzrsMap.get("rs"));
					//生源人数-高职-男生总人数
					 HashMap<String, String> sygzbzrsMap= dao.getZyGzBrs(nj,zydm);
					 ws.addCell(new Label(11,6+i,sygzbzrsMap.get("rs"),dygys));
					 sygzbrs=sygzbrs+Integer.parseInt(sygzbzrsMap.get("rs"));
					//生源人数-高职-女生总人数
					 HashMap<String, String> sygzgzrsMap= dao.getZyGzGrs(nj,zydm);
					 ws.addCell(new Label(12,6+i,sygzgzrsMap.get("rs"),dygys));
					 sygzgrs=sygzgrs+Integer.parseInt(sygzgzrsMap.get("rs"));
					//生源人数-高职-总人数
					 HashMap<String, String> sygzzrsMap= dao.getZyGzZrs(nj,zydm);
					 ws.addCell(new Label(13,6+i,sygzzrsMap.get("rs"),dygdsys));
					 sygzrs=sygzrs+Integer.parseInt(sygzzrsMap.get("rs"));
					//就业意向率-研
					 ws.addCell(new Label(14,6+i,"",dygys));
					//就业意向率-研-%
					 ws.addCell(new Label(15,6+i,"",dygdsys));
					//就业意向率-本科
					 HashMap<String,String> jylbkzrsMap = dao.getZyJylBkZrs(nj,zydm);
					 ws.addCell(new Label(16,6+i,jylbkzrsMap.get("rs"),dygys));
					 jylbkrs=jylbkrs+Integer.parseInt(jylbkzrsMap.get("rs"));
					//就业意向率-本科-%
					 double jylbkzrs = Double.parseDouble(jylbkzrsMap.get("rs"));//就业率本科男生总人数
					 double sybkzrs = Double.parseDouble(sybkzrsMap.get("rs"));//生源本科总人数
					 ws.addCell(new Label(17,6+i,df.format(jylbkzrs/sybkzrs*100)+"%",dygdsys));
					 if(sybkzrs==0.0){
						 ws.addCell(new Label(17,6+i,"0.00%",dygdsys));
					 }else{
						 ws.addCell(new Label(17,6+i,df.format(jylbkzrs/sybkzrs*100)+"%",dygdsys));
					 }
					//就业意向率-高职
					 HashMap<String,String> jylgzzrsMap = dao.getZyJylGzZrs(nj,zydm);
					 ws.addCell(new Label(18,6+i,jylgzzrsMap.get("rs"),dygys));
					 jylgzrs=jylgzrs+Integer.parseInt(jylgzzrsMap.get("rs"));
					//就业意向率-高职-%
					 double jylgzzrs = Double.parseDouble(jylgzzrsMap.get("rs"));//就业率高职总人数
					 double sygzzrs = Double.parseDouble(sygzzrsMap.get("rs"));//生源高职总人数
					 if(sygzzrs==0.0){
						 ws.addCell(new Label(19,6+i,"0.00%",dygdsys));
					 }else{
						 ws.addCell(new Label(19,6+i,df.format(jylgzzrs/sygzzrs*100)+"%",dygdsys));
					 }
					//就业意向率-合计
					 ws.addCell(new Label(20,6+i,Integer.parseInt(jylbkzrsMap.get("rs"))+Integer.parseInt(jylgzzrsMap.get("rs"))+"",dygdsys));
					 jylrs=jylrs+Integer.parseInt(jylbkzrsMap.get("rs"))+Integer.parseInt(jylgzzrsMap.get("rs"));
					//就业意向率-合计-%
					 double syzrs = sybkzrs+sygzzrs;//生源总人数
					 double jyyxzrs = jylbkzrs+jylgzzrs;//就业意向总人数
					 if(jyyxzrs==0.0){
						 ws.addCell(new Label(21,6+i,"0.00%",dygdsys));
					 }else{
						 ws.addCell(new Label(21,6+i,df.format(jyyxzrs/syzrs*100)+"%",dygdsys));
					 }
					//签约率-研（男、女、总）
					 ws.addCell(new Label(22,6+i,"",dyghsys));
					 ws.addCell(new Label(23,6+i,"",dyghsys));
					 ws.addCell(new Label(24,6+i,"0",dygdsys));
					//签约率-研-%（男、女、总）
					 ws.addCell(new Label(25,6+i,"",dygdsys));
					 ws.addCell(new Label(26,6+i,"",dygdsys));
					 ws.addCell(new Label(27,6+i,"",dygdsys));
					//签约率-本科-男生总人数
					 HashMap<String, String> qylbkbzrsMap= dao.getZyQylBkBZrs(nj,zydm);
					 ws.addCell(new Label(28,6+i,qylbkbzrsMap.get("rs"),dygys));
					 qylbkbrs=qylbkbrs+Integer.parseInt(qylbkbzrsMap.get("rs"));
					//签约率-本科-女生总人数
					 HashMap<String, String> qylbkgzrsMap= dao.getZyQylBkGZrs(nj,zydm);
					 ws.addCell(new Label(29,6+i,qylbkgzrsMap.get("rs"),dygys));
					 qylbkgrs=qylbkgrs+Integer.parseInt(qylbkgzrsMap.get("rs"));
					//签约率-本科-总人数
					 HashMap<String, String> qylbkzrsMap= dao.getZyQylBkZrs(nj,zydm);
					 ws.addCell(new Label(30,6+i,qylbkzrsMap.get("rs"),dygdsys));
					 qylbkrs=qylbkrs+Integer.parseInt(qylbkzrsMap.get("rs"));
					//签约率-判断生源本科总人数
					 if(sybkzrs==0.0){
						//签约率-本科-男生总人数-%
						 ws.addCell(new Label(31,6+i,"0.00%",dygdsys));
						//签约率-本科-女生总人数-%
						 ws.addCell(new Label(32,6+i,"0.00%",dygdsys));
						//签约率-本科-总人数-%
						 ws.addCell(new Label(33,6+i,"0.00%",dygdsys));
					 }else{
						//签约率-本科-男生总人数-%
						 double qylbkbzrs = Double.parseDouble(qylbkbzrsMap.get("rs"));
						 ws.addCell(new Label(31,6+i,df.format(qylbkbzrs/sybkzrs*100)+"%",dygdsys));
						//签约率-本科-女生总人数-%
						 double qylbkgzrs = Double.parseDouble(qylbkgzrsMap.get("rs"));
						 ws.addCell(new Label(32,6+i,df.format(qylbkgzrs/sybkzrs*100)+"%",dygdsys));
						//签约率-本科-总人数-%
						 double qylbkzrs = Double.parseDouble(qylbkzrsMap.get("rs"));
						 ws.addCell(new Label(33,6+i,df.format(qylbkzrs/sybkzrs*100)+"%",dygdsys));
					 }
					//签约率-高职-男生总人数
					 HashMap<String, String> qylgzbzrsMap= dao.getZyQylGzBZrs(nj,zydm);
					 ws.addCell(new Label(34,6+i,qylgzbzrsMap.get("rs"),dygys));
					 qylgzbrs=qylgzbrs+Integer.parseInt(qylgzbzrsMap.get("rs"));
					//签约率-高职-女生总人数
					 HashMap<String, String> qylgzgzrsMap= dao.getZyQylGzGZrs(nj,zydm);
					 ws.addCell(new Label(35,6+i,qylgzgzrsMap.get("rs"),dygys));
					 qylgzgrs=qylgzgrs+Integer.parseInt(qylgzgzrsMap.get("rs"));
					//签约率-高职-总人数
					 HashMap<String, String> qylgzzrsMap= dao.getZyQylGzZrs(nj,zydm);
					 ws.addCell(new Label(36,6+i,qylgzzrsMap.get("rs"),dygdsys));
					 qylgzrs=qylgzrs+Integer.parseInt(qylgzzrsMap.get("rs"));
					//判断生源高职总人数
					 if(sygzzrs==0.0){
						//签约率-高职-男生总人数-%
						 ws.addCell(new Label(37,6+i,"0.00%",dygdsys));
						//签约率-高职-女生总人数-%
						 ws.addCell(new Label(38,6+i,"0.00%",dygdsys));
						//签约率-高职-总人数-%
						 ws.addCell(new Label(39,6+i,"0.00%",dygdsys));
					 }else{
						//签约率-高职-男生总人数-%
						 double qylgzbzrs = Double.parseDouble(qylgzbzrsMap.get("rs"));
						 ws.addCell(new Label(37,6+i,df.format(qylgzbzrs/sygzzrs*100)+"%",dygdsys));
						//签约率-高职-女生总人数-%
						 double qylgzgzrs = Double.parseDouble(qylgzgzrsMap.get("rs"));
						 ws.addCell(new Label(38,6+i,df.format(qylgzgzrs/sygzzrs*100)+"%",dygdsys));
						//签约率-高职-总人数-%
						 double qylgzzrs = Double.parseDouble(qylgzzrsMap.get("rs"));
						 ws.addCell(new Label(39,6+i,df.format(qylgzzrs/sygzzrs*100)+"%",dygdsys));
					 }
					 ws.addCell(new Label(40,6+i,Integer.parseInt(qylgzzrsMap.get("rs"))+Integer.parseInt(qylbkzrsMap.get("rs"))+"",dygdsys));
					 qylgjrs=qylgjrs+Integer.parseInt(qylgzzrsMap.get("rs"))+Integer.parseInt(qylbkzrsMap.get("rs"));
					 double qylzrs = Double.parseDouble(qylgzzrsMap.get("rs"))+Double.parseDouble(qylbkzrsMap.get("rs"));
					 ws.addCell(new Label(41,6+i,df.format(qylzrs/syzrs*100)+"%",dygdsys));
					 ws.addCell(new Label(42,6+i,"",dygys));
				 }
				 int row =jyzyList.size()+6;
				 ws.mergeCells(0, row,1, row);
				 ws.addCell(new Label(0,row,"总计",btdsys));
				 //总计开始
				 ws.addCell(new Label(2,row,sybrs+"",dygdsys));//生源男生人数
				 ws.addCell(new Label(3,row,sygrs+"",dygdsys));//生源女生人数
				 ws.addCell(new Label(4,row,syrs+"",dygdsys));//生源人数
				 ws.addCell(new Label(5,row,"0",dygdsys));
				 ws.addCell(new Label(6,row,"0",dygdsys));
				 ws.addCell(new Label(7,row,"0",dygdsys));
				 ws.addCell(new Label(8,row,sybkbrs+"",dygdsys));
				 ws.addCell(new Label(9,row,sybkgrs+"",dygdsys));
				 ws.addCell(new Label(10,row,sybkrs+"",dygdsys));//生源本科人数
				 ws.addCell(new Label(11,row,sygzbrs+"",dygdsys));
				 ws.addCell(new Label(12,row,sygzgrs+"",dygdsys));
				 ws.addCell(new Label(13,row,sygzrs+"",dygdsys));//生源高职人数
				 ws.addCell(new Label(14,row,"0",dygdsys));
				 ws.addCell(new Label(15,row,"",dygdsys));
				 ws.addCell(new Label(16,row,jylbkrs+"",dygdsys));//就业意向率本科人数
				 double dsybkrs = sybkrs;//生源本科人数
				 double djylbkrs = jylbkrs;//就业意向率本科人数
				 ws.addCell(new Label(17,row,df.format(djylbkrs/dsybkrs*100)+"%",dygdsys));//就业意向率本科
				 ws.addCell(new Label(18,row,jylgzrs+"",dygdsys));//就业意向率高职人数
				 double dsygzrs = sygzrs;//生源高职人数
				 double djylgzrs = jylgzrs;//就业意向率高职人数
				 ws.addCell(new Label(19,row,df.format(djylgzrs/dsygzrs*100)+"%",dygdsys));//就业意向率高职
				 ws.addCell(new Label(20,row,jylrs+"",dygdsys));//就业意向率人数
				 double dsyrs = syrs;//生源人数
				 double djylrs = jylrs;//就业意向率人数
				 ws.addCell(new Label(21,row,df.format(djylrs/dsyrs*100)+"%",dygdsys));//就业意向率
				 ws.addCell(new Label(22,row,"0",dygdsys));//签约率-研
				 ws.addCell(new Label(23,row,"0",dygdsys));
				 ws.addCell(new Label(24,row,"0",dygdsys));
				 ws.addCell(new Label(25,row,"",dygdsys));
				 ws.addCell(new Label(26,row,"",dygdsys));
				 ws.addCell(new Label(27,row,"",dygdsys));
				 
				 ws.addCell(new Label(28,row,qylbkbrs+"",dygdsys));//签约率本科男生人数
				 ws.addCell(new Label(29,row,qylbkgrs+"",dygdsys));//签约率本科女生人数
				 ws.addCell(new Label(30,row,qylbkrs+"",dygdsys));//签约率本科人数
				 double dqylbkbrs = qylbkbrs;
				 ws.addCell(new Label(31,row,df.format(dqylbkbrs/dsybkrs*100)+"%",dygdsys));//签约率本科男生
				 double dqylbkgrs = qylbkgrs;
				 ws.addCell(new Label(32,row,df.format(dqylbkgrs/dsybkrs*100)+"%",dygdsys));//签约率本科女生
				 double dqylbkrs = qylbkrs;
				 ws.addCell(new Label(33,row,df.format(dqylbkrs/dsybkrs*100)+"%",dygdsys));//签约率本科
				 ws.addCell(new Label(34,row,qylgzbrs+"",dygdsys));//签约率高职男生人数
				 ws.addCell(new Label(35,row,qylgzgrs+"",dygdsys));//签约率高职女生人数
				 ws.addCell(new Label(36,row,qylgzrs+"",dygdsys));//签约率合计人数
				 double dqylgzbrs = qylgzbrs ;
				 ws.addCell(new Label(37,row,df.format(dqylgzbrs/dsygzrs*100)+"%",dygdsys));//签约率高职男生
				 double dqylgzgrs = qylgzgrs;
				 ws.addCell(new Label(38,row,df.format(dqylgzgrs/dsygzrs*100)+"%",dygdsys));//签约率高职女生
				 double dqylgzrs = qylgzrs;
				 ws.addCell(new Label(39,row,df.format(dqylgzrs/dsygzrs*100)+"%",dygdsys));//签约率高职人数
				 ws.addCell(new Label(40,row,qylgjrs+"",dygdsys));//签约率合计人数
				 double dqylgjrs = qylgjrs;
				 ws.addCell(new Label(41,row,df.format(dqylgjrs/dsyrs*100)+"%",dygdsys));
				 ws.addCell(new Label(42,row,"",dygys));
				 //总计结束
				 ws.mergeCells(0, row+1,42, row+1);
				 ws.addCell(new Label(0,row+1,"　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　填表人：",dygjcys));
				 ws.mergeCells(0, row+2,42, row+2);
				 ws.addCell(new Label(0,row+2,"备注：1.就业意向率包括正在试工等待签约、出国、升学、自主创业等",dygbtys3));
				 //ws.mergeCells(0, row+3,42, row+3);
				 //ws.addCell(new Label(0,row+3,"      2.按签约率从高到低排序",dygbtys3));
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		
	}
	
	
	/**
	 * 就业率统计
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void printJyltj(OutputStream os, String[] nj) throws SQLException {
		List<HashMap<String, String>> xybj = dao.getXyBj(nj);
		HashMap<String, String> jyltj = dao.getJylHj(nj);//就业率合计
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("就业率统计", 0);
		
		try {
			
			excel.printTitle(ws, Base.xxmc + Arrays.toString(nj).replace("[", "").replace("]", "").replaceAll(",", "、") + "届毕业生初次就业率统计表", 23, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			for (int i = 0 ; i < 7 ; i ++) {
				ws.mergeCells(i, 2, i, 3);// int 列起，int 行起，int 列结束，int 行结束
			}
			
			for (int i =12 ; i < 18 ; i ++) {
				ws.mergeCells(i, 2, i, 3);
			}
			
			ws.mergeCells(20, 2, 20, 3);
			ws.mergeCells(21, 2, 21, 3);
			ws.mergeCells(22, 2, 22, 3);
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			ws.addCell(new Label(0, 2, "学历", wcfTytle));
			ws.addCell(new Label(1, 2, "二级"+Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(2, 2, "专业", wcfTytle));
			ws.addCell(new Label(3, 2, "班级", wcfTytle));
			ws.addCell(new Label(4, 2, "毕业生数", wcfTytle));
			ws.addCell(new Label(5, 2, "签约总数", wcfTytle));
			
			//签约率、灵活就业率列的style
			WritableCellFormat format =  ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			format.setBackground(Colour.AQUA);
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//就业标志列的style
			WritableCellFormat bzColor =  ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			bzColor.setBackground(Colour.YELLOW);
			bzColor.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//	就业率的style
			WritableCellFormat jyColor =  ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			jyColor.setBackground(Colour.ICE_BLUE);
			jyColor.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			/*
			 * 表头
			 */
			ws.addCell(new Label(6, 2, "签约率", format));
			ws.mergeCells(7, 2, 11, 2);//合并其中部分
			ws.addCell(new Label(7, 2, "其中", wcfTytle));
			ws.addCell(new Label(7, 3, "签协议", wcfTytle));
			ws.addCell(new Label(8, 3, "出国", wcfTytle));
			ws.addCell(new Label(9, 3, "升学", wcfTytle));
			ws.addCell(new Label(10, 3, "公务员", wcfTytle));
			ws.addCell(new Label(11, 3, "西部", wcfTytle));
			ws.addCell(new Label(12, 2, "灵活就业", wcfTytle));
			ws.addCell(new Label(13, 2, "灵活就业率", format));
			ws.addCell(new Label(14, 2, "应聘数", wcfTytle));
			ws.addCell(new Label(15, 2, "应聘率", format));
			ws.addCell(new Label(16, 2, "就业率", jyColor));
			ws.addCell(new Label(17, 2, "未就业数", wcfTytle));
			ws.mergeCells(18, 2, 19, 2);//合并其中部分
			ws.addCell(new Label(18, 2, "其中", wcfTytle));
			ws.addCell(new Label(18, 3, "待就业", wcfTytle));
			ws.addCell(new Label(19, 3, "暂不就业", wcfTytle));
			ws.addCell(new Label(20, 2, "专业签约数", wcfTytle));
			ws.addCell(new Label(21, 2, "专业签约率", wcfTytle));
			ws.addCell(new Label(22, 2, "待就业率", format));
			
			int row = 4;
			
			for ( int i = 0 ; i < xybj.size() ; i ++) {
				String xymc = xybj.get(i).get("xymc");
				String xydm = xybj.get(i).get("xydm");
				List<HashMap<String, String>> jyl = dao.getJyl(xydm,nj);
				int bjs = Integer.valueOf(xybj.get(i).get("bjs"));
				/*
				 * 小计部分数据
				 */	
				int bysrs = 0;
				int qyrs = 0;
				int qxjrs = 0;
				int cgrs = 0;
				int sxrs = 0;
				int jgrs = 0;
				int xbrs = 0;
				int lhjyrs = 0;
				int yps = 0 ;
				int wjys = 0;
				int djys = 0;
				int zbjys = 0;
				
				for ( int j = 0 ; j < bjs ; j++) {
					bysrs+=Integer.valueOf(jyl.get(j).get("bysrs"));
					qyrs+=Integer.valueOf(jyl.get(j).get("qys"));
					qxjrs+=Integer.valueOf(jyl.get(j).get("qxjrs"));
					cgrs+=Integer.valueOf(jyl.get(j).get("cgrs"));
					sxrs+=Integer.valueOf(jyl.get(j).get("sxrs"));
					jgrs+=Integer.valueOf(jyl.get(j).get("jgrs"));
					xbrs+=Integer.valueOf(jyl.get(j).get("xbrs"));
					lhjyrs+=Integer.valueOf(jyl.get(j).get("lhjys"));
					yps+=Integer.valueOf(jyl.get(j).get("yps"));
					wjys+=Integer.valueOf(jyl.get(j).get("wjys"));
					djys+=Integer.valueOf(jyl.get(j).get("djys"));
					zbjys+=Integer.valueOf(jyl.get(j).get("zbjys"));
					
					ws.addCell(new Label(1, row+j, xymc, wcfTytle));
					ws.addCell(new Label(2, row+j, jyl.get(j).get("zymc"), wcfTytle));
					ws.addCell(new Label(3, row+j, jyl.get(j).get("bjmc"), wcfTytle));
					ws.addCell(new Label(4, row+j, jyl.get(j).get("bysrs"), wcfTytle));
					ws.addCell(new Label(5, row+j, jyl.get(j).get("qys"), wcfTytle));
					ws.addCell(new Label(6, row+j, jyl.get(j).get("qyl"), format));
					ws.addCell(new Label(7, row+j, jyl.get(j).get("qxjrs"), bzColor));
					ws.addCell(new Label(8, row+j, jyl.get(j).get("cgrs"), bzColor));
					ws.addCell(new Label(9, row+j, jyl.get(j).get("sxrs"), bzColor));
					ws.addCell(new Label(10, row+j, jyl.get(j).get("jgrs"), bzColor));
					ws.addCell(new Label(11, row+j, jyl.get(j).get("xbrs"), bzColor));
					ws.addCell(new Label(12, row+j, jyl.get(j).get("lhjys"), wcfTytle));
					ws.addCell(new Label(13, row+j, jyl.get(j).get("lhjyl"), format));
					ws.addCell(new Label(14, row+j, jyl.get(j).get("yps"), bzColor));
					ws.addCell(new Label(15, row+j, jyl.get(j).get("ypl"), format));
					ws.addCell(new Label(16, row+j, jyl.get(j).get("jyl"), jyColor));
					ws.addCell(new Label(17, row+j, jyl.get(j).get("wjys"), wcfTytle));
					ws.addCell(new Label(18, row+j, jyl.get(j).get("djys"), wcfTytle));
					ws.addCell(new Label(19, row+j, jyl.get(j).get("zbjys"), bzColor));
					
					ws.addCell(new Label(22, row+j, jyl.get(j).get("djyl"), format));
				}
				
				ws.addCell(new Label(1, row+bjs, xymc, wcfTytle));
				
				/*
				 * 小计部分输出
				 */
				ws.mergeCells(2, row+bjs, 3, row+bjs);
				ws.addCell(new Label(2, row+bjs, "小计", wcfTytle));
				ws.addCell(new Label(4, row+bjs, bysrs+"", wcfTytle));
				ws.addCell(new Label(5, row+bjs, qyrs+"", wcfTytle));
				ws.addCell(new Label(7, row+bjs, qxjrs+"", bzColor));
				ws.addCell(new Label(8, row+bjs, cgrs+"", bzColor));
				ws.addCell(new Label(9, row+bjs, sxrs+"", bzColor));
				ws.addCell(new Label(10, row+bjs, jgrs+"", bzColor));
				ws.addCell(new Label(11, row+bjs, xbrs+"", bzColor));
				ws.addCell(new Label(12, row+bjs, lhjyrs+"", wcfTytle));
				ws.addCell(new Label(14, row+bjs, yps+"", bzColor));
				ws.addCell(new Label(17, row+bjs, wjys+"", wcfTytle));
				ws.addCell(new Label(18, row+bjs, djys+"", wcfTytle));
				ws.addCell(new Label(19, row+bjs, zbjys+"", bzColor));
				
				/*
				 * 小计部分统计
				 */
				if (bysrs==0) {
					ws.addCell(new Label(6, row+bjs, "0.00%", format));
					ws.addCell(new Label(13, row+bjs, "0.00%", format));
					ws.addCell(new Label(15, row+bjs, "0.00%", format));
					ws.addCell(new Label(16, row+bjs, "0.00%", jyColor));
				} else {
					ws.addCell(new Label(6, row+bjs, Math.round((Float.valueOf(qyrs)/Float.valueOf(bysrs))*100)+"%", format));
					ws.addCell(new Label(13, row+bjs, Math.round((Float.valueOf(lhjyrs)/Float.valueOf(bysrs))*100)+"%", format));
					ws.addCell(new Label(15, row+bjs, Math.round((Float.valueOf(yps)/Float.valueOf(bysrs))*100)+"%", format));
					ws.addCell(new Label(16, row+bjs, Math.round((Float.valueOf(qyrs+yps+lhjyrs)/Float.valueOf(bysrs))*100)+"%", jyColor));
				}
				
				
				row += bjs+1;
			}
			
			this.mergeCellsByXy(ws, row, 1, 4,format);
			this.mergeCellsByZy(ws, row, 2, 4);
			
			ws.mergeCells(0,4,0, row);
			ws.addCell(new Label(0,4,"本科",wcfTytle));
			/*
			 * 合计部分输出
			 */
			ws.mergeCells(1,row, 3, row);
			ws.addCell(new Label(1,row,"合计",wcfTytle));
			ws.addCell(new Label(4,row,jyltj.get("bysrs"),wcfTytle));
			ws.addCell(new Label(5,row,jyltj.get("qys"),wcfTytle));
			ws.addCell(new Label(6,row,jyltj.get("qyl"),format));
			ws.addCell(new Label(7,row,jyltj.get("qxjrs"),bzColor));
			ws.addCell(new Label(8,row,jyltj.get("sxrs"),bzColor));
			ws.addCell(new Label(9,row,jyltj.get("cgrs"),bzColor));
			ws.addCell(new Label(10,row,jyltj.get("jgrs"),bzColor));
			ws.addCell(new Label(11,row,jyltj.get("xbrs"),bzColor));
			ws.addCell(new Label(12,row,jyltj.get("lhjys"),wcfTytle));
			ws.addCell(new Label(13,row,jyltj.get("lhjyl"),format));
			ws.addCell(new Label(14,row,jyltj.get("yps"),bzColor));
			ws.addCell(new Label(15,row,jyltj.get("ypl"),format));
			ws.addCell(new Label(16,row,jyltj.get("jyl"),jyColor));
			ws.addCell(new Label(17,row,jyltj.get("wjys"),wcfTytle));
			ws.addCell(new Label(18,row,jyltj.get("djys"),wcfTytle));
			ws.addCell(new Label(19,row,jyltj.get("zbjys"),bzColor));
			ws.addCell(new Label(20,row,jyltj.get("qys"),wcfTytle));
			ws.addCell(new Label(21,row,jyltj.get("qyl"),wcfTytle));
			ws.addCell(new Label(22,row,jyltj.get("djyl"),format));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 学院合并单元格
	 * @param ws
	 * @param size
	 * @param col
	 * @param row
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void mergeCellsByXy(WritableSheet ws, int size, int col, int row,WritableCellFormat format)
			throws RowsExceededException, WriteException {

		int count = 1;
		boolean isHb = false;
		int bysrs = 0;
		int djyrs = 0;

		for (int i = 0; i < size; i++) {
			WritableCell currCell = ws.getWritableCell(col, row + i);// 当前单元格
			WritableCell beforeCell = ws.getWritableCell(col, row + i - 1);// 上一单元格
			String currCellStr = currCell.getContents();
			String beforeCellStr = beforeCell.getContents();

			String content = ws.getWritableCell(18, row + i).getContents();
			String byscontent = ws.getWritableCell(4, row + i).getContents();

			if (!Base.isNull(content)) {
				djyrs += Integer.valueOf(content);
				bysrs += Integer.valueOf(byscontent);
			}

			if (currCellStr.equals(beforeCellStr)) {
				count++;
				isHb = true;
			}

			// 判断是否该合并
			if ((!currCellStr.equals(beforeCellStr)) && isHb) {
				ws.mergeCells(col, row + i - count, col, row + i - 1);

				if (bysrs == 0 || djyrs == 0) {
					ws.addCell(new Label(22, row + i - count, "0.00%",format));
				} else {
					ws.addCell(new Label(22, row + i - count, Math.round((Float
							.valueOf(djyrs) / Float.valueOf(bysrs)) * 100)
							+ "%",format));
				}

				ws.mergeCells(22, row + i - count, 22, row + i - 1);

				// 单元格合并，重置参数
				count = 1;
				isHb = false;
				djyrs = 0;
				bysrs = 0;
			}
		}
	}
	
	
	/**
	 * 专业合并独有
	 * @param ws
	 * @param size
	 * @param col
	 * @param row
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void mergeCellsByZy(WritableSheet ws, int size, int col, int row)
			throws RowsExceededException, WriteException {
		
		int count = 1;
		boolean isHb = false;
		int zyqys = 0;
		int bysrs = 0;
		int hjbys = 0;
		int hjqys = 0;
		
		for ( int i = 0 ; i < size ; i++) {
			WritableCell currCell = ws.getWritableCell(col, row+i );//当前单元格
			WritableCell beforeCell = ws.getWritableCell(col, row+i - 1);//上一单元格
			String currCellStr = currCell.getContents();
			String beforeCellStr = beforeCell.getContents();
			
			String content = ws.getWritableCell(col+3, row+i ).getContents();
			String byscontent = ws.getWritableCell(col+2, row+i ).getContents();
			
			if (!Base.isNull(content)) {
				zyqys += Integer.valueOf(content);
				bysrs += Integer.valueOf(byscontent);
			}
		
			
			if (currCellStr.equals(beforeCellStr)) {
				count++;
				isHb = true;
			}
			
			//判断是否该合并
			if ((!currCellStr.equals(beforeCellStr)) && isHb) {
				ws.mergeCells(col, row + i - count, col, row + i - 1);
				
				ws.addCell(new Label(20,row + i - count,zyqys+""));
				ws.mergeCells(20, row + i - count, 20, row + i - 1);
				
				if (bysrs == 0 || zyqys == 0) {
					ws.addCell(new Label(21,row + i - count,"0.00%"));
				} else {
					int zyjyl = Math.round((Float.valueOf(zyqys)/Float.valueOf(bysrs))*100);	
					ws.addCell(new Label(21,row + i - count,zyjyl+"%"));
				}
				ws.mergeCells(21, row + i - count, 21, row + i - 1);
				// 单元格合并，重置参数
				count = 1;
				isHb = false;
				
				hjqys+=zyqys;
				hjbys+=bysrs;
				
				zyqys=0;
				bysrs=0;
			}
		}
		ws.addCell(new Label(20,size-1,hjqys+""));
		ws.addCell(new Label(21,size-1,Math.round((Float.valueOf(hjqys)/Float.valueOf(hjbys))*100)+"%"));
	}
	
	
	
	/**
	 * 获取开关状态
	 * @return
	 */
	protected String getKgzt() {
		
		if (Base.isNull(dao.getKgzt())) {
			return "close";
		} else {
			return "open";
		}
	}
	
	
	/**
	 * 地大就业率统计
	 * @param wwb
	 * @param nj
	 * @throws SQLException
	 */
	protected void zgddPrintJyltj(OutputStream os, String nj) throws SQLException {
		List<HashMap<String, String>> xybj = dao.getXyBj(nj);
		HashMap<String, String> jylhj = dao.getZgddJylhj(nj);//就业率合计
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("就业率统计", 0);
		
		try {
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(18);
			wcfTytle.setFont(wfTytle);

			// int 列起，int 行起，跨int 列，int 行结束
			ws.mergeCells(0, 0, 12, 0);
			ws.addCell(new Label(0, 0, Base.xxmc + nj + "届毕业生就业率统计表", wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			//设置单元格上下居中
			wcfTytle.setVerticalAlignment(VerticalAlignment.CENTRE);

			for (int i = 0 ; i < 13 ; i ++) {
				ws.mergeCells(i, 2, i, 3);// int 列起，int 行起，int 列结束，int 行结束
			}
			
			/*
			 * 表头
			 */
			ws.addCell(new Label(0, 2, Base.YXPZXY_KEY, wcfTytle));
			ws.addCell(new Label(1, 2, "专业", wcfTytle));
			ws.addCell(new Label(2, 2, "班级", wcfTytle));
			ws.addCell(new Label(3, 2, "毕业生总数", wcfTytle));
			ws.addCell(new Label(4, 2, "签约", wcfTytle));
			ws.addCell(new Label(5, 2, "升学", wcfTytle));
			ws.addCell(new Label(6, 2, "出国", wcfTytle));
			ws.addCell(new Label(7, 2, "自由职业", wcfTytle));
			ws.addCell(new Label(8, 2, "自主创业", wcfTytle));
			ws.addCell(new Label(9, 2, "待就业", wcfTytle));
			ws.addCell(new Label(10, 2, "签约率", wcfTytle));
			ws.addCell(new Label(11, 2, "升学率", wcfTytle));
			ws.addCell(new Label(12, 2, "就业率", wcfTytle));
			
			int row = 4;
			/*
			 * 输出院系、专业、班级
			 */
			for ( int i = 0 ; i < xybj.size() ; i ++) {
				String xymc = xybj.get(i).get("xymc");
				int bjs = Integer.valueOf(xybj.get(i).get("bjs"));
				List<HashMap<String, String>> jyl = dao.getZgddJyl(xybj.get(i).get("xydm"),nj);
				
				int bysrs = 0;//小计－毕业生人数
				int qyzs = 0;//小计－签约总数
				int sxrs = 0;//小计－升学人数
				int cgrs = 0;//小计－出国人数
				int zyzy = 0;//小计－自由职业人数
				int zzcy = 0;//小计－自主创业人数
				int djys = 0;//小计－待就业人数
				
				for ( int j = 0 ; j < bjs ; j++) {
					
					bysrs+=Integer.valueOf(jyl.get(j).get("bysrs"));
					qyzs+=Integer.valueOf(jyl.get(j).get("qyzs"));
					sxrs+=Integer.valueOf(jyl.get(j).get("sxrs"));
					cgrs+=Integer.valueOf(jyl.get(j).get("cgrs"));
					zyzy+=Integer.valueOf(jyl.get(j).get("zyzy"));
					zzcy+=Integer.valueOf(jyl.get(j).get("zzcy"));
					djys+=Integer.valueOf(jyl.get(j).get("djys"));
					
					ws.addCell(new Label(0, row+j, xymc, wcfTytle));
					ws.addCell(new Label(1, row+j, jyl.get(j).get("zymc"), wcfTytle));
					ws.addCell(new Label(2, row+j, jyl.get(j).get("bjmc"), wcfTytle));
					ws.addCell(new Label(3, row+j, jyl.get(j).get("bysrs"), wcfTytle));
					ws.addCell(new Label(4, row+j, jyl.get(j).get("qyzs"), wcfTytle));
					ws.addCell(new Label(5, row+j, jyl.get(j).get("sxrs"), wcfTytle));
					ws.addCell(new Label(6, row+j, jyl.get(j).get("cgrs"), wcfTytle));
					ws.addCell(new Label(7, row+j, jyl.get(j).get("zyzy"), wcfTytle));
					ws.addCell(new Label(8, row+j, jyl.get(j).get("zzcy"), wcfTytle));
					ws.addCell(new Label(9, row+j, jyl.get(j).get("djys"), wcfTytle));
					ws.addCell(new Label(10, row+j,jyl.get(j).get("qyl"), wcfTytle));
					ws.addCell(new Label(11, row+j,jyl.get(j).get("sxl"), wcfTytle));
					ws.addCell(new Label(12, row+j,jyl.get(j).get("jyl"), wcfTytle));
					
				}
				
				/*
				 * 小计行
				 */
				ws.addCell(new Label(1, row+bjs, xymc, wcfTytle));
				ws.mergeCells(0, row+bjs, 2, row+bjs);
				ws.addCell(new Label(0, row+bjs, "小计", wcfTytle));
				ws.addCell(new Label(3, row+bjs, String.valueOf(bysrs), wcfTytle));
				ws.addCell(new Label(4, row+bjs, String.valueOf(qyzs), wcfTytle));
				ws.addCell(new Label(5, row+bjs, String.valueOf(sxrs), wcfTytle));
				ws.addCell(new Label(6, row+bjs, String.valueOf(cgrs), wcfTytle));
				ws.addCell(new Label(7, row+bjs, String.valueOf(zyzy), wcfTytle));
				ws.addCell(new Label(8, row+bjs, String.valueOf(zzcy), wcfTytle));
				ws.addCell(new Label(9, row+bjs, String.valueOf(djys), wcfTytle));
				
				/*
				 * 签约率、升学率、就业率
				 * 毕业生总数为0： 则全部为0.00％
				 * 否则：
				 * 签约率＝签约总数/毕业生总数
				 * 升学率＝升学总数/毕业生总数
				 * 就业率＝非待就业总数/毕业生总数
				 */
				if (bysrs==0) {
					ws.addCell(new Label(10, row+bjs,"0.00%", wcfTytle));
					ws.addCell(new Label(11, row+bjs,"0.00%", wcfTytle));
					ws.addCell(new Label(12, row+bjs,"0.00%", wcfTytle));
				} else {
					ws.addCell(new Label(10, row+bjs, Math.round((Float.valueOf(qyzs)/Float.valueOf(bysrs))*100)+"%", wcfTytle));
					ws.addCell(new Label(11, row+bjs, Math.round((Float.valueOf(sxrs)/Float.valueOf(bysrs))*100)+"%", wcfTytle));
					ws.addCell(new Label(12, row+bjs, Math.round((Float.valueOf(qyzs-djys)/Float.valueOf(bysrs))*100)+"%", wcfTytle));
				}
				
				row += bjs+1;
			}
			
			ExcelMB.mergeCells(ws, row, 0, 4);//合并学院
			ExcelMB.mergeCells(ws, row, 1, 4);//合并专业
			
			/*
			 * 合计部分输出
			 */
			ws.mergeCells(0,row, 2, row);
			ws.addCell(new Label(0,row,"合计",wcfTytle));
			ws.addCell(new Label(3,row,jylhj.get("bysrs"),wcfTytle));
			ws.addCell(new Label(4,row,jylhj.get("qyzs"),wcfTytle));
			ws.addCell(new Label(5,row,jylhj.get("sxrs"),wcfTytle));
			ws.addCell(new Label(6,row,jylhj.get("cgrs"),wcfTytle));
			ws.addCell(new Label(7,row,jylhj.get("zyzy"),wcfTytle));
			ws.addCell(new Label(8,row,jylhj.get("zzcy"),wcfTytle));
			ws.addCell(new Label(9,row,jylhj.get("djys"),wcfTytle));
			ws.addCell(new Label(10,row,jylhj.get("qyl"),wcfTytle));
			ws.addCell(new Label(11,row,jylhj.get("sxl"),wcfTytle));
			ws.addCell(new Label(12,row,jylhj.get("jyl"),wcfTytle));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 年份列表 
	 * @return
	 */
	protected List<HashMap<String, String>> getNfList() {
		int year = Integer.parseInt(dao.getNow().substring(0, 4));
		String[] years = new String[10];
		
		int n = 0 ;
		for(int i = year-5; i<year+5 ;i++) {
			years[n++] = String.valueOf(i);
		}
		return DAO.getInstance().arrayToList(years, years);
	}
	
	
	/**
	 * 获取审核查询中要自定义的字段
	 * @param yhlx
	 * @param shjb
	 * @return String
	 * */
	protected void getCustomAudiColumn(HttpServletRequest request, String yhlx){
		StringBuilder sb = new StringBuilder();
		
		if (yhlx.equalsIgnoreCase("xy")) {
			sb.append(" (case when xxsh='通过' then 'disabled' else '' end) disabled,");
			sb.append(" (case when xysh='通过' then 'tgsh' when xysh='不通过' then 'wtg' else '' end) bgcolor, ");
		}else if (yhlx.equalsIgnoreCase("fdy")){
			sb.append(" (case when xysh='通过' then 'disabled' else '' end) disabled, ");
			sb.append(" (case when fdysh='通过' then 'tgsh' when fdysh='不通过' then 'wtg' else '' end) bgcolor, ");
		}else if (Base.isNull(yhlx)) {
			sb.append(" (case when shzt='通过' then 'disabled' else '' end) disabled, ");
			sb.append(" (case when shzt='通过' then 'tgsh' when shzt='不通过' then 'wtg' else '' end) bgcolor, ");
		}else if ("stu".equals(yhlx)){
			sb.append(" (case when xysh='通过' then 'disabled' else '' end) disabled,");
			sb.append(" (case when xysh='通过' then 'tgsh' when xysh='不通过' then 'wtg' else '' end) bgcolor, ");
		}else{
			sb.append(" '' disabled, ");
			sb.append(" (case when xxsh='通过' then 'tgsh' when xxsh='不通过' then 'wtg' else '' end) bgcolor, ");
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}


	/**
	 * 就业管理disabled 
	 * @param request
	 * @param flg
	 */
	protected void setCustomAudiColumn(HttpServletRequest request, String flg){
		StringBuilder sb = new StringBuilder();
		String userType = (String) request.getSession().getAttribute("userType");
	
		if ("shzt".equals(flg)) {
			sb.append(" (case when shzt='通过' or shzt='不通过' then 'disabled' when shzt='退回' then 'th' else '' end) disabled, ");
		} else if ("xysh".equals(flg)) {
			sb.append(" (case when xxsh='通过' or xxsh='不通过' then 'disabled' else '' end) disabled, ");
			sb.append(" (case when xysh='通过' or xysh='不通过' then 'disabled'  when xysh='退回' then 'th' else '' end) isdis, ");
		} else if ("xxsh".equals(flg) || "adminsh".equals(flg) || "stush".equals(flg)) {
			sb.append("'' disabled,");
			sb.append(" (case when xxsh='通过' or xxsh='不通过' then 'disabled' when xxsh='退回' then 'th' else '' end) isdis, ");
		} else if ("jgcx".equals(flg)) {
			sb.append(" (case when xysh='通过' or xysh='不通过' then 'disabled' when xysh='退回' then 'th' else '' end) disabled, ");
		} else if ("byssh".equals(flg)){
		
			if ("xy".equalsIgnoreCase(userType)){
				sb.append("(case when shzt='通过' or shzt='不通过' then 'disabled' else '' end) disabled,");
			}  else {
				sb.append("'' disabled,");
			}
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}
	
	
	
	/**
	 * 福建工程--学院就业率统计表
	 * @param xydm
	 * @param os
	 */
	protected void printJylForXy(JyglForm model,OutputStream os) {
		
		String xydm = model.getXydm();
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("年分院就业情况汇总统计表");
		
		List<String[]> bksData = dao.getJylForXy("本科",xydm, nf);//本科生数据
		List<String[]> zksData = dao.getJylForXy("专科",xydm, nf);//专科生数据
		List<String[]> xjData = dao.getJylForXy(xydm, nf);//小计数据
		List<String[]> bzxjData = dao.getJylTjForXyByXl(xydm, nf);//本、专科小计
		String[] jylZj = dao.getJylTjForXy(xydm, nf);//总计
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("分院就业情况汇总统计表", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "截止日期：XXXX年X月X日", wcfTytle));
			
			//表头单元格合并
			ws.mergeCells(0, 2, 0, 3);//序号
			ws.mergeCells(1, 2, 1, 3);//系别
			ws.mergeCells(2, 2, 2, 3);//学历
			ws.mergeCells(3, 2, 3, 3);//毕业生人数
			
			ws.mergeCells(4, 2, 12, 2);//就业情况合并
			ws.mergeCells(13, 2, 14, 2);//签约情况合并
			ws.mergeCells(15, 2, 16, 2);//待就业情况合并
			ws.mergeCells(17, 2, 18, 2);//解约情况合并
			ws.mergeCells(19, 2, 19, 3);//离校待考
			
			
			//表头--第一行
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "系别", wcfTytle));
			ws.addCell(new Label(2, 2, "学历", wcfTytle));
			ws.addCell(new Label(3, 2, "毕业生人数", wcfTytle));
			ws.addCell(new Label(4, 2, "就业情况", wcfTytle));
			ws.addCell(new Label(13, 2, "签约情况", wcfTytle));
			ws.addCell(new Label(15, 2, "待就业情况", wcfTytle));
			ws.addCell(new Label(17, 2, "解约情况", wcfTytle));
			
			//表头--第二行
			ws.addCell(new Label(4, 3, "已签约", wcfTytle));
			ws.addCell(new Label(5, 3, "出国", wcfTytle));
			ws.addCell(new Label(6, 3, "升学", wcfTytle));
			ws.addCell(new Label(7, 3, "意向就业", wcfTytle));
			ws.addCell(new Label(8, 3, "灵活就业", wcfTytle));
			ws.addCell(new Label(9, 3, "有接收函", wcfTytle));
			ws.addCell(new Label(10, 3, "地方项目", wcfTytle));
			ws.addCell(new Label(11, 3, "就业总数", wcfTytle));
			ws.addCell(new Label(12, 3, "就业率", wcfTytle));
			ws.addCell(new Label(13, 3, "签约总数（含升学、地项目）", wcfTytle));
			ws.addCell(new Label(14, 3, "签约率", wcfTytle));
			ws.addCell(new Label(15, 3, "人数", wcfTytle));
			ws.addCell(new Label(16, 3, "待就业率", wcfTytle));
			ws.addCell(new Label(17, 3, "人数", wcfTytle));
			ws.addCell(new Label(18, 3, "比例", wcfTytle));
			ws.addCell(new Label(19, 2, "离校待考", wcfTytle));
			
			for (int i = 0 ; i < bksData.size(); i++) {
				int n = i==0 ? 0 : 2;
				
				for (int j = 0 ; j < bksData.get(i).length ; j++) {
					ws.addCell(new Label(j, i+4+n*i, bksData.get(i)[j], wcfTytle));
					ws.addCell(new Label(j, i+5+n*i, zksData.get(i)[j], wcfTytle));
					ws.addCell(new Label(j, i+6+n*i, xjData.get(i)[j], wcfTytle));
				}
			}
			
			ExcelMB.mergeCells(ws, bksData.size()*3+4, 0, 4);//序号合并
			ExcelMB.mergeCells(ws, bksData.size()*3+4, 1, 4);//学院名称合并
			ws.mergeCells(0, bksData.size()*3+4, 1, bksData.size()*3+5);//小计合并
			ws.mergeCells(0, bksData.size()*3+6, 2, bksData.size()*3+6);//小计合并
			
			ws.addCell(new Label(0, bksData.size()*3+4, "小计", wcfTytle));
			ws.addCell(new Label(0, bksData.size()*3+6, "总计", wcfTytle));
			//本、专科小计
			for (int i = 0 ; i<bzxjData.size() ; i++) {
				for (int j = 0 ; j < bzxjData.get(i).length ; j++) {
					ws.addCell(new Label(j+2, bksData.size()*3+4+i, bzxjData.get(i)[j], wcfTytle));
				}
			}
			
			//总计部分数据
			for (int i = 0 ; i < jylZj.length ; i++) {
				ws.addCell(new Label(3+i, bksData.size()*3+6, jylZj[i], wcfTytle));
			}
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.mergeCells(0,  bksData.size()*3+7, 19, bksData.size()*3+9);
			ws.addCell(new Label(0, bksData.size()*3+7, model.getXmbz(), wcfTytle));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 福建工程--专业就业率统计表
	 * @param xydm
	 * @param os
	 */
	protected void printJylForZy(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("年专业就业情况汇总统计表");
		
		List<String[]> xbbData = dao.getJylForZy("xbb","",model);//校本部数据
		List<String[]> rjxyData = dao.getJylForZy("rjxy","",model);//软件学院数据
		List<String[]> xbbXj = dao.getJylForZy(model,"xbb","");//校本部小计数据
		List<String[]> rjxyXj = dao.getJylForZy(model,"rjxy","");//校本部小计数据
		
		String[] xbbZjData = dao.getJylTjForZy(model,"xbb","");
		String[] zjData = dao.getJylTjForZy(model,"","");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("专业就业情况汇总统计表", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "截止日期：XXXX年X月X日", wcfTytle));
			
			//表头单元格合并
			ws.mergeCells(0, 2, 0, 3);//系别
			ws.mergeCells(1, 2, 1, 3);//专业
			ws.mergeCells(2, 2, 2, 3);//学历
			ws.mergeCells(3, 2, 3, 3);//专业人数
			ws.mergeCells(19, 2, 19, 3);//离校待考
			
			ws.mergeCells(4, 2, 12, 2);//就业情况合并
			ws.mergeCells(13, 2, 14, 2);//签约情况合并
			ws.mergeCells(15, 2, 16, 2);//待就业情况合并
			ws.mergeCells(17, 2, 18, 2);//违约情况合并
			
			//表头--第一行
			ws.addCell(new Label(0, 2, "系别", wcfTytle));
			ws.addCell(new Label(1, 2, "专业", wcfTytle));
			ws.addCell(new Label(2, 2, "学历", wcfTytle));
			ws.addCell(new Label(3, 2, "专业就业人数", wcfTytle));
			ws.addCell(new Label(4, 2, "就业情况", wcfTytle));
			ws.addCell(new Label(13, 2, "签约情况", wcfTytle));
			ws.addCell(new Label(15, 2, "待就业情况", wcfTytle));
			ws.addCell(new Label(17, 2, "违约情况", wcfTytle));
			ws.addCell(new Label(19, 2, "离校待考", wcfTytle));
			//表头--第二行
			ws.addCell(new Label(4, 3, "已签约", wcfTytle));
			ws.addCell(new Label(5, 3, "出国", wcfTytle));
			ws.addCell(new Label(6, 3, "升学", wcfTytle));
			ws.addCell(new Label(7, 3, "意向就业", wcfTytle));
			ws.addCell(new Label(8, 3, "灵活就业", wcfTytle));
			ws.addCell(new Label(9, 3, "有接有函", wcfTytle));
			ws.addCell(new Label(10, 3, "地方项目", wcfTytle));
			ws.addCell(new Label(11, 3, "就业总数", wcfTytle));
			ws.addCell(new Label(12, 3, "就业率", wcfTytle));
			ws.addCell(new Label(13, 3, "签约总数（含升学、地方项目）", wcfTytle));
			ws.addCell(new Label(14, 3, "签约率", wcfTytle));
			ws.addCell(new Label(15, 3, "人数", wcfTytle));
			ws.addCell(new Label(16, 3, "待就业率", wcfTytle));
			ws.addCell(new Label(17, 3, "人数", wcfTytle));
			ws.addCell(new Label(18, 3, "比例", wcfTytle));
			
			int row = 0;
			int m = 0;
			int currRow = 0;
			
			/*
			 *校本部数据输出，涉及小部分。
			 *如果学院单元格的当前单元格内容与下一单元格内容
			 *不一样的话空出一行加上小计 
			 */
			for (int i = 0 ; i < xbbData.size(); i++) {
				row=i+m;
				for (int j = 0 ; j < xbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, xbbData.get(i)[j], wcfTytle));
				}
				
				String currCell = xbbData.get(i)[0];//当前单元格
				String beforeCell = i<xbbData.size()-1 ? xbbData.get(i+1)[0] : "";//下一单元格
				
				
				//小计部分
				if (!currCell.equals(beforeCell)) {
					
					for (int j = 0 ; j < xbbXj.get(m).length ; j++) {
						ws.addCell(new Label(j,row+5 , xbbXj.get(m)[j], wcfTytle));
					}
					m++;
				}
			}
			
			
			//校本部合计
			ws.mergeCells(0, row+6, 1, row+6);//总计合并
			ws.addCell(new Label(0,row+6 , "总计", wcfTytle));
			ws.addCell(new Label(2,row+6 , " ", wcfTytle));
			
			//总计部分数据
			for (int i = 0 ; i < xbbZjData.length ; i++) {
				ws.addCell(new Label(3+i, row+6, xbbZjData[i], wcfTytle));
			}
			
			//当前行
			currRow = row+7;
			
			/*
			 *软件学院数据输出 
			 */
			m = 0;
			int count = 0;
			
			if (null != rjxyData && rjxyData.size() > 0) {
				
				for (int i = 0 ; i < rjxyData.size(); i++) {
					count = row+i+m;
					for (int j = 0 ; j < rjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, count+7, rjxyData.get(i)[j], wcfTytle));
					}
					
					String currCell = rjxyData.get(i)[0];//当前单元格
					String beforeCell = i<rjxyData.size()-1 ? rjxyData.get(i+1)[0] : "";//下一单元格
					
					//小计部分
					if (!currCell.equals(beforeCell)) {
						
						for (int j = 0 ; j < rjxyXj.get(m).length ; j++) {
							ws.addCell(new Label(j,count+8 , rjxyXj.get(m)[j], wcfTytle));
						}
						m++;
					}
				}
				
				currRow = count+9;
				
			}
			
			ws.mergeCells(0, currRow, 1, currRow);//总计合并
			ws.addCell(new Label(0,currRow , "总计", wcfTytle));
			ws.addCell(new Label(2,currRow , " ", wcfTytle));
			
			//总计部分数据
			for (int i = 0 ; i < zjData.length ; i++) {
				ws.addCell(new Label(3+i, currRow, zjData[i], wcfTytle));
			}
			
			 wcfTytle = ExcelMB.getWritableCellFormat(8,
						false, Alignment.LEFT, VerticalAlignment.CENTRE,
						Border.ALL);// 单元格样式
			ws.mergeCells(0, currRow+1, 19, currRow+3);
			ws.addCell(new Label(0,currRow+1,model.getXmbz(),wcfTytle));
			
			
			ExcelMB.mergeCells(ws, currRow+1, 0, 3);//学院合并
			ExcelMB.mergeCells(ws, currRow+1, 1, 3);//专业名称合并
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 福建工程--班级就业率统计表
	 * @param xydm
	 * @param os
	 */
	protected void printJylForBj(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("年班级就业率统计汇总表");
		
		List<String[]> xbbData = dao.getJylForBj("xbb",model);//校本部
		List<String[]> rjxyData = dao.getJylForBj("rjxy",model);//软件学院
		String[] xbbZjData = dao.getJylTjForZy(model,"xbb","");
		String[] zjData = dao.getJylTjForZy(model,"","");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("班级就业率统计汇总表", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 21, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "截止日期：XXXX年X月X日", wcfTytle));
			
			//表头单元格合并
			ws.mergeCells(0, 2, 0, 3);//系别
			ws.mergeCells(1, 2, 1, 3);//专业
			ws.mergeCells(2, 2, 2, 3);//班级
			
			ws.mergeCells(3, 2, 3, 3);//学历
			ws.mergeCells(4, 2, 4, 3);//专业人数
			ws.mergeCells(20, 2, 20, 3);//离校待考
			
			ws.mergeCells(5, 2, 13, 2);//就业情况合并
			ws.mergeCells(14, 2, 15, 2);//签约情况合并
			ws.mergeCells(16, 2, 17, 2);//待就业情况合并
			ws.mergeCells(18, 2, 19, 2);//违约情况合并
			
			//表头--第一行
			ws.addCell(new Label(0, 2, "系别", wcfTytle));
			ws.addCell(new Label(1, 2, "专业", wcfTytle));
			ws.addCell(new Label(2, 2, "班级", wcfTytle));
			
			ws.addCell(new Label(3, 2, "学历", wcfTytle));
			ws.addCell(new Label(4, 2, "班级就业人数", wcfTytle));
			ws.addCell(new Label(5, 2, "就业情况", wcfTytle));
			ws.addCell(new Label(14, 2, "签约情况", wcfTytle));
			ws.addCell(new Label(16, 2, "待就业情况", wcfTytle));
			ws.addCell(new Label(18, 2, "违约情况", wcfTytle));
			ws.addCell(new Label(20, 2, "离校待考", wcfTytle));
			//表头--第二行
			ws.addCell(new Label(5, 3, "已签约", wcfTytle));
			ws.addCell(new Label(6, 3, "出国", wcfTytle));
			ws.addCell(new Label(7, 3, "升学", wcfTytle));
			ws.addCell(new Label(8, 3, "意向就业", wcfTytle));
			ws.addCell(new Label(9, 3, "灵活就业", wcfTytle));
			ws.addCell(new Label(10, 3, "有接有函", wcfTytle));
			ws.addCell(new Label(11, 3, "地方项目", wcfTytle));
			ws.addCell(new Label(12, 3, "就业总数", wcfTytle));
			ws.addCell(new Label(13, 3, "就业率", wcfTytle));
			ws.addCell(new Label(14, 3, "签约总数（含升学、地方项目）", wcfTytle));
			ws.addCell(new Label(15, 3, "签约率", wcfTytle));
			ws.addCell(new Label(16, 3, "人数", wcfTytle));
			ws.addCell(new Label(17, 3, "待就业率", wcfTytle));
			ws.addCell(new Label(18, 3, "人数", wcfTytle));
			ws.addCell(new Label(19, 3, "比例", wcfTytle));
			
			
			
			int row = 0;
			int currRow = 0;
			
			/*
			 *校本部数据输出，涉及小部分。
			 *如果学院单元格的当前单元格内容与下一单元格内容
			 *不一样的话空出一行加上小计 
			 */
			for (int i = 0 ; i < xbbData.size(); i++) {
				
				for (int j = 0 ; j < xbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, xbbData.get(i)[j], wcfTytle));
				}
				
				row++;
			}
			
			//校本部合计
			ws.mergeCells(0, row+4, 3, row+4);//总计合并
			ws.addCell(new Label(0,row+4 , "总计", wcfTytle));
			ws.addCell(new Label(2,row+4 , " ", wcfTytle));
			
			//总计部分数据
			for (int i = 0 ; i < xbbZjData.length ; i++) {
				ws.addCell(new Label(4+i, row+4, xbbZjData[i], wcfTytle));
			}
			
			/*
			 *软件学院数据输出 
			 */
			currRow = row + 5;
			
			if (null != rjxyData && rjxyData.size() > 0) {
				
				for (int i = 0 ; i < rjxyData.size(); i++) {
					for (int j = 0 ; j < rjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, currRow, rjxyData.get(i)[j], wcfTytle));
					}
					
					currRow++;
				}
				
				
				ws.mergeCells(0, currRow, 3, currRow);//总计合并
				ws.addCell(new Label(0,currRow , "总计", wcfTytle));
				ws.addCell(new Label(2,currRow , " ", wcfTytle));
				
				//总计部分数据
				for (int i = 0 ; i < zjData.length ; i++) {
					ws.addCell(new Label(4+i, currRow, zjData[i], wcfTytle));
				}
				currRow++;
			}
			
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.mergeCells(0, currRow, 20, currRow+2);
			ws.addCell(new Label(0, currRow, model.getXmbz(), wcfTytle));
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);//学院合并
			ExcelMB.mergeCells(ws, currRow, 1, 3);//专业名称合并
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 福建工程--就业率汇总统计表
	 * @param xydm
	 * @param os
	 */
	protected void printJylHz(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("年就业情况汇总统计总表");
		
		String[] zjData = dao.getJylTjForZy(model,"","");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("就业情况汇总统计总表", 0);
		
		try {
			excel.printTitle(ws, title.toString(),17, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "截止日期：XXXX年X月X日", wcfTytle));
			
			//表头单元格合并
			ws.mergeCells(0, 2, 0, 3);//就业总数
			ws.mergeCells(16, 2, 16, 3);//离校待考
			
			ws.mergeCells(1, 2, 9, 2);//就业情况合并
			ws.mergeCells(10, 2, 11,2);//签约情况合并
			ws.mergeCells(12, 2, 13, 2);//待就业情况合并
			ws.mergeCells(14, 2, 15, 2);//违约情况合并
			
			//表头--第一行
			ws.addCell(new Label(0, 2, "毕业生人数", wcfTytle));
			ws.addCell(new Label(1, 2, "就业情况", wcfTytle));
			ws.addCell(new Label(10, 2, "签约情况", wcfTytle));
			ws.addCell(new Label(12, 2, "待就业情况", wcfTytle));
			ws.addCell(new Label(14, 2, "违约情况", wcfTytle));
			ws.addCell(new Label(16, 2, "离校待考学生数", wcfTytle));
			//表头--第二行
			ws.addCell(new Label(1, 3, "已签约", wcfTytle));
			ws.addCell(new Label(2, 3, "出国", wcfTytle));
			ws.addCell(new Label(3, 3, "升学", wcfTytle));
			ws.addCell(new Label(4, 3, "意向就业", wcfTytle));
			ws.addCell(new Label(5, 3, "灵活就业", wcfTytle));
			ws.addCell(new Label(6, 3, "有接有函", wcfTytle));
			ws.addCell(new Label(7, 3, "地方项目", wcfTytle));
			ws.addCell(new Label(8, 3, "就业总数", wcfTytle));
			ws.addCell(new Label(9, 3, "就业率", wcfTytle));
			ws.addCell(new Label(10, 3, "签约总数（含升学、地方项目）", wcfTytle));
			ws.addCell(new Label(11, 3, "签约率", wcfTytle));
			ws.addCell(new Label(12, 3, "人数", wcfTytle));
			ws.addCell(new Label(13, 3, "待就业率", wcfTytle));
			ws.addCell(new Label(14, 3, "人数", wcfTytle));
			ws.addCell(new Label(15, 3, "比例", wcfTytle));
			
			//总计部分数据
			for (int i = 0 ; i < zjData.length ; i++) {
				ws.addCell(new Label(i, 4, zjData[i], wcfTytle));
			}
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.mergeCells(0, 5, 16, 7);
			ws.addCell(new Label(0, 5, model.getXmbz(), wcfTytle));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 福建工程--本科生就业率统计表
	 * @param xydm
	 * @param os
	 */
	protected void printJylForBks(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("年本科就业率统计表");
		
		List<String[]> bksXbbData = dao.getJylForZy("xbb","bks",model);//校本部本科生数据
		List<String[]> xjXbbData = dao.getJylForZy(model,"xbb","bks");//校本部小计数据
		String[] zjXbbData = dao.getJylTjForZy(model,"xbb","bks");//校本部总计
		
		List<String[]> bksRjxyData = dao.getJylForZy("rjxy","bks",model);//软件学院本科生数据
		List<String[]> xjRjxyData = dao.getJylForZy(model,"rjxy","bks");//软件学院小计数据
		String[] zjData = dao.getJylTjForZy(model,"rjxy","bks");//院总计
		
		
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("本科就业率统计表", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "截止日期：XXXX年X月X日", wcfTytle));
			
			//表头单元格合并
			ws.mergeCells(0, 2, 0, 3);//系别
			ws.mergeCells(1, 2, 1, 3);//专业
			ws.mergeCells(2, 2, 2, 3);//学历
			ws.mergeCells(3, 2, 3, 3);//专业人数
			ws.mergeCells(19, 2, 19, 3);//离校待考
			
			ws.mergeCells(4, 2, 12, 2);//就业情况合并
			ws.mergeCells(13, 2, 14, 2);//签约情况合并
			ws.mergeCells(15, 2, 16, 2);//待就业情况合并
			ws.mergeCells(17, 2, 18, 2);//违约情况合并
			
			//表头--第一行
			ws.addCell(new Label(0, 2, "系别", wcfTytle));
			ws.addCell(new Label(1, 2, "专业", wcfTytle));
			ws.addCell(new Label(2, 2, "学历", wcfTytle));
			ws.addCell(new Label(3, 2, "专业就业人数", wcfTytle));
			ws.addCell(new Label(4, 2, "就业情况", wcfTytle));
			ws.addCell(new Label(13, 2, "签约情况", wcfTytle));
			ws.addCell(new Label(15, 2, "待就业情况", wcfTytle));
			ws.addCell(new Label(17, 2, "违约情况", wcfTytle));
			ws.addCell(new Label(19, 2, "离校待考学生数", wcfTytle));
			//表头--第二行
			ws.addCell(new Label(4, 3, "已签约", wcfTytle));
			ws.addCell(new Label(5, 3, "出国", wcfTytle));
			ws.addCell(new Label(6, 3, "升学", wcfTytle));
			ws.addCell(new Label(7, 3, "意向就业", wcfTytle));
			ws.addCell(new Label(8, 3, "灵活就业", wcfTytle));
			ws.addCell(new Label(9, 3, "有接有函", wcfTytle));
			ws.addCell(new Label(10, 3, "地方项目", wcfTytle));
			ws.addCell(new Label(11, 3, "就业总数", wcfTytle));
			ws.addCell(new Label(12, 3, "就业率", wcfTytle));
			ws.addCell(new Label(13, 3, "签约总数（含升学、地方项目）", wcfTytle));
			ws.addCell(new Label(14, 3, "签约率", wcfTytle));
			ws.addCell(new Label(15, 3, "人数", wcfTytle));
			ws.addCell(new Label(16, 3, "待就业率", wcfTytle));
			ws.addCell(new Label(17, 3, "人数", wcfTytle));
			ws.addCell(new Label(18, 3, "比例", wcfTytle));
			
			int row = 0;
			int m = 0;
			int currRow = 0 ;//当前行
			
			for (int i = 0 ; i < bksXbbData.size(); i++) {
				
				row = i+m;
				
				for (int j = 0 ; j < bksXbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, bksXbbData.get(i)[j], wcfTytle));
				}
				
				String currCell = bksXbbData.get(i)[0];//当前单元格
				String beforeCell = i<bksXbbData.size()-1 ? bksXbbData.get(i+1)[0] : "";//上一单元格
				
				//小计部分
				if (!currCell.equals(beforeCell)) {
					
					for (int j = 0 ; j < xjXbbData.get(m).length ; j++) {
						ws.addCell(new Label(j,row+5 , xjXbbData.get(m)[j], wcfTytle));
					}
					
					m++;
				}
			}
			
			ws.mergeCells(0, row+6, 2, row+6);//总计合并
			ws.addCell(new Label(0,row+6 , "总计", wcfTytle));
			ws.addCell(new Label(2,row+6 , " ", wcfTytle));
			
			//总计部分数据
			for (int i = 0 ; i < zjXbbData.length ; i++) {
				ws.addCell(new Label(3+i, row+6, Base.isNull(zjXbbData[i]) ? "0" : zjXbbData[i], wcfTytle));
			}
			
			currRow = row + 7;
			m = 0;
			
			if (null != bksRjxyData && bksRjxyData.size() > 0) {
				
				for (int i = 0 ; i < bksRjxyData.size(); i++) {
					
					currRow+=m;
					
					for (int j = 0 ; j < bksRjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, currRow, bksRjxyData.get(i)[j], wcfTytle));
					}
					
					String currCell = bksRjxyData.get(i)[0];//当前单元格
					String beforeCell = i<bksRjxyData.size()-1 ? bksRjxyData.get(i+1)[0] : "";//上一单元格
					
					currRow++;
					
					//小计部分
					if (!currCell.equals(beforeCell)) {
						
						for (int j = 0 ; j < xjRjxyData.get(m).length ; j++) {
							ws.addCell(new Label(j,currRow , xjRjxyData.get(m)[j], wcfTytle));
						}
						
						m++;
						currRow++;
					}
					
				}
				
				ws.mergeCells(0, currRow, 2, currRow);//总计合并
				ws.addCell(new Label(0,currRow , "总计", wcfTytle));
				ws.addCell(new Label(2,currRow , " ", wcfTytle));
				
				//总计部分数据
				for (int i = 0 ; i < zjData.length ; i++) {
					ws.addCell(new Label(3+i, currRow, Base.isNull(zjData[i]) ? "0" : zjData[i], wcfTytle));
				}
				
			}
			
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.mergeCells(0, currRow+1, 19, currRow+3);
			ws.addCell(new Label(0,currRow+1 , model.getXmbz(), wcfTytle));
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);//学院合并
			ExcelMB.mergeCells(ws, currRow, 1, 3);//专业名称合并
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	

	/**
	 * 福建工程--专科生就业率统计表
	 * @param xydm
	 * @param os
	 */
	protected void printJylForZks(JyglForm model,OutputStream os) {
		String nf = model.getNf();
		StringBuilder title = new StringBuilder();
		
		title.append(Base.xxmc);
		title.append(nf);
		title.append("年专科就业率统计表");
		
		
		List<String[]> zksXbbData = dao.getJylForZy("xbb","zks",model);//校本部专科生数据
		List<String[]> xjXbbData = dao.getJylForZy(model,"xbb","zks");//校本部小计数据
		String[] zjXbbData = dao.getJylTjForZy(model,"xbb","zks");//校本部总计
		
		List<String[]> zksRjxyData = dao.getJylForZy("rjxy","zks",model);//软件学院专科生数据
		List<String[]> xjRjxyData = dao.getJylForZy(model,"rjxy","zks");//软件学院小计数据
		String[] zjData = dao.getJylTjForZy(model,"rjxy","zks");//院总计
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("专科就业率统计表", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 20, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "截止日期：XXXX年X月X日", wcfTytle));
			
			//表头单元格合并
			ws.mergeCells(0, 2, 0, 3);//系别
			ws.mergeCells(1, 2, 1, 3);//专业
			ws.mergeCells(2, 2, 2, 3);//学历
			ws.mergeCells(3, 2, 3, 3);//专业人数
			ws.mergeCells(19, 2, 19, 3);//离校待考
			
			ws.mergeCells(4, 2, 12, 2);//就业情况合并
			ws.mergeCells(13, 2, 14, 2);//签约情况合并
			ws.mergeCells(15, 2, 16, 2);//待就业情况合并
			ws.mergeCells(17, 2, 18, 2);//违约情况合并
			
			//表头--第一行
			ws.addCell(new Label(0, 2, "系别", wcfTytle));
			ws.addCell(new Label(1, 2, "专业", wcfTytle));
			ws.addCell(new Label(2, 2, "学历", wcfTytle));
			ws.addCell(new Label(3, 2, "专业就业人数", wcfTytle));
			ws.addCell(new Label(4, 2, "就业情况", wcfTytle));
			ws.addCell(new Label(13, 2, "签约情况", wcfTytle));
			ws.addCell(new Label(15, 2, "待就业情况", wcfTytle));
			ws.addCell(new Label(17, 2, "违约情况", wcfTytle));
			ws.addCell(new Label(19, 2, "离校待考学生数", wcfTytle));
			//表头--第二行
			ws.addCell(new Label(4, 3, "已签约", wcfTytle));
			ws.addCell(new Label(5, 3, "出国", wcfTytle));
			ws.addCell(new Label(6, 3, "升学", wcfTytle));
			ws.addCell(new Label(7, 3, "意向就业", wcfTytle));
			ws.addCell(new Label(8, 3, "灵活就业", wcfTytle));
			ws.addCell(new Label(9, 3, "有接有函", wcfTytle));
			ws.addCell(new Label(10, 3, "地方项目", wcfTytle));
			ws.addCell(new Label(11, 3, "就业总数", wcfTytle));
			ws.addCell(new Label(12, 3, "就业率", wcfTytle));
			ws.addCell(new Label(13, 3, "签约总数（含升学、地方项目）", wcfTytle));
			ws.addCell(new Label(14, 3, "签约率", wcfTytle));
			ws.addCell(new Label(15, 3, "人数", wcfTytle));
			ws.addCell(new Label(16, 3, "待就业率", wcfTytle));
			ws.addCell(new Label(17, 3, "人数", wcfTytle));
			ws.addCell(new Label(18, 3, "比例", wcfTytle));
			
			int row = 0;
			int m = 0;
			int currRow = 0 ;//当前行
			
			for (int i = 0 ; i < zksXbbData.size(); i++) {
				
				row = i+m;
				
				for (int j = 0 ; j < zksXbbData.get(i).length ; j++) {
					ws.addCell(new Label(j, row+4, zksXbbData.get(i)[j], wcfTytle));
				}
				
				String currCell = zksXbbData.get(i)[0];//当前单元格
				String beforeCell = i<zksXbbData.size()-1 ? zksXbbData.get(i+1)[0] : "";//上一单元格
				
				//小计部分
				if (!currCell.equals(beforeCell)) {
					
					for (int j = 0 ; j < xjXbbData.get(m).length ; j++) {
						ws.addCell(new Label(j,row+5 , xjXbbData.get(m)[j], wcfTytle));
					}
					
					m++;
				}
			}
			
			ws.mergeCells(0, row+6, 2, row+6);//总计合并
			ws.addCell(new Label(0,row+6 , "总计", wcfTytle));
			ws.addCell(new Label(2,row+6 , " ", wcfTytle));
			
			//总计部分数据
			for (int i = 0 ; i < zjXbbData.length ; i++) {
				ws.addCell(new Label(3+i, row+6, Base.isNull(zjXbbData[i]) ? "0" : zjXbbData[i], wcfTytle));
			}
			
			currRow = row + 7;
			m = 0;
			
			if (null != zksRjxyData && zksRjxyData.size() > 0) {
				
				for (int i = 0 ; i < zksRjxyData.size(); i++) {
					
					currRow+=m;
					
					for (int j = 0 ; j < zksRjxyData.get(i).length ; j++) {
						ws.addCell(new Label(j, currRow, zksRjxyData.get(i)[j], wcfTytle));
					}
					
					currRow++;
					
					String currCell = zksRjxyData.get(i)[0];//当前单元格
					String beforeCell = i<zksRjxyData.size()-1 ? zksRjxyData.get(i+1)[0] : "";//上一单元格
					
					//小计部分
					if (!currCell.equals(beforeCell)) {
						
						for (int j = 0 ; j < xjRjxyData.get(m).length ; j++) {
							ws.addCell(new Label(j,currRow , xjRjxyData.get(m)[j], wcfTytle));
						}
						
						m++;
						currRow++;
					}
					
				}
				
				ws.mergeCells(0, currRow, 2, currRow);//总计合并
				ws.addCell(new Label(0,currRow , "总计", wcfTytle));
				ws.addCell(new Label(2,currRow , " ", wcfTytle));
				
				//总计部分数据
				for (int i = 0 ; i < zjData.length ; i++) {
					ws.addCell(new Label(3+i, currRow, Base.isNull(zjData[i]) ? "0" : zjData[i], wcfTytle));
				}
				
			}
			
			//备注部分
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.mergeCells(0, currRow+1, 19, currRow+3);
			ws.addCell(new Label(0,currRow+1 , model.getXmbz(), wcfTytle));
			
			ExcelMB.mergeCells(ws, currRow, 0, 3);//学院合并
			ExcelMB.mergeCells(ws, currRow, 1, 3);//专业名称合并
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 保存数据含自定义字段数据
	 * 
	 * @return
	 */
	public boolean saveData(String realTable, String[] colList, String pkV,
			JyglForm model, HttpServletRequest request) throws Exception {

		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.insertNoLog(realTable, colList,
				inputList);

		if (updata) {
			//updata = new BdszDAO().updataZdyDdData(realTable, pkV, request);
		}
		return updata;
	}
	
	
	/**
	 * 查询含自定义字段数据
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getDataList(String tableName, JyglForm model,
			String[] colList, String[] zdyCol, String realTable, String[] pk)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZdyQueryList(tableName, model, colList, zdyCol,
				realTable, pk);
	}
	
	
	/**
	 * 更新含自定义字段数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(String realTable, String pk, JyglForm model,
			String pkValue, String newPkValue,String[] colList, HttpServletRequest request)
			throws Exception {

		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.updateNolog(realTable, colList,
				inputList, pk, pkValue) && new BdszDAO().delZdyData(realTable, pkValue);

		if (updata) {
			//updata = new BdszDAO().updataZdyDdData(realTable, newPkValue, request);
		}
		return updata;
	}


	/**
	 * 档案户口批量上报
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	protected boolean dahkPlsb(String[] pkValues) throws SQLException {
		
		if (null != pkValues && pkValues.length>0) {
			return dao.dahkPlsb(pkValues);
		} else {
			return false;
		}
	}


	/**
	 * 市下拉列表
	 * @param shenId
	 * @return
	 */
	protected List<HashMap<String, String>> getShiList(String shenId){
		
		shenId = Base.isNull(shenId) ? "110000" : shenId;
		
		return new StuInfoDAO().getShiList(shenId).get("shiList");
	}
	
	
	/**
	 * 县下拉列表
	 * @param shenId
	 * @return
	 */
	protected List<HashMap<String, String>> getXianList(String shenId){
		
		shenId = Base.isNull(shenId) ? "110000" : shenId;
		
		return new StuInfoDAO().getShiList(shenId).get("xianList");
	}


	/**
	 * 获取综测总分及排名
	 * @return
	 */
	protected HashMap<String, String> getZcfPm(String xh, String xn, String xq){
		
		return dao.getZcfPm(xh, xn, xq);
	}
	
	
	/**
	 * 优秀毕业生统计
	 * @param model
	 * @param os
	 */
	protected void printYxbys(JyglForm model,OutputStream os) {
		
		String sqlb = model.getSqlb();
		String xydm = model.getXydm();
		StringBuilder title = new StringBuilder();
		
		title.append("浙江省普通高等学校(");
		title.append(sqlb);
		title.append(")优秀毕业生名册");
		
		List<String[]> data = dao.getYxbysData(xydm, sqlb);
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("优秀毕业生评选", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 7, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 2, 6, 2);
			Base.YXPZXY_KEY = message.getMessage("lable.xb");
			ws.addCell(new Label(0,2 , Base.YXPZXY_KEY+"名称(盖章)：", wcfTytle));
			
			wcfTytle.setAlignment(Alignment.CENTRE);
			ws.addCell(new Label(0,3 , "专业名称", wcfTytle));
			ws.addCell(new Label(1,3 , "姓名", wcfTytle));
			ws.addCell(new Label(2,3 , "性别", wcfTytle));
			ws.addCell(new Label(3,3 , "学历", wcfTytle));
			ws.addCell(new Label(4,3 , "生源地", wcfTytle));
			ws.addCell(new Label(5,3 , "政治面貌", wcfTytle));
			ws.addCell(new Label(6,3 , "身份证号", wcfTytle));
			
			for (int i = 0 ; i < data.size() ; i++) {
				for (int j = 0 ;j < data.get(i).length ;j++) {
					ws.addCell(new Label(j,4+i , data.get(i)[j], wcfTytle));
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 获取导出的全部列
	 * @param tableName
	 * @return
	 */
	public String[] getColumn(String tableName) {
		
		return dao.getColumn(tableName);
	}
	
	
	/**
	 * 获取指定表的指定列
	 * @param realTable
	 * @param colList
	 * @return
	 */
	public String[] getColumn(String realTable,String[] colList){
		
		return dao.getColumnNameCN(colList, realTable);
	}
	

	/**
	 * 福建工程-辅导员带班就业情况明细
	 */
	protected void printFdyJymx(JyglForm model,OutputStream os) {
		
		StringBuilder title = new StringBuilder();
		List<String[]> data = dao.getFdyJymx(model,"xbb");
		List<String[]> rjxyData = dao.getFdyJymx(model,"rjxy");
		
		
		title.append(Base.xxmc)
			 .append(model.getNf())
			 .append("届毕业班辅导员带班就业情况明细表");
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("就业情况明细表", 0);
		
		try {
			excel.printTitle(ws, title.toString(), 19, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 3, 1);
			ws.addCell(new Label(0, 1, "截止日期：XXXX年X月X日", wcfTytle));
			
			//表头单元格合并
			ws.mergeCells(0, 2, 0, 3);//序号
			ws.mergeCells(1, 2, 1, 3);//姓名
			ws.mergeCells(2, 2, 2, 3);//带班人数
			ws.mergeCells(3, 2, 11, 2);//就业情况
			ws.mergeCells(12,2, 13, 2);//签约情况
			ws.mergeCells(14,2, 15, 2);//待就业情况
			ws.mergeCells(16,2, 17, 2);//解约情况
			ws.mergeCells(18,2, 18, 3);//离校待考
			
			//表头--第一行
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "带班人数", wcfTytle));
			ws.addCell(new Label(3, 2, "就业情况", wcfTytle));
			ws.addCell(new Label(12, 2, "签约情况（含升学、国家地方项目）", wcfTytle));
			ws.addCell(new Label(14, 2, "待就业情况", wcfTytle));
			ws.addCell(new Label(16, 2, "解约情况", wcfTytle));
			ws.addCell(new Label(18, 2, "离校待考", wcfTytle));
			
			//表头--第二行
			ws.addCell(new Label(3, 3, "已签约", wcfTytle));
			ws.addCell(new Label(4, 3, "出国", wcfTytle));
			ws.addCell(new Label(5, 3, "升学", wcfTytle));
			ws.addCell(new Label(6, 3, "意向就业", wcfTytle));
			ws.addCell(new Label(7, 3, "灵活就业", wcfTytle));
			ws.addCell(new Label(8, 3, "有接收函", wcfTytle));
			ws.addCell(new Label(9, 3, "地方项目", wcfTytle));
			ws.addCell(new Label(10, 3, "就业总数", wcfTytle));
			ws.addCell(new Label(11, 3, "就业率", wcfTytle));
			ws.addCell(new Label(12, 3, "人数", wcfTytle));
			ws.addCell(new Label(13, 3, "比例", wcfTytle));
			ws.addCell(new Label(14, 3, "人数", wcfTytle));
			ws.addCell(new Label(15, 3, "待就业率", wcfTytle));
			ws.addCell(new Label(16, 3, "人数", wcfTytle));
			ws.addCell(new Label(17, 3, "比例", wcfTytle));
			
			double xbbDbzs = 0;//校本部带班总数
			double yqrZs = 0;//已签约总数
			double cgZs = 0;//出国总数
			double sxZs = 0;//升学总数	
			double yxjyZs = 0 ;//意向就业总数
			double lhjyZs = 0 ;//灵活就业总数
			double yjshZs = 0 ;//有接收函总数
			double dfxmZs = 0 ;//地放项目总数
			double jyZs = 0 ;//就业总数
			double qyZs = 0 ;//签约总数
			double djyZs = 0 ;//待就业总数
			double jiyZs = 0 ;//解约总数
			double lxdkZs = 0 ;//离校待考总数
			
			
			//校本部数据输出
			for (int i = 0 ; i < data.size() ; i++) {
				ws.addCell(new Label(0, i+4, String.valueOf(i+1), wcfTytle));
				
				xbbDbzs += Integer.valueOf(data.get(i)[1]);
				yqrZs += Integer.valueOf(data.get(i)[2]);
				cgZs += Integer.valueOf(data.get(i)[3]);
				sxZs += Integer.valueOf(data.get(i)[4]);
				yxjyZs += Integer.valueOf(data.get(i)[5]);
				lhjyZs += Integer.valueOf(data.get(i)[6]);
				yjshZs += Integer.valueOf(data.get(i)[7]);
				dfxmZs += Integer.valueOf(data.get(i)[8]);
				jyZs += Integer.valueOf(data.get(i)[9]);
				qyZs += Integer.valueOf(data.get(i)[11]);
				djyZs += Integer.valueOf(data.get(i)[13]);
				jiyZs += Integer.valueOf(data.get(i)[15]);
				lxdkZs += Integer.valueOf(data.get(i)[17]);
				
				
				for (int j = 0 ; j < data.get(i).length ; j++) {
					ws.addCell(new Label(j+1, i+4,data.get(i)[j], wcfTytle));
				}
			}
			
			NumberFormat df = new DecimalFormat("0.00");
			
			//校本部合计
			ws.mergeCells(0, data.size()+4, 1, data.size()+4);
			ws.addCell(new Label(0, data.size()+4, "校本部合计", wcfTytle));
			ws.addCell(new Label(2, data.size()+4, String.valueOf(Math.round(xbbDbzs)), wcfTytle));
			ws.addCell(new Label(3, data.size()+4, String.valueOf(Math.round(yqrZs)), wcfTytle));
			ws.addCell(new Label(4, data.size()+4, String.valueOf(Math.round(cgZs)), wcfTytle));
			ws.addCell(new Label(5, data.size()+4, String.valueOf(Math.round(sxZs)), wcfTytle));
			ws.addCell(new Label(6, data.size()+4, String.valueOf(Math.round(yxjyZs)), wcfTytle));
			ws.addCell(new Label(7, data.size()+4, String.valueOf(Math.round(lhjyZs)), wcfTytle));
			ws.addCell(new Label(8, data.size()+4, String.valueOf(Math.round(yjshZs)), wcfTytle));
			ws.addCell(new Label(9, data.size()+4, String.valueOf(Math.round(dfxmZs)), wcfTytle));
			ws.addCell(new Label(10, data.size()+4, String.valueOf(Math.round(jyZs)), wcfTytle));
			ws.addCell(new Label(11, data.size()+4, df.format(jyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(12, data.size()+4, String.valueOf(Math.round(qyZs)), wcfTytle));
			ws.addCell(new Label(13, data.size()+4, df.format(qyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(14, data.size()+4, String.valueOf(Math.round(djyZs)), wcfTytle));
			ws.addCell(new Label(15, data.size()+4, df.format(djyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(16, data.size()+4, String.valueOf(Math.round(jiyZs)), wcfTytle));
			ws.addCell(new Label(17, data.size()+4, df.format(jiyZs/xbbDbzs*100)+"%", wcfTytle));
			ws.addCell(new Label(18, data.size()+4, String.valueOf(Math.round(lxdkZs)), wcfTytle));
			
			double dbzsRjxy = 0;
			double yqrZsRjxy = 0;
			double cgZsRjxy = 0;
			double sxZsRjxy = 0;
			double yxjyZsRjxy = 0;
			double lhjyZsRjxy = 0;
			double yjshZsRjxy = 0;
			double dfxmZsRjxy = 0;
			double jyZsRjxy = 0;
			double qyZsRjxy = 0;
			double djyZsRjxy = 0;
			double jiyZsRjxy = 0;
			double lxdkZsRjxy = 0;
			
			//软件学院数据输出
			for (int i = 0 ; i < rjxyData.size() ; i++) {
				ws.addCell(new Label(0, i+data.size()+5, String.valueOf(i+1), wcfTytle));
				
				dbzsRjxy += Integer.valueOf(rjxyData.get(i)[1]);
				yqrZsRjxy += Integer.valueOf(rjxyData.get(i)[2]);
				cgZsRjxy += Integer.valueOf(rjxyData.get(i)[3]);
				sxZsRjxy += Integer.valueOf(rjxyData.get(i)[4]);
				yxjyZsRjxy += Integer.valueOf(rjxyData.get(i)[5]);
				lhjyZsRjxy += Integer.valueOf(rjxyData.get(i)[6]);
				yjshZsRjxy += Integer.valueOf(rjxyData.get(i)[7]);
				dfxmZsRjxy += Integer.valueOf(rjxyData.get(i)[8]);
				jyZsRjxy += Integer.valueOf(rjxyData.get(i)[9]);
				qyZsRjxy += Integer.valueOf(rjxyData.get(i)[11]);
				djyZsRjxy += Integer.valueOf(rjxyData.get(i)[13]);
				jiyZsRjxy += Integer.valueOf(rjxyData.get(i)[15]);
				lxdkZsRjxy += Integer.valueOf(rjxyData.get(i)[17]);
				
				
				for (int j = 0 ; j < rjxyData.get(i).length ; j++) {
					ws.addCell(new Label(j+1, i+data.size()+5,rjxyData.get(i)[j], wcfTytle));
				}
			}
			
			
			//软件学院合计
			ws.mergeCells(0, data.size()+rjxyData.size()+5, 1, data.size()+rjxyData.size()+5);
			ws.addCell(new Label(0, data.size()+rjxyData.size()+5, "软件学院合计", wcfTytle));
			ws.addCell(new Label(2, data.size()+rjxyData.size()+5, String.valueOf(Math.round(dbzsRjxy)), wcfTytle));
			ws.addCell(new Label(3, data.size()+rjxyData.size()+5, String.valueOf(Math.round(yqrZsRjxy)), wcfTytle));
			ws.addCell(new Label(4, data.size()+rjxyData.size()+5, String.valueOf(Math.round(cgZsRjxy)), wcfTytle));
			ws.addCell(new Label(5, data.size()+rjxyData.size()+5, String.valueOf(Math.round(sxZsRjxy)), wcfTytle));
			ws.addCell(new Label(6, data.size()+rjxyData.size()+5, String.valueOf(Math.round(yxjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(7, data.size()+rjxyData.size()+5, String.valueOf(Math.round(lhjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(8, data.size()+rjxyData.size()+5, String.valueOf(Math.round(yjshZsRjxy)), wcfTytle));
			ws.addCell(new Label(9, data.size()+rjxyData.size()+5, String.valueOf(Math.round(dfxmZsRjxy)), wcfTytle));
			ws.addCell(new Label(10, data.size()+rjxyData.size()+5, String.valueOf(Math.round(jyZsRjxy)), wcfTytle));
			ws.addCell(new Label(11, data.size()+rjxyData.size()+5, df.format(jyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(12, data.size()+rjxyData.size()+5, String.valueOf(Math.round(qyZsRjxy)), wcfTytle));
			ws.addCell(new Label(13, data.size()+rjxyData.size()+5, df.format(qyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(14, data.size()+rjxyData.size()+5, String.valueOf(Math.round(djyZsRjxy)), wcfTytle));
			ws.addCell(new Label(15, data.size()+rjxyData.size()+5, df.format(djyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(16, data.size()+rjxyData.size()+5, String.valueOf(Math.round(jiyZsRjxy)), wcfTytle));
			ws.addCell(new Label(17, data.size()+rjxyData.size()+5, df.format(jiyZsRjxy/dbzsRjxy*100)+"%", wcfTytle));
			ws.addCell(new Label(18, data.size()+rjxyData.size()+5, String.valueOf(Math.round(lxdkZsRjxy)), wcfTytle));
			
			//总计
			ws.mergeCells(0, data.size()+rjxyData.size()+6, 1, data.size()+rjxyData.size()+6);
			ws.addCell(new Label(0, data.size()+rjxyData.size()+6, "总计", wcfTytle));
			ws.addCell(new Label(2, data.size()+rjxyData.size()+6, String.valueOf(Math.round(xbbDbzs)+Math.round(dbzsRjxy)), wcfTytle));
			ws.addCell(new Label(3, data.size()+rjxyData.size()+6, String.valueOf(Math.round(yqrZs)+Math.round(yqrZsRjxy)), wcfTytle));
			ws.addCell(new Label(4, data.size()+rjxyData.size()+6, String.valueOf(Math.round(cgZs)+Math.round(cgZsRjxy)), wcfTytle));
			ws.addCell(new Label(5, data.size()+rjxyData.size()+6, String.valueOf(Math.round(sxZs)+Math.round(sxZsRjxy)), wcfTytle));
			ws.addCell(new Label(6, data.size()+rjxyData.size()+6, String.valueOf(Math.round(yxjyZs)+Math.round(yxjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(7, data.size()+rjxyData.size()+6, String.valueOf(Math.round(lhjyZs)+Math.round(lhjyZsRjxy)), wcfTytle));
			ws.addCell(new Label(8, data.size()+rjxyData.size()+6, String.valueOf(Math.round(yjshZs)+Math.round(yjshZsRjxy)), wcfTytle));
			ws.addCell(new Label(9, data.size()+rjxyData.size()+6, String.valueOf(Math.round(dfxmZs)+Math.round(dfxmZsRjxy)), wcfTytle));
			ws.addCell(new Label(10, data.size()+rjxyData.size()+6, String.valueOf(Math.round(jyZs)+Math.round(jyZsRjxy)), wcfTytle));
			ws.addCell(new Label(11, data.size()+rjxyData.size()+6, df.format((jyZs+jyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(12, data.size()+rjxyData.size()+6, String.valueOf(Math.round(qyZs)+Math.round(qyZsRjxy)), wcfTytle));
			ws.addCell(new Label(13, data.size()+rjxyData.size()+6, df.format((qyZs+qyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(14, data.size()+rjxyData.size()+6, String.valueOf(Math.round(djyZs)+Math.round(djyZsRjxy)), wcfTytle));
			ws.addCell(new Label(15, data.size()+rjxyData.size()+6, df.format((djyZs+djyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(16, data.size()+rjxyData.size()+6, String.valueOf(Math.round(jiyZs)+Math.round(jiyZsRjxy)), wcfTytle));
			ws.addCell(new Label(17, data.size()+rjxyData.size()+6, df.format((jiyZs+jiyZsRjxy)/(xbbDbzs+dbzsRjxy)*100)+"%", wcfTytle));
			ws.addCell(new Label(18, data.size()+rjxyData.size()+6, String.valueOf(Math.round(lxdkZs)+Math.round(lxdkZsRjxy)), wcfTytle));
			//备注
			wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.mergeCells(0, data.size()+rjxyData.size()+7, 18, data.size()+rjxyData.size()+9);//备注
			ws.addCell(new Label(0, data.size()+rjxyData.size()+7, model.getXmbz(), wcfTytle));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}


	/**
	 * 修改项目备注
	 * @param xmdm
	 * @param xmbz
	 * @return
	 */
	protected boolean updateXmbz(String xmdm,String xmbz) {
		
		try {
			return dao.updateXmbz(xmdm, xmbz);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 单位类别统计表
	 * @param model
	 * @param os
	 */
	protected void printDwlbTj(JyglForm model,OutputStream os) {
		
		String title = "单位类别统计表";
		
		List<HashMap<String, String>> dwxzList = dao.getDwxzList();
		String[] bkData = dao.getDwxzTj("本科", dwxzList);
		String[] zkData = dao.getDwxzTj("专科", dwxzList);
		
		double bksRs = Double.valueOf(dao.getBysRs("本科"));
		double zksRs = Double.valueOf(dao.getBysRs("专科"));
		
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);
		
		try {
			excel.printTitle(ws, title, dwxzList.size()+3, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			ws.mergeCells(0, 1, 1, 1);
			ws.mergeCells(0, 2, 0, 3);
			ws.mergeCells(0, 4, 0, 5);
			ws.mergeCells(0, 6, 0, 7);
			
			ws.addCell(new Label(0, 1,"", wcfTytle));
			//本科部分
			ws.addCell(new Label(0, 2,"本科", wcfTytle));
			ws.addCell(new Label(1, 2,"人数", wcfTytle));
			ws.addCell(new Label(1, 3,"百分比", wcfTytle));
			
			//专科部分
			ws.addCell(new Label(0, 4,"专科", wcfTytle));
			ws.addCell(new Label(1, 4,"人数", wcfTytle));
			ws.addCell(new Label(1, 5,"百分比", wcfTytle));
			
			//合计部分
			ws.addCell(new Label(0, 6,"合计", wcfTytle));
			ws.addCell(new Label(1, 6,"人数", wcfTytle));
			ws.addCell(new Label(1, 7,"百分比", wcfTytle));
			
			DecimalFormat df = new DecimalFormat("#0.00");
			
			int bkzs = 0;//本科总数
			int zkzs = 0;//专科总数
			for (int i = 0 ; i < dwxzList.size() ; i++) {
				
				int bks = Base.isNull(bkData[i]) ? 0 : Integer.valueOf(bkData[i]);
				int zks = Base.isNull(zkData[i]) ? 0 : Integer.valueOf(zkData[i]);
				
				bkzs+=bks;
				zkzs+=zks;
				
				//单位性质输出
				ws.addCell(new Label(2+i, 1,dwxzList.get(i).get("dwxz"), wcfTytle));
				
				//本科数据输出
				ws.addCell(new Label(2+i, 2,String.valueOf(bks), wcfTytle));
				
				//本科百分比输出
				ws.addCell(new Label(2+i, 3,df.format(bks/bksRs*100)+"%", wcfTytle));
				//专科数据输出
				ws.addCell(new Label(2+i, 4,String.valueOf(zks), wcfTytle));
				//专科百分比输出
				ws.addCell(new Label(2+i, 5,df.format(zks/zksRs*100)+"%", wcfTytle));
				//合计数据输出
				ws.addCell(new Label(2+i, 6,String.valueOf(bks+zks), wcfTytle));
				//小计百分比输出
				ws.addCell(new Label(2+i, 7,df.format((zks+bks)/(zksRs+bksRs)*100)+"%", wcfTytle));
			}
			
			ws.addCell(new Label(2+dwxzList.size(), 1,"合计", wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 2,String.valueOf(bkzs), wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 3,df.format(bkzs/bksRs*100)+"%", wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 4,String.valueOf(zkzs), wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 5,df.format(zkzs/bksRs*100)+"%", wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 6,String.valueOf(bkzs+zkzs), wcfTytle));
			ws.addCell(new Label(2+dwxzList.size(), 7,df.format((bkzs+zkzs)/bksRs*100)+"%", wcfTytle));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 行业分类统计
	 * @param model
	 * @param os
	 */
	protected void printHyflTj(JyglForm model, OutputStream os) {

		String title = "单位行业分布情况表";

		List<HashMap<String, String>> hyflList = dao.getHyflList();
		String[] data = dao.getHyflTj(hyflList);

		double bysRs = Double.valueOf(dao.getBysRs(""));

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(title, 0);

		try {
			excel.printTitle(ws, title, hyflList.size()+2, 800);// 标题
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			NumberFormat df = new DecimalFormat("#0.00");
			
			ws.addCell(new Label(0, 1,"单位行业", wcfTytle));
			ws.addCell(new Label(0, 2,"人数", wcfTytle));
			ws.addCell(new Label(0, 3,"百分比", wcfTytle));
			
			int byzs = 0;
			
			for (int i = 0 ; i < hyflList.size() ; i++) {
				
				int bys = Base.isNull(data[i]) ? 0 : Integer.valueOf(data[i]);
				
				byzs += bys;
				
				ws.addCell(new Label(i+1, 1,hyflList.get(i).get("hyfl"), wcfTytle));
				ws.addCell(new Label(i+1, 2,data[i], wcfTytle));
				ws.addCell(new Label(i+1, 3,df.format(bys/bysRs*100)+"%", wcfTytle));
			}
			
			//合计列
			ws.addCell(new Label(hyflList.size()+1, 1,"合计", wcfTytle));
			ws.addCell(new Label(hyflList.size()+1, 2,String.valueOf(byzs), wcfTytle));
			ws.addCell(new Label(hyflList.size()+1, 3,df.format(byzs/bysRs*100)+"%", wcfTytle));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	public List<HashMap<String, String>> getKsList(String lx){
		String[] en = null;
		String[] cn = null;
		
		if("sq".equalsIgnoreCase(lx)){
			en = new String[]{
				"jygl.do?method=byskssb&mk=ky",
				"jygl.do?method=byskssb&mk=kg"	
				};
			
			cn = new String[]{
					"毕业生考研申报",
					"毕业生考公务员申报"
					};
		}
		
		return DAO.getInstance().arrayToList(en, cn);
	}
	
	public List<HashMap<String, String>> getYzjgList(){
		String tableName = "jygl_zjkj_yzjgdmb";
		String[] output = new String[]{"xmdm", "xmmc"};
		return CommonQueryDAO.commonQueryforList(tableName, "", new String[]{}, output, "");
	}
	
	
	
	/**
	 * 就业协议书领取保存
	 * @param xh
	 * @param lqqk
	 * @param xysbh
	 * @return
	 */
	protected boolean saveJyxylq(String[] xh,String[] lqqk,String[] xysbh,String userName) {
		
		if (null != xh && xh.length>0) {
			try {
				return dao.saveJyxylq(xh, lqqk, xysbh,userName);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 *获取就业管理模块参数设置信息 
	 * @return
	 */
	protected HashMap<String,String> getCssz(){
		
		return dao.getCssz();
	}
	
	
	/**
	 * 剩余未使用编号的个数
	 * @return
	 */
	public String getSybh() {
		
		return dao.getSybh();
	}
	
	
	/**
	 * 协议书批量审核
	 * @param pkValue
	 * @return
	 */
	protected boolean xysblsh(String[] pkValue,HashMap<String,String> valueMap) {
		
		try {
			if ("通过".equals(valueMap.get("xxsh"))) {
				return dao.xysblsh(pkValue,valueMap);
			} else {
				return dao.xysblQxsh(pkValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * 协议书补领单个审核后修改协议书领取处编号
	 * @param xh
	 * @param xysbh
	 * @return
	 * @throws Exception
	 */
	public boolean updateXysbhOne(String xh, String xysbh) {
		
		try {
			return dao.updateXysbhOne(xh, xysbh);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 获取已上报毕业生数
	 * @param yhm
	 * @param nd
	 * @return
	 */
	public String getYsbrsByNd(String yhm,String nd) {
		
		return dao.getYsbrsByNd(yhm, nd);
	}
	
	
	/**
	 * 参数设置初始化
	 *
	 */
	public void initCssz() {
		
		HashMap<String,String> cssz = dao.getCssz();
		
		if (Base.isNull(cssz.get("xxdm"))) {
			dao.initCssz();
		}
	}
	
	
	
	/**
	 * 设置批量审核时学校审核状态为“退回”的记录为“需重审”
	 * @param realTable
	 * @param pkValue
	 * @return
	 */
	public boolean resetXxsh(String realTable,String[] pkValue) {
		
		TableModel table = new BasicService().getTable(realTable);
		String pkKey = table.getPrimaryKey().replaceAll(",", "||");
		
		try {
			return dao.resetXxsh(realTable, pkKey, pkValue);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 检测学生是否可以填写就业协议，
	 * 若不能返回提示信息
	 * @param pkValue
	 * @return
	 */
	public String getIsBys(String xh) {
		
		String message = "";
		HashMap<String,String> cssz = getCssz();
		
		if ("1".equals(cssz.get("lcbh"))) {
			HashMap<String,String> map = dao.getMapNotOut("select * from jy_bysxxb where shzt='通过' and xh=?", new String[] {xh});
			
			if (map.isEmpty()) {
				message = "对不起，您不是毕业生或毕业生信息未被审核通过！";
			}
		} else {
			HashMap<String,String> map = dao.getMapNotOut("select * from view_jygl_jyxyslqdjb where lqqk='已领取' and xh=?", new String[] {xh});
			
			if (map.isEmpty()) {
				message = "对不起，您还未领取就业协议书！";
			}
		}
		
		return message;
	}
	
	
	
	/**
	 * 根据用人单位代码取相关信息
	 * @param yrdwdm
	 * @return
	 */
	public HashMap<String, String> getYrdw(String yrdwdm) {
		return dao.getYrdw(yrdwdm);
	}
	
	
	/**
	 * 查找主管单位列表
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getZgdwOption(String zgdwmc) {
		
		if (StringUtils.isNotNull(zgdwmc)){
			return dao.getZgdwOption(zgdwmc);
		} else{
			return dao.getZgdwOption();
		}
	}
	
	/**
	 * 查找主管部门列表
	 * @param zgdwmc
	 * @return
	 */
	public List<HashMap<String, String>> getLsbmOption(String zgdwmc) {
		
		if (StringUtils.isNotNull(zgdwmc)){
			return dao.getLsbmOption(zgdwmc);
		} else{
			return dao.getLsbmOption();
		}
	}


	
	/**
	 * 根据学号查询毕业生部分信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String> getBysxx(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select nvl(yzbh,' ') yzbh,nvl(lxdz,' ') lxdz,nvl(zzmm,' ') zzmm,")
		   .append("nvl(lxdh,' ') lxdh,nvl(sjhm,' ') sjhm,nvl(dzyx,' ') dzyx,nvl(qq,' ') qq,")
		   .append("nvl(sydshen,' ') sydshen,nvl(sydshi,' ') sydshi,")
		   .append("nvl(sydxian,' ') sydxian from jy_bysxxb where xh=?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}

	
	/**
	 * 是否毕业生信息确认时间
	 * @return
	 */
	public boolean getIsBysqrsj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from jygl_csszb where sysdate ")
		   .append(" between to_date(bysqrkssj,'yyyymmdd') and to_date(bysqrjssj,'yyyymmdd')+1")
		   .append(" and xxdm=?");
		
		String count = dao.getOneRs(sql.toString(), new String[]{Base.xxdm}, "count");
		return "1".equals(count);
	}
	
	
	/**
	 * 是否毕业生信息审核时间
	 * @return
	 */
	public boolean getIsBysshsj(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from jygl_csszb where sysdate ")
		   .append(" between to_date(bysshkssj,'yyyymmdd') and to_date(bysshjssj,'yyyymmdd')+1")
		   .append(" and xxdm=?");
		
		String count = dao.getOneRs(sql.toString(), new String[]{Base.xxdm}, "count");
		return "1".equals(count);
	}
	
	/**
	 * 毕业生去向
	 * @param zgdwmc
	 * @return
	 */
	public HashMap<String, String> getBysqx(String xh){

		return dao.getBysqx(xh);
	}
	
	/**
	 * 根据学号获取扩展项信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getJyxyKzx(String xh){
		
		return dao.getJyxyKzx(xh);
	}
	
	/**
	 * 毕业生信息 结果查询 2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getXsxxList(JyglForm model) throws 
		IllegalArgumentException, SecurityException,
		IllegalAccessException, InvocationTargetException,
		NoSuchMethodException{

		return dao.getXsxxList(model);
	}
	
	/**
	 * 上报毕业生信息  结果查询 2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getByssbList(JyglForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getByssbList(model);
	}
	
	/**
	 * 毕业生信息确认  2012.1.10 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getByqrList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getByqrList(model, colArr);
	}
	
	/**
	 * 就业协议审核  2012.1.17 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getJyxyShList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getJyxyShList(model, colArr);
	}
	
	/**
	 * 就业协议查询  2012.1.17 qlj
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]>getJyxycxList(JyglForm model,String[]colArr) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		return dao.getJyxycxList(model, colArr);
	}
	
	/**
	 * 获取学校中以毕业的学院列表
	 * 
	 * @author honglin
	 * @date 2012-4-23
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String, String>> setXyList(String xxdm){
		String sql ="select xymc,xydm from (select xymc,xydm,xxdm from jy_bysxxb group by xymc,xydm,xxdm) where xxdm=?";
		return dao.getList(sql, new String[]{xxdm}, new String []{"xymc","xydm"});
	}
}
	
















