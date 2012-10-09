<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:include value="%{getText('app.includes.head')}" />
</head>

<body>
    <s:include value="%{getText('app.includes.body_header')}" />


        <ul>
            <li><span class="mainmenu"><s:text name="label.configure"/></span><br/>
            Ensure Salesforce is configured for Delegated Authentication Single Sign-On with this service:</li>
                <ul>
                    <li>Delegated Authentication Single Sign-On must be activated for the organization.</li>
                    <li>Enable the <em>Is Single Sign-On Enabled</em> profile permission for the users who should be re-directed to this service.</li>
                    <li>
                        Go to <em>Setup | Security Controls | Single Sign-On Settings | Delegated authentication</em>
                        and set the <em>Delegated Gateway URL</em> to this page's URL with the following parameters appended:
                        <ul>
                            <li><strong>auth</strong> - authentication response string requested [true, false] (required)</li>
                            <li><strong>wait</strong> - milliseconds to wait before sending response (default=0)</li>
                        </ul>
                        <p>For example, to authenticate all requests after three seconds, the endpoint of the service would be formatted as:</p>
                        <p style="font-family: monospace,courier;"><s:text name="ServiceEndpoint"/>?auth=true&wait=3000</p>
                    </li>

                </ul>

            <li><span class="mainmenu"><s:text name="label.login"/></span><br/>
            Using any login method, attempt to login as a user who belongs a Single Sign-On enabled profile.
            The password value will be ignored, and the response will contain the <span style="font-family: monospace,courier;">auth</span>
            parameter value from the Delegated Gateway URL in an AuthenticationResponse SOAP message.</li>

    </ul>


    <s:include value="%{getText('app.includes.body_footer')}" />
</body>

</html>