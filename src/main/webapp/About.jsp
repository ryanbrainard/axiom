<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<html>
<head>
    <s:include value="%{getText('app.includes.head')}" />
</head>

<body>
    <s:include value="%{getText('app.includes.body_header')}" />

        <p>
        <strong><s:text name="app.name"/> v<s:text name="version"/></strong><br/>
        Developed by Ryan Brainard
        </p>


        <p class="fineprint">
	The MIT License (MIT)
	</p>

	<p class="fineprint">
	Copyright (c) <fmt:formatDate value="${date}" pattern="yyyy" /> Ryan Brainard
	</p>

	<p class="fineprint">
	Permission is hereby granted, free of charge, to any person obtaining a copy of
	this software and associated documentation files (the "Software"), to deal in
	the Software without restriction, including without limitation the rights to
	use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
	the Software, and to permit persons to whom the Software is furnished to do so,
	subject to the following conditions:
	</p>

	<p class="fineprint">
	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.
	<p/>

	<p class="fineprint">
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
	FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
	COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
	IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
	CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
	</p>


    <s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>
