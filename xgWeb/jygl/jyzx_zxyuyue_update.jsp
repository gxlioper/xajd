<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function zxyuyueupdate(){
			 var zxwtjs = document.getElementById("zxwtjs").value;
			 var pkValue = document.getElementById('pkValue').value;
				if(zxwtjs.length>300){
					alert("咨询问题简述字数不能超过300个");
					return false;
				}
	         document.forms[0].action = "/xgxt/updateYuyue.do?act=go&doType=update&pkValue="+pkValue;
			 document.forms[0].submit();
		}
		
		function meetonchange(){
	         document.forms[0].action = "/xgxt/updateYuyue.do?act=go&doType2=meetonchange";
			 document.forms[0].submit();
		}
		</script>
	</head>
	<body>
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<html:form action="/data_search" method="post">
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询预约详细信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody> 
						<tr>
							<th>
								预约提交时间
							</th>
							<td colspan="3">
								<html:text name="rs" property="tjsj" readonly="true"
									style="width:160px" />
							</td>
						</tr>
						<tr>
							<th width="20%">
								咨询师编号
							</th>
							<td width="30%">
								<bean:write name="rs" property="num" />
							</td>
							<th width="20%">
								咨询师姓名
							</th>
							<td width="30%">
								<bean:write name="rs" property="zxsname" />
							</td>
						</tr>
					<tr>
						<th>
							学号
						</th>
						<td>
							<html:text name="rs" property="xsxh" style="width=100%"
								readonly="true" />
						</td>
						<th>
							学生姓名
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr>
						<th>
							学生性别
						</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<th>
							学生年级
						</th>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />名称
						</th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<th>
							专业名称
						</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr>
						<th>
							是否要求见面
						</th>
						<td>							
								<logic:equal value="是" name="rs" property="meet">
									<input type="radio" name="meet" value="是" checked onclick="meetonchange()"/>&nbsp;&nbsp;是
							   <input type="radio" name="meet" value="否" onclick="meetonchange()"/>&nbsp;&nbsp;否
							</logic:equal>
								<logic:equal value="否" name="rs" property="meet">
									<input type="radio" name="meet" value="是" onclick="meetonchange()"/>&nbsp;&nbsp;是
							   <input type="radio" name="meet" value="否" checked onclick="meetonchange()"/>&nbsp;&nbsp;否
							</logic:equal>		
						</td>

						<th>
							期望约见时间
						</th>
						<td>
							<logic:equal name="mt" value="yes">
								<html:text name="rs" style="cursor:hand; width=100%;"
									styleId="qwyjtime" property="qwyjtime"
									onclick="return showCalendar('qwyjtime','y-mm-dd');"
									readonly="true" />
							</logic:equal>
							<logic:equal name="mt" value="no">

							</logic:equal>
						</td>
					</tr>
					<tr>
						<th>
							咨询问题简述
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="zxwtjs" rows="6"
								style="width:90%" />
						</td>
					</tr>
					<tr>
						<th>
							咨询问题回复
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="zxwthf" rows="11"
								style="width:90%" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							学生确认
						</th>
						<td>
							<bean:write name="rs" property="xsqr" />
						</td>

						<th>
							<font color="red">*</font>咨询师确认
						</th>
						<td>
							<bean:write name="rs" property="zxsqr" />
						</td>
					</tr>
					<tr>
						<th>
							学生确认时间
						</th>
						<td>
							<bean:write name="rs" property="xsqrsj" />
						</td>

						<th>
							咨询师确认时间
						</th>
						<td>
							<bean:write name="rs" property="zxsqrsj" />
						</td>
					</tr>
					<tr>
						<th>
							最终约见地点
						</th>
						<td>
							<bean:write name="rs" property="yjdd" />
						</td>

						<th>
							最终约见时间
						</th>
						<td>
							<bean:write name="rs" property="yjsj" />
						</td>	
					</tr>
					</tbody>
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button  onclick="zxyuyueupdate()">
									提 交
								</button>
								<button 
									onclick="window.close();window.dialogArguments.document.getElementById('query_go').click();"
									>
									关 闭
								</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                       alert("修改成功！");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("修改失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
