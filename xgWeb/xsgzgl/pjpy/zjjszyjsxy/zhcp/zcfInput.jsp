<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function checkImpGs(){
				var flag = false;
				var impFilePath = jQuery("#impFilePath").val();
				
				if(impFilePath != ""){
					var filegs = impFilePath.substring(impFilePath.length-3,impFilePath.length);
					
					if(filegs != "xls"){
						alertError("请选择处理好数据的Excel模板");
					}else{
						flag = true;
					}
				}
				
				return flag;
			}
		
			function impDqfb(){
				refreshForm('zjjs_zhcp_ajax.do?method=inZcf&doType=imp&czxm=${generalPjpyGeneralForm.czxm}');
			}
		</script>
	</head>
	<body>
		<html:form action="/general_pjpy" enctype='multipart/form-data' method="post">
			<div class="tab">
				<table align="center" class="formlist">
					<tbody>
						<tr>
							<th>
								导入文件
							</th>
							<td>
								<input type="file" id="impFilePath" name="uploadFile"
									style="width:300px" contenteditable="false" />
							</td>
						</tr>
						<tr>
							<th>说明</th>
							<td>
								1.请使用本功能模块所提供的<font color="blue">EXCEL模板</font>进行导入，否则可能出现问题。</br>
								2.模板中的其他信息无需修改，您只需要维护需要导入的<font color="blue">分数</font>。</br>
								3.在本系统中已存在某学生的分数，再执行导入，可能会被<font color="blue">覆盖</font>。</br>
								4.如果不想覆盖某学生记录的话，请在模板中删除该学生，切忌，
								&nbsp;&nbsp;仅置空分数<font color="blue">无效</font>。</br>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="if(checkImpGs){impDqfb()}">
										确定
									</button>
									<button type="button" name="取消" onclick="window.close();return false;">
										取 消
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="message">
				<script defer>
					alertInfo('${message}');
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
