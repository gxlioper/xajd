<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<base target="_self">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link rel="stylesheet" type="text/css" media="all" href="style/calendar.css" title="win2k-cold-1" />
    <script type="text/javascript" src="style/calendar.js"></script>
    <script type="text/javascript" src="style/calendar-zh.js"></script>
    <script type="text/javascript" src="style/calendar-setup.js"></script>
	<script language="JavaScript" src="style/dmwh.js"></script>
	<script language="JavaScript" src="style/xjgl.js"></script>
	<script language="JavaScript" src="style/jhgl.js"></script>
	<script type="text/javascript" src="dwr/interface/ajaxCommon.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script language="javascript">
	function checkForm(){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("请输入学号！");
				return false;
			}
			var szs = document.forms(0).szs.value;
			if(szs==""){
				alert("请选择所在省！");
				return false;
			}
			var szq = document.forms(0).szq.value;
			if(szq==""){
				alert("请选择所在区！");
				return false;
			}
			changTab('/yjsjwgl/jyjlzg.do?dotype=add');
	}
	function checkModify(){
			var xh = document.forms(0).xh.value;
			if(xh==""){
				alert("请输入学号！");
				return false;
			}
			changTab('/yjsjwgl/jyjlzg.do?dotype=modify');
		}
	function getShx(){
			var sdm = document.forms[0].szs.value;
			if(sdm!=null&&sdm!=""){
				ajaxCommon.getShxxx(sdm,shx);
			}
		}
	function shx(data){
		var szq = document.getElementById("szq");
    	for(var i=szq.length-1;i>0;i--){
    		szq.remove(i);
        }
        if(data!=null){ 
    		var le = data.length;
    		if(le>0){   			
    			for(var i=0;i<le;i++){ 
    				var oOption = document.createElement("OPTION"); 
    				szq.options.add(oOption);
    				oOption.innerText = data[i].mc;
    				oOption.value = data[i].dm;
    				if(data[i].dm == "${map.jgdm}"){
    					oOption.selected="selected";
    				}
    			}
    		}
   		}
	}
	</script>
</head>
 <%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<body>
	<html:form method="POST" action="jyjlgl"
		enctype="multipart/form-data">		
		<logic:equal name="page" value="add">
		<div id="title">
			<div class="titiel_img"></div>
			当前所在位置：就业奖励管理-->增加页面
		</div>
		<div>
			<table width="99%">
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>学号
					</td>
					<td><input type="text" name="xh" /></td>
				</tr>
				<tr>
					<td align="center" width="45%">
						个人就业单位
					</td>
					<td><input type="text" name="grjydw"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>所在省
					</td>
					<td>
						<html:select property="szs" onchange="getShx()" style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="slist" property="dm" 
									labelProperty="mc" />
						</html:select>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>所在区
					</td>
					<td>
						<html:select property="szq" style="width:150px">
							<html:option value=""></html:option>
						</html:select>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						证书领取人
					</td>
					<td><input type="text" name="zslqr"/></td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						奖金领取人
					</td>
					<td><input type="text" name="jjlqr"/></td>	
				</tr>
			</table>
			<div id="button">
				<button class="button2" onclick="checkForm()">
					增 加
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					关 闭
				</button>
			</div>
		</div>
		</logic:equal>
	
	<!-- 以上为增加页面内容，以下为修改页面内容 -->
		<logic:equal name="page" value="modify">
		<div>
			<div id="title">
				<div class="titiel_img"></div>
				当前所在位置：就业奖励管理-->修改页面
			</div>
			<input type="hidden" name="dm" 
				value="<bean:write name="dm" scope="request"/>"/>
			<input type="hidden" name="yxh" 
				value="<bean:write name="map" property="xh" scope="request"/>"/>
			<input type="hidden" name="yszq" 
				value="<bean:write name="map" property="szsq" scope="request"/>"/>
			<table width="99%">
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>学号
					</td>
					<td><html:text property="xh" name="map"></html:text>  </td>
				</tr>
				<tr>
					<td align="center" width="45%">
						个人就业单位
					</td>
					<td>
						<html:text property="grjydw" name="map">
						</html:text>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>所在省
					</td>
					<td>
						<html:select property="szs" onchange="getShx()" style="width:150px">
							<html:option value="">-----${smc}----</html:option>
							<html:option value="">------------------</html:option>
							<html:options collection="slist" property="dm" 
									labelProperty="mc" />
						</html:select>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						<font color="red">*</font>所在区
					</td>
					<td>
						<html:select property="szq" style="width:150px">
							<html:option value="">-----${qmc}----</html:option>
						</html:select>
					</td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						证书领取人
					</td>
					<td><html:text property="zslqr" name="map"></html:text> </td>	
				</tr>
				<tr>
					<td align="center" width="45%">
						奖金领取人
					</td>
					<td><html:text property="jjlqr" name="map"></html:text> </td>	
				</tr>
			</table>
			<div id="button">
				<logic:notPresent name="ck">
				<button class="button2" onclick="checkModify();">
					修 改
				</button>
				</logic:notPresent>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.close();return false;">
					关 闭
				</button>
			</div>
		</div>
			</logic:equal>
			<logic:equal name="result" value="view">
				<script>
			    alert("<bean:write name='jyglForm' property='message'/>");
			    window.dialogArguments.document.forms[0].action='/yjsjwgl/jyjlgl.do?act=find';
			   	window.dialogArguments.document.forms[0].submit();
			   	window.close();
			  	</script>
			</logic:equal>
	</html:form>
</body>

</html:html>

