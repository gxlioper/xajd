<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
			function saveBl(){
				var psfbl = $("psfbl").value;
				var wsfbl = $("wsfbl").value;
				var kqfbl = $("kqfbl").value;
				if((parseInt(psfbl) + parseInt(wsfbl)+ parseInt(kqfbl)) > 100){
					alert("���������Ӳ��ܳ���100�֣���ȷ�ϣ�����");
					return false;
				}
				refreshForm('zjlgPjpy.do?method=dycpfDycpfSz&type=save');
			}
		</script>
	</head>
	<body>
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - ��Ϣά�� - �����ɼ�ά��</a>
				</p>
			</div>
			
			<input type="hidden" name="type" id="type" value="<bean:write name="type" />"/>
			<div class="tab">
			<table width="100%"  class="formlist" align="center">
				<thead>
					<tr>
						<th colspan="2">
							<span>��߷�����</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							ƽʱ����߷�
						</th>
						<td align="left" >
							<html:text name="rs" property="psfbl" style="width:50px" 
								onkeyup="ckinpdata(this)" maxlength="4"/>
						</td>
					</tr>
					<tr>
						<th>
							��������߷�
						</th>
						<td align="left">
							<html:text name="rs" property="wsfbl" style="width:50px"
								onkeyup="ckinpdata(this)" maxlength="4"/>
						</td>
					</tr>
					<tr>
						<th>
							���ڷ���߷�
						</th>
						<td align="left" >
							<html:text name="rs" property="kqfbl" style="width:50px"
								onkeyup="ckinpdata(this)" maxlength="4"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="2">
			          <div class="btn">
							<button type="button" name="�ύ" onclick="saveBl();">�ύ</button>
						 	<button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
