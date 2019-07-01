<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/pjpy/typj.js"></script>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
</head>
	<body onload="">
		<html:form action="/typj" method="post">
			<input type="hidden" name="tname" id="tname" value="${tname }"/>
			<input type="hidden" name="userName" value="${userName }"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li class="ha">
							<a href="#" onclick="SelectrwOption('zxszy_dd','/xgxt/typj.do?method=tjsz')">
								<span>��ѧ��</span>
							</a>
						</li>
						<li>
							<a href="#" onclick="SelectrwOption('zxszy_sjd','/xgxt/typj.do?method=rychShsz')" id="${card.en}_a">
								<span>�����ƺ�</span>
							</a>
						</li>
					</ul>				
				</div>
			</div>
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="saveAll('/xgxt/typj.do?method=tjsz&doType=save');" class="btn_ccg">����</a> </li>
						<li> <a href="#" onclick="showShjbList();" class="btn_sz">��������</a> </li>
					 </logic:equal>		
						<li> <a href="#" onclick="expData('/xgxt/typj.do?method=tjsz&doType=expData');" class="btn_dc">��������</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/typj.do?method=tjsz&doType=query')">
									��ѯ
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>��ѧ��</th>
						<td>
							<html:select property="queryequals_jxjdm" styleId="queryequals_jxjdm">
								<html:options collection="jxjList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th>��˼���</th>
						<td>	
							<html:select property="queryequals_fjsh" styleId="queryequals_fjsh">
								<html:option value=""></html:option>
								<html:options collection="fjshList" property="en" labelProperty="cn"/>
							</html:select>	
							<div id ="tempDiv"></div>
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
						<input type="checkbox" name="cb"  id="cb"  onclick="selectAll()" style="height:17.5px;display: none;" disabled="true"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
						<td id="${tit.en}"  nowrap>
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s" indexId="indx">
					<tr onclick="rowOnClick(this)"
						style="cursor:hand;">
						<td align=center>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="checkbox" id="cbv" name="primarykey_cbv" value="<bean:write name="v" />" />
							</logic:iterate>
					    </td>
						<logic:iterate id="v" name="s" offset="1" length="2">
							<td>
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="3" length="1">
							<td>
								<html:select property="save_fjsh" value="${v }" styleId="save_fjsh${indx}">
									<html:option value=""></html:option>
									<html:options collection="fjshList" property="en" labelProperty="cn"/>
								</html:select>
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

				<div id="plszDiv" style="display:none">
					<table width='300' class='formlist'>
						<thead>
						<tr>
							<td colspan='2'>
							   ��ѡ����˼���
							</td>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th>��˼���</th>
							<td>
								<html:select property="shjb" styleId="select_shjb">
									<html:option value=""></html:option>
									<html:options collection="fjshList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</tr>
						<tr><td align='center' colspan='2'><button class='button2'  onclick='plszShjb();'>ȷ��</button> &nbsp;&nbsp;&nbsp;&nbsp;<button class='button2' onclick='hiddenMessage(true,true);'>�ر�</button></td></tr></tbody>
					</table>
				</div>
		</html:form>
		 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
				if (document.getElementById('tempDiv2')) {
					document.getElementById('tempDiv2').style.display='none' ;
				}
				
	         	alert("�����ɹ���");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("" + $('message').value);
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
