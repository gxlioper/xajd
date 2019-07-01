<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/zdsz.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript" defer="defer">
		
		//ҳ���ʼ��
		function onShow(){
			
			//ѧ��Ϊ׼
			showXgwz();
			//���������ֶ�
			setTszd();
			//��Դ��
			showLybList();
		}
		
		//��ʾ��ͼ����DIV
		function showViwDiv(){
			viewTempDiv("��ͼ����","viewDiv",300,100);
		}
		
//�������ֶ�
function setTszd(){
	var num = document.getElementsByName("zd").length;
	var notNull = ["xh","nj","xydm","zydm","bjdm"];
	
	for(var i=0;i<num;i++){
		var obj = document.getElementsByName("zd")[i];
		var rownum = obj.id.replace("zd","");
		 
		var zd = obj.value;	
		
		for(var j=0;j<notNull.length;j++){
			if(zd == notNull[j]){
				setWkxz(rownum);
			}
		}
		
		setQt(zd,rownum);
	}
}

//����Ϊ������
function setWkxz(num){
	var id = "wkxz"+num;
	if($(id)){
		for(var i = 0 ; i <$(id).options.length; i++){
			if($(id).options[i].value == "����Ϊ��"){
				$(id).options[i] = null;
				break;
			}
		}
	}
}

//���������ֶ������ʽ
function setQt(zd,num){

	var bm_arr = ["nj","xydm","zydm","bjdm"];
	var qx_arr = ["syd","jg","hkszd"];
	
	for(var j=0;j<bm_arr.length;j++){
		if(zd == bm_arr[j]){
			var lrxs_id = "lrxs"+num;
			DWRUtil.removeAllOptions(lrxs_id);
			var options = [{dm:"�����ʽ",mc:"�����ʽ"}];
			DWRUtil.addOptions(lrxs_id,options,"dm","mc");
		}
	}
	
	var num_id = "hid_lybnum_"+num;
	
	if($(num_id).value == 0){
	
		var lrxs_id = "lrxs"+num;
		for(var i = $(lrxs_id).options.length-1 ; i >=0; i--){
			if($(lrxs_id).options[i].value == "�����б�" || $(lrxs_id).options[i].value == "��ѡ��"){
				$(lrxs_id).options[i] = null;
			}
		}
	}else{
		var lrxs_id = "lrxs"+num;
		for(var i = $(lrxs_id).options.length-1 ; i >=0; i--){
			if($(lrxs_id).options[i].value == "�����" || $(lrxs_id).options[i].value == "ʱ���ʽ" || $(lrxs_id).options[i].value == "�ı���"){
				$(lrxs_id).options[i] = null;
			}
		}
	}
	
	for(var j=0;j<qx_arr.length;j++){
		if(zd == qx_arr[j]){
			var lrxs_id = "lrxs"+num;
			DWRUtil.removeAllOptions(lrxs_id);
			var options = [{dm:"�����",mc:"�����"},{dm:"�����ʽ",mc:"�����ʽ"}];
			DWRUtil.addOptions(lrxs_id,options,"dm","mc");
		}
	}
	
	var lrxs_select = $("hid_lrxs_"+num).value;
	$(lrxs_id).value = lrxs_select;
}
		</script>
	</head>
	<body onload="onShow()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/xsxxJbsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="hadRs" id="hadRs" value="${hadRs }"/>

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="saveZdsz();return false;"
									class="btn_ccg">����</a>
							</li>
							<li>
								<a href="#"
									onclick="showPlszDiv();return false;"
									class="btn_sz">����</a>
							</li>
							<li>
								<a href="#"
									onclick="creatNewView();return false;"
									class="btn_sx">������ͼ</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('sjy-xgwz-lrxz-wkxz-lrxs-sfqy-zdm-ymxs-xsmc');">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									����Դ
								</th>
								<td>
									<html:select property="search_sjy" style="width: 150px" styleId="sjy">
										<html:option value=""></html:option>
										<html:options collection="jbszSjyList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��ѧ��Ϊ׼
								</th>
								<td>
									<html:select property="search_xgwz" style="width: 150px" styleId="xgwz">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									¼������
								</th>
								<td>
									<html:select property="search_lrxz" style="width: 150px" styleId="lrxz">
										<html:option value=""></html:option>
										<html:options collection="jbszLrxzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�ɷ�Ϊ��
								</th>
								<td>
									<html:select property="search_wkxz" style="width: 150px" styleId="wkxz">
										<html:option value=""></html:option>
										<html:options collection="jbszWkxzList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									¼����ʽ
								</th>
								<td>
									<html:select property="search_lrxs" style="width: 150px" styleId="lrxs">
										<html:option value=""></html:option>
										<html:options collection="jbszLrxsList" property="en" labelProperty="cn" />
									</html:select>		
								</td>
								<th>
									�Ƿ�����
								</th>
								<td>
									<html:select property="search_sfqy" style="width: 150px" styleId="sfqy">
										<html:option value=""></html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									�ֶ���
								</th>
								<td>
									<html:text property="search_zdm" style="" maxlength="20" styleId="zdm"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>					
								</td>
								<th>
									��ʾ����
								</th>
								<td>
									<html:text property="search_ymxs" style="" maxlength="20" styleId="ymxs"
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue">ҳ����ʾ���Ϊ�գ������ֶ���Ϊ׼</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" length="9">
								<td id="<bean:write name="tit" property="en"/>"
