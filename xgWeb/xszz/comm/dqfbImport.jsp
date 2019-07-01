<%@ page language="java" pageEncoding="GBK"%>

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
	
		function impDqfb(tag){
			jQuery("#filePath").val(jQuery("#impFilePath").val());
			
			if(jQuery("#filePath").val() == ""){
				alertInfo("请选择你需要导入的文件！");
				return false;
			}
			
			if(tag=='ok'){
				refreshForm('commXszz.do?method=impDqfbMb');
			}
		}
	</script>
  </head>
  <body>
  	<html:form action="/commXszz" enctype='multipart/form-data' method="post">
	  	<div class="tab">
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="2">
						  <div class="btn">
				          	<button type="button" id="btn_save" onclick="if(checkImpGs()){confirmInfo('请确认是否要执行导入操作',impDqfb)};">
								确 定
							</button>
				            <button type="button" id="btn_clo" onclick="window.close();return false;">
								关 闭
							</button>
				          </div>
						</td>
					</tr>
				</tfoot>
				
				<thead>
					<tr>
						<th colspan="2">
							<span>地区分布导入</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							模版
						</th>
						<td>
							<button type="button" class="btn_01" onclick="document.location.href='/xgxt/commXszz.do?method=downloadMb&doType=dow'">点击下载</button>
						</td>
					</tr>
					<tr>
						<th>导入</th>
						<td>
							<input type="hidden" id="filePath"/>
							<input type="file" 	id="impFilePath" name="uploadFile" style="width:300px" contenteditable="false"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<logic:present name="message">
			<script type="text/javascript">
				alertInfo('${message }');
			</script>
		</logic:present>
		
	</html:form>
  </body>
</html>
