<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/xmwh.js"></script>
		<style>
			#xmList tr{ height:40px; }	
			#xmList td{ width:100px; }	
		</style>
	</head>
	<body>
		<html:form action="/zhf_jfxmwh" method="post" styleId="myForm">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					请输入需要兼得的计分项目，以逗号分隔。
					<br/>
					<span>
					<font color="red">注意：兼得的计分项目名称在统计查询时，只会按最高分取值。</font>
					</span>
				</p>
			</div>
				<div style="width:100%;height:140px;overflow-x:hidden;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
						<tbody>
							<tr>
								<th width="80px">
									兼得设置
								</th>
								<td colspan="3">
									<html:textarea property="jdszContent" rows="6" styleId="jdszContent" style="width:99%">
									
									</html:textarea>
								</td>								
							</tr>
						</tbody>
					</table>
				</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="saveBtn" onclick="saveJdsz();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
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

