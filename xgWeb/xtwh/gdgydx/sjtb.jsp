<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
	
	function queryxxdm(){
	  var xxmc = document.getElementById("xxmc").value;
	 document.forms[0].action = "/xgxt/system_init.do?doType=search&xxmc="+xxmc;
     document.forms[0].submit();
    }
	
	function savextsz(){
	  var xxmc = document.getElementById("xxmc").value;
	  var setxxdm = document.getElementById("setxxdm").value;
	
	if(xxmc==""){
	  alert("ѧУ���Ʋ���Ϊ�գ�");
	  return false;
	}
	if(setxxdm==""){
	  alert("ѧУ���벻��Ϊ�գ�");
	  return false;
	}
	
	
	 document.forms[0].action = "/xgxt/system_init.do?act=save";
     document.forms[0].submit();
    }
	
	
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ϵͳ��ʼ�� - ����ͬ��</a>
			</p>
		</div>
	
		<html:form action="/xsxxGdgydx" method="post">
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>

		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ͬ��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz"><span class="red">��ʾ��ѡ��������ɰ�ѡ�������ͬ��ѧ���������Ϣ����ѡ������ͬ������ѧ������Ϣ !</span></div>
								<div class="btn">
									<button type="button" name="�ύ" onclick="refreshForm('xsxxGdgydx.do?method=sjtbOper')">
										���»�ȡ����
									</button>
									<button type="button" name="����" type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="20%">
								�꼶
							</th>
							<td width="80%">
								<html:select property="nj" styleId="nj"
									onchange="initZyList();initBj();">
								    <html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
					        <th><bean:message key="lable.xsgzyxpzxy" />����</th>
					        <td>
					        	<html:select property="xydm" styleId="xy"
										onchange="initZyList();initBj();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
					        </td>
					      </tr>
					      <tr>
					        <th>רҵ����</th>
					        <td>
					        	<html:select property="zydm" styleId="zy" onchange="initBj();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
					        </td>
					      </tr>
					      <tr>
					        <th>�༶����</th>
					        <td>
					        	<html:select property="bjdm" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
					        </td>
					      </tr>
						</tbody>
						</table>
						</div>
			<logic:present name="noConnect">
			<script>
				alert('����û�����ӳɹ��������������ӣ�');
			</script>			
			</logic:present>
			<logic:present name="tbresult">
			<logic:equal value="true" name="tbresult">
			<script>
				alert('�����ɹ���');
			</script>	
			</logic:equal>
			<logic:equal value="false" name="tbresult">
			<script>
				alert('����ʧ�ܣ�');
			</script>			
			</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
