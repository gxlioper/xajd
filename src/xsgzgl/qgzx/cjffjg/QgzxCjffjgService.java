package xsgzgl.qgzx.cjffjg;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import jxl.write.*;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicService;
import xsgzgl.gyjc.jcjglr.JcjglrForm;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cjffsq.QgzxCjffsqService;
import xsgzgl.qgzx.cssz.QgzxCsszDAO;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.biff.RowsExceededException;
/**
 * 勤工助学-酬金管理-酬金信息管理
 * @author yeyipin
 * @since 2012.7.23
 */
public class QgzxCjffjgService extends BasicService{
	
	
	QgzxCjffjgDAO qgzxCjglDAO = new QgzxCjffjgDAO();
	QgzxCsszDAO qgzxCsszDAO = new QgzxCsszDAO();
	
	
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[]{};
		String[] cn = new String[]{};
		/*取参数配置表中的数据*/
		QgzxCjffsqService qgzxCjffsqService = new QgzxCjffsqService();
		String csz = qgzxCjffsqService.getCspzxx("cjffgs"); 
		
		if("12309".equals(Base.xxdm)){
			 en = new String[] { "", "r", "xn", "bmmc", "ffny", "yffrs","ffrs","jfhbje","zcje","ffje","sjly" };
			 cn = new String[] { "", "行号", "学年", "用人部门", "发放年月","应发放人数","已发放人数","划拨金额<元>","自筹金额<元>","酬金总额<元>","数据来源"};
		}else {
			 en = new String[] { "", "r", "xn", "bmmc", "ffny", "yjsrs","bksrs","ffje","khdj","sjly" };
			 cn = new String[] { "", "行号", "学年", "用人部门", "发放年月","研究生（个）","本科生（个）","发放金额<元>","数据来源"};
		}
		if("xq".equals(QgCommUtilf.getQgzq())){
			if("12309".equals(Base.xxdm)){
				 en = new String[] { "", "r", "xn","xqmc","bmmc", "ffny", "yffrs","ffrs","jfhbje","zcje","ffje","sjly" };
				 cn = new String[] { "", "行号", "学年", "学期", "用人部门", "发放年月","应发放人数","已发放人数","划拨金额<元>","自筹金额<元>","酬金总额<元>","数据来源"};
			}else {
				 en =new String[] { "", "r", "xn","xqmc", "bmmc", "ffny", "yffrs","ffrs","ffje","khdj","sjly" };
				 cn = new String[] { "", "行号", "学年", "学期","用人部门", "发放年月","应发放人数","已发放人数","发放金额<元>","数据来源"};
			}
		}
		if("cjff".equalsIgnoreCase(type)){
			QgzxCsszService qgzxCsszService = new QgzxCsszService();
			HashMap<String, String> map=qgzxCsszService.getCssz();
			String sfsdgwcjsx=map.get("sfsdgwcjsx");
			if("13855".equals(Base.xxdm)){
				if("yes".equals(sfsdgwcjsx)){
					en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","gs", "je","khdj","bz" };
					cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","岗位酬金上限","在岗状态","上岗时间", "工时","金额<元>","考核等级","备注"};
				}else{
					en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","gs", "je","khdj","bz" };
					cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","在岗状态","上岗时间", "工时","金额<元>","考核等级","备注"};
				}
			}else if("10338".equals(Base.xxdm)){
					en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gs", "je","bz" };
					cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质", "(基础工时x基础当量+综合工时x综合当量)x困难等级当量","金额<元>","备注"};
			}else if("12309".equals(Base.xxdm)){
				en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","gwcjbz","gs","jfhb","zc","je","bz" };
				cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","在岗状态","上岗时间","岗位酬金标准","工时","经费划拨<元>","自筹<元>","金额<元>","备注"};
			}else{
				if("yes".equals(sfsdgwcjsx)){
					/*当参数配置表中的酬金发放工时显示为1时显示*/
					if("1".equals(csz)){
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","gs", "je","bz" };
						cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","岗位酬金上限","在岗状态","上岗时间", "工时","金额<元>","备注"};
					}else{
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","je","bz" };
						cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","岗位酬金上限","在岗状态","上岗时间","金额<元>","备注"};
					}
					
					if("10351".equals(Base.xxdm)){
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","gwcjsx","zgztmc","sgsj","sfkns","gs", "je","bz" };
						cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","岗位酬金上限","在岗状态","上岗时间","是否困难生", "工时","金额<元>","备注"};
					}
				}else{
					/*当参数配置表中的酬金发放工时显示为1时显示*/
					if("1".equals(csz)){
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","gs", "je","bz" };
						cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","在岗状态","上岗时间", "工时","金额<元>","备注"};
					}else{
						en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","je","bz" };
						cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","在岗状态","上岗时间","金额<元>","备注"};
					}
					
	                 if("10351".equals(Base.xxdm)){
	                	 en = new String[] { "r", "xh", "xm", "gwmc","gwxzmc","zgztmc","sgsj","sfkns","gs", "je","bz" };
	 					cn = new String[] { "行号", "学号", "姓名", "岗位名称", "岗位性质","在岗状态","上岗时间","是否困难生", "工时","金额<元>","备注"};
					 }

				}
			}
			
		}
		return dao.arrayToList(en, cn);
	}
	
	
	/**
	 * 获得酬金信息List
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCjxxList(QgzxCjffjgForm model) throws Exception {
		return qgzxCjglDAO.getCjxxList(model);
	}
	
	
	/**
	 * 创建页面查询结果
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 2; j < rs.length; j++) {
					html.append("<td id='rs_"+j+"' align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	
	
	
	/**
	 * 获得用人部门列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxCjffjgForm model) {
		return qgzxCjglDAO.getYrbm(model);
	}
	
	/**
	 * 获得用人部门列表
	 * @param
	 * @return
	 */
	public List<HashMap<String, String>> getYrbmOfUser(User user) {
		return qgzxCjglDAO.getYrbmOfUser(user);
	}
	
	
	/**
	 * 获得岗位代码列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getGwdm(QgzxCjffjgForm model) {
		return qgzxCjglDAO.getGwdm(model);
	}
	
	
	/**
	 * 获得岗位人员List
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getGwryList(QgzxCjffjgForm model) throws Exception {
		StringBuilder query = new StringBuilder();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String zgzt = model.getZgzt();
		String gwdm = model.getGwdm();
		String ffny = model.getFfny();
		String xq = model.getXq();
		if(!Base.isNull(xn)){
			query.append(" and xn = '"+xn+"'");
		}
		if(!Base.isNull(yrbm)){
			query.append(" and yrdwdm = '"+yrbm+"'");
		}
		if(!Base.isNull(zgzt)){
			query.append(" and zgzt = '"+zgzt+"'");
		}
		if(!Base.isNull(gwdm)){
			query.append(" and gwdm = '"+gwdm+"'");
		}
		if(!Base.isNull(xq)){
			query.append(" and xq = '"+xq+"'");
		}
//		if(StringUtils.isNotNull(xn) && StringUtils.isNotNull(yrbm)){
//			query.append(" and yrdwdm in (select yrdwdm from xg_qgzx_gwffztb where tjzt = '1' and yrdwdm = '"+yrbm+"'  and xn = '"+xn+"')  ");
//		}
		if(!Base.isNull(ffny)){
			return qgzxCjglDAO.getGwryList(model,query.toString());
		}else{
			return new ArrayList<String[]>();
		}
		
	}

	
	
	/**
	 * 创建酬金发放人员html页面
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 * @throws SQLException 
	 */
	public String createFFryHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user,QgzxCjffjgForm myForm) throws SQLException {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		String sfyxffje = qgzxCsszService.getCssz().get("sfyxffje");
		String dis = ("no".equalsIgnoreCase(sfyxffje))?"disabled":"";
		HashMap<String,String> ffztMap = qgzxCjglDAO.getFfzt(myForm);
		/*取参数配置表中的数据*/
		QgzxCjffsqService qgzxCjffsqService = new QgzxCjffsqService();
		String csz = qgzxCjffsqService.getCspzxx("cjffgs"); 
		//浙江理工个性化_获得勤工当量信息
		String jeDis = ("10338".equalsIgnoreCase(Base.xxdm)?"disabled":"");
		
		StringBuilder html = new StringBuilder();
		html.append("<input type='hidden' id='ffzt' name='ffzt' value='"+ffztMap.get("tjzt")+"'/>");
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr style='height:22px'><td><input type='hidden' id='xh_"+i+"' name='xh' value='"+rs[1]+"'/>");
				html.append("<input type='hidden' id='gwdm_"+i+"' name='gwdm' value='"+rs[2]+"'/>"+rs[0]+"</td>");
				
				html.append("<td>"+rs[1]+"</td><td width='12%'>"+rs[3]+"</td><td>"+rs[4]+"</td><td width='5%'>"+rs[5]+"</td>");
				HashMap<String, String> map=qgzxCsszService.getCssz();
				String sfsdgwcjsx=map.get("sfsdgwcjsx");
				String gwzgcjsx=map.get("gwzgcjsx");
				if("10338".equals(Base.xxdm)){ //浙江理工个性化
					html.append("<td>");
					html.append("(<input type='text' id='jcdlgs_"+i+"' name='jcdlgs' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);getZgs("+i+");' value='"+rs[14]+"'/>");
					html.append(" x <input type='hidden' id='jcdl_"+i+"' name='jcdl' value='"+rs[16]+"' /> "+rs[16]);
					html.append(" + <input type='text' id='zhdlgs_"+i+"' name='zhdlgs' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);getZgs("+i+");' value='"+rs[13]+"'/>");
					html.append(" x <input type='hidden' id='zhdl_"+i+"' name='zhdl' value='"+rs[17]+"' />"+rs[17]);
					html.append(" ) x <input type='hidden' id='kndjdl_"+i+"' name='kndjdl' value='"+rs[15]+"' />"+rs[15]+" = ");
				}else{  
					if(!"12309".equals(Base.xxdm)){
					//如果设置了酬金上限
					if("yes".equals(sfsdgwcjsx)){
						//如果当前岗位未设置酬金上限，则显示基本配置的酬金上限
						if(StringUtils.isNull(rs[11])){
							html.append("<td width='5%'><lable><font color='red'>"+gwzgcjsx+"</font></lable><input name='pzje' type='hidden' value='"+gwzgcjsx+"'/></td>");
						}else{
							html.append("<td width='5%'><lable><font color='red'>"+rs[11]+"</font></lable> <input name='pzje' type='hidden' value='"+rs[11]+"'/></td>");
						}
					}
					}
					html.append("<td width='5%'>"+rs[6]+"</td><td width='5%'>"+rs[7]+"</td>");
					if("12309".equals(Base.xxdm)){//武昌首义个性化字段
						html.append("<td width='5%'>"+rs[14]+"</td>");
					}
					//浙江理工个性化字段，非浙江理工字段隐藏不显示
					html.append("<input type='hidden' id='jcdlgs_"+i+"' name='jcdlgs' value=''/>");
					html.append("<input type='hidden' id='zhdlgs_"+i+"' name='zhdlgs' value=''/>");
					if("10351".equals(Base.xxdm)){
						html.append("<td>"+rs[13]+"");
						html.append("<input type='hidden' name='sfkns' value='"+rs[13]+"' />");	
						html.append("</td>");
					}
					if("1".equals(csz)){
						html.append("<td>");
					}else{
						html.append("");
					}
				}
				
				if("12309".equals(Base.xxdm)){//武昌首义个性化字段
					html.append("<input type='text' id='gs_"+i+"' name='gs' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);Jsje("+i+","+rs[9]+","+rs[10]+","+rs[14]+")' value='"+rs[8]+"' "+jeDis+"/></td>");
					if(rs[8]==""){
						html.append("<td><input type='text' id='jfhb_"+i+"' name='jfhb' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);' value='' /></td>");
						html.append("<td><input type='text' id='zc_"+i+"' name='zc' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);' value='' /></td>");
					}else {
						html.append("<td><input type='text' id='jfhb_"+i+"' name='jfhb' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);' value='"+Integer.valueOf(rs[8])*Integer.valueOf(rs[9])+"' /></td>");
						html.append("<td><input type='text' id='zc_"+i+"' name='zc' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);' value='"+Integer.valueOf(rs[8])*Integer.valueOf(rs[10])+"' /></td>");
					}
					html.append("<input type='hidden' id='gwcjbz_"+i+"' name='gwcjbz' maxlength='4' style='width:50px;' size='3' value='"+rs[14]+"' />");
					html.append("<input type='hidden' id='gwcjsx_"+i+"' name='gwcjsx' maxlength='4' style='width:50px;' size='3' value='"+rs[13]+"' />");
					html.append("<td><input type='text' id='je_"+i+"' name='je' maxlength='7' style='width:50px;' size='4' onblur='checkInputNum(this);autoSetColor(this);' value='"+rs[11]+"' "+dis+"/></td>");
				}else {
					/*根据参数配置表中的值判断工时是否显示【0:不显示,1:显示】，MengWei，20170810*/
					if("1".equals(csz)){
						html.append("<input type='text' id='gs_"+i+"' name='gs' maxlength='4' style='width:50px;' size='3' onblur='checkInputNum(this);scCjbz("+i+");getResult("+i+")' value='"+rs[8]+"' "+jeDis+"/></td>");
					}
						html.append("<td><input type='text' id='je_"+i+"' name='je' maxlength='7' style='width:50px;' size='4' onblur='checkInputNum(this);autoSetColor(this);' value='"+rs[9]+"' "+dis+"/></td>");
				}
				//济南工程职业技术学院个性化考核等级字段
				if("13855".equals(Base.xxdm)){
						html.append("<td><select type='select' id='khdj_"+i+"' name='khdj'value='"+rs[12]+"'>");
						html.append("<option id='yx' value="+"优秀"+"");
						if("优秀".equals(rs[12])){
							html.append(" selected");
						}
						html.append(">优秀</option>");
						html.append("<option id='hg' value="+"合格"+"");
						if("合格".equals(rs[12])){
						html.append(" selected");
						}
						html.append(">合格</option>");
						html.append("<option id='bhg' value="+"不合格"+"");
						if("不合格".equals(rs[12])) {
							html.append(" selected");
						}
						html.append(">不合格</option></select></td>");
				}
				if("12309".equals(Base.xxdm)){//武昌首义个性化字段
					html.append("<td><input type='text' id='bz' name='bz' size='6' style='width:50px;' onblur='checkLen(this,1000)' value='"+rs[12]+"'/></td></tr>");
				} else {
					html.append("<td><input type='text' id='bz' name='bz' size='6' style='width:50px;' onblur='checkLen(this,1000)' value='"+rs[10]+"'/></td></tr>");
				}
			}
		}
		return html.toString();
	}
	
	
	/**
	 * 验证修改信息
	 * @param model
	 * @return
	 */
	public String checkUpdateInfo(QgzxCjffjgForm model) {
		if(isOver(model)){
			return "该部门不存在发放金额或本次发放金额大于划拨剩余金额,请确认!";
		}
		return "true";
	}
	
	

	
	/**
	 * 检测是否超过剩余金额
	 * @param model
	 * @return
	 */
	private boolean isOver(QgzxCjffjgForm model) {
		double zje = 0;
		String[] je = model.getJe().split("!!@@!!");
		for(int i = 0;i < je.length;i++){
			zje+=Double.parseDouble(je[i]);
		}
		return qgzxCjglDAO.isOver(model,zje);
	}
	
	/**
	 * 查询默认参数
	 * @param request 
	 * @return
	 */
	public HashMap<String, String> setCxmrCs(HttpServletRequest request) {
		QgzxCsszService csszService = new QgzxCsszService();
		QgzxGlyglService glyglService = new QgzxGlyglService();
		User user = getUser(request);
		HashMap<String,String> rs = new HashMap<String,String>();
		//如果是勤工管理员
		if(glyglService.sfQggly(user.getUserName())){
			rs.put("isGly", "true");
		}
		
		rs.put("dqsj", DateUtils.getDayOfMonth());
		rs.put("kssj", csszService.getFfkssj());
		rs.put("jssj", csszService.getFfjssj());
		return rs;
	}
	
	/**
	 * 发放默认参数
	 * @param request
	 * @param model 
	 * @return
	 */
	public HashMap<String, String> setFfmrCs(HttpServletRequest request, QgzxCjffjgForm model) {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		HashMap<String,String> rs = qgzxCsszService.getCssz();
		
		//如果有选中后点击“发放”
		String pkValue = request.getParameter("pkValue");
		if(pkValue!=null&&!"".equalsIgnoreCase(pkValue)){
			request.setAttribute("pkValue", pkValue);
			String[] pkV = pkValue.split("!!@@!!");
			model.setXn(pkV[0]);
			model.setYrbm(pkV[1]);
			List<HashMap<String,String>> gwList = getGwdm(model);
			rs.put("xn",pkV[0]);
			rs.put("yrbm", pkV[1]);
			rs.put("ffny", pkV[2]);
			rs.put("dis", "true");
			request.setAttribute("gwList", gwList);
			request.setAttribute("xnList", Base.getXnndList());
			request.setAttribute("bmList", getYrbm(model));
			request.setAttribute("ffnyList", getFFny(model));
		}else{
			QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
			User user = getUser(request);
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			if("xq".equals(QgCommUtilf.getQgzq())){
				model.setXq(null);
			}
			model.setYrbm(user.getUserDep());
			List<HashMap<String,String>> gwList = getGwdm(model);
			
			//如果不是勤工管理员
			if(!qgzxGlyglService.sfQggly(user.getUserName())){
				rs.put("disQg", "true");
			}
			//默认学年
			rs.put("xn", Base.currXn);
			//默认部门
			rs.put("yrbm", user.getUserDep());
			//默认发放年月yyyy-MM
			rs.put("ffny", "");
			request.setAttribute("gwList", gwList);
			request.setAttribute("xnList", Base.getXnndList2());
			request.setAttribute("bmList", getYrbmYgw(model));
			request.setAttribute("ffnyList", getFFny(model));
		}
		return rs;
	}

	
	/**
	 * 获得发放年月
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getFFny(QgzxCjffjgForm model) {
		QgzxCsszService csszService = new QgzxCsszService();
		String ksyf = csszService.getFfksyf();
		String jsyf = csszService.getFfjsyf();
		if(Base.isNull(jsyf)){
			jsyf = ksyf;
		}
		String yffny = qgzxCjglDAO.getGwffny(model);
		List<HashMap<String,String>> ffnyList = scYfList(ksyf,jsyf,yffny);
		return ffnyList;
	}
	
	
	/**
	 * 根据开始时间和结束时间生成列表
	 * @param kssj yyyyMM
	 * @param jssj yyyyMM
	 * @return
	 */
	private List<HashMap<String, String>> scYfList(String kssj, String jssj, String yffny) {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		while(Integer.parseInt(kssj)<=Integer.parseInt(jssj)){
			String curryf = jssj.substring(0,4)+"-"+jssj.substring(4,6);
			if(yffny.indexOf(curryf)==-1){
				HashMap<String,String > map = new HashMap<String,String>();
				map.put("ffny", curryf);
				list.add(map);
			}
			//月份减1
			jssj = yfjy(jssj);
		}
		
		return list;
	}

	/**
	 * 月份减一yyyyMM
	 * @param curryf
	 * @return
	 */
	private String yfjy(String curryf){
		String nf = curryf.substring(0,4);
		String yf = curryf.substring(4,6);
		if("01".equalsIgnoreCase(yf)){
			nf = String.valueOf(Integer.parseInt(nf)-1);
			yf = "12";
		}else{
			yf = String.valueOf(Integer.parseInt(yf)-1);
		}
		return nf+(yf.length()==1?"0"+yf:yf);
	}

	/**
	武昌首义个性化方法
	 **/
	public String  saveCjffForWcsy(QgzxCjffjgForm model) throws Exception{
		String[] xh = model.getXh().split("!!@@!!");
		String[] gwdm = model.getGwdm().split("!!@@!!");
		String ffny = model.getFfny();
		String[] gs = model.getGs().split("!!@@!!");
		String[] je = model.getJe().split("!!@@!!");
		String[] bz = model.getBz().split("!!@@!!");
		String[] khdj = model.getKhdj().split("!!@@!!");
		
		String [] jfhbs = model.getJfhb().split("!!@@!!");
		String [] zcs  = model.getZc().split("!!@@!!");
		
		String cjffr = model.getCjffr();
		List<String[]>params = new ArrayList<String[]>();
		List<String[]>delParams = new ArrayList<String[]>();
		//学生--酬金
		Map<String,List<HashMap<String,String>>> cjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> cjMap = null;
		List<HashMap<String,String>> cjList= null;
		//岗位--酬金
		Map<String,List<HashMap<String,String>>> gwcjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> gwcjMap = null;
		List<HashMap<String,String>> gwcjList= null;
	
		
		if(xh[0]==null ||"".equalsIgnoreCase(xh[0])){
			return "保存失败，该岗位月份无学生信息";
		}
		for (int i = 0; i < gwdm.length; i++) {
			if(gwcjMapList.containsKey(gwdm[i])){
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjMapList.get(gwdm[i]).add(gwcjMap);
				
			}else{
				gwcjList = new ArrayList<HashMap<String,String>>();
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjList.add(gwcjMap);
				gwcjMapList.put(gwdm[i], gwcjList);
			
			}
		}
		for (int i = 0; i < xh.length; i++) {
			if(cjMapList.containsKey(xh[i])){
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjMapList.get(xh[i]).add(cjMap);
				
			}
			else{
				cjList = new ArrayList<HashMap<String,String>>();
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjList.add(cjMap);
				cjMapList.put(xh[i], cjList);
			
			}
		}

		
		for(int i = 0;i < xh.length;i++){
			//重新发放时清空值这里不会删除  bug 注释
			//delParams.add(new String[]{xh[i],gwdm[i],ffny});
			params.add(new String[]{xh[i],gwdm[i],ffny,gs[i],Base.isNull(je[i])?"0":je[i],bz[i],khdj[i],cjffr,model.getXn(),model.getXq(),jfhbs[i],zcs[i]});
		}
		//删除之前所有发放的
		String yffxh=model.getYffxh();
		String yffgw=model.getYffgw();
		int m=-1;
		if(StringUtils.isNotNull(yffxh)){
			String yfxh[]=yffxh.split("!!@@!!");
			String yfgw[]=yffgw.split("!!@@!!");
			for(String str:yfxh){
				m++;
				if(StringUtils.isNull(str)){
					continue;
				}
				System.out.println("del:---xh="+str+"; gwid="+yfgw[m]+"; ffny="+ffny);
				delParams.add(new String[]{str,yfgw[m],ffny});
			}
		}
		//删除
		boolean flag = qgzxCjglDAO.scXsCjffxx(delParams);
		//如果存在结果数据 先删除掉
		if(flag){
			String[] pkValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny(),model.getXq()};
			flag = qgzxCjglDAO.delCjff(pkValue);
		}
		if(flag){
			//保存
			System.out.println("-------------分隔符------------------");
			for(String[] par:params){
				
				System.out.println("add:---xh="+par[0]+"; gwid="+par[1]+"; ffny="+par[2]);
			}
			flag = qgzxCjglDAO.bcCjffxxforWcsy(params);
		}
		if(flag){
			//状态
			model.setTjzt("0");
			flag = qgzxCjglDAO.bcGwffZt(model);
		}
		return flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	
	}
	/**
	 * 保存（提交）酬金发放信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String saveCjffxx(QgzxCjffjgForm model) throws Exception {
		
		String[] xh = model.getXh().split("!!@@!!");
		String[] gwdm = model.getGwdm().split("!!@@!!");
		String ffny = model.getFfny();
		String[] gs = model.getGs().split("!!@@!!");
		String[] je = model.getJe().split("!!@@!!");
		String[] bz = model.getBz().split("!!@@!!");
		String[] khdj = model.getKhdj().split("!!@@!!");
		String[] jcdlgs = model.getJcdlgs().split("!!@@!!");
		String[] zhdlgs = model.getZhdlgs().split("!!@@!!");
		
		String cjffr = model.getCjffr();
		List<String[]>params = new ArrayList<String[]>();
		List<String[]>delParams = new ArrayList<String[]>();
		Map<String,List<HashMap<String,String>>> cjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> cjMap = null;
		List<HashMap<String,String>> cjList= null;
		Map<String,List<HashMap<String,String>>> gwcjMapList = new HashMap<String, List<HashMap<String,String>>>();
		HashMap<String,String> gwcjMap = null;
		List<HashMap<String,String>> gwcjList= null;
		String sfsdcjsx = qgzxCsszDAO.getCssz().get("sfsdgwcjsx");
		StringBuffer gwcjOut = new StringBuffer();
		StringBuffer xscjOut = new StringBuffer();
		StringBuffer gsOut = new StringBuffer();
		
		if(xh[0]==null ||"".equalsIgnoreCase(xh[0])){
			return "保存失败，该岗位月份无学生信息";
		}
		for (int i = 0; i < gwdm.length; i++) {
			if(gwcjMapList.containsKey(gwdm[i])){
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjMapList.get(gwdm[i]).add(gwcjMap);
				
			}else{
				gwcjList = new ArrayList<HashMap<String,String>>();
				gwcjMap=new HashMap<String,String>();
				gwcjMap.put("je", je[i]);
				gwcjMap.put("gs", gs[i]);
				gwcjList.add(gwcjMap);
				gwcjMapList.put(gwdm[i], gwcjList);
			
			}
		}
		for (int i = 0; i < xh.length; i++) {
			if(cjMapList.containsKey(xh[i])){
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjMapList.get(xh[i]).add(cjMap);
				
			}
			else{
				cjList = new ArrayList<HashMap<String,String>>();
				cjMap=new HashMap<String,String>();
				cjMap.put("je", je[i]);
				cjMap.put("gs", gs[i]);
				cjList.add(cjMap);
				cjMapList.put(xh[i], cjList);
			
			}
		}
		//中央民大个性化岗位酬金上限·······
		for (Map.Entry<String, List<HashMap<String,String>>> entry : gwcjMapList
					.entrySet()) {
			//岗位酬金
			double gwcj = 0;
			for (HashMap<String,String> gwcjxxMap : entry.getValue()) {
				if(""!=gwcjxxMap.get("je")&&null!=gwcjxxMap.get("je")){
					gwcj+=Double.parseDouble(gwcjxxMap.get("je"));
				}
				
			}
			HashMap<String,String> gwxx = qgzxCjglDAO.getGwxx(entry.getKey());
			HashMap<String,String> gwcjxx = qgzxCjglDAO.getCjxxByGwdm(entry.getKey(), model.getFfny(), xh);
			if(""!=gwcjxx.get("zje")&&null!=gwcjxx.get("zje")){
				gwcj+=Double.parseDouble(gwcjxx.get("zje"));
			}
			if("yes".equals(sfsdcjsx)&&""!=gwxx.get("gwcjsx")&&null!=gwxx.get("gwcjsx")&&Double.parseDouble(gwxx.get("gwcjsx"))<gwcj){
				gwcjOut.append("["+gwxx.get("gwmc")+"]");
			}
		}
		for (Map.Entry<String, List<HashMap<String,String>>> entry : cjMapList
				.entrySet()) {
			
			//页面发放酬金
			double ffje = 0;
			//工时
			double ffgs = 0;
			for (HashMap<String,String> cjxxMap : entry.getValue()) {
				if(""!=cjxxMap.get("je")&&null!=cjxxMap.get("je")){
				ffje+=Double.parseDouble(cjxxMap.get("je"));
				}
				if(""!=cjxxMap.get("gs")&&null!=cjxxMap.get("gs")){
					ffgs+=Float.parseFloat(cjxxMap.get("gs"));
					}
			}
			
			HashMap<String,String> zjeAndzgs = qgzxCjglDAO.getCjxxByXh(entry.getKey(),model.getFfny(),gwdm);
			if(""!=zjeAndzgs.get("zje")&&null!=zjeAndzgs.get("zje")){
				ffje+=Double.parseDouble(zjeAndzgs.get("zje"));
			}
			if(""!=zjeAndzgs.get("zgs")&&null!=zjeAndzgs.get("zgs")){
				ffgs+=Double.parseDouble(zjeAndzgs.get("zgs"));
			}
			if("gs".equals(model.getSxzd())&&(Double.parseDouble(model.getSxsz())<ffgs)){
				gsOut.append("["+entry.getKey()+"]");
			}
			
		    if(Double.parseDouble(model.getCjsx())<ffje){
				xscjOut.append("["+entry.getKey()+"]");
			}
			
		}
		if("10052".equals(Base.xxdm)&&0!=gwcjOut.length()){
			return "<font color='red'>"+gwcjOut+"</font>"+"发放金额不能大于岗位每月酬金上限"+model.getCjsx()+"元";
		}
		if(0!=gsOut.length()){
			return "<font color='red'>"+gsOut+"</font>"+"发放工时不能大于学生每月工时上限"+model.getSxsz()+"小时";

		}
		//浙江传媒酬金标准档位不同 按工时上限 不验证酬金上限
		/*山东科技大学发放月份，07、08月份不控制发放金额*/
		String ffyf = model.getFfny().substring(5,7);
		if(0!=xscjOut.length() && !"10351".equals(Base.xxdm) && !"11647".equals(Base.xxdm)){
        	if("10424".equals(Base.xxdm)){
        		if(!"07".equals(ffyf) && !"08".equals(ffyf)){
        			return "<font color='red'>"+xscjOut+"</font>"+"发放金额不能大于学生每月酬金上限"+model.getCjsx()+"元";
        		}
        	}else{
        		return "<font color='red'>"+xscjOut+"</font>"+"发放金额不能大于学生每月酬金上限"+model.getCjsx()+"元";
        	}
		}
		for(int i = 0;i < xh.length;i++){
			//重新发放时清空值这里不会删除  bug 注释
			//delParams.add(new String[]{xh[i],gwdm[i],ffny});
			params.add(new String[]{xh[i],gwdm[i],ffny,gs[i],Base.isNull(je[i])?"0":je[i],bz[i],khdj[i],cjffr,jcdlgs[i],zhdlgs[i],model.getXn(),model.getXq()});
		}
		//删除之前所有发放的
		String yffxh=model.getYffxh();
		String yffgw=model.getYffgw();
		int m=-1;
		if(StringUtils.isNotNull(yffxh)){
			String yfxh[]=yffxh.split("!!@@!!");
			String yfgw[]=yffgw.split("!!@@!!");
			for(String str:yfxh){
				m++;
				if(StringUtils.isNull(str)){
					continue;
				}
				System.out.println("del:---xh="+str+"; gwid="+yfgw[m]+"; ffny="+ffny);
				delParams.add(new String[]{str,yfgw[m],ffny});
			}
		}
		//删除
		boolean flag = qgzxCjglDAO.scXsCjffxx(delParams);
		//如果存在结果数据 先删除掉
		if(flag){
			String[] pkValue = new String[]{model.getXn(),model.getYrbm(),model.getFfny(),model.getXq()};
			flag = qgzxCjglDAO.delCjff(pkValue);
		}
		if(flag){
			//保存
			System.out.println("-------------分隔符------------------");
			for(String[] par:params){
				
				System.out.println("add:---xh="+par[0]+"; gwid="+par[1]+"; ffny="+par[2]);
			}
			flag = qgzxCjglDAO.bcCjffxx(params);
		}
		if(flag){
			//状态
			model.setTjzt("0");
			flag = qgzxCjglDAO.bcGwffZt(model);
			
			//结果增加时自动撤销提交，再提交
			List<String[]> paramsPkvalue = new ArrayList<String[]>();
			//原 tjCjffxx 与cxtj方法当发放年月为学年时，xq为"none"。
			String xq=model.getXq();
			if(StringUtils.isNull(xq)){
				xq="none";
			}
			paramsPkvalue.add(new String[]{model.getXn(),model.getYrbm(),model.getFfny(),xq});
			qgzxCjglDAO.cxtj(paramsPkvalue);
			flag=qgzxCjglDAO.tjCjffxx(paramsPkvalue);
		}
		return flag ? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	
	
	/**
	 * 验证酬金发放信息
	 * @param model
	 * @return
	 */
	public String checkTjInfo(QgzxCjffjgForm model) {
		QgzxCsszService qgzxCsszService = new QgzxCsszService();
		//是否经费划拨来控制酬金发放，否则无需验证
		if("no".equalsIgnoreCase(qgzxCsszService.getCssz().get("sfjfhb"))){
			return "";
		}
		String ffje = model.getJe();
		String zje = getTotal(ffje);
		if ("12309".equals(Base.xxdm)) {
			String zcje = model.getZc();
			String zczje = getTotal(zcje);
			model.setZc(zczje);
		}
		model.setJe(zje);
		return qgzxCjglDAO.yzCjffxx(model);
	}
	
	/**
	 * 获得总金额
	 * @param ffje
	 * @return
	 */
	private String getTotal(String ffje) {
		String[] je = ffje.split("!!@@!!");
		double zje = 0;
		for(int i=0;i<je.length;i++){
			zje+=Double.parseDouble(je[i]);
		}
		return String.valueOf(zje);
	}


	/**
	 * 删除酬金发放信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String czCjffxx(QgzxCjffjgForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length;i++){
			params.add(pkValue[i].split("!!@@!!"));
		}
		boolean flag = false;
		if("del".equalsIgnoreCase(model.getDoType())){
			qgzxCjglDAO.cxtj(params);
			flag = qgzxCjglDAO.scCjffxx(params);
		}else if("tj".equalsIgnoreCase(model.getDoType())){
			flag = qgzxCjglDAO.tjCjffxx(params);
		}
		return flag ? MessageInfo.MESSAGE_WORK_SUCCESS : MessageInfo.MESSAGE_WORK_FALSE;
	}
	public String cxtj(QgzxCjffjgForm model) throws Exception {
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length;i++){
			params.add(pkValue[i].split("!!@@!!"));
		}
		boolean flag = false;
		flag=qgzxCjglDAO.cxtj(params);
		return flag ? MessageInfo.MESSAGE_CANCEL_NEW_SUCCESS : MessageInfo.MESSAGE_CANCEL_NEW_FALSE;
	}
	
	
	public boolean isHaveFfxx(QgzxCjffjgForm qf){
		String[] pkValue = qf.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length;i++){
			params.add(pkValue[i].split("!!@@!!"));
		}
		return qgzxCjglDAO.isHaveFfxx(params);
	}
	/**
	 * 酬金信息个人查看
	 * @param model
	 * @return
	 */
	public HashMap<String, String> cjxxCk(QgzxCjffjgForm model) {
		HashMap<String,String> rs = qgzxCjglDAO.cjxxCk(model);
		List<HashMap<String,String>> cjmxList = qgzxCjglDAO.getCjmxList(model);
		rs.put("cjmxHtml", createCjmxHtml(cjmxList));
		rs.put("listSize", cjmxList.size()+"");
		rs.put("xqmc", Base.getXqmcForXqdm(rs.get("xq")));
		return rs;
	}

	
	/**
	 * 创建酬金发放明细html
	 * @param
	 * @return
	 */
	private String createCjmxHtml(List<HashMap<String, String>> rsArrList) {
		/*取参数配置表中的【酬金发放工时显示】，0:不显示,1:显示*/
		QgzxCjffsqService qgzxCjffsqService = new QgzxCjffsqService();
		String csz = qgzxCjffsqService.getCspzxx("cjffgs");
		
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				HashMap<String, String> rs = rsArrList.get(i);
				html.append("<tr><td>"+rs.get("r")+"</td>");
				html.append("<td>"+rs.get("xh")+"</td>");
				html.append("<td>"+rs.get("xm")+"</td>");
				html.append("<td>"+rs.get("gwmc")+"</td>");
				html.append("<td>"+rs.get("gwxzmc")+"</td>");
				if("1".equals(csz)){
					html.append("<td>"+(Base.isNull(rs.get("gs"))?"":rs.get("gs"))+"</td>");
				}
				html.append("<td>"+(Base.isNull(rs.get("je"))?"":rs.get("je"))+"</td>");
				html.append("<td>"+(Base.isNull(rs.get("yhkh"))?"":rs.get("yhkh"))+"</td>");
				html.append("<td id='bz"+i+"' title='"+(Base.isNull(rs.get("bz"))?"":rs.get("bz"))+"'>"+(Base.isNull(rs.get("bz"))?"":rs.get("bz"))+"</td>");
				html.append("<td>"+(Base.isNull(rs.get("cjffrXm"))?"":rs.get("cjffrXm"))+"</td></tr>");
			}
		}
		return html.toString();
	}


	/**
	 * 生成酬金标准（学号，岗位代码）
	 * @param model
	 * @return
	 */
	public String scCjbz(QgzxCjffjgForm model) {
		KnsjgDao knsjgDao = new KnsjgDao();
		String zd1 = "";
		String zd2 = qgzxCjglDAO.getGwxxMap(model).get("gwxzmc");
		HashMap<String,String> knsrddc = knsjgDao.getKnsrddc(model.getXh(),model.getXn());
		if(knsrddc!=null && knsrddc.size()!=0){
			zd1 = knsrddc.get("dcmc");
		}
	
		String cjbz = qgzxCjglDAO.getCjbz(new String[]{zd1,zd2,Base.xxdm});
		if(Base.isNull(cjbz)){
			QgzxCsszService qgzxCsszService = new QgzxCsszService();
			cjbz = qgzxCsszService.getCssz().get("cjbz");
		}
		return cjbz;
	}

	
	/**
	 * 自动批量提交酬金发放信息（过期的）
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public boolean aotoTjCjffxx() throws Exception {
		return qgzxCjglDAO.aotoTjCjffxx();
	}


	/** 
	 * @描述: 面页初始化
	 * @作者：戚立明[工号：964]
	 * @日期：2014-4-21 下午04:46:50
	 * @param request
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> setFfmrCsNew(HttpServletRequest request,
			QgzxCjffjgForm model) {QgzxCsszService qgzxCsszService = new QgzxCsszService();
			List<HashMap<String, String>> bmList= new ArrayList<HashMap<String, String>>();
			HashMap<String,String> rs = qgzxCsszService.getCssz();
			User user = getUser(request);
			//如果有选中后点击“发放”
			String pkValue = request.getParameter("pkValue");
			if(pkValue!=null&&!"".equalsIgnoreCase(pkValue)){
				request.setAttribute("pkValue", pkValue);
				String[] pkV = pkValue.split("!!@@!!");
				model.setXn(pkV[0]);
				model.setXq(pkV[3]);
				if("none".equals(pkV[3])){
					model.setXq(null);
				}
				model.setYrbm(pkV[1]);
				List<HashMap<String,String>> gwList = getGwdm(model);
				rs.put("xn",pkV[0]);
				rs.put("yrbm", pkV[1]);
				rs.put("ffny", pkV[2]);
				rs.put("cjffr", user.getUserName());
				rs.put("dis", "true");
				request.setAttribute("gwList", gwList);
				request.setAttribute("xnList", Base.getXnndList());
				request.setAttribute("bmList", getYrbmYgw(model));
				request.setAttribute("ffnyList", getFFny(model));
			}else{
				QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
				model.setXn(Base.currXn);
				if("xq".equals(QgCommUtilf.getQgzq())){
					model.setXq(Base.currXq);
				}
				model.setCjffr(user.getUserName());
				model.setYrbm(user.getUserDep());
				List<HashMap<String,String>> gwList = getGwdm(model);
				
				//如果不是勤工管理员
				if(!qgzxGlyglService.sfQggly(user.getUserName())){
					bmList=getYrbmOfUser(user);
					rs.put("disQg", "true");
				}else{
					bmList=getYrbmYgw(model);
				}
				//默认学年
				rs.put("xn", Base.currXn);
				//酬金发放人
				rs.put("cjffr", user.getUserName());
				//默认部门
				rs.put("yrbm", user.getUserDep());
				request.setAttribute("gwList", gwList);
				request.setAttribute("xnList", Base.getXnndList());
				request.setAttribute("bmList", bmList);
				request.setAttribute("ffnyList",getFFny(model) );
				//默认发放年月yyyy-MM
			   String dqrq = DateUtils.getYear()+"-"+DateUtils.getMonth();
			   rs.put("ffny", dqrq);
			}
			QgCommUtilf.setCssz(request);
			request.setAttribute("xqList", Base.getXqList());
			return rs;
	}
	

	/**
	 * 获得用人部门列表（有岗位的）
	 * @param model
	 * @return 部门列表
	 */
	public List<HashMap<String, String>> getYrbmYgw(QgzxCjffjgForm model) {
		return qgzxCjglDAO.getYrbmYgw(model);
	}
	
	/**
	 * 
	 * @描述:查询经费多少，已经使用多少，剩余多少
	 * @作者：cq [工号：785]
	 * @日期：2014-8-21 下午03:55:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getJftx (User user){
		return qgzxCjglDAO.getJftx(user);
	}


	/** 
	 * @描述:根据一条酬金发放明细生成Excel文件
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月7日 上午8:59:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param path
	 * @param model
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File getCjxxExcel(String path, QgzxCjffjgForm model) {
		HashMap<String,String> rs = qgzxCjglDAO.cjxxCk(model);
		List<HashMap<String,String>> cjmxList = qgzxCjglDAO.getCjmxList(model);
		
		File file = new File(path,"cjmx_"+System.currentTimeMillis()+".xls");
		
		// 打开文件
		WritableWorkbook book = null;
		
		 try {
		 	book = Workbook.createWorkbook(file);
			WritableSheet sheet1 = book.createSheet("酬金发放明细表", 0);
			//合并单元格
			sheet1.mergeCells(0, 0, 7, 0);

			sheet1.mergeCells(0, 1, 7, 1);
			sheet1.mergeCells(0,2,7,2);

			//单元格格式
			WritableCellFormat wcf1 = new WritableCellFormat();
			WritableCellFormat wcf2 = new WritableCellFormat();
			WritableCellFormat wcf3 = new WritableCellFormat();
			WritableCellFormat wcf4 = new WritableCellFormat();
			WritableFont font1 = new WritableFont(WritableFont.createFont(""),18,WritableFont.BOLD,false);
			wcf1.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
			wcf1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
			wcf1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); //设置边框
			wcf1.setFont(font1);

			 WritableFont font2 = new WritableFont(WritableFont.createFont(""),15,WritableFont.BOLD,false);
			 wcf3.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
			 wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
			 wcf3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); //设置边框
			 wcf3.setFont(font2);

			 WritableFont font3 = new WritableFont(WritableFont.createFont(""),10,WritableFont.BOLD,false);
			 wcf4.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
			 wcf4.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			 wcf4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
			 wcf4.setWrap(true);
			 wcf4.setFont(font3);

			wcf2.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
			wcf2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			wcf2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
			 wcf2.setWrap(true);


			Label label_c0r0 = new Label(0,0,"西安交通大学",wcf1);
			String ffny = rs.get("ffny").replaceAll("-","年");
			Label label_c0r1 = new Label(0,1,ffny+"月（"+rs.get("yrdwmc")+"）本科生勤工助学工资发放情况",wcf3);
		 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Label label_c0r2 = new Label(0,2,"制表单位（盖章）：                                                         制表日期："+sdf.format(new Date()),wcf2);

			Label label_c0r3 = new Label(0,3,"行号",wcf4);
			Label label_c1r3 = new Label(1,3,"岗位名称",wcf4);
			Label label_c2r3 = new Label(2,3,"学号",wcf4);
			Label label_c3r3 = new Label(3,3,"姓名",wcf4);
			Label label_c4r3 = new Label(4,3,"班级",wcf4);
			Label label_c5r3 = new Label(5,3,"累计工作时间",wcf4);
			Label label_c6r3 = new Label(6,3,"金额（元）",wcf4);
			Label label_c7r3 = new Label(7,3,"备注",wcf4);

			sheet1.addCell(label_c0r0);

			sheet1.addCell(label_c0r1);

			sheet1.addCell(label_c0r2);

			sheet1.addCell(label_c0r3);
			sheet1.addCell(label_c1r3);
			sheet1.addCell(label_c2r3);
			sheet1.addCell(label_c3r3);
			sheet1.addCell(label_c4r3);
			sheet1.addCell(label_c5r3);
			sheet1.addCell(label_c6r3);
			sheet1.addCell(label_c7r3);


			for(int i=0;i<cjmxList.size();i++){
				Label label0 = new Label(0,4+i,1+i+"",wcf2);
				Label label3 = new Label(1,4+i,cjmxList.get(i).get("gwmc"),wcf2);
				Label label1 = new Label(2,4+i,cjmxList.get(i).get("xh"),wcf2);
				Label label2 = new Label(3,4+i,cjmxList.get(i).get("xm"),wcf2);

				Label label4 = new Label(4,4+i,cjmxList.get(i).get("bjmc"),wcf2);
				Label label5 = new Label(5,4+i,cjmxList.get(i).get("gs"),wcf2);
				Label label6 = new Label(6,4+i,cjmxList.get(i).get("je"),wcf2);

				Label label7 = new Label(7,4+i,cjmxList.get(i).get("bz"),wcf2);

				sheet1.addCell(label0);
				sheet1.addCell(label1);
				sheet1.addCell(label2);
				sheet1.addCell(label3);
				sheet1.addCell(label4);
				sheet1.addCell(label5);
				sheet1.addCell(label6);

				sheet1.addCell(label7);
			}

			sheet1.setColumnView(0, 5);
			sheet1.setColumnView(1, 15);
			sheet1.setColumnView(2, 15);
			sheet1.setColumnView(3, 15);
			sheet1.setColumnView(4, 15);
			sheet1.setColumnView(5, 10);
			sheet1.setColumnView(6, 8);

			sheet1.setColumnView(7, 20);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}finally {
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
		 
		return file;
	}

	public List<HashMap<String,String>> getCjffList(QgzxCjffjgForm t, User user) throws Exception {
		return qgzxCjglDAO.getCjffList(t,user);
	}
}
