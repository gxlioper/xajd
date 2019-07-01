<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>项目基本情况</span>				
			</th>
		</tr>
	</thead>
	<tbody>
		
		
		<!-- 附件上传 2010-11-29 qph -->
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="file">
				<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">	
					<logic:equal name="nr" property="zdlx" value="file">
						<tr>
							<th>附件</th>
							<td colspan="3">
								<logic:notEqual value="" property="filepath" name="nr">
									<a href="commXszz.do?method=downLoadFile&pkValue=${xmInfo.xmdm }${xmInfo.xh }${xmInfo.sqsj }&filePath=${nr.filepath }&fileName=${nr.filename }&tableName=${xmInfo.xmb }&pkKey=xmdm||xh||sqsj"
										target="_self"/> <font color="red">下载附件</font></a>
										
									<a href="#" onclick="refreshForm('/xgxt/commXszz.do?method=deleteFile&xmdm=${xmInfo.xmdm }&tableName=${xmInfo.xmb }&sqsj=${xmInfo.sqsj }&filepath=${nr.filepath }&url=xmsqUpdate&pkValue=${xmInfo.xmdm }${xmInfo.xh }${xmInfo.sqsj }&doType=${doType }')" />点击删除</a>
								</logic:notEqual>
								<logic:equal value="" property="filepath" name="nr">
									<input type="file" name="uploadFile" style="width:90%">
								</logic:equal>
							</td>
						</tr>
					</logic:equal>
				</logic:iterate>
			</logic:equal>
		</logic:iterate>
		<!-- end 附件上传 -->
		
		<!-- 占一个单元格的字段 -->			
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="short">
				<tr style="height: 23px">
					<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">
						<logic:notEqual name="nr" property="lastZd" value="yes">
							<th align="right" width="15%">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="是">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
							</th>
							<td align="left" width="35%">
								<!-- 文本框 -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- 数字限制 -->
									<logic:equal name="nr" property="lrxz" value="数字">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											maxlength="20"
											onkeypress="chkonlynum(this,event)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 金额限制 -->
									<logic:equal name="nr" property="lrxz" value="金额">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 日期限制 -->
									<logic:equal name="nr" property="lrxz" value="日期">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- 无限制 -->
									<logic:equal name="nr" property="lrxz" value="无限制">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- 下拉框 -->
								<logic:equal name="nr" property="kjlx" value="select">
									<select name="save_${nr.zd }" id="${nr.zd }" ></select>
									<input type="hidden" id="select_${nr.zd }" value="${nr.zdz }">
								</logic:equal>								
							</td>
							<%if((index.intValue()+1)%2==0){%>
								<% out.print("</tr>"); %>
							<%}%> 
						</logic:notEqual>
						<logic:equal name="nr" property="lastZd" value="yes">
							<%if((index.intValue()+1)%2==0){%>
								<th align="right" width="15%">
									${nr.zdm }
									<logic:equal name="nr" property="bxlr" value="是">
										<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
									</logic:equal>
								</th>
								<td align="left" width="35%">
								<!-- 文本框 -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- 数字限制 -->
									<logic:equal name="nr" property="lrxz" value="数字">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 金额限制 -->
									<logic:equal name="nr" property="lrxz" value="金额">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 日期限制 -->
									<logic:equal name="nr" property="lrxz" value="日期">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- 无限制 -->
									<logic:equal name="nr" property="lrxz" value="无限制">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- 下拉框 -->
								<logic:equal name="nr" property="kjlx" value="select">
									<select name="save_${nr.zd }" id="${nr.zd }" value=""></select>
									<input type="hidden" id="select_${nr.zd }" value="${nr.zdz }">
								</logic:equal>
								</td>
							<%}%> 	
							<%if((index.intValue()+1)%2==1){%>
								<th align="right" width="15%">
									${nr.zdm }
									<logic:equal name="nr" property="bxlr" value="是">
										<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
									</logic:equal>
								</th>
								<td align="left" width="35%">
								<!-- 文本框 -->
								<logic:equal name="nr" property="kjlx" value="text">
									<!-- 数字限制 -->
									<logic:equal name="nr" property="lrxz" value="数字">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return onlyNum(this,20)" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 金额限制 -->
									<logic:equal name="nr" property="lrxz" value="金额">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											onkeypress="return sztzNumInputValue(this,20)" 
											maxlength="20" 
											style="ime-mode:disabled"/>
									</logic:equal>
									<!-- 日期限制 -->
									<logic:equal name="nr" property="lrxz" value="日期">
										<input type="text" name="save_${nr.zd }" 
											id="${nr.zd }" 
											value="${nr.zdz }" 
											readonly="true"  style="cursor:hand;"
											onblur="dateFormatChg(this)"
											onclick="time(this.id)"/>
									</logic:equal>
									<!-- 无限制 -->
									<logic:equal name="nr" property="lrxz" value="无限制">
										<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="20"/>
									</logic:equal>
								</logic:equal>
								<!-- 下拉框 -->
								<logic:equal name="nr" property="kjlx" value="select">
									<select name="save_${nr.zd }" id="${nr.zd }" value=""></select>
									<input type="hidden" id="select_${nr.zd }" value="${nr.zdz }">
								</logic:equal>	
								</td>
								<td></td>
								<td></td>
							<%}%> 	
							<% out.print("</tr>"); %>
						</logic:equal>
					</logic:iterate>		
				</tr>
			</logic:equal>
		</logic:iterate>
		<!-- 占一个单元格的字段 end -->
		
		<!-- 占多个单元格的字段 -->
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="long">
				<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">	
					<logic:equal name="nr" property="zdlx" value="long">
						<tr style="height: 23px">						
							<th align="right" width="15%">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="是">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
							</th>
							<td align="left" colspan="3">
								<!-- 数字限制 -->
								<logic:equal name="nr" property="lrxz" value="数字">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										onkeypress="return onlyNum(this,20)" 
										style="ime-mode:disabled;width: 90%"/>
								</logic:equal>
								<!-- 金额限制 -->
								<logic:equal name="nr" property="lrxz" value="金额">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										onkeypress="return sztzNumInputValue(this,20)" 
										maxlength="20" 
										style="width:90%;ime-mode:disabled"/>
								</logic:equal>
								<!-- 日期限制 -->
								<logic:equal name="nr" property="lrxz" value="日期">
									<input type="text" name="save_${nr.zd }" 
										id="${nr.zd }" 
										value="${nr.zdz }" 
										readonly="true"  style="cursor:hand;width: 90%"
										onblur="dateFormatChg(this)"
										onclick="time(this.id)"/>
								</logic:equal>
								<!-- 无限制 -->
								<logic:equal name="nr" property="lrxz" value="无限制">
									<input type="text" name="save_${nr.zd }" id="${nr.zd }" value="${nr.zdz }" maxlength="50" style="width: 90%"/>
								</logic:equal>
							</td>				
						</tr>
					</logic:equal>
					
					
					
					
					<logic:equal name="nr" property="zdlx" value="textarea">
						<tr style="height: 23px">						
							<th align="right" width="15%">
								${nr.zdm }
								<logic:equal name="nr" property="bxlr" value="是">
									<input type="hidden" name="bxlr" value="${nr.zdm }!!@@!!${nr.zd }">
								</logic:equal>
								<br>
								<font color="red">(限制录入500字)</font>
							</th>
							<td align="left" colspan="3">
								<textarea rows="15" style="width:600px" name="save_${nr.zd }" id="${nr.zd }" onblur="chLeng(this,500)" type="_moz">${nr.zdz }</textarea>
							</td>			
						</tr>
					</logic:equal>				
					
				</logic:iterate>		
			</logic:equal>
		</logic:iterate>
		<!-- 占多个单元格的字段end -->
		<!-- 项目分级情况 -->
		<logic:notEmpty name="xmfjqkList">
			<tr style="height: 23px">
				<td align="center" colspan="4">
					项目级别情况
				</td>		
			</tr>
			<logic:iterate id="xmjb" name="xmfjqkList">
				<tr style="height: 23px">
					<th align="right" width="15%">
						<logic:equal name="xmjb" property="fjmc" value="无">
							金额
						</logic:equal>
						<logic:notEqual name="xmjb" property="fjmc" value="无">
							${xmjb.fjmc }
						</logic:notEqual>
					</td>
					<td align="left" colspan="3">
						<logic:notEqual name="xmjb" property="fjqdje" value="无">
							${xmjb.fjqdje }
						</logic:notEqual>
						<logic:equal name="xmjb" property="fjqdje" value="无">
							<logic:equal name="xmjb" property="fjxxje" value="无">
								<logic:equal name="xmjb" property="fjsxje" value="无">
									不涉及金额
								</logic:equal>
							</logic:equal>
						</logic:equal>
						<logic:notEqual name="xmjb" property="fjxxje" value="无">
							<logic:equal name="xmjb" property="fjqdje" value="无">
								<logic:notEmpty name="xmjb" property="fjxxje">
									${xmjb.fjxxje }
								</logic:notEmpty>
								<logic:empty name="xmjb" property="fjxxje">
									无下限
								</logic:empty>
								--
								<logic:notEmpty name="xmjb" property="fjsxje">
									${xmjb.fjsxje }
								</logic:notEmpty>
								<logic:empty name="xmjb" property="fjsxje">
									无上限
								</logic:empty>
							</logic:equal>
						</logic:notEqual>
					</td>			
				</tr>
			</logic:iterate>
		</logic:notEmpty>
		<!-- 项目分级情况 end-->
		<tr style="height: 23px">
			<th align="right" width="15%">
				项目说明
			</th>
			<td align="left" colspan="3">
				<html:textarea name="xmInfo" property="xmsm" rows="15" style="width : 600px" disabled="true"></html:textarea>
			</td>			
		</tr>
	<tbody>
</table>