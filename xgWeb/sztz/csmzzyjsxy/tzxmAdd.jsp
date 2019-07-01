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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<base target="_self" />
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
<!--
function bmDisabled(ele) {
    var userType = document.forms[0].userType.value;
	if ( userType == "xy"||userType == "stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	}
}
function isNotSee(ele){
    var doT = document.forms[0].doType.value;
    if(doT == "view"){ 
    var tmp = ele.split("-");      
	  for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).style.display = "none";
		}		
    }
}

function addTr(the_tab){
var sum=document.getElementById(the_tab).rows.length;
 for(var i=1;i<=sum-1;i++)
 {
   if(document.getElementById(i).style.display=='none')
   {
      document.getElementById(i).style.display='';
	  break;
   }
 }
}

function decrease(the_tab){
var sum=document.getElementById(the_tab).rows.length;
var my_JxAarry = ["jxm","jxnr"];
for(var i=sum-1;i>0;i--)
{
   if(document.getElementById(i).style.display=='')
   {
      document.getElementById(i).style.display='none';
      document.getElementById(my_JxAarry[0]+i).value='';
      document.getElementById(my_JxAarry[1]+i).value='';
	  break;
   }
}
}

function defaultSee(the_tab){
var sum=document.getElementById(the_tab).rows.length;
var my_JxAarry = ["jxm"];

for(i=1;i<=sum-1;i++)
{  
   if($(my_JxAarry[0]+i).value!="")
   {
   document.getElementById(i).style.display='';
   }  
 }
 
}
function view(){
   if($("view").value=="toview"){
       $("viewdetail").style.display="";
   }else{
       $("viewdetail").style.display="none";
   }
}

function inputLimit(){
    var xxdm = $("xxdm").value;
    var xmmc = document.getElementById("xmmc");  
    if(xxdm=="10827"){
      if($("xmjb").value!="班级"){
        xmmc.maxLength = 13;
      }else{
        xmmc.maxLength = 100;
      }
    }else{
      xmmc.maxLength = 100;
    }
    
}
function sbrModi(){
    var userType = "";  
    var doType   = "";
    if($("userType")){
      userType = $("userType").value;
    } 
    if($("doType")){
      doType  = $("userType").value;
    }
    if(userType!="xx"&&userType!="admin"){
      if(doType!="add"){
          if($("xmsbr")){
             $("xmsbr").disabled = true;
          }
          if($("xmsbr1")){
             $("xmsbr1").disabled = true;
          }
      }
    }
}

