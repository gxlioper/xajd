<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/zbsh/js/zbsh.js"></script>
		<script type="text/javascript" defer="defer">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${ZbshForm.sbsqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${ZbshForm.splcid}&shid=${ZbshForm.shid}");
		})
			function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "xlzxnew_zbsh.do?method=saveZbsh";
		ajaxSubFormWithFun("ZbshForm",url,function(data){
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
		<html:form action="/xlzxnew_zbsh" method="post" styleId="ZbshForm">
			<html:hidden property="sbsqid"/>
			<html:hidden property="splcid"/>
			    <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${zbrc.xn}<input type="hidden" name="xn" />
								</td>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${xqmc}<input type="hidden" name="xq" />
								</td>	
							</tr>
							<tr>
								<th>
									�ܴ�
								</th>
								<td>
									${zbrc.zbzc}
								</td>
								<th>
									��ֹ����
								</th>
								<td>
									${zbrc.zbksrq} ��  ${zbrc.zbjsrq}
								</td>
							</tr>
							<tr>
								<th>�༶����</th>
								<td>${bjxx.bjmc}
									<input type="hidden" name="bjdm" id="bjdm" value="${bjxx.bjdm}"/>
								</td>
								<th>������</th>
								<td>${bjxx.xm}</td>
							</tr>
						<tr>
							<th>
								�����ڰ༶������<br />
								�ش��¼�<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${ZbshForm.ztqk}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="5">
								<table width="100%" >
									<thead>
										<tr>
											<th width='20%' style="text-align:center">ѧ��</th>
											<th width='20%' style="text-align:center">����</th>
											<th width='20%' style="text-align:center">�Ա�</th>
											<th width='40%' style="text-align:center">��Ҫ��������</th>
										</tr>
									</thead>
									<tbody id="tablebody">
										<logic:iterate id="i" name="wtryInfo">
											<tr>
												<td style="text-align:center">${i.xh}</td>
												<td style="text-align:center">${i.xm}</td>
												<td style="text-align:center">${i.xb}</td>
												<td style="text-align:center">${i.zbwtms}</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</th>
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
					<th >
						��˽��
					</th>
					<td id="shjgSpan">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zbshx&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
				</table>
			</div>
			<div style="height:30px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"  onclick="saveSh();return false;">
										�� ��
								   </button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

