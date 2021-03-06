<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript">
		//照片导出层
		jQuery(function(){
			jQuery('#btn_dc').click(function(){
				tipsWindown("系统提示","id:zpdcDiv","500","300","true","","true","id");
			});
			
		})
		
		//照片导出
		function zpdc(){
			var photoNameType=$("photoNameType").value;
			var zpType=jQuery("input[name=zpType]").val();
			var api = frameElement.api,w = api.opener;
			w.exportZpxx(photoNameType,zpType);
		}
		</script>
	</head>
			<html:form action="/xtwhZpgl" method="post">
				<input type="hidden" id="xxdm" value="${xxdm}"/>
				<div id="zpdcDiv">	
					<div class="open_win01">
						<table align="center" class="formlist">
							<div id="xxPrompt" class="prompt">
								<h3>
									<span>提示：</span>
								</h3>
								<p >
									若学生信息中所选字段内容为空，则照片的导出命名方式默认为“学号”。 
								</p>
								<a class="close" title="隐藏"
									onclick="this.parentNode.style.display='none';"></a>
							</div>
							<tbody>
								<tr>
									<th>
										导出照片的命名方式
									</th>
									<td>
										<html:select property="photoNameType" styleId="photoNameType" style="width:120px">
											<logic:equal value="12688" name="xxdm">
												<html:option value="sfzh">身份证号</html:option>
											</logic:equal>
											<logic:notEqual value="12688" name="xxdm">										
											 	<html:option value="zd30-xm">医保号+姓名</html:option>
											</logic:notEqual>
										</html:select>
									</td>
								</tr>
								<input type="hidden" name="zpType" value="xs_zs" />
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="btn">
											<button type="button" name="导出"
												onclick="zpdc()">
												导 出
											</button>
											<button type="button" name="取消" onclick="iFClose();return false;">
												取 消
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				</html:form>
				</html>