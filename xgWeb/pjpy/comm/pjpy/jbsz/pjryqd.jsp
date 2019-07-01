<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pjpy/comm/pjpy/jbsz/pjryqd.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/ryqdDWR.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		
		//��֤�ɷ����
		function checkKfdh(){
			var flag= true;
			var userType= $("userType").value;
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if(userType != "admin"){
				for(var i=0;i<checkbox.length;i++){
					var obj = checkbox[i];
					var id = "yjdh_"+obj.id.replace("pkV_","");
					var color = $(id).value;
					if(color != ""){
						flag = false;
						alertError("�Ѿ������������ŵ�ѧ�������ٱ�����������ϵ����Ա");
						break;
					}
				}
			}
			
			return flag;
		}
		
		//��ʾ��������DIV
		function showMdbc(){
			
			var flag = checkKfdh();
			
			//��֤�ɷ����
			if(flag){
				var checkbox = jQuery('input[name=primarykey_cbv]:checked');
				
				if(checkbox.length > 0){
					tipsWindown("ϵͳ��ʾ","id:ryqddiv","350","250","true","","true","id");
				}else{
					showTopWin('/xgxt/pjpyRyqd.do?method=pjztmdsz','600','400');
				}
			}
		}
		
		//����������Ա����
		function saveMd(){
			var bjdm="";
			if($("select_save_bjdm")){
				bjdm=$("select_save_bjdm").value;
				if(bjdm!="" && bjdm!=null){
					var bjmc = jQuery('#select_save_bjdm option:selected').text();
					confirmInfo('��ȷ��Ҫ����ѡѧ��������"'+bjmc+'"��?',function(t){
						if (t == 'ok'){
							refreshForm('/xgxt/pjpyRyqd.do?method=pjryqd&doType=bcmd&save_bjdm='+bjdm);	
						}
					})
				}else{
					alertInfo("�༶����Ϊ��!");
				}
				
			}
			
		}
		
		//������Ա����
		function qxpjrysz(){
			var sfysz = jQuery('input[name=save_sfysz]:checked').val();
			
			confirmInfo('��ȷ��Ҫ����ѡѧ�����Ƿ��������Ϊ"'+sfysz+'"��?',function(t){
				if (t == 'ok'){
					refreshForm('/xgxt/pjpyRyqd.do?method=pjryqd&doType=qxmdsz&save_sfysz='+sfysz);	
				}
			})
		}
		
		function showSzDiv(){
			
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if(checkbox.length > 0){
				tipsWindown("ϵͳ��ʾ","id:qxszdiv","350","140","true","","true","id");
				//viewTempDiv('��Ա����','',350,200);
			} else {
				alertInfo("��ѡ��Ҫ�����ļ�¼��");
				return false;
			}
			
		}
		
