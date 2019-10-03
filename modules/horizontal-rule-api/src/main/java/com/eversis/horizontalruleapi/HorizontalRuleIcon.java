package com.eversis.horizontalruleapi;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

@Component(
        property = {"editor.name=ckeditor",
                "service.ranking:Integer=100"},
        service = EditorConfigContributor.class
)
public class HorizontalRuleIcon extends BaseEditorConfigContributor {

    @Override
    public void populateConfigJSONObject(JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
            ThemeDisplay themeDisplay, RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

        String extraPlugins = jsonObject.getString("extraPlugins");

        if(!extraPlugins.contains("horizontalrule")){
            extraPlugins += ",horizontalrule";
        }

        jsonObject.put("extraPlugins", extraPlugins);

        JSONArray toolbarsJSONArray = jsonObject.getJSONArray("toolbar_liferay");

        JSONArray rule = JSONFactoryUtil.createJSONArray();
        rule.put("HorizontalRule");

        toolbarsJSONArray.put("/");

        toolbarsJSONArray.put(rule);

        jsonObject.put("toolbar_liferay", toolbarsJSONArray);
    }
}