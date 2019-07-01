<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function loadXsxx(){
			var srxh = jQuery('#xh').val();
			if(srxh == ""){
				setTimeout('checkXh("������ѧ��")',500);				
				return false;
			}else{
				var xhs = document.getElementsByName("pk_xh");
				var flag = "y";
				for(var i=0;i<xhs.length;i++){
					if(srxh == xhs[i].value){
						flag = "n";
						break;
					}
				}	
				if(flag != "y"){
					setTimeout('checkXh("��ѧ������ӣ������ظ����")',500);
					jQuery('#xh').val('');	
					return false;
				}			
			}
			jQuery.post('gyglnew_tsgl.do?method=loadXsxx',{xh:srxh},function(data){
				var xh = data.xh;
				if(xh==undefined){
					alertInfo("��ѧ������ס��Ϣ���������ޣ�");
					jQuery('#xh').val('');	
					return false;
				}
				var xm = data.xm;
				var xb = data.xb;
				var ldmc = data.ldmc;
				var qsh = data.qsh;
				var cwh = data.cwh;
				var xymc = data.xymc;
				var nj = data.nj;
											
				var option = "<tr><input type='hidden' name='pk_xh' value='"+xh+"'/><td>" + xh + 
							"</td><td>" + xm + "</td><td>" + xb + "</td><td>" + nj + "</td><td>" + xymc + 
							"</td><td>" + ldmc + "</td><td>" + qsh + "</td><td>" + cwh + 
							"</td><td><div onclick='delRow(this);' style='cursor:hand;'>��ɾ����</div></td></tr>"
				
				jQuery('#xsxx').append(option);		
				jQuery('#xsxx').css({display:''})
				jQuery('#xh').val('');	
				changeShow();
							
			},'json');
		}

		function delRow(obj){
			var tab = document.getElementById('xsxx');
			tab.deleteRow(obj.parentNode.parentNode.rowIndex);
			if(document.getElementById('xsxx').rows.length == 1){
				jQuery('#xsxx').css({display:'none'});
			}
		}

		function getLd(){
			jQuery('#ch').empty();
			jQuery('#qsh').empty();
			jQuery('#cwh').empty();
			jQuery.getJSON('gyglnew_tsgl.do?method=getLdForXb',{xb:encodeURI(jQuery("#xb").val(),"utf-8")},function(data){
				jQuery('#lddm').empty();
				jQuery('#lddm').append("<option value=''>--��ѡ��--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].lddm + "\">" + data[i].ldmc + "</option>";
						jQuery('#lddm').append(option);
					}
				}
			});
		}

		function getCh(){
			jQuery('#qsh').empty();
			jQuery('#cwh').empty();
			jQuery.getJSON('gyglnew_tsgl.do?method=getChForLddm',{lddm:jQuery('#lddm').val(),xb:encodeURI(jQuery("#xb").val(),"utf-8")},function(data){
				jQuery('#ch').empty();
				jQuery('#ch').append("<option value=''>--��ѡ��--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].ch + "\">" + data[i].chmc + "</option>";
						jQuery('#ch').append(option);
					}
				}
			});
		}

		function getQsh(){
			jQuery('#cwh').empty();
			var obj = {lddm:jQuery('#lddm').val(),ch:jQuery('#ch').val(),xb:encodeURI(jQuery("#xb").val(),"utf-8")};
			jQuery.getJSON('gyglnew_tsgl.do?method=getQshForLdch',obj,function(data){
				jQuery('#qsh').empty();
				jQuery('#qsh').append("<option value=''>--��ѡ��--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].qsh + "\">" + data[i].qsh + "</option>";
						jQuery('#qsh').append(option);
					}
				}
			});
		}
		
		function getCwh(){
			var obj = {lddm:jQuery('#lddm').val(),qsh:jQuery('#qsh').val()};
			
			jQuery.getJSON('gyglnew_tsgl.do?method=getCwhForQs', obj, function(data){
				jQuery('#cwh').empty();
				jQuery('#cwh').append("<option value=''>--��ѡ��--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].cwh + "\">" + data[i].cwh + "</option>";
						jQuery('#cwh').append(option);
					}
				}
			});
		}

		function changeShow(){
			var tsyy = document.getElementById("tsyy").value;
			var xhs = document.getElementsByName("pk_xh");
			
			if(tsyy=="ס���춯" && xhs.length==1){
				var xb = jQuery('#xsxx tr:nth-child(2) td:nth-child(4)').html();
				jQuery('#xb').val(xb);
				getLd();
				document.getElementById("ldqs").style.display="";
				document.getElementById("cw").style.display="";
			}else{
				document.getElementById("ldqs").style.display="none";
				document.getElementById("cw").style.display="none";
			}
		}

		function checkXh(msg){
			alertInfo(msg,function(){
				document.getElementById("xh").focus();
			});
		}
		
		function saveTsxx(){
			var xhs = document.getElementsByName("pk_xh");
			if(xhs.length == 0){
				alertInfo("������ѧ��");
				return false;
			}
			saveData('gyglnew_tsgl.do?method=tsglAdd&doType=save','');
		}
	</script>
</head>
<body>
	<html:form action="/gyglnew_tsgl" method="post">	
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ����</a>
			</p>
		</div>		
		--%><div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>��ʾ��</span></h3>
	       <p></p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>ѧ��
				</th>
				<td align="left" width="84%" nowrap="nowrap" colspan="3">
					<html:text property="xb" styleId="xb" style="display:none"/>
					<html:text property="xh" styleId="xh" onkeypress="if(window.event.keyCode==13) loadXsxx();"/>					
					(����ѧ�ţ��س��������ѧ��)
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<table id="xsxx" style="display: none;width: 100%">
						<tr>
							<th>ѧ��</th><th>����</th><th>�Ա�</th><th>�꼶</th><th><bean:message key="lable.xsgzyxpzxy" /></th><th>¥��</th>
							<th>����</th><th>��λ</th><th>����</th>
						</tr>
					</table>
				</td>
			</tr>
			</tbody>			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<th>
					<font color="red">*</font>����ԭ��				
				</th>
				<td colspan="3">
					<html:select property="tsyy" styleId="tsyy" onchange="changeShow()" >						
						<html:options collection="tsyyList" labelProperty="tsyymc" property="tsyymc"/>
					</html:select>
				</td>
			</tr>
			<tr id="ldqs"  style="display: none">
				<th align="right" width="16%">
					¥������
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					<html:select property="lddm" styleId="lddm" onchange="getCh();">
					</html:select>
				</td>				
				<th width="16%">
					���				
				</th>
				<td width="34%">
					<html:select property="ch" styleId="ch" onchange="getQsh();">
					</html:select>
				</td>
			</tr>
			<tr id="cw" style="display: none">			
				<th width="16%">
					���Һ�				
				</th>
				<td width="34%">
					<html:select property="qsh" styleId="qsh" onchange="getCwh();">
					</html:select>
				</td>
				<th>
					��λ��
				</th>
				<td>
					<html:select property="cwh" styleId="cwh" >
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					��ע
					<br /><font color="red">(������1000����)</font>
				</th>
				<td colspan="3">
					<html:textarea property='bz' style="width:95%" styleId="bz" rows='7'/>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="saveTsxx();">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alertInfo('����ɹ�', function(){
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();
				}	
			});
			
		</script>
	</logic:present>
</body>
</html>
