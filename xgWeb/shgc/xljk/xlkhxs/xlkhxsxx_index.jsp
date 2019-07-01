<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="JavaScript">
		function xlkhxs_view(){
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_view&xn_id="+xn_id,525,300);
		}
		
		function xlkhxs_add(){
			showTopWin("/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_add",550,300);
		}
		
		function xlkhxs_modi(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			}else {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin("/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_modi&xn_id="+xn_id,525,300);
				return true;
			}
		}
		
		function xlkhxs_del(){
			if (curr_row == null) {
				alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
				return false;
			}else {
				if (confirm("ȷ��Ҫɾ������������")) {
					var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
					refreshForm("/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_del&xn_id="+xn_id);
					return true;
				}else {
					return false;
				}
			}
		}
		
		function xlkhxs_pcjg_view(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�鿴�����ݣ�\n��������Ӧ���У�");
				return false;
			}else {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin("/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_pcjg_view&xn_id="+xn_id,750,500);
				return true;
			}
		}
		
		function xlkhxs_ftjl_view(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�鿴�����ݣ�\n��������Ӧ���У�");
				return false;
			}else {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				
				showTopWin("/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_ftjl_view&nobutton=yes&xn_id="+xn_id,700,600);
				return true;
			}
		}
		function usercheck(){
			var userType=document.all['userType'].value;
			if("xx"==userType||"admin"==userType){
				document.getElementById('xy').disabled=false;
			}else{
				document.getElementById('xy').disabled=true;
			}
		}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<input type="hidden" id="cbVal" name="cbVal" value="" />
		<input type="hidden" id="userName" name="userName" value="${userName }" />
		<input type="hidden" id="userDep" name="userDep" value="${userDep }" />
		<input type="hidden" id="userType" name="userType"
				value="${userType }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }"/>
	
		<html:form action="/xljk_xlkhxs" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ��������ѧ������ - ��������ѧ����Ϣ</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- ��ť -->
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="xlkhxs_add()" class="btn_zj"> ���� </a> </li>
			    <li> <a href="#" onclick="xlkhxs_modi()" class="btn_xg"> �޸� </a> </li>
				<li> <a href="#" onclick="xlkhxs_del()" class="btn_sc"> ɾ�� </a> </li>
				<li> <a href="#" onclick="xlkhxs_pcjg_view()" class="btn_ck"> �鿴�ղ���� </a> </li>
				<li> <a href="#" onclick="xlkhxs_ftjl_view()" class="btn_ck"> �鿴��̸��¼ </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			 
			 <div class="searchtab">
				<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			               <input type="hidden" name="go" value="a" />
			              <button class="btn_cx" id="search_go" 
			              	onclick="allNotEmpThenGo('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_search')">
			              	�� ѯ
			              </button>
			              <button id="search_go1" style="display:none"
									onclick="refreshForm('/xgxt/xljk_xlkhxs.do?act=xljk_xlkhxs&doType=xlkhxs_search')">
						  </button>
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
			
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="session"/>" />
					<tbody>
						<tr>
							<th align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" style="width:180px" 
											 styleId="xy"
											 onchange="initBjList()" >
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									
								</html:select>
								<logic:equal name="userType" value="xy">
								<input type="hidden" name="xydm" value="${userDep }"/>
								</logic:equal>
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<th>
								�Ƿ��ص�۲����
							</th>
							<td>
								<html:select property="zdgcdxdm" style="width:80px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="zdgcdxList" property="DMH"
										labelProperty="DMMC" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="left">
								ѧ��
							</th>
							<td>
								<!-- ѧ���û� -->
								<logic:equal value="stu" name="userType" scope="session">
									<html:text property="xh" styleId="xh" value="${userName }" readonly="true"/>
								</logic:equal>
								
								<!-- ��ѧ���û� -->
								<logic:notEqual value="stu" name="userType" scope="session">
									<html:text property="xh" styleId="xh" />
								</logic:notEqual>
								
							</td>
							<th>
								����
							</th>
							<td>
								<html:text property="xm" styleId="xm" />
							</td>
							<td colspan="2">
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
			 		 	��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			
			<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:" ondblclick="xlkhxs_view()">
								<td>
									<input type="hidden" id="xn_id" name="xn_id"
										value="<bean:write name="s" property="xn_id"/>" />
									<bean:write name="s" property="xh" />
								</td>
								<td>
									<bean:write name="s" property="xm" />
								</td>
								<td>
									<bean:write name="s" property="xb" />
								</td>
								<td>
									<bean:write name="s" property="xymc" />
								</td>
								<td>
									<bean:write name="s" property="bjmc" />
								</td>
								<td>
									<bean:write name="s" property="dmmc" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>
						alert("ɾ���ɹ�!");
						document.getElementById("search_go1").click();
					</script>
				</logic:equal>
				<logic:equal name="message" value="del_fail">
					<script>
						alert("ɾ��ʧ��!");
					</script>
				</logic:equal>
				<logic:equal name="message" value="update_success">
					<script>
						alert("���³ɹ�!");
						document.getElementById("search_go1").click();
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
