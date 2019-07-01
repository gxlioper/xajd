<%@ page language="java" import="java.util.*"  pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript">
	
		function setEdit(obj){
			var td = jQuery(obj);
	        //����һ���ı��������ı����ֵΪ�����ֵ     
	        var input=jQuery("input[type=text]",td); 
	        //
			input.attr('style','display:""');
			//�����ı����ȵ���td�Ŀ��  
			input.width(100); 
			
			td.html(input);
		}
		
		function setSelect(obj){
			var td = jQuery(obj);
	        //����һ���ı��������ı����ֵΪ�����ֵ     
	        var span=jQuery("span",td); 
	        //
			span.attr('style','display:""');
			
			td.html(span);
		}
		
		function saveSjsz(){
			var sqkssj = jQuery('input[name=sqkssj]').length;
			var sqjssj = jQuery('input[name=sqjssj]').length;

			for(var i=0;i<sqkssj;i++){
				var kssj = jQuery('input[name=sqkssj]')[i].value;
				var jssj = jQuery('input[name=sqjssj]')[i].value;
				
				if(kssj != "" && jssj != "" && kssj > jssj){
					alertError("��"+(i+1)+"�����뿪ʼʱ�䲻�������������ʱ�䣬��ȷ��");
					return false;
				}
			}
			
			var shkssj = jQuery('input[name=shkssj]').length;
			var shjssj = jQuery('input[name=shjssj]').length;
			
			for(var i=0;i<shkssj;i++){
				var kssj = jQuery('input[name=shkssj]')[i].value;
				var jssj = jQuery('input[name=shjssj]')[i].value;
				
				if(kssj != "" && jssj != "" && kssj > jssj){
					alertError("��"+(i+1)+"����˿�ʼʱ�䲻��������˽���ʱ�䣬��ȷ��");
					return false;
				}
			}
			
			confirmInfo('��ȷ��Ҫ������?',function(type){
				if ('ok' == type){
					refreshForm('pjpy_ty_sjsz.do?method=sjszSave');
				}
			})
		}
		
		function showPlszWindow(){
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if (checkbox.length == 0){
				alertInfo('�빴ѡ��Ҫ���õļ�¼');
			} else {
				tipsWindown("ϵͳ��ʾ","id:plszDiv","350","300","true","","true","id");
			}
		}
		
		
		function plsz(){
			confirmInfo('��ȷ��Ҫ��������ѡ�еļ�¼��?',function(type){
				if ('ok' == type){
					var temp_sqkssj = jQuery('#temp_sqkssj').val();
					var temp_sqjssj = jQuery('#temp_sqjssj').val();
					var temp_sqkzkg = jQuery('input[type=radio][name=temp_sqkzkg]:checked').val();
					
					if(temp_sqkssj != "" && temp_sqjssj != "" && temp_sqkssj > temp_sqjssj){
						alertError("���뿪ʼʱ�䲻�������������ʱ�䣬��ȷ��");
						return false;
					}
					
					var temp_shkssj = jQuery('#temp_shkssj').val();
					var temp_shjssj = jQuery('#temp_shjssj').val();
					var temp_shkzkg = jQuery('input[type=radio][name=temp_shkzkg]:checked').val();
					
					if(temp_shkssj != "" && temp_shjssj != "" && temp_shkssj > temp_shjssj){
						alertError("��˿�ʼʱ�䲻��������˽���ʱ�䣬��ȷ��");
						return false;
					}
					
					var checkbox = jQuery('input[name=primarykey_cbv]:checked');
					
					for (var i = 0 ; i < checkbox.length ; i++){
						var tr = jQuery(checkbox[i]).parents('tr');
					
						jQuery('input[name=sqkssj]',tr).val(temp_sqkssj);
						jQuery('input[name=sqjssj]',tr).val(temp_sqjssj);
						jQuery('input[type=radio][value='+temp_sqkzkg+']',tr).eq(0).attr('checked',true);
						
						jQuery('input[name=shkssj]',tr).val(temp_shkssj);
						jQuery('input[name=shjssj]',tr).val(temp_shjssj);
						jQuery('input[type=radio][value='+temp_shkzkg+']',tr).eq(1).attr('checked',true);
					}
					closeWindown();
					alertInfo('���óɹ�,�����Ӧ����������������С�');
				}
			})
		}
		//��ʾ��������Ϣ
		function showHelpDiv(){

			if($("div_help")){
				if($("div_help").style.display == "none"){
					$("div_help").style.display = "";
				}else{
					$("div_help").style.display = "none";
				}
			}
			
		}
	</script>
  </head>
  
  <body>
 	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ�ã�</em><a>��������-�����ۺ�����-ʱ������</a>
		</p>
		
		<!-- ���߰��� -->
		<p class="help">
			<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
		</p>
		<!-- ���߰��� end -->
		
		<!-- ��ع��� -->
		<p class="other" style="position:relative;">
			<a href="#" onclick="return false;" 
				onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
				style="display:block;height:30px;">��ع���</a>
		</p>
		<!-- ��ع��� end-->
			
	</div>
	
	<!-- ��ʾ��Ϣ-->
	<div class="prompt">
		<h3>
			<span>�������ڣ�</span>
		</h3>
		<p>
			����ѧ��(${pjxtszModel.pjxn })&nbsp;&nbsp;
			����ѧ��(${pjxtszModel.pjxqmc })&nbsp;&nbsp;
			�������(${pjxtszModel.pjnd })&nbsp;&nbsp;
		</p>
	</div>
	<!-- ��ʾ��Ϣ end-->	
	
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.��ģ����������������Ŀ���������˿��أ���:��ǰʱ��������"��ĿA"�����뿪ʼʱ�����������ʱ��֮�䲢��������ƿ���״̬Ϊ"��"�������Ŀ�����롢���򲻿����롣��˿��ƿ���ͬ��
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_rssz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function25.png" />
							<p>������������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_tjsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function56.png" />
							<p>������������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_jdsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function71.png" />
							<p>��Ŀ�������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_xmsz_tzfwsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function24.png" />
							<p>��Ŀ������Χ����</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		
	
	<html:form action="/pjpy_ty_sjsz" method="post">

			<div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_ccg"
									onclick="saveSjsz();return false;"
									id="btn_dr">��������</a>
							</li>
						
							<li>
								<a href="#" class="btn_sz"
									onclick='showPlszWindow();return false;'
									id="btn_sz">��������</a>
							</li>
						</ul>
					</div>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th width="80px">��Ŀ����</th>
								<td colspan="4">
									<html:text property="xmmc"></html:text>
								</td>
								<td width="16%">
				                <div class="btn">
				                    <button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('pjpy_ty_sjsz.do?method=sjszManage&doType=query')">
										�� ѯ
									</button>
				                  </div>
				                </td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>


			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue"></font>
						</logic:notEmpty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%" >
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<td>��Ŀ����</td>
								<td>���뿪ʼʱ��</td>
								<td>�������ʱ��</td>
								<td>������ƿ���</td>
								<td>��˿�ʼʱ��</td>
								<td>��˽���ʱ��</td>
								<td>��˿��ƿ���</td>
							</tr>
						</thead>
						<tbody id="dataTab">
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="r" indexId="i">
									<tr>
										<logic:iterate id="v" name="r" offset="0" length="1">
											<td>
												<input type="checkbox" id="cbv" name="primarykey_cbv" value="${v }"/>
												<input type="hidden" id="cbv" name="pkValue" value="${v }"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="r" offset="1" length="1">
											<td>
												${v }
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="2" length="1">
											<td>
												<input type="text" name="sqkssj" id="sqkssj${i }" value="${v }" 
													   style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"
												/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="3" length="1">
											<td>
												<input type="text" name="sqjssj" id="sqjssj${i }" value="${v }" 
														 style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="4" length="1">
											<td>
												<logic:equal value="1" name="v">
													<input type="radio"  name="sqkzkg${i }"  value="0"/>��
													<input type="radio"  name="sqkzkg${i }"  value="1" checked="true"/>��
												</logic:equal>
												<logic:notEqual value="1" name="v">
													<input type="radio"  name="sqkzkg${i }"  value="0" checked="true"/>��
													<input type="radio"  name="sqkzkg${i }"  value="1" />��
												</logic:notEqual>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="5" length="1">
											<td>
												<input type="text" name="shkssj" id="shkssj${i }" value="${v }" 
														 style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"
												/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="6" length="1">
											<td>
												<input type="text" name="shjssj" id="shjssj${i }" value="${v }" 
													   style="width:90px"
													   readonly="true" onclick="return showCalendar(this.id,'y-mm-dd')"
												/>
											</td>
										</logic:iterate>
										
										<logic:iterate id="v" name="r" offset="7" length="1">
											<td>
												<logic:equal value="1" name="v">
													<input type="radio"  name="shkzkg${i }"  value="0" />��
													<input type="radio"  name="shkzkg${i }"  value="1" checked="true"/>��
												</logic:equal>
												<logic:notEqual value="1" name="v">
													<input type="radio"  name="shkzkg${i }"  value="0" checked="true"/>��
													<input type="radio"  name="shkzkg${i }"  value="1" />��
												</logic:notEqual>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum")- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
									for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=pjpySjszForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
		
		<div id="plszDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									���뿪ʼʱ��
								</th>
								<td>
									<input type="text" id="temp_sqkssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									�������ʱ��
								</th>
								<td>
									<input type="text" id="temp_sqjssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									������ƿ���
								</th>
								<td>
									<input type="radio"  name="temp_sqkzkg"  value="0" checked="true"/>��
									<input type="radio"  name="temp_sqkzkg"  value="1" />��
								</td>
							</tr>
							<tr>
								<th>
									��˿�ʼʱ��
								</th>
								<td>
									<input type="text" id="temp_shkssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									��˽���ʱ��
								</th>
								<td>
									<input type="text" id="temp_shjssj"
										   style="width:100px" readonly="true" 
										   onclick="return showCalendar(this.id,'y-mm-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									��˿��ƿ���
								</th>
								<td>
									<input type="radio"  name="temp_shkzkg"  value="0" checked="true"/>��
									<input type="radio"  name="temp_shkzkg"  value="1" />��
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="�� ��" onclick="plsz()">
											ȷ ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		<logic:present name="message">
			<script>
				alertInfo("${message}");
			</script>
		</logic:present>
  </body>
</html>
