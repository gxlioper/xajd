<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- ͷ�ļ� -->
<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxZgkd.js'></script>
	<script type='text/javascript' src='js/check.js'></script>	
	<script language="javascript" src="js/xgutil.js"></script>
	
	<script>
		function zgdzdxSaveXsxx(){
			var sfzc = document.getElementById('sfzc').value;
			var nfby = document.getElementById('nfby').value;
			var sfzcV = document.getElementById('sfzcV').value;
			var nfbyV = document.getElementById('nfbyV').value;
			var xh = document.getElementById('xh').value;
			var flag = true;	
			//�жϱ�����д�ֶ��Ƿ���д
			//����ʱ�ж�ѧ����Ϣ�Ƿ��Ѿ�����
			var result = true;
			dwr.engine.setAsync(false);
			var yhjs = val('yhjs');
			if(yhjs == "stu"){
				yhjs = "student";
			}			
			xsxxZgkd.getBtzdByYh(yhjs,"xsxxb",function(data){
				if(data != null){
					for(var i=0; i<data.length; i++){
						if(ele(data[i].en) && ele(data[i].en).value == ""){
							if(data[i].en == "syd"){
								alert("��Դ�ػ���Դ��������Ϊ�գ�");
							}else{
								alert(data[i].cn + "����Ϊ�գ�");								
							}
							result = false;
							break;
						}
					}
				}				
			});
			xsxxZgkd.getBtzdByYh(yhjs,"xsfzxxb",function(data){
				if(data != null){
					for(var i=0; i<data.length; i++){
						if(ele(data[i].en) && ele(data[i].en).value == ""){
							alert(data[i].cn + "����Ϊ�գ�");
							result = false;
							break;
						}
					}
				}				
			});
			dwr.engine.setAsync(true);
			if(result){
				var tvalue = ["xh", "xm", "xy", "zy", "bj", "nj"];
				for(var i=0;i<tvalue.length;i++){
					if(document.getElementById(tvalue[i]).value==""){
					alert("�뽫�����ŵ���Ŀ��д������");
					return false;
					}
				}
	         	refreshForm('xsxxZgdzdx.do?method=saveStuinfoModi');
	          	$("buttonSave").disabled=true;
          }
    }
	 function sfbyChek(){
				var nfby = document.getElementById('nfby').value;			
				var xh = document.getElementById('xh').value;
				    if(nfby=='��'){
					  //�ܷ��ҵ�ж�
					  getStuDetails.zgdzdxCheckBy(xh,function(data){
						   if(data==false){
							  alert('��ѧ���������ϱ�ҵ������');
							  document.getElementById("nfby").options[0].selected=true;				
							  return false;						
						   }
					  });
					}									
	 }
	 
	 
	function sfzcChek(){
	            var sfzc = document.getElementById('sfzc').value;
				var xh = document.getElementById('xh').value;  
				if(sfzc=='��ע��'){				
					//�Ƿ�ע���ж�
					getStuDetails.zgdzdxCheckZc(xh,function(data){
						if(data==false){
							alert('��ѧ����������ע����������ȷ�Ϸ����Ƿ���壡');
							document.getElementById("sfzc").options[0].selected=true;				
							return false;	
						}
					});
				}
	 }
	</script>
