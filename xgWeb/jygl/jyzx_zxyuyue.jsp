<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript">
	function savego(){
		var qwyjtime = document.getElementById("qwyjtime").value;
		var meet = document.getElementsByName("meet");
		var zxwtjs = document.getElementById("zxwtjs").value;
		var t;
		
		for(i=0;i<meet.length;i++){
		  if(meet[i].checked==true){
		     t = meet[i].value;
		  }
		}
		if(t=="��"&&qwyjtime!=""){
		alert("��Ҫ����棬������дԼ��ʱ�䣡");
		return false;
		}
		
		if(zxwtjs==""){
		alert("��ѯ�������������д��");
		return false;
		}
	
		 showTips('���������У���ȴ�......');
	     document.forms[0].action = "/xgxt/saveYuyue.do?doType=save";
		 document.forms[0].submit();
	}
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ѯʦ��ϸ��Ϣ</a>
			</p>
		</div>
	
		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯʦ��ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="�ύ" onclick="savego()">
										�� ��
									</button>
									<button name="����" type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								��ѯʦ���
							</th>
							<td width="34%">
								<html:text name="rs" property="num" style="100%" readonly="true" />
							</td>
							<th width="16%">
								��ѯʦ����
							</th>
							<td width="34%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ����
							</th>
							<td>
								<bean:write name="rs" property="age" />
							</td>
							<th>
								��ѯʦ�Ա�
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ�ʸ�
							</th>
							<td>
								<bean:write name="rs" property="zxszg" />
							</td>
							<th>
								��������
							</th>
							<td>
								<bean:write name="rs" property="email" />
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td>
								<bean:write name="rs" property="lxdh" />
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ���
							</th>
							<td colspan="3" style="word-break:break-all">
								<html:textarea name="rs" property="zxsjj" rows="6"
								style="width:100%" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ�Ҫ�����
							</th>
							<td>
								<input type="radio" name="meet" value="��"/>
							&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="meet" value="��" checked="checked"/>
							&nbsp;��
							</td>
							<th>
								����Լ��ʱ��
							</th>
							<td>
								<html:text name="rs" style="cursor:hand; width=100%;"
								styleId="qwyjtime" property="qwyjtime"
								onclick="return showCalendar('qwyjtime','y-mm-dd');"
								readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��ѯ�������
								<br/>
								<font color="red"><��500��></font>
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zxwtjs" rows="6"
								style="width:100%" onblur="checkLen(this,500)"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                       alert("ԤԼ�ɹ�������ϸ������鿴ԤԼ�����ѯ��");
                       if (window.dialogArguments) {
							Close();
							dialogArgumentsQueryChick();
						}
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("ԤԼʧ�ܣ������Ƿ��ظ��ύ��");
                      if (window.dialogArguments) {
						Close();
						dialogArgumentsQueryChick();
					}
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>