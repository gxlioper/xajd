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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<style type="text/css">
<!--
.style1 {font-size: 16px}
.style2 {font-size: 14px}
.style3 {
	color: #000000;
	font-size: 15px;
}
.style4 {
	font-size: 15px;
	font-weight: bold;
}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
	function add(){
		  var xsczqk = document.getElementById("xsczqk").value;   
	     
	      if(xsczqk==""){
	      alert("本学期跟踪服务及学生成长情况不能为空！");
	      document.getElementById("xsczqk").focus();
	      return false;
	      }
	      if(xsczqk.length>800){
		      alert("本学期跟踪服务及学生成长不能大于800个汉字！");
		      document.getElementById("xsczqk").focus();
		      return false;
		      }
	        BatAlert.showTips('正在提交，请稍侯...');
			 document.forms[0].action = "ahjg_wtqtgl.do?act=add&doType=add";
			 document.forms[0].submit();
  }
	</script>
	
	<body>
	<html:form action="/ahjg_wtqtgl.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：学生信息增加
			</div>
		</div>
		
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb" />
		<input type="hidden" id="url" name="url" value="/dtjs/ahjg/stu_info_add.jsp" />
		<input type="hidden" id="from" name="from" value="/dtjs/ahjg/stu_info_add.jsp" />
		<input type="hidden" id="xxdm" name="xxdm"
			value="<bean:write name="xxdm"/>" />
			
		<table width="100%" align="center" class="tbstyle">		
			<tr>
				<td>
<%--					<table width="100%" class="tbstyle">--%>
<%--						<tr>--%>
<%--							<td bgcolor="#CCCCCC">--%>
<%--								<div id="main1" style="color:blue;cursor:hand"--%>
<%--									onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">--%>
<%--									<div align="center" class="style1 style3">--%>
<%--										<strong>学生基本信息</strong>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child1">
						<table width="100%" align="center" class="tbstyle">
							<tr>
								<td align="right" width="15%">
									学号：
								</td>
								<td align="left" width="25%">
									<html:text name="rs" property="xh" styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"></html:text>
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</td>
								<td align="right" width="15%">
									年级：									
								</td>
								<td align="left" width="30%">
									<bean:write name="rs" property="nj" />
								</td>
							</tr>
							<tr>
								<td align="right">
									当前学年：
								</td>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
								<td align="right">
									当前学期：
								</td>
								<td align="left">
									<bean:write name="rs" property="xq" />
								</td>
							</tr>
							<tr>
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									性别：
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									专业：
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
								<tr>
									<td align="right">
										出生日期：
									</td>
									<td align="left">
										<bean:write name="rs" property="csrq" />
									</td>
									<td align="right">
										班级：
									</td>
									<td align="left">
										<bean:write name="rs" property="bjmc" />
									</td>
								</tr>
							<tr>
								<td align="right">
									民族：
								</td>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
								<td align="right">
									学制：
								</td>
								<td align="left">
									<bean:write name="rs" property="xz" />
								</td>
							</tr>
						
							
							<tr>
								<td align="center">
									<font color="red">*</font>本学<br>期跟<br>踪服<br>务及<br>学生<br>成长<br>情况
								</td>
								<td align="left" colspan="4">
									<html:textarea property="xsczqk" rows="8" style="width=100%"></html:textarea>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<div align="center">
				<button type="button" class="button2" onclick="add();" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
					关 闭
				</button>
			</div>
		</html:form>
			<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				    <script>
                      alert("提交成功！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("重复提交！操作失败!");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="iscz">
				<script>
                      alert("该学生信息已存在，请操作修改错做");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
