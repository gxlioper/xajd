/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.hdbljg;

import java.io.IOException;
import java.io.InputStream;
import java.lang.Boolean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.zdydr.service.ZdydrService;
import com.zfsoft.xgxt.dekt.xmwh.DektxmwhForm;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

import javax.servlet.ServletOutputStream;

/**
 * @className	： HdbljgService
 * @description	： TODO(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-22 上午09:55:30
 * @version 	V1.0 
 */

public class HdbljgService extends SuperServiceImpl<HdbljgForm, HdbljgDao> {
	private ZdydrService zdydrService = new ZdydrService();
	/**
	 * @description	：插入
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-22 上午11:07:17
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runInsert(HdbljgForm model) throws Exception{
		
		String[] hdbqs = model.getHdbqs();/*获取活动标签*/
		String[] nlbqs = model.getNlbqs();/*获取能力标签*/
		String jgid = model.getJgid();
		
		boolean result = dao.runInsertNotCommit(model);
		/*插入活动标签*/
		if(null != hdbqs && hdbqs.length > 0){			
			result = dao.BatchInsertHdbqx(jgid, hdbqs);
		}
		/*插入能力标签*/
		if(null != nlbqs && nlbqs.length > 0){
			result = dao.BatchInsertNlbqx(jgid, nlbqs);
		}

