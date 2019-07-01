<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<tr>
		<td colspan="4" class="tdbox">
			<table class="dateline" width="100%" id="jtqkdc_jtcy">
				<thead>
					<tr>
						<logic:present name="jtcyList">
							<logic:iterate id="j" name="jtcyList">
								<td>
									${j.zdmc }
									<logic:equal value="select" property="kjlx" name="j">
										<input type="hidden" value="${j.sjy }" name="sjy" selectName="${j.zddm}"/>
									</logic:equal>
								</td>
							</logic:iterate>
						</logic:present>
					</tr>
				</thead>
				<tbody>
					<logic:present name="cyList" >
						<logic:iterate id="c" name="cyList">
							<tr>
								<logic:present name="jtcyList">
									<logic:iterate id="j" name="jtcyList">
										<td style="word-break:break-all;">
											<logic:equal value="text" property="kjlx" name="j">
													<bean:write name="c" property="${j.zddm}"/>															
											</logic:equal>
											<logic:equal value="select" property="kjlx" name="j">
												<select name="jtcy.${j.zddm}" labName="${j.zddm}" tempValue='<bean:write name="c" property="${j.zddm}"/>'>
													<option></option>
												</select>
											</logic:equal>
										</td>
									</logic:iterate>
								</logic:present>
							</tr>
						</logic:iterate>
					</logic:present>
				</tbody>
			</table>
		</td>
	</tr>
</tbody>