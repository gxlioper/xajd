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
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${GypyShForm.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${GypyShForm.splc}&shid=${GypyShForm.shid}");
		});
		function saveSh(){
			var shzt = jQuery("#shjg").val();
			if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
				showAlert("�뽫��������д������");
				return false;
			}
			if(jQuery("#gxsj").length == 1 ){
				if(jQuery("#gxsj").val() == ""){
					showAlert("����ʱ�䲻��Ϊ�գ�");
					return false;
				}
			}
			var url = "gypynew_gypysh.do?method=saveGypySh";
			ajaxSubFormWithFun("GypyShForm",url,function(data){
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
		<html:form action="/gypynew_gypysh" method="post" styleId="GypyShForm">
			<html:hidden  property="sqid" styleId="sqid"/>
			<html:hidden  property="splc" styleId="splc"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
							<th width="15%">¥��</th>
							<td width="35%">
								${qsxx.ldmc}
								<!-- 
								<html:select property="lddm" styleId="lddm" style="width:150px;">
								
								</html:select>	 -->							
							</td>
							<th width="15%">���</th>
							<td width="35%">
								${qsxx.ch}��
								<!-- <html:select property="ch" styleId="ch" style="width:150px;">
									
								</html:select> -->
							</td>
						</tr>
						<tr>
							<th>���Һ�</th>
							<td>
								${qsxx.qsh}
							<!-- 
								<html:select   property="qsh" styleId="qsh"  style="width:150px;">
								</html:select>-->
							</td>
							<th>�����Ǽ�</th>
							<td>
								${GypyShForm.sqxj}
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td>
								${GypyShForm.sqsj}
							</td>
							<th>�Ƿ�Ϊ�ٴι���</th>
							<td>
								${GypyShForm.sfzcgx}
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								${GypyShForm.sqly}
							</td>
						</tr>
					</tbody>
					<logic:notEmpty name="wjxx">
						<thead>
						<tr>
							<th colspan="4">
								<span>������ԱΥ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="text-align:center">ѧ��</th>
							<th style="text-align:center">����</th>
							<th style="text-align:center">Υ�����</th>
							<th style="text-align:center">Υ��ʱ��</th>
						</tr>
						<logic:iterate name="wjxx" id="i">
							<tr>
								<td style="text-align:center">${i.xh}</td>
								<td style="text-align:center">${i.xm}</td>
								<td style="text-align:center">${i.gyjllbmc}</td>
								<td style="text-align:center">${i.wjsj}</td>
							</tr>
						</logic:iterate>
					</tbody>
					</logic:notEmpty>
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
						<logic:equal value="true" name="isLast">
							<tr>
							<th>
								<span class="red">*</span>����ʱ��
							</th>
							<td colspan="3" >
								<html:text property="gxsj" styleId="gxsj" maxlength="10" onclick="return showCalendar('gxsj','y-mm-dd');" style="width:150px" value="${gxsj}" />
							</td>
							</tr>
						</logic:equal>
						<tr>
							<th width="20%">
								<font color="red">*&nbsp;</font> ������
								<br />
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gypysh&id=shyj" />
								<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
							</td>
						</tr>
					</tbody>	
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"  onclick="saveSh();return false;">
										����
									</button>
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