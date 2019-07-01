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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
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
      function setTBGbed(){
          totalBed.innerText="0";
          boyBed.innerText="0";
          girlBed.innerText="0";
      }
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript">
    function fzLd(ele){ 
       var userType = document.getElementById("userType").value;
	   var xxdm = document.getElementById("xxdm").value;
	   if(xxdm=="13429"){//南昌大学科学技术<bean:message key="lable.xsgzyxpzxy" />      
	      if (userType!="admin") {
		     var tmp = ele.split("-");
		     for (i = 0; i < tmp.length; i++) {
			   document.getElementById(tmp[i]).disabled = true;
		   }
		  }
	  }   
    }
    </script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="fzLd('xq-xb-ld')">
		<html:form action="/dorm_distribute" method="post">
			<html:hidden name="commanForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="commanForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="<bean:write name="oldMappingItems" scope="request"/>"/>	
			xbmc		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 宿舍分配 - 宿舍划分
				</div>
			</div>
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="bjV" id="bjV">	
			<div class="rightcontent">
				<fieldset>
					<legend>
						宿舍划分
					</legend>
					<table width="98%" align="center" class="tbstyle"  bgcolor="#D0E0EE">
						<tr align="center">
							<td width="30%" align="left" rowspan="2">
								&nbsp;&nbsp;&nbsp;&nbsp;校区名：
								<html:select property="xqdm"  styleId="xq" onfocus="beforSSFPSubmit()"
									onchange="ssFp_Xq()">
									<html:option value="">--请选择--</html:option>
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								&nbsp;性别限定：
								<html:select property="xb" styleId="xb"  onfocus="beforSSFPSubmit()"
									onchange="if($('xq').value==''){}else{initSsFpLdList();initSsFpSsXxList();}">
									<html:option value="">--请选择--</html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
									<html:option value="混合">混合</html:option>
								</html:select>
								<br>
								&nbsp;&nbsp;<font color="red">*</font>楼栋名：
								<html:select property="lddm" styleId="ld"  onfocus="beforSSFPSubmit()"
									onchange="initSsFpSsXxList();initSsFpFpCsList();initSsFpFpSxXxList();">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>							
								<br>
						          &nbsp;&nbsp;&nbsp;&nbsp;层&nbsp;&nbsp;&nbsp;号：
						           	<html:select property="cs" styleId="cs"
										onchange="initSsFpSsXxList();initSsFpFpSxXxList();">
										<html:options collection="lcList" property="dm"
										labelProperty="mc" />
									</html:select> 
								<br>    							
							</td>
							<td width="70%" align="left">								
								   年级：
									<html:select property="nj" styleId="nj"  onfocus="beforSSFPSubmit()"
										onchange="ssfpTj();initBjList();initSsFpFpSxXxList();">
										<html:option value="">--请选择--</html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>		
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm"  styleId="xy" onfocus="beforSSFPSubmit()"
										onchange="ssfpTj();initBjList();initSsFpFpSxXxList();">
										<html:option value="">--请选择--</html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />																						
									</html:select>
									&nbsp;<font color="red">*</font>班级：
									<html:select property="bjdm"  styleId="bj" onfocus="beforSSFPSubmit()"
										onchange="ssfpTj();initSsFpFpSxXxList();">										
										<html:options collection="bjList" property="dm"
											labelProperty="mc" />
									</html:select>							
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未分配学生总数(人):
								<span id="allbody" style="width: 70px"><bean:write name="total"scope="request" /></span> (男):
								<span id="allboy" style="width: 70px"><bean:write name="boy" scope="request" /></span>(女):
								<span id="allgirl" style="width: 100px"><bean:write name="girl"scope="request" />
								</span> 
								<br>
								 已划分总(床)位数(人):
								<span id="totalBed" style="width: 70px"><bean:write name="totalBed"scope="request" /></span>(男):
								<span id="boyBed" style="width: 70px"><bean:write name="boyBed"scope="request" /></span> (女):
								<span id="girlBed" style="width: 70px"><bean:write name="girlBed"scope="request" /></span>混合：
								<span id="bgBed" style="width: 70px"><bean:write name="bgBed"scope="request" /></span>
							</td>
						</tr>

						<tr align="center">
							<td rowspan="2" valign="top" colspan="2">
								<table width="97%" align="center" class="tbstyle">
									<tr align="center" bgcolor="#D0E0EE">
										<td align="center">
											未划分宿舍
										</td>
										<td></td>

										<td align="center">
											已划分情况
										</td>
									</tr>

									<tr align="center">
										<td rowspan="2" valign="top">
										<font color="red">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>
										<br>											
							楼栋名称/层号/房间号/总床位数/剩余床位数/床位号
												<html:select property="oracleItem" style="width:100%;"
												size="27" styleId="oracleList" title=""
												ondblclick="" multiple="multiple">												
												<html:options collection="ssxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
										<td valign="top">
                                            <div title="操作方式">
											 操作方式：
											 <br>
											 <html:select property="fpfs" styleId="fpfs"onchange="initSsFpSsXxList()" onfocus="beforSSFPSubmit()" >
										     <html:option value="ass">按宿舍</html:option>
										    <html:option value="acw">按床位</html:option>
									        </html:select>
											</div>	
											<br>
											<br>
											<br>
											<br>
											<br>											
											<font color="blue">划分</font>
											<br>
											<button class="button2" onclick="addBatchColum()"
												style="width:50px;height: 20px" title="宿舍划分">
												&rarr;

											</button>
											<br>
											<br>
											<br>
											<br>
										    <font color="blue">释放</font>
										    <br>
											<button class="button2" onclick="delBatchColum()"
												style="width:50px;height: 20px" title="宿舍释放">
												&larr;
											</button>
										</td>
										<td valign="top">
										<font color="red">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>
										<br>
							                  班级/楼栋名称/层号/房间号/总床位数/剩余床位数/划分床位号											
											<html:select property="sql" style="width:100%;" size="26"
												styleId="sql" ondblclick=""multiple="multiple">
												<html:options collection="ssfpList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="2">
											<input class="button2" type="button" name="button1"
												style="width:100px" value="确 定" 
												<logic:equal value="no" name="writeAble">
												disabled="true"
												</logic:equal>
												onclick="if(confirm('是否要提交当前已划分情况数据？\n点击\'确定\'，保存数据；\n点击\'取消\'，将放弃提交！')){dormDistSave()}" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" class="button2" name="button2"
												style="width:100px" value="重 置"
												<logic:equal value="no" name="writeAble">
												disabled="true"
												</logic:equal>
												onclick="refreshForm('/xgxt/dorm_distribute.do')" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</fieldset>
			</div>
			<div id="tmpdivone"></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>						
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
<%--				    document.forms[0].action = "/xgxt/dorm_distribute.do";--%>
<%--					document.forms[0].submit();--%>
					alert("操作成功!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
<%--				    document.forms[0].action = "/xgxt/dorm_distribute.do";--%>
<%--					document.forms[0].submit();								--%>
					alert("操作失败!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
