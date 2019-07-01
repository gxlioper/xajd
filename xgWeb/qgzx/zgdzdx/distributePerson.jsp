<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">	
		function showDetail(w,h){
			url = 'qgzxZgdzdx.do?method=showDistributeP';
			if(curr_row == null){
					alert("��ѡ��һ��Ҫ�鿴�ļ�¼��");
					return false;
			}
			
			var pkV = curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin(url+"&pk="+pkV,w,h);
		}
		
		function search(){
			if(document.getElementById("xn").value == ""){
				alert("��ѡ��ѧ�꣡");
				return false;
			}
			if(document.getElementById("nd").value == ""){
				alert("��ѡ����ȣ�");
				return false;
			}
			if(document.getElementById("xq").value == ""){
				alert("��ѡ��ѧ�ڣ�");
				return false;
			}
			allNotEmpThenGo('qgzxZgdzdx.do?method=distributePerson');
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
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="search()">
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
							<html:select property="xn" styleId="xn" style="width:180px">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
						<th>���</th>
						<td>
							<html:select property="nd" styleClass="select" styleId="nd" style="width:180px">
								<html:options collection="xnList" property="nd" labelProperty="nd" />
							</html:select>						
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" styleId="xq" styleClass="select" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" 
								labelProperty="xqmc"/>
							</html:select>
						</td>
					  </tr>
					  <!--���ݴ�ѧ-->
					  <logic:equal value="11078" name="xxdm">
					  <tr>
						<th>��λ</th>
						<td>
							<html:select property="xmdm" styleId="xmdm" style="width:180px">
								<html:options collection="gwList" property="gwdmgwsbsj"  labelProperty="gwdm"/>
							</html:select>
						</td>
						<th></th>
						<td>
													
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>		
					  </logic:equal>
					  <!--end���ݴ�ѧ-->
					  <!--������һְҵ����ѧԺ-->
					  <logic:equal value="13742" name="xxdm">
					  <tr>
						<th>��λ</th>
						<td>
							<html:select property="xmdm" styleId="xmdm">
								<html:options collection="gwList" property="gwdmgwsbsj"  labelProperty="gwdm"/>
							</html:select>
						</td>
						<th></th>
						<td>
													
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>		
					  </logic:equal>
					  <!--end������һְҵ����ѧԺ-->		  
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
								<font color="blue">��ʾ��˫��һ�п��Է���������������ͷ��������</font>
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
								ondblclick="showDetail('500','400')">
								<td>
									<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									<input type="checkbox" id="pk" name="pk" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
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
