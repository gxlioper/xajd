<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script language="javascript">
		function modi(url){
			
			if(curr_row != null){
				alert(curr_row.getElementsByTagName('input')[0].value);
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function view(url){
			
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);

		}
		
		function checkUpdate(){
			
			var fsArr=document.getElementsByName("fsArr");
			var fsHidArr=document.getElementsByName("fsHidArr");
			var flag=false;
			for(var i=0;i<fsArr.length;i++){
				if(fsArr[i].value!=fsHidArr[i].value){
					flag=true;
				}
			}
			
			if(flag){
				confirmInfo("国防教育课程分已修改，是否需要保存？",function(t){
					if(t=="ok"){
						refreshForm('/xgxt/cdtyGfjy.do?method=kcfManage&doType=save');
					}else{
						
						allNotEmpThenGo('/xgxt/cdtyGfjy.do?method=kcfManage');
					}
				});
			}else{
				allNotEmpThenGo('/xgxt/cdtyGfjy.do?method=kcfManage');
			}
		}
		
		/**
			 * 检测数字可以带小数点
			 * @param obj
			 * @return
			 */
			function checkInputNum(obj){
				 //先把非数字的都替换掉，除了数字和.
				obj.value = obj.value.replace(/[^\d.]/g,"");
				//必须保证第一个为数字而不是.
				obj.value = obj.value.replace(/^\./g,"");
				//保证只有出现一个.而没有多个.
				obj.value = obj.value.replace(/\.{2,}/g,".");
				//保证.只出现一次，而不能出现两次以上
				obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			}
					
		function searchReset() {

			var input = document.getElementsByTagName('input');
			var select = document.getElementsByTagName('select');
						
			for (var i = 0;i<input.length;i++) {
				
				if (input[i].type != 'hidden' && input[i].disabled != true 
					&& input[i].type != 'checkbox' 
					&& (!input[i].readOnly||input[i].className=="jssj")
					&& input[i].id !="pageno"
					&& input[i].id !="pagesize" 
					&& input[i].className=="gd")
					input[i].value='';
			}
			for (var i = 0;i<select.length;i++) {
				if (select[i].disabled != true && select[i].options.length > 0 && select[i].options(0).value == "")
				select[i].value='';
			}
}

		jQuery(function(){
			dwr.engine.setAsync(false);
			initZyList();
			initBjList();
		
			if(jQuery("#hidxy").val()!=""){
				jQuery('#xy').val(jQuery("#hidxy").val());
			}
			jQuery('#zy').val(jQuery("#hidzy").val());
			jQuery('#bj').val(jQuery("#hidbj").val());
			
			dwr.engine.setAsync(true);
		})

		</script>
	</head>
	<body>

		<html:form action="/cdtyGfjy" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="realTable" id="realTable"
				value="xg_jxgl_gfjyfb" />
			<input type="hidden" name="tableName" id="tableName"
				value="xg_jxgl_gfjyfb" />
			<input type="hidden" name="viewName" id="viewName"
				value="xg_jxgl_gfjyfb" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="isBzr" id="isBzr" value="${BzrQx}" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<input type="hidden" name="userName" id="userName" value='${userName}' />
			
			<input type="hidden" name="hidxy" id="hidxy" value='${xydm}' />
			<input type="hidden" name="hidzy" id="hidzy" value='${zydm}' />
			<input type="hidden" name="hidbj" id="hidbj" value='${bjdm}' />
			<input type="hidden" name="ok" id="ok" value='${ok}' />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="refreshForm('/xgxt/cdtyGfjy.do?method=kcfManage&doType=save')"
								class="btn_ccg"> 保存 </a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="checkUpdate()">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" styleClass="gd"/>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" styleClass="gd"/>
								</td>
								<th>
									年级
								</th>
								<td>
									
									<html:select property="queryequals_nj" styleId="queryequals_nj" style="width:150px"
										value="${nd}" disabled="true" >
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									<input type="hidden" name="nj" id="nj" value="${nd}"/>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="queryequals_xydm" 
											disabled="true" value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" styleId="xy" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
										<html:option value=""></html:option>
										<logic:notEmpty name="bjList">
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
										</logic:notEmpty>
									</html:select>
								</td>
							</tr>


						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rsArrList">
							<font color="red">未找到任何记录！</font>
						</logic:empty> 
					</span>
				</h3>


				<div class="con_overlfow">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">

								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsArrList">
								<logic:iterate name="rsArrList" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										>
										<td style="display:none">

											<input type="hidden" name="xhArr"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>" />
											<input type="hidden" name="njArr"
												value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v" /></logic:iterate>" />

										</td>
										<logic:iterate id="v" name="s" offset="0" length="7">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="7">
											<td nowrap>
												<input type="text" name="fsArr" id="" value="${v}" size="10"
													maxlength="10" onblur="checkInputNum(this)" />
												<input type="hidden" name="fsHidArr" id="" value="${v}"
													size="10" />
											</td>
										</logic:iterate>

									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- 补空行 -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- 补空行 end-->
						</tbody>
					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=cdtyGfjyForm"></jsp:include>
				<script type="text/javascript">
											$('choose').className="hide";
									</script>


			</div>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
