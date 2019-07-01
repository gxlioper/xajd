<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		
	</head>
	<body>
		<html:form method="post" styleId="smwhForm" action="/dekt_smwhgl"
			enctype="multipart/form-data">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>书本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								年级
							</th>
							<td align="left" >
									${smwhForm.nj }
								</select>
							</td>
							<th align="right">
								书名
							</th>
							<td align="left" >
								${smwhForm.ssm }
							</td>
						</tr>
						<tr>
							<th align="right">
								出版社
							</th>
							<td align="left" >
								${smwhForm.cbs }
							</td>
							<th align="right">
								作者
							</th>
							<td align="left" >
								${smwhForm.author }
							</td>
						</tr>
						<tr>
							<th align="right">
								类型
							</th>
							<td align="left" >
								${smwhForm.lx }
							</td>
							<th align="right">
								电子书链接
							</th>
							<td align="left" >
								${smwhForm.ebook }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
