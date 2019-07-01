<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/tjgy_xfjm" method="post">
			<logic:notEmpty name="xmsqInfo">
				<html:hidden property="doType" value="modify"/>
				<script defer>
					var sqtj = '${xmsqInfo.sqtj}'.split(',');
					for (var i = 0 ; i < sqtj.length; i++){
						jQuery('input[name=tjid][value='+sqtj[i]+']').attr('checked',true);
					}
				</script>
			</logic:notEmpty>
		
		
			<div class="tab" />
				<table class="formlist" width="93%">
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								学号
							</th>
							<td>
								${rs.xh }
								<html:hidden property="xh" value="${rs.xh }"/>
								<html:hidden property="xmid" />
							</td>
							<th>
								姓名
							</th>
							<td>
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								性别
							</th>
							<td width="34%">
								${rs.xb }
							</td>
							<th width="16%">
								手机
							</th>
							<td width="34%">
								<html:text property="sjhm" maxlength="15" onkeyup="value=value.replace(/[^\d]/g,'')" value="${xmsqInfo.sjhm }"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>学费减免信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<logic:present name="xmtjList">
								<logic:iterate id="x" name="xmtjList" indexId="i">
									<%
										if (i != 0 && i %2 == 0){
									%>
									</tr>
									<tr>
									<%
										}
									 %>
									<td colspan="2">
										<input type="checkbox" name="tjid" value="${x.tjdm }"/>${x.tjmc }
									</td>
								</logic:iterate>
							</logic:present>
						</tr>
						
						<tr>
							<th>
								应缴学费
							</th>
							<td>
								<html:text property="yjxf" maxlength="10" onkeyup="checkInputNum(this)" value="${xmsqInfo.yjxf }"></html:text>
							</td>
							<th>
								减免学费
							</th>
							<td>
								<html:text property="jmxf" maxlength="10" onkeyup="checkInputNum(this)" value="${xmsqInfo.jmxf }"></html:text>
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="bz" style="width:90%" rows="5" onblur='chLeng(this,1000)' value="${xmsqInfo.bz }"></html:textarea>
							</td>
						</tr>
					</tbody>
					
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存" id="buttonSave"
										onclick="saveUpdate('tjgy_xfjm.do?method=saveXmsq','');">
										保&nbsp;&nbsp;存
									</button>
									<button type="button" name="关闭" onclick="window.close();return false;">
										关&nbsp;&nbsp;闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if (t=="ok") {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			</script>
		</logic:present>
	</body>
</html>
