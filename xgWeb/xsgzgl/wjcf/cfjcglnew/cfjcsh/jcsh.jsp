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
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsh/js/cfjcsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		var text;
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${cfjcshForm.ywid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${cfjcshForm.splcid}&shid=${cfjcshForm.shid}",function(){
				jQuery("#shjg").change(function(){
					if(jQuery(this).val()=='1')
						jQuery("#jcfw_tr1").show();
					else
						jQuery("#jcfw_tr1").hide();
				});
			});
			text=jQuery("#text").val();
			
			var cflbdm ='${map.cflbdm }';
			showCfqxFlag(cflbdm);
		});

		//����
		function fjxz(){
			var url="wjcf_cfsh.do?method=fjxz";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function save_sh(shzt,message){
			var shzt = jQuery("#shjg").val();
			var jcwh = jQuery("#jcwh").val();
			var cfid = jQuery("#cfid").val();
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
					if("12684"!=jQuery("#xxdm").val()){
						if(jQuery("#jcwh").val()==""){
							showAlertDivLayer("����д"+text+"�ĺţ�");
							return false;
						}
					}
					if(jQuery("#jcsj").val()==""){
						showAlertDivLayer("����д"+text+"ʱ�䣡");
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
			if("true"==jQuery("#isZhgw").val()&&"12686"==jQuery("#xxdm").val()){
				var flag=true;
				jQuery.ajaxSetup({async:false});
				jQuery.post("wjcf_cfjcsh.do?method=checkExistJcwh", {
					cfid:cfid,
					jcwh:jcwh
				}, function(data) {
					if(data ==null || data){
						flag=false;
					}
				},"json");
				jQuery.ajaxSetup({async:true});
				if(!flag){
					showAlert("�ý���ĺ��Ѵ��ڣ����޸ģ�");
					return false;
					}
				}
		
			//�ύ���
			showConfirmDivLayer("��ȷ����"+message+"����������",{"okFun":function(){
				var url = "wjcf_cfjcsh.do?method=jcsh&type=save";
				ajaxSubFormWithFun("cfjcshForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						//if (parent.window){
							refershParent();
						//}
					}});
				});
			}});
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
		<html:form method="post" styleId="cfjcshForm" action="/wjcf_cfjcsh" >
		<html:hidden property="ywid" name="cfjcshForm" styleId="ywid"/>
		<html:hidden property="shid" name="cfjcshForm" styleId="shid"/>
		<html:hidden property="gwid" name="cfjcshForm" styleId="gwid"/>
		<html:hidden property="splcid" name="cfjcshForm" styleId="splcid"/>
		<html:hidden property="cfid" name="cfjcshForm" styleId="cfid"/>
		<input name="isZhgw" type="hidden" id="isZhgw" value="${isZhgw }"/>
		<input type="hidden" name="fjmc" id="fjmc" value="${map.fjmc }"/>
		<input type="hidden" name="shzt" id="shzt"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
		<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
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
								${map.xn }${map.xqmc }
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
								���ֽ���
							</th>
							<td align="left" colspan="3">
								${map.cfyj }
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								ѧ��������
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
							<th align="right" width="20%">
								����Υ�ͼ�¼��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-2" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath2}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-2'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�д�ֽ��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-3" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath3}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-3'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�������¼
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-4" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${map.filepath4}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-4'
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
								<span><bean:message key="wjcf.text" />��Ϣ</span>
							</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								<bean:write name="jcxx" property="sqsj"/>
							</td>
							<th align="right">
							</th>
							<td align="left">
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="12865">
							<tr>
								<th align="right">
									<bean:message key="wjcf.text" />����
								</th>
								<td  align="left" colspan="3" style="word-break:break-all;width:100%">
									<%--<bean:write name="jcxx" property="sqly"/>--%>
									${jcxx.sqly}
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal name="xxdm" value="12865">
							<tr>
								<th align="right">
									������Ϣ
								</th>
								<td align="left" colspan="3" style="word-break:break-all;width:100%">
									<bean:write name="jcxx" property="jdxx"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									���ּ�������
								</th>
								<td  align="left" colspan="3" style="word-break:break-all;width:100%">
									<%--<bean:write name="jcxx" property="sqly"/>--%>
									${jcxx.sqly}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th align="right" width="20%">
								���ٽ�����¼��
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-5" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${jcxx.filepath}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-5'
                                        });
                                    });
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								�ɼ���
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-6" style="padding: 5px;"></div>
								<script type="text/javascript">
                                    //���ø���
                                    jQuery(function(){
                                        var gid = "${jcxx.filepath2}";
                                        jQuery.MultiUploader_q({
                                            gid : gid,
                                            targetEl : 'commonfileupload-list-6'
                                        });
                                    });
								</script>
							</td>
						</tr>
					</tbody>
					<logic:equal value="11318" name="xxdm">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������Ϣ
									<a onclick="showLsjl(this);" class="down" 
									   href="javascript:void(0);">
									   <font color="blue">���չ��/����</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_hjjl" style="display: table-row-group;">
						<tr>
							<td colspan="4">
							<table class="dateline" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>��������</b></td>
										<td><b>��Ŀ����</b></td>
										<td><b>��Ŀ���</b></td>
										<td ><b>����ʱ��</b></td>
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="hjqkList">
							<logic:iterate name="hjqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.pjzq}
											</td>
											
											<td >
												${s.xmmc}
											</td>
											<td >
												${s.xmje}
											</td>
											<td >
												${s.sqsj}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
							</table>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="wjcf.text" />������</span>
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
							<tr id="jcfw_tr1">
								<logic:equal value="12684" name="xxdm">
									<th align="right">
										<bean:message key="wjcf.text" />�ĺ�
									</th>
								</logic:equal>
								<logic:notEqual value="12684" name="xxdm">
									<th align="right">
										<font color="red">*</font><bean:message key="wjcf.text" />�ĺ�
									</th>
								</logic:notEqual>
								<td align="left"  >
									<html:text property='jcwh' styleId="jcwh" maxlength="30"/>
								</td>
								<th align="right">
									<font color="red">*</font><bean:message key="wjcf.text" />ʱ��
								</th>
								<td align="left">
								<html:text property="jcsj" styleId="jcsj"
									style="cursor:hand;"
									onclick="return showCalendar('jcsj','y-mm-dd');" />
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
				     	    	<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cfjc&id=shyj" />
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