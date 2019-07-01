<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>

		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dksq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/hsdComm.js"></script>
		<script type="text/javascript" defer="defer">
		
		jQuery(function(){

			zdybdInit("zxdk_add");
			
			jQuery("#mnje").attr("readonly","readonly");
			jQuery("#dkje").attr("readonly","readonly");
			jQuery("#xn").val(jQuery("#dqxn").val());
			
			if("12861" == jQuery("#xxdm").val()){
				var afterxn = jQuery("#afterxn").val();
				var zdybdcon_td_xn = jQuery("td[name='zdybdcon_td_xn']").eq(0);
				var zdybdcon_td_xn_html = "<input type='hidden' name='xn' id='xn' value='"+afterxn+"' />";
				zdybdcon_td_xn_html += afterxn;
				zdybdcon_td_xn.html(zdybdcon_td_xn_html);
			}
			//��������ѡ��
			loadMkxxSelectOptions();
			//����radioѡ��
			loadMkxxRadioOptions();

			var xh = jQuery("#xh").val();
			if (jQuery.trim(xh) != ""){
				jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
				if(jQuery("#xxdm").val() == '10511'){
					autoProduce();
				}
			}

			jQuery("#zsfdks").keyup(function(){
				getzje(this);
				});
			jQuery("#xfdks").keyup(function(){
				getzje(this);
				});
			jQuery("#shfdks").keyup(function(){
				getzje(this);
				});
			jQuery("#dkqx").keyup(function(){
				getzje(this);
				});
			jQuery("#xzf").keyup(function(){
				getzje(this);
				});
			if("10335" == jQuery("#xxdm").val()){
				zddkqx();
				jQuery("#dkqx").attr("readonly","readonly");
				jQuery("#xn").change(function(){
					zddkqx();
					});
			}
		});

		function lock(){
			jQuery(".btn").find("button").each(function(){
				jQuery(this).attr("disabled","disabled");
				jQuery(this).css({color:"#cccccc"});
			});
		}
	
		function saveDksq(url){

			var xh = jQuery("#xh").val();
			var xn = jQuery("#xn").val();
			var nj = jQuery("#nj").val();
			var xz = jQuery("#xz").val();
			var dkje = jQuery("#dkje").val();
			var xzf = jQuery("#xzf").val();
			var zsf = jQuery("#zsf").val();
			var dkqx = jQuery("#dkqx").val();
			var sqly = jQuery("#sqly").val();
			var mnje = jQuery("#mnje").val();
			var zsfdks = jQuery("#zsfdks").val();
			var xfdks =  jQuery("#xfdks").val();
			var shfdks = jQuery("#shfdks").val();
			var xxdm = jQuery("#xxdm").val();

			// ���ѧ���Ƿ����
			if (xh==""){
				showAlertDivLayer("[ѧ��]����Ϊ��!");
				return false;
			}
			
			if(!zdybdCheck()){ //�����ֶ���֤
				return;
			}
			if('10335' != xxdm){
				
				if(parseInt(zsfdks)>parseInt(zsf)){
					showAlertDivLayer("ס�޷�����������ܴ���ס�޷�Ӧ������");
					return false;
				}
				if(parseInt(xfdks)>parseInt(xzf)){
					showAlertDivLayer("ѧ������������ܴ���ѧ��Ӧ������");
					return false;
				}
			}
			if(parseInt(mnje)>parseInt(jQuery("#dkzesx").val())){
				showAlertDivLayer("ÿ������ܶ��"+jQuery("#dkzesx").val()+"Ԫ!");
				return false;
			}
			
			//�Ƿ񵯳���ͥ���������дҳ��
			var openJtqk = jQuery("#openJtqk").val();

			if ("true" == openJtqk){
				var xh = jQuery("#xh").val();

				showAlertDivLayer("������д��ͥ��������",{},{"clkFun":function(){
					editJtqk();
				}});
				return false;
			}

			jQuery.post("zxdkXyddk.do?method=getCountByXhAndXn",{xh:xh,xn:xn,xz:xz,dkqx:dkqx,nj:nj},function(data){
				if (Number(data) == 0){
					ajaxSubFormWithFun("xyddkForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
					});
				} else {
					showAlertDivLayer("��ѧ���Ѿ������������ѧ�����ȷ�ϣ�");
				}
			},"json"); 
			
		}
		</script>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm" onsubmit="return false">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="splcid" value="${cssz.xydshlc }"/>
			<input type="hidden" id="openJtqk" value="${openJtqk }" />
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<input type="hidden" id="afterxn" value="${afterxn }" />
		    <input type="hidden" id="dkzesx" value="${cssz.dkzesx }"/>
		    <input type="hidden" id="xz" value="${jbxx.xz }" />
		    <input type="hidden" id="nj" value="${jbxx.nj }" />
		    <input type="hidden" id="xxdm" value="${xxdm }" />
		    <input type="hidden" id="qsxn" value="${qsxn}" />
		    <input type="hidden" id="jesx" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
				<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
				</table>
				<table width="100%" border="0" class="formlist" style="margin-top: 5px;">
					<logic:notEqual value="10264" name="xxdm"><!-- �Ϻ�������Ի�������Ҫ��ʾ -->
						<thead>
							<tr>
								<th colspan="4">
									<span>��ͥ��� 
										<logic:notEqual value="" property="xh" name="zxdkXyddkForm">
											<a onclick="showJtqk(this);" class="up"
												href="javascript:void(0);"> <font color="blue">���չ��/����</font>
											</a>
											|
											<a onclick="editJtqk();" class="btn_xg"
												href="javascript:void(0);"> <font color="blue">�༭��ͥ���</font>
											</a>
										</logic:notEqual> 
									</span>
								</th>
							</tr>
						</thead>
					</logic:notEqual>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<logic:notEqual name="xxdm" value="10511">
					<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;"></div>
				</logic:notEqual>
				<logic:equal name="xxdm" value="10511">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;��λ��Ԫ��</font>&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">ÿ������ܽ��ܳ����������</font><font color="red" id="tip"></font><font color="red" >Ԫ</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%"><font class='red'>*</font>��������</th>
							<td width="34%" id='dkqxtd'>
								<select name="dkqx" id="dkqx">
								
								</select>
							</td>
							<th width="16%"><font class='red'>*</font>�������ޣ��£�</th>
							<td width="34%"><input name="jhr1" id="jhr1" class="text_nor"/></td>
						</tr>
						<tr  id = "tableshow" style="display:none">
							<td colspan="4"  width="100%">
								<table id="table">
									<tr>
										<th style="text-align:center" width="15%" >ѧ��</th>
										<th style="text-align:center" ><font class="red">*</font>ס�޷Ѵ����</th>
										<th style="text-align:center" ><font class="red">*</font>ѧ�Ѵ����</th>
										<th style="text-align:center" ><font class="red">*</font>����Ѵ����</th>
										<th style="text-align:center" ><font class="red">*</font>��ס�޷�Ӧ�ɶ�</th>
										<th style="text-align:center" ><font class="red">*</font>��ѧ��Ӧ�ɶ�</th>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th width="16%"><font class='red'>*</font>ס�޷Ѵ����ܶ�</th>
							<td width="34%"><input name="zsfdks" value="0" id="zsfdks" class="text_nor" readonly="readonly"/></td>
							<th width="16%"><font class='red'>*</font>ѧ�Ѵ����ܶ�</th>
							<td width="34%"><input name="xfdks" value="0" id="xfdks" class="text_nor" readonly="readonly"/></td>
						</tr>
						<tr>
							<th width="16%"><font class='red'>*</font>����Ѵ����ܶ�</th>
							<td width="34%"><input name="shfdks" value="0" id="shfdks" class="text_nor" readonly="readonly"/></td>
							<th width="16%"><font class='red'>*</font>�����ܽ��</th>
							<td width="34%"><input name="dkje" value="0" id="dkje"  class="text_nor" readonly="readonly"/></td>
						</tr>
						<tr>
							<th  width="16%"><font class='red'>*</font>��������</th>
							<td colspan="3" width="84%">
								<textarea rows="4" id="sqly" name="sqly" style="width:98%"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</logic:equal>
				</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th align="right" width="16%">
							������Ϣ
						</th>
						<logic:equal value="10511" name="xxdm">
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//��׺
											accept : 'pdf',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
						   </td>	
						</logic:equal>
						<logic:notEqual value="10511" name="xxdm">
						  <td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//��׺
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
						   </td>
						</logic:notEqual>
						
					</tr>
				</table>
				<div style="height: 20px">
				</div>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual name="xxdm" value="10511">
										<button type="button"  onclick="saveDksq('zxdkXyddk.do?method=save');">
										        ����ݸ�
									    </button>
									    <button type="button"  onclick="saveDksq('zxdkXyddk.do?method=saveAndSubmit');">
										      �ύ����
									    </button>
									</logic:notEqual>
									<logic:equal name="xxdm" value="10511">
										<button type="button" id="bccg" onclick="saveSqForHsd('save')">
										        ����ݸ�
									    </button>
									    <button type="button" id="tjsq" onclick="saveSqForHsd('submit')">
										      �ύ����
									    </button>									
									</logic:equal>
									<button type="button" onclick="iFClose();">
										�ر�
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