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
</head>
	<body onload="document.getElementById('drms').checked=true;" >	
    <html:form action="/impAndChkData.do" enctype='multipart/form-data' method="post">
    <input type="hidden" name="filepath" id='filepath' value="${filepath}"/>
    <input type="hidden" name="flag" id='flag' value="${ flag }" />
    <input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
    <input type="hidden" name="dzyDdTab" id="dzyDdTab" value="${dzyDdTab}"/> 
    <input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
    <input type="hidden" name="nf" id="nf" value="${nf}"/>
    <input type="hidden" name="yf" id="yf" value="${yf}"/>
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
	       <p>�����յ�������֮ǰ�������������ݼ�⡣<br/>
	       	     ����������ݼ�⣬Ҳ����ֱ�ӵ������ݡ�<br/>
	       	     ��ɫ<font color="red">�����ֶ�</font>����Ϊ�գ���ע�⣡</p>
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
						<button type="button" onclick="dataStencilExport()" class="btn_01">�� ��</button>
					</td>
				</tr>
				
				<tr>
					<th>��Ϣ������</th>
					<td>
						<input type="file" id="checkFilePath" name="checkFilePath"/>
						<button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('checkFilePath'))) importCheckSubmit();">���ݵ�����</button>
					</td>
					
				</tr>
				
				<tr>
					<th>�������ݵ���</th>
					<td>
					 <input type="file" id="impFilePath" name="impFilePath"/>
					 <logic:notEqual value="" name="dzyDdTab">
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(DWRUtil.byId('impFilePath'))) importSubmitZdy();">
				          	�������ݵ���
				          </button>
			          </logic:notEqual>
			          <logic:equal value="" name="dzyDdTab">
			          	 <button type="button" class="btn_01" onclick="if(checkFileFormat(DWRUtil.byId('impFilePath'))) importSubmit();">
				          	�������ݵ���
				          </button>
			          </logic:equal>
					</td>
				</tr>
				
				<tr>
					<th>����ģʽ</th>
					<td>
						<html:radio property="drms" value="1" styleId="drms">����������</html:radio>
						 
						<logic:notEqual value="zdlskhb" name="realTable">
							<logic:notEqual value="xg_rcsw_sybxxxb" name="realTable">
								<html:radio property="drms" value="0">���ݸ���</html:radio>
							</logic:notEqual>
						</logic:notEqual>
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
