<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script type="text/javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<input type="hidden" name="id" value="${rs.id }" />
			<input type="hidden" name="xmid" value="${rs.id }" />
			<input type="hidden" name="shlcid" value="${rs.shlcid }" />
			<input type="hidden" name="shzt" value="${rs.shzt}" />
			<input type="hidden" id="xh"  name="xh" value="${xh}" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									申请信息
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr >
													<td colspan="2">
														科目名称： ${rs.kmmc }
													</td>
													<td>
														核心能力名称： ${rs.hxnlmc }
													</td>
													<td>
														项目类型： ${rs.xmlxmc }
													</td>
												</tr>
												<tr >
													<td colspan="2">
														参与角色：
														<logic:equal value="组织" name="rs" property="cyjs">
															<input type="radio" name="cyjs" value="参与"  disabled="disabled"/>
															参与
															<input type="radio" name="cyjs" value="组织" disabled="disabled"
																checked="true" />
															组织
														</logic:equal>
														<logic:notEqual value="组织" name="rs" property="cyjs">
															<input type="radio" name="cyjs" value="参与" disabled="disabled"
																checked="true" />
															参与
															<input type="radio" name="cyjs" value="组织"  disabled="disabled"/>
															组织
														</logic:notEqual>
													</td>
													<td>
														是否重修：

														<logic:equal value="是" name="rs" property="sfcx">
															<input type="radio" name="sfcx" value="是" disabled="disabled"
																checked="true" />
															是
															<input type="radio" name="sfcx}" value="否" disabled="disabled"/>
															否
														</logic:equal>

														<logic:notEqual value="是" name="rs" property="sfcx">
															<input type="radio" name="sfcx" value="是" disabled="disabled"/>
															是
															<input type="radio" name="sfcx" value="否" disabled="disabled"
																checked="true" />
															否
														</logic:notEqual>
													</td>
													<td>
														人数上限：${rs.rssx }(人)
													</td>
													
												</tr>
												<tr >
													<td align="right" width="10%">
														成果描述
													</td>
													<td align="left" colspan="3" style="word-break:break-all;width:100%">
														${rs.cgms }
													</td>
												</tr>
												<tr >
													<td align="right">
														备注
													</td>
													<td align="left" colspan="3" style="word-break:break-all;width:100%">
														${rs.bz }
													</td>
												</tr>
					</tbody>
				</table>
			</div>
			
		</html:form>
	</body>
</html>
