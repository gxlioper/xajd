<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ������ -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/jtcy.js"></script>	
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xszz/xszzInit.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzOptionList.js"></script>
	</head>
	<body onload="onShow_xmsq();xszzInit();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz" method="post" enctype="multipart/form-data">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xxdm" value="${xxdm }"/>
			
			<input type="hidden" name="url" id="url" value="/commXszz.do?method=zzxbUpdate" />
			
			<input type="hidden" name="save_sqsj" id="sqsj" value="${xmInfo.sqsj }"/>
			<input type="hidden" name="save_xmdm" id="xmdm" value="${xmInfo.xmdm }"/>
			<input type="hidden" name="save_xh" id="xh" value="${xmInfo.xh }"/>
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
						<font size="5"><b>
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
						${xmInfo.xmmc } ����</b>
						</font>
					</td>
				</tr>
			</table>
			<logic:notEmpty name="xmnrList">
				<logic:iterate name="xmnrList" id="xmnr">
					<!-- ѧ��������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="view_xsjbxx">
						<%@ include file="../sqsh/xsInfo.jsp"%>
					</logic:equal>
					<!-- ѧ��������Ϣ end-->
					<!-- ��ͥ��� -->
					<logic:equal name="xmnr" property="mk" value="jtqkdcb">
						<%@ include file="../sqsh/jtqkInfo.jsp"%>
					</logic:equal>
					<!-- ��ͥ��� end-->					
					<!-- �۲�����ʲ��� -->
					<logic:equal name="xmnr" property="mk" value="zcf">
						<%@ include file="../sqsh/zcf.jsp"%>
					</logic:equal>		
					<!-- ������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="xsqtxxb">
						<%@ include file="../sqsh/qtInfo.jsp"%>
					</logic:equal>	
					<!-- ������Ϣ end-->	
					<!-- ��������Ϣ -->
					<logic:equal name="xmnr" property="mk" value="xszz_knsb">
						<%@ include file="../sqsh/knInfo.jsp"%>
					</logic:equal>	
					<!-- ��������Ϣ end-->
					<!-- ��Ŀ��� -->
					<logic:equal name="xmnr" property="mk" value="xmxgInfo">
						<%@ include file="../sqsh/wsInfo.jsp"%>
					</logic:equal>
					<!-- ��Ŀ��� end-->
					
					<!-- �㽭�Ƽ���ѧ���������  -->
					<logic:equal name="xmnr" property="mk" value="zzxb">
						<%@ include file="zzxbInfo.jsp"%>
					</logic:equal>
					<!-- �㽭�Ƽ���ѧ��������� end -->
					
				</logic:iterate>	
			</logic:notEmpty>
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
							<!-- �ǲ鿴 -->
							<logic:notEqual name="doType" value="view">
								<button type="button"  id="buttonSave" onclick="saveZzxb()" style="width: 80px">
									��	��
								</button>
							</logic:notEqual>
							<button type="button"  id="buttonSave" onclick="window.close();return false;" style="width: 80px">
								��	��
							</button> 
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="../other/tsxx.jsp"%>
		</html:form>
	</body>
</html>