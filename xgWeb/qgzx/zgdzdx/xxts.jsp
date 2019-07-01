<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/qgzxFunction.js"></script>
	<script>
		function checkTime(){
			var result = true;
			result = checkSjTj('querygreaterequal_kssj','开始时间段开始时间','querylessequal_kssj','结束时间');
			if(result){
				result = checkSjTj('querygreaterequal_jssj','结束时间段开始时间','querylessequal_jssj','结束时间');
			}
			return result;
		}
		function del(url){
			var RowsStr="!!";		
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("请选择要批量设置的记录！");
				return false;
			}
			
			if (!confirm("确定要删除所选记录？")){
				return false;
			}
			refreshForm(url);
		}
	</script>
</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/qgzxZgdzdx" method="post">
			<input type="hidden" name="realTable" value="${realTable }"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
					  		<li> <a href="#" onclick="showTopWin('/xgxt/qgzxZgdzdx.do?method=xxtsUpdate&doType=add','500','350');" class="btn_zj">增 加</a> </li>
							<li> <a href="#" onclick="showInfo('/xgxt/qgzxZgdzdx.do?method=xxtsUpdate&doType=modi','update','500','350');" class="btn_xg">修 改</a> </li>
							<li> <a href="#" onclick="del('/xgxt/qgzxZgdzdx.do?method=xxts&doType=del');" class="btn_sc">删 除</a> </li>
							<li> <a href="#" onclick="impAndChkData();" class="btn_dr">导入数据</a> </li>
					  </logic:equal>
						<li> <a href="#" onclick="expData('/xgxt/qgzxZgdzdx.do?method=xxts&doType=expData');" class="btn_dc">导出数据</a> </li>
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
									onclick="if(checkTime()){allNotEmpThenGo('/xgxt/qgzxZgdzdx.do?method=xxts&doType=query')}">
									查询
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>开始时间段</th>
						<td>
							<html:text property="querygreaterequal_kssj" style="width:90px"
							onclick="return showCalendar('querygreaterequal_kssj','y-mm-dd');" 
							styleId="querygreaterequal_kssj" onblur="dateFormatChg(this)" 
							
							/> -
							<html:text property="querylessequal_kssj"  style="width:90px"
							onclick="return showCalendar('querylessequal_kssj','y-mm-dd');" 
							styleId="querylessequal_kssj" onblur="dateFormatChg(this)" 
							
							/>
						</td>
						<th>结束时间段</th>
						<td>
							<html:text property="querygreaterequal_jssj" style="width:90px"
							onclick="return showCalendar('querygreaterequal_jssj','y-mm-dd');" 
							styleId="querygreaterequal_jssj" onblur="dateFormatChg(this)" 
							readonly="true"
							/> -
							<html:text property="querylessequal_jssj"  style="width:90px"
							onclick="return showCalendar('querylessequal_jssj','y-mm-dd');" 
							styleId="querylessequal_jssj" onblur="dateFormatChg(this)" 
							readonly="true"
							/>								
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
<!--						<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px"/>-->
					</td>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
						<td id="${tit.en}"
							onclick="tableSort(this)">
							${tit.cn}
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;">
							<td align=center>
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="cbv" name="primarykey_cbv" value="<bean:write name="v"/>" />
						   		</logic:iterate>
						    </td>
							<logic:iterate id="v" name="s" offset="1" >
								<td>
									${v}
								</td>
							</logic:iterate>
							
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxZgdzdxForm"></jsp:include>
			 	<!--分页显示-->
				
				</logic:notEmpty>
				</div>
		</html:form>
		 
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("操作失败 ！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>