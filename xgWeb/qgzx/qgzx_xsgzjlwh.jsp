<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function modi(doType){
			if(curr_row != null){
				showTopWin('/xgxt/qgzxkh.do?method=xsgzjlModi&pkValue='+curr_row.getElementsByTagName('input')[0].value+"&doType="+doType,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ��У�');
				return false;
			}
		}
		
		function del(){
			var RowsStr="!!";	
			var mes = "ȷ��Ҫ������ѡ��¼��";
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			
			if (!confirm(mes)){
				return false;
			}
			
			url = "qgzxkh.do?method=delXsgzjl";
			refreshForm(url);
		}
		
		function exportData(){
			var url = "qgzxkh.do?method=expXsgzjl";
			var eleArr = ["nd","yf","nj","gwdm","xh","xm"];
			url += "&xydm=" + val("xy"); 
			url += "&zydm=" + val("zy");
			url += "&bjdm=" + val("bj");
			for(var i=0; i<eleArr.length;i++){
				url += "&" + eleArr[i] + "=" + val(eleArr[i]);
			}
			window.open(url);
		}
	</script>
</head>
	<body>
		<html:form action="/qgzxkh.do" method="post">
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
			<input type="hidden" name="xyV" value=""/>
		    <input type="hidden" name="zyV" value=""/>
		    <input type="hidden" name="bjV" value=""/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
			<div class="toolbox">	
			<!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<!--��дȨ-->
					<logic:equal value="yes" name="writeAble">
					<logic:notEqual value="stu" name="userType">
						<li> <a href="#" onclick="showTopWin('qgzxkh.do?method=xsgzjlAddBatch',800,600)" class="btn_zj">��������</a> </li>
						<li> <a href="#" onclick="showTopWin('qgzxkh.do?method=xsgzjlAdd',800,600)" class="btn_zj">�� ��</a> </li>
						<li> <a href="#" onclick="modi('modi')" class="btn_xg">�޸�</a> </li>
						<li> <a href="#" onclick="del()" class="btn_sc">ɾ ��</a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					</logic:notEqual>
					</logic:equal>
					<!--end��дȨ-->
					<li> <a href="#" onclick="dataExport()" class="btn_dc">��������</a> </li>
			    </ul>
			  </div>			 
			 <!--��ѯ����-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('/xgxt/qgzxkh.do?method=queryXsgzjl')">
							��ѯ
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>					
		      		<th>���</th>
					<td>
						<html:select property="nd" styleId="nd" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="ndList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
					<th>�·�</th>
					<td>
						<html:select property="yf" styleId="yf" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="yfList" property="yf" labelProperty="yf" />
						</html:select>
					</td>						
					<th>��λ</th>
					<td>
						<html:select property="gwdm" styleId="gwdm" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="gwmcList" property="gwdm" labelProperty="gwdm" />
						</html:select>
					</td>				
				</tr>	
				<tr>					
		      		<th>�꼶</th>
					<td>
						<html:select property="nj"  styleId="nj"
							onchange="initZyList();initBjList();" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<th>ѧ��</th>
					<td>
						<html:text property="xh" styleId="xh" style="width:180px"></html:text>
					</td>						
					<th>����</th>
					<td>
						<html:text property="xm" styleId="xm" style="width:180px"></html:text>
					</td>				
				</tr>					
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<th>רҵ</th>
					<td>
						<html:select property="zydm" styleId="zy" onchange="initBjList();" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
					<th>�༶</th>
					<td>
						<html:select property="bjdm" styleId="bj" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
			  </tbody>
			</table>				
		</div>	
		<div class="formbox">
			<h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
		 		</logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
			      	<td>
						<input type="checkbox" name="all" value="all" onclick="chec()"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowMoreClick(this,'',event);"
							ondblclick="modi('view')" style="cursor:hand">
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" name="pkV"
										value="<bean:write name="v"/>"/>
								</logic:iterate>
							</td>
							<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />

								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="2">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>			
			   </table>
			   </div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxForm"></jsp:include>
			    <!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>

			<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('�����ɹ���');
				</script>			
			</logic:equal>
			<logic:notEqual value="true" name="result">
				<script>
					alert('����ʧ�ܣ�');
				</script>
			</logic:notEqual>
			</logic:present>
		</html:form>
	</body>
</html>