<%--		function removeOption(){--%>
<%--			$("pjxn").options[0]=null;--%>
<%--			$("pjxq").options[0]=null;--%>
<%--			$("pjnd").options[0]=null;--%>
<%--		}--%>
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��������-�����ۺ�����-������Աȷ��
				</a>
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
		<!-- ���� end-->	
		
		<div class="prompt">
			<h3>
				<span>�������ڣ�</span>
			</h3>
			<p>
				����ѧ��(${pjxn})&nbsp;&nbsp;
				����ѧ��(${pjxqmc})&nbsp;&nbsp;
				�������(${pjnd})&nbsp;&nbsp;
			</p>
		</div>

		<!-- ��ʾ��Ϣ start-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.��������Ҫ����ѧ����ǰ���ڰ༶����������༶<font color="blue">��һ��</font>ʱ�����ڰ༶ȷ����<br/>
				2.��<font color="blue">��ѡ</font>ѧ��������£�����Ϊ����ѡѧ���ĵ��������༶��<br/>
				3.��<font color="blue">û�й�ѡ</font>ѧ��������£����Խ��а�԰�ĵ�����<br/>
			    4.����в���ѧ��û���ʸ���뱾����������ִ��<font color="blue">��������</font>����������Ϊ���ɲ�����<br/>
			    5.��ѯ���ݵ�ɫΪ��ɫ˵������������������Ա�༶�������������ݡ�
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
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>������������</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		<html:form action="/pjpyRyqd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="pjxyV" value="" />
			<input type="hidden" name="pjzyV" value="" />
			<input type="hidden" name="pjbjV" value="" />
			<input type="hidden" name="select_save_xydmV" value="" />
			<input type="hidden" name="select_save_zydmV" value="" />
			<input type="hidden" name="select_save_bjdmV" value="" />
			
			<!-- ������ -->
			<div class="toolbox">
		
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="showMdbc();return false;" class="btn_sz">������Ա�༶����</a>
							</li>
							<li>
								<a href="#" onclick="showSzDiv();return false;" class="btn_qx">��������</a>
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
												onclick="document.forms[0].go.value='go';refreshForm('/xgxt/pjpyRyqd.do?method=pjryqd&doType=query')">
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
									<th align="right">
										�꼶
									</th>
									<td align="left">
										<html:select property="queryequals_nj" styleId="nj" onmouseover=""
											 onchange="initZyList();initBjList();" style="width: 150px">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th align="right">
										<bean:message key="lable.xb" />
									</th>
									<td align="left">
										<html:select property="queryequals_xydm" styleId="xy" onmouseover=""
											style="width:150px" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th align="right">
										רҵ
									</th>
									<td align="left">
										<html:select property="queryequals_zydm" styleId="zy" onmouseover=""
											style="width:150px" onchange="initBjList();">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th align="right">
										�༶
									</th>
									<td align="left">
										<html:select property="queryequals_bjdm" styleId="bj" onmouseover=""
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th align="right">
										ѧ��
									</th>
									<td align="left">
										<html:text property="querylike_xh" styleId="xh" style="width: 150px"/>
									</td>
									<th align="right">
										����
									</th>
									<td align="left">
										<html:text property="querylike_xm" styleId="xm" style="width: 150px"/>
									</td>
								</tr>
								<tr>
									<th align="right">
										�����꼶
									</th>
									<td align="left">
										<html:select property="queryequals_pjnj" styleId="pjnj" onmouseover=""
											style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjnjList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th align="right">
										����<bean:message key="lable.xb" />
									</th>
									<td align="left">
										<html:select property="queryequals_pjxydm" styleId="pjxy" onmouseover=""
											style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjnj');
											initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjxyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th align="right">
										����רҵ
									</th>
									<td align="left">
										<html:select property="queryequals_pjzydm" styleId="pjzy" onmouseover=""
											style="width:150px" onchange="initPjBjList('view_xg_pjpy_njxyzybj','pjxy','pjzy','pjbj','pjnj')">
											<html:option value=""></html:option>
											<html:options collection="pjzyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th align="right">
										�����༶
									</th>
									<td align="left">
										<html:select property="queryequals_pjbjdm" styleId="pjbj" onmouseover=""
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="pjbjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
									<th align="right">
										�Ƿ����
									</th>
									<td align="left">
										<html:select property="queryequals_sfysz" style="width: 150px">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
									<th align="right">
										�Ƿ��ѵ���
									</th>
									<td align="left">
										<html:select property="queryequals_sftz" style="width: 150px">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
				 		 </logic:empty>
				 		 <html:hidden property="queryequals_pjxn" styleId="xn" value="${pjxn}"/> 
				 		 <html:hidden property="queryequals_pjxq" styleId="xq" value="${pjxq}"/> 
				 		 <html:hidden property="queryequals_pjnd" styleId="nd" value="${pjnd}"/> 
				    </span>
				    </h3>
					<logic:notEmpty name="rs">
						<div class="con_overlfow">
							<table summary="" id="rsTable" class="dateline tablenowrap" width="100%">
								<!-- ��ͷ -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" disabled="true" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2">
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
									<tr onclick="rowOnClick(this);" style="background-color:<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>">
										<td>
											<input type="hidden" name="yjdh" id="yjdh_${index }" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
											<input type="checkbox" name="primarykey_cbv" id="pkV_${index }"
												value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
											<input type="hidden" name="save_xh" id="save_xh" 
												value="<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>"/>
											<input type="hidden" name="save_xm" id="save_xm" 
												value="<logic:iterate id="v" name="s" offset="3" length="1">${v}</logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
								<!--���� end-->
							</table>
							<!--��ҳ-->
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyRyqdForm"></jsp:include>

							<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>
			
			<div id="ryqddiv" style="display: none;width:230px">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<td colspan="2">
									<span>��������</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th align="right">
									�꼶
								</th>
								<td align="left">
									<html:select property="save_nj" styleId="select_save_nj"
										style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_nj');
											initPjBjList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_bjdm','select_save_nj')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									<bean:message key="lable.xb" />
								</th>
								<td align="left">
									<html:select property="save_xydm" styleId="select_save_xydm"
										style="width:150px" onchange="initPjZyList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_nj');
											initPjBjList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_bjdm','select_save_nj')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									רҵ
								</th>
								<td align="left">
									<html:select property="save_zydm" styleId="select_save_zydm"
										style="width:150px" onchange="initPjBjList('view_xg_pjpy_njxyzybj','select_save_xydm','select_save_zydm','select_save_bjdm','select_save_nj');">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right">
									<font color="red">*</font>�༶
								</th>
								<td align="left">
									<html:select property="save_bjdm" styleId="select_save_bjdm"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align='right'>
									<div class="bz">"<font color="red">*</font>"Ϊ������</div>
									<button type="button" onclick='saveMd()'>
										����
									</button>
									<button type="button" onclick='closeWindown()'>
										ȡ��
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ������ -->
			<div id="qxszdiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<td colspan="2">
									ע���ù����������ò�����������Ա����ѡ��Ա��<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����Ϊ"��"����뱾��������"��"�򲻲��뱾��������
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									�Ƿ����
								</th>
								<td>
									<input type="radio" name="save_sfysz" id="save_sfysz"  value="��"/>��
									<input type="radio" name="save_sfysz" id="save_sfysz" checked="checked"  value="��"/>��
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan='2' align='right'>
									<button type="button" onclick='qxpjrysz()'>
										����
									</button>
									<button type="button" onclick='closeWindown()'>
										ȡ��
									</button>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>		
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
