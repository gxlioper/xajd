<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>

		<script language="javascript">
	function jyglByqxUpdate(){
	var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;
	 
	 
	 if(byqx==""){
	 alert("��ҵȥ�������д��");
	 return false;
	 }
	 if(lxdz==""){
	 alert("��ϵ��ַ������д��");
	 return false;
	 }
	 if(yzbm==""){
	 alert("�������������д��");
	 return false;
	 }
	 if(!isNumber(yzbm)){
	 alert("��������ӦΪ���֣�")
	 return false;
	 }
	 if(yzbm!=""&&yzbm.length!=6){
	 alert("�������볤�Ȳ���Ҫ��");
	 return false;
	 }
	 
	 
	 if(lxdh!=""&&!isNumber(lxdh)){
	 alert("��ϵ�绰ӦΪ���֣�")
	 return false;
	 }
	 if(lxdh!=""&&lxdh.length<7){
	 alert("��ϵ�绰���Ȳ���Ҫ��");
	 return false;
	 }
	 if(yddh!=""&&!isNumber(yddh)){
	 alert("�ƶ��绰ӦΪ���֣�")
	 return false;
	 }
	 if(yddh!=""&&yddh.length!=11){
	 alert("�ֻ����볤�Ȳ���Ҫ��");
	 return false;
	 } 
	 if(lxdh==""&&yddh==""){
	 alert("��������дһ����ϵ�绰��");
	 return false;
	 }
	   
		 		document.forms[0].action = "/xgxt/jyglByqxUpdate.do?doType=update&act=update";
		 		document.forms[0].submit();
    }
   
	
	function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
    
   
	</script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ѧ����Ϣ - ��ҵȥ����Ϣ</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td align="left" colspan="4">
								��ҵ���
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								�ύʱ��
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tbody>
						<tr style="height:22px">
							<th width="15%">
								ѧ��
							</th>
							<td width="35%">
								<html:text name="rs" property="xsxh" readonly="true"
									style="width=100%" />
							</td>
							<th width="15%">
								����
							</th>
							<td width="35%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�Ա�
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							<th>
								���֤��
							</th>
							<td>
								<bean:write name="rs" property="id" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��Դ����
							</th>
							<td>
								<bean:write name="rs" property="sydq" />
							</td>
							<th>
								��ҵ��λ
							</th>
							<td>
								<html:text name="rs" property="jydw" style="width=100%" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<font color="red">*</font>��ҵȥ��
							</th>
							<td>
								<html:select name="rs" property="byqx" styleId="byqx"
									style="width=100%">
									<html:options collection="byqxList" property="byqx"
										labelProperty="byqx" />
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>��ϵ��ַ
							</th>
							<td align="left">
								<html:text name="rs" property="lxdz" style="width=100%" maxlength="25"/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<font color="red">*</font>��ϵ�绰
							</th>
							<td>
								<html:text name="rs" property="lxdh" style="width=100%" maxlength='12'/>
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text name="rs" property="yzbm" style="width=100%"  maxlength='6'/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								<font color="red">*</font>�ƶ��绰
							</th>
							<td>
								<html:text name="rs" property="yddh" style="width=100%" maxlength="11" />
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:text name="rs" property="email" style="width=100%" maxlength='20'/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								ѧУ��˽��
							</th>
							<td>
								<html:text name="rs" property="xxsh" style="width=100%"
									readonly="true" />
							</td>
							<th>
								���ʱ��
							</th>
							<td>
								<html:text name="rs" property="shsj" style="width=100%"
									readonly="true" />
							</td>
						</tr>
						<tr style="height:55px">
							<th>
								�޸����
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="xgyj" rows="3"
									style="word-break:break-all;width:99%" readonly="true"
									onblur="chLeng(this,50);"/>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�����
							</th>
							<td>
								<html:text name="rs" property="shperson" style="width=100%"
									readonly="true" />
							</td>
							<th>

							</th>
							<td align="center">
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button name="����" onclick="jyglByqxUpdate()" id="buttonSave">
										�� ��
									</button>
									<button name="�ر�" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("�޸ĳɹ�!");
                      if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
                </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("�����ͨ����������Ȩ�����޸ģ�");
		               if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
