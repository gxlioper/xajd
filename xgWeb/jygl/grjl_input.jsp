<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function savegrjl(){   
	var xsxh = document.getElementById("xsxh").value;
	var lxdh = document.getElementById("lxdh").value;
	var yzbm = document.getElementById("yzbm").value;
	var email = document.getElementById("email").value;
	var lxdz =document.getElementById("lxdz").value;

	var hjqk = document.getElementById("hjqk").value;
	var xxqk = document.getElementById("xxqk").value;
	var xjysjl = document.getElementById("xjysjl").value;
	var shsjqk = document.getElementById("shsjqk").value;
	var gzjl =document.getElementById("gzjl").value;
	var grtc = document.getElementById("grtc").value;
	var zwtj =document.getElementById("zwtj").value;
	
	if(xsxh==""){
	alert("ѧ�Ų���Ϊ�գ�");
	return false;
	}
	if(lxdz!=""&&lxdz.length>25){
	alert("��ϵ��ַ���ȹ�������ԣ�");
	return false;
	}
	
	if(lxdh.length<7&&lxdh!=""){
	alert("�绰���볤�Ȳ���Ҫ��");
	return false;
	}
	if(lxdh.length>13&&lxdh!=""){
	alert("�绰���볤�Ȳ���Ҫ��");
	return false;
	}
	if(!isNumber(lxdh)&&lxdh!=""){
	alert("�绰����ӦΪ���֣�");
	return false;
	}
	if(yzbm.length!=6&&yzbm!=""){
	alert("�������볤��ӦΪ6λ");
	return false;
	}
	if(!isNumber(yzbm)&&yzbm!=""){
	alert("��������ӦΪ���֣�");
	return false;
	}
	if(!isEmail(email)&&email!=""){
	alert("�������䲻�Ϸ���");
	return false;
	}	
	if(lxdz==""&&lxdh==""&&email==""){
	alert("��������дһ����ϵ��ʽ��");
	return false;
	}
	if(hjqk.length>1200&&hjqk!=""){
		alert("��������ܳ���1200������");
		return false;
		}
	if(xxqk.length>1200&&xxqk!=""){
		alert("ѧϰ������ܳ���1200������");
		return false;
		}
	if(xjysjl.length>1200&&xjysjl!=""){
		alert("У�����Ͻ������ܳ���1200������");
		return false;
		}
	if(shsjqk.length>1200&&shsjqk!=""){
		alert("���ʵ��������ܳ���1200������");
		return false;
		}
	if(gzjl.length>1200&&gzjl!=""){
		alert("�����������ܳ���1200������");
		return false;
		}
	if(grtc.length>1200&&grtc!=""){
		alert("�����س����ܳ���1200������");
		return false;
		}
	if(zwtj.length>1200&&zwtj!=""){
		alert("�����Ƽ����ܳ���1200������");
		return false;
		}
		 	document.forms[0].action = "/xgxt/savegrjl.do?doType=save";
		 	document.forms[0].submit();
		 	document.getElementById('tjan').disabled=true;
	}
	
	function returntobegin(){   
		 	document.forms[0].action = "/xgxt/savegrjl.do";
		 	document.forms[0].submit();
	}
	
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    } 
    
      //exclude left and right space; 
	function trim(s){
 		return rtrim(ltrim(s)); 
	}
	//exclude left space; 
	function ltrim(s){
 		return s.replace( /^\s*/, ""); 
	} 
	//exclude right space; 
	function rtrim(s){ 
 		return s.replace( /\s*$/, ""); 
	}
    
    function isEmail(s){
	s = trim(s); 
 	var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	return p.test(s);
    }
	</script>
	</head>
