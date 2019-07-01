<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
  </head>
  <body>
		<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
			<html:hidden property="xqid"/>
			<div class='tab' style='tab;width:100%;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>评价信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%">评价指数</th>
							<td>
								<bean:write name="pjxxModel"  property="pjzsmc"/>
							</td>
						</tr>
						<tr>
							<th width="25%">评语</th>
							<td>
								<bean:write name="pjxxModel"  property="py"/>
							</td>
						</tr>
						<tr>
							<th width="25%">评价时间</th>
							<td><bean:write name="pjxxModel"  property="pjsj"/></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
