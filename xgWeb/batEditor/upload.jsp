<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
<title>s</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="������� zfsoft" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 
<meta http-equiv="expires" content="0">
<style type="text/css">
body, a, table, div, span, td, th, input, select{
	font:9pt;font-family: "����", Verdana, Arial, Helvetica, sans-serif;
}
body {
	adding:0px;margin:0px
}
</style>
<script language="JavaScript" src="batEditor/dialog/dialog.js">
</script>
</head>
<body bgcolor=menu>
<logic:present name="back">
<form action="/xgxt/getNewsFile.do" method=post name=myform enctype="multipart/form-data">
<input type=file name=uploadfile size=1 style="width:100%" onchange="originalfile.value=this.value">
<input type=hidden name=originalfile value="">
</form>
<script language=javascript>
var sAllowExt = "";
// ����ϴ���
function CheckUploadForm() {
return true;
}
// �ύ�¼��������
var oForm = document.myform;
oForm.attachEvent("onsubmit", CheckUploadForm) ;
if (! oForm.submitUpload) oForm.submitUpload = new Array() ;
oForm.submitUpload[oForm.submitUpload.length] = CheckUploadForm ;
if (! oForm.originalSubmit) {
oForm.originalSubmit = oForm.submit ;
oForm.submit = function() {
if (this.submitUpload) {
for (var i = 0 ; i < this.submitUpload.length ; i++) {
this.submitUpload[i]() ;
			}
		}
		this.originalSubmit() ;
	}
}
// �ϴ�����װ�����
try {
	parent.UploadLoaded();
}
catch(e){
}
</script>

<script language=javascript>
parent.UploadSaved('<bean:write name="back"/>');
var obj=parent.dialogArguments.dialogArguments;
if (!obj) obj=parent.dialogArguments;
try{
obj.addUploadFile('dd', 'ss', 'ff');
}catch(e){}
history.back();
</script>
</logic:present>

<logic:notPresent name="back">
<form action="/xgxt/getNewsFile.do" method=post name=myform enctype="multipart/form-data">
<input type=file name="file" id="uploadfile" size=1 style="width:100%" onchange="originalfile.value=this.value">
<input type=hidden name=originalfile value="">
</form>
<script language=javascript>

function CheckUploadForm() {
	return true;
}
// �ύ�¼��������
var oForm = document.myform ;
oForm.attachEvent("onsubmit", CheckUploadForm) ;
if (! oForm.submitUpload) oForm.submitUpload = new Array() ;
oForm.submitUpload[oForm.submitUpload.length] = CheckUploadForm ;
if (! oForm.originalSubmit) {
	oForm.originalSubmit = oForm.submit ;
	oForm.submit = function() {
		if (this.submitUpload) {
			for (var i = 0 ; i < this.submitUpload.length ; i++) {
				this.submitUpload[i]() ;
			}
		}
		this.originalSubmit() ;
	}
}
// �ϴ�����װ�����
try {
	parent.UploadLoaded();
}
catch(e){
}
</script>
</logic:notPresent>
</body>
</html>

<!-- 
	    out.println("<HTML><HEAD><TITLE>Զ���ϴ�</TITLE><meta http-equiv='Content-Type' content='text/html; charset=GBK'></head><body>");
	    out.print("<input type=hidden id=UploadText value=\"");
	    out.print(sContent);
	    out.println("\">");
	    out.println("</body></html>");
	    out.println("<script language=javascript>");
	    out.print("parent.setHTML(UploadText.value);try{parent.addUploadFile('");//Ϊʲôֻȡһ���ֵ����ֻȡ������ҳ����λ��֮ǰ��ֵ��
	    out.print(sOriginalFileName);
	    out.print("', '");
	    out.print(sSaveFileName);
	    out.print("', '");
	    out.print(SaveFileName);
	    out.println("');} catch(e){} parent.remoteUploadOK();");
	    out.println("</script>");
	    //DoRemote();


	    out.println("<HTML>");
	    out.println("<HEAD>");
	    out.println("<TITLE>�ļ��ϴ�</TITLE>");
	    out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");
	    out.println("<style type=\"text/css\">");
	    out.println("body, a, table, div, span, td, th, input, select{font:9pt;font-family: \"����\", Verdana, Arial, Helvetica, sans-serif;}");
	    out.println("body {padding:0px;margin:0px}");
	    out.println("</style>");
	    out.println("<script language=\"JavaScript\" src=\"dialog/dialog.js\">");
	    out.println("</script>");
	    out.println("</head>");
	    out.println("<body bgcolor=menu>");
	    out.print("<form action=\"?action=save&type=");//ע��˴�Ϊʲô����println()
	    out.print(sType);
	    out.print("&style=");
	    out.print(sStyleName);
	    out.println("\" method=post name=myform enctype=\"multipart/form-data\">");
	    out.println("<input type=file name=uploadfile size=1 style=\"width:100%\" onchange=\"originalfile.value=this.value\">");
	    out.println("<input type=hidden name=originalfile value=\"\">");
	    out.println("</form>");
	    out.println("<script language=javascript>");
	    out.print("var sAllowExt = \"");
	    out.print(sAllowExt);
	    out.println("\";");
	    out.println("// ����ϴ���");
	    out.println("function CheckUploadForm() {");
	    out.println("if (!IsExt(document.myform.uploadfile.value,sAllowExt)){");
	    out.println("parent.UploadError(\"��ʾ��\\n\\n��ѡ��һ����Ч���ļ���\\n֧�ֵĸ�ʽ�У�\"+sAllowExt+\"����\");");
	    out.println("return false;");
	    out.println("}");
	    out.println("return true");
	    out.println("}");
	    out.println("// �ύ�¼��������");
	    out.println("var oForm = document.myform;");
	    out.println("oForm.attachEvent(\"onsubmit\", CheckUploadForm) ;");
	    out.println("if (! oForm.submitUpload) oForm.submitUpload = new Array() ;");
	    out.println("oForm.submitUpload[oForm.submitUpload.length] = CheckUploadForm ;");
	    out.println("if (! oForm.originalSubmit) {");
	    out.println("oForm.originalSubmit = oForm.submit ;");
	    out.println("oForm.submit = function() {");
	    out.println("if (this.submitUpload) {");
	    out.println("for (var i = 0 ; i < this.submitUpload.length ; i++) {");
	    out.println("this.submitUpload[i]() ;");
	    out.println("			}");
	    out.println("		}");
	    out.println("		this.originalSubmit() ;");
	    out.println("	}");
	    out.println("}");
	    out.println("// �ϴ�����װ�����");
	    out.println("try {");
	    out.println("	parent.UploadLoaded();");
	    out.println("}");
	    out.println("catch(e){");
	    out.println("}");
	    out.println("</script>");
	    out.println("</body>");
	    out.println("</html>");

	    out.println("<script language=javascript>");
	    out.print("parent.UploadSaved('");
	    out.print(sSaveFileName);
	    out.println("');");
	    out.println("var obj=parent.dialogArguments.dialogArguments;");
	    out.println("if (!obj) obj=parent.dialogArguments;");
	    out.println("try{");
	    out.print("obj.addUploadFile('");
	    out.print(sOriginalFileName);
	    out.print("', '");
	    out.print(sSaveFileName);
	    out.print("', '");
	    out.print(sPathFileName);
	    out.println("');");
	    out.println("}catch(e){}");
	    out.println("history.back();");
	    out.println("</script>");




	    //��ʾ�ϴ���
 -->