<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
			<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			var path = jQuery("#path").val();
			var url = "szdw_dwwh.do?method=searchDwwh&path="+path;
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		function viewjgz(zgh){
			var url='szdw_dwwh.do?method=ckJzgxx&zgh='+zgh;
			showDialog('', 830, 500, url);
		}
		function fdyxxwhExportConfig() {
			customExport("szdw_general_dwwh.do", fdyxxwhExportData);
		}
		
		// ��������
		function fdyxxwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "szdw_dwwh.do?method=fdyxxwhExportData&dcclbh=" + "szdw_general_dwwh.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body  >
		<input type="hidden" name="isopen" id="isopen" value="${bbsjModel.isopen }"/>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">				
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				<logic:equal value="12834" name="xxdm">
					ѡ���ӳ���Ϊ��ӳ������Ͻ�İ༶
				</logic:equal>
				<logic:notEqual value="12834" name="xxdm">
					ѡ�񸨵�Ա��Ϊ����Ա�����Ͻ�İ༶
				</logic:notEqual>
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/general_szdw" method="post">
			<!-- ������ -->
			<input type="hidden" id="path" value="${path}" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						
						<!-- ��дȨ begin-->
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="bbsjModel" property="isopen" value="true" >
							<li>
								<a href="#" class="btn_sr" onclick="goFdybb();return false;">
									<logic:equal value="12834" name="xxdm">
												��ӳ����
									</logic:equal>
									<logic:notEqual value="12834" name="xxdm">
												����Ա���
									</logic:notEqual>
								</a>
							</li>
							<logic:notEqual value="10264" name="xxdm"> <!-- �Ϻ������ѧ����Ҫ��ʾ  -->
								<li>
									<a href="#" class="btn_gx" onclick="goBzrbb();return false;">
									<logic:equal value="12834" name="xxdm">
												�жӳ����
									</logic:equal>
									<logic:notEqual value="12834" name="xxdm">
												�����α��
									</logic:notEqual>
									</a>
								</li>
							</logic:notEqual>
							</logic:equal>
							<li>
								<a href="#" class="btn_csh" onclick="createYxjrDiv();return false;">
									ԺУ�����������
								</a>
							</li>
						<li><a href="#" class="btn_dc" onclick="fdyxxwhExportConfig();return false;">����</a></li>
						</logic:equal>
						<!-- ��дȨ end-->
					
						
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<div id="div_rs" style="" class="con_overlfow">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ����ά��Div begin -->
			<div id="div_dwwh" style="display:none">
				
			</div>
			<!-- ����ά��Div end -->

			<!-- �û���Div begin -->
			<div id="div_yhk" style="display:none">
				
			</div>
			<!-- �û���Div end -->
			
			<!-- ԺУ����Div begin -->
			<div id="div_yxjr" style="display:none">
				
			</div>
			<!-- ԺУ����Div end -->
			
			<!-- �༶��ϢDiv begin -->
			<div id="div_bjxx" style="display:none">
				
			</div>
			<!-- �༶��ϢDiv end -->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>