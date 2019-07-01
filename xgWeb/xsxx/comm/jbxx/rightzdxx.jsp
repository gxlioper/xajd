<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
				<logic:equal name="xsh" property="rightMap.lrxs" value="输入框">
					<html:text name="rs" property ="${xsh.rightMap.zd}" disabled="${xsh.rightMap.disabled}" 
						styleId="${xsh.rightMap.zd}" onblur="${xsh.rightMap.methods}"/>
				</logic:equal>
				<logic:equal name="xsh" property="rightMap.lrxs" value="下拉列表">
					<html:select name="rs" property="${xsh.rightMap.zd}" disabled="${xsh.rightMap.disabled}"
						styleId="${xsh.rightMap.zd}">
						<option value=""></option>
						<html:options collection="${xsh.rightMap.zd}List" property="dm"
							labelProperty="mc" />
					</html:select>
				</logic:equal>
				<logic:equal name="xsh" property="rightMap.lrxs" value="单选框">
					<logic:iterate name="${xsh.rightMap.zd}List" id="xbf">
						<html:radio name="rs" property="${xsh.rightMap.zd}" disabled="${xsh.rightMap.disabled}"
							styleId="${xsh.rightMap.zd}" value="${xbf.dm}">${xbf.mc }</html:radio>
					</logic:iterate>
				</logic:equal>
				<logic:equal name="xsh" property="rightMap.lrxs" value="文本域">
					<html:textarea name="rs" property="${xsh.rightMap.zd}" styleId="${xsh.rightMap.zd}" disabled="${xsh.rightMap.disabled}"
						style="word-break:break-all;width:99%"  disabled="${xsh.rightMap.disabled}" onblur="${xsh.rightMap.methods}" />
				</logic:equal>
				<logic:equal name="xsh" property="rightMap.lrxs" value="时间格式">
					<html:text name="rs" property="${xsh.rightMap.zd}" disabled="${xsh.rightMap.disabled}"
						styleId="${xsh.rightMap.zd}"
						onclick="return showCalendar('${xsh.rightMap.zd}','y-mm-dd');"
						onblur="dateFormatChg(this)" readonly="true" />
				</logic:equal>
				<logic:equal name="xsh" property="rightMap.lrxs" value="特殊格式">
					<logic:notEmpty name="xsh" property="rightMap.jc">
						<html:select name="rs" property="${xsh.rightMap.jc}shen" disabled="${xsh.rightMap.disabled}"
							styleId="${xsh.rightMap.jc}shen"
							onchange="loadShi('${xsh.rightMap.jc}shen','${xsh.rightMap.jc}shi','${xsh.rightMap.jc}xian');saveSsx('${xsh.rightMap.jc}');">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<html:select name="rs" disabled="${xsh.rightMap.disabled}"
							property="${xsh.rightMap.jc}shi" styleId="${xsh.rightMap.jc}shi"
							onchange="loadXian('${xsh.rightMap.jc}shi','${xsh.rightMap.jc}xian');saveSsx('${xsh.rightMap.jc}');">
							<html:options collection="shiList" property="shidm"
								labelProperty="shimc" />
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<html:select name="rs" disabled="${xsh.rightMap.disabled}"
							property="${xsh.rightMap.jc}xian"
							styleId="${xsh.rightMap.jc}xian" onchange="saveSsx('${xsh.rightMap.jc}');">
							<html:options collection="xianList" property="xiandm"
								labelProperty="xianmc" />
						</html:select>
						<html:hidden name="rs" property="${xsh.rightMap.jc}" styleId="${xsh.rightMap.jc}"/>
					</logic:notEmpty>
					<logic:equal name="xsh" property="rightMap.zd" value="nj">
						<html:select name="rs" property="nj" styleId="nj" disabled="${xsh.rightMap.disabled}"
							style="width:90px" onchange="initXyzybj();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</logic:equal>
					<!-- 特殊字段 学院  -->
					<logic:equal name="xsh" property="rightMap.zd" value="xydm">
						<input type="text" id="xymc" disabled="${xsh.rightMap.disabled}"
							onkeydown="return onlyBackSpace(this,event);" value="${rs.xymc }" />
						<button type="button" class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
							选择
						</button>
						<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
					</logic:equal>
					<!-- 特殊字段 专业  -->
					<logic:equal name="xsh" property="rightMap.zd" value="zydm">
						<input type="text" id="zymc" disabled="${xsh.rightMap.disabled}"
							onkeydown="return onlyBackSpace(this,event);" value="${rs.zymc }" />
						<button type="button" class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
							style="display: ">
							选择
						</button>
						<input type="hidden" name="zydm" tyleId="xydm" value="${rs.zydm}" />
					</logic:equal>
					<!-- 特殊字段 班级  -->
					<logic:equal name="xsh" property="rightMap.zd" value="bjdm">
						<input type="text" id="bjmc" disabled="${xsh.rightMap.disabled}"
							onkeydown="return onlyBackSpace(this,event);" value="${rs.bjmc }" />
						<button type="button" class="btn_01" id="button_bj" style="display: "
							onclick="getXyzybj('bj');">
							选择
						</button>
						<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
					</logic:equal>
				</logic:equal>
</html>
