<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript">
	function thisCommit(){
		var yesNo = document.getElementById("yesNo").value;
		var userName = document.getElementById("userName").value;
		var xxdm = document.getElementById("xxdm").value;
		var pkString = document.getElementById("pkVal").value + "!!SplitOneSplit!!";
		var userType = document.getElementById("userType").value;
		if(yesNo!=null && yesNo=='ͨ��'){
			if(xxdm == "13742"){//������һְҵ����ѧԺ
				cqkjFunc.checkFprs(pkString,userType,function(data){
						if(data!=null && data.length>0){
							var message = "";
							for (i=0; i<data.length; i++){
								if(data[i]!=null && data[i]!=""){
									message = message+"\n" + data[i];
								}
							}
							if(message!=""){
								alert("�޷����ͨ����"+message);
								return false;
							}else{
								showTips();
								refreshForm('/xgxt/postStuChkOne.do?act=save');
								if($("buttonSave")){$("buttonSave").disabled=true;}
							}						
						}
					});
			}else if(xxdm=='12703'){ //��ͨ����
				cqkjFunc.checkXsgwsqqk(pkString,function(mes){
					if(mes!="" && mes !=null){
						cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
							if(data!=null && data.length>0){
								var message = "";
								for (i=0; i<data.length; i++){
									if(data[i]!=null && data[i]!=""){
										message = message+"\n" + data[i];
									}
								}
								if(message!=""){
									if(xxdm == "11057"){
										//�㽭�Ƽ�ѧԺ
										if(confirm(message+"\n��ȷ������������")){
											showTips();
											refreshForm('/xgxt/postStuChkOne.do?act=save');
											if($("buttonSave")){$("buttonSave").disabled=true;}
										}							
									}else{
										alert("�޷����ͨ����"+message);
										return false;
									}								
								}else{
									if(confirm(mes + "\n��ȷ������������")){
										showTips();
										refreshForm('/xgxt/postStuChkOne.do?act=save');
										if($("buttonSave")){$("buttonSave").disabled=true;}
									}else{
										return false;
									}
								}						
							}
						});		
					}else{
						cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
							if(data!=null && data.length>0){
								var message = "";
								for (i=0; i<data.length; i++){
									if(data[i]!=null && data[i]!=""){
										message = message+"\n" + data[i];
									}
								}
								if(message!=""){
									if(xxdm == "11057"){
										//�㽭�Ƽ�ѧԺ
										if(confirm(message+"\n��ȷ������������")){
											showTips();
											refreshForm('/xgxt/postStuChkOne.do?act=save');
											if($("buttonSave")){$("buttonSave").disabled=true;}
										}							
									}else{
										alert("�޷����ͨ����"+message);
										return false;
									}								
								}else{
									showTips();
									refreshForm('/xgxt/postStuChkOne.do?act=save');
									if($("buttonSave")){$("buttonSave").disabled=true;}
								}						
							}
						});		
					}
				});
			}else{		
				cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
						if(data!=null && data.length>0){
							var message = "";
							for (i=0; i<data.length; i++){
								if(data[i]!=null && data[i]!=""){
									message = message+"\n" + data[i];
								}
							}
							if(message!=""){
								if(xxdm == "11057"){
									//�㽭�Ƽ�ѧԺ
									if(confirm(message+"\n��ȷ������������")){
										showTips();
										refreshForm('/xgxt/postStuChkOne.do?act=save');
										if($("buttonSave")){$("buttonSave").disabled=true;}
									}							
								}else{
									alert("�޷����ͨ����"+message);
									return false;
								}								
							}else{
								showTips();
								refreshForm('/xgxt/postStuChkOne.do?act=save');
								if($("buttonSave")){$("buttonSave").disabled=true;}
							}						
						}
					});				
				
			}
		}else{
			showTips('���������У����Ժ�......');
			refreshForm('/xgxt/postStuChkOne.do?act=save');
			if($("buttonSave")){$("buttonSave").disabled=true;}
		}
	}
	</script>
