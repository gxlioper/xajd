<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${rs.shid}",function(){
				jQuery("#shjg").change(function(){
					if(jQuery(this).val()=='1')
						jQuery("#tr_xbjf").show();
					else
						jQuery("#tr_xbjf").hide();
				});
			});
			
		});
	
		function saveShzt(){
			var shjg = jQuery("#shjg").val();
			var xbjf = jQuery("#xbjf").val();
			if ("1" == shjg && jQuery.trim(xbjf) == ""){
				showAlert("�²����Ѳ���Ϊ�գ�");
				return false;
			}
			var shyj = jQuery("#shyj").val();
			if (jQuery.trim(shyj) == ""){
				showAlert("����д��������");
				return false;
			}
			if (shyj.length > 200){
				showAlert("���������ܳ���200�֣�");
				return false;
			}
			var url = "kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmDgsh&type=save";
			ajaxSubFormWithFun("kycxxmshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}
		
		</script>
	</head>
<body>
	<html:form action="/kycxgl_kycxxm_kycxxmshgl" method="post" styleId="kycxxmshForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="splc" styleId="splc"/>
		<html:hidden name="rs" property="shid" styleId="shid"/>
		<html:hidden name="rs" property="czr" styleId="czr"/>
		
		<div style="overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>������Ŀ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="18%">ѧ��</th>
						<td width="32%">
							${rs.xn}
						</td>
						<th width="18%">������Ŀ����</th>
						<td width="32%">
							${rs.xmmc }
						</td>
				    </tr>
				    <tr>
						<th width="18%">ָ����ʦ</th>
						<td width="32%">
							${rs.zdlsxm }
						</td>
						<th width="18%">�������</th>
						<td width="32%">
							${rs.lbmc }
						</td>
				    </tr>
				    <tr>
						<th width="18%">��Ŀ������</th>
						<td width="32%">
							${rs.xmsqrxm }
						</td>
						<th width="18%">����ʱ��</th>
						<td width="32%">
							${rs.xmsqsj }
						</td>
				    </tr>
				    <tr>
						<th>
							������Ϣ
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<input type="hidden" id="fjxx" value="${rs.fjxx }" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjxx').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>������Ŀ��Ա</span>
						</th>
					</tr>
				</thead>
			</table>
			
			<logic:empty name="kycxxmcyList">
					<div>
						<table width="100%" border="0" class="datelist" style="margin:2px auto;">
							<tbody>
								<tr>
									<td style="text-align: center;">��ʱû������</td>
								</tr>
							</tbody>
						</table>
					</div>				
				</logic:empty>
				<logic:notEmpty name="kycxxmcyList">
					<div>
						<table width="100%" border="0" class="datelist" style="margin:2px auto;">
							<thead>
								<tr>
									<td width='11%'>ѧ��</td>
									<td width='11%'>����</td>
									<td width='6%'>�Ա�</td>
									<td width='6%'>�꼶</td>
									<td width='15%'><bean:message key="lable.xb" /></td>
									<td width='16%'>רҵ</td>
									<td width='20%'>�༶</td>
									<td width='15%'>��ϵ��ʽ</td>
								</tr>
							</thead>
							<tbody id="tbody_kycxxm_xs">
								<logic:iterate id="kycxxmcy" name="kycxxmcyList">
									<tr>
										<td>${kycxxmcy.xh }</td>
										<td>${kycxxmcy.xm }</td>
										<td>${kycxxmcy.xb }</td>
										<td>${kycxxmcy.nj }</td>
										<td>${kycxxmcy.xymc }</td>
										<td>${kycxxmcy.zymc }</td>
										<td>${kycxxmcy.bjmc }</td>
										<td>${kycxxmcy.lxfs }</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>				
				</logic:notEmpty>	
				
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�����ʷ</span>
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
					<tr id="tr_xbjf">
						<th>
							<font color="red">*</font>�²����ѣ�Ԫ��
						</th>
						<td colspan="3" >
							<html:text  
							onblur="checkMoneyForBlur(this)"
							onkeyup="checkMoneyForKeyup(this)"
							name="rs" property='xbjf' styleId="xbjf" maxlength="20"  />
							<input style="display: none;" type="text" /> 
						</td>
					</tr>
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
							<font color="red">(��200��)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=kycxxm&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</td>
		</tr>
		</table>
		</div>
		
		<div style="height: 40px"></div>
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
		
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
