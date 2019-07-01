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
		//勾选一条记录
		function updateOne(type,oper){
			var pk = "";
			var xn = "";
			var xq = "";
			var c = 0;
			if(oper=='view'){
				pk = curr_row.getElementsByTagName('input')[0].value;
				xn = curr_row.getElementsByTagName('input')[1].value;
				xq = curr_row.getElementsByTagName('input')[2].value;
			}else{
				var pkArr = document.getElementsByName('primarykey_cbv');
				var xnArr = document.getElementsByName('xnArr');
				var xqArr = document.getElementsByName('xqArr');
				for(var i=0;i<pkArr.length;i++){
					if(pkArr[i].checked){
						pk = pkArr[i].value;
						xn = xnArr[i].value;
						xq = xqArr[i].value;
						c++;
					}
				}
				if(c!=1){
					alertInfo('请勾选一条您要操作的数据');
					return false;
				}
			}
			var url = 'xszcgl.do?method=xszcsqyydetail&pk=' + pk + '&xn=' + xn + '&xq=' + xq + '&type=' + type + '&oper=' + oper;
			showTopWin(url,400,300);
		}

		function ck(){
			//alert(curr_row.getElementsByTagName('input')[0].value);
			alert(curr_row.getElementsByTagName('input')[1].value);
			
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

		//注册
		function zc(){
			var pkArr = document.getElementsByName('primarykey_cbv');
			var xnArr = document.getElementsByName('xnArr');
			var xqArr = document.getElementsByName('xqArr');
			var xn = "";
			var xq = "";
			var c = 0;
			for(var i=0;i<pkArr.length;i++){
				if(pkArr[i].checked){
					xn += xnArr[i].value + ',';
					xq += xqArr[i].value + ',';
					c++;
				}
			}
			if(c==0){
				alertInfo('请选择您要操作的数据');
				return false;
			}
			//提示确认信息
			confirmInfo('确定要注册吗？', function(tag){
				if(tag == 'ok'){
					document.getElementById('zczt').value = '已注册';
					document.getElementById('xn').value = xn;
					document.getElementById('xq').value = xq;
					document.forms[0].action = 'xszcgl.do?method=xszcsq&doType=zc';
					document.forms[0].submit();
				}
			});
		}
	</script>
</head>

<body>
	<html:form action="/xszcgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<button type="button" onclick="allNotEmpThenGo('/xgxt/xsxx_xszcsq.do')" id="search_go" style="display: none"></button>
		<input type="hidden" id="zczt" name="zczt"/>
		<input type="hidden" id="xh" name="xh" value="${xh }"/>
		<input type="hidden" id="xn" name="xn"/>
		<input type="hidden" id="xq" name="xq"/>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	  <div class="toolbox">
			<div class="buttonbox">
				<ul>
					<li> 
						<a href="#" onclick="zc();" class="btn_zj">注册</a> 
					</li>
					<li>
						<a href="#" id="btn_sc"
						   onclick="updateOne('未注册')"
						   class="btn_sc">未注册 </a>
					</li>
					<li>
						<a href="#" id="btn_xg"
							onclick="updateOne('暂缓注册')"
							class="btn_xg">暂缓注册 </a>
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
					<font color="blue">提示：双击可以查看详细信息，单击表头可以排序，背景色为蓝色表示该学期已经维护过！</font> 
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
						<logic:iterate id="tit" name="topTr" scope="request">
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
							ondblclick="updateOne('view','view');"
							style="cursor:hand;background: <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>">
							<td>
								<input type="checkbox" id="cbv" name="primarykey_cbv"
									<logic:iterate id="v" name="s" offset="5" length="1">
										<logic:notEqual value="未审核" name="v"> disabled="disabled"</logic:notEqual>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<logic:notEqual value="${currXn }" name="v"> disabled="disabled"</logic:notEqual>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1">
										<logic:notEqual value="${currXq }" name="v"> disabled="disabled"</logic:notEqual>
									</logic:iterate>
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />	
												
								<logic:iterate id="v" name="s" offset="2" length="1">
									<input type="hidden" name="xnArr" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="1">
									<input type="hidden" name="xqArr" value="${v }"/>
								</logic:iterate>
								
							</td>
							<logic:iterate id="v" name="s" offset="2">
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
			alert(''+$('message').value);
			if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
