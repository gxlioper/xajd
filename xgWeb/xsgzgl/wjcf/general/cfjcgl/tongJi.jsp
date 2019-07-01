<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		
		<script type="text/javascript">

		//提交
		function tijiao(){
			confirmInfo("此操作将会提交审核结束的记录，提交后审核不能再修改，是否确定？",function(tag){
				if(tag=="ok"){
					refreshForm('general_wjcf_cfjcsh_ajax.do?method=tjSh');
				}
			});
		}

		
		</script>
	</head>
	<body >
		<html:form action="/general_wjcf_cfjcsh_ajax" method="post">
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">	
					<input type="hidden" name="message" id="message" value="${message }">	
					<input type="hidden" name="doType" id="doType"  >	
					<input type="hidden" name="sfZgj" id="sfZgj"  value="${sfZgj }">	
					<html:hidden property="spgwId" styleId="spgwId" value="${spgw}" />
					<html:hidden property="cflbdm" styleId="cflbdm"  value="${cflbdm}" />
					<html:hidden property="cfId" styleId="cfId"    value="${cfId }" />
					<html:hidden property="primarykey_checkVal" value="${primarykey_checkVal}" />
					<table width="100%" border="0" class="formlist">
					<tbody>
					<thead>
							<tr>
								<th colspan="4">
									<span>审核统计信息</span>
								</th>
							</tr>
						</thead>

						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td ><b>未审核数</b></td>
										<td ><b>退回数</b></td>
										<td ><b>审核不通过数</b></td>
										<td ><b>审核通过数</b></td>
									</tr>
								</thead>
								<tbody align="center">
							<logic:notEmpty name="tongjiList">
								<logic:iterate name="tongjiList" id="s">
										<tr  style="cursor:hand">
										<td >
											${s.wshsl}
										</td>
										<td >
											${s.thsl}
										</td>
										<td >
											${s.btgsl}
										</td>
										<td >
											${s.tgsl}
										</td>
										</tr>
										</logic:iterate>
										</logic:notEmpty>
										</tbody>
								
								</table>
							</td>
						</tr>
							
					</tbody>
						
					<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="btn">
								<button type="button"  id="btn_btg"  onclick="tijiao();return false;" id="buttonSave">
										提交
								</button>
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</table>
				</input>
			</div>
			
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
