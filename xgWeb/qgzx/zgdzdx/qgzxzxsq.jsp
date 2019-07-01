<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	
	function chkisDataExist(obj){
		var value = obj.split("-");
		for(var i=0; i<value.length; i++){
			if(document.getElementById(value[i]).value==""){
				alert("�뽫��\*�ŵ���Ŀ��д������");				
				return false;
			}
		}
		refreshForm('qgzxZgdzdx.do?method=saveQgzxzx');
	}
	function print() {
		var xh = $('xh').value;
		var xn = $('xn').value;
		var nd = $('nd').value;
		var xq = $('xq').value;
		
		wjcfDataExport('qgzxZgdzdx.do?method=qgzxzxPrint&xh=' + xh+'&xn=' + xn + '&nd=' + nd+'&xq='+xq );
	}
	</script>
</head>
	<body>
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/qgzx/zgdzdx/qgzxzxsq.jsp" />
			<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />"/>
			<input type="hidden" name="mes" id="mes" value="${hmdMember}"/>
			<input type="hidden" name="doType" id="doType" value="${doType}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - �ڹ���ѧ֮�� - �ڹ���ѧ֮������</a>
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
								<logic:notEqual value="modi" name="doType">
									<html:text name='rs' property="xh" styleId="xh" 
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
								<logic:equal value="modi" name="doType">
									<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
								</logic:equal>								
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th><span class="red">*</span>ѧ��</th>
							<td>								
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>				
						<th><span class="red">*</span>���</th>
						<td>
							<logic:notEqual value="modi" name="doType">
								<html:select property="nd" name="rs" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd"/>
								</html:select>	
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:select property="nd" name="rs" styleId="nd" disabled="true">
								<html:options collection="xnList" property="nd" labelProperty="nd"/>
								</html:select>
								<html:hidden property="nd" name="rs"/>
							</logic:equal>
							
						</td>		
					</tr>
					<tr>
						<th>����</th>
						<td>
							<bean:write name='rs' property="xm" />
						</td>
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<logic:notEqual value="modi" name="doType">
								<html:select property="xn" name="rs" styleId="xn">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:select property="xn" name="rs" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
								<html:hidden property="xn" name="rs"/>
							</logic:equal>							
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							<bean:write name='rs' property="xb" />
						</td>
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<logic:notEqual value="modi" name="doType">
								<html:select property="xq" name="rs" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>	
							</logic:notEqual>
							<logic:equal value="modi" name="doType">
								<html:select property="xq" name="rs" styleId="xq" disabled="true">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>	
								<html:hidden property="xq" name="rs"/>
							</logic:equal>
							
						</td>
					</tr>
					<tr>
						<th>�༶</th>
						<td colspan="3">
							<bean:write name='rs' property="bjmc" />
						</td>						
					</tr>					
					<tr>
						<th>���Ҽ���(0~300��)</th>
						<td colspan="3">
							<html:textarea name='rs' property='zwjd' styleId="zwjd" 
								  cols="80" rows='5' />
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				            <button type="button" class="button2" onclick="chkisDataExist('xh-xn-nd-xq')">
								�� �� �� ��
							</button>
							<button type="button" class="button2"
								onclick="print()">
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
				<logic:present name="noPass">
					<script>
				    	alert("��û�вμ�У���ڹ���ѧ����ʱ���������ڹ���ѧ֮�ǣ�");
				    </script>
				</logic:present>
				<logic:notPresent name="noPass">
					<script>
				    	alert("����ʧ�ܣ�");
				    </script>
				  </logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
