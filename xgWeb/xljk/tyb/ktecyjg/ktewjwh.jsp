<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/xljkXlcyjg.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="tableName" value="view_ktergysjcb"/>
		<input type="hidden" name="realTable" value="ktergysjcb"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }"/> 
		<input type="hidden" id="userName" name="userName" value="${userName }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
		 <!-- ��ť -->
		 <logic:equal value="yes" name="writeAble" scope="request">
		 <div class="buttonbox">
		    <ul>
			<li> <a href="#" id="btn_add"  onclick="showTopWin('xljk_tyb_addKtewjwh.do',730,620)" class="btn_zj"> ���� </a> </li>
		      <li> <a href="#" id="btn_xg" onclick="modiAndDel('xljk_tyb_updateKtewjwh.do?pkValue=','modi',730,620)"class="btn_xg"> �޸� </a> </li>
			<li> <a href="#" id="btn_sc" onclick="deldata('xljk_tyb_ktewjwh.do?act=del')" class="btn_sc"> ɾ�� </a> </li>
			<li> <a href="#" id="btn_dr" onclick="impAndChkData()" class="btn_dr"> ���� </a> </li>
			<li> <a href="#"id="btn_dc" onclick="dataExport()"class="btn_dc"> ���� </a> </li>
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
		              	onclick="refreshForm('xljk_tyb_ktewjwh.do?act=qry')">
		              	�� ѯ
		              </button>
		              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	�� ��
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
		
			<tbody>
					<tr>
						<th align="" nowrap="nowrap">
							ѧ��
						</th>
						<td>
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px" maxlength="20">
							</html:text>
						</td>
						<th>
							����
						</th>
						<td>
							<html:text property="xm" styleId="xm" styleClass="inputtext"
								style="width:100px" maxlength="10">
							</html:text>
						</td>
						<th>
							����ʱ��
						</th>
						<td>
							<html:text property="cssj" styleId="cssj"  
							onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cssj','y-mm-dd');"></html:text>
						</td>
					</tr>
					<tr>
						<th align="left" nowrap>
							�꼶
						</th>
						<td>
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<html:select property="xydm" style="width:150px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>
							רҵ
						</th>
						<td>
							<html:select property="zydm" style="width:150px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							�༶
						</th>
						<td>
<%--							<logic:notEmpty name="bjList">--%>
							<html:select property="bjdm" style="width:150px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
<%--							</logic:notEmpty>--%>
						</td>
						<td colspan="4"></td>
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
						<font color="blue">��ʾ��������ͷ���Խ�������˫��һ�п��Բ鿴��ϸ��Ϣ��</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
		
			<logic:notEmpty name="rs">
				<div class="con_overlfow">
					 <table summary="" class="dateline tablenowrap" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" />
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								align="center" ondblclick="modiAndDel('xljk_tyb_updateKtewjwh.do?act=view&pkValue=','modi',730,620)">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xljkXlcyjgActionForm"></jsp:include>
			</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
	</html:form>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ��!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
