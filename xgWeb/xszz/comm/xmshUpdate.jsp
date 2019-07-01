<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xszz/jtcy.js"></script>
	<script language="javascript" src="js/xszz/xszzInit.js"></script>
	<script language="javascript" src="js/xszz/xszzComm.js"></script>
	<script language="javascript" src="js/xszz/xszzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
	<script language="javascript" src="js/xszz/xszzOptionList.js"></script>	
	<script language="javascript">
	
	//��Ŀ���⴦��
	function xmTsCl(){
	
		var xmb = $("xmb").value;
	
		if(xmb == "gjzxdkb"){
	
			if($("xnje")){
				$("xnje").readOnly = true;
			}
		}
	}
	
	//����ѧ����
	function setXnje(){
	
		var xmb = $("xmb").value;
		var xmzzje = $("xmzzje").value;
		
		if(xmb == "gjzxdkb"){
		
			var xz = "1";
			
			if($("xz")){
				xz = $("xz").value;
			}
			alert(xz);
		}
	}
	
	function showXscjXx(){
		showTopWin('/xgxt/xszzXszj.do?method=xscjManage&xh='+$("xh").value,400,300);
	}
	</script>
	</head>

	<body onload="onShow_xmsh();xszzInit();">
		<!-- ���� -->
		<!-- ���� end-->
		<html:form action="/commXszz" method="post" enctype="multipart/form-data">
			<html:hidden property="iskns" styleId="iskns"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xh" id="xh" value="${xmInfo.xh }"/>
			<input type="hidden" name="xmb" id="xmb" value="${xmInfo.xmb }"/>
			<input type="hidden" name="mrxm" id="mrxm" value="${xmInfo.mrxm }"/>
			<!-- ��ϵ -->
			<html:select property="gx" styleClass="select" style="display : none" styleId="gx">
				<html:options collection="gxList" property="dm" labelProperty="mc" />
			</html:select>
			<!-- ������ end-->
			<!-- ��Ŀ������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
						<logic:equal name="xmInfo" property="sqzq" value="ѧ��">
							${xmInfo.xn }ѧ��
							<input type="hidden" name="save_xn" value="${xmInfo.xn }"/>
						</logic:equal>
						<logic:equal name="xmInfo" property="sqzq" value="���">
							${xmInfo.nd }���
							<input type="hidden" name="save_nd" value="${xmInfo.nd }"/>
						</logic:equal>
						<logic:equal name="xmInfo" property="sqzq" value="ѧ��">
							${xmInfo.xn }ѧ��
							${xmInfo.xqmc }ѧ��
							<input type="hidden" name="save_xq" value="${xmInfo.xq }"/>
							<input type="hidden" name="save_xn" value="${xmInfo.xn }"/>
						</logic:equal>
						${xmInfo.xmmc } ���� 
						</font>
					</td>
				</tr>
			</table>
			<logic:notEmpty name="xmnrList">
				<logic:iterate name="xmnrList" id="xmnr">
					<!-- ѧ��������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="view_xsjbxx">
						<%@ include file="sqsh/xsInfo.jsp"%>
					</logic:equal>
					<!-- ѧ��������Ϣ end-->
					
					<!-- �������϶���Ϊ����ģ�飬�����������϶������˼�ͥ���������Ŀ -->
					<logic:equal name="xmInfo" property="xmb" value="xszz_knsb">
						<logic:equal value="yes" property="iskns" name="xszzTyForm">
							<logic:equal value="true" name="knsdl">
								<logic:equal value="true" name="jtqkdc">
									<logic:equal name="xmnr" property="mk" value="xmxgInfo">
										<%@ include file="sqsh/jtcyInfo.jsp"%>
									</logic:equal>
								</logic:equal>
							</logic:equal>
						</logic:equal>
					</logic:equal>
					
					<!-- ��ͥ��� -->
					<logic:equal name="xmnr" property="mk" value="jtqkdcb">
						<%@ include file="sqsh/jtqkInfo.jsp"%>
					</logic:equal>
					<!-- ��ͥ��� end-->
					<!-- �۲�����ʲ��� -->
					<logic:equal name="xmnr" property="mk" value="zcf">
						<%@ include file="sqsh/zcf.jsp"%>
					</logic:equal>
					<!-- �۲�����ʲ��� end-->
					<!-- ������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="xsqtxxb">
						<%@ include file="sqsh/qtInfo.jsp"%>
					</logic:equal>	
					<!-- ������Ϣ end-->	
					<!-- ��������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="xszz_knsb">
						<%@ include file="sqsh/knInfo.jsp"%>
					</logic:equal>	
					<!-- ��������Ϣ end-->
					<!-- ��Ŀ��� -->
					<logic:equal name="xmnr" property="mk" value="xmxgInfo">
						<%@ include file="sqsh/wsInfo.jsp"%>
					</logic:equal>
					<!-- ��Ŀ��� end-->
					<logic:equal name="xmnr" property="mk" value="shInfo">
						<%@ include file="sqsh/shInfo.jsp"%>
					</logic:equal>
				</logic:iterate>				
			</logic:notEmpty>
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tr>
					<td align="center">
						<div class="btn">
							<!-- �ǲ鿴 -->
							<logic:notEqual name="doType" value="view">
								<button type="button" id="buttonSave" onclick="xmsh('tg')" style="width: 80px">
									ͨ��
								</button>
								&nbsp;&nbsp;
								<button type="button" id="buttonSave" onclick="xmsh('btg')" style="width: 80px">
									��ͨ��
								</button>
								&nbsp;&nbsp;
							</logic:notEqual>
							<button type="button" id="buttonSave" onclick="window.close();return false;" style="width: 80px">
								��	��
							</button> 
							<logic:equal name="xxdm" value="10491">
							&nbsp;&nbsp;
							<button type="button" id="buttonSave" onclick="showXscjXx()" style="width: 80px">
								��  ��
							</button>
							</logic:equal>
						</div>
					</td>
				</tr>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="other/tsxx.jsp"%>
		</html:form>
	</body>
</html>