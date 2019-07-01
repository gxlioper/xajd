/*
 * 创建日期 2007-5-16  bat_zzj
 *
 */
package batEditor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import xgxt.action.Base;
import xgxt.base.Configuration;
public class BatEditor extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BatEditor() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException { 
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>batEditor在线文本编辑器</title>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");

		out.println("<Script Language=Javascript>"); ;
		out.println("var sLinkFieldName = \"content1\"");
		out.println("var config = new Object();");
		out.println("config.Version = \"\" ;");
		out.println("config.ReleaseDate = \"\" ;");
		out.println("config.StyleName = \"standard\";");
		out.println("config.StyleEditorHeader = \"<head><link href='batEditor/css/skin1/EditorArea.css' type='text/css' rel='stylesheet'></head><body MONOSPACE>\" ;");
		out.println("config.StyleMenuHeader = \"<head><link href='batEditor/css/skin1/MenuArea.css' type='text/css' rel='stylesheet'></head><body scroll='no' onConTextMenu='event.returnValue=false;'>\";");
		out.println("config.StyleDir = \"standard\";");
		//2010/4/26 luojw edit
		//out.println("config.StyleUploadDir = \"" + "upload/" + "\";");
		out.println("config.StyleUploadDir = \"" + "WEB-INF/upLoad/" + "\";");
		//out.println("config.StyleUploadDir = \"" + Configuration.FILE_UPLOAD_DIR + "\";");
		out.println("config.InitMode = \"EDIT\";");
		out.println("config.AutoDetectPasteFromWord = true;");
		out.println("config.BaseUrl = 1;");
		out.println("config.AutoRemote = 1;");
		out.println("</Script>");

		out.println("<link href=\"batEditor/css/skin1/Editor.css\" type=\"text/css\" rel=\"stylesheet\">");
		out.println("<Script Language=\"Javascript\" src=\"batEditor/css/editor.js\"></Script>");
		out.println("<Script Language=\"Javascript\" src=\"batEditor/css/table.js\"></Script>");
		out.println("<Script Language=\"Javascript\" src=\"batEditor/css/menu.js\"></Script>");
		out.println("</head>");
		out.println("<body SCROLLING=no oncontextmenu=\"return false;\" onfocus=\"VerifyFocus()\" onload=\"setMode('EDIT');\">");
		out.println("<table border=0 cellpadding=0 cellspacing=0 width='100%' height='100%'>");
		out.println("<tr>");
		out.println("<td>");
		out.println(Base.sButton);
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td height='100%'>");
		out.println("<table border=0 cellpadding=0 cellspacing=0 width='100%' height='100%'>");
		out.println("<tr>");
		out.println("<td height='100%'>");
		out.println("<input type=\"hidden\" ID=\"ContentEdit\" value=\"\">");
		out.println("<input type=\"hidden\" ID=\"ContentLoad\" value=\"\">");
		out.println("<input type=\"hidden\" ID=\"ContentFlag\" value=\"0\">");
		out.println("<iframe class=\"Composition\" ID=\"eWebEditor\" MARGINHEIGHT=\"1\" MARGINWIDTH=\"1\" width=\"100%\" height=\"100%\" scrolling=\"yes\">");
		out.println("</iframe>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</td>");
		out.println("</tr>");

		out.println("</table>");
		out.print("<div id=\"eWebEditor_Temp_HTML\"");
		out.println(" style=\"VISIBILITY: hidden; OVERFLOW: hidden; POSITION: absolute; WIDTH: 1px; HEIGHT: 1px\"></div>");
		out.print("<form id=\"eWebEditor_UploadForm\"");
		out.print("action=\"/Upload?action=remote&type=remote&style=");
		//out.print(sStyleName);
		out.print("\"");
		out.println(" method=\"post\" target=\"eWebEditor_UploadTarget\">");
		out.println("<input type=\"hidden\" name=\"eWebEditor_UploadText\">");
		out.println("</form>");
		out.println("<iframe name=\"eWebEditor_UploadTarget\" width=0 height=0></iframe>");
		out.println("<div id=divProcessing");
		out.println("style=\"width:200px;height:30px;position:absolute;display:none\">");
		out.print("<table border=0 cellpadding=0 cellspacing=1 bgcolor=\"#000000\"");
		out.println(" width=\"100%\" height=\"100%\">");
		out.println("<tr>");
		out.println("<td bgcolor=#3A6EA5>");
		out.println("<marquee align=\"middle\" behavior=\"alternate\" scrollamount=\"5\"");
		out.println("style=\"font-size:9pt\">");
		out.println("<font color=#FFFFFF>...远程文件收集中...请等待...</font>");
		out.println("</marquee>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("");
		out.flush();
		out.close();
	}

	public void init() {
	}

}
