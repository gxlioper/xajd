<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
	</head>
<body onload="checkWinType();">
	<html:form action="/xljkXlcyjg" method="post">
    	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>心理健康 - 心理测验结果维护 - 人格因素心理问卷维护</a>
			</p>
		</div>
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-csrq" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-csrq" />
			<input type="hidden" id="url" name="url" value="/xljk_tyb_addDxsrgyscy.do" />
			<table style="width:100%" class="tbstyle">
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>单个增加</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button name="保存" id="btn_save"  onclick="saveinfo('xljk_tyb_addDxsrgyscy.do?act=save','xh-xm-cssj');">保 存</button>
			          </logic:notEqual>
			          &nbsp;
			            <button name="关闭" id="btn_close" onclick="Close();return false;">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    
				<tbody>
				<tr style="width:22px">
					<th>
						<font color="red">*</font>学号
					</th>
					<td align="left">
					<html:text name='rs' property="xh" styleId="xh" maxlength="20"
						onkeypress="autoFillStuInfo(event.keyCode,this);checkXhExists('xm-xb-csrq-nj-xymc-zymc-bjmc')" />
					<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						 id="buttonFindStu" class="btn_01">
						选择
					</button>
				</td>
					<th>
						<font color="red">*</font>测试时间
					</th>
					<td align="left">
						<html:text property="cssj" styleId="cssj" readonly="true" onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cssj','y-mm-dd');"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th>
							<font color="red">*</font>姓名
						</th>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<th>
						躯体症状测试分值
					</th>
					<td align="left">
						<html:text property="qtzz" styleId="qtzz" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
					
				</tr>
				<tr style="width:22px">
					<th>
							性别
						</th>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<th>
						精神分裂测试分值
					</th>
					<td align="left">
						<html:text property="jsfl" styleId="jsfl" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th>
							出生日期
						</th>
						<td align="left">
							<html:text name='rs' property="csrq" styleId="csrq" readonly="true"/>
						</td>
					<th>
						抑郁症测试分值
					</th>
					<td align="left">
						<html:text property="yyz" styleId="yyz" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th>
							年级
						</th>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<th>
						神经症测试分值
					</th>
					<td align="left">
						<html:text property="sjz" styleId="sjz" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<th>
						总分
					</th>
					<td align="left">
						<html:text property="zf" styleId="zf" onkeyup="ckinpdata(this)" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<th>
							专业
						</th>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<th>
						&nbsp;
					</th>
					<td align="left">
						&nbsp;
					</td>
				</tr>
				<tr>
						<th>
							班级
						</th>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<th>
							&nbsp;
						</th>
						<td align="left">
							&nbsp;
						</td>
				</tr>
				</tbody>
			</table>
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>