<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript">
		function showTsDiv(id){
			if($(id)){
				$(id).style.display = "";
			}
		}
		
		function hiddTsDiv(id){
			if($(id)){
				$(id).style.display = "none";
			}
		}
		</script>
		<script language="javascript">
		//document.onmousedown=click;
		jQuery(function(){
		//	onShow();
		})

		</script> 
	</head>
	<body>
		<!-- ���� -->
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="xxdm"/>
			<html:hidden name="rs" property="xmdm"/>
			<html:hidden name="rs" property="xmb"/>
			<html:hidden name="rs" property="mrxm"/>	
			<input type="hidden" name="savedXmdm" id="savedXmdm" value="${savedXmdm }"/>		
			<input type="hidden" name="savedRskz" id="savedRskz" value="${savedRskz }"/>	
			<input type="hidden" name="savedKzjb" id="savedKzjb" value="${savedKzjb }"/>			
				
			<input type="hidden" id="xyshCheck" value="${rs.xysh }"/>
			<input type="hidden" id="xxshCheck" value="${rs.xxsh }"/>
			<input type="hidden" id="bzrshCheck" value="${rs.bzrsh }"/>
			<input type="hidden" id="fdyshCheck" value="${rs.fdysh }"/>				
			<input type="hidden" id="bzrkzCheck" value="${rs.bzrkz }"/>
			<input type="hidden" id="fdykzCheck" value="${rs.fdykz }"/>
			<input type="hidden" id="xykzCheck" value="${rs.xykz }"/>
			<input type="hidden" id="xxkzCheck" value="${rs.xxkz }"/>
			<!-- ������ end-->
	
			<div class="tab">		
			<!-- ��Ŀ������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��Ŀ�������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="15%">
							<font color="red">*</font>��Ŀ���ƣ�
						</th>
						<td align="left" width="35%">
							<html:text name="rs" property="xmmc" maxlength="25" style="width : 100%" onkeyup="chgPkValue(this)"/>	
						</td>
						<th align="right" width="15%">
							��Ŀ���
						</th>
						<td align="left" width="35%">
							<html:select name="rs" property="xmlb" style="" styleId="xmlb" onchange="">
								<html:options collection="xmlbList" property="en" labelProperty="cn" />
							</html:select>	
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							��ʾ˳��
						</th>
						<td align="left">
							<html:text name="rs" property="xssx"
								onkeydown="return onlyNum(this,5)"
								onmousedown="return onlyNum(this,5)"
								maxlength="5" 
								style="width : 100%;ime-mode:disabled"/>	
						</td>
						<td align="left" colspan="2">
							<span onmousemove="showTsDiv('sxts')" onmouseout="hiddTsDiv('sxts')">
								<font color="blue">��ʾ��</font>
								<font color="blue" id="sxts" style="display : none">
								����Ŀ�б��е���ʾλ�ã���������ԽС������Խ��ǰ��
								</font>
							</span>		
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							��Ŀ˵����
							<br>
							<font color="red">(����¼��500��)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="xmsm"
								rows="5" style="width : 500px;" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- ��Ŀ������� end-->
			<!-- ��Ŀ���� -->
				<%@ include file="other/fjqk.jsp"%>
			<!-- ��Ŀ���� end-->
			<!-- ������� -->
				<%@ include file="other/xgsz.jsp"%>
			<!-- ������� end-->
			<!-- �������� -->
				<%@ include file="other/tjsz.jsp"%>
			<!-- �������� end -->
			<!-- ������� -->
				<%@ include file="other/jdsz.jsp"%>
			<!-- ������� end -->
			
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<!-- �ǲ鿴 -->
								<logic:notEqual name="doType" value="view">
									<button type="button" onclick="checkZs();return false;" id="buttonSave">
										�� ��
									</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:notEqual>
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<logic:present name="message">
				<script defer="defer">
					if($("message") && $("message").value != ""){
					
						var savedRskz = $("savedRskz").value;
						var savedXmdm = $("savedXmdm").value;
						var savedKzjb = $("savedKzjb").value;
						
						if(savedRskz == "��Ҫ"){
							confirmInfo("����Ŀ��Ҫ������������������þ�����������Ӱ����Ŀ�����룬������Ҫ����ȥ����������",goRssz);
						}else{
							alert("����ɹ�");
							if(opener){
								opener.document.getElementById("search_go").click();
							}else if(window.dialogArguments){
								window.dialogArguments.document.getElementById("search_go").click();
							}
							window.close();
						}
					}
					
					//��ʾ�����ɹ���Ϣ
					function goRssz(tag){
						if(tag == "ok"){
							window.dialogArguments.document.getElementById("savedXmdm").value = savedXmdm;
							window.dialogArguments.document.getElementById("savedKzjb").value = savedKzjb;
							window.dialogArguments.document.getElementById("forward").click();
						}else{
							window.dialogArguments.document.getElementById("search_go").click();
						}
						window.close();
					}
				</script>
			</logic:present>
		</html:form>
	</body>
</html>