<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMyqjList";
	
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			var xn = $("xn").value;
			if(xn == ""){
				xn = " ";
			}
			
			var xq = $("xq").value;
			if(xq == ""){
				xq = " ";
			}
			
			var kssj = $("kssj").value;
			var jssj = $("jssj").value;
			
			if(kssj!="" && jssj!="" && parseInt(kssj) > parseInt(jssj)){
				alertError("�����뿪ʼʱ�䡿�������ڡ��������ʱ�䡿����ȷ��");
				return false;
			}
			
			if(kssj == ""){
				kssj = " ";
			}
			
			if(jssj == ""){
				jssj = " ";
			}
			
			var otherValue = [xn,xq,kssj,jssj];
			
			searchRsByAjax(url,otherValue);
		}
		
		//��ʾ�ҵ������ϸ
		function showMyqjDetail(type){
		
			var id="";
			var flag = false;
			
			if(type == "edit"){
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("�빴ѡ����Ҫά����¼");
					flag = false;
				}else if(num > 1){
					alertError("���ܹ�ѡ������¼���빴ѡһλ����Ҫά���ļ�¼");
					flag = false;
				}else{
					id=jQuery("input[name=checkVal]:checked")[0].value;
					flag = true;
				}				
			}else{
				flag = true;
			}

			if(flag){
			
				var url = "/xgxt/rcsw_qjgl.do?method=myqjDetail";
					url+= "&id="+id;
					
					showDialog('','800','570',url);	
			}	
		}
		
		//ɾ���ҵ����
		function delMysq(){
			
			
				var num = jQuery("input[name=checkVal]:checked").length;
					
				if(num == 0){
					alertError("�빴ѡ��ϣ��ȡ���������¼");
					flag = false;
					return false;
				}else{
					confirmInfo('ȷ��Ҫȡ��������',function(t){
						if(t=="ok"){
							var id = new Array();
							var n=0;
							
							for(var i=0;i<num;i++){
								var obj = jQuery("input[name=checkVal]:checked")[i];
								id[n] = obj.value;
								n++;
							}
							
							var url = "/xgxt/rcsw_qjgl.do?method=delMyqj";
				
							$("divWaiting").style.display="";
							$("divDisable").style.display="";
								
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
		
		function print(sqid){
			window.open("rcsw_qjgl.do?method=jgcxDetail&doType=print&sqid="+sqid);
		}

		function viewdetail(sqid){
			var url = "rcsw_qjgl_viewdetail.do?sqid="+sqid; 
			showDialog('','800','600',url);
			//showOpenWindow("rcsw_qjgl_viewdetail.do?sqid="+sqid);
		}
		
		//���� ֻ��input
		function resetReadOnly(){
			if($("roInput") && $("roInput").value!=""){
				var roInputArr=$("roInput").value.split("-");
				for(i=0;i<roInputArr.length;i++){
					
					$(roInputArr[i]).readonly=false;
					$(roInputArr[i]).value="";
					$(roInputArr[i]).readonly=true;
				}
			}
		}
		</script>
	</head>
	<body onload="searchRs()" >
		<input type="hidden" name="roInput" id="roInput" value="kssj-jssj"/>
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
				<span>ϵͳ��ʾ��</span>
			</h3>
			<p>
				<span>
				1.����չ�����������������٣���������ʱ�䣬�������С�</br>
				2.������������µ���٣�����<font color="blue">�������</font>��ť��</br>
				3.������������벻����ʱ�����Թ�ѡ��¼��ִ��<font color="blue">ȡ������</font>��ȡ����Щ��ѡ����������¼��</br>
				4.����������޷�ȡ��ĳ�������¼ʱ�������Ǹ������¼�Ѿ���<font color="blue">��˹���</font>��</br>
				5.��������¼��<font color="blue">��ӡ�����</font>��ϵͳ�������һ�������񣬷�������ӡ��
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
								<a href="#" onclick="showMyqjDetail('add');return false;" class="btn_zj">
									�������
								</a>
							</li>
							<li>
								<a href="#" onclick="delMysq();return false;" class="btn_sc">
									ȡ������
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
					             		<button type="button" class="btn_cz" id="btn_cz" onclick="resetReadOnly();searchReset();">
					              			�� ��
					             		</button>
				            		</div>
		          				</td>
		       				</tr>
		     			</tfoot>
		     			<tbody>
			              	<tr>
			              		<th>ѧ��</th>
			              		<td>
			              			<html:select property="xn" style="width: 150px"styleId="xn">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>ѧ��</th>
								<td>
									<html:select property="xq" style="width: 150px"styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
								<th>����ʱ��</th>
								<td>
									<html:text property="kssj" styleId="kssj" style="width: 80px"
										onclick="return showCalendar('kssj','ymmdd');" readonly="true" />
									-
									<html:text property="jssj" styleId="jssj"  style="width: 80px"
										onclick="return showCalendar('jssj','ymmdd');" readonly="true" />
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