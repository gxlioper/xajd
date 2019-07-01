package xsgzgl.wjcf.general.cfjcgl;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhDao;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.inter.WjcfCfjcInterface;

public class WjcfCfjcService extends CommService implements WjcfCfjcInterface{
	private WjcfCfjcDao dao = new WjcfCfjcDao();
	
	/**
	 *构建html
	 * @param model
	 * @return
	 *
	 */
	public String createWjcfCfjcHTML(SearchRsModel rsModel,
			WjcfCfjcModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				String sqjg = rs[11];
				
				String jckz = rs[12];

				html.append("<tr onclick=\"rowOnClick(this);\" >");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

			
				html.append("<input type='checkbox' name='primarykey_checkVal' ");
				
				
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length-2; j++) {
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
					if(j==10){
						
						if( ( (null==sqjg||"".equals(sqjg))) &&("y".equals(jckz))){
							html.append("<a href=\"#\" onclick=\"sqJc('"+rs[0]+"');return false;\" class=\"a_lssh\" ><font color=\"blue\">申请解除</font></a>");
						}else if("wsh".equals(rs[9])&&"wsh".equals(sqjg)&&"y".equals(jckz)){
							html.append("<a href=\"#\" onclick=\"xgJc('"+rs[0]+"');return false;\" class=\"a_lssh\"><font color=\"blue\">修改</font></a>");
							html.append("&nbsp;&nbsp;");
							html.append("<a href=\"#\" onclick=\"cxJc('"+rs[0]+"');return false;\" class=\"a_lssh\"><font color=\"blue\">撤销</font></a>");
						}else{
							html.append("<a href=\"#\" disabled=\"true\" onclick='return false;' class=\"a_lssh\" style=\"color: #cccccc;\">申请解除</a>");
						}
					}else if(j==9){
						html.append(rs[10]);
						
					}else {
						html.append(rs[j]);
						
					}
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();

	}

	/**
	 *查询结果
	 * @param model
	 * @return
	 * @throws Exception 
	 *
	 */
	public ArrayList<String[]> getWjcfCfjcList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getCfjcList(myForm, model, user);
	}

	/**
	 *构建表头
	 * @param model
	 * @return
	 *
	 */
	public List<HashMap<String, String>> getWjcfCfjcTop(WjcfCfjcModel model,
			User user) {
		String[] en = new String[] {"pk" ,"xn","xqmc","xh", "xm", "cflb","cfyy","cfwh","cfsj","shjg" ,"shjgmc"  };
		String[] cn = new String[] { "","学年", "学期","学号", "姓名", "处分类别","处分原因","解除文号","解除时间","解除结果" ,"操作"};
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}
	
	/**
	 *删除
	 * @param model
	 * @return
	 * @throws Exception 
	 *
	 */
	public boolean delJcSq(String cfId) throws Exception {
		
		return dao.delJcSq(cfId);
	}

	/**
	 *保存
	 * @param model
	 * @return
	 *
	 */
	public boolean saveJcSq(WjcfGeneralForm form) throws Exception {
		form.setSbsj(GetTime.getNowTime2());
		return dao.saveJcSq(form);
	}

	/**
	 *修改
	 * @param model
	 * @return
	 *
	 */
	public boolean updateJcSq(WjcfGeneralForm form) throws Exception {
		return dao.updateJcSq(form);
	}

	/**
	 *获取一条申请记录
	 * @param model
	 * @return
	 *
	 */
	public HashMap<String, String> getJcSq(String cfId) {
		
		return dao.getJcSq(cfId);
	}


	/**
	 * 查询附件信息
	 * @param form
	 * @return
	 */
	public InputStream fjCx(WjcfGeneralForm form) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfb where cfid = ?", new String[]{form.getCfId()}, "fj");
		return blob.getBinaryStream();
	}
	
	/**
	 * 自定义导出【处分解除维护】
	 */
	public List<HashMap<String,String>> getWjcfCfjcExportList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getCfjcwhExportList(myForm, model, user);
	}

	/*
	      描述: @see xsgzgl.wjcf.general.inter.WjcfCfjcInterface#ssFjxz(xsgzgl.wjcf.general.WjcfGeneralForm)
	 */
	
	public InputStream ssFjxz(WjcfGeneralForm myForm) throws Exception {
		WjcfCfsjwhDao dao = new WjcfCfsjwhDao();
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfssb where cfid = ?", new String[]{myForm.getCfId()}, "fj");
		return blob.getBinaryStream();
	}

}
