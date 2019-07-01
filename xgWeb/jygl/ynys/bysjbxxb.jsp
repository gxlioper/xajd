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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript">
	    function add(url){
        var xsxh = document.getElementById("xh").value;
        var name = document.getElementById("xm").value;

    	
    	if(xsxh==""){
    	alert("学号必须填写！");
    	return false;
    	}
    	if(!isNumber(xsxh)){
    	alert("学号输入错误！");
    	return false;
    	}
    	if(name==""){
    	alert("姓名必须填写！");
    	return false;
    	}
		 		document.forms[0].action = "/xgxt/bysjbxxbSave.do?doType=save";
		 		document.forms[0].submit();

    }
    
    function reinputagain(url){
    		
            document.forms[0].action = "/xgxt/bysjbxxbSave.do?act=cancle";
		 	document.forms[0].submit();
    }
    


function isCard(s){ 
	s = trim(s); 
	var p = /^\d{15}(\d{2}[xX0-9])?$/; 
	return p.test(s);
}
		   
function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
}
	
	
	
	
		function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}

	function loadXian(){
		var shi = document.getElementById("jgshi").value;	
		getStuDetails.getXianList(shi,function(data){
			if (data != null) {
					var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);							
						DWRUtil.addOptions(objId,data,"xiandm","xianmc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}		
		});
	}
  
	</script>
	<body>
		<html:form action="/bysjbxxb_input.do" method="post">
			<html:hidden property="jg" styleId="jg" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：就业管理 - 学生信息 - 学生信息上报
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />
			<input type="hidden" id="url" name="url" value="/bysjbxxb_input.do" />
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
                      alert("您输入的学号无效!");
                   </script>
				</logic:equal>

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:25px">
							<td colspan="4" align="center">
								<b>毕业生基本信息</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>学号:
							</td>
							<td align="left" width="35%">
								<html:text name='rs' property="xh" styleId="xh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>学号：
							</td>
							<td align="left" width="35%">
								<html:text property="xh" name="rs" styleId="xh" readonly="true"
									style="width:210px" />
							</td>
						</logic:equal>
						<td align="right" width="10%">
							<font color="red">*</font>姓名：
						</td>
						<td align="left" width="40%">
							<html:text name="rs" property="xm" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:select name="rs" property="xb" style="width:60px">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:select name="rs" property="nj" styleId="nj"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">

						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:select name="rs" property="zymc" styleId="zymc"
								style="width:270px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zymc"
									labelProperty="zymc" />
							</html:select>
						</td>
						<td align="right">
							生源地：
						</td>
						<td align="left">

							<html:select name="rs" property="jgs" onchange="loadShi()"
								styleId="jgshen">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="jgshi" styleId="jgshi"
								onchange="loadXian()">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select name="rs" property="jgx" styleId="jgxian">
								<html:options collection="xianList" labelProperty="xianmc"
									property="xiandm" />
							</html:select>



							<%--							<html:select name="rs" property="sydq" styleId="sydq"--%>
							<%--								style="width:150px">--%>
							<%--								<html:option value=""></html:option>--%>
							<%--								<html:options collection="sydqdmList" property="sydq"--%>
							<%--									labelProperty="sydq" />--%>
							<%--							</html:select>--%>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							出生日期：
						</td>
						<td align="left">
							<html:text name="rs" style="cursor:hand;" styleId="csrq"
								property="csrq" onclick="return showCalendar('csrq','y-mm-dd');"
								readonly="true" />
						</td>
						<td align="right">
							民族：
						</td>
						<td align="left">
							<html:select name="rs" property="mz" styleId="mz"
								style="width:100px">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzmc"
									labelProperty="mzmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							政治面貌：
						</td>
						<td align="left">
							<html:select name="rs" property="zzmm" styleId="zzmm"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="zzmmdmList" property="zzmm"
									labelProperty="zzmm" />
							</html:select>
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							入学时间：
						</td>
						<td align="left">
							<html:select name="rs" property="nd" styleId="nd"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<td align="right">
							毕业年度：
						</td>
						<td align="left">
							<html:select name="rs" property="bynd" style="width:150px">
								<html:option value=""></html:option>
								<html:option value="2004"></html:option>
								<html:option value="2005"></html:option>
								<html:option value="2006"></html:option>
								<html:option value="2007"></html:option>
								<html:option value="2008"></html:option>
								<html:option value="2009"></html:option>
								<html:option value="2010"></html:option>
								<html:option value="2011"></html:option>
								<html:option value="2012"></html:option>
								<html:option value="2013"></html:option>
								<html:option value="2014"></html:option>
								<html:option value="2015"></html:option>
								<html:option value="2016"></html:option>
								<html:option value="2017"></html:option>
							</html:select>

						</td>

					</tr>

					<tr style="height:22px">
						<td align="right">
							就业方式：
						</td>
						<td align="left">
							<html:select name="rs" property="jyfs" styleId="jyfs"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="byqxdmList" property="byqx"
									labelProperty="byqx" />
							</html:select>
						</td>
						<td align="right">
							就业意向：
						</td>
						<td align="left">
							<html:select name="rs" property="jyyx" styleId="jyyx"
								style="width:270px">
								<html:option value=""></html:option>
								<html:options collection="dwxzdmList" property="dwxz"
									labelProperty="dwxz" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							欠款情况：
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="qkqk" style="width:100%"
								rows="4" />
						</td>

					</tr>
					<tr>
						<td align="right">
							奖惩情况：
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="jcqk" style="width:100%"
								rows="4" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="add('/xgxt/bysjbxxbSave.do')"
						style="width:80px">
						提 交
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" type="reset" onclick="returntobegin()"
						style="width:80px">
						重 置
					</button>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
                      alert("提交成功！");
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("重复提交！操作失败!");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
