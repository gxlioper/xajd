<%@ page language="java" contentType="text/html; charset=GBK"%>
<input type="hidden" name="xyV" id="xyV" value="" />
<input type="hidden" name="zyV" id="zyV" value="" />
<input type="hidden" name="bjV" id="bjV" value="" />
<input type="hidden" name="njV" id="njV" value="" />
<input type="hidden" name="userType" id="userType" value="${userType }"/>
<input type="hidden" name="pk" id="pk" value="${pk }"/>
<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
<input type="hidden" name="doType" id="doType" value="${doType }"/>
<input type="hidden" name="userName" id="userName" value="${userName }"/>
<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />