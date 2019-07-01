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
			
			var url = "jhzy_knsrd.do?method=searchKnsjg";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//��ϸ
		function showKnsjgDetail(){
		var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len!=1){	
				alertError("��<font color='blue'>��ѡһ��</font>��ϣ���鿴�ļ�¼��");	
				return false;
			}else if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="jhzy_knsrd.do?method=knsjgDetail";
					url+="&pkValue="+pkValue;
					showTopWin(url,800,600);
			}
		}
		
		//չʾ�Ƽ�����DIV
		function showTjdcDiv(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==0){	
				alertError("��<font color='blue'>��ѡ</font>��ϣ���޸ĵļ�¼��");	
				return false;
			}else{	
				tipsWindown("ϵͳ��ʾ","id:div_tjdc","500","200","true","","true","id");
			}
		}
		
		//�����Ƽ�����
		function saveKnsrdTjdc(){
			var url = "jhzy_knsrd.do?method=saveKnsrdTjdc";
			var pkValue=new Array();
			var i=0;
			
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){
				pkValue[i]=jQuery(this).val();
				i++;
			});
			
			var tjdc = jQuery("[name=tjdc]:checked").eq(0).val();
			
			var parameter={};
			parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
			parameter["str_tjdc"]=escape(tjdc);
	
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
		
		function showPrint(){
		
			var n=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n==1){
				var pk=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();

				var pkArr=pk.split("luojw");
				var xn=pkArr[0];
				var xh=pkArr[1];
				showOpenWindow("jhzy_knsrd.do?method=knsrdb&xh="+xh+"&xn="+xn);
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
						<li>
							<a href="#" onclick="showKnsjgDetail();return false;" id="btn_shtg" class="btn_shtg">
								�鿴
							</a>
						</li>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showTjdcDiv();return false;" id="btn_xg" class="btn_xg">
									�޸��Ƽ�����
								</a>
							</li>
							<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
							<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
						</logic:equal>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jhzyXszzKnsrdForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- �Ƽ����ε����� -->
			<div id="div_tjdc" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>�Ƽ�����
								</th>
								<td colspan="3">
									<input type="radio" name="tjdc" value="һ������" checked="checked"/>һ������<br/>
									<input type="radio" name="tjdc" value="�ر�����"/>�ر�����<br/>
									<input type="radio" name="tjdc" value="������"/>������
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveKnsrdTjdc()">����</button>
										<button type="button" onclick="closeWindown();return false;">�ر�</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �Ƽ����ε����� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>