<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var sfzh = document.getElementById('sfzh').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((sfzh == null) || (sfzh == "")){
				alert("���֤����Ϊ�գ��뵽ѧ����Ϣ��ά��!");
				return false;
			}
			//if(!chkSfzh(sfzh)){
			//	return false;
			//}
			//if ("6000" < Math.round(sqje)){
			//	alert("����������ѳ�����������6000Ԫ!");
			//	return false;
			//}
			
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=xssqSave";
			document.forms[0].submit();
		}
		function hm(){
			var hkczh = document.getElementById('hkczh').value;
			if(hkczh.length != 18){
				alert("�����ʺź���λ������!");
				return false;
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		/**���幫�÷���:�������֤����*/
function chkSfzh(obj) {
	var sfzh = obj.toLowerCase();
	var OldID;
	if(sfzh.length == 15){
	    if(sfzh.substring(6,8)<'80'){
	    alert("����������֤�����ǣ�������ǰ�ģ�������ѧ�����˵����֤���룡");
		return false;
	    }else{
		OldID = sfzh;
		return true;
		}
	}else if(sfzh.length == 18){
	    if(sfzh.substring(6,10)<'1980'){
	    alert("����������֤�����ǣ�����������ǰ�ģ�������ѧ�����˵����֤���룡");
		return false;
	    }else{
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
		}
		
	}else{
		alert("��������ȷ�����֤���룡");
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "x","9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		alert("��������ȷ�����֤���룡");
		return false;
	}
	return true;
}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ѧ������ - ѧ������ </a>
		</p>
	</div>
		<html:form action="zgmsxy_xszz.do?method=xssq_xssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgmsxy_xszz.do?method=xssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />"/>
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />"/>

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("��ͨ����ˣ�����������޸ģ�");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="result">
				<logic:match value="false" name="result">
					<script language="javascript">
					window.close();
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			<table class="formlist" width="90%">
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual value="modi" name="doType">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<th width="16%">
						<div>
							����
						</div>
					</th>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							�Ա�
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<th>
						<div>
							<bean:message key="lable.xb" />
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					
					<th>
						<div>
							רҵ
						</div>
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
						<div>
							�༶
						</div>
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ѧ����
						</div>
					</th>
					<td>
						<bean:write name="rs" property="rxrq"/>
					</td>
					<th>
						<div>
							��ҵʱ��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="byny"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ѧ��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<th>
						<div>
							��ͥ��ϵ�绰���̻���
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
						�����ֻ�����
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
					<th>
						<div>
							<font color="red">*</font>���֤��
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
						<input type="hidden" id="sfzh" name="sfzh" value="<bean:write name="rs" property="sfzh"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ͥ�ʱ�
						</div>
					</th>
					<td>
						<bean:write name="rs" property="jtyb"/>
					</td>
					<th>
						<div>
							���֤��Ч��ֹ����
						</div>
					</th>
					<td>
						<input type="text" id="sfzyxzzrq" name="sfzyxzzrq"
							value="<bean:write name="rs" property="sfzyxzzrq"/>" 
							readonly="readonly" onclick="return showCalendar('sfzyxzzrq','ymmdd');"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��ͥ��ϸ��ַ
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtdz"/>
					</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>��ѧ����ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<div>
							��ͥ��ϵ�绰2
						</div>
					</th>
					<td>
						<input type="text" id="lxdh2" name="lxdh2" maxlength="14"
						 value="<bean:write name="rs" property="lxdh2"/>" 
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
					<th>
						<div>
							��ͥ��ϵ�绰3
						</div>
					</th>
					<td>
						<input type="text" id="lxdh3" name="lxdh3" maxlength="14"
						 value="<bean:write name="rs" property="lxdh3"/>"  
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							QQ
						</div>
					</th>
					<td>
						<input type="text" id="qq" maxlength="18" name="qq"
							value="<bean:write name="rs" property="qq"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
					<th>
						<div>
							Email
						</div>
					</th>
					<td>
						<input type="text" id="email" maxlength="18" name="email" value="<bean:write name="rs" property="email"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							��������
						</div>
					</th>
					<td>
						<input type="text" id="fqxm" maxlength="20" name="fqxm" value="<bean:write name="rs" property="fqxm"/>" />
					</td>
					<th>
						<div>
							�������֤��
						</div>
					</th>
					<td>
						<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
						 value="<bean:write name="rs" property="fqsfzh"/>" 
							onblur="if(!checkSfzh(this)){this.focus();}" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							ĸ������
						</div>
					</th>
					<td>
						<input type="text" id="mqxm" maxlength="20" name="mqxm" value="<bean:write name="rs" property="mqxm"/>" />
					</td>
					<th>
						<div>
							ĸ�����֤��
						</div>
					</th>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
						 value="<bean:write name="rs" property="mqsfzh"/>" 
							onblur="if(!checkSfzh(this)){this.focus();}" />
					</td>
				</tr>
				<tr>
					<th>
							����ϼ�
					</th>
					<td>
						<input type="text" id="dkhj" name="dkhj" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="dkhj"/>" 
							/>
					</td>
					<th>
						��һѧ��ѧϰ���úϼ�
					</th>
					<td>
						<input type="text" id="xq1" name="xq1" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq1"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						�ڶ�ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<input type="text" id="xq2" name="xq2" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq2"/>" 
							/>
					</td>
					<th>
						����ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<input type="text" id="xq3" name="xq3" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq3"/>" 
							/>
					</td>
				</tr>
				<tr>
					<th>
						����ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<input type="text" id="xq4" name="xq4" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq4"/>" 
							/>
					</td>
					<th>
						����ѧ��ѧϰ���úϼ�
					</th>
					<td>
						<input type="text" id="xq5" name="xq5" maxlength="10"
							value="<bean:write name="rs" property="xq5"/>" 
							onkeyup="value=value.replace(/[^\d]/g,'')" 
							/>
					</td>
				</tr>
				<tr>
					<th>
						ѧ�ѽ��
					</th>
					<td>
						<input type="text" id="xfje" name="xfje" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')"  
							value="<bean:write name="rs" property="xfje"/>" 
							/>
					</td>
					<th>
						ס�޷ѽ��
					</th>
					<td>
						<input type="text" id="zsfje" name="zsfje" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="zsfje"/>" 
							/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<div class="bz">
							 <logic:equal name="sfksq" value="1">
					        	"<span class="red">*</span>"Ϊ������
					        	
					        	<logic:equal value="xy" name="userType">
			          				<logic:equal name="rs" property="xxsh" value="ͨ��"> 
			          					&nbsp;&nbsp;<span class="red"><bean:message key="lable.xb" />�û������޸�ѧУ�û����ͨ������Ϣ��</span>
			          				</logic:equal>
			          			</logic:equal>
					         </logic:equal>
					          
					      	  <logic:equal name="sfksq" value="-1">
					        		<span class="red">���ڲ�������ʱ���ڣ�</span>
					          </logic:equal>
						</div>
			          <div class="btn">
          				<button type="button" name="�ύ" onclick="yz();">����</button>
		          		<button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
</body>
</html>
