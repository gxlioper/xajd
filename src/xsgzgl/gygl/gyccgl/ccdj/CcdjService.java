package xsgzgl.gygl.gyccgl.ccdj;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

public class CcdjService extends SuperServiceImpl<CcdjForm, CcdjDao> {
	/**
	 * 
	 * @描述: 删除方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 下午05:37:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean delResult(String[] ids) throws Exception{
		boolean result = dao.delCcdjbJg(ids);
		if(result){
			result = dao.delQswpshbJg(ids);
		}
		return result;
	}
	
	/**
	 * 
	 * @描述: 获取楼栋代码List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:02:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(CcdjForm ccdj){
		return dao.getLddmList(ccdj);
	}
	
	/**
	 * 
	 * @描述: 获取寝室List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:04:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(String lddm,String ch){
		return dao.getQshList(lddm, ch);
	}
	
	/**
	 * 
	 * @描述: 获取层号List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-9 下午04:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(String lddm){
		return dao.getChList(lddm);
	}
	
	/**
	 * 
	 * @描述: 获取物品List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-7 下午02:15:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWpList(){
		return dao.getWpList();
	}
	
	/**
	 * 
	 * @描述:获取物品损坏程度List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午10:20:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getShcdList(){
		return dao.getShcdList();
	}
	
	/**
	 * 
	 * @描述: 获取财产登记信息map
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午10:40:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCcdjMap(String id){
		return dao.getCcdjMap(id);
	}
	
	/**
	 * 
	 * @描述: 获取寝室物品维护List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午11:23:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQswpshbList(String id){
		return dao.getQswpshbList(id);
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存结果方法
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 上午11:42:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveForm(CcdjForm ccdjform) throws Exception{
		boolean rs = true;
		String id = ccdjform.getId();
		/**
		 * 根据id是否存在判断是插入操作还是更新操作
		 */
		if(StringUtils.isNull(id)){
			if(!dao.checkIsExistNotInCcdjb(ccdjform)){
				throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
			}
			id = UniqID.getInstance().getUniqIDHash().toUpperCase();
			//插入主表
			ccdjform.setId(id);
			rs = dao.runInsertNotCommit(ccdjform);
		}else{
			 dao.delQswpshbJg(new String[]{id});
			//更新主表
			 rs = dao.runUpdateNotCommit(ccdjform);
		}
		/**
		 * 通过手动扔异常来抛出消息。
		 */
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		/**
		 * 批处理返回的int类型jdbc数组标记进行判断
		 */
		 rs = dao.runBatchQswpshb(ccdjform);
		/**
		 * 通过手动扔异常来抛出消息。
		 */
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 获取查看时需要查询的损坏程度List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 下午03:16:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getViewWpShcdList(String id){
		return dao.getViewWpShcdList(id);
	}
	
	/**
	 * 
	 * @描述: 寝室号，学年，学期联动时获取数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-9 上午10:10:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccdjform
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getViewWpList(CcdjForm ccdjform){
		return dao.getViewWpList(ccdjform);
	}
	
	/**
	 * 
	 * @描述: 验证是否在财产登记表中存在重复数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-8 下午05:59:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccdjform
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsExistNotInCcdjb(CcdjForm ccdjform){
		return dao.checkIsExistNotInCcdjb(ccdjform);
	}
	
	/**
	 * 
	 * @描述: 下载导入模板
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-13 上午09:46:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public  void createWwb(OutputStream os,CcdjForm ccdj) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		WritableCellFormat titlebody =  this.getFontStyle("titlebody");
		/**
		 * sheet1
		 */
		WritableSheet ws = wwb.createSheet("数据导入模板", 0);
	    Label row1col1= new Label(0, 0, "学年",titlefont);
	    Label row1col2= new Label(1, 0, "学期",titlefont);
	    Label row1col3= new Label(2, 0, "楼栋",titlefont);
	    Label row1col4= new Label(3, 0, "寝室号",titlefont);
	    ws.addCell(row1col1);
		ws.addCell(row1col2);
		ws.addCell(row1col3);
		ws.addCell(row1col4);
	    /**
	     * 以下表头不固定，按xg_gygl_new_ssccgl_wpwhb(物品维护表)dm顺序输出
	     */
	    List<HashMap<String, String>> wpList = dao.getWpList();
	    int size = wpList.size();
	    for (int i = 4; i < 4+wpList.size(); i++) {
			HashMap<String, String> lsMap = wpList.get(i-4);
			Label rowcol = new Label(i, 0, lsMap.get("mc"), titlefont);
			WritableCellFeatures cellFeatures = new WritableCellFeatures();  
			cellFeatures.setComment(lsMap.get("dm"));  
			rowcol.setCellFeatures(cellFeatures);
			ws.addCell(rowcol);
		}
	    
