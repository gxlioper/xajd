<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
		
			var czxm = $("shxm").value;
			
			if(czxm == ""){
				var id = "left_a_0";
				if($(id)){
					$(id).click();
				}
			}else{
				var click_li = $("click_li").value;
				var id = "left_a_"+click_li;
				$(id).click();
			}
		}
		
		function getShgwInfo(){
		
			if($('shgw').value == ""){/*
			
				var czxm = $("shxm").value;
				var url="rcsw_qjgl.do?method=getShgwInfo";
					url+="&czxm="+czxm;
				
				jQuery.ajax({
					type:'post',
					url:url,
					dataType:'json',
					async: false,
					success:function(result){
						if(result.length==1){
							$('shgw').value=result[0].gwid;
							searchRs();
						}else{
							$("p_gwxx").innerHTML="";
							
							var html = "";
							for(var i=0;i<result.length;i++){
								var gwid = result[i].gwid;
								var gwmc = result[i].gwmc;
								
								html+="<input type=\"radio\" name=\"rad_gwid\" value=\""+gwid+"\" onclick=\"$('hid_gwid').value=this.value\"/>";
								html+=gwmc;
								html+="<br/>";
							}
							
							html+="<input type=\"hidden\" id=\"hid_gwid\" value=\"\"/>";
							$("p_gwxx").innerHTML=html;
							
							tipsWindown("ϵͳ��ʾ","id:div_gwxx","350","250","true","","true","id");
						}
					}
				});*/
				var czxm = $("shxm").value;
				var url="rcsw_qjgl.do?method=getShgwInfo";
					url+="&czxm="+czxm;
				showDialog("", 300, 200, url);
			} else {
				searchRs();
			}
		}
		
		//��ѯ�����
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMyshList";
			var czxm = $("shxm").value;
			var shgw = $("shgw").value;
			var ie = "ie";

			var otherValue = [czxm,shgw,ie,stylePath];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		//�ж�������
		function judgeShqk(){
			var num = jQuery("input[name=checkVal]:checked").length;
				
			if(num == 0||num > 1){
				alertError("�빴ѡһ����ϣ����˵ļ�¼");
				flag = false;
			}else if(num == 1){
				
				var sqid = jQuery("input[name=checkVal]:checked")[0].value;
				var shgw = $("shgw").value;
				
				var url = "rcsw_qjgl.do?method=myshDetail";
					url+= "&sqid="+sqid;
					url+= "&shgw="+shgw;
					
				showDialog('',800,500,url);
				
			}else{
				tipsWindown("ϵͳ��ʾ","id:div_shxx","350","250","true","","true","id");
			}		
		}
		
		//�������״̬
		function saveShzt(){
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("�빴ѡ��ϣ����˵ļ�¼");
				flag = false;
			}	
			
			var shzt = $("shzt").value;
			var shgw = $("shgw").value;
			var pk = new Array();
			var n=0;
			
			for(var i=0;i<num;i++){
				var obj = jQuery("input[name=checkVal]:checked")[i];
				pk[n] = obj.value;
				n++;
			}
			
			var url="rcsw_qjgl.do?method=saveShzt";
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
				
			//����
		 	var parameter = {
				"shzt":escape(shzt),
				"shgw":shgw,
				"pk":pk.join("!!@@!!")
			};
			
			jQuery.post(url,parameter,function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
			});
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
				<em>���ĵ�ǰλ�ã�</em><a>�ճ����� - ��ٹ��� - �ҵ����</a>
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
				1.���ݽ�չ����Ҫ��˵������¼��������һ�������ͨ���ļ�¼��</br>
				2.��ѡ������¼�����<font color="blue">���</font>��ť����չ����ϸҳ����е�����¼��ˡ�</br>
				3.��ѡ������¼�����<font color="blue">���</font>��ť����ִ��������˲�����</br>
				4.�������һ�˶�ڵĻ����ڿ�ʼ��ѯǰ����Ҫ��ָ�����Ժ�����ݽ�����ˡ�
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �Ƿ��ʼ��  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- �Ƿ��޸� -->
			<input type="hidden" id="had_edit" value="no"/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<html:hidden property="shgw" styleId="shgw" />
			<input type="hidden" name="shzt" id="shzt" value=""/>
			
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_js">
							<a href="#" onclick="refreshForm('rcsw_qjgl_mygz_tea.do');return false;" class="btn_fh">
								����
							</a>
						</li>
						<li id="li_js">
							<a href="#" onclick="judgeShqk();return false;" class="btn_sh">
								���
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
		
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end-->
			
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
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									&nbsp;
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">	
								<div class="titlelist" style="height: 380px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');getShgwInfo();return false;">
															<input type="hidden" id="click_li" value="${index}"/>
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');getShgwInfo();return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:380px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
					
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<!-- ��λ��Ϣ������ -->
			<div id="div_gwxx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��λ��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									���ĸ�λ
								</th>
								<td>
									<p id="p_gwxx" style="height: 80px">
									
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="$('shgw').value=$('hid_gwid').value;searchRs();closeWindown();">
											ȷ ��
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��λ��Ϣ������ -->
			<div id="div_shxx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>���״̬</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									���״̬
								</th>
								<td>
									<input type="radio" name="rad_shzt" value="ͨ��" onclick="$('hid_shzt').value=this.value" checked="checked"/>ͨ��
									<br />
									<input type="radio" name="rad_shzt" value="��ͨ��" onclick="$('hid_shzt').value=this.value"/>��ͨ��
									
									<input type="hidden" id="hid_shzt" value="ͨ��"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="$('shzt').value=$('hid_shzt').value;saveShzt();closeWindown();">
											ȷ ��
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
		</html:form>
	</body>
</html>