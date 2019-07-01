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
				
	
		function newpage_syjs(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewsyjsinfo.do?method=qitainfo&jytype=jyweb&rowid="+pkValue;
		}
		
		function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "viewallzczpinfo.do?method=allsyjsinfo&jytype=jyweb&doType=del&rowid="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewallsyjsinfo.do?method=allsyjsinfo&jytype=jyweb";
            document.forms[0].submit();
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
        <html:form action="/viewallsyjsinfo" method="post">
		<div class="mainframe">
			<div class="ny_midframe">
				<div class="leftframe">
					<jsp:include flush="true" page="left_search.jsp"></jsp:include>

				
				</div>
				<div class="ny_rightframe">
					<div class="liebiao">
							<h3>
								��ǰλ�ã���ҳѡ�� ��Դ���� ��
								<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
								</b>&nbsp;</FONT>����ؼ�¼
							</h3>
						<table width="98%" align="center" class="tbborder">
							<thead><tr height="25" class="btys">
								<td align="left">
									����
								</td>
								<td align="center">
									������
								</td>
								<td align="center">
									����ʱ��
								</td>
								<logic:equal name="usertype" value="admin" scope="session">
									<td align="right">
										����
									</td>
								</logic:equal>
							</tr></thead>
							<logic:iterate name="syjs" id="s4">
								<tr height="25" onmouseover="rowOnClick2(this)">
									<logic:iterate id="v4" name="s4" offset="0" length="1">
										<input type="hidden" value="<bean:write name='v4' />" />
									</logic:iterate>
									<logic:iterate id="v4" name="s4" offset="1" length="1">
										<td class="tubiao">
											<a target="_blank" onclick="newpage_syjs(this)" href="">
												<bean:write name="v4" /> </a>
										</td>
									</logic:iterate>
									<logic:iterate id="v4" name="s4" offset="2" length="1">
										<td align="center">
											<bean:write name="v4" />
										</td>
									</logic:iterate>
									<logic:iterate id="v4" name="s4" offset="3" length="1">
										<td align="center">
											<bean:write name="v4" />
										</td>
										<logic:equal name="usertype" value="admin" scope="session">
											<td align="right">
												<a onclick="delone(this)" href="">ɾ��
												</a>
											</td>
										</logic:equal>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="99%" id="rsTable" class="tbborder">
							<TR>
								<TD>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
								</TD>
							</TR>
						</TABLE>
					</div>
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
		<button onclick="refreshtheweb()" id="search_go" style="display: none"></button>
		</html:form>
	</body>
</html>
