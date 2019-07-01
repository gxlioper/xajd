<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script language="javascript" src="comm/editor/kindeditor.js"></script>
	<script language="javascript" src="comm/editor/zh_CN.js"></script>
	<script language="javascript" src="comm/editor/editor.js"></script>
	<link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
	<link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
  </head>
  
  <body>
		<html:form action="/jjgl_tzgggl" method="post" styleId="jjglTzggForm">
			<div class='tab' style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>֪ͨ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th  width="20%">����</th>
					    	<td colspan="3">
					    		${modelMap.title}
					    	</td>
					    </tr>
					    <tr>
					    	<th>���ȼ�</th>
					    	<td>
					    		${modelMap.prioritymc}
					    	</td>
					    	<th>����״̬</th>
					    	<td>
					    		${modelMap.sffbmc}
					    	</td>
					    </tr>
					    <tr>
					    	<th>������</th>
					    	<td>
					    		${modelMap.yhm}
					    	</td>
					    	<th>����ʱ��</th>
					    	<td>
					    		${modelMap.publishdate}
					    	</td>
					    </tr>
					    <tr>
					    	<th>����</th>
					    	<td colspan="3">
					    		<textarea id="editorid" name="contents" style="width:700px;height:280px;" disabled="disabled">
					    			${modelMap.contents}
					    		</textarea>
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
									<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
										�ر�
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
