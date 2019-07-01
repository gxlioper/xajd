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
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
       function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
      }
      function cwfp_xq(){
      	$("xb").value=""; //重置性别值为空	
		     var ld = document.forms[0].ld;
		     var cs = document.forms[0].cs;
		     for (i=0;i<ld.length;i++) {	//清空楼栋分列表  
		        ld.options[i]=null;
		     }
		     for (i=0;i<cs.length;i++) {	//清空层号列表  
		        cs.options[i]=null;
		     }
      }
      function cwfp_xb(){
      		var xn = "";
			var xq = "";	
			var nj = "";
			var xydm = "";
			if($("xn")){xn = $("xn").value};
		    if($("xy")){xydm = $("xy").value};
			if($("xxxq")){xq = $("xxxq").value};
			if($("nj")){nj = $("nj").value};
      		var	xqdm = $("xq").value;
			var xb = $("xb").value;
			getOtherData.getLdList(xqdm,xb,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = "ld";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
					}
				}
			});
			var objId = "xh";
		    var show = objId+"show";
		    showDivWait(show,'30%','50%');
			getOtherData.getWfpxsxxList(xn,xq,nj,xydm,xb,function initTjList(data){
					if (data != null && typeof data == 'object') {				
						var objId = "xh";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){					
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");												
						}
					}
		          if($(show)){
						$(show).style.display= "none";
					}			
			});
			var objId1 = "sql";
		    var show1 = objId1+"show";
		    showDivWait(show1,'70%','50%');	
			getOtherData.getYfpqkxxList(xn,xq,nj,xydm,xb,function initTjList(data){
					if (data != null && typeof data == 'object') {				
						if($(objId1) && $(objId1).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId1);			
							DWRUtil.addOptions(objId1,data,"dm","mc");	
							oldCondiSqlVConn();						
						}
					}
					if($(show1)){	
					  $(show1).style.display= "none";
					}
				});
      }
      function cwfp_ld(){
      		var lddm = "";	
			if($("ld")){lddm = $("ld").value};
				getOtherData.getLcList(lddm,function initTjList(data){
					if (data != null && typeof data == 'object') {
						var objId = "cs";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					}		
				});
			var objId = "oracleList";
		    var show = objId+"show";
		    showDivWait(show,'6%','50%');	
			var xqdm = "";
			var xb = "";
			var cs = "";
			if($("xq")){xqdm = $("xq").value};
			if($("xb")){xb = $("xb").value};
			if($("cs")){cs = $("cs").value};
				getOtherData.getWfpcwxxList(xqdm,lddm,xb,cs,function initTjList(data){
					if (data != null && typeof data == 'object') {				
						//var objId = "oracleList";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");							
						}
					}
					if($(show)){	
					  $(show).style.display= "none";
					}
				});
      }
      function cwfp_cs(){
      	var objId = "oracleList";
		    var show = objId+"show";
		    showDivWait(show,'6%','50%');	
			var xqdm = "";
			var xb = "";
			var cs = "";
			var lddm = "";	
			if($("ld")){lddm = $("ld").value};
			if($("xq")){xqdm = $("xq").value};
			if($("xb")){xb = $("xb").value};
			if($("cs")){cs = $("cs").value};
				getOtherData.getWfpcwxxList(xqdm,lddm,xb,cs,function initTjList(data){
					if (data != null && typeof data == 'object') {				
						//var objId = "oracleList";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");							
						}
					}
					if($(show)){	
					  $(show).style.display= "none";
					}
				});
      }
      function cwfp_nj(){
      		var xn = "";
			var xq = "";	
			var nj = "";
			var xydm = "";
			var xb = "";
			if($("xb")){xb = $("xb").value};
			if($("xn")){xn = $("xn").value};
		    if($("xy")){xydm = $("xy").value};
			if($("xxxq")){xq = $("xxxq").value};
			if($("nj")){nj = $("nj").value};
		    getOtherData.getWfpcwzsData(xn,xq,nj,xydm,function(data){//未分配
		       if(data!=null){
		        allbody.innerText=data[0];
		       	allboy.innerText=data[1];
		       	allgirl.innerText=data[2];
		       }
		    });	
		    getOtherData.getYfpcwzsData(xn,xq,nj,xydm,function(data){//已分配
		       if(data!=null){
		        totalBed.innerText=data[0];
		       	boyBed.innerText=data[1];
		       	girlBed.innerText=data[2];
		       }
    });
    	var objId = "xh";
	    var show = objId+"show";
	    showDivWait(show,'30%','50%');
		getOtherData.getWfpxsxxList(xn,xq,nj,xydm,xb,function initTjList(data){
				if (data != null && typeof data == 'object') {				
					var objId = "xh";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){					
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");												
					}
				}
	          if($(show)){
					$(show).style.display= "none";
				}			
		});
		var objId1 = "sql";
	    var show1 = objId1+"show";
	    showDivWait(show1,'70%','50%');	
		getOtherData.getYfpqkxxList(xn,xq,nj,xydm,xb,function initTjList(data){
				if (data != null && typeof data == 'object') {				
					if($(objId1) && $(objId1).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId1);			
						DWRUtil.addOptions(objId1,data,"dm","mc");
						oldCondiSqlVConn();							
					}
				}
				if($(show1)){	
				  $(show1).style.display= "none";
				}
			});
      }
      function sscwfpSave(){
      	 if(confirm('是否要提交当前已分配情况数据？\n点击\'确定\'， 保存数据；\n点击\'取消\'，将放弃提交！')){
      	 	 hiddenField();
			  showTips();
			  saveConditionSql();
			  refreshForm('/xgxt/XsgyglDispatch.do?method=jqLxCwFp&doType=save');
      	 }
      }
     function sfSave() {
     	  if(compartStatus){
				if (confirm("当前已分配情况列表发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
			          hiddenField();
					  showTips();
					  saveConditionSql();
					  refreshForm('/xgxt/XsgyglDispatch.do?method=jqLxCwFp&doType=save');
			    } else {
					refreshForm("/xgxt/XsgyglDispatch.do?method=jqLxCwFp");
				}
			}
	}
    </script>
	<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<base target="_self" />
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body >
		<html:form action="/XsgyglDispatch.do?method=jqLxCwFp" method="post">
			<html:hidden name="xsgyglForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="xsgyglForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="<bean:write name="oldMappingItems" scope="request"/>"/>
				<input type="hidden" name="xxdm" id="xxdm" 			 
			       value="<bean:write name="xxdm" scope="request"/>"/>
			<input type="hidden" name="mappingItems" value="" />       	
			<input type="hidden" name="bjV" id="bjV" />	
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType"/>" />
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 宿舍分配 - 假期留校床位分配
				</div>
			</div>
            <div id="items" name="items" style="display:none; position: absolute;color: blue; " >
					<span>&nbsp;&nbsp;&nbsp;&nbsp;请输入学号，并按回车键;按学号查询已分配情况</span>
			</div>
			<logic:notEmpty name="errINFO">
			<script language="javascript">
			     alert('<bean:write name="errINFO"/>');
			</script>
			</logic:notEmpty>
			<fieldset>
				<legend>
					床位分配
				</legend>
<%--				<logic:present name="showhzy">--%>
<%--					<table width="98%" align="center" class="tbstyle">--%>
<%--						<td>--%>
<%--							<html:checkbox property="cwfp" styleId="cwfp" --%>
<%--								onclick="initCwFpXqListt();initCwFpSsCwXxList()">多余床位分配不同<bean:message key="lable.xsgzyxpzxy" /></html:checkbox>--%>
<%--						</td>--%>
<%--					</table>--%>
<%--				</logic:present>--%>
				<table width="98%" align="center" class="tbstyle"  bgcolor="#D0E0EE" >
					<tr align="center">
						<td width="27%" align="left" rowspan="2">
							&nbsp;&nbsp; 校区名：							
								<html:select property="xqdm" styleId="xq" style="width:130px" onfocus="sfSave()" onchange="cwfp_xq()">									
									<html:options collection="xiaoquList" property="dm"
										labelProperty="xqmc" />
								</html:select>
							<br>	
							  性别限定：
							<html:select property="xb" styleId="xb" style="width:130px" onfocus="sfSave()"
								onchange="cwfp_xb()">
								<html:option value="">--请选择--</html:option>
							    	<html:option value="男">男</html:option>
							    	<html:option value="女">女</html:option>
							    	<html:option value="混合">混合</html:option>
							</html:select>
							<br>														
           					&nbsp;<font color="red">*</font>楼栋名：
							<html:select property="lddm" style="width:130px" styleId="ld" onfocus="sfSave()"
								onchange="cwfp_ld()">
								<html:options collection="ldList" property="dm"
									labelProperty="mc" />
							</html:select>													
<%--							<logic:present name="showhzy">--%>
						       <br> 
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;层号：
						       <html:select property="cs" styleId="cs" style="width:130px" onfocus="sfSave()"
									onchange="cwfp_cs()">
									<html:options collection="csList" property="dm"
										labelProperty="mc" />
								</html:select>
<%--							</logic:present>--%>
						</td>
						<td width="70%" align="left" colspan="3">
							 学年：
							 <input type="text" name="xn" id="xn" value="<bean:write name="xn" scope="request"/>" 
							        size="9" readonly="true"/>
							 学期：
							<input type="text" name="xxxq" id="xxxq" value="<bean:write name="xq" scope="request"/>" 
							        size="3" readonly="true"/>
						    <font color="red">*</font>年级：
							<html:select property="nj" style="width:80px" styleId="nj" onfocus="sfSave()"
										onchange="cwfp_nj()">
								<html:option value="">-请选择-</html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:170px" styleId="xy" onfocus="sfSave()"
								 onchange="cwfp_nj()">
								<html:option value="">--------请选择--------</html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>							
<%--							&nbsp;&nbsp;学号：<html:text property="ksh" styleId="ksh" onfocus="beforSubmit();showItems();"  onblur="hiddenItems();"></html:text>												--%>
			     		</td>
					</tr>
					<tr>
						<td colspan="3">
							未分配总数(人):
							<span id="allbody" style="width: 100px"><bean:write name="total" scope="request" /></span>
							(男):
							<span id="allboy" style="width: 100px"><bean:write name="boy" scope="request" /></span>
							(女):
							<span id="allgirl" style="width: 100px"><bean:write name="girl" scope="request" /></span>
							<br>
							已分配总数(人):	
							<span id="totalBed" style="width: 100px"><bean:write name="totalBed"  scope="request"/></span>
							(男):
							<span id="boyBed" style="width: 100px"><bean:write name="boyBed" scope="request" /></span>
							(女):
							<span id="girlBed" style="width: 100px"><bean:write name="girlBed" scope="request" /></span>						
<%--						<div align="right">--%>
<%--								<a   href="/xgxt/csmz_gygl.do?method=FpDeTailView" target="_blank" style="color: blue;font-weight: bold"   title="分配情况统计">分配情况统计</a>	--%>
<%--								</div>--%>
						</td>						
					</tr>
					<tr align="center" bgcolor="#D0E0EE">
						<td align="center" width="20%">
							未分配床位
						</td>
						<td align="center" width="27%">
							未分配学生
						</td>
						<td align="center" width="6%">
							
						</td>
						<td align="center" width="45%">
							已分配情况
						</td>
					</tr>
					<tr align="center">
						<td rowspan="2" valign="top">
						<font color="red" style="font-size:10px;">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>							
							<br>							
							寝室编号/床位数/床位号
							<html:select property="oracleItem" style="width:100%;" size="27" multiple="multiple"
								styleId="oracleList">
								<html:options collection="ssxxList" labelProperty="mc"
									property="dm" />
							</html:select>
						</td>
						<td valign="top">
							<font color="red" style="font-size: 10px;">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>
							<br>
							学号/姓名/性别
							<html:select property="xh" style="width:100%;" size="27"
								styleId="xh" multiple="multiple">
								<logic:notEmpty name="xsList">
									<html:options collection="xsList" labelProperty="mc" 
										property="dm" />
								</logic:notEmpty>
							</html:select>
						</td>
						<td valign="top">
							<br>
							<br>
							<br>
							<br>
							<br>
							<font color="blue">分配</font>
							<br>
							<button class="button2" onclick="addCwBatchColum()"
								style="width:50px;height: 20px" title="床位分配">
								&rarr;
							</button>
							<br>
							<br>
							<br>
							<br>
							<font color="blue">释放</font>
							<br>
							<button class="button2" onclick="delCwBatchColum()"
								style="width:50px;height: 20px" title="床位释放">
								&larr;
							</button>
						</td>
						<td valign="top">
						<font color="red" style="font-size: 10px;">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>								
							<br>
							学号/姓名/性别/寝室编号/床位数/分配床位号
							<html:select property="sql" size="27" style="width:100%" ondblclick="" styleId="sql"  multiple="multiple">
							<html:options collection="yfpCwList" property="dm"
									labelProperty="mc" />
							</html:select>					
						</td>
						
					</tr>
					<tr>
						<td align="center" colspan="3">
							<input class="button2" type="button" name="button1"
								style="width:100px" value="确 定"
								onclick="sscwfpSave();" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="button2" name="button2"
								style="width:100px" value="重 置"
								onclick="refreshForm('/xgxt/XsgyglDispatch.do?method=jqLxCwFp')" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="button2" name="button2"
								style="width:100px" value="默 认 分 配"
								onclick="defaultDisBedList()" />
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="tmpdivone" ></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
				//document.forms[0].action = "/xgxt/XsgyglDispatch.do?method=jqLxCwFp";
				//document.forms[0].submit();
				alert("操作成功!");				
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
				//document.forms[0].action = "/xgxt/bed_distribute.do";
				//document.forms[0].submit();
				alert("操作失败!");				
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script language="javascript">	
function showItems(){
	var items = document.getElementById("items");
	if($("ksh").value==""){
	items.style.left = (screen.availwidth)/2.5;
	items.style.top = ((screen.availheight)/11);
	items.style.display = "block";
	}
}
function hiddenItems(){
    var items = document.getElementById("items");
    items.style.display = "none";
}
</script>
</html>
