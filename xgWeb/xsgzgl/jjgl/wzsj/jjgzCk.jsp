<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
  </head>
  
  <body>
		<html:form action="/jjgl_jjgzgl" method="post" styleId="jjglJJgzForm">
			<html:hidden property="sid"/>
			<div class='tab' style='tab;width:100%;height:350px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>查看</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th  width="20%">家教规则名称</th>
					    	<td colspan="3">
					    		${modelMap.gzmc}
					    	</td>
					    </tr>
					     <tr>
					    	<th  width="20%">家教规则内容</th>
					    	<td colspan="3">
					    		${modelMap.gznr}
					    	</td>
					    </tr>
					     <tr>
					    	<th>是否发布</th>
					    	<td colspan="3">
					    		${modelMap.sffb == '1' ? '已发布' : '未发布'}
					    	</td>
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
