<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body >
		<html:form action="/addzxdt" method="post" >
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="webtype" value="zxdt" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="contrl.jsp"></jsp:include>
 					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
							<h1>
								<h3>
									��ǰλ�ã�
									<a href="index.do">��ҳ</a>ѡ�����¶�̬
									<font color="red">��ʾ�����������Բ鿴����,˫��һ�пհ״������޸����ݡ�</font>
								</h3>

							</h1>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<tr align="center" class="btys">
									<td>
										<font color="red">���ű���</font>
									</td>
									<td>
										<font color="red">������</font>
									</td>
									<td>
										<font color="red">����ʱ��</font>
									</td>
									<td>
										<font color="red">����</font>
									</td>
								</tr>
								<logic:iterate name="news" id="s1">
									<tr onmouseover="rowOnClick(this)" ondblclick="updateinfo()">
										<input type="hidden" name="newsid"
											value="<bean:write name="s1" property="newsid" />" />
										<td>
											<div align="left">
												<a title="<bean:write name="s1" property="allnewstitle" />" class="tubiao" target="_blank"
													href="viewnewsinfo.do?method=newsinfo&jytype=jyweb&newsid=<bean:write name="s1" property="newsid" />&rownum=<bean:write name="s1" property="r" />"
													 title="<bean:write name="s1" property="newstitle" />">
													<bean:write name="s1" property="newstitle" /> </a>
											</div>
										</td>
										<td align="center">
											<bean:write name="s1" property="puber" />
										</td>
										<td align="center">
											<bean:write name="s1" property="pubtime" />
										</td>
										<td align="center">
											<a href="#" onclick="updateinfo()">�޸�</a>&nbsp;&nbsp;&nbsp;<a href="" onclick="delmessage(this)">ɾ��</a>
										</td>

									</tr>
								</logic:iterate>
								<logic:present name="news">
									<script language="javascript">
										changeView('tb1',0,25,'yes','yes');
									</script>
								</logic:present>
							</table>
							<TABLE width="98%" align="center" class="tbborder">
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
							</TABLE>
							<br>
							<table width="100%" align="center" class="tbborder">
								<tr>
									<td width="30">
										����
									</td>
									<td>
										<DIV align="left">
											<input type="text" id="bt" name="bt" value="" maxlength="25"
												size="60%">
										</DIV>
									</td>
								</tr>
								<TR>
									<TD align=right>
										��Ŀ
									</TD>
									<TD>
										<div align="left">
											<select name="wjlx">
												<option value="���¶�̬">
													���¶�̬
												</option>
											</select>
										</div>
									</TD>
								</TR>
								<TR>
									<TD align=right>
										����
									</TD>
									<TD align=center>
										<INPUT type="hidden" name="content1" value="">
										<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
											scrolling="no" width="100%" height="350"></IFRAME>
									</TD>
								</TR>
								<TR>

									<TD colspan=2 align=center>
										<button onclick="addmessage();">
											�ύ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="reset">
											����
										</button>
									</TD>
								</TR>
							</table>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" />
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
    alert("�ύ�ɹ���");
    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
    alert("�ύʧ�ܣ�");
    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("��¼ɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("��¼ɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script type="text/javascript">
		function addmessage(){	  
		   var newsTitle = document.getElementById("bt").value;
		   
		   if(newsTitle==""){
		     alert("���ⲻ��Ϊ�գ�");
		     return false;
		   }
		   
		   document.getElementById('content1').value = frames('eWebEditor1').getHTML();
	       document.forms[0].action = "addzxdt.do?method=addzxdt&jytype=jyweb&doType=save";
	       document.forms[0].submit();
		}
		
		function updateinfo(){
		var url ="updatenewsinfo.do?method=updatenewsinfo&jytype=jyweb&pkValue=";
		
		var pkValue ="";	 
		  pkValue = curr_row.getElementsByTagName("input")[0].value;
		  url += pkValue;
		  showTopWin(url, 650, 500);
		}
		
		
		function delmessage(obj){
		   var pkValue = curr_row.getElementsByTagName("input")[0].value;

           obj.href = "addzxdt.do?method=addzxdt&doType=del&jytype=jyweb&pkValue="+pkValue;
           
		}
		function refreshtheweb()
		{
			document.forms[0].action = "addzxdt.do?method=addzxdt&jytype=jyweb";
            document.forms[0].submit();
		}
		</script>
</html>
