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
		jQuery.post('gyglnew_tsgl.do?method=loadXsxx',{xh:jQuery('#xh').val()},function(data){
			var xh = data.xh;
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
						"</td></tr>"
			
			jQuery('#xsxx').append(option);		
			jQuery('#xstr').css({display:''});						
		},'json');
	}
		
		function getLd(){
			jQuery('#ch').empty();
			jQuery('#qsh').empty();
			jQuery('#cwh').empty();
			jQuery.getJSON('gyglnew_tsgl.do?method=getLd',{xh:jQuery("#xh").val()},function(data){
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
			jQuery.getJSON('gyglnew_tsgl.do?method=getChForLddm',{lddm:jQuery('#lddm').val(),xh:jQuery("#xh").val()},function(data){
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
			var obj = {lddm:jQuery('#lddm').val(),ch:jQuery('#ch').val(),xh:jQuery("#xh").val()};
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
			var obj = {lddm:jQuery('#lddm').val(),qsh:jQuery('#qsh').val(),xh:jQuery("#xh").val()};
			
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

		function loadXs(){
			//��ѡ��ѡ��ѧ��
			var xhs = window.dialogArguments.document.getElementsByName("primarykey_checkVal");
			//��ѯ�������ݼ��� ����			
			var num = window.dialogArguments.document.getElementById("num").value;
			//��ѯ�����ݼ��Ĳ�ѯ����
			var searchTjstr = window.dialogArguments.document.getElementById("searchTjstr").value;

			var RowsStr="";
			var count =0;
			for (i=0; i<xhs.length; i++){
	 			if(xhs[i].checked){
	 				RowsStr+=xhs[i].value+"!!splitOne!!";
	 				count++;
	 			}
			}
			if(count==""){//δѡ��ѧ��
				jQuery('#searchTjstr').val(searchTjstr);
				jQuery('#xhtd').html("���޲�ѯ����ȫ��ѧ����<font color='red'>"+num+"</font>��");
				if(num == "1"){//�����ݼ���һ�����ݣ�����ѧ����Ϣ
					jQuery('#xhstr').val(xhs[0].value);
					jQuery('#xh').val(xhs[0].value);
					loadXsxx();
				}
			}else{//ѡ��ѧ��
				jQuery('#xhstr').val(RowsStr);
				jQuery('#xhtd').html("���޵�ǰѡ��ѧ��<font color='red'>"+count+"</font>��");
				if(count=="1"){//ѡ��һ��ѧ��������ѧ����Ϣ
					jQuery('#xh').val(RowsStr.replace("!!splitOne!!",""));
					loadXsxx();
				}
			}

		}
		
		function changeShow(){
			var tsyy = document.getElementById("tsyy").value;
			
			if(tsyy=="ס���춯" && jQuery('#xh').val()!=""){
				getLd();
				document.getElementById("ldqs").style.display="";
				document.getElementById("cw").style.display="";
			}else{
				document.getElementById("ldqs").style.display="none";
				document.getElementById("cw").style.display="none";
			}
		} 
		
		function saveTsxx(){
			saveData('gyglnew_tsgl.do?method=tsglPladd&doType=save','');
		}
	</script>
</head>
<body onload="loadXs()">
	<html:form action="/gyglnew_tsgl" method="post">	
		<input type="hidden" id="xh" name="xh"��value=""/>
		<input type="hidden" id="xhstr" name="xhstr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ����</a>
			</p>
		</div>		
		--%>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<font color="red">*</font>��������				
					</th>
					<td colspan="3" id="xhtd">
						
					</td>
				</tr>
				<tr id="xstr" style="display: none">
					<th width="16%">
						<font color="red">*</font>����ѧ����Ϣ				
					</th>
					<td colspan="3" >
						<table id="xsxx" style="width: 100%">
							<tr>
								<th>ѧ��</th><th>����</th><th>�Ա�</th><th>�꼶</th><th><bean:message key="lable.xsgzyxpzxy" /></th><th>¥��</th>
								<th>����</th><th>��λ</th>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="16%">
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
