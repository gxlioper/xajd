<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function modi(url){
			
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function view(url){
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);

		}
		
		function checkDel(){
			
			if($("xn").value==$("dqxn").value
				&& $("xq").value==$("dqxq").value){
				var pkV=document.getElementsByName("pkV");
				var sfzx=document.getElementsByName("sfzxArr");
				for(var i=0;i<pkV.length;i++){
					if(pkV[i].checked && sfzx[i].value!="��"){
						alertInfo("��ѡ�ļ�¼�д���δע���ļ�¼,�޷�ȡ��ע����");
						return false;
					}else if(pkV[i].checked){
						flag=true;
					}
				}
			}else {
				alertInfo("ȡ��ע��ʱ����ѡ��ǰѧ�ꡢѧ��!");
				return false;
			}
			dataBatch('rcsw_xszgl.do?method=xszzxManage&doType=del')
		}
		
		function plzx(){
			
			if($("select_zxyy")&&$("select_zxyy").value==""){
				alertInfo("ע��ԭ����Ϊ��!");
				return false;
			}
			
			dataBatch('rcsw_xszgl.do?method=xszzxManage&doType=plzx');
		}
		
	
		function  showPlzxDiv(){
			if($("xn").value==$("dqxn").value
				&& $("xq").value==$("dqxq").value){
				var pkV=document.getElementsByName("pkV");
				var sfzx=document.getElementsByName("sfzxArr");
				
				var flag=false;
				for(var i=0;i<pkV.length;i++){
					if(pkV[i].checked && sfzx[i].value!="��"){
						alertInfo("��ѡ�ļ�¼�д�����ע���ļ�¼,�޷�����ע��������");
						return false;
					}else if(pkV[i].checked){
						flag=true;
					}
				}
				
				if(!flag){
					alertInfo("�빴ѡ��Ҫע����ѧ��֤��¼��");
					return false;
				}
			}else {
				alertInfo("ע��ʱ����ѡ��ǰѧ�ꡢѧ��!");
				return false;
			}
			tipsWindown("ϵͳ��ʾ","id:div_xszzx","450","300","true","","true","id");
		}
		
		function checkUpdate(){
		
			if(curr_row){
				var sfff=curr_row.getElementsByTagName("input")[2];
				if(sfff.value=="��"){
					alertInfo("ѡ�еļ�¼��δע��,�޷������޸Ĳ�����");
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
		}
		
		function checkView(){
		
			if(curr_row){
				var sfzx=curr_row.getElementsByTagName("input")[10];
				if(sfzx.value=="��"){
					alertInfo("ѡ�еļ�¼��δע��,�޷����в鿴������");
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
		}
		
		
		</script>
	</head>
	<body>

		<html:form action="/rcsw_xszgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="realTable" id="realTable"
				value="xszffb" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_xszff" />
			<input type="hidden" name="viewName" id="viewName"
				value="view_xszff" />
			<input type="hidden" name="userName" id="userName"
				value="${userName }" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="userDep" id="userDep"
				value="${userDep }" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<input type="hidden" name="pk" id="pk" value='xh||ffsj' />
			<input type="hidden" name="dqxn" id="dqxn" value='${dqxn}' />
			<input type="hidden" name="dqxq" id="dqxq" value='${dqxq}' />
			<input type="hidden" id="act" name="act"
						value="studentPaperPut" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showTopWin('rcsw_xszgl.do?method=xszzxUpdate',800,600)"
								class="btn_zj"> ѧ��֤ע�� </a>
						</li>
						<li>
							<a href="#" onclick="checkDel()" class="btn_sc"> ȡ��ע�� </a>
						</li>
						<li>
							<a href="#" onclick="showPlzxDiv()" class="btn_ccg"> ����ע�� </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('rcsw_xszgl.do?method=xszzxManage')">
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
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" />
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" />
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:150px"
										onchange="initZyList();initBjList();">
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
									<logic:equal name="userType" value="xy">
										<html:select property="queryequals_xydm" styleId="xy"
											disabled="true" value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:150px"
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
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>

							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:150px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" styleId="xq" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
								<th>
									�Ƿ�ע��
								</th>
								<td>
									<html:select property="sfzx" styleId="sfzx" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								
							</tr>
							<tr>
								<th>
									ע��ԭ��
								</th>
								<td>
									<html:select property="zxyy" styleId="zxyy" style="width:150px"
										>
										<html:option value=""></html:option>
										<html:options collection="ydlbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									
								</th>
								<td>
								</td>
								<th>
									
								</th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							<font color="blue">��ʾ��������ͷ��������;˫��һ����¼���Բ鿴��ϸ��Ϣ��</font>
						</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									&nbsp;
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="if(checkView()){view('rcsw_xszgl.do?method=xszzxOne')}">
										<td>
											&nbsp;
											<input type="checkbox" name="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
										</td>
										
										<td>
											<logic:iterate id="v" name="s" offset="1" length="1" >
												<bean:write name="v" />
												<input type="hidden" value="${v}"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1" >
												<bean:write name="v" />
												<input type="hidden" name="xmArr" id="xm_${index }" value="${v}"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="3" length="1" >
												<bean:write name="v" />
												<input type="hidden" name="njArr" id="nj_${index }" value="${v}"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="4" length="1" >
												<input type="hidden" name="xydmArr" id="xydm_${index }" value="${v}"/>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="5" length="1" >
												<bean:write name="v" />
												<input type="hidden" name="xymcArr" id="xymc_${index }" value="${v}"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="6" length="1" >
												<input type="hidden" name="zydmArr" id="zydm_${index }" value="${v}"/>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="7" length="1" >
												<bean:write name="v" />
												<input type="hidden" name="zymcArr" id="zymc_${index }" value="${v}"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="8" length="1" >
												<input type="hidden" name="bjdmArr" id="bjdm_${index }" value="${v}"/>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="9" length="1" >
												<bean:write name="v" />
												<input type="hidden"  name="bjmcArr" id="bjmc_${index }" value="${v}"/>
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="10" length="1" >
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="11" length="1" >
												<bean:write name="v" />
												<input type="hidden" name="sfzxArr" id="sfzx_${index }" value="${v}"/>
											</logic:iterate>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- ������ end-->
						</tbody>
					</table>
				</div>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
			</div>
			
			<div id="div_xszzx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>ѧ��֤ע��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_clr">
								<th width="30%">
									<font color="red">*</font>ע��ԭ��
								</th>
								<td>
									<html:select property="select_zxyy" styleId="select_zxyy" style="width:150px"
										>
										<html:option value=""></html:option>
										<html:options collection="ydlbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr id="tr_tsnr">
								<th width="30%">
									��ע<br/>
									<font color="red"><��500��></font>
								</th>
								<td>
									<textarea name="bz" id="bz" rows="5" cols="" 
										style="word-break:break-all;width:96%" onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										ע������<font color="red">*</font>���ֶα���
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="plzx()">
											�� ��
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
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
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
