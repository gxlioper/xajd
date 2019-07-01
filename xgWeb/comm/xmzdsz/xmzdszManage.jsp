<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function showXmzdszUpdate(){
				var xmdm = $('xmdm').value;
				var mkmc = $('mkmc').value;
				
				if (""==xmdm){
					alert("请在左侧栏选择一个您要维护的项目");
					return false;
				} else {
					showTopWin('/xgxt/xmzdsz.do?method=xmzdszUpdate&mkmc='+mkmc+'&xmdm='+xmdm,800,600);
				}
			}
			
			
			function checkXmList(text){
				var liArr = $('xmList').getElementsByTagName('li');
				
				for (var i = 0 ; i < liArr.length ; i++){
					if (liArr[i].id.indexOf(text)>=0){
						liArr[i].style.display="";
					} else {
						liArr[i].style.display="none";
					}
				}
			
			}
			
			
		</script>
		<style type="css">
.text-overflow {
	display: block; /*内联对象需加*/
	width: 16em;
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
}
		
		</style>
	</head>

	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a onmouseover="">${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xmzdsz">
			<!-- 隐藏域 -->
			<input type="hidden" name="mkmc" value="${mkmc }" id="mkmc" />
			<html:hidden property="queryequals_xmdm" styleId="xmdm" />
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="showXmzdszUpdate();" class="btn_sz">字段设置</a>
							</li>
							<li>
								<a href="#"
									onclick="batchData('primarykey_cbv','/xgxt/xmzdsz.do?method=xmzdszManage&doType=del','del');return false;"
									class="btn_sc">字段删除</a>
							</li>
						</logic:equal>
					</ul>
				</div>
			</div>
			<!-- 按钮 end-->
			<!-- 过滤条件 -->
			<!-- 多功能操作区 -->
			<div class="leftframe04">
				<div class="menulist">
					<!--层start-->
					<div class="menutitle">
						<h3>
							<span class="title"> 
					      	资助项目
							</span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:420px; overflow-x:hidden;overflow-y:auto;">
							<p class="choose_select">
								<label>
									项目名称
									<input type="text" style="width:80px" class="text_nor"
										onkeyup="checkXmList(this.value)" />
								</label>

							</p>
							

							<ul id="xmList">
								<logic:iterate id="x" name="xmList">
									<li id="${x.xmmc}"
										class='<logic:equal value="${xmdm}" name="x" property="xmdm">Current</logic:equal>'>
										<a href="#" class='Child' title="${x.xmmc}"
											onclick="$('xmdm').value='${x.xmdm }';$('search_go').click();return false;"
											style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>${x.xmmc}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>

					</div>

				</div>
				<!--层end-->
			</div>

			<div class="rightframe04" style="80%">
				<!--查询条件-->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xmzdsz.do?method=xmzdszManage')">
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
								<th>
									数据来源
								</th>
								<td>
									<html:text property="querylike_lybmc"></html:text>
								</td>
								<th>
									字段名称
								</th>
								<td>
									<html:text property="querylike_zdmc"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table width="100%" class="dateline tablenowrap">
						<thead>
							<tr>
								<td>
									<input type="checkbox" id="jiade" />
									<input type="checkbox" id="selall" name="selall"
										onclick="selAll()" />
								</td>
								<logic:iterate id="b" name="topTr" offset="1" scope="request">
									<td id="${b.en}" onclick="tableSort(this)">
										${b.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											<input type="hidden" value="${v }" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xmzdszForm"></jsp:include>
				<!--分页end-->
				<script type="text/javascript">
									$('choose').className="hide";
							</script>
			</div>
				<!-- 查询结果 end-->
		</html:form>
	</body>
</html>
