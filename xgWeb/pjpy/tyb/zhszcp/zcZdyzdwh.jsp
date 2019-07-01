

<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		</head>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script type="text/javascript">	  
	function selectAllByzc(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyTybZctjsz" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
		
		<input type="hidden" id="pt" value="pt"/>
		<input type="hidden" id="message" name="message" value="${message }"/>
		
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
		<div class="toolbox">
			  <!-- 按钮 -->
			  <logic:equal value="yes" name="writeAble">
			  <div class="buttonbox">
			    <ul>
			     <li>
			     	<a href="#" class="btn_zj" id="btn_dc" onclick="showTopWin('pjpy_tyb_zczdyzdAdd.do?doType=add',600,480)">
					增加
				</a>
			     </li>
			     <li>
				<a href="#" class="btn_xg" id="btn_dc" onclick="modiAndDel('pjpy_tyb_zczdyzdAdd.do?doType=edit&pk=','modi','600','480')">
					修改
				</a>
			     </li>
			     <li>
			     <a href="#" class="btn_sc" id="btn_dc" onclick="deletedata('pjpy_tyb_zczdyzdwh.do?act=del')">
					删除
				</a>
			     </li>
			    </ul>
			  </div>
			  </logic:equal>
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <button type="button" class="" id="search_go"
									onclick="refreshForm('pjpy_tyb_zczdyzdwh.do?act=qry');">
									查询
								</button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz" 
		              	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
		      		<th align="left" width="70px">综测项目</th>
		      		<td align="left">
		      			<html:select property="queryequals_tabname" styleId="queryequals_tabname">
									<html:option value=""></html:option>
									<html:options collection="dmList" property="dm" labelProperty="mc"/>
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		      		</td>
					<th>&nbsp;</th>
		      		<td>&nbsp;</td>
		      		<th>&nbsp;</th>
		      		<td>&nbsp;</td>
		        </tr>
			</tbody>
			</table>
		  </div>	
		
		<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
						<font color="blue">提示：单击表头可以排序; 双击可以查看详细信息</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			   
			  <logic:notEmpty name="rs">
			  <table summary="" class="dateline" align="center" width="100%">
			    <thead>
			     <tr align="center" style="cursor:hand">
								<td align="left">
									<input type="checkbox" style="display: none;"/>
									<input type="checkbox" name="cb" onclick="selectAllByzc()" style="height:17.5px" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap align="left">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
			    </thead>
			    <tbody>
			 <logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_tyb_zczdyzdAdd.do?doType=view&pk=','modi','550','450')">
								<td align="left">
									<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px"
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
			  <!--分页显示-->
			  <jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyZctjszActionForm"></jsp:include>
			  <!--分页显示-->
			  </logic:notEmpty>
			</div>	
	</div>
	</html:form>
	<logic:present name="result">
				<script>
					alert("" + $('message').value);
					document.getElementById('search_go').click();
				</script>
		</logic:present>
</body>
</html>