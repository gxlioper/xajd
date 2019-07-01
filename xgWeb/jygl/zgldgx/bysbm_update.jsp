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
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<base target="_self">

<script language="javascript" src="js/function.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script language="javascript">
	function add(){
		 var xymc = document.getElementById("xydm").value;   
	      var xh = document.getElementById("xh").value; 
	      var zymc = document.getElementById("zydm").value; 
	      var bmxm = document.getElementById("bmxm").value;
	      var xm = document.getElementById("xm").value;   
	      var xb = document.getElementById("xb").value; 
	      var syd = document.getElementById("syd").value; 
	      var bkqx = document.getElementById("bkqx").value;
	      var lxfs = document.getElementById("lxfs").value;
	      if(xymc==""){
	      alert("<bean:message key="lable.xsgzyxpzxy" />名称不能为空！");
	      document.getElementById("xydm").focus();
	      return false;
	      }
	      if(xh==""){
	      alert("学号不能为空！");
	      document.getElementById("xh").focus();
	      return false;
	      }
	      if(zymc==""){
	      alert("专业名称不能为空！");
	      document.getElementById("zydm").focus();
	      return false;
	      }
	      if(bmxm==""){
	      alert("报名名称不能为空！");
	      document.getElementById("bmxm").focus();
	      return false;
	      }
	 
	      if(xm==""){
	      alert("姓名不能为空！");
	      document.getElementById("xm").focus();
	      return false;
	      }
	      if(xb==""){
	      alert("性别不能为空！");
	      document.getElementById("xb").focus();
	      return false;
	      }
	      if(syd==""){
	      alert("生源地不能为空！");
	      document.getElementById("syd").focus();
	      return false;
	      }
	      if(bkqx==""){
		      alert("报名名称不能为空！");
		      document.getElementById("bkqx").focus();
		      return false;
		      }
	      if(lxfs==""){
		      alert("联系方式不能为空！");
		      document.getElementById("lxfs").focus();
		      return false;
		      }



	      	var pkValue ="";
	         var url = "bysbm.do?act=update&doType=update&pkValue=";
			 pkValue = document.getElementById("pkValue").value;
			 url += pkValue;
	         BatAlert.showTips('正在提交，请稍侯...');
			 document.forms[0].action = url;
			 document.forms[0].submit();
	}
	function initZyList1(){
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
	function change(){
		var type = document.getElementById("bmxm").value;
		if(type=="考研"){
			document.getElementById("bybmqx").style.display="none";
			document.getElementById("bybmky").style.display="inline";
			document.getElementById("bybmsq").style.display="none";
		}else if(type=="社区助理"){
			document.getElementById("bybmqx").style.display="none";
			document.getElementById("bybmky").style.display="none";
			document.getElementById("bybmsq").style.display="inline";
		}else{
			document.getElementById("bybmqx").style.display="inline";
			document.getElementById("bybmky").style.display="none";
			document.getElementById("bybmsq").style.display="none";
		}
	}
	</script>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
<html:form action="/yxjzyjs.do" method="post">
	<logic:empty name="rs1">
			未找到结果集
		</logic:empty>
	<logic:notEmpty name="rs1">
		<logic:iterate id="rs1" name="rs1">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>报名信息录入</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>报名项目：
					</td>
					<td>  
					<input id="pkValue" name="pkValue" type="hidden" value="${rs1.id }"/>
						<html:select name="rs1" property="bmxm" style="width: 70%" onchange="change();">
							<html:option value="--请选择--"></html:option>
							<html:option value="村官">村官</html:option>
							<html:option value="考研">考研</html:option>
							<html:option value="社区助理">社区助理</html:option>
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>学号：
					</td>
					<td>
						<html:text name="rs1" property="xh"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>姓名：
					</td>
					<td>  
						<html:text name="rs1" property="xm" style="width: 70%"/>
					</td>
					<td align="right">
						<font color="red">*</font>性别：
					</td>
					<td>
						<html:select name="rs1" property="xb">
							<html:option value="男">男</html:option>
							<html:option value="女">女</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>系(院)名称：
					</td>
					<td>
						<html:select name="rs1" property="xydm" onchange="initZyList1();" style="width:200px" styleId="xy">
									<html:option value="--请选择--"></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>专业名称：
					</td>
					<td>
						<html:select name="rs1" property="zydm" onchange="" style="width:150px" styleId="zy">
									<html:option value="--请选择--"></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>生源地：
					</td>
					<td>  
						<html:select name="rs1" property="syd">
							<html:option value="--请选择--"></html:option>
							<html:options collection="sydList" property="ssdm" labelProperty="ssmc"/>
						</html:select>
					</td>
					<td align="right" id="bybmqx" style="display: none;">
						<font color="red">*</font>报考区县：
					</td>
						<td align="right" id="bybmky">
						<font color="red">*</font>报考学校：
					</td>
						<td align="right" id="bybmsq" style="display: none;">
						<font color="red">*</font>报考社区：
					</td>
					<td>
						<html:text name="rs1" property="bkqx"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>联系方式：
					</td>
					<td>  
						<html:text name="rs1" property="lxfs" style="width: 70%"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						备注：
					</td>
					<td align="right" colspan="3">
					<html:textarea name="rs1" property="bz" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
			</table>
		</logic:iterate>
	</logic:notEmpty>
	<div align="center">
	<button class="button2"
		onclick="add();" style="width: 80px">
	提 交</button>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button class="button2"
		onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
		style="width: 80px">关 闭</button>
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

