<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script	language="javascript" defer="defer" >
			// ����
			function Save(){
				if($("tdrydm") && $("tdrydm").value==""){
		     		alertInfo("�Ŷ���������Ϊ��!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
	     		}
				var message = checkSaveInfo();
				if("true"!=message){
					alertInfo(message,function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
				confirmInfo("�Ƿ�Ҫ�����޸ĵ����ݣ�",saveTdryInfo);	
				
			}
			function checkSaveInfo(){
				var data = "true";
				jQuery.ajaxSetup({async:false});
				// �õ�JSON����
			    var parameter ={};
			    parameter["pkValue"]=escape(jQuery("#pkValue").val());
			    parameter["xn"]=escape(jQuery("#xn").val());
			    parameter["bzdm"]=escape(jQuery("#bzdm").val());
			    parameter["tdrydm"]=escape(jQuery("#tdrydm").val());
			    parameter["doType"]=escape("update");
				var url = "jxgl_tdry_ajax.do?method=checkSaveInfo";
				jQuery.post(url,parameter,
					function(result){
						data = result;
					}
				);
				jQuery.ajaxSetup({async:true});
				return data;
			}
			
			function saveTdryInfo(tag){
				if(tag=="ok"){
					jQuery.ajaxSetup({async:false});
					// �õ�JSON����
				    var parameter ={};	
				    parameter["pkValue"]=escape(jQuery("#pkValue").val());
				    parameter["xn"]=escape(jQuery("#xn").val());
				    parameter["bzdm"]=escape(jQuery("#bzdm").val());
				    parameter["bzjbdm"]=escape(jQuery("#bzjbdm").val());
				    parameter["tdrydm"]=escape(jQuery("#tdrydm").val());
				    parameter["bz"]=escape(jQuery("#bz").val());
					var url = "jxgl_tdry_ajax.do?method=update";
			        $("divWaiting").style.display="";
					$("divDisable").style.display="";
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
						}
					);
					jQuery.ajaxSetup({async:true});
					}
			}
		</script>
	</head>
	<body>
		<html:form action="/jxgl_grry" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>������������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr style="height:22px">
							<th width="16%">
								<span class="red">*</span>ѧ��
							</th>
							<td width="84%">
								<html:hidden name="rs" property="xn" styleId="xn"/>
								${rs.xn}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<span class="red">*</span>���Ƽ���
							</th>
							<td width="84%">
								<html:hidden name="rs" property="bzjbdm" styleId="bzjbdm"/>
								${rs.bzjbmc}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<span class="red">*</span>��������
							</th>
							<td width="84%">
								<html:hidden name="rs" property="bzdm" styleId="bzdm"/>
								${rs.bzmc}
							</td>
						</tr>
						<logic:equal name="doType" value="update">
						<tr style="height:22px">
							<th>
								<font color="red">*</font>�Ŷ�����
							</th>
							<td>
								<html:select name="rs" property="tdrydm" style="width:180px" styleId="tdrydm">
									<html:options collection="tdryList" property="tdrydm" labelProperty="tdrymc"/>
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��ע
							</th>
							<td>
								<html:textarea name="rs" property='bz' styleId="bz" onblur="chLeng(this,1000);" style="word-break:break-all;width:97%" rows='3' />
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="doType" value="view">
						<tr style="height:22px">
							<th>
								<font color="red">*</font>�Ŷ�����
							</th>
							<td>
								${rs.tdrymc}
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��ע
							</th>
							<td>
								<html:textarea name="rs" property='bz' styleId="bz" style="word-break:break-all;width:97%" rows='3' readonly="true"/>
							</td>
						</tr>
						</logic:equal>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:equal name="doType" value="update">
									<button type="button" onclick="Save();return false;">
										�� ��
									</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

