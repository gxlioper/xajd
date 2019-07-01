<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//ˢ��ҳ��
		function reflashFivesDetail(){
			
			var xh = $("xh").value;
			var url = "szgyyq_mypj_tea.do?method=fivesDetail";
				url+="&xh="+xh;
				
			refreshForm(url)
		}
		
		//����5S��
		function addFives(){

			var div_id = "div_fives";
			
			if($(div_id)){
				
				var add_num = parseInt($("add_num").value)+1;
				
				var divHtml = $(div_id).innerHTML;
					divHtml+="<table class=\"dateline\" style=\"width: 100%\"\">";
					divHtml+="<tbody>";
					divHtml+="<tr>";
					divHtml+="<td align=\"left\" style=\"width:5%\"><input type='checkbox' name=\"checkVal\" value=\"no\"/></td>";
					divHtml+="<td align=\"left\" style=\"width:25%\"><select name=\"fzxm\">"+$("sel_fzxm").innerHTML+"</select></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\"><select name=\"jjf\">"+$("sel_jjf").innerHTML+"</select></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+add_num+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\"><input type=\"text\" name=\"jfrq\" id=\"jfrq_"+add_num+"\" style=\"width:60px\" onclick=\"return showCalendar('jfrq_"+add_num+"','ymmdd');\" readOnly=\"true\" /></td>";
					divHtml+="<td align=\"left\" style=\"width:20%\"><select name=\"yy\" onchange='otheryy(this);'>"+$("sel_yy").innerHTML+"</select><input type='hidden' name='jfyy' value=''/></td>";
					divHtml+="<td align=\"left\" style=\"width:10%\">0</td>";
					divHtml+="<td align=\"left\" style=\"width:10%\">0</td>";
					divHtml+="</tr>";
					divHtml+="</tbody>";
					divHtml+="</table>";
					
				$(div_id).innerHTML = "";
				$(div_id).innerHTML = divHtml;
				
				$("add_num").value = add_num;
			}
		}
		
		//����5S��
		function saveFives(){
			var flag = true;
		
			var sqf_num = jQuery("input[name=sqf]").length;
			var jfrq_num = jQuery("input[name=jfrq]").length;
			
			if(flag){
				for(var i=0;i<sqf_num;i++){
					var obj = jQuery("input[name=sqf]")[i];
					if(obj.value == ""){
						alertError("��ֵ����Ϊ�գ���ȷ��");
						flag = false;
					}
				}
			}
			
			if(flag){
				for(var i=0;i<jfrq_num;i++){
					var obj = jQuery("input[name=jfrq]")[i];
					if(obj.value == ""){
						alertError("���ڲ���Ϊ�գ���ȷ��");
						flag = false;
					}
				}
			}
			
			if(flag){
			
				var url="szgyyq_mypj_tea.do?method=saveFives";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//��ֵ��Ŀ
				var i = 0; 
				var fzxm = new Array();   	
				
				var fzxm_num = document.getElementsByName("fzxm").length;
				for(i=0;i<fzxm_num;i++){
					fzxm[i]=document.getElementsByName("fzxm")[i].value;
				}

				//ID
				i = 0;
				var id = new Array();   				  
				jQuery("input[name=checkVal]").each(function(){id[i] = jQuery(this).val();i++;});
				
				//�Ӽ���
				i = 0;
				var jjf = new Array();   				  
				jQuery("select[name=jjf]").each(function(){jjf[i] = escape(jQuery(this).val());i++;});
				
				//�����
				i = 0;
				var sqf = new Array();   				  
				jQuery("input[name=sqf]").each(function(){sqf[i] = jQuery(this).val();i++;});
				
				//����
				i = 0;
				var jfrq = new Array();   				  
				jQuery("input[name=jfrq]").each(function(){jfrq[i] = jQuery(this).val();i++;});
				
				//ԭ��
				i = 0;
				var yy = new Array();   				  
				jQuery("select[name=yy]").each(function(){
					if(id[i] == "no"){
						yy[i] = jQuery(this).val();
					}else{
						yy[i] = " ";
					}
					i++;
				});

				//�ӷ�ԭ��
				i = 0;
				var jfyy = new Array();   				  
				jQuery("input[name=jfyy]").each(function(){
					if(jQuery(this).val() != ""){
						jfyy[i] = escape(jQuery(this).val());
					}else{
						jfyy[i] = " ";
					}
					i++;
				});

				//ѧ��
				var xh = $("xh").value;

				//����
			 	var parameter = {
					"xh":xh,
					"id":id.join("!!@@!!"),
					"fzxm":fzxm.join("!!@@!!"),
					"jjf":jjf.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!"),
					"jfrq":jfrq.join("!!@@!!"),
					"yy":yy.join("!!@@!!"),
					"jfyy":jfyy.join("!!@@!!")
				};

				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//ɾ��5S��
		function delFives(){
		
			var flag = true;
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("�빴ѡϣ��ɾ���ļ�¼");
				return false;
			}
			
			if(flag){
			
				var url="szgyyq_mypj_tea.do?method=delFives";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//ID
				var i = 0;
				var id = new Array();   				  
				jQuery("input[name=checkVal]:checked").each(function(){id[i] = jQuery(this).val();i++;});
				
				//����
			 	var parameter = {
					"id":id.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//ִ�гɹ�
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){if(tag == "ok"){reflashFivesDetail();}});
		}
		
		//��ʾ�޸ķ�ֵ
		function showEditFz(obj){
			
			var id = obj.value;
			var sqf_id = "sqf_"+id;
			var p_id = "p_"+id;
			
			if(obj.checked){
				$(sqf_id).style.display = "";
				$(p_id).style.display = "none";
			}else{
				$(sqf_id).style.display = "none";
				$(sqf_id).value = $(p_id).innerHTML;
				$(p_id).style.display = "";
			}
		}
		//����ԭ��ʱ��Ҫ��д����ԭ�򣬷Ǳ���
		var curr_yy_obj;
		function otheryy(obj){
			if(obj.value=="other"){
				curr_yy_obj=obj;
				tipsWindown("����ԭ��","id:tempDiv","350","200","true","","true","id");
			}else{
				curr_yy_obj=null;
				obj.nextSibling.value="";
			}
		}
		//������ԭ�򱣴���ҳ����
		function temp_save_other_yy(){
			curr_yy_obj.nextSibling.value=$("other_yy").value;
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ��� - 5S��ά��</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
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
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				2.����ͨ��<font color="blue">����</font>��<font color="blue">ɾ��</font>��ά��5S�֡�</br>
				3.���ԭ���б���û������Ҫ��ԭ����ѡ��<font color="blue">����</font>��</br>
				4.�������ԭ��Ϊ�����Ļ��������ɲ鿴<font color="blue">��ϸ</font>��</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szgyyq_mypj_tea" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ��ֵ��Ŀ -->
			<select id="sel_fzxm" style="display: none">
				<option value="grszf">�������ʷ�</option>
				<option value="jsssf">����������5S��</option>
				<option value="ktf">����5S��</option>
				<option value="cxf">���ŷ�</option>
				<option value="qtf">������</option>
			</select>
			<!-- �Ӽ��� -->
			<select id="sel_jjf" style="display: none">
				<option value="�ӷ�">�ӷ�</option>
				<option value="����">����</option>
			</select>
			<!-- ��ֵ��Ŀ -->
			<select id="sel_yy" style="display: none;">
				<logic:notEmpty name="yyList">
					<logic:iterate name="yyList" id="yyMap">
						<option value="${yyMap.dm }">${yyMap.mc }</option>
					</logic:iterate>
						<option value="other">����</option>
				</logic:notEmpty>
			</select>
			<!-- ��������  -->
			<input type="hidden" id="add_num" value="0" />
			
			<input type="hidden" id="xh" name="xh" value="${xh }" />
			<!-- ˢ��  -->
			<button type="button" id="btn_sx" onclick="reflashFivesDetail()" style="display:none">
				ˢ��
			</button>
			<!-- ������ end-->
			
			<!-- ѧ��������Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">ѧ��</th>
						<td width="30%">
							<input type="hidden" name="xh" value="${stuInfo.xh }" />
							${stuInfo.xh }
						</td>
						<th width="20%">����</th>
						<td width="30%">
							${stuInfo.xm }
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							${stuInfo.xb }
						</td>
						<th>�༶</th>
						<td>
							${stuInfo.bjmc }
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div>
								<button type="button" onclick="addFives()" id="btn_add">����</button>
								<button type="button" onclick="delFives()">ɾ��</button>
							</div>
							<br/>
							<div style="height:300px;overflow-x:auto;overflow-y:auto;">
								<table class="dateline" width="100%">
									<!-- ���� -->
							    	<thead>
										<tr>
											<td style="width: 5%">
												<input type="checkbox" id="selall"name="selall" onclick="selAll()" />
											</td>
											<td style="width: 25%">
												��ֵ��Ŀ
											</td>
											<td style="width: 10%">
												�Ӽ���
											</td>
											<td style="width: 10%">
												��ֵ
											</td>
											<td style="width: 10%">
												����
											</td>
											<td style="width: 20%">
												ԭ��
											</td>
											<td style="width: 10%">
												<bean:message key="lable.xb" />���
											</td>
											<td style="width: 10%">
												ѧУ���
											</td>
							      		</tr>
									</thead>
									<!-- ���� end-->
								
									<logic:iterate name="infoList" id="info">
										<tr>
											<!-- ���޸� -->
											<logic:equal name="info" property="shzt" value="yes">
												<td style="width: 5%">
													<input type="checkbox" name="checkVal" value="${info.id }" onclick="showEditFz(this);"/>
												</td>
												<td style="width: 25%">
													${info.fzxm }
													<input type="hidden" name="fzxm" value="123"/>
<%--													<select name="fzxm" style="display: none">--%>
<%--														<option value="${info.fzxm }">${info.fzxm }</option>--%>
<%--													</select>--%>
												</td>
												<td style="width: 10%">
													${info.jjf }
													<select name="jjf" style="display: none">
														<option value="${info.jjf }">${info.jjf }</option>
													</select>
												</td>
												<td style="width: 10%">
													<p id="p_${info.id }">${info.sqf }</p>
													<input type="text" name="sqf" 
														id="sqf_${info.id }" 
														value="${info.sqf }" 
														onkeydown="return onlyNum(this,3)" 
														onmousedown="return onlyNum(this,3)" 
														maxlength="3" 
														style="width : 30px;ime-mode:disabled;display: none"/>
												</td>
												<td style="width: 10%">
													${info.jfrq }
													<input type="text" name="jfrq" value="${info.jfrq }" style="display: none"/>
												</td>
												<td style="width: 20%">
													<logic:notEmpty name="info" property="yy">
														${info.yy }
													</logic:notEmpty>
													<logic:empty name="info" property="yy">
														<a href="#" onclick="tipsWindown('����ԭ��','id:tempDiv','350','200','true','','true','id');$('other_yy').value='${info.jfyy }';$('btn_bc').style.display='none';return false;"><font color="blue">����</font></a>
													</logic:empty>
													<input type='hidden' name='jfyy' id="jfyy_${info.id }" value='${info.jfyy }'/>
													<select name="yy" style="display:none">
														<option value="${info.yy }">${info.yy }</option>
													</select>
												</td>
												<td style="width: 10%">
													${info.xyshf }
												</td>
												<td style="width: 10%">
													${info.xxshf }
												</td>
											</logic:equal>
											<!-- �����޸� -->
											<logic:equal name="info" property="shzt" value="no">
												<td style="width: 5%">
													<input type="checkbox" disabled="disabled"/>
												</td>
												<td style="width: 25%">
													${info.fzxm }
												</td>
												<td style="width: 10%">
													${info.jjf }
												</td>
												<td style="width: 10%">
													${info.sqf }
												</td>
												<td style="width: 10%">
													${info.jfrq }
												</td>
												<td style="width: 20%">
													${info.yy }
												</td>
												<td style="width: 10%">
													${info.xyshf }
												</td>
												<td style="width: 10%">
													${info.xxshf }
												</td>
											</logic:equal>
										</tr>
									</logic:iterate>
								</table>
								<div id="div_fives">

								</div>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- ���� -->
								<button type="button" onclick="saveFives();">
									<bean:message key="lable.btn_bc_space" />
								</button>
								<!-- �ر�-->
								<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ѧ��������Ϣ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">����дԭ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_bz">
								<th style="width:30%">
									ԭ��<br />
									<font color="red"><��250��></font>
								</th>
								<td>
									<textarea id="other_yy" rows="5"  cols="30"
									style="word-break:break-all;width:100%" onblur="chLeng(this,250)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="����" id="btn_bc"  onclick="temp_save_other_yy();closeWindown()">
											ȷ �� 
										</button>
										<button type="button" name="ȡ��"  onclick="closeWindown();return false;">
											ȡ ��
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