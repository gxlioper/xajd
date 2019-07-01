<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery.ajax({
					url:'jyglTables.do?method=getXkkcForAjax&xh=${rs.xh }',
					type:'post',
					dataType:'json',
					success:function(data){
						jQuery.each(data,function(i,v){
							jQuery('input[type="checkbox"][value="'+v.xkkh+'"]').attr('checked',true);
						})
					}
				})
			})
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/jyglTables" method="post">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="saveUpdate('jyglTables.do?method=dgszDykc&doType=save','')">
										保存
									</button>
									<button id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.xm }
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
						<tr>
							<th>
								选择课程
							</th>
							<td colspan="3">
								<logic:present name="kcList">
									<logic:iterate id="k" name="kcList" indexId="i">
										<%if (i % 3 == 0){
										%>
										<br/>
										<%
										} %>
										<html:checkbox property="xkkh" value="${k.xkkh }">${k.kcmc }</html:checkbox>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
				window.close();
			</script>
		</logic:present>
	</body>
</html>
