<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfssglnew/cfsssh/js/cfsssh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${cfssshForm.ywid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${cfssshForm.splcid}&shid=${cfssshForm.shid}",function(){
				jQuery("#shjg").change(function(){
					if(jQuery(this).val()=='1')
						jQuery("#ssfw_tr1,#ssfw_tr2").show();
					else
						jQuery("#ssfw_tr1,#ssfw_tr2").hide();
				});
			});
			
			var cflbdm ='${map.cflbdm }';
			showCfqxFlag(cflbdm);
		});

		/**
		 * ������˲���
		 * @param shzt
		 * @param message
		 * @return
		 */
		function save_sh(){
			var shzt = jQuery("#shjg").val();
			jQuery("#shzt").val(shzt);
			
			if (jQuery("#shyj").val() == ""){
				showAlertDivLayer("����д��������");
				return false;
			}
			if (jQuery("#shyj").val().length>200){
				showAlertDivLayer("���������ܳ���200��");
				return false;
			}
			//�����һ�����ͨ��ʱ���ж��Ƿ���д�����ĺż�����ʱ��
			if((shzt=="1"||shzt==1)){
				if(jQuery("#isZhgw").val()=="true"){
					if(jQuery("#sswh").val()==""){
						showAlertDivLayer("����д�����ĺţ�");
						return false;
					}
					if(jQuery("#sssj").val()==""){
						showAlertDivLayer("����д����ʱ�䣡");
						return false;
					}
					if(jQuery("#cfggw").val()==""){
						showAlertDivLayer("��ѡ����Ĵ������");
						return false;
					}
				}
			}
			var message;
			if(jQuery("#shjg").val() == "1"){
				message = "ͨ��";
			}
			if(jQuery("#shjg").val() == "2"){
				message = "��ͨ��";
			}
			if(jQuery("#shjg").val() == "3"){
				message = "�˻�";
			}
			//�ύ���
			showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
				var url = "wjcf_cfsssh.do?method=sssh&type=save";
				ajaxSubFormWithFun("cfssshForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						//if (parent.window){
							refershParent();
						//}
					}});
				});
			}});
		}


		
		function ssjgChange(){
			var zzssjg=jQuery("#zzssjg").val();
			if(zzssjg=="���Ĵ���"){
				jQuery(".yes").css("display","");
				jQuery(".no").css("display","none");
			}else{
				jQuery(".no").css("display","");
				jQuery(".yes").css("display","none");
			}
		}

		//����
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		//���߸�������
		function ssfjxz(){
			var url="wjcf_cfsssh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//����������ʾ
		function showCfqxFlag(cflbdm){
			//�����ൺ�Ƶ����ְҵ����ѧԺ���θù���
			if(${xxdm=='13011'}) return false;
			
			if(cflbdm==""){return false;}
			jQuery.post("wjcf_cflbdmwh.do?method=getCfqx",{cflbdm:cflbdm},function(data){
				jQuery("#showCfqx").html(data["message"]);
			},'json');
		}
		</script>
	</head>
	
	<body>
		<html:form method="post" styleId="cfssshForm" action="/wjcf_cfsssh" >
		<html:hidden property="ywid" name="cfssshForm" styleId="ywid"/>
		<html:hidden property="shid" name="cfssshForm" styleId="shid"/>
		<html:hidden property="gwid" name="cfssshForm" styleId="gwid"/>
		<html:hidden property="splcid" name="cfssshForm" styleId="splcid"/>
		<html:hidden property="cfid" name="cfssshForm" styleId="cfid"/>
		<input name="isZhgw" type="hidden" id="isZhgw" value="${isZhgw }"/>
		<input type="hidden" name="shzt" id="shzt"/>
		<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
		<input type="hidden" name="ssfjmc" id="ssfjmc" value="${ssxx.fjmc }"/>
			<div
				style='width: 100%; height: 460px; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/wjcf/cfsbglnew/cfsb/selectStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="20%">
								ѧ��ѧ��
							</th>
							<td align="left" width="30%">
								${map.xn }:${map.xqmc }
							</td>
							<th align="right" width="20%">
								Υ��ʱ��
							</th>
							<td align="left" width="30%">
								${map.wjsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								����ԭ��
							</th>
							<td align="left">
								${map.cfyymc }
							</td>
							<th align="right">
								�������
							</th>
							<td align="left">
								${map.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;<span id="showCfqx" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<th align="right">
								��������
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�������������ϻ򸽼�
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${map.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								Υ����ʵ����
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.wjssjg }
							</td>
						</tr>
						<tr>
							<th align="right">
								�����ĺ�
							</th>
							<td align="left">
								${map.cfwh }
							</td>
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								${map.cfsj }
							</td>
						</tr>
						<logic:present name="map" property="cfdqsj">
							<tr>
								<th align="right">
									���ֵ���ʱ��
								</th>
								<td align="left"  colspan="3">
									${map.cfdqsj }
								</td>
							</tr>
						</logic:present>
						<tr>
							<th align="right">
								��ע
							</th>
							<td align="left" colspan="3" style="word-break:break-all;width:100%">
								${map.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								<bean:write name="ssxx" property="sqsj"/>
							</td>
							<th align="right">
								֤�����ϻ򸽼�
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-1" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${map.ssfilepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-1'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th align="right">
								��������
							</th>
							<td  align="left" colspan="3" style="word-break:break-all;width:100%">
								<%--<bean:write name="ssxx" property="sqly"/>--%>
								${ssxx.sqly }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal value="true" name="isZhgw">
							<tr id="ssfw_tr1">
								<th align="right">
									<font color="red">*</font>�����ĺ�
								</th>
								<td align="left"  >
									<html:text property='sswh' styleId="sswh" maxlength="30"/>
								</td>
								<th align="right">
									<font color="red">*</font>����ʱ��
								</th>
								<td align="left">
								<html:text property="sssj" styleId="sssj"
									style="cursor:hand;"
									onclick="return showCalendar('sssj','y-mm-dd');" />
								</td>
							</tr>
							<tr id="ssfw_tr2">
								<th align="right">
									���߽��
								</th>
								<td align="left"  >
									<html:select property="zzssjg" styleId="zzssjg" onchange="ssjgChange()" style="width: 150px">
										<html:option value="ά��ԭ����">ά��ԭ����</html:option>
										<html:option value="��������">��������</html:option>
										<html:option value="���Ĵ���">���Ĵ���</html:option>
									</html:select>
								</td>
								<th align="right" class="no">
								</th>
								<td align="left" class="no">
								</td>
								<th align="right" class="yes" style="display: none">
									���ָ���Ϊ
								</th>
								<td align="left" class="yes"style="display: none" >
									<html:select property="cfggw" styleId="cfggw" style="width:150px">
										<html:options collection="cflbList" property="mc"
										labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<tr>
								<th>
									<font color="red">*</font>��˽��
								</th>
								<td colspan="3" id="shjgSpan">
									
								</td>
							</tr>
						</tr>
						<tr>
       						<th >
				        		<font color="red">*</font>������
				       		</th>
				     	    <td width="34%" colspan="3">
				     	    	<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cfss&id=shyj" />
				        		<textarea rows="5" style="width: 90%;margin-top:5px" id="shyj" name="shyj"></textarea>
				       		</td>
				        </tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<div class="btn">
										<button type="button" onclick="save_sh();">
											����
										</button>
										&nbsp;&nbsp;
										<button type="button" name="�� ��" onclick="iFClose();">
											�� ��
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</html:form>
	</body>
</html>