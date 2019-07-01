<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>

<script language="javascript" src="js/jsFunction.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script language="javascript" src="js/qtsjFunction.js"></script>
<script language="javascript" src="js/xgutil.js"></script>
<script type="text/javascript">	  
	function loadchange(){
		
	var table = val('bm');
			var tab = table != '' ? table : ele('ul1').children[0].children[0].id;
			ele(tab).parentNode.className = "ha";
			document.getElementById("bm").value=tab;
	
    }
    
    function changePage(obj, num){
    	obj.parentNode.className = 'ha';
	    	
	    	var elementLi = ele('ul1').children;
	    	for(li in elementLi){
	    		if(li.id != obj.id){
	    			li.className = 'ha';
	    		}
	    	}
			//id = id.replace("ul", "");
			$('bm').value = $('bm' + num).value;
		$('bm').value = $('bm' + num).value;
		refreshForm('pjpy_tyb_zhszcpSjsh.do?dmlb=2&dm=' + $('dm').value+ '&bm='+$('bm').value);
    }
</script>
</head>
<body onload="xyDisabled('xy');loadchange();">
	<html:form action="/pjpyTybZhszcp" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName }" />
		<input type="hidden" name="shjb" id="shjb"
			value="${shjb }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" id="bm" name="bm" value="${bm }" />
		<input type="hidden" id="dm" name="dm" value="${dm }" />
		<input type="hidden" id="dmlb" name="dmlb" value="${dmlb }" />
		<input type="hidden" id="shjb" name="shjb" value="${shjb }" />
		<input type="hidden" id="pt" value="pt"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<div class="compTab" id="card" style="width: 100%">
			<div class="comp_title" id="div1">
				<ul id="ul1">
					<logic:notEmpty name="pages">
						<logic:iterate id="card" name="pages" scope="request" indexId="num">
							<li>
								<a href="#"  onclick="changePage(this,${num })" id="${card.bm}">
									<span>${card.mc}</span>
								</a>
								<input type="hidden" name="bm${num }" id="bm${num }" value="${card.bm}"/>
							</li>	
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="pages">
						<p align="center">
							暂无记录!
						</p>
					</logic:empty>
				</ul>				
			</div>
		</div>
	
			<div class="toolbox">
		  <!-- 按钮 -->				  
		 
			  <!--读写权-->
			  <logic:equal value="yes" name="writeAble">
			   <div class="buttonbox">
		    	<ul>
					<li>
					<a href="#" class="btn_shtg" id="btn_tg" onclick="dataBatch('pjpy_tyb_zhszcpSjsh.do?act=tg')">
						通过
					</a>
					</li>
					<li>
					<a href="#" class="btn_shbtg" id="btn_btg" onclick="dataBatch('pjpy_tyb_zhszcpSjsh.do?act=btg')">
						不通过
					</a>
					</li>
					<li>
					<a href="#" class="btn_sh" id="btn_dgsh" onclick="modiAndDel('pjpyTybZhszcp.do?method=zhszcpShOne&act=sh&bm='+val('bm')+'&shjb=' + val('shjb') + '&dmlb=' + val('dmlb') + '&dm=' + val('dm') +'&pkValue=','modi','700','600')">
						单个审核
					</a>
					</li>
					</ul>					
		  </div>
			  </logic:equal>	
			
		  <div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>			
				  <td>
					<!--操作开关提示-->
					<logic:notEmpty name="yhInfo">
						<font color="red">提示：${ yhInfo}</font>
					</logic:notEmpty>
					<!--end操作开关提示-->
				  </td>			  
		          <td colspan="5">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="" id="search_go"
									onclick="refreshForm('pjpy_tyb_zhszcpSjsh.do?act=qry');">
									查询
								</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
												重置
											</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
			 	<tr>
			 		<th>
			 			年级
			 		</th>
			 		<td>
			 			<html:select property="nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
			 		</td>
			 		<logic:equal value="nd" name="pjzq">
									<th>年度</th>
									<td>
									<html:select property="nd" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									</td>
									<th></th>
									<td></td>
					</logic:equal>
					<logic:notEqual value="nd" name="pjzq">
						<logic:equal value="xn" name="pjzq">
									<th>学年</th>
									<td>
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									</td>
									<th></th>
									<td></td>
						</logic:equal>
						<logic:notEqual value="xn" name="pjzq">
							<logic:equal value="xq" name="pjzq">
									<th>学年</th>
									<td>
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									</td>
									<th>学期</th>
									<td>
									<html:select property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									</td>
								</logic:equal>
								
						</logic:notEqual>
					</logic:notEqual>
			 	</tr>
			 	<tr>
			 		<th><bean:message key="lable.xsgzyxpzxy" /></th>
			 		<td><html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()" style="width:170px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
					<th>专业</th>
					<td><html:select property="zydm" styleId="zy"
									onchange="initBjList()" style="width:170px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
					<th>班级</th>
					<td><html:select property="bjdm" styleId="bj" style="width:170px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select></td>
			 	</tr>
			 	<tr>
			 		<th>学号</th>
			 		<td>
			 		
								<html:text property="xh" styleId="xh" maxlength="10"></html:text></td>
								<th>姓名</th>
									<td><html:text property="xm"  styleId="xm" maxlength="19"></html:text></td>
					<th></th>
					<td></td>
			 	</tr>
			 	<tr>
			 		
			 		<logic:equal value="3" name="shjb">
								<logic:equal value="true" name="isFdy">
									<th>辅导员审核</th>
									<td>
									<html:select property="fdysh" styleId="fdysh">
										<html:option value=""></html:option>
										<html:options collection="chkList" property="en" labelProperty="cn"/>
									</html:select>
									</td>
									<th>
									<bean:message key="lable.xsgzyxpzxy" />审核
									</th>
									<td>
								<html:select property="xysh" styleId="xysh">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn"/>
								</html:select>
								</td>
								
								</logic:equal>
								<logic:equal value="false" name="isFdy">
								<logic:equal value="xy" name="userType">
								<th>
									<bean:message key="lable.xsgzyxpzxy" />审核
									</th>
									<td>
									<html:select property="xysh" styleId="xysh">
										<html:option value=""></html:option>
										<html:options collection="chkList" property="en" labelProperty="cn"/>
									</html:select>
									</td>
									
									
								</logic:equal>
								</logic:equal>								
					</logic:equal>
					<logic:equal value="2" name="shjb">
							<logic:equal value="xy" name="userType">
							<th>
									<bean:message key="lable.xsgzyxpzxy" />审核</th>
									<td>
								<html:select property="xysh" styleId="xysh">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn"/>
								</html:select>
								</td>
								
							</logic:equal>
					</logic:equal>
					<logic:notEmpty name="shjb">
							<logic:notEqual value="0" name="shjb">
								<th>学校审核</th>
								<td>
								<html:select property="xxsh" styleId="xxsh">
									<html:option value=""></html:option>
									<html:options collection="chkList" property="en" labelProperty="cn"/>
								</html:select>
								</td>
								
								</logic:notEqual>
								<logic:equal value="3" name="shjb">
									<logic:equal value="false" name="isFdy">
									
								<logic:equal value="xy" name="userType">
									<th></th>
									<td></td>
								</logic:equal>
								</logic:equal>
								</logic:equal>
					</logic:notEmpty>
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
							记录数： ${rsNum } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序; 双击可以查看详细信息;</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpyTybZhszcp.do?method=zhszcpShOne&act=view&bm='+val('bm')+'&shjb=' + val('shjb') +'&pkValue=','modi','700','600')" bgcolor="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>">
								<td align="center">
									<input type="checkbox" id="pkV" name="primarykey_cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZhszcpwhActionForm"></jsp:include>
			 	<!--分页显示-->
				</logic:notEmpty>
				</div>
		</div>
	</html:form>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
	<center>
		<logic:equal value="yes" name="writeAble">
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				
			</div>
			<script language="javascript">
                  document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                  document.getElementById("btn").style.width = "96%";
                  window.setInterval("initBTNTool('btn')",1);
                </script>
		</logic:equal>
	</center>
	<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!");				
				</script>
			</logic:equal>
		</logic:present>
</body>
