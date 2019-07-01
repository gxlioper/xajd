<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/gygl/gyglFunction.js"></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 信息维护 - 房源库自动生成</a>
			</p>
		</div>
		<!-- 标题 end-->	
		<html:form action="/zgdzdx_Gygl" method="post">
		<input type="hidden" id="delPk" name="delPk"/>
		<input type="hidden" id="searchGo" name="searchGo" onclick="refreshForm('/xgxt/roomAutoCreate.do');"/>
				<div id="items" name="items" style="display:none; position: absolute;background-color: #D0E0EE; width: 300px" >
					<table class="formlist" style=" width: 300px">						
					<thead>
						<tr>
							<th colspan="2">
								<span>默认寝室号编排规则为：层号+房间顺序号</span>
							</th>
						</tr>
					</thead>
					<tbody>	
						<tr>
							<th align="right">
								层号：
							</th>
							<td align="left">
								<html:select property="chsfbl"  styleId="chsfbl">							
								<html:option value="0">否</html:option>
								<html:option value="1">是</html:option>	
								</html:select>
								 (小于10层时左边是否补零)
							</td>
						</tr>
						<tr>
							<th align="right">
								房间顺序号：
							</th>
							<td align="left">
<%--								<html:text property="fjhws" styleId="fjhws" style="width:50px" maxlength="1" onkeypress="chkonlynum()"  onblur="onlyNumInput(this)"/>--%>
								<html:select property="fjhws"  styleId="fjhws">								
								<html:option value="2">2</html:option>
								<html:option value="3">3</html:option>
								<html:option value="4">4</html:option>
								<html:option value="5">5</html:option>	
								<html:option value="6">6</html:option>	
								<html:option value="7">7</html:option>	
								<html:option value="8">8</html:option>	
								<html:option value="9">9</html:option>
								<html:option value="10">10</html:option>			
								</html:select>
							 	(位数)
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan=2 align=center>
							<div class="btn">
								<button  style="height: 19px" onclick="roomCodeSave()">
									确 定
								</button>
								&nbsp;&nbsp;
								<button  style="height: 19px" onclick="document.getElementById('items').style.display='none'">
									关 闭
								</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="modifyFYKNote()"
								class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#"
								onclick="batch()"
								class="btn_sc">删除</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="roomATCreat(this)">
											生成
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="xiaoqu" styleId="xiaoqu" onchange="clearData();refreshForm('/xgxt/roomAutoCreate.do');">
									<html:option value="">--请选择--</html:option>
									<html:options collection="xiaoQList" labelProperty="mc"
										property="dm" />
								</html:select>
								</td>
								<th>
									楼栋名称
								</th>
								<td>
									<html:select property="lddm" styleId="lddm"  onchange="clearData();refreshForm('/xgxt/roomAutoCreate.do');">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ldList" labelProperty="mc"
										property="dm" />
								</html:select>
								</td>
								<th>
									楼层
								</th>
								<td>
									<html:select property="cs" styleId="cs" onchange="clearData();refreshForm('/xgxt/roomAutoCreate.do');">
									<html:option value="">--请选择--</html:option>
									<html:options collection="lcList" labelProperty="mc"
										property="dm" />
								</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									层房间数
								</th>
								<td>
									<html:text property="cfjs" styleId="cfjs" 
										style="width:50px"
										onkeydown="return onlyNum(this,2)"
										onmousedown="return onlyNum(this,2)"
										maxlength="2" 
										style="width : 20%;ime-mode:disabled"/>
								</td>
								<th>
									房间床位数(人数上限)
								</th>
								<td>
									<html:text property="jcws" styleId="jcws" 
										maxlength="2" onkeypress="chkonlynum()"  
										onblur="onlyNumInput(this)" style="width:50px" />
								</td>
								<th>
									房间分配标记
								</th>
								<td>
									<html:select property="fpbz" styleId="fpbz">
									    <html:option value="">--请选择--</html:option>
										<html:options collection="fpbjList" property="en" labelProperty="cn"/>
									</html:select>
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									房间标准收费
								</th>
								<td>
									<html:text property="sfbz" styleId="sfbz" 
										onkeypress="return sztzNumInputValue(this,7,event)" style="width:50px" />
								</td>
								<th>
									寝室号编排设置：	(双击右边文本框)	
								</th>
								<td colspan="3">
									<html:text property="qshbpgz" styleId="qshbpgz"
										readonly="true" style="cursor:hand;width:300px " 
										ondblclick="showGzSet()"/>							 
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<script type="text/javascript">
								    var ldtext = document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
									var cstext = document.forms[0].cs.options[document.forms[0].cs.selectedIndex].text;
									if($("cs").value!=""){
									     document.write(ldtext+"&nbsp;&nbsp;");							
									     if($("cs").value!="all"){
											document.write(cstext);						  
									     }
									}
									
								</script>
								<font color="red">暂无房间数据！</font>
							</logic:empty>
							<logic:notEmpty name="rs">
							总房间数：
							<bean:write name="rsNum" />
							&nbsp;间 &nbsp;&nbsp;&nbsp;
							</logic:notEmpty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td title="全选删除" align="left">
									<input type="checkbox" name="fyxx" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 表头 end-->
						<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="">
									<td title="单选删除" align="left">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>"/>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						<!--内容 end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>
			<logic:equal value="true" name="flag">
			 <script language="javascript">
			   alert("删除成功！");
			   </script>
			</logic:equal>
			<logic:equal value="false" name="flag">
			 <script language="javascript">
			   alert("删除失败！");
			   </script>
			</logic:equal>
		</html:form>
	</body>
</html>

