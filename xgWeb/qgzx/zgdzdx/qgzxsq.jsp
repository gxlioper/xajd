<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = val('xmdm');
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			setVal('sqdw',data[0]);
			setVal('fzr',data[1]);
			setVal('gwlxdh',data[2]);
		});
		}		
	}
	
	function chkisDataExist(obj){
		var value = obj.split("-");
		for(var i=0; i<value.length; i++){
			if(trim(val(value[i]))==""){
				alert("�뽫��\*�ŵ���Ŀ��д������");				
				return false;
			}
		}
		var yhtc = val('yhtc');
		var bz = val('bz');
		var sqly = val('sqly');
		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�к��س����ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("��ע���ܴ���250���ַ�");
	          		 return false;
	       		 }
			}
		if(sqly != null){
	         	if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("�������ɲ��ܴ���250���ַ�");
	          		 return false;
	       		 }
			}
		
		refreshForm('qgzxZgdzdx.do?method=saveQgzxsq');
	}
	</script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx.co" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/qgzx/zgdzdx/qgzxsq.jsp" />
			<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
			<input type="hidden" name="mes" id="mes" value="${hmdMember}"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ���� - �����ڹ���ѧ</a>
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
				<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��д�����</span>
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
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<th>���</th>
						<td>
							<html:text name="rs" property="nd" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							<bean:write name='rs' property="xm" />
						</td>
						<th>ѧ��</th>
						<td>
							<html:text name="rs" property="xn" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							<bean:write name='rs' property="xb" />
						</td>
						<th>ѧ��</th>
						<td>
							<html:text name="rs" property="xqmc" readonly="true" />
							<html:hidden name="rs" property="xq"/>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name='rs' property="xymc" />
						</td>
						<th><span class="red">*</span>��ϵ�绰</th>
						<td>
							<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>
					</tr>
					<tr>
						<th>רҵ</th>
						<td>
							<bean:write name='rs' property="zymc" />
						</td>
						<th><span class="red">*</span>�ɲμ��ڹ���ѧʱ��</th>
						<td>
							<html:text name='rs' property="kcjqgzxsj" styleId="kcjqgzxsj" maxlength="250"/>
						</td>
					</tr>

					<tr>
						<th>�༶</th>
						<td>
							<bean:write name='rs' property="bjmc" />
						</td>
						<th>�Ƿ�ƶ����</th>
						<td>
							<bean:write name='rs' property="sfpks" />
							<input type="hidden" name="sfpks" id="sfpks" />
						</td>
					</tr>	
					<tr>
						<th>������ò</th>
						<td>
							<html:select property="zzmmdm" name="rs" styleId="zzmmdm">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" labelProperty="zzmmmc" property="zzmmdm"/>
							</html:select>								
						</td>
						<th>������</th>
						<td>
							<bean:write name="rs" property="ssbh" />
						</td>
					</tr>
					<tr>
						<th>�к��س�</th>
						<td colspan="3">
							<html:textarea name='rs' property='yhtc' styleId="yhtc"
								style="width:99%" rows='5' onblur="chLeng(this,100)"/>
						</td>
					</tr>		
					<tr>
						<th><span class="red">*</span>��������</th>
						<td colspan="3">
							<html:textarea name='rs' property='sqly' styleId="sqly"
								style="width:99%" rows='5' onblur="chLeng(this,250)"/>
						</td>
					</tr>
					<tr>
						<th>��ע</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' onblur="chLeng(this,250)"/>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				            <button type="button" class="button2" onclick="chkisDataExist('xh-lxdh-kcjqgzxsj-sqly')">
								�� �� �� ��
							</button>
							<button type="button" class="button2"
								onclick="expAppTab('rsT','�ڹ���ѧ�����','')">
								�� ӡ Ԥ ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
				</div>
			</logic:notEmpty>

			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
				    	alert("����ɹ���");
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
				<logic:present name="hmdMember">
					<script>
				    	alert("���Ѿ��������ڹ���ѧ���������޷����룡");
				    </script>
				</logic:present>
				<logic:notPresent name="hmdMember">
					<script>
				    	alert("����ʧ�ܣ�");
				    </script>
				  </logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
