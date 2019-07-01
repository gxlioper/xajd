<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>

		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjgwh/js/knsjgwh.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form action="/xszz_knsdcwh" styleId="knsjgwhForm" method="post">
			<div class="tab"
				style="overflow-x: hidden; overflow-y: auto; height: 380px; margin-bottom: 0px;">
				<table class="formlist" width="95%">
				<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>学生调查信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="16%">
								年度
							</th>
							<td align="left" width="30%" nowrap="nowrap">
								${rs.nd }
							</td>
							<th width="16%">
								总分	
							</th>
							<td width="38%">
								${rs.zf}
							</td>
						</tr>
						<tr>
							<th width="16%" rowspan="4">
								状态	
							</th>
							<td width="34%" colspan="3" rowspan="4">
								${rs.zt}
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
