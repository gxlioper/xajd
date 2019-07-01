<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">
	function choiceDisabled(){
		var userType = $('userType').value;
		
		if('stu' == userType){
			$('xhs').disabled = true;
		}else if('xy' == userType){
			$('xy').disabled = true;
		}
	}
		
	function dyzz(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var yzchk=true;
		for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked){
					yzchk =false;
					break;
				}
		}
		if(yzchk==true){
			alert("请勾选需批量转入的学生");
			return;
		}
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------转为预备党员时间---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "时间:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveYbdy()';>确定</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>关闭</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function time(id){
		return showCalendar(id,'y-mm-dd');
	}
	
	function saveYbdy() {
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("请确定转为预备党员时间");
				return false;
			}
		}
		var url = "/xgxt/czxxDtjsDyxx.do?method=fzdxManage&doType=zz";
		showTips('处理数据中，请等待......');
		refreshForm(url);
	}
	
	</script>
	</head>
	
	<body onload="choiceDisabled();">
		<html:form action="/czxxDtjsDyxx" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" name="url" id="url" value="/xgxt/czxxDtjsDyxx.do?method=fzdxManage&doType=zhdj" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			
			<logic:equal name="userType" value="stu">
				<input type="hidden" name="xh" value="${userName }" />
			</logic:equal>
			
			<logic:equal name="userType" value="xy">
				<input type="hidden" name="xydm" value="${userDep }" />
			</logic:equal>
			
			<!-- 通用 -->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /></font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
						<logic:equal value="yes" name="writeAble">
						<div class="toolbox">
						<div class="buttonbox">
							<ul>
								<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/czxxDtjsDyxx.do?method=fzdxUpdate',800,550);">增加</a></li>
								<li><a href="#" class="btn_xg" onclick="showInfo('/xgxt/czxxDtjsDyxx.do?method=fzdxUpdate','update','800','550')">修改</a></li>
								<li><a href="#" class="btn_sc" onclick="sumitInfo('/xgxt/czxxDtjsDyxx.do?method=fzdxManage','del')">删除</a></li>
									<!-- 常州信息 -->
								<logic:equal name="xxdm" value="12317">
								<li><a href="#" class="btn_zj" onclick="dyzz()">批量转入预备党员</a></li>
								</logic:equal>
									<!-- 通用 -->
								<logic:notEqual name="xxdm" value="12317">
								<li><a href="#" class="btn_zj" onclick="djzh('jjfzManage')">转换等级</a></li>
								</logic:notEqual>
								
								<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>
							</ul>
						</div>
						</div>
						</logic:equal>
						
					<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
		              		<input type="hidden" name="go" value="a" />
		              		<button type="button" class="btn_cx" id="search_go" 
		              		onclick="allNotEmpThenGo('/xgxt/czxxDtjsDyxx.do?method=fzdxManage');">
		              		查 询
		              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			重 置
		             		 </button>
		            		</div>
		          		</td>
		       			</tr>
		     			</tfoot>
		              	<tbody>
		              	<tr>
							<th>学年</th>
							<td><html:select property="xn" style="width: 120px" styleId="xn"
											onchange="">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
							</td>
							<th>年度</th>
							<td><html:select property="nd" style="width: 60px" styleId="nd"
											onchange="">
											<html:options collection="ndList" property="nd" labelProperty="nd" />
										</html:select></td>
							<th>学期</th>
							<td><html:select property="xq" style="width: 60px" onchange="" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select></td>
		              	</tr>
		         		<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width: 150px" styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select></td>
		         			<th>专业</th>
							<td><html:select property="zydm" style="width: 150px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select></td>
		         			<th>班级</th>
		         			<td><html:select property="bjdm" style="width: 150px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select></td>
		         		</tr>
		         		<tr>
							<th>年级</th>
		              		<td><html:select property="nj" style="width: 60px" onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select></td>			
							<th>学号</th>
		         			<td><html:text property="xh" style="" maxlength="20" styleId="xhs"/></td>
		         			<th>姓名</th>
		         			<td><html:text property="xm" style="" maxlength="20" styleId="xm"/></td>
		             	</tr> 	
		         		<tr>
		         			<logic:equal name="xxdm" value="11057">
								<th>状态</th>
							</logic:equal>
							<logic:notEqual name="xxdm" value="11057">
								<th>在职状态</th>
							</logic:notEqual>
							<td><html:select property="zzzt" style="width:60px" styleId="zzzt">
											<html:option value="all">&nbsp;</html:option>
											<html:option value="yes">是</html:option>
											<html:option value="no">否</html:option>
										</html:select></td>
		         			<td colspan="4"></td>
		         		</tr>
		              	</tbody>
						</table>
						</div>
				
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
			  <table summary="" class="dateline" width="100%">
			    <thead>
			      <tr>
			        <td>
						<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
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
			      <logic:iterate name="rs" id="s" indexId="index">
				      <tr onclick="rowOnClick(this);" style="cursor:hand" 
						ondblclick="showInfo('/xgxt/czxxDtjsDyxx.do?method=fzdxUpdate','view','800','550')">
					<td align="center">
						<input type="checkbox" id="checkVal" name="checkVal" 
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
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=czxxDtjsForm"></jsp:include>
			  <!--分页显示-->
			  </logic:notEmpty>
			</div></div>	
			<div id="tmpdiv1" style="display: none">
				<div class="tab">
					<table class="formlist">
						<tr>
							<th>转换等级</th>
							<td>
								<html:select property='zhdj'  styleId='select_zhdj' style='width: 156px' onchange='chZhdj(this.value)'>
									<html:option value="">----请选择----</html:option>
									<html:options collection="zhList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>时间</th>
							<td><input type='text' name='zhsj' id='zhsj' style='width: 150px' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='readonly'/></td>
						</tr>
				
						<tr>
							<td colspan='2' align='right'>
							<button type="button" onclick='saveZhdj()'>确定</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
			</logic:empty></html:form>
					
<!--================================================================================================						-->
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
