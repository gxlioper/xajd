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

			//��ʼ��
			function onShow(){ 
				//�Բ���ѧ��Ϊ׼���ֶν��в����޸Ĵ���
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
					<em>���ĵ�ǰλ��:</em><a>�༶ѡ��</a>
				</p>
			</div>
			<!-- ������ -->
		<%@ include file="/comm/hiddenValue.jsp"%>
		
			<html:form action="/commXgInfo.do" method="post">
				<input type="hidden" name="zdpzstr" id="zdpzstr" value="${zdpzstr }"/>
				<input type="hidden" id="isZybjXz" value="${isZybjXz }"/>
				<div class="searchtab">
					<table width="90%" class="" border="0">
						<tbody>
							<tr>
									<th>�꼶</th>
									<td>
										<html:select property="nj" style="width: 80px" styleId="nj" onchange="onChangJcsj('nj','xy','zy','','nj','0','1');">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>	
									</td>
									<th>Ժϵ</th>
									<td>
											<html:select property="xydm" style="width: 180px" styleId="xy" 
												onchange="onChangJcsj('nj','xy','zy','','xy','0','1');">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
									</td>
									<th>רҵ</th>
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
			              			��ѯ
			              		</button>
			             		 <button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
			              			����
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
					    	��ѯ���&nbsp;&nbsp;
								<font color="red">δ�ҵ��κμ�¼��</font> 
					    </span>
					    </h3>
					 </logic:empty>
					<logic:notEmpty name="rs">
						<h3 class="datetitle_01">
							<span>
								��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font> 
							</span>
						</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									
									<td onclick="tableSort(this)">
										�꼶
									</td>
									
									<td onclick="tableSort(this)">
										�༶����
									</td>
									
									<td onclick="tableSort(this)">
										רҵ
									</td>
									
									<td onclick="tableSort(this)">
										<bean:message key="lable.xb" />
									</td>
									<%--<logic:equal value="10698" name="xxdm">
										<td onclick="tableSort(this)">
											��Ժ
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
						
						<!--��ҳ��ʾ-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commForm"></jsp:include>
						<!--��ҳ��ʾ-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
				</logic:notEmpty>
				</div>
			</html:form>
		</center>
	</body>
</html>
