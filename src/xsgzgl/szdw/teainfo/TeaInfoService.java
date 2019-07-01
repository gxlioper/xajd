package xsgzgl.szdw.teainfo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import com.zfsoft.utils.StringUtil;

import xgxt.DAO.PicDAO;
import xgxt.utils.String.StringUtils;

public class TeaInfoService {

	private TeaInfoDAO dao = new TeaInfoDAO();
	
	
	/**
	 * 验证是否存在职工号
	 * @param model
	 * @return
	 */
	public boolean checkZgh(TeaInfoForm model){
		
		String count = dao.getTeaCount(model);
		
		if (Integer.valueOf(count) > 0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 增加教师第一步---保存职工号、姓名
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public boolean addTea(TeaInfoForm model){
		
		try {
			return dao.addTea(model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * 查询教师信息
	 * @param model
	 * @return
	 */
	public Map<String,String> getTeaInfo(TeaInfoForm model){
		
		String zgh = model.getZgh();
		
		if (StringUtils.isNotNull(zgh)){
			return dao.getTeaInfo(zgh);
		}
		
		return null;
	}
	
	
	/**
	 * 加载职务列表
	 * @return
	 */
	public List<HashMap<String,String>> getZwList(){
		
		return dao.getZwList();
	}
	
	/**
	 * 加载职称列表
	 * @return
	 */
	public List<HashMap<String,String>> getZcList(){
		
		return dao.getZcList();
	} 
	/**
	 * 加载编制列表
	 * @return
	 */
	public List<HashMap<String,String>> getBzlxList(){
		
		return dao.getBzlxList();
	} 
	/**
	 * 加载专职辅导员身份类型列表
	 * @return
	 */
	public List<HashMap<String,String>> getZzFdyList(){
		
		return dao.getZzFdyList();
	} 

	
	
	
	/**
	 * 保存教师信息-构建输入值
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveTeaInfo(TeaInfoForm model) throws Exception{
		String[] field = new String[]{"xm","xb","mz","jg","zzmm",
				"csrq","sfzh","xw","xl","byyx","sxzy","lxdh","yddh",
				"dzyx","yzbm","bgdh","cz","bgdd","jtzz","txdz","zyzz",
				"bmdm","sjdw","zw","zwrzsj","xsgzyjfx","zc","jsrzsj",
				"fblw","gzjl","grhjqk","kyjl","pxqk","bz","kzzd1",
				"kzzd2","kzzd3","kzzd4","kzzd5","sfjryx","kzzd6",
				"kzzd7","kzzd8","kzzd9","kzzd10","kzzd11","kzzd12",
				"kzzd13","kzzd14","kzzd15","kzzd16","kzzd17","kzzd18",
				"kzzd19","kzzd20","ssbyyx","ssbyzy"};
		String[] input = new String[field.length];
		StringBuilder sql = new StringBuilder();
		Class c = model.getClass();
		
		for (int i = 0 ; i < field.length ; i++){
			sql.append(field[i])
			   .append("=?");
			
			if (i != field.length-1){
				sql.append(",");
			}
			
			StringBuilder methodName = new StringBuilder();
			methodName.append("get")
					  .append(field[i].substring(0, 1).toUpperCase())
					  .append(field[i].substring(1));
			
			input[i] = (String) c.getMethod(methodName.toString()).invoke(model);
			input[i] = StringUtils.isNull(input[i]) ? "" : input[i];
		}
		
		return dao.saveTeaInfo(model, sql.toString(), input);
	}
	
	
	
	/**
	 * 保存教师照片
	 * @param model
	 * @return
	 */
	public String saveTeaPic(TeaInfoForm model){
		PicDAO picDao = new PicDAO();
		
		String zgh = model.getZgh();
		FormFile file = model.getTeaPic();
		String fileName = file.getFileName();
		boolean isAllowType = fileName.endsWith("jpg") 
		   || fileName.endsWith("gif") 
		   || fileName.endsWith("png")
		   || fileName.endsWith("bmp") || fileName.endsWith("JPG") || fileName.endsWith("GIF") || fileName.endsWith("PNG")|| fileName.endsWith("BMP");

		if (!isAllowType){
			return "上传失败，非法的文件格式！";
		}

		if (file.getFileSize() > 1024 * 1024){
		return "上传失败，文件大小超过1M！";
		} else {
			try {
				picDao.savePic(file.getInputStream(), zgh, "");
				return "上传成功！";
			} catch (Exception e) {
				e.printStackTrace();
				return "上传失败，请重新上传！";
			} 
		}
	}
	/** 
	 * @描述: 查询照片流
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-1 下午03:37:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * InputStream 返回类型 
	 * @throws 
	 */
	public InputStream getTeaPhotoStream(String zgh){
		PicDAO picDao = new PicDAO();
		if (StringUtil.isNull(zgh)){
			throw new NullPointerException();
		}
		
		InputStream is = null;
		try {
			is = picDao.getPic(zgh, "fdy");
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return is;
	}
	
	
	/**
	 * 查询教师信息列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getTeaList(TeaInfoForm model) throws Exception{
		
		return dao.getTeaList(model);
	}
	
	
	/**
	 * 教师信息导出查询
	 * @param model
	 * @param colList
	 * @return
	 * @throws Exception 
	 */
	public List<String[]> getExpTeaList(TeaInfoForm model,String[] colList) throws Exception{
		model.getPages().setPageSize(Integer.MAX_VALUE);
		return dao.getExpTeaList(model, colList);
	}
	
	
	
	/**
	 * 删除教师信息
	 * @param pkValue
	 * @return
	 */
	public boolean delTeaInfo(String[] pkValue){
		
		if (null != pkValue && pkValue.length > 0){
			List<HashMap<String,String>> teaClass = dao.getTeaClass(pkValue);
			
			if (teaClass.size() > 0){
				return false;
			} else {
				try {
					return dao.delTeaInfo(pkValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * 教师详细信息-扩展字段配置信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getOtherInfo(TeaInfoForm model){
		List<HashMap<String,String>> zdxxList = dao.getZdxxList("teainfo");
		
		for (HashMap<String, String> map : zdxxList) {
			String lylx = map.get("zdlylx");
			String zdz = "";

			if ("method".equalsIgnoreCase(lylx)) {
				//TODO 目前没有配置method
				/*Class c = PjpyOtherInfo.class;
				// 反射调用配置的方法
				zdz = (String) c.getMethod(map.get("zdly"),
						new Class[] { String.class }).invoke(
						new PjpyOtherInfo(), new String[] { xh });*/
			} else if ("view".equalsIgnoreCase(lylx)) {
				// 调用视图
				zdz = getTeaInfo(model).get(map.get("zdm"));
			}
			map.put("zdz", zdz);
		}

		return zdxxList;
	}
	
	
	
	/**
	 * 查询列选所用列
	 * @return
	 */
	public List<HashMap<String,String>> getColList(){
		
		return dao.getColList("view_fdyxx");
		
	}
	
	
	/**
	 * 下拉列表设置表数据查询
	 * @param tableName
	 * @return
	 */
	public List<HashMap<String,String>> getDataList(String tableName){
		
		if (StringUtils.isNotNull(tableName)){
			return dao.getDataList(tableName);
		}
		return null;
	}


	
	/**
	 * 查询带班信息
	 * @param zgh
	 * @return
	 */
	public List<HashMap<String,String>> getTeaClass(String[] zgh){
	
		return dao.getTeaClass(zgh);
	}
	
	
	/**
	 * 同步教师信息到用户表
	 * @param model
	 * @param pkValue
	 * @return
	 */
	public boolean tbTeaInfo(TeaInfoForm model,String[] pkValue){
		

		if (null != pkValue && pkValue.length > 0){
			
			try {
				return dao.tbTeaInfo(model, pkValue);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 加载岗位类别列表
	 * @return
	 */
	public List<HashMap<String,String>> getGwlbList(){
		
		return dao.getGwlbList();
	} 
	public List<HashMap<String,String>> getJslbList(){
		
		return dao.getJslbList();
	} 
}
