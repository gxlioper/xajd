<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script language="javascript" src="js/calendar/calendar.js"></script>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<script type="text/javascript">
		  function szsave(){
		     if($("kssqsj1")){
		        if($("kssqsj1").value==''){
		           alert('开始日期不能为空！');
		            return false;
		        }		       
		     }
		     if($("kssqsj1")){
		        if($("jssqsj1").value==''){
		           alert('截止日期不能为空！');
		           return false;
		        }		        
		     }
		     if($("bjgbdm")){
		        if($("bjgbdm").value==''){
		           alert('请选择操作学生干部种类！');
		           return false;	
		        }		      	        
		     }
		     
		     saveSztzParaSet('kssqsj','jssqsj','xn','nd','csmz_sztz.do?method=param_set&act=save');
		     $("buttonSave").disabled=true;		    
		  }
		</script>
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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<center>
			<html:form action="/csmz_sztz.do?method=param_set" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>${tips }</a>
					</p>
				</div>

				<div class="tab">
				  <table width="100%"  border="0" class="formlist">
				    <thead>
				    	<tr>
				        	<th colspan="3"><span>参数设置</span></th>
				        </tr>
				    </thead>
						 <tfoot>
						      <tr>
						        <td colspan="3">
						          <div class="btn">
						          		<button  id="buttonSave"
										onclick="szsave();">
										保存
									</button>
						          </div></td>
						      </tr>
						    </tfoot>
						<tbody>
						<tr>
							<th align="right">
								<logic:present name="showCSMZZYJSXY">
							    拓展成果申报开始时间
							    </logic:present>
								<logic:notPresent name="showCSMZZYJSXY">
									<logic:present name="XXDM_BJLHDX">
									得分申报开始时间
									</logic:present>
									<logic:notPresent name="XXDM_BJLHDX">
								    学分申报开始时间
								    </logic:notPresent>
								</logic:notPresent>
							</th>
							<td align="left" colspan="2">
								<input type="hidden" name="kssqsj" id="kssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('kssqsj1','y-mm-dd');"
									value="<bean:write name="kssj1" />" name="kssqsj1" id="kssqsj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj2" />"
									name="kssqsj2" id="kssqsj2" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj3" />"
									name="kssqsj3" id="kssqsj3" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj4" />"
									name="kssqsj4" id="kssqsj4" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<logic:present name="showCSMZZYJSXY">
							    拓展成果申报截至时间
							    </logic:present>
								<logic:notPresent name="showCSMZZYJSXY">
									<logic:present name="XXDM_BJLHDX">
									得分申报截至时间
									</logic:present>
									<logic:notPresent name="XXDM_BJLHDX">
								    学分申报截至时间
								    </logic:notPresent>								
							    </logic:notPresent>
							</th>
							<td align="left" colspan="2">
								<input type="hidden" name="jssqsj" id="jssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('jssqsj1','y-mm-dd');"
									value="<bean:write name="jssj1" />" name="jssqsj1" id="jssqsj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj2" />"
									name="jssqsj2" id="jssqsj2" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj3" />"
									name="jssqsj3" id="jssqsj3" />
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj4" />"
									name="jssqsj4" id="jssqsj4" />
							</td>
						</tr>
					   <logic:present name="showXsGbCzZl">
						<tr>
							<th width="25%" height="25" align="right">
								选择操作学生干部种类
							</th>
							<td height="25" align="left">
								<html:select property="bjgbdm" style="width:150px"
									styleId="bjgbdm">
									<html:option value=""></html:option>
									<html:options collection="bjgbList" property="bjgbdm"
										labelProperty="bjgbmc" />
								</html:select>
							</td>
							<td>
								<span style="font-size: 11px;color: red">请在思政队伍-&gt;学生干部管理-&gt;班级学生干部队伍中，设置班干部成员</span>
							</td>
						</tr>
						</logic:present>
						<logic:present name="showCSMZZYJSXY">
							<tr>
							<th width="25%" height="25" align="right">
								成果申报学年、学期
							</th>
							<td height="25" align="left">
								<html:select property="xn" style="width:150px"
									styleId="xn">           								
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<td height="25" align="left">
									<html:select property="xq" style="width:150px"
									styleId="xq">           								
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								    </html:select>
							</td>	
						</tr>
					</logic:present>
					</tbody>	
					</table>
				<logic:notEmpty name="ok">
					<logic:equal name="ok" value="ok">
						<script>alert("保存成功!")</script>
					</logic:equal>
					<logic:equal name="ok" value="no">
						<script>alert("保存失败!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
</html>
