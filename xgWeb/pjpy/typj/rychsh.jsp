<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script type="text/javascript">
	function shOne() {
		var shjb = $('shjb').value;
		if (curr_row == null) {
			alert('��ѡ��һ��');
			return false;
		} else {
			var check = curr_row.getElementsByTagName('input')[1].value;
			if ('' != check) {
				alert('�ϼ����������,���������!');
				return false;
			}
		}
		showInfo('/xgxt/typj.do?method=rychView&shjb='+shjb,'sh','900','600');
	}
	
	function checkSyme() {
		var syme = $('syme').value;
		var shjb = $('shjb').value;
		var userType = $('userType').value;
		var isFdy = $('isFdy').value;
		var checkBoxArr = document.getElementsByName("primarykey_cbv");
		var n=0;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				var text = checkBoxArr[i].parentNode.parentNode.name.trim();
				if ('#CCCCCC'==text) {
					n++;
				}
			}
		}
		if ('xy'==userType && 'false'==isFdy && Number(n)>Number(syme)){
			alert('������������!');
			return false;
		} 
		shformdata('/xgxt/typj.do?method=rychsh&shjg=ͨ��&doType=sh');
	}
</script>
</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/typj" method="post">
			<input type="hidden" name="syme" id="syme" value="${syme }"/>
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="isFdy"name="isFdy" value="${isFdy }"/>
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="shjb" name="shjb" value="${shjb }"/>
			
			<logic:notEqual value="true" name="isFdy" scope="session">
				<logic:equal value="xy" name="userType" scope="session">
					<input type="hidden" name="queryequals_xydm" value="${userDep }"/>
				</logic:equal>
			</logic:notEqual>
			<logic:equal value="2" name="shjb">
				<logic:notEqual value="xy" name="userType" scope="session">
					<input type="hidden" name="queryequals_xysh" value="ͨ��"/>
				</logic:notEqual>
			</logic:equal>
			
			<logic:equal value="3" name="shjb">
			<logic:notEqual value="true" name="isFdy" scope="session">
				<logic:equal value="true" name="bzrSh">
					<logic:notEqual value="true" name="isBzr" scope="session">
						<logic:equal value="xy" name="userType" scope="session">
							<input type="hidden" name="queryequals_fdysh" value="ͨ��"/>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
							<input type="hidden" name="queryequals_xysh" value="ͨ��"/>
						</logic:notEqual>
					</logic:notEqual>
				</logic:equal>
				<logic:notEqual value="true" name="bzrSh">
					<logic:equal value="xy" name="userType" scope="session">
						<input type="hidden" name="queryequals_fdysh" value="ͨ��"/>
					</logic:equal>
					<logic:notEqual value="xy" name="userType" scope="session">
						<input type="hidden" name="queryequals_xysh" value="ͨ��"/>
					</logic:notEqual>
				</logic:notEqual>
					
			</logic:notEqual>
				
			</logic:equal>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
					  <logic:empty name="yhInfo">
							<li> <a href="#" onclick="shOne();" class="btn_sh">�������</a> </li>								
							<logic:equal value="10657" name="xxdm" scope="session">
							<li> <a href="#" onclick="checkSyme();" class="btn_shtg">����ͨ��</a> </li>
							</logic:equal>
							<logic:notEqual value="10657" name="xxdm" scope="session">
							<li> <a href="#" onclick="shformdata('/xgxt/typj.do?method=rychsh&shjg=ͨ��&doType=sh');" class="btn_shtg">����ͨ��</a> </li>
							</logic:notEqual>
							<li> <a href="#" onclick="shformdata('/xgxt/typj.do?method=rychsh&shjg=��ͨ��&doType=sh');" class="btn_shbtg">������ͨ��</a> </li>
					  </logic:empty>	
					  </logic:equal>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>			
						 <td>
						<!--����������ʾ-->
						<logic:notEmpty name="yhInfo">
							<font color="red">��ʾ��${ yhInfo}</font>
						</logic:notEmpty>
						<!--end����������ʾ-->
						</td>			  
				          <td colspan="5">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
										onclick="if(''==$('rychdm').value){alert('��ѡ�������ƺ�!');return false;}   allNotEmpThenGo('/xgxt/typj.do?method=rychsh&doType=query')">
									��ѯ
								</button>
								<button
									onclick="searchReset();return false;">
									����
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					<!--��������Ϊѧ��-->
					<logic:equal value="xn" name="pjzq">
					<tr>
						<th>ѧ��</th>
						<td>
							<html:hidden property="queryequals_xn" value="${xn }"/>
							<html:select property="queryequals_xn" disabled="true" value="${xn }">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
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
					<!--end��������Ϊѧ��-->

					<!--��������Ϊ���-->
					<logic:equal value="nd" name="pjzq">
					<tr>
						<th>���</th>
						<td>
							<html:hidden property="queryequals_nd" value="${nd }"/>
							<html:select property="queryequals_nd" disabled="true" value="${nd }">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
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
					<!--end��������Ϊѧ��-->

					<!--��������Ϊѧ��-->
					<logic:equal value="xq" name="pjzq">
					<tr>
						<th>ѧ��</th>
						<td>
							<html:hidden property="queryequals_xn" value="${xn }"/>
							<html:select property="queryequals_xn" disabled="true" value="${xn }">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:hidden property="queryequals_xq" value="${xq }"/>
							<html:select property="queryequals_xq" disabled="true" value="${xq }">
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>		
						</td>
						<th></th>
						<td>
							
						</td>
					</tr>
					</logic:equal>
					<!--end��������Ϊѧ��-->
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="queryequals_nj" onchange="initZyList();initBjList()" styleId="nj">
								<logic:notEqual value="10657" name="xxdm" scope="session">
									<html:option value=""></html:option>
								</logic:notEqual>
								<logic:equal value="10657" name="xxdm" scope="session">
									<logic:notEqual value="xy" name="userType" scope="session">
										<html:option value=""></html:option>
									</logic:notEqual>
								</logic:equal>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>		
							<html:text property="querylike_xh" maxlength="20" style="width:80px"></html:text>
						</td>
						<th>����</th>
						<td>
							<html:text property="querylike_xm" maxlength="20" style="width:80px"></html:text>
						</td>
					  </tr>	
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm" onchange="initZyList();initBjList()"  styleId="xy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>	
							<html:select property="queryequals_zydm" onchange="initBjList()"  styleId="zy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
							</html:select>	
						</td>
						<th>�༶</th>
						<td>
							<html:select property="queryequals_bjdm"  styleId="bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>	
					  <logic:empty name="shjb" >
						<tr>
						<th>�����ƺ�</th>
						<td>
							<html:select property="queryequals_rychdm" styleId="rychdm" onchange="allNotEmpThenGo('/xgxt/typj.do?method=rychsh')">
								<html:options collection="rychList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th></th>
						<td>
								
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>	
					  </logic:empty>
					  <logic:equal value="1" name="shjb">
						<tr>
						<th>�����ƺ�</th>
						<td>
							<html:select property="queryequals_rychdm" styleId="rychdm" onchange="allNotEmpThenGo('/xgxt/typj.do?method=rychsh')">
								<html:options collection="rychList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th>���״̬</th>
						<td>
							<html:select property="queryequals_shzt"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>		
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>	
					  </logic:equal>
					  <logic:equal value="2" name="shjb">
						<tr>
						<th>�����ƺ�</th>
						<td>
							<html:select property="queryequals_rychdm" styleId="rychdm" onchange="allNotEmpThenGo('/xgxt/typj.do?method=rychsh')">
								<html:options collection="rychList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<logic:equal value="xy" name="userType" scope="session">
						<th><bean:message key="lable.xsgzyxpzxy" />���</th>
						<td>
							<html:select property="queryequals_xysh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>	
						</td>	
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
						<th>ѧУ���</th>
						<td>
							<html:select property="queryequals_xxsh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>	
						</logic:notEqual>
						<th></th>
						<td>
							
						</td>
					  </tr>								
					  </logic:equal>
					  <logic:equal value="3" name="shjb">
						<tr>
						<th>�����ƺ�</th>
						<td>
							<html:select property="queryequals_rychdm" styleId="rychdm" onchange="allNotEmpThenGo('/xgxt/typj.do?method=rychsh')">
								<html:options collection="rychList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<logic:equal value="xy" name="userType" scope="session">
						<logic:equal value="true" name="isFdy" scope="session">
						<th>����Ա���</th>
						<td>
							<html:select property="queryequals_xysh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						</logic:equal>						
						<logic:equal value="false" name="isFdy" scope="session">
						<th><bean:message key="lable.xsgzyxpzxy" />���</th>
						<td>
							<html:select property="queryequals_xysh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						</logic:equal>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
						<th>ѧУ���</th>
						<td>
							<html:select property="queryequals_xxsh"  >
								<html:option value=""></html:option>
								<html:options collection="shztList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						</logic:notEqual>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  </logic:equal>
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
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������
							<logic:equal value="10657" name="xxdm" scope="session">
								<logic:equal value="xy" name="userType" scope="session">
									<logic:equal value="false" name="isFdy" scope="session">
										&nbsp;&nbsp;&nbsp;&nbsp;���������ƺ�ʣ��ͨ������Ϊ��${syme }��
									</logic:equal>
								</logic:equal>
							</logic:equal>
							</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="3" scope="request">
						<td id="${tit.en}"
							onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
					<tr onclick="rowOnClick(this)" name="<logic:iterate id="v" name="s" offset="1" length="1">
						    <bean:write name="v"/>
						    </logic:iterate>"
						ondblclick="var shjb = $('shjb').value;showInfo('/xgxt/typj.do?method=rychView&shjb='+shjb,'view','900','600')"
						style="cursor:hand;background-color:
						    <logic:iterate id="v" name="s" offset="1" length="1">
						    <bean:write name="v"/>
						    </logic:iterate>
						     ">
						<td align=center>
							<logic:iterate id="v" name="s" offset="2" length="1">
								<input type="checkbox" name="primarykey_cbv" id="cbv" value="<bean:write name="v"/>" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>/>
								<input type="hidden" value="<bean:write name="v" />"/>
								<input type="hidden" name="check" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							</logic:iterate>
					   		
					    </td>
						<logic:iterate id="v" name="s" offset="3">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
				</div>
		</html:form>
		 
		 <logic:present name="result">
			<script>
				alert(''+$('message').value);
				document.getElementById('search_go').click();
			</script>
	</logic:present>
	</body>
</html>
