<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("��ѡ��һ��Ҫ�޸ĵļ�¼��");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			refreshForm(url+pkValue);
		}
		
		function showDetail(url,w,h){
			if(curr_row == null){
					alert("��ѡ��һ��Ҫ�鿴�ļ�¼��");
					return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin(url+pkValue,w,h);
		}
		
		function del(url){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ�������õļ�¼��");
				return false;
			}
			
			if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
<body>
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - У���ڹ���ѧ - ��λ��Ϣ��ѯ</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
						 <li> <a href="#" onclick="refreshForm('qgzxZgdzdx.do?method=showGwxxb');" class="btn_zj">�� ��</a> </li>
						 <li> <a href="#" onclick="modidata('qgzxZgdzdx.do?method=modiGwxx&pkValue=','500','400');" class="btn_xg">�޸�</a> </li>
						 <li> <a href="#" onclick="del('qgzxZgdzdx.do?method=delXwgwxx');" class="btn_sc">ɾ��</a> </li>
						 <li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					  </logic:equal>
					  <li> <a href="#" onclick="dataExport('/xgxt/expData.do?tableName=qgzx_xwgwxxb');" class="btn_dc">��������</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=gwglSearch')">
									��ѯ
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>ѧ��</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:80px">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>	
						</td>
						<th>���</th>
						<td>
							<html:select property="nd" style="width:175px" styleClass="select" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" style="width:175px" styleId="xq" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" 
								labelProperty="xqmc"/>
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>������λ</th>
						<td>
							<html:select property="sqdwmc" style="width:175px" styleId="sqdwmc" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="sqdwList" property="sqdwmc" 
								labelProperty="sqdwmc"/>
							</html:select>	
						</td>
						<th>����ʱ��</th>
						<td>
							<html:text property="sqsj" onclick="return showCalendar('sqsj','y-mm-dd');" onblur="value=value.replace('-','').replace('-','')" styleId="sqsj"/>
						</td>
						<th></th>
						<td>
							
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
							<font color="blue">��ʾ��˫����¼���Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
				 		 </logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
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
					<tr onclick="rowOnClick(this)"
						style="cursor:hand;" align="center" bgcolor="" 
						ondblclick="showDetail('qgzxZgdzdx.do?method=showGwxxDetail&pkValue=','600','500')">
						<td>
							<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							<input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
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
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxZgdzdxForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
			<div id="tmpdiv"></div>
		</html:form>		

		<logic:present name="result">
			<logic:equal value="true" name="result">
			<script>
					alert('�����ɹ�!');
					document.getElementById('search_go').click();
				</script>	
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>
				alert(document.getElementById('mes').value);				
			</script>
		</logic:equal>	
		</logic:present>
		
	</body>
</html>
