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
		function checkExists(tableName, pk){
			var lddm = jQuery('#lddm').val();
			var qsh = jQuery('#qsh').val();
			var cwh = jQuery('#cwh').val();
			var pkV = lddm + qsh + cwh;
			
			dwr.engine.setAsync(false);
			
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					jQuery('#span_cwh').show('normal')
					jQuery('#buttonSave').attr('disabled', 'disabled');
				}else{
					jQuery('#span_cwh').hide('normal')
					jQuery('#buttonSave').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}
	
		function getQsh(){			
			jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm',{lddm:jQuery('#lddm').val(), ch:jQuery('#ch').val()},function(data){
				jQuery('#qsh').empty();
				jQuery('#qsh').append("<option value=''>--��ѡ��--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].qsh + "\">" + data[i].qsh + "</option>";
						jQuery('#qsh').append(option);
					}
					if(jQuery("#qshV").val()!=""){
						jQuery("#qsh").val(jQuery("#qshV").val());
					}
				}
			});
		}
		
		function loadLdxx(){
			if($("doType").value=="update"){
				return false;
			}
			jQuery.getJSON('gyglnew_qsgl.do?method=loadLdxx',{lddm:jQuery('#lddm').val()},function(data){
				var qsch = parseInt(data.qsch);
				var ldcs = parseInt(data.ldcs);
				var sfhlc = data.sfhlc;
				var ldxb = data.ldxb;
				
				var count = 0;
				
				jQuery('#ch').empty();
				jQuery('#ch').append("<option value=''>--��ѡ��--</option>");
				while(count<ldcs){
					var chdm;
					var chmc;
					if('��' == sfhlc){
						
						if((qsch+count)>= 0){
							chdm = qsch>0 ? qsch+count:qsch+count+1;
							chmc = chdm + "��";
						}else{
							chdm = qsch+count;
							chmc = "B" + Math.abs(chdm) + "��";	
						}
						
					}else{
						chdm = qsch+count;
						chmc = chdm<0 ? "B" + Math.abs(chdm) + "��" : chdm + "��";
					}
					
					var option = "<option value=\"" + chdm + "\">" + chmc + "</option>"
					jQuery('#ch').append(option);
					
					count ++;
				}
				if(jQuery("#chV").val()!=""){
					jQuery("#ch").val(jQuery("#chV").val());
				}
				getQsh();
			});
		}
		//��ȡ����������Ϣ
		function getWmqsxx(){
			var obj = {pkValue:jQuery('#lddm').val()+""+jQuery('#qsh').val()};
			
			jQuery.getJSON('gzdxWmqs.do?method=getWmqsxx', obj, function(data){
				if(data != null){
					//jQuery('#qsrs').val(data.qsrs);
					jQuery('#wspjf').html(data.wspjf);
					jQuery('#sqsm').val(data.sqsm);
					jQuery('#qsxs_td').html(data.xsHtml);
				}	
			});
		}
	</script>
</head>
<body onload="loadLdxx()">
	<html:form action="/gzdxWmqs.do?method=wmqsglModi" method="post">
		<input type="hidden" id="doType" value="${doType}"/>
		<input type="hidden" id="lddmV" value="${rs.lddm}" />
		<input type="hidden" id="chV" value="${rs.ch }" />
		<input type="hidden" id="qshV" value="${rs.qsh }" />
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��λ��Ϣά��</a>
			</p>
		</div>		
		--%>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>������������</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<logic:equal value="add" name="doType">
				<tr>
				<th align="right" width="16%">
					<font color="red">*</font>¥������
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					<html:select property="lddm" styleId="lddm" name="rs" onchange="loadLdxx();">
						<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
					</html:select>
				</td>
				<th width="16%">���</th>
				<td width="38%">
					<html:select property="ch" styleId="ch" name="rs" onchange="getQsh();">
					</html:select>
				</td>
			</tr>

			<tr>
				<th width="16%">
					<font color="red">*</font>���Һ�				
				</th>
				<td width="34%">
					<html:select property="qsh" styleId="qsh" name="rs" onchange="getWmqsxx();">
					</html:select>
				</td>
<%--				<th>--%>
<%--					��������--%>
<%--				</th>--%>
<%--				<td>--%>
<%--					<html:text property="qsrs" name="rs" styleId="qsrs"></html:text>--%>
<%--				</td>--%>
				<th>
					<font color="red"></font>����ƽ����
				</th>
				<td>
					   <span id="wspjf"><bean:write name="rs" property="wspjf"/></span>
				</td>
			</tr>
			</logic:equal>
			<logic:equal value="update" name="doType">
				<tr>
				<th align="right" width="16%">
					<font color="red"></font>¥������
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					<input type="hidden" id="lddm" name="lddm" value="${rs.lddm}" />
					${rs.ldmc }
				</td>
				<th width="16%">���</th>
				<td width="38%">
					<input type="hidden" id="ch" name="ch" value="${rs.ch}" />
					${rs.chmc }
				</td>
			</tr>

			<tr>
				<th width="16%">
					<font color="red"></font>���Һ�				
				</th>
				<td width="34%">
					<input type="hidden" id="qsh" name="qsh" value="${rs.qsh}" />
					${rs.qsh}
				</td>
<%--				<th>--%>
<%--					��������--%>
<%--				</th>--%>
<%--				<td>--%>
<%--					<html:text property="qsrs" name="rs" styleId="qsrs"></html:text>--%>
<%--				</td>--%>
				<th>
					<font color="red"></font>����ƽ����
				</th>
				<td>
					   <span id="wspjf"><bean:write name="rs" property="wspjf"/></span>
				</td>
			</tr>
			</logic:equal>
			
			<tr>
				<th>
					����ѧ��
					<br /><font color="red"></font>
				</th>
				<td colspan="3" id="qsxs_td">
					<table width="100%">
						<tr>
							<th>ѧ��</th>
							<th>����</th>
							<th><bean:message key="lable.xb" /></th>
							<th>רҵ</th>
							<th>�༶</th>
						</tr>
						<logic:present name="xsList">
						<logic:iterate id="xs" name="xsList">
						<tr>
							<td><bean:write name="xs" property="xh"/></td>
							<td><bean:write name="xs" property="xm"/></td>
							<td><bean:write name="xs" property="xymc"/></td>
							<td><bean:write name="xs" property="zymc"/></td>
							<td><bean:write name="xs" property="bjmc"/></td>
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
				</td>
			</tr>
			<tr>
				<th>
					����˵��
					<br /><font color="red">(������500����)</font>
				</th>
				<td colspan="3">
					<html:textarea property='sqsm' style="width:95%" styleId="sqsm" name="rs" rows='7' value="${rs.sqsm}" onblur="chLeng(this,500);"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave"  onclick="saveData('gzdxWmqs.do?method=wmqsglModi&doType=save','lddm-qsh');">����</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
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
