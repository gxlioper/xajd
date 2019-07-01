<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("sqdw").value;
		if(pk!=null && pk!=""){
		cqkjFunc.getPrincipalInfo(pk,function(data){
			document.getElementById("fzr").value = data.fzr;
			document.getElementById("gwlxdh").value = data.lxdh;
		});
		}		
	}
	
	function changeGwmc(){
		var sqdw = document.getElementById("sqdw").value;
		cqkjFunc.getGwmcList(sqdw,function (data){
				if (data != null && typeof data == 'object') {
					DWRUtil.removeAllOptions("xmdm");			
					DWRUtil.addOptions("xmdm",data,"gwdm","gwmc");
				}
			});
	}
	</script>
</head>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc-kh" />
		<input type="hidden" id="url" name="url" value="/qgzxHgsq.do" />
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ���� - ��������</a>
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

			<logic:notEqual name="gwExists" value="yes">
				<script>
  					alert("�������ڹ���ѧ�ڸ�ѧ���������뻻��!");
  					</script>
			</logic:notEqual>

			<logic:equal name="sqsjFlag" value="1">
				<script>
   				alert("�����趨ʱ�䷶Χ��,�ݲ���������!");
   				location.href="about:blank";
   				</script>
			</logic:equal>

			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>��д���������</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>
					<th>����</th>
					<td>
						<bean:write name='rs' property="xm" />
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>�ָ�</th>
					<td>
						<input type="hidden" name="xmdmmodi" id="xmdmmodi" value="${rs.gwdmgwsbsj}"/>
						<html:select name="rs" property="gwdmgwsbsj" styleId="xmdm"
							style="width:150px" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th><span class="red">*</span>�������λ1</th>
					<td>
						<html:select name="rs" property="hgdmhgsj1" styleId="xmdm"
							style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>�������λ2</th>
					<td>
						<html:select name="rs" property="hgdmhgsj2" styleId="xmdm"
							style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th>�������λ3</th>
					<td>
						<html:select name="rs" property="hgdmhgsj3" styleId="xmdm"
							style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>������ò</th>
					<td>
						<bean:write name='rs' property="zzmmmc" />
					</td>
					<th>סַ</th>
					<td>
						<bean:write name='rs' property="jtdz" />
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						<bean:write name='rs' property="xb" />
					</td>
					<th>ѧ��</th>
					<td>
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						<bean:write name='rs' property="nj" />
					</td>
					<th>ѧ��</th>
					<td>
						${rs.xqmc}
						<html:hidden name="rs" property="xq"></html:hidden>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<th>���</th>
					<td>
						<html:text name="rs" property="nd" readonly="true"></html:text>
					</td>						
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						<bean:write name='rs' property="zymc" />
					</td>
					<th>������</th>
					<td>
						<input type="text" id="fzr" name="fzr" readonly="readonly"
							value="<bean:write name="rs" property="fzr"/>" />
					</td>
				</tr>
				<tr>
					<th>�༶</th>
					<td>
						<bean:write name='rs' property="bjmc" />
					</td>
					<th>��ϵ�绰</th>
					<td>
						<input type="text" id="gwlxdh" name="gwlxdh" readonly="readonly"
							value="<bean:write name="rs" property="gwlxdh"/>"
							disabled="disabled" />
					</td>
				</tr>
				<tr>
					<th>�Ƿ�ƶ����</th>
					<td>
						<bean:write name='rs' property="sfpks" />
					</td>
					<th>������ϵ�绰</th>
					<td>
						<html:text property="lxdh" name="rs" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td colspan="3">
						<html:textarea name='rs' property='xssq' styleId="sqly"
							style="width:99%" rows='5' onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr>
					<th>�س�</th>
					<td colspan="3">
						<html:textarea name='rs' property='yhtc' styleId="yhtc"
							style="width:99%" rows='5' onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr>
					<th>����ʱ��</th>
					<logic:present name="kxList">
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" class="formlist">
								<tr>
									<th>
										ʱ��
									</th>
									<th>
										����һ
									</th>
									<th>
										���ڶ�
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
								</tr>
								<logic:iterate id="kxsj" name="kxList">
									<tr>
										<td>
											${kxsj.sj}
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}1" value="��" />
											<input type="hidden" name="index${kxsj.sjIndex}1"
												value="${kxsj.mon}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}2" value="��" />
											<input type="hidden" name="index${kxsj.sjIndex}2"
												value="${kxsj.tue}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}3" value="��" />
											<input type="hidden" name="index${kxsj.sjIndex}3"
												value="${kxsj.wed}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}4" value="��" />
											<input type="hidden" name="index${kxsj.sjIndex}4"
												value="${kxsj.thu}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}5" value="��" />
											<input type="hidden" name="index${kxsj.sjIndex}5"
												value="${kxsj.fri}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}6" value="��" />
											<input type="hidden" name="index${kxsj.sjIndex}6"
												value="${kxsj.sat}" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}7" value="��" />
											<input type="hidden" name="index${kxsj.sjIndex}7"
												value="${kxsj.sun}" />
										</td>

									</tr>
								</logic:iterate>
							</table>
								<script language="javascript">
									for(var i=0;i<7;i++){
										for(var j=1;j<8;j++){
										 	if(document.getElementById("index"+i+j)){
										 		if(document.getElementById("index"+i+j).value==1){
													document.getElementById(i+""+j).checked="checked";
											    }
										 	}
										}
									}
								</script>
						</td>
					</logic:present>
					<logic:notPresent name="kxList">
						<td height="22" colspan="3">
							<table id="tbSj" class="formlist">
								<tr>
									<th>
										ʱ��
									</th>
									<th>
										����һ
									</th>
									<th>
										���ڶ�
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
									<th>
										������
									</th>
								</tr>
								<logic:iterate id="kxsj" name="whkxList">
									<tr>
										<td>
											${kxsj.sj}
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
										</td>
										<td align="center">
											<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
										</td>
									</tr>
								</logic:iterate>
							</table>
						</td>
					</logic:notPresent>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="formlist">
							<tr>
								<td width="20">ԭ<br/>��<br/>��<br/>��<br/>λ<br/>��<br/>��</td>
								<td valign="bottom">ǩ�����£�<br/><br/>���ڣ�</td>
								<td width="20">��<br/>��<br/>��<br/>��<br/>��<br/>��<br/>λ<br/>��<br/>��</td>
								<td valign="bottom">ǩ�����£�<br/><br/>���ڣ�</td>
								<td width="20">��<br/>��<br/>��<br/>ѧ<br/>��<br/>��<br/>��<br/>��<br/>��</td>
								<td valign="bottom">ǩ�����£�<br/><br/>���ڣ�</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<th>��ע</th>
					<td colspan="3">
						<html:textarea name='rs' property='bz' styleId="bz"
							  cols="80" rows='5' onblur="chLeng(this,60)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button type="button" class="button2" onclick="zgdzdx_chkisDataExist('xh-xmdm');">
							�� �� �� ��
						</button>
						<logic:notPresent name="zdy">
						<button type="button" class="button2" onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">
							�� ӡ Ԥ ��
						</button>
						</logic:notPresent>
						<logic:present name="zdy">
						<button type="button" class="button2"
							onclick="printReport('qgzx_bb_gwsqb.do?gwdm=')">
							�� ӡ Ԥ ��
						</button>
						</logic:present>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</logic:notEmpty>

		<logic:notEmpty name="inserted">
			<logic:equal name="inserted" value="ok">
				<script>
				    alert("����ɹ���");
				</script>
			</logic:equal>
			<logic:equal name="inserted" value="no">
				<script>
					var html_reason= '<bean:write name="reason"/>';
  						alert("����ʧ�ܣ�"+html_reason);
   				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
	</body>
</html>
