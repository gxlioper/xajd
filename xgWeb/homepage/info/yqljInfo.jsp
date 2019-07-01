<%@ page language="java" contentType="text/html; charset=GBK"%>
<div class="piclink_04">
	<ul>
		<logic:iterate name="yqljList" id="yqlj" indexId="index">
			<logic:empty name="yqlj" property="xmmc" >
				<li>
					<a href="#">							
					</a>
				</li>
			</logic:empty>
			<logic:notEmpty name="yqlj" property="xmmc">
				<li>
					<a href="${yqlj.xmnr }"  target="_blank">
					${yqlj.xmmc}						
					</a>
				</li>
			</logic:notEmpty>
		</logic:iterate>
	</ul>
</div>