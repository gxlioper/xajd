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
		<script type="text/javascript">
				
		
		
		function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "viewalljyzdinfo.do?method=alljyzdinfo&doType=del&jytype=jyweb&pkValue="+pkValue;
		}
		
		</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>

		<div class="mainframe">
			<div class="ny_midframe">
				<div class="leftframe">
					<jsp:include flush="true" page="left_search.jsp"></jsp:include>
					
					
				</div>
				<div class="ny_rightframe">
			<div class="liebiao">
							<h3>
								��ǰλ�ã���ҳѡ�� ��ҵָ�� ��
								<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
								</b>&nbsp;</FONT>����ؼ�¼
							</h3>
						<font color="red" size="4">�Բ���,��ģ�黹�ڿ�����...</font>
				<%--<table width="93%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr height="25" class="btys">
						<td align="left">
							<font color="red">���</font>
						</td>
						<td align="left">
							<font color="red">����</font>
						</td>
						<td align="left">
							<font color="red">�Ա�</font>
						</td>
						<td align="left">
							<font color="red">��ѯʦ�ʸ�</font>
						</td>
						<td align="center">
							<font color="red">����</font>
						</td>
					</tr>
					<logic:iterate name="jyzd" id="s">
						<tr height="25" onmouseover="rowOnClick2(this)">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="<bean:write name='v' />" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="left">
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<td align="center">
								<font color="#0F03AF"> 
								<a href="" target="_blank"
									onclick="newpage_zx(this)"><font color="#0F03AF"> ��ϸ����</font></a>&nbsp;|&nbsp;
							    <a href=""
									target="_blank" onclick="newpage_zx(this)"><font color="#0F03AF">������ѯ</font></a>&nbsp;
									<logic:equal name="usertype" value="admin" scope="session">|&nbsp; 
								<a onclick="delone(this)" href=""><font color="#0F03AF">ɾ��</font>
										</a>
									</logic:equal>
								
									
							</td>
						</tr>
					</logic:iterate>
				</table>
					--%></div>
					<h2></h2>
				</div>
			</div>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>
		<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("��¼ɾ���ɹ���");
                    </script>
				</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="delete">
				<logic:equal name="delete" value="no">
					<script>
                      alert("��¼ɾ��ʧ�ܣ�");
                    </script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
