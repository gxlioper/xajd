<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var sqid = jQuery("#sqid").val();
				var splc = jQuery("#splc").val();
				var shid = jQuery("#shid").val();
				if(jQuery("#xxdm").val() == "11488"){
					jQuery.ajaxSetup({async:false});
				}			
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
				if(jQuery("#xxdm").val() == "11488"){
					getXmje();
					if(jQuery("#shlccx_table tr").length>2){
						jQuery("#sxje").html("���޽��:"+jQuery("#shje").val())
					}
					if(jQuery("#xmje").val()==null || jQuery("#xmje").val()==""){
						jQuery("th.qzxy").css("display","none");
						jQuery("td.qzxy").css("display","none");
					}					
					jQuery.ajaxSetup({async:true});					
				}																							
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
					
				var shXmdm = jQuery("input[name=shXmdm]").last().val();
				jQuery("#pdjx").val(shXmdm);
				
			});
			
			function saveAuding(){
				
				if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == null){
					showAlert("�뽫��������д������");
					return false;
				}
				if (jQuery("#pdjx").val() == "" || jQuery("#pdjx").val() == null){
					showAlert("�뽫��������д������");
					return false;
				}
				
				if(jQuery("#xxdm").val() == "11488"){
					if(jQuery("#xmje").val()==null || jQuery("#xmje").val()==""){
						
					}else{
						if(jQuery("#shje").val() == "" || jQuery("#shje").val() == null){
							showAlert("�뽫��������д������");
							return false;
						}
					}
					
				}
							
				var url = "bzjl_sqsh.do?method=runAuding";
				ajaxSubFormWithFun("sqshForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				});
			}
			
			function checkResult(xmdm){
				var xh = jQuery("#xh").val();
				
				jQuery.post("bzjl_sqsh.do?method=checkCondition",{xh:xh,xmdm:xmdm},function(data){
					
					if (data.length == 0){
						jQuery("#checkTd").html("<font color='green'>����������</font>");
					} else {
						var html="";
						
						for (var j = 0, m = data.length ; j<m ; j++){
							if (data[j]["result"] == "true"){
								html+="<img src='images/ico_38.gif' title='��������'/>";
							} else {
								html+="<img src='images/ico_39.gif' name='faidImg' title='����������'/>";
							}
							html+= "  ";
							html+= (j+1)+"��";
							html+= data[j]["sqts"];
							html+= ";";
							html+="<br/>";
						}
						
						jQuery("#checkTd").html(html);
					}
				},"json");
								
			}
			
			function jgcxView(){
				var url = "xpjpy_pjpybjpy_jgcxgl.do?method=jgcxView&xn=${mkxxForm.xn}&xq=${mkxxForm.xq}&sqr=${mkxxForm.xh}&shztbjpy=99&xmdm=${mkxxForm.dqxmdm}";
				var title = "�鿴��������";
				showDialog(title,800,500,url);
			}

			

			//��֤��˽���Ƿ񳬹�������ޣ����ҳ�棩
			function checkJesxSh(obj){
				var ylzd1= jQuery(obj).val();
				var xmje = jQuery("#xmje").val();
				if(parseFloat(ylzd1)>parseFloat(xmje)){
					showAlertDivLayer("��˽�����Ŀ���޽��",{},{"clkFun":function(){
						obj.focus();
					}});
					return false;
				}
			}

			//��ȡ��Ŀ���
			function getXmje(){
				if(jQuery("#shlccx_table tr").length>2){
					jQuery("#shje").val(jQuery.trim(jQuery("#shlccx_table tr").eq(-2).find("td").eq(-1).text()));
					jQuery("#xmje").val(jQuery.trim(jQuery("#shlccx_table tr").eq(-2).find("td").eq(-1).text()));
					}
				else{
				jQuery("#shje").val(jQuery("#xmje").val());
				}			
			}

			function getXmxx(xmdm){
				if(jQuery("#xxdm").val() == "11488"){
					jQuery.post("bzjl_sqsh.do?method=getXmje",{xmdm:xmdm},function(data){
						if (data["xmje"] == '0' || data["xmje"] == null){
							jQuery("#shje").val(null);
							jQuery("th.qzxy").css("display","none");
							jQuery("td.qzxy").css("display","none");
						} else {
							jQuery("#shje").val(data["xmje"]);								
							jQuery("#sxje").html("���޽��:"+data["xmje"]);							
						}
					},"json");
				}
			};

			//���ӻ���Ϣ
			function addHjxx(){
				var xh = jQuery("#xh").val();
				var url = "bzjl_sqsh.do?method=getHjjgAdd&xh="+xh;
				showDialog('ѡ�����뽱��',800,550,url);
			}
		</script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="sqid" styleId="sqid"/>
			<html:hidden property="shid" styleId="shid"/>
			<html:hidden property="splc" styleId="splc"/>
			<html:hidden property="gwid" styleId="gwid"/>
			<html:hidden property="dqxmdm" styleId="dqxmdm"/>
			<html:hidden property="xh" styleId="xh" />
			<html:hidden property="xn" />
			<html:hidden property="xq" />
			<input type="hidden" value="${jbxx.xh }" id="xh"/>
			<input type="hidden" id="xmje" value="${xmwhModel.xmje }" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			
			
			<div style='tab;width:100%; height: 450px; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�۲�ɼ�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">�۲���Ŀ</th>
										<th style="text-align: center;">����</th>
										<th style="text-align: center;">����������</th>
										<th style="text-align: center;">�꼶רҵ����</th>
										<th style="text-align: center;">�༶����</th>
									</tr>
									<logic:present name="zcfsList">
										<logic:iterate id="z" name="zcfsList">
											<tr>
												<td>${z.xmmc }</td>
												<td>${z.fs }</td>
												<td>${z.cpzpm }</td>
												<td>${z.njzypm }</td>
												<td>${z.bjpm }</td>
											</tr>
										</logic:iterate>
										<logic:empty name="zcfsList">
											<tr>
												<td colspan="5">δ�ҵ��κμ�¼��</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
					<logic:equal name="xxdm"value="12865">
						<thead>
							<tr>
								<th colspan="4">
									<span>�۲�ɼ���������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
									<table width="99%" style="text-align: center;">
										<tr>
											<th style="text-align: center;">ѧ��ѧ��</th>
											<th style="text-align: center;">�༶����</th>
										</tr>
										<logic:present name="zcpmAll">
											<logic:iterate id="z" name="zcpmAll">
												<tr>
													<td>${z.xn }${z.xq }</td>
													<td>${z.pm}</td>
												</tr>
											</logic:iterate>
											<logic:empty name="zcpmAll">
												<tr>
													<td colspan="5">δ�ҵ��κμ�¼��</td>
												</tr>
											</logic:empty>
										</logic:present>
									</table>
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>���뽱��&nbsp;&nbsp;	
								<logic:equal value="1" name="hjjgxskg">
								<a href="javascript:void(0);" onclick="addHjxx()" >
										<font color="blue"  id="hjbutton" ><u>ѡ�����Ϣ</u></font>	
									</a>
								</logic:equal></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								<bean:write property="xn" name="xpjSqshModel"/>&nbsp;<bean:write property="xqmc" name="xpjSqshModel"/>
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td>
								${xmwhModel.xmmc }
							</td>
							<th>
								��Ŀ���
							</th>
							<td>
								${xmwhModel.xmje }
							</td>
						</tr>
						<logic:equal value="	10279" name="xxdm">
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${mkxxForm.ylzd1 }
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td colspan="3">
								${mkxxForm.ylzd3}
							</td>
						</tr>
						<tr>
							<th>
								���쵥λ
							</th>
							<td colspan="3">
								${mkxxForm.ylzd4 }
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="10355" name="xxdm">
							<logic:equal value="1" name="hjjgxskg">
								<tr id="hjtr">
								<th>����Ϣ</th>
								<td colspan="3">
									<table width="100%">
									<thead>
										<tr>
											<td width="30%">������</td>
											<td width="15%">��ʱ��</td>
											<td width="55%">�佱��λ</td>
										</tr>
									</thead>
									<tbody id="hjxx">
										<logic:iterate id="i" name="hjjgList">
											<tr>
												<td>${i.hjmc}</td>
												<td>${i.hjsj}</td>
												<td>${i.fjdw}</td>
											</tr>									
										</logic:iterate>
									</tbody>
								</table>
								</td>
							</tr>
							</logic:equal>
						</logic:equal>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="ylzd5" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
						<%--����ҽҩ�ߵ�ר�Ƹ��Ի��ֶ�--%>
						<logic:equal value="70002" name="xxdm">
						<tr>
						   <th>
								���ܺν���
						   </th>
						   <td colspan="3" style="word-break:break-all;">
						   	<bean:write property="djjl" name="xpjSqshModel" filter="false"/>
						   </td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								<logic:equal value="70002" name="xxdm">
									��Ҫ�¼�
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									����/�ϱ�����
								</logic:notEqual>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<bean:write property="sqly" name="xpjSqshModel" filter="false"/>
							</td>
						</tr>
					</tbody>
					<logic:equal name="SFBJPY_Y" value="true">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶������<a href='javascript:void(0);' class='name' onclick='jgcxView();return false;'>�鿴��������</a></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								����С���Ա
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								����С�����
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgshztmc }
							</td>
						</tr>
						<tr>
							<th>
								�����ʱ��
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhsj }
							</td>
						</tr>
						<tr>
							<th>
								�����ص�
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhdd }
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td colspan="3" style="word-break: break-all;">
								${mkxxForm.bjpyjgpyyj }
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>						
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td id="shjgSpan">
								
							</td>
							
							<logic:equal value="11488" name="xxdm">
							
								<th class="qzxy">
									<font class="je" color="red">*</font>
									<font class="je">��˽��</font>
								</th>
								<td class="qzxy">
									<html:text onkeyup="checkInputData(this);"  onblur="checkJesxSh(this);" maxlength="7" styleClass="je" property="shje" styleId="shje" style="width:100px"></html:text>
									<font class="je" id="message" color="blue"><span id="sxje">���޽�${xmwhModel.xmje }</span></font>
								</td>
							
							</logic:equal>
							
						</tr>
						<tr>
							<th id="shglTh"><font color="red">*</font>��������</th>
							<td id="shglTd" colspan="3">
								<html:select property="pdjx" styleId="pdjx" onchange="getXmxx(this.value);checkResult(this.value);">
									<html:option value="${dqxmwhModel.xmdm }">${dqxmwhModel.xmmc }</html:option>
									<logic:present name="ktzPjxmList">
										<html:options collection="ktzPjxmList" property="xmdm" labelProperty="xmmc"/>
									</logic:present>
								</html:select>
								
								<html:select property="thgw" styleId="thgw" style="display:none;">
									<html:options collection="kthGwList" labelProperty="gwmc" property="spgw"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" id="checkTd">
								<logic:notEmpty name="checkResult">
									<logic:iterate id="check" name="checkResult" indexId="i">
										<logic:equal value="true" name="check" property="result">
											<img src='images/ico_38.gif' title='��������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
										<logic:equal value="false" name="check" property="result">
											<img src='images/ico_39.gif' name='faidImg' title='����������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="checkResult">
									<font color='green'>����������</font>
								</logic:empty>								
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								������
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=pjpy&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="5"
											   onblur="checkLen(this,200);" styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveAuding();">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

