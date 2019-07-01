<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("必填字段未填完整！");
						return false;
					}
				}
				
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
		</script>
	</head>
	<body>
		<html:form action="/rcsw_gzdx_lxgl" method="post">				
				<input type="hidden" name="pkValue" value="${param.pkValue }"/>
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><span>学生留校信息</span></th>
							</tr>
						</thead>
						<tr>
							<th width="20%">
								学号
							</th>
							<td width="30%">
								${rs.xh }
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								院系
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
							
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								联系电话
							</th>
							<td>
								${rs.sjhm }
								<logic:notEqual value="" name="rs" property="lxdh">
									<logic:notEqual value="" name="rs" property="sjhm">
									/
									</logic:notEqual>
								</logic:notEqual>
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th>原寝室号</th>
							<td>${rs.qsh }</td>
							<th>
								是否吃年夜饭
							</th>
							<td>
								${rs.sfnyf }
							</td>
						</tr>
						<tr>
							<th>
								留校开始时间
							</th>
							<td>
								${rs.kssj }
							</td>
							<th>
								留校结束时间
							</th>
							<td>
								${rs.jssj }
							</td>
						</tr>
						
						<tr>
							<th>
								留校天数
							</th>
							<td>
								${rs.ts }天
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />审核
							</th>
							<td>
								${rs.sh1 }
							</td>
							<th>
								<bean:message key="lable.xb" />审核人
							</th>
							<td>
								${rs.shr1 }
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xb" />审核意见</th>
							<td colspan="3">
								${rs.shyj1 }
							</td>
						</tr>
						<tr>
							<th>
								学校审核
							</th>
							<td>
								${rs.sh2 }
							</td>
							<th>
								学校审核人
							</th>
							<td>
								${rs.shr2 }
							</td>
						</tr>
						<tr>
							<th>学校审核意见</th>
							<td colspan="3">
								${rs.shyj2 }
							</td>
						</tr>
						<tr>
							<th>
								家长联系方式 
							</th>
							<td colspan="3">
								${rs.jzlxfs }
							</td>
						</tr>
						<tr>
							<th>
								留校原因<br/>
							</th>
							<td colspan="3">
								<html:textarea property="qjsy" style="width: 95%;word-break:break-all;" readonly="true"
								 onblur="chLeng(this,200);" rows='5' value="${rs.lxyy}"/>
							</td>
						</tr>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
								  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table> 
			</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
