<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script>
	function checkText(flag){
		//alert(flag);
		if(flag==1){	
			var dd_dm=document.all["dd_dm"].value;
			if ( dd_dm==""){
				alert ("��ѡ����ѯ�ص�");
				return false;
			}
			var sjd_dm=document.all["sjd_dm"].value;
			if ( sjd_dm==""){
				alert ("��ѡ��ʱ���");
				return false;
			}
		}
		if(flag==1||flag==2){
			var rq=document.all["rq"].value;
			if ( rq==""){
				alert ("��ѡ������");
				document.all["rq"].focus();
				return false;
			}
		}
		if(flag==3){
			var formrq=document.all["fromrq"].value;
			if ( formrq==""){
				alert ("��ѡ���ĳ�쿪ʼʱ��");
				document.all["fromrq"].focus();
				return false;
			}
			var torq=document.all["torq"].value;
			if ( torq==""){
				alert ("��ѡ��ĳ�����ʱ��");
				document.all["torq"].focus();
				return false;
			}
		}
		underDealWith();
		if(flag==1){
			refreshForm('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl&doType=Add_xx');//������Դ
		}
		if(flag==2){
			refreshForm('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl&doType=Add_mt'); //����ĳ�յ���Դ
		}
		if(flag==3){
			refreshForm('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl&doType=Add_pl'); //��������
		}
	}
	</script>
</head>
<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ - ������ѯ - ����ԤԼ��Դ</a>
			</p>
		</div>
	<div id="tmpdiv"></div>

	<html:form action="/xljk_zxszy_add">
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ѡ��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="checkText('1')">
										������Դ
									</button>
									<button onclick="checkText('2')" >
										����ĳ����Դ
									</button>
									<button  onclick="checkText('3')">
										��������
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				<tbody>
				<tr>
					<th width="40%">
						�ص�
					</th>
					<td>
						<html:select property="dd_dm" style="width:100px" styleId="dd_dm"
							onchange="">
							<html:option value=""></html:option>
							<html:options collection="ddList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						ʱ���
					</th>
					<td>
						<html:select property="sjd_dm" style="width:100px"
							styleId="sjd_dm" onchange="">
							<html:option value=""></html:option>
							<html:options collection="sjdList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						����
					</th>
					<td>
						<html:text style="cursor:hand; width:75px;" styleId="dateF"
							property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>

				<tr>
					<th>
						������������������ĳ��
					</th>
					<td>
						<html:text style="cursor:hand; width:75px;" styleId="dateFr"
							property="fromrq"
							onclick="return showCalendar('dateFr','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>
						������������������ĳ��
					</th>
					<td>
						<html:text style="cursor:hand; width:75px;" styleId="dateTo"
							property="torq"
							onclick="return showCalendar('dateTo','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>
						��������������������
					</th>
					<td>
						<html:select property="monthday">
							<html:option value=""></html:option>
							<html:option value="1">����</html:option>
							<html:option value="2">��һ</html:option>
							<html:option value="3">�ܶ�</html:option>
							<html:option value="4">����</html:option>
							<html:option value="5">����</html:option>
							<html:option value="6">����</html:option>
							<html:option value="7">����</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<td colspan="2">
							<span>���˵��</span>
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2">
							<font color="blue">һ�� ������Դ��</font>����������Դֻ��ע��
							<font color="red">�ص㣺</font><font color="red">ʱ��Σ�</font>��
							<font color="red">���ڣ�</font>,�������һ����¼
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<font color="blue">���� ���ɵ�����Դ��</font>�������ɵ�����Դֻ��ע��
							<font color="red">����:</font>��
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�������
							<font color="red">�ص������ * ʱ��ε�����</font>����¼
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<font color="blue">���� �������ɣ�</font>������������ֻ��ע
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">(������������)��ĳ��:</font>��
							<font color="red">(������������)��ĳ�죺</font>�������������뿼�ǣ��������
							<br/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">(��ĳ�� - ��ĳ��) * �ص������ * ʱ��ε�����</font>����Դ��¼
							.���ѡ�������ڣ������ɴӴ�ĳ�쵽ĳ����ѡ���ڵ���Դ
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<logic:notEmpty name="message">
			<logic:equal name="message" value="insert_success">
				<script>
							alert("����ɹ�!");
							Close();
							dialogArgumentsQueryChick();
						</script>
			</logic:equal>
			<logic:equal name="message" value="insert_fail">
				<script>alert("����ʧ��!")</script>
			</logic:equal>
			<logic:equal name="message" value="date error">
				<script>alert("������������!")</script>
			</logic:equal>
			<logic:equal name="message" value="date exits">
				<script>alert("��ʱ����Ѿ�����!")</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
