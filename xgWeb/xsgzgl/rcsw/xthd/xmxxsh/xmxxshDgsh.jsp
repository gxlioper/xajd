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
			var sqid = jQuery("#sqid").val();
			var splc = jQuery("#splc").val();
			var shid = jQuery("#shid").val();
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+sqid+"&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+splc+"&shid="+shid);
		});
	
		function saveShzt(){
			var shyj = jQuery("#shyj").val();
			if (jQuery.trim(shyj) == ""){
				showAlertDivLayer("����д��������");
				return false;
			}
			var url = "rcsw_txhd_xmxxshgl.do?method=xmxxshDgsh&type=save";
			ajaxSubFormWithFun("xmxxshForm",url,function(data){
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
	<html:form action="/rcsw_txhd_xmxxshgl" method="post" styleId="xmxxshForm">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="splc" styleId="splc"/>
		<html:hidden property="shid" styleId="shid"/>
		<html:hidden property="sqr" styleId="sqr"/>
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
			<table width="100%" border="0" class="formlist" >
				<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								��Ŀ����
							</th>
							<td width="34%">
								${data.xmmc}
							</td>
							<th width="16%">
								�ʱ��
							</th>
							<td width="34%" >
								${data.hdkssj}��${data.hdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${hdlbmc}
							</td>
							<th width="16%">
								��ص�
							</th>
							<td width="34%" >
								${data.hddd}
							</td>
						</tr>
						<tr>
							<th width="16%">
								������������
							</th>
							<td width="34%" >
								${data.sqrssx}
							</td>
							<th width="16%">
								�����������
							</th>
							<td width="34%" >
								${data.shrssx}
							</td>
							
						</tr>
						<tr>
						<th align="right" width="10%">
						�а쵥λ
						</th>
						<td align="left" colspan="3" >
							${data.cbdw}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">�����</th>
						<td align="left" colspan="3" >
							${data.hdzt}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�Ŀ�ļ�����
						</th>
						<td colspan="3">
							${data.hdmdyy}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�����
						</th>
						<td colspan="3">
							${data.hdfa}
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th width="16%">
							����ѧ��
						</th>
						<td width="34%" >
							${data.syxf}
						</td>
						<th width="16%">
						</th>
						<td width="34%" >
						</td>
					</tr>
					<% } %>
					<tr>
						<th width="16%">
							�˵��
						</th>
						<td width="34%" colspan="3">
							${data.hdsm}
						</td>
					</tr>
				</tbody>
			</table>
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xthd&id=shyj" />
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
		<table width="100%" border="0" class="formlist">	
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
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
