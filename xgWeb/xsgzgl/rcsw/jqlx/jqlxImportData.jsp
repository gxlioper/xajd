<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript">
		//�������ݵ���
		function importSubmit(){
			var filepath = document.getElementById('impFilePath').value;
			document.getElementById('filepath').value = filepath;
			url = 'rcsw_jqlx.do?method=importData&act=import';
			refreshForm(url);
			BatAlert.showTips('���ݵ����У����Ե�...');
		}

		// ����������ģ��
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
	       <p>���Ϲ淶�����ݿɵ���ɹ��������Ϲ淶�����ݽ��ᷴ�����������в����Ϲ淶ԭ��<br/>
	       	     ��ɫ<font color="red">���</font>�ֶ�Ϊ����Ϊ�յ��ֶΣ���ע�⣡<br/>
	       	  ����ʧ�ܵ�ԭ���У�
				1.���������ֶ��п�ֵ;
	       	    2.�����ѧ��ѧ��������;
				3.����ʱ���ʽ���淶;
				4.��У��ʼʱ�䡢��У��ֹʱ�������ϵͳ���õ�ѧ����У��ֹʱ����;
				5.����Ĵ�λ������;
				6.����Ĵ�λΪ������λ;
				7.����ǵ�ǰѧ��ѧ�ڵ�����;
				8.�����ѧ���Ա��봲λ�������Ա�ͬ;
				9.�����ѧ���ظ�;
				10.����Ĵ�λ�ظ�;

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
