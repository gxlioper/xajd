<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function dc(){
		var dclx=document.getElementById("dclx").value;
		if(dclx==""){
			alert("��ѡ��Ҫ�����ı�������");
			return false;
		}
		var url="rstj.do?method=rstjDc&doType="+dclx;
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
    </script>
    </head>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>


	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<form action="/xsxx_rstjcx" method="post" name="from">
			<div class="tab">
				<table width="70%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��ѡ����Ҫͳ�Ƶı�������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="����" onclick="dc()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="40%">
								��������
							</th>
							<td>
								<div class="pos" style="z-index:3">
									<select id="dclx">
										<option value="">---------��ѡ��--------</option>
										<option value="xqxynj">У��<bean:message key="lable.xb" />�꼶</option>
										<option value="ssccxbmc">ʡ�в���Ա�����</option>
										<option value="xyzynjxb"><bean:message key="lable.xb" />רҵ�꼶�Ա�</option>
										<option value="xyzynj"><bean:message key="lable.xb" />רҵ�꼶</option>
									</select>
						         </div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</body>
</html>
