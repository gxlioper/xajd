<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
</head>

<body onload="checkWinType();">
	<html:form action="/data_search" method="post">
		<input type="hidden" id="pk" name="pk" value="${pk}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ������Ϣ - ��Ϣά��<</a>
			</p>
		</div>
				
		<div class="tab">
		  <table width="100%" border="0" class="formlist"id="rsT">
			<tbody>
			<tr>
				<td height="27" align="center">
					�Ƿ��ڷ�У��
				</td>
				<td align="center">
					<html:select property="sfzfx" styleId="sfzfx" style="width:120px">
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>								
					</html:select>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <button 
						onclick="refreshForm('stu_info_add.do?method=saveStuinfoBatch');BatAlert.showTips('����ִ�в�������ȴ�...');"
						id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;				
					<button onclick="Close();return false;">
						�� ��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>									
			</table>
		</div>
		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>	
				alert("�����ɹ���");
				Close();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>	
				alert("����ʧ�ܣ�");
			</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
</html>

