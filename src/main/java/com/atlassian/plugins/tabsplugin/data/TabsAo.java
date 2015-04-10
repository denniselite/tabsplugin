package com.atlassian.plugins.tabsplugin.data;

import net.java.ao.Entity;
import net.java.ao.Preload;

//entity for data storage
@Preload
public interface TabsAo extends Entity
{
    String getString();

    void setString(String stringData);

    String getDate();

    void setDate(String dateData);
}