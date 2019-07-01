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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">	
		
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function add(){
		  var gzjcs = document.getElementById("gzjcs").value;   
	      var zywt = document.getElementById("zywt").value; 
	      var jyxsfx = document.getElementById("jyxsfx").value; 
	      var lxr = document.getElementById("lxr").value;
	      var lxdh = document.getElementById("lxdh").value;   
	      var tbsm = document.getElementById("tbsm").value; 
	      if(gzjcs==""){
	      alert("系（院）开展的主要工作及措施不能为空！");
	      document.getElementById("gzjcs").focus();
	      return false;
	      }
	      if(zywt==""){
	      alert("面临的主要困难,问题不能为空！");
	      document.getElementById("zywt").focus();
	      return false;
	      }
	      if(jyxsfx==""){
	      alert("就业形势分析不能为空！");
	      document.getElementById("jyxsfx").focus();
	      return false;
	      }
	      if(lxr==""){
	      alert("就业进展情况报送联系人不能为空！");
	      document.getElementById("lxr").focus();
	      return false;
	      }
	 
	      if(lxdh==""){
	      alert("联系电话不能为空！");
	      document.getElementById("lxdh").focus();
	      return false;
	      }
	      if(tbsm==""){
	      alert("填表说明不能为空！");
	      document.getElementById("tbsm").focus();
	      return false;
	      }
	      
	         BatAlert.showTips('正在提交，请稍侯...');
			 document.forms[0].action = "jyjzjkbs.do?act=add&doType=add";
			 document.forms[0].submit();
    }
	
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/bysbm" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>就业进展情况报送录入</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" style="width: 20%">
						<font color="red">*</font>系（院）开展的<br>主要工作<br>及措施
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="gzjcs" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>面临的主要<br>困难,问题
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="zywt" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>就业形势<br>分析
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="jyxsfx" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>就业进展情况<br>报送联系人
					</td>
					<td>  
						<html:text name="rs1" property="lxr"/>
					</td>
					<td align="right">
						<font color="red">*</font>联系电话
					</td>
					<td>
						<html:text name="rs1" property="lxdh" />
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<font color="red">*</font>填表说明
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="tbsm" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
			</table>
			<div align="center">
				<button class="button2" onclick="add();" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
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
		</logic:notEmpty>
	</body>
</html>

