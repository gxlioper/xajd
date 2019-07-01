<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript" src="js/gygl/zjcm/gybx.js"></script>
		<script language="javascript">
		//����ύʱ��
		function checkTjsj(){
			var wgsjm = $("wgsjm").value;//��
			var wgsjh = $("wgsjh").value;//ʱ
			
			if(wgsjh != ""){
			
				if(wgsjh > 24){
					alert("Сʱ¼�벻�ܴ���24����ȷ�ϣ�");
					$("wgsjh").value = "";
				 	$("wgsjh").focus();
					return false;
				}
				
				if(wgsjh.length > 0 && wgsjh.substring(0,1) == 0){
					$("wgsjh").value = wgsjh.substring(1,wgsjh.length);
				}
			}
			
			if(wgsjm != ""){
			
				if(wgsjm > 59){
					alert("����¼�벻�ܴ���59����ȷ�ϣ�");
					$("wgsjm").value = "";
				 	$("wgsjm").focus();
					return false;
				}
				
				if(wgsjm.length > 0 && wgsjm.substring(0,1) == 0){
					$("wgsjm").value = wgsjm.substring(1,wgsjm.length);
				}
			}
		}
	</script>
	</head>

	<body onload="setGybxclInfo()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl" >
			<!-- ������ -->
			<html:hidden name="rs" property="shzt" styleId="shzt"/>
			<html:select name="rs" property="cl" style="display : none" styleId="cl" onchange="">
				<html:options collection="cllxList" property="dm" labelProperty="mc" />
			</html:select>
			<%@ include file="/gygl/hiddenValue.jsp"%>
			<!-- ������ end-->

			<!-- ѧ����Ϣ -->
			<%@ include file="info/xsInfo.jsp"%>
			<!-- ѧ����Ϣ end-->
			
			<!-- ������Ϣ -->
			<%@ include file="info/bxInfo.jsp"%>
			<!-- ������Ϣ end-->
			
			<!--������ť -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
						<div class="bz">					
							<font color="blue">
								<!-- ��ʾ��Ϣ -->
								<logic:equal name="doType" value="update">
									<logic:notEqual name="rs" property="shzt" value="δ���">
										<div>
											<font color="red">ע���Ѿ���˹��ôα��ޣ��������޸ġ�</font>
										</div>
									</logic:notEqual>
								</logic:equal>
								<!-- ��ʾ��Ϣ end -->
							</font>
						</div>
							<div class="btn">
								<logic:equal name="doType" value="sh">
									<button class="" id="buttonSave" onclick="saveBxSh('tg')" style="width: 80px">
										ͨ	��
									</button>
									&nbsp;&nbsp;
									<button class="" id="buttonSave" onclick="saveBxSh('btg')" style="width: 80px">
										��ͨ��
									</button>
									&nbsp;&nbsp;
								</logic:equal>
								<logic:equal name="doType" value="update">
									<logic:equal name="rs" property="shzt" value="δ���">
										<button class="" id="buttonSave" onclick="saveBxEdit()" style="width: 80px">
											��	��
										</button>
										&nbsp;&nbsp;
									</logic:equal>
								</logic:equal>
								<button class="" id="buttonSave" onclick="Close();return false;" style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!--������ť end-->

		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
	