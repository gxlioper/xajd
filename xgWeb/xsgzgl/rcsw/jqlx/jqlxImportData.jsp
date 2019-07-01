<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript">
		//最终数据导入
		function importSubmit(){
			var filepath = document.getElementById('impFilePath').value;
			document.getElementById('filepath').value = filepath;
			url = 'rcsw_jqlx.do?method=importData&act=import';
			refreshForm(url);
			BatAlert.showTips('数据导入中，请稍等...');
		}

		// 导出导入用模版
		function importModelUpload() {		
			if("10344"==jQuery("#xxdm").val()){
				document.forms[0].action = "print/rcsw/rcsw_jqlx_10344.xls";
			}else{
				document.forms[0].action = "print/rcsw/rcsw_jqlx.xls";
			}
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			/*
			var url = "rcsw_jqlx.do?method=downloadXls";
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
			*/
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
    <html:form action="/rcsw_jqlx" enctype='multipart/form-data' method="post">
    <input type="hidden" name="filepath" id='filepath' value="${filepath}"/>
    <input type="hidden" name="flag" id='flag' value="${ flag }" />
    <input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
    <input type="hidden" name="dzyDdTab" id="dzyDdTab" value="${dzyDdTab}"/> 
    <input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
    <input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/> 
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
	       <p>符合规范的数据可导入成功，不符合规范的数据将会反馈回来，并有不符合规范原因<br/>
	       	     底色<font color="red">标红</font>字段为不可为空的字段，请注意！<br/>
	       	  导入失败的原因有：
				1.导入数据字段有空值;
	       	    2.导入的学号学生不存在;
				3.导入时间格式不规范;
				4.留校开始时间、留校截止时间必须在系统设置的学生留校起止时间内;
				5.导入的床位不存在;
				6.导入的床位为保留床位;
				7.导入非当前学年学期的数据;
				8.导入的学生性别与床位的寝室性别不同;
				9.导入的学号重复;
				10.导入的床位重复;

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
