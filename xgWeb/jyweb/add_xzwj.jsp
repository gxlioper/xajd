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
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
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
		function updatethefile(){
		     var wjh = document.getElementById("wjh").value;
		     var wjm = document.getElementById("wjm").value;
		     var wjffbm = document.getElementById("wjffbm").value;
		     var wjscsm = document.getElementById("wjscsm").value;
		
		if(wjh==""||wjm==""||wjffbm==""||wjscsm==""){
		    alert("请将带*号的部分填写完整！");
		    return false;
		}
			
		    document.forms[0].action = "updatewj.do?method=updatewj&jytype=jyweb&doType=save";
	        document.forms[0].submit();
		}
		function updateinfo(){
		var url ="updatewjxzinfo.do?method=updateWjxzInfo&jytype=jyweb&pkValue=";
		
		var pkValue ="";	 
		  pkValue = curr_row.getElementsByTagName("input")[0].value;
		  url += pkValue;
		  showTopWin(url, 650, 500);
		  
		}
		function refreshtheweb()
		{
			document.forms[0].action = "updatewj.do?method=updatewj&jytype=jyweb";
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
		<html:form action="/updatewj" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="webtype" value="xzzx" />
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
									当前位置：
									<a href="index.do">首页</a>选择 文件下载 共
									<font color="red"><b>&nbsp;<bean:write name="rsNum" />&nbsp;</b>
									</font>条相关记录&nbsp;&nbsp;<font color="red">提示：双击一行空白处可以修改内容。</font>
								</h3>
							<table width="98%" align="center" class="tbborder" id="tb1">
								<thead height="25" class="btys">
								<tr>
									<td>
										<font color="red">文件号</font>
									</td>
									<td>
										<font color="red">文件名</font>
									</td>
									<td>
										<font color="red">上传部门</font>
									</td>
									<td>
										<font color="red">上传人</font>
									</td>
									<td>
										<font color="red">上传时间</font>
									</td>
									<td>
										<font color="red">操作</font>
									</td>
								</tr>
								</thead>
								<logic:iterate id="list" name="rs">
									<tr height="25" onmouseover="rowOnClick2(this)" ondblclick="updateinfo()">
										<td title="<bean:write name="list" property="wjh" />">
										<input type="hidden" value="<bean:write name="list" property="wjh" />"/>
											<bean:write name="list" property="wjh" />
										</td>
										<td title="<bean:write name="list" property="wjh" />">
											<bean:write name="list" property="wjm" />
										</td>
										<td>
											<bean:write name="list" property="wjffbm" />
										</td>
										<td>
											<bean:write name="list" property="ffr" />
										</td>
										<td>
											<bean:write name="list" property="wjffsj" />
										</td>
										<td>
											<a
												href="viewwjxzinfo.do?method=wjxzsinfo&jytype=jyweb&wjh=<bean:write name="list" property="wjh"/>"
												target="_blank">查看</a>
											<logic:equal name="usertype" value="admin" scope="session">|&nbsp;
											<a href="#" onclick="updateinfo(this)" >修改</a>|&nbsp;
											<a href="updatewj.do?method=updatewj&jytype=jyweb&doType=del&pkValue=<bean:write name="list" property="wjh"/>">删除</a>
											</logic:equal>

										</td>
									</tr>
								</logic:iterate>
								<logic:present name="list">
									<script language="javascript">
										changeView('tb1',0,9,'no','yes');
										changeView('tb1',1,9,'no','yes');
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
							<div id="admingl2" style="display:">
								<button onclick="setdisplay()">
									上传新文件
								</button>
							</div>
							<div id="admingl" style="display: none">
								<button onclick="setdisplay()">
									关闭窗体
								</button>

								<table width="98%" align="center" class="tbborder">
									<tr>
										<td width="90">
											<font color="red">*</font>文件号:
										</td>
										<td>
											<div align="left">
												<input type="text" name="wjh" id="wjh" size="30"
													maxlength="25" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="90">
											<font color="red">*</font>文件名:
										</td>
										<td>
											<div align="left">
												<input type="text" name="wjm" id="wjm" size="30"
													maxlength="50" />
											</div>
										</td>
									</tr>
									<tr>
										<td width="100px">
											<font color="red">*</font>上传部门:
										</td>
										<td>
											<div align="left">
												<input type="text" name="wjffbm" id="wjffbm" value="学生处"
													size="20" maxlength="20" readonly="readonly" />
											</div>
										</td>
									</tr>
									<tr>
										<td align="left" valign="top">
											<font color="red">*</font>文件说明:
											<p>
												<font color="red">说<br>明<br>不<br>超<br>过<br>500<br>字</font>
											</p>
										</td>
										<td>
											<textarea rows="10" style="width:100%" name="wjscsm"
												id="wjscsm" type="_moz"></textarea>
										</td>
									</tr>
									<tr>
										<td align="right">
											附件：
										</td>
										<td align="left">
											<div align="left">
												<input type="file" name="uploadFile" style="width:60%">
											</div>
										</td>
									</tr>
								</table>
								<div align="center">
									<button onclick="updatethefile();" style="width:80px">
										上 传
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="reset" onclick="" style="width:80px"
										id="buttonSave">
										重 填
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
                      alert("记录删除成功！");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("记录删除失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("文件上传成功！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("文件上传失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