		Label row1col6= new Label(4+wpList.size(), 0, "备注",titlefont);
		Label row1col7= new Label(4+wpList.size()+1, 0, "费用总计(元)",titlefont);
		 
		
		ws.addCell(row1col6);
		ws.addCell(row1col7);
	    WritableCellFormat wcfF = new WritableCellFormat(
	    	      NumberFormats.TEXT); //导入模板单元格格式设置为文本格式
	    CellView cv = new CellView(); //定义一个列显示样式 
	    cv.setFormat(wcfF);//把定义的单元格格式初始化进去
	    cv.setSize(10*265);//设置列宽度（不设置的话是0，不会显示）
	    for (int i = 0; i < 6+size; i++) {
	    	ws.setColumnView(i, cv);
		}
	    /**
	     * sheet2损坏程度维护表
	     */
	    WritableSheet ws1 = wwb.createSheet("顺坏程度维护表", 1);
	    List<HashMap<String, String>> shcdList = dao.getShcdList();
	    Label ws1row1col1= new Label(0, 0, "损坏程度名称",titlefont);
	    ws1.addCell(ws1row1col1);
	    for (int i = 0; i < shcdList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, shcdList.get(i).get("shcdmc"),titlebody);
	    	 ws1.addCell(tempLabel);
		}
	    
	    /**
		  * sheet3学期
		  */
		 WritableSheet ws2 = wwb.createSheet("学期表", 2);
		 List<HashMap<String, String>> xqList = Base.getXqList();
		 Label ws2row1col1= new Label(0, 0, "学期",titlefont);
		 ws2.addCell(ws2row1col1);
		 for (int i = 0; i < xqList.size(); i++) {
			 Label tempLabel = new Label(0, i+1, xqList.get(i).get("xqmc"),titlebody);
	    	 ws2.addCell(tempLabel);
		 }
		 
		 /**
		  * sheet4
		  */
		 WritableSheet ws3 = wwb.createSheet("楼栋表", 2);
		 List<HashMap<String, String>> lddmList = dao.getLddmList(ccdj);
		 Label ws3row1col1= new Label(0, 0, "楼栋",titlefont);
		 ws3.addCell(ws3row1col1);
		 for (int i = 0; i < lddmList.size(); i++) {
			 Label tempLabel = new Label(0, i+1, lddmList.get(i).get("ldmc"),titlebody);
			 ws3.addCell(tempLabel);
		 }
		 wwb.write();
		 wwb.close();
		 
	}
	
	/**
	 * 
	 * @描述: excel字体
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-13 上午09:51:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat 返回类型 
	 * @throws
	 */
	 public WritableCellFormat getFontStyle(String paras) throws WriteException{
			
			/** 
	         * 定义单元格样式 
	         */ 
		    if("titlehead".equals(paras)){
				  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 14,  
			                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
			                jxl.format.Colour.BLACK);   
				  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//				  wcf_table.setShrinkToFit(true);
		          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
		          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		          return wcf_table;
			}else if("title".equals(paras)){
				  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 12,  
			                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
			                jxl.format.Colour.BLACK);  
				  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
//				  wcf_table.setShrinkToFit(true);
		          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
		          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		          return wcf_table;
			}else if("titlebody".equals(paras)){
				  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
			                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
			                jxl.format.Colour.BLACK);  
				  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
