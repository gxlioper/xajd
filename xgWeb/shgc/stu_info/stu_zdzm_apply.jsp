<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript">
		function printZm(url){
		var xh = document.getElementById("xh").value;
		var lxfs = document.getElementById("lxfs").value;
		var blfs = document.getElementById("blfs").value;
		var sqly = document.getElementById("sqly").value;
		
		url +="&xh="+xh;
		url +="&lxfs="+lxfs;
		url +="&blfs="+blfs;
		url +="&sqly="+sqly;
		window.open(url);
		}
	</script>
</head>
	<body>		
		<html:form action="/data_search" method="post">
			<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="sh" name="sh" value="<bean:write name="sh"/>" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<logic:notEqual name="xxdm" value="13022">
				<input type="hidden" id="url" name="url" value="/attend_school_prove.do?sfcg=0" />
			</logic:notEqual>
			<logic:equal name="xxdm" value="13022">
				<logic:equal name="sh" value="no">
				<input type="hidden" id="url" name="url" value="/attend_school_prove.do?sfcg=0&sh=no" />
				</logic:equal>
				<logic:equal name="sh" value="yes">
				<input type="hidden" id="url" name="url" value="/attend_school_prove.do?sfcg=0&sh=yes" />
				</logic:equal>
			</logic:equal>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - �ڶ�֤������ - ��д�����</a>
				</p>
			</div>
				
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("����ɹ���");
    				</script>
				</logic:equal>
			</logic:notEmpty>
			
			<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:write name="xxmc"/>�����ڶ�֤������</span>
							</th>
						</tr>
					</thead>	
					<tbody>					
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th><span class="red">*</span>ѧ��</th>
								<td>
									<logic:equal value="view" name="doType">
										<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
									</logic:equal>
									<logic:notEqual value="view" name="doType">
										<html:text name='rs' property="xh" styleId="xh"
										onblur="autoFillStuInfo2(this)" onkeypress="if(event.keyCode == 13) return false;" />
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:notEqual>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th><span class="red">*</span>ѧ��</th>
								<td>
									<input type="text" id="xh" name="xh" value="<bean:write name='rs' property="xh" />" readonly="readonly" />
									<input type="hidden" id="xh" name="xh" value="<bean:write name='rs' property="xh" />" />
								</td>
							</logic:equal>
							<th>רҵ</th>
							<td>
								<input type="text" value="<bean:write name="rs" property="zymc"/>" readonly="readonly"/>								
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<input type="text" value="<bean:write name='rs' property="xm" />" readonly="readonly"/>																
							</td>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<input type="text" value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>�Ա�</th>
							<td>
								<input type="text" value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
							</td>
							<th>�༶</th>
							<td>
								<input type="text" value="<bean:write name='rs' property="bjmc" />"readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>��ϵ��ʽ(�ֻ�)</th>
							<td>
								<html:text name="rs" property="lxfs" styleId="lxfs" maxlength="32"/>
							</td>
							<th><span class="red">*</span>֤������</th>
							<td>
								<html:select property="zmlx" name="rs" styleId="zmlx">
								<html:option value=""></html:option>
								<html:options collection="typeList" labelProperty="cn" property="en"/>	
								</html:select>								
							</td>	
						</tr>						
						<tr>
							<th><span class="red">*</span>�������</th>
							<td>
								<html:text name="rs" property="blfs" styleId="blfs" style="width:90%" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "/>��
							</td>
							<th></th>						
							<td></td>
						</tr>						
						<tr>
							<th><span class="red">*</span>����ԭ��</th>
							<td colspan="3">
								<html:textarea name="rs" property="sqly" style="height:100px;width:100%" styleId="sqly"/>
							</td>
						</tr>	
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					            <logic:equal name="sh" value="no">
									<button
										onclick="commSave('/xgxt/attend_school_prove.do?doType=save&sh=no','xh-sqly-blfs-zmlx')">
										�� �� �� ��
									</button>
									</logic:equal>
									<logic:notEqual name="sh" value="no">
									<button
										onclick="commSave('/xgxt/attend_school_prove.do?doType=save','xh-sqly-blfs-zmlx')">
										�� �� �� ��
									</button>
									</logic:notEqual>
									<button onclick="printZm('business.do?method=printZmreporter')">
										�� ӡ �� ��
									</button>
									<button onclick="printCertificate()">
										֤ �� �� ӡ
									</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>					
					</table>
				</div>

				<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<logic:present name="msg">
						<input type="hidden" id="msg" value="${msg}"/>
						<script>
							alert(document.getElementById('msg').value);
						</script>
					</logic:present>
					<logic:notPresent name="msg">
						<script>
							alert("����ɹ���");
						</script>
					</logic:notPresent>
				</logic:equal>
				
				<logic:equal value="false" name="result">
					<logic:present name="msg">
						<input type="hidden" id="msg" value="${msg}"/>
						<script>
							alert(document.getElementById('msg').value);
						</script>
					</logic:present>
					<logic:notPresent name="msg">
						<script>
							alert("����ʧ�ܣ�");
						</script>
					</logic:notPresent>
				</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
