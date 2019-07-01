<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
       function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
      }
    </script>
	<script language="javascript" src="js/function.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
<%--	<script language="javascript" src="js/AjaxFunction.js"></script>--%>
	<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	</head>	

	<body onload="xyDisabled('xy');">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 宿舍分配 - 床位分配</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zgdzdx_Gygl.do?method=bedCompartition&doXh=true" method="post">
			<html:hidden name="zgdzdxForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="<bean:write name="oldCondiSqlValue" scope="request"/>"/>
			<input type="hidden" name="mappingItems" value="" />       				
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType"/>" />	
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName"/>" />
			
			
            <div id="items" name="items" style="display:none; position: absolute;color: blue; " >
					<span>&nbsp;&nbsp;&nbsp;&nbsp;请输入学号，并按回车键；按学号查询已分配情况</span>
			</div>
			<logic:notEmpty name="errINFO">
			<script language="javascript">
			     alert('<bean:write name="errINFO"/>');
			</script>
			</logic:notEmpty>
            <div id="showDiv" style="display:none" align="center">
					<table class="formlist" border="0" align="center" style="width: 100%">
						<thead>
							<tr>
								<th colspan="2">
									<span>入住时间设置</span>
								</th>
							</tr>
						</thead>
						<tbody>		
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>入住时间：
								</td>
								<td align='left'>
								<html:text property="rzrq" styleId="rzrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rzrq','y-mm-dd','aa');" readonly="true"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align="center">
									<button id="kfbtnSave" onclick='zgdz_addCwColum()'>
										提交
									</button>
									&nbsp;&nbsp;
									<button id="kfbtnClose" onclick='hiddenMessage(true,true)'>
										关闭
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>										
			<!-- 卫生检查情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>床位分配</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr align="center">
						<th width="28%" align="left" rowspan="2">
							&nbsp;&nbsp; 校区名：							
								<html:select property="xiaoqu" styleId="xq" style=""
									onfocus="bedbeforSubmit() " onchange="dataCL();SssCwXxList();">									
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
							<br>	
							  性别限定：
							<html:select property="xbxd" styleId="xbxd" style="" onfocus="bedbeforSubmit() "
								onchange="if($('xq').value==''){}else{SssLdList();SssXsList();SssCwXxList();SssFpCwList();}">
								<html:option value="">--请选择--</html:option>
							    	<html:option value="男">男</html:option>
							    	<html:option value="女">女</html:option>
							    	<html:option value="混合">混合</html:option>
							</html:select>
							<br>														
           					&nbsp;<font color="red">*</font>楼栋名：
							<html:select property="lddm" style="" styleId="ld" onfocus="bedbeforSubmit() "
								onchange="SssLcList();SssCwXxList();">								
								<html:options collection="ldList" property="dm"
									labelProperty="mc" />
							</html:select>													
						       <br> 
						       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;层号：
						       <html:select property="cs" styleId="cs" style="" onfocus="bedbeforSubmit() "
									onchange="SssCwXxList();">
								
									<html:options collection="csList" property="dm"
										labelProperty="mc" />
								</html:select>
						</th>
						<td width="67%" align="left" colspan="3">
						    <font color="red">*</font>年级：
							<html:select property="nj" style="width:60px" onfocus="bedbeforSubmit() " styleId="nj"
								onchange="SssCwYfpZsData();SssBjLb();SssXsList();SssFpCwList();SssXiaoqLb();">
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:160px" styleId="xy" onfocus="bedbeforSubmit() "
								onchange=" SssCwYfpZsData();SssXiaoqLb();SssBjLb();SssXsList();SssFpCwList();">
								<html:option value="">--请选择--</html:option>
								<html:options collection="xyList" property="dm"
									labelProperty="mc" />
							</html:select>						
							&nbsp;&nbsp;班级:
							<html:select property="bjdm" style="width:150px" styleId="bj" onfocus="bedbeforSubmit() "
								onchange=" SssCwYfpZsData();SssXsList();SssFpCwList();">                                 
                                    <html:options collection="bjList" property="dm" labelProperty="mc"/>
							</html:select>								
							
