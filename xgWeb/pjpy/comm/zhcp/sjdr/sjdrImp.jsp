<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	
	<style type="text/css">
	<!--
		#formlist th{width:30%}
		#formlist td{width:70%}
		.impFilePath{width:60%}
	-->
	</style>
	
	<script language="javascript" defer="defer">
	function impZcf(tag){
	
			
		$("filePath").value = $("impFilePath").value;
		
		if($("filePath").value == ""){
			alertInfo("请选择你需要导入的文件！");
			return false;
		}
		
		if(tag=='ok'){
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			refreshForm('/xgxt/zhcpSjdr.do?method=sjdrImp&doType=imp','');
		}
	}
	
	function checkImpGs(){
		var flag = false;
		var impFilePath = $("impFilePath").value;
		
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
	</script>
</head>
	<body onload="" >	
	
    <html:form action="/zhcpSjdr" enctype='multipart/form-data' method="post">
	
	<div class="tab">
		<table class="formlist" border="0" align="center" style="width: 95%;" >	
			<thead>
				<tr>
					<td colspan="2"><span>信息导入</span></td>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<th>文件选择</th>
					<td>
						<input type="file" 	id="impFilePath" name="impFilePath" style="width:300px" contenteditable="false"/>
						<input type="hidden" id="filePath"/>
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
		          <div class="btn">
		          	<button type="button" id="btn_save" onclick="if(checkImpGs()){confirmInfo('请确认是否要执行导入操作',impZcf)};">
						确 定
					</button>
					
		            <button type="button" id="btn_clo" onclick="Close();return false;">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
		
		<!-- 提示信息 -->
		<logic:present name="message">
			<script defer="defer">
				if($("message") && $("message").value != ""){
				
					alertInfo($("message").value);
					
					$("message").value = "";
					$("doType").value = "";
		
					if($("importResult")){
						$("importResult").value = "";
					}
				}
			</script>
		</logic:present>
		
		<!-- 等待ing -->
		<div id="divWaiting" style="display: none; z-index: 2100; left: 25%; right: 25%; position: absolute;
		     text-align: center; width: 50%; height: 50px;left: expression((this.offsetParent.clientWidth/2)-(this.clientWidth/2)+this.offsetParent.scrollLeft);
		     top: expression((this.offsetParent.clientHeight/2)-(this.clientHeight/2)+this.offsetParent.scrollTop);">
		   <img src="<%=stylePath%>images/ico_loading.gif"/>
		   <p id="p_tsxx"><B>数据处理中，请稍后。。。</B></p>
		</div>
		
		<div id="divDisable" style="display: none;width:expression(document.body.offsetWidth);height:expression(document.body.offsetHeight); z-index: 2000; position: absolute;left: 0px; top: 0px;filter:alpha(opacity=50); background-color:White"></div>
				
    </html:form>
  </body>
</html>
