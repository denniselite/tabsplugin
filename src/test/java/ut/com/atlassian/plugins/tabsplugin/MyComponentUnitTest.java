package ut.com.atlassian.plugins.tabsplugin;

import org.junit.Test;
import com.atlassian.plugins.tabsplugin.MyPluginComponent;
import com.atlassian.plugins.tabsplugin.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}