</head>
	<body onload="showColumns()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="<bean:write name="zdList"/>"/>
			<input type="hidden" name="url" id="url" value="/xsxx/zgdzdx/zgdzdx_stu_modinfo.jsp"/>
			<input type="hidden" name="redirect" id="redirect" value=""/>
			<input type="hidden" name="variable" id="variable" value=""/>
			<input type="hidden" name="sfzcV" id="sfzcV" value="${rs.sfzc}"/>
			<input type="hidden" name="nfbyV" id="nfbyV" value="${rs.nfby}"/>
			<input type="hidden" name="yhjs" id="yhjs" value="${userType}"/>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ</a>
				</p>
			</div>
			<logic:equal name="userType" value="admin" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</p>
			</logic:equal>

			<logic:equal name="userType" value="xx" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</p>
			</logic:equal>

			<logic:equal value="student" name="userOnLine">
			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("�����趨ʱ�䷶Χ��,�ݲ����Ÿù���!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
			</logic:equal>

			<div class="tab">
			<table class="formlist" id="rsTable" width="100%">
				<thead>
					<tr>
					<th colspan="4">
						<span>������Ϣ�޸�
						<logic:present name="shjg">
						<font color="red">
						(���δͨ��)
						</font>
						</logic:present>
						</span>
					</th>
					</tr>
				</thead>
				<tbody>
					<tr>			
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<html:text property="xh" name="rs" disabled="true" styleId="xh" maxlength="20"/>	
							<html:hidden property="xh" name="rs"/>			
							<logic:equal value="xy" name="userType">
								<button type="button"
									onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/>
						</td>
					</tr>					
					<tr>
						<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" name="rs" disabled="true" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
							</html:select>
						</td>
						<th><span class="red">*</span>רҵ</th>
						<td>
							<html:select property="zydm" name="rs" disabled="true" styleId="zy">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" name="rs" disabled="true" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th><span class="red">*</span>�༶</th>
						<td>
							<html:select property="bjdm" name="rs" disabled="true" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>�Ա�</th>
						<td>
							<html:radio property="xb" value="��" name="rs" disabled="true" styleId="xbn">��</html:radio>
							<html:radio property="xb" value="Ů" name="rs" disabled="true"  styleId="xbv">Ů</html:radio>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xz" name="rs" disabled="true" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"/>��
						</td>
					</tr>					
					<tr>
						<th>����</th>
						<td>
							<html:select property="mz" name="rs" disabled="true" styleId="mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>������ò</th>
						<td>
							<html:select property="zzmm" name="rs" disabled="true" styleId="zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>ѧ��״̬</th>
						<td>
							<html:select property="xjztm" name="rs" disabled="true" styleId="xjztm">
								<html:option value=""></html:option>
								<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
							</html:select>
						</td>
						<th>��������</th>
						<td>
							<html:text property="csrq" name="rs" disabled="true" readonly="" onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq"/>
						</td>
					</tr>					
					<tr>
						<th>����ƴ��</th>
						<td>
							<html:text property="xmpy" name="rs" disabled="true" styleId="xmpy" maxlength="32"/>
						</td>
						<th>������</th>
						<td>
							<html:text property="cym" name="rs" disabled="true" styleId="cym" maxlength="16"/>
						</td>
					</tr>					
					<tr>
						<th>���</th>
						<td>
							<html:text property="sg" name="rs" disabled="true" styleId="sg" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> ����
						</td>
						<th>����</th>
						<td>
							<html:text property="tz" name="rs" disabled="true" styleId="tz" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> ǧ��
						</td>
					</tr>					
					<tr>
						<th>���֤��</th>
						<td>
							<html:text property="sfzh" name="rs" disabled="true"  styleClass="text_nor" onblur="if(!checkSfzh(this)){this.focus();}"  styleId="sfzh" maxlength="18"/>
						</td>
						<th>�س�</th>
						<td>
							<html:text property="tc" name="rs" disabled="true" styleId="tc" maxlength="32"/>
						</td>
					</tr>					
					<tr>
						<th>������ʽ</th>
						<td>
							<html:text property="pyfs" name="rs" disabled="true" styleId="pyfs" maxlength="32"/>
						</td>
						<th>�������</th>
						<td>
							<html:text property="pycc" name="rs" disabled="true" styleId="pycc" maxlength="32"/>
						</td>
					</tr>					
					<tr>
						<th>��ѧ��ʽ</th>
						<td>
							<html:text property="rxfs" name="rs" disabled="true" styleId="rxfs" maxlength="32"/>
						</td>
						<th>�������</th>
						<td>
							<html:text property="kslb" name="rs" disabled="true" styleId="kslb" maxlength="32"/>
						</td>
					</tr>
					<tr>
						<th>��ѧʱ��</th>
						<td>
							<html:text property="rxrq" name="rs" disabled="true" styleId="rxrq" onclick="return showCalendar('rxrq','y-mm-dd');"/>
						</td>
						<th>������</th>
						<td>
							<html:text property="ksh" name="rs" disabled="true"
										styleId="ksh" maxlength="32" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
					</tr>
					<tr>
						<th>��Դ����</th>
						<td>
							<html:text property="syd" name="rs" disabled="true" styleId="syd" maxlength="25"/>
						</td>
						<th>����</th>
						<td>
							<html:text property="jg" name="rs" disabled="true" styleId="jg" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>��������</th>
						<td>
							<html:text property="dzyx" name="rs" disabled="true" styleId="dzyx" maxlength="32"/>
						</td>
						<th>��ϵ�绰</th>
						<td>
							<html:text property="lxdh" name="rs" disabled="true" styleId="lxdh" maxlength="15"/>
						</td>
					</tr>					
					<tr>
						<th>�ֻ�����</th>
						<td>
							<html:text property="sjhm" name="rs" disabled="true" styleId="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						<th>��ҵѧУ</th>
						<td>
							<html:text property="rxqdw" name="rs" disabled="true" styleId="rxqdw" maxlength="125"/>				
						</td>
					</tr>
					<tr>
						<th>��ѧ�꼶</th>
						<td colspan="3">
							<html:select property="rxnj" name="rs" disabled="true" styleId="rxnj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>�Ƿ��ҵ��</th>
						<td>
							<html:select property="sfbys" name="rs" styleId="sfbys" disabled="true">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<th>�Ƿ��ѱ�ҵ</th>
						<td>
							<html:select property="sfyby" name="rs" styleId="sfyby" disabled="true">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>�ܷ��ҵ</th>
						<td colspan="3">
							<html:select property="nfby" name="rs" disabled="true" styleId="nfby" onchange="sfbyChek()">
								<html:option value=""></html:option>
								<html:options collection="nfbyList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					
					<tr>
						<th>�Ƿ�ע��</th>
						<td>
							<logic:equal value="" property="sfzc" name="rs">
								<html:select property="sfzc" name="rs" styleId="sfzc" disabled="true" onchange="sfzcChek()"  value="δע��">
								<html:option value=""></html:option>
								<html:options collection="sfzcList" property="en" labelProperty="cn"/>
								</html:select>
							</logic:equal>
							<logic:notEqual value="" property="sfzc" name="rs">
								<html:select property="sfzc" name="rs" styleId="sfzc" disabled="true" onchange="sfzcChek()" >
								<html:option value=""></html:option>
								<html:options collection="sfzcList" property="en" labelProperty="cn"/>
								</html:select>
							</logic:notEqual>					
						</td>	
						<th>��ҵʱ��</th>
						<td>
							<html:text property="byny" name="rs" disabled="true"
								readonly="true" onclick="return showCalendar('byny','y-mm-dd');"
								styleId="byny" />
						</td>		
					</tr>
					<tr>
						<th>�Ƿ���У</th>
						<td colspan="3">
							<html:select property="sfzx" name="rs" styleId="sfzx" disabled="true">
								<html:option value=""></html:option>
								<html:option value="��У">��У</html:option>
								<html:option value="����У">����У</html:option>
							</html:select>
						</td>						
					</tr>
					<tr>
						<th>ѧ������</th>
						<td>
							<html:select property="xslx" name="rs"  disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xsLxList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>ѧ�����</th>
						<td>
							<html:select property="xslb" name="rs"  disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xsLbList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>��ע</th>
						<td colspan="3">
							<html:textarea property="bz" disabled="true" name="rs" rows="5" cols="80" onblur="chLeng(this,500)"></html:textarea>
						</td>					
					</tr>
					<tr>
						<th>��ͥ��ַ</th>
						<td>
							<html:text property="jtszd" name="rs" disabled="true" styleId="jtszd" maxlength="25"/>
						</td>
						<th>��ͥ��ϵ�绰</th>
						<td>
							<html:text property="lxdh1" name="rs" disabled="true" styleId="lxdh1" maxlength="25" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
					</tr>
					<tr>
						<th>��ͥ�ʱ�</th>
						<td>
							<html:text property="yb" name="rs" disabled="true" styleId="yb" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						<th>��ͥ�������</th>
						<td>
							<html:text property="jjzk" name="rs" disabled="true" styleId="jjzk" maxlength="100"/>
						</td>
					</tr>
					</tbody>					
					
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									<span>ѧ����ͥ��Ա��Ϣ1</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr id="jt1">
					<td colspan="4">					
					<table class="formlist" width="100%">	
					<tbody>				
						<tr>
							<th>����</th>
							<td>
								<html:text property="jtcy1_xm" name="rs" disabled="true" styleId="jtcy1_xm" maxlength="25"/>
							</td>
							<th>�뱾�˹�ϵ</th>
							<td>
								<html:text property="jtcy1_gx" name="rs" disabled="true" styleId="jtcy1_gx" maxlength="10"/>
							</td>
						</tr>						
						<tr>
							<th>��������</th>
							<td>
								<html:text property="jtcy1_nl" name="rs" disabled="true" styleId="jtcy1_nl" readonly="" onclick="return showCalendar('jtcy1_nl','y-mm-dd');" styleId="jtcy1_nl"/>
							</td>
							<th>���֤��</th>
							<td>
								<html:text property="jtcy1_sfzh" name="rs" disabled="true" styleId="jtcy1_sfzh" maxlength="18"/>
							</td>
						</tr>
						
						<tr>
							<th>����</th>
							<td>
								<html:select property="jtcy1_mz" name="rs" disabled="true" styleId="jtcy1_mz">
									<html:option value=""></html:option>
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
								</html:select>
							</td>
							<th>������ò</th>
							<td>
								<html:select property="jtcy1_zzmm" name="rs" disabled="true" styleId="jtcy1_zzmm">
									<html:option value=""></html:option>
									<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th>ְҵ</th>
							<td>
								<html:text property="jtcy1_zy" name="rs" disabled="true" styleId="jtcy1_zy" maxlength="10"/>
							</td>
							<th>ְ��</th>
							<td>
								<html:text property="jtcy1_zw" name="rs" disabled="true" styleId="jtcy1_zw" maxlength="10"/>
							</td>
						</tr>							
						<tr>
							<th>������λ�绰</th>
							<td>
								<html:text property="jtcy1_lxdh1" name="rs" disabled="true" styleId="jtcy1_lxdh1" maxlength="25"/>
							</td>
							<th>���˵绰</th>
							<td>
								<html:text property="jtcy1_lxdh2" name="rs" disabled="true" styleId="jtcy1_lxdh2" maxlength="25"/>
							</td>
						</tr>						
						<tr>
							<th>������ַ</th>
							<td colspan="3">
								<html:text property="jtcy1_gzdz" name="rs" disabled="true" styleId="jtcy1_gzdz" maxlength="25" style="width:100%"/>
							</td>			
						</tr>	
					</tbody>		
					</table>
					</td>
					</tr>
					</tbody>

					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
								<span>ѧ����ͥ��Ա��Ϣ2</span>
							</th>
						</tr>
					</thead>

					<tbody>
					<tr id="jt2">
					<td colspan="4">					
					<table class="formlist" width="100%">
					<tbody>				
					<tr>
						<th>����</th>
						<td>
							<html:text property="jtcy2_xm" name="rs" disabled="true" styleId="jtcy2_xm" maxlength="25"/>
						</td>
						<th>�뱾�˹�ϵ</th>
						<td>
							<html:text property="jtcy2_gx" name="rs" disabled="true" styleId="jtcy2_gx" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>��������</th>
						<td>
							<html:text property="jtcy2_nl" name="rs" disabled="true" styleId="jtcy2_nl" readonly="" onclick="return showCalendar('jtcy2_nl','y-mm-dd');" styleId="jtcy2_nl"/>
						</td>
						<th>���֤��</th>
						<td>
							<html:text property="jtcy2_sfzh" name="rs" disabled="true" styleId="jtcy2_sfzh" maxlength="18"/>
						</td>
					</tr>					
					<tr>
						<th>����</th>
						<td>
							<html:select property="jtcy2_mz" name="rs" disabled="true" styleId="jtcy2_mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>������ò</th>
						<td>
							<html:select property="jtcy2_zzmm" name="rs" disabled="true" styleId="jtcy2_zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>ְҵ</th>
						<td>
							<html:text property="jtcy2_zy" name="rs" disabled="true" styleId="jtcy2_zy" maxlength="10"/>
						</td>
						<th>ְ��</th>
						<td>
							<html:text property="jtcy2_zw" name="rs" disabled="true" styleId="jtcy2_zw" maxlength="10"/>
						</td>
					</tr>						
					<tr>
						<th>������λ�绰</th>
						<td>
							<html:text property="jtcy2_lxdh1" name="rs" disabled="true" styleId="jtcy2_lxdh1" maxlength="25"/>
						</td>
						<th>���˵绰</th>
						<td>
							<html:text property="jtcy2_lxdh2" name="rs" disabled="true" styleId="jtcy2_lxdh2" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>������ַ</th>
						<td colspan="3">
							<html:text property="jtcy2_gzdz" name="rs" disabled="true" styleId="jtcy2_gzdz" maxlength="25" style="width:100%"/>
						</td>			
					</tr>
					</tbody>
					</table>
					</td>
					</tr>
					<tbody>
					<thead>
						<tr>
							<th colspan="4" style="cursor:hand"
								onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									<span>ѧ����ͥ��Ա��Ϣ3</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr id="jt3">
					<td colspan="4">
					<table class="formlist" width="100%">
					<tbody>				
					<tr>
						<th>����</th>
						<td>
							<html:text property="jtcy3_xm" name="rs" disabled="true" styleId="jtcy3_xm" maxlength="25"/>
						</td>
						<th>�뱾�˹�ϵ</th>
						<td>
							<html:text property="jtcy3_gx" name="rs" disabled="true" styleId="jtcy3_gx" maxlength="10"/>
						</td>
					</tr>					
					<tr>
						<th>��������</th>
						<td>
							<html:text property="jtcy3_nl" name="rs" disabled="true" styleId="jtcy3_nl" readonly="" onclick="return showCalendar('jtcy3_nl','y-mm-dd');" styleId="jtcy3_nl"/>
						</td>
						<th>���֤��</th>
						<td>
							<html:text property="jtcy3_sfzh" name="rs" disabled="true" styleId="jtcy3_sfzh" maxlength="18"/>
						</td>
					</tr>					
					<tr>
						<th>����</th>
						<td>
							<html:select property="jtcy3_mz" name="rs" disabled="true" styleId="jtcy3_mz">
								<html:option value=""></html:option>
								<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
							</html:select>
						</td>
						<th>������ò</th>
						<td>
							<html:select property="jtcy3_zzmm" name="rs" disabled="true" styleId="jtcy3_zzmm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
							</html:select>
						</td>
					</tr>					
					<tr>
						<th>ְҵ</th>
						<td>
							<html:text property="jtcy3_zy" name="rs" disabled="true" styleId="jtcy3_zy" maxlength="10"/>
						</td>
						<th>ְ��</th>
						<td>
							<html:text property="jtcy3_zw" name="rs" disabled="true" styleId="jtcy3_zw" maxlength="10"/>
						</td>			
					</tr>
						
					<tr>
						<th>������λ�绰</th>
						<td>
							<html:text property="jtcy3_lxdh1" name="rs" disabled="true" styleId="jtcy3_lxdh1" maxlength="25"/>
						</td>
						<th>���˵绰</th>
						<td>
							<html:text property="jtcy3_lxdh2" name="rs" disabled="true" styleId="jtcy3_lxdh2" maxlength="25"/>
						</td>
					</tr>					
					<tr>
						<th>������ַ</th>
						<td colspan="3">
							<html:text property="jtcy3_gzdz" name="rs" disabled="true" styleId="jtcy3_gzdz" maxlength="25" style="width:100%"/>
						</td>			
					</tr>
					</tbody>
					</table>
					</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button type="button" class="button2" id="buttonSave"
							onclick="zgdzdxSaveXsxx()">
							������Ϣ
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");				
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");				
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
