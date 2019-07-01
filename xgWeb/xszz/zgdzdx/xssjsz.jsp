<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

<script type="text/javascript" src="js/xszz/xszzFunction.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
</head>
<body onload="xyDisabled('xy')">
	<html:form action="/zgdzdx_xszz" method="post">
		<input type="hidden" name="userType" value="${userType }"/>
		<input type="hidden" name="userName" value="${userName }"/>
		<input type="hidden" name="userDep" value="${userDep }"/>
	
		<logic:equal value="xy" name="userType" scope="session">
			<html:hidden property="queryequals_xydm" value="${userDep }"/>
		</logic:equal>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
		 <!-- 按钮 -->
		 <logic:equal name="writeAble" value="yes">
		 <div class="buttonbox">
		    <ul>
			  <li> <a href="#" onclick="if(confirm('您确定要初始化吗？')) refreshForm('/xgxt/zgdzdx_xszz.do?method=xssjsz&doType=reset');return false;" class="btn_cs"> 初始化 </a> </li>
		      <li> <a href="#" onclick="showInfo('/xgxt/zgdzdx_xszz.do?method=xssjszOne','update','450','280');;return false;" class="btn_csh"> 单个设置 </a> </li>
			  <li> <a href="#" onclick="showPlszDiv();return false;" class="btn_sz"> 批量设置 </a> </li>
		    </ul>
		 </div>
		 </logic:equal>
		<div class="searchtab">
		<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="2">
		            <div class="btn">
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="allNotEmpThenGo('/xgxt/zgdzdx_xszz.do?method=xssjsz&doType=query')">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
				<tbody>
					<tr>
						<th >
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />
							&nbsp;&nbsp;
							<html:select property="queryequals_xydm" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</div>
						</th>
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
		 		 	记录数： ${rsNum} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">提示：双击一行可以查看详细，单击表头可以排序</font>
				</logic:notEmpty>
		    </span>
		    </h3>
		    <logic:notEmpty name="rs">
				 <table summary="" class="dateline" align="" width="100%">
					<thead>
						<tr align="center" style="cursor:hand">
							<td nowrap>
								<input type="checkbox" name="cb" onclick="selectAll()"
									style="height:17.5px">
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" scope="request">
								<td id="${tit.en}" onclick="tableSort(this)" nowrap>
									${tit.cn}
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="showInfo('/xgxt/zgdzdx_xszz.do?method=xssjszOne','view','450','280');"
							style="cursor:hand;">
							<td align=center>
								<input type="checkbox" id="cbv" name="primarykey_cbv"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								<input type="hidden" value="<bean:write name="v" />">
							</td>
							<logic:iterate id="v" name="s" offset="1">
								<td align=center>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					</tbody>
				</table>
				<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=xszzZgdzdxActionForm"></jsp:include>
		</logic:notEmpty>
		</div>
		<div id="tempDiv"></div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="result">
		<script>
			alert('${message}');
			document.getElementById('search_go').click();
		</script>
	</logic:present>
</body>
</html>
