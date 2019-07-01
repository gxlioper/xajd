<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>

		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xfjs/js/xfjs.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form action="/szdw_xfjsgl" styleId="xfjsForm" method="post">
			<div class="tab"
				style="overflow-x: hidden; overflow-y: auto; height: 380px; margin-bottom: 0px;">
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>学风考勤情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="16%">
								学年
							</th>
							<td align="left" width="30%" nowrap="nowrap">
								${rs.xn }
							</td>
							<th width="16%">
								学期
							</th>
							<td width="38%">
								${rs.xqmc }
							</td>
						</tr>

						<tr>
							<th width="16%">
								班级
							</th>
							<td width="34%">
								${rs.bjmc }
							</td>
							<th width="16%">
								录入人
							</th>
							<td width="34%">
								${rs.lrr}
							</td>

						</tr>
						<tr>
							<th width="16%">
								学院		
							</th>
							<td width="34%" >
								${rs.xymc}
							</td>
							<th width="16%">
								校区		
							</th>
							<td width="34%" >
								${rs.yxmc}
							</td>
						</tr>
						<tr>
						</td>
							<th width="16%">
								学生类型	
							</th>
							<td width="34%" >
								${rs.pyccmc}
							</td>
							<th width="16%">
								辅导员	
							</th>
							<td width="34%">
								${rs.fdy}
							</td>
						</tr>
						<tr>
							<th width="16%">
								应到学生人数
							</th>
							<td width="34%">
								${rs.ydxsrs }
							</td>
							<th width="16%">
								实际出勤人数
							</th>
							<td width="34%">
								${rs.sjcqrs }
							</td>

						</tr>
						<tr>
							<th width="16%">
								检查时间
							</th>
							<td width="34%">
								${rs.jcsj }
							</td>
							<th width="16%">
								检查节次
							</th>
							<td width="34%">
								${rs.jcjc }
							</td>
						</tr>
						<tr>
							<th width="16%" rowspan="4">
								备注
							</th>
							<td width="34%" colspan="3" rowspan="4">
								${rs.bz }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table class="formlist" width="97%">
				<tfoot>
				<tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
				</tfoot>
			</table>

		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
		 showAlert("操作成功",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		</script>
		</logic:present>
	</body>
</html>
