<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript">
		function changePre(){		
			document.getElementById("studentqt").innerHTML = "����ѧ�ţ�";
			document.getElementById("studentid").innerHTML = 
				"<button type='button' onclick=\"checkid()\" id=\"btu_qmod\">ȷ���޸�</button>";
			document.getElementById("studentxh").readOnly = false;
			
			if(document.getElementById('studentxh').style.display=='none'){
				document.getElementById("studentxh").style.display='';
				document.getElementById("studentqt").style.display='';
				document.getElementById("studentxh").value="";	
				document.getElementById("tishixx").type="text";
				document.getElementById("btu_add").style.display="none";
				document.getElementById("btu_can").style.display="none";
			}else{
				document.getElementById("btu_add").style.display="";
				document.getElementById("btu_can").style.display="";
				document.getElementById("studentxh").style.display='none';
				document.getElementById("btu_qmod").style.display='none';
				document.getElementById("studentqt").style.display='none';	
				document.getElementById("tishixx").style.display='none';	
			}
			
		}
		function checkid(){
			var studentxh = document.getElementById("studentxh").value;
			var xh = document.getElementById('xh').value;
			//��ʾ���Ӻ�ȡ����ť
			document.getElementById("tzhgzxn").value = document.getElementById("tzqgzxn").value;
			document.getElementById("tzhgznd").value = document.getElementById("tzqgznd").value;
			document.getElementById("tzhgzxq").value = document.getElementById("tzqgzxq").value;
			document.getElementById("tzhgw").value = document.getElementById("tzqgw").value;
			document.getElementById("tzhgwsbsj").value = document.getElementById("tzqgwsbsj").value;					
									
			if(isEmpty(studentxh)){
				document.getElementById("tishixx").innerHTML = "<font color=\"red\">ѧ�Ų���Ϊ��</font>";
				return ;
			}else if(!isNumber(studentxh)){
				document.getElementById("tishixx").innerHTML = "<font color=\"red\">����Ϊ����</font>";
				return ;
			}else if(xh==null || xh==''){
				alert("��ѡ����Ҫ������ԭ��λ�ϵ�ѧ����");
				return false;
			}else{
				//����ѧ�ŵĴ���				
				var gwdm = document.getElementById("tzhgw").value;
				var gwsbsj = document.getElementById("tzhgwsbsj").value;
				cqkjFunc.isStudentExists(studentxh,gwdm,gwsbsj,function(data){
					if(data[0] == "0"){
						alert("��ѧ�Ų����ڣ�");
						return;
					}else if(data[0] == "1"){
						alert("��ѧ���Ѿ�����Ҫ�����ĸ�λ����,����������һ��ѧ����");
						return;
					}else{
						//��ʾ����
						var sConfirm = '��ȷ����ԭ��λ�ϵ� '+document.getElementById('xm').innerText +'ͬѧ�滻���������ѧ����';
						if(confirm(sConfirm)){
						cqkjFunc.isStudentExists(studentxh,gwdm,gwsbsj,function(data){
							//��ԭ��ѧ������Ϣ���������������ύ��action��ɾ��ԭ����������Ϣ
							document.getElementById("oldStudent").value = document.getElementById("xh").value;							
							//��������ҳ����
							document.getElementById("xh").value = data[0];
							document.getElementById("xm").innerText = data[1];
							document.getElementById("nj").innerText = data[2];
							document.getElementById("xymc").innerText = data[3];
							document.getElementById("zymc").innerText = data[4];
							document.getElementById("bjmc").innerText = data[5];
							//�ָ�ԭ״
							document.getElementById("btu_can").style.display="";
							document.getElementById("btu_add").style.display="";
							document.getElementById("studentqt").innerHTML = "";
							document.getElementById("studentid").innerHTML = "";
							document.getElementById("tishixx").innerHTML = "";
							document.getElementById("studentxh").style.display="none";
							alert('���������Ϣ���[ ���� ]��ť���б��棡');
							//document.getElementById("tishixx").innerHTML = "<font color=\"blue\">�Ѿ��޸ĵ��[ ���� ]��ť���б���</font>" ;
							//����changeStudent���������ֶν��б�־����action������ж��ǲ��ǡ���ɳ������
							document.getElementById("changeStudent").value = "ok";
						});
						}
					}
				});
			}
		}
		/*�ж��Ƿ�Ϊ����*/
		function isNumber(str) {
			var Letters = "1234567890";
			for (var i = 0; i < str.length; i = i + 1) {
				var CheckChar = str.charAt(i);
				if (Letters.indexOf(CheckChar) == -1) {
					return false;
				}
			}
			return true;
		}
		/*�ж��Ƿ�Ϊ��*/
		function isEmpty(value) {
			return /^\s*$/.test(value);
		}
	</script>
