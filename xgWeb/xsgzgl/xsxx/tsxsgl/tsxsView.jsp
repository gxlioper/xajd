<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<style type="text/css">	
			.demo_data2 {
			 	/*border: 1px solid #DEDEDE;*/
				display: inline;
			    float: left;
			    height: 15px;
			    margin: 0px 0px 0;
			    padding: 0px;
			    width: 160px;
			}
		</style>
	</head>
	<body>
		<html:form action="/tsxs_tsxswh" method="post" styleId="TsxsglForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<tbody id="tbody_jbxx">
					<tr>
					<th width="16%">
						学生类型
					</th>
						
							<td width="84%" colspan="3">
								${rs.xslxmc }
							</td>
					</tr>
						<tr>
							<th>
								<span class="red">*</span>关注状态
							</th>
							<td colspan="3">
								${rs.gzztmc}
								
							</td>
						</tr>
						<tr>
						    <th>
								处理措施
							</th>
							<td colspan="3">
								${rs.clcs} 
							</td>
						</tr>
						<tr>
						    <th>
								备注
							</th>
							<td colspan="3">
								${rs.bz} 
							</td>
						</tr>
						
						
					</tbody>
				</table>
			</div>	
				 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>

		</html:form>
	</body>
</html>

