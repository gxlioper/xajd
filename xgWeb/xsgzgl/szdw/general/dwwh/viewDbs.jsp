<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type="text/javascript">
		var DCCLBH = "szdw_dbstu.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, jgExportData);
		}

		//导出方法
		function jgExportData() {
			var url = "szdw_dwwh.do?method=exportDbStu&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>

	</head>
	<body>

		<html:form action="/general_szdw" method="post">
			<input type="hidden" name="zgh" id="zgh" value="${zgh}" />
			<input type="hidden" name="lx" id="lx" value="${lx}" />
			<div style=''>
				<table width="100%" border="0" class="formlist">
				
					<logic:equal value="13871" name="xxdm">
						<thead>
						<tr>
							<th colspan="5">
								<span>带班信息</span>
								<button type="button" type="button" onclick="exportConfig();">
										带班学生导出
								</button>
							</th>
							
						</tr>
						</thead>
					</logic:equal>
					<tbody>
						<tr colspan="5" >
							<th width="25%">
								职工号
							</th>
							<td width="25%" id="zgh">
								${map.zgh}
							</td>
							<th width="25%">
								姓名
							</th>
							<td width="25%" colspan="2">
								${map.xm}
							</td>
						</tr>

						<tr colspan="5">
							<th width="25%">
								所属部门
							</th>
							<td width="25%">
								${map.bmmc}
							</td>
							<th width="25%">
								${lxmc}带班数 
							</th>
							<td width="25%" colspan="2">
								${map.num}
							</td>
						</tr>
						<logic:equal name = "xxdm" value = "14008">
							<tr colspan="5">
								<th width="25%">
									班长姓名
								</th>
								<td width="25%">
									${map.bzxm}
								</td>
								<th width="25%">
									班长联系电话 
								</th>
								<td width="25%" colspan="2">
									${map.bzlxdh}
								</td>
						</tr>
						</logic:equal>
									
						<tr colspan="5">
							<th width="20%" >
							<div align='center'>
								年级
							</div>	
							</th>
							<th width="20%" >
							<div align='center'>
								院系
							</div>	
							</th>
							<th width="20%" >
							<div align='center'>
								专业
							</div>	
							</th>
							<th width="20%" >
							<div align='center'>
								班级
							</div>
							</th>
							<th width="20%" >
							<div align='center'>
								人数
							</div>
							</th>
						</tr>
								
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s">
								<tr colspan="5">
									<!-- 显示信息 -->
									<logic:iterate id="v" name="s" offset="0" length="5">
										<td align="center"  width="20%">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
				
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5">
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

