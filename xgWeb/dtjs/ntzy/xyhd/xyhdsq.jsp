<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript">
		function checkHour(obj){
			checkInputData(obj);
			var hour = obj.value;
			if(eval(hour)>23){
				obj.value = "";
			}
		}
		
		function checkMinute(obj){
			checkInputData(obj);
			var minute = obj.value;
			if(eval(minute)>59){
				obj.value = "";
			}
		}
	
		function setKssj(){
			var kssj = $('day').value + $('hour1').value+":"+$('min1').value+"-"+$('hour2').value+":"+$('min2').value;
			$('kssj').value = kssj;
		}
	</script>
</head>
<body>
	<html:form action="ntzy_xyhd.do?method=xyhdsq" method="post">
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>${title }</a>
		</p>
	</div>
	
		<input type="hidden" name="save_sqsj" value="${sqsj }" />
		<input type="hidden" name="save_ssbm" value="${userDep }" />
		<input type="hidden" name="save_sqr" value="${sessionScope.userName }" />
		<input type="hidden" name="save_kssj" id="kssj" value=""/>
		<logic:equal value="xx" name="userType">
			<input type="hidden" name="save_xysh" value="通过"/>
		</logic:equal>
		<div class="tab">
		<table class="formlist" width="93%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>填写申请表</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<th align="right" width="20%">
						<font color="red">*</font>申请单位
					</th>
					<td align="left" width="35%">
						<html:text property="save_sqdw" styleId="sqdw"></html:text>
					</td>
				<th width="20%">
					<div align="right">
						总负责人
					</div>
				</th>
				<td width="25%">
					<html:text property="save_zfzr" styleId="zfzr"></html:text>
				</td>

			</tr>
			<tr>
				<th>
					<div align="right">
						<font color="red">*</font>活动拟开始时间
					</div>
				</th>
				<td>
					<html:text property="day" styleId="day" readonly="true" style="width:100px" onclick="showCalendar('day','y年mm月dd日');"/>
					<html:text property="hour1" styleId="hour1" style="width: 25px" maxlength="2" onblur="checkHour(this);"/>:
					<html:text property="minute1" styleId="min1" style="width: 25px" maxlength="2" onblur="checkMinute(this);"/>-
					<html:text property="hour2" styleId="hour2" style="width: 25px" maxlength="2" onblur="checkHour(this);"/>:
					<html:text property="minute2" styleId="min2" style="width: 25px" maxlength="2" onblur="checkMinute(this);"/>
				</td>
				
				<th>
					<div align="right">
						地点
					</div>
				</th>
				<td>
					<html:text property="save_dd" styleId="dd"></html:text>
				</td>
			</tr>
			<tr>
				<th width="16%">
					<div align="right">
						活动内容
					</div>
				</th>
				<td width="34%">
					<html:text property="save_hdnr" styleId="hdnr"></html:text>
				</td>
				<th>
					<div align="right">
						参与人数
					</div>
				</th>
				<td>
					<html:text property="save_cyrs" styleId="cyrs" onkeyup="checkInputData(this);" maxlength="5"></html:text>
				</td>
			</tr>
			<tr>	
				<th>
					<div align="right">
						现场负责人一
					</div>
				</th>
				<td>
					<html:text property="save_xcfzr1" styleId="xcfzr1"></html:text>
				</td>
				
				<th>
					<div align="right">
						负责人一联系电话
					</div>
				</th>
				<td>
					<html:text property="save_fzr1dh" styleId="fzr1dh" onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>
			
			<tr>	
				<th>
					<div align="right">
						现场负责人二
					</div>
				</th>
				<td>
					<html:text property="save_xcfzr2" styleId="xcfzr2"></html:text>
				</td>
				
				<th>
					<div align="right">
						负责人二联系电话
					</div>
				</th>
				<td>
					<html:text property="save_fzr2dh" styleId="fzr2dh" onkeyup="checkInputData(this);"></html:text>
				</td>
			</tr>		

			<tr align="left" style="height:22px">
							<th align="right">
								活动方案简要描述
								<br />
							<font color="red">(限制在1000字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_hdfa' style="width:99%"
									onblur="checkLen(this,1000)" rows='8'/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<th align="right">
								申请单位意见
								<br/>
								<font color="red">(限制在400字内)</font>
							</th>
							<td colspan="7">
								<html:textarea property='save_sqdwyj' style="width:99%"
									rows='7' onblur="checkLen(this,400)"/>
							</td>
						</tr>
			</tbody>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			        	<logic:equal value="yes" name="writeAble">
			          		<button type="button" name="提交" onclick="setKssj();saveDataShowTips('ntzy_xyhd.do?method=xyhdsq&doType=save','sqdw-day-hour1-min1-hour2-min2','正在处理数据，请稍候！');">提 交</button>
			       		</logic:equal>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
		
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html>
