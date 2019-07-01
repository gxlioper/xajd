<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/zjdx/cjff/js/cjff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
	</head>
	<body>
		<html:form action="/cjff_zjdx" method="post" styleId="CjffForm">
		<input type="hidden" name="sxsz" id="sxsz" value="${sxsz}" />
		<input type="hidden" name="cjbz" id="cjbz" value="${cjbz}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/qgzx/zjdx/cjff/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>酬金发放信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>发放年月</th>
							<td>
								${bdxxMap.ffndyf}								
							</td>
							<th>用人单位</th>
							<td>
								${bdxxMap.yrdwmc}
							</td>
						</tr>
						<tr>
							<th>岗位性质</th>
							<td>
								${bdxxMap.gwxzmc}
							</td>
							<th>岗位类别</th>
							<td>
								${bdxxMap.gwlbmc}
							</td>
						</tr>
						<tr>
							<th>校区</th>
							<td>
								${bdxxMap.xqmc}
							</td>
							<th>酬金(元)</th>
							<td>
								${bdxxMap.bcje}
							</td>
						</tr>
						<tr>
							<th>工时(小时)</th>
							<td >
								${bdxxMap.gss}
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								工作内容
							</th>
							<td colspan="3">
								${bdxxMap.gznr}
							</td>
						</tr>
						<tr>
							<th>备注
							</th>
							<td colspan="3">
								${bdxxMap.bz}
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
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