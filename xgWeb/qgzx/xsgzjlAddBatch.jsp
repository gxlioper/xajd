<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/qgzxgzkh.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script>
		function save(){
			if(filedNotNull('xh-xmdm-nd-yf','-')){
				//�жϼ�¼�Ƿ����
				var flag = false;
				for(var i=1; i<32; i++){
					var zgzsj = $('zgzsj'+i).value;
					if(zgzsj != null && zgzsj != ""){
						flag = true;
						if(flag){
							break;
						}
					}
				}
				if(flag){
					refreshForm('qgzxkh.do?method=addXsgzjlBatch');
				}else {
					alert ('����д������ʱ�䣡');
				}
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
		
		function changeRecord(rq){
			rq = parseInt(rq);
			if(rq == '2'){
				ele('tr29').style.display='none';
				ele('tr30').style.display='none';
				ele('tr31').style.display='none';
			}else if(rq == '4' || rq =='6' || rq=='9' || rq=='11'){
				ele('tr29').style.display='';
				ele('tr30').style.display='';
				ele('tr31').style.display='none';
			}else{
				ele('tr29').style.display='';
				ele('tr30').style.display='';
				ele('tr31').style.display='';
			}
		}
	</script>
</head>
	<body>
		<html:form action="/qgzxkh.do">
			<input type="hidden" name="url" id="url" value="/qgzxkh.do?method=xsgzjlAddBatch"/>
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<input type="hidden" id="tableName" name="tableName" value="view_qgzx_xsxx"/>
			<input type="hidden" id="zd" name="zd" value="dlm"/>
			<input type="hidden" id="va" name="va" value="${userName }"/>
			<input type="hidden" id="lx" name="lx" value="�ڹ���ѧѧ��"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ���� - ѧ��������¼ - �����Ϣ</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th><span class="red">*</span>ѧ��</th>
						<td>
							<!-- ���ݴ�ѧ -->
							<logic:equal name="xxdm" value="11078">
								<html:text name="rs" property="xh" styleId="xh" readonly="true" />
								<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
							<!-- �ǹ��ݴ�ѧ -->
							<logic:notEqual name="xxdm" value="11078">
								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button type="button" class="btn_01" onclick="showTopWin('/xgxt/stu_info.do',750,550);" id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
						</td>
						<th>����</th>
						<td>
							<bean:write name="rs" property="xm" />
						</td>						
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<th>�Ա�</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>												
					</tr>
					<tr>
						<th>רҵ</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>						
						<th>�꼶</th>
						<td>
							<bean:write name="rs" property="nj" />
						</td>						
					</tr>
					<tr>
						<th>�༶</th>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<th><span class="red">*</span>��λ</th>
						<td>
							<html:select property="xmdm" styleId="xmdm" style="width:180px">
								<html:options collection="gwList" property="xmdm"
									labelProperty="xmmc" />
							</html:select>
						</td>					
					</tr>
					<tr>						
						<th><span class="red">*</span>���</th>
						<td>
							<html:select property="nd" styleId="nd" style="width:180px">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>	
						<th><span class="red">*</span>�·�</th>
						<td>
							<html:select property="yf" styleId="yf" onchange="changeRecord(this.value)" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="yfList" property="yf" labelProperty="yf" />
							</html:select>
						</td>				
					</tr>
					</tbody>
				</table>
				<table class="formlist" width="100%">
					<thead>
					<tr><th colspan="5"><font color="red"><b>��ʾ������д�ܹ���ʱ�佫�������¼</b></font></th></tr>
					<tr>
						<th>����</th>
						<th>������ʼʱ��</th>
						<th>��������ʱ��</th>
						<th><span class="red">*</span>�ܹ���ʱ��</th>
						<th>��������</th>
					</tr>
					</thead>
					<tbody>
					<logic:iterate id="rs" name="rqList">
					<tr id="tr${rs.rq}">
						<td>
							${rs.rq}
						</td>
						<td>
							<input type="text" name="gzkssj${rs.rq}" id="gzkssj${rs.rq}" maxlength="10" onkeyup="value=value.replace(/[^\d|:|��]/g,'')"/>
						</td>
						<td>
							<input type="text" name="gzjssj${rs.rq}" id="gzjssj${rs.rq}" maxlength="10" onkeyup="value=value.replace(/[^\d|:|��]/g,'')"/>
						</td>
						<td>
							<input type="text" name="zgzsj${rs.rq}" id="zgzsj${rs.rq}" maxlength="4" onkeyup="value=value.replace(/[^\d|.]/g,'')"/>
						</td>
						<td>
							<input type="text" name="gznr${rs.rq}" id="gznr${rs.rq}" maxlength="300"/>
						</td>
					</tr>
					</logic:iterate>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="5"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				            <button type="button"
								onclick="save();return false;"
								style="width:80px">
								�� ��
							</button>
							<button type="button" onclick="window.close();return false;"
								style="width:80px">
								�� ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
				</div>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
