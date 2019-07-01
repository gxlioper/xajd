<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function save(){
			var url="/xgxt/xtwhSpgw.do?method=spgwManage&doType=save";
			refreshForm(url);
		}
		function modi(){
			var url="/xgxt/xtwhSpgw.do?method=spgwManage&doType=modi";
			refreshForm(url);
		}
		</script>
	</head>
	<body >
		<html:form action="/xtwhSpgw" method="post">
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
                                    <span>审批岗位信息</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
                                	<html:hidden property="spgw" styleId="spgw"/>
									<div class="btn">
										<logic:equal name="doType" value="add">
										<button class="button2" onclick="save();return false;">
											保存
										</button>
										</logic:equal>
										<logic:equal name="doType" value="update">
                                        <html:hidden name="rs" property="id" styleId="id" />
										<button class="button2" onclick="modi()">
											保存
										</button>
										</logic:equal>
										<button class="button2" onclick="Close();return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
                        <tbody>
							<tr>
							<th>
								<font color="red">*</font>名 称
							</th>
							<td coalpsn="3">
								<html:text name="rs" property="mc" styleId="mc" />
							</td>
							</tr>
							<tr>
							<th>
								组织
							</th>
							<td coalpsn="3">
                                <html:text name="rs" property="zzjs" styleId="zzjs" />
							</td>
							</tr>
							<tr>
							<th>
								描述
							</th>
							<td coalpsn="3">
                                <html:textarea name="rs" property="ms" styleId="ms" rows="9" cols="50" />
							</td>
							</tr>
						</tbody>
					</table>
				</div>


			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("操作成功！");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form></body>
</html>