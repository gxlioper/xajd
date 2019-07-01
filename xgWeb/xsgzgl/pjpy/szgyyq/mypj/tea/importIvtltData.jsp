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
			var filepath = document.getElementById('impFilePath').value;
			document.getElementById('filepath').value = filepath;
			url = '/xgxt/szgyyq_mypj_tea.do?method=importIvtlt&act=import';
			refreshForm(url);
			BatAlert.showTips('数据导入中，请稍等...');
		}

		// 导出导入用模版
		function importModelUpload() {
			document.forms[0].action = "print/gygl/gygl_cwrz.xls";
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
    <div class="tab">
		<table class="formlist" border="0" align="center" style="width: 95%;">	
			<thead>
				<tr>
					<th colspan="2"><span>信息导入</span></th>
				</tr>
			</thead>
		</table>
	</div>
	<div>
	   <table border="0" align="center" style="width: 95%;">
	   <tr>
	   <td>
	    <div class="prompt">
	       <h3><span>系统提示：</span></h3>

	       	     </p>
	<%--       <a class="close" title="隐藏" onclick="hidParent(this);"></a>--%>
	   	</div>
	   	</td>
	   	</tr>
	   	</table>
	 </div>
	
	<div class="tab">
		<table class="formlist" border="0" align="center" style="width: 95%;" >	
			<tbody>
<%--				<tr>--%>
<%--					<th>数据模板下载</th>--%>
<%--					<td>--%>
<%--						<button type="button" onclick="importModelUpload()" class="btn_01">下 载</button>--%>
<%--					</td>--%>
<%--				</tr>--%>
				
				<tr>
					<th>最终数据导入</th>
					<td>
					 <input type="file" id="impFilePath" name="impFilePath"/>
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('impFilePath'))) importSubmit();">
				          	最终数据导入
				          </button>
					</td>
				</tr>
<%--				<tr>--%>
<%--					<th>寝室所属更新方案：</th>--%>
<%--					<td>--%>
<%--						<input type="radio" name="qssxxgfa" />按学生最多的更新--%>
<%--						<input type="radio" name="qssxxgfa" />按最小床位更新--%>
<%--						<input type="radio" name="qssxxgfa" />只更新非混住--%>
<%--					</td>--%>
<%--				</tr>--%>
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
