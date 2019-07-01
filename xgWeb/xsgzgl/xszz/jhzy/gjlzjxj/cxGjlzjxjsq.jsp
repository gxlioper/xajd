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
			
			var url = "jhzyGjlzjxj.do?method=searchGjlzjxjsq";
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
		function showKnssqAdd(){
			var url = "jhzyGjlzjxj.do?method=gjlzjxjsqInsert";
			showTopWin(url,"830","680");
		}
		
		//�޸�
		function showKnssqEdit(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var flag = true;	
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					var shzt = jQuery(this).parents().children("td").eq(9).html();
					if(shzt != "δ���"){
						flag = false;
						
					}
				});
				
				if (!flag) {
					alertError("ֻ���޸�<font color='blue'>δ���</font>�ļ�¼������ȷ��^_^||");
					return false;
				}
				
				if(flag){
					var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
					var url= "jhzyGjlzjxj.do?method=gjlzjxjsqUpdate";
						url+="&pkValue="+pkValue;
						showTopWin(url,830,680);
				}
			}else{	
				alertError("��<font color='blue'>��ѡһ��</font>��ϣ���޸ĵļ�¼��");	
				return false;
			}
		}
		
		//�鿴
		function showKnssqView(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var flag = true;	
				
				if(flag){
					var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
					var url= "jhzyGjlzjxj.do?method=gjlzjxjsqView";
						url+="&pkValue="+pkValue;
						showTopWin(url,830,680);
				}
			}else{	
				alertError("��<font color='blue'>��ѡһ��</font>��ϣ���鿴�ļ�¼��");	
				return false;
			}
		}
		
		//ɾ��
		function deleteKnsrdSq(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len!=0){	
				var flag = true;	
				jQuery("[name=primarykey_checkVal]:checked").each(function(){
					var shzt = jQuery(this).parents().children("td").eq(9).html();
					if(shzt != "δ���"){
						flag = false;
					}
				});
				
				if (!flag) {
					alertError("ֻ��ɾ��<font color='blue'>δ���</font>�ļ�¼������ȷ��^_^||");
					return false;
				}
				
				if(flag){
				
					confirmInfo("����ȷ���Ƿ�ɾ������ѡ�������¼��",function(tag){
						if(tag=="ok"){
							var url = "jhzyGjlzjxj.do?method=deleteGjlzjxjSq";
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
				var url = "jhzyGjlzjxj.do?method=createLcgzHtml";
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

				var pkArr=pk.split("!!luojw!!");
				var xn=pkArr[0];
				var xh=pkArr[1];
				showOpenWindow("jhzyGjlzjxj.do?method=gjlzjxjb&xh="+xh+"&xn="+xn);
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
	<!-- ��ʾ��Ϣ end-->
		<logic:notEmpty name="czqx">
		<div class="prompt">
			<h3>
				<span>��ʾ��${czqx }</span>
			</h3>
			<p>
				
			</p>
		</div>
</logic:notEmpty>
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
		
		<html:form action="/jhzyGjlzjxj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:empty name="czqx"><!-- ��ʱ�䷶Χ�ڲ��ܲ��� -->
							<li>
								<a href="#" onclick="showKnssqAdd();return false;" id="btn_zj" class="btn_zj">
									����
								</a>
							</li>
							<li>
								<a href="#" onclick="showKnssqEdit();return false;" id="btn_xg" class="btn_xg">
									�޸�
								</a>
							</li>
							<li>
								<a href="#" onclick="deleteKnsrdSq();return false;" id="btn_sc" class="btn_sc">
									ɾ��
								</a>
							</li>
							</logic:empty>
							<li>
								<a href="#" onclick="showKnssqView();return false;" id="btn_ck" class="btn_ck">
									�鿴
								</a>
							</li>
							
							<li>
								<a href="#" onclick="showLcgz();return false;" id="btn_ccg" class="btn_ccg">
									���̸���
								</a>
							</li>
							<li>
								<a href="#" class="btn_dy" onclick="showPrint();return false;" onclick="return false;">��ӡ����</a>
							</li>
							<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						    <li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
				
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gjlzjxjForm"></jsp:include>
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