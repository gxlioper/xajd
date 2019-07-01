<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script	language="javascript" defer="defer" >
			//��þ�ѵѧ��������Ϣ
			function getJxxs(){
				var url = 'gyglnew_qszf.do?method=getJxxs';	
				showTopWin(url,800,600);
			}
			// ����
			function Save(){
				if($("grrydm") && $("grrydm").value==""){
		     		alertInfo("������������Ϊ��!",function(tag){
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
				confirmInfo("�Ƿ�Ҫ���������ݣ�",saveGrryInfo);	
				
			}
			function checkSaveInfo(){
				var data = "true";
				jQuery.ajaxSetup({async:false});
				// �õ�JSON����
			    var parameter ={};
			    parameter["pkValue"]=escape(jQuery("#pkValue").val());
			    parameter["xn"]=escape(jQuery("#xn").val());
			    parameter["xh"]=escape(jQuery("#xh").val());
			    parameter["grrydm"]=escape(jQuery("#grrydm").val());
			    parameter["doType"]=escape("update");
				var url = "jxgl_grry_ajax.do?method=checkSaveInfo";
				jQuery.post(url,parameter,
					function(result){
						data = result;
					}
				);
				jQuery.ajaxSetup({async:true});
				return data;
			}
			
			function saveGrryInfo(tag){
			if(tag=="ok"){
				jQuery.ajaxSetup({async:false});
				// �õ�JSON����
			    var parameter ={};	
			    parameter["pkValue"]=escape(jQuery("#pkValue").val());
			    parameter["xn"]=escape(jQuery("#xn").val());
			    parameter["xh"]=escape(jQuery("#xh").val());
			    parameter["grrydm"]=escape(jQuery("#grrydm").val());
			    parameter["bz"]=escape(jQuery("#bz").val());
				var url = "jxgl_grry_ajax.do?method=update";
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
							<th colspan="4">
								<logic:equal name="doType" value="update">
								<span>���������޸�</span>
								</logic:equal>
								<logic:equal name="doType" value="view">
								<span>���������鿴</span>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr style="height:22px">
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:hidden name="rs" property="xh" styleId="xh"></html:hidden>
								${rs.xh}
							</td>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:hidden name="rs" property="xn" styleId="xn"></html:hidden>
								${rs.xn}
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								����
							</th>
							<td>
								${rs.xm}
							</td>
							<th>
								�ż�
							</th>
							<td>
								${rs.tuanmc}
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								Ӫ��
							</th>
							<td>
								${rs.yingmc}
							</td>
							<th>
								����
							</th>
							<td>
								${rs.lianmc}
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc}
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<logic:equal name="doType" value="update">
								<html:select name="rs" property="grrydm" styleId="grrydm">
									<option value=''>--��ѡ��--</option>
									<html:options collection="grryList" property="grrydm" labelProperty="grrymc"/>
								</html:select>
								</logic:equal>
								<logic:equal name="doType" value="view">
								${rs.grrymc }
								</logic:equal>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								��ע
							</th>
							<td colspan="3">
								<logic:equal name="doType" value="update">
								<html:textarea  name="rs" property='bz' styleId="bz" onblur="chLeng(this,1000);" style="word-break:break-all;width:97%" rows='3' />
								</logic:equal>
								<logic:equal name="doType" value="view">
								<html:textarea  name="rs" property='bz' styleId="bz" style="word-break:break-all;width:97%" rows='3' readonly="true"/>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
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

