<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript">
			function Save(){
				var zdgcdxdm=document.all["zdgcdxdm"].value;
				if ( zdgcdxdm==""){
					alert ("��ѡ���Ƿ��ص�۲����");
					document.all["zdgcdxdm"].focus();
					return false;
				}
				document.all["modi_flag"].value="yes";
				underDealWith();
				refreshForm('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_modi');
			}
		</script>
	</head>
	<body>
		<html:form action="/xljk_xlkhxs" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ��������ѧ������ - ��������ѧ����Ϣ - �޸���Ϣ</a>
				</p>
			</div>
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<input type="hidden" id="xn_id" name="xn_id"
				value="<bean:write name="xn_id" scope="request"/>" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>�޸���Ϣ</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          		<button name="�޸�" onclick="Save();return false;" id="buttonSave">�� ��</button>
			           		<button name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height:22px" name="aa" id="a1">
					<th align="right" colspan="2">
						ѧ ��
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" onblur="" onkeypress=""
							readonly="true" />
					</td>

					<th align="right">
						�� ��
					</th>
					<td align="left" colspan="2">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px" name="aa" id="a2">
					<th align="right" colspan="2" readonly="true">
						�� ��
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						�� ��
					</th>
					<td align="left" colspan="2">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px" name="aa" id="a3">
					<th align="right" colspan="2">
						ѧ Ժ
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
					<th align="right">
						�ص�۲����
					</th>
					<td align="left" colspan="2">
						<html:select property="zdgcdxdm" style="width:80px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="zdgcdxList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
				</tr>
				</tbody>
			</table>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:notEmpty name="message">
			<logic:equal name="message" value="update_success">
				<script>
					alert("�޸ĳɹ�!");
					window.dialogArguments.document.getElementById("search_go1").click();
					Close();
				</script>
			</logic:equal>
			<logic:equal name="message" value="update_fail">
				<script>
					alert("�޸�ʧ��!");
					document.getElementById("tmpdiv").innerHTML = "";
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
