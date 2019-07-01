<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>	
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript">
					//var W=api.get("parentDialog",1);
					//alert(api.get("parentDialog"));
					 var W;
					 var api = frameElement.api;
						if (api){
							if (api.get('childDialog')){
								W=api.get('parentDialog')
							} else {
								W = api.opener;
							}
						} else if (parent.window){
							W=parent.window;						
						}
			function sendInfo(){
				if(jQuery("#isZybjXz").val() == "true"){
                    if(window.opener == undefined){

                        W.document.getElementById('zybjmc').value = curr_row.getElementsByTagName('input')[2].value;
                        W.document.getElementById('zybj').value = curr_row.getElementsByTagName('input')[1].value;
                        W.document.getElementById('xymc').value = curr_row.getElementsByTagName('input')[6].value;
                        W.document.getElementById('xydm').value = curr_row.getElementsByTagName('input')[5].value;
                        W.document.getElementById('zymc').value = curr_row.getElementsByTagName('input')[4].value;
                        if(W.document.getElementById('zydm')){
                            W.document.getElementById('zydm').value = curr_row.getElementsByTagName('input')[3].value;
                        }
                        W.document.getElementById('nj').value = curr_row.getElementsByTagName('input')[0].value;
						Close();
                    }else{

                        window.opener.document.getElementById('zybjmc').value = curr_row.getElementsByTagName('input')[2].value;
                        window.opener.document.getElementById('zybj').value = curr_row.getElementsByTagName('input')[1].value;
                        window.opener.document.getElementById('xymc').value = curr_row.getElementsByTagName('input')[6].value;
                        window.opener.document.getElementById('xydm').value = curr_row.getElementsByTagName('input')[5].value;
                        window.opener.document.getElementById('zymc').value = curr_row.getElementsByTagName('input')[4].value;
                        if(window.opener.document.getElementById('zydm')){
                            window.opener.document.getElementById('zydm').value = curr_row.getElementsByTagName('input')[3].value;
                        }
                        window.opener.document.getElementById('nj').value = curr_row.getElementsByTagName('input')[0].value;
                        Close();
                    }
				} else {
                    if(window.opener == undefined){
                        if(W.document.getElementById('bjmc')){

                            W.document.getElementById('bjmc').value = curr_row.getElementsByTagName('input')[2].value;
                            W.document.getElementById('bjdm').value = curr_row.getElementsByTagName('input')[1].value;
                            Close();
                        }
                    }else{
                        if(window.opener.document.getElementById('bjmc')){

                            window.opener.document.getElementById('bjmc').value = curr_row.getElementsByTagName('input')[2].value;
                            window.opener.document.getElementById('bjdm').value = curr_row.getElementsByTagName('input')[1].value;
                            Close();
                        }
                    }
                }
			}

			function validate(){
				if(W.document.getElementById('nj')){
					var nj = W.document.getElementById('nj').value;
					var xydm = W.document.getElementById('xydm').value;
					var zydm = W.document.getElementById('zydm').value;
				}
			}

			//初始化
			function onShow(){ 
				//对不以学工为准的字段进行不能修改处理
				var zdpzstr = jQuery('#zdpzstr').val();
				var array = zdpzstr.split("!!@@!!");	
				if (array != null && array.length > 0) {
					for (var i=0;i<array.length;i++) {
						if (document.getElementById(array[i])) {
							document.getElementById(array[i]).disabled = true;
						}
					}
				}

				titleLoad('xy');
				titleLoad('zy');

				xyDisabled('xy');
			}
				
			function titleLoad(id){

				if(jQuery("#"+id)){
				
					jQuery("#"+id).children("option").each(function(){
						jQuery(this).attr("title",jQuery(this).text());
					});
				}
			}

			function jzzy(){
				setTimeout("titleLoad('zy')",500);
			}
			
			jQuery(function(){
				onShow();
			})
			
		</script>
	</head>
	<body >
		<center>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>班级选择</a>
				</p>
			</div>
			<!-- 隐藏域 -->
		<%@ include file="/comm/hiddenValue.jsp"%>
		
			<html:form action="/commXgInfo.do" method="post">
				<input type="hidden" name="zdpzstr" id="zdpzstr" value="${zdpzstr }"/>
				<input type="hidden" id="isZybjXz" value="${isZybjXz }"/>
				<div class="searchtab">
					<table width="90%" class="" border="0">
						<tbody>
							<tr>
									<th>年级</th>
									<td>
										<html:select property="nj" style="width: 80px" styleId="nj" onchange="onChangJcsj('nj','xy','zy','','nj','0','1');">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>	
									</td>
									<th>院系</th>
									<td>
											<html:select property="xydm" style="width: 180px" styleId="xy" 
												onchange="onChangJcsj('nj','xy','zy','','xy','0','1');">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
									</td>
									<th>专业</th>
									<td>
										<html:select property="zydm" style="width: 180px" styleId="zy" >
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
								</tr>
							
						</tbody>
						
						<tfoot>
			        		<tr>
			          			<td colspan="6">
			            		<div class="btn">
			              		<input type="hidden" name="go" value="a" />
			              		<button type="button" id="search_go" 
			              		onclick="document.forms[0].go.value='go';refreshForm('/xgxt/commXgInfo.do?method=xzBj&isZybjXz='+jQuery('#isZybjXz').val());this.disabled=true;">
			              			查询
			              		</button>
			             		 <button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
			              			重置
			             		 </button>
			            		</div>
			          		</td>
			       			</tr>
			     		</tfoot>
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
								查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序</font> 
							</span>
						</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									
									<td onclick="tableSort(this)">
										年级
									</td>
									
									<td onclick="tableSort(this)">
										班级名称
									</td>
									
									<td onclick="tableSort(this)">
										专业
									</td>
									
									<td onclick="tableSort(this)">
										<bean:message key="lable.xb" />
									</td>
									<%--<logic:equal value="10698" name="xxdm">
										<td onclick="tableSort(this)">
											书院
										</td>
									</logic:equal>--%>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
									
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendInfo();">
									
									<td align="left">
										<logic:iterate id="v" name="s" offset="0">
										<input type="hidden" value="${v }"/>
									</logic:iterate>
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
									</td>
										<td>
										<logic:iterate id="v" name="s" offset="2" length="1">${v}</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="4" length="1">${v}</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="6" length="1">${v}</logic:iterate>
									</td>
									<%--<logic:equal value="10698" name="xxdm">
										<td>
											<logic:iterate id="v" name="s" offset="8" length="1">${v}</logic:iterate>
										</td>
									</logic:equal>--%>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commForm"></jsp:include>
						<!--分页显示-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
				</logic:notEmpty>
				</div>
			</html:form>
		</center>
	</body>
</html>