</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" id="pkVal" />
			<input type="hidden" name="gwdm" value="<bean:write name="rs" property="gwdm"/>" />
			<input type="hidden" name="xh" value="<bean:write name="rs" property="xh"/>" />
			<input type="hidden" name="pk" value="<bean:write name="rs" property="pk"/>" id="pk"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>" />
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em>
					<a>
						<%--��ɳ����--%>
						<logic:equal value="10827" name="xxdm">
							ѧ���幤 - ��� - ѧ��������� - �������
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
							�ڹ���ѧ - ��� - ѧ��������� - �������
						</logic:notEqual>
					</a>
				</p>
			</div>
			
			<div class="tab">
		 	<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>����ѧ���������</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>ѧ��</th>
					<td>
						<bean:write name="rs" property="xh" />
					</td>
					<th>���</th>
					<td>
						<bean:write name="rs" property="nd" />
					</td>
				</tr>

				<tr>
					<th>����</th>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<th>ѧ��</th>
					<td>
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						<bean:write name="rs" property="xb" />
					</td>
					<th>��λ����</th>
					<td>
						<bean:write name="rs" property="gwdm" />
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
					<th>����ʱ��</th>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<th>
						<%--�㽭����ְҵ����ѧԺ--%>
						<logic:equal value="12861" name="xxdm">
							�Ƿ�������
						</logic:equal>
						<logic:notEqual value="12861" name="xxdm">						
							�Ƿ�������
						</logic:notEqual>
					</th>
					<td>
						<bean:write name="rs" property="sfpks" />
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<th>��ϵ�绰</th>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<%--����ɽ��ѧ--%>
				<logic:equal value="10419" name="xxdm">
					<tr>
						<th>��λҪ��</th>
						<td colspan="3">
							<bean:write name="gwyq" />
						</td>
					</tr>
					<tr>
						<th>ѧ�������ϵ�Ҫ��</th>
						<td colspan="3">
							<bean:write name="rs" property="tsxx" />
						</td>
					</tr>
				</logic:equal>
				<%--end����ɽ��ѧ--%>

				<%--����Ƽ�ѧԺ--%>
				<logic:equal value="11551" name="xxdm">
					<tr>
						<th>�ɲμ��ڹ���ѧʱ��</th>
						<td colspan="3">
							<logic:notEmpty name="kxList">
								<table border="0" cellpadding="0" cellspacing="0" class="formlist">
									<thead>
									<tr>
										<th align="center">
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
									</thead>
									<tbody>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}6"
													value="${kxsj.sat}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}7"
													value="${kxsj.sun}" />
											</td>

										</tr>
									</logic:iterate>
									</tbody>
								</table>
									<script>
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
							</logic:notEmpty>
						</td>
					</tr>
				</logic:equal>
				<%--end����Ƽ�ѧԺ--%>

				<tr>
					<th>�༶</th>
					<td width="30%">
						<bean:write name="rs" property="bjmc" />
					</td>
					<th>�ֻ�����</th>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>						
				</tr>
				<tr>
					<th>����</th>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>	
					<th>������ò</th>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
					
                </tr>
				<!--���㽭�Ƽ�ѧԺ-->
				<logic:notEqual value="11057" name="xxdm">
				<tr>
					<th>�¾������</th>
					<td>
						<bean:write name="rs" property="yjshf"/>
					</td>
					<th>��ͥ���</th>
					<td>
						<html:checkbox property="sfgr" name="rs" value="��" disabled="true">�¶�</html:checkbox>
						<html:checkbox property="sfdq" name="rs" value="��" disabled="true">����</html:checkbox>
						<html:checkbox property="sfdbh" name="rs" value="��" disabled="true">�ͱ���</html:checkbox>
						<html:checkbox property="sfyfdx" name="rs" value="��" disabled="true">�ŷ�����</html:checkbox>
					</td>
                </tr>
				</logic:notEqual>
				<!--end���㽭�Ƽ�ѧԺ-->
				
				<%--�㶫Ů��ְҵ����ѧԺ--%>
				<logic:equal value="12742" name="xxdm">
					<tr>
					<th>��ͥ������</th>
					<td colspan="3">
						${rs.jtnsr}
					</td>
					</tr>
				</logic:equal>	
				<%--end�㶫Ů��ְҵ����ѧԺ--%>
				<tr>
					<th>�к��س�</th>
					<td colspan="3">
						<bean:write name="rs" property="yhtc"/>
					</td>
                </tr>
				<tr>
					<th>��������</th>
					<td colspan="3">
						<textarea rows="3" cols="3" style="width: 95%;word-break:break-all;" readonly="readonly" type="_moz">${rs.sqly }</textarea>
					</td>
                </tr>
				<tr>
					<th>��ע</th>
					<td colspan="3">
						<textarea rows="3" cols="3" style="width: 95%;word-break:break-all;" readonly="readonly" type="_moz">${rs.bz }</textarea>
					</td>
                </tr>
				<!--����ѧԺ-->
				<logic:equal value="10628#" name="xxdm">
					<tr>
						<th>������</th>
						<td>
							<bean:write name="rs" property="kh"/>
						</td>
						<th>һ��ͨ��</th>
						<td>
							<bean:write name="rs" property="ykth"/>
						</td>
					</tr>
					<tr>
						<th>���п���</th>
						<td>
							<bean:write name="rs" property="yhkh"/>
						</td>
						<th>���뵥λ</th>
						<td>
							<bean:write name="rs" property="sqdw"/>
						</td>
					</tr>
					<tr>
						<th>���֤��</th>
						<td colspan="3">
							<bean:write name="rs" property="sfzh"/>
						</td>
					</tr>
				</logic:equal>
				<!--end����ѧԺ-->

				<%--�㽭����ְҵ����ѧԺ--%>
				<logic:equal value="12861" name="xxdm">
					<tr>
						<th>��������</th>
						<td colspan="3">
							<textarea rows="3" cols="3" style="width: 95%;word-break:break-all;" readonly="readonly" type="_moz">${rs.sqly }</textarea>
						</td>
					</tr>
					<tr>
						<th>�ɲμ��ڹ���ѧʱ��</th>
						<td colspan="3">
							<bean:write name="rs" property="kcjqgzxsj"/>
						</td>
					</tr>
				</logic:equal>
				<%--end�㽭����ְҵ����ѧԺ--%>

				<%--��������Ժ�Զ����ֶ�--%>
				<logic:notEmpty name="filedList">
					</tbody>
					<thead>
						<tr>
							<th style="cursor:hand" colspan="4">
								<span>���븽����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:iterate id="filed" name="filedList" indexId="index">
						<tr>
							<td align="right">
								<bean:write name="filed" property="zdmc" />
								��
							</td>
							<td colspan="3">
								<bean:write name="rs" property="${filed.zddm}" />
							</td>
						</tr>
					</logic:iterate>
					</tbody>
					<tbody>
				</logic:notEmpty>
				<%--end��������Ժ�Զ����ֶ�--%>
					
				<%--��������ѧԺ--%>
				<logic:equal value="10690" name="xxdm" scope="request">
					<tr>
						<th>������ò</th>
						<td>
							${rs.zzmmmc}
						</td>
						<th>��ͥ��ַ</th>
						<td>
							${rs.jtdz}
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							${rs.mzmc}
						</td>
						<th>��ͥ�绰</th>
						<td>
							${rs.jtdh}
						</td>
					</tr>
					<tr>
						<th>������ѧ����</th>
						<td>
							${rs.gjzxdk}
						</td>
						<th>��ͥ�ʱ�</th>
						<td>
							${rs.jtyb}
						</td>
					</tr>
					<tr>
						<th>�����������</th>
						<td>
							${rs.zzqk}
						</td>
						<th>��ͥ��Ա��Ϣ</th>
						<td>
							${rs.jtcy}
						</td>
					</tr>
					<tr>
						<th>ƶ���ȼ�</th>
						<td>
							${rs.pkdj}
						</td>
						<th>��ͥ������</th>
						<td>
							${rs.jtysr}
						</td>
					</tr>
					<tr>
						<th>�Ը�</th>
						<td>
							${rs.xg}
							<br />
							(����Ϊ1������Ϊ10)
						</td>
						<th>�س�</th>
						<td>
							${rs.yhtc}
						</td>
					</tr>
					<tr>
						<th>�Ͷ�����</th>
						<td>
							${rs.gzjl}
						</td>
						<th>�Ͷ�����</th>
						<td>
							${rs.ldyx}
						</td>
					</tr>
					<tr>
						<th>���</th>
						<td>
							<html:text property="bh" name="rs" styleId="bh"></html:text>
						</td>
						<th>����</th>
						<td>
							<html:text property="gh" name="rs" styleId="gh"></html:text>
						</td>
					</tr>
					<tr>
						<th>���</th>
						<td>
							<html:select name="rs" property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
						</td>
					</tr>
					<tr>
						<th>ѧУ���</th>
						<td colspan="3">
							<logic:notEqual value="xy" name="userType">
								<html:textarea name="rs" property="xxshyj"
									style="width:100%;height:45px" styleId="xxshyj"></html:textarea>
							</logic:notEqual>
							<logic:equal value="xy" name="userType">
								<html:textarea name="rs" property="xxshyj"
									style="width:100%;height:45px" readonly="true" styleId="xxshyj"></html:textarea>
							</logic:equal>
						</td>
					</tr>
				</logic:equal>
				<%--end��������ѧԺ--%>				

				<!--- �㶫����ѧԺ---->
				<logic:equal value="10822" name="xxdm" scope="request">
					<tr>
						<th>��ͥ��Ҫ������Դ</th>
						<td colspan="3">
							${rs.jtzyjjly}
						</td>
					</tr>
					<th>�ɲμ��ڹ���ѧʱ��</th>
					<td colspan="3">
						${rs.kcjqgzxsj}
					</td>
					<tr>
						<th>���</th>
						<td colspan="3">
							<html:select name="rs" property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table class="formlist" width="100%" >
								<tr>
									<th rowspan="5" align="right" width="18">
										��
										<br />
										ͥ
										<br />
										��
										<br />
										Ա
										<br />
										��
										<br />
										Ϣ
										<br />
									</th>
									<th align="center">
										��ͥ��Ա����
									</th>
									<th align="center">
										��ν
									</th>
									<th align="center">
										������λ��ְ��
									</th>
									<th colspan="2" align="center">
										������
									</th>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy1_xm}
									</td>
									<td align="center">
										${rs.jtcy1_cw}
									</td>
									<td align="center">
										${rs.jtcy1_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy1_nsr}
									</td>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy2_xm}
									</td>
									<td align="center">
										${rs.jtcy2_cw}
									</td>
									<td align="center">
										${rs.jtcy2_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy2_nsr}
									</td>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy3_xm}
									</td>
									<td align="center">
										${rs.jtcy3_cw}
									</td>
									<td align="center">
										${rs.jtcy3_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy3_nsr}
									</td>
								</tr>
								<tr align="center" style="height:22px">
									<td align="center">
										${rs.jtcy4_xm}
									</td>
									<td align="center">
										${rs.jtcy4_cw}
									</td>
									<td align="center">
										${rs.jtcy4_gzdwjzw}
									</td>
									<td colspan="2" align="center">
										${rs.jtcy4_nsr}
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</logic:equal>
				<!--- end�㶫����ѧԺ---->

				<%--�㶫Ů��ְҵ����ѧԺ--%>
				<logic:equal value="12742" name="xxdm">
					<tr>
						<th>����</th>
						<td>
							${rs.ssbh}
						</td>
						<th>�س�����</th>
						<td>
							${rs.yhtc}
						</td>
					</tr>
					<tr>
						<th>��ѧ���Ƿ��в��ϸ��Ŀ</th>
						<td>
							${rs.bhgkm}
						</td>
						<th>��ѧ������Υ�ʹ���</th>
						<td>
							${rs.wjcf}
						</td>
					</tr>
					<tr>
						<th>���</th>
						<td colspan="3">
							<html:select name="rs" property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<%--end�㶫Ů��ְҵ����ѧԺ--%>
				<logic:present name="showshgc">
					<tr>
						<th>���</th>
						<td>
							<html:select name="rs" property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th></th>
						<td>
						</td>
					</tr>
				</logic:present>
				<logic:notPresent name="showshgc">
					<logic:notEqual value="12742" name="xxdm"><%--�ǹ㶫Ů��ְҵ����ѧԺ--%>
					<logic:notEqual value="10822" name="xxdm"><%--�� �㶫����ѧԺ--%>
					<logic:notEqual value="10690" name="xxdm"><%--����������ѧԺ--%>	
					<tr>
						<th>���</th>
						<td colspan="3">
							<html:select name="rs" property="yesNo" styleId="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
							</html:select>
						</td>
					</tr>			
					</logic:notEqual>
					</logic:notEqual>
					</logic:notEqual>
				</logic:notPresent>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:equal value="12036" name="xxdm">
								<logic:equal value="xy" name="userType">
									<logic:equal value="true" name="isFdy">
										<logic:equal value="δ���" name="rs" property="xyyj">
											<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
												id="buttonSave">
												�� ��
											</button>
										</logic:equal>
									</logic:equal>
									<logic:notEqual value="true" name="isFdy">
											<logic:equal value="δ���" name="rs" property="xxyj">
												<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
													id="buttonSave">
													�� ��
												</button>
											</logic:equal>
									</logic:notEqual>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
									<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
										id="buttonSave">
										�� ��
									</button>
								</logic:notEqual>
							</logic:equal>
							<logic:notEqual value="12036" name="xxdm">
								<button type="button" class="button2" onclick="thisCommit();" style="width:80px"
									id="buttonSave">
									�� ��
								</button>
							</logic:notEqual>
			            
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
		</html:form>

		<!--������ʾ��Ϣ-->
		<logic:notEmpty name="flag">
			<logic:equal value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
				<logic:empty name="mes">
					<script language="javascript">
						alert("�����ɹ���");
					</script>
				</logic:empty>
				<script language="javascript">				
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>

			<logic:notEqual value="true" name="flag">
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
					</script>
				</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
					alert("����ʧ�ܣ�");
				</script>
			</logic:empty>
			<script language="javascript">
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:notEqual>
		</logic:notEmpty>
		<!--end������ʾ��Ϣ-->
		
		<!--�������������ʾ-->
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
			         	alert("��ѧ������������Ŀͨ����ˣ�");
			         	Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
			         	alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
	         		</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<!--end�������������ʾ-->

		<!--����������Ϣ��ʾ-->
		<logic:equal value="full" name="result">
			<script>
				alert("����������");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("����������������");
			</script>
		</logic:equal>
		<!--end����������Ϣ��ʾ-->
	</body>
</html>
