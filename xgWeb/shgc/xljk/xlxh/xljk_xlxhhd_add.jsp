<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	function xljk_xlxhhd_save(){
			var zt=document.all["zt"].value;
			if ( zt==""){
				alert ("����д���⣡");
				document.all["zt"].focus();
				return false;
			}
			var hdxs=document.all["hdxs"].value;
			if ( hdxs==""){
				alert ("��ѡ����ʽ!");
				document.all["hdxs"].focus();
				return false;
			}else if(hdxs=="013"){
				var qthdxs=document.all["qthdxs"].value;
				if(qthdxs==""){
					alert ("����д�������ʽ!");
					document.all["qthdxs"].focus();
					return false;
				}
			}
			
			var dd=document.all["dd"].value;
			if ( dd==""){
				alert ("����д��ص㣡");
				document.all["dd"].focus();
				return false;
			}
			
			var rq=document.all["rq"].value;
			if ( rq==""){
				alert ("��ѡ�����ڣ�");
				document.all["rq"].focus();
				return false;
			}
			
			var zcr=document.all["zcr"].value;
			if ( zcr==""){
				alert ("����д�����ˣ�");
				document.all["zcr"].focus();
				return false;
			}
			
			var xs=document.all["xs"].value;
			if ( xs==""){
				alert ("����дѧ����");
				document.all["xs"].focus();
				return false;
			}
			
			var cyxs=document.all["cyxs"].value;
			if ( cyxs==""){
				alert ("����д����ѧ����");
				document.all["cyxs"].focus();
				return false;
			}
			
			var rs=document.all["rs"].value;
			if ( rs==""){
				alert ("����д����ѧ��������");
				document.all["rs"].focus();
				return false;
			}
			
			var hdjl=document.all["hdjl"].value;
			if ( hdjl==""){
				alert ("����д���¼��");
				document.all["hdjl"].focus();
				return false;
			}
			if (hdjl.length >150){
				alert ("���¼���ֻ����д150�����֣�");
				document.all["hdjl"].focus();
				return false;
			}
			
			var hdxg=document.all["hdxg"].value;
			if ( hdxg==""){
				alert ("����д�Ч����");
				document.all["hdxg"].focus();
				return false;
			}
			if ( hdxg.length>150){
				alert ("�Ч�����ֻ����д150�����֣�");
				document.all["hdxg"].focus();
				return false;
			}
			document.all["add_flag"].value="yes";
			underDealWith();
			refreshForm('/xgxt/xljk_xlxhhd.do?act=xljk_xlxhhd&doType=xlxhhd_add');
	}
	
	
	function check_qthdxs(){
		var hdxs=document.all["hdxs"].value;
		if(hdxs=="013"){
			document.getElementById('qthdxs').readOnly=false;
		}else{
			document.getElementById('qthdxs').readOnly=true;
		}
	}
	</script>
	</head>
	<body>
		<html:form action="/xljk_xlxhhd" method="post">
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				 <thead>
    				<tr>
        				<th colspan="8"><span>Э����¼</span></th>
        			</tr>
   				 </thead>
			   				
				 <tbody>
				<tr>
					<th  colspan="2">
						<font color="red">*</font>����
					</th>
					<td colspan="6">
						<html:text style="width:98%" property="zt" styleId="zt" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>���ʽ
					</th>
					<td colspan="2">
						<html:select property="hdxs" style="width:150px" styleId="hdxs"
							onchange="check_qthdxs()">
							<html:option value=""></html:option>
							<html:options collection="hdxsList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
					<th colspan="2">
						������ʽ
					</th>
					<td colspan="2">
						<html:text property="qthdxs" styleId="qthdxs" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�ص�
					</th>
					<td colspan="2">
						<html:text property="dd" styleId="dd" />
					</td>
					<th colspan="2">
						<font color="red">*</font>�����
					</th>
					<td colspan="2">
						<html:text style="cursor:hand;" styleId="dateF" property="rq"
							onclick="return showCalendar('dateF','y-mm-dd');" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						��ʼʱ��
					</th>
					<td colspan="2">
						<html:text property="kssj" styleId="kssj" />
					</td>
					<th colspan="2">
						����ʱ��
					</th>
					<td colspan="2">
						<html:text property="jssj" styleId="jssj" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>������
					</th>
					<td colspan="2">
						<html:text property="zcr" styleId="zy" />
					</td>
					<th colspan="2">
						<font color="red">*</font>ѧ��
					</th>
					<td colspan="2">
						<html:text property="xs" />
					</td>
				</tr>
				<tr>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>����ѧ��
					</th>
					<td colspan="6">
						<html:text style="width:98%" property="cyxs" styleId="cyxs" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>����ѧ������
					</th>
					<td colspan="6">
						<html:text property="rs" styleId="rs" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>���¼
					</th>
					<td colspan="6">
						<html:textarea rows="5" style="word-break:break-all;width:99%" property="hdjl"
							styleId="a" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�Ч��
					</th>
					<td colspan="6">
						<html:textarea rows="5" style="word-break:break-all;width:99%" property="hdxg"
							styleId="a" />
					</td>
				</tr>
				</tbody>
				 <tfoot>
			      <tr>
			        <td colspan="8"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			     
						<button name="����" onclick="xljk_xlxhhd_save()" id="buttonSave">
							�� ��
						</button>
						<button  onclick="Close();return false;" 
							 id="buttonClose" >
							�� ��
						</button>
									           
			          </div>
			          </td>
			      </tr>
			    </tfoot>
			</table>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="insert_success">
					<script>
						alert("����ɹ�!");
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
		</html:form>
	</body>
</html>
