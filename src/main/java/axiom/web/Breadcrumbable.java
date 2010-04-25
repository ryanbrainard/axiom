package axiom.web;

import java.util.List;

public interface Breadcrumbable {
	public List<Breadcrumb> getBreadcrumbs();
	public Breadcrumbable getParentPage();
}
