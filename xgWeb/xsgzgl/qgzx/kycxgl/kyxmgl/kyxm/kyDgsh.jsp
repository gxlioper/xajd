<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#lxdj").val('${rs.zd2}');
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.xmid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""||jQuery("#pzjf").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "qgzx_kycxkyxmsh.do?method=kyDgsh&type=save";
		ajaxSubFormWithFun("KyxmglForm",url,function(data){
			 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
	}
	
	</script>
</head>
<body>
	<html:form action="/qgzx_kycxkyxmsh" method="post" styleId="KyxmglForm">
		<html:hidden name="rs" property="xmid" styleId="xmid"/>
		<html:hidden name="rs" property="splcid" styleId="splcid"/>
		<html:hidden property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${rs.xmbh}
							</td>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
							<th width="20%">
								��Ŀ������λ
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ������ѧ��
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								��Ŀ����������
							</th>
							<td width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ϵ��ʽ
							</th>
							<td width="30%">
								${rs.lxfs}
							</td>
							<th width="20%">
								����ʵ����
							</th>
							<td width="30%">
								${rs.ytsys}
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								��Ŀ��ʼʱ��
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								��Ŀ����ʱ��
							</th>
							<td width="30%">
								${rs.jssj}
							</td>																			
						</tr>
						<tr>
							<th>
								�о�����
							</th>
							<td width="30%" >
								${rs.yjzq}
							</td>
							<th width="20%">
								����ȼ�
							</th>
							<td width="30%" >
								${rs.lxdjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����Ԥ��
								<a  style="text-align: left;" onclick="showYsmx(this,'orign');" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a>
								<font>�ϼ����뾭���ܶ�:<font color='red'>${sbjfhj}</font>Ԫ</font>
								</span>
								
								
							</th>
						</tr>
					</thead>
					<tbody class="ysTbody">
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">֧�����</th>
										<th style="text-align: center;">Ԥ���Ԫ��</th>
										<th style="text-align: center;">��Ҫ��;</th>
									</tr>
									<logic:present name="jfysList">
										<logic:iterate id="z" name="jfysList">
											<tr>
												<td>${z.zclb }</td>
												<td>${z.ysje }</td>
												<td>${z.zyyt }</td>
											</tr>
										</logic:iterate>
										<logic:empty name="jfysList">
											<tr>
												<td colspan="5">δ�ҵ��κμ�¼��</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
				 </table>
				
			<table width="100%" border="0" class="formlist">
			<thead>
					<tr>
						<th colspan="4">
							<span>������Ϣ</span>
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
						<th >
							����ȼ�
						</th>
						<td>
							<html:select property="lxdj" styleId="lxdj" >
								<option value="1">�ص���Ŀ</option>
								<option value="0">һ����Ŀ</option>
							</html:select>
						</td>
						<th >
							<font color="red">*</font>��׼����
						</th>
						<td>
							<html:text property="pzjf" styleId="pzjf" maxlength="100" value="${rs.zd6}" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3')"></html:text>
						</td>
					</tr>
			</tr>
			<tr>
					<tr>
						<th >
							��˽��
						</th>
						<td id="shjgSpan" colspan="3">
							
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=kysh&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveSh();return false;">
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
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
