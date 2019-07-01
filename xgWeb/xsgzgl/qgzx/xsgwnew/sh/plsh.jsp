<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="xsgzgl/qgzx/xsgwnew/sh/js/plsh.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgwnew/sq/js/xsgwsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#tbody_gwxx").load("xsgwsqnew_sq.do?method=gwxx&gwdm=${gwdms}&tt="+new Date().getTime(),function(){
				jQuery("#tbody_gwxx tr:first").hide();
			});
		});
		//ѡ�������λ
		function wdgwxzCx(){
			var xh = jQuery("#xh").val();
			var gwdm=jQuery("#gwdm").val();
			if(xh==null || xh==""){
				showAlert("��ѡ��һ��ѧ��");
			}else{
				showDialog("������λ",800,500,"xsgwsqnew_sq.do?method=wdgwxzCx&gwdm="+gwdm+"&xh="+xh+"&lx=tz",{
					close:function(){
					}
				});
			}
			
		}

		function savePlsh(shzt){
			if (jQuery("#shyj").val() == ""){
				showAlert("����д��������");
				return false;
			}
			if (jQuery("#shyj").val().length>200){
				showAlert("���������ܳ���200��");
				return false;
			}
			var ids=jQuery("#ids").val();
			var shgws=jQuery("#shgws").val();
			var shyj=jQuery("#shyj").val();
			var message=jQuery("#message").val();
			var gwdm=jQuery("#gwdm").val();
			var splc=jQuery("#splc").val();

			var api = frameElement.api,W = api.opener;
			W.savePlsh(shzt,gwdm,shyj,splc);
			closeDialog();
		}
			
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/xsgwshnew_sh" method="post" styleId="demoForm">
			<input type="hidden" name="ids" id="ids" value="${ids}"/>
			<input type="hidden" name="message" id="message" value="${message}"/>
			<input type="hidden" name="shgws" id="shgws" value="${shgws}"/>
			<input type="hidden" name="gwdms" id="gwdms" value="${gwdms}"/>
			<input type="hidden" name="splc" id="splc" value="${splc}"/>
			<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<logic:empty name="gwdms">
						<thead>
							<tr>
								<th colspan="4">
									<span>��λ��Ϣ</span> 
								</th>
							</tr>
						</thead>
						<tbody id="tbody_gwxxOne">
							<tr>
								<td colspan="4"><button class="btn_01" onclick="wdgwxzCxF();return false;" type="button">ѡ���λ</button></td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="gwdms">
						<tr>
							<th colspan="4" style="text-align:left">
								<span>��λ��Ϣ <button class="btn_01" onclick="wdgwxzCx();return false;" type="button">������λ</button></span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
					</tbody>
					</logic:notEmpty>
						<tr>
							<th width="16%">
								<font color="red">*</font>������
								<br />
								<font color="red">��200��</font>
							</th>
							<td  colspan="3">
							
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgwsh&id=shyj" />
								<textarea rows="10" style="width: 90%;margin-top: 5px" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
								<button type="button" id="btqd" onclick="savePlsh('1');">
									ͨ��
								</button>
								<button type="button" id="btqd" onclick="savePlsh('2');">
									��ͨ��
								</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

