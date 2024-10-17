package nhuquynh.demospringboot.configs;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(final SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "web.jsp");
        builder.addDecoratorPath("/admin/*", "/admin.jsp");

        builder.addExcludedPath("/v1/api/*");
    }
}
