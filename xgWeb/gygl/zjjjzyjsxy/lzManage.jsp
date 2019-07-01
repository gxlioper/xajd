<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ¥����Ϣ</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjjjzy_Gygl" method="post">
		<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
		<input type="hidden" id="realTable" name="realTable" value="" />				
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="lzManageAdd()"
									class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="lzManageModi()"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="lzManageDel()"
									class="btn_sc">ɾ��</a>
							</li>	
						</logic:equal>
						<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="dataExport()"
								class="btn_dc">����</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zjjjzy_Gygl.do?method=lzManage')">
											��ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									¥��
								</th>
								<td>
									<html:select property="lddm" style="width:120px"
									onchange="getqshLb()" styleId="lddm">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
									</html:select>										
								</td>
								<th>
									�Ƿ���ְ
								</th>
								<td>
									<html:select property="sfzz" style="width:90px" styleId="sfzz">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="lz" style="" styleId="lz" maxlength="20" />
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" style="" styleId="xm" maxlength="20"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
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
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="lzManageView()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--���� end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
				
				
			</div>
		</html:form>

	</body>
	<script type="text/javascript">
	       function lzManageAdd(){
	            var url = "/xgxt/zjjjzy_Gygl.do?method=lzAdd";
	            showTopWin(url,"600","300");
	       }
	       function lzManageModi(){
	           if (curr_row == null) {
		          alert("��ѡҪ�޸ĵļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {
	            var url = "/xgxt/zjjjzy_Gygl.do?method=lzModi";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            showTopWin(url,"600","400");
	           }
	       }
	       function lzManageView(){	          
	            var url = "/xgxt/zjjjzy_Gygl.do?method=lzModi";
	            url +="&pkValue=";
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	            url +="&view=true";
	            showTopWin(url,"600","350");	          
	       }
	       function lzManageDel(){
	           if (curr_row == null) {
		          alert("��ѡҪɾ���ļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {
	              if(confirm("ȷ��Ҫɾ���ü�¼��")){
	                 var url = "/xgxt/zjjjzy_Gygl.do?method=lzDel";
	                 url +="&pkValue=";
	                 url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;
	                 refreshForm(url);
	              }else{	 
	                 return false;           	            
	              }
	          }
	       }
	       
	</script>
</html>
