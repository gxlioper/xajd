<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script>
			function dataCollect(){
				window.open('/xgxt/yxgl_bdyz.do?method=printXsbdhzb');
			}
		</script>
		<input type="hidden" id="flag" name="flag" value="<bean:write name="onload" />"/>
		<html:form action="yxgl_xsgl.do">
		<input type="hidden" id="realTable" name="realTable" value="view_newstureportinfo" />
		<input type="hidden" id="tableName" name="tableName" value="view_newstureportinfo" />
		<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ӭ�¹��� �� �������� �� ��������</a>
				</p>
			</div>
		<div class="toolbox">
			 <!-- ��ť -->
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" id="add" onclick="showTopWin('yxgl_xsgl_one.do?active=add',600,450);"
						 onfocus="this.click();" class="btn_zj"> ���� </a> </li>
			    <li> <a href="#"onclick="yxglXsglModi();" class="btn_xg"> �޸� </a> </li>
				<li> <a href="#"  onclick="yxglBdDelete('yxgl_xsgl.do');" class="btn_sc"> ɾ�� </a> </li>
				<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> ���� </a> </li>
				<li> <a href="#" onclick="document.getElementById('chaflag').value=2;dataExport()" class="btn_dc"> ���� </a> </li>
			    <input type="hidden" name="chaflag" value="1"/>	
			    
				<%-- ���Ϲ�ҵ��ѧ--%>
				<logic:equal value="10463" name="xxdm">
					<li> <a href="#" onclick="dataExport()"  class="btn_dc"
					title="����ѧ���Ѿ�����������,������<bean:message key="lable.xsgzyxpzxy" />/���᡿����Ѿ�������ѧ������"
					> ���������������� </a> </li>
					<li> <a href="#" onclick="dataCollect()"  class="btn_dc"
					title="������������������ܱ�,��<bean:message key="lable.xsgzyxpzxy" />������ѧ�����ݡ�"
					> ������������ </a> </li>			
				</logic:equal>	
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
			              	onclick="search('yxgl_xsgl.do')"
							onkeypress="keyPressDo(this)" >
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
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
								<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
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
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
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
							</tr>
							<tr>
								<th>
									������
								</th>
								<td>
									<html:text property="ksh" styleId="ksh" style="width:120px" onkeydown="if(event.keyCode==13)search_go.click();"></html:text>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="width:120px"></html:text>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:120px"></html:text>
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
			 		 	��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				<logic:notEmpty name="rs">
					 <table summary="" class="datelist" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0" length='1'>
										<td  style="display:none" id="<bean:write name="tit" property="en"/>"
											nowrap>
											<input type="hidden" value="<bean:write name="tit" property="cn" />" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="yxgltableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>						
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">	
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="yxglXsglView();">
										<td style="display:none">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>											
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
				</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>


