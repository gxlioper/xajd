<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			function initRssz(){
				var xmmc = jQuery('#xmdm option:selected').text();
				confirmInfo('��ȷ��Ҫ��ʼ����Ŀ"'+xmmc+'"������������?',function(type){
					if ('ok' == type){
						var xmdm = jQuery('#xmdm').val();
						var url = "pjpy_comm_rssz.do?method=initData";
						
						jQuery.post(url,{xmdm:xmdm},function(data){
							if ('true' == data){
								ymPrompt.alert({message:'�������ó�ʼ���ɹ�!',handler:function(d){
									if ('ok' == d){
										jQuery('#search_go').click();
									}
								}})
							} else {
								alertInfo('�������ó�ʼ��ʧ��!');
							}
							
						})
					}
				});
			}

			function plsz(){
				if (isChecked('primarykey_cbv')){
					tipsWindown("ϵͳ��ʾ","id:rsszDiv","350","160","true","","true","id");
				}
			}
			
			function rsszSave(){
				var bl = jQuery('#bl').val();
				var fpfs = jQuery('input[type=radio][name=fpfs]:checked').val()
			
				confirmInfo('��ȷ��Ҫ������?',function(type){
					if ('ok' == type){
						refreshForm('pjpy_comm_rssz.do?method=saveRssz&fpfs='+fpfs+'&bl='+bl);
					}
				})
			}
			
			
			function szfwSearch(){
				var url = "/xgxt/pjpy_comm_rssz.do";
				var szfw = $('szfw').value;

				if('xy' == szfw){
					url += "?method=rsszForSzfw&szfw=xy" ;
				}else if('zy' == szfw){
					url += "?method=rsszForSzfw&szfw=zy";
				}else if('bj' == szfw){
					url += "?method=rsszForSzfw&szfw=bj";
				}else{
					url += "?method=rsszForSzfw&szfw=nj";
				}
				
				
				allNotEmpThenGo(url);
			}
			
			function saveQdrs(){
				var qdrs = document.getElementsByName('qdrs')
				if( qdrs != null && qdrs.length>0){
					confirmInfo('��ȷ��Ҫ������?',function(type){
						if ('ok' == type){
							refreshForm('/xgxt/pjpy_comm_rssz.do?method=saveQdrs');	
						}
					})
				}else {
					alertInfo('������ѯ����дȷ�������󱣴棡');
				}
			}
			
			function setMrrs(){
				var checkbox = document.getElementsByName('primaryKey_cbv');
				var mrrs = document.getElementsByName('mrrs');
				var qdrs = document.getElementsByName('qdrs');
				var count = $('count').value;
				var syrs = $('syrs').value;
					
				var count_now = 0;
				var temp = 0;
				for(var i=0;i<checkbox.length;i++){
					if(checkbox[i].checked == true){
						temp = mrrs[i].value == "" ? 0 : eval(mrrs[i].value);
					}else{
						temp = qdrs[i].value == "" ? 0 : eval(qdrs[i].value);
					}
					count_now += temp;										
				}
				
				var cz = count_now - count;
				
				if(cz<=syrs || syrs == -1){				
					batchData('primarykey_cbv','/xgxt/pjpy_comm_rssz.do?method=saveQdrsFromMrrs','tb')
				}else{
					alertInfo('��������Ŀ�������ޣ�');
				}
			}
			
			function skipNext(obj){
			
				var currIndex = jQuery(obj).parents("tr").index();
				
				if(event.keyCode==13 || event.keyCode==40){
					currIndex++;
				} else if (event.keyCode==38){
					currIndex--;
				}
				var tr = jQuery('#dataTab tr:eq('+currIndex+')');
				tr.click();
				jQuery('input[name=qdrs]',tr).focus();
			}
			
			function calculateQdrs(obj){
				checkInputData(obj);
				
				var syrs = eval($('syrs').value);
				var count = eval($('count').value);
				var dqrs = obj.value;
				
				var qdrs = document.getElementsByName('qdrs');
				
				var count_now = 0;
				if(qdrs !=null){
					for(var i=0;i<qdrs.length;i++){
						var temp = qdrs[i].value == "" ? 0 : eval(qdrs[i].value);
						
						count_now += temp;
					}
				}
				
				var cz = count_now - count;
				
				if(cz<=syrs){				
					$('count').value = count_now;
					$('syrs').value = syrs-cz;
						if($('span_syrs')!=null)
					$('span_syrs').innerHTML = $('syrs').value;
				}else if(syrs != -1){
					alertInfo('��������Ŀ�������ޣ�');
					obj.value = "";
				}
			}
			
			function showTj(){
				var szfw = $('szfw').value;
				if("xy" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').disabled = false;
					$('zy').value = ""
					$('zy').disabled = true;
					$('bj').value = ""
					$('bj').disabled = true;
					}
				}else if("zy" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').disabled = false;
					$('zy').disabled = false;
					$('bj').value = ""
					$('bj').disabled = true;
					}
				}else if("bj" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').disabled = false;
					$('zy').disabled = false;
					$('bj').disabled = false;
					}
				}else if("nj" == szfw){
					if($('xy')!=null||$('zy')!=null||$('bj')!=null){
					$('xy').value = ""
					$('xy').disabled = true;
					$('zy').value = ""
					$('zy').disabled = true;
					$('bj').value = ""
					$('bj').disabled = true;
					}
				}
				
			}
			
			function choiceKzfw(){
				var xmdm = $('xmdm').value;
				var kzfw = $(xmdm+'_kzfw').value;
				
				$('szfw').value = kzfw;
				$('sel_szfw').value = kzfw;
				
				showTj();
				
				var rssz = jQuery('#rssz').val();
				if(rssz!='true'){
					jQuery('#search_go').click();
				}
				
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
	<body onload="choiceKzfw();">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��������-�����ۺ�����-��������</a>
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
			 1.�� ʼ ���������ǰ��ѡ��Ŀ��ص��������á�<br/>
		     2.����������ü����������С�����ڼ�λ������������Ĭ��������<br/>
		                  �磺���������Ϊ12.45����С������һλ������������Ĭ������Ϊ12����С�����ڶ�λ�����Ĭ������Ϊ13��<br/>
		     3.�������ã�ѡ���¼���ñ�����ϵͳ�Զ������Ĭ��������<br/>
		     4.����Ĭ��������ѡ���¼����ȷ������Ϊϵͳ�Զ��������Ĭ��������<br/>
		     5. ���棺�ֹ�������ȷ�������󱣴档<br/>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					
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
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_sjsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function60.png" />
							<p>ʱ������</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		<html:form action="/pjpy_comm_rssz" method="post">
			<input type="hidden" id="message" value="${message}" />
			<input type="hidden" name="lastXmdm" value="${lastXmdm }" />
			<input type="hidden" name="lastSzfw" value="${lastSzfw }" />
			<input type="hidden" id="syrs" value="${syrs }" />
			<input type="hidden" id="count" value="${count }" />

			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />

			<input type="hidden" id="szfw" name="szfw" value="" />
			<input type="hidden" id="rssz" name="rssz" value="${rssz }" />

			<input type="hidden" id="doType" name="doType" value="${doType }" />
			
			<logic:iterate id="pjxmMap" name="pjxmList">
				<input type="hidden" id="${pjxmMap.xmdm }_kzfw"
					value="${pjxmMap.kzfw }" />
			</logic:iterate>


				<logic:equal value="true" name="kfsz">
					<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li>
									<a href="#" class="btn_csh" onclick="initRssz();return false;">��ʼ��</a>
								</li>
								<li>
									<a href="#" class="btn_sz"
										onclick="showTopWin('/xgxt/pjpy_comm_rssz.do?method=rsszCssz',500,300);return false;">�������</a>
								</li>
								<li>
									<a href="#" class="btn_sz" onclick="plsz();return false;">��������</a>
								</li>
								<li>
									<a href="#" class="btn_sx" onclick="setMrrs();return false;">����Ĭ������</a>
								</li>
								<li>
									<a href="#" class="btn_ccg" onclick="saveQdrs();return false;">��������</a>
								</li>
							</ul>
						</div>
					</div>
				</logic:equal>

			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td>
								<html:select property="xmdm" styleId="xmdm" style="width:180px"
									onchange="choiceKzfw();szfwSearch();">
									<html:options collection="pjxmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
							<th>
								���÷�Χ
							</th>
							<td>
								<select id="sel_szfw" disabled="disabled" style="width: 180px">
									<option value="xy">
										<bean:message key="lable.xb" />
									</option>
									<option value="zy">
										רҵ
									</option>
									<option value="bj">
										�༶
									</option>
									<option value="nj">
										�꼶
									</option>
								</select>
							</td>
							<th>
								�꼶 
							</th>
							<td> 
								<html:select property="nj" styleId="nj" 
											 style="width: 180px"
											 onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<html:select property="xydm" styleId="xy" style="width: 180px"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" styleId="zy" style="width: 180px"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" styleId="bj" style="width: 180px"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="szfwSearch()">
										�� ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="czSearchCond('nj-xy-zy-bj');return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<div class="formbox">
				<logic:empty name="rs">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <font color="red">δ�ҵ��κμ�¼��</font> </span>
					</h3>
				</logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp;
							<font color="blue">������ͷ��������;
								<logic:equal value="-1" name="syrs">����Ŀ����������;</logic:equal>
							</font>
							<logic:greaterEqual value="0" name="syrs">
								<font color="blue">����Ŀδ��������:</font><font id="span_syrs" color="red">${syrs }</font>
							</logic:greaterEqual>
						</span>
					</h3>
					<div class="con_overlfow">
						<table width="99%" id="rsTable" class="dateline tablenowrap">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:equal value="true" name="kfsz">
										<td>
											<input type="checkbox" disabled="true" />
										</td>
									</logic:equal>
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody id="dataTab">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<logic:equal value="true" name="kfsz">
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="checkbox" name="primarykey_cbv" id="pkV"
													${v } value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											</logic:iterate>
										</td>
									</logic:equal>
									<logic:iterate id="v" name="s" offset="1" length="${size-2}">
										<td>
											${v }
										</td>
									</logic:iterate>
									<td>
										<logic:iterate id="v" name="s" offset="${size-1}" length="1">
											<logic:equal value="true" name="kfsz">
												<html:text property="qdrs" value="${v }"
													onblur="calculateQdrs(this);" 
													onkeydown="return onlyNum(this,5);skipNext(this);"
													onmousedown="return onlyNum(this,5)"
													maxlength="5" style="width:50px"></html:text>
											</logic:equal>
											<logic:notEqual value="true" name="kfsz">
										 	${v }
										 </logic:notEqual>
										</logic:iterate>

										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" name="pks" value="${v }" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="${size-2}" length="1">
											<input type="hidden" name="mrrs" value="${v }" />
										</logic:iterate>
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
					</div>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=pjpyRsszForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
			
			
			
			
			
			
			<div id="rsszDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>������������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									���䷽ʽ
								</th>
								<td>
									<html:radio property="fpfs" value="bl" />����������
								</td>
							</tr>
							<tr>
								<th>����</th>
								<td>
									<html:text property="bl" styleId="bl" onblur="checkInputNum(this);chMax(this,100);" maxlength="3"></html:text>%
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
										<button type="button" name="����" onclick="rsszSave()">
											�� ��
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
				<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>
		</html:form>
	</body>
</html>
