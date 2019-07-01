<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<tr>
		<td colspan="4" class="tdbox">
		 <div style="overflow-x: auto;width:925px;">
			<table class="dateline" width="100%" id="jtqkdc_jtcy">
				<thead>
					<tr>
						<logic:present name="jtcyList">
							<logic:iterate id="j" name="jtcyList">
								<td>
									<logic:equal value="yes" property="sfbt" name="j">
										<input type="hidden" value="${j.zddm}" name="btx"/>
										<font color="red">*</font>
									</logic:equal>
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
						<logic:iterate id="c" name="cyList" indexId="index01">
							<tr>
								<logic:present name="jtcyList">
									<logic:iterate id="j" name="jtcyList" >
										<td>
											<logic:equal value="text" property="kjlx" name="j" >
												<input type="text" name="jtcy.${j.zddm}" maxlength="${j.zdcd}" id="jtcy.${j.zddm}_${index01}"
													   style="${j.kjys}" labName="${j.zddm}" onblur="${j.zdgs }"
													   value='<bean:write name="c" property="${j.zddm}"/>'/>																
											</logic:equal>
											<logic:equal value="select" property="kjlx" name="j">
												<select style="${j.kjys}" name="jtcy.${j.zddm}" labName="${j.zddm}" tempValue='<bean:write name="c" property="${j.zddm}"/>'>
													<option></option>
												</select>
											</logic:equal>
										</td>
									</logic:iterate>
								</logic:present>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:present name="blankList">
						<logic:iterate id="b" name="blankList" indexId="i">
							<tr>
								<logic:iterate id="j" name="jtcyList">
									<td>
										<logic:equal value="text" property="kjlx" name="j">
											<input type="text" name="jtcy.${j.zddm}" 
												   style="${j.kjys}" onblur="${j.zdgs }"
												   labName="${j.zddm}" maxlength="${j.zdcd}" id="jtcy.${j.zddm}_0${i}"/>																
										</logic:equal>
										<logic:equal value="select" property="kjlx" name="j">
											<select name="jtcy.${j.zddm}" labName="${j.zddm}" style="${j.kjys}" >
												<option></option>
											</select>
										</logic:equal>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:present>
				</tbody>
			</table>
			</div>
		</td>
	</tr>
</tbody>