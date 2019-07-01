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
		<title>学生工作管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />
		<base target="_self">
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function updatethepic(){
		     var bt = document.getElementById("bt").value;
		     var nr = document.getElementById("nr").value;
		
		if(bt==""||nr==""){
		    alert("请将带*号的部分填写完整！");
		    return false;
		}
			BatAlert.showTips('正在上传，请等待...');
		    document.forms[0].action = "updatethephoto.do?doType=save";
	        document.forms[0].submit();
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
		<html:form action="/updatethephoto" method="post"
			enctype="multipart/form-data">
			<%--
							<table width="98%" align="center" >
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
									<tr height="25" onmouseover="rowOnClick2(this)">
										<td>
											<bean:write name="list" property="bt" />
										</td>
										<td>
											<bean:write name="list" property="fbr" />
										</td>
										<td>
											<bean:write name="list" property="fbsj" />
										</td>
										<td>
											<logic:equal name="usertype" value="admin" scope="session">
												<a
													href="viewtpxxinfo.do?method=tpxxinfo&jytype=jyweb&doType=view&pkValue=<bean:write name="list" property="rid"/>"
													target="_blank"><font color="#0F03AF">查看详细</font> </a>
											&nbsp;|&nbsp;
								        <a
													href="updatepic.do?method=updatepic&jytype=jyweb&doType=del&pkValue=<bean:write name="list" property="rid"/>"><font
													color="#0F03AF">删除</font> </a>
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
							--%>
			<br>

			<table width="98%" align="center" class="tbborder">
				<tr>
					<td align="center" width="70">
						<font color="red">*</font>标题:
					</td>
					<td align="left">
						<input style="width:70%" type="text" name="bt" id="bt" size="30"
							maxlength="50" />
						图片分类：
						<select name="remark">
							<option value="国旗护卫队">国旗护卫队</option>
							<option value="军训相关">军训相关</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="70" align="center" valign="top">
						<font color="red">*</font>相关描述:
						<p>
							<font color="red">说<br>明<br>不<br>超<br>过<br>100<br>字</font>
						</p>
					</td>
					<td align="left">
						<textarea rows="9" name="nr" id="nr" type="_moz"
							style="width:100%"></textarea>
					</td>
				</tr>
				<tr>
					<td width="70" align="center">
						图片路径：
					</td>
					<td align="left">
						<div align="left">
							<input type="file" name="uploadFile" style="width:60%">
							&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
							<button type="button" onclick="updatethepic();" style="width:60px">
								上 传
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" type="reset" onclick="" style="width:60px"
								id="buttonSave">
								重 填
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button"
								onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
								style="width:60px" id="buttonSave">
								关 闭
							</button>
						</div>
					</td>
				</tr>
			</table>

			<button type="button" onclick="refreshtheweb()" id="search_go"
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
