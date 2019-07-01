<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "jhzy_gjzxj.do?method=searchZxjsq";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//����
		function showZxjsqAdd(){
			var url = "jhzy_gjzxj.do?method=zxjsqInsert";
			showTopWin(url,"800","480");
		}
		
		//�޸�
		function showZxjsqEdit(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var flag = true;	
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					var shzt = jQuery(this).parents().children("td").eq(8).html();
					if(shzt != "δ���"){
						flag = false;
					}
				});
				
				if(!flag){
					alertError("ֻ���޸�<font color='blue'>δ���</font>�ļ�¼������ȷ��^_^||");
				}
				
				if(flag){
					var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
					var url="jhzy_gjzxj.do?method=zxjsqUpdate";
						url+="&pkValue="+pkValue;
						showTopWin(url,800,480);
				}
			}else{	
				alertError("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");	
				return false;
			}
		}
		
		//ɾ��
		function deleteGjzxjSq(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len!=0){	
				var flag = true;	
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					var shzt = jQuery(this).parents().children("td").eq(8).html();
					if(shzt != "δ���"){
						flag = false;
					}
				});
				
				if(!flag){
					alertError("ֻ��ɾ��<font color='blue'>δ���</font>�ļ�¼������ȷ��^_^||");
				}
				
				if(flag){
				
					confirmInfo("����ȷ��<font color='blue'>�Ƿ�ɾ��</font>����ѡ�������¼��",function(tag){
						if(tag=="ok"){
							var url = "jhzy_gjzxj.do?method=deleteGjzxjSq";
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
				}
			}else{	
				alertError("��<font color='blue'>��ѡ</font>��ϣ��ɾ���ļ�¼��");	
				return false;
			}
		}
		
		//���̸���
		function showLcgz(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pk=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				jQuery.ajaxSetup({async:false});

				//·��
				var url = "jhzy_gjzxj.do?method=createLcgzHtml";
				//����
			 	var parameter = {
					"str_pk":pk
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery("#div_lcgz").load(
					url,
					parameter,
					function(){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
					}
				);
					
				tipsWindown("ϵͳ��ʾ","id:div_lcgz","500","300","true","","true","id");
			
				jQuery.ajaxSetup({async:true});
			}else{	
				alertError("��<font color='blue'>��ѡһ��</font>��ϣ���������̵ļ�¼��");	
				return false;
			}	
		}
		
		function showPrint(){
		
			var n=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n==1){
				var pk=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();

				var pkArr=pk.split("luojw");
				var xn=pkArr[0];
				var xh=pkArr[1];
				showOpenWindow("jhzy_gjzxj.do?method=gjzxjb&xh="+xh+"&xn="+xn);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ��ӡ�ļ�¼��");
				return false;
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" 
					onmouseover ="showHelpDiv()"
					onmouseout="showHelpDiv()"
				>
				ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showZxjsqAdd();return false;" id="btn_zj" class="btn_zj">
									����
								</a>
							</li>
							<li>
								<a href="#" onclick="showZxjsqEdit();return false;" id="btn_xg" class="btn_xg">
									�޸�
								</a>
							</li>
							<li>
								<a href="#" onclick="deleteGjzxjSq();return false;" id="btn_sc" class="btn_sc">
									ɾ��
								</a>
							</li>
						</logic:equal>
						<li>
							<a href="#" onclick="showLcgz();return false;" id="btn_ccg" class="btn_ccg">
								���̸���
							</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
						<li>
							<a href="#" class="btn_dy" onclick="showPrint();return false;" onclick="return false;">��ӡ����</a>
						</li>
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jhzyXszzGjzxjForm"></jsp:include>
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
					sss
				</div>
			</div>
			<!-- ���̸��ٵ����� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>