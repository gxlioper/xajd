<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsfunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
	function xlkhxsxx_save(){
		var xh=document.all["xh"].value;
		if ( xh==""){
			alert ("�����ѡ�񰴼�ѡ���ѧ����Ϣ��");
			document.all["xh"].focus();
			return false;
		}
		var zdgcdxdm=document.all["zdgcdxdm"].value;
		if ( zdgcdxdm==""){
			alert ("��ѡ���Ƿ��ص�۲����");
			document.all["zdgcdxdm"].focus();
			return false;
			}
		document.all["add_flag"].value="yes";
		underDealWith();
		refreshForm('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_add');
	}	
	</script>
	</head>
	<body>
		<html:form action="/xljk_xlkhxs" method="post">
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��11:</em><a>������ - ��������ѧ������ - ��������ѧ����Ϣ - ����ѧ����Ϣ</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>��������ѧ��</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button name="�ύ" onclick="xlkhxsxx_save()" id="buttonSave">�� ��</button>
			            <button name="�ر�"  onclick="Close();return false;"  id="buttonClose">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
				<tbody>
				<tr style="height:22px" name="aa" id="a1">
					<th align="right" colspan="2" nowrap>
						 ѧ ��
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" onblur="" onkeypress=""
							readonly="true" />
						<button
							onclick="showTopWin('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=stu_info',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>

					<th align="right">
						�� �� 
					</th>
					<td align="left" colspan="2">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px" name="aa" id="a2">
					<th align="right" colspan="2">
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
			</table>
			
			<div id="tmpdiv"></div>
		</html:form>
	</body>
	<logic:notEmpty name="message">
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
		<logic:equal name="message" value="xh_exits">
			<script>alert("��ѧ���Ѿ����ڣ�����ʧ�ܣ�")</script>
		</logic:equal>
	</logic:notEmpty>
</html>
