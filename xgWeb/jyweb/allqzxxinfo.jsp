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
				
	
		function newpage_qzxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "qzxxinfo.do?method=qzxxinfo&jytype=jyweb&doType=view&pkValue="+pkValue;
		}
		
		
		function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  obj.href = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&doType=del&pkValue="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&doType2=change";
            document.forms[0].submit();
		}
		
		function qingkong()
		{
			document.forms[0].action = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&doType3=qingkong";
            document.forms[0].submit();
		}
	
		function query()
		{
			document.forms[0].action = "viewallqzxxinfo.do?method=allqzxxinfo&jytype=jyweb&act=query";
            document.forms[0].submit();
		}
	
		function   document.onclick(){   
           showSel(false)   
        }   
        function   showSel(flag){   
        document.all.hyfl.style.display=(flag?"block":"none")   
        window.event.cancelBubble=true;   
        }   
        function   selText(obj){   
        obj.value=(document.all.hyfl.options[document.all.hyfl.selectedIndex].text)    
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
		<html:form action="/viewallqzxxinfo" method="post">
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<div class="default_box">
							<h3><em>����ְλ</em></h3>
							<table>
								<tr>
									<td colspan="2">
										<bean:message key="lable.xsgzyxpzxy" />��
										<html:select name="rs1" property="xymc" styleId="xymc"
											style="width:113px" onchange="refreshtheweb();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xymc"
												labelProperty="xymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										רҵ��
										<html:select name="rs1" property="zymc" styleId="zymc"
											style="width:113px">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zymc"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										�Ա�
									</td>
									<td>
										<html:select name="rs1" property="xb" style="width:100%">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="Ů">Ů</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										��ְ��ҵ��
									</td>
									<td>
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<html:text name="rs1" property="iData" style="width:78px"
														readonly="true" styleClass="inputtext" />
													<%--<input name=iData size="13"
														style="margin:0;border-right-width:0px;height:20;" />
													--%>
													<input type=button onclick=showSel(true) value="6"
														style="margin:0;cursor:hand;font-family:Webdings;width:18px;height:18px;vertical-align:baseline;padding:1px;line-height:5px" />
												</td>
											</tr>
											<tr>
												<td>
													<html:select name="rs1" property="hyfl" size="10"
														style="position:absolute;display:none"
														onchange="selText(iData)" styleClass="inputtext">
														<html:option value=""></html:option>
														<html:options collection="hyflList" property="hyfl"
															labelProperty="hyfl" />
													</html:select>
												</td>
											</tr>
										</table>

									</td>
								</tr>
								<tr>
									<td>
										��ְ����
									</td>
									<td>
										<html:text name="rs1" property="qzyx" style="width:100%"  styleClass="inputtext" />

									</td>
								</tr>
								<tr>
									<td>
										����ʱ�䣺
									</td>
									<td>
										<html:select name="rs1" property="xjsj" style="width:100%">
											<html:option value=""></html:option>
											<html:option value="-1">����</html:option>
											<html:option value="-2">������</html:option>
											<html:option value="-7">һ����</html:option>
											<html:option value="-15">������</html:option>
											<html:option value="-30">һ����</html:option>
											<html:option value="-90">������</html:option>
											<html:option value="-180">������</html:option>
											<html:option value="-365">һ����</html:option>

										</html:select>
									</td>
								</tr>
								<tr align="center">
									<td colspan="2">
										<button onClick="query();">
											�� ��
										</button>
										&nbsp;&nbsp;
										<button onClick="qingkong();">
											�� ��
										</button>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="liebiao">
								<h3>
									��ǰλ�ã���ҳѡ�� ��ְ��Ϣ ��
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>����ؼ�¼
								</h3>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<tr height="25" class="btys">
									<td align="center">
										רҵ
									</td>
									<td align="center">
										�Ա�
									</td>
									<td align="center">
										��ְ����
									</td>
									<td align="center">
										����ʱ��
									</td>
									<td align="center">
										����
									</td>
								</tr>
								<logic:iterate name="rs" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name='v' />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3">
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<td>
										<div align="center">
											<a onclick="newpage_qzxx(this)" href="" target="_blank">�鿴��ϸ</a>
											<logic:equal name="usertype" value="admin" scope="session">
									|&nbsp;<a onclick="delone(this)" href="">ɾ�� </a>
											</logic:equal>
											</div>
										</td>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="98%" align="center" class="tbborder">
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
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" ></button>
		</html:form>
	</body>
</html>
