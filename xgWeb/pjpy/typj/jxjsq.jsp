<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyService.js'></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/pjpy/typj.js"></script>
<script type="text/javascript">
	function tjgl(xxdm){
		var xh = $('xh').value;
		var xn = $('xn').value;
		var jxjdm = $('jxjdm').value;
		var userType = $('userType').value;
		
		if (''==xh || ''==xn || ''==jxjdm){
			alert('带*项不能为空！');
			return false;
		}
		
		if ('10657' == xxdm) {
			pjpyService.pjpyTjsz({xh:xh,xn:xn,lx:'jxj',jxjdm:jxjdm},function(data){
				if ('false' == data.result && userType!="admin" && userType!="xx"){
					alert(data.message);
				} else {
					saveUpdate('/xgxt/typj.do?method=jxjsq&doType=save','save_xh-save_xn-save_jxjdm');
				}
			}); 
		} else if ('12645'==xxdm) {
			pjpyService.nbcxPjpyTjsz({xh:xh,xn:xn,lx:'jxj',jxjdm:jxjdm},function(data){
				if ('false' == data.result){
					alert(data.message);
				} else {
					saveUpdate('/xgxt/typj.do?method=jxjsq&doType=save','save_xh-save_xn-save_jxjdm');
				}
			}); 
		}
	}
	
	function printSqb() {
		var text = DWRUtil.getText('jxjdm');
		var pkValue = $('pkValue').value;
		var url = '/xgxt/typj.do?method=jxjPrint&jxjmc='+text+'&pkValue='+pkValue;
					
		window.open(url);
	}
