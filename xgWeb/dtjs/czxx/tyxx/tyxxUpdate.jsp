<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	
	<html:form action="/czxxDtjsTyxx" method="post">
	<input type="hidden" name="pkValue" value="${param.pkValue }"/>
	<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ա��Ϣ�޸�</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<td colspan="12">
						<b>��Ա������Ϣ</b>
					</td>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>ѧ��
				</th>
				<td align="left" width="30%">
					<html:text styleId="xh" property="save_xh"
						style="width:80%;heigh:100%" value="${rs.xh}" readonly="true" />
				</td>
				<th>
				<div align="right">
					�꼶
				</div>
				</th>
				<td>
					${rs.nj }
				</td>
			</tr>
			<tr>
				<th width="16%">
					<div align="right">
						����
					</div>
				</th>
				<td width="34%">
					${rs.xm }
				</td>
				<th>
					<div align="right">
						�Ա�
					</div>
				</th>
				<td>
					${rs.xb }
				</td>
			</tr>
			<tr>	
				<th>
					<div align="right">
						<bean:message key="lable.xb" />����
					</div>
				</th>
				<td>
					${rs.xymc }
				</td>
				<th>
					<div align="right">
						רҵ����
					</div>
				</th>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>	
				<th>
					<div align="right">
						�༶
					</div>
				</th>
				<td>
					${rs.bjmc }
				</td>
				
				<th>
					<div align="right">
						���ŵص�
					</div>
				</th>
				<td>
					<html:text property="save_rtdd" value="${rs.rtdd }" maxlength="50"></html:text>
				</td>
			</tr>
			
			<tr>
				<th>
					<div align="right">
						����ʱ��
					</div>
				</th>
				<td colspan="3">
					<html:text property="save_rtrq" readonly="true" styleId="rtrq" value="${rs.rtrq}" onclick="showCalendar('rtrq','y-mm-dd')" 
					onblur="dateFormatChg(this);"></html:text>
				</td>
			</tr>
		</tbody>
		 <tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          	<button type="button" name="�ύ" 
		          		onclick="saveDataShowTips('czxxDtjsTyxx.do?method=tyxxUpdate&doType=save','xh','���ڴ����������Ժ�');">�� ��</button>
		            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
		          </div></td>
		      </tr>
		  </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
	</logic:present>
</body>
</html>
