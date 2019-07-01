package xgxt.utils.util.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import xgxt.utils.util.Tool;

public class CreateApp {
	final static String projectName = "newfun";

	final static String srcFolder = "src";

	final static String basePage = "xgxt";

	final static String utilPage = "xgxt.utils.util";

	final static String fileConfigStruts = "F:\\j2ee\\eclipse\\workspace\\xgxt\\xgWeb\\WEB-INF\\struts-" + projectName+ ".xml";

	final public static void main(String[] args) throws Exception {
		String appName = null;
		ParameterBean pbean = new ParameterBean();
		String[] tables = new String[] { "jxgl_jgxxb" };
		
		// String[] tables = new String[] { "T_FLOWINSTANCE" };
		for (int i = 0; i < tables.length; i++) {
			appName = tables[i].toLowerCase();
			if (!appName.equalsIgnoreCase("")) {
				pbean.setTableName(appName);
				pbean.setBeans(DBHandle.getTableStruct(pbean.getTableName()));
				if (appName.toLowerCase().startsWith("jxgl_")) {
					pbean.setAppName(appName.substring(5));
				} else {
					pbean.setAppName(appName);
				}
			} else {
				return;
			}
			String columCreateTime = "";
			if (!columCreateTime.equalsIgnoreCase("")) {
				pbean.setColumCreateTime(columCreateTime);
			} else {
				pbean.setColumCreateTime("createtime");
			}
			pbean.setColumId("id");
			
			//实体结构
			Tool.print("创建实体层:" + Tool.toUpcaseFirstChar(pbean.getAppName())
					+ ".java");
			if (saveClassFile("entitys", pbean)) {
				Tool.print("实体层:" + Tool.toUpcaseFirstChar(pbean.getAppName())
						+ ".java创建成功!");
			} else {
				Tool.print("实体层:" + Tool.toUpcaseFirstChar(pbean.getAppName())
						+ ".java创建失败!");
			}
			
			//参数
			Tool.print("创建Parameter层:"
					+ Tool.toUpcaseFirstChar(pbean.getAppName())
					+ "Parameter.java");
			if (saveClassFile("parameter", pbean)) {
				Tool.print("Parameter层:"
						+ Tool.toUpcaseFirstChar(pbean.getAppName())
						+ "Parameter.java创建成功!");
			} else {
				Tool.print("Parameter层:"
						+ Tool.toUpcaseFirstChar(pbean.getAppName())
						+ "Parameter.java创建失败!");
			}

			//form
			Tool.print("创建Form层:" + Tool.toUpcaseFirstChar(pbean.getAppName())
					+ "Form.java");
			if (saveClassFile("form", pbean)) {
				Tool.print("Form层:"
						+ Tool.toUpcaseFirstChar(pbean.getAppName())
						+ "Form.java创建成功!");
			} else {
				Tool.print("Form层:"
						+ Tool.toUpcaseFirstChar(pbean.getAppName())
						+ "Form.java创建失败!");
			}

			//业务层
			Tool.print("创建operation层:" + pbean.getAppName() + "Operation.java");
			if (saveClassFile("operation", pbean)) {
				Tool.print("operation层:" + pbean.getAppName() + "Operation.java创建成功!");
			} else {
				Tool.print("operation层:" + pbean.getAppName() + "Operation.java创建失败!");
			}

			//action层
			Tool.print("Action:" + pbean.getAppName() + "Action.java");
			if (saveClassFile("action", pbean)) {
				Tool.print("action层:" + pbean.getAppName() + "Action.java创建成功!");
			} else {
				Tool.print("action层:" + pbean.getAppName() + "Action.java创建失败!");
			}
			
			 saveJSPFile("list", pbean); 
			 saveJSPFile("edit", pbean); 
			 updateStrutsConfig(pbean);
			// saveJSPFile("view", pbean);
			
			Tool.print("OK!");
		}
	}