</script>
</head>
<body onload="setSqsj();display();">
	<html:form action="/typj" method="post">
		<input type="hidden" id="url" name="url" value="/typj.do?method=jxjsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc-mzmc-zzmmmc-csrq-lxdh" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="yhInfo" id="yhInfo" value="${yhInfo }" />
		<input type="hidden" name="save_nd" value="${sqsjInfo.nd}"/>
		<input type="hidden" name="save_xq" value="${sqsjInfo.xq}"/>
		<input type="hidden" name="save_sqsj" value="${sqsjInfo.sqsj}" id="sqsj"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="now" value="${nowTime }" id="now"/>
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<input type="hidden" name="userType" value="${userType }" id="userType"/>

		<logic:equal value="10657" name="xxdm" scope="session">
			<logic:notEqual value="stu" name="userType" scope="session">
				<logic:notEqual value="xy" name="userType" scope="session">
					<input type="hidden" name="save_shzt" value="通过" />
					<input type="hidden" name="save_fdysh" value="通过" />
					<input type="hidden" name="save_xysh" value="通过" />
					<input type="hidden" name="save_xxsh" value="通过" />
				</logic:notEqual>
			</logic:notEqual>
		</logic:equal>	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4">
						<span>基本信息
						<logic:equal value="12645" name="xxdm" scope="session">
							<font color="red">（除素质与能力成就拓展奖学金外，每人每学年只能申请一项奖学金）</font>
						</logic:equal>
						</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th width="24%"><font color="red">*</font>学号</th>
					<td width="26%">
						<logic:equal value="stu" name="userType">
						<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')"  readonly="true"
						/>
						</logic:equal>
						<logic:notEqual value="stu" name="userType">
						<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')" 
						/>
						
							<button onclick="showTopWin('/xgxt/stu_info.do',800,600);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</logic:notEqual>
						
					</td>
					<th width="24%">姓名</th>
					<td width="26%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>${rs.xb }</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>专业</th>
					<td>
						${rs.zymc }
					</td>
					<th>班级</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th>民族</th>
					<td>${rs.mzmc }</td>
					<th>政治面貌</th>
					<td>${rs.zzmmmc }</td>
				</tr>
				<tr>
					<th>出生日期</th>
					<td>${rs.csrq }</td>
					<th>联系电话</th>
					<td>${rs.lxdh }</td>
				</tr>
				<tr>
					<th><font color="red">*</font>学年</th>
					<td>
						<html:text property="save_xn" readonly="true" value="${sqsjInfo.xn }" styleId="xn"></html:text>
					</td>
					<th><font color="red">*</font>奖学金名称</th>
					<td>
						<html:select property="save_jxjdm" styleId="jxjdm" onchange="setSqsj();">
							<html:options collection="jxjList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				</tbody>
			</table>
		
			<logic:notEqual value="11355" name="xxdm" scope="session">
				<table width="99%" id="rsTable" class="formlist">
					<logic:empty  name="zcpm">
						<thead>
						<tr><th  colspan="6"><span>综合素质测评<font color="blue">(根据参数设置模块中设置的评奖周期获取综测数据)</font></span></th></tr>						
						</thead>
						<tbody>
							<tr><td  colspan="6"><span>没有记录！</span></td></tr>
						</tbody>
					</logic:empty>

					<logic:notEmpty name="zcpm">
					<thead>
						<tr><th  colspan="6"><span>综合素质测评<font color="blue">(根据参数设置模块中设置的评奖周期获取综测数据)</font></span></th></tr>
						<tr>
							<td align="center">学年</td>
							<td align="center">学期</td>
							<td align="center">年度</td>
							<td align="center">名称</td>
							<td align="center">分数</td>
							<td align="center">排名</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="v" name="zcpm" offset="0" scope="request">
							<tr align="center" style="cursor:hand">
								<td>
									${v.xn }
								</td>
								<td>
									${v.xqmc }
								</td>
								<td>
									${v.nd }
								</td>
								<td>
									${v.mc }
								</td>
								<td>
									${v.fs }
								</td>
								<td>
									${v.pm }
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				
				<logic:equal name="xxdm" value="10657">
				<table width="99%" id="rsTable" class="formlist">
					<tbody>
					<tr><th>智成绩总绩点</th><td>${rs.zyjd }</td><th>总分排名比例<font color="red">(%)</font></th><td>${rs.zpmbl }</td></tr>
					<tr><th>智成绩排名</td><td>${rs.zypm }</td><th>智成绩排名比例<font color="red">(%)</font></th><td>${rs.zypmbl }</td></tr>
					</tbody>
				</table>
				</logic:equal>
				</logic:notEmpty>
				</table>
			</logic:notEqual>
			<!-- 贵州大学 -->
			<logic:equal value="10657" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/guizhdx_jxjsq.jsp"></jsp:include>
			</logic:equal>
			<!-- 南宁职业 -->
			<logic:equal value="11355" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/nnzy_jxjsq.jsp"></jsp:include>
			</logic:equal>
			<!-- 宁波城市 -->
			<logic:equal value="12645" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/nbcs_jxjsq.jsp"></jsp:include>
			</logic:equal>
			<table class="formlist" width="100%">
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <logic:equal value="10657" name="xxdm" scope="session">
							<button class="" style="" id="buttonSave" onclick="tjgl('${xxdm}');">
								保&nbsp;&nbsp;存
							</button>
						</logic:equal>
						<logic:equal value="12645" name="xxdm" scope="session">
							<button class="" style="" id="buttonSave"  onclick="tjgl('${xxdm}');">
								保&nbsp;&nbsp;存
							</button>
						</logic:equal>
						<logic:notEqual value="12645" name="xxdm" scope="session">
						<logic:notEqual value="10657" name="xxdm" scope="session">
							<button class="" style="" id="buttonSave"
								 onclick="saveUpdate('/xgxt/typj.do?method=jxjsq&doType=save','save_xh-save_xn-save_jxjdm');">
								保&nbsp;&nbsp;存
							</button>
						</logic:notEqual>
						</logic:notEqual>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<logic:equal value="10657" name="xxdm" scope="session">
							<button class="" onclick="printSqb();" >
									打印报表
							</button>
						</logic:equal>
						
						<logic:equal value="gb" name="lx">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="Close();return false;" >
									关&nbsp;&nbsp;闭
							</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
	</html:form>
	<logic:present name="yhInfo">
	
		<script>
				alert('提示信息: '+$('yhInfo').value != null ? $('yhInfo').value : "操作失败!");
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
	<logic:present name="message">
		<script>
				alert("${message}");
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>