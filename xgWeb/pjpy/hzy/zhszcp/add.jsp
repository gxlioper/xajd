<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/pjpy/hzy/hzzyjs.js">
</script>
<script type='text/javascript'
			src='/xgxt/dwr/interface/hzyZhszcpf.js'></script>
<body onload="checkWinType();">
	<html:form action="/pjpyhzzywh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyhzzy" key="pjpy_hzzy_zhszcp" />
       		</div>
    	</div>
    	<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("您输入的学号无效!");
    				</script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/hzzy_zhszcpadd.do" />
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							单个增加
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>学号：
					</td>
					<td align="left">
						<html:text name='rs' property="xh"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="">
								选择
							</button>
					</td>
					<td align="right">
						<font color="red">*</font>学年：
					</td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
						<font color="red">*</font>年度：
					</td>
					<td align="left">
						<html:select property="nd" styleId="nd" styleClass="select" style="width:90px">
							<html:options collection="xnList" property="nd" labelProperty="nd"/>
						</html:select>
					</td>
					
				</tr>
				<tr style="width:22px">
					<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<td align="right">
						<font color="red">*</font>学期：
					</td>
					<td align="left">
						<html:select property="xq" styleId="xq" 
						styleClass="select" style="width:90px">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />德育分：
					</td>
					<td align="left">
						<html:text property="xydykpf" styleId="xydykpf" styleClass="inputtext" 
						onblur="if (ckinpdata(this)) countZf('xydykpf','zcj','tcj','gydykpf','gzxxcx');" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<td align="right">
						智成绩：
					</td>
					<td align="left">
						<html:text property="zcj" styleId="zcj" styleClass="inputtext" 
							onblur="if (ckinpdata(this)) countZf('xydykpf','zcj','tcj','gydykpf','gzxxcx');" maxlength="5"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<td align="right">
						体成绩：
					</td>
					<td align="left">
						<html:text property="tcj" styleId="tcj" styleClass="inputtext"
						onblur="if (ckinpdata(this)) countZf('xydykpf','zcj','tcj','gydykpf','gzxxcx');" maxlength="5"></html:text>
					</td>
				</tr>
				<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">
							学习创新分：
						</td>
						<td align="left">
							<html:text property="gzxxcx" styleId="gzxxcx" styleClass="inputtext"
						onblur="if (ckinpdata(this)) countZf('xydykpf','zcj','tcj','gydykpf','gzxxcx');" maxlength="5"></html:text>
						</td>
				</tr>
				<tr style="height:22px">
				<td align="right">
					是否住宿生：
				</td>
				<td align="left">
					<bean:write name="rs" property="sfzss"/>
					<input type="hidden" name="sfzss" id="sfzss" value="<bean:write name="rs" property="sfzss"/>"/>
				</td>
					<td align="right">
						公寓德育分：
					</td>
					<td align="left">
						<html:text property="gydykpf" styleId="gydykpf" onblur="if (ckinpdata(this)) countZf('xydykpf','zcj','tcj','gydykpf','gzxxcx');"
						styleClass="inputtext" maxlength="5"></html:text>
					</td>
				</tr>
				<tr>
					<td align="right">
						综合测评名次：	
					</td>
					<td align="left">
						<html:text property="zhszcpcjpm" styleId="zhszcpcjpm" maxlength="5" 
						styleClass="inputtext" onblur="ckinpdata(this);"></html:text>
					</td>
					<td align="right">
						综合素质测评总分：
					</td>
					<td align="left">
						<html:text property="zhszcpzf" styleId="zhszcpzf" maxlength="5" styleClass="inputtext"
						onblur=""></html:text>
					</td>
					
				</tr>
				<tr>
					<td colspan="" align="right">
						备注：
					</td>
					<td colspan="3" align="left">
						<html:textarea property="bz" styleClass="inputtext" 
						rows="4" styleId="bz" style="width:98%"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('hzzy_zhszcpsave.do','xh-xn-xq-nd');"
						style="width:80px">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>