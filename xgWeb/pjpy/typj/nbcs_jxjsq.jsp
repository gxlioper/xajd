<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script type="text/javascript">
	function selectCj() {
		var text = document.getElementById('cj').style;
		if (text.display == ''){
			text.display = 'none';
		} else {
			text.display = '';
		}
	}
</script>
<body>
	<fieldset>
		<legend>
			其它
		</legend>

		<table class="tbstyle" width="100%" id="szynltz" style="display:none">
			<tr height="40px">
				<td align="right" width="16%">
					申请类别：
				</td>
				<td colspan="3">
					<logic:present property="hjdj" name="rs">
						<input type="checkbox" value="1" onclick="display()"
							checked="checked" id="lb1">竞赛&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="hjdj" name="rs">
						<input type="checkbox" value="1" onclick="display()" id="lb1">竞赛&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>

					<logic:present property="kwmckh" name="rs">
						<input type="checkbox" value="2" onclick="display()"
							checked="checked" id="lb2")>著作/论文&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="kwmckh" name="rs">
						<input type="checkbox" value="2" onclick="display()" id="lb2">著作/论文&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>

					<logic:present property="cg" name="rs">
						<input type="checkbox" value="3" onclick="display()"
							checked="checked" id="lb3">专利、发明&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="cg" name="rs">
						<input type="checkbox" value="3" onclick="display()" id="lb3">专利、发明&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>

					<logic:present property="zysj" name="rs">
						<input type="checkbox" value="4" onclick="display()"
							checked="checked" id="lb4">其他&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:present>
					<logic:notPresent property="zysj" name="rs">
						<input type="checkbox" value="4" onclick="display()" id="lb4">其他&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>



				</td>
			</tr>
			<tr height="40px" id="1" style="display:none">
				<td align="right">
					获奖等级：
				</td>
				<td width="34%">
					<html:text property="save_hjdj" maxlength="20" value="${rs.hjdj }" styleId="hjdj"></html:text>
				</td>
				<td width="16%"></td>
				<td></td>
			</tr>
			<tr height="40px" id="2" style="display:none">
				<td align="right">
					赛事主办单位：
				</td>
				<td>
					<html:text property="save_zbdw" maxlength="50" value="${rs.zbdw }" styleId="zbdw"></html:text>
				</td>
				<td align="right">
					证书颁发单位：
				</td>
				<td>
					<html:text property="save_bfdw" maxlength="50" value="${rs.bfdw }" styleId="bfdw"></html:text>
				</td>
			</tr>

			<tr height="40px" id="3" style="display:none">
				<td align="right">
					刊物名称及刊号：
				</td>
				<td>
					<html:text property="save_kwmckh" maxlength="50" styleId="kwmckh"
						value="${rs.kwmckh }"></html:text>
				</td>
				<td></td>
				<td></td>

			</tr>
			<tr height="40px" id="4" style="display:none">
				<td align="right">
					作者排名：
				</td>
				<td>
					<html:text property="save_zzpm" maxlength="10" styleId="zzpm"
						onkeyup="value=value.replace(/[^\d]/g,'')" value="${rs.zzpm }"></html:text>
				</td>
				<td align="right">
					发表时间：
				</td>
				<td>
					<html:text property="save_fbsj" maxlength="10" styleId="fbsj"
						onclick="showCalendar(this.id,'y-mm-dd');"
						onblur='dateFormatChg(this)' readonly="true" value="${rs.fbsj }"></html:text>
				</td>
			</tr>

			<tr height="40px" id="5" style="display:none">
				<td align="right">
					成果：
				</td>
				<td>
					<html:text property="save_cg" maxlength="50" value="${rs.cg }" styleId="cg"></html:text>
				</td>
				<td align="right">
					成果级别：
				</td>
				<td>
					<html:text property="save_cgjb" maxlength="20" value="${rs.cgjb }" styleId="cgjb"></html:text>
				</td>
			</tr>
			<tr height="40px" id="6">
				<td align="right">
					<font color="red"><限500字>
					</font>主要事迹：
				</td>
				<td colspan="3">
					<html:textarea property="save_zysj" style="width:90%" rows="5"
						styleId="zysj"
						value="${rs.zysj }"
						onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
		</table>

		<table class="tbstyle" width="100%" id="nbcsjxj">
			<tr height="40px">
				<td align="right" width="16%">
					班级人数：
				</td>
				<td width="34%">
					<html:text property="save_bjrs"  
						readonly="true"
						value="${rs.bjrs }${bjrs }"></html:text>
				</td>
				<td align="right" width="16%">
					学绩分成绩：
				</td>
				<td>
					<html:text property="save_xjfcj"  
						readonly="true"
						value="${rs.xjfcj }${xjf.xjfcj }"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">
					学绩分名次：
				</td>
				<td>
					<html:text property="save_xjfpm" 
						readonly="true"
						value="${rs.xjfpm }${xjf.xjfpm }"></html:text>
				</td>
				<td align="right">
					学生品行评价等级:
				</td>
				<td>
					<html:text property="save_pxdj" 
							   value="${rs.pxdj }${pxdj }" 
							   readonly="true"
							   styleId="pxdj"
							   maxlength="10"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">
					《学生体质健康标准》等级:
				</td>
				<td colspan="3">
					<html:text property="save_jkdj" value="${rs.jkdj }" maxlength="10"></html:text>
				</td>
			</tr>
		</table>
		
		
		<table class="tbstyle" width="100%" id="gjlzjxj">
			<tr height="40px">
				<td align="right" width="16%">家庭户口:</td>
				<td width="34%">
					<logic:notPresent name="rs">
						<html:radio property="save_jthk" value="城镇">城镇</html:radio>
						<html:radio property="save_jthk" value="农村">农村</html:radio>
					</logic:notPresent>
					<logic:present name="rs">
						<html:radio property="save_jthk" value="城镇" name="rs">城镇</html:radio>
						<html:radio property="save_jthk" value="农村" name="rs">农村</html:radio>
					</logic:present>
					
				</td>
				<td align="right" width="16%">家庭人口总数:</td>
				<td width="34%">
					<html:text property="save_rkzs" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" value="${rs.rkzs }"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">家庭月总收入:</td>
				<td>
					<html:text property="save_jtyzsr"  maxlength="10" value="${rs.jtyzsr }${rs.jtzsr }" onblur="checkMoney(this)"></html:text>
				</td>
				<td align="right">人均收入:</td>
				<td>
					<html:text property="save_rjsr"  maxlength="10" value="${rs.rjsr }${rs.jtrjsr }" onblur="checkMoney(this)"></html:text>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">收入来源:</td>
				<td>
					<html:text property="save_srly"  maxlength="10" value="${rs.srly }"></html:text>
				</td>
				<td align="right">邮政编码:</td>
				<td>
					<html:text property="save_yzbm" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="7" value="${rs.yzbm }${rs.yb }"></html:text>
				</td>
				
			</tr>
			<tr height="40px">
				<td align="right">家庭详细住址:</td>
				<td colspan="3">
					<html:text property="save_jtxxdz"  maxlength="100" value="${rs.jtxxdz }${rs.jtszd }" style="width:90%"></html:text>
				</td>
			</tr>
			
			<tr>
				<td bgcolor="#CCCCCC" colspan="4" align="center" onclick="selectCj();">
					<div style="color:blue;cursor:hand"><b>各课成绩</b></div>
				</td>
			</tr>
			<tr id="cj" style="display :none">
				<td align="right">
					各课成绩：
				</td>
				<td colspan="3" align="left" >
					<logic:empty name="cjxx">
						没有记录!
					</logic:empty>
					<logic:present name="cjxx">
						<logic:notEmpty name="cjxx">
						<table class="tbstyle" width="85%"  >
							<thead>
							<tr>
								<td>学年</td>
								<td>学期</td>
								<td>课程名称</td>
								<td>课程性质</td>
								<td>成绩</td>
								<td>补考成绩</td>
							</tr>
							</thead>
							<logic:iterate id="s" name="cjxx">
								<tr>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</logic:notEmpty>
					</logic:present>
				</td>
			</tr>
			<tr height="40px">
				<td align="right">
					<font color="red"><限500字></font>曾获何种奖励:
				</td>
				<td colspan="3">
					<html:textarea property="save_hjqk" rows="5" style="width:85%" value="${rs.hjqk }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
		</table>
		<table class="tbstyle" width="100%" >
			<tr >
				<td align="right" width="16%">
					<font color="red"><限500字></font>申请理由：
				</td>
				<td>
					<html:textarea property="save_sqly" rows="8" style="width:85%" value="${rs.sqly }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
		</table>
	</fieldset>
</body>
