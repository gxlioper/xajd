<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript">
		//�������ݵ���
		function importSubmit(){
			
			var filepath = document.getElementById('impFilePath').value;
			document.getElementById('filepath').value = filepath;
			url = 'dtjs_dtxxgl.do?method=importData&act=import';
			refreshForm(url);
			BatAlert.showTips('���ݵ����У����Ե�...');
		}

		// ����������ģ��
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
					<th colspan="2"><span>��Ϣ����</span></th>
				</tr>
			</thead>
		</table>
	</div>
	<div>
	   <table border="0" align="center" style="width: 95%;">
	   <tr>
	   <td>
	    <div class="prompt" id="div_help">
	       <h3><span>��ʾ��</span></h3>
	       <p>���Ϲ淶�����ݿɵ���ɹ��������Ϲ淶�����ݽ��ᷴ�����������в����Ϲ淶ԭ��<br/>
	       	  1�����������밴ϵͳ�ṩģ����������ǰ�׶δ������ģ���еġ��׶δ���������еĴ�����д<br/>
	       	  2�����������ֶ���������дһ���׶εĿ�ʼʱ�䣬�������ݽ��޷�����ɹ�<br/>
	       	  ����ʧ�ܵ�ԭ���У�<br/>
	       	    &nbsp;&nbsp;1).��ǰ�׶δ���Ϊ�ջ򲻴�����ϵͳ��<br/>
	       	    &nbsp;&nbsp;2).��ǰ�׶ο�ʼʱ��Ϊ��<br/>
	       	    &nbsp;&nbsp;3).�����ѧ��Ϊ�ջ򲻴�����ϵͳ��<br/>
	       	    &nbsp;&nbsp;4).��Ӧѧ��ѧ������Ϣ�Ѵ����ڵ�����Ϣ��¼��<br/>
	       	    &nbsp;&nbsp;5).ѧ�Ž׶��ظ�<br/>
	       	    &nbsp;&nbsp;6).ʱ���ʽ����ȷ(ע��Ŀǰϵͳֻ֧��"yyyy-MM-dd"��"yyyyMMdd"��ʽ)<br/>
	       	    <font color="red">˵����ģ�����غ�һ��Ҫ�����еĵ�Ԫ��ĸ�ʽ���ó��ı���ʽ������ʱ���������</font>			
	       	     </p>
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
						<button type="button" onclick="importModelUpload()" class="btn_01">�� ��</button>
					</td>
				</tr>
				
				<tr>
					<th>�������ݵ���</th>
					<td>
					 <input type="file" id="impFilePath" name="impFilePath"/>
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('impFilePath'))) importSubmit();">
				          	�������ݵ���
				          </button>
					</td>
				</tr><%--
				<tr>
					<th>��ѡ������ֶΣ�</th>
					<td>
						<input type="radio" name="drzd" value="qsdh" checked="checked"/>�������ҵ绰
						<input type="radio" name="drzd" value="sfbz"/>���������շѱ�׼
					</td>
				</tr>
			--%></tbody>
			
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
    <logic:present name="back">
    	<script>
    		alertInfo("${back}");
    	</script>
    </logic:present>
  </body>
</html>