<%--							&nbsp;&nbsp;学号：<html:text property="ksh" styleId="ksh" onfocus="bedbeforSubmit() ;showItems();"  onkeypress="" onblur="hiddenItems();"></html:text>															     		--%>
			     		</td>
					</tr>
					<tr>
						<td colspan="3">
							未分配总数(人):
							<span id="allbody"><bean:write name="nototal"scope="request" />
							</span> &nbsp;&nbsp;&nbsp;&nbsp;(男):
							<span id="allboy"><bean:write name="noboy" scope="request" />
							</span> &nbsp;&nbsp;&nbsp;&nbsp;(女):
							<span id="allgirl"><bean:write name="nogirl" scope="request" />
							</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已分配总人数:
							<span id="totalBed"><bean:write name="total"scope="request" /></span>&nbsp;&nbsp;&nbsp;&nbsp;(男):
							<span id="boyBed"><bean:write name="boy"scope="request" /></span>&nbsp;&nbsp;&nbsp;&nbsp;(女):
							<span id="girlBed"><bean:write name="girl"scope="request" /></span>
						</td>						
					</tr>
					<tr align="center" bgcolor="#D0E0EE">
						<td align="center" >
							未分配床位
						</td>
						<td align="center" width="19%">
							未分配学生
						</td>
						<td align="center" width="6%">							
						</td>
						<td align="center" width="57%">
							已分配情况
						</td>
					</tr>
					</tbody>
					</table>
					<table class="permissionlist" border="0" align="center" style="width: 100%">
					<tr align="center">
							<td colspan="3">
								<table width="100%" align="center" class="">
					<tr align="center">
						<td rowspan="2" valign="top">
						<font color="red" style="font-size:10px;">提示：按住Ctrl键或Shift键<br>(或按下鼠标左键上下移动)进行多选</font>							
							<br>							
							寝室编号/床位数/床位号
							<html:select property="oracleItem" style="width:100%;" size="27" multiple="multiple"
								styleId="oracleList" onmouseover="null">
								<html:options collection="ssxxList" labelProperty="mc"
									property="dm" />
							</html:select>
						</td>
						<td valign="top">
							<font color="red" style="font-size: 10px;">提示：按住Ctrl键或Shift键<br>(或按下鼠标左键上下移动)进行多选</font>
							<br>
							学号/姓名/性别
							<html:select property="xh" style="width:100%;" size="27"
								styleId="xh" multiple="multiple" onmouseover="null">
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
							<button class="button2" onclick="zgdz_addCwBatchColum()"
								style="width:50px;height: 20px" title="床位分配">
								&rarr;
							</button>
							<br>
							<br>
							<br>
							<br>
							<font color="blue">释放</font>
							<br>
							<button class="button2" onclick="zgdz_delCwBatchColum()"
								style="width:50px;height: 20px" title="床位释放">
								&larr;
							</button>
						</td>
						<td valign="top">
						<font color="red" style="font-size: 10px;">提示：按住Ctrl键或Shift键<br>(或按下鼠标左键上下移动)进行多选</font>								
							<br>
							学号/姓名/性别/寝室编号/床位数/分配床位号
							<html:select property="sql" size="27" style="width:100%" ondblclick="" styleId="sql"  multiple="multiple" onmouseover="null">
							<html:options collection="yfpCwList" property="dm"
									labelProperty="mc" />
							</html:select>					
						</td>
						
					</tr>
					</table>
					</td>
					</tr>
					</table>
						<table class="formlist" border="0" align="center" style="width: 100%">
						<tfoot>
					<tr>
						<td align="center" colspan="3">
							<button name="button1"
								style="width:100px"
								onclick="if(confirm('是否要提交当前已分配情况数据？\n点击\'确定\'，保存数据；\n点击\'取消\'，将放弃提交！')){SsscwfpDataSave();}">
								确 定
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button name="button2"
								style="width:100px"
								onclick="refreshForm('/xgxt/zgdzdx_Gygl.do?method=bedCompartition')" >
								重 置
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%--							<input type="button" class="button2" name="button2"--%>
<%--								style="width:100px" value="默 认 分 配"--%>
<%--								onclick="defaultDisBedList()" />--%>
						</td>
					</tr>
					</tfoot>
				</table>
			<div id="tmpdivone" ></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="true">
				<script>
<%--				document.forms[0].action = "/xgxt/zgdzdx_Gygl.do?method=bedCompartition&doXh=true";--%>
<%--				document.forms[0].submit();--%>
				alert("操作成功!");				
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="false">
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
	items.style.top = ((screen.availheight)/10);
	items.style.display = "block";
	}
}
function hiddenItems(){
    var items = document.getElementById("items");
    items.style.display = "none";
}
</script>
</html>