<%--									onclick="tableSort(this)" --%>
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="checkVal" 
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- �ֶ� -->
								<logic:iterate id="v" name="s" offset="0" length="1">
									<td align="left" style="display: none">
										${v }
										<input type="hidden" name="zd" id="zd${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- �ֶ��� -->
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" nowrap="nowrap">
										${v }
										<input type="hidden" name="zdm" id="zdm${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- ҳ����ʾ -->
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td align="left" nowrap="nowrap">
										<input type="text" name="xsmc" id="xsmc${index }" value="${v }" style="width: 70px" maxlength="25"/>
									</td>
								</logic:iterate>
								<!-- ����Դ -->
								<logic:iterate id="v" name="s" offset="3" length="1">
									<td align="left" width="60px" nowrap="nowrap">
										<select name="sjy" id="sjy${index }" style="width: 60px" onchange="selectSjy('${index }')">
											<logic:iterate name="jbszSjyList" id="sjyOption">
												<logic:equal name="sjyOption" property="en" value="${v }">
													<option value="${sjyOption.en }" selected="selected">${sjyOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="sjyOption" property="en" value="${v }">
													<option value="${sjyOption.en }">${sjyOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>	
										</select>
									</td>
								</logic:iterate>
								<!-- �Ƿ�ѧ��Ϊ׼ -->
								<logic:iterate id="v" name="s" offset="4" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="��">
											<input type="radio" name="xgwz${index }" value="��" onclick="clickRadioValue(${index },'xgwz',this.value)" checked="checked"/>��
											<input type="radio" name="xgwz${index }" value="��" onclick="clickRadioValue(${index },'xgwz',this.value)"/>��
										</logic:equal>
										<logic:equal name="v" value="��">
											<input type="radio" name="xgwz${index }" value="��" onclick="clickRadioValue(${index },'xgwz',this.value)"/>��
											<input type="radio" name="xgwz${index }" value="��" onclick="clickRadioValue(${index },'xgwz',this.value)" checked="checked"/>��
										</logic:equal>
										<input type="hidden" name="xgwz" id="hid_xgwz_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- ¼������ -->
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="left" width="150px" nowrap="nowrap">
										<select name="lrxz" id="lrxz${index }" style="width: 150px">
											<logic:iterate name="jbszLrxzList" id="lrxzOption">
												<logic:equal name="lrxzOption" property="en" value="${v }">
													<option value="${lrxzOption.en }" selected="selected">${lrxzOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="lrxzOption" property="en" value="${v }">
													<option value="${lrxzOption.en }">${lrxzOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>	
										</select>
									</td>
								</logic:iterate>
								<!-- Ϊ������ -->
								<logic:iterate id="v" name="s" offset="6" length="1">
									<td align="left" width="80px" nowrap="nowrap">
										<select name="wkxz" id="wkxz${index }" style="width: 80px">
											<logic:iterate name="jbszWkxzList" id="wkxzOption">
												<logic:equal name="wkxzOption" property="en" value="${v }">
													<option value="${wkxzOption.en }" selected="selected">${wkxzOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="wkxzOption" property="en" value="${v }">
													<option value="${wkxzOption.en }">${wkxzOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>	
										</select>
									</td>
								</logic:iterate>
								<!-- ¼����ʽ -->
								<logic:iterate id="v" name="s" offset="7" length="1">
									<td align="left" width="width: 100px" nowrap="nowrap">
										<select name="lrxs" id="lrxs${index }" style="width: 80px" onchange="setLybList('${index }')">
											<logic:iterate name="jbszLrxsList" id="lrxsOption">
												<logic:equal name="lrxsOption" property="en" value="${v }">
													<option value="${lrxsOption.en }" selected="selected">${lrxsOption.cn }</option>
												</logic:equal>
												<logic:notEqual name="lrxsOption" property="en" value="${v }">
													<option value="${lrxsOption.en }">${lrxsOption.cn }</option>
												</logic:notEqual>
											</logic:iterate>
										</select>
										<input type="hidden" id="hid_lrxs_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- ������Դ�� -->
								<logic:iterate id="v" name="s" offset="8" length="1">
									<td align="left" nowrap="nowrap">
										<select name="lyb" id="lyb${index }" style="width: 130px">
											<option value="">����ѡ��¼����ʽ</option>
										</select>
									</td>
								</logic:iterate>
								<!-- �Ƿ����� -->
								<logic:iterate id="v" name="s" offset="9" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="��">
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)" checked="checked"/>��
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)"/>��
										</logic:equal>
										<logic:equal name="v" value="��">
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)"/>��
											<input type="radio" name="sfqy${index }" value="��" onclick="clickRadioValue(${index },'sfqy',this.value)" checked="checked"/>��
										</logic:equal>
										<input type="hidden" name="sfqy" id="hid_sfqy_${index }" value="${v }"/>
									</td>
								</logic:iterate>
								<!-- �Ƿ�����Դ�� -->
								<logic:iterate id="v" name="s" offset="10" length="1">
									<td align="left" nowrap="nowrap" style="display: none">
										<input type="text" name="lybnum" id="hid_lybnum_${index }" value="${v }"/>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--���� end-->
					<!-- ������ -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxJbszForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			<div id="tmpdiv1" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�������ݣ�ע����Ҫִ�б�������������ã�</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">ҳ����ʾ</th>
								<td>
									<input type="radio" name="sz_ymxs" value="������" onclick="setPlszHiddenValue('ymxs',this.value)" checked="checked"/>������
									<input type="radio" name="sz_ymxs" value="ͬ�ֶ���" onclick="setPlszHiddenValue('ymxs',this.value)"/>ͬ�ֶ���
									<input type="hidden" id="sz_ymxs_value" value="������"/>
								</td>
							</tr>
							<tr>
								<th>����Դ</th>
								<td>
									<input type="radio" name="sz_sjy" value="������" onclick="setPlszHiddenValue('sjy',this.value)" checked="checked"/>������
									<input type="radio" name="sz_sjy" value="ѧ��" onclick="setPlszHiddenValue('sjy',this.value)"/>ѧ��
									<input type="radio" name="sz_sjy" value="�ӿ�" onclick="setPlszHiddenValue('sjy',this.value)"/>�ӿ�
									<input type="hidden" id="sz_sjy_value" value="������"/>
								</td>
							</tr>
							<tr>
								<th>��ѧ��Ϊ׼</th>
								<td>
									<input type="radio" name="sz_xgwz" value="������" onclick="setPlszHiddenValue('xgwz',this.value)" checked="checked"/>������
									<input type="radio" name="sz_xgwz" value="��" onclick="setPlszHiddenValue('xgwz',this.value)"/>��
									<input type="radio" name="sz_xgwz" value="��" onclick="setPlszHiddenValue('xgwz',this.value)"/>��
									<input type="hidden" id="sz_xgwz_value" value="������"/>
								</td>
							</tr>
							<tr>
								<th>¼������</th>
								<td>
									<select name="sz_lrxz" id="select_sz_lrxz" onchange="setPlszHiddenValue('lrxz',this.value)">
										<option value="������">������</option>
										<logic:iterate name="jbszLrxzList" id="lrxzOption">
											<option value="${lrxzOption.en }">${lrxzOption.cn }</option>
										</logic:iterate>	
									</select>
									<input type="hidden" id="sz_lrxz_value" value="������"/>
								</td>
							</tr>
							<tr>
								<th>Ϊ������</th>
								<td>
									<input type="radio" name="sz_wkxz" value="������" onclick="setPlszHiddenValue('wkxz',this.value)" checked="checked"/>������
									<input type="radio" name="sz_wkxz" value="����Ϊ��" onclick="setPlszHiddenValue('wkxz',this.value)"/>����Ϊ��
									<input type="radio" name="sz_wkxz" value="����Ϊ��" onclick="setPlszHiddenValue('wkxz',this.value)"/>����Ϊ��
									<input type="hidden" id="sz_wkxz_value" value="������"/>
								</td>
							</tr>

							<tr>
								<th>¼����ʽ</th>
								<td>
									<input type="radio" name="sz_lrxs" value="������" onclick="setPlszHiddenValue('lrxs',this.value)" checked="checked"/>������
									<input type="radio" name="sz_lrxs" value="�����" onclick="setPlszHiddenValue('lrxs',this.value)"/>�����
									<input type="hidden" id="sz_lrxs_value" value="������"/>
								</td>
							</tr>
							<tr>
								<th>�Ƿ�����</th>
								<td>
									<input type="radio" name="sz_sfqy" value="������" onclick="setPlszHiddenValue('sfqy',this.value)" checked="checked"/>������
									<input type="radio" name="sz_sfqy" value="��" onclick="setPlszHiddenValue('sfqy',this.value)"/>��
									<input type="radio" name="sz_sfqy" value="��" onclick="setPlszHiddenValue('sfqy',this.value)"/>��
									<input type="hidden" id="sz_sfqy_value" value="������"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">

									<button type="button" onclick="setPlsz()" id="buttonSave">
										ȷ ��
									</button>
									
									<button type="button" onclick="hiddenMessage(true,true);return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<logic:present name="message">
				<logic:equal name="message" value="��Ҫ">
					<script defer="defer">
						$("message").value = "";
						$("doType").value = "";
						
						if (confirm("����ɹ����Ƿ�Ҫ������һ������ѧ����Ϣҳ����ֶ����ã�")) {
							refreshForm("xsxx_jbsz_ymjbsz.do");
						}
					</script>
				</logic:equal>
				<logic:notEqual name="message" value="��Ҫ">
					<%@ include file="/comm/other/tsxx.jsp"%>
				</logic:notEqual>
			</logic:present>	
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>