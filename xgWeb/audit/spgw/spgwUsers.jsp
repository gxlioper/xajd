<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
    </head>
	<body>
        <div class="open_win">
            <h3>
                <em> 岗位用户列表 </em>
            </h3>
            <div class="formbox">
			  <h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue"></font>	
						</logic:notEmpty>
					</span>
				</h3>
			  <logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />

								</td>
                                <td>
                                    用户名
                                </td>
                                <td>
                                    姓名
                                </td>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<input type="checkbox" name="checkVal" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>
										/>
									</td>
									<logic:iterate id="v" name="s" offset="0" >
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				  </div>
			  </logic:notEmpty>
			</div>
            <br />
            <div style="width:100%; text-align:right;">
                <button type="button" class="button2" onclick="">
                    打  印
                </button>
                <button type="button" class="button2" onclick="Close();return false;">
                    关 闭
                </button>
            </div>
        </div>
	</body>
</html>