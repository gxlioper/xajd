<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script	type="text/javascript">
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_byqxgl" method="post" styleId="byqxForm">
			<html:hidden property="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:315px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>毕业去向信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">手机号码</th>
							<td width="30%">
								${rs.sjhm }
							</td>
							<th width="20%">QQ号码</th>
							<td width="30%">
								${rs.qqhm }
							</td>
						</tr>
						<tr>
							<th width="">
							<logic:equal value="70002" name="xxdm">
								实际工作单位
							</logic:equal>
							<logic:notEqual value="70002" name="xxdm">							
								就业单位
							</logic:notEqual>
							</th>
							<td width="">
								${rs.jydw }
							</td>
							<th width="">就业单位性质</th>
							<td width="">
								${rs.jydwxzmc }
							</td>
					    </tr>
						<tr>
							<th width="">派遣单位</th>
							<td width="">
								${rs.pqdw }
							</td>
							<th width="">就业形式</th>
							<td width="">
								${rs.jyxsmc }
							</td>
					    </tr>
					    <!-- 南京高等职业技术学校 -->
					    <logic:equal value="10874" name="xxdm">
					    	<tr>
						    	<th>学历</th>
								<td>
									${rs.xl}	
								</td>
								<th>学位</th>
								<td>
									${rs.xw}
								</td>
						    </tr>
					    </logic:equal>
					    <logic:equal value="70002" name="xxdm">
					    	<tr>
						    	<th>毕业去向</th>
								<td>
									${rs.byqxmc}
								</td>
								<th>就业类别</th>
								<td>
									${rs.jylbmc}
								</td>
						    </tr>
						    <tr>
						    	<th>就业状况</th>
						    	<td>
						    		${rs.jyzkmc}
						    	</td>
						    	<th>报到证号</th>
						    	<td>
						    		${rs.bdzh}
						    	</td>
						    </tr>
						    <tr>
						    	<th>派遣时间</th>
						    	<td>
						    		${rs.pqsj}
						    	</td>
						    	<th>学生档案投递单位</th>
						    	<td>
						    		${rs.tddw}
						    	</td>
						    </tr>
						    <tr>
						    	<th>档案转递时间</th>
						    	<td>${rs.zdsj}</td>
						    	<th></th>
						    	<td></td>
						    </tr>
						    <tr>
						    	<th>备注</th>
						    	<td colspan="3">
						    		${rs.bz}
						    	</td>
						    </tr>
					    </logic:equal>
					</tbody>
				 </table>
				</div>
				<div>
					<table width="100%" border="0" class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" type="button" onclick="iFClose();">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
	</body>
</html>

