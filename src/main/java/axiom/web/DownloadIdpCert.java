package axiom.web;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;

public class DownloadIdpCert extends SamlIdpSupport {

    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(DownloadIdpCert.class);

    private InputStream idpCertStream;

    public InputStream getIdpCertStream() {
        return idpCertStream;
    }

    @Override
    public String execute() throws Exception {
        idpCertStream = new FileInputStream(getIdpConfig().getXmlObjectSigner().getCertFile());
        return super.execute();
    }

    @Override
    public Breadcrumbable getParentPage() {
        return new SamlIdpHome();
    }

}
