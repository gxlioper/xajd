<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/mdqrDWR.js"></script>	
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript">
			function showStu() {
				if (null == curr_row) {
					alert('��ѡ��һ��');
				} else {
					var xh = curr_row.getElementsByTagName('input')[0].value;
					var url = '/xgxt/stu_info_details.do?xh='+xh;
					showTopWin(url,'820','600');
				}
			}
			
			function modi(url){
				if(curr_row != null){
					showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('��ѡ��Ҫ�޸ĵ������У�');
					return false;
				}
			}
			
			function getXm(){
				if($("xmlbdm")){
					var xmlbdm=$("xmlbdm").value;
					var yhqx=$("yhqx").value;
					mdqrDWR.getXmListBySzqx(xmlbdm,yhqx,function(data){
						DWRUtil.removeAllOptions($("xmdm"));		
						DWRUtil.addOptions($("xmdm"),data,"dm","mc");
					});
				}
			}
			function getSavexm(){
				if($("select_save_xmlbdm")){
					var xmlbdm=$("select_save_xmlbdm").value;
					var yhqx=$("yhqx").value;
					mdqrDWR.getXmListBySzqx(xmlbdm,yhqx,function(data){
						DWRUtil.removeAllOptions($("select_save_xmdm"));		
						DWRUtil.addOptions($("select_save_xmdm"),data,"dm","mc");
					});
				}
			}
			
			function checkXmlx(){ 
				if($("xmlbdm").value==""){
					alert("��ѡ����Ŀ���");
					return false;
				}
				
				if($("xmdm").value==""){
					alert("��ѡ����Ŀ��");
					return false;
				}
				
				if($("szqx").value=="false"){
					alert("��û�����ø���Ŀ������Ȩ��!")
					return false;
				}
				
				return true;
			}
			
			function changeXm(){
				
				if($('xmdm').value!="" && checkSzqx()){
					allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrWh&gnmk=${gnmk }&doType=query');
				}
			}
			
			function checkSzqx(){
				var xmdm=$("xmdm").value;
				var userType=$("userType").value;
				var isFdy=$("isFdy").value;
				var blog=true;
				dwr.engine.setAsync(false);
				mdqrDWR.checkMdSz(xmdm,function(data){
					if((userType=="admin" || userType=="xx")){
						if(data!="xx" && data!="no"){
							alert("��û�����ø���Ŀ������Ȩ��!")
							blog=false;
						}
					}else if(userType=="xy" && isFdy!="true"){
						if(data!="xy" && data!="no"){
							alert("��û�����ø���Ŀ������Ȩ��!")
							blog=false;
						}
					}else if(isFdy=="true"){
						if(data!="fdy" && data!="no"){
							alert("��û�����ø���Ŀ������Ȩ��!")
							blog=false;
						}
					}
				});
				dwr.engine.setAsync(true);
				$("szqx").value=blog;
				return blog;
			}
			
			function changeSzzq(){
				var xmdm=$("xmdm").value;
				mdqrDWR.getSzzq(xmdm,function(data){
					if(data=="xn"){
						$("showTr").style.display="";
						$("showXn").style.display="";
						$("showXq").style.display="none";
						$("dxq").disabled="true";
						$("dnd").disabled="true";
						$("dxn").disabled="";
						$("showNd").style.display="none";
					}else if(data=="xq"){
						$("showTr").style.display="";
						$("showXn").style.display="";
						$("showXq").style.display="";
						$("dnd").disabled="true";
						$("dxn").disabled="";
						$("dxq").disabled="";
						$("showNd").style.display="none";
					}else if(data=="nd"){
						$("showTr").style.display="";
						$("showXn").style.display="none";
						$("showXq").style.display="none";
						$("showNd").style.display="";
						$("dxn").disabled="true";
						$("dxq").disabled="true";
						$("dnd").disabled="";
					}else if(data=="wzq"){
						$("showTr").style.display="none";
						$("dxn").disabled="true";
						$("dxq").disabled="true";
						$("dnd").disabled="true";
					}else if(data=="jyc"){
						$("showTr").style.display="none";
						$("dxn").disabled="true";
						$("dxq").disabled="true";
						$("dnd").disabled="true";
					}
				})
			}
			
			function showSaveXm(){
				viewTempDiv('��������','divq',360,200);
			}
			
			function saveMdsz(){
				if($("select_save_xmlbdm").value==""){
					alert("��ѡ����Ŀ���!");
					return false;
				}
				
				if($("select_save_xmdm").value==""){
					alert("��ѡ����Ŀ!");
					return false;
				}
				
				
				var pk= document.getElementsByName("primarykey_cbv");
				var len=pk.length;
				var blog=false;
				
				if(!len>0){
					alert("û�п�ȷ�ϵ�ѧ������!");
					return false;
				}
				
				for(i=0;i<len;i++){
					if(!pk[i].disabled){
						blog=true;
						break;
					}
				}
				
				if(!blog){
					alert("û�п�ȷ�ϵ�ѧ������!");
					return false;

				}
				
				allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrWh&gnmk=${gnmk }&doType=mdsz');
				
			}
			
		</script>
	</head>
	<body onload="changeSzzq();getSavexm()">
		
		<html:form action="/mdqr" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
		    <input type="hidden" name="realTable" id="realTable" value="mdqr_xmnrb" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		    <input type="hidden" name="userType" id="userType" value="${userType}" />
		    <input type="hidden" name="yhqx" id="yhqx" value="${yhqx}" />
		    <input type="hidden" name="szqx" id="szqx" />
		    <input type="hidden" name="save_sbsj" id="save_sbsj" value="${nowTime }"/>
		    <html:hidden property="tabName" value="mdqr_xmnrb" />
		    <html:hidden property="viewName"value="view_mdqr_xmnrb" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			
			
			<div class="toolbox">
			 <!-- ��ť -->
			 <logic:equal name="writeAble" value="yes">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="showSaveXm()" class="btn_zj"> ���� </a> </li>
				<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="if(checkXmlx()){allNotEmpThenGo('/xgxt/mdqr.do?method=mdqrWh&gnmk=${gnmk }&doType=query')}">
			              	�� ѯ
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
						<tr>
							<th >
								ѧ��
							</th>
							<td>
								<html:text property="querylike_xh" styleId="xh"/>
							</td>
							<th>
								����
							</th>
							<td>
								<html:text property="querylike_xm" styleId="xm"/>
							</td>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="queryequals_nj" styleId="nj"  style="width:150px"
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
									<html:select property="xydm" styleId="xy"  style="width:150px"
										onchange="initZyList();initBjList();" disabled="true" value="${userDep }">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="queryequals_xydm" value="${userDep}"/> 
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
									<html:select property="queryequals_xydm" styleId="xy"  style="width:150px"
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
								<html:select property="queryequals_zydm" styleId="zy"  style="width:150px"
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
								<html:select property="queryequals_bjdm" styleId="bj"  style="width:150px" >
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr>
							<th>
								��Ŀ���
							</th>
							<td>
								<html:select property="queryequals_xmlbdm"  styleId="xmlbdm" style="width:150px"
											onchange="getXm()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList"  property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								��Ŀ
							</th>
							<td>
								<html:select property="queryequals_xmdm"
										onchange="changeSzzq()" styleId="xmdm" style="width:150px">
										<html:options collection="xmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<th>
								�Ƿ���������	
							</th>
							<td>	
								<html:select property="yesNo" styleId="yesNo">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>	
						<tr id="showTr" style="display:none">
							<td colspan="6">
								<span id="showXn" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ��
								<html:select property="save_xn" styleId="xn"  style="width:150px;" 
										disabled="true">
										<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<html:hidden property="queryequals_xn" styleId="dxn" />
								</span>
								<span id="showXq" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ��
								<html:select property="save_xq" styleId="xq"  style="width:150px;"
										disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<html:hidden property="queryequals_xq" styleId="dxq"/>
								</span>
								<span id="showNd" style="display:none">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���
								<html:select property="save_nd" styleId="nd"  style="width:150px;"
										disabled="true">
										<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								<html:hidden property="queryequals_nd" styleId="dnd"/>
								</span>
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
			 		 <logic:notEmpty name="rs">
			 		 	<font color="blue">��ʾ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
	
			<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()"/>
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" name="save_xh" id="save_xh"  value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
										<input type="hidden" name="isDisable" id="isDisable" value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"/>
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
											   <logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>
											   <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate> />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3" length="1">
									<td nowrap>
										<a href="javascript:showStu();" class="pointer" style="color:#0A63BF">${v }</a>
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="4">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=mdqrForm"></jsp:include>
			</logic:notEmpty>
			</div>
			
			<div id="divq" style="display: none">
				<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="2">
								<span color="blue">
									����ѡ����Ŀ���,��ѡ����Ŀ���ơ�
									</br>��Ŀ������Ŀ���ƽ�Ӱ�챣��Ľ����
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>
							<font color="red">*</font>��Ŀ���
						</th>
						<td>
							<html:select property="save_xmlbdm" styleId="select_save_xmlbdm"  style="width:150px"
											onchange="getSavexm()">
										<html:option value=""></html:option>
										<html:options collection="xmlbList" property="dm"
										labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��Ŀ����
						</th>
						<td>
							<html:select property="save_xmdm"  styleId="select_save_xmdm"
									onchange="changeSzzq()" style="width:150px">
									
							</html:select>
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan='2' align='right'>
							<button onclick='saveMdsz()'>ȷ��</button>
						</td>
					</tr>
					</tfoot>
				</table>
				</div>
			</div>	
		</html:form>
  </body>
</html>