</script>
	<body
		onload="defaultSee('tTb');showBj();bmDisabled('xy-bj-xmsbr');view();inputLimit();sbrModi()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/csmz_sztz.do?method=tzxm_sb" method="post">
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="xmsbrV" id="xmsbrV" />
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>">
			<input type="hidden" name="pkValue" id="pkValue"
				value="<bean:write name="pkValue" scope="request"/>">
			<input type="hidden" name="doType" id="doType"
				value="<bean:write name="doType" scope="request"/>">
			<input type="hidden" name="view" id="view"
				value="<bean:write name="view"  scope="request"/>">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm"  scope="request"/>">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>项目维护</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:notEqual value="toview" name="view">
			          		<button name="保存"  id="buttonSave" onclick="mySztzchkCode('xn-xq-xmmc-xmjb-kmdm-xydm-zzdw-zbsj-hddx');">保 存</button>
			          </logic:notEqual>
			            <button name="关闭" onclick="Close();return false;" id="buttonClose">关闭</button>
			          </div>
			         </td>
			      </tr>
			    </tfoot>
				<tbody>
					<logic:equal value="add" name="doType">
						<br>
						<div align="center">
							<font color="red"> 此处添加的项目，不需上级部门审核，即可通过！ </font>
						</div>
					</logic:equal>
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>填写项目表</b>
							</td>
						</tr>
					</thead>
					<tr valign="middle">
						<th align="right" nowrap>
							<font color="red">*</font>学年
						</th>
						<td align="left">
							<html:select name="rs" property="xn" style="width:120px"
								style="background-color:#FFFFFF;">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th width="15%" align="right" nowrap>
							<font color="red">*</font>项目名称
						</th>
						<td width="35%" align="left">
							<%--							<logic:present name="isCSMZ">			--%>
							<html:text name="rs" property="xmmc" styleId="xmmc"
								style="width:250px" />
							<%--							</logic:present>	--%>
							<%--							<logic:notPresent name="isCSMZ">--%>
							<%--							<html:text name="rs" property="xmmc" style="width:250px"  maxlength="100"/>	--%>
							<%--							</logic:notPresent>--%>

						</td>
					</tr>
					<tr valign="middle">
						<th align="right" nowrap>
							<font color="red">*</font>学期
						</th>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								style="background-color:#FFFFFF;">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
						<th align="right" nowrap>
							<font color="red">*</font>所属科目
						</th>
						<td align="left">
							<html:select name="rs" styleId="kmdm" property="kmdm"
								style="background-color:#FFFFFF;">
								<html:options collection="kmList" property="kmdm"
									labelProperty="kmm"></html:options>
							</html:select>
						</td>
					</tr>
					<tr valign="middle">
						<th align="right">
							<font color="red">*</font>项目级别
						</th>
						<td align="left">
							<html:select name="rs" property="xmjb" styleId="xmjb"
								onchange="showBj();inputLimit()"
								style="background-color:#FFFFFF;">
								<html:options collection="xmjbList" property="en"
									labelProperty="cn"></html:options>
							</html:select>
						</td>
						<th align="right" nowrap>
							<font color="red">*</font>申请部门
						</th>
						<td align="left">
							<html:select name="rs" property="xydm" styleId="xy" 
								onchange="initBjList();initYhList()"
								style="background-color:#FFFFFF;width:150px">
								<option value=""></option>
								<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc"></html:options>
							</html:select>
							<div id="bjlb" style="display: none">
								班级
								<html:select name="rs" property="bjdm" styleId="bj"
									onchange="initXhList()">
									<html:options collection="bjList" labelProperty="mc"
										property="dm" />
								</html:select>
								<div>
						</td>
					</tr>
					<tr valign="middle">
						<th align="right" nowrap>
							<font color="red">*</font>主办时间
						</th>
						<td align="left">
							<html:text name='rs' property="zbsj" styleId="zbsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('zbsj','y-mm-dd');" />
						</td>
						<th align="right" nowrap>
							<font color="red">*</font>
							<logic:equal value="12104" name="xxdm">
