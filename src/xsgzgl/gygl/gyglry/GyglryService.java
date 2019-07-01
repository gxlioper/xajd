package xsgzgl.gygl.gyglry;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewInit;
import xsgzgl.gygl.comm.GyglNewService;

import com.ctc.wstx.util.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrForm;

public class GyglryService extends GyglNewService{
	
	private GyglryDao dao=new GyglryDao();
	//操作系统缓存目录
	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	public static final String FILE_EMPTY="导入文件为空！";
	public static final String FILE_REPEAT="excel中存在重复数据，请仔细检查！";
//	public static final String 
	
//	public String[] getTopTr(GyglryForm model){
//		String[] top=new String[]{"楼栋","层号","寝室号","类型","性别","学号","姓名","联系电话","宿舍电话","备注"};
//		return top;
//	}
	
	/**
	 * 获取公寓管理人员信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getGyglryList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		return dao.getGyglryList(model,request,user);
	}
	
	/**
	 * 获取入住学生列表
	 */
	public List<String[]> getRzxsxxList(GyglryForm model) throws Exception{
		return dao.getRzxsxxList(model);
	}
	
	/**
	 * 获取层号列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getChList(GyglryForm model){
		return dao.getChList(model);
	}
	
	/**
	 * 获取寝室号列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getQshList(GyglryForm model){
		return dao.getQshList(model);
	}
	
	/**
	 * 管理人员分配
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryFp(GyglryForm model) throws Exception{
		boolean flag = this.fpFullFlag(model);
		return dao.gyglryFp(model,flag);
	}
	
	/**
	 * 公寓管理人员取消分配
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String gyglryQxfp(GyglryForm model) throws Exception{
		return dao.gyglryQxfp(model);
	}
	
	/**
	 * 同步干部信息（楼长、层长、寝室长）
	 * @return boolean
	 */
	public boolean tbgbInfo(){
		String sql="{call pro_xg_gygl_bgbtb()}";
		return dao.runPro(sql);
	}
	
	/**
	 * 寝室长维护自定义导出设置
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGyglryExportList(GyglryForm model,HttpServletRequest request,User user) throws Exception{
		return dao.getGyglryExportList(model,request,user);
	}
	/**
	 * 获取公寓管理人员楼长信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLzInfoList(String lddm) throws Exception{
		
		return dao.getLzInfoList(lddm);
	}
	/**
	 * 获取公寓管理人员层长信息列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCzInfoList(String lddm,String ch) throws Exception{
		
		return dao.getCzInfoList(lddm,ch);
	}
	
	/**
	 * 管理人员数量是否达到（true只能更新，false可更新可追加）
	 * jQuery -  调用
	 */
	public boolean fpFullFlag(GyglryForm model)
			throws Exception {
		boolean fpFullFlag = false;
		if(Base.isNull(model.getCh()) && Base.isNull(model.getQsh())){//分配楼长
			List<HashMap<String, String>> lzInfoList = this.getLzInfoList(model.getLddm());
			if(lzInfoList!=null && lzInfoList.size()>0){
				if(lzInfoList.size()>=Integer.parseInt(GyglNewInit.LZFPNUM)){
					fpFullFlag = true;
				}
			}else{
				fpFullFlag = true;
			}
		}else if(!Base.isNull(model.getCh()) &&  Base.isNull(model.getQsh())){//分配层长
			List<HashMap<String, String>> czInfoList = this.getCzInfoList(model.getLddm(),model.getCh());
			if(czInfoList!=null && czInfoList.size()>0){
				if(czInfoList.size()>=Integer.parseInt(GyglNewInit.LZFPNUM)){
					fpFullFlag = true;
				}
			}else{
				fpFullFlag = true;
			}
		}else if(!Base.isNull(model.getFp_ch()) && !Base.isNull(model.getFp_qsh())){//分配寝室长（只能分配一个，不做控制）
			fpFullFlag = true;
		}
		return fpFullFlag;
	}
	
