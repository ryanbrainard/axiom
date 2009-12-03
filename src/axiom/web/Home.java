package axiom.web;

import org.apache.log4j.Logger;

public class Home extends AxiomSupport implements Breadcrumbable {
	
    @SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(Home.class);

	@Override
	public Breadcrumbable getParentPage() {
		return null;
	}

}
