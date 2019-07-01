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
		  var xymc = document.getElementById("xydm").value;   
	      var xyjbqk = document.getElementById("xyjbqk").value; 
	      var zymc = document.getElementById("zydm").value; 
	      var pycc = document.getElementById("pycc").value;
	      var xz = document.getElementById("xz").value; 
	      var bmrs = document.getElementById("rs1").value;
	      if(xymc==""){
	      alert("<bean:message key="lable.xsgzyxpzxy" />名称不能为空！");
	      document.getElementById("xydm").focus();
	      return false;
	      }
	      if(xyjbqk==""){
	      alert("<bean:message key="lable.xsgzyxpzxy" />基本情况不能为空！");
	      document.getElementById("xyjbqk").focus();
	      return false;
	      }
	      if(zymc==""){
	      alert("专业名称不能为空！");
	      document.getElementById("zydm").focus();
	      return false;
	      }
	      if(pycc==""){
	      alert("培养层次不能为空！");
	      document.getElementById("pycc").focus();
	      return false;
	      }
	      if(xz != ""){
	      if(!isNumber(xz)){
		      alert("学制只能是数字！");
		      return false;
		      }
	      }
	      if(bmrs != ""){
		  if(!isNumber(bmrs)){
		      alert("人数只能是数字！");
		      return false;
		      }
	      }
	        BatAlert.showTips('正在提交，请稍侯...');
			 document.forms[0].action = "yxjzyjs.do?act=add&doType=add";
			 document.forms[0].submit();
    }
	function initZyList(){
		var xydm = "";
		var query = "";
		var userName = "";
		var isFdy = "false";
		if($("isFdy")){isFdy = $("isFdy").value};
		if($("xy")){xydm = $("xy").value};	
		if($("userName")){userName = $("userName").value};
			if(xydm == null || xydm == ""){
				xydm = "%";
			} else{
				xydm = "%" + xydm +"%";
			}
			query = xydm;	
			if($("isBzr")){isBzr = $("isBzr").value};
			GetListData.getZyList(query,userName,isFdy,isBzr,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId){
						if($(objId).value != "" && $(objId).value != null){
							alert($(objId).tagName);
							for(var i = 0;i < $(objId).options.length; i++){
								if($(objId).options[i].value == $(objId).value){
									$(objId).options[i].selected = true;
									return true;
								}
							}
						}
						}
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}
			});
	}
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    }  
			
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/yxjzyjs.do" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>系（院）及专业介绍录入</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>系(院)名称：
					</td>
					<td colspan="3">
					<logic:equal value="xy" name="userType" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />：
								<input name="xydndt" value="<bean:write name="bmxxmc"/>" disabled="disabled"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="xy" name="userType" scope="session">
							<logic:equal value="stu" name="userType" scope="session">
								<input name="xydndt" value="<bean:write name="bmxxmc"/>" disabled="disabled"/>
							<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="stu" name="userType" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:notEqual>
							</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>系(院)基本情况：
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="xyjbqk" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>专业名称：
					</td>
					<td>
						<html:select name="rs1" property="zydm" onchange="" style="width:220px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
						</html:select>
					</td>
					<td align="right">
						人数：
					</td>
					<td>
						<html:text name="rs1" property="rs1"/>人
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>培养层次：
					</td>
					<td>  
						<html:text name="rs1" property="pycc" style="width: 70%"/>
					</td>
					<td align="right">
						学制：
					</td>
					<td>
						<html:text name="rs1" property="xz"/>年
					</td>
				</tr>
				<tr>
					<td align="right">
						学位：
					</td>
					<td colspan="3">
						<html:text name="rs1" property="xw" style="width: 80%"/>
					</td>
					
				</tr>
				<tr>
					<td align="right">
						培养目标及特色：
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="pymb" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						主要课程：
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="zykc" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						就业前景及方向：
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="jyqj" style="width: 100%" rows="8"></html:textarea>
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