		return result;
	}
	
	/**
	 * @description	： 删除
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-22 下午05:30:25
	 * @param values
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runDeleteJg(String[] values) throws Exception {
		
		boolean result = true;
		/*删除活动标签关联表*/
		result = dao.deleteHdbq(values);
		/*删除能力标签关联表*/
		result = dao.deleteNlbq(values);
		if(result){
			return dao.deleteHdjg(values);
		}
		return result;
		
	}
	
	/**
	 * @description	： 修改
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-23 下午03:23:30
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean runUpdate(HdbljgForm model) throws Exception{
		boolean result = true;
		/*删除活动标签关联表*/
		result = dao.deleteHdbq(new String[]{model.getJgid()});
		/*删除能力标签关联表*/
		result = dao.deleteNlbq(new String[]{model.getJgid()});
		if(result){
			String[] hdbqs = model.getHdbqs();/*获取活动标签*/
			String[] nlbqs = model.getNlbqs();/*获取能力标签*/
			String jgid = model.getJgid();
			if(hdbqs != null && hdbqs.length>0){
				/*插入活动标签*/
				result = dao.BatchInsertHdbqx(jgid, hdbqs);
			}
			if(nlbqs != null && nlbqs.length>0){
				/*插入能力标签*/
				result = dao.BatchInsertNlbqx(jgid, nlbqs);
			}
			if(result){
				return dao.runUpdate(model);
			}
		}
		return result;
	}
	
	/**
	 * @description	： 根据申请id获取结果id
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-23 下午03:18:17
	 * @param sqid
	 * @return
	 */
	public String getJgidBySqid(String sqid){
		return dao.getJgidBySqid(sqid);
	}
	
	/**
	 * @description	： 获取model
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-23 下午04:14:05
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public HdbljgForm getModelForJg(HdbljgForm model) throws Exception{
		HdbljgForm model2 = getModel(model);
		if(null==model2){
			model2 = new HdbljgForm();
		}
		if(StringUtils.isNotNull(model.getXh())){ //只有新增补录时条件中才会携带xh
			model2.setXh(model.getXh());
		}
		HashMap<String, String> modelInfo = dao.getModelInfo(model);
		if(null!=modelInfo){
			if(StringUtils.isNotNull(model.getLy())){
				HdblsqshService service = new HdblsqshService();
				HashMap<String,String> map = service.gethdInfo(model.getHdid());
				map.remove("fjpath");//不替换原有附件
				modelInfo.putAll(map);
			}
			BeanUtils.copyProperties(model2, StringUtils.formatData(modelInfo));
		}
		/*学期名称*/
		if(StringUtils.isNotNull(modelInfo.get("xqmc"))){
			model2.setXqmc(modelInfo.get("xqmc"));
		}
		/*活动类型名称*/
		if(StringUtils.isNotNull(modelInfo.get("hdlxmc"))){
			model2.setHdlxmc(modelInfo.get("hdlxmc"));
		}
		/*讲座类型名称*/
		if(StringUtils.isNotNull(modelInfo.get("jzlxmc"))){
			model2.setJzlxmc(modelInfo.get("jzlxmc"));
		}
		if(StringUtils.isNotNull(modelInfo.get("zxkclxmc"))){
			model2.setZxkclxmc(modelInfo.get("zxkclxmc"));
		}
		/*活动标签*/
		if(StringUtils.isNotNull(modelInfo.get("hdbq"))){
			model2.setHdbq(modelInfo.get("hdbq"));
			model2.setHdbqs(modelInfo.get("hdbq").split(","));
			model2.setHdbqmc(modelInfo.get("hdbqmc"));
		}
		/*能力标签*/
		if(StringUtils.isNotNull(modelInfo.get("nlbq"))){
			model2.setNlbq(modelInfo.get("nlbq"));
			model2.setNlbqs(modelInfo.get("nlbq").split(","));
			model2.setNlbqmc(modelInfo.get("nlbqmc"));
		}
		return model2;
	}
	
	/**
	 * @描述: 获取活动标签列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:12:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getHdbqList(){
		return dao.getHdbqList();
	}
	
	/**
	 * @描述: 获取讲座类型列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:12:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJzlxList(){
		return dao.getJzlxList();
	}
	public List<HashMap<String,String>> getZxkcDmList(){
		return dao.getZxkcDmList();
	}
	/**
	 * @描述: 获取能力标签列表
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-6-6 上午10:12:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getAbilityLabelList(){
		return dao.getAbilityLabelList();
	}

	/**
	 *  个性化导入模版，生成工作簿作为浏览器的响应.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-09 14:38
	 * @param outputStream
	 * @return void
	 * @throw
	 */
	public void createWwb(ServletOutputStream outputStream, String drmkdm) {

		// 打开文件
		WritableWorkbook book = null;
		try {
			//获取导入规则，写入excel导入列批注
			List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

			book = Workbook.createWorkbook(outputStream);
			// 生成导入表，参数0表示sheet1，impor为其名称
			WritableSheet sheet1 = book.createSheet("import", 0);

			//填充导入列及批注提示到导入表
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			//设置背景颜色;
			cellFormat1.setBackground(Colour.GREEN);
			for(int i=0;i<drgzxxList.size();i++){
				//考虑根据内容自适应宽度，暂未实现...

				Label label = new Label(i,0, drgzxxList.get(i).get("drlmc"),cellFormat1);
//				sheet1.setColumnView(1, drgzxxList.get(i).get("drlmc").length());

				/*
				 * 主键、唯一(sfzj/sfwy)：不能重复
				 * 必填(sfbt)：不可为空
				 * 最大长度(zdcd)：最大长度为?
				 */
				List<String> pznrList = new ArrayList<String>();
				if("1".equals(drgzxxList.get(i).get("sfwy"))){
					pznrList.add("不能重复");
				}
				if("1".equals(drgzxxList.get(i).get("sfbt"))){
					pznrList.add("不可为空");
				}
				if(!StringUtil.isNull(drgzxxList.get(i).get("zdcd"))){
					pznrList.add("最大长度为："+drgzxxList.get(i).get("zdcd"));
				}

				String pznr = "";
				for(int j=0;j<pznrList.size();j++){
					pznr = pznr+(j+1)+"."+pznrList.get(j);
					if(j!=pznrList.size()-1) pznr+="\r\n";
				}

				WritableCellFeatures cellFeatures = new WritableCellFeatures();
				cellFeatures.setComment(pznr);
				label.setCellFeatures(cellFeatures);

				sheet1.addCell(label);
			}

			//获取辅助表信息，生成并填充辅助表
			List<HashMap<String,Object>> drfzxxAndFzdmxxList = zdydrService.getDrfzxxAndFzdmxxList(drmkdm);

			//循环生成辅助表sheet
			for(int k=0;k<drfzxxAndFzdmxxList.size();k++){
				HashMap<String,Object> drfzxxAndFzdmxx = drfzxxAndFzdmxxList.get(k);
				String dm = (String)drfzxxAndFzdmxx.get("fzdmxx_dm");
				String mc = (String)drfzxxAndFzdmxx.get("fzdmxx_mc");
				WritableSheet sheet = book.createSheet((String) drfzxxAndFzdmxx.get("fzmc"),k+1);
				List<HashMap<String,String>> fzdmxxList = (List<HashMap<String,String>>)drfzxxAndFzdmxx.get("fzdmxxList");
				for(int x=0;x<fzdmxxList.size();x++){
					Label label1 = new Label(0, x, fzdmxxList.get(x).get(dm));
					Label label2 = new Label(1, x, fzdmxxList.get(x).get(mc));
					sheet.addCell(label1);
					sheet.addCell(label2);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 写入数据并关闭文件
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 *  保存导入信息.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-10 20:24
	 * @param inputStream
	 * @param path
	 * @param drmkdm
	 * @return java.util.HashMap<java.lang.String,java.lang.Object>
	 * @throw
	 */
	public HashMap<String,Object> saveImport(InputStream inputStream, String path, String drmkdm) throws Exception {

		HashMap<String, Object> resultMap = null;
		//获取导入列规则
		List<HashMap<String,String>> drgzxxList = zdydrService.getDrgzxxList(drmkdm);

		Workbook wb = null;
		Sheet sheet = null;
		try {
			wb = Workbook.getWorkbook(inputStream);
			sheet = wb.getSheet(0);
			//验证模版头部列  error:01
			resultMap = zdydrService.checkImportHeader(sheet, drgzxxList);
			if((Boolean) resultMap.get("result")){
				//模版无误后进行下面的操作
				//读取所有行存入List<HashMap<String,String>>  每行对应一个HashMap
				List<HashMap<String,String>> excelDataList = zdydrService.getExcelDataList(sheet,drgzxxList);

				if(excelDataList.isEmpty()){
					resultMap.put("totalCount", excelDataList.size());
					return resultMap;
				}

				//保存前的逐行逐列验证 error:02
				resultMap = zdydrService.checkExcelDataList(excelDataList,drgzxxList,new String(),new ArrayList<HashMap<String, Object>>());
				if((Boolean) resultMap.get("result")){
					//验证通过，还需要判断excel数据中本身是否有重复
					resultMap = zdydrService.checkExcelDataRepeat(excelDataList,drgzxxList);
					if((Boolean) resultMap.get("result")){
						//不存在重复则进行最后的插入数据到数据库的操作
						boolean insertResult = this.insertDataIntoDB(excelDataList);
						if(insertResult){
							resultMap.put("totalCount", excelDataList.size());
						}
					}else{
						//存在重复，根据错误数据提示生成excel文件到服务器error:03
						String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
						resultMap.put("errorTipsExcelName", errorTipsExcelName);
					}
				}else{
					//验证不通过，根据错误数据提示生成excel文件到服务器
					String errorTipsExcelName = zdydrService.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
					resultMap.put("errorTipsExcelName", errorTipsExcelName);
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 *  将验证通过后的数据插入数据库.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-11 16:56
	 * @param excelDataList
	 * @return boolean
	 * @throw
	 */
	private boolean insertDataIntoDB(List<HashMap<String, String>> excelDataList) throws Exception {

		List<String []> paraList = new ArrayList<String[]>();
		boolean result = false;
		for(HashMap<String,String> excelData:excelDataList){
			HdbljgForm hdbljgForm = new HdbljgForm();
			hdbljgForm.setXh(excelData.get("xh"));
			hdbljgForm.setXn(excelData.get("xn"));
			hdbljgForm.setXq(excelData.get("xq"));
			hdbljgForm.setHdmc(excelData.get("hdmc"));
			hdbljgForm.setHdsj(excelData.get("hdsj"));
			hdbljgForm.setHdxs(excelData.get("hdxs"));
			hdbljgForm.setHdlx(excelData.get("hdlx"));
			hdbljgForm.setHddd(excelData.get("hddd"));
			hdbljgForm.setCjlx(excelData.get("cjlx"));
			hdbljgForm.setZdzw(excelData.get("zdzw"));
			hdbljgForm.setHdzw(excelData.get("hdzw"));
			hdbljgForm.setHdjx(excelData.get("hdjx"));
			hdbljgForm.setHdxf(excelData.get("hdxf"));
			hdbljgForm.setJzlx(excelData.get("jzlx"));
			hdbljgForm.setXsxxlx(excelData.get("xsxxlx"));
			hdbljgForm.setHdkclx(excelData.get("hdkclx"));
			hdbljgForm.setBz(excelData.get("bz"));
			hdbljgForm.setZbf(excelData.get("zbf"));
			hdbljgForm.setZjrxm(excelData.get("zjrxm"));
			hdbljgForm.setZjrdw(excelData.get("zjrdw"));
			hdbljgForm.setZjrzc(excelData.get("zjrzc"));
			hdbljgForm.setZjrzw(excelData.get("zjrzw"));
			hdbljgForm.setZjrjs(excelData.get("zjrjs"));
			hdbljgForm.setJzjb(excelData.get("jzjb"));
			hdbljgForm.setZyxss(excelData.get("zyxxs"));
			hdbljgForm.setZjrzw(excelData.get("zjrzw"));
			hdbljgForm.setJgid(UniqID.getInstance().getUniqIDHash());
			String hdbq = excelData.get("hdbq");
			if(!StringUtil.isNull(hdbq)){
				hdbljgForm.setHdbqs(hdbq.split(","));
			}else {
				hdbljgForm.setHdbqs(new String[]{});
			}
			String nlbq = excelData.get("nlbq");
			if(!StringUtil.isNull(nlbq)){
				hdbljgForm.setNlbqs(nlbq.split(","));
			}else {
				hdbljgForm.setNlbqs(new String[]{});
			}
			result = insert(hdbljgForm);
		}
		return result;
	}

	@TransactionControl
	public boolean insert(HdbljgForm model) throws Exception{

		String[] hdbqs = model.getHdbqs();/*获取活动标签*/
		String[] nlbqs = model.getNlbqs();/*获取能力标签*/
		String jgid = model.getJgid();

		boolean result = dao.runInsert(model);
		/*插入活动标签*/
		if(null != hdbqs && hdbqs.length > 0){
			result = dao.BatchInsertHdbqx(jgid, hdbqs);
		}
		/*插入能力标签*/
		if(null != nlbqs && nlbqs.length > 0){
			result = dao.BatchInsertNlbqx(jgid, nlbqs);
		}

		return result;
	}
}
