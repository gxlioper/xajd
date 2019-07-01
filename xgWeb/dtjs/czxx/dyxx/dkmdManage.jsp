<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
	
	function closeStuDiv(){
			$('div').style.display='none';
			i = document.getElementsByTagName("select").length;
			
			for (j = 0; j < i; j++) {
				var obj=document.getElementsByTagName("select")[j];
				if(obj.id!="stjxn" && obj.id!="stjxq"){
					obj.style.visibility = "";
					obj.style.display = "";
				}
			}
		}	
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	
	function saveZsdy(){
		var url = "/xgxt/czxxDtjsDyxx.do?method=dkmdManage&doType=save";
		showTips('���������У���ȴ�......');
		refreshForm(url);
	}
	
	function saveMd(){
		if($("rsNum")){
			if($("rsNum").value == "0"){
				alert("��ȷ����ѵ��ѧ����");
				return false;
			}else{
				var checkBoxArr = document.getElementsByName("checkVal");
				var flag = false;
	
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						flag = true;
					}
				}
				if(flag){
					if (confirm("ȷ������ѧ���μ���ѵ?")) {
					viewTempDiv("��ѵʱ��","div",280,160);
				}
				}else{
					alert("��ȷ����ѵ��ѧ��!");
					return false;
				}
			}
		}
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/czxxDtjsDyxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
						
						<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li><a href="#" class="btn_zj" onclick="saveMd()">����</a></li>
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">����</a></li>
							</ul>
						</div>
						</div>
						</logic:equal>
						
						<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" class="btn_cx" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/czxxDtjsDyxx.do?method=dkmdManage');">
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
		              		<th>�꼶</th>
		              		<td><html:select property="nj" style="width: 60px" onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select></td>
							<th>ѧ��</th>
		         			<td><html:text property="xh" style="" maxlength="20" styleId="xh"/></td>
		         			<th>����</th>
		         			<td><html:text property="xm" style="" maxlength="20" styleId="xm"/></td>
						
		              	</tr>
		         		<tr>
		         			<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
								</logic:notEqual>
								
								<logic:equal name="userType" value="xy">		
										<html:select property="queryequals_xydm" value="${userDep}" styleId="xy" disabled="true" style="width: 150px"  onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden  property="xydm" value="${userDep }"/>
								</logic:equal>
								</td>	
		         			<th>רҵ</th>
							<td><html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select></td>
		         			<th>�༶</th>
		         			<td><html:select property="bjdm" style="width: 150px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select></td>
		         		</tr>
		         		
		              	</tbody>
						</table>
						</div>
		
					
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
			 
			  <table summary="" class="dateline tablenowrap" width="100%">
			    <thead>
			      <tr>
			        <td>
						
					</td>
					<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<input type="checkbox" value="" style="display:none"/>
												<bean:write name="tit" property="cn" />
											</td>
						</logic:iterate>
			    	  </tr>
			  			  </thead>
			    		<tbody>
									<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand" 
										ondblclick="showInfo('/xgxt/czxxDtjsDyxx.do?method=dkmdUpdate','view','600','480')">
										<td align="center">
											<input type="checkbox" id="checkVal" name="checkVal"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
			   			 </tbody>
			 		 </table>
			 	
			  <!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=czxxDtjsForm"></jsp:include>
			  <!--��ҳ��ʾ-->
			  </logic:notEmpty>
			<div id="div" style="display:none" >
				<table width='300' class='formlist'>
					<thead>
		 			<tr height='20'>
					 <td colspan='2'>
					 <span>��ѵʱ��</span>
					 </td>
					 </tr>
					 </thead>
					 <tbody>
					 <tr height='30'>
					 <td align='right' width='30%'>
					 ��ʼʱ��
					 </td>
					 <td align='left'>
					 <input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>
					 </td>
					 </tr>
					 <tr bgcolor='EEF4F9'>
					 <td colspan='2' align='center'>
					 <button type="button" onclick='saveZsdy()';>ȷ��</button>
					 &nbsp;&nbsp;&nbsp;&nbsp;
					 <button type="button" onclick='hiddenMessage(true,true)'>�ر�</button>
					 </td>
					 </tr>
					 <tbody>
				 </table>
			</div>		
			</div></div>	
			</logic:empty></html:form>	
			
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
