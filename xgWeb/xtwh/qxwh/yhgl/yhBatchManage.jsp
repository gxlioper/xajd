<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript">	
	 	 function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = '/xgxt/yhwhManage.do?method=yhqxfpManage';
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function checkOne(obj){
			var id = obj.id;
			var ids = id.split('_');
			
			if(ids[1] == 'yy'){
				var oid = ids[0] + "_gl";
				
				if($(oid)){
					$(oid).checked = "";	
				}
			}else{
				var oid = ids[0] + "_yy";
				if($(oid)){
					$(oid).checked = "";
				}
			}
		}
		 
	</script>
	</head>
	
	<body>
		<html:form action="/yhwhManage">
			<input type="hidden" name="userName" id="userName" value="${user.userName }"/>
			<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
			
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-�û���Ȩ</a>
				</p>
			</div>	
			<div class="toolbox">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
			      <li><a id="add" class="btn_fh" onclick="refreshForm('/xgxt/yhwhManage.do?method=jsBatchManage');return false;">����</a></li>
			      <li><a id="add" class="btn_zj" onclick="batchData('yhs','yhwhManage.do?method=yhBatchManage&doType=save','');return false;">����</a></li>
			    </ul>
			  </div>
			  <!-- ��ť -->
			  <p class="toolbox_fot"><em></em> </p>
			</div>
			
			<div class="leftframe04">	
				<div class="menulist">
					<div class="menutitle" style="width:100%;height:490px; overflow:auto;">
						<h3><span class="title">��ѡ��ɫ˵��</span></h3>
						<logic:iterate id="jsMap" name="jsList">
							<input type="hidden" value="${jsMap.jsdm }" name="jsIds"/>
							��ɫ����${jsMap.jsmc }	<br/>
							��ɫ���${jsMap.jslxmc }<br/>
							��ɫ������Χ��${jsMap.jscmmc }<br/>
							��ɫ˵����${jsMap.jssm }<br/><br/><br/>
						</logic:iterate>
					</div>
				</div>
			</div>
			
			<div class="rightframe04"><!--�������Ŀ��������ʱ����rightframe04_hidden���class��-->
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>�û���</th>
							<td>
								<html:text property="yhm" styleId="yhm"></html:text>
							</td>
							<th>�û�����</th>
							<td>
								<html:select property="szbm" style="width: 200px">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
								</html:select>
							</td>
							<th></th>
							<td>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('yhwhManage.do?method=yhBatchManage');">
										�� ѯ
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										�� ��
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">
							<td style="display: none"><input type="checkbox" name="checAll()" /></td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="${tit }" onclick="tableSort(this)">
									${tit }
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:empty name="rs">
					  <%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>	
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td>${v }</td>
								</logic:iterate>
								<td nowrap="nowrap">
									<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" name="yhs" value="${v }_yy" id="${v }_yy" onclick="checkOne(this);"/>ӵ��&nbsp;&nbsp;&nbsp;&nbsp;
										<logic:equal value="admin" name="userType">
										<input type="checkbox" name="yhs" value="${v }_gl" id="${v }_gl" onclick="checkOne(this);"/>����
										</logic:equal>
									</logic:iterate>
								</td>
							</tr>
						</logic:iterate>
						<%
						int rsNum = ((List)request.getAttribute("rs")).size();
						int pageSize = 11;
						if(rsNum < pageSize){
							for(int i=0; i<(pageSize-rsNum); i++){
						%>
						<tr>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
					 	</tr>
						<%}}%>
					</logic:notEmpty>
				</table>
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglForm"></jsp:include>
				<!--��ҳ��ʾ-->
				
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<input type="hidden" id="message" value="${message }"/>
				<script type="text/javascript" defer="defer">
					alert(document.getElementById('message').value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