</head>
	<body>
		<html:form action="/qgzx_work_adjustInfo" method="post">
			<input type="hidden" name="url" id="url" value="/qgzx/ynys/qgzx_ynys_gwtzb.jsp"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xymc-nj-zymc-bjmc" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em>
					<a>
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
						ѧ���幤 - ��� - ��λ����
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						�ڹ���ѧ - ��� - ��λ����
					</logic:notEqual>
					</a>
				</p>
			</div>
			
		  <div class="tab">
		  <table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>��λ����</span>
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
							<input type="hidden" id="xh" value="${rs.xh}"/>
							${rs.xh}
						</td>
					</logic:equal>
					<th>����</th>
					<td id="xm">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td id="nj">
						<bean:write name="rs" property="nj" />
					</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td id="xymc">
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td id="zymc">
						<bean:write name="rs" property="zymc" />
					</td>
					<th>�༶</th>
					<td id="bjmc">
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>����ԭ��</th>
					<td>
						<html:text name="rs" property="tzyy" styleId="tzyy" />
					</td>
					<th><span class="red">*</span>����ʱ��</th>
					<td>
						<html:text name="rs" property="tzsj" styleId="tzsj"
							readonly="true" onclick="return showCalendar('tzsj','y-mm-dd');" />
					</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>�춯ѧ����λ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<td colspan="2">
						<fieldset>
							<legend>
								<span><b>�춯ǰ��Ϣ</b></span>
							</legend>
							<table style="width:310px" class="formlist">
								<tbody>
								<tr>
									<th>ѧ��</th>
									<td>
										<html:text name="rs" property="tzqgzxn" styleId="tzqgzxn"
											readonly="true" style="width:180px"></html:text>
									</td>
								</tr>
								<tr>
									<th>���</th>
									<td>
										<html:text name="rs" property="tzqgznd" styleId="tzqgznd"
											readonly="true" style="width:180px"></html:text>
									</td>
								</tr>
								<tr>
									<th>ѧ��</th>
									<td>
										<html:text name="rs" property="tzqgzxqmc" styleId="tzqgzxqmc"
											readonly="true" style="width:180px"></html:text>
										<html:hidden property="tzqgzxq" name="rs" styleId="tzqgzxq"/>
									</td>
								</tr>
								<tr>
									<th><span class="red">*</span>��λ����</th>
									<td>
										<html:select name="rs" property="tzqgw" styleId="tzqgw"
											style="width:180px" onchange="GetOneList1(this)">
											<html:option value=""></html:option>
											<html:options name="rs" collection="qgwList" property="gwdm"
												labelProperty="gwdm" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th><span class="red">*</span>��λ����ʱ��</th>
									<td>
										<input type="hidden" name="tzqgwsbsjV" value="" />
										<html:select name="rs" property="tzqgwsbsj"
											styleId="tzqgwsbsj" style="width:180px"
											onchange="GetGwInfo()">
											<html:option value=""></html:option>
											<html:options collection="qgwsbsjList" property="gwsbsj"
												labelProperty="labgwsbsj" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>�ɲμӹ���ʱ��</th>
									<td>
										<html:text name="rs" property="tzqkcjqgzxsj"
											styleId="tzqkcjqgzxsj" readonly="true" style="width:180px"></html:text>
									</td>
								</tr>
								</tbody>
							</table>
						</fieldset>
					</td>
					<td colspan="2">
						<fieldset>
							<legend>
								<span><b>�춯����Ϣ</b></span>
							</legend>
							<table width="310px" class="formlist">
								<tbody>
								<tr>
									<th><span class="red">*</span>ѧ��</th>
									<td>
										<html:select name="rs" property="tzhgzxn" styleId="tzhgzxn"
											style="width:180px">
											<html:options name="rs" collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th><span class="red">*</span>���</th>
									<td>
										<html:select styleId="tzhgznd" name="rs" property="tzhgznd"
											style="width:180px">
											<html:options name="rs" collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th><span class="red">*</span>ѧ��</th>
									<td>
										<html:select name="rs" property="tzhgzxq" styleId="tzhgzxq"
											style="width:180px">
											<html:option value=""></html:option>
											<html:options name="rs" collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th><span class="red">*</span>��λ����</td>
									<td>
										<html:select name="rs" property="tzhgw" styleId="tzhgw"
											style="width:180px" onchange="GetOneList(this)">
											<html:option value=""></html:option>
											<html:options name="rs" collection="gwList" property="gwdm"
												labelProperty="gwdm" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th><span class="red">*</span>��λ����ʱ��</th>
									<td>
										<input type="hidden" name="tzhgwsbsjV" value="" />
										<html:select name="rs" property="tzhgwsbsj"
											style="width:180px" styleId="tzhgwsbsj">
											<html:option value=""></html:option>
											<html:options collection="gwsbsjList" property="gwsbsj"
												labelProperty="labgwsbsj" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th><span class="red">*</span>�ɲμӹ���ʱ��</th>
									<td>
										<html:text name="rs" property="tzhkcjqgzxsj"
											styleId="ydhkcjqgzxsj"></html:text>
									</td>
								</tr>
								</tbody>
							</table>
						</fieldset>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button type="button"
							onclick="Savedata('xh-tzyy-tzsj-tzhgzxn-tzhgzxq-tzgzhnd-tzhgw-tzhgwsbsj-tzhkcjqgzxsj-tzqgwfbsj-tzqgw','qgzx_work_adjustSave.do');"
							style="width:80px" id="btu_add">
							�� ��
						</button>
						<button type="button" onclick="Close();return false;"
							style="width:80px" id="btu_can">
							�� ��
						</button>
						<button type="button" onclick="changePre()"
							style="width:80px">
							����ѧ��
						</button>
						<input type="hidden" name="oldStudent" id="oldStudent" value="" />
						<input type="hidden" name="changeStudent" id="changeStudent" value="" />
						<span id="studentqt" style="display:none"></span>
						<input type="text" name="studentxh" id="studentxh" value="�޸�ʱ����ѧ��"
							readonly="readonly" size="15" style="display:none" />
						<span id="studentid" style=""></span>
						<span id="tishixx"></span>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>			
		</div>
		</html:form>

		<logic:present name="result">
			<logic:equal value="ok" name="result" scope="request">
				<script>
					alert("����ɹ�");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="result" scope="request">
				<logic:present name="msg">
					<input type="hidden" id="msg" name="msg" value="${msg}"/>
					<script>
						alert(document.getElementById('msg').value);
					</script>
				</logic:present>
				<logic:notPresent name="msg">
					<script>
						alert("����ʧ��");
					</script>
				</logic:notPresent>				
			</logic:equal>
		</logic:present>
	</body>
</html>
