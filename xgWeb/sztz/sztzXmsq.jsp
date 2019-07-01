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
		<%--function saveXssq(){
			confirmInfo("申请后无法撤销，确定申请吗？",function(t){
				if (t=='ok'){
					
						var id = jQuery('input[name=id]').val();
					var xmid = jQuery('input[name=xmid]').val();
					var shlcid = jQuery('input[name=shlcid]').val();
					var cyjs = jQuery('input[type=radio]:checked[name=cyjs]').val();
					var sfcx = jQuery('input[type=radio]:checked[name=sfcx]').val();
					var cgms = jQuery('textarea[name=cgms]').val();
					var bz = jQuery('textarea[name=bz]').val();
					var shzt = jQuery('input[name=shzt]').val();
					
					var sztzModel = {
						id:id,
						xh:jQuery('#xh').val(),
						xmid:xmid,
						cyjs:encodeURI(cyjs),
						sfcx:encodeURI(sfcx),
						cgms:encodeURI(cgms),
						bz:encodeURI(bz),
						shlcid:shlcid,
						shzt:encodeURI(shzt)
					}
					
					jQuery.post('sztz.do?method=xmsqAjaxSave',sztzModel,function(data){
						jQuery('input[name=id]').val(data.id);
						alertInfo(data.message);
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					},'json')
					
				}
			});	
		}--%>
		
		</script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<input type="hidden" name="id" value="${rs.id }" />
			<input type="hidden" name="xmid" value="${rs.xmid }" />
			<input type="hidden" name="shlcid" value="${rs.shlcid }" />
			<input type="hidden" id="xh"  name="xh" value="${xh}" />
			<input type="hidden" id="shzt"  name="shzt" value="${rs.shzt}" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="saveUpdate('sztz.do?method=xsxmsqSave','xh-xmid')">
										保 存
									</button>
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
															<input type="radio" name="cyjs" value="参与" />
															参与
															<input type="radio" name="cyjs" value="组织"
																checked="true" />
															组织
														</logic:equal>
														<logic:notEqual value="组织" name="rs" property="cyjs">
															<input type="radio" name="cyjs" value="参与"
																checked="true" />
															参与
															<input type="radio" name="cyjs" value="组织" />
															组织
														</logic:notEqual>
													</td>
													<td>
														是否重修：

														<logic:equal value="是" name="rs" property="sfcx">
															<input type="radio" name="sfcx" value="是"
																checked="true" />
															是
															<input type="radio" name="sfcx}" value="否" />
															否
														</logic:equal>

														<logic:notEqual value="是" name="rs" property="sfcx">
															<input type="radio" name="sfcx" value="是" />
															是
															<input type="radio" name="sfcx" value="否"
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
													<td style="word-break:break-all;" colspan="3">
														<html:textarea property="cgms" style="width:100%" rows="3"
															onblur="checkLen(this,400)" value="${rs.cgms }"></html:textarea>
													</td>
												</tr>
												<tr >
													<td align="right">
														备注
													</td>
													<td style="word-break:break-all;" colspan="3">
														<html:textarea property="bz" style="width:100%" rows="3"
															onblur="checkLen(this,400)" value="${rs.bz }"></html:textarea>
													</td>
												</tr>
					</tbody>
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>

				ymPrompt.alert({message:'${message}',handler:function(m){
					if ('ok' == m){
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				},maskAlpha:0.01
			});
					
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
