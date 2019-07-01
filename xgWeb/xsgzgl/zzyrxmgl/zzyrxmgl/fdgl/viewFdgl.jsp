<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglfdgl" method="post" styleId="fdglForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>辅导信息</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th width="20%">助教姓名</th>
						<td width="30%">${rs.fdrxm }</td>
						<th width="20%">助教联系电话</th>
						<td width="30%">${rs.fdrlxdh }</td>
					</tr>
					<tr>
						<th>学员姓名</th>
						<td>${rs.bfdrxm }</td>
						<th>学员联系电话</th>
						<td>${rs.bfdrlxdh }</td>
					</tr>
					<tr>
						<th>辅导科目</th>
						<td colspan="3">${rs.fdkm }</td>
					</tr>
					</table>
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>辅导记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="30%">
								辅导日期
							</td>
							<td width="40%">
								辅导内容
							</td>
							<td width="30%">
								备注
							</td>
						</tr>
						<logic:iterate name="fdjlList" id="f">
						<tr>
							<td>${f.fdrq }
							</td>
							<td>${f.fdnr }
							</td>
							<td>${f.fdbz }
							</td>
			      		</tr>
			      		</logic:iterate>
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

