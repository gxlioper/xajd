<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function modi(url){
			
			if(curr_row != null){
				alert(curr_row.getElementsByTagName('input')[0].value);
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function view(url){
			
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);

		}
		
		function gfjyfTj(){
			url='/xgxt/cdtyGfjy.do?method=printGfjyf'; 
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
		}
		
		function checkZybj(){
			if($("ok").value==""){
				initZyList();initBjList();
			}
		}
		
		/*
		���ݵ�����
		*/	
		function impAndChkData(){
			var realTable = "";
			var tableName = "";
			var drnj="";
			var sty = "toolbar=no,location=no,directories=no,status=yes";
			sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=500,top=100";
			sty += ",left=200";
			
			if($("realTable")) realTable = document.getElementById("realTable").value;
			if($("tableName")) tableName = document.getElementById("tableName").value;
			if($("drnj")) drnj = document.getElementById("drnj").value;
			
			url = 'cdtyGfjy.do?method=importData';
			url += "&realTable=" + realTable;
			url += "&tableName=" + tableName;
			url += "&drnj="+drnj;
			//showTopWin(url,600,500);
			//refreshForm(url);
			window.open(url,'',sty);
		}
		
		
		jQuery(function(){
			dwr.engine.setAsync(false);
			initZyList();
			initBjList();
			if(jQuery("#hidxy").val()!=""){
				jQuery('#xy').val(jQuery("#hidxy").val());
			}
			jQuery('#zy').val(jQuery("#hidzy").val());
			jQuery('#bj').val(jQuery("#hidbj").val());
			
			dwr.engine.setAsync(true);
		})
		
		function showBm(){
			tipsWindown("ϵͳ��ʾ","id:div_nd","350","150","true","","true","id");
		}
		</script>
	</head>
	<body >

		<html:form action="/cdtyGfjy" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="realTable" id="realTable"
				value="xg_jxgl_gfjyfb" />
			<input type="hidden" name="tableName" id="tableName"
				value="xg_jxgl_gfjyfb" />
			<input type="hidden" name="viewName" id="viewName"
				value="xg_jxgl_gfjyfb" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<input type="hidden" name="userName" id="userName" value='${userName}' />
			<input type="hidden" name="hidxy" id="hidxy" value='${xydm}' />
			<input type="hidden" name="hidzy" id="hidzy" value='${zydm}' />
			<input type="hidden" name="hidbj" id="hidbj" value='${bjdm}' />
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
							<a href="#" class="btn_dc"
								onclick="gfjyfTj();return false;">����</a>
						</li>
						<li>
							<a href="#" onclick="showBm();return false;" class="btn_dr">����</a>
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
											onclick="allNotEmpThenGo('/xgxt/cdtyGfjy.do?method=gfjycxManage&go=go')">
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
										value="${nd}" onchange="initZyList();initBjList();">
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

						
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s">
									<tr style="cursor:hand"
										>
										<td style="display:none">
											
											<input type="hidden" name="xhArr" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>
											<input type="hidden" name="njArr" value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v" /></logic:iterate>"/>
											
										</td>
										<logic:iterate id="v" name="s" offset="0">
											<td nowrap>
												<bean:write name="v" />
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
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=cdtyGfjyForm"></jsp:include>
									<script type="text/javascript">
											$('choose').className="hide";
									</script>


			</div>
			
			<!-- �����γ̵��� �꼶ѡ�� begin -->
			<div id="div_nd" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>ѡ���꼶</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="drnj" styleId="drnj" style="width:150px"
										onchange="initZyList();initBjList();" value="${nd }">
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										<font color="blue">��ѡ���赼���ѧ���꼶���Ǹ��꼶�����ݲ��ᱻ���롣</font>
									</div>
									<div class="btn">
										<button type="button" name="ȷ ��" onclick="impAndChkData();">
											ȷ ��
										</button>
										<button type="button" name="ȡ ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- �����γ̵��� �꼶ѡ�� end -->
			
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
