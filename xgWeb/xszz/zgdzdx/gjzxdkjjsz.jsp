<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body onload="document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function dataCl(){
			var jjrq = document.getElementById('jjrq').value;
			re = /-/gi;
			document.getElementById('jjrq').value = jjrq.replace(re,"");
		}
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			var jjrq = document.getElementById('jjrq').value;
			var pkVal = document.getElementById('pkVal').value;
			var qs = document.getElementById('qs').value;
			
			if (null == jjrq || jjrq == ""){
				alert("������д��Ҫ��ӡ��ݵ����ڣ�");
				return false;
			}
			var url = "/xgxt/zgdzdx_xszz.do?method=gjzxdkjjdy";
			url += "&pkVal="+pkVal;
			url += "&qs="+qs;
			url += "&jjrq="+jjrq;
			window.open(url, 1000, 1000);
			return true;
		}
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdkjjsz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ��� - ������ѧ�����ݴ�ӡ</a>
				</p>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="2" align="center">
						</td>
					</tr>
				</thead>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="�ύ"onClick="toPrintOut();">�� ӡ</button>
			          </logic:notEqual>
			            <button type="button" name="�ر�"  onclick="Close();return false;"  id="buttonClose">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr>
					<th align="center" width="50%">
						������
					</th>
					<td width="50%">
						<html:select name="rs" property="qs" style="width:100%;heigh:100%">
							<html:option value="1">��һ��</html:option>
							<html:option value="2">�ڶ���</html:option>
							<html:option value="3">������</html:option>
							<html:option value="4">������</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
							<span class="red">*</span>������ڣ�
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%" onblur="dataCl();"
								onclick="return showCalendar('jjrq','y-mm-dd');"
								value="<bean:write name='rs' property="jjrq" />" name="jjrq"
								id="jjrq" />
					</td>
				</tr>
				</tbody>
			</table>
		</html:form>
	</body>
</html>
