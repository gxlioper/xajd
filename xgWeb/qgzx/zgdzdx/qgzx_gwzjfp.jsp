<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		/* ============ begin ���ΰ =============== */ 
		function ch(){
			var gw = document.getElementById("gw").value;
			var sq = "";
			var ap = "";
			document.getElementById("sq").value = "";
			document.getElementById("ap").value = "";
			for(var i = 0;i < document.getElementById("sq").options.length; i++){
				if(document.getElementById("sq").options[i].value ==gw){
					document.getElementById("sq").options[i].selected = true;
					sq = document.getElementById("sq").options[i].text;
				}
			}
			
			for(var i = 0;i < document.getElementById("ap").options.length; i++){
				if(document.getElementById("ap").options[i].value ==gw){
					document.getElementById("ap").options[i].selected = true;
					ap = document.getElementById("ap").options[i].text;
				}
			}
			document.getElementById("kyrs").value = sq - ap;
		}
		
		function aa(url,w,h){
			if (curr_row == null) {
				
			}else{
				if (w==''||w==null||h==''||h==null){
					w=500;
					h=400;
				}
				var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
				url += pkValue;
				showTopWin(url,w,h);
			}
		}
		/* ============ end ���ΰ =============== */ 
	</script>
</head>
	<body onload="">
		<html:form action="/qgzxZgdzdx.do" method="post">
		<input type="hidden" id="userType" name="userType" value="<bean:write name="userType"/>"/>
		<input type="hidden" id="userName" name="userName" value="<bean:write name="userName"/>"/>
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>

		<logic:equal value="false" name="allow">
			<br/><br/>
			<center>���ڲ��ڿ�ֱ�ӷ����ʱ��֮��,��ʱ����ֱ�ӷ���!</center>
		</logic:equal>
			
		<logic:equal value="true" name="allow">
			<logic:notEqual value="stu" name="userType">
				<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
					  		<li> <a href="#" onclick="gwzjfp();" class="btn_zj">�����λ</a> </li>
					  </logic:equal>
						<li> <a href="#" onclick="refreshForm('qgzxZgdzdx.do?method=showExportPage');" class="btn_dc">��������</a> </li>
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
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=searchGwfp')">
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
							<html:select property="xn" style="width:90px" styleId="xn" 
								onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>���</th>
						<td>
							<html:select property="nd" styleId="nd" onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>			
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" style="width:60px" styleId="xq" onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" style="width:70px"
								onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>		
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xh" style="width:120px"></html:text>
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" style="width:85px"></html:text>
						</td>
					  </tr>
					  <tr>
						<logic:equal value="xyhj" name="xyhjType">
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th></th>
						<td>
									
						</td>
						<th></th>
						<td>
							
						</td>
						</logic:equal>

						<logic:notEqual value="xyhj" name="xyhjType">
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:150px" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" style="width:150px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>			
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" style="width:150px" styleId="bj">
								<logic:notEqual value="yes" name="isBzr">
									<html:option value=""></html:option>
								</logic:notEqual>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
						</logic:notEqual>
					  </tr>		
					  <tr>
						<th>�Ƿ��и�λ</th>
						<td>
							<html:select property="sfygw" style="width:150px">
								<html:option value=""></html:option>
								<html:option value="�и�">�и�</html:option>
								<html:option value="�޸�">�޸�</html:option>
							</html:select>
						</td>
						<th>��λ</th>
						<td>
							<!-- begin ���ΰ 2009/3/26 -->
							<html:select property="gwdm" styleId="gw" onchange="ch()" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="gwList" labelProperty="gwdm" property="gwdm"/>
							</html:select>			
						</td>
						<th>���˵�λ</th>
						<td>
							<html:select property="yrdwdm" style="width:150px" onchange="loadGwmcxx('gw','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="yrdwList" property="yrdwdm"
									labelProperty="yrdwmc" />
							</html:select>		
						</td>
					  </tr>	
					  <tr>
						<th>��λ�Ѱ�������</th>
						<td>
							<html:select property="gwdm" styleId="ap" disabled="true" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="apList" labelProperty="num" property="gwdmgwsbsj"/>
							</html:select>
						</td>
						<th>��λ����ʹ������</th>
						<td>
							<html:select property="gwdm" styleId="sq" disabled="true" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="sqList" labelProperty="sqsyrs" property="gwdmgwsbsj"/>
							</html:select>		
						</td>
						<th>��λ������</th>
						<td>
							<input type="text" id="kyrs" disabled="true" style="width:150px"/>  
						</td>
					  </tr>	
					  <!-- end ���ΰ 2009/3/26 -->
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
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" id="all" name="all" onclick="chec()" />
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
					<tr onclick="rowOnClick(this);" style="cursor:hand">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" name="pkV" value="${v}"/>
							</logic:iterate>
						</td>
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="<bean:write name="v"/>" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="10" length="1">
								<input type="hidden" value="<bean:write name="v"/>" name="sfyg" id="sfyg"/>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								<bean:write name="v" />
							</logic:iterate>
						</td>
						<logic:iterate id="v" name="s" offset="2" length="3">
							<td>
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="5">
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
			</logic:notEqual>
		</logic:equal>

		<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="result" value="true">
			<script language="javascript">
      				alert("�����ɹ���");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="false">
			<logic:notEmpty name="mes">
				<input name="mes" id="mes" value="${mes}" />
				<script>
					alert(document.getElementById('mes').value);
				</script>
			</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
			</logic:empty>
		</logic:equal>
	</body>
</html>
