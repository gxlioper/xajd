<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxZgdzdxFunc.js'></script>
	<script type="text/javascript">
		function modidata(url,w,h){
			if(curr_row == null){
				alert("��ѡ��һ��Ҫ�޸ĵļ�¼��");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pkValue;
			showTopWin(url,w,h);
		}	
		
		//�������ͨ��
		function qgzxsqshtg(){
			var xxdm = val('xxdm');
			if(xxdm == '11078'){
				//���ݴ�ѧ
				var pkString = "!!SplitOneSplit!!";
				var yesNo = yesNo;
				var url = "qgzxZgdzdx.do?method=saveQgzxshBatch";
				var gwxzdm = $("gwxzdm").value;
				var xxdm = $("xxdm").value;
				var xydm = $("xydm").value;
				var userType = $("userType").value;
				for (i=0; i<document.getElementsByName("pk").length; i++){ //�����ַ���
					if(document.getElementsByName("pk")[i].checked){
			    		pkString += document.getElementsByName("pkV")[i].value +"!!SplitOneSplit!!";
			    	}
				}
				
				if(pkString == "!!SplitOneSplit!!"){
					alert('��ѡ��Ҫ�����ļ�¼��');
					return false;
				}
				
				url += "&pkString=";
				url += pkString;
				url += "&yesNo=ͨ��";					
				if(gwxzdm=="002" && userType=="xx" 
					|| gwxzdm=="002" && userType=="admin" 
					|| gwxzdm=="001" && userType=="xy"){
					if (confirm("ȷ��Ҫ����������")) {
							showSelectGwDiv(url);	
					}
				}else{
					url+="&gwxzdm=";
					url+=gwxzdm;
					refreshForm(url);
				}					
			}else{
				qgzxAudiBatch('ͨ��');
			}
		}		
		
		function auditing(url){	
			var xmdm = val("hidGw");
					
			url += "&gwdm=";
			url += val("txtGw");
			url += "&xmdm=";
			url += val("hidGw");
			if(xmdm == ""){
				alert("��ָ����λ��");
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
	<body onload="xyDisabled('xy')">
		<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" name="mes" id="mes" value="${mes}"/>			
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ��� - �ڹ���ѧ�������</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
						<logic:notEqual name="userType" value="xy">
							<li> <a href="#" onclick="qgzxsqshtg();" class="btn_shtg">���ͨ��</a> </li>
							<li> <a href="#" onclick="qgzxAudiBatch('��ͨ��');" class="btn_shbtg">��˲�ͨ�� </a> </li>
					  </logic:notEqual>
					  <!-- ���ʴ�ѧ˵ѧԺ�û���ȥ��������˰�ť,���޸�-->
					  <logic:equal value="xy" name="userType">
					  <logic:equal value="10491" name="xxdm">
					  	<li> <a href="#" onclick="qgzxsqshtg();" class="btn_shtg">���ͨ��</a> </li>
						<li> <a href="#" onclick="qgzxAudiBatch('��ͨ��');" class="btn_shbtg">��˲�ͨ�� </a> </li>
					  </logic:equal>
					  </logic:equal>
					  <!-- end ���ʴ�ѧ˵ѧԺ�û���ȥ��������˰�ť,���޸�-->
					  </logic:equal>

					  <!--�ǹ��ݴ�ѧ-->
					  <logic:notEqual value="11078" name="xxdm">
						<li> <a href="#" onclick="dataExport();" class="btn_dc">��������</a> </li>
					  </logic:notEqual>
					  <!--end�ǹ��ݴ�ѧ-->						
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
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=qgzxsqAudi')">
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
							<html:select property="nd" styleClass="select" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd" />
							</html:select>			
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" styleId="xq" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" 
								labelProperty="xqmc"/>
							</html:select>	
						</td>
					  </tr>	
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" style="width:90px"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
							</html:select>	
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xh"/>
						</td>
						<th>����</th>
						<td>
							<html:text property="xm"/>	
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:175px" styleClass="select" styleId="xy" 
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" style="width:175px" styleClass="select" styleId="zy"
								onchange="initBj();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>			
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" style="width:175px" styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>��˽��</th>
						<td>
							<logic:equal value="xx" name="userType">
								<html:select property="xxsh">
									<html:option value=""></html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="δ���">δ���</html:option>
								</html:select>
							</logic:equal>
								
							<logic:equal value="admin" name="userType">
								<html:select property="xxsh">
									<html:option value=""></html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="δ���">δ���</html:option>
								</html:select>
							</logic:equal>		
				
							<logic:notEqual value="xx" name="userType">
							<logic:notEqual value="admin" name="userType">
								<html:select property="xysh">
									<html:option value=""></html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="δ���">δ���</html:option>
								</html:select>	
							</logic:notEqual>
							</logic:notEqual>
						</td>

						<!--���ݴ�ѧ-->
						<logic:equal value="11078" name="xxdm">	
						<th>��λ����</th>
						<td>
							<html:select property="gwxzdm" styleId="gwxzdm" styleClass="select">
								<html:options collection="gwxzList" property="gwxzdm" 
								labelProperty="gwxzmc"/>
							</html:select>			
						</td>
						</logic:equal>
						<!--end���ݴ�ѧ-->
						<!--�ǹ��ݴ�ѧ-->
						<logic:notEqual value="11078" name="xxdm">	
						<th></th>
						<td>
									
						</td>
						</logic:notEqual>
						<!--end�ǹ��ݴ�ѧ-->
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
							<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ<logic:equal value="xx" name="userType">�����</logic:equal><logic:equal value="admin" name="userType">�����</logic:equal>��������ͷ��������</font> 
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
					<logic:iterate id="tit" name="topTr" offset="2">
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
							style="cursor:hand;" align="center" 
							bgcolor="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" 
							ondblclick="modidata('qgzxZgdzdx.do?method=showQgzxsqsh&pkValue=',800,600)">
							
							<logic:equal name="xxdm" value="11078">
							<td>
								<input type="hidden" id="pkV" name="pkV" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>  value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>							
							<logic:iterate id="v" name="s" offset="3">
								<td>
									${v}
								</td>
							</logic:iterate>
							</logic:equal>

							<logic:notEqual name="xxdm" value="11078">
							<td>
								<input type="hidden" id="pkV" name="pkV" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
								<input type="checkbox" id="pk" name="pk"  value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
							</td>							
							<logic:iterate id="v" name="s" offset="2">
								<td>
									${v}
								</td>
							</logic:iterate>
							</logic:notEqual>
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
				<logic:empty name="mes">
					<script>
						alert('�����ɹ�!');
						document.getElementById('search_go').click();
					</script>	
				</logic:empty>
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
						document.getElementById('search_go').click();
					</script>
				</logic:notEmpty>
			</logic:equal>
			<logic:equal value="false" name="result">
			<logic:empty name="mes">
					<script>
						alert('����ʧ��!');
						document.getElementById('search_go').click();
					</script>	
				</logic:empty>
				<logic:notEmpty name="mes">
					<input type="hidden" id="mes" name="mes" value="${mes}"/>
					<script>
						alert(document.getElementById('mes').value);
						document.getElementById('search_go').click();
					</script>
				</logic:notEmpty>
		</logic:equal>	
		</logic:present>
	</body>
</html>
