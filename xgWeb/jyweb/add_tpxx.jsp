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
		function updatethepic(){
		     var bt = document.getElementById("bt").value;
		     var nr = document.getElementById("nr").value;
		
		if(bt==""||nr==""){
		    alert("请将带*号的部分填写完整！");
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
									当前位置：
									<a href="index.do">首页</a>选择 图片信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共
									<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
									</b>&nbsp;</FONT>条相关资料<font color="red">&nbsp;&nbsp;提示：双击一行空白处可以修改内容。</font>
								</h3>
							<table width="98%" align="center" class="tbborder">
								<tr height="25" class="btys">
									<td>
										<font color="red">标题</font>
									</td>
									<td>
										<font color="red">发布人</font>
									</td>
									<td>
										<font color="red">发布时间</font>
									</td>
									<td>
										<font color="red">操作</font>
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
												<a href="#" onclick="updateinfo()">修改</a>
												<a href="updatepic.do?method=updatepic&jytype=jyweb&doType=del&pkValue=<bean:write name="list" property="rid"/>">删除</a>
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
									发布图片信息
								</button>
							</div>
							<div id="admingl" style="display: none">
								<button onclick="setdisplay()">
									关闭窗体
								</button>
								<table width="98%" align="center" class="tbborder">
									<tr>
										<td align="left" width="70">
											<font color="red">*</font>标题:
										</td>
										<td align="left">

											<input style="width:96%" type="text" name="bt" id="bt"
												size="30" maxlength="25" />

										</td>
									</tr>
									<tr>
										<td width="70" align="center" valign="top">
											<font color="red">*</font>图片内容:
											<p>
												<font color="red">说<br>明<br>不<br>超<br>过<br>500<br>字</font>
											</p>
										</td>
										<td align="left">
											<textarea rows="10" name="nr" id="nr" type="_moz"
												style="width:96%"></textarea>
										</td>
									</tr>
									<tr>
										<td width="70" align="left">
											图片路径：
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
