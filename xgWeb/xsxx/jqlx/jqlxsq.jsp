<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
	<script type="text/javascript">
		//申请
		function sq(){
			var cont = document.getElementById('cont').value;
			if(0==cont){
				showTopWin('jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok&oper=add',770,550);
			}else{
				alertInfo('本学期已经申请，不可再次申请！');
				return false;
			}
		}

		//全选
		function selectAll(){
			var checkBoxArr = document.getElementsByName('primarykey_cbv');
			var selall = document.getElementById('allS');
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].disabled!=true){
					checkBoxArr[i].checked = selall.checked;
				}
			}
		}

		//删除
		function del(checkboxName,url){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('请选择您要删除的数据');
				return false;
			} 
			//提示确认信息
			confirmInfo('确定要删除所选择的数据吗？', function(tag){
				if(tag == 'ok'){
					document.forms[0].action = url;
					document.forms[0].submit();
				}
			});
			if ($("pt")){
				BatAlert.showTips('正在操作，请等待...');
			}
		}
	</script>
</head>

<body>
	<html:form action="/jqlxgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="save_xn" value="${xn }"/>
		<!-- 
		<input type="hidden" name="save_nd" value="${nd }"/>
		 -->
		<input type="hidden" name="save_xq" value="${xq }"/>
		<input type="hidden" id="cont" name="cont" value="${cont }"/>
		<button type="button" onclick="allNotEmpThenGo('/xgxt/xsxx_xsjqlxsq.do')" id="search_go" style="display: none"></button>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	<!-- 	
		<div class="tab">
		<table class="formlist" width="100%">
			<tbody>
			<tr>
				<th>学年</th>
				<td>
					${xn }
				</td>
				<th>学期</th>
				<td>
					${xqmc }
				</td>
			</tr>
			<tr>
				<th>学号</th>
				<td>
					${map.xh }
					<input type="hidden" id="save_xh" name="save_xh" value="${map.xh }"/>
				</td>
				<th>姓名</th>
				<td>
					${map.xm }
				</td>
			</tr>
			<tr>
				<th>性别</th>
				<td>${map.xb }</td>
				<th>辅导员</th>
				<td>
					${map.fdyxm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${map.xymc }</td>
				<th>专业</th>
				<td>${map.zymc }</td>
			</tr>
			<tr>
				<th>班级</th>
				<td>${map.bjmc }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			</tbody>
		</table>
	  </div>
	   -->
	  <div class="toolbox">
			<div class="buttonbox">
				<ul>
					<li> 
						<a href="#" onclick="sq();" class="btn_zj">申请</a> 
					</li>
					<li>
						<a href="#" id="btn_xg"
						   onclick="showViewWindow('primarykey_cbv','jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok','770','550')"
						   class="btn_xg"> 修改 </a>
					</li>
					<li>
						<a href="#" id="btn_sc"
							onclick="del('primarykey_cbv','jqlxgl.do?method=jqlxsq&doType=del')"
							class="btn_sc"> 删除 </a>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="formbox" id="result">
			<h3 class="datetitle_01">
		    <span>
		    	查询结果&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">未找到任何记录！</font> 
		 		 </logic:empty>
		 		 <logic:notEmpty name="rs">
					<font color="blue">提示：双击可以查看详细信息，单击表头可以排序！</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
			      		<td>
			      			<input type="checkbox" id="allS" name="allS" onclick="selectAll();"/>
			      		</td>
						<logic:iterate id="tit" name="topTr" offset="4" scope="request">
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
							ondblclick="showInfo('/xgxt/jqlxgl.do?method=jqlxsqDetial','view','770','700');"
							style="cursor:hand;background: <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>">
							<td>
								<!-- 
								<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
								 -->
								<input type="checkbox" id="cbv" name="primarykey_cbv" alt=""
									<logic:iterate id="v" name="s" offset="11" length="1">
								<logic:notEqual value="未审核" name="v"> disabled="disabled"</logic:notEqual>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="12" length="1">
								<logic:notEqual value="未审核" name="v"> disabled="disabled"</logic:notEqual>
								</logic:iterate>
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />	
							</td>
							<logic:iterate id="v" name="s" offset="4">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>			
			   </table>
			   </div>
			</logic:notEmpty>
			</div>
	  
	</html:form>
	<logic:present name="result">
		<script>
			alertInfo(''+$('message').value);
			if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
