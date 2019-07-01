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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<base target = "_self" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
		
		<html:form action="/czxxGygl" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - ��������淶�� - �Զ�����
				</div>
			</div>				 
				<table width="100%" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								�淶���Զ�����
							</td>
						</tr>
					</thead>
					<tr>
						
                        <td height="22" align="right">
							ѧ�꣺
						</td>
						<td height="22" align="left">
							<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
						</td>
											
					</tr>
					<tr>
					    <td height="22" align="right">
							ѧ�ڣ�
						</td>
						<td height="22" align="left">
							<html:select property="xq" styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
						</td>						
																	
					</tr>	
					<tr>
					<td height="22" align="right">
                        <bean:message key="lable.xsgzyxpzxy" />��
					</td>					
                    <td height="22" align="left">
						<html:select property="xydm" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>	
				    </td>							                        					
					</tr>
																			
				</table>
				<br />
			<div class="buttontool" id="button" align="center">
				<button class="button2" onclick="toAtcout()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			<div>
				<ul style="color: red">
					<li>
						��������淶��=�����*60%+ ���ɷ�*40%��
					</li>
					<li>
						�����=һ��������ÿѧ���������μ�����С��80��ʱ�������Ҹ���Ա�ĸ�ѧ���������Ϊ0�֣�
						С�����εİ������������ƽ�����м��㣻
					</li>
					<li>
						���ɷ�=�ڸ�ѧ������ס�޼���Υ�����μ���������ʱ�����ѧ�ڸ������ɷ�Ϊ��֣�
						Ψ��һ��Υ�ͣ�����ɷ�Ϊ50�֣�û��Υ�ͣ�����ɷ�Ϊ100�֣�����Ϊ100�֣�
					</li>
				</ul>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("������ɣ�");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ�ܣ�");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function toAtcout(){
	        var xydm = $("xy").value;
	        var xymc = document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
	        var xn = $("xn").value;
	        var xqmc = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
	        var clin="";
	        if(xydm==""){
	           clin="�˲�������"+xn+"ѧ��"+xqmc+"ѧ��"+"ȫУѧ��\n\n\"��������淶��\"�����Զ����㣬 \n\n���ܻ���������ʱ�䡣\n\nȷ��Ҫ�����Զ�������";
	        }else{
	           clin="�˲�������"+xn+"ѧ��"+xqmc+"ѧ��\n\n\'"+xymc+"\'ѧ��\n\n\"��������淶��\"�����Զ����㣬 \n\nȷ��Ҫ�����Զ�������";
	        }
	        if(confirm(clin)){
	          refreshForm('/xgxt/czxxGygl.do?method=gffAutoCount&doType=doCout');
	          var dd_html = "";
		      dd_html += "<div><td height='60' align='center'><font color='red'>���ڴ����У����Ժ�......<br><br></div>";		     			
	          showDiv(dd_html, 300, 120);
	        }
	     }
	</script>
</html>
