<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		
	<script language="javascript">
	</script>
</head>
<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>˼������ - ��Ϣά�� - ����Ա��� - ��������</a>
		</p>
	</div>
	
	<%--	<logic:equal name="sfksq" value="-1">--%>
	<%--		<center>--%>
	<%--			<p>--%>
	<%--				���ڲ�������ʱ���ڣ�--%>
	<%--			</p>--%>
	<%--		</center>--%>
	<%--	</logic:equal>--%>
	<html:form action="/szdwfzjy" method="post">
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>��������</span></th>
			        </tr>
			    </thead>
			    <tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			         
			          	<button type="button" name="����" onClick="refreshForm('szdwfzjy.do?method=fdybjjcszSave');">�� ��</button>
			        	<logic:equal name="userType" value="admin" scope="session">
						<button type="button"  onClick="if(confirm('�ò�������Ѿ�����ĸ���Ա�༶ȫ����գ���ȷ��Ҫ���½��з�����?')){refreshForm('szdwfzjy.do?method=fdybjCsh');}">
							��ո���Ա�ѷ���༶
						</button>
						<button type="button"  onClick="if(confirm('�ò�������Ѿ�����İ����ΰ༶ȫ����գ���ȷ��Ҫ���½��з�����?')){refreshForm('szdwfzjy.do?method=bzrbjCsh');}">
							��հ������ѷ���༶
						</button>
						</logic:equal>
			          </div></td>
			      </tr>
			    </tfoot>
			<tbody>
			<tr>
				<th colspan="2">
				<div  align="center">
					<font color="red">��ܰ��ʾ:   </font>
					<font color="blue">�޸Ĳ������ö��Ѿ�����õİ༶�Ĳ����κ�����</font>
				</div>
				</th>
			</tr>
			<tr>
				<th align="center" width="20%" rowspan="4">
						����Ա��������
				</th>
				<td width="80%">
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="1">��������Աֻ�ֹܷܵ����༶</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="2">��������Ա���Էֹܶ���༶</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="3">�������Ա���Էֹܵ����༶</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="fdybjsz" value="4">�������Ա���Էֹܶ���༶</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<th align="center" width="20%" rowspan="4">
						�����β�������
				</th>
				<td width="80%">
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="1">����������ֻ�ֹܷܵ����༶</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="2">���������ο��Էֹܶ���༶</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="3">��������ο��Էֹܵ����༶</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<html:radio name = "rs" property="bzrbjsz" value="4">��������ο��Էֹܶ���༶</html:radio>
					</div>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		
		
		</html:form>
			<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('�޸ĳɹ���');
	         	window.dialogArguments.refreshForm("/xgxt/setFdyBj.do");
	         	Close();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('�޸�ʧ��');
			  </script>
			</logic:equal>
			<logic:equal value="qk" name="done">
			  <script type="text/javascript">
			    alert('��ճɹ���');
	         	window.dialogArguments.refreshForm("/xgxt/setFdyBj.do");
	         	Close();
			  </script>
			</logic:equal>	
			<logic:equal value="wqk" name="done">
			  <script type="text/javascript">
			    alert('���ʧ��');
			  </script>
			</logic:equal>
</body>
</html>
