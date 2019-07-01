<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 申请 - 精神文明活动申请 </a>
			</p>
		</div>
		<!-- 标题 end-->
			<html:form action="/zgdzdx_Gygl" method="post">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<logic:notEqual value="stu" name="userType">
				<div align="center">
					<font color="red">只有学生用户可以申请!</font>
				</div>
			</logic:notEqual>

			<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								<span>活动情况</span>
							</td>
						</tr>
					</thead>
				<tbody>		
				<tr>
					<th height="22" width="15%" align="right">
						<font color="red">*</font>活动名称：
					</th>
					<td height="22" align="left">
						<html:text property="hdmc" maxlength="40"></html:text>
					</td>
					<th height="22" align="right" width="15%">
						<font color="red">*</font>主办单位：
					</th>
					<td height="22" align="left">
						<html:text property="zbdw" maxlength="40"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right" width="20%">
						<font color="red">*</font>负责人姓名：
					</th>
					<td height="22" align="left">
						<html:text property="fzrxm"></html:text>
					</td>
					<th height="22" align="right" width="20%">
						<font color="red">*</font>负责人联系方式：
					</th>
					<td height="22" align="left">
						<html:text property="fzrlxfs"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						经手人姓名：
					</th>
					<td height="22" align="left">
						<html:text property="jsrxm"></html:text>
					</td>
					<th height="22" align="right">
						经手人联系方式：
					</th>
					<td height="22" align="left">
						<html:text property="jsrlxfs"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>活动地点：
					</th>
					<td height="22" align="left">
						<html:text property="hddd"></html:text>
					</td>
					<th height="22" align="right">
						参加人数：
					</th>
					<td height="22" align="left">
						<html:text property="cjrs"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>活动开始日期：
					</th>
					<td height="22" align="left">
							<html:text property="hdksrq" styleId="hdksrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px"
							onclick="return showCalendar('hdksrq','y-mm-dd');"
							readonly="true" />(日)<html:text property="hdkssj" styleId="hdkssj"
							 style="width:70px"/>(时)
					
					</td>
					<th height="22" align="right">
						活动结束日期：
					</th>
					<td height="22" align="left">
							<html:text property="hdjsrq" styleId="hdjsrq"
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px"
							onclick="return showCalendar('hdjsrq','y-mm-dd');"
							readonly="true" />(日)<html:text property="hdjssj" styleId="hdjssj"
							 style="width:70px"/>(时)
					
					</td>					
				</tr>
				<tr>
					<th height="22" align="right">
						活动申请人：
					</th>
					<td height="22" align="left">
						<input type="hidden" id="sqrxm" name="sqrxm" value="<bean:write name="userNameReal"/>" />
						<bean:write name="userNameReal"/>				
					</td>	
					<th height="22" align="right">
						
					</th>
					<td height="22" align="left">
					</td>				
				</tr>
				<tr>
					<th height="22" align="right">
						<font color="red">*</font>活动内容：
					</th>
					<td height="22" align="left" colspan="3">
						<html:textarea property='hdnr'style="width:99%"
							rows='15' />
					</td>					
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:equal value="stu" name="userType">
									<button id="buttonSave" 
										onclick="jswmhdSave('hdmc-zbdw-fzrxm-fzrlxfs-hddd-hdksrq-hdkssj-hdnr')"
										style="width: 80px">
										提 交
									</button>
								</logic:equal>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="refreshForm('/xgxt/zgdzdx_Gygl.do?method=jswmhdSq')"
									style="width: 80px">
									重 填
								</button>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="jswmsqb()"
									style="width: 80px">
									申请表打印
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("操作成功！");
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("操作失败!");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function jswmhdSave(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("请将带\"*\"号的项目输入完整！");
			       return false;
		           }		
	           }	
	       if(confirm("确定要提交该活动申请？")){           
              refreshForm("/xgxt/zgdzdx_Gygl.do?method=jswmhdSq&doType=Save");
	       }
	     }
	</script>
</html>
