<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/uicomm.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script language="javascript" src="xsgzgl/szdw/fdypx/js/fdypxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<!-- ���빦����Ҫ -->
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
		//�����Ƽ�ʦ����ѧ˼�����鸨��Ա�䱸�������Ի�
		function pbbExportData() {
				var url = "szdw_dwwh.do?method=pbbExportData";
				var map = getSuperSearch();
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		//�����Ƽ�ʦ����ѧ˼�����鸨��Ա������Ϣ�������Ի�
		function dabExportData() {
			var url = "szdw_dwwh.do?method=fdyxxwhExportJxsf";
			var map = getSuperSearch();
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		//�����Ƽ�ʦ����ѧ˼�����鸨��Ա��������������Ի�
		function dbqkbData() {
				var url = "szdw_dwwh.do?method=dbqkbData";
				var map = getSuperSearch();
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		
		function configureExportData() {
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

		function viewFdyxx(){
			var url='szdw_fdyxx.do?method=fdyxxView&zgh='
			var check = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var num = check.length;
			if(num == 1){
				var zgh = jQuery(check[0]).val();
				//showTopWin("/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh="+zgh,830,600); .
				showDialog('', 830, 500, url+zgh);
				//window.open ('/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh='+zgh);
			}else{
				showAlertDivLayer("�빴ѡ<font color='blue'>һ����¼</font>���в鿴");
				return false;
			}
		}
		function viewjgz(zgh){
			var url='szdw_fdyxx.do?method=fdyxxView&zgh='+zgh;
			showDialog('', 830, 500, url);
		}
		// ˼����������
		function szdwSz_10352(){
			var array = new Array();
			jQuery("[name=primarykey_checkVal]:checked").each(function(i){
				array[i] = escape(jQuery(this).val());
			});
			if(array.length == 0){
				showAlertDivLayer("�빴ѡ<font color='blue'>����һ����¼</font>");
				return false;
			}
			var ids = array.join('!!array!!');
			showDialog('˼����������',450,150,'szdw_dwwh.do?method=szdwSz_10352&ids='+ids);
		}
		
		jQuery(function(){
			onShow();
		})
		
		//ɽ���ƾ���ѧ��ʦ�ǼǱ���
		function getSxCjDxJsdjbExport(){
			var check = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			if(check.length == 0){
				showAlertDivLayer("�빴ѡ<font color='blue'>����һ����¼</font>");
				return false;
			}
			
			var url = "";
			if(check.length == 1){
				var zgh = jQuery(check[0]).val();
				url = "szdw_fdyxx.do?method=getJsdj";
				url += "&zgh=" + zgh;
			}else{
				var zghs = "";
				for(var i=0;i<check.length;i++){
					zghs +=jQuery(check[i]).val()+",";
				}
				url = "szdw_fdyxx.do?method=getJsdjTy";
				url += "&value=" + zghs;
			}
			
			window.open(url);
		}
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span> �ڴ˴�ά����ʦ��Ϣ����Ҫ��¼ϵͳ����ʦ��Ҫ�ڡ��û�ά�����˵��н��з������ </span>
			</p>
			<a class="close" title="����"
				onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/general_szdw" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="path" value="${path}" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ begin-->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" class="btn_zj"
									onclick="createDwwhDiv('insert');return false;"> ���� </a>
							</li>
							<li>
								<a href="#" class="btn_xg"
									onclick="checkDwwhUpdate();return false;"> �޸� </a>
							</li>
							<li>
								<a href="#" class="btn_sc" onclick="deleteDwwh();return false;">
									ɾ�� </a>
							</li>
							<li>
								<a href="#" class="btn_ck" onclick="viewFdyxx();return false;">
									�鿴 </a>
							</li>
							<li>
							<logic:equal value="10698" name="xxdm">
								<a href="#" id="btn_dr"
									onclick="toImportDataNew('IMPORT_N100108_10698'); return false;"
									class="btn_dr">����</a>
							</logic:equal>
							<logic:equal value="10080" name="xxdm">
								<a href="#" id="btn_dr"
									onclick="toImportDataNew('IMPORT_N100108_10698'); return false;"
									class="btn_dr">����</a>
								</logic:equal>
							<logic:notEqual value="10698" name="xxdm">
							<logic:notEqual value="13431" name="xxdm">
								<logic:notEqual value="10080" name="xxdm">
								<a href="#" id="btn_dr"
										onclick="toImportData('IMPORT_N100108'); return false;"
										class="btn_dr">����</a>
								</logic:notEqual>
							</logic:notEqual>
							</logic:notEqual>
							</li>
							<logic:equal value="11318" name="xxdm">
								<li>
									<a href="#" class="btn_dc"
										onclick="pbbExportData();return false;">�䱸����</a>
								</li>
							</logic:equal>
							<logic:equal value="11318" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="dbqkbData();return false;">�����������</a>
								</li>
							</logic:equal>
							<logic:equal value="11318" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="dabExportData();return false;">��������</a>
								</li>
							</logic:equal>
							
							<logic:equal value="10335" name="xxdm">
								<logic:equal value="zf01" name="userName">
									<li>
										<a href="#" class="btn_dc"
											onclick="configureExportData();return false;">����</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li>
									<a href="#" class="btn_dc" onclick="configureExportData();return false;">����</a>
								</li>
							</logic:notEqual>
							
							<logic:equal value="10125" name="xxdm">
								<li>
									<a href="#" class="btn_dy"
										onclick="getSxCjDxJsdjbExport();return false;">��ʦ�ǼǱ�</a>
								</li>
							</logic:equal>
							<logic:equal value="14073" name="xxdm">
								<li>
									<a href="#" class="btn_dy"
										onclick="getSxCjDxJsdjbExport();return false;">��ʦ�ǼǱ�</a>
								</li>
							</logic:equal>
							<%--<logic:equal value="13431" name="xxdm">--%>
								<li>
									<a href="#" class="btn_dy"
										onclick="getSxCjDxJsdjbExport();return false;">��ʦ�ǼǱ�</a>
								</li>
							<%--</logic:equal>--%>
							<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>--%>
							<logic:equal value="10352" name="xxdm">
								<li>
									<a href="#" class="btn_sz" onclick="szdwSz_10352();return false;">˼����������</a>
								</li>
							</logic:equal>
							<!-- ������ҽҩ��ѧ ��ʦ���ά�� -->
							<logic:equal value="10026" name="xxdm">
								<li>
									<a href="#" class="btn_csh" onclick="jssfPlwh();return false;">��ʦ���ά��</a>
								</li>
							</logic:equal>
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
				<div style="clear: both;">
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"></jsp:include>
					<script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->

			<!-- ����ά��Div begin -->
			<div id="div_dwwh" style="display: none">

			</div>
			<!-- ����ά��Div end -->

			<!-- �û���Div begin -->
			<div id="div_yhk" style="display: none">

			</div>
			<!-- �û���Div end -->

			<!-- ԺУ����Div begin -->
			<div id="div_yxjr" style="display: none">

			</div>
			<!-- ԺУ����Div end -->

			<!-- �༶��ϢDiv begin -->
			<div id="div_bjxx" style="display: none">

			</div>
			<!-- �༶��ϢDiv end -->

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>