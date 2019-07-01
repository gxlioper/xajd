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
<script type="text/javascript" src="js/pjpy/typj.js"></script>
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
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li class="">
							<a href="#" onclick="SelectrwOption('zxszy_dd','/xgxt/typj.do?method=tjsz')">
								<span>奖学金</span>
							</a>
						</li>
						<li  class="ha">
							<a href="#" onclick="SelectrwOption('zxszy_sjd','/xgxt/typj.do?method=rychShsz')" id="${card.en}_a">
								<span>荣誉称号</span>
							</a>
						</li>
					</ul>				
				</div>
			</div>
			<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
							<li> <a href="#" onclick="saveAll('/xgxt/typj.do?method=rychShsz&doType=save');" class="btn_ccg">保存</a> </li>
							<li> <a href="#" onclick="showShjbList();" class="btn_sz">批量设置</a> </li>
					  </logic:equal>
							<li> <a href="#" onclick="expData('/xgxt/typj.do?method=rychShsz&doType=expData');" class="btn_dc">导出数据</a> </li>
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
									onclick="allNotEmpThenGo('/xgxt/typj.do?method=rychShsz&doType=query')">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>荣誉称号</th>
						<td>
							<html:select property="queryequals_rychdm" styleId="rychdm">
								<html:options collection="rychList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<th>审核级别</th>
						<td>	
							<html:select property="queryequals_shjb" styleId="queryequals_shjb">
								<html:option value=""></html:option>
								<html:options collection="shjbList" property="en" labelProperty="cn"/>
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
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序！</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" name="cb" id="cb" onclick="selectAll()" style="height:17.5px;display: none;" disabled="true"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
						<td id="${tit.en}"
							onclick="" nowrap>
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
						<logic:iterate id="v" name="s" offset="1" length="1">
							<td align=center>
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<logic:iterate id="v" name="s" offset="2" length="1">
							<td>
								<html:select property="save_shjb" value="${v }" styleId="shjb${indx}">
									<html:option value=""></html:option>
									<html:options collection="shjbList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=guizhdxForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>

				<div id="plszDiv" style="display:none">
					<table width='300' class='formlist'>
						<thead>
						<tr>
							<td colspan='2'>
							   请选择审核级别
							</td>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th>审核级别</th>
							<td>
								<html:select property="shjb" styleId="select_shjb">
									<html:option value=""></html:option>
									<html:options collection="shjbList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</tr>
						<tr><td align='center' colspan='2'><button class='button2'  onclick='plszShjb();'>确定</button> &nbsp;&nbsp;&nbsp;&nbsp;<button class='button2' onclick='hiddenMessage(true,true);'>关闭</button></td></tr></tbody>
					</table>
				</div>
		</html:form>
		 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
					if (document.getElementById('tempDiv2')) {
					document.getElementById('tempDiv2').style.display='none' ;
				}
				
	         	alert("操作成功！");
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
