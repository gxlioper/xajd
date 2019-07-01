<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function saveData(){
			if(!filedNotNull([],'')){
				alert('请将带*项目填写完整！');
				return false;
			}else{
				//检测时间
				if(ele('kssqsj1')){
					var kssqsj = val('kssqsj1').replace('-','').replace('-','')+val('kssqsj2')+val('kssqsj3')+val('kssqsj4');
					var jssqsj = val('jssqsj1').replace('-','').replace('-','')+val('jssqsj2')+val('jssqsj3')+val('jssqsj4');
					if(toInt(kssqsj)>toInt(jssqsj)){
						alert('校内学生申请岗位开始时间晚于结束时间！');
						return false;
					}
				}
				if(ele('xwkssqsj1')){
					var xwkssqsj = val('xwkssqsj1').replace('-','').replace('-','')+val('xwkssqsj2')+val('xwkssqsj3')+val('xwkssqsj4');
					var xwjssqsj = val('xwjssqsj1').replace('-','').replace('-','')+val('xwjssqsj2')+val('xwjssqsj3')+val('xwjssqsj4');
					if(toInt(xwkssqsj)>toInt(xwjssqsj)){
						alert('校外学生申请岗位开始时间晚于结束时间！');
						return false;
					}
				}
				if(ele('shkssj1')){
					var shkssj = val('shkssj1').replace('-','').replace('-','')+val('shkssj2')+val('shkssj3')+val('shkssj4');
					var shjssj = val('shjssj1').replace('-','').replace('-','')+val('shjssj2')+val('shjssj3')+val('shjssj4');
					if(toInt(shkssj)>toInt(shjssj)){
						alert('用人单位审核开始时间晚于结束时间！');
						return false;
					}
				}
				
				saveTrainConf('kssqsj','jssqsj','xn','nd','work_conf.do?act=save');
			}
		}
	</script>
</head>
	<body>
			<html:form action="/chgPriseBat" method="post">
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
				<div class="tab_cur">
					<p class="location">
						<em>您的当前位置:</em><a>勤工助学 - 参数设置 - 参数设定</a>
					</p>
				</div>
				<div class="tab">
					<table width="80%" align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>勤工助学参数设定</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select property="xn">
									<html:options collection="xnndList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>年度</th>
							<td>
								<html:select property="nd">
									<html:options collection="xnndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:select property="xq">
									<option value="">
									</option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>校<font color="red">内</font>学生申请岗位开始时间</th>
							<td>
								<input type="hidden" name="kssqsj" id="kssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('kssqsj1','y-mm-dd');"
									value="<bean:write name="kssj1" />" name="kssqsj1" id="kssqsj1" />
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
							<th>校<font color="red">内</font>学生申请岗位截至时间</th>
							<td>
								<input type="hidden" name="jssqsj" id="jssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('jssqsj1','y-mm-dd');"
									value="<bean:write name="jssj1" />" name="jssqsj1" id="jssqsj1" />
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
						<tr>
							<th>校<font color="red">外</font>学生申请岗位开始时间</th>
							<td>
								<input type="hidden" name="xwkssqsj" id="xwkssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('xwkssqsj1','y-mm-dd');"
									value="<bean:write name="xwkssj1" />" name="xwkssqsj1" id="xwkssqsj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwkssj2" />"
									name="xwkssqsj2" id="xwkssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwkssj3" />"
									name="xwkssqsj3" id="xwkssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwkssj4" />"
									name="xwkssqsj4" id="xwkssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>校<font color="red">外</font>学生申请岗位截至时间</th>
							<td>
								<input type="hidden" name="xwjssqsj" id="xwjssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('xwjssqsj1','y-mm-dd');"
									value="<bean:write name="xwjssj1" />" name="xwjssqsj1" id="xwjssqsj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwjssj2" />"
									name="xwjssqsj2" id="xwjssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwjssj3" />"
									name="xwjssqsj3" id="xwjssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwjssj4" />"
									name="xwjssqsj4" id="xwjssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>用人单位审核开始时间</th>
							<td>
								<input type="hidden" name="shkssj" id="shkssj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('shkssj1','y-mm-dd');"
									value="<bean:write name="shkssj1" />" name="shkssj1" id="shkssj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shkssj2" />"
									name="shkssj2" id="shkssj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shkssj3" />"
									name="shkssj3" id="shkssj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shkssj4" />"
									name="shkssj4" id="shkssj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>用人单位审核截至时间</th>
							<td>
								<input type="hidden" name="shjssj" id="shjssj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('shjssj1','y-mm-dd');"
									value="<bean:write name="shjssj1" />" name="shjssj1" id="shjssj1" />
								－
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shjssj2" />"
									name="shjssj2" id="shjssj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shjssj3" />"
									name="shjssj3" id="shjssj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								：
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shjssj4" />"
									name="shjssj4" id="shjssj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>每天最高工作时间数</th>
							<td>
								<html:text property="mtzgxs" styleId="mtzgxs" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>24){this.value=24}if(this.value<0){this.value=0} "/>小时				
							</td>
						</tr>
						<tr>
							<th>每月最高工作时间数</th>
							<td>
								<html:text property="myzgxs" styleId="myzgxs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>744){this.value=744}if(this.value<0){this.value=0} "/>
								<html:select property="myzgsjfs" styleId="myzgsjfs">
									<html:option value="h">小时</html:option>
<!--									<html:option value="d">天</html:option>-->
<!--									<html:option value="w">周</html:option>-->
<!--									<html:option value="m">月</html:option>-->
								</html:select>
							</td>
						</tr>
						<tr>
							<th>每月最高酬金数</th>
							<td>
								<html:text property="myzgbc" styleId="myzgbc" maxlength="5" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
								元
							</td>
						</tr>
						<tr>
							<th>酬金发放时间间隔</th>
							<td>
								<html:text property="ffsjjg" styleId="ffsjjg" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="4"/>
								天
							</td>
						</tr>
						<tr>
							<th>校内固定岗位使用困难生比例不得低于</th>
							<td>
								<html:text property="knsbl" styleId="knsbl" style="width:40px"
									onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>100){this.value=100}if(this.value<0){this.value=0} " maxlength="3" />
								%
							</td>
						</tr>
						<tr>
							<th>上报提示</th>
							<td>
								<html:text property="sbts" styleId="sbts" style="width:150px"
									maxlength="50"/>
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
					          <div class="btn">
					            <button type="button"
									onclick="saveData()">
									保存
								</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>
					</table>
				</div>				
			</html:form>

			<logic:notEmpty name="ok">
				<logic:equal name="ok" value="ok">
					<script>alert("保存成功!")</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>alert("保存失败!")</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