<%--							   <!-- 柳州职业技术<bean:message key="lable.xsgzyxpzxy" />学校代码 -->--%>
							   指导老师
							</logic:equal>
							<logic:notEqual value="12104" name="xxdm">
							   组织单位
							</logic:notEqual>
						</th>
						<td align="left">
							<html:text name="rs" property="zzdw" />
						</td>
					</tr>
					<tr valign="middle">
						<th align="right" nowrap>
							<font color="red">*</font>活动对象
						</th>
						<td align="left">
							<html:text name="rs" property="hddx" />
						</td>
						<th align="right" nowrap>

						</th>
						<td align="left">

						</td>
					</tr>
                  <logic:equal value="12104" name="xxdm">
                     <tr valign="middle">
						<th align="right" nowrap>
							活动计划人数
						</th>
						<td align="left">
							<html:text name="rs" property="hdjhrs" maxlength="10"></html:text>
						</td>
						<th align="right" nowrap>
                            活动形式
						</th>
						<td align="left">
                            <html:text name="rs" property="hdxs" maxlength="15"></html:text>
						</td>
					</tr>
                     </logic:equal>							
					<tr valign="middle">
						<th align="right" nowrap>						   
							   项目负责人(申报人)						
						</th>
						<td align="left" colspan="3">
							<div id="stufzr" style="display: none">
								<html:select name="rs" property="xmsbr" styleId="xmsbr">
									<html:option value=""></html:option>
									<html:options collection="xsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</div>
							<div id="techfzr" style="display: none">								
								<html:select name="rs" property="xmsbr1" styleId="xmsbr1" onchange="getYhSzbmInfo()">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp; &nbsp; 所属部门
								<span id="yhssbm"><bean:write name="rs" property="szbmmc" />
								</span>
							</div>
						</td>
					</tr>
					<tr align="left" valign="top">
						<th align="right">
							项目描述
							<br>
							(限500字)&nbsp;
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xmms" rows="5" style="word-break:break-all;" 
								cols='76'></html:textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="99%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td align="center">
											项目奖项
											<div align="left">
												<button  onclick="showHide(this,'ly')">
													显示
												</button>
											</div>
										</td>
									</tr>
								</thead>
								<tr id="ly" style="display: none">
									<td>
										<fieldset>
											<legend>
												奖项 &nbsp;&nbsp;&nbsp;
												<font color="blue"><button 
														onclick="addTr('tTb')" style="height:18px;width:20px">
														+
													</button> &nbsp;
													<button  onclick="decrease('tTb')"
														style="height:18px;width:20px">
														-
													</button> </font>
											</legend>
											<table width="100%" align="center" class="tbstyle" id="tTb">
												<thead>
													<tr>
														<td align="center" style="cursor:hand" style="width:50px">
															序号
														</td>
														<td align="center" style="cursor:hand">
															奖项名
														</td>
														<td align="center" style="cursor:hand">
													       ${fsclin}
														</td>
													</tr>
												</thead>
												<tr style="display:none" id="1">
													<td>
														1
													</td>
													<td>
														<html:text name="rs" property="jxm1" styleId="jxm1"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr1" styleId="jxnr1"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="2">
													<td>
														2
													</td>
													<td>
														<html:text name="rs" property="jxm2" styleId="jxm2"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr2" styleId="jxnr2"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="3">
													<td>
														3
													</td>
													<td>
														<html:text name="rs" property="jxm3" styleId="jxm3"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr3" styleId="jxnr3"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="4">
													<td>
														4
													</td>
													<td>
														<html:text name="rs" property="jxm4" styleId="jxm4"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr4" styleId="jxnr4"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="5">
													<td>
														5
													</td>
													<td>
														<html:text name="rs" property="jxm5" styleId="jxm5"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr5" styleId="jxnr5"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="6">
													<td>
														6
													</td>
													<td>
														<html:text name="rs" property="jxm6" styleId="jxm6"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr6" styleId="jxnr6"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="7">
													<td>
														7
													</td>
													<td>
														<html:text name="rs" property="jxm7" styleId="jxm7"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr7" styleId="jxnr7"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="8">
													<td>
														8
													</td>
													<td>
														<html:text name="rs" property="jxm8" styleId="jxm8"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr8" styleId="jxnr8"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="9">
													<td>
														9
													</td>
													<td>
														<html:text name="rs" property="jxm9" styleId="jxm9"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr9" styleId="jxnr9"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="10">
													<td>
														10
													</td>
													<td>
														<html:text name="rs" property="jxm10" styleId="jxm10"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr10" styleId="jxnr10"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="11">
													<td>
														11
													</td>
													<td>
														<html:text name="rs" property="jxm11" styleId="jxm11"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr11" styleId="jxnr11"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="12">
													<td>
														12
													</td>
													<td>
														<html:text name="rs" property="jxm12" styleId="jxm12"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr12" styleId="jxnr12"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="13">
													<td>
														13
													</td>
													<td>
														<html:text name="rs" property="jxm13" styleId="jxm13"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr13" styleId="jxnr13"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="14">
													<td>
														14
													</td>
													<td>
														<html:text name="rs" property="jxm14" styleId="jxm14"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr14" styleId="jxnr14"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
												<tr style="display:none" id="15">
													<td>
														15
													</td>
													<td>
														<html:text name="rs" property="jxm15" styleId="jxm15"
															style="width:200px;cursor:hand;color:#999999"
															maxlength="5" />
													</td>
													<td>
														<html:text name="rs" property="jxnr15" styleId="jxnr15"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="viewdetail" style="display: none">
						<td colspan="4">
							<table width="99%" align="center" class="tbstyle">
								<thead>
									<tr>
										<td align="center" style="cursor:hand">
											<logic:equal value="11417" name="xxdm">
														本项目学生${fsclin}申报情况
														</logic:equal>
											<logic:notEqual value="11417" name="xxdm">
														本项目学生成果(${fsclin})申报情况
														</logic:notEqual>

											<div align="left">
												<button  onclick="showHide(this,'showList')">
													显示
												</button>
											</div>
										</td>
									</tr>
								</thead>
								<tr id="showList" style="display: none">
									<td>
										<fieldset>
											<legend>
												情况列表
											</legend>
											<logic:empty name="res">
												<p>
												<div align="center">
													该项目暂无学生申报
												</div>
											</logic:empty>
											<logic:notEmpty name="res">
												<table width="100%" align="center" class="tbstyle" id="tTb">
													<thead>
														<tr>
															<td align="center" style="cursor:hand" style="width:50px">
																学号
															</td>
															<td align="center" style="cursor:hand">
																姓名
															</td>
															<td align="center">
																性别
															</td>
															<td align="center">
																年级
															</td>
															<td align="center">
																<bean:message key="lable.xsgzyxpzxy" />
															</td>
															<td align="center">
																班级
															</td>
															<td align="center">
																奖项
															</td>
															<td align="center">
																学分
															</td>
															<td align="center">
																审核
															</td>
														</tr>
													</thead>
													<logic:iterate name="res" id="s">
														<tr>
															<logic:iterate id="v" name="s">
																<td>
																	<bean:write name="v" />
																</td>
															</logic:iterate>
														</tr>
													</logic:iterate>
												</table>
											</logic:notEmpty>
										</fieldset>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</tbody>
				</table>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
	<logic:notEqual value="" name="doType">
		<logic:equal value="ok" name="done">
			<script type="text/javascript">
	        alert("操作成功");
	        Close();
	        window.dialogArguments.document.getElementById('search_go').click();	        	        
	        </script>
		</logic:equal>
	</logic:notEqual>
	<logic:equal value="" name="doType">
		<logic:equal value="ok" name="done">
			<script type="text/javascript">
	        alert("申报成功");
	        </script>
		</logic:equal>
	</logic:equal>
	<logic:equal value="isExist" name="isExist">
		<script type="text/javascript">
	    alert("该学年、学期、该项目已通过审核或正在审核中，不能再操作！");
	</script>
	</logic:equal>
	<script type="text/javascript">
