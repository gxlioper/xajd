package xsgzgl.wjcf.general.cfsbgl;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.inter.WjcfCfsbInterface;

/**
 * 
* 
* 类名称：WjcfCfsbAction 
* 类描述：违纪处分处分上报Service
* 创建人：xucy 
* 创建时间：2012-6-20 下午01:18:00 
* 修改备注：  
* @version 
*
 */
public class WjcfCfsbService extends CommService implements WjcfCfsbInterface{
	
	private WjcfCfsbDao dao = new WjcfCfsbDao();

	/**
	 *构建html
	 * @param model
	 * @return
	 *
	 */
	public String createWjcfCfsbHTML(SearchRsModel rsModel,
			WjcfCfsbModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" >");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

				
				html.append("<input type='checkbox' name='primarykey_checkVal' ");
				
				
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if(j==1){
						html.append(" style=\"width:10%\"");
					}
					if(j==2){
						html.append(" style=\"width:5%\"");
					}
					if(j==3){
						html.append(" style=\"width:10%\"");
					}
					if(j==4){
						html.append(" style=\"width:10%\"");
					}
					if(j==5){
						html.append(" style=\"width:10%\"");
					}if(j==6){
						html.append(" style=\"width:10%\"");
					}
					if(j==7){
						html.append(" style=\"width:10%\"");
					}
					if(j==8){
						html.append(" style=\"width:10%\"");
					}
					html.append(">");
					
					html.append(rs[j]);
					
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 *构建表头
	 * @param model
	 * @return
	 *
	 */
	public List<HashMap<String, String>> getWjcfCfsbTop(WjcfCfsbModel model, User user) {
		String[] en = new String[] {"pk" ,"xn","xq","xh", "xm", "cflb","cfyy","cfwh","cfsj","sbr" ,"shjg"  };
		String[] cn = new String[] { "","学年", "学期","学号", "姓名", "处分类别","处分原因","处分文号","处分时间","上报人","审核结果" };
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}

	/**
	 *查询违纪处分列表
	 * @param model
	 * @return
	 *
	 */
	public ArrayList<String[]> getWjcfCfsbfList(WjcfGeneralForm myForm,
			WjcfCfsbModel model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getWjcfList(myForm, model,user);
	}
	
	/**
	 * 工具类   格式化文件路径名s   
	 * @param fileName
	 * @return
	 */
	public String formatFileName(String url){
		if(url==null || "".equals(url)){
			return "";
		}
		String fileName=url;
		if(url.lastIndexOf("\\") > -1){
			fileName=url.substring(url.lastIndexOf("\\")+1, url.length());
		}
		return fileName;
	}
	
	/**
	 * 工具类  确认文件格式化
	 * @param fileName
	 * @return
	 */
	public String isCriterionFile(String fileName){
		if(fileName==null || "".equals(fileName)){
			return "";
		}
		String[] suffix=new String[]{"doc","xls","rar","pdf","txt"};
		for (int i = 0; i < suffix.length; i++) {
			if(fileName.lastIndexOf(suffix[i]) == -1){
				//格式不合格
				return fileName;
			}
		}
		return "";
	}
	
	/**
	 *新增保存
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String saveCfsb(WjcfGeneralForm form,User user) throws Exception{
		form.setSbb(user.getUserName());
		form.setSbsj(GetTime.getNowTime2());
		form.setXn(Base.currXn);
		form.setXq(Base.currXq);
		InputStream instream = form.getFj().getInputStream();
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc()
				.substring(form.getFjmc().lastIndexOf("\\")+1,
						form.getFjmc().length()) : "";
		form.setFjmc(fjmc);
		return dao.saveCfsb(form,instream);
	}
	
	/**
	 *修改保存
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean updateCfsb(WjcfGeneralForm form,User user,String cflbdmXt) throws Exception{
		form.setSbb(user.getUserName());
		form.setSbsj(GetTime.getNowTime2());
		String fjmc = StringUtils.isNotNull(form.getFjmc()) ? form.getFjmc()
				.substring(form.getFjmc().lastIndexOf("\\")+1,
						form.getFjmc().length()) : "";
		form.setFjmc(fjmc);
		boolean flag =  dao.updateCfsb(form,cflbdmXt);
		return flag;
	}

	/**
	 *查询一条记录
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public HashMap<String, String> getCfsb(String cfid) throws Exception {
		return dao.getCfsb(cfid);
	}

	/**
	 * 查询学生已受违纪处分列表（全部记录）
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getYscfqk(String xh){
		return dao.getYscfqk(xh);
	}
	/**
	 * 查询学生已受违纪处分列表（不包含本记录）
	 */
	public List<HashMap<String,String>> getYscfqkNotId(String xh, String cfid){
		return dao.getYscfqkNotId(xh, cfid);
	}
	
	/**
	 * 查询违纪处分审核列表
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getCfsh(String xh){
		return dao.getCfsh(xh);
	}

	/**
	 *删除违纪处分上报
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean delWjsb(String[] pk) {
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : pk){
			params.add(new String[]{str});
		}
		return dao.delWjsb(params);
	}
	

	public List<HashMap<String, String>> getCfshxxList(String cfid) {
		// TODO 自动生成方法存根
		return dao.getCfshxx(cfid);
	}
	
	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public InputStream fjCx(WjcfGeneralForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfsbb where cfid = ?", new String[]{form.getCfId()}, "fj");
		return blob.getBinaryStream();
	}

	public String checkCfsb(WjcfGeneralForm model) {
		// TODO 自动生成方法存根
		return dao.checkCfsb(model);
	}

	public String[] getSpgwByCflb(String cflbdm) {
		try {
			return dao.getSpgwByCflb(cflbdm);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 自定义导出设置
	public List<HashMap<String,String>> getWjcfCfsbfExportList(WjcfGeneralForm myForm, User user) throws IllegalArgumentException,
	SecurityException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException{
		
		return dao.getWjcfCfsbfExportList(myForm,user);
	}
}
