<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript">
		
		function save(){
			if($("refreshParent")){
			  	$("refreshParent").value="no";
		    }
			  	
			if($("xh").value==""){
			  	
				alertInfo("学号不能为空!");
				return false;
			}
			
			if($("sfbrtj").value==""){
				alertInfo("是否本人提档不能为空!");
				return false;
			}
			refreshForm("/xgxt/guizdxDagl.do?method=byzdwhUpdate&doType=save");
		}
		
		function update(){
			if($("refreshParent")){
			  $("refreshParent").value="no";
			}
			  	
			if($("xh").value==""){
			  	
				alertInfo("学号不能为空!");
				return false;
			}
			
			if($("sfbrtj").value==""){
				alertInfo("是否本人提档不能为空!");
				return false;
			}
			refreshForm("/xgxt/guizdxDagl.do?method=byzdwhUpdate&doType=modi");
		}
		
		function checkCb(){
			var xsdaxx=document.getElementsByName("xsdaxx");
			var xsdaInfo=document.getElementsByName("xsdaInfo");
			for(i=0;i<xsdaxx.length;i++){
				for(j=0;j<xsdaInfo.length;j++){
					if(xsdaxx[i].value==xsdaInfo[j].value){
						xsdaInfo[j].checked=true;
						
					}
				}
			}
			
			if($("doType").value=="view"){
				var inputArr=document.getElementsByTagName("input");
				for(i=0;i<inputArr.length;i++){
					inputArr[i].disabled=true;
				}
				if($("sfbrtj")){
					$("sfbrtj").disabled=true;
				}
			}
		}
	</script>
	</head>
	<body onload="checkCb()">

		<html:form action="/guizdxDagl" method="post">
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<input type="hidden" name="message" id="message"
				value="${message}" />
			<logic:iterate name="xsdadmInfo" id="xsdadm">
				<input type="hidden" name="xsdaxx" id="xsdaxx_${index}" value="${xsdadm.dm}"/>
			</logic:iterate>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								学号
							</th>
							<td style="width:34%">

								<input type="text" name="xh" id="xh" value="${rs.xh}"
									readonly="readonly" />
							</td>

							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>

							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>

							<th style="width:16%">
								专业
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>

							<th style="width:16%">
								<logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>是否本人提档
							</th>
							<td style="width:34%">
								<html:select name="rs" property="sfbrtj" styleId="sfbrtj">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								档案投递地址
							</th>
							<td  colspan="3">
								<html:textarea name='rs' property='datddz' styleId="datddz"
									style="word-break:break-all;width:80%"
									onblur="chLeng(this,100);" rows='4' />
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>毕业生转档信息</span>
							</th>
						</tr>
					</thead>
					<logic:iterate name="lxList" id="lx" indexId="index">
						<tr>
							<th>
								<html:checkbox property="xsdaInfo" styleId="lx_${index}"
									value="${lx.dm}" />
							</th>
							<td colspan="3">
								${lx.mc }
							</td>
						</tr>
					</logic:iterate>
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:notEqual name="doType" value="view">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								</logic:notEqual>
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" onclick="save();return false;">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button type="button" onclick="update();return false;">
											保 存
										</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