	/** 
	 * @描述:获取公寓管理人员寝室长信息
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-31 上午10:48:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getQszInfo(String lddm,String qsh) {
		return dao.getQszInfo(lddm, qsh);
	}
	
	/**
	 * @throws WriteException 
	 * @throws IOException 
	 * 
	 * @描述: 下载导入模板
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-22 上午09:53:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File downloadMb() throws Exception{
		File file = new File(TEMP_DIR +"/xg_gyglydr.xls");
		if(!file.exists()){
			file.createNewFile();
		}
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		createWookBook(wwb);
        wwb.write();
        wwb.close();
		return file;
	}
	
	public void createWookBook(WritableWorkbook wwb) throws RowsExceededException, WriteException{
		  // 创建工作表
	        WritableSheet ws = wwb.createSheet("公寓管理员导入模板", 0);
	        Label labelXh= new Label(0, 0, "学号");
	        setComment("必填且学号必须存在！",labelXh);
	        Label labelLddm= new Label(1, 0, "楼栋");
	        setComment("必填且楼栋必须存在！",labelLddm);
	        Label labelCh= new Label(2, 0, "层号");
	        setComment("必填且层号必须存在或填#！",labelCh);
	        Label labelQsh= new Label(3, 0, "寝室号");
	        setComment("必填且寝室号必须存在或填#！",labelQsh);
//	        Label labelRzzt= new Label(4, 0, "任职状态");
//	        setComment("必填,只能填写在任/离任！",labelRzzt);
	        Label labelRzksrq = new Label(4, 0, "任职开始日期");
	        setComment("必填且格式须如2018-01-01！",labelRzksrq);
//	        Label labelRzjsrq = new Label(5, 0, "任职结束日期");
//	        setComment("格式须如2018-01-01！",labelRzjsrq);
	        Label labelBz = new Label(5, 0, "备注");
	        setComment("备注长度必须小于100！",labelBz);
	        Label labelLxdh = new Label(6, 0, "联系电话");
	        setComment("联系电话长度必须小于15！",labelLxdh);
	        ws.addCell(labelXh);
	        ws.addCell(labelLddm);
	        ws.addCell(labelCh);
	        ws.addCell(labelQsh);
	        ws.addCell(labelRzksrq);
	        ws.addCell(labelBz);
	        ws.addCell(labelLxdh);
	        //设置单元格文本格式
	        WritableCellFormat wcfF = new WritableCellFormat(
	        NumberFormats.TEXT); 
	        CellView cv = new CellView(); 
	        cv.setFormat(wcfF);
	        cv.setSize(10*265);
	        for(int i=0;i<7;i++){
	        	ws.setColumnView(i, cv);
	        }
	}
	
	/**
	 * 
	 * @描述: 添加注释
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-22 上午11:30:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public void setComment(String content,Label label){
		WritableCellFeatures wcf = new WritableCellFeatures();
		wcf.setComment(content);
		label.setCellFeatures(wcf);
	}
	
	/**
	 * 
	 * @描述:保存导入数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-22 下午04:27:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getAndSaveDrData(InputStream is)throws Exception{
		Workbook rwb = null;
		//导入记录数组
		List<String[]> drAddlist = new ArrayList<String[]>();
		List<String[]> drUplist = new ArrayList<String[]>();
		//错误记录数组
		List<String[]> errorlist = new ArrayList<String[]>() ;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
		    int cols=rs.getColumns();//得到所有的列
	        int rows=rs.getRows();//得到所有的行
	        isEmptyWookBook(cols,rows);
	        checkRepeatExcel(cols, rows, rs);
	        for (int i = 1; i < rows; i++) {
	        	//取出每行验证数据，塞入lsmap
	        	HashMap<String, String>  lsmap = new HashMap<String, String>();
	        	String xh = rs.getCell(0, i).getContents();
	        	String ldmc = rs.getCell(1, i).getContents();
	        	String ch = rs.getCell(2,i).getContents();
	        	String qsh = rs.getCell(3,i).getContents();
	        	String rzksrq = rs.getCell(4,i).getContents();
	        	String bz = rs.getCell(5,i).getContents();
	        	String lxdh = rs.getCell(6,i).getContents();
	        	lsmap.put("xh", xh);
	        	lsmap.put("ldmc", ldmc);
	        	lsmap.put("ch", ch);
	        	lsmap.put("qsh", qsh);
	        	lsmap.put("rzksrq", rzksrq);
	        	lsmap.put("bz", bz);
	        	lsmap.put("lxdh", lxdh);
	        	//全空行跳过
	        	if( checkBlankCellAll(lsmap)){
	        		continue;
	        	}
	        	//验证每行数据
	        	checkEveryRowRecord(lsmap);
	        	//验证是否有错误数据
	        	ArrayList<String> paralist = new ArrayList<String>();
	        	ArrayList<String> paraUplist = new ArrayList<String>();
	        	if("false".equals(lsmap.get("message"))){
	        		paralist.add(lsmap.get("xh"));
	        		paralist.add(lsmap.get("ldmc"));
	        		paralist.add(lsmap.get("ch"));
	        		paralist.add(lsmap.get("qsh"));
	        		paralist.add(lsmap.get("rzksrq"));
	        		paralist.add(lsmap.get("bz"));
	        		paralist.add(lsmap.get("lxdh"));
	        		for (Map.Entry<String, String> entry : lsmap.entrySet()){
      					if(!entry.getKey().equals("xh") && !entry.getKey().equals("ldmc")
	        					&&	!entry.getKey().equals("ch") && !entry.getKey().equals("qsh") && !entry.getKey().equals("rzksrq")	
	        					&& !entry.getKey().equals("bz") && !entry.getKey().equals("lxdh")&& !entry.getKey().equals("lddm") && !entry.getKey().equals("message")){
      						paralist.add(entry.getValue());
      					}
      					
      				}
	        		errorlist.add(paralist.toArray(new String[]{}));
	        	}else{
	        		paralist.add(lsmap.get("xh"));
	        		paralist.add(lsmap.get("lddm"));
	        		paralist.add(lsmap.get("ch"));
	        		paralist.add(lsmap.get("qsh"));
	        		paralist.add(lsmap.get("rzksrq"));
	        		paralist.add(lsmap.get("bz"));
	        		paralist.add(lsmap.get("lxdh"));
	        		paraUplist.add(lsmap.get("lddm"));
	        		paraUplist.add(lsmap.get("ch"));
	        		paraUplist.add(lsmap.get("qsh"));
	        		drAddlist.add(paralist.toArray(new String[]{}));
	        		drUplist.add(paraUplist.toArray(new String[]{}));
	        	}
			}
	        //如果正确集合不为空，进行数据保存
	        int drNum = drUplist != null ? drUplist.size():0;
	        if(drNum > 0){
	        	  dao.saveDrDataForUpdate(drUplist);
	        	  dao.saveDrData(drAddlist);
	        	 
	        }
	        //如果错误集合不为空,在服务器保存错误信息以供下载
	        int errNum = errorlist != null ? errorlist.size():0;
	        String fileName = "";
	        if(errorlist != null && errorlist.size() > 0){
	        	 fileName =  uploadErrorExcel(errorlist);
	        }
	        HashMap<String, String> rsmap = new HashMap<String, String>();
	        rsmap.put("drNum",drNum+"" );
	        rsmap.put("errNum",errNum+"" );
	        rsmap.put("fileName",fileName);
	        return rsmap;
		}catch (NullPointerException e) {
			throw new SystemException(getJsonMessage(FILE_EMPTY));
		}catch (BiffException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return new HashMap<String, String>();
	}
	
	/**
	 * 把message封装为json对象
	 * @param message
	 * @return
	 */
	protected JSONObject getJsonMessage(String message){
		Map<String,String> map = new HashMap<String, String>();
		map.put("message", message);
		JSONObject json = JSONObject.fromObject(map); 
		return json;
	}
	
	public void isEmptyWookBook(int cols,int rows){
		 //空判断
        if(cols < 7 || rows < 2){
        	throw new SystemException(getJsonMessage(FILE_EMPTY));
        }
	}
	
	/**
	 *
	 * @描述: 空白符空判断
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-23 上午10:45:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkBlankCellAll(HashMap<String,String> lsmap){
		//空白符空判断
    	boolean emptyflag = true;
    	for (String key : lsmap.keySet()) {
			if(StringUtils.isNotNull(lsmap.get(key))){
				emptyflag = false;
				break;
			}
		}
    	return emptyflag;
	}
	
	/**
	 * 
	 * @描述: 验证非空
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-23 下午01:33:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String,String> lsmap) throws Exception{
		HashMap<String,String> destMap = new HashMap<String, String>();
		for (String key : lsmap.keySet()) {
			destMap.put(key, lsmap.get(key));
		}
		//去掉非必填项
		destMap.remove("rzjsrq");
		destMap.remove("bz");
		destMap.remove("lxdh");
    	boolean nullflag = true;
    	for (String key : destMap.keySet()) {
			if(StringUtils.isNull(destMap.get(key))){
				nullflag = false;
				break;
			}
		}
    	return nullflag;
	}
	
	/**
	 *
	 * @描述: 验证每一行
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-23 上午11:14:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsmap
	 * @param gyglryForm
	 * @return
	 * HashMap<String,Object> 返回类型 
	 * @throws
	 */
	public void checkEveryRowRecord(HashMap<String, String> lsmap)throws Exception{
		//按行验证，错误的和正确的分别放入两个集合
		String message = "true";
		//空验证
		if(!checkNotNull(lsmap)){
			message = "false";
			lsmap.put("nullyz","学号,楼栋,层号,寝室号,任职开始日期必填！");
		}
		//学号是否存在验证(暂定有效学号为在校学生，离校离任暂不考虑)
		if(dao.checkXhIsExist(lsmap.get("xh"))){
			message = "false";
			lsmap.put("xhyz","学号不存在或该学生已离校！");
		}
		//楼栋是否存在验证
		String lddm = dao.checkLdmcIfExist(lsmap.get("ldmc"),lsmap.get("xh"));
		if(StringUtils.isNull(lddm)){
			message = "false";
			lsmap.put("ldmcyz","楼栋不存在或学生不在该楼栋！");
		}else{
			lsmap.put("lddm", lddm);
		}
		//层号是否存在验证,ch=#代表是楼长
		if(!"#".equals(lsmap.get("ch"))){
			if(!dao.checkChIfExist(lddm, lsmap.get("ch"),lsmap.get("xh"))){
				message = "false";
				lsmap.put("chyz","层号不存在或学生不在该层！");
			}
		}
		//寝室号是否存在验证,qsh=#代表是层长
		if(!"#".equals(lsmap.get("qsh"))){
			if(!dao.checkQshIfExist(lddm, lsmap.get("ch"),lsmap.get("qsh"),lsmap.get("xh"))){
				message = "false";
				lsmap.put("qshyz","寝室号不存在或学生不在该寝室！");
			}
		}
		//任职开始日期、任职结束日期验证  验证格式是否为2018-01-01
		String rzksrq = lsmap.get("rzksrq");
		if(StringUtils.isNotNull(rzksrq)){
			if(!common.newp.StringUtil.checkDateFormatAndValite("yyyy-MM-dd", rzksrq)){
				message = "false";
				lsmap.put("rzksrqyz","任职开始日期格式须如'2018-01-01'！");
			}
			  
		}
		String rzjsrq = lsmap.get("rzjsrq");
		if(StringUtils.isNotNull(rzjsrq)){
			if(!common.newp.StringUtil.checkDateFormatAndValite("yyyy-mm-dd", rzjsrq)){
				message = "false";
				lsmap.put("rzjsrqyz","任职开始日期格式须如'2018-01-01'！");
			}
		}
		//备注验证  验证长度是否超过100
		String bz = lsmap.get("bz");
		if(StringUtils.isNotNull(bz) && bz.length() > 100){
			message = "false";
			lsmap.put("bzyz","备注不能超过100字！");
		}
		//联系电话验证 
		String lxdh = lsmap.get("lxdh");
		if(StringUtils.isNotNull(lxdh) && !common.newp.StringUtil.checkTelePhone(lxdh)){
			message = "false";
			lsmap.put("lxdhyz","请输入正确的电话号码！");
		}
		lsmap.put("message", message);
	}
	
