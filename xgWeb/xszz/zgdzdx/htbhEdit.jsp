<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<base target="_self" />
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
		function yz(){
			var zxhth = document.getElementById('zxhth').value;
			var zdhth = document.getElementById('zdhth').value;
			var yfphthList = document.getElementById('yfphthList').value;
			if((zxhth == null) || (zxhth == "")){
				alert("��С��ͬ��Ų���Ϊ��!");
				return false;
			}
			if((zdhth == null) || (zdhth == "")){
				alert("����ͬ��Ų���Ϊ��!");
				return false;
			}
			
			if (Math.round(zxhth) >= Math.round(zdhth)){
				alert("��С��ͬ��Ų��ܴ��ڻ��������ͬ���!");
				return false;
			}
			
			list = yfphthList.split("!!OneSpile!!");
			
			for(i=Math.round(zxhth); i<=Math.round(zdhth); i++){
				for(j=0; j < list.length; j++){
					if (i == list[j]){
						alert("�ֺ�ͬ��ź��ѷ���ĺ�ͬ������ص�����˶Ժ����ύ!");
						return false;
					}
				}
			}
			
			
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=htbhEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ��������ά�� - ��ͬ�������</a>
		</p>
	</div>
	<html:form action="/zgdzdx_xszz.do?method=htbhEdit" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab">
		  <table width="100%"  border="0" class="formlist">
		    <thead>
		    	<tr>
		        	<th colspan="4"><span>��ͬ�������</span></th>
		        </tr>
		    </thead>
		   <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:equal name="writeAble" value="yes">
			          		<button type="button" name="�ύ" onclick="yz()">�� ��</button>
			          </logic:equal>
			            <button type="button" name="�ر�" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		
		<tbody>
			<tr>
				<th width="50%">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</th>
				<td width="50%">
					<bean:write name="rs" property="xymc"/>
					<input type="hidden" id="xydm" name="xydm"
						value="<bean:write name="rs" property="xydm" />">
					<input type="hidden" id="yfphthList" name="yfphthList"
						value="<bean:write name="rs" property="yfphthList" />">
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						��ͬ��ż��
					</div>
				</th>
				<td align="center">
					<input type="text" id="htmc" name="htmc" maxlength="4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htmc"/>">
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						 <span class="red">*</span>��С��ͬ��
					</div>
				</th>
				<td align="center">
					<input type="text" id="zxhth" name="zxhth" maxlength="4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zxhth"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						 <span class="red">*</span>����ͬ��
					</div>
				</th>
				<td align="center">
					<input type="text" id="zdhth" name="zdhth" maxlength="4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zdhth"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						 �ѷ����ͬ�ţ��벻Ҫ���ѷ���ĺ�ͬ���ٴη��䣩
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<font color="red">
					<bean:write name="rs" property="yfphth"/>
					</font>
				</td>
			</tr>
			</tbody>
		</table>
	</html:form>
</body>
</html>
