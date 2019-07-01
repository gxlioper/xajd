<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/xskqgl/js/xskqgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		   jQuery(function(){
			   var kqlb = jQuery("#kqlbmc").text();
			   if("病假"==trim(kqlb)){
				   jQuery("#noneOne").css({"display":""});
				   jQuery("#noneTwo").css({"display":""});
			   }else{
				   jQuery("#noneOne").css({"display":"none"});
				   jQuery("#noneTwo").css({"display":"none"});
			   }
		   });
		</script>
		
	</head>
		
	<body>
		
		<html:form action="/rcsw_kqgl_kqgljg" method="post" styleId="KqgljgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生基本信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>考勤信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>考勤日期</th>
							<td>
								${KqgljgForm.kqrq }
							</td>
							<th>考勤类别</th>
							<td id="kqlbmc">
								${KqgljgForm.kqlbmc }

							</td>
						</tr>
						<tr>
							<th>缺勤天数(天)</th>
							<td>
								${KqgljgForm.qqts }
							</td>
					    
							<th>缺课节数</th>
							<td>
								${KqgljgForm.kkjs }
							</td>
						</tr>
						<tr id="noneOne" style="display:none;">
							<th>缺课疾病类别</th>
							<td>
								${KqgljgForm.qkjblbmc }
							</td>
					    
							<th>因病缺课疾病名称</th>
							<td>
								${KqgljgForm.ybqkjbmc }
							</td>
						</tr>
					    <tr id="noneTwo" style="display:none;">
							<th>
								目前状况
								<br />
							</th>
							<td colspan="3">
								${KqgljgForm.dqztmc }
							</td>
			      		</tr>
			      		<tr height="100px;">
							<th>
								备注
								<br />
							</th>
							<td colspan="3">
								${KqgljgForm.bz }
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
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
		</html:form>
	</body>
</html>

