<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function saveZxdkSq(){
			alert(11);
			var url="/xgxt/zxdk_xnmz.do?method=zxdkSq&doType=save";
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="">
		<html:form action="/zxdk_xnmz" method="post">
			<!-- 隐藏域 -->

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>流程跟踪</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td>
								<table  width="100%" >
									<thead>
										<tr>
											<td>
												<div align="center">
												审核岗位
												</div>
											</td>
											<td>
												<div align="center">
												审核时间
												</div>
											</td>
											<td>
												<div align="center">
												审核人
												</div>
											</td>
											<td>
												<div align="center">
												审核结果
												</div>
											</td>
										</tr>
									</thead>
									<tbody>
										<logic:iterate id="s" name="rs">
											<tr>
												<td>
													<div align="center">
													${s.gwmc }
													</div>
												</td>
												<td>
													<div align="center">
													${s.shsj }
													</div>
												</td>
												<td>
													<div align="center">
													${s.shr }
													</div>
												</td>
												<td>
													<div align="center">
													${s.shzt }
													</div>
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>

				</table>
			</div>

		</html:form>
	</body>
</html>
