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
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript" src="js/gygl/zjcm/gybx.js"></script>
		<script language="javascript">
		function setBxReadOnly(){
			$("sfsf").disabled = true;
			$("wxry").disabled = true;
			$("clfy").disabled = true;
			$("wxfy").disabled = true;
			$("sfwg").disabled = true;
			$("wgsj").disabled = true;
			$("wgsjh").disabled = true;
			$("wgsjm").disabled = true;
			$("cljg").disabled = true;
		}
		
		function saveBxPj(){
		
			var fwtd = $("fwtd").value;
			var fwzl = $("fwzl").value;
			var sfjs = $("sfjs").value;
			var xcql = $("xcql").value;
			
			if(fwtd != "" && fwzl != "" && sfjs != "" && xcql != ""){
				saveUpdate('/xgxt/zjcmGygl.do?method=gybxPj&doType=save','');
			}else{
				alert("��Դ�'*'����Ŀ��֣�");
			}
		
		}
		</script>
	</head>

	<body onload="setGybxclInfo();setBxReadOnly();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl" >
		
			<!-- ������ -->
			<html:select name="rs" property="cl" style="display : none" styleId="cl" onchange="">
				<html:options collection="cllxList" property="dm" labelProperty="mc" />
			</html:select>
			<%@ include file="/gygl/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<!--ѧ����Ϣ -->
			<%@ include file="info/xsInfo.jsp"%>
			<!--ѧ����Ϣ end-->
			
			<!--������Ϣ -->
			<%@ include file="info/bxInfo.jsp"%>
			<!--������Ϣ end-->
	
			<!--�û����� -->
			<%@ include file="info/pjInfo.jsp"%>
			<!--�û����� end-->
			
			<br>
			<!-- ��ʾ��Ϣ -->
			<logic:notEqual name="rs" property="shzt" value="ͨ��">
				<div>
					<font color="red">ע���˱��޻�δ���ͨ�����޷��������ۡ�</font>
				</div>
			</logic:notEqual>
			<!-- ��ʾ��Ϣ end-->
			
			<!--������ť -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:equal name="rs" property="shzt" value="ͨ��">
									<button id="buttonSave" 
										onclick="saveBxPj()"
										style="width: 80px">
										ȷ	��
									</button>
								</logic:equal>
								&nbsp;&nbsp;
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