<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 无题目 -->
<logic:empty name="questionsList">

</logic:empty>
<!-- 有题目 -->
<logic:notEmpty name="questionsList">
	<logic:iterate name="questionsList" id="stss" indexId="ss">
		<tr>
			<td colspan="2">
				<font size="5">${stss.num }.${stss.ssmc }</font><font color="blue">(注：限录入500字)</font>
			</td>
		</tr>
		<logic:iterate name="stss" property="stList" id="st" indexId="num">
			<tr>
				<td colspan="2">
					<font size="3">${num+1 }.${st.stmc }</font><br>
					<input type="hidden" name="questionsBh" value="${st.stbh }"/>	
				</td>
			</tr>
			<logic:iterate name="st" property="stda" id="da"  indexId="index">
				<tr>
					<td colspan="2" align="center">
						<textarea rows="5" style="width:90%" cols="" id="" name="questionsDa" onblur="chLeng(this,500)" type="_moz">${da.danr }</textarea>
					</td>
				</tr>
			</logic:iterate>
		</logic:iterate>
	</logic:iterate>
</logic:notEmpty>