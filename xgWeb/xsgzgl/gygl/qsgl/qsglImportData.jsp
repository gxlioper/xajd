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
			var drzd=document.getElementsByName("drzd");
			if(drzd[0].checked){
				confirmInfo("ȷ���������ҵ绰��",function(ok){if(ok=="ok"){importSubmit_end()}});
			}else if(drzd[1].checked){
				confirmInfo("ȷ�����������շѱ�׼��",function(ok){if(ok=="ok"){importSubmit_end()}});
			}else if(drzd[2].checked){
				confirmInfo("ȷ�������������޿յ���",function(ok){if(ok=="ok"){importSubmit_end()}});
			}else{
				alertInfo("��ѡ������ֶΣ�");
				return false;	
			}
		}
		function importSubmit_end(){
			
			var filepath = document.getElementById('impFilePath').value;
			document.getElementById('filepath').value = filepath;
			url = 'gyglnew_qsgl.do?method=importData&act=import';
			refreshForm(url);
			BatAlert.showTips('���ݵ����У����Ե�...');
		}

		// ����������ģ��
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
	       <h3><span>��ʾ��</span></h3>
	       <p>���Ϲ淶�����ݿɵ���ɹ��������Ϲ淶�����ݽ��ᷴ�����������в����Ϲ淶ԭ��<br/>
	       	     ��ɫ<font color="red">���</font>�ֶ�Ϊ����Ϊ�յ��ֶΣ���ע�⣡</br>
	       	  ����ʧ�ܵ�ԭ���У�
	       	    1.ϵͳ���޶�Ӧ������

	       	     </p>
	   	</div>
	<div style='width:100%;overflow:hidden;overflow-x:hidden'>
		<table class="formlist" >	
			<thead>
				<tr>
					<th colspan="2">
						<span>��Ϣ����</span>
					</th>
				</tr>
			</thead>
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
				</tr>
				<tr>
					<th>��ѡ������ֶΣ�</th>
					<td>
						<input type="radio" name="drzd" value="qsdh" checked="checked"/>�������ҵ绰
						<input type="radio" name="drzd" value="sfbz"/>���������շѱ�׼
						<input type="radio" name="drzd" value="ywkt"/>�����������޿յ�
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
    <logic:present name="back">
    	<script>
    		alertInfo("${back}");
    	</script>
    </logic:present>
  </body>
</html>
