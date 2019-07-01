<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
		function checkNull(){
			var xq=document.forms[0].xq.value;
			var mxsbc=document.forms[0].mxsbc.value;
			var mtzgxs=document.forms[0].mtzgxs.value;
			var myzgxs=document.forms[0].myzgxs.value;
			if(xq==null||xq==""||mxsbc==null||mxsbc==""||mtzgxs==null||mtzgxs==""||myzgxs==null||myzgxs==""){
				alert("请将带\*号的项目填写完整！");
				return false;
			}
			return true;
		}
		
		function ckinpdata(obj){
			obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = obj.value;
			
			if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
				alert('数据格式不正确，只能是数字！');
				obj.value = '';
				return false;
			}
			return true;
		}
		
		function save(){
			var ele = ["xn","nd","xq","myzgxs"];
			for(var i=0; i<ele.length; i++){
				if(document.getElementById(ele[i]).value ==''){
					alert('请将带\*号的项目填写完整！');
					return false;
				}
			}
			if($("cjffsj")){
				var cjffsj = $("cjffsj").value;
				if(cjffsj != ""){
				if (cjffsj.length !=6){
					alert("时间格式错误，应该为(格式：199001<1990年01月>)，请确认 ");
					return false;
				}
				if (cjffsj.substring(0,4) < 1990){
					alert("时间格式错误，应该为(格式：199001<1990年01月>)，请确认 ");
					return false;
				}
				if (cjffsj.substring(4,6) >12){
					alert("时间格式错误，应该为(格式：199001<1990年01月>)，请确认 ");
					return false;
				}
				}
			}
			saveTrainConf('kssqsj','jssqsj','xn','nd','work_conf.do?act=save');
		}
	</script>