//				  wcf_table.setShrinkToFit(true);
		          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
		          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		          return wcf_table;
			}else if("errorinfo".equals(paras)){
				WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.RED);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
			}
	     
	        return null;
		}
	 
		/**
		 * 
		 * @描述: 导入信息保存
		 * @作者：yxy[工号：1206]
		 * @日期：2016-12-23 上午11:17:13
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param is
		 * @param zhfdrform
		 * @return
		 * HashMap<String,Object> 返回类型 
		 * @throws
		 */
	 	@SuppressWarnings("unchecked")
		@TransactionControl
		public HashMap<String, Object> saveDrExcelInfo(InputStream is,CcdjForm ccdjForm) throws Exception {
			HashMap<String, Object> resultMap= null;
				 resultMap= this.DrExcelInfoCheck(is, ccdjForm);
				//判断excel表格是否为空
				if( resultMap.isEmpty()){
					throw new SystemException(MessageKey.SYS_DR_KBG);
				}else if("true".equals(resultMap.get("result"))){
					List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
					List<String[]> paralistsc = (List<String[]>) resultMap.get("drlistsc");
					List<String[]> paralistfb = (List<String[]>) resultMap.get("drlistfb");
					if( paralist == null ||paralist.size() == 0 ){
						throw new SystemException(MessageKey.SYS_DR_KBG);
					}
				  	 this.saveDrDataIntoDb(paralist, paralistsc, paralistfb);
				  	 resultMap.put("message", MessageKey.SYS_DR_DRCGBZ);
				}else{
					String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),ccdjForm.getFilepath());
					resultMap.put("gid", gid);
					resultMap.put("message", MessageKey.SYS_DR_DRBFSB);
					List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
					List<String[]> paralistsc = (List<String[]>) resultMap.get("drlistsc");
					List<String[]> paralistfb = (List<String[]>) resultMap.get("drlistfb");
					if( paralist == null ||paralist.size() == 0 ){
						resultMap.put("result", "false");
						resultMap.put("message", MessageKey.SYS_DR_DRSBBZ);
						return resultMap;
					}
				  	 this.saveDrDataIntoDb(paralist, paralistsc, paralistfb);
					 logger.info("导入失败！");
				}
			return resultMap;
		}
		
		/**
		 * 
		 * @描述:导入信息验证
		 * @作者：yxy[工号：1206]
		 * @日期：2016-6-20 下午05:41:00
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param is
		 * @param cjffForm
		 * @return
		 * HashMap<String,Object> 返回类型 
		 * @throws
		 */
		@SuppressWarnings("unchecked")
		public HashMap<String, Object> DrExcelInfoCheck(InputStream is,CcdjForm ccdjForm) throws Exception{
			//导入记录数组1 主表数组
			List<String[]> drlist = new ArrayList<String[]>();
			//导入记录数组2 损害程度维护表
			List<String[]> drlistfb = new ArrayList<String[]>();
			//导入记录数组3 删除数组
			List<String[]> drlistsc = new ArrayList<String[]>();
			//错误记录数组
			List<String[]> errorlist = new ArrayList<String[]>();
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", "true");
			Workbook rwb = null;
			List<HashMap<String, String>> wpdmList =  dao.getWpList();
			int num = (wpdmList != null && !wpdmList.isEmpty()) ? wpdmList.size() : 0;
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
		    int clos=rs.getColumns();//得到所有的列
	        int rows=rs.getRows();//得到所有的行
	        //判断excel表格是否为空
	        if(rows < 2){
	        	throw new SystemException(MessageKey.SYS_DR_KBG);
	        }else if(6+num < clos){
	        	//未按模板格式输入
	        	throw new SystemException(MessageKey.SYS_DR_FORMATER);
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){
	        	throw new SystemException(MessageKey.SYS_DR_EXCELREPEAT);
	        }else{
//	        	  HashMap<String, String> dataMap = new HashMap<String, String>();
//	        	  for (int i = 4; i < clos-2; i++) {
//	        		  dataMap.put("shcdmc"+i,  rs.getCell(i,0).getContents());
//				  }
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//取出每行验证数据，塞入lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xn = rs.getCell(0, i).getContents();
	  	        		String xq = rs.getCell(1, i).getContents();
	  	        		String ldmc = rs.getCell(2, i).getContents();
	  	        		String qsh = rs.getCell(3, i).getContents();
	  	        		String bz = rs.getCell(clos-2, i).getContents();
	  	        		String zje = rs.getCell(clos-1, i).getContents();
	  	        		lsmap.put("xn", xn);
	  	        		lsmap.put("xqmc", xq);
	  	        		lsmap.put("ldmc", ldmc);
	  	        		lsmap.put("qsh", qsh);
	  	        		lsmap.put("bz", bz);
	  	        		lsmap.put("zje",zje);
	  	        		/**
	  	        		 * 将不定列的损害程度放入lsmap
	  	        		 */
	  	        	    for (int k = 4; k < clos-2; k++) {
	  	        	    	lsmap.put("shcdmc"+k,  rs.getCell(k,i).getContents());
	  	        	    	lsmap.put("wpdm"+k,  rs.getCell(k,0).getCellFeatures().getComment());
					    }
	  	        		//空行判断
	  	        	    if(this.checkNullRow(lsmap)){
	  	        	    	continue;
	  	        	    }
	  	        		
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, ccdjForm);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		lsmap = (HashMap<String, String>) resultmap.get("resultmap");
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xn"));
	  	        			paralist.add(lsmap.get("xqmc"));
	  	        			paralist.add(lsmap.get("ldmc"));
	  	        			paralist.add(lsmap.get("qsh"));
	  	        			for (int k = 4; k < clos-2; k++) {
	  	        				 paralist.add(lsmap.get("shcdmc"+k));
	  	        			}
	  	        			paralist.add(lsmap.get("bz"));
	  	        			paralist.add(lsmap.get("zje"));
	  	        			if(lsmap != null ){
	  	        				for (Map.Entry<String, String> entry : lsmap.entrySet()){
	  	        					if(entry.getKey().indexOf("yz") != -1){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			String id = UniqID.getInstance().getUniqIDHash().toUpperCase();
	  	        			paralist.add(id);
	  	        			paralist.add(lsmap.get("xn"));
	  	        			paralist.add(lsmap.get("xq"));
	  	        			paralist.add(lsmap.get("lddm"));
	  	        			paralist.add(lsmap.get("qsh"));
	  	        			paralist.add(lsmap.get("zje"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			for (String key : lsmap.keySet()) {
		  	  					if(key.indexOf("shcddm") != -1){
		  	        			  ArrayList<String> paralist1 = new ArrayList<String>();
		  	  					  paralist1.add(id);
		  	  					  paralist1.add(lsmap.get("xn"));
		  	  					  paralist1.add(lsmap.get("xq"));
		  	  					  paralist1.add(lsmap.get("lddm"));
		  	  					  paralist1.add(lsmap.get("qsh"));
		  	  					  paralist1.add(lsmap.get("wpdm"+key.substring(key.length()-1, key.length())));
		  	  					  paralist1.add(lsmap.get(key));
		  	  					  drlistfb.add(paralist1.toArray(new String[]{}));
		  	  					}
	  	  				    }
	  	        			ArrayList<String> paralist2 = new ArrayList<String>();
	  	        			paralist2.add(lsmap.get("xn"));
	  	        			paralist2.add(lsmap.get("xq"));
	  	        			paralist2.add(lsmap.get("lddm"));
	  	        			paralist2.add(lsmap.get("qsh"));
	  	        		    drlistsc.add(paralist2.toArray(new String[]{}));
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
	        	  
	        	  resultMap.put("drlist", drlist);
	        	  resultMap.put("drlistsc", drlistsc);
	        	  resultMap.put("drlistfb", drlistfb);
	        	  resultMap.put("errorlist", errorlist);
	        }
	      
		
		    return resultMap;
		}
		
		/**
		 * 
		 * @描述: 验证每行的记录
		 * @作者：yxy[工号：1206]
		 * @日期：2016-12-23 下午02:11:13
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param lsmap
		 * @param cjffForm
		 * @return
		 * HashMap<String,Object> 返回类型 
		 * @throws
		 */
		public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,CcdjForm ccdjForm){
			String message = "true";
			//为空验证
			if(!this.checkNotNull(lsmap)){
					message = "false";
					lsmap.put("kyz", "学年，学期，楼栋，寝室号，总金额不可为空，各项物品损害程度至少填写一项！");
			}else{
				//学年验证
				String[] xns = lsmap.get("xn").split("-");
				if(xns.length != 2){
					message = "false";
					lsmap.put("xnyz", "学年格式不正确，请输入(xxxx-xxxx):如2016-2017！");
				}else if(lsmap.get("xn").length() > 9){
					message = "false";
					lsmap.put("xnyz", "学年格式不正确，请输入(xxxx-xxxx):如2016-2017！");
				}else{
					try {
						int xn1 = Integer.parseInt(xns[0]);
						int xn2 = Integer.parseInt(xns[1]);
						if(xn2 != xn1+1){
							message = "false";
							lsmap.put("xnyz", "学年格式不正确，请输入(xxxx-xxxx):如2016-2017！");
						}
					} catch (NumberFormatException e) {
						message = "false";
						lsmap.put("xnyz", "学年格式不正确，请输入(xxxx-xxxx):如2016-2017！");
					}
				}
				//学期验证
				List<HashMap<String,String>> xqList = ccdjForm.getXqList();
				String xq = "";
				for (HashMap<String, String> hashMap : xqList) {
					if(hashMap.get("xqmc").equals(lsmap.get("xqmc"))){
						xq = hashMap.get("xqdm");
						lsmap.put("xq", xq);
						break;
					}
				}
				if(StringUtils.isNull(xq)){
					message = "false";
					lsmap.put("xqyz", "学期名称不存在，请确认！");
				}
				
				//楼栋名称和寝室号是否可以导入
				String lddm = dao.checkLdmcDryz(ccdjForm, lsmap.get("ldmc"), lsmap.get("qsh"));
				if(StringUtils.isNull(lddm)){
					message = "false";
					lsmap.put("ldmcqshlhyz", "楼栋，寝室号不存在或者没有权限进行该楼栋数据的导入！");
					
				}
				
				if("true".equals(message)){
					lsmap.put("lddm", lddm);
				}
				
				//备注验证
				if(StringUtils.isNotNull(lsmap.get("bz")) && lsmap.get("bz").length() > 1000){
					message = "false";
					lsmap.put("bzyz", "备注字段不能超过1000字！");
					
				}
				
				//金额验证
				if(lsmap.get("zje").length() > 8){
					message = "false";
					lsmap.put("jecdyz", "费用总计不能超过8位！");
				}
				try {
					Float.parseFloat(lsmap.get("zje"));
				} catch (NumberFormatException e1) {
					message = "false";
					lsmap.put("jeyz", "费用总计请输入整数或一位小数！");
				}
				
			     //不定项损害程度验证
				List<HashMap<String, String>> shcdList = ccdjForm.getShcdList();
				HashMap<String, String> tempMap = new HashMap<String, String>();
				for (String key:lsmap.keySet()) {
					String shcdmc = lsmap.get(key);
					if(StringUtils.isNotNull(shcdmc) && key.indexOf("shcdmc") != -1){
						boolean flag = false;
						for (HashMap<String, String> hashMap : shcdList) {
							if(hashMap.get("shcdmc").equals(shcdmc)){
								flag = true;
								tempMap.put("shcddm"+key.substring(key.length()-1, key.length()), hashMap.get("shcddm"));
							}
						}
						if(!flag){
							message = "false";
							tempMap.put("shcdyz", "损害程度不存在，请确认！");
							break;
						}
					}
				}
				lsmap.putAll(tempMap);
			
					
			}
			
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", message);
			resultMap.put("resultmap", lsmap);
			return resultMap;
		}
		
		/**
		 * @throws WriteException 
		 * 
		 * @描述:将错误excel表格写入服务器
		 * @作者：yxy[工号：1206]
		 * @日期：2016-6-22 下午05:30:28
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param errorlist
		 * @return
		 * @throws IOException
		 * String 返回类型 
		 * @throws
		 */
		public String uploadErrorExcel(List<String[]> errorlist,String filepath) throws Exception{
			 String fileName = System.currentTimeMillis()+"error.xls";
			 String path = filepath+fileName;
	         File file=new File(path);
	         if (!file.exists()) {
	             file.createNewFile();
	         }
	         WritableWorkbook  wwb = Workbook.createWorkbook(file);
			try {
				
				  // 创建工作表
	            WritableSheet ws = wwb.createSheet("错误数据", 0);
	        	WritableCellFormat titlefont =  this.getFontStyle("title");
	    		/**
	    		 * sheet1
	    		 */
	    	    Label row1col1= new Label(0, 0, "学年",titlefont);
	    	    Label row1col2= new Label(1, 0, "学期",titlefont);
	    	    Label row1col3= new Label(2, 0, "楼栋",titlefont);
	    	    Label row1col5= new Label(3, 0, "寝室号",titlefont);
	    		 
	    	    List<HashMap<String, String>> wpList = dao.getWpList();
        	    int size = wpList.size();
	            try {
	            	ws.addCell(row1col1);
	        		ws.addCell(row1col2);
	        		ws.addCell(row1col3);
	        		ws.addCell(row1col5);
	        	    /**
	        	     * 以下表头不固定，按xg_gygl_new_ssccgl_wpwhb(物品维护表)dm顺序输出
	        	     */
	        	    for (int i = 4; i < 4+wpList.size(); i++) {
	        			HashMap<String, String> lsMap = wpList.get(i-4);
	        			Label rowcol = new Label(i, 0, lsMap.get("mc"), titlefont);
	        			WritableCellFeatures cellFeatures = new WritableCellFeatures();  
	        			cellFeatures.setComment(lsMap.get("dm"));  
	        			rowcol.setCellFeatures(cellFeatures);
	        			ws.addCell(rowcol);
	        		}
	        	    
	        		Label row1col6= new Label(4+wpList.size(), 0, "备注",titlefont);
	        		Label row1col7= new Label(4+wpList.size()+1, 0, "费用总计(元)",titlefont);
	        		 
	        		
	        		ws.addCell(row1col6);
	        		ws.addCell(row1col7);
				} catch (RowsExceededException e1) {
					e1.printStackTrace();
				} catch (WriteException e1) {
					e1.printStackTrace();
				}
	            for (int i = 0; i < errorlist.size(); i++) {
	                 for(int j = 0;j<errorlist.get(i).length;j++){
	                	 Label labelId_i= null;
	                	 if(j<6+size){
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
								e.printStackTrace();
							} catch (WriteException e) {
								e.printStackTrace();
							}
	         				
	                	 }
	                		try {
								ws.addCell(labelId_i);
							} catch (RowsExceededException e) {
								e.printStackTrace();
							} catch (WriteException e) {
								e.printStackTrace();
							}
	                	
	                 }
	            }
	            
			}finally{
				 wwb.write();
				 try {
					wwb.close();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
			return fileName;
		}
		
		/**
		 * 
		 * @描述: 批量保存导入数据
		 * @作者：yxy[工号：1206]
		 * @日期：2016-12-23 下午04:49:29
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param paralist
		 * @return
		 * @throws Exception
		 * boolean 返回类型 
		 * @throws
		 */
		public boolean saveDrDataIntoDb(List<String[]> paralist,List<String[]> paralistsc,List<String[]> paralistfb) throws Exception{
			boolean rs = dao.delCcdjb(paralistsc);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			rs = dao.delQswpshb(paralistsc);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			rs = dao.saveDrDataIntoZb(paralist);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			rs = dao.saveDrDataIntoFb(paralistfb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			return rs;
		}
		
		/**
		 * 
		 * @描述:excel表格中是否有重复数据
		 * @作者：yxy[工号：1206]
		 * @日期：2016-8-2 下午04:25:09
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param rs
		 * @param cols
		 * @param rows
		 * @return
		 * boolean 返回类型 
		 * @throws
		 */
		public boolean checkExcelRepeat(Sheet rs,int cols,int rows){
			ArrayList<String> compareList = new ArrayList<String>();
			boolean flag = true;
			for (int i = 0; i < rows; i++) {
				String xn = rs.getCell(0, i).getContents();
				String xq = rs.getCell(1,i).getContents();
				String ldmc = rs.getCell(2,i).getContents();
				String qsh = rs.getCell(3,i).getContents();
				String str = "";
				if(StringUtils.isNotNull(xn)){
					str = str + xn.trim();
				}
				if(StringUtils.isNotNull(xq)){
					str = str + xq.trim();
				}
				if(StringUtils.isNotNull(ldmc)){
					str = str + ldmc.trim();
				}
				if(StringUtils.isNotNull(qsh)){
					str = str + qsh.trim();
				}
				if(StringUtils.isNull(str)){
					continue;
				}
				compareList.add(str);
			}
		    for (int i = 0; i < compareList.size(); i++) {
				for (int j = i+1; j < compareList.size(); j++) {
					if(compareList.get(i).equals(compareList.get(j))){
						flag = false;
						break;
					}
				}
				if(!flag){
					break;
				}
			}
			return flag;
		}
		
		/**
		 * 
		 * @描述: 检查空行
		 * @作者：yxy[工号：1206]
		 * @日期：2016-12-23 下午01:59:20
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @return
		 * boolean 返回类型 
		 * @throws
		 */
		public boolean checkNullRow(HashMap<String, String> yzMap){
			boolean rs = true;
			for (String key : yzMap.keySet()) {
				if(StringUtils.isNotNull(yzMap.get(key))){
					rs = false;
					break;
				}
			}
			return rs;
		}
		
		/**
		 * 
		 * @描述: 验证非空项
		 * @作者：yxy[工号：1206]
		 * @日期：2016-12-23 下午02:17:09
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @return
		 * boolean 返回类型 
		 * @throws
		 */
		public boolean checkNotNull(HashMap<String, String> yzMap){
			boolean rs = true;
			for (String key : yzMap.keySet()) {
				if(StringUtils.isNull(yzMap.get(key)) && key.indexOf("shcdmc") == -1  && !("bz").equals(key)){
					rs = false;
					break;
				}
			}
			if(!rs){
				return rs;
			}
			 rs = false;
			 for (String key : yzMap.keySet()) {
					if(StringUtils.isNotNull(yzMap.get(key)) && key.indexOf("shcdmc") != -1){
						rs = true;
						break;
					}
				}
			return rs;
		}
		
		 /**
	     * 
	     * @描述: 获取导出需要的分组数据
	     * @作者：喻鑫源[工号：1206]
	     * @日期：2017-3-14 下午01:56:17
	     * @修改记录: 修改者名字-修改日期-修改内容
	     * @param t
	     * @param user
	     * @return
	     * @throws Exception
	     * List<HashMap<String,String>> 返回类型 
	     * @throws
	     */
		public List<HashMap<String, String>> getGroupLddmList(CcdjForm t)
		throws Exception {
			return dao.getGroupLddmList(t);
	    }
		
		/**
		 * @throws Exception 
		 * 
		 * @描述: 导出文件
		 * @作者：喻鑫源[工号：1206]
		 * @日期：2017-3-14 下午03:03:42
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param ccdj
		 * @return
		 * File 返回类型 
		 * @throws
		 */
		public File createDcFile(List<HashMap<String, String>> dataList,String ldmc,String username,List<HashMap<String, String>> wpList) throws Exception{
			File tempFile = new File(System.getProperty("java.io.tmpdir")+"/"+Calendar.getInstance().getTimeInMillis()+username+"-"+ldmc+".xls");
			if(!tempFile.exists()){
				tempFile.createNewFile();
			}
			WritableWorkbook wwb = Workbook.createWorkbook(tempFile);
			WritableSheet ws = wwb.createSheet("财产登记情况", 0);
			String title =  ldmc+"号楼宿舍财产清点表";
			WritableCellFormat titlethead =  this.getFontStyle("titlehead");//表头样式
			WritableCellFormat titlefont =  this.getFontStyle("title");//指标行
			WritableCellFormat titlebody =  this.getFontStyle("titlebody");//正文
			Label TitleLabel = new Label(0, 0, title, titlethead);
			ws.addCell(TitleLabel);
			ws.mergeCells(0, 0, 6+wpList.size()-1, 0);
			Label row2col1 = new Label(0, 1, "学年", titlefont);
			Label row2col2 = new Label(1, 1, "学期", titlefont);
			Label row2col3 = new Label(2, 1, "楼栋", titlefont);
			Label row2col4 = new Label(3, 1, "寝室号", titlefont);
			ws.addCell(row2col1);
			ws.addCell(row2col2);
			ws.addCell(row2col3);
			ws.addCell(row2col4);
			int i = 1;
			for (HashMap<String, String> hashMap : wpList) {
				Label tempLabel = new Label(3+i, 1, hashMap.get("wpmc"), titlefont);
				ws.addCell(tempLabel);
				i++;
			}
			int wpnum = wpList.size();
			Label row2colz = new Label(4+wpnum, 1, "责任班级", titlefont);
			Label row2colx = new Label(5+wpnum, 1, "备注", titlefont);
			Label row2coly = new Label(6+wpnum, 1, "费用总计(元)", titlefont);
			ws.addCell(row2colz);
			ws.addCell(row2colx);
			ws.addCell(row2coly);
			if(dataList != null && dataList.size() > 0){
				for (int j = 0; j < dataList.size(); j++) {
					Label rowjcol1 = new Label(0, j+2, dataList.get(j).get("xn"), titlebody);
					Label rowjcol2 = new Label(1, j+2, dataList.get(j).get("xqmc"), titlebody);
					Label rowjcol3 = new Label(2, j+2, dataList.get(j).get("ldmc"), titlebody);
					Label rowjcol4 = new Label(3, j+2, dataList.get(j).get("qsh"), titlebody);
					ws.addCell(rowjcol1);
					ws.addCell(rowjcol2);
					ws.addCell(rowjcol3);
					ws.addCell(rowjcol4);
					for (int k = 0; k < wpnum; k++) {
						Label tempLabel = new Label(4+k, j+2, dataList.get(j).get("shcd"+k), titlebody);
						ws.addCell(tempLabel);
					}
					Label rowhcolz = new Label(4+wpnum, j+2, dataList.get(j).get("bjmc"), titlebody);
					Label rowjcolx =  new Label(5+wpnum, j+2, dataList.get(j).get("bz"), titlebody);
					//Label rowjcoly =  new Label(6+wpnum, j+2, dataList.get(j).get("zje"), titlebody);
					Label rowjcoly =  new Label(6+wpnum, j+2, dataList.get(j).get("avgje"), titlebody);
					ws.addCell(rowhcolz);
					ws.addCell(rowjcolx);
					ws.addCell(rowjcoly);
				}
			}
			wwb.write();
			wwb.close();
			return tempFile;
		}
		
		/**
		 * @throws Exception 
		 * 
		 * @描述: 获取File数组List
		 * @作者：喻鑫源[工号：1206]
		 * @日期：2017-3-16 上午11:13:03
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param groupList
		 * @param dataList
		 * @return
		 * List<File> 返回类型 
		 * @throws
		 */
		public List<File> getFileArryList(List<HashMap<String, String>> groupList,List<HashMap<String, String>> dataList,List<HashMap<String, String>> wpList,String username) throws Exception{
			List<File> files = new ArrayList<File>();
			for (int i = 0; i < groupList.size(); i++) {
				List<HashMap<String, String>> paralist = new ArrayList<HashMap<String,String>>();
				String lddm = groupList.get(i).get("lddm");
				String ldmc = groupList.get(i).get("ldmc");
				for (int j = 0; j < dataList.size(); j++) {
					if(lddm.equals(dataList.get(j).get("lddm"))){
						paralist.add(dataList.get(j));
					}
				}
				File file = this.createDcFile(paralist, ldmc, username, wpList);
				files.add(file);
			}
			return files;
		}
		
		
		/** 
		 * @描述:财产登记导出(需要分班级以及金额分开导出)
		 * @作者：柳俊[工号：1282]
		 * @日期：2017-5-26 上午10:39:41
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param form
		 * @param user
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> 返回类型 
		 * @throws 
		 */
		public List<HashMap<String, String>> getAllListForDc(CcdjForm form,User user) throws Exception{
			Pages pages = (Pages) form.getClass().getMethod("getPages").invoke(form);
			pages.setPageSize(Integer.MAX_VALUE);
			
			form.getClass().getMethod("setPages",Pages.class).invoke(form, pages);
			
			return dao.getPageListForDc(form, user);
		}
		
}
