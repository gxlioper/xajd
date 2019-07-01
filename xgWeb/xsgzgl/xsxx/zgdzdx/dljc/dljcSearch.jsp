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
			
			var url = "general_xsxx_dljc.do?method=searchDljc";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//���õ�¼���
		function resetDljc(){
		
			var num=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(num == 0){//����
			
				//�꼶
				var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
				//ѧԺ
				var xy_num = jQuery("a[name=a_name_xy]").length; 		
				//רҵ
				var zy_num = jQuery("a[name=a_name_zy]").length; 
				//�༶
				var bj_num = jQuery("a[name=a_name_bj]").length; 
		
				confirmInfo("��ȷ���Ƿ�<font color='blue'>������������ָ����ѧ��</font>������Ϣ����״̬Ϊ��",resetDljcNoChecked);
				
			}else{//��ѡ
				confirmInfo("��ȷ���Ƿ�<font color='blue'>����ѡ��ѧ��</font>������Ϣ����״̬Ϊ��",resetDljcChecked);
			}
		}
		
		//���õ�¼��⡾δ��ѡ��
		function resetDljcNoChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				//�꼶
				var nj = new Array();  
				var i = 0;				  
				jQuery("a[name=a_name_nj]").each(function(){
					var nj_id = jQuery(this).attr("id");
					nj[i] = nj_id.replace("a_id_","");
					i++;
				});
				
				//ѧԺ
				var xy = new Array(); 
				i = 0;			  
				jQuery("a[name=a_name_xy]").each(function(){
					var xy_id = jQuery(this).attr("id");
					xy[i] = xy_id.replace("a_id_","");
					i++;
				});
		
				//רҵ
				var zy = new Array(); 
				i = 0;					  
				jQuery("a[name=a_name_zy]").each(function(){
					var zy_id = jQuery(this).attr("id");
					zy[i] = zy_id.replace("a_id_","");
					i++;
				});
		
				//�༶
				var bj = new Array();  				  
				jQuery("a[name=a_name_bj]").each(function(){
					var bj_id = jQuery(this).attr("id");
					bj[i] = bj_id.replace("a_id_","");
					i++;
				});
				
				var url = "general_xsxx_dljc.do?method=resetDljc";
				
				//����
			 	var parameter = {
			 		"array_nj":nj.join("!!array!!"),
			 		"array_xy":xy.join("!!array!!"),
			 		"array_zy":zy.join("!!array!!"),
			 		"array_bj":bj.join("!!array!!"),
			 		"str_checked":"no"
				};
		
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						searchRs();
					}
				);
		
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//���õ�¼��⡾��ѡ��
		function resetDljcChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var xh = new Array();//ѧ��
				var i = 0;			
				jQuery("input[name=primarykey_checkVal]:checked").each(function(){
					xh[i] = jQuery(this).val();
					i++;
				});
				
				var url = "general_xsxx_dljc.do?method=resetDljc";
				
				//����
			 	var parameter = {
			 		"array_pkValue":xh.join("!!array!!"),
			 		"str_checked":"yes"
				};
		
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						searchRs();
					}
				);
		
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//������Ϣ����
		function showDljcSetting(){
			var url = "general_xsxx_dljc.do?method=dljcSetting";
			showTopWin(url,"800","600");
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_xsxx" method="post">
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
								<a href="#" onclick="resetDljc();return false;" id="btn_csh" class="btn_csh">
									���ü��
								</a>
							</li>
							<li>
								<a href="#" onclick="showDljcSetting();return false;" id="btn_sz" class="btn_sz">
									�����ֶ�����
								</a>
							</li>
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
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