</head>
<body>
<html:form action="/work_conf" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm"/>" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em>
				<a>
					<%--长沙民政--%>
					<logic:equal value="10827" name="xxdm">
					学生义工 - 参数设置 - 参数设定
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
					勤工助学 - 参数设置 - 参数设定
					</logic:notEqual>
				</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
			<thead>
				<%--长沙民政--%>
				<logic:equal value="10827" name="xxdm">
					<tr align="center">
						<th colspan="2">
							<span>学生义工参数设定</span>
						</th>
					</tr>
				</logic:equal>
				<%--非长沙民政--%>
				<logic:notEqual value="10827" name="xxdm">
					<tr align="center">
						<th colspan="2">
							<span>勤工助学参数设定</span>
						</th>
					</tr>
				</logic:notEqual>
			</thead>
			<tbody>	
				<tr>
					<th><span class="red">*</span>学年</th>
					<td>
						<html:select property="xn" styleId="xn">
							<html:options collection="xnndList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>年度</th>
					<td>
						<html:select property="nd" styleId="nd">
							<html:options collection="xnndList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>学期</th>
					<td>
						<html:select property="xq" styleId="xq">
							<option value="">
							</option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>						
				<%--非上海工程技术大学--%>
				<logic:notEqual value="10856" name="xxdm">						
					<tr>
						<%--浙江传媒学院--%>
						<logic:equal value="11647" name="xxdm">
							<th>岗位发布开始时间</th>
						</logic:equal>
						<%--end浙江传媒学院--%>
						<%-- 非浙江传媒学院--%>
						<logic:notEqual value="11647" name="xxdm">
							<th>申请开始时间</th>
						</logic:notEqual>
						<%-- end非浙江传媒学院--%>
						<td>
							<input type="hidden" name="kssqsj" id="kssqsj" value="" />
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('kssqsj1','y-mm-dd');"
								value="<bean:write name="kssj1" />" name="kssqsj1"
								id="kssqsj1" />
							－
							<input type="text" onkeypress="return numInputValue(this,2,event)"
								style="width:20px" value="<bean:write name="kssj2" />"
								name="kssqsj2" id="kssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
							：
							<input type="text" onkeypress="return numInputValue(this,2,event)"
								style="width:20px" value="<bean:write name="kssj3" />"
								name="kssqsj3" id="kssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							：
							<input type="text" onkeypress="return numInputValue(this,2,event)"
								style="width:20px" value="<bean:write name="kssj4" />"
								name="kssqsj4" id="kssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
						</td>
					</tr>
					<tr>
						<%--浙江传媒学院--%>
						<logic:equal value="11647" name="xxdm">
							<th>岗位发布截至时间</th>
						</logic:equal>
						<%--end浙江传媒学院--%>
						<%-- 非浙江传媒学院--%>
						<logic:notEqual value="11647" name="xxdm">
							<th>申请截至时间</th>
						</logic:notEqual>
						<%-- end非浙江传媒学院--%>						
						<td>
							<input type="hidden" name="jssqsj" id="jssqsj" value="" />
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('jssqsj1','y-mm-dd');"
								value="<bean:write name="jssj1" />" name="jssqsj1"
								id="jssqsj1" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
							－
							<input type="text" onkeypress="return numInputValue(this,2,event)"
								style="width:20px" value="<bean:write name="jssj2" />"
								name="jssqsj2" id="jssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
							：
							<input type="text" onkeypress="return numInputValue(this,2,event)"
								style="width:20px" value="<bean:write name="jssj3" />"
								name="jssqsj3" id="jssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							：
							<input type="text" onkeypress="return numInputValue(this,2,event)"
								style="width:20px" value="<bean:write name="jssj4" />"
								name="jssqsj4" id="jssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
						</td>
					</tr>
				</logic:notEqual>
				<tr>
					<th><span class="red">*</span>每月最高工作小时数</th>
					<td>
						<html:text property="myzgxs" styleId="myzgxs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>248){this.value=248}if(this.value<0){this.value=0}"/>小时
					</td>
				</tr>
				<!--北京联合大学-->
				<logic:equal value="11417" name="xxdm">
				<tr>
					<th><span class="red">*</span>每天最高工作小时数</th>
					<td>
						<html:text property="mtzgxs" styleId="mtzgxs" onkeyup="value=value.replace(/[^\d]/g,'') "/> 小时
					</td>
				</tr>
				</logic:equal>
				<!--end北京联合大学-->
				<!--非北京联合大学-->
				<logic:notEqual value="11417" name="xxdm">
					<tr>
						<th>每天最高工作小时数</th>
						<td>
							<html:text property="mtzgxs" styleId="mtzgxs" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>8){this.value=8}if(this.value<0){this.value=0} " maxlength="2"/> 小时
						</td>
					</tr>
				</logic:notEqual>
				<!--end非北京联合大学-->
				<%--中国地质大学--%>
				<logic:equal value="10491" name="xxdm">							
					<tr>
						<th>每月最高酬金数</th>
						<td>
							<html:text property="myzgbc" styleId="myzgbc" />
							元
						</td>
					</tr>
				</logic:equal>
				<%--北京联合大学--%>
				<logic:equal value="11417" name="xxdm">
					<tr>
						<th><span class="red">*</span>每小时酬金</th>
						<td>
							<html:text property="mxsbc" styleId="mxsbc" />
						</td>
					</tr>	
				</logic:equal>		
				<!--海南大学	-->
				<logic:equal value="10589" name="xxdm">
					<tr>
						<th>上学期补考科目数不得高于</th>
						<td>
							<html:text property="bkkmsxz" 
							           styleId="bkkmsxz" 
							           maxlength="6"
							           onkeyup="value=value.replace(/[^\d]/g,'') "/>门
						</td>
					</tr>						
				</logic:equal>
				<!--end海南大学	-->			
				<%--长沙民政职业技术学院--%>
				<logic:equal value="10827" name="xxdm">
					<tr>
						<th>校内固定岗位使用困难生人数</th>
						<td>
							<html:text property="knsbl" styleId="knsbl" style="width:40px"
								onblur="numFormatChk(this,0,100)" />
						</td>
					</tr>
				</logic:equal>
				<%--非长沙民政职业技术学院--%>
				<logic:notEqual value="10827" name="xxdm">
					<tr>
						<th>校内固定岗位使用困难生比例不得低于</th>
						<td>
							<html:text property="knsbl" styleId="knsbl" style="width:40px"
								onblur="numFormatChk(this,0,100)" />
							%
						</td>
					</tr>
				</logic:notEqual>						
				<tr>
					<th>酬金发放月份</th>
					<td>
						<html:text property="cjffsj" styleId="cjffsj" onkeyup="value=value.replace(/[^\d]/g,'') ;" maxlength="6"/> (格式：199001<1990年01月>)
					</td>
				</tr>
				<tr>
					<th>启用经费管理功能</th>
					<td>
						<html:radio property="jfglkg" value="1">开</html:radio>
						<html:radio property="jfglkg" value="0">关</html:radio>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span class="red">注：酬金发放月份不设置时，默认发放月份为当月。</span>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <%--北京联合大学--%>
						<logic:equal value="11417" name="xxdm">
							<button type="button" class="button2"
								onclick="if(checkNull()) saveTrainConf('kssqsj','jssqsj','xn','nd','work_conf.do?act=save')">
								保存
							</button>
						</logic:equal>
						<%--非北京联合大学--%>
						<logic:notEqual value="11417" name="xxdm">
							<button type="button" class="button2"
								onclick="save();return false;">
								保存
							</button>
						</logic:notEqual>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		<logic:notEmpty name="ok">
			<logic:equal name="ok" value="ok">
				<script>alert("保存成功!")</script>
			</logic:equal>
			<logic:equal name="ok" value="no">
				<script>alert("保存失败!")</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