<%--	<logic:equal value="teacher" name="userOnLine">--%>
<%--		<script language="javascript">--%>
<%--			alert('��ҳ��Ϊѧ����дҳ�棡');--%>
<%--		</script>--%>
<%--	</logic:equal>--%>
	<body>
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ѧ�����˼��� - ���˼����Ǽ�</a>
				</p>
			</div>
			
			<div class="tab">
			<table width="100%" class="formlist" id="grjl">
				<thead>
					<tr><th colspan="5"><span>��������</span></th></tr>
				</thead>
			<tbody>
				<tr>
				<td colspan="4">
				<logic:notEqual value="12061" name="xxdm" scope="session">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���֤��
					<html:text name="rs" property="id" readonly="true" />
					<html:checkbox name="rs" property="idsee" value="no" />(����) 
				</logic:notEqual>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>ѧ��
					<html:text name='rs' property="xsxh" styleId="xsxh"
						style="width:150px"
						readonly="true" />
					<button onclick="showTopWin('/xgxt/grjlTurnInfo.do',750,550);"
						class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
				</logic:equal>
				<logic:equal name="userOnLine" value="student" scope="session">
					<font color="red">*</font>ѧ��
				<html:text property="xsxh" name="rs" styleId="xsxh"
						readonly="true" style="width:150px" />
				</logic:equal>
				</td>
				<td rowspan="6" align="center" width=20%">
					��Ƭ
				</td>
				</tr>
				<tr>
					<th width=15%">
						����
					</th>
					<td width=25%">
						<html:text name="rs" property="name" style="100%" readonly="true" />
					</td>
					<th width=15%">
						�Ա�
					</th>
					<td width=25%">
						<html:text name="rs" property="xb" style="100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						��������
					</th>
					<td>
						<html:text name="rs" property="csny" style="100%" readonly="true" />
					</td>
					<th>
						����
					</th>
					<td>
						<html:text name="rs" property="mz" style="100%" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th>
						ѧ��
					</th>
					<td>
						<html:text name="rs" property="xl" style="100%" maxlength="10"/>
					</td>
					<th>
						������ò
					</th>
					<td>
						<html:select name="rs" property="zzmm" style="width:150px">
							<html:option value="�޵���������ʿ">�޵���������ʿ</html:option>
							<html:options collection="zzmmList" property="zzmm"
								labelProperty="zzmm" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						רҵ����
					</th>
					<td>
						<html:text name="rs" property="zymc" style="100%" readonly="true" />
					</td>
					<th>
						����רҵ
					</th>
					<td>
						<html:text name="rs" property="fxzymc" style="100%" />
					</td>
				</tr>
				
				<tr>
					<th>
						��Դ����
					</th>
					<td>
						<html:text name="rs" property="sydq" style="100%" readonly="true" />
					</td>
					<th></th>
					<td></td>
				</tr>
			</tbody>
		</table>
		<br/>
		
		<table width="100%" class="formlist" id="">
			<thead>
				<tr><th colspan="5"><span>��ϵ��ʽ</span></th></tr>	
			</thead>
			<tbody>
				<tr>
					<th width=15%">
						��ϵ��ַ
					</th>
					<td width=25%">
						<html:text name="rs" property="lxdz" maxlength="150"/>
					</td>
					<th width=15%"> 
						��ϵ�绰
					</th>
					<td colspan="2" width=45%">
						<html:text name="rs" property="lxdh" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<th>
						��������
					</th>
					<td>
						<html:text name="rs" property="yzbm" style="width=100%" maxlength="6"/>
					</td>
					<th>
						��������
					</th>
					<td colspan="2">
						<html:text name="rs" property="email" style="width=100%" maxlength="25"/>
					</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<table width="100%" class="formlist" id="">
			<thead>
				<tr><th colspan="5"><span>ѧ���ۺ����</span></th></tr>
			</thead>
			<tbody>
				<tr>
					<th width="15%">
						�����
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="hjqk" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th width="15%">
						ѧϰ���
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="xxqk" rows="4"
							style="width: 95%;word-break:break-all;" />
					</td>
				</tr>
				<tr>
					<th>
						У���� �Ͻ���
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="xjysjl" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						���ʵ �����
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="shsjqk" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						��������
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="gzjl" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						�����س�
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="grtc" rows="4" style="width: 95%;word-break:break-all;"/>
					</td>
				</tr>
				<tr>
					<th>
						�����Ƽ�
					</th>
					<td colspan="4">
						<html:textarea name="rs" property="zwtj" rows="6"
							style="width: 95%;word-break:break-all;" />
					</td>
				</tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
					<tr>
						<th>
							ѧУ�Ƽ�
						</th>
						<td colspan="4">
							<html:textarea name="rs" property="xxtj" rows="6"
								style="width: 95%;word-break:break-all;" />
						</td>
					</tr>
				</logic:equal>
				</tbody>
			</table>
			<br/>
	
			<table width="100%" class="formlist" id="printcj">
				<thead><tr><th colspan="4"><span>ѧ���ɼ���</span></th></tr></thead>
				<logic:notEqual value="10338" name="xxdm" scope="session">
					<tr>
						<th>
							��Ŀ����
						</th>
						<th>
							����
						</th>
						<th>
							��Ŀ����
						</th>
						<th>
							����
						</th>
					</tr>
					<tr>
						<td align="center">
							��Ŀ1
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ13
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ2
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ14
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ3
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ15
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ4
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ16
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ5
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ17
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ6
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ18
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ7
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ19
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ8
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ20
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ9
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ21
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ10
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ22
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ11
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ23
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��Ŀ12
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
						<td align="center">
							��Ŀ24
							<html:text name="rs" property="km1" style="width=60%" />
						</td>
						<td align="center">
							<html:text name="rs" property="fs1" style="width=60%" />
						</td>
					</tr>
					</logic:notEqual>
					<logic:equal value="10338" name="xxdm" scope="session">
						<logic:empty name="xscjList">
							û�п��Կ�Ŀ
						</logic:empty>
						<logic:notEmpty name="xscjList">
							<logic:iterate id="xscjs1" name="xscjList">
								<tr>
								<logic:iterate id="xscjs" name="xscjs1">
								<td align="center">
								<logic:iterate id="xscj" name="xscjs" offset="0" length="1">
									<bean:write name="xscj"/>
								</logic:iterate>
								</td>
								<td align="center">
								<logic:iterate id="xscj" name="xscjs" offset="1" length="2">
									<bean:write name="xscj"/>
								</logic:iterate>
								</td>
								</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</logic:equal>
					<tfoot>
						<tr>
							<td align="center" colspan="4">
								<div class="btn">
									<button class="button2" onclick="savegrjl()" 
										id="tjan" style="width:80px">
										�� ��
									</button>
									<button class="button2" onclick="returntobegin()" type="reset"
										style="width:80px">
										�� ��
									</button>
									<button class="button2" onclick="expAppTab('grjl','���˼���','')"
										style="width:80px">
										�� ӡ
									</button>
									<logic:equal value="10338" name="xxdm" scope="session">
									<button class="button2" onclick="expAppTab('printcj','���˼���','')"
										style="width:80px">
										��ӡ�ɼ�
									</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
                        alert("�ύ�ɹ���");
                   </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
                        alert("�ύʧ�ܣ�");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="exists">
				<logic:equal name="exists" value="exists">
					<script>
    alert("ѧУ�����δͨ������ȴ�ѧУ���ͨ���Ժ��������룡");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>

	</body>
</html>
