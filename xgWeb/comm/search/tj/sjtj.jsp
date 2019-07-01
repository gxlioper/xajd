<%@ page language="java" contentType="text/html; charset=GBK"%>

<!-- 毕业年月 -->
<logic:equal name="tjMap" property="tj" value="byny">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<li>
					开始时间
					<input type="text" name="searchModel.search_tj_kssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_kssj','ymmdd');"
						id="${tjMap.tj }_kssj" onclick="return showCalendar('${tjMap.tj }_kssj','y-mm-dd');"/>
				</li>
				<li>
					结束时间
					<input type="text" name="searchModel.search_tj_jssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_jssj','ymmdd');"
						id="${tjMap.tj }_jssj" onclick="return showCalendar('${tjMap.tj }_jssj','y-mm-dd');"/>
				</li>
			</ul>
			<input type="hidden" name="search_tj_sjmc" value="${tjMap.mc }">
		</dd>
	</dl>
</logic:equal>
<!-- 毕业年月 end-->

<!-- 出生日期 -->
<logic:equal name="tjMap" property="tj" value="csrq">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<li>
					开始时间
					<input type="text" name="searchModel.search_tj_kssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_kssj','ymmdd');"
						id="${tjMap.tj }_kssj" onclick="return showCalendar('${tjMap.tj }_kssj','y-mm-dd');"/>
					</li>
				<li>
					结束时间
					<input type="text" name="searchModel.search_tj_jssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_jssj','ymmdd');"
						id="${tjMap.tj }_jssj" onclick="return showCalendar('${tjMap.tj }_jssj','y-mm-dd');"/>
				</li>
			</ul>
			<input type="hidden" name="search_tj_sjmc" value="${tjMap.mc }">
		</dd>
	</dl>
</logic:equal>
<!-- 出生日期 end-->

<!-- 异动日期 -->
<logic:equal name="tjMap" property="tj" value="ydrq">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<li>
					开始时间
					<input type="text" name="searchModel.search_tj_kssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_kssj','ymmdd');"
						id="${tjMap.tj }_kssj" onclick="return showCalendar('${tjMap.tj }_kssj','y-mm-dd');"/>
					</li>
				<li>
					结束时间
					<input type="text" name="searchModel.search_tj_jssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_jssj','ymmdd');"
						id="${tjMap.tj }_jssj" onclick="return showCalendar('${tjMap.tj }_jssj','y-mm-dd');"/>
				</li>
			</ul>
			<input type="hidden" name="search_tj_sjmc" value="${tjMap.mc }">
		</dd>
	</dl>
</logic:equal>
<!-- 异动日期 end-->

<!-- 异动截止日期 -->
<logic:equal name="tjMap" property="tj" value="ydjzrq">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<li>
					开始时间
					<input type="text" name="searchModel.search_tj_kssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_kssj','ymmdd');"
						id="${tjMap.tj }_kssj" onclick="return showCalendar('${tjMap.tj }_kssj','y-mm-dd');"/>
					</li>
				<li>
					结束时间
					<input type="text" name="searchModel.search_tj_jssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_jssj','ymmdd');"
						id="${tjMap.tj }_jssj" onclick="return showCalendar('${tjMap.tj }_jssj','y-mm-dd');"/>
				</li>
			</ul>
			<input type="hidden" name="search_tj_sjmc" value="${tjMap.mc }">
		</dd>
	</dl>
</logic:equal>
<!-- 异动截止日期 end-->

<!-- 加入年月 -->
<logic:equal name="tjMap" property="tj" value="jrsj">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<li>
					开始时间
					<input type="text" name="searchModel.search_tj_kssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_kssj','ymmdd');"
						id="${tjMap.tj }_kssj" onclick="return showCalendar('${tjMap.tj }_kssj','y-mm-dd');"/>
				</li>
				<li>
					结束时间
					<input type="text" name="searchModel.search_tj_jssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_jssj','ymmdd');"
						id="${tjMap.tj }_jssj" onclick="return showCalendar('${tjMap.tj }_jssj','y-mm-dd');"/>
				</li>
			</ul>
			<input type="hidden" name="search_tj_sjmc" value="${tjMap.mc }">
		</dd>
	</dl>
</logic:equal>
<!-- 加入年月 end-->

<!-- 退宿时间 -->
<logic:equal name="tjMap" property="tj" value="tssj">
	<dl>
		<dt>${tjMap.mc }：</dt>
		<dd>
			<ul>
				<li>
					开始时间
					<input type="text" name="searchModel.search_tj_kssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_kssj','ymmdd');"
						id="${tjMap.tj }_kssj" onclick="return showCalendar('${tjMap.tj }_kssj','y-mm-dd');"/>
					</li>
				<li>
					结束时间
					<input type="text" name="searchModel.search_tj_jssj" class="jssj"
						onclick="return showCalendar('${tjMap.tj }_jssj','ymmdd');"
						id="${tjMap.tj }_jssj" onclick="return showCalendar('${tjMap.tj }_jssj','y-mm-dd');"/>
				</li>
			</ul>
			<input type="hidden" name="search_tj_sjmc" value="${tjMap.mc }">
		</dd>
	</dl>
</logic:equal>
<!-- 退宿时间 end-->