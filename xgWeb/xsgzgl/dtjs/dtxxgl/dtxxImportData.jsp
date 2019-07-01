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
			url = 'dtjs_dtxxgl.do?method=importData&act=import';
			refreshForm(url);
			BatAlert.showTips('数据导入中，请稍等...');
		}

		// 导出导入用模版
		function importModelUpload() {
			document.forms[0].action = "dtjs_dtxxgl.do?method=importData&act=mbxz";
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
	    <div class="prompt" id="div_help">
	       <h3><span>提示：</span></h3>
	       <p>符合规范的数据可导入成功，不符合规范的数据将会反馈回来，并有不符合规范原因<br/>
	       	  1、导入内容请按系统提供模板制作，当前阶段代码详见模板中的《阶段代码表》按表中的代码填写<br/>
	       	  2、表中其他字段至少需填写一个阶段的开始时间，否则数据将无法导入成功<br/>
	       	  导入失败的原因有：<br/>
	       	    &nbsp;&nbsp;1).当前阶段代码为空或不存在于系统中<br/>
	       	    &nbsp;&nbsp;2).当前阶段开始时间为空<br/>
	       	    &nbsp;&nbsp;3).导入的学号为空或不存在于系统中<br/>
	       	    &nbsp;&nbsp;4).对应学号学生的信息已存在于党团信息记录中<br/>
	       	    &nbsp;&nbsp;5).学号阶段重复<br/>
	       	    &nbsp;&nbsp;6).时间格式不正确(注：目前系统只支持"yyyy-MM-dd"和"yyyyMMdd"格式)<br/>
	       	    <font color="red">说明：模板下载后一定要将所有的单元格的格式设置成文本格式，否则时间会有问题</font>			
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
				</tr><%--
				<tr>
					<th>请选择更新字段：</th>
					<td>
						<input type="radio" name="drzd" value="qsdh" checked="checked"/>更新寝室电话
						<input type="radio" name="drzd" value="sfbz"/>更新寝室收费标准
					</td>
				</tr>
			--%></tbody>
			
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