	final public static Boolean saveClassFile(String type, ParameterBean pbean)
			throws Exception {
		try {
			String pack = CreateApp.basePage  + "." + projectName + "." + type.toLowerCase();
			String fileName = Tool.toUpcaseFirstChar(pbean.getAppName()) + Tool.toUpcaseFirstChar(type);
			if (type.endsWith("entitys")) {
				fileName = Tool.toUpcaseFirstChar(pbean.getAppName());
			}

			String _path = Tool.replaceAll(CreateApp.srcFolder + "." + pack,
					".", "/");
			File pf = new File(_path + "/");
			if (!pf.isDirectory())
				pf.mkdirs();
			File file = new File(_path + "/" + fileName + ".java");
			FileWriter writer = new FileWriter(file);
			String body = "";
			if (type.endsWith("entitys")) {
				body = getVoClass(pbean);
			}
			if (type.endsWith("parameter")) {
				body = getParameterClass(pbean);
			}
			if (type.endsWith("form")) {
				body = getFormClass(pbean);
			}
			if (type.endsWith("operation")) {
				body = getOperationClass(pbean);
			}
			if (type.endsWith("action")) {
				body = getActionClass(pbean);
			}
			writer.write(body);
			writer.flush();
			writer.close();
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static String getActionClass(ParameterBean pbean) {

		String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
//		String appName = pbean.getAppName();
		StringBuffer buffer = new StringBuffer("");
		buffer.append("package " + CreateApp.basePage + "." + CreateApp.projectName + ".action"
				 + ";\r\n");
		buffer.append("\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".form"
				+ "." + upCaseAppName + "Form;\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".entitys"
				 + "." + upCaseAppName + ";\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".parameter"
				 + "." + upCaseAppName + "Parameter;\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".operation"
				+ "." + upCaseAppName + "Operation;\r\n");
		buffer.append("import " + CreateApp.utilPage + ".Tool;\r\n");
		buffer.append("\r\n");
		buffer.append("import javax.servlet.http.HttpServletRequest;\r\n");
		buffer.append("import javax.servlet.http.HttpServletResponse;\r\n");
		buffer.append("import org.apache.struts.action.Action;\r\n");
		buffer.append("import org.apache.struts.action.ActionForm;\r\n");
		buffer.append("import org.apache.struts.action.ActionForward;\r\n");
		buffer.append("import org.apache.struts.action.ActionMapping;\r\n");
		buffer.append("\r\n");
		buffer.append("import xgxt.action.Base;\r\n");
		buffer.append("\r\n");
		

		buffer.append("public class " + upCaseAppName
				+ "Action extends Action {\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("public ActionForward execute(ActionMapping mapping, ActionForm form,\r\n");
		buffer.append("\t\t\t");
		buffer.append("HttpServletRequest request, HttpServletResponse response) throws Exception {\r\n");
		buffer.append("\t\t");
		buffer.append("String myAction = mapping.getParameter();\r\n");
		buffer.append("\t\t");
		buffer.append("ActionForward myFwd = null;\r\n");
		buffer.append("\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t");
		buffer.append("String writeAble = Base.getWriteAble(request);\r\n");
		buffer.append("\r\n");
		buffer.append("\t\t\t");
		buffer.append("if (\"" + upCaseAppName + "Edit" + "\"" + ".equalsIgnoreCase(myAction)) {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("myFwd = " + upCaseAppName + "Operation." + upCaseAppName + "Edit" + "(mapping, form,request, response);\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t\t");
		buffer.append(" else if(\"" + upCaseAppName + "Save" + "\"" + ".equalsIgnoreCase(myAction)) {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("myFwd = " + upCaseAppName + "Operation." + upCaseAppName + "Save" + "(mapping, form,request, response);\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t\t");
		buffer.append(" else if(\"" + upCaseAppName + "List" + "\"" + ".equalsIgnoreCase(myAction)) {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("myFwd = " + upCaseAppName + "Operation." + upCaseAppName + "List" + "(mapping, form,request, response);\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t\t");
		buffer.append(" else if(\"" + upCaseAppName + "Delete" + "\"" + ".equalsIgnoreCase(myAction)) {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("myFwd = " + upCaseAppName + "Operation." + upCaseAppName + "Delete" + "(mapping, form,request, response);\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t\t");
		buffer.append("request.setAttribute(\"writeAble\", writeAble);\r\n");
		buffer.append("\t\t\t");
		buffer.append("return myFwd;\r\n");
		buffer.append("\t\t");
		buffer.append("} catch (Exception e) {\r\n");
		buffer.append("\t\t\t");
		buffer.append("e.printStackTrace();\r\n");
		buffer.append("\t\t\t");
		buffer.append("return new ActionForward(\"/errMsg.do\", false);\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		
//		actionEdit(pbean, upCaseAppName, appName, buffer);		
//		actionSave(pbean, upCaseAppName, appName, buffer);		
//		actionList(upCaseAppName, appName, buffer);		
//		actionDel(upCaseAppName, appName, buffer);		
		buffer.append("}\r\n");
		return buffer.toString();
	}

	@SuppressWarnings("unused")
	private static void actionDel(String upCaseAppName, String appName, StringBuffer buffer) {
		buffer.append("\t");
		buffer.append("public void delete() throws Exception {\r\n");
		buffer.append("\t\t");
		buffer.append("Boolean res=false;\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + "Form form = (" + upCaseAppName
				+ "Form) this.form;\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + "EO " + appName + "EO = new "
				+ upCaseAppName + "EO();\r\n");
		buffer.append("\t\t");
		buffer.append("res=" + appName + "EO.delete(form.getParameter());\r\n");
		buffer.append("\t\t");
		buffer.append("if(res){\r\n");		
		buffer.append("\t\t\t");
		buffer.append("String logMsg = \"删除信息!\";\r\n");		
		buffer.append("\t\t\t");
		buffer.append("tool.operatSuccess(request,logMsg,sUName);\r\n");		
		buffer.append("\t\t\t");
		buffer.append("this.forward = \"return\";\r\n");		
		buffer.append("\t\t");
		buffer.append("}else{\r\n");		
		buffer.append("\t\t\t");
		buffer.append("request.setAttribute(\"message\", \"操作失败,请重试!\");\r\n");		
		buffer.append("\t\t\t");
		buffer.append("this.forward = \"edit\";\r\n");		
		buffer.append("\t\t");
		buffer.append("}\r\n");		
		buffer.append("\t");
		buffer.append("}\r\n");
	}

	@SuppressWarnings("unused")
	private static void actionList(String upCaseAppName, String appName, StringBuffer buffer) {
		buffer.append("\t");
		buffer.append("public void list() throws Exception {\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + "Form form = (" + upCaseAppName
				+ "Form) this.form;\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + "EO " + appName + "EO = new "
				+ upCaseAppName + "EO();\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName
				+ "Parameter parameter =  form.getParameter();\r\n");
		buffer.append("\t\t");
		buffer.append("" + appName + "EO.list(parameter);\r\n");
		buffer.append("\t\t");
		buffer.append("this.forward = \"list\";\r\n");
		buffer.append("\t");		
		buffer.append("}\r\n");
		buffer.append("\r\n");
	}

	@SuppressWarnings("unused")
	private static void actionSave(ParameterBean pbean, String upCaseAppName, String appName, StringBuffer buffer) {
		buffer.append("\t");
		buffer.append("public void save() throws Exception {\r\n");
		buffer.append("\t\t");
		buffer.append("Boolean res=false;\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + "Form form = (" + upCaseAppName
				+ "Form) this.form;\r\n");
		buffer.append("\t\t");
		buffer.append("String " + pbean.getColumId() + " = form.get"
				+ upCaseAppName + "().get"
				+ Tool.toUpcaseFirstChar(pbean.getColumId()) + "();\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + "EO " + appName + "EO = new "
				+ upCaseAppName + "EO();\r\n");
		buffer.append("\t\t");
		buffer.append("if (!Tool.isNull(" + pbean.getColumId() + ")) {\r\n");
		buffer.append("\t\t\t");
		buffer.append("res=" + appName + "EO.update(" + pbean.getColumId()
				+ ", form.get" + upCaseAppName + "());\r\n");
		buffer.append("\t\t");
		buffer.append("} else {\r\n");
		buffer.append("\t\t\t");
		buffer.append("res=" + appName + "EO.insert(form.get" + upCaseAppName
				+ "());\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");		
		buffer.append("\t\t");
		buffer.append("if(res){\r\n");		
		buffer.append("\t\t\t");
		buffer.append("String logMsg = \"保存信息!\";\r\n");		
		buffer.append("\t\t\t");
		buffer.append("tool.operatSuccess(request,logMsg,sUName);\r\n");		
		buffer.append("\t\t\t");
		buffer.append("this.forward = \"return\";\r\n");		
		buffer.append("\t\t");
		buffer.append("}else{\r\n");		
		buffer.append("\t\t\t");
		buffer.append("request.setAttribute(\"message\", \"操作失败,请重试!\");\r\n");		
		buffer.append("\t\t\t");
		buffer.append("this.forward = \"edit\";\r\n");		
		buffer.append("\t\t");
		buffer.append("}\r\n");		
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
	}

	@SuppressWarnings("unused")
	private static void actionEdit(ParameterBean pbean, String upCaseAppName, String appName, StringBuffer buffer) {
		buffer.append("\t");
		buffer.append("public void edit() throws Exception {\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + "Form form = (" + upCaseAppName
				+ "Form) this.form;\r\n");
		buffer.append("\t\t");
		buffer.append("String " + pbean.getColumId() + " = form.get"
				+ upCaseAppName + "().get"
				+ Tool.toUpcaseFirstChar(pbean.columId) + "();\r\n");
		buffer.append("\t\t");
		buffer.append("if (!Tool.isNull(" + pbean.getColumId() + ")) {\r\n");
		buffer.append("\t\t\t");
		buffer.append("" + upCaseAppName + "EO " + appName + "EO = new "
				+ upCaseAppName + "EO();\r\n");
		buffer.append("\t\t\t");
		buffer.append("" + upCaseAppName + " " + appName + " = " + appName
				+ "EO.getInstance(" + pbean.getColumId() + ");\r\n");
		buffer.append("\t\t\t");
		buffer.append("form.set" + upCaseAppName + "(" + appName + ");\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("this.forward = \"edit\";\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
	}

	private static String getOperationClass(ParameterBean pbean) {
		String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
		BeanTableStruct []beans = pbean.getBeans();
//		String appName = pbean.getAppName();
		String tableName = pbean.getTableName();
		StringBuffer buffer = new StringBuffer("");

		// 创建包名开始
		buffer.append("package " + CreateApp.basePage + "." + CreateApp.projectName + ".operation"
				 + ";\r\n");
		buffer.append("\r\n");
		// 创建包名结束

		// 导入包列表开始
		
		buffer.append("import java.util.Vector;\r\n");
		buffer.append("import java.util.HashMap;\r\n");
		buffer.append("import java.util.List;\r\n");
		buffer.append("import java.util.ArrayList;\r\n");
		buffer.append("\r\n");
		buffer.append("import java.sql.SQLException;\r\n");
		buffer.append("\r\n");
		buffer.append("import javax.servlet.http.HttpServletRequest;\r\n");
		buffer.append("import javax.servlet.http.HttpServletResponse;\r\n");
		buffer.append("import javax.servlet.http.HttpSession;\r\n");
		buffer.append("import org.apache.struts.action.ActionForm;\r\n");
		buffer.append("import org.apache.struts.action.ActionForward;\r\n");
		buffer.append("import org.apache.struts.action.ActionMapping;\r\n");
		buffer.append("\r\n");
		buffer.append("import common.Globals;\r\n");
		buffer.append("import xgxt.DAO.DAO;\r\n");
		buffer.append("import xgxt.base.DealString;\r\n");
		buffer.append("import xgxt.daoActionLogic.StandardOperation;\r\n");
		buffer.append("import xgxt.utils.Check_Input_Value;\r\n");
		buffer.append("\r\n");

		buffer.append("import " + CreateApp.basePage + "." + projectName + ".entitys"
				 + "." + upCaseAppName + ";\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".parameter"
				 + "." + upCaseAppName + "Parameter;\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".form"
				 + "." + upCaseAppName + "Form;\r\n");
		buffer.append("import " + CreateApp.utilPage + ".Tool;\r\n");

		buffer.append("\r\n");
		// 导入包列表结束

		// 创建类
		buffer.append("public class " + upCaseAppName
				+ "Operation {\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("public static ActionForward " + upCaseAppName + "Edit" + "(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){\r\n");
		String fun = edit(upCaseAppName, beans, tableName);
		buffer.append(fun);
		buffer.append("\t\t");
		buffer.append("return mapping.findForward(\"success\");\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		
		buffer.append("\t");
		buffer.append("public static ActionForward " + upCaseAppName + "Save" + "(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){\r\n");
		fun = save(upCaseAppName, beans, tableName);
		buffer.append(fun);
		buffer.append("\t\t");
		buffer.append("return mapping.findForward(\"success\");\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		
		buffer.append("\t");
		buffer.append("public static ActionForward " + upCaseAppName + "List" + "(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){\r\n");
		fun = List(upCaseAppName, beans, tableName);
		buffer.append(fun);
		buffer.append("\t\t");
		buffer.append("return mapping.findForward(\"success\");\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		
		buffer.append("\t");
		buffer.append("public static ActionForward " + upCaseAppName + "Delete" + "(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{\r\n");
		fun = delete(upCaseAppName, beans, tableName);
		buffer.append(fun);
		buffer.append("\t\t");
		buffer.append("return mapping.findForward(\"success\");\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");

//		// 得到实体类实例开始
//		beans = daoInit(pbean, upCaseAppName, appName,
//				tableName, buffer);
//		// 得到实体类实例结束
//
//		// 实体更新操作,参数 ID,实体类
//		daoUpdate(pbean, upCaseAppName, appName, tableName, buffer, beans);
//		daoInsert(upCaseAppName, appName, tableName, buffer, beans);
//		daoList(pbean, upCaseAppName, appName, tableName, buffer, beans);
//		daoDelete(pbean, upCaseAppName, tableName, buffer);
		buffer.append("}\r\n");
		buffer.append(" \r\n");
		return buffer.toString();
	}
	
	private static String edit(String upCaseAppName, BeanTableStruct[] beans, String tableName) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("\t\t");
		buffer.append("DAO dao = DAO.getInstance();\r\n");
		buffer.append("\t\t");
		buffer.append("String tableName = \"view_" + tableName + "\";\r\n");
		buffer.append("\t\t");
		buffer.append("String pk = \"\";\r\n");	
		buffer.append("\t\t");
		buffer.append("String pkVal = \"\";\r\n");
		buffer.append("\t\t");
		buffer.append("HashMap<String, String> map = new HashMap<String, String>();\r\n");
		buffer.append("\t\t");
		buffer.append("String []colList = {\"主键\",\"行号\",");
		for(int i=0; i<beans.length; i++){
			BeanTableStruct beanTableStruct = beans[i];
			buffer.append("\"");
			buffer.append(beanTableStruct.getCName());
			buffer.append("\"");
			if(i != beans.length-1){
				buffer.append(",");
			}
		}
		buffer.append("};\r\n");
		buffer.append("\t\t");
		buffer.append("String sql = \"select \" + pk + \" 主键, rownum 行号,");
		for(int i=0; i<beans.length; i++){
			BeanTableStruct beanTableStruct = beans[i];
			buffer.append(beanTableStruct.getCName());
			if(i != beans.length-1){
				buffer.append(",");
			}
		}
		buffer.append(" from \" + tableName + \" where \" + pk + \"=?\";\r\n");
		buffer.append("\t\t");
		buffer.append("map = dao.getMap(sql, new String []{pkVal}, colList);\r\n");
		buffer.append("\t\t");
		buffer.append("request.setAttribute(\"rs\", map);\r\n");
		return buffer.toString();
	}

	private static String save(String upCaseAppName, BeanTableStruct[] beans, String tableName) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("\t\t");
		buffer.append("String realTable = \"" + tableName + "\";\r\n");
		buffer.append("\t\t");
		buffer.append(upCaseAppName + " " + tableName + " = new " + upCaseAppName + "();\r\n");
		buffer.append("\t\t");
		buffer.append("String []colList = {");
		for(int i=0; i<beans.length; i++){
			BeanTableStruct beanTableStruct = beans[i];
			buffer.append("\"");
			buffer.append(beanTableStruct.getCName());
			buffer.append("\"");
			if(i != beans.length-1){
				buffer.append(",");
			}
		}
		buffer.append("};\r\n");
		buffer.append("\t\t");
		buffer.append("String []VcolList = {");
		for(int i=0; i<beans.length; i++){
			BeanTableStruct beanTableStruct = beans[i];
			buffer.append(tableName);
			buffer.append(".get");
			buffer.append(Tool.toUpcaseFirstChar(beanTableStruct.getCName()));
			buffer.append("()");
			if(i != beans.length-1){
				buffer.append(",");
			}
		}
		buffer.append("};\r\n");
		buffer.append("\t\t");
		buffer.append("StandardOperation.insert(realTable, colList, VcolList);\r\n");
		return buffer.toString();
	}
	
	private static String List(String upCaseAppName, BeanTableStruct[] beans, String tableName) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("\t\t");
		buffer.append("DAO dao = DAO.getInstance();\r\n");
		buffer.append("\t\t");
		buffer.append("String tableName = \"view_" + tableName + "\";\r\n");
		buffer.append("\t\t");
		buffer.append("String pk = \"\";\r\n");	
		buffer.append("\t\t");
		buffer.append("String go = \"\";\r\n");
		buffer.append("\t\t");
		buffer.append("Vector<Object> vector = new Vector<Object>();\r\n");
		buffer.append("\t\t");
		buffer.append("String rsNum = \"\";\r\n");
		buffer.append("\t\t");
		buffer.append("String querry = \" where 1=1 \";\r\n");
		buffer.append("\t\t");
		buffer.append("String []colList = {\"主键\",\"行号\",");
		for(int i=0; i<beans.length; i++){
			BeanTableStruct beanTableStruct = beans[i];
			buffer.append("\"");
			buffer.append(beanTableStruct.getCName());
			buffer.append("\"");
			if(i != beans.length-1){
				buffer.append(",");
			}
		}
		buffer.append("};\r\n");
		buffer.append("\t\t");
		buffer.append("String sql = \"select \" + pk + \" 主键, rownum 行号,");
		for(int i=0; i<beans.length; i++){
			BeanTableStruct beanTableStruct = beans[i];
			buffer.append(beanTableStruct.getCName());
			if(i != beans.length-1){
				buffer.append(",");
			}
		}
		buffer.append(" from \" + tableName + querry;\r\n");
		buffer.append("\t\t");
		buffer.append("if(\"go\".equalsIgnoreCase(go)){\r\n");
		buffer.append("\t\t\t");
		buffer.append("vector.add(dao.rsToVator(sql, new String []{}, colList));\r\n");
		buffer.append("\t\t\t");
		buffer.append("rsNum = vector == null? \"0\" : String.valueOf(vector.size());\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("request.setAttribute(\"rs\", vector);\r\n");
		buffer.append("\t\t");
		buffer.append("request.setAttribute(\"rsNum\", rsNum);\r\n");
		return buffer.toString();
	}
	
	private static String delete(String upCaseAppName, BeanTableStruct[] beans, String tableName) {
		StringBuffer buffer = new StringBuffer("");
		buffer.append("\t\t");
		buffer.append("String realTable = \"" + tableName + "\";\r\n");
		buffer.append("\t\t");
		buffer.append("String pk = \"\";\r\n");	
		buffer.append("\t\t");
		buffer.append("String pkVal = \"\";\r\n");
		buffer.append("\t\t");
		buffer.append("StandardOperation.delete(realTable, pk, pkVal);\r\n");
		return buffer.toString();
	}

	
	final public static String getParameterClass(ParameterBean pbean)
			throws IOException {
		String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
		// String appName = pbean.getAppName();

		StringBuffer buffer = new StringBuffer("");
		buffer.append("package " + CreateApp.basePage + "." + projectName + ".parameter"
				 + ";\r\n");
		buffer.append("\r\n");
		buffer.append("public class " + upCaseAppName
				+ "Parameter {\r\n");
		buffer.append("\r\n");
		buffer.append("}\r\n");

		return buffer.toString();
	}

	final public static String getFormClass(ParameterBean pbean)
			throws IOException {
		StringBuffer buffer = new StringBuffer("");
		String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());

		String appName = pbean.getAppName();

		buffer.append("package " + CreateApp.basePage + "." + CreateApp.projectName + ".form"
				 + ";\r\n");
		buffer.append("\r\n");
		buffer.append("import " + CreateApp.basePage + ".form.BaseForm;\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".parameter"
				 + "." + upCaseAppName + "Parameter;\r\n");
		buffer.append("import " + CreateApp.basePage + "." + projectName + ".entitys"
				+ "." + upCaseAppName + ";\r\n");
		buffer.append("\r\n");
		buffer.append("public class " + upCaseAppName
				+ "Form extends BaseForm {\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("private " + upCaseAppName + "Parameter parameter = new "
				+ upCaseAppName + "Parameter();\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("private " + upCaseAppName + " " + appName + " = new "
				+ upCaseAppName + "();\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("public " + upCaseAppName
				+ "Parameter getParameter() {\r\n");
		buffer.append("\t\t");
		buffer.append("return parameter;\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("public void setParameter(" + upCaseAppName
				+ "Parameter parameter) {\r\n");
		buffer.append("\t\t");
		buffer.append("this.parameter = parameter;\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("public " + upCaseAppName + " get" + upCaseAppName
				+ "() {\r\n");
		buffer.append("\t\t");
		buffer.append("return " + appName + ";\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("public void set" + upCaseAppName + "(" + upCaseAppName
				+ " " + appName + ") {\r\n");
		buffer.append("\t\t");
		buffer.append("this." + appName + " = " + appName + ";\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("}\r\n");

		return buffer.toString();
	}

	final public static void saveJSPFile(String type, ParameterBean pbean)
			throws IOException {
		// String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
		String appName = pbean.getAppName();

		String _path = "xgWeb/"+projectName+"/" + appName;
		File pf = new File(_path + "/");
		if (!pf.isDirectory())
			pf.mkdirs();
		File file = new File(_path + "/" + type + ".jsp");

		FileWriter writer = new FileWriter(file);
		String body = "";
		if (type.endsWith("list")) {
			body = getListJSP(pbean);
		}
		if (type.endsWith("edit")) {
			body = getEditJSP(pbean);
		}
		if (type.endsWith("view")) {
			body = getViewJSP(pbean);
		}
		writer.write(body);
		writer.flush();
		writer.close();
	}

	final public static String getListJSP(ParameterBean pbean) {

		@SuppressWarnings("unused")
		String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
		String appName = pbean.getAppName();
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<%@ page language=\"java\" pageEncoding=\"GBK\" %>\r\n");
		buffer.append("\r\n");
		buffer.append("<%@taglib prefix=\"template\" uri=\"/WEB-INF/struts-template.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"html\" uri=\"/WEB-INF/struts-html.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"logic\" uri=\"/WEB-INF/struts-logic.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"bean\" uri=\"/WEB-INF/struts-bean.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"tiles\" uri=\"/WEB-INF/struts-tiles.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"nested\" uri=\"/WEB-INF/struts-nested.tld\"%>\r\n");
		
		buffer.append("\r\n");
		buffer.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		buffer.append("<html>\r\n");
		buffer.append("\t");
		buffer.append("<head>\r\n");
		buffer.append("\t\t");
		buffer.append("<title>标题</title>\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\" />\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"Content-Language\" content=\"GBK\" />\r\n");
		buffer.append("\t\t");
		buffer.append("<meta name=\"Copyright\" content=\"正方软件 zfsoft\" />\r\n");		
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"cache-control\" content=\"no-cache, must-revalidate\">\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"expires\" content=\"0\">\r\n");
		buffer.append("\t");
		buffer.append("</head>\r\n");		
		buffer.append("\t");
		buffer.append("<base target=\"_self\">\r\n");		
		buffer.append("\t");
		buffer.append("<link rel=\"icon\" href=\"images/icon.ico\" type=\"image/x-icon\" />\r\n");
		buffer.append("\t");
		buffer.append("<link id=\"csss\" rel=\"stylesheet\" rev=\"stylesheet\" href=\"skin/style/main.css\" type=\"text/css\" media=\"all\" />\r\n");
		buffer.append("\t");
		buffer.append("<link id=\"csss\" rel=\"stylesheet\" rev=\"stylesheet\" href=\"skin/style/forms.css\" type=\"text/css\" media=\"all\" />\r\n");
		buffer.append("\t");
		buffer.append("<script type=\"text/javascript\" src=\"js/"+projectName+".js\"></script>\r\n");	
		buffer.append("\t");
		buffer.append("<body>\r\n");
		buffer.append("\t");
		buffer.append("<html:form action=\"" + appName+ ".do\" method=\"post\">\r\n");
		buffer.append("\t");
		buffer.append("<html:hidden property=\"theAction\" />\r\n");
		buffer.append("\t");
		buffer.append("<html:hidden property=\"parameter.delId\" />\r\n");
		buffer.append("\t");
		buffer.append("<div id=\"controltool\">\r\n");
		buffer.append("\t\t");
		buffer.append("<ul>\r\n");
		buffer.append("\t\t\t");
		buffer.append("<li id=\"add\"><a href=\"#\"> 增 加 </a></li>\r\n");
		buffer.append("\t\t\t");
		buffer.append("<li id=\"edit\"><a href=\"#\"> 修 改 </a></li>\r\n");		
		buffer.append("\t\t\t");
		buffer.append("<li id=\"delete\"><a href=\"#\"> 删 除 </a></li>\r\n");
		buffer.append("\t\t");
		buffer.append("</ul>\r\n");
		buffer.append("\t");
		buffer.append("</div>\r\n");
		buffer.append("\t");
		buffer.append("<div class=\"select\">\r\n");
		buffer.append("\t");
		buffer.append("</div>\r\n");
		buffer.append("\t");
		buffer.append("<table class=\"tbstyle\" width=\"100%\" align=\"center\">\r\n");
		buffer.append("\t\t");
		buffer.append("<thead>\r\n");
		buffer.append("\t\t\t");
		buffer.append("<tr style=\"cursor:hand\">\r\n");
		BeanTableStruct[] beans = pbean.getBeans();
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String label = beanTableStruct.getCLable();
			buffer.append("\t\t\t\t");
			buffer.append("<td width=\"10%\" align=\"center\" >"+ label + "</td>\r\n");
		}
		buffer.append("\t\t\t");
		buffer.append("</TR>\r\n");
		buffer.append("\t\t");
		buffer.append("</thead>\r\n");
		buffer.append("\t\t");
		buffer.append("<tbody id=\"rsTable\">\r\n");
		buffer.append("\r\n");
		buffer.append("\t\t");
		buffer.append("</tbody>\r\n");
		buffer.append("\t");
		buffer.append("</table>\r\n");
		buffer.append("</html:form>\r\n\r\n");
		
		buffer.append("<script language=javascript>\r\n");
		buffer.append("\t");
		buffer.append("window.onload=function(){\r\n");		
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("function refurbish(){\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("var action=document.forms[0].action;\r\n");
		buffer.append("\t");
		buffer.append("if($(\"add\")){\r\n");
		buffer.append("\t\t");
		buffer.append("$(\"add\").onclick=function(){\r\n");
		buffer.append("\t\t\t");
		buffer.append("var url=action+\"?theAction=edit\";\r\n");	
		buffer.append("\t\t\t");
		buffer.append("showTopWin(url,600,515,\"Y\");\r\n");	
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		
		buffer.append("\t");
		buffer.append("if($(\"edit\")){\r\n");
		buffer.append("\t\t");
		buffer.append("$(\"edit\").onclick=function(){\r\n");
		buffer.append("\t\t\t");
		buffer.append("if(chkCurrRow()){\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("var url=action+\"?theAction=edit&id=\"+curr_row.cells[0].innerText;\r\n");	
		buffer.append("\t\t\t\t");
		buffer.append("showTopWin(url,600,515,\"Y\");\r\n");	
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("if($(\"delete\")){\r\n");
		buffer.append("\t\t");
		buffer.append("$(\"delete\").onclick=function(){\r\n");
		buffer.append("\t\t\t");
		buffer.append("if(chkCurrRow()&&confirm(\"确定删除该条信息吗?\")){\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("var url=action+\"?theAction=del&id=\"+curr_row.cells[0].innerText;\r\n");	
		buffer.append("\t\t\t\t");
		buffer.append("showTopWin(url,400,300,\"Y\");\r\n");	
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");	
		buffer.append("</script>\r\n");
		buffer.append("\t");
		buffer.append("</body>\r\n");
		buffer.append("</html>\r\n");
		
		return buffer.toString();

	}

	final public static String getEditJSP(ParameterBean pbean) {
		String appName = pbean.getAppName();
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<%@ page language=\"java\" pageEncoding=\"GBK\" %>\r\n");
		buffer.append("\r\n");
		buffer.append("<%@taglib prefix=\"template\" uri=\"/WEB-INF/struts-template.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"html\" uri=\"/WEB-INF/struts-html.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"logic\" uri=\"/WEB-INF/struts-logic.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"bean\" uri=\"/WEB-INF/struts-bean.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"tiles\" uri=\"/WEB-INF/struts-tiles.tld\"%>\r\n");
		buffer.append("<%@taglib prefix=\"nested\" uri=\"/WEB-INF/struts-nested.tld\"%>\r\n");
		
		buffer.append("\r\n");
		buffer.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		buffer.append("<html>\r\n");
		buffer.append("\t");
		buffer.append("<head>\r\n");
		buffer.append("\t\t");
		buffer.append("<title>标题</title>\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\" />\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"Content-Language\" content=\"GBK\" />\r\n");
		buffer.append("\t\t");
		buffer.append("<meta name=\"Copyright\" content=\"正方软件 zfsoft\" />\r\n");		
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"cache-control\" content=\"no-cache, must-revalidate\">\r\n");
		buffer.append("\t\t");
		buffer.append("<meta http-equiv=\"expires\" content=\"0\">\r\n");
		buffer.append("\t");
		buffer.append("</head>\r\n");		
		buffer.append("\t");
		buffer.append("<base target=\"_self\">\r\n");		
		buffer.append("\t");
		buffer.append("<link rel=\"icon\" href=\"images/icon.ico\" type=\"image/x-icon\" />\r\n");
		buffer.append("\t");
		buffer.append("<link id=\"csss\" rel=\"stylesheet\" rev=\"stylesheet\" href=\"skin/style/main.css\" type=\"text/css\" media=\"all\" />\r\n");
		buffer.append("\t");
		buffer.append("<link id=\"csss\" rel=\"stylesheet\" rev=\"stylesheet\" href=\"skin/style/forms.css\" type=\"text/css\" media=\"all\" />\r\n");
		buffer.append("\t");
		buffer.append("<script type=\"text/javascript\" src=\"js/"+projectName+".js\"></script>\r\n");	
		buffer.append("\t");
		buffer.append("<body>\r\n");			
		buffer.append("\t");
		buffer.append("<html:form action=\"" + appName+ ".do\"  method=\"post\"");
		buffer.append(" onsubmit=\"return CheckForm();\">\r\n");
		buffer.append("\t\t");
		buffer.append("<table class=\"tbstyle\" width=\"100%\" align=\"center\">\r\n");	
		buffer.append("\t\t\t");
		buffer.append("<html:hidden property=\"theAction\" value=\"save\" />\r\n");
		buffer.append("\t\t\t");
		buffer.append("<html:hidden property=\"" + appName + "."
				+ pbean.getColumId() + "\" />\r\n");
		buffer.append("\t\t\t");
		buffer.append("<thead>\r\n");		
		buffer.append("\t\t\t\t");
		buffer.append("<tr>\r\n");	
		buffer.append("\t\t\t\t\t");
		buffer.append("<td colspan=\"2\" align=\"center\">信息维护</td>\r\n");	
		buffer.append("\t\t\t\t");
		buffer.append("</tr>\r\n");	
		buffer.append("\t\t\t");
		buffer.append("</thead>\r\n");	
		
		buffer.append("\t\t\t");
		buffer.append("<tbody id=\"rsTable\">\r\n");
		BeanTableStruct[] beans = pbean.getBeans();
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String label = beanTableStruct.getCLable();
			String cName = beanTableStruct.getCName();
			if (!cName.equalsIgnoreCase(pbean.getColumId())
					&& !cName.equalsIgnoreCase(pbean.getColumCreateTime())) {
				buffer.append("\t\t\t\t");
				buffer.append("<TR>\r\n");	
				buffer.append("\t\t\t\t\t");
				buffer.append("<td align=\"right\" width=\"100px\" >"+label+"：</td>\r\n");
				buffer.append("\t\t\t\t\t");
				buffer.append("<td align=\"left\" >" );
				buffer.append("<html:text property=\""	+ appName + "." + cName + "\"  styleClass=\"inputtext\"  style=\"width:100%\"></html:text>\r\n");
				buffer.append("\t\t\t\t\t");
				buffer.append("</td>\r\n");
				buffer.append("\t\t\t\t");
				buffer.append("</TR>\r\n");
			}
		}	
		
		buffer.append("\t\t\t");
		buffer.append("</tbody>\r\n");	
		buffer.append("\t\t");
		buffer.append("</table>\r\n");	
		buffer.append("\t\t");
		buffer.append("<div class=\"buttontool\">\r\n");	
		buffer.append("\t\t\t");
		buffer.append("<button id=\"save\">\r\n");	
		buffer.append("\t\t\t\t");
		buffer.append("保 存\r\n");	
		buffer.append("\t\t\t");
		buffer.append("</button>\r\n");	
		buffer.append("\t\t\t");
		buffer.append("<button id=\"cancel\" onclick=\"Close()\">\r\n");	
		buffer.append("\t\t\t\t");
		buffer.append("关 闭\r\n");	
		buffer.append("\t\t\t");
		buffer.append("</button>\r\n");	
		buffer.append("\t\t");
		buffer.append("</div>\r\n");	
		buffer.append("\t\t");
		buffer.append("<script type=\"text/javascript\" src=\"js/bottonCLick.js\"></script>\r\n");	
		buffer.append("\t\t");		
		buffer.append("</html:form>\r\n");
		buffer.append("<script language=javascript>\r\n");
		buffer.append("\t");
		buffer.append("window.onload=function(){\r\n");		
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("function CheckForm(){\r\n");
		buffer.append("\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		
		buffer.append("\t");
		buffer.append("var action=document.forms[0].action;\r\n");
		buffer.append("\t");
		buffer.append("if($(\"save\")){\r\n");
		buffer.append("\t\t");
		buffer.append("$(\"save\").onclick=function(){\r\n");
		buffer.append("\t\t\t");
		buffer.append("try{\r\n");	
		buffer.append("\t\t\t\t");
		buffer.append("if(!CheckForm()){ return false;}\r\n");	
		buffer.append("\t\t\t");
		buffer.append("}catch(e){\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");	
		buffer.append("\t\t\t");
		buffer.append("var url=action+\"?theAction=save\";\r\n");
		buffer.append("\t\t\t");
		buffer.append("refreshForm(url);\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");		
		buffer.append("</script>\r\n");
		buffer.append("\t\t");		
		buffer.append("<jsp:include flush=\"true\" page=\"/tsxx.jsp\"></jsp:include>\r\n");
		buffer.append("\t");		
		buffer.append("</body>\r\n");	
		buffer.append("</html>\r\n");

		return buffer.toString();

	}

	final public static String getViewJSP(ParameterBean pbean) {

		// String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
		String appName = pbean.getAppName();
		StringBuffer buffer = new StringBuffer("");
		buffer.append("<%@ page language=\"java\" pageEncoding=\"GBK\"%>\r\n");
		buffer.append("\r\n");
		buffer
				.append("<%@taglib prefix=\"template\" uri=\"/WEB-INF/struts-template.tld\"%>\r\n");
		buffer
				.append("<%@taglib prefix=\"html\" uri=\"/WEB-INF/struts-html.tld\"%>\r\n");
		buffer
				.append("<%@taglib prefix=\"logic\" uri=\"/WEB-INF/struts-logic.tld\"%>\r\n");
		buffer
				.append("<%@taglib prefix=\"bean\" uri=\"/WEB-INF/struts-bean.tld\"%>\r\n");
		buffer.append("\r\n");
		buffer.append("<template:insert template=\"/template.jsp\">\r\n");
		buffer
				.append("<template:put name='title' content='edit' direct='true' />\r\n");
		buffer.append("<template:put name='content'>\r\n");
		buffer.append("\r\n");
		buffer.append("\r\n");
		buffer.append("id:<bean:write name=\"" + appName + "Form\" property=\""
				+ appName + "." + pbean.getColumId()
				+ "\"></bean:write><br>\r\n");
		buffer.append("<%--名称:<bean:write name=\"" + appName
				+ "Form\" property=\"" + appName
				+ ".name\"></bean:write><br>--%>\r\n ");
		buffer.append("\r\n");
		buffer.append("</template:put>\r\n");
		buffer.append("</template:insert>\r\n");

		return buffer.toString();

	}

	
	final public static void updateStrutsConfig(ParameterBean pbean) {

		String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
		String appName = pbean.getAppName();
		try {
			Tool.print(new File(CreateApp.fileConfigStruts));
				SAXReader reader = new SAXReader();		
			    Document document = reader.read(new File(CreateApp.fileConfigStruts));
			Element root = document.getRootElement();

			Iterator iterator = root.elementIterator("form-beans");
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				Element e = element.addElement("form-bean");
				e.addAttribute("name", "" + appName + "Form");
				e.addAttribute("type", "" + CreateApp.basePage + ".form."
						+ upCaseAppName + "Form");
			}

			Iterator iterator2 = root.elementIterator("action-mappings");
			while (iterator2.hasNext()) {
				Element element = (Element) iterator2.next();
				Element e = element.addElement("action");
				e.addAttribute("attribute", "" + appName + "Form");
				e.addAttribute("name", "" + appName + "Form");
				e.addAttribute("path", "/" + appName + "");
				e.addAttribute("scope", "request");
				e.addAttribute("type", "" + CreateApp.basePage + ".action."
						+ upCaseAppName + "Action");
				Element eForward1 = e.addElement("forward");
				eForward1.addAttribute("name", "list");
				eForward1.addAttribute("path", "/jsp/" + appName + "/list.jsp");
				Element eForward2 = e.addElement("forward");
				eForward2.addAttribute("name", "edit");
				eForward2.addAttribute("path", "/jsp/" + appName + "/edit.jsp");
				Element eForward3 = e.addElement("forward");
				eForward3.addAttribute("name", "view");
				eForward3.addAttribute("path", "/jsp/" + appName + "/view.jsp");
			}
			FileWriter out = new FileWriter(CreateApp.fileConfigStruts);

			document.write(out);
			out.close();
			//inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	final public static String getVoClass(ParameterBean pbean) throws Exception {

		String upCaseAppName = Tool.toUpcaseFirstChar(pbean.getAppName());
		// String appName = pbean.getAppName();

		StringBuffer buffer = new StringBuffer("");

		buffer.append("package " + CreateApp.basePage + "." + CreateApp.projectName + ".entitys"
				 + ";\r\n");

		buffer.append("\r\n");
		buffer.append("public class " + upCaseAppName + "{\r\n");
		buffer.append("\r\n");
		BeanTableStruct[] beans = pbean.getBeans();
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String type = beanTableStruct.getCType();
			String cName = beanTableStruct.getCName();

			if (type.equalsIgnoreCase("FLOAT")) {
				buffer.append("\t private float " + cName + ";\r\n");
			}
			if (type.equalsIgnoreCase("LONG")
					|| type.equalsIgnoreCase("NUMBER")) {
				buffer.append("\t private int " + cName + ";\r\n");
			} else {
				buffer.append("\t private String " + cName + ";\r\n");
			}
			buffer.append("\r\n");
		}
		buffer.append("\r\n");

		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String type = beanTableStruct.getCType();
			String cName = beanTableStruct.getCName();

			if (type.equalsIgnoreCase("FLOAT")) {
				buffer.append("\t public float get"
						+ Tool.toUpcaseFirstChar(cName) + "(){\r\n");
				buffer.append("\t\t return this." + cName + ";");
				buffer.append("\r\n\t }\r\n");
				buffer.append("\r\n");
				buffer.append("\t public void set"
						+ Tool.toUpcaseFirstChar(cName) + "(\t\t float "
						+ cName + "){\r\n");
				buffer.append(" this." + cName + "=" + cName + ";");
				buffer.append("\r\n\t }\r\n");
				buffer.append("\r\n");

			}
			if (type.equalsIgnoreCase("LONG")
					|| type.equalsIgnoreCase("NUMBER")) {
				buffer.append("\t public int get"
						+ Tool.toUpcaseFirstChar(cName) + "(){\r\n");
				buffer.append("\t\t return this." + cName + ";");
				buffer.append("\r\n\t }\r\n");
				buffer.append("\r\n");
				buffer.append("\t public void set"
						+ Tool.toUpcaseFirstChar(cName) + "(int " + cName
						+ "){\r\n");
				buffer.append("\t\t this." + cName + "=" + cName + ";");
				buffer.append("\r\n\t }\r\n");
				buffer.append("\r\n");
			} else {
				buffer.append("\t public String get"
						+ Tool.toUpcaseFirstChar(cName) + "(){\r\n");
				buffer.append("\t\t return this." + cName + ";");
				buffer.append("\r\n\t }\r\n");
				buffer.append("\r\n");
				buffer.append("\t public void set"
						+ Tool.toUpcaseFirstChar(cName) + "(String " + cName
						+ "){\r\n");
				buffer.append("\t\t this." + cName + "=" + cName + ";");
				buffer.append("\r\n\t }\r\n");
				buffer.append("\r\n");
			}
		}
		buffer.append("}\r\n");
		return buffer.toString();
	}

	private static void updateSql(ParameterBean pbean, String appName,
			String tableName, BeanTableStruct[] beans, StringBuffer sql,
			StringBuffer _buffer) {
		sql.append("UPDATE ");
		sql.append(tableName);
		sql.append(" SET ");
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String type = beanTableStruct.getCType();
			String cName = beanTableStruct.getCName();
			if (!cName.equalsIgnoreCase(pbean.getColumCreateTime())
					&& !type.equalsIgnoreCase(pbean.getColumId())) {
				if (i == 0) {
					sql.append(cName);
					sql.append(" = ? ");
				} else {
					sql.append(",");
					sql.append(cName);
					sql.append(" = ? ");
				}
				_buffer.append("\t\t\t");
				if (type.equalsIgnoreCase("DATETIME")
						|| type.equalsIgnoreCase("DATE")) {
					_buffer.append("ps.setTimestamp(" + (i + 1)
							+ ", Tool.getSqlTime3(" + appName + ".get"
							+ Tool.toUpcaseFirstChar(cName) + "()));\r\n");
				} else if (type.equalsIgnoreCase("FLOAT")) {
					_buffer.append("ps.setFloat(" + (i + 1) + ", " + appName
							+ ".get" + Tool.toUpcaseFirstChar(cName)
							+ "());\r\n");
				} else if (type.equalsIgnoreCase("LONG")
						|| type.equalsIgnoreCase("NUMBER")) {
					_buffer.append("ps.setInt(" + (i + 1) + ", " + appName
							+ ".get" + Tool.toUpcaseFirstChar(cName)
							+ "());\r\n");
				} else {
					_buffer.append("ps.setString(" + (i + 1) + ", " + appName
							+ ".get" + Tool.toUpcaseFirstChar(cName)
							+ "());\r\n");
				}
			}
		}
		sql.append("WHERE ");
		sql.append(pbean.getColumId());
		sql.append(" = ?");
	}

	@SuppressWarnings("unused")
	private static BeanTableStruct[] daoInit(ParameterBean pbean,
			String upCaseAppName, String appName, String tableName,
			StringBuffer buffer) {
		buffer.append("\t");
		buffer.append("public " + upCaseAppName + " getInstance(String "
				+ pbean.getColumId() + ") throws SQLException {\r\n");
		buffer.append("\t\t");
		buffer.append("PreparedStatement ps = null;\r\n");
		buffer.append("\t\t");
		buffer.append("ResultSet rs = null;\r\n");
		buffer.append("\t\t");
		buffer.append("" + upCaseAppName + " " + appName + " = new "
				+ upCaseAppName + "();\r\n");
		buffer.append("\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t");
		buffer.append("String sql=\"select * from " + tableName + " where "
				+ pbean.getColumId() + "=?\";\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps = this.getConn().prepareStatement(sql);\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps.setString(1, " + pbean.getColumId() + ");\r\n");
		buffer.append("\t\t\t");
		buffer.append("rs = ps.executeQuery();\r\n");
		buffer.append("\t\t\t");
		buffer.append("if (rs.next()) {\r\n");
		BeanTableStruct[] beans = pbean.getBeans();
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String type = beanTableStruct.getCType();
			String cName = beanTableStruct.getCName();
			if (type.equalsIgnoreCase("DATETIME")
					|| type.equalsIgnoreCase("DATE")) {
				buffer.append("\t\t\t\t");
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName)
						+ "(Tool.getTimeString2(rs.getTimestamp(\"" + cName
						+ "\")));\r\n");
			} else if (type.equalsIgnoreCase("FLOAT")) {
				buffer.append("\t\t\t\t");
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName) + "(rs.getFloat(\""
						+ cName + "\"));\r\n");
			} else if (type.equalsIgnoreCase("LONG")
					|| type.equalsIgnoreCase("NUMBER")) {
				buffer.append("\t\t\t\t");
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName) + "(rs.getInt(\""
						+ cName + "\"));\r\n");
			} else {
				buffer.append("\t\t\t\t");
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName) + "(rs.getString(\""
						+ cName + "\"));\r\n");
			}
		}
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("} catch (SQLException e) {\r\n");
		buffer.append("\t\t\t");
		buffer.append("//此次执行执行错误检测机制 \r\n");
		buffer.append("\t\t\t");
		buffer.append("e.printStackTrace();\r\n");
		buffer.append("\t\t");
		buffer.append("} finally {\r\n");
		buffer.append("\t\t\t");
		buffer.append("rs=null;\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps=null;\r\n");
		buffer.append("\t\t\t");
		buffer.append("closeConn();\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("return " + appName + ";\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append("\r\n");
		return beans;
	}

	@SuppressWarnings("unused")
	private static void daoUpdate(ParameterBean pbean, String upCaseAppName,
			String appName, String tableName, StringBuffer buffer,
			BeanTableStruct[] beans) {
		buffer.append("\t");
		buffer.append("public Boolean update(String " + pbean.getColumId()
				+ ", " + upCaseAppName + " " + appName
				+ ") throws SQLException {\r\n");
		buffer.append("\t\t");
		buffer.append("Connection conn = this.getConn();\r\n");
		buffer.append("\t\t");
		buffer.append("Boolean res = true;\r\n");
		buffer.append("\t\t");
		buffer.append("PreparedStatement ps = null;\r\n");
		StringBuffer sql = new StringBuffer();
		StringBuffer _buffer = new StringBuffer();
		updateSql(pbean, appName, tableName, beans, sql, _buffer);
		buffer.append("\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t");
		buffer.append("String sql=\"");
		buffer.append(sql);
		buffer.append("\";\r\n");
		buffer.append("\t\t\t");
		buffer.append("conn.setAutoCommit(false);\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps = conn.prepareStatement(sql);\r\n");
		buffer.append(_buffer);
		buffer.append("\t\t\t");
		buffer.append("ps.setString(");
		buffer.append((beans.length + 1));
		buffer.append(", ");
		buffer.append(pbean.getColumId());
		buffer.append(");\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps.executeUpdate();\r\n");
		exceptionOper(buffer);
	}

	@SuppressWarnings("unused")
	private static void daoInsert(String upCaseAppName, String appName,
			String tableName, StringBuffer buffer, BeanTableStruct[] beans) {
		StringBuffer _buffer;
		buffer.append("\t");
		buffer.append("public Boolean insert(" + upCaseAppName + " " + appName
				+ ") throws SQLException {\r\n");
		buffer.append("\t\t");
		buffer.append("Connection conn = this.getConn();\r\n");
		buffer.append("\t\t");
		buffer.append("Boolean res = true;\r\n");
		buffer.append("\t\t");
		buffer.append("PreparedStatement ps = null;\r\n");
		buffer.append("\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t");
		buffer.append("String sql = \"insert into " + tableName + "(");
		String _tmp = "";
		_buffer = new StringBuffer();
		String _tmp1 = "";
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String type = beanTableStruct.getCType();
			String cName = beanTableStruct.getCName();
			if (i == 0) {
				_tmp += cName;
				_tmp1 += "?";
			} else {
				_tmp += "," + cName;
				_tmp1 += ",?";
			}
			_buffer.append("\t\t\t");
			if (type.equalsIgnoreCase("FLOAT")) {
				_buffer.append("ps.setFloat(" + (i + 1) + ", " + appName
						+ ".get" + Tool.toUpcaseFirstChar(cName) + "());\r\n");
			} else if (type.equalsIgnoreCase("LONG")
					|| type.equalsIgnoreCase("NUMBER")) {
				_buffer.append("ps.setInt(" + (i + 1) + ", " + appName + ".get"
						+ Tool.toUpcaseFirstChar(cName) + "());\r\n");
			} else if (type.equalsIgnoreCase("DATETIME")
					|| type.equalsIgnoreCase("DATE")) {
				_buffer.append("ps.setTimestamp(" + (i + 1)
						+ ", Tool.getSqlTime3(" + appName + ".get"
						+ Tool.toUpcaseFirstChar(cName) + "()));\r\n");
			} else {
				_buffer.append("ps.setString(" + (i + 1) + ", " + appName
						+ ".get" + Tool.toUpcaseFirstChar(cName) + "());\r\n");
			}
		}
		buffer.append(_tmp);
		buffer.append(" ) values(" + _tmp1 + ")\";\r\n");
		buffer.append("\t\t\t");
		buffer.append("conn.setAutoCommit(false);\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps = conn.prepareStatement(sql);\r\n");
		buffer.append(_buffer);
		exceptionOper(buffer);
	}

	private static void exceptionOper(StringBuffer buffer) {
		buffer.append("\t\t\t");
		buffer.append("} catch (SQLException e) {\r\n");
		buffer.append("\t\t\t");
		buffer.append("//此次处理检错机制\r\n");
		buffer.append("\t\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("conn.rollback();\r\n");
		buffer.append("\t\t\t");
		buffer.append("} catch (SQLException e1) {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("e1.printStackTrace();\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t\t");
		buffer.append("res = false;\r\n");
		buffer.append("\t\t");
		buffer.append("} finally {\r\n");
		buffer.append("\t\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("conn.commit();\r\n");
		buffer.append("\t\t\t");
		buffer.append("} catch (SQLException e) {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("e.printStackTrace();\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps = null;\r\n");
		buffer.append("\t\t\t");
		buffer.append("conn = null;\r\n");
		buffer.append("\t\t\t");
		buffer.append("closeConn();\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("return res;\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append(" \r\n");
	}

	@SuppressWarnings("unused")
	private static void daoDelete(ParameterBean pbean, String upCaseAppName,
			String tableName, StringBuffer buffer) {
		buffer.append("\t");
		buffer.append("public Boolean delete(" + upCaseAppName
				+ "Parameter parameter) throws SQLException {\r\n");
		buffer.append("\t\t");
		buffer.append("Connection conn = this.getConn();\r\n");
		buffer.append("\t\t");
		buffer.append("Boolean res = true;\r\n");
		buffer.append("\t\t");
		buffer.append("PreparedStatement ps = null;\r\n");
		buffer.append("\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t");
		buffer.append("String sql = \"delete from " + tableName
				+ " where 1=0 \";\r\n");
		buffer.append("\t\t\t");
		buffer.append("String[] ids = parameter.getSelIds();\r\n");
		buffer.append("\t\t\t");
		buffer.append("for (int i = 0; i < ids.length; i++) {\r\n");
		buffer.append("\t\t\t");
		buffer.append("sql = sql + \" or " + pbean.getColumId()
				+ " = '\" + ids[i] + \"'\";\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t\t");
		buffer.append("conn.setAutoCommit(false);\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps = conn.prepareStatement(sql);\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps.executeUpdate();\r\n");
		exceptionOper(buffer);
	}

	@SuppressWarnings("unused")
	private static void daoList(ParameterBean pbean, String upCaseAppName,
			String appName, String tableName, StringBuffer buffer,
			BeanTableStruct[] beans) {
		buffer.append("\t");
		buffer.append("public void list(" + upCaseAppName
				+ "Parameter parameter) throws SQLException {\r\n");
		buffer.append("\t\t");
		buffer.append("PreparedStatement ps = null;\r\n");
		buffer.append("\t\t");
		buffer.append("ResultSet rs = null;\r\n");
		buffer.append("\t\t");
		buffer.append("List list = new ArrayList();\r\n");
		buffer.append("\t\t");
		buffer.append("String whereStr = \"\";\r\n");
		buffer.append("\t\t");
		buffer.append("try {\r\n");
		buffer.append("\t\t\t");
		buffer
				.append("parameter.setMaxRecord(Integer.parseInt(getOneRs(\"select count(*) from "
						+ tableName + " where 1=1 \" + whereStr )));\r\n");
		buffer.append("\t\t\t");
		buffer.append("String sql=\"select * from " + tableName
				+ " where 1=1 \" + whereStr  ;\r\n");
		buffer.append("\t\t\t");
		buffer
				.append("ps = this.getConn().prepareStatement(this.getPageSql(sql");
		buffer.append(",\"" + Tool.toUpcaseFirstChar(pbean.columCreateTime)
				+ " desc\",\"" + DBHandle.dbtype + "\",parameter));");

		buffer.append(" \r\n");
		buffer.append("\t\t\t");
		buffer.append("rs = ps.executeQuery();\r\n");
		buffer.append("\t\t\t");
		buffer.append("while (rs.next()) {\r\n");
		buffer.append("\t\t\t\t");
		buffer.append("" + upCaseAppName + " " + appName + " = new "
				+ upCaseAppName + "();\r\n");
		for (int i = 0; i < beans.length; i++) {
			BeanTableStruct beanTableStruct = beans[i];
			String type = beanTableStruct.getCType();
			String cName = beanTableStruct.getCName();
			buffer.append("\t\t\t\t");
			if (type.equalsIgnoreCase("DATETIME")
					|| type.equalsIgnoreCase("DATE")) {
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName)
						+ "(Tool.getTimeString2(rs.getTimestamp(\"" + cName
						+ "\")));\r\n");
			} else if (type.equalsIgnoreCase("FLOAT")) {
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName) + "(rs.getFloat(\""
						+ cName + "\"));\r\n");
			} else if (type.equalsIgnoreCase("LONG")
					|| type.equalsIgnoreCase("NUMBER")) {
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName) + "(rs.getInt(\""
						+ cName + "\"));\r\n");
			} else {
				buffer.append("" + appName + ".set"
						+ Tool.toUpcaseFirstChar(cName) + "(rs.getString(\""
						+ cName + "\"));\r\n");
			}
		}
		buffer.append("\t\t\t\t");
		buffer.append("list.add(" + appName + ");\r\n");
		buffer.append("\t\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("} catch (SQLException e) {\r\n");
		buffer.append("\t\t\t");
		buffer.append("//此次执行执行错误检测机制 \r\n");
		buffer.append("\t\t\t");
		buffer.append("e.printStackTrace();\r\n");
		buffer.append("\t\t");
		buffer.append("} finally {\r\n");
		buffer.append("\t\t\t");
		buffer.append("rs=null;\r\n");
		buffer.append("\t\t\t");
		buffer.append("ps=null;\r\n");
		buffer.append("\t\t\t");
		buffer.append("closeConn();\r\n");
		buffer.append("\t\t");
		buffer.append("}\r\n");
		buffer.append("\t\t");
		buffer.append("parameter.setList(list);\r\n");
		buffer.append("\t");
		buffer.append("}\r\n");
		buffer.append(" \r\n");
	}

}
