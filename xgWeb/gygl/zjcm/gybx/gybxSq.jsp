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
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		//���汨������
		function saveBxSq(){
			
			var mklx = $("mklx").value;
			var url = "/xgxt/zjcmGygl.do?method=gybxSq&doType=save";
			url += "&mklx="+mklx;
			if (checkSjTj("bxsj","����ʱ��","xwsj","ϣ��ʱ��")) {	
				saveUpdate(url,'xh-bxlxdh');	
			}
		}
		
		//���ϣ������ʱ��
		function checkXwsj(){
			var xwrq = $("xwsj").value;
			var kssj = $("xwkssj").value;
			var jssj = $("xwjssj").value;
			
			if(kssj != ""){
			
				if(kssj > 24){
					alert("ʱ��¼�벻�ܴ���24����ȷ�ϣ�");
					$("xwkssj").value = "";
				 	$("xwkssj").focus();
					return false;
				}
				
				if(kssj.length > 0 && kssj.substring(0,1) == 0){
					$("xwkssj").value = kssj.substring(1,kssj.length);
				}
			}
			
			if(jssj != ""){
			
				if(jssj > 24){
					alert("ʱ��¼�벻�ܴ���24����ȷ�ϣ�");
					$("xwjssj").value = "";
				 	$("xwjssj").focus();
					return false;
				}
				
				if(jssj.length > 0 && jssj.substring(0,1) == 0){
					$("xwjssj").value = jssj.substring(1,jssj.length);
				}
			}
			
			if(kssj!="" && jssj!="" && parseInt(kssj)>parseInt(jssj) ){
				alert("��ʼʱ�䲻�ܴ��ڽ���ʱ�䣬��ȷ�ϣ�");
				$("xwkssj").value = "";
			 	$("xwjssj").value = "";
			 	$("xwkssj").focus();
				return false;
			}
		}
		</script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl" >
		
			<!-- ������ -->
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="ѧ����Ϣ"/>
			<input type="hidden" id="zd" name="zd" value=""/>
			<input type="hidden" id="va" name="va" value=""/>
			<input type="hidden" id="url" name="url" value="/xgxt/zjcmGygl.do?method=gybxSq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<!--ѧ����Ϣ -->
			<%@ include file="info/xsInfo.jsp"%>
			<!--ѧ����Ϣ end-->
			
			<!--������ť -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="bz">
								<font color="blue">
									ע��������ύ��ظ�������ˣ������˲�ͨ�����뼰ʱ�鿴�Ƿ�ôα�����Ҫά�޷��ã��뼰ʱ���塣
								</font>
							</div>
							<div class="btn">
								<button id="buttonSave" 
									onclick="saveBxSq()"
									style="width: 80px">
									��	��
								</button>
								<logic:equal name="isjg" value="yes">
								&nbsp;&nbsp;
								<button class="" id="buttonSave" 
									onclick="Close();return false;" style="width: 80px">
									��	��
								</button>
								</logic:equal>
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