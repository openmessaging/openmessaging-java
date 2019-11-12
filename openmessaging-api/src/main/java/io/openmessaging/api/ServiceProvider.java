/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.openmessaging.api;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ServiceProvider {

    /**
     * A reference to the classloader that loaded this class. It's more efficient to compute it once and cache it here.
     */
    private static ClassLoader thisClassLoader;

    /**
     * JDK1.3+ <a href= "http://java.sun.com/j2se/1.3/docs/guide/jar/jar.html#Service%20Provider" > 'Service Provider' specification</a>.
     */
    public static final String OMS_DRIVER = "META-INF/service/io.openmessaging.api.ONSFactoryAPI";

    static {
        thisClassLoader = getClassLoader(ServiceProvider.class);
    }

    /**
     * Returns a string that uniquely identifies the specified object, including its class.
     * <p>
     * The returned string is of form "classname@hashcode", ie is the same as the return value of the Object.toString() method, but works even when the specified object's class has overidden the toString method.
     *
     * @param o may be null.
     * @return a string of form classname@hashcode, or "null" if param o is null.
     */
    protected static String objectId(Object o) {
        if (o == null) {
            return "null";
        } else {
            return o.getClass().getName() + "@" + System.identityHashCode(o);
        }
    }

    protected static ClassLoader getClassLoader(Class<?> clazz) {
        try {
            return clazz.getClassLoader();
        } catch (SecurityException e) {
            throw e;
        }
    }

    protected static ClassLoader getContextClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (SecurityException ex) {
            /**
             * The getContextClassLoader() method throws SecurityException when the context
             * class loader isn't an ancestor of the calling class's class
             * loader, or if security permissions are restricted.
             */
        }
        return classLoader;
    }

    protected static InputStream getResourceAsStream(ClassLoader loader, String name) {
        if (loader != null) {
            return loader.getResourceAsStream(name);
        } else {
            return ClassLoader.getSystemResourceAsStream(name);
        }
    }

    public static <T> List<T> load(String name, Class<?> clazz) {
        List<T> services = new ArrayList<T>();
        try {
            ArrayList<String> names = new ArrayList<String>();
            final InputStream is = getResourceAsStream(getContextClassLoader(), name);
            if (is != null) {
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                } catch (java.io.UnsupportedEncodingException e) {
                    reader = new BufferedReader(new InputStreamReader(is));
                }
                String serviceName = reader.readLine();
                while (serviceName != null && !"".equals(serviceName)) {
                    if (!names.contains(serviceName)) {
                        names.add(serviceName);
                    }

                    services.add((T) initService(getContextClassLoader(), serviceName, clazz));

                    serviceName = reader.readLine();
                }
                reader.close();
            } else {
                // is == null
            }
        } catch (Exception e) {
        }
        return services;
    }

    protected static <T> T initService(ClassLoader classLoader, String serviceName, Class<?> clazz) {
        Class<?> serviceClazz = null;
        try {
            if (classLoader != null) {
                try {
                    // Warning: must typecast here & allow exception to be generated/caught & recast properly
                    serviceClazz = classLoader.loadClass(serviceName);
                    if (clazz.isAssignableFrom(serviceClazz)) {

                    } else {

                    }
                    return (T) serviceClazz.newInstance();
                } catch (ClassNotFoundException ex) {
                    if (classLoader == thisClassLoader) {
                        // Nothing more to try, onwards.
                        throw ex;
                    }
                    // Ignore exception, continue
                } catch (NoClassDefFoundError e) {
                    if (classLoader == thisClassLoader) {
                        throw e;
                    }
                    // Ignore exception, continue
                }
            }
        } catch (Exception e) {
        }
        return (T) serviceClazz;
    }
}
