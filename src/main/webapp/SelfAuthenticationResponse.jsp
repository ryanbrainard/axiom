<%@ page contentType="text/xml; charset=UTF-8" %>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"> 
    <soapenv:Body>
    <AuthenticateResponse xmlns="urn:authentication.soap.sforce.com">
        <Authenticated>${param.auth}</Authenticated>
    </AuthenticateResponse>
    </soapenv:Body>
</soapenv:Envelope>
