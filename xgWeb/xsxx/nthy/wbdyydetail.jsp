<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/checkUtils.js"></script>
</head>

<body>
	<html:form action="/xsbd" method="post">
		<input type="hidden" id="xhstr" name="xhstr" value="${xhstr }"/>

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="2"><span>δ����ԭ����д</span></th>
		        </tr>
		    </thead>
			<tbody>
			<tr>
				<th>
					<span class="red">*</span>δ����ԭ��
					<br/>
					<font color="red">(��������1000��)</font>
				</th>
				<td>
					<html:textarea property="wbdyy" cols="40" rows="8" onblur="checkLeng(this,1000)"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="2"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('xsbd.do?method=wbdyydetail&doType=save&zt=δ����','wbdyy-wbdyy');">
						��&nbsp;&nbsp;��
					</button>
					<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
						��&nbsp;&nbsp;��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	  </div>
		
	</html:form>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script language="javascript">
         	alert("�����ɹ���");
         	if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
         	</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
       	 	 alert("����ʧ�ܣ�");
       	 	if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
         	</script>
		</logic:equal>
	</logic:present>
</body>
</html>
