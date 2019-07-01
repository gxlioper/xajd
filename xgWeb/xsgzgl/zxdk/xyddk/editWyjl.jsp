<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/wyjl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		
		<script type="text/javascript">
			function saveWyjl(url){
				var xh= jQuery("#xh").val();
				var wyxq = jQuery("#wyxq").val();
				var wysj = jQuery("#wysj").val();

				
				// ���ѧ���Ƿ����
				if (xh==""){
					showAlertDivLayer("��ѡ��ѧ��!");
					return false;
				}

				// ���ΥԼʱ���Ƿ�Ϊ��
				if (wysj==""){
					showAlertDivLayer("ΥԼʱ�䲻����Ϊ��!");
					return false;
				}
				
				// ���ΥԼ�����Ƿ����
				if (wyxq==""){
					showAlertDivLayer("ΥԼ���鲻����Ϊ��!");
					return false;
				}
				
				ajaxSubFormWithFun("wyjlForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
				});	
			}
			
		</script>
	</head>
	<body>
		<html:form action="/zxdkWyjl" method="post" styleId="wyjlForm">
			<html:hidden property="splcid" value="${cssz.xydshlc }"/>
			<html:hidden property="dkzesx" styleId="dkzesx" value="${cssz.dkzesx }"/>
			<input type="hidden" id="dqxn" value="${dqxn }" />
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				</table>
			</div>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="dkxxList">
				<thead>
						<tr>
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_szxx">
						<tr>
							<th>
								<div align="center">
									�ܶ�
								</div>
							</th>
							<th>
								<div align="center">
									ÿ�������
								</div>
							</th>
							<th>
								<div align="center">
									ÿ��ѧ�ӷ�
								</div>
							</th>
							<th>
								<div align="center">
									ÿ��ס�޷�
								</div>
							</th>
							<th>
								<div align="center">
									ÿ�������
								</div>
							</th>
						</tr>
						<logic:notEmpty name="dkxxList">
							<logic:iterate id="dkxxList" name="dkxxList">
								<tr>
									<td align="center">
										${dkxxList.dkje }
									</td>
									<td align="center">
										${dkxxList.mnje }
									</td>
									<td align="center">
										${dkxxList.xfdks }
									</td>
									<td align="center">
										${dkxxList.zsfdks }
									</td>
									<td align="center">
										${dkxxList.shfdks }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="dkxxList">
							<tr>
								<td colspan="5" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
			</table>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table style="margin-bottom: 5px" width="100%" border="0"
					class="formlist" id="fkList">
				<thead>
						<tr>
							<th colspan="4">
								<span>�Ŵ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_szxx">
						<tr>
							<th>
								<div align="center">
									�ſ����
								</div>
							</th>
							<th>
								<div align="center">
									��ͬ���
								</div>
							</th>
							<th>
								<div align="center">
									�Ŵ�ʱ��
								</div>
							</th>
							<th>
								<div align="center">
									�Ŵ����
								</div>
							</th>
						</tr>
						<logic:notEmpty name="fkList">
							<logic:iterate id="fk" name="fkList">
								<tr>
									<td align="center">
										${fk.dkxn }
									</td>
									<td align="center">
										${fk.htbh }
									</td>
									<td align="center">
										${fk.fksj }
									</td>
									<td align="center">
										${fk.fkje }
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="fkList">
							<tr>
								<td colspan="4" align="center">
									�������ݣ�
								</td>
							</tr>
						</logic:empty>
			</table>
			<div class="tab"  id="content" style="margin-top: 5px; margin-bottom: -10px; overflow-x:hidden;" >
			</div>
			<table width="100%" border="0" class="formlist" style="margin-bottom: 35px;">
				<thead>
					<tr>
						<th colspan="4">
							<span>ΥԼ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tr>
					<th width="15%">�ֻ�����</th>
					<td width="35%">
						<html:text  property="sjhm" styleId="sjhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
					<th width="15%">QQ����</th>
					<td width="35%">
						<html:text  property="qqhm" styleId="qqhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">΢��</th>
					<td width="35%">
						<html:text  property="wxhm" styleId="wxhm" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
					<th width="15%">����</th>
					<td width="35%">
						<html:text  property="dzyx" styleId="dzyx" maxlength="25"  styleClass="text_nor"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">�Ƿ���ϵ��</th>
					<td>
						<html:select property="wyzt" styleId="wyzt" style="width:155px">
						<html:options collection="wyztList" property="wyzt"
							labelProperty="wyzt" />
						</html:select>
					</td>
					<th>
						<font color="red">* </font>ΥԼʱ��
						
					</th>
					<td>
						<html:text property="wysj" styleId="wysj" onfocus="return showCalendar(this.id,'yyyy-MM');" maxlength="20"></html:text>
					</td>
				</tr>
				<tr>
					<th width="15%">
						<span class="red">*</span>ΥԼ����
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<html:textarea  property="wyxq" styleId="wyxq" rows="5" cols="75" onblur="checkLen(this,200);"></html:textarea>
					</td>
				</tr>
				<tr>
					<th width="15%">
						��ע
						<br />
						<font color="red">(��500��)</font>
					</th>
					<td colspan="3">
						<html:textarea  property="bz" styleId="bz" rows="5" cols="75" onblur="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
			</table>
			<div>
				<table class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveWyjl('zxdkWyjl.do?method=update');">
										��    ��
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