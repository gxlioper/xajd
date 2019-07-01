<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
<%--		<%@ include file="/syscommon/pagehead_V4.ini"%>--%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
				
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "xsxx_tygl.do?method=cxZxsxxjg";
			var ie = "ie";
			
			var otherValue = [ie];

			
			searchRsByAjax(url,otherValue);

			
			jQuery.ajaxSetup({async:true});
			
		}
		
		//����
		function showzxsxxAdd(){
			var url = "xsxx_tygl.do?method=zjZxsxx";
			showDialog("����ѧ����Ϣ",750,380,url);
		}
		
		//�޸�
		function showzxsxxEdit(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=xgZxsxx";
				url+="&xh="+pkValue;
				showDialog("�޸�ѧ����Ϣ",850,550,url);
			}else{	
				alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");	
				return false;
			}
		}
		
		//�鿴
		function showzxsxxView(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showDialog("�鿴ѧ����Ϣ",850,500,url);
				var height = 640;
				var left = (screen.width/2) - width/2;
				var top = (screen.height/2) - height/2;
				//var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
				//window.open(url,"msgWindow", styleStr);
			}else{	
				alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ���鿴�ļ�¼��");
				return false;
			}
		}

		//�°�鿴������
		function zxsxxView(xh){

				showDialog("ѧ����Ϣ��ѯ",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh+"&xs");
		}

		
		//ɾ��
		function deletezxsxx(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len!=0){	
					confirmInfo("����ȷ��<font color='blue'>�Ƿ�ɾ��</font>����ѡ�ļ�¼��",function(tag){
						if(tag=="ok"){
							var url = "xsxx_tygl.do?method=scZxsxx";
							var pkValue=new Array();
							var i=0;
							
							jQuery("input[name=primarykey_checkVal]:checked").each(function(){
								pkValue[i]=jQuery(this).val();
								i++;
							});
							
							var parameter={};
							parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
					
						 	$("divWaiting").style.display="";
							$("divDisable").style.display="";
							
							jQuery.ajaxSetup({async:false});
							
							jQuery.post(url,
								parameter,
								function(result){
									$("divWaiting").style.display="none";
									$("divDisable").style.display="none";
									searchRs();
									alertInfo(result);
									closeWindown();		
								}
							);
					
							jQuery.ajaxSetup({async:true});
						}
					});
			}else{	
				alertInfo("��<font color='blue'>��ѡ</font>��ϣ��ɾ���ļ�¼��");	
				return false;
			}
		}
		//�����ʼ��
		function mmcsh(){
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				showDialog("�����ʼ��",350,210,"xsxx_tygl.do?method=showCshmm");
			}else{	
				alertInfo("��<font color='blue'>��ѡ</font>����Ҫ��ʼ�������ݣ�");				
			}
		}
		
		jQuery(function(){
			onShow();
		})
		
		//��ӡͨ��ѧ����
		function printTyXjk(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=getXjk";
				
				var url= url+"&xh="+pkValue;
				window.open(url);
			}else if(len==0){	
				alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");	
				return false;
			}else{
				var url="xsxx_tygl.do?method=getXjkZipTy";
				var pkValue=jQuery("[name=primarykey_checkVal]:checked")
				var ids=pkValue.eq(0).val();
				for(i=1;i<pkValue.length;i++){
					ids+=","+pkValue.eq(i).val();
				}
				var url= url+"&value="+ids;
				window.open(url);
			}
		}
		
		//��ӡ����
		function printZxsxx(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=printJsp";
				url+="&xh="+pkValue;
				window.open(url);
			}else{	
				alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");	
				return false;
			}
		}

		function getWord(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=getXjkWord";
				
				var url= url+"&xh="+pkValue;
				window.open(url);
			}else if(len==0){	
				alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");	
				return false;
			}else{
				var url="xsxx_tygl.do?method=getXjkZip";
				var pkValue=jQuery("[name=primarykey_checkVal]:checked")
				var ids=pkValue.eq(0).val();
				for(i=1;i<pkValue.length;i++){
					ids+=","+pkValue.eq(i).val();
				}
				var url= url+"&value="+ids;
				window.open(url);
			}
		}
		
		function printXjk(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len>=1){	
				var xhstr=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var pkValue="";
				for(i=1;i<len;i++){
					xhstr+=","+jQuery("[name=primarykey_checkVal]:checked").eq(i).val();
				}
				jQuery("#xhstr").val(xhstr);
				var url="xsxx_tygl.do?method=plPrintJsp";
				document.forms[0].action=url;
				document.forms[0].target="_blank";
				document.forms[0].submit();
				document.forms[0].target="_self";
			}else{
				alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");	
				return false;
			}
		}
		
		
		function zxsxxExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("xsxx_tygl_cxzxs.do", zxsxxExportData,1000,500);
		}
		
	
		
		// ��������
		function zxsxxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xsxx_tygl.do?method=zxsxxExportData&dcclbh=" + "xsxx_tygl_cxzxs.do";
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function drxx(){
			toImportData("IMPORT_N100101");
			return false;
		}
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
<!--			<p class="help">-->
<!--				<a href="#" onclick="return false;" -->
<!--					onmouseover ="showHelpDiv()"-->
<!--					onmouseout="showHelpDiv()"-->
<!--				>-->
<!--				ʹ�ð���</a>-->
<!--			</p>-->
		</div>
		<!-- ���� end-->
		
		<html:form action="/xsxx_tygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<!-- ���Ĵ�����ְҵ����ѧԺ -->
							<logic:notEqual name="xxdm" value="12751" >
							<li>
								<a href="#" onclick="showzxsxxAdd();return false;" id="btn_zj" class="btn_zj">
									����
								</a>
							</li>
							<li>
								<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg" class="btn_xg">
									�޸�
								</a>
							</li>
							<li>
								<a href="#" onclick="deletezxsxx();return false;" id="btn_sc" class="btn_sc">
									ɾ��
								</a>
							</li>
							<!-- end ���Ĵ�����ְҵ����ѧԺ -->
							</logic:notEqual>
							<!-- �Ĵ�����ְҵ����ѧԺ -->
							<logic:equal name="xxdm" value="12751">
								<logic:notEqual value="xx" name="userStatus">	
									<li>
										<a href="#" onclick="showzxsxxEdit();return false;" id="btn_xg" class="btn_xg">
											�޸�
										</a>
									</li>
								</logic:notEqual>
							<!-- end �Ĵ�����ְҵ����ѧԺ -->
							</logic:equal>
						</logic:equal>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck" class="btn_ck">
								�鿴
							</a>
						</li>
						<!--  -->
						<logic:notEqual name="xxdm" value="11600">
							<logic:notEqual name="xxdm" value="10834">
								<logic:notEqual name="xxdm" value="9800003">
									<logic:notEqual name="xxdm" value="110501">
										<li><a href="javascript:void(0);" onclick="printTyXjk();return false;" class="btn_dy">ѧ���ǼǱ��ӡ</a></li>
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						<!-- ��������ѧԺ���Ի� -->
						<logic:equal name="xxdm" value="11600">
						<li><a href="javascript:void(0);" onclick="printZxsxx();return false;" class="btn_dy">ѧ���ǼǱ��ӡ</a></li>
						</logic:equal>
						<!-- �人ְҵ����ѧԺ���Ի� -->
						<logic:equal name="xxdm" value="10834">
						<li><a href="javascript:void(0);" onclick="printZxsxx();return false;" class="btn_dy">ѧ���ǼǱ��ӡ</a></li>
						</logic:equal>
						<!-- �㶫�Ṥ -->
						<logic:equal name="xxdm" value="9800003">
						<li><a href="javascript:void(0);" onclick="printXjk();return false;" class="btn_dy">ѧ��ѧ������ӡ</a></li>
						</logic:equal>
						<!-- ��ͨ�ߵ�ʦ��ѧУ -->
						<logic:equal name="xxdm" value="110501"><!-- update �Ų�·[982] -->
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_dy">����ѧ��ѧ����</a></li>
						<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">���������Ϣ</a></li>
						</logic:equal>
						<li><a href="#" class="btn_qx" onclick="zxsxxExportConfig();return false;">����</a></li>
						<logic:equal name="writeAble" value="yes">
							<li><a href="#" onclick="drxx();return false;" class="btn_dr">����</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
						--%>
						<li><a href="#" onclick="mmcsh('show');return false;" class="btn_csh">�����ʼ��</a></li>
						
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
				<div id="div_rs" style="height:420px;overflow-x:hidden;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxtyglForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ���̸��ٵ����� -->
			<div id="div_lcgz" style="display:none">
				<div class="open_win01">
				</div>
			</div>
			<!-- ���̸��ٵ����� end-->
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>