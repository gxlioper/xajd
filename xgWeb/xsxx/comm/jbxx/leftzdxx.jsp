<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
				<logic:equal name="xsh" property="leftMap.lrxs" value="输入框">
					<html:text name="rs" property="${xsh.leftMap.zd}" disabled="${xsh.leftMap.disabled}"
						styleId="${xsh.leftMap.zd}" onblur="${xsh.leftMap.methods}" />
				</logic:equal>
				<logic:equal name="xsh" property="leftMap.lrxs" value="下拉列表">
					<html:select  name="rs" property="${xsh.leftMap.zd}" disabled="${xsh.leftMap.disabled}"
						styleId="${xsh.leftMap.zd}">
						<html:option value=""></html:option>
						<html:options collection="${xsh.leftMap.zd}List" property="dm"
							labelProperty="mc" />
					</html:select>
				</logic:equal>
				<logic:equal name="xsh" property="leftMap.lrxs" value="单选框">
					<logic:iterate name="${xsh.leftMap.zd}List" id="xbf">
						<html:radio name="rs" property="${xsh.leftMap.zd}" disabled="${xsh.leftMap.disabled}"
							styleId="${xsh.leftMap.zd}_${xbf.dm}" value="${xbf.dm}">${xbf.mc }</html:radio>
					</logic:iterate>
				</logic:equal>
				<logic:equal name="xsh" property="leftMap.lrxs" value="文本域">
					<html:textarea name="rs" property="${xsh.leftMap.zd}" styleId="${xsh.leftMap.zd}" disabled="${xsh.leftMap.disabled}"
						style="word-break:break-all;width:99%"  disabled="${xsh.leftMap.disabled}" onblur="${xsh.leftMap.methods}" />
				</logic:equal>
				<logic:equal name="xsh" property="leftMap.lrxs" value="时间格式">
					<html:text name="rs" property="${xsh.leftMap.zd}" styleId="${xsh.leftMap.zd}" disabled="${xsh.leftMap.disabled}"
						onclick="return showCalendar('${xsh.leftMap.zd}','y-mm-dd');"
						onblur="dateFormatChg(this)" readonly="true" />
				</logic:equal>
				<logic:equal name="xsh" property="leftMap.lrxs" value="特殊格式">
					<logic:notEmpty name="xsh" property="leftMap.jc">
						<html:select name="rs" property="${xsh.leftMap.jc}shen"
							styleId="${xsh.leftMap.jc}shen"
							onchange="loadShi('${xsh.leftMap.jc}shen','${xsh.leftMap.jc}shi','${xsh.leftMap.jc}xian');saveSsx('${xsh.leftMap.jc}');">
							<html:option value="">--请选择--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select>															
						<html:select name="rs"
							property="${xsh.leftMap.jc}shi" styleId="${xsh.leftMap.jc}shi" disabled="${xsh.leftMap.disabled}"
							onchange="loadXian('${xsh.leftMap.jc}shi','${xsh.leftMap.jc}xian');saveSsx('${xsh.leftMap.jc}');">
							<html:options collection="shiList" property="shidm"
								labelProperty="shimc" />
						</html:select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<html:select name="rs" onchange="saveSsx('${xsh.leftMap.jc}');" disabled="${xsh.leftMap.disabled}"
							property="${xsh.leftMap.jc}xian" styleId="${xsh.leftMap.jc}xian">
							<html:options collection="xianList" property="xiandm"
								labelProperty="xianmc" />
						</html:select>
						<html:hidden name="rs" property="${xsh.leftMap.jc}" styleId="${xsh.leftMap.jc}"/>
					</logic:notEmpty>
					<logic:equal name="xsh" property="leftMap.zd" value="nj">
						<html:select name="rs" property="nj" styleId="nj" disabled="${xsh.leftMap.disabled}"
							style="width:90px" onchange="initXyzybj();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</logic:equal>
					<!-- 特殊字段 学院  -->
					<logic:equal name="xsh" property="leftMap.zd" value="xydm">
						<input type="text" id="xymc" disabled="${xsh.leftMap.disabled}"
							onkeydown="return onlyBackSpace(this,event);" value="${rs.xymc }" />
						<button type="button" class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
							选择
						</button>
						<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
					</logic:equal>
					<!-- 特殊字段 专业  -->
					<logic:equal name="xsh" property="leftMap.zd" value="zydm">
						<input type="text" id="zymc" disabled="${xsh.leftMap.disabled}"
							onkeydown="return onlyBackSpace(this,event);" value="${rs.zymc }" />
						<button type="button" class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
							style="display: ">
							选择
						</button>
						<input type="hidden" name="zydm" tyleId="xydm" value="${rs.zydm}" />
					</logic:equal>
					<!-- 特殊字段 班级  -->
					<logic:equal name="xsh" property="leftMap.zd" value="bjdm">
						<input type="text" id="bjmc" disabled="${xsh.leftMap.disabled}"
							onkeydown="return onlyBackSpace(this,event);" value="${rs.bjmc }" />
						<button type="button" class="btn_01" id="button_bj" style="display: "
							onclick="getXyzybj('bj');">
							选择
						</button>
						<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
					</logic:equal>
				</logic:equal>
</html>
