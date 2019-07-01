<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ѯ�����
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getQjlcList";
	
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			var lxmc = $("lxmc").value;
			if(lxmc != ""){
				lxmc = escape(lxmc);
			}else{
				lxmc = " ";
			}
			
			var kfxg = " ";
			
			
			var otherValue = [
				lxmc,
				kfxg
			];
			
			searchRsByAjax(url,otherValue);
		}
		
		//��ʾ��ٹ�����ϸ
		function showQjlcDetail(type){
		
			var id="";
			var flag = false;
			
			if(type == "edit"){
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("&nbsp;&nbsp;�빴ѡ����Ҫά����¼");
					flag = false;
				}else if(num > 1){
					alertError("���ܹ�ѡ������¼���빴ѡһ������Ҫά���ļ�¼");
					flag = false;
				}else{
					id=jQuery("input[name=checkVal]:checked")[0].value;
					flag = true;
				}				
			}else{
				flag = true;
			}

			if(flag){
			
				var url = "/xgxt/rcsw_qjgl.do?method=qjlcDetail";
					url+= "&id="+id;
					showDialog('', 600, 380, url);
				//showTopWin(url,'600','480');	
			}	
		}

		//ɾ���������
		function delQjlc(){
		
				var flag = true;
				
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("�빴ѡ����Ҫɾ���ļ�¼");
					
				}else{	
					confirmInfo('ȷ��Ҫɾ����ѡ��������',function(t){
						if(t=="ok"){
							$("divWaiting").style.display="";
							$("divDisable").style.display="";
							var id = new Array();
							var n=0;
							
							for(var i=0;i<num;i++){
								var obj = jQuery("input[name=checkVal]:checked")[i];
								id[n] = obj.value;
								n++;
							}
								
							var url="rcsw_qjgl.do?method=delQjlc";
							
							//����
						 	var parameter = {
								"id":id.join("!!@@!!")
							};
							
							jQuery.post(url,parameter,function(result){
								$("divWaiting").style.display="none";
								$("divDisable").style.display="none";
								alertInfo(result,function(){searchRs();});
							});	
						}
					});
				}
			
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
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
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.��ģ��ά��������̣����û��ά�����̵Ļ���<font color="blue">����</font>��ѧ�����롣</br>
				2.���ĳ����ٻ�����δ�����ϵļ�¼���򲻿�ɾ�������޸ġ�
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showQjlcDetail('add');return false;" class="btn_zj">
									����
								</a>
							</li>
							<li>
								<a href="#" onclick="showQjlcDetail('edit');return false;" class="btn_xg">
									�޸�
								</a>
							</li>
							<li>
								<a href="#" onclick="delQjlc();return false;" class="btn_sc">
									ɾ��
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
		        			<tr>
		          				<td colspan="6">
		            				<div class="btn">
				              		<button type="button" class="btn_cx" id="search_go" 
					              		onclick="searchRs();return false;">
					              			�� ѯ
					              		</button>
					             		<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
					              			�� ��
					             		</button>
				            		</div>
		          				</td>
		       				</tr>
		     			</tfoot>
		     			<tbody>
			              	<tr>
			              		<th width="10%">�������</th>
			              		<td colspan="5">
			              			<html:text property="lxmc" onkeypress="if(pressEnter(event)){searchRs();return false;}" style="width:150px" styleId="lxmc"/>
								</td>
								
								
			              	</tr>	      		
						</tbody>
					</table>
				</div>
		              	
				<!-- �������� -->
				<div id="div_superSh" style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
					 <script type="text/javascript">
				     $('choose').className="hide";
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