function mySztzchkCode(mustFill) {
	 var eles = mustFill.split("-");
     for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("请将带\"*\"号的项目输入完整！");
			return false;
		}
	 }
	var bj= $("xmjb").value;
	var xmsbr = $("xmsbr").value;
	var xmsbr1= $("xmsbr1").value;
	if(bj=="班级"){
	   if($("bjdm").value==""){
	      alert("请选择申报部门或班级！");
          return false;	  
	   }
	   if(xmsbr==""){
          alert("请选择班级项目负责人学号！");
          return false;	   
	   }
	}else{
	   if(xmsbr1==""){
          alert("请选择"+bj+"项目负责人！");
          return false;	   
	   }
	}

	var my_LyAarry = ["jxm","jxnr"];
	for(i=0;i<15;i++){
		var n=i+1;
			if($(my_LyAarry[0]+n).value!=""&&$(my_LyAarry[1]+n).value==""){
				alert("第"+n+"个奖项学分为空！");		
				$(my_LyAarry[1]+n).focus();	
				return false;			
				break;
			}
			if($(my_LyAarry[1]+n).value!=""&&$(my_LyAarry[0]+n).value==""){
			   alert("第"+n+"个奖项名为空！");			
				$(my_LyAarry[0]+n).focus();	
				return false;			
				break;
			}
	  }
	if(confirm('确定要提交该项目信息？')){
	    var xmmc = $('xmmc').value;
	    var xmjb = $('xmjb').value;
	    var xn   = $('xn').value;
	    var xq   = $('xq').value;
	    var val = xn+xq+xmmc+xmjb;   
	    var doType = $("doType").value;
	    if("add"==doType){
		 getSztzData.getDataEx("csmz_tzxmb","xn||xq||xmmc||xmjb",val,function(str){		       
		         if(str!=""){		         	
		            alert("该项目已经提交！");		            
			        return false;
		         }else{
		          refreshForm("/xgxt/csmz_sztz.do?method=tzxmAdd&act=save");
		         }		       
		 });	
		 }else{
		   refreshForm("/xgxt/csmz_sztz.do?method=tzxmAdd&act=save");
		 }  
		document.getElementById("buttonSave").disabled=true;
    }else{
       return false;
    }
   }
      
function showHide(obj,idStr){	
      if(document.getElementById(idStr).style.display==''){
		 document.getElementById(idStr).style.display='none';
		 obj.value='显示'
      }else{
		 document.getElementById(idStr).style.display='';
		 obj.value='隐藏'
	  }		
  }
function showBj(){
   var bjv= $("xmjb").value;
   if(bjv=='班级'){
      document.getElementById('bjlb').style.display='';
      document.getElementById('stufzr').style.display='';
      document.getElementById('techfzr').style.display='none';
   }else{
      document.getElementById('bjlb').style.display='none';
      document.getElementById('techfzr').style.display='';
      document.getElementById('stufzr').style.display='none';
   }
}
function bmDisabled(ele) {
    var userType = document.forms[0].userType.value;
	if ( userType!='admin'&&userType!='xx')  {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
		    if(document.getElementById(tmp[i])){
			   document.getElementById(tmp[i]).disabled = true;
			}
		}
	}
}
</script>
</html>

