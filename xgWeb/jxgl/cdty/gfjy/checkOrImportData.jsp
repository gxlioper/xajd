<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
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
	// ����������ģ��
	function stencilExport() {
		document.forms[0].action = "cdtyGfjy.do?method=stencil";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	
	
	/*
	���ݵ�����
	*/
	function importCheck(){
		var filepath = DWRUtil.byId('checkFilePath').value;
		document.getElementById('filepath').value = filepath;
		document.forms[0].action="cdtyGfjy.do?method=showCheckData";
		document.forms[0].submit();	
	}
	
	/*
	�������ݵ���
	*/
	function importSubmit(){
		var filepath = document.getElementById('impFilePath').value;
		document.getElementById('filepath').value = filepath;
		
		refreshForm("cdtyGfjy.do?method=showImportData");
		//showTopWin("/xgxt/impAndChkData.do?method=showImportData");
	}	
	
	//�Զ��嵼��
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
					<th colspan="2"><span>��Ϣ����</span></th>
				</tr>
			</thead>
		</table>
	</div>
	<div>
	   <table border="0" align="center" style="width: 95%;">
	   <tr>
	   <td>
	    <div class="prompt">
	       <h3><span>��ʾ��</span></h3>
	       <p>
	       	     ����������ݼ�⣬Ҳ����ֱ�ӵ������ݡ�<br/>
	       	     ��ɫ<font color="red">���</font>�ֶ�Ϊ����Ϊ�յ��ֶΣ���ע�⣡</p>
	<%--       <a class="close" title="����" onclick="hidParent(this);"></a>--%>
	   	</div>
	   	</td>
	   	</tr>
	   	</table>
	 </div>
	
	<div class="tab">
		<table class="formlist" border="0" align="center" style="width: 95%;" >	
			<tbody>
				<tr>
					<th>����ģ������</th>
					<td>
						<button type="button" onclick="stencilExport()" class="btn_01">�� ��</button>
					</td>
				</tr>
				
<%--				<tr>--%>
<%--					<th>��Ϣ������</th>--%>
<%--					<td>--%>
<%--						<input type="file" id="checkFilePath" name="checkFilePath"/>--%>
<%--						<button type="button" class="btn_01" onclick="if(checkFileFormat(DWRUtil.byId('checkFilePath'))) importCheck();">���ݵ�����</button>--%>
<%--					</td>--%>
<%--					--%>
<%--				</tr>--%>
				
				<tr>
					<th>�������ݵ���</th>
					<td>
					 <input type="file" id="impFilePath" name="impFilePath"/>
					 <logic:notEqual value="" name="dzyDdTab">
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('impFilePath'))) importSubmitZdy();">
				          	�������ݵ���
				          </button>
			          </logic:notEqual>
			          <logic:equal value="" name="dzyDdTab">
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('impFilePath'))) importSubmit();">
				          	�������ݵ���
				          </button>
			          </logic:equal>
					</td>
				</tr>
				
				<tr>
					<th>����ģʽ</th>
					<td>
						<html:radio property="drms" value="1" styleId="drms">����������</html:radio>
						<html:radio property="drms" value="0">���ݸ���</html:radio>
					</td>
				</tr>
			</tbody>
			
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn">
		            <button type="button" id="btn_clo" onclick="Close();return false;">
						�� ��
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
