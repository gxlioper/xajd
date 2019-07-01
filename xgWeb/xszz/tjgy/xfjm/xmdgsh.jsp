<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//检查该学生当前学年是否有其它项目审核通过
				if ('xy' == '${userType}'){
					jQuery.post('tjgy_xfjm.do?method=checkSfktg',{xh:'${rs.xh }',xmid:'${xmsqInfo.xmid}'},function(data){
						
						if (data == 'false'){
							jQuery('#shtg').attr('disabled',true);
							jQuery('#shtg').attr('class','disabled');
							jQuery('#prompt').css('display','');
						}
						
					})
				}
			})
		</script>
	</head>
	<body>
		<html:form action="/tjgy_xfjm" method="post">
			<html:hidden property="xh" value="${rs.xh }"/>
			<html:hidden property="xmid" value="${xmsqInfo.xmid}"/>
			<html:hidden property="xn" value="${xmsqInfo.xn}"/>
			<html:hidden property="pkValue" value="${xmsqInfo.xmid}${xmsqInfo.xh}${xmsqInfo.xn}"/>
		
			<logic:notEmpty name="xmsqInfo">
				<script defer>
					var sqtj = '${xmsqInfo.sqtj}'.split(',');
					for (var i = 0 ; i < sqtj.length; i++){
						jQuery('input[name=tjid][value='+sqtj[i]+']').attr('checked',true);
					}
				</script>
			</logic:notEmpty>
		
			<!-- 提示信息 end-->
			<div class="prompt" style="display:none" id="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					该学生已有其它学费减免项目审核通过。
				</p>
			</div>
			<!-- 提示信息 end-->
			
			
			<div class="tab" style="overflow-x:hidden;overflow-y:auto;overflow-x:0;height:550px"/>
				<table class="formlist" width="90%">
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
								${xmsqInfo.sjhm }
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
										<input type="checkbox" name="tjid" value="${x.tjdm }" disabled="disabled"/>${x.tjmc }
									</td>
								</logic:iterate>
							</logic:present>
						</tr>
						
						<tr>
							<th>
								应缴学费
							</th>
							<td>
								${xmsqInfo.yjxf }
							</td>
							<th>
								减免学费
							</th>
							<td>
								${xmsqInfo.jmxf }
							</td>
						</tr>
						<tr>
							<th>备注</th>
							<td colspan="3" style="word-break:break-all;">
								${xmsqInfo.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual value="view" property="doType" name="xfjmForm">
							<logic:equal value="xy" name="userType">
								<tr>
									<th><bean:message key="lable.xb" />审核</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="xyshyj" style="width:90%" rows="5" onblur='chLeng(this,1000)' value="${xmsqInfo.xyshyj }"></html:textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:notEqual value="xy" name="userType">
								<tr>
									<th><bean:message key="lable.xb" />审核</th>
									<td>${xmsqInfo.xysh }</td>
									<th><bean:message key="lable.xb" />审核时间</th>
									<td>${xmsqInfo.xyshsj }</td>
								</tr>
								<tr>
									<th><bean:message key="lable.xb" />审核意见</th>
									<td colspan="3" style="word-break:break-all;">
										${xmsqInfo.xyshyj }
									</td>
								</tr>
								<tr>
									<th>学校审核</th>
									<td>${xmsqInfo.xxsh }</td>
									<th>学校审核时间</th>
									<td>${xmsqInfo.xxshsj }</td>
								</tr
								<tr>
									<th>学校审核意见</th>
									<td colspan="3" style="word-break:break-all;">
										<html:textarea property="xyshyj" style="width:90%" rows="5" onblur='chLeng(this,1000)' value="${xmsqInfo.xxshyj }"></html:textarea>
									</td>
								</tr>
							</logic:notEqual>
						</logic:notEqual>
						<logic:equal value="view" property="doType" name="xfjmForm">
							<tr>
								<th><bean:message key="lable.xb" />审核</th>
								<td>${xmsqInfo.xysh }</td>
								<th><bean:message key="lable.xb" />审核时间</th>
								<td>${xmsqInfo.xyshsj }</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xb" />审核意见</th>
								<td colspan="3" style="word-break:break-all;">
									${xmsqInfo.xyshyj }
								</td>
							</tr>
							<tr>
								<th>学校审核</th>
								<td>${xmsqInfo.xxsh }</td>
								<th>学校审核时间</th>
								<td>${xmsqInfo.xxshsj }</td>
							</tr
							<tr>
								<th>学校审核意见</th>
								<td colspan="3" style="word-break:break-all;">
									${xmsqInfo.xxshyj }
								</td>
							</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual value="view" property="doType" name="xfjmForm">
										<button type="button" name="保存" id="shtg" onclick="saveUpdate('tjgy_xfjm.do?method=saveXmdgsh&amp;shzt=通过','');">
											通&nbsp;&nbsp;过
										</button>
										<button type="button" name="保存" id="shbtg" onclick="saveUpdate('tjgy_xfjm.do?method=saveXmdgsh&amp;shzt=不通过','');">
											不通过
										</button>
									</logic:notEqual>
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
