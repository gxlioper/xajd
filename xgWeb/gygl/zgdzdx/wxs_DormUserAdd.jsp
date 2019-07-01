<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 
					<logic:equal value="11647" name="xxdm"><!-- 浙江传媒<bean:message key="lable.xsgzyxpzxy" /> -->
					特殊生住宿信息维护
					</logic:equal>
					<logic:notEqual value="11647" name="xxdm">				
					非本校生住宿信息维护
					</logic:notEqual></a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<html:form action="/zgdzdx_Gygl" method="post">
			<!-- 住宿信息维护 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>住宿信息维护</span>
						</th>
					</tr>
				</thead>
				<tbody>		
						<tr>
							<th align="right" width="25%">
								<font color="red">*</font>学号：<br>(临时)&nbsp;&nbsp;							
							</th>
							<td align="left" width="15%">
							<html:text  property="xh" styleId="xh" onblur="wxsChekXh(this)" maxlength="20"/>
							</td>
							<th align="right">
								<font color="red">*</font>姓名：
							</th>
							<td align="left">
								<html:text  property="xm" styleId="xm" maxlength="15"/>
							</td>						
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>性别：
							</th>
							<td align="left">
								<html:select  property="xb"  styleId="xb"  style="width:150px"
								onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=wxsDormUserAdd');">                                
                                 <html:option value=""></html:option>
                                 <html:option value="男">男</html:option>
                                 <html:option value="女">女</html:option>
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>宿舍号：
							</th>
							<td align="left">
								<html:select  property="ssbh" 
									styleId="ssbh" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=wxsDormUserAdd');">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							</tr>
						<tr>
												
							<th align="right">
								<font color="red">*</font>年级：
							</th>
							<td align="left">
								<html:select  property="nj" 
									styleId="nj" onchange="">
									<html:options collection="njList" property="nd"
										labelProperty="nd" />
                                </html:select>
							</td>
						
							<th align="right">
								<font color="red">*</font>床位号：
							</th>
							<td align="left">
								<html:select  property="cwh"  styleId="cwh"  style="width:150px">																		
									<html:options collection="cwhList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
						<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</th>
							<td align="left">
								<html:text  property="xymc" styleId="xy" maxlength="25"/>
							</td> 
							<th align="right">
								<font color="red">*</font>入住时间：
							</th>
							<td align="left">
								<html:text  property="rzrq" styleId="rzrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true"/>
							</td>							
						</tr>
						<tr>
							<th align="right">
								专业：
							</th>
							<td align="left">
								<html:text  property="zymc" styleId="zy"  maxlength="25"/>
							</td>
							<th align="right">								
							</th>
							<td align="left">
							</td>                         
						</tr>
						<tr>
						<th align="right">
							班级：
						</th>
						<td align="left">
							<html:text property="bjmc" styleId="bj"  maxlength="25"/>
						</td>
						<th align="right">

						</th>
						<td align="left" colspan="2">
						</td>
					</tr>						
						<tr align="left">
							<th align="right">
								备注：
							</th>
							<td colspan="4">
								<html:textarea  property='bz' style="width:400px"
									rows='5' />
							</td>
						</tr>
						</tbody>
						<tfoot>
						<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="wxs_DUAddSave('xh-xm-xb-ssbh-cwh-nj-rzrq')"
										style="width: 80px">
										保	存
									</button>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
						</tfoot>
</table>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败,或该学生(学号)已经入住，请检查输入的数据后再提交！");
			</script>
		</logic:equal>
				
		</html:form>
	</body>
	<script type="text/javascript">
	     function wxs_DUAddSave(mustFill){
	       var eles = mustFill.split("-");
	       for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("请将带\"*\"号的项目输入完整！");
			       return false;
		           }		
	       }
	       if($("bz").value.length>200){
	           alert("备注字数不能超过200字！");
	           return false;
	       }	          
           refreshForm("/xgxt/zgdzdx_Gygl.do?method=wxsDormUserAdd&doType=Save");
           $("buttonSave").disabled=true;
	     }
	</script>
</html>
