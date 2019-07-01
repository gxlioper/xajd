<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� --> 
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "szdw_szbb.do?method=searchSzbb";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			setDivHeight();
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		// ����Ա���
		function fdybbSetting(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ���ű�࣬����ϵ����Ա��");
				return false;
			}
		
			var length=jQuery("[name=div_pkValue]:checked").length;
			var nj="";
			var xydm="";
			var zydm="";
			var bjdm="";
			if(length>1){
				
				alertInfo("����ѡ��һ���༶!");
				return false;
			}else if(length==1){
				
				var pkValue=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var pkArr=new Array();
				pkArr=pkValue.split('!!luojw!!');
				nj=pkArr[0];
				xydm=pkArr[1];
				zydm=pkArr[2];
				bjdm=pkArr[3];
				
			}
			
			var url = "general_szdw.do?method=szbbSetting&fplx=fdy";
			url+="&nj="+nj;
			url+="&xydm="+xydm
			url+="&zydm="+zydm
			url+="&bjdm="+bjdm
			refreshForm(url);
			
		}

		//�������⾭��ѧԺ����QQȺ��--
		function SetQQqh(){
			var length=jQuery("[name=div_pkValue]:checked").length;
			var bjdm="";
			if(length!=1){
				
				alertInfo("��ѡ���ҽ�ѡ��һ���༶!");
				return false;
			}else if(length==1){
			
				var pkValue=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var pkArr=new Array();
				pkArr=pkValue.split('!!luojw!!');
				nj=pkArr[0];
				bjdm=pkArr[3];
				xymc=pkArr[4];
				zymc=pkArr[5];
				rs=pkArr[6];
				fdyxm=pkArr[7];
				bzrxm=pkArr[8];
				var url = "general_szdw.do?method=setQQqh&nj="+nj+"&xymc="+xymc+"&zymc="+zymc+"&bjdm="+bjdm+"&rs="+rs+"&fdyxm="+fdyxm+"&bzrxm="+bzrxm;
				showDialog("��д�༶QQȺ",600,355,url);
			}
		}
		
		jQuery(function(){
			onShow();
		})
		
		function szdwbbExportConfig() {
			customExport("general_szdw.do", szdwbbExportData);
		}
		
		// ��������
		function szdwbbExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "szdw_szbb.do?method=szdwbbExportData&dcclbh=" + "general_szdw.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//���뷽��
		function drxx(){
			toImportData("IMPORT_SZDW_BBXX");
			return false;
		}

		// ѧ����Ϣ
		function xsxxView(pk,n){
			 pk = encodeURI(encodeURI(pk));
			if(0 == n) {
				alertError("�༶����Ϊ0���޷��鿴���飡");
				return false;
			}
			showDialog("ѧ����ϸ��Ϣ",800,430,"gygl_lfrydj.do?method=xsxxView&pk="+pk);
		}

		function jslx(){
			var length=jQuery("[name=div_pkValue]:checked").length;
			if(length < 1){
				showAlertDivLayer("��ѡ��һ���༶��");
			}else{
				//top.location.href = "";
				showDialog("��ѡ����Ľ�ʦ����", 400, 200, "szdw_szbb.do?method=jslx&quantity="+length);
			}
		}
	
		</script>
	</head>
	<body  >
		<input type="hidden" name="isopen" id="isopen" value="${bbsjModel.isopen }"/>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
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
					ѡ����Ӧ�İ༶��Ϊ�༶�����ӳ����жӳ�
				</logic:equal>
				<logic:notEqual value="12834" name="xxdm">
					ѡ����Ӧ�İ༶��Ϊ�༶���丨��Ա�������
				</logic:notEqual>
					
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/general_szdw" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="bbsjModel" property="isopen" value="true" >
							<li>
								<a href="#" class="btn_sr" onclick="fdybbSetting();return false;">
									
											<logic:equal value="12834" name="xxdm">
												���Ӵ�ӳ�
											</logic:equal>
											<logic:notEqual value="12834" name="xxdm">
												���Ӹ���Ա
											</logic:notEqual>
								</a>
							</li>
							<logic:notEqual value="10264" name="xxdm">  <!-- �Ϻ������ѧ����Ҫ��ʾ  -->
								<%--<li>
									<a href="#" class="btn_gx" onclick="bzrbbSetting();return false;">
										<logic:equal value="12834" name="xxdm">
												�����жӳ�
											</logic:equal>
											<logic:notEqual value="12834" name="xxdm">
												���Ӱ�����
											</logic:notEqual>
									</a>
								</li>--%>
								<logic:equal value="12303" name="xxdm">
									<li>
										<a href="#" class="btn_zj" onclick="SetQQqh();return false;">
												���Ӱ༶��Ϣ
										</a>	
									</li>
								</logic:equal>
							</logic:notEqual>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="szdwbbExportConfig();return false;">����</a></li>
							<!-- <li><a href="javascript:;" onclick="drxx();return false;" id="btn_dr" class="btn_dr">����</a></li> -->							
						</logic:equal>
						<logic:equal value="00000" name="xxdm">
						<li><a href="javascript:void(0);" onclick="jslx();" class="btn_sz">�������</a></li>
						</logic:equal>
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:hidden;">
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
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>