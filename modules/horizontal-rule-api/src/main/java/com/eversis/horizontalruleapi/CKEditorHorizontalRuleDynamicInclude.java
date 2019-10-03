package com.eversis.horizontalruleapi;

import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pleczycki
 */

@Component(property = {"editor.name=alloyeditor", "editor.name=ckeditor"}, immediate = true, service = DynamicInclude.class)
public class CKEditorHorizontalRuleDynamicInclude extends BaseDynamicInclude {

    @Override
    public void include(
            HttpServletRequest request, HttpServletResponse response,
            String key)
            throws IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                WebKeys.THEME_DISPLAY);

        PrintWriter printWriter = response.getWriter();

        StringBundler sb = new StringBundler(7);

        sb.append("<script src=\"");
        sb.append(themeDisplay.getPortalURL());
        sb.append(PortalUtil.getPathProxy());
        sb.append(_servletContext.getContextPath());
        sb.append("/horizontal-rule/plugin.js");
        sb.append("\" ");
        sb.append("type=\"text/javascript\"></script>");

        System.out.println("theme = " + themeDisplay.getPortalURL());
        System.out.println("proxy = " + PortalUtil.getPathProxy());
        System.out.println("context = " + _servletContext.getContextPath());
        System.out.println(sb.toString());

        printWriter.println(sb.toString());
    }

    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
        dynamicIncludeRegistry.register(
                "com.liferay.frontend.editor.ckeditor.web#ckeditor#additionalResources");
        System.out.println("register");
    }

    @Reference(
            target = "(osgi.web.symbolicname=com.eversis.horizontalruleapi)",
            unbind = "-"
    )
    protected void setServletContext(ServletContext servletContext) {
        _servletContext = servletContext;
    }

    private ServletContext _servletContext;
}