<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//保存学生走读
		function saveXszd(tag){
			
			if(tag == "ok"){
				var zdkssj = $("zdkssj").value;
				var zdjssj = $("zdjssj").value;
				
				if(zdkssj == "" || zdjssj == ""){
					alertError("走读开始时间和结束时间不能为空<br>请确认!");
					return false;
				}else if(zdkssj > zdjssj){
					alertError("走读开始时间不能晚于结束时间<br>请确认!");
					return false;
				}else{
					showTips('处理中，请稍后......');
					var url = "gyglZjjs.do?method=xszdsq&doType=save";
					refreshForm(url);
				}
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.联系电话、家庭地址、家庭电话取自学生信息，如果在申请时修改后，将以修改后的为准。</br>
				2.在一天之内，您只可以申请一次走读，请不要重复操作。</br>
				3.如果您发现无法操作保存按钮的话，可能是此次申请已经被您的辅导员审核过了，请前往结果查询处查看。</br>
				4.这里仅保存您今天的申请情况，如果你不确定之前是否有申请的，请前往结果查询进行查看。</br>
				</span>
			</p>			
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/gyglZjjs" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xh" value="${rs.xh }" />
			<input type="hidden" name="sqsj" value="${rs.sqsj }" />
			<input type="hidden" name="id" value="${rs.id }" />
			<!-- 隐藏域 end-->
			
			<!-- 学生基本信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">学号</th>
						<td width="30%">
							${rs.xh }
						</td>
						<th width="20%">姓名</th>
						<td width="30%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>
							${rs.xb }
						</td>
						<th>班级</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>联系电话</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"
							onkeyup="checkInputNum(this)" onblur="checkInputNum(this)" 
							maxlength="20" style="ime-mode:disabled;"
							/>
						</td>
						<th>家庭电话</th>
						<td>
							<html:text name="rs" property="jtdh" styleId="jtdh"
							onkeyup="checkInputNum(this)" onblur="checkInputNum(this)" 
							maxlength="20" style="ime-mode:disabled;"
							/>
						</td>
					</tr>
					<tr>
						<th>家庭地址</th>
						<td colspan="3">
							<html:text name="rs" property="jtdz" styleId="jtdz" style="width: 545px" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th>住宿地址</th>
						<td colspan="3">
							<html:text name="rs" property="zsdd" styleId="zsdd" style="width: 545px" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>走读开始时间</th>
						<td>
							<html:text name="rs" property="zdkssj" styleId="zdkssj"
								onclick="return showCalendar('zdkssj','ymmdd');" 
								readonly="true"
							/>
						</td>
						<th><font color="red">*</font>走读结束时间</th>
						<td>
							<html:text name="rs" property="zdjssj" styleId="zdjssj"
								onclick="return showCalendar('zdjssj','ymmdd');" 
								readonly="true"
							/>
						</td>
					</tr>
					<tr>
						<th>
							申请理由
							<br />
							<font color="red">(限制录入500字)</font>						
						</th>
						<td colspan="3">
							<html:textarea name='rs' property="sqly"  
								styleId="sqly" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<th>
							备注
							<br />
							<font color="red">(限制录入500字)</font>		</th>
						<td colspan="3">
							<html:textarea name='rs' property="bz"  
								styleId="bz" rows="5" 
								style="word-break:break-all;width:545px" 
								onblur="chLeng(this,'500')">
							</html:textarea>
						</td>
					</tr>
					<tr>
						<th>附件下载</th>
						<td colspan="3">
							<a href="gyglZjjs.do?method=downLoadFile" target="_blank">
								<font color="blue">点击下载</font>
							</a>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- 保存 -->
								<button onclick="confirmInfo('是否保存您的申请？',saveXszd);"
								<logic:notEqual name="rs" property="bjsh" value="未审核">disabled="disabled"</logic:notEqual>
								>
									<bean:message key="lable.btn_bc_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 学生基本信息 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
				
		</html:form>
	</body>
</html>