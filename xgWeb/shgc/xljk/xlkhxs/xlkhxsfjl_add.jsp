<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript">
		function xlkhxsftjl_save(){
			var xh=document.all["xh"].value;
			if ( xh==""){
				alert ("�����ѡ�񰴼�ѡ���ѧ����Ϣ��");
				document.all["xh"].focus();
				return false;
			}
			var ftsj=document.all["ftsj"].value;
			if ( ftsj==""){
				alert ("����д��̸���ڣ�");
				document.all["ftsj"].focus();
				return false;
			}
			var ftdd=document.all["ftdd"].value;
			if ( ftdd==""){
				alert ("����д��̸�ص㣡");
				document.all["ftdd"].focus();
				return false;
			}
			var ftr=document.all["ftr"].value;
			if ( ftr==""){
				alert ("��д��̸�ˣ�");
				document.all["ftr"].focus();
				return false;
			}
			var ftjl=document.all["ftjl"].value;
			if ( ftjl==""){
				alert ("����д��̸��¼��");
				document.all["ftjl"].focus();
				return false;
			}
			var sjhm=document.all["sjhm"];
			sjhm.value = sjhm.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = sjhm.value;
			if((inputStr != null && inputStr != "")&&!inputStr.match(/\d+/g)){		
				alert("�ֻ������������������͵����ݣ�");
				sjhm.value = '';
				sjhm.focus();
				return false;
			}
			document.all["add_flag"].value="yes";
			underDealWith();
			refreshForm('/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_add');
		}
		function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
	</script>
	</head>
	<body onload="jd()">
		<html:form action="/xljk_xlkhxsftjl" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
			
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>������������ѧ����̸��¼</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button name="����" onclick="xlkhxsftjl_save()" id="buttonSave">�� ��</button>
			            <button name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
			<tbody>
				<tr style="height:22px">
					<th align="right" colspan="2" nowrap="nowrap">
						<font color="red">*</font>����ѧ��ѧ��
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" onblur="" onkeypress=""
							readonly="true" />
						<button
							onclick="showTopWin('/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=stu_info',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>

					<th align="right">
						����
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2" readonly="true">
						�Ա�
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						<bean:message key="lable.xb" />
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						�༶
					</th>
					<td align="left">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
					<th align="right" nowrap="nowrap">
						<font color="red">*</font>��̸����
					</th>
					<td align="left">
						<html:text style="cursor:hand;" styleId="dateF"
							property="ftsj" onclick="return showCalendar('dateF','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						<font color="red">*</font>��̸�ص�
					</th>
					<td align="left">
						<html:text property="ftdd" maxlength="20"/>
					</td>
					<th align="right">
						<font color="red">*</font> ��̸��
					</th>
					<td align="left" colspan="4">
						<html:text property="ftr" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						�ֻ�����
					</th>
					<td align="left">
						<html:text property="sjhm" maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>
					</td>
					<th align="right">
					</th>
					<td align="left" colspan="4">
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						<font color="red">*</font>��̸��¼
					</th>
					<td align="left" colspan="6">
						<html:textarea rows="20" style="width:98%" property="ftjl"
							styleId="a" onblur="chLeng(this,2000)"/>
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						��ע
					</th>
					<td align="left" colspan="6">
						<html:textarea rows="3" style="width:98%" property="bz"
							styleId="a" onblur="chLeng(this,1000)"/>
					</td>
				</tr>
			</table>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="gcybh_exits">
			<script>alert("����Ѿ�����!����ʧ��")</script>
		</logic:equal>
		<logic:equal name="message" value="xh_exits">
			<script>alert("��ѧ���Ѿ�����!����ʧ��")</script>
		</logic:equal>
		<logic:equal name="message" value="insert_success">
			<script>
				alert("���ӳɹ�!");
				window.dialogArguments.document.getElementById("search_go1").click();
				Close();
			</script>
		</logic:equal>
		<logic:equal name="message" value="insert_fail">
			<script>
				alert("����ʧ��!");
				document.getElementById("tmpdiv").innerHTML = "";
			</script>
		</logic:equal>
	</logic:notEmpty>
</html>
