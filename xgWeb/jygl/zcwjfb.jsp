<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
	
	function viewMoreinfo(doType){
		var url ="/xgxt/showmorezcwj.do?wjbt=";
		var pkValue ="";
		
		 if (doType == "view"){
			 pkValue = curr_row.getElementsByTagName("input")[0].value;
			 url += pkValue;
			 window.open(url);
		 }
	}
		
	function zcwjupdate(doType){
		var url ="/xgxt/zcwjupdate.do?doType=update&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 700, 600);
				return true;
			} else {
				return false;
			}		
		}	
	}	
		
	function add(){
		var wjbt=document.getElementById('wjbt1').value;
		
	    if((wjbt==null) || (wjbt == "")){
			alert("请输入文件标题及文件类型！");
			return false;
		}	
		
		if(frames('eWebEditor1').getHTML()){
			$('wjnr').value = frames('eWebEditor1').getHTML();
		}
		
		 	document.forms[0].action = "/xgxt/savezcwj.do?act=save";
		 	document.forms[0].submit();
		 	
    }
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/savezcwj.do" method="post">
			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<logic:notEmpty name="wjlxList">
						<logic:iterate id="v" name="wjlxList">
							<thead>
								<tr>
									<th colspan="6">
										<span>${v.lxmc }</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr style="cursor:hand">
									<logic:iterate id="tit" name="topTr" >
										<td id="<bean:write name="tit" property="en"/>">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<td align="center">
										操 作
									</td>
								</tr>
							</tbody>
							<logic:notEmpty name="rs">
								<logic:iterate id="m" name="rs">
									<tbody>
										<logic:equal value="${v.lxdm }" name="m" property="wjlx">
											<tr onmouseover="rowOnClick(this)" ondblclick="viewMoreinfo('view')" >
												<td>
													<input type="hidden" value="${m.wjbt }" />
													${m.wjbt }
												</td>
												<td>
													${m.lxmc }
												</td>
												<td>
													${m.fbr }
												</td>
												<td>
													${m.readtimes }
												</td>
												<td>
													${m.fbsj }
												</td>
												<td align="center">
													<a href="zcwjupdate.do?updateone=${m.fbsj }">修改</a>/
													<a href="zcwjdel.do?delone=${m.fbsj }&act=del">删除</a>
												</td>
											</tr>
										</logic:equal>
									</tbody>
								</logic:iterate>
							</logic:notEmpty>
						</logic:iterate>
					</logic:notEmpty>


					<!--  
				
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>国家文件</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--								<table summary="" class="dateline" align="" width="100%">--%>
<%--										<thead>--%>
<%--											<tr align="center" style="cursor:hand">--%>
<%--												<logic:iterate id="tit" name="topTr1" offset="2">--%>
<%--													<td id="<bean:write name="tit" property="en"/>">--%>
<%--														<bean:write name="tit" property="cn" />--%>
<%--													</td>--%>
<%--												</logic:iterate>--%>
<%--												<td align="center">--%>
<%--													操 作--%>
<%--												</td>--%>
<%--											</tr>--%>
<%--										</thead>--%>
<%--								<logic:notEmpty name="rs1">--%>
<%--										<tbody>--%>
<%--											<logic:iterate name="rs1" id="s">--%>
<%--												<tr onmouseover="rowOnClick(this)" style="cursor:hand"--%>
<%--													ondblclick="viewMoreinfo('view')">--%>
<%--													<logic:iterate id="v" name="s" offset="2">--%>
<%--														<td align="center">--%>
<%--															<bean:write name="v" />--%>
<%--															<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g"/></logic:iterate>" />--%>
<%--														</td>--%>
<%--													</logic:iterate>--%>
<%--													<td align="center">--%>
<%--														<a href="zcwjupdate.do?updateone=<bean:write name="v" />">修改</a>/--%>
<%--														<a--%>
<%--															href="zcwjdel.do?delone=<bean:write name="v" />&act=del">删除</a>--%>
<%--													</td>--%>
<%--												</tr>--%>
<%--											</logic:iterate>--%>
<%--										</tbody>--%>
<%--									</logic:notEmpty>--%>
<%--								</table>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>市级文件</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--								<table summary="" class="dateline" align="" width="100%">--%>
<%--										<thead>--%>
<%--											<tr align="center" style="cursor:hand">--%>
<%--												<logic:iterate id="tit" name="topTr2" offset="2">--%>
<%--													<td id="<bean:write name="tit" property="en"/>">--%>
<%--														<bean:write name="tit" property="cn" />--%>
<%--													</td>--%>
<%--												</logic:iterate>--%>
<%--												<td align="center">--%>
<%--													操 作--%>
<%--												</td>--%>
<%--											</tr>--%>
<%--										</thead>--%>
<%--								<logic:notEmpty name="rs2">--%>
<%--										<tbody>--%>
<%--											<logic:iterate name="rs2" id="s">--%>
<%--												<tr onmouseover="rowOnClick(this)" style="cursor:hand"--%>
<%--													ondblclick="viewMoreinfo('view')">--%>
<%--													<logic:iterate id="v" name="s" offset="2">--%>
<%--														<td align="center">--%>
<%--															<bean:write name="v" />--%>
<%--															<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g"/></logic:iterate>" />--%>
<%--														</td>--%>
<%--													</logic:iterate>--%>
<%--													<td align="center">--%>
<%--														<a href="zcwjupdate.do?updateone=<bean:write name="v" />">修改</a>/--%>
<%--														<a--%>
<%--															href="zcwjdel.do?delone=<bean:write name="v" />&act=del">删除</a>--%>
<%--													</td>--%>
<%--												</tr>--%>
<%--											</logic:iterate>--%>
<%--										</tbody>--%>
<%--										</logic:notEmpty>--%>
<%--									</table>--%>
<%--								--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>校级文件</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--								--%>
<%--									<table summary="" class="dateline" align="" width="100%">--%>
<%--										<thead>--%>
<%--											<tr align="center" style="cursor:hand">--%>
<%--												<logic:iterate id="tit" name="topTr3" offset="2">--%>
<%--													<td id="<bean:write name="tit" property="en"/>">--%>
<%--														<bean:write name="tit" property="cn" />--%>
<%--													</td>--%>
<%--												</logic:iterate>--%>
<%--												<td align="center">--%>
<%--													操 作--%>
<%--												</td>--%>
<%--											</tr>--%>
<%--										</thead>--%>
<%--										<logic:notEmpty name="rs3">--%>
<%--										<tbody>--%>
<%--											<logic:iterate name="rs3" id="s">--%>
<%--												<tr onmouseover="rowOnClick(this)" style="cursor:hand"--%>
<%--													ondblclick="viewMoreinfo('view')">--%>
<%--													<logic:iterate id="v" name="s" offset="2">--%>
<%--														<td align="center">--%>
<%--															<bean:write name="v" />--%>
<%--														<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g"/></logic:iterate>" />--%>
<%--														</td>--%>
<%--													</logic:iterate>--%>
<%--													<td align="center">--%>
<%--														<a href="zcwjupdate.do?updateone=<bean:write name="v" />">修改</a>/--%>
<%--														<a--%>
<%--															href="zcwjdel.do?delone=<bean:write name="v" />&act=del">删除</a>--%>
<%--													</td>--%>
<%--												</tr>--%>
<%--											</logic:iterate>--%>
<%--										</tbody>--%>
<%--										</logic:notEmpty>--%>
<%--									</table>--%>
<%--								--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>公告栏</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--									<table summary="" class="dateline" align="" width="100%">--%>
<%--										<thead>--%>
<%--											<tr align="center">--%>
<%--												<logic:iterate id="tit" name="topTr4" offset="2">--%>
<%--													<td id="<bean:write name="tit" property="en"/>">--%>
<%--														<bean:write name="tit" property="cn" />--%>
<%--													</td>--%>
<%--												</logic:iterate>--%>
<%--												<td align="center">--%>
<%--													操 作--%>
<%--												</td>--%>
<%--											</tr>--%>
<%--										</thead>--%>
<%--										<logic:notEmpty name="rs4">--%>
<%--										<tbody>--%>
<%--											<logic:iterate name="rs4" id="s">--%>
<%--												<tr onmouseover="rowOnClick(this)" style="cursor:hand"--%>
<%--													ondblclick="viewMoreinfo('view')">--%>
<%--													<logic:iterate id="v" name="s" offset="2">--%>
<%--														<td align="center">--%>
<%--															<bean:write name="v" />--%>
<%--														<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g"/></logic:iterate>" />--%>
<%--														</td>--%>
<%--													</logic:iterate>--%>
<%--													<td align="center">--%>
<%--														<a href="zcwjupdate.do?updateone=<bean:write name="v" />">修改</a>/--%>
<%--														<a--%>
<%--															href="zcwjdel.do?delone=<bean:write name="v" />&act=del">删除</a>--%>
<%--													</td>--%>
<%--												</tr>--%>
<%--											</logic:iterate>--%>
<%--										</tbody>--%>
<%--										</logic:notEmpty>--%>
<%--									</table>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>校园专场招聘会</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--								--%>
<%--									<table class="dateline" align="" width="100%">--%>
<%--										<thead>--%>
<%--											<tr align="center" style="cursor:hand">--%>
<%--												<logic:iterate id="tit" name="topTr5" offset="2">--%>
<%--													<td id="<bean:write name="tit" property="en"/>">--%>
<%--														<bean:write name="tit" property="cn" />--%>
<%--													</td>--%>
<%--												</logic:iterate>--%>
<%--												<td align="center">--%>
<%--													操 作--%>
<%--												</td>--%>
<%--											</tr>--%>
<%--										</thead>--%>
<%--										<logic:notEmpty name="rs5">--%>
<%--										<tbody>--%>
<%--											<logic:iterate name="rs5" id="s">--%>
<%--												<tr onmouseover="rowOnClick(this)" style="cursor:hand"--%>
<%--													ondblclick="viewMoreinfo('view')">--%>
<%--													<logic:iterate id="v" name="s" offset="2">--%>
<%--														<td align="center">--%>
<%--															<bean:write name="v" />--%>
<%--														<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g"/></logic:iterate>" />--%>
<%--														</td>--%>
<%--													</logic:iterate>--%>
<%--													<td align="center">--%>
<%--														<a href="zcwjupdate.do?updateone=<bean:write name="v" />">修改</a>/--%>
<%--														<a--%>
<%--															href="zcwjdel.do?delone=<bean:write name="v" />&act=del">删除</a>--%>
<%--													</td>--%>
<%--												</tr>--%>
<%--											</logic:iterate>--%>
<%--										</tbody>--%>
<%--										</logic:notEmpty>--%>
<%--									</table>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>生源介绍</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--									<table summary="" class="dateline" align="" width="100%">--%>
<%--										<thead>--%>
<%--											<tr align="center" style="cursor:hand">--%>
<%--												<logic:iterate id="tit" name="topTr6" offset="2">--%>
<%--													<td id="<bean:write name="tit" property="en"/>">--%>
<%--														<bean:write name="tit" property="cn" />--%>
<%--													</td>--%>
<%--												</logic:iterate>--%>
<%--												<td align="center">--%>
<%--													操 作--%>
<%--												</td>--%>
<%--											</tr>--%>
<%--										</thead>--%>
<%--										<logic:notEmpty name="rs6">--%>
<%--										<tbody>--%>
<%--											<logic:iterate name="rs6" id="s">--%>
<%--												<tr onmouseover="rowOnClick(this)" style="cursor:hand"--%>
<%--													ondblclick="viewMoreinfo('view')">--%>
<%--													<logic:iterate id="v" name="s" offset="2">--%>
<%--														<td align="center">--%>
<%--															<bean:write name="v" />--%>
<%--														<input type="hidden" value="<logic:iterate id="g" name="s" offset="0" length="1"><bean:write name="g"/></logic:iterate>" />--%>
<%--														</td>--%>
<%--													</logic:iterate>--%>
<%--													<td align="center">--%>
<%--														<a href="zcwjupdate.do?updateone=<bean:write name="v" />">修改</a>/--%>
<%--														<a--%>
<%--															href="zcwjdel.do?delone=<bean:write name="v" />&act=del">删除</a>--%>
<%--													</td>--%>
<%--												</tr>--%>
<%--											</logic:iterate>--%>
<%--										</tbody>--%>
<%--										</logic:notEmpty>--%>
<%--									</table>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
					
					
				-->
					<thead>
						<tr>
							<th colspan="6">
								<span>添加文件</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%">
								文件类型
							</th>
							<td colspan="5">
								<html:select name="map" property="wjlx">
									<html:option value=""></html:option>
									<html:options collection="wjlxList" property="lxdm" labelProperty="lxmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								文件标题
							</th>
							<td colspan="5">
								<input type="text" name="wjbt" id="wjbt1" style="width:95%"
									onkeyup="value=value.replace(/[+&%#]/g,'')" maxlength="200" />
							</td>
						</tr>
						<tr>
							<th>
								文件内容
							</th>
							<td colspan="5">
<%--								<html:textarea name="map" property="wjnr" style="width:95%"--%>
<%--									rows="20" onblur="checkLen(this,2000)" styleId="wjnr"/>--%>
									
								<html:hidden property="wjnr" styleId="wjnr" name="map"/>
								<input type="hidden" name="content1"/>
								
								<iframe id="eWebEditor1" src="BatEditor" frameborder="0"
									scrolling="no" width="100%" height="350"></iframe>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button onclick="add();">
										提 交
									</button>
									<button type="reset">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="inserted">
			<logic:equal name="inserted" value="que">
				<script>
                      alert("请选择文件类型!");
                    </script>
			</logic:equal>
			<logic:equal name="inserted" value="ok">
				<script>
                      alert("发布成功！");
                    </script>
			</logic:equal>
			<logic:equal name="inserted" value="no">
				<script>
                      alert("发布失败!请检查是否重复提交！");
                    </script>
			</logic:equal>
		</logic:notEmpty>

		<logic:notEmpty name="del">
			<logic:equal name="del" value="ok">
				<script>
                      alert("删除成功!");
                    </script>
			</logic:equal>
			<logic:equal name="del" value="no">
				<script>
                      alert("删除失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

