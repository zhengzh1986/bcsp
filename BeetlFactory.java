package com.sf.bcsp.base.export.excel;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.Map;

public class BeetlFactory {

     StringTemplateResourceLoader resourceLoader ;
     Configuration cfg;
     GroupTemplate gt;
     static BeetlFactory factory = new BeetlFactory();

    public BeetlFactory() {
        resourceLoader= new StringTemplateResourceLoader();
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gt = new GroupTemplate(resourceLoader, cfg);
    }

    public String render(String templateString, Map<String,Object> mapData)
    {
        Template t = gt.getTemplate(templateString);
        t.binding(mapData);
        return t.render();
    }

    public static BeetlFactory beetlFactory(){
        return factory;
    }
}
