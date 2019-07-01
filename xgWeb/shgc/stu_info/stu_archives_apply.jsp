<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript">
		function printLjsApp(url){
		   document.forms[0].action = url;
		   document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
		}
		
		/**
		 * �滻�����ַ�
		 * */
		function replaceStr(obj){
			if(obj){
				var value = obj.value;
				obj.value = value.replace('+','��');
				value = obj.value;
				obj.value = value.replace('%','��');
				value = obj.value;
				obj.value = value.replace('#','��');
				value = obj.value;
				obj.value = value.replace('&','��');
				//ele(obj.id).value = value.replace('','');		
			}
		}
	</script>
	<%@include file="/comm/other/backgroud.jsp" %>
</head>
<body>
	<html:form action="/stu_archives_apply" method="post">
		<input type="hidden" name="url" id="url" value="/shgc/stu_info/stu_archives_apply.jsp"/>
		<input type="hidden" value="xh-xm-xb-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ-ת������-ת������</a>
			</p>
		</div>
		
		<div class="tab">
		  	<table width="100%" border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4"><span>����ѧ��ת������</span></th>
					</tr>
				</thead>						
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th width="20%"><span class="red">*</span>ѧ��</th>
						<td width="30%">
							<html:text name="rs" property="xh" styleId="xh"
								onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button class="btn_01"
								onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								id="buttonFindStu">
								ѡ��
							</button>							
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th  width="20%"><span class="red">*</span>ѧ��</th>
						<td  width="30%">
							<input type="text" id="xh" name="xh" value="<bean:write name="userName"/>" readonly="readonly" />
						</td>
					</logic:equal>
					<th  width="20%"><bean:message key="lable.xsgzyxpzxy" /></th>
					<td  width="30%">	
						${rs.xymc}							
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						${rs.xm}
					</td>							
					<th>רҵ</th>
					<td>
						${rs.zymc}
					</td>
				</tr>
				<tr>	
					<th>�Ա�</th>
					<td>
						${rs.xb}
					</td>
					<th>�༶</th>
					<td>
						${rs.bjmc}
					</td>
				</tr>						
				<tr>
					<th><span class="red">*</span>������������</th>
					<td>
						<html:text property="hkssqx" styleId="hkssqx" onkeyup="replaceStr(this)" maxlength="20" />
					</td>
					<th><span class="red">*</span>���������ֵ�</th>
					<td>
						<html:text  property="hkssjd" styleId="hkssjd" onkeyup="replaceStr(this)" maxlength="25"></html:text>
					</td>
				</tr>	
				<tr>
					<th><span class="red">*</span>������ϸ��ַ</th>
					<td>
						<html:text  property="hkxxdz" styleId="hkxxdz" onkeyup="replaceStr(this)" maxlength="40"/>
					</td>
					<th>�绰��������ϵ��ʽ</th>
					<td>
						<html:text  property="lxfs" styleId="lxfs" onkeyup="replaceStr(this)" onkeypress="return onlyNum(this,25)"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>����ת���صĵ�λ��ϸ��ַ</th>
					<td>
						<html:text  property="zddwdz" styleId="zddwdz" onkeyup="replaceStr(this)" maxlength="50"/>
					</td>
					<th>����ת�����ʱ�</th>
					<td>
						<html:text property="zddwyb" styleId="zddwyb" onkeypress="return onlyNum(this,10)"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>����ת���صĵ�λ����(ȫ��)</th>
					<td>
						<html:text  property="zddwmc" styleId="zddwmc" onkeyup="replaceStr(this)" maxlength="50"/>
					</td>
					<th></th>
					<td></td>
				</tr>
				
				<tr>
					<th><span class="red">*</span>��������</th>
					<td colspan="3">
						<html:textarea  property="sqly" cols="80" rows="4" style="width: 95%;word-break:break-all;" onkeyup="replaceStr(this)" onblur="chLeng(this,150)" styleId="sqly"/>
					</td>
				</tr>	
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button class="button2"
							onclick="commSave('/xgxt/stu_archives_apply.do?doType=save',
							'xh-sqly-zddwmc-zddwdz-hkssqx-hkssjd-hkxxdz')">
							�� ��
						</button>
						<button class="button2" onclick="printLjsApp('stu_info_add.do?method=printLjsApp')">
							�� ӡ
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
					alert("����ɹ���");
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("����ʧ�ܣ�");
				</script>
			</logic:equal>
			</logic:notEmpty>
			<logic:equal value="no" name="isLjs">
			<script>
				alert("ֻ�б�ҵ�������ϵ�ѧ���ſɲ������㲻�Ǳ�ҵ�������ϵ�ѧ����");
			</script>				
			</logic:equal>
	</html:form>
</body>
</html>
