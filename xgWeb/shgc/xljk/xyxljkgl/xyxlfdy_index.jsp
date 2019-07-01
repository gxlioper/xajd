<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="JavaScript">
		function fdy_add(){
			showTopWin("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_add",750,600);
		}
		
		function fdy_view(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return ;
			}	
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_view&xn_id="+xn_id,750,600);
		}
		
		function fdy_modi(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return ;
			}	
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_modi&xn_id="+xn_id,750,600);
		}
		
		function fdy_del(){
			if (curr_row == null) {
				alert("��ѡ��Ҫɾ�������ݣ�\n��������Ӧ���У�");
				return false;
			} else if (confirm("ȷ��Ҫɾ������������")) {
				var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
				refreshForm("/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_del&xn_id="+xn_id);
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
	<body onload="usercheck()">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>


		<html:form action="/xljk_xljkfdy" method="post">
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />

			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="fdy_add();" class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#" onclick="fdy_modi();" class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#" onclick="fdy_del();" class="btn_sc"> ɾ�� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_search')">
											��ѯ
										</button>
										<button class="btn_cx" style="display:none" id="search_go1"
											onclick="refreshForm('/xgxt/xljk_xljkfdy.do?act=xljk_xljkfdy&doType=fdy_search')">
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									<logic:equal value="10827" name="xxdm">
										����ר
										<br/>
										�ɱ��
									</logic:equal>
									<logic:notEqual value="10827" name="xxdm">
										������
										<br/>����Ա���
									</logic:notEqual>
								</th>
								<td>
									<html:text property="fdybh" styleId="gcybh" style="width:80px"/>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:80px"/>
								</td>
								<th>
									�Ա�
								</th>
								<td>
									<html:select property="xb" styleId="xb" onchange="">
										<html:option value=""></html:option>
										<html:options collection="sexList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
								</td>

								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td align="left" nowrap>
									<html:select property="xydm" style="width:180px" styleId="xy"
										disabled="true"
										>
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>


				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</logic:notEmpty> </span>
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
										style="cursor:hand;background-color:" ondblclick="fdy_view()">
										<td>
											<input type="hidden" id="xn_id" name="xn_id"
												value="<bean:write name="s" property="xn_id"/>" />
											<bean:write name="s" property="fdybh" />
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
											<bean:write name="s" property="rzsj" />
										</td>
										<td>
											<bean:write name="s" property="csny" />
										</td>
										<td>
											<bean:write name="s" property="sjhm" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEmpty>
				</div>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="del_success">
					<script>
						alert("ɾ���ɹ�!");
						document.getElementById("search_go1").click();
						</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>alert("����ʧ��!��ѯʦ����Ѿ����ڣ�")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
