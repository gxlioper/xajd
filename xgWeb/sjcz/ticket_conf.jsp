<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function saveTrainConf(url){
			var xn = 'xn';
			var nd = 'nd';
			var kssj = document.forms[0].kssqsj1.value.replace("-","").replace("-","")+document.forms[0].kssqsj2.value+document.forms[0].kssqsj3.value+document.forms[0].kssqsj4.value;
			var jssj = document.forms[0].jssqsj1.value.replace("-","").replace("-","")+document.forms[0].jssqsj2.value+document.forms[0].jssqsj3.value+document.forms[0].jssqsj4.value;	
			if(kssj>jssj){
				alert("申请结束时间应晚于申请开始时间！");
				return false;
			}
			document.getElementById('kssqsj').value=kssj;
			document.getElementById('jssqsj').value=jssj;
			
			kssj = document.forms[0].cckssj1.value.replace("-","").replace("-","")+document.forms[0].cckssj2.value+document.forms[0].cckssj3.value+document.forms[0].cckssj4.value;
			jssj = document.forms[0].ccjssj1.value.replace("-","").replace("-","")+document.forms[0].ccjssj2.value+document.forms[0].ccjssj3.value+document.forms[0].ccjssj4.value;
			
			document.getElementById('cckssj').value=kssj;
			document.getElementById('ccjssj').value=jssj;
			
			if(kssj>jssj){
				alert("乘车结束时间应晚于乘车开始时间！");
				return false;
			}
			
			if (checkXnNd(xn, nd)) {
				if (url != null) {
					refreshForm(url);
				}
				return true;
			} else {
				return false;
			}
		}
		</script>
	</head>
	<body>

		<center>
			<html:form action="/chgPriseBat" method="post">
				<div class="tab_cur" id="jd">
					<p class="location">
						<em>您的当前位置:</em><a>日常事务 - 票务管理 - 时间设定</a>
					</p>
				</div>
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="2" align="center">
									<div class="btn">
										<button type="button" class="button2"
											onclick="saveTrainConf('ticket_conf.do?act=save')">
											保存
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<thead>
							<tr>
								<th colspan="4">
									<span>车票预订时间设定</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tbody>
							<tr>
								<th width="45%" height="25" align="right">
									学年
								</th >
								<td>
									<html:select property="xn" style="width:100px">
										<html:options collection="xnndList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th  width="45%" height="25" align="right">
									年度
								</th >
								<td >
									<html:select property="nd" style="width:100px">
										<html:options collection="xnndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th  width="45%" height="25" align="right">
									学期
								</th >
								<td height="25" align="left">
									<html:select property="xq" style="width:100px">
										<option value="">
										</option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th  align="right">
									申请开始时间
								</th >
								<td align="left">
									<input type="hidden" name="kssqsj" id="kssqsj" value="" />
									<input type="text" readonly style="cursor:hand;width:80px"
										onclick="return showCalendar('kssqsj1','y-mm-dd');"
										value="<bean:write name="kssj1" />" name="kssqsj1"
										id="kssqsj1" />
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
								<th  align="right">
									申请截至时间
								</th >
								<td align="left">
									<input type="hidden" name="jssqsj" id="jssqsj" value="" />
									<input type="text" readonly style="cursor:hand;width:80px"
										onclick="return showCalendar('jssqsj1','y-mm-dd');"
										value="<bean:write name="jssj1" />" name="jssqsj1"
										id="jssqsj1" />
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

							<tr>
								<th  align="right">
									乘车开始时间
								</th >
								<td align="left">
									<input type="hidden" name="cckssj" id="cckssj" value="" />
									<input type="text" readonly style="cursor:hand;width:80px"
										onclick="return showCalendar('cckssj1','y-mm-dd');"
										value="<bean:write name="cckssj1" />" name="cckssj1"
										id="cckssj1" />
									－
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<bean:write name="cckssj2" />"
										name="cckssj2" id="cckssj2" />
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<bean:write name="cckssj3" />"
										name="cckssj3" id="cckssj3" />
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<bean:write name="cckssj4" />"
										name="cckssj4" id="cckssj4" />
								</td>
							</tr>
							<tr>
								<th  align="right">
									乘车截至时间
								</th >
								<td align="left">
									<input type="hidden" name="ccjssj" id="ccjssj" value="" />
									<input type="text" readonly style="cursor:hand;width:80px"
										onclick="return showCalendar('ccjssj1','y-mm-dd');"
										value="<bean:write name="ccjssj1" />" name="ccjssj1"
										id="ccjssj1" />
									－
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<bean:write name="ccjssj2" />"
										name="ccjssj2" id="ccjssj2" />
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<bean:write name="ccjssj3" />"
										name="ccjssj3" id="ccjssj3" />
									：
									<input type="text" onkeypress="return numInputValue(this,2,event)"
										style="width:20px" value="<bean:write name="ccjssj4" />"
										name="ccjssj4" id="ccjssj4" />
								</td>
							</tr>
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
