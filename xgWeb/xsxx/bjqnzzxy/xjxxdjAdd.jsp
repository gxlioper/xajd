<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">		
		function commitData(){
			saveData('xjxxdj.do?method=xjxxdjAdd&type=save','xh');
		}		
	</script>
</head>
	<body onload="checkWinType();">		
		<html:form action="/xjxxdj.do" method="post"  enctype="multipart/form-data">
			<input type="hidden" id="disableEle" name="disableEle" value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
			<input type="hidden" id="url" name="url" value="/xjxxdj.do?method=xjxxdjAdd" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}-����</a>
				</p>
			</div>
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
   					alert("�������ѧ����Ч!");
   				</script>
			</logic:equal>	
			<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ����Ϣ�Ǽ�</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><font color="red">*</font>ѧ��</th>
					<td>
						<html:text name='rs' 
						           property="save_xh" 
						           readonly="readonly"
							       styleId="xh" 
							       onkeypress="autoFillStuInfo(event.keyCode,this);" maxlength="20" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							id="buttonFindStu">
							ѡ��
						</button>
					</td>
					<th>������</th>
					<td>
						<html:select name="rs" property="save_csd" styleId="csd">
						<html:option value=""></html:option>
						<html:options collection="ssList" property="ssdm" labelProperty="ssmc"/>
						</html:select>						
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
					</td>
					<th>Ѫ��</th>
					<td>
						<html:select name="rs" property="save_xx" styleId="xx">
							<html:option value=""></html:option>
							<html:option value="A">A</html:option>
							<html:option value="B">B</html:option>
							<html:option value="O">O</html:option>
							<html:option value="AB">AB</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>������ƴ</th>
					<td>
						<html:text name='rs' property="save_xmjp" styleId="xmjp" onkeyup="value=value.replace(/[^a-zA-Z]/g,'') " maxlength="50"/>
					</td>
					<th>����״��</th>
					<td>
						<html:select name="rs" property="save_hyzkdm" styleId="hyzkdm">
							<html:options collection="hyzkList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>
						<html:select name="rs" property="save_hkxzdm" styleId="hkxzdm">
							<html:option value=""></html:option>
							<html:option value="����">����</html:option>
							<html:option value="ũ��">ũ��</html:option>	
						</html:select>
					</td>
					<th>����</th>
					<td>
						<html:select name="rs" property="save_gbdm" styleId="gbdm">
							<html:options collection="gbList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>�˳�������ʼ</th>
					<td>
						<html:text property="save_ccqjqs" name="rs" styleId="ccqjqs" maxlength="40"></html:text>
					</td>
					<th>�˳����䵽��</th>
					<td>
						<html:text property="save_ccqjdd" name="rs" styleId="ccqjdd" maxlength="40"></html:text>
					</td>
				</tr>	
				<tr>					
					<th>У��</th>
					<td>
						<html:select name="rs" property="save_xqdm" styleId="xqdm">
							<html:options collection="xqList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th>ѧ�����</th>
					<td>
						<html:select name="rs" property="save_xslbdm" styleId="xslbdm">
							<html:options collection="xslbList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>			
				<tr>
					<th>�뵳ʱ��</th>
					<td>
						<html:text name='rs' property="save_rdsj" styleId="rdsj" onclick="return showCalendar('rdsj','y-mm-dd');"/>
					</td>
					<th>����ʱ��</th>
					<td>
						<html:text name='rs' property="save_rtsj" styleId="rtsj" onclick="return showCalendar('rtsj','y-mm-dd');"/>
					</td>					
				</tr>
				<tr>
					<th>֤������</th>
					<td>
						<html:select name="rs" property="save_zjlxdm" styleId="zjlxdm">
							<html:option value=""></html:option>
							<html:options collection="zjlxList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th>֤������</th>
					<td>
						<html:text name='rs' property="save_zjhm" styleId="zjhm" maxlength="20" onkeyup="value=value.replace(/[^\d|[^a-zA-Z]]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th>�۰�̨��</th>
					<td>
						<html:select name="rs" property="save_gatqdm" styleId="gatqdm">
							<html:options collection="gatqList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th>��ͥ����</th>
					<td>
						<html:text name='rs' property="save_jtcs" styleId="jtcs" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>����״��</th>
					<td>
						<html:text name='rs' property="save_jkzk" styleId="jkzk" maxlength="50"/>
					</td>
					<th>�ڽ�����</th>
					<td>
						<html:text name='rs' property="save_zjxy" styleId="zjxy" maxlength="50"/>
					</td>
				</tr>
				<tr>					
					<th>����Ա����</th>
					<td>
						<html:text name='rs' property="save_fdyxm" styleId="fdyxm" maxlength="25"/>
					</td>
					<th>����Ա��ϵ��ʽ</th>
					<td>
						<html:text name='rs' property="save_fdylxfs" styleId="fdylxfs" maxlength="50" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>
					</td>
				</tr>
				<tr>					
					<th>רҵ��</th>
					<td>
						<html:text name='rs' property="save_zyh" styleId="zyh" maxlength="10"/>
					</td>					
					<th>רҵ����</th>
					<td>
						<html:select name="rs" property="save_zykldm" styleId="zykldm">
							<html:options collection="zyklList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>					
					<th>�����Ƿ���У</th>
					<td>
						<html:select name="rs" property="save_dazxzt" styleId="dazxzt">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>					
					<th>�Ƿ���ְ</th>
					<td>
						<html:select name="rs" property="save_zzzt" styleId="zzzt">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>�Ƿ�ȫ����</th>
					<td>
						<html:select name="rs" property="save_qrzzt" styleId="qrzzt">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>					
					<th>�Ƿ�ѧ����</th>
					<td>
						<html:select name="rs" property="save_xlszt" styleId="xlszt">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>					
					<th>��ѧ����</th>
					<td>
						<html:text name='rs' property="save_rxjj" styleId="rxjj" maxlength="5"/>
					</td>					
					<th>��������</th>
					<td>
						<html:select name="rs" property="save_pylxdm" styleId="pylxdm">
							<html:options collection="pylxList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>					
					<th>ѧϰ��ʽ</th>
					<td>
						<html:select name="rs" property="save_xxxsdm" styleId="xxxsdm">
							<html:options collection="xxxsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>�����ί�е�λ</th>
					<td>
						<html:text name='rs' property="save_dxhwpdw" styleId="dxhwpdw" maxlength="100"/>						
					</td>
				</tr>
				<tr>					
					<th>�����ί�е�λ��ַ</th>
					<td>
						<html:text name='rs' property="save_dxhwpdw" styleId="dxhwpdwdz" maxlength="100"/>
					</td>					
					<th>�����ί�е�λ�ʱ�</th>
					<td>
						<html:text name='rs' property="save_dxhwpdwyb" styleId="dxhwpdwyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>						
					</td>
				</tr>
				<tr>					
					<th>�����ί�е�λ�绰</th>
					<td>
						<html:text name='rs' property="save_dxhwpdwdh" styleId="dxhwpdwdh" maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>
					</td>					
					<th>�Ƿ���������</th>
					<td>
						<html:select name="rs" property="save_sflhpy" styleId="sflhpy">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>����������λ</th>
					<td>
						<html:text name='rs' property="save_lhpydw" styleId="lhpydw" maxlength="100"/>
					</td>					
					<th>����������λ��ַ</th>
					<td>
						<html:text name='rs' property="save_lhpydwdz" styleId="lhpydwdz" maxlength="100"/>						
					</td>
				</tr>
				<tr>					
					<th>����������λ�ʱ�</th>
					<td>
						<html:text name='rs' property="save_lhpydwyb" styleId="lhpydwyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>����������λ��ϵ�绰</th>
					<td>
						<html:text name='rs' property="save_lhpydwdh" styleId="lhpydwdh" maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>						
					</td>
				</tr>
				<tr>					
					<th>�Ƿ�רҵѧλ</th>
					<td>
						<html:select name="rs" property="save_sfzyxw" styleId="sfzyxw">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>					
					<th>������ʽ</th>
					<td>
						<html:select name="rs" property="save_ldfs" styleId="ldfs">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��˶����">��˶����</html:option>
							<html:option value="��˶������">��˶������</html:option>
							<html:option value="˶������">˶������</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>���ѧ����ʽ</th>
					<td>
						<html:select name="rs" property="save_hdxlfsdm" styleId="hdxlfsdm">
							<html:options collection="hdxlfsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>�Ƿ�ѧ����ѧ��</th>
					<td>
						<html:select name="rs" property="save_xfzxszk" styleId="xfzxszk">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>						
					</td>
				</tr>
				<tr>					
					<th>רҵѧλ���</th>
					<td>
						<html:text name='rs' property="save_zyxwlb" styleId="lhpydw" maxlength="25"/>
					</td>					
					<th>Ӧ��ҵʱ��</th>
					<td>
						<html:text name='rs' property="save_ybysj" styleId="ybysj" onclick="return showCalendar('ybysj','y-mm-dd');"/>				
					</td>
				</tr>
				<tr>					
					<th>ѧϰ��ʽ</th>
					<td>
						<html:select name="rs" property="save_xxfsdm" styleId="xxfsdm">
							<html:options collection="xxfsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>������Ƭ</th>
					<td>
						<html:file property="bkzp" style="width:90%"></html:file>				
					</td>
				</tr>
				<tr>					
					<th>��ѧ��Ƭ</th>
					<td>
						<html:file property="rxzp" style="width:90%"></html:file>	
					</td>					
					<th>��ҵ��Ƭ</th>
					<td>
						<html:file property="byzp" style="width:90%"></html:file>						
					</td>
				</tr>
				<tr>					
					<th>��ǰѧ����ҵ֤����</th>
					<td>
						<html:text name='rs' property="save_dqxjbyzsbh" styleId="dqxjbyzsbh" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>��ǰѧ��ѧλ֤����</th>
					<td>
						<html:text name='rs' property="save_dqxjxwzsbh" styleId="dqxjxwzsbh" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>�������</th>
					<td>
						<html:select name="rs" property="save_xsbj" styleId="xsbj">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>		
					</td>					
					<th>ѧ����Դ</th>
					<td>
						<html:select name="rs" property="save_xslydm" styleId="xslydm">
							<html:options collection="xslyList" property="dm" labelProperty="mc"/>
						</html:select>					
					</td>
				</tr>
				<tr>					
					<th>��Դ����</th>
					<td>
						<html:select name="rs" property="save_lygbdm" styleId="lygbdm">
							<html:options collection="gbList" property="dm" labelProperty="mc"/>
						</html:select>		
					</td>					
					<th>��Դ�ޱ�</th>
					<td>
						<html:select name="rs" property="save_lyzbdm" styleId="lyzbdm">
							<html:options collection="zbList" property="dm" labelProperty="mc"/>
						</html:select>					
					</td>
				</tr>
				<tr>					
					<th>�Ͷ���ʽ</th>
					<td>
						<html:select name="rs" property="save_jdfsdm" styleId="jdfsdm">
							<html:options collection="jdfsList" property="dm" labelProperty="mc"/>
						</html:select>		
					</td>					
					<th>�Ͻ�ҵ����</th>
					<td>
						<html:text name='rs' property="save_bjyjl" styleId="bjyjl" maxlength="200"/>				
					</td>
				</tr>
				<tr>					
					<th>�Ƿ�У����ѧλ���</th>
					<td>
						<html:select name="rs" property="save_sfzxsyxwbj" styleId="sfzxsyxwbj">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>		
					</td>					
					<th>����ѧλʱ��</th>
					<td>
						<html:text name='rs' property="save_syxwsj" styleId="syxwsj" onclick="return showCalendar('syxwsj','y-mm-dd');"/>				
					</td>
				</tr>
				<tr>					
					<th>��ǰѧ����ҵ֤��ӡ�ƺ�</th>
					<td>
						<html:text name='rs' property="save_dqxjbyzsyzh" styleId="dqxjbyzsyzh" maxlength="10"/>
					</td>					
					<th>��ǰѧ��ѧλ֤��ӡ�ƺ�</th>
					<td>
						<html:text name='rs' property="save_dqxjxwzsyzh" styleId="dqxjxwzsyzh" maxlength="10"/>				
					</td>
				</tr>
				<tr>					
					<th>רҵ����</th>
					<td>
						<html:text name='rs' property="save_zyfx" styleId="zyfx" maxlength="30"/>
					</td>					
					<th>��סַ</th>
					<td>
						<html:text name='rs' property="save_xzz" styleId="xzz" maxlength="100"/>				
					</td>
				</tr>
				<tr>					
					<th>MSN</th>
					<td>
						<html:text name='rs' property="save_msn" styleId="msn" maxlength="30"/>
					</td>					
					<th>ͨѶ��ַ</th>
					<td>
						<html:text name='rs' property="save_txdz" styleId="txdz" maxlength="100"/>				
					</td>
				</tr>
				<tr>					
					<th>��������</th>
					<td>
						<html:text name='rs' property="save_yzbm" styleId="yzbm" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>��ҳ��ַ</th>
					<td>
						<html:text name='rs' property="save_zydz" styleId="zydz" maxlength="100"/>				
					</td>
				</tr>
				<tr>					
					<th>��һ��������</th>
					<td>
						<html:select property="save_dywyyzdm" styleId="dywyyzdm" name="rs">
							<html:options collection="wyyzList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>��һ����ȼ�</th>
					<td>
						<html:text name='rs' property="save_dywydj" styleId="dywydj" maxlength="10"/>				
					</td>
				</tr>
				<tr>					
					<th>�ڶ���������</th>
					<td>
						<html:select property="save_dewyyzdm" styleId="dewyyzdm" name="rs">
							<html:options collection="wyyzList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>					
					<th>�ڶ�����ȼ�</th>
					<td>
						<html:text name='rs' property="save_dewydj" styleId="dewydj" maxlength="10"/>				
					</td>
				</tr>
				<tr>					
					<th>¼ȡ֪ͨ���</th>
					<td>
						<html:text name='rs' property="save_lqtzsh" styleId="lqtzsh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>׼��(����)��</th>
					<td>
						<html:text name='rs' property="save_dewydj" styleId="dewydj" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>��ҵ��ѧ</th>
					<td>
						<html:text name='rs' property="save_byzx" styleId="byzx" maxlength="50"/>
					</td>					
					<th>��ҵ��ѧ�ʱ�</th>
					<td>
						<html:text name='rs' property="save_byzxyb" styleId="byzxyb" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>����/����</th>
					<td>
						<html:text name='rs' property="save_gn" styleId="gn" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>					
					<th>��һ����ɼ�</th>
					<td>
						<html:text name='rs' property="save_dywycj" styleId="dywycj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>�Ƿ�ת����</th>
					<td>
						<html:select property="save_sfzhk" styleId="sfzhk" name="rs">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>					
					<th>�ڶ�����ɼ�</th>
					<td>
						<html:text name='rs' property="save_dewycj" styleId="dewycj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>������Ŀһ</th>
					<td>
						<html:text name='rs' property="save_bkkm1" styleId="bkkm1" maxlength="50"/>
					</td>					
					<th>������Ŀһ�ɼ�</th>
					<td>
						<html:text name='rs' property="save_bkkm1cj" styleId="bkkm1cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>������Ŀ��</th>
					<td>
						<html:text name='rs' property="save_bkkm2" styleId="bkkm2" maxlength="50"/>
					</td>					
					<th>������Ŀ���ɼ�</th>
					<td>
						<html:text name='rs' property="save_bkkm2cj" styleId="bkkm2cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>������Ŀ��</th>
					<td>
						<html:text name='rs' property="save_bkkm3" styleId="bkkm3" maxlength="50"/>
					</td>					
					<th>������Ŀ���ɼ�</th>
					<td>
						<html:text name='rs' property="save_bkkm3cj" styleId="bkkm3cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>������Ŀ��</th>
					<td>
						<html:text name='rs' property="save_bkkm4" styleId="bkkm4" maxlength="50"/>
					</td>					
					<th>������Ŀ�ĳɼ�</th>
					<td>
						<html:text name='rs' property="save_bkkm4cj" styleId="bkkm4cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>������Ŀ��</th>
					<td>
						<html:text name='rs' property="save_bkkm5" styleId="bkkm5" maxlength="50"/>
					</td>					
					<th>������Ŀ��ɼ�</th>
					<td>
						<html:text name='rs' property="save_bkkm5cj" styleId="bkkm5cj" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>				
					</td>
				</tr>
				<tr>					
					<th>����</th>
					<td>
						<html:text name='rs' property="save_kq" styleId="kq" maxlength="50"/>
					</td>					
					<th>���������</th>
					<td>
						<html:select property="save_fsxlb" styleId="fsxlb" name="rs">
							<html:option value=""></html:option>
							<html:option value="A��">A��</html:option>
							<html:option value="B��">B��</html:option>
							<html:option value="C��">C��</html:option>
						</html:select>				
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            	<button type="button" class="button2" onclick="commitData()"
								style="width:80px" id="buttonSave">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
								id="buttonClose">
								�� ��
							</button>			
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>				
		 </div>
		</html:form>

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
