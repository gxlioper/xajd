<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stsh/js/stsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		xsbjfj();
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splc}&shid=${rs.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "stglStsh.do?method=stDgsh&type=save";
		ajaxSubFormWithFun("StshForm",url,function(data){
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
	function xsbjfj(){
		var fzrlb='${rs.fzrlb}';
		if(fzrlb=="ѧ��"){
			document.getElementById("bjmctr").style.display = "";
		}else{
			document.getElementById("bjmctr").style.display = "none";
		}
	}
	</script>
</head>
<body>
	<html:form action="/stglStsh" method="post" styleId="StshForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="splc" styleId="splc"/>
		<html:hidden name="rs" property="shid" styleId="shid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								������Ŀ����
							</th>
							<td colspan="3">
								${rs.stxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�������
							</th>
							<td width="30%">
								${rs.stlbmc}
							</td>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Чѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">
								�ҿ���λ
							</th>
							<td width="30%">
								${rs.gkdw}
							</td>
						</tr>
						<!--
						<tr>
							<th width="20%">
								���ſ�ʼʱ��
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								���Ž���ʱ��
							</th>
							<td width="30%">
								${rs.jssj}
							</td>
						</tr>  -->
					</tbody>
					<%--<thead>--%>
						<%--<tr>--%>
							<%--<th colspan="4">--%>
									   <%--<a style="text-align: left;" onclick="showPfzmx(this);" class="down"--%>
											<%--href="javascript:void(0);"> <font color="blue">���չ��/����</font>--%>
										<%--</a>--%>
							<%--</th>--%>
						<%--</tr>--%>
					<%--</thead>--%>
					<tbody id="tbody_toggle">
						<tr>
							<th width="20%">
								���������
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								���Ÿ�����
							</th>
							<td width="30%">
								${rs.stfzrxm}
							</td>
						</tr>
						<tr id='bjmctr'>
							<th width="20%">
								����������ѧԺ
							</th>
							<td width="30%">
								${rs.fzrxy}
							</td>
							<th width="20%">
								���������ڰ༶
							</th>
							<td width="30%">
								${rs.fzrbj}
							</td>
						</tr>
					<thead>
					<tr>
						<th colspan="4">
							<span>ָ����ʦ</span>

						</th>
					</tr>
					</thead>
					<tbody>
					<tr colspan="4">
						<td width="100%" colspan="4">
							<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="30%" style="text-align:left;">ָ����ʦ����</th>
										<th width="20%" style="text-align:left;">��������</th>
										<th width="20%" style="text-align:left;">��ϵ�绰</th>
										<th width="20%" style="text-align:left;">ְ��</th>
									</tr>
									<logic:iterate id="i" name="ZdlsInfoList">
										<tr name="deltr">
											<td><input name="zgh" type="hidden" value="${i.zgh}" style="width:90%"/><label name = "xm">${i.xm}</label></td>
											<td><input name="bmdm" type="hidden" value="${i.bmdm}" style="width:90%"/><label name = "bmmc">${i.bmmc}</label></td>
											<td><label name = "lxdh">${i.lxdh}</label></td>
											<td><input name="zc" type="hidden" value="${i.zc}" style="width:90%"/><label name = "zcmc">${i.zcmc}</label></td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</td>
					</tr>

						<tr>
							<th width="20%">
								������ϵ�绰
							</th>
							<td width="30%" >
								${rs.lxdh}
							</td>
							<th width="20%">
								������
							</th>
							<td width="30%">
								${rs.jtrxm}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								���ų���ʱ��
							</th>
							<td width="30%" >
								${rs.stclsj}
							</td>
							<th width="20%">
								����ʱ��
							</th>
							<td width="30%">
								${rs.sqsj}
							</td>	
						</tr>
					<logic:equal value="12872" name = "xxdm">
						<tr>
							<th width="20%">
								�Ǽ�
							</th>
							<td width="30%">
									${rs.stxj}
							</td>
							<th width="20%">
							</th>
							<td width="30%">
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th width="20%">
								���ż��
							</th>
							<td colspan="3">
								${rs.stsm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���Ż����
							</th>
							<td colspan="3">
								${rs.sthjqk}
							</td>
						</tr>
						<tr>
							<th>
								����ƻ���
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="fj" styleId="fjid" value="${rs.fj}"/>
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
							��˽��
						</th>
						<td id="shjgSpan">
							
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=s&id=shyj" />
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
