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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="gygl/bjlh/cwfp/cwfp.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
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
    function dispXylist() {
    	var fplx = document.getElementById('fplx').value;
    	var userType = document.getElementById('userType').value;
    	if (fplx=='全日制') {
    		xyDisabled('xy');
    	} else {
    		if (userType != 'admin' && userType != 'xx') {
    			document.getElementById('fpbj').disabled = true;
    		}
    	}
    }
    function chLx(value){
		if(value=="0110"){
			$("xslx").value="研究生";
		}else if(value=="0117"){
			$("xslx").value="成教生";
		} else {
			$("xslx").value="";
		}
		setNjList($('xslx').value);
		setXyList($('xslx').value);
		setZyList($('xslx').value);
		setBjList($('xslx').value);
	}
	function queryXlCw() {
		return showTopWin("bjlh_gygl_xlcw.do",750,550);
	}
    </script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="dispXylist()">
		<html:form action="/bjlh_cwfp" method="post">
			<html:hidden name="commanForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="commanForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<html:hidden name="commanForm" property="conditionXlValue"
				styleId="conditionXlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" 			 
			       value="${oldMappingItems }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：北京联合公寓管理 - 床位分配
				</div>
			</div>
			<input type="hidden" name="mappingItems" value="" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="fplx" id="fplx"
				value="${fplx }" />
			<input type="hidden" id="xslx" name="xslx" value="" />
			
			<div id="showDiv" style="display:none" align="center">
             <iframe  src='javascript:false' border=0px  style='position: absolute;visibility: inherit;top: 0px;left: 0px;width: 350px;height: 200px;z-index: -999;filter: Alpha(Opacity=0)'></iframe>          
					<fieldset style="width:90%;height:90%">
						<legend>
							操作提示信息
						</legend>
						<table class='buttontool' hight='100px' >
							<thead>
								<tr>
									<td colspan='2'>
										入住时间设置
									</td>
								</tr>
							</thead>
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>入住时间：
								</td>
								<td align='left'>
								<html:text name="commanForm" property="rzrq" styleId="rzrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rzrq','y-mm-dd','aa');" readonly="true"/>
								</td>
							</tr>
							<tfoot>
								<tr>
									<td colspan='2'>
										<button class='button2' id="kfbtnSave" onclick='addBjlhCwColum()'>
											提交
										</button>
										&nbsp;&nbsp;
										<button id="kfbtnClose" onclick='hiddenMessage(true,true)' class='button2'>
											关闭
										</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</fieldset>
				</div>
			
			<div class="rightcontent" >
				<fieldset>
					<legend>
						床位分配
					</legend>
					<table width="98%" align="center" class="tbstyle"  bgcolor="#D0E0EE">
						<tr align="center">
							<td width="30%" align="left" rowspan="2">
								&nbsp;&nbsp;&nbsp;&nbsp;校区名：
								<html:select property="xqdm"  styleId="xq" 
									onchange="bjlhssFp_Xq()">
									<html:option value="">--请选择--</html:option>
									<html:options collection="xiaoqquList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								&nbsp;性别限定：
								<html:select property="xb" styleId="xb"  onfocus="bjlhbeforSSFPSubmit()"
									onchange="if($('xq').value==''){}else{bjlhinitSsFpLdList();bjlhinitCwFpCwXxList();bjlhinitCwFpWfpXsXxList();}">
									<html:option value="">--请选择--</html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
									<html:option value="混合">混合</html:option>
								</html:select>
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;楼栋名：
								<html:select property="lddm" styleId="ld"  onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();bjlhinitCwFpCwXxList();bjlhinitSsFpFpCsList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>							
								<br>
						          &nbsp;&nbsp;&nbsp;&nbsp;层&nbsp;&nbsp;&nbsp;号：
						           	<html:select property="cs" styleId="cs"
										onfocus="bjlhbeforSSFPSubmit()"
										onchange="bblhssfpTj();bjlhinitCwFpCwXxList();bjlhinitCwFpFpSxXxList();">										
										<html:options collection="lcList" property="dm"
										labelProperty="mc" />
									</html:select>
								<br>    							
							</td>
							<td width="70%" align="left">							
								分配标记：
								<html:select property="fpbj" styleId="fpbj" onfocus="bjlhbeforSSFPSubmit()" onchange="bblhssfpTj();chLx(this.value);bjlhinitCwFpCwXxList();bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="fpbjList" property="en" labelProperty="cn"/>
								</html:select>
								&nbsp;&nbsp;
								&nbsp;年级：
								<html:select property="nj" styleId="nj" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();setZyList($('xslx').value);setBjList($('xslx').value);bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="njList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" styleId="xy" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();setZyList($('xslx').value);setBjList($('xslx').value);bjlhinitCwFpFpSxXxList();bjlhinitCwFpWfpXsXxList();">
									<html:options collection="xyList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专业：
								<html:select property="zydm" styleId="zy" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();setBjList($('xslx').value);bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="zyList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;班级：
								<html:select property="bjdm" styleId="bj" onfocus="bjlhbeforSSFPSubmit()"
									onchange="bblhssfpTj();bjlhinitCwFpWfpXsXxList();bjlhinitCwFpFpSxXxList();">
									<html:options collection="bjList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								未分配学生&nbsp;&nbsp;总数(人):
								<span id="allbody" style="width: 70px">${total }</span> (男):
								<span id="allboy" style="width: 70px">${boy }</span> (女):
								<span id="allgirl" style="width: 100px">${girl }
								</span> 
								<br>
								未划分床位&nbsp;&nbsp;总数(人):
								<span id="totalBedF" style="width: 70px">${totalBedF }</span> (男):
								<span id="boyBedF" style="width: 70px">${boyBedF }</span> (女):
								<span id="girlBedF" style="width: 70px">${girlBedF }</span>混合：
								<span id="bgBedF" style="width: 70px">${bgBedF }</span>
								<br>
								已划分床位&nbsp;&nbsp;总数(人):
								<span id="totalBed" style="width: 70px">${totalBed }</span> (男):
								<span id="boyBed" style="width: 70px">${boyBed }</span> (女):
								<span id="girlBed" style="width: 70px">${girlBed }</span>行李：
								<span id="xlBed" style="width: 70px">${xlBed }</span>
							</td>
						</tr>

						<tr align="center">
							<td rowspan="2" valign="top" colspan="2">
								<table width="97%" align="center" class="tbstyle">
									<tr align="center">
										<td align="center" width="25%">
											未分配床位
										</td>
										<td align="center" width="25%">
											学生信息
										</td>
										<td width="10%">
										</td>
										<td align="center" width="40%">
											已分配情况
										</td>
									</tr>
									<tr align="center">
										<td rowspan="2" valign="top">
										<font color="red" style="font-size:10px;">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>
											<br>
											寝室编号/床位数/床位号
											<html:select property="oracleItem" style="width:100%;"
												size="27" styleId="oracleList" title="" ondblclick=""
												multiple="multiple">
												<html:options collection="cwxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
										<td rowspan="2" valign="top">
										<font color="red" style="font-size:10px;">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>
											<br>
											学号/姓名/性别
											<html:select property="oracleItem" style="width:100%;"
												size="27" styleId="oracleXsList" title="" ondblclick=""
												multiple="multiple">
												<html:options collection="xsxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
										<td valign="top">
											<br>
											<br>
											<br>											
											<font color="blue">行李床位</font>
											<br>
											<button class="button2" onclick="addBjlhXlCw()"
												style="width:50px;height: 20px" title="分配为行李床位">
												划分
											</button>
											<br>
											<br>
											<button class="button2" onclick="queryXlCw()"
												style="width:50px;height: 20px" title="查看行李床位信息">
												查看
											</button>
											<br>
											<br>
											<br>
											<br>
											<br>											
											<font color="blue">分配</font>
											<br>
											<button class="button2" onclick="addBjlhCwBatchColum()"
												style="width:50px;height: 20px" title="床位分配">
												&rarr;
											</button>
											<br>
											<br>
											<br>
											<br>
										    <font color="blue">释放</font>
										    <br>
											<button class="button2" onclick="delBjlhCwBatchColum()"
												style="width:50px;height: 20px" title="床位释放">
												&larr;
											</button>
										</td>
										<td valign="top">
										<font color="red" style="font-size:10px;">提示：按住Ctrl键(或按下鼠标左键上下移动)<br>可进行多选</font>
										<br>
											学号/姓名/性别/寝室编号/床位数/分配床位号/入住时间
											<html:select property="sql" style="width:100%;" size="26"
												styleId="sql" ondblclick=""multiple="multiple">
												<html:options collection="yfpxsxxList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="2">
											<input class="button2" type="button" name="button1"
												style="width:100px" value="确 定"
												onclick="if(confirm('是否要提交当前已分配情况数据？\n点击\'确定\'，保存数据；\n点击\'取消\'，将放弃提交！')){bjlhCwDistSave()}" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" class="button2" name="button2"
												style="width:100px" value="重 置"
												onclick="refreshForm('bjlh_gygl_cwfp.do')" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</fieldset>				
			</div>
			<div id="tmpdiv"></div>
			<div id="tmpdivone"></div>
			<div id="tmpdivtow"></div>
			<div id="tmpdivthree"></div>			
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
					alert("操作成功!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>
					alert("操作失败!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
