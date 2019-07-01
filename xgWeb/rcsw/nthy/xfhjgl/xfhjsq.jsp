<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/xfhjglService.js"></script>
		<script type="text/javascript">
				function loadcheck() {
					var xh = document.getElementById("xh").value;
					var xn = document.getElementById("xn").value;
					xfhjglService.checkXfhjsqxx(xh,xn,function(data){
						if (data == 'shz') {
							document.getElementById("msg").style.display='';
							if (document.getElementById("btn_save")) {document.getElementById("btn_save").disabled = 'disabled';}
						} else {
							document.getElementById("msg").style.display='none';
							if (document.getElementById("btn_save")) document.getElementById("btn_save").disabled = '';
						}
					});
					
				}	
				//数据保存
			  function sub(){
			  		var url='rcsw_nthy_xfhjsq.do?doType=save';
				  	if (document.getElementById('xh').value==''||document.getElementById('sqyy').value=='') {
				  		alertInfo("请将带*号的信息填写完整！",function(){});
				  		return false;
				  	}
				  	if($("show") && $("show").value=="cx"){
				  		url+="&show=cx";
				  	}
					refreshForm(url);
			  }
		</script>
	</head>

	<body onload="loadcheck()">
		<html:form action="/rcsw_nthy_xfhjgl.do" method="post">
			<logic:notEqual name="show" value="cx">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
			</logic:notEqual>
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
    					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-xymc-nj-zymc-bjmc-xb" />
			<input type="hidden" id="url" name="url" value="/rcsw_nthy_xfhjgl.do?method=xfhjsq" />
			<input type="hidden" id="show" name="show" value="${show }" />	

			
			<!-- 提示信息 end-->
			<div class="prompt" id="msg" style="display: none;" >
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					1.你已申请过学费缓交，相关部门审核中，不能再进行修改。
				</p>
				<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
			</div>


			<div class="tab" >
				<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
		
									<button type="button" class="button2" id="btn_save" 
										onclick="sub('rcsw_nthy_xfhjsq.do?doType=save','xh-xn-sqyy');">
										保 存
									</button>
									<logic:equal name="show" value="cx">
										<button type="button" class="button2" onclick="window.close();return false;">
											关 闭
										</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>学费缓交申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:text  property="xh" readonly="true" value="${rs.xh }"
									styleId="xh"  onkeypress="autoFillStuInfo(event.keyCode,this);"  />
								<logic:notEqual value="student" name="userOnLine">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:notEqual>
								
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								年级
							</th>
							<td>
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								专业
							</th>
							<td>
								${rs.zymc }
							</td>
						</tr>
						<tr>
							
							<th>
								班级
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>申请学年
							</th>
							<td>
								<input type="text" id="xn" name="xn" value="${xn }" readonly="readonly"/>
							</td> 
							<th>
								申请时间
							</th>
							<td>
								<html:text property="sqsj" styleId="sqsj" value="${sqsj}" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
							<font color="red">*</font>申请原因
							</th>
							<td colspan="3">
								<html:textarea property="sqyy" styleId="sqyy" rows="5" style="width:600px"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}',function(t){
					if(t=="ok"){
						dialogArgumentsQueryChick();
			 			window.close();
					}
				});
			</script>
		</logic:present>
	</body>
</html>
