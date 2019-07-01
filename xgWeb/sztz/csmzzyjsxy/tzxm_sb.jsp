<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<title><bean:message key="lable.title" />
		</title>
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
//-->
</script>
	<body
		onload="bmDisabled('xydm');defaultSee('tTb');isNotSee('btnsave');inputLimit()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<html:form action="/csmz_sztz.do?method=tzxm_sb" method="post">
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>">
			<input type="hidden" name="pkValue" id="pkValue"
				value="<bean:write name="pkValue" scope="request"/>">
			<input type="hidden" name="doType" id="doType"
				value="<bean:write name="doType" scope="request"/>">
			<input type="hidden" name="xxdm" id="xxdm" 
			    value="<bean:write name="xxdm" scope="request"/>">	
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>${tips }</a>
					</p>
				</div>

			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>填写申报表</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button name="提交"
						onclick="mySztzchkCode('xn-xq-xmmc-xmjb-kmdm-xydm-zzdw-zbsj-hddx-xmms');"
						id="buttonSave">
						提 交
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    
				 <tbody>
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
						<th align="right" nowrap>
							<font color="red">*</font>项目名称
						</th>
						<td align="left">
							<logic:present name="isCSMZ">
								<html:text name="rs" property="xmmc" style="width:150px"
									maxlength="13" />
							</logic:present>
							<logic:notPresent name="isCSMZ">
								<html:text name="rs" property="xmmc" style="width:150px"
									maxlength="100" />
							</logic:notPresent>
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
							<html:select property="xmjb" styleId="xmjb" onchange="inputLimit()"
								style="background-color:#FFFFFF;">
								<html:options collection="xmjbList" property="en"
									labelProperty="cn"></html:options>
							</html:select>

						</td>
						<th align="right" nowrap>
							<font color="red">*</font>申请部门
						</th>
						<td align="left">
							<html:select name="rs" property="xydm" styleId="xydm"
								style="width:150px;background-color:#FFFFFF;" onchange="initYhList()">
								<option value=""></option>
								<html:options collection="bmList" property="bmdm"
									labelProperty="bmmc"></html:options>
							</html:select>
							<logic:present name="isBJXM">
							班级
							<html:select property="bjdm" styleId="bjdm">
									<html:options collection="bjList" labelProperty="mc"
										property="dm" />
								</html:select>
							</logic:present>
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
								<!-- 柳州职业技术<bean:message key="lable.xsgzyxpzxy" />学校代码 -->
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
							<html:text name="rs" property="hddx" maxlength="25"/>
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
							<td align="left" >
								<logic:equal value="11417" name="xxdm">
						        <!-- 北京联合大学学校代码 -->
								<html:select name="rs" property="xmsbr" styleId="xmsbr1" style="width:150px"
									onchange="getYhSzbmInfo()">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhm"
										labelProperty="xm" />
								</html:select>
								</logic:equal>
								<logic:notEqual value="11417" name="xxdm">
									<bean:write name="rs" property="xmsbrmc" />
								</logic:notEqual>
							</td>
							<th>
								所属部门
							</th>
							<td>
								<logic:equal value="11417" name="xxdm">
									<span id="yhssbm"><bean:write name="rs" property="szbmmc" /></span>
								</logic:equal>
								<logic:notEqual value="11417" name="xxdm">
									<bean:write name="rs" property="szbmmc" />
								</logic:notEqual>
							</td>	
							
						</tr>
					<tr align="left" valign="top">
						<th align="right">
							<font color="red">*</font>项目描述
							<br>
							<font color="red">(限500字)&nbsp;</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xmms" rows="5" cols="66"
								style="word-break:break-all;" ></html:textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4">
						
 					 <div style="overflow-x:auto;overflow-y:auto;width:780px;height:400px;">
							<table width="99%" align="center" class="tbstyle">
								<thead>
									<tr>
										<th align="center">
											项目奖项
			           							 <button id="xxbut" onclick="showHide()">隐藏</button>
										</th>
									</tr>
								</thead>
								<tr id="ly">
									<td>
										<fieldset>
											<legend>
												奖项 &nbsp;&nbsp;&nbsp;
												<font color="blue"><button 
														onclick="addTr('tTb')" style="height:18px;width:20px">+</button> &nbsp;
													<button  onclick="decrease('tTb')"
														style="height:18px;width:20px">-</button>
												</font>
											</legend>
											<table width="100%" align="center" class="tbstyle" id="tTb">
												<thead>
													<tr>
														<th align="center" style="cursor:hand" style="width:50px">
															<div align="left">
															序号
															</div>
														</th>
														<th align="left" style="cursor:hand">
															<div align="left">
															奖项名
															</div>
														</th>
														<th align="left" style="cursor:hand">
													<logic:equal value="11417" name="xxdm">
													 <!-- 北京联合大学 -->
													 	<div align="left">
														得分
														</div>
														
													</logic:equal>
													<logic:notEqual value="11417" name="xxdm">
																<logic:equal value="12104" name="xxdm">
																	<!-- 柳州职业技术<bean:message key="lable.xsgzyxpzxy" />学校代码 -->
							                                            <div align="left">
							                                            活动分
							                                            </div>
							                                    </logic:equal>
							                                    <logic:equal value="12078" name="xxdm">
																	<!-- 硅湖学院 -->
																		<div align="left">
							                                            项目分
							                                            </div>
							                                    </logic:equal>
																<logic:notEqual value="12104" name="xxdm">
																	<logic:notEqual value="12078" name="xxdm">
																		<div align="left">
								                                         学分
								                                         </div>
								                                    </logic:notEqual>
								                                </logic:notEqual>
													</logic:notEqual>
														</th>
															<!-- 硅湖学院 -->
														   <logic:equal value="12078" name="xxdm">
														   <th align="left">	
														   		<div align="left">
														   		项目最高分
														   		</div>
														   </th>	
														   </logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf1" styleId="xzf1"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf2" styleId="xzf2"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf3" styleId="xzf3"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf4" styleId="xzf4"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf5" styleId="xzf5"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf6" styleId="xzf6"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf7" styleId="xzf7"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf8" styleId="xzf8"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf9" styleId="xzf9"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf10" styleId="xzf10"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf11" styleId="xzf11"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf12" styleId="xzf12"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf13" styleId="xzf13"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf14" styleId="xzf14"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
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
													<logic:equal value="12078" name="xxdm">
													<td>
														<html:text name="rs" property="xzf15" styleId="xzf15"
															style="width:80px;cursor:hand;color:#999999"
															onkeypress="return sztzNumInputValue(this,4,event);"
															onblur="chkInput(this,event)" />
													</td>
													</logic:equal>
												</tr>
											</table>
										</fieldset>
									</td>
								</tr>
							</table></div>
						</td>
					</tr>
					</tbody>
				</table>
				</div>
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
	    alert("该学年、学期、该项目已通过审核，不能再操作！");
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
	if($("xxdm").value=='11417'){//北京联合大学
	    var xmsbr = ($("xmsbr"))?$("xmsbr").value:"";
	    if(xmsbr==""){
	       alert("请选择项目负责人！");
	       return false;
	    }
	}
	if($("xmms").value.length>500){
	    alert("项目描述字数过多，不能超过500字！")
	    return false;
	}
	if($("xxdm").value=="12078"){//硅湖学院
		var my_LyAarry = ["jxm","jxnr","xzf"];
		for(i=0;i<15;i++){
			var n=i+1;
				if($(my_LyAarry[0]+n).value!=""&&$(my_LyAarry[1]+n).value==""){
					alert("第"+n+"个奖项分为空！");		
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
				if($(my_LyAarry[2]+n).value=="" &&$(my_LyAarry[0]+n).value!=""){
					alert("第"+n+"个限制分为空！");
					$(my_LyAarry[2]+n).focus();	
					return false;
					break;
				}
				if(eval($(my_LyAarry[1]+n).value)>eval($(my_LyAarry[2]+n).value)){
					alert("第"+n+"个奖项分高于限制分！");
					return false;
					break;
				}
		}
	}else{
		var my_LyAarry = ["jxm","jxnr"];
		for(i=0;i<15;i++){
			var n=i+1;
				if($(my_LyAarry[0]+n).value!=""&&$(my_LyAarry[1]+n).value==""){
					alert("第"+n+"个奖项分为空！");		
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
	}
	if(confirm('确定要提交该项目信息？')){
	    var xmmc = $('xmmc').value;
	    var xmjb = $('xmjb').value;
	    var xn   = $('xn').value;
	    var xq   = $('xq').value;

	    var val = xn+xq+xmmc+xmjb;	   
<%--	    if($('doType').value=="modi"){--%>
<%--	       refreshForm("/xgxt/csmz_sztz.do?method=tzxm_sb&act=save");	       --%>
<%--	    }else{--%>
		    getSztzData.getDataEx("csmz_tzxmb","xn||xq||xmmc||xmjb",val,function(str){
		         if(str!=""){		         	
		            alert("该项目已经申报！");		            
			        return false;
		         }else{
		            refreshForm("/xgxt/csmz_sztz.do?method=tzxm_sb&act=save");
		         }
		    });	  
<%--		} 		 --%>
		 document.getElementById("buttonSave").disabled=true;
    }else{
       return false;
    }
   }
      
function showHide(){		
      if(document.getElementById('ly').style.display==''){
		  document.getElementById('ly').style.display='none';
		  document.getElementById('xxbut').value='显示'
      }else{
		  document.getElementById('ly').style.display='';
		  document.getElementById('xxbut').value='隐藏'
	  }		
  }		
</script>
</html>

