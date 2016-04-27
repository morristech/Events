package com.alexvasilkov.events;

import android.support.test.runner.AndroidJUnit4;

import com.alexvasilkov.events.internal.Dispatcher;
import com.alexvasilkov.events.internal.EventsParams;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ClassInterfaceTest {

    private static final String INTERNAL_PACKAGE = "com.alexvasilkov.events.internal.";

    @SuppressWarnings({ "ConstantConditions", "deprecation" })
    @Test
    public void testDeprecatedInit() {
        Events.init(null);
    }

    /**
     * Tests that classes has correct accessibility modifiers.
     * It also fixes missing jococo coverage for private constructors.
     */
    @Test
    public void testConstructorsPrivate() throws Exception {
        checkPrivate(Events.class);
        checkPrivate(Dispatcher.class);
        checkPrivate(EventsParams.class);
        checkPrivate(ListUtils.class);
        checkPrivate(INTERNAL_PACKAGE + "EventMethodsHelper");
        checkPrivate(INTERNAL_PACKAGE + "Utils");
    }

    private void checkPrivate(Class<?> clazz) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    private void checkPrivate(String className) throws Exception {
        checkPrivate(Class.forName(className));
    }

}
