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
				alertInfo('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function view(url){
			
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,800,600);

		}
		
		function checkPlff(){
			
			if($("qdz") && $("qdz").value==""){
				alertInfo("���վ����Ϊ��,��ȷ��!");
				return false;
			}
			
			if($("zdz") && $("zdz").value==""){
				alertInfo("�յ�վ����Ϊ��,��ȷ��!");
				return false;
			}

			if($("hid_ffsj") && $("hid_ffsj").value==""){
				alertInfo("����ʱ�䲻��Ϊ��,��ȷ��!");
				return false;
			}
			
			dataBatch('xhgl.do?method=xhffManage&doType=plff')
		}
		
		
		function checkDel(){
			
			var pkV=document.getElementsByName("pkV");
			var sfff=document.getElementsByName("sfffArr");
			for(var i=0;i<pkV.length;i++){
				if(pkV[i].checked && sfff[i].value!="��"){
					alertInfo("��ѡ�ļ�¼�д���δ���ŵļ�¼,�޷�ȡ������,��ȷ�ϣ�");
					return false;
				}else if(pkV[i].checked){
					flag=true;
				}
			}
			dataBatch('xhgl.do?method=xhffManage&doType=del');
		}
		
		function plff(){
			var flag=false;
			
			var pkV=document.getElementsByName("pkV");
			var sfff=document.getElementsByName("sfffArr");
			for(var i=0;i<pkV.length;i++){
				if(pkV[i].checked && sfff[i].value!="��"){
					alertInfo("��ѡ�ļ�¼�д����ѷ��ŵļ�¼,�޷��ظ�����,��ȷ�ϣ�");
					return false;
				}else if(pkV[i].checked){
					flag=true;
				}
			}
		
			
			if(!flag){
				alertInfo("�빴ѡ��Ҫ����ѧ��֤�ļ�¼��");
				return false;
			}
			
			tipsWindown("ϵͳ��ʾ","id:div_xhff","450","300","true","","true","id");
		}
		
		function checkUpdate(){
		
			if(curr_row){
				var sfff=curr_row.getElementsByTagName("input")[1];
				if(sfff.value=="��"){
					alertInfo("ѡ�еļ�¼��δ����,�޷������޸Ĳ�����");
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
				var sfzc=curr_row.getElementsByTagName("input")[1];
				if(sfzc.value=="��"){
					alertInfo("ѡ�еļ�¼��δ����,�޷����в鿴������");
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
		}
		
		function checkSearch(){
			
			allNotEmpThenGo('xhgl.do?method=xhffManage')
		}
		</script>
	</head>
	<body>

		<html:form action="/xhgl" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
			<input type="hidden" name="userDep" id="userDep" value="${userDep}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<input type="hidden" name="pk" id="pk" value='xh||ffsj' />
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
								onclick="showTopWin('xhgl.do?method=xhffUpdate',800,600)"
								class="btn_zj"> У�շ��� </a>
						</li>
						<li>
							<a href="#"
								onclick="if(checkUpdate()){modi('xhgl.do?method=xhffOne&doType=update')}"
								class="btn_xg"> �޸� </a>
						</li>
						<li>
							<a href="#" onclick="checkDel()" class="btn_sc"> ȡ������ </a>
						</li>
						<li>
							<a href="#" onclick="plff()" class="btn_ccg"> �������� </a>
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
											onclick="checkSearch()">
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
									�Ƿ񷢷�
								</th>
								<td>
									<html:select property="sfff" styleId="sfff" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<td colspan="4">
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
										ondblclick="if(checkView()){view('xhgl.do?method=xhffOne&doType=view')}">
										<td>
											&nbsp;
											<input type="checkbox" name="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1" length="8" >
										
											<td>
												<bean:write name="v" />
											</td>
										
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="9" length="1" >
										
											<td>
												<bean:write name="v" />
												<input type="hidden" name="sfffArr" id="sfff_${index }" value="${v}"/>
											</td>
										
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- ������ end-->
						</tbody>
					</table>
				</div>
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xhglForm"></jsp:include>
				
			</div>
				<div id="div_xhff" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>У�շ���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_clr">
								<th width="30%">
									<font color="red">*</font>����ʱ��
								</th>
								<td>
									<input type="text" name="hid_ffsj" id="hid_ffsj"
									onblur="dateFormatChg(this)" style="cursor:hand"
									onclick="showCalendar(this.id,'y-mm-dd')"/>	
								</td>
							</tr>
							<tr>
								<th  width="30%">
									������
								</th>
								<td>
									<html:select  property="select_jbr"  style="width:90px" value="${rs.jbr }"
										styleId="jbr">
										<html:option value=""></html:option>
										<html:options collection="jbrList" property="jbrgh"
											labelProperty="jbrxm" />
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
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="checkPlff()">
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
