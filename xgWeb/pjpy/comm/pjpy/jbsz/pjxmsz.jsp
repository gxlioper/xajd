<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pjpy/comm/pjpy/jbsz/pjryqd.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/ryqdDWR.js"></script>
		<script language="javascript">
			function xmszWh(doType){
				showTopWin("pjpyXmsz.do?method=pjxmszOne&doType=add","800","600");
			}
			function xmszWhFlow(doType){
				showTopWin("pjpyXmsz.do?method=pjxmszFlow&doType=add","1000","650");
			}
			function saveSfqy(){
				var blog=false;
				if(confirm("ȷ��Ҫ���б��������")){
					refreshForm('pjpyXmsz.do?method=pjxmsz&doType=save');
				}
			}
			
			function changeSfqy(value,num){
				var id = "sfqy"+num;
				$(id).value = value;
			}
			
			
			function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
					return true;
				}else{
					alertInfo('��ѡ��Ҫ�޸ĵ������У�');
					return false;
				}
			}
			
			function showXmfz(){
				if($("xmfzdiv")){
					viewTempDiv('��Ŀ����','xmfzdiv',220,230);
				}else{
					alertInfo("��ѡ��Ҫ�����ļ�¼��");
					return false;
				}
			}
			
			function fzlnxm(){
				if($("select_xn") && $("select_xn").value==""){
					alertInfo("�踴����Ŀѧ�겻��Ϊ��!");
					return false;
				}
				if($("select_xq") && $("select_xq").value==""){
					alertInfo("�踴����Ŀѧ�ڲ���Ϊ��!");
					return false;
				}
				if($("select_nd") && $("select_nd").value==""){
					alertInfo("�踴����Ŀ��Ȳ���Ϊ��!");
					return false;
				}
				refreshForm('pjpyXmsz.do?method=pjxmsz&doType=fzxm');
			}
			
			function removeOption(){
				$("pjxn").options[0]=null;
				$("pjxq").options[0]=null;
				$("pjnd").options[0]=null;
			}
		</script>
	</head>
	<body onload="removeOption()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��������-�����ۺ�����-������Ŀ����</a>
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
		
		
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����<br/>
				<span id="div_help" style="display: none">
				1.��ģ�������������Ŀ�����ӡ��޸ġ�ɾ���͸��ƵĹ��ܡ�<br/>
				2.�޸ĺ�ɾ���Ĺ��ܽ���ѡ�������Ϊ��ǰ��������ʱ�ſ��š�
				</span>
			</p>
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
		
		<!-- ���� end-->
		<html:form action="/pjpyXmsz">
			<!-- ������ -->
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						     <li>
								<a href="#" onclick="xmszWhFlow('add')" class="btn_zj">����</a>
							</li>
							<!--  <li>
								<a href="#" onclick="xmszWh('add')" class="btn_zj">����</a>
							</li>-->
						<logic:present name="showDqpj">
							<li>
								<a href="#" onclick="modi('/xgxt/pjpyXmsz.do?method=pjxmszUpdate&doType=update')" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="dataBatch('/xgxt/pjpyXmsz.do?method=pjxmsz&doType=delete');"
									class="btn_sc">ɾ��</a>
							</li>
						
<%--							<li>--%>
<%--								<a href="#" onclick="saveSfqy()" class="btn_ccg">����</a>--%>
<%--							</li>--%>
						</logic:present>
							<li>
								<a href="#" onclick="showXmfz()" class="btn_fz">������Ŀ</a>
							</li>
						
					</ul>
				</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="document.forms[0].go.value='go';refreshForm('/xgxt/pjpyXmsz.do?method=pjxmsz&doType=query')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									����ѧ��
								</th>
								<td>
									<html:select property="pjxn" styleId="pjxn" style="width: 150px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									����ѧ��
								</th>
								<td>
									<html:select property="pjxq" styleId="pjxq" style="width: 150px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									�������
								</th>
								<td >
									<html:select property="pjnd" styleId="pjnd" style="width: 150px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��Ŀ����
								</th>
								<td >
									<html:select property="xmlx" styleId="xmlx" style="width: 150px">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:select property="xmxz" styleId="xmxz"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��Ŀ��Χ
								</th>
								<td>
									<html:select property="xmfw" styleId="xmfw"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:text property="xmdm" styleId="xmdm" style="width: 150px"/>
								</td>
								<th>
									��Ŀ����
								</th>
								<td align="left">
									<html:text property="xmmc" styleId="xmmc" style="width: 150px"/>
								</td>
								<th align="right">
									Ӣ������
								</th>
								<td align="left">
									<html:text property="ywmc" styleId="ywmc" style="width: 150px"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" id="rsTable" class="dateline" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" style="display:none" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<td nowrap="nowrap">
											<input type="checkbox" name="primarykey_cbv" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
											<input type="hidden" name="xmdms" id="xmdms"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="9">
											<td align="left"  nowrap="nowrap">
												<bean:write name="v" />
											</td>
										</logic:iterate>
										
										<!-- �Ƿ����� -->
										<logic:iterate id="v" name="s" offset="10" length="1">
											<td align="left"  nowrap="nowrap">
												<logic:iterate name="sfqyList" id="sfqy">
													${sfqy.en }
													<logic:equal name="sfqy" property="en" value="${v }">
														<input type="radio" value="${sfqy.en }" name="qy${index }"
															checked="checked" onclick="changeSfqy('${sfqy.en }','${index }')"/>
													</logic:equal>
													<logic:notEqual name="sfqy" property="en" value="${v }">
														<input type="radio" value="${sfqy.en }" name="qy${index }" 
															onclick="changeSfqy('${sfqy.en }','${index }')"/>
													</logic:notEqual>

												</logic:iterate>
												<input type="hidden" name="sfqy" id="sfqy${index }" value="${v }" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
							<!--���� end-->
						</table>
						</div>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=pjpyXmszForm"></jsp:include>

						<!--��ҳend-->
				</logic:notEmpty>
			</div>
			<!-- ��ѯ��� end-->
			
			
			<!-- ������ -->
			<div id="xmfzdiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>�踴����Ŀ��<br/>������ѧ�꣺
								</th>
								<td>
									<html:select property="select_xn" styleId="select_xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>�踴����Ŀ��<br/>������ѧ�ڣ�
								</th>
								<td>
									<html:select property="select_xq" styleId="select_xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>�踴����Ŀ��<br/>��������ȣ�
								</th>
								<td>
									<html:select property="select_nd" styleId="select_nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align='right'>
									<div class="bz">"<font color="red">*</font>"Ϊ������</div>
									<button type="button" onclick='fzlnxm()'>
										����
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<%@ include file="/comm/delMessage.jsp"%>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alertInfo('�����ɹ���');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
