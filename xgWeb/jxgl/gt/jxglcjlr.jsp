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
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function chkInputJx(obj){
		var num = obj.value;
		if(num != ""){
		if(num.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("必需为数字！");
				document.getElementById("isErr").value = "1";
				return false;
			}
		if(num>100){
				alert("军训成绩不能大于100！");
				document.getElementById("isErr").value = "1";
				return false;
			}
		}
		document.getElementById("isErr").value = "0";	
		return true;
	}
	function saveCj(){
		showTips('处理数据中，请等待......');
		if(document.getElementById("isErr").value == "1"){
			alert("请输入正确的成绩！");
		}else{
		cjSave('ArmyAchievementSave.do');
		}
	}
	function chBzList(nj){
		dwr.engine.setAsync(false);
		getJxglDAO.getLdList(nj,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");		
				$(objId).options[0].value = "";
				if(objId + "V"){
					if($(objId +"V").value != "" && $(objId + "V").value!= null){
						for(var i = 0;i < $(objId).options.length; i++){
							if($(objId).options[i].value == $(objId +"V").value){
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
		dwr.engine.setAsync(true);
	}
	
	function searchLd(){
		var lddm = $("lddm").value;
		var nj = $("nj").value;
		if(lddm != "0000"){
			if(nj == ""){
				alert("搜索连队信息必须选择年级");
				return false;
			}
		}
		return true;
	}
</script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/jxgl.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<body onload="bjLimit('bj')">
		<html:form action="/jxglgt" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>军训管理 - 军训考核 - 军训成绩录入</a>
				</p>
			</div>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="xyV"/>
			<input type="hidden" name="zyV"/>
			<input type="hidden" name="bjV"/>
			<input type="hidden" name="lddmV" id="lddmV" />
			<input type="hidden" name="userName" id="userName" value="<bean:write name="userName"/>"/>
			<input type="hidden" name="isFdy" id="isFdy" value="<bean:write name="isFdy"/>"/>
			<input type="hidden" id="isErr" name="isErr" value="0" />
			<div class="toolbox">
				 <!-- 按钮 -->
				 <div class="buttonbox">
				    <ul>
				    <logic:notEqual name="xxdm" value="11647">
					<li> <a href="#" onclick="refreshForm('jxglgt.do?method=SaveArmyAchievement');" class="btn_ccg"> 保存 </a> </li>
					</logic:notEqual>
					<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> 导入 </a> </li>
					<li> <a href="#" onclick="dataExport()" class="btn_dc"> 导出 </a> </li>
				    </ul>
				 </div>
			
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <input type="hidden" name="go" value="a" />
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="if(searchLd()){allNotEmpThenGo('/xgxt/jxglgt.do?method=ArmyIntoAchievement')}">
			              	查 询
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="czSearchCond('xn-nj-xydm-zydm-bjdm-lddm-xlcjbl-kscjbl-xh-xm-xb-sfzh-cj-qszz');return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

				<tbody>
						<tr>
							<th >
								学年：
								</th>
								<td>
									<html:select property="xn" style=""
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="xnList" labelProperty="xn" property="xn"/>
									</html:select>
								</td>
								<th>
									年级：
								</th>
								<td>
									<html:select property="nj" style=""
										onchange="initZyList();initBjList();">
										<html:options collection="njList" labelProperty="njmc" property="njdm"/>
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />：
								</th>
								<td>
								<logic:present name="fdy">
								<html:select property="xydm" style="width:170px" styleId="xy" onchange="initZyList();initBjList();">
									<html:option value="" />
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
								</logic:present>
								<logic:notPresent name="fdy">
								<html:select property="xydm" style="width:170px" styleId="xy" onchange="initZyList();initBjList();">
									<html:option value="" />
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
								</logic:notPresent>
								</td>
						</tr>
						<tr>
							<td colspan="6">
								专业：
								<logic:present name="fdy">
								<html:select property="zydm" style="width:170px" styleId="zy" onchange="initBjList();">
									<html:option value="" />
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
								</logic:present>
								<logic:notPresent name="fdy">
								<html:select property="zydm" style="width:170px" styleId="zy" onchange="initBjList();">
									<html:option value="" />
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
								</logic:notPresent>
								班级：
								<html:select property="bjdm" style="width:170px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
								<logic:notPresent name="cjlrType2">
								所属连队：
								<html:select property="lddm" style="width:180px" styleId="lddm">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
								</logic:notPresent>
								<logic:present name="cjlrType2">
								&nbsp;成绩比例:<font color="red">训练成绩
								<html:text property="xlcjbl" styleId="xlcjbl" style="width:35px" maxlength="3" value="60" onkeyup="chkInput(this,event)"/>%
								&nbsp;考试成绩
								<html:text property="kscjbl" styleId="kscjbl" style="width:35px" maxlength="3" value="40" onkeyup="chkInput(this,event)"/>%</font>
								</logic:present>
							</td>
						</tr>
						<logic:notPresent name="cjlrType2">
						<tr>
							<td colspan="6">
								学号：
								<html:text property="xh" style="width:120px" />
								&nbsp;&nbsp;&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:100px" />
								&nbsp;&nbsp;性别：
								<html:select property="xb" style="width:90px"
									styleId="xb">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
								<!-- begin 骆嘉伟 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								&nbsp;成绩比例:<font color="red">军训实践
								<html:text property="xlcjbl" styleId="xlcjbl" style="width:35px" maxlength="3" value="" onkeyup="chkInput(this,event)" disabled="true"/>%
								&nbsp;军训理论
								<html:text property="kscjbl" styleId="kscjbl" style="width:35px" maxlength="3" value="" onkeyup="chkInput(this,event)" disabled="true"/>%</font>
								</logic:present>
								<!-- end 骆嘉伟 2009/3/30 -->
								<logic:present name="showNnzy">
									&nbsp;&nbsp;身份证号：
									<html:text property="sfzh" style="width:100px" />
								</logic:present>
							</td>
						</tr>
						</logic:notPresent>
						<logic:present name="cjlrType2">
						<tr>
							<td colspan="3">
								&nbsp;查询成绩总值为:
								<input type = "test" name = "cj" id="cj" maxlength="3" style="width:35px"/>
								&nbsp;&nbsp;&nbsp;&nbsp;查询成绩总值在
								<input type = "test" maxlength="3" id="qszz" name = "qszz" style="width:35px" onkeyup="chkInput(this,event)"/>&nbsp;到&nbsp;<input type = "test" maxlength="3" name = "jszz" style="width:35px" onkeyup="chkInput(this,event)"/>之间
							</td>
						</tr>
						</logic:present>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			 <logic:notEmpty name="rs">
			  <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="center">学号</td>
								<td align="center">姓名</td>
								<!-- 南宁职业技术学院 -->
								<logic:present name="showNnzy">
									<td align="center">身份证号</td>
								</logic:present>
								<td align="center">军训学年</td>
								<td align="center">性别</td>
								<td align="center">年级</td>
								<logic:present name="showNnzy">
									<td align="center">专业</td>
								</logic:present>
								<td align="center">班级</td>
								<logic:present name="cjlrType2">
								<td align="center" style="width: 3%">训练成绩</td>
								<td align="center" style="width: 3%">考试成绩</td>
								</logic:present>
								<!-- begin 骆嘉伟 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								<td align="center" style="width: 3%">军训实践</td>
								<td align="center" style="width: 3%">军训理论</td>
								</logic:present>
								<!-- end 骆嘉伟 2009/3/30 -->
								<td align="center" style="width: 3%">成绩</td>
							</tr>
						</thead>
						<tbody >
						<logic:iterate name="rs" id="s">
						     <tr>
								<td align="left"><bean:write name="s" property="xhArray"/><html:hidden name="s" property="xhArray"/></td>
								<td align="left"><bean:write name="s" property="xmArray"/><html:hidden name="s" property="xmArray"/></td>
								<logic:present name="showNnzy">
									<td align="left"><bean:write name="s" property="sfzh"/><html:hidden name="s" property="sfzh"/></td>
								</logic:present>
								<td align="left"><bean:write name="s" property="ndArray"/><html:hidden name="s" property="ndArray"/></td>
								<td align="left"><bean:write name="s" property="xb"/></td>
								<td align="left"><bean:write name="s" property="nj"/></td>
								<logic:present name="showNnzy">
									<td align="left"><bean:write name="s" property="zymc"/></td>
								</logic:present>
								<td align="left"><bean:write name="s" property="bjmc"/></td>
								<logic:notEqual name="xxdm" value="11647">
								<td align="left"><html:text styleId="text" name="s" property="cjArray" style="width: 50px" size="8" maxlength="3" 
								onkeyup="value=value.replace(/[^\d]/g,'') "/></td>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11647">
								<td align="left"><bean:write name="s" property="cjArray"/></td>
								</logic:equal>
							 </tr>
						</logic:iterate>
						</tbody>
					</table>
					<p>&nbsp;</p>
			</logic:notEmpty>
			</div>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
