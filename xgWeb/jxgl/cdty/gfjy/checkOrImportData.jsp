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
	
	<script language="javascript" >
	// 导出导入用模版
	function stencilExport() {
		document.forms[0].action = "cdtyGfjy.do?method=stencil";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	
	
	/*
	数据导入检测
	*/
	function importCheck(){
		var filepath = DWRUtil.byId('checkFilePath').value;
		document.getElementById('filepath').value = filepath;
		document.forms[0].action="cdtyGfjy.do?method=showCheckData";
		document.forms[0].submit();	
	}
	
	/*
	最终数据导入
	*/
	function importSubmit(){
		var filepath = document.getElementById('impFilePath').value;
		document.getElementById('filepath').value = filepath;
		
		refreshForm("cdtyGfjy.do?method=showImportData");
		//showTopWin("/xgxt/impAndChkData.do?method=showImportData");
	}	
	
	//自定义导入
	function importSubmitZdy(){
		var filepath = document.getElementById('impFilePath').value;
		document.getElementById('filepath').value = filepath;
		
		refreshForm("cdtyGfjy.do?method=showZdyImportData");
		//showTopWin("/xgxt/impAndChkData.do?method=showImportData");
	}
	
	</script>
</head>
	<body onload="document.getElementById('drms').checked=true;" >	
    <html:form action="/impAndChkData.do" enctype='multipart/form-data' method="post">
    <input type="hidden" name="filepath" id='filepath' value="${filepath}"/>
    <input type="hidden" name="flag" id='flag' value="${ flag }" />
    <input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
    <input type="hidden" name="dzyDdTab" id="dzyDdTab" value="${dzyDdTab}"/> 
    <input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
    <input type="hidden" name="drnj" id="drnj" value="${drnj}"/>
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
	       <h3><span>提示：</span></h3>
	       <p>
	       	     如果不做数据检测，也可以直接导入数据。<br/>
	       	     底色<font color="red">标红</font>字段为不可为空的字段，请注意！</p>
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
						<button type="button" onclick="stencilExport()" class="btn_01">下 载</button>
					</td>
				</tr>
				
<%--				<tr>--%>
<%--					<th>信息导入检测</th>--%>
<%--					<td>--%>
<%--						<input type="file" id="checkFilePath" name="checkFilePath"/>--%>
<%--						<button type="button" class="btn_01" onclick="if(checkFileFormat(DWRUtil.byId('checkFilePath'))) importCheck();">数据导入检测</button>--%>
<%--					</td>--%>
<%--					--%>
<%--				</tr>--%>
				
				<tr>
					<th>最终数据导入</th>
					<td>
					 <input type="file" id="impFilePath" name="impFilePath"/>
					 <logic:notEqual value="" name="dzyDdTab">
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('impFilePath'))) importSubmitZdy();">
				          	最终数据导入
				          </button>
			          </logic:notEqual>
			          <logic:equal value="" name="dzyDdTab">
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('impFilePath'))) importSubmit();">
				          	最终数据导入
				          </button>
			          </logic:equal>
					</td>
				</tr>
				
				<tr>
					<th>导入模式</th>
					<td>
						<html:radio property="drms" value="1" styleId="drms">导入新数据</html:radio>
						<html:radio property="drms" value="0">数据更新</html:radio>
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
  </body>
</html>
