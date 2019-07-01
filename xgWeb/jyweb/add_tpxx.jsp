<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<script type="text/javascript">
		function updatethepic(){
		     var bt = document.getElementById("bt").value;
		     var nr = document.getElementById("nr").value;
		
		if(bt==""||nr==""){
		    alert("�뽫��*�ŵĲ�����д������");
		    return false;
		}
			
		    document.forms[0].action = "updatepic.do?method=updatepic&jytype=jyweb&doType=save";
	        document.forms[0].submit();
		}
		function updateinfo(){
		var url ="updatepictureinfo.do?method=updatePictureInfo&jytype=jyweb&pkValue=";
		
		var pkValue ="";	 
		  pkValue = curr_row.getElementsByTagName("input")[0].value;
		  url += pkValue;
		  showTopWin(url, 650, 500);
		  
		}
		function refreshtheweb()
		{
			document.forms[0].action = "updatepic.do?method=updatepic&jytype=jyweb";
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
		<html:form action="/updatepic" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="webtype" value="tpxx" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="contrl.jsp"></jsp:include>
						<div class="rdxw">
							<h1></h1>
						</div>
						<div class="yqlj">
							<h1></h1>
							<span></span>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
								<h3>
									��ǰλ�ã�
									<a href="index.do">��ҳ</a>ѡ�� ͼƬ��Ϣ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>���������<font color="red">&nbsp;&nbsp;��ʾ��˫��һ�пհ״������޸����ݡ�</font>
								</h3>
							<table width="98%" align="center" class="tbborder">
								<tr height="25" class="btys">
									<td>
										<font color="red">����</font>
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
								<logic:iterate id="list" name="rs">
									<tr height="25" onmouseover="rowOnClick2(this)" ondblclick="updateinfo()"> 
										<td>
										<input type="hidden" value="<bean:write name="list" property="rid"/>"/>
											<a
												href="viewtpxxinfo.do?method=tpxxinfo&jytype=jyweb&doType=view&pkValue=<bean:write name="list" property="rid"/>"
												target="_blank"><bean:write
														name="list" property="bt" /> </a>
										</td>
										<td>
											<bean:write name="list" property="fbr" />
										</td>
										<td>
											<bean:write name="list" property="fbsj" />
										</td>
										<td>
											<logic:equal name="usertype" value="admin" scope="session">
												<a href="#" onclick="updateinfo()">�޸�</a>
												<a href="updatepic.do?method=updatepic&jytype=jyweb&doType=del&pkValue=<bean:write name="list" property="rid"/>">ɾ��</a>
											</logic:equal>
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
							<br>
							<div id="admingl2" style="display:">
								<button onclick="setdisplay()">
									����ͼƬ��Ϣ
								</button>
							</div>
							<div id="admingl" style="display: none">
								<button onclick="setdisplay()">
									�رմ���
								</button>
								<table width="98%" align="center" class="tbborder">
									<tr>
										<td align="left" width="70">
											<font color="red">*</font>����:
										</td>
										<td align="left">

											<input style="width:96%" type="text" name="bt" id="bt"
												size="30" maxlength="25" />

										</td>
									</tr>
									<tr>
										<td width="70" align="center" valign="top">
											<font color="red">*</font>ͼƬ����:
											<p>
												<font color="red">˵<br>��<br>��<br>��<br>��<br>500<br>��</font>
											</p>
										</td>
										<td align="left">
											<textarea rows="10" name="nr" id="nr" type="_moz"
												style="width:96%"></textarea>
										</td>
									</tr>
									<tr>
										<td width="70" align="left">
											ͼƬ·����
										</td>
										<td align="left">
											<div align="left">
												&nbsp;&nbsp;&nbsp;
												<input type="file" name="uploadFile" style="width:60%">
											</div>

										</td>
									</tr>
								</table>

								<div align="center">
									<button onclick="updatethepic();" style="width:80px">
										�� ��
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" onclick="" style="width:80px"
										id="buttonSave">
										�� ��
									</button>
								</div>
							</div>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" >
			</button>
		</html:form>
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
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("�ļ��ϴ��ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ļ��ϴ�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
