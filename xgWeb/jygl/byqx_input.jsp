<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript">
	function savebyqx(){
	 var xsxh = document.getElementById("xsxh").value;
	 var byqx = document.getElementById("byqx").value;
	 var lxdz = document.getElementById("lxdz").value;
	 var lxdh = document.getElementById("lxdh").value;
	 var yzbm = document.getElementById("yzbm").value;
	 var yddh = document.getElementById("yddh").value;
	 var email = document.getElementById("email").value;

	 if(xsxh==""){
	 alert("ѧ�ű�����д��")
	 return false;
	 }
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
		 		document.forms[0].action = "/xgxt/savebyqx.do?doType=save";
		 		document.forms[0].submit();
	 }
	
	function returntobegin(){
	            document.forms[0].action = "/xgxt/savebyqx.do";
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
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵЭ�鷽�� - ��ҵȥ��¼��</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
                      alert("�������ѧ����Ч!");
                   </script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />
				<html:hidden name="rs" property="xslb" />
				
				<div class="tab">
					<table width="80%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>��ҵȥ��¼��</span>
								</th>
							</tr>
						<tr>
							<td align="left" colspan="4">
                                 ��ҵ���:
								<html:text property="bynd" name="rs" readonly="true"
									style="width:35px" />
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
								<html:text property="xymc" name="rs" readonly="true"
									style="width:130px" />
							</td>
						</tr>
					</thead>
					<tbody>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th align="right" width="15%">
							    <font color="red">*</font>ѧ��
							</th>
							<td align="left" width="35%">
								<html:text name='rs' property="xsxh" styleId="xsxh"
									style="width:210px" readonly="true" />
								<button onclick="showTopWin('/xgxt/byqxTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th align="right" width="15%">
								<font color="red">*</font>ѧ��
							</th>
							<td align="left" width="35%">
								<html:text property="xsxh" name="rs" styleId="xsxh"
									readonly="true" style="width:210px" />
							</td>
						</logic:equal>
						<th align="right" width="15%">
							����
						</th>
						<td align="left" width="35%">
							<html:text name="rs" property="name" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�Ա�
						</th>
						<td align="left">
							<html:text name="rs" property="xb" readonly="true"
								style="width:210px" />
						<th align="right">
							���֤��
						</th>
						<td align="left">
							<html:text name="rs" property="id" readonly="true"
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��Դ����
						</th>
						<td align="left">
							<html:text name="rs" property="sydq" readonly="true"
								style="width:210px" />
						</td>
						<logic:equal name="xxdm" value="8001">
						<th align="right">
							��ҵ��λ
						</th>
						<td align="left">
							<html:text name="rs" property="jydw" 
								style="width:210px" maxlength="100"/>
						</td>
						</logic:equal>
					</tr>
					<tr style="height:22px">
						<th align="right">
							<font color="red">*</font>��ҵȥ��
						</th>
						<td align="left">
							<html:select name="rs" property="byqx" styleId="byqx"
								style="width:210px">
								<html:option value=""></html:option>
								<html:options collection="byqxList" property="byqx"
									labelProperty="byqx" />
							</html:select>
						</td>
						<th align="right">
							<font color="red">*</font>��ϵ��ַ
						</th>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"
								maxlength="25" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							��ϵ�绰
						</th>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width:210px"
								maxlength="12" />
						</td>
						<th align="right">
							<font color="red">*</font>��������
						</th>
						<td align="left">
							<html:text name="rs" property="yzbm" style="width:210px"
								maxlength="6" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�ƶ��绰
						</th>
						<td align="left">
							<html:text name="rs" property="yddh" style="width:210px"
								maxlength="11" />
						</td>
						<th align="right">
							��������
						</th>
						<td align="left">
							<html:text name="rs" property="email" style="width:210px"
								maxlength="20" onblur="if(this.value != '' && !isEmail(this.value)){alert('�����ʽ����ȷ��');this.focus();}"/>
						</td>
					</tr>
					</tbody>
					 <tfoot>
						      <tr>
						        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
						          <div class="btn">
						          	<button onclick="savebyqx()">
											�� ��
										</button>
												           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
				</table>
			</logic:notEmpty>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
    alert("�ύ�ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    alert("�ύʧ�ܣ������Ƿ��ظ��ύ��");
    </script>
				</logic:equal>
			</logic:notEmpty>
			
			<logic:notEmpty name="exists">
				<logic:equal name="exists" value="exists">
					<script>
    alert("ѧУ�����δͨ������ȴ�ѧУ���ͨ���Ժ��������룡");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
