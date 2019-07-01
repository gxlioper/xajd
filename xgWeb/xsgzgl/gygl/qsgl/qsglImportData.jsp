<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript">
		//最终数据导入
		function importSubmit(){
			var drzd=document.getElementsByName("drzd");
			if(drzd[0].checked){
				confirmInfo("确定更新寝室电话吗？",function(ok){if(ok=="ok"){importSubmit_end()}});
			}else if(drzd[1].checked){
				confirmInfo("确定更新寝室收费标准吗？",function(ok){if(ok=="ok"){importSubmit_end()}});
			}else if(drzd[2].checked){
				confirmInfo("确定更新寝室有无空调吗？",function(ok){if(ok=="ok"){importSubmit_end()}});
			}else{
				alertInfo("请选择更新字段！");
				return false;	
			}
		}
		function importSubmit_end(){
			
			var filepath = document.getElementById('impFilePath').value;
			document.getElementById('filepath').value = filepath;
			url = 'gyglnew_qsgl.do?method=importData&act=import';
			refreshForm(url);
			BatAlert.showTips('数据导入中，请稍等...');
		}

		// 导出导入用模版
		function importModelUpload() {
			document.forms[0].action = "print/gygl/gygl_qsgl.xls";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
	<style type="text/css">
	<!--
		#formlist th{width:30%}
		#formlist td{width:70%}
		.impFilePath{width:60%}
	-->
	</style>
</head>
	<body>	
    <html:form action="/impAndChkData.do" enctype='multipart/form-data' method="post">
    <input type="hidden" name="filepath" id='filepath' value="${filepath}"/>
    <input type="hidden" name="flag" id='flag' value="${ flag }" />
    <input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
    <input type="hidden" name="dzyDdTab" id="dzyDdTab" value="${dzyDdTab}"/> 
    <input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
	    <div class="prompt">
	       <h3><span>提示：</span></h3>
	       <p>符合规范的数据可导入成功，不符合规范的数据将会反馈回来，并有不符合规范原因<br/>
	       	     底色<font color="red">标红</font>字段为不可为空的字段，请注意！</br>
	       	  导入失败的原因有：
	       	    1.系统中无对应的数据

	       	     </p>
	   	</div>
	<div style='width:100%;overflow:hidden;overflow-x:hidden'>
		<table class="formlist" >	
			<thead>
				<tr>
					<th colspan="2">
						<span>信息更新</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>数据模板下载</th>
					<td>
						<button type="button" onclick="importModelUpload()" class="btn_01">下 载</button>
					</td>
				</tr>
				
				<tr>
					<th>最终数据导入</th>
					<td>
					 <input type="file" id="impFilePath" name="impFilePath"/>
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('impFilePath'))) importSubmit();">
				          	最终数据导入
				          </button>
					</td>
				</tr>
				<tr>
					<th>请选择更新字段：</th>
					<td>
						<input type="radio" name="drzd" value="qsdh" checked="checked"/>更新寝室电话
						<input type="radio" name="drzd" value="sfbz"/>更新寝室收费标准
						<input type="radio" name="drzd" value="ywkt"/>更新寝室有无空调
					</td>
				</tr>
			</tbody>
			
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn">
		            <button type="button" id="btn_clo" onclick="Close();return false;">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
    </html:form>
    <logic:present name="back">
    	<script>
    		alertInfo("${back}");
    	</script>
    </logic:present>
  </body>
</html>