	/**
	 * 
	 * @描述: 上传错误数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-24 下午04:37:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws IOException
	 * String 返回类型 
	 * @throws
	 */
	public String uploadErrorExcel(List<String[]> errorlist) throws IOException{
		 String fileName = System.currentTimeMillis()+"error.xls";
		 String path = TEMP_DIR+"\\"+fileName;
        File file=new File(path);
        if (!file.exists()) {
            boolean rs = file.createNewFile();
            System.out.print("rs="+rs);
        }
        WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
			
			  // 创建工作表
           WritableSheet ws = wwb.createSheet("错误数据", 0);
           Label labelXh= new Label(0, 0, "学号");
           Label labelLddm= new Label(1, 0, "楼栋");
           Label labelCh= new Label(2, 0, "层号");
           Label labelQsh= new Label(3, 0, "寝室号");
           Label labelRzksrq = new Label(4, 0, "任职开始日期");
           Label labelBz = new Label(5, 0, "备注");
           Label labelLxdh = new Label(6, 0, "联系电话");
           try {
        	   	ws.addCell(labelXh);
	   	        ws.addCell(labelLddm);
	   	        ws.addCell(labelCh);
	   	        ws.addCell(labelQsh);
	   	        ws.addCell(labelRzksrq);
	   	        ws.addCell(labelBz);
	   	        ws.addCell(labelLxdh);
			} catch (RowsExceededException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO 自动生成 catch 块
				e1.printStackTrace();
			}
           
           for (int i = 0; i < errorlist.size(); i++) {
                for(int j = 0;j<errorlist.get(i).length;j++){
               	 Label labelId_i= null;
               	 if(j<=6){
               		  labelId_i= new Label(j, i+1, errorlist.get(i)[j]+"");
               	 }else{
               		 	WritableCellFormat wcf = new WritableCellFormat();
        				WritableFont wf = new WritableFont(WritableFont.ARIAL);
        				try {
							wf.setColour(Colour.RED);
							wcf.setFont(wf);
							wcf.setAlignment(Alignment.CENTRE);
							labelId_i = new Label(j, i+1, errorlist.get(i)[j],wcf);
						} catch (RowsExceededException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
        				
               	 }
               		try {
							ws.addCell(labelId_i);
						} catch (RowsExceededException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
               	
                }
           }
           
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	/**
	 * 
	 * @描述: 验证重复性
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-31 下午04:46:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public void checkRepeatExcel(int cols,int rows,Sheet rs ) throws Exception{
		boolean result = true;
		for (int i = 1; i < rows; i++) {
			String flagStr = rs.getCell(1, i).getContents()+rs.getCell(2, i).getContents()+rs.getCell(3, i).getContents();
			if(StringUtils.isNull(flagStr)){
				continue;
			}
			for (int j = i+1; j < rows; j++) {
				String compareStr = rs.getCell(1, j).getContents()+rs.getCell(2, j).getContents()+rs.getCell(3, j).getContents();
				if(StringUtils.isNull(compareStr)){
					continue;
				}
				if(flagStr.equals(compareStr)){
					result = false;
					break;
				}
				
			}
			if(!result){
				throw new SystemException(getJsonMessage(FILE_REPEAT));
			}
		}
	}
	
}
