<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
			});
			function saveShzt(){
				var shzt = jQuery("#shjg").val();
				var shyj = jQuery("#shyj").val();
				
				if(jQuery("#shjg").val() == "0"){
					showAlertDivLayer("��ѡ�����״̬��");
					return false;
				}
			
				if (jQuery.trim(shyj) == ""){
					showAlertDivLayer("����д��������");
					return false;
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
				showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
					var url = "ttgl_stglsh.do?method=stglDgsh&type=save";
					ajaxSubFormWithFun("stglshForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					});
				}});
		
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="stglshForm" action="/ttgl_stglsh"
			enctype="multipart/form-data">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>ѧ����֯��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="20%">ѧ����֯ȫ��</th>
						<td width="30%">
								${rs.stqc }
						</td>
						<th width="20%">ѧ����֯���</th>
						<td width="30%">
								${rs.stjc }
						</td>
					</tr>
					<tr>
						<th width="20%">����ʱ��</th>
						<td width="30%">
								${rs.sqsj}
						</td>
						<th width="20%">��֯����</th>
						<td width="30%">
								${rs.strs}
						</td>
					</tr>
					<tr>
						<th>ѧ����֯����</th>
						<td>
								${rs.styx}
						</td>
						<th>ѧ����֯���ں�</th>
						<td>
								${rs.gzh}
						</td>
					</tr>
					<tr>
						<th>ָ����ʦ</th>
						<td>
								${rs.zdlsxm }
						</td>
						<th>ָ����λ</th>
						<td>
								${rs.bmmc }
						</td>
					</tr>
					<tr>
						<th>�칫�ҵ�ַ</th>
						<td colspan="3">
								${rs.bgsdz}
						</td>
					</tr>
					<tr>
						<th>ѧ����֯������Դ</th>
						<td colspan="3">
							<logic:iterate id="item" collection="${xszzjflyList}">
								<input type="checkbox" name="jflyArray" value="${item.dm}" disabled>${item.mc}
							</logic:iterate>
						</td>
						<script type="text/javascript">
                            jQuery(function(){
                                var r = '${rs.jfly}';
                                var result = r.split(",");
                                for(var i=0;i<result.length;i++){
                                    jQuery("input[value='"+result[i]+"'").attr("checked","checked");
                                }
                            })
						</script>
					</tr>
					<tr>
						<th>��֯����</th>
						<td colspan="3" id="stlx">
								${rs.stlx }
						</td>
					</tr>
					<tr id="ndzzzt" style="display:none">
						<th>�����֯״̬</th>
						<td colspan="3">
								${rs.xn}
								${rs.ndzzztmc}
						</td>
					</tr>
					<tr>
						<th>��֯���</th>
						<td colspan="3">
								${rs.zzlbmc}
						</td>
					</tr>
					<tr>
						<th>ѧ����֯��ּ</th>
						<td colspan="3">
								${rs.stjs }
						</td>
					</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span>ѧ����֯������</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr class="h">
							<th colspan="7">
								<table width="100%" >
									<thead>
										<tr>
											<th width='10%' style="text-align:center">ѧ��</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='10%' style="text-align:center">��Ժ</th>
											<th width='10%' style="text-align:center">ѧԺ</th>
											<th width='10%' style="text-align:center">רҵ</th>
											<th width='10%' style="text-align:center">�༶</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='10%' style="text-align:center">�绰</th>
										</tr>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="fzrxxInfo" indexId="index">
											<tr name='deltr'>
												<td style='text-align:center'><input name='xh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
												<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
												<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
												<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
												<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
												<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
												<td style='text-align:center'><label name = 'fz'>${i.fzrfz}</label></td>
												<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
											</tr>
									</logic:iterate>
									</tbody>
								</table>
							</th>
						</tr>
					</tbody>
					<thead>
					<tr class="h">
						<th colspan="4">
							<span>��֧��</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr class="h">
						<th colspan="7">
							<table width="100%" >
								<thead>
								<tr>
									<th width='10%' style="text-align:center">ѧ��</th>
									<th width='10%' style="text-align:center">����</th>
									<th width='10%' style="text-align:center">��Ժ</th>
									<th width='10%' style="text-align:center">ѧԺ</th>
									<th width='10%' style="text-align:center">רҵ</th>
									<th width='10%' style="text-align:center">�༶</th>
									<th width='10%' style="text-align:center">����</th>
									<th width='10%' style="text-align:center">�绰</th>
								</tr>
								</thead>
								<tbody id="tablebody2">
								<logic:iterate id="i" name="tzsxxInfo" indexId="index">
									<tr name='deltr'>
										<td style='text-align:center'><input name='tzsxh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
										<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
										<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
										<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
										<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
										<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
										<td style='text-align:center'><label name = 'fz'>��֧��</label></td>
										<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
						</th>
					</tr>
					<tr>
						<th align="right" width="10%">
							������Ϣ
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<input type="hidden" id="fjid" value="${filepath}"/>
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
					</tbody>
				</table>
					<table width="100%" border="0" class="formlist">
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
						</tbody>
						
						<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
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
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">��200��</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsxxxg&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top:5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
					</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveShzt();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
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
    <script type="text/javascript">
        jQuery(function(){
            var ndzzzt = jQuery("#ndzzzt");
            if(jQuery("#stlx").html().trim() == "ѧ������"){
                ndzzzt.removeAttr('style')
            } else {
                ndzzzt.attr('style','display:none');
            }
        })
    </script>
</html>
